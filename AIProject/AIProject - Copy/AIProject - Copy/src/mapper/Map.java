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

public class Map
{
	ArrayList<Tile> map = new ArrayList<Tile>();
	int posx,posy;
	int yOffset = 275;
	public Map(Explorer e)
	{
		posx = e.x;
		posy = e.y + yOffset;
	}
	public void addTile(Tile t)
	{
		map.add(t);
	}
}
