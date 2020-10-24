import java.util.ArrayList;

public class Tile 
{
	private ArrayList<Camel> camels;
	private boolean trapsPlaced;
	private int moveModifier;
	private int player;
	
	public Tile()
	{
		camels = new ArrayList<Camel>();
		trapsPlaced = false;
		moveModifier = 0;
		player = -1;
	}
	
	public void reset()
	{
		moveModifier = 0;
		trapsPlaced = false;
		player = -1;
		for (int i = 0; i < camels.size(); i++)
		{
			camels.get(i).reset();
		}
	}
	
	public void flip()
	{
		if (moveModifier == 1)
			moveModifier = -1;
		else
			moveModifier = 1;
	}
	
	public boolean hasTrap()
	{
		return trapsPlaced;
	}
	
	public void resetBase()
	{
		moveModifier = 0;
		player = -1;
		trapsPlaced = false;
	}
	
	public void placeTrap(int p, boolean m,boolean side)
	{
		if(side)
		{
		trapsPlaced = true;	
		}
		else
		{
		player = p;
		if(m)
			moveModifier = 1;
		else
			moveModifier = -1;
		trapsPlaced = true;
		}
   }
	
	public boolean land(Camel cc)
	{
		if (trapsPlaced)
		{
			if (moveModifier == 1)
			{
				for (int i = 0; i < camels.size(); i++)
				{
					camels.get(i).addPosition(1);
				}
			}
			else
			{
				for (int i = 0; i < camels.size(); i++)
				{
					camels.get(i).addPosition(-1);
				}
			}
			return true;
		}
		return false;
	}
	
	public int pay()
	{
		return player;
	}
	public boolean hasCamel()
	{
		if (!camels.isEmpty())
			return true;
		return false;
	}
	
	public ArrayList<Camel> getCamels()
	{
		return camels;
	}
	
	public void addBottom( int i , Camel camel )
	{
		camels.add(i, camel);
	}
	
	public int getStackPos(int c)
	{
		for (int i = 0; i < camels.size(); i++)
		{
			if (camels.get(i).getColor() == c)
				return i;
		}
		return -1;
	}
	
	public void placeCamel(Camel temp)
	{
			camels.add(temp);
	}
	
	public String toString()
	{
		return "MoveMod: " + moveModifier + " || Cams: " + camels.toString();
	}
	
	public int getMoveModifier()
	{
		return moveModifier;
	}
	
	public Camel removeCamel( Camel cc )
	{
		return this.getCamels().remove(camels.indexOf(cc));
	}
	
	public int getOwner()
	{
		return player;
	}
}





