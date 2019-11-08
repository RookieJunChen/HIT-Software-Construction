package climbstrategy;



import adt.Ladder;
import adt.Monkey;

/**
 * Strategy模式，用于表示猴子选择阶梯的接口.
 * 
 * @author junbaba
 *
 */
public interface ClimbStrategy {

  /**
   * 静态工厂方法.
   * 
   */
  public static ClimbStrategy empty(int choice) {
    if (choice == 1) {
      return new WaitUntilEmpty();
    } else if (choice == 2) {
      return new ChooseTheFastest();
    } else {
      throw new UnsupportedOperationException();
    }
  }


  /**
   * 用于选择阶梯的方法.
   */
  public Ladder chooseladder(String direction, Monkey monkey);


}
