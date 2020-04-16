package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Canvas extends JPanel implements ActionListener, MouseListener 
{
	private static final long serialVersionUID = 1L;
	private static final int CANVAS_SIZE = 600;
	private static final int COLS = 12;
	private static ArrayList<Color> colours;
	private int mouseX;
	private Point mouseAbs;
	
	private JLabel livesArea;
	private JLabel scoreArea;
	private Paddle p;
	private Ball b;
	private int lives;
	private int score;
//	private Random r = new Random();
	
	private Timer t = new Timer(2, this);
	
	static
	{
		colours = new ArrayList<Color>();
		colours.add(Color.red);
		colours.add(Color.cyan);
		colours.add(Color.green);
		
	}

	public Canvas()
	{
		lives = 3;
		JPanel infoPanel = new JPanel();
		GridLayout gl = new GridLayout(0,2);
		gl.setHgap(30);
		infoPanel.setLayout(gl);
		
		livesArea = new JLabel(lives +" Lives");
		infoPanel.add(livesArea);
		
		score = 0;
		scoreArea = new JLabel("Score: " + score);
		infoPanel.add(scoreArea);
		
		add(infoPanel, BorderLayout.PAGE_START);
		
		addMouseListener(this); 
		t.start();
		setPreferredSize(new Dimension(CANVAS_SIZE,CANVAS_SIZE));
		p = new Paddle(CANVAS_SIZE);
		b = new Ball(p);
	}

	public void paint(Graphics g) 
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		
		if(b.lifeLost()!=0) addRemoveLives(b.lifeLost());
		if(lives<=0) gameOver(g);
        p.update(getMouseX());
        switch(b.update()) 
        {
        case "paddle":
        	score+=100;
        	scoreArea.setText("Score: " + String.valueOf(score));
        	b.setSpeed(score);
        }
        
        
        // Draw paddle
        g.setColor(Color.BLUE);
        g.fillRect(p.getX()-(p.getWidth()/2), p.getY(), p.getWidth(), p.getHeight());
        
        drawBricks(g);
        
        // Draw ball
        g.setColor(Color.RED);
        g.fillOval(b.getX(), b.getY(), b.getRadius()*2, b.getRadius()*2);
        
	}

	private void drawBricks(Graphics g) 
	{
		int col = CANVAS_SIZE/COLS;
		
		for(int i=1; i<COLS-1; i++)
		{
			for(int y=1; y<4; y++)
			{
				g.setColor(new Color(255/y, 255, i*100%255));
				g.fillRect(i*col, y*26, col-2, 25);
			}
		}
	}

	private void gameOver(Graphics g) 
	{
		g.drawString("GAME OVER!", CANVAS_SIZE/2-25, CANVAS_SIZE/2-5);
	}

	private int getMouseX() 
	{
		mouseAbs = MouseInfo.getPointerInfo().getLocation();
		mouseX = mouseAbs.x - getLocationOnScreen().x;
        if(mouseX>CANVAS_SIZE) mouseX=CANVAS_SIZE;
        return mouseX;
	}
	
	public void addRemoveLives(int x)
	{
		lives = lives + x;
		b.resetLives();
		p.setWidth(1.2);
		livesArea.setText(String.valueOf(lives) + " lives");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == t)
		{
			repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(b.isStuck()) b.release();
	}

	public void mousePressed(MouseEvent e) {	}
	public void mouseReleased(MouseEvent e) {	}
	public void mouseEntered(MouseEvent e) {	}
	public void mouseExited(MouseEvent e) {	}

}


