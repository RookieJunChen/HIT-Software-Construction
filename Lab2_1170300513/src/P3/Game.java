package P3;

import java.util.Scanner;






public interface Game 
{
	/**对两位选手进行初始化
	 * 
	 * @param 选手1的名字p1
	 * @param 选手2的名字p2
	 */
	public void InitializeThePlayer(String p1 , String p2);
	
	
	
	/**执行某位选手在一回合内的操作
	 * 
	 * @param 回合计数器n
	 */
	public void display(int n);
	
	
	
	/**对棋盘进行初始化
	 * 
	 */
	public void  Initializeboard(); 
	
	
	/**展示历史记录
	 * 
	 */
	public void showhistory();
}


class goGame implements Game
{
	private goPlayer play1;
	private goPlayer play2;
	private Board board = new Board();
	public goAction action = new goAction();
	StringBuilder history = new StringBuilder();
	// Abstraction function:
    // play1对应第一位选手，play2对应第二位选手，board对应棋盘，action对应将要执行的行为，history对应整盘棋的走棋历史。
    
    // Representation invariant:
	// play1 != null
	// play2 ！= null
	// board != null
	// action != null
	// history != null
	// 其余invariant在相应的ADT里已定义。

	// Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
	
	// TODO checkRep
	public void checkRep()
	{
		assert play1 != null;
		assert play2 != null;
		assert board != null;
		assert action != null;
		assert history != null;
	}
			
	@Override
	public void InitializeThePlayer(String p1 , String p2) 
	{
		play1 = new goPlayer(p1);
		play1.initialize("White");
		play2 = new goPlayer(p2);
		play2.initialize("Black");
		checkRep();
	}
	

	@Override
	public void  Initializeboard() 
	{
		board.initializegoboard();
		checkRep();
 
	}
	

	@Override 
	public void display(int n)
	{
		
		
		while(true)
		{
			goPlayer player;
			int choice;
			int flag = 0;
			if(n%2 == 0) //判断轮到哪一位选手.
			{
				player = play1;
			}
			else
			{
				player = play2;
			}
			System.out.println(player.getname() + ": ");
			System.out.println("1.View situation.");
			System.out.println("2.Look at a position.");
			System.out.println("3.Place a piece.");
			System.out.println("4.Remove a piece.");
			System.out.println("5.Skip.");
			System.out.println("Input your choice:");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			switch(choice)
			{
			case 1: 
				action.showsituation(play1);
				action.showsituation(play2);
				break;
			case 2:
				System.out.println("Input the position.");
				System.out.print("x: ");
				int x3 = sc.nextInt();
				System.out.print("y: ");
				int y3 = sc.nextInt();
				System.out.println(board.getgoboard()[x3][y3].toString());
				break;
			case 3:
				int m = findfree(player);
				System.out.println("Input the position.");
				System.out.print("x: ");
				int x1 = sc.nextInt();
				System.out.print("y: ");
				int y1 = sc.nextInt();
				if(action.place(player, player.getgoPicecs()[m], board.getgoboard()[x1][y1]))
				{
					flag = 1;
					history.append(player.getname() + ": place " + player.getgoPicecs()[m].toString() + "\n");
					eat(board, action, player,history);
					
				}
				break;
			case 4:
				System.out.println("Input the position.");
				System.out.print("x: ");
				int x2 = sc.nextInt();
				System.out.print("y: ");
				int y2 = sc.nextInt();
				if(action.remove(player, board.getgoboard()[x2][y2]))
				{
					flag = 1;
					history.append(player.getname() + ": remove " + player.getgoPicecs()[0].gettype() + 
							": "+"(" + x2 + "," + y2 + ")\n");
				}
				break;
			case 5:
				flag = 1;
				history.append(player.getname() + ": Skip his turn.\n");
				break;
			default:
				System.out.println("Input wrong!");
				break;
				
			}
			
			System.out.println("\n");
			
			if(flag == 1)
				break;
			
		}
		checkRep();
		
	}
	
	/**辅助函数，找出选手还没放在棋盘上的棋子
	 * 
	 * @param goPlayer类选手player
	 * @return 选手的棋子数组中第一个不在棋盘上的棋子的下标。
	 */
	public static int findfree(goPlayer player)
	{
		int i;
		for(i = 0 ; i < player.getgoPicecs().length; i++)
		{
			if((player.getgoPicecs()[i].getpos().x == -1) && (player.getgoPicecs()[i].getpos().y == -1) )
				return i;
		}
		return -1;
	}
	
	public static void eat(Board board , goAction action , Player player , StringBuilder history)
	{
		int i , j;
		for(i = 1 ; i < 18 ; i++)
		{
			for(j = 1 ; j < 18 ; j++)
			{
				Position p = board.getgoboard()[i][j];
				if( (!p.isempty) && (!p.getpiece().getbelongs().equals(player)))
				{
					Position pl = board.getgoboard()[i-1][j];
					Position pr = board.getgoboard()[i+1][j];
					Position pu = board.getgoboard()[i][j+1];
					Position px = board.getgoboard()[i][j-1];
					if( 
						((!pl.isempty) && (!pl.getpiece().getbelongs().equals(p.getpiece().getbelongs()))) &&
						((!pr.isempty) && (!pr.getpiece().getbelongs().equals(p.getpiece().getbelongs()))) &&
						((!pu.isempty) && (!pu.getpiece().getbelongs().equals(p.getpiece().getbelongs()))) &&
						((!px.isempty) && (!px.getpiece().getbelongs().equals(p.getpiece().getbelongs())))
						)
						{
							history.append(p.getpiece().getbelongs() + "'s " + p.getpiece() + " is eaten.\n");
							action.remove(player, p);
						}
				}
			}
		}
	}
	
	@Override
	public void showhistory() {
		System.out.println(history.toString());
		
	}	
}






class chessGame implements Game
{
	private ChessPlayer play1;
	private ChessPlayer play2;
	private Board board = new Board();
	public chessAction action = new chessAction();
	StringBuilder history = new StringBuilder();

	// Abstraction function:
    // play1对应第一位选手，play2对应第二位选手，board对应棋盘，action对应将要执行的行为，history对应整盘棋的走棋历史。
    
    // Representation invariant:
	// play1 != null
	// play2 ！= null
	// board != null
	// action != null
	// history != null
	
	// Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
	
	// TODO checkRep
	public void checkRep()
	{
		assert play1 != null;
		assert play2 != null;
		assert board != null;
		assert action != null;
		assert history != null;
	}
	
	@Override
	public void Initializeboard() 
	{
		int i;
		board.initializechessboard();
		//将棋子摆上棋盘。
		for(i = 0 ; i < play1.getchessPieces().length ; i++)
		{
			int x = play1.getchessPieces()[i].getpos().x;
			int y = play1.getchessPieces()[i].getpos().y;
			board.getchessboard()[x][y].isempty = false;
			board.getchessboard()[x][y].reset(play1.getchessPieces()[i]);
		}
		for(i = 0 ; i < play2.getchessPieces().length ; i++)
		{
			int x = play2.getchessPieces()[i].getpos().x;
			int y = play2.getchessPieces()[i].getpos().y;
			board.getchessboard()[x][y].isempty = false;
			board.getchessboard()[x][y].reset(play2.getchessPieces()[i]);
		}
		checkRep();
	}

	@Override
	public void InitializeThePlayer(String p1, String p2) 
	{
		play1 = new ChessPlayer(p1);
		play1.initialize("White");
		play2 = new ChessPlayer(p2);
		play2.initialize("Black");
		checkRep();
	}
	
	@Override
	public void display(int n) 
	{
		while(true)
		{
			ChessPlayer player;
			int choice;
			int flag = 0;
			if(n%2 == 0) //判断轮到哪一位选手.
			{
				player = play1;
			}
			else
			{
				player = play2;
			}
			System.out.println(player.getname() + ": ");
			System.out.println("1.View situation.");
			System.out.println("2.Look at a position.");
			System.out.println("3.Move a piece.");
			System.out.println("4.Skip.");
			System.out.println("Input your choice:");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				action.showsituation(play1);
				action.showsituation(play2);
				break;
			case 2:
				System.out.println("Input the position.");
				System.out.print("x: ");
				int x3 = sc.nextInt();
				System.out.print("y: ");
				int y3 = sc.nextInt();
				System.out.println(board.getchessboard()[x3][y3].toString());
				break;
			case 3:
				System.out.println("Input starting point.");
				System.out.print("x:");
				int x1 = sc.nextInt();
				System.out.print("y:");
				int y1 = sc.nextInt();
				System.out.println("Input destination point.");
				System.out.print("x:");
				int x2 = sc.nextInt();
				System.out.print("y:");
				int y2 = sc.nextInt();
				int k;
				Piece tmp1 = board.getchessboard()[x1][y1].getpiece();
				Piece tmp2 = board.getchessboard()[x2][y2].getpiece();
				if((k=action.move(player, board.getchessboard()[x1][y1], board.getchessboard()[x2][y2])) != -1) 
				{
					flag = 1;
					history.append(player.getname() + ": " + "move his " +  tmp1.gettype() + 
							" from " + "(" + x1 + "," + y1 + ") to " +  "(" + x2 + "," + y2 + ")" );
					if(k == 1)
					{
						history.append(" and eat " + tmp2.getbelongs()+ "'s " +tmp2.gettype() );
					}
					history.append("\n");
				}
				break;
			case 4:
				flag = 1;
				history.append(player.getname() + ": Skip his turn.\n");
				break;
			default:
				System.out.println("Input wrong!");
				break;
				
			}
			
			System.out.println("\n");
			
			if(flag == 1)
				break;
			
		}
		checkRep();
		
	}

	@Override
	public void showhistory() {
		System.out.println(history.toString());
		
	}
	
}