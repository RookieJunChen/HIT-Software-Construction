package exceptions;

/**
 * 用于描述轨道上出现标签相同错误的异常.
 * 
 * @author junbaba
 *
 */
public class TheSameTagRException extends Exception {
  private Object obj = null;

  public TheSameTagRException(Object o) {
    this.obj = o;
  }

  /**
   * 展示错误.
   */
  public String showwrong() {
    StringBuilder str = new StringBuilder();
    str.append(obj.toString() + " is already in the CircularOrbit System!");
    return str.toString();
  }
}
