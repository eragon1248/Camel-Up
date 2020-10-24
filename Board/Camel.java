public class Camel implements Comparable
{
	private int position, lastRolled;
	private boolean rolled;
	private int color;
	
	public Camel(int c)
	{
		position = (int)(Math.random()*3);
		lastRolled = 0;
		rolled = false;
		color = c;
	}

	public int getLastRolled()
	{
		return lastRolled;
	}
	
	public void setRolled()
	{
		rolled = true;
	}
	
	public String getStringColor()
	{
		switch(color)
		{
		case 0: return"Blue";
		case 1: return"Green";
		case 2: return"Orange";
		case 3: return"Yellow";
		case 4: return"White";
		}
		return "ERROR IN CAMEL GET STRING COLOR";
	}
	
	public void setLastRolled( int mm )
	{
		lastRolled = mm;
	}
	public int getPosition()
	{
		return position;
	}
	
	public int move(int mm)
	{
	//	System.out.println("camel pos before move" + position);
		addPosition(mm);
		
		//System.out.println("camel pos after move" + position);
		return position;
	}
	
	public void reset()
	{
		rolled = false;
		lastRolled = 0;
	}
	
//	public int compareTo(Camel p) 
//	{
//		if (p.getPosition() > position)
//			return -1;
//		else if (p.getPosition() < position)
//			return 1;
//		return 0;
//	}

	
	public void addPosition(int p)
	{
		position += p;
	}
	
	public int getColor()
	{
		return color;
	}
	
	public boolean hasRolled()
	{
		return rolled;
	}
	public String toString()
	{
		return "||C: " + getColor() + " - P: " + position + "||";
	}

	@Override
	public int compareTo(Object arg0) 
	{
		Camel p = (Camel)arg0;
		if (p.getPosition() > position)
			return -1;
		else if (p.getPosition() < position)
			return 1;
		return 0;
	}
}



