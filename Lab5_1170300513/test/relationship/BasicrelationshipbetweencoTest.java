package relationship;

import static org.junit.Assert.assertEquals;

import centralobject.CentralObject;
import org.junit.Test;
import physicalobject.PhysicalObject;

public class BasicrelationshipbetweencoTest {

  PhysicalObject physicalobject = PhysicalObject.electronicscreator();
  CentralObject centralObject = CentralObject.atomcreator("Na");

  // Testing strategy:
  // 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。


  @Test
  public void testGetc() {
    Basicrelationshipbetweenco<CentralObject, PhysicalObject> rel =
        new Basicrelationshipbetweenco<>(centralObject);
    assertEquals(centralObject, rel.getc());
  }

  @Test
  public void testGetrel() {
    Basicrelationshipbetweenco<CentralObject, PhysicalObject> rel =
        new Basicrelationshipbetweenco<>(centralObject);
    rel.addconnection(physicalobject, 0.2);
    assertEquals(physicalobject, rel.getrel().get(0).getobj());
  }

  @Test
  public void testAddconnection() {
    Basicrelationshipbetweenco<CentralObject, PhysicalObject> rel =
        new Basicrelationshipbetweenco<>(centralObject);
    rel.addconnection(physicalobject, 0.1);
    assertEquals(physicalobject, rel.getrel().get(0).getobj());
  }

}
