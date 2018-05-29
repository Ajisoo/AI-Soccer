import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/* 
 * Class used to view a game of soccer.
 */

public class ViewExampleGame extends JPanel implements ActionListener{
	
	public static void main(String[] args){
		// Choose which weights. (-1 for AndrewMethod).
		int left = 0;
		int right = 1;
		
		JFrame frame = new JFrame("AI Soccer");
		frame.setSize(1200,800);
		frame.setUndecorated(true);
		frame.setLocation(0,0);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new ViewExampleGame(left,right));
		frame.setVisible(true);
	}
	
	private GameState gameState;
	private Timer t;
	
	public ViewExampleGame(int left, int right){
		t = new Timer(1000/60,(ActionListener) this);
		gameState = new GameState(left,right);
		t.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		gameState.draw(g,getWidth(), getHeight());
	}

	
	public void actionPerformed(ActionEvent e) {
		gameState.update();
		repaint();
		
	}
	
}
