package P3;

public class Board 
{
	private final Position[][] goboard = new Position[19][19];
	private final Position[][] chessboard = new Position[8][8];
	
	// Abstraction function:
    // goboard对应围棋棋盘，chessboard对应象棋棋盘。
    
    // Representation invariant:
    // goboard != null
	// chessboard != null
	// 棋盘上的position坐标应为正数。
	
	// Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
	
	// TODO checkRep
		public void checkRep()
		{
			assert goboard != null;
			assert chessboard != null;
		}
	
	/**初始化围棋棋盘
	 * 
	 */
	public  void initializegoboard() 
	{
		int i , j;
		for(i = 0 ; i < 19 ; i++)
		{
			for(j = 0 ; j < 19 ; j++)
			{
				goboard[i][j] = new Position(new Point(i,j));
			}
		}
		checkRep();

	}
	
	
	/**初始化象棋棋盘
	 * 
	 */
	public  void initializechessboard()
	{
		int i, j;
		for(i = 0 ; i < 8 ; i++)
		{
			for(j = 0 ; j < 8 ; j++)
			{
				chessboard[i][j] = new Position(new Point(i,j));
			}
		}
		checkRep();
	}
	
	
	/**获得指向围棋棋盘的引用
	 * 
	 * @return 指向围棋棋盘的引用goboard
	 */
	public Position[][] getgoboard()
	{
		return goboard;
	}
	
	
	
	
	/**获得指向象棋棋盘的引用
	 * 
	 * @return 指向象棋棋盘的引用chessboard
	 */
	public Position[][] getchessboard() 
	{
		return chessboard;
	}
	
}
