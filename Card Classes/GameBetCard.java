public class GameBetCard 
{
	private int correct;
	private int wrong;
	private int owner;
	private int camel;
	public GameBetCard(int cc, int ow, int c)
	{
		correct = cc;
		wrong  = -1;
		owner = ow;
		camel = c;
	}
	public int getOwner()
	{
		return owner;
	}
	public int getCorrect()
	{
		return correct;
	}
	public int getWrong()
	{
		return wrong;
	}
	public int getCamel()
	{
		return camel;
	}
}

