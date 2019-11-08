package P3;

import static org.junit.Assert.*;

import org.junit.Test;


public class ActionTest {

	// Testing strategy:
	// 构造特例以实现执行每行代码提高覆盖率;
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	Board board = new Board();
	@Test
	public void testgoAction() {
		goAction action = new goAction();
		goPlayer player = new goPlayer("CJ");
		player.initialize("White");
		board.initializegoboard();

		
		/*检验showsituation方法。*/
		player.getgoPicecs()[5].getpos().x = 1; 
		player.getgoPicecs()[5].getpos().y = 1;
		action.showsituation(player);
		
		
		/*检验place方法*/
		//超出棋盘的情况。
		assertEquals(false, action.place(player, player.getgoPicecs()[0], new Position(new Point(100,100))));
		
		//位置上已经有棋子的情况。
		Position p = new Position(new Point(3,3));
		p.isempty = false;
		assertEquals(false, action.place(player, player.getgoPicecs()[0], p)); 
		
		//棋子已经放上棋盘的情况。
		player.getgoPicecs()[0].getpos().x = 1; 
		player.getgoPicecs()[0].getpos().y = 1;
		assertEquals(false, action.place(player, player.getgoPicecs()[0], board.getgoboard()[0][0]));
		
		//棋子不属于该选手的情况。
		Piece piece = new Piece("1", "wyz");
		assertEquals(false, action.place(player, piece, board.getgoboard()[0][0]));
		
		//正确情况。
		assertEquals(true, action.place(player, player.getgoPicecs()[1], board.getgoboard()[1][1]));
		
		
		/*检验remove方法。*/
		//正确情况。
		assertEquals(true, action.remove(player, board.getgoboard()[1][1]));
		
		//超出棋盘的情况。
		Position extreme = new Position(new Point(100,100));
		extreme.reset(player.getgoPicecs()[6]);
		extreme.isempty = false;
		assertEquals(false, action.remove(player, extreme));
		
		//棋盘上没棋子的情况。
		p.isempty = true;
		assertEquals(false, action.remove(player, p));
		
		//棋子不属于该玩家的情况。
		p.isempty = false;
		p.reset(piece);
		assertEquals(false, action.remove(player, p)); 
		
	}
	
	@Test
	public void testchessAction() {
		chessAction action = new chessAction();
		ChessPlayer player1 = new ChessPlayer("CJ");
		player1.initialize("White");
		ChessPlayer player2 = new ChessPlayer("wyz");
		player2.initialize("Black");
		board.initializechessboard();
		
		/*检验showsituation方法。*/
		action.showsituation(player1);
		
		
		/*检验remove方法*/
		//起始点超出棋盘的情况。
		Position p = new Position(new Point(100,100));
		assertEquals(-1, action.move(player1, p, board.getchessboard()[0][0]));
		
		//目的地超出棋盘的情况。
		assertEquals(-1, action.move(player1, board.getchessboard()[0][0], p));
		
		//两点重合的情况。
		assertEquals(-1, action.move(player1, board.getchessboard()[0][0], board.getchessboard()[0][0]));
		
		//起始点上无棋子的情况。
		assertEquals(-1, action.move(player1, board.getchessboard()[0][0], board.getchessboard()[5][5]));
		
		//将棋子摆上棋盘。
		int i; 
		for(i = 0 ; i < player1.getchessPieces().length ; i++)
		{
			int x = player1.getchessPieces()[i].getpos().x;
			int y = player1.getchessPieces()[i].getpos().y;
			board.getchessboard()[x][y].isempty = false;
			board.getchessboard()[x][y].reset(player1.getchessPieces()[i]);
		}
		for(i = 0 ; i < player2.getchessPieces().length ; i++)
		{
			int x = player2.getchessPieces()[i].getpos().x;
			int y = player2.getchessPieces()[i].getpos().y;
			board.getchessboard()[x][y].isempty = false;
			board.getchessboard()[x][y].reset(player2.getchessPieces()[i]);
		}
		
		//目的点上有己方子的情况。
		assertEquals(-1, action.move(player1, board.getchessboard()[0][0], board.getchessboard()[0][1]));
		
		//移动非己方子的情况
		assertEquals(-1, action.move(player1, board.getchessboard()[0][7], board.getchessboard()[3][3]));
		
		//吃子的情况
		assertEquals(1, action.move(player1, board.getchessboard()[0][0], board.getchessboard()[0][7]));
		
		//正常的移动情况。
		assertEquals(0, action.move(player1, board.getchessboard()[0][1], board.getchessboard()[3][3]));
		
		
	}
}
