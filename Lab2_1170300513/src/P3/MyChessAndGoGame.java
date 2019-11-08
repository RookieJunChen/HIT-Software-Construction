package P3;

import java.io.IOException;
import java.util.Scanner;

public class MyChessAndGoGame 
{
	//主要操作在Game类中，此类主要是进行初始的选择以及回合计数。
	public static void main(String[] args)
	{
		System.out.println("Enter what you want to play: go or chess.");
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		int n = 0;
		if(choice.equals("go"))
		{
			goGame game = new goGame();
			System.out.println("Please enter the names of two players:");
			System.out.println("Play1: ");
			String player1 = sc.next();
			System.out.println("Play2: ");
			String player2 = sc.next();
			game.InitializeThePlayer(player1, player2); //对game进行相应的初始化.
			game.Initializeboard();
			while(true)
			{
				if(n%2 == 0) //某一回合开始。
				{
					System.out.println("If you want to quit, please enter end, otherwise enter any string:");
					String input = sc.next();
					if(input.equalsIgnoreCase("end"))
					{
						System.out.println("Game over.");
						game.showhistory();
						break;
					}
					System.out.println("Round " + (n/2+1) + ":");
				}
				game.display(n);
				n++;
			}
			
		}
		else if(choice.equals("chess"))
		{
			chessGame game = new chessGame();
			System.out.println("Please enter the names of two players:");
			System.out.println("Play1: ");
			String player1 = sc.next();
			System.out.println("Play2: ");
			String player2 = sc.next();
			game.InitializeThePlayer(player1, player2); //对game进行相应的初始化.
			game.Initializeboard();
			while(true)
			{
				if(n%2 == 0) //某一回合开始
				{
					System.out.println("If you want to quit, please enter end, otherwise enter any string:");
					String input = sc.next();
					if(input.equalsIgnoreCase("end"))
					{
						System.out.println("Game over.");
						game.showhistory();
						break;
					}
					System.out.println("Round " + (n/2+1) + ":");
				}
				game.display(n);
				n++;
			}
		} 
		else
		{
			System.out.println("Illegal input!");
			System.exit(0);
		}

	}

}
