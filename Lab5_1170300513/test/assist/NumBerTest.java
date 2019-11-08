package assist;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumBerTest {


  // Testing strategy:
  // 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。

  @Test
  public void testIntValue() {
    NumBer num = new NumBer("23");
    assertEquals(23, num.intValue());
  }

  @Test
  public void testDoubleValue() {

    NumBer num = new NumBer("2E7");
    double answer = 2.0E7;
    assertEquals(0, (int) (answer - num.doubleValue()));
  }

}
