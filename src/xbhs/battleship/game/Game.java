package xbhs.battleship.game;

import xbhs.battleship.player.Player;

public class Game
{
	private Space[][] grid1, grid2;
	private Ship[] ships;
	private Player[] thePlayers;

	public Game(int rowSize, int colSize)
	{
		thePlayers = new Player[2];
		
		grid1 = new Space[rowSize][colSize];
		grid2 = new Space[rowSize][colSize];
		for (int i = 0; i < grid1.length; i++)
			for (int j = 0; j < grid1[0].length; j++)
			{
				grid1[i][j] = new Space();
				grid2[i][j] = new Space();
			}
		
		ships = new Ship[5];
		ships[0] = new Ship(2);
		ships[1] = new Ship(3);
		ships[2] = new Ship(3);
		ships[3] = new Ship(4);
		ships[4] = new Ship(5);
	}
	
	private void placeShips(Space[][] grid)
	{
		
	}
	
	public Ship[] getShips()
	{
		return ships;
	}
	
	public boolean isWon()
	{
		for(int i = 0; i < ships.length; i++)
		{
			if (!ships[i].isSunk())
				return false;
		}
		return true;
	}
	
	public void makeMove(Move[] move)
	{
		
	}
}
