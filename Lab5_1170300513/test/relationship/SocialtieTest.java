package relationship;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import physicalobject.PhysicalObject;

public class SocialtieTest {

  PhysicalObject physicalObject = PhysicalObject.electronicscreator();
  Socialtie<PhysicalObject> socialtie = new Socialtie<PhysicalObject>(physicalObject, 1);

  // Testing strategy:
  // 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。

  @Test
  public void testGetobj() {
    assertEquals(physicalObject, socialtie.getobj());
  }

  @Test
  public void testGetIntimacy() {
    assertEquals(1, socialtie.getIntimacy());
  }

}
