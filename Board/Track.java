import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Track 
{
	private Tile[] tiles;
	private int trackLength;
	private Camel[]camels;
	private boolean side=false;
	public Track()
	{
		tiles = new Tile[17];
		camels = new Camel[5];
		trackLength = 16;
		for(int  x = 0 ; x < tiles.length ; x++)
		{
			tiles[x]=new Tile();
			
		}
		
		for(int i = 0; i < 5; i++)
		{
			camels[i] = new Camel(i);
			tiles[camels[i].getPosition()].placeCamel(camels[i]);
		}
		
		
		
	}
	
	public int getStackPos(int c)
	{
		int p = 0;
		for (int i = 0; i < tiles.length; i++)
		{
			if (!(tiles[i].getStackPos(c) == -1))
				p = tiles[i].getStackPos(c);
		}
		return p; 
	}
	////////////////////////////////////////////////////////////////////// QUARENTINE THE FOLLOWING one METHODS, I WOULD LIKE THEM REMOVED//////////////////////////////////////////////////////////
//	public int getCamelPos(int c)
//	{
//		Camel[] temp = getCamelOrder();
//
//		int color = -1;
//		for (int i = 0; i < temp.length; i++)
//		{
//			if (temp[i].getColor() == c)
//				color = temp[i].getPosition();
//		}
//		return color;
//	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int[] getCamelOrder()
	{
		int[] camelOrder = new int[5];
		int pos = 0;
		for (int i = 16; i > -1; i--)
		{
			if (tiles[i].hasCamel())
			{
				for(int x = tiles[i].getCamels().size()-1; x >-1; x--)
				{
					camelOrder[pos++] = tiles[i].getCamels().get(x).getColor(); 
				}
			}
		}
		return camelOrder;
	}
	public boolean ownerPlacedTile(int owner) 
	{
		for (int i=0; i<17; i++) 
		{
			if (tiles[i].getOwner()==owner) 
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean canPlaceTrap(int t, int p)
	{
	if(t == 0)
	return false;

	boolean canPlace = true;
	       
//	if( tiles[t+1].hasTrap()||tiles[t-1].hasTrap()||tiles[t].hasTrap())
//	{
//	canPlace = false;
//	if(tiles[t].getOwner()==p || tiles[t+1].getOwner()==p || tiles[t-1].getOwner()==p)
//	{
//	canPlace = true;
//	}
//	if(tiles[t].getOwner() != -1)
//	{
//		if ( tiles[t].getOwner()!=p || tiles[t+1].getOwner()!=p || tiles[t-1].getOwner()!=p)
//		{
//			canPlace = false;
//		}
//	}
//	
//	}
	
	if( tiles[t+1].hasTrap() && tiles[t+1].getOwner() != p) {
		canPlace = false;
	}
	if( tiles[t-1].hasTrap() && tiles[t-1].getOwner() != p) {
		canPlace = false;
	}
	if( tiles[t].hasTrap() && tiles[t].getOwner() != p) {
		canPlace = false;
	}
	
	if (tiles[t].hasCamel()) 
	       {
	       	canPlace = false;
	       }

	return canPlace;

	}
	public boolean side()
	{
		return side;
	}
	
	public int getLength()
	{
		return trackLength;
	}
	
	public int moveCamel(int c)
	{
		System.out.println("UNROLLED CAMELS: " + this.getUnrolledCamels() );
		
		int  playToPay = -1;
		int move = (int)(Math.random() * 3 + 1);
		int moveModifier = 0;
		Camel camel = this.getUnrolledCamels().get(c);
		camel.setRolled();
		camel.setLastRolled(move);
		System.out.println("CAMEL: " + camel);
		
		Tile tile = tiles[camel.getPosition()];
		
		if(camel.getPosition() + move > 16)
		{
			move = 16 - camel.getPosition();
		}
			
		System.out.println("TILE: " + tile);
		
		Tile nextTile = tiles[camel.getPosition() + move];
		if(nextTile.hasTrap())
		{
			playToPay = nextTile.getOwner();
			move += nextTile.getMoveModifier();
			moveModifier = nextTile.getMoveModifier();
		}
	
		nextTile = tiles[camel.getPosition() + move];
		int stackPos = tile.getCamels().indexOf(camel);
		
		int pos = 0;
		//System.out.println("STACKPOS: " + stackPos);
		while(stackPos < tile.getCamels().size())
		{
		//	System.out.println("MOVE CAMELS: " + tile.getCamels());
			
			tile.getCamels().get(stackPos).move(move);
			if(moveModifier != -1)
			{
				nextTile.placeCamel(tile.getCamels().remove(stackPos));
			}
			else
			{
				nextTile.addBottom(pos, tile.getCamels().remove(stackPos));
			}
			if(nextTile.equals(tile))
				break;
			pos++;
			
		}
		return playToPay;
		
		
	}
	
	public void reset()
	{
		for (int i = 0; i < tiles.length; i++)
		{
			tiles[i].reset();
		}
	}
	
	public void placeTrap(int p, int t, boolean m)
	{
		if(canPlaceTrap(t,p))
		{
			removePlayerTile(p);
			tiles[t].placeTrap(p, m,side);
		}
		
	}
	
	public void removePlayerTile(int p)
	{
		for(int i = 0; i < 16; i++)
		{
			if(tiles[i].getOwner() == p)
			{
				tiles[i].resetBase();
			}
		}
	}
	
	public Tile[] getTiles()
	{
		return tiles;
	}

	public String toString()
	{
		String output = "";
		for(int i = 0; i < tiles.length; i++)
		{
			output = output + "\nTILE: " + i + " || " + tiles[i].toString();
		}
		return output;
	}
	
	public ArrayList<Camel> getCamels()
	{
		ArrayList<Camel> camelsList = new ArrayList<Camel>();
		for(Camel c: camels)
		{
			camelsList.add(c);
		}
		return camelsList;
	}
	
	public ArrayList<Camel> getUnrolledCamels()
	{
		ArrayList<Camel> unRolled = new ArrayList<Camel>();
		for(int i = 0; i < camels.length; i++)
		{
			if (!camels[i].hasRolled())
				unRolled.add(camels[i]);
		}
		return unRolled;
	}
	
	public Tile getTile(int tile)
	{
		return tiles[tile];
	}
	
	
}





