package mapper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.util.Random;

public class Area extends JPanel implements ActionListener
{
	ArrayList<Tile> area = new ArrayList<Tile>();
	Explorer player = new Explorer(25,225, this);
	boolean ingame;
	String path = "Lib/explorer.png";
	Image i;
	ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
	Timer time;
	Map m;
	
	public Area()
	{
		setBackground(Color.white);
		setSize(300,650);
		setFocusable(true);
		setDoubleBuffered(true);
        ingame = true;
		CreateWorld();
		i = ii.getImage();
		time = new Timer(500,this);
		time.start();
		m = new Map(player);
	}
	public void CreateWorld()
	{
		int x = 10, y = 10;
		
		for(int i = 1; i < y; i++) // this is for the center tiles
		{
			for(int u = 1; u < x; u++)
			{
				if(u < 3)
				{
					//Random rand = new Random();
					//int type = rand.nextInt(4);
					Tile t = new Tile((i*25), (u*25), 1);
					area.add(t);
				}
				else if(i < 3 || i > 7)
				{
					Tile t = new Tile((i*25), (u*25), 1);
					area.add(t);
				}
			}
		}
		for(int i = 0; i < 11; i++) // border tiles
		{
			if(i == 0 || i == 10)
			{
				for(int u = 0; u < 11; u++)
				{
					Tile t = new Tile((u*25),(i*25), 4);
					area.add(t);
				}
			}
			else
			{
				Tile t1 = new Tile((0*25), (i*25), 4);
				Tile t2 = new Tile((10*25), (i*25), 4);
				area.add(t1);
				area.add(t2);
			}
		}
		for(int i = 3; i < 8; i++)
		{
			for(int u = 3; u < 10; u++)
			{
				Tile t1 = new Tile(i*25, u*25, 4);
				area.add(t1);
			}
		}
	}
	public void drawArea(Graphics2D g) //used in the paint function, draws the walls for the areas
    {
    		for(Tile w: area)
    		{
    			g.setPaint(w.c);
    			g.fillRect(w.x, w.y, w.width, w.height);
    		}
    		for(Tile w: player.map.map)
    		{
    			g.setPaint(w.c);
    			g.fillRect(w.x, w.y+player.map.yOffset, w.width, w.height);
    		}
    }
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		drawArea(g2d);
		g2d.drawImage(player.makeColorTransparent(Color.white), player.x, player.y, this); //draws player;
		Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}
	public void actionPerformed(ActionEvent e)
	{
		player.move();
		repaint();
	}
}

	

