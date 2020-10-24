public class LegCard implements Comparable
{
	private int camelColor;
	private int moneyModifier;
	
	public LegCard(int mon, int color)
	{
		camelColor = color;
		moneyModifier = mon;
	}
	public int getCamelColor()
	{
		return camelColor;
	}
	public int getOrigMoneyMod()
	{
		return moneyModifier;
	}
	public int getMoneyModifier(int[] camelOrder) // checks place of camel and returns money
	{
		if (camelOrder[0] == camelColor)
		{
			return moneyModifier;
		}
		else if (camelOrder[0] == camelColor)
			return 1;
		else
			return -1;
	}
	@Override
	public int compareTo(Object o) 
	{
		LegCard in = (LegCard)o;
		if (in.getOrigMoneyMod() < moneyModifier)
			return -1;
		if (in.getOrigMoneyMod() > moneyModifier)
			return 1;
		return 0;
	}
	
	public String toString()
	{
		return "CC: " + camelColor + " MM: " + moneyModifier;
	}
	
	
}

