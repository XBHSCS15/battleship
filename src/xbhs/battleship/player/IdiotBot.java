
package xbhs.battleship.player;
import xbhs.battleship.game.*;

/**
 *
 * @author Faraaz
 * 
 * BUGS: 
 * the getPlacement() method places randomly, one at a time.
 * It cannot retry if it runs out of room. Make sure the board size is large 
 * enough so that ships can be placed in any arrangement without running out of 
 * space for the last ship.
 */
public class IdiotBot extends ComputerPlayer 
{
    /** 
     *  Returns a move, a.k.a. a place to fire a missile. It will 
     *  not fire in an area that has already been fired at.
     * 
     *  @param grid
     */
    public Move[] getMove(Space[][] grid)
    {
        // "dart" signifies random value
        int dartX;
        int dartY;
        
        // currently we only handle one move at a time
        Move[] moves = new Move[1];        
        
        // get a valid move and return it
        do {
            dartX = (int)(Math.random()*grid.length);
            dartY = (int)(Math.random()*grid[0].length);
            moves[0] = new Move(dartX, dartY);
        } while (!isValid(moves[0], grid));
        
        try {
            grid[dartX][dartY].hit();
        } catch (NullPointerException e) {
            // no ship here
        }
        
        return moves;
    }
    
    /**
     * Returns a boolean that determines if the potential move given is valid.
     * Checks if out of bounds or if already fired in that space.
     * This is a helper method for the getMove() method.
     * 
     * @param m
     * @param grid
     * @return 
     */
    private boolean isValid(Move m, Space[][] grid)
    {
        // check if x coord is out of bounds
        if (m.getX()<0 || m.getX()>=grid.length)
            return false;
        // check if y coord is out of bounds
        else if (m.getY()<0 || m.getY()>=grid[0].length)
            return false;
        // check if already fired here
        else if (grid[(int)m.getX()][(int)m.getY()].isHit())
            return false;
        
        return true;
    }
    
    /**
     * Checks the board and returns an array of ship placements to determine 
     * where ships should be placed to begin the game. This is randomly 
     * generated.
     * 
     * @param grid
     * @param ships
     * @return 
     */
    public ShipPlacement[] getPlacement(Space[][] grid, Ship[] ships)
    {
        ShipPlacement[] shipPlacements = new ShipPlacement[ships.length];
        
        for (int i = 0; i < shipPlacements.length; i++)
        {
            // need to place ships on fake board as I go
            shipPlacements[i] = oneShipPlacement(grid, ships[i]);
            for (int j = 0; j < shipPlacements[i].getShip().getSize(); j++)
            {
		try 
                {
                    grid[shipPlacements[i]
                        .getStartingPoint().x + shipPlacements[i].getXdir() * j]
                        [shipPlacements[i].getStartingPoint().y
                        + shipPlacements[i].getYdir() * j]
                        .addShip(shipPlacements[i].getShip());
                } catch (ShipAlreadyThereException e)
                {
                    System.err.print("ship already there bro");
                    e.printStackTrace();
                }
            }
        }
        
        return shipPlacements;
    }
    
    /**
     * A helper method for the getPlacement() method that randomly places one
     * ship on the board.
     * 
     * @param grid
     * @param ship
     * @return 
     */
    private ShipPlacement oneShipPlacement(Space[][] grid, Ship ship)
    {
        int dartX;
        int dartY;
        int dartDirX;
        int dartDirY;
        Move m;
        ShipPlacement placement;
        
        do {
            dartX = (int)(Math.random()*grid.length);
            dartY = (int)(Math.random()*grid[0].length);
            m = new Move(dartX, dartY);
            // this makes a random x direction and y direction
            // only one direction can be 1, other must be 0
            dartDirX = (int)(Math.random()*2);
            dartDirY = (dartDirX+1)%2;
            
            placement = new ShipPlacement(m, ship, dartDirX, dartDirY);
        } while (!isValid(placement, grid));

        return placement;
    }
    
    // check if the placement of one ship is valid
    /**
     * A helper method for the oneShipPlacement() method that checks if a 
     * potential ship placement is valid.
     * 
     * @param p
     * @param grid
     * @return 
     */
    private boolean isValid(ShipPlacement p, Space[][] grid)
    {
        // series to check start of ship goes out of bounds
        if (p.getStartingPoint().getX()<0 
                || p.getStartingPoint().getX()>=grid.length)
            return false;
        else if (p.getStartingPoint().getY()<0 
                || p.getStartingPoint().getY()>=grid[0].length)
            return false;
        Move start = new Move((int)p.getStartingPoint().getX(), 
                              (int)p.getStartingPoint().getY());
        Ship ship = p.getShip();
        // series to check if end of ship goes out of bounds
        if (start.getX()+p.getXdir()*ship.getSize() < 0
                || start.getX()+p.getXdir()*ship.getSize() > grid.length)
            return false;
        else if (start.getY()+p.getYdir()*ship.getSize() < 0
                || start.getY()+p.getYdir()*ship.getSize() > grid[0].length)
            return false;
        // series to check if spaces are empty
        for (int i = 0; i < ship.getSize(); i++)
        {
            int x = (int)start.getX() + i*p.getXdir();
            int y = (int)start.getY() + i*p.getYdir();
            if (grid[x][y].hasShip())
                return false;
        }
        return true;
    }
<<<<<<< HEAD
    
    // some debug code
    public static void main(String[] args)
    {
        Space[][] grid = new Space[10][10];
        Ship[] ships = {
            new Ship(2), new Ship(3), new Ship(3), new Ship(4), new Ship(5)
        }; 
        IdiotBot idiot = new IdiotBot();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                grid[i][j] = new Space();
        ShipPlacement[] placements = idiot.getPlacement(grid, ships);
        for (int i = 0; i < placements.length; i++)
            System.out.println("Ship" + i + ": " 
                        + placements[i].getStartingPoint()
                        + ", " + placements[i].getXdir() 
                        + ", " + placements[i].getYdir()
                        + "; " + placements[i].getShip().getSize());
        
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                if (grid[i][j].hasShip())
                    System.out.print("X");
                else
                    System.out.print("-");
                
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println(idiot.getMove(grid)[0]);
        System.out.println(idiot.getMove(grid)[0]);
        System.out.println(idiot.getMove(grid)[0]);
        System.out.println(idiot.getMove(grid)[0]);
        System.out.println(idiot.getMove(grid)[0]);
        System.out.println(idiot.getMove(grid)[0]);
        System.out.println(idiot.getMove(grid)[0]);
        System.out.println(idiot.getMove(grid)[0]);
        
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                if (grid[i][j].isHit() && grid[i][j].hasShip())
                    System.out.print("O");
                else if (grid[i][j].isHit())
                    System.out.print("X");
                else 
                    System.out.print("-");
                
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
=======
>>>>>>> origin/master
}
