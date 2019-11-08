package physicalobject;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import physicalobject.PhysicalObject;

public class PlanetTest {

  PhysicalObject earth = PhysicalObject.planetcreator("earth", "solid", "blue", 6371, 10, "CW");
  PhysicalObject earth2 = PhysicalObject.planetcreator("earth", "solid", "blue", 6371, 10, "CW");
  // Testing strategy:
  // 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。

  @Test
  public void testGetform() {
    String form = "solid";
    assertEquals(form, earth.getform());
  }

  @Test
  public void testGetcolor() {
    String color = "blue";
    assertEquals(color, earth.getcolor());
  }

  @Test
  public void testGetplanetr() {
    int planetr = 6371;
    assertEquals(planetr, earth.getplanetr().intValue());
  }

  @Test
  public void testGetv() {
    int v = 10;
    assertEquals(v, earth.getv().intValue());
  }

  @Test
  public void testGetdirection() {
    String direction = "CW";
    assertEquals(direction, earth.getdirection());
  }

  @Test
  public void testGetname() {
    String name = "earth";
    assertEquals(name, earth.getname());
  }

  @Test
  public void testEqualsPhysicalObject() {
    assertEquals(true, earth.equals(earth2));
  }



}
