package P3;

import java.util.Scanner;

public class Action 
{
	
	/**在棋盘上放置棋子
	 * 
	 * @param 选手player
	 * @param 棋子piece
	 * @param 位置p
	 * @return 成功放置返回true，否则返回false.
	 */
	public boolean place(Player player , Piece piece , Position p) 
	{
		if(!p.isempty) //该位置有棋子了。
		{
			System.out.println("There is already a piece in this position.");
			return false;
		}
		else if(!piece.getbelongs().equals(player.getname())) //该棋子不属于该选手
		{
			System.out.println("Piece does not belong to the player.");
			return false;
		}
		else if((piece.getpos().x!=-1) && (piece.getpos().y!=-1)) //该棋子已位于棋盘上。
		{
			System.out.println("The piece is already on the board.");
			return false;
		}
		else
		{
			piece.getpos().x = p.getpos().x;
			piece.getpos().y = p.getpos().y;
			p.isempty = false;
			p.reset(piece);
			return true;
		}
	}
	
}


class goAction extends Action
{
	// Abstraction function:
    // Nothing
    
    // Representation invariant:
    // Nothing
	
	/**展示当前该选手的情况
	 * 
	 * @param 选手player
	 */
	public void showsituation(goPlayer player) 
	{
		
		int i , n = 0;
		System.out.println(player.getname() + "'s situtation:");
		for(i = 0 ; i < player.getgoPicecs().length ; i++)
		{
			
			if((player.getgoPicecs()[i].getpos().x != -1) && (player.getgoPicecs()[i].getpos().y != -1) )
			{
				System.out.println(player.getgoPicecs()[i]);
				n++;
			}
		}
		System.out.println(player.getname() + " have " + n + " pieces");
		System.out.println();
		
	}
	
	@Override public boolean place(Player player , Piece piece , Position p) 
	{
		
		if((p.getpos().x > 18) || (p.getpos().y > 18)) //位置超出棋盘范围。
		{
			System.out.println("Out of board.");
			return false;
		}
		return super.place(player, piece, p);
		
	}
	
	/**从围棋棋盘上拿走棋子
	 * 
	 * @param 选手player
	 * @param 位置p
	 * @return 成功返回true,否则返回false.
	 */
	public boolean remove(Player player , Position p) 
	{
		if(p.isempty)
		{
			System.out.println("There is no  piece here.");
			return false;
		}
		else if(p.getpiece().getbelongs().equals(player.getname()))
		{
			System.out.println("Piece does not belong to the opponent.");
			return false;
		}
		else if((p.getpos().x > 18) || (p.getpos().y > 18))
		{
			System.out.println("Out of board.");
			return false;
		}
		else
		{
			p.getpiece().getpos().x = -1;
			p.getpiece().getpos().y = -1;
			p.reset(null);
			p.isempty = true;
			return true;
		}
	}
	
}

class chessAction extends Action
{
	// Abstraction function:
    // Nothing
    
    // Representation invariant:
    // Nothing
	
	/**展示当前该选手的情况
	 * 
	 * @param 选手player
	 */
	public void showsituation(ChessPlayer player) 
	{
		int i , n= 0;
		System.out.println(player.getname() + "'s situtation:");
		for(i = 0 ; i < player.getchessPieces().length ; i++)
		{
			
			if((player.getchessPieces()[i].getpos().x != -1) && (player.getchessPieces()[i].getpos().y != -1) )
			{
				System.out.println(player.getchessPieces()[i]);
				n++;
			}
		}
		System.out.println( player.getname() + " have " + n + " pieces");
		System.out.println();
	}
	

	
	/**移动棋子，包含吃子。
	 * 
	 * @param 选手player
	 * @param 旧位置oldp
	 * @param 新位置newp
	 * @return 失败返回-1，仅移动而未吃子返回0，吃子返回1.
	 */
	public int  move(Player player , Position oldp , Position newp)
	{
		if((newp.getpos().x > 7) || (newp.getpos().y > 7)) //新位置超出棋盘。
		{
			System.out.println("Out of board.");
			return -1;
		}
		else if((oldp.getpos().x > 7) || (oldp.getpos().y > 7)) //旧位置超出棋盘。
		{
			System.out.println("Out of board.");
			return -1;
		}
		else if((oldp.getpos().x == newp.getpos().x) && (oldp.getpos().y == newp.getpos().y)) //旧位置与新位置重合。
		{
			System.out.println("Two identical positions.");
			return -1;
		}
		else if(oldp.isempty) //旧位置上无棋子。
		{
			System.out.println("There is no  piece here.");
			return -1;
		} 
		else if(!oldp.getpiece().getbelongs().equals(player.getname())) //旧位置上棋子不属于该选手。
		{
			System.out.println("Piece does not belong to the player.");
			return -1;
		}
		
		else if((!newp.isempty) && (newp.getpiece().getbelongs().equals(player.getname()))) //不能自己吃自己的棋子。
		{
			System.out.println("You can't eat your own chess pieces.");
			return -1;
		}
		else if(!newp.isempty && (!(newp.getpiece().getbelongs().equals(player.getname()))))
		{
			System.out.println("Do you want to eat " + newp.getpiece().getbelongs() + "'s " +newp.getpiece().toString() + " ?");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if(choice.equalsIgnoreCase("yes"))
			{
				//去掉目的位置的棋子。
				newp.getpiece().getpos().x = -1;
				newp.getpiece().getpos().y = -1;
				//将旧位置上的棋子放到新位置上。
				oldp.getpiece().getpos().x = newp.getpos().x;
				oldp.getpiece().getpos().y = newp.getpos().y;
				newp.reset(oldp.getpiece());
				//删除旧位置上的棋子痕迹。
				oldp.reset(null);
				oldp.isempty = true;
				return 1;
			}
			else 
			{
				System.out.println("You don't move.");
				return -1;
			}
		}
		else
		{
			newp.isempty = false;
			oldp.getpiece().getpos().x = newp.getpos().x;
			oldp.getpiece().getpos().y = newp.getpos().y;
			newp.reset(oldp.getpiece());
			oldp.reset(null);
			oldp.isempty = true;
			return 0;
		}
		
	}

	
	
}