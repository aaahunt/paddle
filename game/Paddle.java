package game;

public class Paddle 
{
	private static final int PADDLE_HEIGHT = 25;
	private  int canvas;
	private int paddleWidth = 75;
	private int paddleX;
	private int paddleY;
	
	public Paddle(int canvas)
	{
		this.canvas = canvas;
		paddleX = canvas/2;
		paddleY = canvas-PADDLE_HEIGHT;
	}
	
	public void update(int x)
	{
		if (x<0) x=0;
		paddleX = x;
	}
	
	public int getX()
	{
		return paddleX;
	}
	
	public int getY()
	{
		return paddleY;
	}
	
	public int getHeight()
	{
		return PADDLE_HEIGHT;
	}
	
	public void setWidth(double i)
	{
		paddleWidth = (int) (paddleWidth * i);
	}
	
	public int getWidth()
	{
		return paddleWidth;
	}
	
	public int getCanvas()
	{
		return canvas;
	}
	
}
