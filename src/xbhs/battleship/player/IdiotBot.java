
package xbhs.battleship.player;
import xbhs.battleship.game.*;

/**
 *
 * @author Faraaz
 */
public class IdiotBot implements Player 
{
    public Move[] getMove(Space[][] grid)
    {
        // "dart" signifies random value
        // how are we defining x and y?
        int dartX;
        int dartY;
        
        // currently we only handle one move at a time
        Move[] moves = new Move[1];        
        
        // get a valid move and return it
        do {
            dartX = (int)(Math.random()*grid.length);
            dartY = (int)(Math.random()*grid[0].length);
            moves[0] = new Move(dartX, dartY);
        } while (isValid(moves[0], grid));
        
        return moves;
    }
    
    private boolean isValid(Move m, Space[][] grid)
    {
        // check if x coord is out of bounds
        if ((int)m.getX()<0 || (int)m.getX()>=grid.length)
            return false;
        // check if y coord is out of bounds
        else if ((int)m.getY()<0 || (int)m.getY()>=grid[0].length)
            return false;
        // check if already fired here
        else if (!grid[(int)m.getX()][(int)m.getY()].isHit())
            return false;
        
        return true;
    }
    
    public ShipPlacement[] getPlacement(Space[][] grid, Ship[] ships)
    {
        // TODO: method is not finished
        ShipPlacement[] shipPlacements = new ShipPlacement[ships.length];
        return shipPlacements;
    }
    
    // some debug code
    public static void main(String[] args)
    {
        Space[][] grid = new Space[10][10];
        IdiotBot idiot = new IdiotBot();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                grid[i][j] = new Space();
        System.out.println(idiot.getMove(grid));
        System.out.println(idiot.getMove(grid));
        System.out.println(idiot.getMove(grid));
        System.out.println(idiot.getMove(grid));
        
        //System.out.println(dartX + ", " + dartY);
    }
}
