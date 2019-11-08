package adt;


import java.util.logging.Logger;

import climbstrategy.ClimbStrategy;
import program.MonkeyThroughRiver;

public class Monkey extends Thread {

  Logger log = MonkeyThroughRiver.log;
  private final int id;
  private final String direction;
  private final int velocity;


  // Abstraction function:
  // id对应猴子编号，direction对应猴子过河方向，velocity对应猴子爬梯子的速度

  // Representation invariant:
  // direction != null
  // 1 <= velocity <= MV

  // Safety from rep exposure:
  // 通过private使其它类中无法得知本类中的rep

  // Thread safety:
  // 在run方法中，对共享的、可能会发生线程间竞争的类在使用时上锁，避免竞争（同时上锁的范围要尽可能小，避免变成串行影响性能）


  public Monkey(int id, String direction, int velocity) {
    this.id = id;
    this.direction = direction;
    this.velocity = velocity;
  }


  /**
   * 获得id的方法.
   * 
   * @return id
   */
  public int getid() {
    return id;
  }

  /**
   * 获得速度v的方法.
   * 
   * @return v
   */
  public int getv() {
    return velocity;
  }

  /**
   * 获得方向direction的方法
   * 
   * @return direction
   */
  public String getdirection() {
    return direction;
  }


  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("<monkey:" + id + "," + velocity + "step/s," + direction + ">");
    return str.toString();
  }



  @Override
  public void run() {
    ClimbStrategy strategy = ClimbStrategy.empty(MonkeyThroughRiver.strategynum);
    long start = System.currentTimeMillis();
    int from = 1;
    Ladder ladder = strategy.chooseladder(direction, this);
    int laddernum = MonkeyThroughRiver.ladders.indexOf(ladder);
    if (direction.equals("L->R")) {
      from = 1;
    } else {
      from = ladder.getladder().size();
    }
    while (true) {
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        e.printStackTrace();
      }
      int endplace = ladder.move(direction, from, velocity);
      long end = System.currentTimeMillis();
      long delta = (end - start) / 1000;
      if (endplace == -1) {
        String line = this.toString() + " arrived at the opposite bank " + "from the " + laddernum
            + " ladder." + "(" + delta + "s from the birth)";
        log.info(line);
        synchronized (MonkeyThroughRiver.loggings) {
          MonkeyThroughRiver.loggings.append(line + "\n");
        }
        break;
      } else {
        String line = this.toString() + " move from " + from + "step to " + endplace + "step "
            + "in the " + laddernum + " ladder." + "(" + delta + "s from the birth)";
        log.info(line);
        synchronized (MonkeyThroughRiver.loggings) {
          MonkeyThroughRiver.loggings.append(line + "\n");
        }
        from = endplace;
      }

    }
    synchronized (MonkeyThroughRiver.finishsum) {
      MonkeyThroughRiver.finishsum++;
    }
    synchronized (MonkeyThroughRiver.arriveorder) {
      MonkeyThroughRiver.arriveorder.add(this.id);

    }
  }
}
