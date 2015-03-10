package xbhs.battleship.gui;

import java.awt.Color;

import xbhs.battleship.gui.buttonfunctions.IButtonFunction;

public class ButtonElement extends GUIElement
{
	
	IButtonFunction type;
	boolean isPressed = false;
	
	public ButtonElement(int minX, int minY, int maxX, int maxY, GUI gui, IButtonFunction type) 
	{
		super(minX, minY, maxX, maxY, gui);
		this.type = type;
	}
	
	public ButtonElement(int minX, int minY, int maxX, int maxY, GUI gui, int priority, IButtonFunction type) 
	{
		super(minX, minY, maxX, maxY, gui, priority);
		this.type = type;
	}
	
	@Override
	public void init() 
	{
	}
	
	@Override
	public void drawElement() 
	{
		drawRect(getCoords()[0][0], getCoords()[0][1], getCoords()[2][0], getCoords()[2][1], Color.gray, 0.8);
		getGUI().fill(Color.BLACK.getRGB());
		getGUI().textSize(15f);
		getGUI().textMode(GUI.MODEL);
		int height =  getCoords()[2][1] - getCoords()[0][1];
		int width =  getCoords()[2][0] - getCoords()[0][0];
		getGUI().text(type.getName(), getCoords()[0][0] + (width - getGUI().textWidth(type.getName()))/2 , getCoords()[0][1] + height/2);
	}
	
	@Override
	public void onClicked(int x, int y) 
	{	
		type.act();
	}
}
