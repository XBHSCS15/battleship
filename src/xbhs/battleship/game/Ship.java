package xbhs.battleship.game;

/**
 * @author Mohak
 *
 */
public class Ship
{
	private int size;
	private int hitCount;
	
	public Ship(int size)
	{
		this.size = size;
		hitCount = 0;
	}
	
	public int getSize()
	{
		return size;
	}

	public void incrementHitCount()
	{
		hitCount++;
	}
	
	public boolean isSunk()
	{
		return hitCount >= size;
	}
}
