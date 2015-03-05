/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xbhs.battleship.player;

import java.util.Scanner;
import xbhs.battleship.game.*;

/**
 *
 * @author Yusheng Feng
 */
public class localHumanPlayer implements Player
{
     public Move[] getMove(Space[][] grid)
    {
        Scanner keyboard = new Scanner(System.in);
        int x = keyboard.nextInt();
        int y = keyboard.nextInt();
        Move[] moves = new Move[1]; 
        moves[0] = new Move(x, y);
        if (isValid(moves[0], grid))
        {
            return moves;
        }
        return null;
    }
     
    public ShipPlacement[] getPlacement(Space[][] grid, Ship[] ships)
    {
        return null;
    }
    
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
}
