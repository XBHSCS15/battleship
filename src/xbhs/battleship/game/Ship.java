package xbhs.battleship.game;

public class Ship
{
	private int size;
	private int hitCount;
	
	public Ship(int size)
	{
		this.size = size;
		hitCount = 0;
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
