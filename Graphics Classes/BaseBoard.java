import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;
public class BaseBoard extends JPanel implements MouseListener{
	private BoardGraphics b ;
	private PlayerGraphics p ;
	private int []tileXPoints = {1000, 1000, 1000, 800, 600, 400, 200, 200, 200, 200, 200, 400, 600, 800, 1000, 1000, 1000};
	private int []tileYPoints = {330, 470, 610, 610, 610, 610, 610, 470, 330, 190, 50, 50, 50, 50, 50, 190, 330};
	GameState gm = new GameState();
	private boolean Won=false;
	private int currentPlayer;
	Color BackgroundColor = new Color(253, 229, 159);
	boolean leaderboardMode=false;
	private boolean legEnded = false;
	public BaseBoard() 
	{
		b = new BoardGraphics();
		p = new PlayerGraphics(); 
		addMouseListener(this);
		
	}
	public void paint(Graphics g) 
	{
		if (!Won)
		{
			if (legEnded&&!gm.checkGameEnded()) {
				leaderboardMode = true;
				leaderBoard(g, false);
		    }
			else
			{
			currentPlayer = gm.getCurrentPlayer();
			drawBackground(g);
			drawTiles(g);
			drawPyramid(g);
			drawStackBases(g);
			drawTents(g);
			drawHud(g);
			drawTileButtons(g);
		    b.drawSpecialTile(g, gm);
		    b.drawCamels(g, gm);
		    b.drawGameBets(g, gm);
		    b.drawLeaderBoard(g, gm);
		    b.drawLegBets(g, gm);
		    p.drawPlayer(g, gm);
		    b.drawCardPacks(g, gm);
		    p.drawMoney(g,gm);
		    drawStartStop(g);
		    gameOver(g);
			}
		}
		else 
		{
			leaderBoard(g, true);
		}
	}
	public void drawBackground(Graphics g) 
	{
		//background coloring
		g.setColor(BackgroundColor);
		g.fillRect(0, 0, 1920, 1080);
	}
	public void drawPyramid(Graphics g) 
	{
		Color PyramidColor = new Color(255, 213, 50);
		//pyramid coloring
		g.setColor(Color.BLACK);
		g.drawRect(410, 230, 300, 300);
		g.setColor(PyramidColor);
		g.fillRect(410, 230, 300, 300);
		PyramidColor = new Color(255, 218, 27);
		g.setColor(Color.BLACK);
		g.drawRect(435, 255, 250, 250);
		g.setColor(PyramidColor);
		g.fillRect(435, 255, 250, 250);
		PyramidColor = new Color(255, 218, 27);
		g.setColor(Color.BLACK);
		g.drawRect(460, 280, 200, 200);
		g.setColor(PyramidColor);
		g.fillRect(460, 280, 200, 200);
		PyramidColor = new Color(255, 222, 54);
		g.setColor(Color.BLACK);
		g.drawRect(485, 305, 150, 150);
		g.setColor(PyramidColor);
		g.fillRect(485, 305, 150, 150);
		PyramidColor = new Color(255, 228, 90);
		g.setColor(Color.BLACK);
		g.drawRect(510, 330, 100, 100);
		g.setColor(PyramidColor);
		g.fillRect(510, 330, 100, 100);
		PyramidColor = new Color(255, 233, 115);
		g.setColor(Color.BLACK);
		g.drawRect(535, 355, 50, 50);
		g.setColor(PyramidColor);
		g.fillRect(535, 355, 50, 50);
	}
	public void drawTiles(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(200, 50, 200, 140);
		g.drawRect(400, 50, 200, 140);
		g.drawRect(600, 50, 200, 140);
		g.drawRect(800, 50, 200, 140);
		g.drawRect(1000, 50, 200, 140);
		g.drawRect(1000, 190, 200, 140);
		//this is start tile
		g.drawRect(1000, 330, 200, 140);
		g.drawRect(1000, 470, 200, 140);
		g.drawRect(1000, 610, 200, 140);
		g.drawRect(800, 610, 200, 140);
		g.drawRect(600, 610, 200, 140);
		g.drawRect(400, 610, 200, 140);
		g.drawRect(200, 610, 200, 140);
		g.drawRect(200, 470, 200, 140);
		g.drawRect(200, 330, 200, 140);
		g.drawRect(200, 190, 200, 140);	
		
	}
	
	public void drawStartStop(Graphics g)
	{
		Font hello = new Font ("Papyrus", Font.BOLD, 25);
		g.setColor(Color.BLACK);
		g.setFont(hello);
		g.drawString("Start", 1075, 460);
		g.drawString("End", 1075, 320);
	}
	
	public void drawStackBases(Graphics g) {
		//bases
		g.setColor(Color.black);
		g.drawRect(1749, 749, 151, 251);
		g.setColor(new Color(51, 204, 51));
		g.fillRect(1750, 750, 150, 250);
		g.setColor(Color.black);
		g.drawRect(1524, 749, 151, 251);
		g.setColor(new Color(204, 0, 0));
		g.fillRect(1525, 750, 150, 250);
		//titles
		Font hello = new Font ("Papyrus", Font.BOLD, 25);
		g.setColor(Color.BLACK);
		g.setFont(hello);
		g.drawString("Game Win", 1765, 740);
		g.drawString("Game Loss", 1535, 740);
		//dots
		int y = 750;
		for (int i=0; i<5; i++) {
			if (!gm.getPlayer(gm.getCurrentPlayer()).hasBettedGame(true, i)||!gm.getPlayer(gm.getCurrentPlayer()).hasBettedGame(false, i)) {
				g.setColor(Color.GRAY);
				g.fillOval(1692, y, 50, 50);
				g.fillOval(1469, y, 50, 50);
				y+=50;
			}
			else {
				b.colorizer(i, g);
				g.fillOval(1692, y, 50, 50);
				g.fillOval(1469, y, 50, 50);
				y+=50;
			}
			
		}
		
	}
	public void drawTents(Graphics g) {
		int y = 150;
		Font hello = new Font ("Papyrus", Font.BOLD, 50);
		g.setFont(hello);
		for (int i=0; i<5; i++) {
			b.colorizer(i, g);
			g.fillRect(1250, y, 100, 75);
			y+=100;
			if (gm.getBoard().getTrack().getCamels().get(i).getLastRolled()!=0) {
				g.setColor(Color.BLACK);
				g.drawString(""+gm.getBoard().getTrack().getCamels().get(i).getLastRolled(), 1288, y-48);
			}

		}
	}
	public void drawHud(Graphics g) {
		Color HudColor = new Color(228, 134, 28);
		//player inventory rectangle
		g.setColor(Color.black);
		g.drawRect(1, 865, 1200, 150);
		g.setColor(HudColor);
		g.fillRect(1, 865, 1200, 150);
		//player avatar frame
		g.setColor(Color.BLACK);
		g.drawOval(1,815, 200, 200);
		g.setColor(HudColor);
		g.fillOval(1,815, 200, 200);
	}
	public void drawTileButtons(Graphics g) 
	{
			//if (!gm.getBoard().getTrack().ownerPlacedTile(gm.getCurrentPlayer())) 
			//{
				for (int i=0; i<16; i++) 
				{
					if (gm.getBoard().getTrack().canPlaceTrap(i, gm.getCurrentPlayer())) 
					{
						g.setColor(new Color(204, 0, 0));
						g.fillRect(tileXPoints[i]+20, tileYPoints[i]+80, 50, 50);
						g.setColor(new Color(51, 204, 51));
						g.fillRect(tileXPoints[i]+140, tileYPoints[i]+80, 50, 50);
					}
				}
			//}
		}
	
	public void gameOver(Graphics g) 
	{
		if (gm.checkGameEnded()) 
		{
			Font hello = new Font ("Papyrus", Font.BOLD, 25);
			g.setColor(Color.BLACK);
			g.setFont(hello);
			int c = gm.getWinningCamel().getColor();
			g.drawString("Game Over", 713, 350);
			b.colorizer(c, g);
			g.drawString("Winning Camel: " + gm.getWinningCamel().getStringColor() , 713, 400);
			b.colorizer(gm.getLoosingCamel().getColor(), g);
			g.drawString("Losing Camel: " + gm.getLoosingCamel().getStringColor(), 713, 450);
			g.setColor(Color.BLACK);
			g.drawString("Winning Player: " + gm.getWinner().get(0).toString(), 713, 500);
			
			Won=true;
			repaint();
		}
	}
	public void leaderBoard(Graphics g, boolean mode) {
		g.setColor(BackgroundColor);
		g.fillRect(0, 0, 1920, 1080);
		Font hello = new Font ("Papyrus", Font.BOLD, 50);
		g.setColor(Color.BLACK);
		g.setFont(hello);
		//title
		if (mode)
		{
			g.drawString("Game End Leaderboards", 600, 150);
		}
		else{
			g.drawString("Leg End Leaderboards", 600, 150);
		}
		//camel leaderboard
		g.drawString("Camel Leaderboard:", 250, 330);
		int []camelList = gm.getBoard().getTrack().getCamelOrder();
		b.colorizer(camelList[0], g);
		g.drawString("1st Place: "+gm.getBoard().getTrack().getCamels().get(camelList[0]).getStringColor(), 250, 400);
		b.colorizer(camelList[1], g);
		g.drawString("2nd Place: "+gm.getBoard().getTrack().getCamels().get(camelList[1]).getStringColor(), 250, 470);
		b.colorizer(camelList[2], g);
		g.drawString("3rd Place: "+gm.getBoard().getTrack().getCamels().get(camelList[2]).getStringColor(), 250, 540);
		b.colorizer(camelList[3], g);
		g.drawString("4th Place: "+gm.getBoard().getTrack().getCamels().get(camelList[3]).getStringColor(), 250, 610);
		b.colorizer(camelList[4], g);
		g.drawString("5th Place: "+gm.getBoard().getTrack().getCamels().get(camelList[4]).getStringColor(), 250, 680);
		//player leaderboard
		g.setColor(Color.BLACK);
		g.drawString("Player Leaderboard:", 1050, 330);
		Player []playerList = new Player[5];
		for (int i=0; i<5; i++) 
		{
			playerList [i] = gm.getPlayer(i);
		}
		Arrays.sort(playerList);
		g.drawString("1st Place: "+playerList[4].toString()+" ($"+playerList[4].getMoney()+")", 1050, 400);
		g.drawString("2nd Place: "+playerList[3].toString()+" ($"+playerList[3].getMoney()+")", 1050, 470);
		g.drawString("3rd Place: "+playerList[2].toString()+" ($"+playerList[2].getMoney()+")", 1050, 540);
		g.drawString("4th Place: "+playerList[1].toString()+" ($"+playerList[1].getMoney()+")", 1050, 610);
		g.drawString("5th Place: "+playerList[0].toString()+" ($"+playerList[0].getMoney()+")", 1050, 680);
		//end results
		b.colorizer(gm.getBoard().getTrack().getCamels().get(camelList[0]).getColor(), g);
		g.drawString("Winning Camel: " + gm.getBoard().getTrack().getCamels().get(camelList[0]).getStringColor() , 250, 800);
		b.colorizer(gm.getBoard().getTrack().getCamels().get(camelList[4]).getColor(), g);
		g.drawString("Losing Camel: " + gm.getBoard().getTrack().getCamels().get(camelList[4]).getStringColor(), 250, 870);
		g.setColor(Color.BLACK);
		g.drawString("Winning Player: " + playerList[4].toString(), 1050, 800);
		g.drawString("Losing Player: " + playerList[0].toString(), 1050, 870);
		if (!mode) {
			g.drawString("Click Here to Move on", 600, 1000);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (!leaderboardMode)
		{
			// pyramid
			if (e.getX()>410&&e.getX()<710&&e.getY()>230&&e.getY()<530) 
			{
				if(!gm.checkGameEnded()) {
					legEnded = gm.roll();
					System.out.println(legEnded);
					repaint();
				}
			}
			//leg bets
			int y = 10;
			for (int i=0; i<5; i++) 
			{
				if (e.getX()>1490&&e.getX()<1790&&e.getY()>y&&e.getY()<(y+125)) {
					gm.legBet(i);
					repaint();
				}
				y+=145;
			}
			//game win bets
			y=750;
			for (int i=0; i<5; i++) 
			{
				if (e.getX()>1692&&e.getX()<1742&&e.getY()>y&&e.getY()<(y+50)) {
					gm.gameBet(i, true);
				repaint();
				}
				y+=50;
			}
			//game loss bets
			y=750;
			for (int i=0; i<5; i++) {
				if (e.getX()>1469&&e.getX()<1519&&e.getY()>y&&e.getY()<(y+50)) {
					gm.gameBet(i, false);
					repaint();
				}
				y+=50;
			}
			//trap placement
			//if (!gm.getBoard().getTrack().ownerPlacedTile(gm.getCurrentPlayer())) 
			//{
			for (int i=0; i<16; i++) 
			{
				if (gm.getBoard().getTrack().canPlaceTrap(i, gm.getCurrentPlayer())) 
				{
					if (e.getX()>(tileXPoints[i]+20)&&e.getX()<((tileXPoints[i])+20+50)&&e.getY()>(tileYPoints[i]+80)&&e.getY()<(tileYPoints[i]+130)) 
					{
						System.out.println("AAAAAAAAAAAAAAAAAAAA");
						System.out.println(i);
						gm.placeTrap( i, false);
						repaint();
					}
				if (e.getX() > ( tileXPoints[i]+140 ) && e.getX() < ( (tileXPoints[i]) + 140 + 50 ) && e.getY() > (tileYPoints[i] + 80) && e.getY() < (tileYPoints[i] + 130)) 
				{
					System.out.println(gm.getCurrentPlayer());
					gm.placeTrap(i, true);
					System.out.println(gm.getCurrentPlayer());
					repaint();
				}
			 }
		  }
			//}
			//flipping tiles
			for (int i=0; i<16; i++) {
				if (gm.getBoard().getTrack().getTile(i).getOwner()==gm.getCurrentPlayer()) {
					if (e.getX()>(tileXPoints[i]+10)&&e.getX()<(tileXPoints[i]+190)&&e.getY()>(tileYPoints[i]+10)&&e.getY()<(tileYPoints[i]+130)) {
						gm.getBoard().getTrack().getTile(i).flip();
						gm.cycleCurrentPlayer();
						repaint();
					}
				}
			}	
		}
		//leaderboard
		else {
			if (e.getX()>550&&e.getX()<1200&&e.getY()>925) {
				leaderboardMode = false;
				legEnded = false;
				repaint();
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) 
	{
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}



