package xbhs.battleship.player;
import xbhs.battleship.game.*;

public interface Player 
{
    public Move[] getMove(Space[][] grid);
    
    public ShipPlacement[] getPlacement(Space[][] grid, Ship[] ships);
}