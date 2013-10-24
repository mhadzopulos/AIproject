package mapper;

import javax.swing.JFrame;

public class Explore extends JFrame 
{
	public Explore()
	{
		add(new Area());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(275,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
	}
	public static void main(String[] args)
	{
		new Explore();
		
	}
}
