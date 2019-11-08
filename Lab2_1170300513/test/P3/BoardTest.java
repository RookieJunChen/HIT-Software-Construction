package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	// Testing strategy:
	// 构造特例以实现执行每行代码提高覆盖率;
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	public Board board = new Board();
	@Test
	public void testGetgoboard() {
		board.initializegoboard();
		assertEquals(2, board.getgoboard()[2][3].getpos().x);
		assertEquals(3, board.getgoboard()[2][3].getpos().y);
		assertEquals(true, board.getgoboard()[2][3].isempty);
	}

	@Test
	public void testGetchessboard() {
		board.initializechessboard();
		assertEquals(4, board.getchessboard()[4][3].getpos().x);
		assertEquals(3, board.getchessboard()[4][3].getpos().y);
		assertEquals(true, board.getchessboard()[4][3].isempty);
	}

}
