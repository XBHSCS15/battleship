package xbhs.battleship.gui;

public abstract class GUIElement 
{
	int[][] coords;
	GUI gui;

	public GUIElement(int minX, int minY, int maxX, int maxY, GUI gui)
	{
		coords = new int[][]{{minX , minY} , {minX , maxY} , {maxX , maxY} , {maxX , minY}};
		this.gui = gui;
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
}