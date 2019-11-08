package centralObject;

import static org.junit.Assert.*;

import org.junit.Test;

public class Q3_NeutronTest {

	CentralObject p1 = CentralObject.Q3creator("Neutron");
	CentralObject p2 = CentralObject.Q3creator("Neutron");
	
	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	@Test
	public void testGetname() {
		String answer = "Neutron";
		assertEquals(answer, p1.getname());
	}

	@Test
	public void testEqualsCentralObject() {
		assertEquals(true, p1.equals(p2));
	}

}
