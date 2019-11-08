package P3;

public class Player 
{
	private final String name;
	
	// Abstraction function:
    // name对应选手姓名
    
    // Representation invariant:
    // name != null
	
	// Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
	
	// TODO checkRep
	public void checkRep()
	{
		assert name != null;	
	}
			
	public Player(String name) 
	{
		this.name = name;
	}
	
	/**得到选手名字
	 * 
	 * @return
	 */
	public String getname() 
	{
		return name;
	}
}

class ChessPlayer extends Player
{
	private final Piece[] chessPieces = new Piece[16];
	
	// Abstraction function:
    // 数组chessPieces中的每个元素对应该选手的棋子。
    
    // Representation invariant:
    // chessPieces != null
	// chessPieces中的Piece的belongs必须与Player的name一致。
	
	// Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
	
	@Override
	public void checkRep() {
		
		super.checkRep();
		assert chessPieces != null;
	}
	
	public ChessPlayer(String name) 
	{
		super(name);
	}
	
	/**初始化选手的象棋棋子
	 * 
	 * @param 棋子所属方type
	 */
	public void initialize(String type) 
	{
		int i;
		//对兵进行初始化。
		if(type.equals("White"))
		{
			for(i = 0 ; i <= 7 ; i++)
			{
				Piece temp = new Piece("Pawn", super.getname());
				temp.getpos().x = i;
				temp.getpos().y = 1;
				chessPieces[i] = temp;
			}
		
			//对车进行初始化。
			Piece temp = new Piece("Rook", super.getname());
			temp.getpos().x = 0;
			temp.getpos().y = 0;
			chessPieces[8] = temp;
			temp = new Piece("Rook", super.getname());
			temp.getpos().x = 7;
			temp.getpos().y = 0;
			chessPieces[9] = temp;
		
			//对马进行初始化。
			temp = new Piece("Knight", super.getname());
			temp.getpos().x = 1;
			temp.getpos().y = 0;
			chessPieces[10] = temp;
			temp = new Piece("Knight", super.getname());
			temp.getpos().x = 6;
			temp.getpos().y = 0;
			chessPieces[11] = temp;
		
			//对象进行初始化。
			temp = new Piece("Bishop", super.getname());
			temp.getpos().x = 2;
			temp.getpos().y = 0;
			chessPieces[12] = temp;
			temp = new Piece("Bishop", super.getname());
			temp.getpos().x = 5; 
			temp.getpos().y = 0;
			chessPieces[13] = temp;
		
			//对王后进行初始化。
			temp = new Piece("Queen", super.getname());
			temp.getpos().x = 3;
			temp.getpos().y = 0;
			chessPieces[14] = temp;
			
			//对王进行初始化。
			temp = new Piece("King", super.getname());
			temp.getpos().x = 4;
			temp.getpos().y = 0;
			chessPieces[15] = temp;
		
		}
		
		else if(type.equals("Black"))
		{
			for(i = 0 ; i <= 7 ; i++)
			{
				Piece temp = new Piece("Pawn", super.getname());
				temp.getpos().x = i;
				temp.getpos().y = 6;
				chessPieces[i] = temp;
			}
		
			//对车进行初始化。
			Piece temp = new Piece("Rook", super.getname());
			temp.getpos().x = 0;
			temp.getpos().y = 7;
			chessPieces[8] = temp;
			temp = new Piece("Rook", super.getname());
			temp.getpos().x = 7;
			temp.getpos().y = 7;
			chessPieces[9] = temp;
		
			//对马进行初始化。
			temp = new Piece("Knight", super.getname());
			temp.getpos().x = 1;
			temp.getpos().y = 7;
			chessPieces[10] = temp;
			temp = new Piece("Knight", super.getname());
			temp.getpos().x = 6;
			temp.getpos().y = 7;
			chessPieces[11] = temp;
		
			//对象进行初始化。
			temp = new Piece("Bishop", super.getname());
			temp.getpos().x = 2;
			temp.getpos().y = 7;
			chessPieces[12] = temp;
			temp = new Piece("Bishop", super.getname());
			temp.getpos().x = 5;
			temp.getpos().y = 7;
			chessPieces[13] = temp;
		
			//对王后进行初始化。
			temp = new Piece("Queen", super.getname());
			temp.getpos().x = 3;
			temp.getpos().y = 7;
			chessPieces[14] = temp;
			
			//对王进行初始化。
			temp = new Piece("King", super.getname());
			temp.getpos().x = 4;
			temp.getpos().y = 7;
			chessPieces[15] = temp;
		}
		checkRep();
	}
	
	public Piece[] getchessPieces()
	{
		return chessPieces;
	}
	
}

class goPlayer extends Player
{

	private final Piece[] goPieces = new Piece[361];
	
	// Abstraction function:
    // 数组goPieces中的每个元素对应该选手的棋子。
    
    // Representation invariant:
    // goPieces != null
	// goPieces中的Piece的belongs必须与Player的name一致。
	
	// Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
	
	@Override
	public void checkRep() {
		
		super.checkRep();
		assert goPieces != null;
	}
	
	public goPlayer(String name) 
	{
		super(name); 
		
	}
	
	/**初始化该选手的围棋棋子
	 * 
	 * @param 棋子黑白type
	 */
	public void initialize(String type)
	{
		int i;
		for(i = 0 ; i < 361 ; i++)
		{
			goPieces[i] = new Piece(type ,super.getname());
		}
		checkRep();
	}
	
	public Piece[] getgoPicecs()
	{
		return goPieces;
	}
	

}