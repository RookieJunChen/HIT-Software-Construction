package circularOrbit;

import static org.junit.Assert.*;

import org.junit.Test;

import centralObject.CentralObject;
import physicalObject.PhysicalObject;

public class SocialNetworkCircleTest {

	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	//关系类的测试，通过物体的轨道变化间接测试其正确性。（同时保证物体的轨道无误）
	
	@Test
	public void testAddcontactco() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
		try {
			cir.readfile("test SocialNetworkCircle.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(3, cir.getorbits().get(0).getthingsintrack().size());
		PhysicalObject p = cir.getorbits().get(1).getthingsintrack().get(0);
		cir.addcontactco(cir.getcenter().get(0), p, 0.6);
		assertEquals(4, cir.getorbits().get(0).getthingsintrack().size());
	}

	@Test
	public void testDeletecontactco() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
		try {
			cir.readfile("test SocialNetworkCircle.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PhysicalObject p = cir.getorbits().get(0).getthingsintrack().get(0);
		cir.deletecontactco(cir.getcenter().get(0), p);
		assertEquals(3, cir.getorbits().get(0).getthingsintrack().size());
	}

	
	@Test
	public void testAddcontactoo() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
		try {
			cir.readfile("test SocialNetworkCircle.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PhysicalObject p1 = cir.getorbits().get(0).getthingsintrack().get(0);
		PhysicalObject p2 = cir.getorbits().get(0).getthingsintrack().get(1);
		cir.deletecontactco(cir.getcenter().get(0), p1);
		cir.addcontactoo(p1, p2, 0.6);
		assertEquals(1, cir.getorbits().get(1).getthingsintrack().size());
	}

	
	
	@Test
	public void testDeletecontactoo() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
		try {
			cir.readfile("test SocialNetworkCircle.txt");
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
	}

	
	@Test
	public void testGetlogicdistance() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
		try {
			cir.readfile("test SocialNetworkCircle.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PhysicalObject p2 = cir.getorbits().get(0).getthingsintrack().get(2);
		PhysicalObject p3 = cir.getorbits().get(1).getthingsintrack().get(0);
		assertEquals(3, cir.getlogicdistance(p2, p3));
	}

	@Test
	public void testGetinfodiffu() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
		try {
			cir.readfile("test SocialNetworkCircle.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PhysicalObject p2 = cir.getorbits().get(0).getthingsintrack().get(2);
		assertEquals(1, cir.getinfodiffu(p2));
	}




	@Test
	public void testGetrelbetweenoo() 
	{
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
		try {
			cir.readfile("test SocialNetworkCircle.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(4, cir.getrelbetweenoo().size());
	}

	
	@Test
	public void testGetrelbetweenco() 
	{
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q5");
		try {
			cir.readfile("test SocialNetworkCircle.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(3, cir.getrelbetweenco().get(0).getrel().size());
	}

}
