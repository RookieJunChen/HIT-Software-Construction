package climbstrategy;


import java.util.logging.Logger;

import adt.Ladder;
import adt.Monkey;
import program.MonkeyThroughRiver;

public class ChooseTheFastest implements ClimbStrategy {

  Logger log = MonkeyThroughRiver.log;

  // Thread safety:
  // 对共享的、可能会发生线程间竞争的类在使用时上锁，避免竞争（同时上锁的范围要尽可能小，避免变成串行影响性能）

  @Override
  public Ladder chooseladder(String direction, Monkey monkey) {
    Ladder chosenladder = null;
    long start = System.currentTimeMillis();
    while (chosenladder == null) {
      int longestj = -1;
      synchronized (MonkeyThroughRiver.ladders) {
        int remlongest = 0;
        for (int j = 0; j < MonkeyThroughRiver.ladders.size(); j++) {
          Ladder ladder = MonkeyThroughRiver.ladders.get(j);
          synchronized (ladder) {
            int length = ladder.getladder().size();
            int flag1 = 0, flag2 = 0;
            int shortest = 0;
            for (int i = 0; i < length; i++) {
              if (ladder.getladder().get(i + 1) != null) {
                flag1 = 1;
                Monkey mk = ladder.getladder().get(i + 1);
                if (!mk.getdirection().equals(direction)) {
                  flag2 = 1;
                }
              }
            }
            if (direction.equals("L->R") && flag2 == 0) {
              for (int i = 0; i < length; i++) {
                if (ladder.getladder().get(i + 1) != null) {
                  shortest = i;
                  break;
                }
              }
            } else if (flag2 == 0) {
              for (int i = length; i >= 1; i--) {
                if (ladder.getladder().get(i) != null) {
                  shortest = length - i;
                  break;
                }
              }
            }

            if (flag1 == 0) {
              if (monkey.getdirection().equals("L->R")) {
                ladder.upfromL(monkey);
              } else {
                ladder.upfromR(monkey);
              }
              long end = System.currentTimeMillis();
              long delta = (end - start) / 1000;
              int laddernum = MonkeyThroughRiver.ladders.indexOf(ladder);
              String line = monkey + " climbed up the " + laddernum + " ladder." + "(" + delta
                  + "s from the birth)";
              log.info(line);
              synchronized (MonkeyThroughRiver.loggings) {
                MonkeyThroughRiver.loggings.append(line + "\n");
              }
              chosenladder = ladder;
              break;
            }
            if (flag1 == 1 && flag2 == 0) {
              if (remlongest < shortest) {
                remlongest = shortest;
                if (remlongest != 0) {
                  longestj = j;
                }
              }
            }
          }

        }
      }
      if (chosenladder != null) {
        break;
      } else if (longestj != -1) {
        if (monkey.getdirection().equals("L->R")) {
          MonkeyThroughRiver.ladders.get(longestj).upfromL(monkey);
        } else {
          MonkeyThroughRiver.ladders.get(longestj).upfromR(monkey);
        }
        long end = System.currentTimeMillis();
        long delta = (end - start) / 1000;
        String line =
            monkey + " climbed up the " + longestj + " ladder." + "(" + delta + "s from the birth)";
        log.info(line);
        synchronized (MonkeyThroughRiver.loggings) {
          MonkeyThroughRiver.loggings.append(line + "\n");
        }
        chosenladder = MonkeyThroughRiver.ladders.get(longestj);
        break;
      }
      try {
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        long delta = (end - start) / 1000;
        String[] sides = direction.split("->");
        String line = monkey.toString() + " still wait on the " + sides[0] + "." + "(" + delta
            + "s from the birth)";
        log.info(line);
        synchronized (MonkeyThroughRiver.loggings) {
          MonkeyThroughRiver.loggings.append(line + "\n");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return chosenladder;
  }

}
