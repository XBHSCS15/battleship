package xbhs.battleship.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import processing.core.PApplet;


public class GUI extends PApplet {

  public void setup() 
  {
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();
	size(width,height);
    background(0);
  }

  public void draw() 
  {
    stroke(255);
    if (mousePressed) 
    {
      line(mouseX,mouseY,pmouseX,pmouseY);
    }
  }
  
  public static void main(String args[]) 
  {
    PApplet.main(new String[] { "--present", "xbhs.battleship.gui.GUI" });
  }
}