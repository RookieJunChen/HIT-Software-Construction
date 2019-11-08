package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	// Testing strategy:
	// 构造特例以实现执行每行代码提高覆盖率;
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	goGame go = new goGame();
	chessGame chess = new chessGame();
	@Test
	public void testInitializeThePlayer() 
	{
		go.InitializeThePlayer("CJ", "wyz");
		chess.InitializeThePlayer("CJ", "wyz");
	}

	@Test
	public void testDisplay() {
		go.InitializeThePlayer("CJ", "wyz");
		chess.InitializeThePlayer("CJ", "wyz");
		go.Initializeboard();
		chess.Initializeboard();
		go.display(0);
		chess.display(0);
	}

	@Test
	public void testInitializeboard() {
		go.InitializeThePlayer("CJ", "wyz");
		chess.InitializeThePlayer("CJ", "wyz");
		go.Initializeboard();
		chess.Initializeboard();
	}

}
