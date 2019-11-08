package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {
	// Testing strategy:
	// 构造特例以实现执行每行代码提高覆盖率;
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	Piece p = new Piece("White", "CJ");
	Point point = new Point(1,2);
	Position test = new Position(point);
	@Test
	public void testGetpos() {
		assertEquals(1, test.getpos().x);
		assertEquals(2, test.getpos().y);
		assertEquals(true, test.isempty);
	}

	@Test
	public void testGetpiece() {
		assertEquals(null,test.getpiece());
	}

	@Test
	public void testReset() {
		test.reset(p);
		assertEquals("White: (-1,-1)", test.getpiece().toString());
		
	}

}
