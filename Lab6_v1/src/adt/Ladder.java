package adt;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import program.MonkeyThroughRiver;

/**
 * 用于模拟梯子的类.
 * 
 * @author junbaba
 *
 */
public class Ladder {

  private final Map<Integer, Monkey> ladder = Collections.synchronizedMap(new HashMap<>());
  private Logger log = MonkeyThroughRiver.log;

  // Abstraction function:
  // ladder中每一个元素对应梯子上的一个台阶.

  // Representation invariant:
  // ladder != null

  // Safety from rep exposure:
  // 通过private使其它类中无法得知本类中的rep

  // Thread safety:
  // 在可能发生竞争的方法处对该方法上锁，使得只有某个线程能够调用该方法；使用线程安全的类型Collections.synchronizedMap.


  /**
   * 构造方法.
   */
  public Ladder(int h) {
    for (int i = 1; i <= h; i++) {
      ladder.put(i, null);
    }
  }


  /**
   * 返回表示梯子的Map.
   * 
   * @return 表示梯子的Map.
   */
  public Map<Integer, Monkey> getladder() {
    return ladder;
  }


  /**
   * 让猴子从左岸上楼梯（此时要对该方法上锁）.
   */
  public synchronized void upfromL(Monkey monkey) {
    if (ladder.get(1) == null) {
      ladder.replace(1, monkey);
      log.info(monkey + " climb the ladder from L.");
    }
  }

  /**
   * 让猴子从右岸上楼梯（此时要对该方法上锁）.
   */
  public synchronized void upfromR(Monkey monkey) {
    if (ladder.get(ladder.size()) == null) {
      ladder.replace(ladder.size(), monkey);
      log.info(monkey + " climb the ladder from L.");
    }
  }

  /**
   * 模拟猴子在阶梯上移动的方法.
   * 
   * @return 移动的终点（如果超出梯子范围返回-1）.
   */
  public synchronized int move(String direction, int from, int distance) {
    int end = from;
    if (direction.equals("L->R")) {
      for (int i = 1; i <= distance; i++) {
        if (from + i > ladder.size()) {
          end = -1;
          break;
        } else if (ladder.get(from + i) != null) {
          end = from + i - 1;
          break;
        } else {
          end = from + i;
        }
      }
    } else {
      for (int i = 1; i <= distance; i++) {
        if (from - i <= 0) {
          end = -1;
          break;
        } else if (ladder.get(from - i) != null) {
          end = from - i + 1;
          break;
        } else {
          end = from - i;
        }
      }
    }
    if (end == -1) {
      ladder.replace(from, null);
    } else if (end != from) {
      ladder.replace(end, ladder.get(from));
      ladder.replace(from, null);
    }
    return end;
  }

  public synchronized boolean isempty() {
    int flag = 0;
    for (int i = 1; i <= this.ladder.size(); i++) {
      if (ladder.get(i) != null) {
        flag = 1;
      }
    }
    if (flag == 0) {
      return true;
    } else {
      return false;
    }
  }

}
