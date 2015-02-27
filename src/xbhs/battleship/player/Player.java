package xbhs.battleship.player;
import xbhs.battleship.game.*;

public interface Player 
{
    Move[] getMove(Space[][] grid);
    ShipPlacement[] getPlacement(Space[][] grid, Ship[] ships);
}