package xbhs.battleship.gui;

import java.awt.Color;

public abstract class GUIElement 
{
	int[][] coords;
	GUI gui;
	int priority;

	public GUIElement(int minX, int minY, int maxX, int maxY, GUI gui)
	{
		this(minX, minY, maxX, maxY, gui, 0);
	}
	
	public GUIElement(int minX, int minY, int maxX, int maxY, GUI gui, int renderPriority)
	{
		coords = new int[][]{{minX , minY} , {minX , maxY} , {maxX , maxY} , {maxX , minY}};
		this.gui = gui;
		priority = renderPriority;
	}

	public abstract void init();

	public abstract void drawElement();

	public abstract void onClicked(int x, int y);

	public int[][] getCoords()
	{
		return coords;
	}

	public void setMinX(int x)
	{
		coords[0][0] = x;
		coords[1][0] = x;
	}

	public void setMaxX(int x)
	{
		coords[2][0] = x;
		coords[3][0] = x;
	}

	public void setMinY(int y)
	{
		coords[0][1] = y;
		coords[3][1] = y;
	}

	public void setMaxY(int y)
	{
		coords[1][1] = y;
		coords[2][1] = y;
	}

	protected GUI getGUI()
	{
		return gui;
	}
	
	public int getRenderPriority()
	{
		return priority;
	}
	
	public void setRenderPriority(int i)
	{
		priority = i;
	}
	
	public void fillSquare(int x, int y, Color color, double opacity)
	{
		int sideLength = getCoords()[2][0] - getCoords()[0][0];
		int startX = getCoords()[0][0];
		int delta = sideLength / 10;
		// Fixes issues with truncation
		sideLength = delta * 10;
		getGUI().fill(color.getRGB(), 100f * (float)opacity);
		getGUI().beginShape();
		getGUI().vertex((float)x * delta + startX, (float) y * delta);
		getGUI().vertex((float)x * delta + startX, (float) (y + 1) * delta);
		getGUI().vertex((float)(x + 1) * delta + startX, (float) (y + 1) * delta);
		getGUI().vertex((float)(x + 1) * delta + startX, (float) y * delta);
		getGUI().endShape();
	}
}