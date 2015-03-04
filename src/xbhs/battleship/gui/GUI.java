package xbhs.battleship.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import processing.core.PApplet;

public class GUI extends PApplet 
{ 
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int initWidth = gd.getDisplayMode().getWidth()/2;
	int initHeight = gd.getDisplayMode().getHeight()/2;
	boolean mousePressedLastFrame = false;
	ArrayList<GUIElement> guiElements = new ArrayList<GUIElement>();

	@Override
	public void setup() 
	{
		size(initWidth,initHeight);
		background(0);
		initGUIElements();
		for(GUIElement e: guiElements)
			e.init();
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
		for(GUIElement e: guiElements)
			e.drawElement();
	}

	private Object[] getElementsAtCoords(int x, int y)
	{
		ArrayList<GUIElement> temp = new ArrayList<GUIElement>();
		for(GUIElement e: guiElements)
		{
			int[][] coords = e.getCoords();
			if(x >= coords[0][0] && x <= coords[2][0])
				if(y >= coords[0][1] && y <= coords[2][1])
					temp.add(e);
		}
		return temp.toArray();
	}

	public void addGUIElement(GUIElement e)
	{
		guiElements.add(e);
	}

	private void initGUIElements()
	{
		guiElements.add(new GridElement((getWidth() - getHeight()) / 2, 0, (getWidth() - getHeight()) / 2 + getHeight(), getHeight(), this));
	}

	public static void main(String args[]) 
	{
		PApplet.main("xbhs.battleship.gui.GUI");
	}
}