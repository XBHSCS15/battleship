package xbhs.battleship.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import processing.core.PApplet;

public class GUI extends PApplet {
 
  GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
  int width = gd.getDisplayMode().getWidth()/2;
  int height = gd.getDisplayMode().getHeight()/2;
	
  public void setup() 
  {
	size(width,height);
    background(0);
  }

  public void draw() 
  {
    stroke(255);
    int sideLength = getHeight();
    int startX = (getWidth() - sideLength) / 2;
    int delta = sideLength / 10;
    for(int i = 0; i < 11; i++)
    {
    	line(startX, i * delta, startX + sideLength, i * delta);
    	line(i * delta + startX, 0, i * delta + startX, sideLength);
    }
  }
  
  public static void main(String args[]) 
  {
    PApplet.main("xbhs.battleship.gui.GUI");
  }
}