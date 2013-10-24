package mapper;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import java.util.ArrayList;


public class Explorer 
{
	int x,y, height,width, lx,ly, state;
	final int UP = 1;
	final int DOWN = 2;
	final int LEFT = 3;
	final int RIGHT = 4;
	
	String pic = "Lib/explorer.png";
	private ImageIcon ii = new ImageIcon(this.getClass().getResource(pic));
	Image image;
	boolean hit = false;
	ArrayList<Tile> area = new ArrayList<Tile>();
	ArrayList<Tile> memory = new ArrayList<Tile>();
	Rectangle r;
	Area a;
	Map map;
	public Explorer(int x, int y, Area a)
	{
		state = UP;
		map = new Map(this);
		this.x = x;
		this.y = y;
		lx = x;
		ly = y;
		image = ii.getImage();
		width = image.getWidth(null); //height and width are used for drawing
        height = image.getHeight(null);
        r = new Rectangle (x,y,width,height);
        this.a = a;
	}
	
	public void move()
	{
		if(state == UP) //check up and left
		{
			y = y - 25; //check above you
			sense(a); //feel the wall
			if(hit == true)// if you bump, change direction to right
			{
				y = ly; //go back to last position
				hit = false; //reset hit counter
				state = RIGHT; // change state to going to the right
				System.out.println("right");
				return;
			}
			else // if not...
			{
				ly = y; //permanently change position
				x = x - 25; //check left
				sense(a);
				if(hit == true) //if bump, keep going up and checking left
				{
					x = lx; //move back
					hit = false; //reset hit counter
				}
				else
				{
					System.out.println("left");
					state= LEFT; //otherwise start going left
					lx = x;
					return;
				}
				
			}
		}
		if(state == DOWN)
		{
			y = y + 25; //check below you
			sense(a);
			if(hit == true)// if you bump, change direction to left
			{
				y = ly;
				hit = false;
				state = LEFT;
				System.out.println("left");
				return;
			}
			else // if not...
			{
				ly = y;
				x = x + 25; //check right
				sense(a);
				if(hit == true) //if bump, keep going down and checking right
				{
					x = lx;
					hit = false;
				}
				else
				{
					state= RIGHT; //otherwise start going right
					lx = x;
					System.out.println("right");
					return;
				}
				
			}
		}
		if(state == LEFT)
		{
			x = x - 25; //check to the left
			sense(a);
			if(hit == true)// if you bump, change direction to UP
			{
				x = lx;
				hit = false;
				state = UP;
				System.out.println("up");
				return;
			}
			else // if not...
			{
				lx = x;
				y = y + 25; //check down
				sense(a);
				if(hit == true) //if bump, keep going left and checking down
				{
					y = ly;
					hit = false;
				}
				else
				{
					ly = y;
					state= DOWN; //otherwise start going left
					System.out.println("down");
					return;
				}
				
			}
		}
		if(state == RIGHT)
		{
			x = x + 25; //check to the right
			sense(a);
			if(hit == true)// if you bump, change direction to down
			{
				x = lx;
				hit = false;
				state = DOWN;
				System.out.println("DOWN");
				return;
			}
			else // if not...
			{
				lx = x;
				y = y - 25; //check UP
				sense(a);
				if(hit == true) //if bump, keep going right and checking up
				{
					y = ly;
					hit = false;
				}
				else
				{
					ly = y;
					state= UP; //otherwise start going up
					System.out.println("UP");
					return;
				}
			}
		}
	}
	public void sense(Area a)
	{
		r = new Rectangle(x,y,height,width);
		for(Tile t: a.area)
		{
			if(t.type == 4 && r.intersects(t.tile))
				hit = true;
			
		}
	}
	public void drawMap()
	{
		boolean search = false;
		r = new Rectangle(x,y,height,width);
		for(Tile t: a.area) //for every tile in the area
		{
			if(r.intersects(t.tile)) // if player intersects with the tile
			{
				for(Tile t1: map.map) // for every tile on the map
				{
					//System.out.println("Area Tile: " + t.y + " Map Tile: " + t1.y + " My Pos: " + y);	
					//System.out.println("Area Tilex: " + t.x + " Map Tilex: " + t1.x);
					if(t.tile.intersects(t1.tile))
					{
						search = true;
						System.out.println("duplicate");
					}
				}
				if(search == false)
				{
					map.addTile(t);
					//System.out.println("draw");
				}
				
			}
			
		}
	}
	public BufferedImage makeColorTransparent(Color color) 
    {    
        BufferedImage dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dimg.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(image,0,0,null);
        for(int i = 0; i < dimg.getHeight(); i++) {  
            for(int j = 0; j < dimg.getWidth(); j++) {  
                if(dimg.getRGB(j, i) == color.getRGB()) {  
                dimg.setRGB(j, i, AlphaComposite.CLEAR);  
                }  
            }  
        }  
        return dimg;  
    }
}
