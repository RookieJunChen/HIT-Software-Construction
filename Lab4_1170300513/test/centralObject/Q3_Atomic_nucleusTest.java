package centralObject;

import static org.junit.Assert.*;

import org.junit.Test;

import assist.number;

public class Q3_Atomic_nucleusTest {

	CentralObject nucleus = CentralObject.Q3creator("Na");
	CentralObject nucleus2 = CentralObject.Q3creator("Na");
	
	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	@Test
	public void testGetname() {
		String n = "Na";
		assertEquals(n, nucleus.getname());
	}


	@Test
	public void testEqualsCentralObject()
	{
		assertEquals(true, nucleus.equals(nucleus2));
	}

}
