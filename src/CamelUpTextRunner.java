import java.util.Scanner;
public class CamelUpTextRunner 
{
	public static void main(String[]args)
	{
		GameState game=new GameState();
		game.printGameState();
		game.roll();
		game.printGameState();
		game.roll();
		game.printGameState();
		game.roll();
		game.printGameState();
		game.roll();
		game.printGameState();
		game.roll();
		game.printGameState();
		game.roll();
		game.printGameState();
		while(!game.checkGameEnded())
			game.roll();
		game.printGameState();
		
		System.out.println(game.getWinner());
		System.out.println(game.checkGameEnded());
		System.out.println(game.getWinningCamel());
		System.out.println(game.getLoosingCamel());
	}
}

//Its Gotten to the Point that Roll Works all the way through, with the game knowing when to end legs, and when to end the game (based on the position of the camels) GET ROLLABLE CAMELS MAY BE FLAWED
//also probably the money distribution at the end of the game is wrong


//its 3:28 am (saturday december 7th 2018) and I want to just finish this and sleep...

//FINISH TILE PLACEABLE/////////////////


