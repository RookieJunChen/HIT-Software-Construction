package assist;

/**
 * 辅助类，用于将输入的number类字符串转化为java中已有的Number类.
 * 
 * @author junbaba
 *
 */
public class NumBer {

  private final Number num;

  // Abstraction function:
  // 无

  // Representation invariant:
  // num != null

  // Safety from rep exposure:
  // 通过private使其它类中无法得知本类中的rep

  // TODO checkRep
  public void checkRep() {
    assert num != null;
  }


  // TODO constructor
  /**
   * constructor.
   * 
   * @param str String
   */
  public NumBer(String str) {
    String[] nums = str.split("e");
    if (nums.length == 1) {
      this.num = Double.valueOf(nums[0]);
    } else if (nums.length == 2) {
      double v1 = 0;
      double v2 = 0;
      if ((v1 = Double.valueOf(nums[0])) >= 1 && v1 <= 9 && (v2 = Double.valueOf(nums[1])) >= 3) {
        this.num = v1 * Math.pow(10, v2);
      } else {
        throw new UnsupportedOperationException();
      }
    } else {
      throw new UnsupportedOperationException();
    }
  }


  /**
   * 返回num的int类值.
   * 
   * @return num的int类值
   */
  public int intValue() {
    return num.intValue();
  }

  /**
   * 返回num的double类值.
   * 
   * @return num的double类值
   */
  public double doubleValue() {
    return num.doubleValue();
  }



}
