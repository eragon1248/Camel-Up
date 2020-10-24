import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
public class PlayerGraphics {
	private int []tileXPoints = {1000, 1000, 1000, 800, 600, 400, 200, 200, 200, 200, 200, 400, 600, 800, 1000, 1000};
	private int []tileYPoints = {330, 470, 610, 610, 610, 610, 610, 470, 330, 190, 50, 50, 50, 50, 50, 190};
	public void drawPlayer(Graphics g, GameState gm) {
		int player = gm.getCurrentPlayer();
		if (player==0) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("C:\\Users\\slhscs338\\eclipse-workspace\\Camel up-20181210T204209Z-001\\Camel up\\Graphics Classes\\Player Icons\\Saved Pictures\\Camel Up1.PNG"));
			} catch (IOException e) {
			}
			g.drawImage(img, 45,865, null);
			g.drawString(""+(gm.getCurrentPlayer()+1), 85, 855);
		}
		if (player==1) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("C:\\Users\\slhscs338\\eclipse-workspace\\Camel up-20181210T204209Z-001\\Camel up\\Graphics Classes\\Player Icons\\Saved Pictures\\Camel Up2.PNG"));
			} catch (IOException e) {
			}
			g.drawImage(img, 45,865, null);
			g.drawString(""+(gm.getCurrentPlayer()+1), 85, 855);
		}
		if (player==2) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("C:\\Users\\slhscs338\\eclipse-workspace\\Camel up-20181210T204209Z-001\\Camel up\\Graphics Classes\\Player Icons\\Saved Pictures\\Camel Up3.PNG"));
			} catch (IOException e) {
			}
			g.drawImage(img, 45,865, null);
			g.drawString(""+(gm.getCurrentPlayer()+1), 85, 855);
		}
		if (player==3) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("C:\\Users\\slhscs338\\eclipse-workspace\\Camel up-20181210T204209Z-001\\Camel up\\Graphics Classes\\Player Icons\\Saved Pictures\\Camel Up4.PNG"));
			} catch (IOException e) {
			}
			g.drawImage(img, 45,865, null);
			g.drawString(""+(gm.getCurrentPlayer()+1), 85, 855);
		}
		if (player==4) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("C:\\Users\\slhscs338\\eclipse-workspace\\Camel up-20181210T204209Z-001\\Camel up\\Graphics Classes\\Player Icons\\Saved Pictures\\Camel Up5.PNG"));
			} catch (IOException e) {
			}
			g.drawImage(img, 45,865, null);
			g.drawString(""+(gm.getCurrentPlayer()+1), 85, 855);
		}
	}
	public void drawCards(Graphics g, ArrayList<RollCard> rollCards, ArrayList<LegCard> legCards)
	{
		int x = 510;
		int y = 865;
		for(int i=0; i<rollCards.size(); i++)
		{
			g.setColor(new Color(209, 152, 92));
			g.fillRect(x, y, 130, 15);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, 130, 15);
			y = y - 15;
		}
		x = 310;
		y = 865;
		for(int i=0; i<legCards.size(); i++)
		{
			int color = legCards.get(i).getCamelColor();
			if(color == 0)
			{
				g.setColor(new Color(0, 102, 255));
				g.fillRect(x, y, 130, 15);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, 130, 15);
			} else if(color == 1)
			{
				g.setColor(new Color(51, 204, 51));
				g.fillRect(x, y, 130, 15);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, 130, 15);
			} else if(color == 2)
			{
				g.setColor(new Color(255, 153, 51));
				g.fillRect(x, y, 130, 15);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, 130, 15);
			} else if(color == 3)
			{
				g.setColor(new Color(255, 255, 102));
				g.fillRect(x, y, 130, 15);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, 130, 15);
			}
			else if(color == 4)
			{
				g.setColor(new Color(250,250,250));
				g.fillRect(x, y, 130, 15);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, 130, 15);
			}
			y = y - 15;
		}
	}
	public void drawMoney(Graphics g, GameState gm)
	{
		int money = gm.getPlayer(gm.getCurrentPlayer()).getMoney();
		g.setColor(Color.BLACK);
		g.setFont(new Font("Papyrus",Font.BOLD,15));
		g.drawString("Total = $"+money, 1100, 950);
		int rem = money / 20;
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Papyrus",Font.BOLD,25)); //  (34, 124, 51)
		g.setColor(new Color(204, 0, 0));
		g.fillRect(950, 950, 100, 50);
		g.setColor(Color.BLACK);
		g.drawRect(950, 950, 100, 50);
		g.drawString("x" + rem, 1066, 985);
		g.drawString("20", 985, 980);
		money = money - (rem*20);
		
		rem = money / 10;
		g.setColor(new Color(77, 77, 255));
		g.fillRect(950, 885, 100, 50);
		g.setColor(Color.BLACK); // (84, 221, 110)
		g.drawRect(950, 885, 100, 50);
		g.drawString("x" + rem, 1066, 915);
		g.drawString("10", 985, 915);
		money = money - (rem*10);
		
		rem = money / 5; // (206, 205, 204)
		g.setColor(new Color(255, 173, 51));
		g.fillOval(800, 950, 50, 50);
		g.setColor(Color.BLACK);
		g.drawOval(800, 950, 50, 50);
		g.drawString("x" + rem, 870, 985);
		g.drawString("5", 818, 983);
		money = money - (rem*5);
		
		rem = money; // (229, 158, 87)
		g.setColor(new Color(0, 179, 89));
		g.fillOval(800, 885, 50, 50);
		g.setColor(Color.BLACK);
		g.drawOval(800, 885, 50, 50);
		g.drawString("x" + rem, 870, 915);
		g.drawString("1", 818, 917);
	}

}

