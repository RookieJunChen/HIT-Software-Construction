package ladder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import main.Direction;
import monkey.Monkey;

/**
 * A ladder. Immutable class.
 *
 */
public class Ladder {
  private final int idNumber;
  /** 
   * 梯子上挡板个数，从第0个开始计数. 
   */
  private final int rungs;
  private String direction = Direction.NO;
  private final Map<Integer, Monkey> monkeysInLadder = Collections.synchronizedMap(new HashMap<>());
  private int numMonkeys;

  /**
   * constructor
   * 
   * @param idNumber - the number of the ladder.
   * @param rungs    - how many rungs.
   */
  public Ladder(int idNumber, int rungs) {
    super();
    this.idNumber = idNumber;
    this.rungs = rungs;
  }

  private void checkRep() {
    if (numMonkeys != monkeysInLadder.size()) {
      System.out.println("------------------");
      System.out.println(numMonkeys);
      System.out.println(monkeysInLadder.size());
      System.out.println("------------------");
      System.exit(1);
    }
  }

  /**
   * Get the monkey advanced.
   * 
   * @param currentMonkey - to advance , and currentLocation should be >= 0.
   * @return new location
   *         -2 if arrived the other side.
   */
  public int advance(Monkey currentMonkey) {
    int currentLocation = currentMonkey.getCurrentLocation();
    int speed = currentMonkey.getSpeed();
    int targetLocation = currentLocation + speed;
    // 删除当前位置的记录
    monkeysInLadder.remove(currentLocation);

    // 扫描从当前位置到目标位置之间是否有其他monkey
    for (int i = currentLocation + 1; i <= targetLocation && i < rungs; i++) {

      // 如果当前挡板有猴子
      if (monkeysInLadder.get(i) != null) {
        // 更新位置
        monkeysInLadder.put(i - 1, currentMonkey);
        checkRep();
        return i - 1; // 前一挡板
      }
    }

    // 前无阻挡，可能脱离梯子
    if (targetLocation >= rungs) {
      // 脱离梯子
      numMonkeys--;
      checkRep();
      return -2;
    }

    // 更新位置
    // 前无阻挡，可以到达目标位置
    monkeysInLadder.put(targetLocation, currentMonkey);
    checkRep();
    return targetLocation;
  }

  /**
   * Add a new monkey to the ladder. The return of accessible should be true!
   * 
   * @param currentMonkey - aboard the ladder.
   * @return new location.
   */
  public int getMonkey(Monkey currentMonkey) {
    monkeysInLadder.put(0, currentMonkey);
    direction = currentMonkey.getDirection();
    numMonkeys++;
    checkRep();
    return 0;
  }

  /**
   * is the ladder accessible for a newborn monkey.
   * 
   * @return true - if direction is proper, there is no monkey in location 0;<br>
   *         false - else.
   */
  public boolean accessible(String monkeyDirection) {

    // 梯子有猴子 且 方向相反
    if (!monkeysInLadder.isEmpty() && !monkeyDirection.equals(direction)) {
      return false;
    }

    // 第一个踏板有猴子
    if (monkeysInLadder.get(0) != null) {
      return false;
    }

    return true;
  }

  public int getNumMonkeys() {
    return monkeysInLadder.size();
  }

  public String getDirection() {
    return direction;
  }

  public int getIdNumber() {
    return idNumber;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  /**
   * 获得当前梯子上最晚的猴子的速度.
   * 
   * @return - 最近猴子速度.
   */
  public int getFirstSpeed() {
    for (int i = 0; i < rungs; i++) {
      if (monkeysInLadder.keySet().contains(new Integer(i))) {
        return monkeysInLadder.get(i).getSpeed();
      }
    }
    return 127; // 由于SortedMap限制 最大为127
  }

  @Override
  public String toString() {
    return "Ladder [idNumber=" + idNumber + ", direction=" + direction + "]";
  }

  /**
   * 信息可视化.
   * 
   * @return - 用string动态显示.
   */
  public String toView() {
    String view = "";

    if (direction.equals(Direction.RL)) {
      for (int i = rungs - 1; i >= 0; i--) {
        if (monkeysInLadder.keySet().contains(new Integer(i))) {
          view += " < " + monkeysInLadder.get(new Integer(i)).getIdNumber();
        } else {
          view += " < __";
        }
      }
    } else {
      for (int i = 0; i < rungs; i++) {
        if (monkeysInLadder.keySet().contains(new Integer(i))) {
          view += " > " + monkeysInLadder.get(new Integer(i)).getIdNumber();
        } else {
          view += " > __";
        }
      }
    }
    return view;
  }
}
