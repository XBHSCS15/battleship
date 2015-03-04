package xbhs.battleship.gui;

import java.util.ArrayList;

/**
 * 	This will probably be necessary as the order
 * 	of the GUIElementList will determine rendering
 * 	order and therefore foreground/background.
 * 	This is intended to be a static import to the
 * 	GUI class
 */
public class GUIElementListHandler 
{
	public static ArrayList<GUIElement> list;


	public static boolean replaceElement(GUIElement elementToReplace, GUIElement elementToReplaceWith)
	{
		for(int i = 0; i < list.size(); i++)
			if(list.get(i).equals(elementToReplace))
			{
				list.set(i, elementToReplaceWith);
				return true;
			}
		return false;
	}
	public static boolean swapElements(GUIElement ele1, GUIElement ele2)
	{
		int x = -1, y = -1;
		for(int i = 0; i < list.size(); i++)
			if(list.get(i).equals(ele1))
				x = i;
			else if(list.get(i).equals(ele2))
				y = i;
		if(x != -1 || y != -1)
		{
			list.set(y, ele1);
			list.set(x, ele2);
			return true;
		}
		return false;
	}

	public static void addElement(GUIElement e)
	{
		if(list.size() == 0)
		{
			list.add(e);
			return;
		}
		for(int i = 0; i < list.size(); i++)
			if(e.getRenderPriority() < list.get(i).getRenderPriority())
			{
				list.add(i, e);
				return;
			}
	}
	public static void addInFrontOf(GUIElement elementToAdd, GUIElement referenceElement)
	{
		for(int i = 0; i < list.size(); i++)
			if(referenceElement == list.get(i))
			{
				list.add(i + 1, elementToAdd);
				elementToAdd.setRenderPriority(list.get(i).getRenderPriority());
				return;
			}
	}
	public static void addBehind(GUIElement elementToAdd, GUIElement referenceElement)
	{
		for(int i = 0; i < list.size(); i++)
			if(referenceElement == list.get(i))
			{
				list.add(i, elementToAdd);
				elementToAdd.setRenderPriority(list.get(i + 1).getRenderPriority());
				return;
			}
	}
	public static void moveToInFrontOf(GUIElement elementToMove, GUIElement referenceElement)
	{
		for(int i = 0; i < list.size(); i++)
			if(elementToMove == list.get(i))
			{
				list.remove(i);
				break;
			}
		for(int i = 0; i < list.size(); i++)
			if(referenceElement == list.get(i))
			{
				list.add(i + 1, elementToMove);
				elementToMove.setRenderPriority(list.get(i).getRenderPriority());
				return;
			}
	}
	public static void moveToBehind(GUIElement elementToMove, GUIElement referenceElement)
	{
		for(int i = 0; i < list.size(); i++)
			if(elementToMove == list.get(i))
			{
				list.remove(i);
				break;
			}
		for(int i = 0; i < list.size(); i++)
			if(referenceElement == list.get(i))
			{
				list.add(i, elementToMove);
				elementToMove.setRenderPriority(list.get(i + 1).getRenderPriority());
				return;
			}
	}
}
