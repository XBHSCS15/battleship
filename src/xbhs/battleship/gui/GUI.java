<<<<<<< HEAD
package xbhs.battleship.gui;

<<<<<<< HEAD
import javax.swing.JFrame;
=======

package xbhs.battleship.gui;
>>>>>>> master
=======
import processing.core.*;
>>>>>>> origin/GUI

public class GUI extends PApplet {

  public void setup() 
  {
    size(200,200);
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
    PApplet.main(new String[] { "--present", "GUI" });
  }
}