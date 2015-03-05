package xbhs.battleship.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import processing.core.PApplet;
import xbhs.battleship.game.Game;
import static xbhs.battleship.gui.GUIElementListHandler.*;

@SuppressWarnings("serial")
public class GUI extends PApplet 
{ 
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int initWidth = gd.getDisplayMode().getWidth()/2;
	int initHeight = gd.getDisplayMode().getHeight()/2;
	boolean mousePressedLastFrame = false;
	Game g;
	
	@Override
	public void setup() 
	{
		list = new ArrayList<GUIElement>();
		size(initWidth,initHeight);
		background(0);
		g = new Game(10,10, this);
		for(int i = 0; i < list.size(); i++)
			list.get(i).init();
	}

	@Override
	public void draw() 
	{
		if(mousePressed)
		{
			Object[] elementsAtPoint = getElementsAtCoords(mouseX, mouseY);
			if(elementsAtPoint != null)
				for(Object e: elementsAtPoint)
					((GUIElement)e).onClicked(mouseX, mouseY);
		}
		mousePressedLastFrame = mousePressed;
		for(int i = 0; i < list.size(); i++)
			list.get(i).drawElement();
	}

	private Object[] getElementsAtCoords(int x, int y)
	{
		ArrayList<GUIElement> temp = new ArrayList<GUIElement>();
		for(GUIElement e: list)
		{
			int[][] coords = e.getCoords();
			if(x >= coords[0][0] && x <= coords[2][0])
				if(y >= coords[0][1] && y <= coords[2][1])
					temp.add(e);
		}
		return temp.toArray();
	}
	
	public static void addElementToList(GUIElement e)
	{
		addElement(e);
	}

	public static void main(String args[]) 
	{
		PApplet.main("xbhs.battleship.gui.GUI");
	}
	
}