package xbhs.battleship.game;

import xbhs.battleship.player.IdiotBot;
import xbhs.battleship.player.Player;

import java.util.Arrays;

/**
 * @author Mohak
 *
 */
public class Game
{
	private Space[][] grid0, grid1;
	private Ship[] ships;
	private Player[] thePlayers;
	private int currPlayer;

	public Game(int rowSize, int colSize)
	{
		thePlayers = new Player[2];

		currPlayer = 0;

		grid0 = new Space[rowSize][colSize];
		grid1 = new Space[rowSize][colSize];
		for (int i = 0; i < grid0.length; i++)
			for (int j = 0; j < grid0[0].length; j++)
			{
				grid0[i][j] = new Space();
				grid1[i][j] = new Space();
			}

		ships = new Ship[5];
		ships[0] = new Ship(2);
		ships[1] = new Ship(3);
		ships[2] = new Ship(3);
		ships[3] = new Ship(4);
		ships[4] = new Ship(5);
		placeShips(grid1, thePlayers[0]);
		placeShips(grid0, thePlayers[1]);
	}

	private void placeShips(Space[][] grid, Player player)
	{
		ShipPlacement[] placements = player.getPlacement(grid0, ships);
		for (int i = 0; i < placements.length; i++)
		{
			for (int j = 0; j < placements[i].getShip().getSize(); j++)
			{
				try
				{
					grid1[placements[i].getStartingPoint().y
							+ placements[i].getYdir() * j][placements[i]
							.getStartingPoint().x + placements[i].getXdir() * j]
							.addShip(placements[i].getShip());
				} catch (ShipAlreadyThereException e)
				{
					System.err.print("That's some bad programming, Faraaz");
					e.printStackTrace();
				}
			}
		}
	}

	public int getCurrPlayer()
	{
		return currPlayer;
	}

	public void setCurrPlayer(int currPlayer)
	{
		this.currPlayer = currPlayer;
	}

	public Ship[] getShips()
	{
		return ships;
	}

	public boolean isWon()
	{
		for (int i = 0; i < ships.length; i++)
		{
			if (!ships[i].isSunk()) return false;
		}
		return true;
	}

	public void makeMove()
	{
		Move[] moves = thePlayers[currPlayer].getMove(currPlayer == 0 ? Arrays.copyOf(grid0, grid0.length)
				: Arrays.copyOf(grid1, grid1.length));
		for (int i = 0; i < moves.length; i++)
		{
			try
			{
				(currPlayer == 0 ? grid0 : grid1)[moves[i].y][moves[i].x].hit();
			} catch(ArrayIndexOutOfBoundsException e)
			{
				System.err.println("You've got to pick up your game, Faraaz");
				e.printStackTrace();
			}
		}
		
		currPlayer = (currPlayer + 1) % 2;
	}
	
    public static void init()
    {
        Space[][] grid = new Space[4][1];
        Ship[] ships = {
            new Ship(2)
        }; 
    }
}
