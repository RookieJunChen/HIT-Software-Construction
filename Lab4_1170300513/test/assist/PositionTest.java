package assist;

import static org.junit.Assert.*;

import org.junit.Test;

import physicalObject.PhysicalObject;

public class PositionTest {

	PhysicalObject p = PhysicalObject.Q3creator();
	
	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	
	@Test
	public void testGetobject() {
		Position<PhysicalObject> pos = new Position<>(p,2,3);
		assertEquals(p, pos.getobject());
	}

	@Test
	public void testGetridus() {
		Position<PhysicalObject> pos = new Position<>(p,2,3);
		assertEquals(2, pos.getridus().intValue());
	}

	@Test
	public void testGetangle() {
		Position<PhysicalObject> pos = new Position<>(p,2,3);
		assertEquals(3, pos.getangle().intValue());
	}

	@Test
	public void testChangeangle() {
		Position<PhysicalObject> pos = new Position<>(p,2,3);
		pos.changeangle(6);
		assertEquals(6, pos.getangle().intValue());
	}

	@Test
	public void testChangeridus() {
		Position<PhysicalObject> pos = new Position<>(p,2,3);
		pos.changeridus(7);;
		assertEquals(7, pos.getridus().intValue());
	}

	@Test
	public void testToString() {
		Position<PhysicalObject> pos = new Position<>(p,2,3);
		String answer = "<Electronics>: (2.0,3.0)";
		assertEquals(answer, pos.toString());
	}

}
