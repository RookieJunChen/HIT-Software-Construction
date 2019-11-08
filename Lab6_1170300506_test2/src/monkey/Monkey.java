package monkey;

import java.util.concurrent.CountDownLatch;
import ladder.Ladder;
import main.Direction;

/**
 * Monkey property.
 * Immutable class.
 *
 */
public class Monkey extends Thread {
  private final int idNumber;
  private final String direction;
  private final int speed;
  private final int birthTime;

  /** 所在梯子. */
  private Ladder myLadder;
  private int age;
  /** 未上梯子：-1 . */
  private int currentLocation;
  /** 线程计数. */
  private CountDownLatch countDownLatch;

  /*
   * RI idNumber is a nature number. direction is either "L->R" or "R->L".
   * velocity is a positive integer ranges in [1,MV].
   * 
   * ThreadSafe only advance in the ladder is concurrent , and it will lock the
   * ladder to do.
   */

  /**
   * for thread.
   */
  public void run() {
    advance();
    countDownLatch.countDown();
  }

  /**
   * Generate a monkey.
   * 
   * @param idNumber  a nature number.
   * @param direction either "L->R" or "R->L".
   * @param speed  a positive integer ranges in [1,MV].
   * @param birthTime the time when the monkey borned.
   */
  public Monkey(int idNumber, String direction, int speed, int birthTime) {
    super();
    this.idNumber = idNumber;
    this.direction = direction;
    this.speed = speed;
    this.currentLocation = -1;
    this.age = 0;
    this.birthTime = birthTime;
  }

  /**
   * 新生猴子选择一个梯子并登上.
   * 
   * @return true - 得到梯子并登上.<br>
   *         false - 未得到梯子并等待.
   */
  public boolean boardLadder(Ladder ladder) {
    if (ladder == null) {
      return false;
    }
    if (!ladder.accessible(direction)) {
      return false;
    }
    // 登上梯子：将自身加入梯子.
    myLadder = ladder;
    currentLocation = ladder.getMonkey(this);
    return true;
  }

  /**
   * Lock the ladder and go ahead.
   */
  public void advance() {
    synchronized (myLadder) {
      this.currentLocation = myLadder.advance(this);
    }
  }

  /**
   * increase the age, not be call when is has been arrived the destination.
   */
  public void addAge() {
    age++;
  }

  public void setCountDownLatch(CountDownLatch countDownLatch) {
    this.countDownLatch = countDownLatch;
  }

  public int getIdNumber() {
    return idNumber;
  }

  public int getCurrentLocation() {
    return currentLocation;
  }

  public String getDirection() {
    return direction;
  }

  public int getSpeed() {
    return speed;
  }

  public int getAge() {
    return age;
  }

  public int getBirthTime() {
    return birthTime;
  }

  /**
   * whether the monkey has arrived the other side.
   * 
   * @return true - if location is -2;<br>
   *         false - else.
   */
  public boolean hasArrived() {
    if (currentLocation == -2) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idNumber;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Monkey other = (Monkey) obj;
    if (idNumber != other.idNumber) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Monkey [idNumber=" + idNumber + "]";
  }

  public String properties() {
    return "Monkey [idNumber=" + idNumber + ", direction=" + direction + ", velocity=" + speed
        + "]";
  }

  /**
   * The monkey's state on the bank.
   * 
   * @return is waiting on the bank , and has been age seconds old.
   */
  public String stateOnTheBank() {
    String bank = "";
    if (direction.equals(Direction.LR)) {
      bank = "[Left Bank]";
    } else {
      bank = "[Right Bank]";
    }
    return "Monkey [idNumber=" + idNumber + ", direction=" + direction + "]" + " is waiting on the "
        + bank + " , and has been " + age + " seconds old.";
  }

  /**
   * The monkey's state on the ladder.
   * 
   * @return the location , the direction and the age.
   */
  public String stateOnTheLadder() {
    return "Monkey [idNumber=" + idNumber + ", direction=" + direction + "]" + " is running on the "
        + myLadder.getIdNumber() + " ladder's " + (currentLocation + 1) + " rung "
        + " , and has been " + age + " seconds old.";
  }

  /**
   * The monkey's state on the destination.
   * 
   * @return has been arrived the destination from the start, and costs some
   *         seconds.
   */
  public String stateOnTheDestination() {
    return "Monkey [idNumber=" + idNumber + ", direction=" + direction + "]"
        + " has arrived the destination " + " , and costs " + age + " seconds.";
  }
}
