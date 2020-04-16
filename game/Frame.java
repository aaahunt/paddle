package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Frame 
{
	
	private static final String FRAME_TITLE = "Paddle Ball";
	private static void GUI() 
	{
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) 
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI();
            }
        });
    }
}

