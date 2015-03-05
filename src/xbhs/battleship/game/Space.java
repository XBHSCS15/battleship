package xbhs.battleship.game;

import xbhs.battleship.player.Player;

/**
 * @author Mohak
 *
 */
public class Space implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private Ship ship;
	private boolean hit;
	private Player player;

	public Space(Player p)
	{
		ship = null;
		hit = false;
		player = p;
	}

	public void hit()
	{
<<<<<<< HEAD
                hit = true;
		ship.incrementHitCount();
=======
		if (hit == false)
		{
			ship.incrementHitCount();
			hit = true;
		}
>>>>>>> origin/master
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
	
	public Player getPlayer()
	{
		return player;
	}
}
