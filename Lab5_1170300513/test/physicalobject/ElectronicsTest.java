package physicalobject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import physicalobject.PhysicalObject;
import track.Track;

public class ElectronicsTest {

  PhysicalObject e1 = PhysicalObject.electronicscreator();
  PhysicalObject e2 = PhysicalObject.electronicscreator();

  // Testing strategy:
  // 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。

  @Test
  public void testGetname() {
    String name = "Electronics";
    assertEquals(name, e1.getname());
    Efactory fac = Efactory.getfactory();
    fac.gete(e1, Track.roundcreator(3));
  }

  @Test
  public void testEqualsPhysicalObject() {
    assertEquals(true, e1.equals(e1));
    assertEquals(false, e1.equals(e2));
  }
}
