package xbhs.battleship.gui;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;
import static xbhs.battleship.gui.GUIElementListHandler.*;

@SuppressWarnings("serial")
public class GUI extends PApplet 
{ 
    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int initWidth = gd.getDisplayMode().getWidth()/2;
    int initHeight = gd.getDisplayMode().getHeight()/2;
    boolean mousePressedLastFrame = false;

    @Override
    public void setup() 
    {
        list = new ArrayList<GUIElement>();
        size(initWidth,initHeight);
        String workingDirectory = System.getProperty("user.dir");
        BufferedImage tempImg = null, img;
        String filePath = workingDirectory + File.separator + "assets" + File.separator + "textures" + File.separator + "oceanripple.gif"; 
        try {
            tempImg = ImageIO.read(new File(filePath));
        } catch (IOException e) {
        	System.out.println("Texture expected at: " + filePath + " Could not be found, please ensure your working directory is properly setup and the texture exists.");
        }
        img = new BufferedImage(tempImg.getWidth(), tempImg.getHeight(), BufferedImage.TYPE_INT_RGB);
        int width = img.getWidth();
        int height = img.getHeight();
        for(int i = 0; i < width; i++)
        	for(int j = 0; j < height; j++)
        		img.setRGB(i, j, tempImg.getRGB(i, j));
        PImage bg = new PImage(img);
        bg.resize(getWidth(), getHeight());
        background(bg);
        initGUIElements();
        for(int i = 0; i < list.size(); i++)
                list.get(i).init();
    }

    @Override
    public void draw() 
    {
        if(mousePressed)
        {
                Object[] elementsAtPoint = getElementsAtCoords(mouseX, mouseY);
                if(elementsAtPoint != null)
                        for(Object e: elementsAtPoint)
                                ((GUIElement)e).onClicked(mouseX, mouseY);
        }
        mousePressedLastFrame = mousePressed;
        for(int i = 0; i < list.size(); i++)
                list.get(i).drawElement();
    }

    private Object[] getElementsAtCoords(int x, int y)
    {
        ArrayList<GUIElement> temp = new ArrayList<GUIElement>();
        for(GUIElement e: list)
        {
                int[][] coords = e.getCoords();
                if(x >= coords[0][0] && x <= coords[2][0])
                        if(y >= coords[0][1] && y <= coords[2][1])
                                temp.add(e);
        }
        return temp.toArray();
    }

    private void initGUIElements()
    {
            addElement(new GridElement((getWidth() - getHeight()) / 2, 0, 
                (getWidth() - getHeight()) / 2 + getHeight(), getHeight(), this, Integer.MAX_VALUE));
    }
       
    public static void main(String args[]) 
    {
            PApplet.main("xbhs.battleship.gui.GUI");
    }
}