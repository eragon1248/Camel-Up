import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
public class GameState 
{
	private Board board;
	private Player[]players = new Player[5];
	private boolean hasWinner = false;
	private Stack<GameBetCard>gameWin;
	private Stack<GameBetCard>gameLost;
	private int currentPlayer;
	private int lastLostBet;
	private int lastWinBet;

	//=======================================================================================================================================================================
	//  CONSTRUCTOR
	//=======================================================================================================================================================================
	public GameState()
	{
		currentPlayer = (int)(Math.random() * players.length);
		board = new Board();

	
		
		for (int i = 0; i < players.length; i++)
		{
			players[i] = new Player(i);
		}
		
		gameWin = new Stack<GameBetCard>();
		gameLost = new Stack<GameBetCard>();
	
		lastLostBet = 8;
		lastWinBet = 8;
	
	}
	
	//=======================================================================================================================================================================
	//  PLAYER CHOICES
	//=======================================================================================================================================================================
	
	//=====================================PLACE TRAP=====================================//
	public void placeTrap( int tilePos, boolean OasisOrMirage)
	{
		if(!checkGameEnded())
		{
			board.getTrack().placeTrap(currentPlayer, tilePos, OasisOrMirage);
		cycleCurrentPlayer();
		}
		
	}
	
	//=====================================FLIP TILE=====================================//
	public void flipTile ( int tilePos )
	{
		if(!checkGameEnded())
		{
			board.getTrack().getTiles()[tilePos].flip();
		cycleCurrentPlayer();
		}
		
	}
	
	
	//=====================================ROLL=====================================//
	public boolean roll()
	{
		if(!checkGameEnded())
		{
			ArrayList<Camel> notRolled = board.getTrack().getUnrolledCamels();
		int camel = (int) (Math.random()*notRolled.size());
		int playerToPay = board.getTrack().moveCamel(camel);
		if(playerToPay != -1)
		{
			players[playerToPay].moneyincrement(1);
		}
		System.out.println(board.getRollCards());
		players[currentPlayer].getRollCards().add(board.removeRollCard());
		cycleCurrentPlayer();
		if(checkLegEnded()) 
		{
			legEnd();
			return true;
		}
		if(checkGameEnded())
		{
			legEnd();
			gameEnd();
		}
		}
		return false;
	}
	
	//=====================================LEG BET=====================================//
	public void legBet(int camel)
	{
		if(!checkGameEnded())
		{
			if(!board.getLegCards().get(camel).isEmpty())
		{
			players[currentPlayer].legBet(board.removeLegCard(camel));
			cycleCurrentPlayer();
		}
		}
			
	}
	
	//=====================================GAME BET=====================================//
	public void gameBet(int camel, boolean winOrLose)
	{
		if(!checkGameEnded())
		{
			Player p = players[currentPlayer];
		if(p.canGameBet(winOrLose, camel))
		{
			if(winOrLose == true)
			{
				p.winRaceBet(camel);
				gameWin.push(new GameBetCard( lastWinBet , currentPlayer , camel ));
				if( lastWinBet == 8) lastWinBet -= 3;
				else if( lastWinBet == 5) lastWinBet -= 2;
				else lastWinBet--;
				
			}
				
			else
			{
				p.loseRaceBet(camel);
				gameLost.push(new GameBetCard( lastLostBet , currentPlayer , camel ));
				if( lastLostBet == 8) lastLostBet -= 3;
				else if( lastLostBet == 5) lastLostBet -= 2;
				else lastLostBet--;
				
			}
			cycleCurrentPlayer();
		}
		}
		
		
	}
	
	
	//=======================================================================================================================================================================
	//  END LEG / GAME METHODS
	//=======================================================================================================================================================================
	public boolean checkGameEnded()
	{
		for (int i = 0; i < board.getTrack().getCamels().size(); i++)
		{
			if(board.getTrack().getCamels().get(i).getPosition() >= board.getTrack().getLength())
				return true;
		}
		return false;
	}
	
	
	public boolean checkLegEnded()
	{
			if (!board.getTrack().getUnrolledCamels().isEmpty())
				return false;
		return true;
	}
	
	public void legEnd()
	{
		board.getTrack().reset();
		int[] camelOrder = board.getTrack().getCamelOrder();
		Player player;
		int money;
		LegCard card;
		for ( int i = 0; i < players.length; i++ )
		{
			while( !players[i].getLegCards().isEmpty() )
			{
				player  = players[i];
				card = player.removeLegCard();
				money = card.getMoneyModifier(camelOrder);
				player.moneyincrement(money);
				board.getLegCards().get(card.getCamelColor()).add(card);
			}
			
			while( !players[i].getRollCards().isEmpty() )
			{
				players[i].moneyincrement(1);
				board.addRollCard(players[i].removeRollCard());
			}  
		}
	}
	
	public ArrayList<Player> gameEnd()
	{
		if(checkGameEnded())
		{
			giveMoneyWinner();
			giveMoneyLoser();
			ArrayList<Player>winner = getWinner();
			return winner;
		}
		return null;
	}
	
	public void giveMoneyWinner()
	{
		Stack<GameBetCard> temp = new Stack<GameBetCard>();
		while (!gameWin.isEmpty())
		{
			temp.push(gameWin.pop());
		}
		Camel win = getWinningCamel();
		while (!temp.isEmpty())
		{
			GameBetCard card = temp.pop();
			if (card.getCamel() == win.getColor())
				players[card.getOwner()].moneyincrement(card.getCorrect());
			else 
				players[card.getOwner()].moneyincrement(card.getWrong());
		}
	}
	
	public void giveMoneyLoser()
	{
		Stack<GameBetCard> temp = new Stack<GameBetCard>();
		while (!gameLost.isEmpty())
		{
			temp.push(gameLost.pop());
		}
		Camel lost = getLoosingCamel();
		while (!temp.isEmpty())
		{
			GameBetCard card = temp.pop();
			if (card.getCamel() == lost.getColor())
				players[card.getOwner()].moneyincrement(card.getCorrect());
			else 
				players[card.getOwner()].moneyincrement(card.getWrong());
		}
	}
	
	public ArrayList<Player> getWinner()
	{
		ArrayList<Player> winners = new ArrayList<Player>();
		if(this.checkGameEnded() == true)
		{
			Player winner = players[0];
			winners.add(winner);
			for(int i=1; i<players.length; i++)
			{
				int num = winners.get(0).compareTo(players[i]);
				if(num == 0)
				{
					winners.add(players[i]);
				}
				else if(num == -1)
				{
					for(int j = 0; j < winners.size(); j++)
						winners.remove(j);
					winners.add(players[i]);
				}
			}
		}
		return winners;
	}
	//=======================================================================================================================================================================
	//  GETTERS AND SETTERS
	//=======================================================================================================================================================================

	public void cycleCurrentPlayer()
	{
		if (currentPlayer + 1 == players.length)
			currentPlayer = 0;
		else
			currentPlayer += 1;
	}

	public int getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	public boolean hasWon()
	{
		return hasWinner;
	}
	
	public Board getBoard()
	{
		return board;
	}

	public Player getPlayer(int playerNum) {
		return players[playerNum];
	}
	public Stack getGameWin() {
		return gameWin;
	}
	public Stack getGameLost() {
		return gameLost;
	}
	public Camel getWinningCamel()
	{
//		Track track = board.getTrack();
//		Camel winner = board.getTrack().getCamels().get(0);
//		for(int i=1; i<board.getTrack().getCamels().size(); i++)
//		{
//			int comp = winner.compareTo(board.getTrack().getCamels().get(i));
//			if(comp == 0)
//			{
//				int posWinner = track.getStackPos(winner.getColor());
//				int posSomeone = track.getStackPos(board.getTrack().getCamels().get(i).getColor());
//				if(posSomeone > posWinner)
//					winner = board.getTrack().getCamels().get(i);
//			}
//		}
		ArrayList<Camel> cams = board.getTrack().getTile(16).getCamels();
		return cams.get(cams.size()-1);
	}
	
	public Camel getLoosingCamel()
	{
//		Track track = board.getTrack();
//		Camel loser = board.getTrack().getCamels().get(0);
//		for(int i=1; i<board.getTrack().getCamels().size(); i++)
//		{
//			int comp = loser.compareTo(board.getTrack().getCamels().get(i));
//			if(comp == 0)
//			{
//				int posLoser = track.getStackPos(loser.getColor());
//				int posSomeone = track.getStackPos(board.getTrack().getCamels().get(i).getColor());
//				if(posSomeone < posLoser)
//					loser = board.getTrack().getCamels().get(i);
//			}
//		}
		return board.getTrack().getCamels().get(board.getTrack().getCamelOrder()[4]);
	}
	public int getCamelPos(int color) 
	{
		for (int i=0; i<17; i++) 
		{
			for (int j=0; j<board.getTrack().getTile(i).getCamels().size(); j++) 
			{
				if (board.getTrack().getTile(i).getCamels().get(j).getColor()==color) 
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	public void printGameState()
	{
		System.out.println("------------------------------------------------------------------");
		System.out.println("CURRENT PLAYER: " + currentPlayer);
		System.out.println("-----------------PLAYERS---------------------");
		int i = 0;
		for(Player p : players) 
		{
			System.out.println("PLAYER " + i++);
			p.printPlayer();
		}
		System.out.println();
		System.out.println("-----------------BOARD---------------------");
		board.printBoard();
		System.out.println("------------------------------------------------------------------");
	}
	
}//END OF CLASS












