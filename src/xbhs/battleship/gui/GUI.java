package xbhs.battleship.gui;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;

import processing.core.PApplet;

public class GUI extends PApplet {
 
  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
  int initWidth = gd.getDisplayMode().getWidth()/2;
  int initHeight = gd.getDisplayMode().getHeight()/2;
  boolean mousePressedLastFrame = false;
  ArrayList<String> buttonTitles = new ArrayList<String>();
  /**
   * Placeholder variable stores if a grid space
   * should be colored to test click detection
   */
  boolean[][] grid = new boolean[10][10];
  @Override
  public void setup() 
  {
	for(int i = 0; i < 10; i++)
		Arrays.fill(grid[i], false);
	size(initWidth,initHeight);
    background(0);
    /**
    buttonTitles.add("A button");
    buttonTitles.add("Another button");
    buttonTitles.add("A third button");
    */
  }

  @Override
  public void draw() 
  {
    stroke(255);
    int sideLength = getHeight();
    int startX = (getWidth() - sideLength) / 2;
    int delta = sideLength / 10;
	// Fixes issues with truncation
    sideLength = delta * 10;
	if(mousePressed != mousePressedLastFrame && !mousePressedLastFrame)
	{
		int[] coords = getGridSquare(mouseX, mouseY);
		if(coords != null)
			grid[coords[0]][coords[1]] = !grid[coords[0]][coords[1]];
	}
	mousePressedLastFrame = mousePressed;
    for(int i = 0; i < 10; i++)
    	for(int j = 0; j < 10; j++)
    		if(grid[i][j])
    			fillSquare(i, j, Color.RED);
    		else
    			fillSquare(i, j, getForeground());
    for(int i = 0; i < 11; i++)
    {
    	line(startX, i * delta, startX + sideLength, i * delta);
    	line(i * delta + startX, 0, i * delta + startX, sideLength);
    }
    //addButtons();
  }
  
  /**
   * Gets the grid space a given coordinate is inside of
   */
  public int[] getGridSquare(int x, int y)
  {
	int sideLength = getHeight();
	int startX = (getWidth() - sideLength) / 2;
	int delta = sideLength / 10;
	// Fixes issues with truncation
	sideLength = delta * 10;
	if(x < startX || x > startX + sideLength)
		return null;
	int[] coords = new int[2];
	for(int i = 0; i < 11; i++)
		if(x < startX + (i * delta))
		{
			coords[0] = i;
			break;
		}
	for(int i = 0; i < 11; i++)
		if(y < i * delta)
		{
			coords[1] = i;
			break;
		}
	/**
	 * To avoid indexOutOfBoundsExceptions on the grid array
	 */
	coords[0] += coords[0] == 0 ? 0 : -1;
	coords[1] += coords[1] == 0 ? 0 : -1;
	return coords;
  }
  
  public void fillSquare(int x, int y, Color color)
  {
	  int sideLength = getHeight();
	  int startX = (getWidth() - sideLength) / 2;
	  int delta = sideLength / 10;
	  // Fixes issues with truncation
	  sideLength = delta * 10;
	  fill(color.getRGB());
	  beginShape();
	  	vertex((float)x * delta + startX, (float) y * delta);
	  	vertex((float)x * delta + startX, (float) (y + 1) * delta);
	  	vertex((float)(x + 1) * delta + startX, (float) (y + 1) * delta);
	  	vertex((float)(x + 1) * delta + startX, (float) y * delta);
	  endShape();
  }
  
  public void addButtons()
  {
	  int minX = 0, maxX = (getWidth() - getHeight()) / 2;
	  int deltaY = getHeight()/buttonTitles.size();
	  for(int i = 0; i < buttonTitles.size(); i++)
		  addButton(minX, maxX, i * deltaY, i + 1 * deltaY, buttonTitles.get(i));
  }
  
  public void addButton(int minX, int maxX, int minY, int maxY, String text)
  {
	  //TODO: Fill this out
  }
  
  public static void main(String args[]) 
  {
    PApplet.main("xbhs.battleship.gui.GUI");
  }
}