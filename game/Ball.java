package game;

import java.util.Random;

public class Ball 
{
	private static final int RADIUS = 8;
	
	private int ballX;
	private int ballY;
	
	private double speedX;
	private double speedY;
	private double speedInc;
	
	private String CollisionValue;
	private boolean stuck;
	private int livesGainedLost;
	private Paddle myPaddle;
	
	private Random r = new Random();
	
	public Ball(Paddle p)
	{
		myPaddle = p;
		onPaddle();
		CollisionValue="";
	}
	
	private void onPaddle() 
	{
		stuck = true;
		ballX = myPaddle.getX();
		ballY = myPaddle.getY();
	}

	public String update() 
	{
		if(!stuck)
		{
			CollisionValue = checkForCollision();
			ballX += speedX;
			ballY += speedY;
			return CollisionValue;
		}
		else
			return "";
	}
	
	private String checkForCollision() 
	{
		
//		System.out.println("ball Y:"+ ballY + " Paddle Y: " + myPaddle.getY());
//		System.out.println("ball X:"+ ballX + " Paddle X: " + myPaddle.getX());
//		
		// hit the walls
		if(ballX ==0 || ballX == myPaddle.getCanvas()) 
		{
			speedX = -speedX;
			return "wall";
		}
		
		// hit the cieling
		else if(ballY ==0)
		{
			speedY = -speedY;
			return "wall";
		}
		
		// hit the paddle
		else if((ballX >= myPaddle.getX() - myPaddle.getWidth()/2 && 
				ballX <= myPaddle.getX() + myPaddle.getWidth()/2)
			&&
			(ballY == myPaddle.getCanvas()- myPaddle.getHeight()))
		{
			speedY = -speedY;
			return "paddle";
		}
		
		// hit the floor
		else if(ballY == myPaddle.getCanvas())
		{
			onPaddle(); 
			livesGainedLost -= 1;
			return "floor";
		}
		else
			return "no";
		
	}

	public void release()
	{
		stuck = false;
		speedX = ((1.6) * r.nextDouble()) - 0.8;
		speedY = (0.5 + r.nextDouble()) * -1;
	}
	
	public void setSpeed(int score) 
	{
		speedInc = ((double) score/1_0000) + 1;
		speedX = speedX * speedInc;
		speedY = speedY * speedInc;
	}
	
	public int getX()
	{
		if(stuck) 
		{
			ballX = myPaddle.getX()-RADIUS;
			ballY = myPaddle.getY()-(RADIUS*2);
		}
		return ballX;
	}
	
	public boolean isStuck()
	{
		return stuck;
	}
	
	public int getY()
	{
		return ballY;
	}
	
	public int getRadius()
	{
		return RADIUS;
	}
	
	public int lifeLost()
	{
		return livesGainedLost;
	}
	
	public void resetLives() 
	{
		livesGainedLost = 0;
	}


}
