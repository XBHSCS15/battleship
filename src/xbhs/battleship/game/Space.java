package xbhs.battleship.game;

public class Space
{
	private Ship ship;
	private boolean hit;
	
	public Space()
	{
		ship = null;
		hit = false;
	}
	
	public boolean hasShip()
	{
		return ship != null;
	}
	 
	public Ship shipContained()
	{
		return ship;
	}
	
	public void addShip(Ship ship) throws ShipAlreadyThereException
	{
		if(ship == null)
		{
			this.ship = ship;
		} else
		{
			throw new ShipAlreadyThereException();
		}
	}
	
	public boolean hasBeenHit()
	{
		return hit;
	}
}
