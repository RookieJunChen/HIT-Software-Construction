package relationship;

import static org.junit.Assert.*;

import org.junit.Test;

import physicalObject.PhysicalObject;

public class SocialtieTest {

	PhysicalObject p = PhysicalObject.Q3creator();
	Socialtie<PhysicalObject> s = new Socialtie<PhysicalObject>(p, 1);
	
	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	@Test
	public void testGetobj() {
		assertEquals(p, s.getobj());
	}

	@Test
	public void testGetIntimacy() {
		assertEquals(1, s.getIntimacy());
	}

}
