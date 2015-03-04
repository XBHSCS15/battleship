package xbhs.battleship.game;

/**
 * @author Mohak
 *
 */
public class Space implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private Ship ship;
	private boolean hit;

	public Space()
	{
		ship = null;
		hit = false;
	}

	public void hit()
	{
		if (hit == false)
		{
			ship.incrementHitCount();
			hit = true;
		}
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
		if (this.ship == null)
		{
			this.ship = ship;
		} else
		{
			throw new ShipAlreadyThereException();
		}
	}

	public boolean isHit()
	{
		return hit;
	}
}
