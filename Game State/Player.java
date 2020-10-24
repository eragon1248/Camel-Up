import java.util.ArrayList;
public class Player implements Comparable
{
	private int money;
	private ArrayList<LegCard>legDeck;
	private boolean[]gameWinBets;
	private boolean[]gameLoseBets;
	private  ArrayList <RollCard>rollCards ;
	private String name;
	
	public Player(int k)
	{
		
			switch(k)
			{
			case 0: name = "Player 1"; break;
			case 1: name = "Player 2"; break;
			case 2: name = "Player 3"; break;
			case 3: name = "Player 4"; break;
			case 4: name = "Player 5"; break;
			}
		
		legDeck=new ArrayList<LegCard>();
		rollCards=new ArrayList<RollCard>();
		gameWinBets=new boolean[5];
		gameLoseBets=new boolean[5];
		for(int i = 0; i < 5; i++)
		{
			gameWinBets[i] = false;
			gameLoseBets[i] = false;
		}
		money = 3;
	}
	
	public String toString()
	{
		return name;
	}
	
//	public void endLeg(int[] camelOrder)
//	{
//		
//	}
	public RollCard removeRollCard()
	{
		System.out.println("INT PLAYER " + rollCards.get(0));
		return rollCards.remove(0);
	}
	
	public boolean canGameBet( boolean winOrLose , int camel )
	{
		if( winOrLose )
		{
			return !gameWinBets[camel];
		}
		else
		{
			return !gameLoseBets[camel];
		}
	}
	
	public LegCard removeLegCard()
	{
		return legDeck.remove(0);
	}
	
	public ArrayList<RollCard>getRollCards()
	{
		return rollCards;
	}
	
	public ArrayList<LegCard> getLegCards()
	{
		return legDeck;
	}
	
	public void winRaceBet(int camel)
	{
		gameWinBets[camel] = true;
	}
	public void loseRaceBet(int camel)
	{
		gameLoseBets[camel] = true;
	}
	
	public void moneyincrement(int m)
	{
		money+=m;
		if (money<=0) 
		{
			money=0;
		}
	}
	
	public void rollCard(RollCard camel)
	{
		rollCards.add(camel);
	}
	
	public boolean hasBettedGame(boolean win, int camel)
	{
		if(win==true)
		{
			if(gameWinBets[camel]==true)
			{
				return false;
			}
		}
		else
			if(gameLoseBets[camel]==true)
			{
				return false;
			}
		return true;
	}
	
	public int compareTo(Object d)
	{
		Player  p = (Player)d;
		if (getMoney() > p.getMoney())
			return 1;
		
		else if (getMoney() < p.getMoney())
			return -1;
		
		return 0;
	}

	public int getMoney()
	{
		return money;
	}

	public void legBet(LegCard camel)
	{
		legDeck.add(camel);
	}
	
	public void printPlayer()
	{
		System.out.println("\tMoney: " + money);
		System.out.println("\tLeg Bets: "+ legDeck) ;
		System.out.println("\tRoll Cards: "+ rollCards) ;
	}

	public void raceBet( boolean win, int color) {
		if (win) {
			gameWinBets[color]=true;
		}
		else {
			gameLoseBets[color]=true;
		}
	}
	
	
	
	

}






