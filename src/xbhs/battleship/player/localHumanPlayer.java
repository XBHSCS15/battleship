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
        Move[] coordinate = new Move[1];
        coordinate[0] = new Move(x,y);
        return coordinate;
    }
    public ShipPlacement[] getPlacement(Space[][] grid, Ship[] ships)
    {
        return null;
    }
}
