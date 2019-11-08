package climbstrategy;



import java.util.logging.Logger;

import adt.Ladder;
import adt.Monkey;
import program.MonkeyThroughRiver;

public class WaitUntilEmpty implements ClimbStrategy {

  private Logger log = MonkeyThroughRiver.log;

  // Thread safety:
  // 对共享的、可能会发生线程间竞争的类在使用时上锁，避免竞争（同时上锁的范围要尽可能小，避免变成串行影响性能）


  @Override
  public Ladder chooseladder(String direction, Monkey monkey) {
    Ladder freeladder = null;
    long start = System.currentTimeMillis();
    while (freeladder == null) {
      synchronized (MonkeyThroughRiver.ladders) {
        for (Ladder ladder : MonkeyThroughRiver.ladders) {
          synchronized (ladder) {
            if (ladder.isempty()) {
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
              freeladder = ladder;
              break;
            }
          }

        }
      }
      if (freeladder != null) {
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
    return freeladder;
  }

}
