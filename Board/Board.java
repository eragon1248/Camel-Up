 import java.util.PriorityQueue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Collections;
public class Board 
{
	private HashMap<Integer,PriorityQueue<LegCard>> legCards;
	private Stack<RollCard> rollCards;
	private Track track;
	
	
	public Board()
	{
		track = new Track(); 
		legCards = new HashMap<Integer,PriorityQueue<LegCard>>();
		legCards.put(0,new PriorityQueue<LegCard>());
		legCards.put(1,new PriorityQueue<LegCard>());
		legCards.put(2,new PriorityQueue<LegCard>());
		legCards.put(3,new PriorityQueue<LegCard>());
		legCards.put(4,new PriorityQueue<LegCard>());
		
		for(int i=0; i<legCards.size(); i++)
		{
			PriorityQueue<LegCard> ts = legCards.get(i);
			LegCard card1 = new LegCard(5, i);
			LegCard card2 = new LegCard(3, i);
			LegCard card3 = new LegCard(2, i);
			ts.add(card1);
			ts.add(card2);
			ts.add(card3);
			legCards.put(i, ts);
		}
		
		rollCards = new Stack<RollCard>();
		for(int i=1; i<=5; i++)
			rollCards.push(new RollCard());
		
		
	}	
		
	public RollCard removeRollCard()
	{
		return rollCards.pop();
	}
	public LegCard removeLegCard(int color)
	{
		return legCards.get(color).poll();
	}
	public Track getTrack()
	{
		return track;
	}
	public boolean checkIfStackEmpty(int stack)
	{
		PriorityQueue<LegCard> st = legCards.get(stack);
		if(st.isEmpty())
			return true;
		return false;
	}
	
	public int move(int c)
	{
		return track.moveCamel(c);
	}
	
	public void printBoard()
	{
		for(int i = 0; i < 5; i++)
		{
			System.out.println("Leg Deck " + i + ": " + legCards.get(i).toString());
		}
		System.out.println(track);
	}
	
	public  HashMap<Integer,PriorityQueue<LegCard>> getLegCards()
	{
		return legCards;
	}
	
	public Stack<RollCard> getRollCards()
	{
		return rollCards;
	}
	
	public void addRollCard( RollCard rc )
	{
		rollCards.push(rc);
	}
	
}






