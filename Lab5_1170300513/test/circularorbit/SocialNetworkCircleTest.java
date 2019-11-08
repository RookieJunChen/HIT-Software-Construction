package circularorbit;

import static org.junit.Assert.assertEquals;

import centralobject.CentralObject;
import circularorbit.CircularOrbit;
import exceptions.ReadFileFailException;
import org.junit.Test;
import physicalobject.PhysicalObject;

public class SocialNetworkCircleTest {

  // Testing strategy:
  // 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
  // 关系类的测试，通过物体的轨道变化间接测试其正确性。（同时保证物体的轨道无误）

  @Test
  public void testAddcontactco() {
    CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircle.txt", 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals(3, cir.getorbits().get(0).getthingsintrack().size());
    PhysicalObject p = cir.getorbits().get(1).getthingsintrack().get(0);
    cir.addcontactco(cir.getcenter(), p, 0.6);
    assertEquals(4, cir.getorbits().get(0).getthingsintrack().size());
  }

  @Test
  public void testDeletecontactco() {
    CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircle.txt", 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals(3, cir.getorbits().get(0).getthingsintrack().size());
    PhysicalObject p = cir.getorbits().get(0).getthingsintrack().get(0);
    cir.deletecontactco(cir.getcenter(), p);
    assertEquals(2, cir.getorbits().get(0).getthingsintrack().size());
  }


  @Test
  public void testAddcontactoo() {
    CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircle.txt", 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
    PhysicalObject p1 = cir.getorbits().get(0).getthingsintrack().get(0);
    PhysicalObject p2 = cir.getorbits().get(0).getthingsintrack().get(1);
    cir.deletecontactco(cir.getcenter(), p1);
    assertEquals(1, cir.getorbits().get(1).getthingsintrack().size());
    cir.addcontactoo(p1, p2, 0.6);
    assertEquals(2, cir.getorbits().get(1).getthingsintrack().size());
  }



  @Test
  public void testDeletecontactoo() {
    CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircle.txt", 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
    PhysicalObject p1 = cir.getorbits().get(0).getthingsintrack().get(1);
    PhysicalObject p2 = cir.getorbits().get(0).getthingsintrack().get(2);
    PhysicalObject p3 = cir.getorbits().get(1).getthingsintrack().get(0);
    assertEquals(2, cir.getorbits().size());
    cir.deletecontactoo(p1, p3);
    cir.deletecontactoo(p2, p3);
    assertEquals(1, cir.getorbits().size());
    cir.deletecontactoo(p1, p2);
    PhysicalObject p4 = cir.getorbits().get(0).getthingsintrack().get(0);
    cir.deletecontactoo(p3, p4);
    cir.deletecontactoo(p1, p4);
    cir.deletecontactoo(p2, p4);
    cir.deletecontactco(cir.getcenter(), p1);
    cir.deletecontactco(cir.getcenter(), p2);
    cir.deletecontactco(cir.getcenter(), p3);
    cir.deletecontactco(cir.getcenter(), p4);
  }


  @Test
  public void testGetlogicdistance() {
    CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircle.txt", 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
    PhysicalObject p2 = cir.getorbits().get(0).getthingsintrack().get(2);
    PhysicalObject p3 = cir.getorbits().get(1).getthingsintrack().get(0);
    assertEquals(1, cir.getlogicdistance(p2, p3));
  }

  @Test
  public void testGetinfodiffu() {
    CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircle.txt", 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
    PhysicalObject p2 = cir.getorbits().get(0).getthingsintrack().get(2);
    assertEquals(1, cir.getinfodiffu(p2));
  }



  @Test
  public void testGetrelbetweenoo() {
    CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircle.txt", 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals(4, cir.getrelbetweenoo().size());
  }


  @Test
  public void testGetrelbetweenco() {
    CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircle.txt", 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals(3, cir.getrelbetweenco().get(0).getrel().size());
  }

  @Test
  public void testWrongtxt() {
    CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircleWrong.txt", 3);
    } catch (ReadFileFailException e) {
      ReadFileFailException exp = new ReadFileFailException();
      assertEquals(exp.getClass(), e.getClass());
    } catch (Exception e) {
      System.out.println("The path is not valid, please re-enter it.");
    }
    try {
      cir.islegal();
    } catch (Exception e) {
      // TODO: handle exception
    }
    cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircleWrong2.txt", 3);
    } catch (ReadFileFailException e) {
      ReadFileFailException exp = new ReadFileFailException();
      assertEquals(exp.getClass(), e.getClass());
    } catch (Exception e) {
      System.out.println("The path is not valid, please re-enter it.");
    }
    cir = CircularOrbit.empty("Q5");
    try {
      cir.readfile("test SocialNetworkCircleWrong3.txt", 3);
    } catch (ReadFileFailException e) {
      ReadFileFailException exp = new ReadFileFailException();
      assertEquals(exp.getClass(), e.getClass());
    } catch (Exception e) {
      System.out.println("The path is not valid, please re-enter it.");
    }
    cir = CircularOrbit.empty("Q5");
    try {
      cir.addcenterobject(null);
    } catch (Throwable e) {
      // TODO: handle exception
    }
    try {
      cir.deletecentralobj(null);
    } catch (Throwable e) {
      // TODO: handle exception
    }
    try {
      cir.deletecontactco(null, null);
    } catch (Throwable e) {
      // TODO: handle exception
    }
    try {
      cir.deleteorbitobj(null);
    } catch (Throwable e) {
      // TODO: handle exception
    }
    try {
      cir.deletecontactoo(null, null);
    } catch (Throwable e) {
      // TODO: handle exception
    }
    try {
      cir.addtoorbit(null, null);
    } catch (Throwable e) {
      // TODO: handle exception
    }
    try {
      cir.addcontactoo(null, null, null);
    } catch (Throwable e) {
      // TODO: handle exception
    }
    try {
      cir.addcontactco(null, null, null);
    } catch (Throwable e) {
      // TODO: handle exception
    }
  }

}
