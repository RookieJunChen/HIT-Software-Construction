package P3;

public class Position 
{
	private final Point pos;
    boolean isempty = true;
	private Piece piece;
	
	// Abstraction function:
    // pos对应该位置在棋盘上的坐标，isempty对应该位置是否为空，piece对应该位置上的棋子。
    
    // Representation invariant:
    // pos != null
	// when isempty == false then piece != null
	
	// Safety from rep exposure:
    // Use private to prevent the parameter from exposure.
	
	// TODO checkRep
		public void checkRep()
		{
			assert pos != null;
		}
	
	Position(Point pos)
	{
		this.pos = pos;
		checkRep();
	}
	
	public Point getpos()
	{
		return pos;
	}
	
	public Piece getpiece() 
	{
		return piece;
	}
	
	public  void  reset(Piece piece) 
	{
		this.piece = piece;
		checkRep();
	}
	
	 @Override public String toString()
	 {
		 StringBuilder str = new StringBuilder();
		 if(isempty)
		 {
			 str.append("This position is empty.");
			 return str.toString();
		 }
		 else
		 {
			 str.append(piece.getbelongs() + ": " + piece.toString());
			 return str.toString();
		 }
	 }
}
