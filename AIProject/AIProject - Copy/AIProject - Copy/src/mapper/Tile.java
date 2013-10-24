package mapper;

import java.awt.Color;
import java.awt.Rectangle;

public class Tile 
{
	int x, y, type, width, height; 
	Color c;
	Rectangle tile;
	public Tile(int x, int y, int t)
	{
		this.x = x;
		this.y = y;
		type = t;
		width = 25;
		height = 25;
		
		tile = new Rectangle(x,y,25,25);
		
		if(type == 0) //grassy
		{
			c = Color.green;
		}
		if(type == 1) //dirt
		{
			c = Color.orange;
		}
		if(type == 2) // water
		{
			c = Color.blue;
		}
		if(type == 3) //snow
		{
			c = Color.white;
		}
		if(type == 4) //swamp
		{
			c = Color.black;
		}
	}
}
