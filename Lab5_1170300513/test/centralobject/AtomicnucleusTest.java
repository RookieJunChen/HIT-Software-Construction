package centralobject;

import static org.junit.Assert.assertEquals;

import centralobject.CentralObject;
import org.junit.Test;


public class AtomicnucleusTest {

  CentralObject nucleus = CentralObject.atomcreator("Na");
  CentralObject nucleus2 = CentralObject.atomcreator("Na");

  // Testing strategy:
  // 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。

  @Test
  public void testGetname() {
    String n = "Na";
    assertEquals(n, nucleus.getname());
  }


  @Test
  public void testEqualsCentralObject() {
    assertEquals(true, nucleus.equals(nucleus2));
  }

}
