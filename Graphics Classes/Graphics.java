import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Graphics extends JFrame {
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;
	static Graphics game;

	public Graphics(String frameName) {
		super(frameName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		drawBase();
		setVisible(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					game.dispose();
					game = new Graphics("Camel Up");
				}
			}
		});
	}

	public static void main(String args[]) {
		game = new Graphics("Camel Up");
	}

	public void drawBase() {
		add(new BaseBoard());
	}

	public void drawLegCards() {

	}

	public void drawLeaderboard(Player[] players) {

	}

}
