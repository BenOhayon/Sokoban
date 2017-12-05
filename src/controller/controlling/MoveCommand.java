package controller.controlling;

import model.data.AnchorPoint;
import model.data.Box;
import model.data.ItemInWarehouse;
import model.data.Level;
import model.data.Player;
import model.data.Position;
import model.data.UnknownCommandException;
import model.general.Model;
import model.policy.MySokobanPolicy;
import view.general.View;

/**
 * This class represents a moving command which controls the character 
 * moving options. If the requested move is possible after running
 * MySokoBanPolicy test and getting permission to move, this class executes
 * and performs the requested move. Finally, updates the current level.
 * 
 * @implements {@linkplain Command}
 * @author Sapir Shloush & Ben Ohayon
 */
public class MoveCommand extends SokobanCommand
{
	private String direction;
	private Level level;
	
	/**
	 * Constructs the move command with the requested direction and the current level.
	 * @param direction - The requested direction to move.
	 * @param level - The current level.
	 * @throws UnknownCommandException in case of inserting an invalid command.
	 */
	
	public MoveCommand(Model m, View v)
	{
		this.model = m;
		this.view = v;
	}
	
	/**
	 * This method calls MySokobanPolicy functions to test the possibility 
	 * to move in the requrested direction and finally executes and perfroms the move.
	 */
	public void execute()
	{
		level = model.getLevel();
		this.direction = params.removeFirst();
		if(direction.toLowerCase().equals("right") ||
		   direction.toLowerCase().equals("left") ||
		   direction.toLowerCase().equals("up") ||
		   direction.toLowerCase().equals("down"))
		{
			
			ItemInWarehouse character = level.getItemFromPosition(level.getCharacterPosition());
			
			if(direction.toLowerCase().equals("right"))
			{
				if (MySokobanPolicy.isValidMove(direction, level))
				{
					moveRight(character, level);
				}
			}
			
			else if(direction.toLowerCase().equals("left"))
			{
				if (MySokobanPolicy.isValidMove(direction, level))
				{
					moveLeft(character, level);
				}
			}
			
			else if(direction.toLowerCase().equals("up"))
			{
				if (MySokobanPolicy.isValidMove(direction, level))
				{
					moveUp(character, level);
				}
			}
			
			else if(direction.toLowerCase().equals("down"))
			{
				if (MySokobanPolicy.isValidMove(direction, level))
				{
					moveDown(character, level);
				}
			}
			
			level.setSteps(level.getSteps() + 1);
			model.setLevel(level);
			view.viewLevel(model.getLevel());
			
			if(model.getLevel().hallOfFame())
				view.win();
		}
		
		else
			System.err.println("Invalid move. Cannot move character.");
	}
	
	private void moveRight(ItemInWarehouse character, Level level)
	{
		Position rightPos = new Position(character.getPosition().getX(), character.getPosition().getY() + 1);
		ItemInWarehouse rightItem = level.getItemFromPosition(rightPos);
		Position rightrightPos = new Position(character.getPosition().getX(), character.getPosition().getY() + 2);
		ItemInWarehouse rightrightItem = level.getItemFromPosition(rightrightPos);
		
		
		if(rightItem instanceof Box)
		{
			if(rightrightItem == null)
			{
				level.getBoard().get(rightrightPos.getX()).set(rightrightPos.getY(), new Box(rightrightPos));
			}
			
			else if(rightrightItem instanceof AnchorPoint)
			{
				((AnchorPoint) rightrightItem).setItem(new Box(rightrightPos));
			}
			
			level.getBoard().get(rightPos.getX()).set(rightPos.getY(), new Player(rightPos));
		}
		
		else if(rightItem instanceof AnchorPoint)
		{
			AnchorPoint rt = (AnchorPoint)rightItem;
			
			if (rt.getItem() instanceof Box)
			{
				if (rightrightItem == null)
				{
					level.getBoard().get(rightrightPos.getX()).set(rightrightPos.getY(), new Box(rightrightPos));
				}

				else if (rightrightItem instanceof AnchorPoint)
				{
					((AnchorPoint) rightrightItem).setItem(new Box(rightrightPos));
				} 
			}
			
			rt.setItem(new Player(rightPos));
		}
		
		else
		{
			level.getBoard().get(character.getPosition().getX()).set(character.getPosition().getY() + 1, new Player(new Position(character.getPosition().getX(), character.getPosition().getY() + 1)));
		}
		
		updateCharacter(character, level);
	}
	
	private void moveLeft(ItemInWarehouse character, Level level)
	{
		Position leftPos = new Position(character.getPosition().getX(), character.getPosition().getY() - 1);
		ItemInWarehouse leftItem = level.getItemFromPosition(leftPos);
		Position leftleftPos = new Position(character.getPosition().getX(), character.getPosition().getY() - 2);
		ItemInWarehouse leftleftItem = level.getItemFromPosition(leftleftPos);
		
		
		if(leftItem instanceof Box)
		{
			if(leftleftItem == null)
			{
				level.getBoard().get(leftleftPos.getX()).set(leftleftPos.getY(), new Box(leftleftPos));
			}
			
			else if(leftleftItem instanceof AnchorPoint)
			{
				((AnchorPoint) leftleftItem).setItem(new Box(leftleftPos));
			}
			
			level.getBoard().get(leftPos.getX()).set(leftPos.getY(), new Player(leftPos));
		}
		
		else if(leftItem instanceof AnchorPoint)
		{
			AnchorPoint rt = (AnchorPoint)leftItem;
			
			if (rt.getItem() instanceof Box)
			{
				if (leftleftItem == null)
				{
					level.getBoard().get(leftleftPos.getX()).set(leftleftPos.getY(), new Box(leftleftPos));
				}

				else if (leftleftItem instanceof AnchorPoint)
				{
					((AnchorPoint) leftleftItem).setItem(new Box(leftleftPos));
				} 
			}
			
			rt.setItem(new Player(leftPos));
		}
		
		else
		{
			level.getBoard().get(character.getPosition().getX()).set(character.getPosition().getY() - 1, new Player(new Position(character.getPosition().getX(), character.getPosition().getY() - 1)));
		}
		
		updateCharacter(character, level);
	}
	
	private void moveDown(ItemInWarehouse character, Level level)
	{
		Position downPos = new Position(character.getPosition().getX() + 1, character.getPosition().getY());
		ItemInWarehouse downItem = level.getItemFromPosition(downPos);
		Position downdownPos = new Position(character.getPosition().getX() + 2, character.getPosition().getY());
		ItemInWarehouse downdownItem = level.getItemFromPosition(downdownPos);
		
		
		if(downItem instanceof Box)
		{
			if(downdownItem == null)
			{
				level.getBoard().get(downdownPos.getX()).set(downdownPos.getY(), new Box(downdownPos));
			}
			
			else if(downdownItem instanceof AnchorPoint)
			{
				((AnchorPoint) downdownItem).setItem(new Box(downdownPos));
			}
			
			level.getBoard().get(downPos.getX()).set(downPos.getY(), new Player(downPos));
		}
		
		else if(downItem instanceof AnchorPoint)
		{
			AnchorPoint rt = (AnchorPoint)downItem;
			
			if (rt.getItem() instanceof Box)
			{
				if (downdownItem == null)
				{
					level.getBoard().get(downdownPos.getX()).set(downdownPos.getY(), new Box(downdownPos));
				}

				else if (downdownItem instanceof AnchorPoint)
				{
					((AnchorPoint) downdownItem).setItem(new Box(downdownPos));
				} 
			}
			
			rt.setItem(new Player(downPos));
		}
		
		else
		{
			level.getBoard().get(character.getPosition().getX() + 1).set(character.getPosition().getY(), new Player(new Position(character.getPosition().getX() + 1, character.getPosition().getY())));
		}
		
		updateCharacter(character, level);
	}
	
	private void moveUp(ItemInWarehouse character, Level level)
	{
		Position upPos = new Position(character.getPosition().getX() - 1, character.getPosition().getY());
		ItemInWarehouse upItem = level.getItemFromPosition(upPos);
		Position upupPos = new Position(character.getPosition().getX() - 2, character.getPosition().getY());
		ItemInWarehouse upupItem = level.getItemFromPosition(upupPos);
		
		
		if(upItem instanceof Box)
		{
			if(upupItem == null)
			{
				level.getBoard().get(upupPos.getX()).set(upupPos.getY(), new Box(upupPos));
			}
			
			else if(upupItem instanceof AnchorPoint)
			{
				((AnchorPoint) upupItem).setItem(new Box(upupPos));
			}
			
			level.getBoard().get(upPos.getX()).set(upPos.getY(), new Player(upPos));
		}
		
		else if(upItem instanceof AnchorPoint)
		{
			AnchorPoint rt = (AnchorPoint)upItem;
			
			if (rt.getItem() instanceof Box)
			{
				if (upupItem == null)
				{
					level.getBoard().get(upupPos.getX()).set(upupPos.getY(), new Box(upupPos));
				}

				else if (upupItem instanceof AnchorPoint)
				{
					((AnchorPoint) upupItem).setItem(new Box(upupPos));
				} 
			}
			
			rt.setItem(new Player(upPos));
		}
		
		else
		{
			level.getBoard().get(character.getPosition().getX() - 1).set(character.getPosition().getY(), new Player(new Position(character.getPosition().getX() - 1, character.getPosition().getY())));
		}
		
		updateCharacter(character, level);
	}
	
	private void updateCharacter(ItemInWarehouse character, Level level)
	{
		if(character instanceof Player)
		{
			level.getBoard().get(character.getPosition().getX()).set(character.getPosition().getY(), null);
		}
		
		else if(character instanceof AnchorPoint)
		{
			((AnchorPoint) character).setItem(null);
		}		
	}
}