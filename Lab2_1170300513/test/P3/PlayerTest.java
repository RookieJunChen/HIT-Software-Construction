package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	// Testing strategy:
	// 构造特例以实现执行每行代码提高覆盖率;
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	@Test
	public void testgoPlayer() {
		goPlayer player = new goPlayer("CJ");
		assertEquals("CJ", player.getname());
		player.initialize("White");
		assertEquals("CJ", player.getgoPicecs()[0].getbelongs());
		assertEquals("White", player.getgoPicecs()[1].gettype());
	}

	@Test
	public void testchessPlayer() 
	{
		ChessPlayer player = new ChessPlayer("CJ");
		assertEquals("CJ", player.getname());
		player.initialize("White");
		assertEquals("CJ", player.getchessPieces()[0].getbelongs());
		assertEquals("Pawn", player.getchessPieces()[0].gettype());
		player.initialize("Black");
		assertEquals("CJ", player.getchessPieces()[0].getbelongs());
		assertEquals("Pawn", player.getchessPieces()[0].gettype());
	}
}
