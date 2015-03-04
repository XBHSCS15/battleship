package xbhs.battleship.gui;

import java.awt.Color;
import java.util.Arrays;

import xbhs.battleship.game.Space;

public class GridElement extends GUIElement
{
	private Space[][] grid;
	public GridElement(int minX, int minY, int maxX, int maxY, GUI gui, Space[][] grid)
	{
		super(minX, minY, maxX, maxY, gui);
		this.grid = grid;
	}
	
	public GridElement(int minX, int minY, int maxX, int maxY, GUI gui, int renderPriority, Space[][] grid)
	{
		super(minX, minY, maxX, maxY, gui, renderPriority);
		this.grid = grid;
	}

	@Override
	public void init() 
	{
	}

	@Override
	public void drawElement() 
	{
		int sideLength = getCoords()[2][0] - getCoords()[0][0];
		int startX = getCoords()[0][0];
		int delta = sideLength / 10;		
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++)
				if(grid[i][j].hasShip())
					fillSquare(i, j, Color.WHITE);
				else if(grid[i][j].isHit())
					fillSquare(i, j, Color.RED);
				else
					fillSquare(i, j, getGUI().getForeground());
		for(int i = 0; i < 11; i++)
		{
			if(i == 11)
			{
				getGUI().line(getCoords()[0][0], getCoords()[0][1], getCoords()[3][0], getCoords()[3][1]);
				getGUI().line(getCoords()[2][0], getCoords()[2][1], getCoords()[3][0], getCoords()[3][1]);
			}
			getGUI().line(startX, i * delta, startX + sideLength, i * delta);
			getGUI().line(i * delta + startX, 0, i * delta + startX, sideLength);
		}
	}

	@Override
	public void onClicked(int x, int y) 
	{
		if(!((getGUI().g.getPlayerObject()) == grid[0][0].getPlayer()))
			return;
		if(getGUI().mousePressed != getGUI().mousePressedLastFrame && !getGUI().mousePressedLastFrame)
		{
			int[] coords = getGridSquare(getGUI().mouseX, getGUI().mouseY);
			if(coords != null)
				if(!grid[coords[0]][coords[1]].isHit())
					grid[coords[0]][coords[1]].hit();
		}
	}

	public int[] getGridSquare(int x, int y)
	{
		int sideLength = getCoords()[2][0] - getCoords()[0][0];
		int startX = getCoords()[0][0];
		int delta = sideLength / 10;
		// Fixes issues with truncation
		sideLength = delta * 10;
		if(x < startX || x > startX + sideLength)
			return null;
		int[] coords = new int[2];
		for(int i = 0; i < 11; i++)
			if(i == 11)
			{
				coords[1] = 11;
			}
			else if(x <= startX + (i * delta))
			{
				coords[0] = i;
				break;
			}
		for(int i = 0; i < 11; i++)
			if(i == 11)
			{
				coords[1] = 11;
			}
			else if(y <= i * delta)
			{
				coords[1] = i;
				break;
			}
		/**
		 * To avoid indexOutOfBoundsExceptions on the grid array
		 */
		coords[0] += coords[0] == 0 ? 0 : -1;
		coords[1] += coords[1] == 0 ? 0 : -1;
		return coords;
	}

	public void fillSquare(int x, int y, Color color)
	{
		int sideLength = getCoords()[2][0] - getCoords()[0][0];
		int startX = getCoords()[0][0];
		int delta = sideLength / 10;
		// Fixes issues with truncation
		sideLength = delta * 10;
		getGUI().fill(color.getRGB());
		getGUI().beginShape();
		getGUI().vertex((float)x * delta + startX, (float) y * delta);
		getGUI().vertex((float)x * delta + startX, (float) (y + 1) * delta);
		getGUI().vertex((float)(x + 1) * delta + startX, (float) (y + 1) * delta);
		getGUI().vertex((float)(x + 1) * delta + startX, (float) y * delta);
		getGUI().endShape();
	}
}
