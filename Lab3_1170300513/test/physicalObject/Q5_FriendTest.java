package physicalObject;

import static org.junit.Assert.*;

import org.junit.Test;

import assist.number;

public class Q5_FriendTest {

	PhysicalObject friend = PhysicalObject.Q5creator("CJ", "M", 20);
	PhysicalObject friend2 = PhysicalObject.Q5creator("CJ", "M", 20);
	
	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	@Test
	public void testGetname() {
		String name = "CJ";
		assertEquals(name, friend.getname());
	}

	@Test
	public void testGetsex() {
		String sexString = "M";
		assertEquals(sexString, friend.getsex());
	}

	@Test
	public void testGetages() {
		int year = 20;
		assertEquals(year, friend.getages().intValue());
	}

	@Test
	public void testEqualsPhysicalObject() {
		assertEquals(true,friend.equals(friend2) );
	}
}
