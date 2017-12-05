package model.policy;

import model.data.AnchorPoint;
import model.data.Box;
import model.data.ItemInWarehouse;
import model.data.Level;
import model.data.Position;
import model.data.Wall;

/**
 * MySokobanPolicy.java
 * This class dictates the rules of moving the character across the board.
 * Provides static service methods for the flow control of the game.
 * 
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public class MySokobanPolicy
{
	
	/**
	 * This method checks if the requested moving direction is possible
	 * using four other methods.
	 * @param direction - The requested direction to move the character.
	 * @param level - The requested level
	 * @return True if the requested moving is possible, otherwise false.
	 */
	public static boolean isValidMove(String direction, Level level)
	{
		Position charPos = level.getCharacterPosition();
		
		switch(direction.toLowerCase())
		{
		case "right":
			return isValidRight(charPos, direction, level);
					
		case "left":
			return isValidLeft(charPos, direction, level);
			
		case "up":
			return isValidUp(charPos, direction, level);
			
		default:
			return isValidDown(charPos, direction, level);
		}
	}
	
	/**
	 * There is no use in this method at this time.
	 * @param direction
	 * @param level
	 * @return -
	 */
	public static boolean canPullBox(String direction, Level level) {return false;}
	
	
	
	private static boolean isValidDown(Position charPos, String direction, Level level)
	{
		Position downPos = new Position(charPos.getX() + 1, charPos.getY());
		ItemInWarehouse downItem = level.getItemFromPosition(downPos);
		
		if(downItem instanceof Wall)
			return false;
		
		if(downItem instanceof AnchorPoint)
		{
			Position downdownPos = new Position(charPos.getX() + 2, charPos.getY());
			ItemInWarehouse downdownItem = level.getItemFromPosition(downdownPos);
			if(((AnchorPoint) downItem).getItem() instanceof Box)
			{
				if((downdownItem instanceof Box) || (downdownItem instanceof Wall) 
						|| (downdownItem instanceof AnchorPoint && ((AnchorPoint)downdownItem).getItem() instanceof Box))
					return false;
			}
		}
		
		if(downItem instanceof Box)
		{
			downPos.setX(downPos.getX() + 1);
			downItem = level.getItemFromPosition(downPos);
			if(downItem instanceof Box || downItem instanceof Wall)
				return false;
			
			return true;
		}
		
		return true;
	}
	
	
	private static boolean isValidRight(Position charPos, String direction, Level level)
	{
		Position rightPos = new Position(charPos.getX(), charPos.getY()+1);
		ItemInWarehouse rightItem = level.getItemFromPosition(rightPos);
		
		
		if(rightItem instanceof Wall)
			return false;
		
		if(rightItem instanceof AnchorPoint)
		{
			Position rightrightPos = new Position(charPos.getX(), charPos.getY()+2);
			ItemInWarehouse rightrightItem = level.getItemFromPosition(rightrightPos);
			if(((AnchorPoint) rightItem).getItem() instanceof Box)
			{
				if((rightrightItem instanceof Box) || (rightrightItem instanceof Wall) 
						|| (rightrightItem instanceof AnchorPoint && ((AnchorPoint)rightrightItem).getItem() instanceof Box))
					return false;
			}
		}
		
		if(rightItem instanceof Box)
		{
			rightPos.setY(rightPos.getY() + 1);
			rightItem = level.getItemFromPosition(rightPos);
			if(rightItem instanceof Box || rightItem instanceof Wall)
				return false;
			
			return true;
		}
		
		return true;
	}
	
	private static boolean isValidLeft(Position charPos, String direction, Level level)
	{
		Position leftPos = new Position(charPos.getX(), charPos.getY() - 1);
		ItemInWarehouse leftItem = level.getItemFromPosition(leftPos);
		
		if(leftItem instanceof Wall)
			return false;
		
		if(leftItem instanceof AnchorPoint)
		{
			Position leftleftPos = new Position(charPos.getX(), charPos.getY() - 2);
			ItemInWarehouse leftleftItem = level.getItemFromPosition(leftleftPos);
			if(((AnchorPoint) leftItem).getItem() instanceof Box)
			{
				if((leftleftItem instanceof Box) || (leftleftItem instanceof Wall) 
						|| (leftleftItem instanceof AnchorPoint && ((AnchorPoint)leftleftItem).getItem() instanceof Box))
					return false;
			}
		}
		
		if(leftItem instanceof Box)
		{
			leftPos.setY(leftPos.getY() - 1);
			leftItem = level.getItemFromPosition(leftPos);
			if(leftItem instanceof Box || leftItem instanceof Wall)
				return false;
			
			return true;
		}
		
		return true;
	}
	
	private static boolean isValidUp(Position charPos, String direction, Level level)
	{
		Position upPos = new Position(charPos.getX() - 1, charPos.getY());
		ItemInWarehouse upItem = level.getItemFromPosition(upPos);
		
		if(upItem instanceof Wall)
			return false;
		
		if(upItem instanceof AnchorPoint)
		{
			Position upupPos = new Position(charPos.getX() - 2, charPos.getY());
			ItemInWarehouse upupItem = level.getItemFromPosition(upupPos);
			if(((AnchorPoint) upItem).getItem() instanceof Box)
			{
				if((upupItem instanceof Box) || (upupItem instanceof Wall) 
						|| (upupItem instanceof AnchorPoint && ((AnchorPoint)upupItem).getItem() instanceof Box))
					return false;
			}
		}
		
		if(upItem instanceof Box)
		{
			upPos.setX(upPos.getX() - 1);
			upItem = level.getItemFromPosition(upPos);
			if(upItem instanceof Box || upItem instanceof Wall)
				return false;
			
			return true;
		}
		
		return true;
	}
}