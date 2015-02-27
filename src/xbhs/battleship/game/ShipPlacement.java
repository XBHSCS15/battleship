package xbhs.battleship.game;

import java.awt.Point;

public class ShipPlacement
{
	private Point startingPoint;
	private Ship ship;
	private int xdir;
	private int ydir;
	
	public ShipPlacement(Point startingPoint, Ship ship, int xdir, int ydir)
	{
		this.startingPoint = startingPoint;
		this.ship = ship;
		this.xdir = xdir;
		this.ydir = ydir;
	}

	public Point getStartingPoint()
	{
		return startingPoint;
	}

	public Ship getShip()
	{
		return ship;
	}

	public int getXdir()
	{
		return xdir;
	}

	public int getYdir()
	{
		return ydir;
	}
}
