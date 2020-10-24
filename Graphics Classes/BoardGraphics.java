import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.*;
public class BoardGraphics{
	private int []tileXPoints = {1000, 1000, 1000, 800, 600, 400, 200, 200, 200, 200, 200, 400, 600, 800, 1000, 1000, 1000};
	private int []tileYPoints = {330, 470, 610, 610, 610, 610, 610, 470, 330, 190, 50, 50, 50, 50, 50, 190,330};
	public BoardGraphics() {
		
		
	}
	public void drawSpecialTile(Graphics g, GameState gm) {
		//int []xdata = {0, 0, -1, 0, 1, 0, 0, -1, 0, 0, 1, 0, 0,0 , 0,1 };
		Font hello = new Font ("Papyrus", Font.BOLD, 50);
		g.setColor(Color.BLACK);
		g.setFont(hello);
		for (int i=1; i<16; i++) {
			int x = gm.getBoard().getTrack().getTile(i).getMoveModifier();
			
			if (x ==-1) {
				g.setColor(new Color(204, 0, 0));
				g.fillRect(tileXPoints[i]+10, tileYPoints[i]+10, 180, 120);
				g.setColor(Color.BLACK);
				g.drawString(""+(gm.getBoard().getTrack().getTile(i).getOwner()+1), tileXPoints[i]+90, tileYPoints[i]+85);

			}
			if ( x == 1 ) 
			{
				g.setColor(new Color(51, 204, 51));
				g.fillRect(tileXPoints[i]+10, tileYPoints[i]+10, 180, 120);
				g.setColor(Color.BLACK);
				g.drawString(""+(gm.getBoard().getTrack().getTile(i).getOwner()+1), tileXPoints[i]+90, tileYPoints[i]+85);

			}
		}
	}
	public void drawCamels(Graphics g, GameState gm)
	{
		ArrayList<Camel> camels;
		for(int i = 0; i < 16; i++)
		{
			camels = gm.getBoard().getTrack().getTile(i).getCamels();
			for(int k = camels.size()-1; k > -1 ; k--)
			{
				colorizer(camels.get(k).getColor(), g);
				g.drawPolygon(camelCreator(k, i, k));
				g.fillPolygon(camelCreator(k, i, k));
				
			}
		}
		if(gm.checkGameEnded())
		{
			colorizer(gm.getWinningCamel().getColor(), g);
			g.drawPolygon(camelCreator(gm.getWinningCamel().getColor(), 0, 1));
			g.fillPolygon(camelCreator(gm.getWinningCamel().getColor(), 0, 1));
		}
		
		
	}
	public void colorizer(int i, Graphics g) 
	{
		if (i==0) {
			g.setColor(new Color(51, 102, 255));
		}
		if (i==1) {
			g.setColor(new Color(51, 204, 51));
		}
		if (i==2) {
			g.setColor(new Color(255, 153, 0));
		}
		if (i==3) {
			g.setColor(new Color(255, 255, 102));
		}
		if (i==4) {
			g.setColor(new Color(250, 250, 250));
		}
	}
	public Polygon camelCreator(int color, int pos, int stackPos) 
	{
		//int []tileXPoints = {1000, 1000, 1000, 800, 600, 400, 200, 200, 200, 200, 200, 400, 600, 800, 1000, 1000, 1000};
		//int []tileYPoints = {330, 470, 610, 610, 610, 610, 610, 470, 330, 190, 50, 50, 50, 50, 50, 190, 330};
		// x coordinates of vertices 
        int xpoints[] = { 145, 140,120, 110, 100, 80, 75, 67, 60, 50, 50, 55, 75, 93, 110, 122}; 
        // y coordinates of vertices 
        int ypoints[] = { 40, 100, 100, 76, 100, 100, 70, 70, 45, 45, 35, 30, 28, 55, 45, 55}; 
        // number of vertices 
        int numberofpoints = 16;
        //base camel
		Polygon baseCamel = new Polygon (xpoints, ypoints, numberofpoints);		
		for (int i=0; i<xpoints.length; i++) {
			xpoints[i]+=tileXPoints[pos];
		}
		for (int i=0; i<xpoints.length; i++) {
			ypoints[i]+=tileYPoints[pos];
			ypoints[i]+=38;
			ypoints[i]-=stackPos*25;
		}
		Polygon poly = new Polygon(xpoints, ypoints, numberofpoints);
		return poly;
	}
	public void drawLeaderBoard(Graphics g, GameState gm) {
		int y = 200;
		//int [] moneyCountsData = {1, 2, 3, 4, 5};
		Font hello = new Font ("Papyrus", Font.BOLD, 50);
		g.setColor(Color.BLACK);
		g.setFont(hello);
		for (int i=0; i<5; i++) {
			if(gm.getCurrentPlayer() == i)
			{
				g.setColor(Color.red);
			}
			else
			{
				g.setColor(Color.BLACK);
			}
			g.drawString(""+(i+1)+" = $"+gm.getPlayer(i).getMoney(), 15,y);
			y+=100;
		}
		
	}
	
	public void drawGameBets(Graphics g, GameState gm) {
		Font hello = new Font ("Papyrus", Font.BOLD, 50);
		g.setColor(Color.BLACK);
		g.setFont(hello);
		if (!gm.getGameWin().isEmpty()){
			int win = ((GameBetCard) gm.getGameWin().peek()).getOwner()+1;
			g.drawString(""+win, 1810,885);
			
		}
		if(!gm.getGameLost().isEmpty()) {
			int loss = ((GameBetCard) gm.getGameLost().peek()).getOwner()+1;	
			g.drawString(""+loss, 1585, 885);
		}
		
	}
	
	public void drawLegBets(Graphics g, GameState gm) 
	{
		
		int y=10;
		HashMap<Integer, PriorityQueue<LegCard>> p = gm.getBoard().getLegCards();
		
		for (int i=0; i<5; i++) {
		if (p.get(i).isEmpty()) {
			g.setColor(Color.GRAY);
			g.fillRect(1490, y, 300, 125);
			y+=145;
		}
		else {
		colorizer(i, g);
		g.fillRect(1490, y, 300, 125);
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString(p.get(i).peek().getOrigMoneyMod()), 1625,y+80);
		y+=145;
		}
		}
	}
	public void drawCardPacks(Graphics g, GameState gm)
	{
		g.setColor(new Color(255, 199, 48));
		g.fillRect(200, 880, 250, 140);
		g.fillRect(500, 880, 250, 140);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(200, 865, 250, 15);
		g.fillRect(500, 865, 250, 15);
		g.setColor(Color.BLACK);
		g.drawRect(200, 880, 250, 140);
		g.drawRect(500, 880, 250, 140);
		Font hello = new Font ("Papyrus", Font.BOLD, 15);
		g.setFont(hello);
		g.drawString("Leg Bets", 280, 900);
		g.drawString("Game Bets", 570, 900);
		//displaying leg bets
		int xCount =0;
		int yCount = 0;
		int count = 0;
		for (int i=0; i<gm.getPlayer(gm.getCurrentPlayer()).getLegCards().size(); i++) {
			count++;
			if (count==6) {
				yCount++;
				yCount = 1;
				xCount = 0;
			}
			g.setColor(Color.BLACK);
			g.drawRect(204+xCount*50, yCount*50+909, 41, 41);
			colorizer(gm.getPlayer(gm.getCurrentPlayer()).getLegCards().get(i).getCamelColor(), g);
			g.fillRect(205 +xCount*50, yCount*50+910, 40, 40);
			xCount ++;
			g.setColor(Color.black);
			g.drawString(""+gm.getPlayer(gm.getCurrentPlayer()).getLegCards().get(i).getOrigMoneyMod(), 170+xCount*50, yCount*50+935);
		}
		// displaying game bets
		int []gameBets = new int [5];
		for (int i =0; i<5; i++) {
			if (!gm.getPlayer(gm.getCurrentPlayer()).hasBettedGame(true, i)) {
				gameBets[i]=1;
			}
			else if (!gm.getPlayer(gm.getCurrentPlayer()).hasBettedGame(false, i)) {
				gameBets[i]=-1;
			}
			else {
				gameBets[i]=0;
			}
		}
		int row1Count = 0;
		int row2Count = 0;
		for (int i=0; i<5; i++) {
				g.setColor(Color.BLACK);
				if (gameBets[i]==0) {
					g.drawRect(504+row1Count*50, 909, 41, 41);
					colorizer(i, g);
					g.fillRect(505 +row1Count*50,910, 40, 40);
					row1Count ++;
					g.setColor(Color.black);
				}
				else {
					g.drawRect(504+row2Count*50, 959, 41, 41);
					colorizer(i, g);
					g.fillRect(505 +row2Count*50,960, 40, 40);
					row2Count ++;
					g.setColor(Color.black);
					if (gameBets[i]==1){
						g.drawString("W", 467+row2Count*50, yCount*50+985);
					}
					if (gameBets[i]==-1){
						g.drawString("L", 470+row2Count*50, yCount*50+985);
					}
				}
		}	
	}
}



