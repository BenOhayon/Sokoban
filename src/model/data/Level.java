package model.data;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a level in Sokoban game.
 * 
 * @implemets {@linkplain Serializable}
 * @author Sapir Shloush & Ben Ohayon
 */
public class Level implements Serializable
{
	ArrayList<ArrayList<ItemInWarehouse>> board;
	transient private int steps;
	transient private int seconds;
	private String name;
	
	
	/**
	 * Constructs a level.
	 */
	public Level()
	{
		board = null;
		steps = 0;
		seconds = 0;
	}
	
	/**
	 * @return the current position of the character in the board.
	 */
	public Position getCharacterPosition()
	{
		Position charPos = null;
		for(ArrayList<ItemInWarehouse> x : this.board)
		{
			for(ItemInWarehouse y : x)
			{
				if(y instanceof Player)
					charPos = y.getPosition();
				
				else if(y instanceof AnchorPoint)
				{
					if(((AnchorPoint) y).getItem() instanceof Player)
						return ((AnchorPoint) y).getItem().getPosition();
				}
			}
		}
		
		return charPos;
	}
	
	/**
	 * @return the number of rows of the board.
	 */
	public int getNumberOfRows()
	{
		return board.size();
	}
	
	/**
	 * @return the number of columns of the board.
	 */
	public int getNumberOfColumns()
	{
		int maxColumn = 0;
		for(ArrayList<ItemInWarehouse> x : board)
		{
			if(maxColumn < x.size())
				maxColumn = x.size();
		}
		
		return maxColumn;
	}

	/**
	 * This method finds an item in the board according to the given position.
	 * @param pos - The position of the requested item in the board.
	 * @return the item in the specified 'pos'.
	 */
	public ItemInWarehouse getItemFromPosition(Position pos)
	{
		if(pos.getX() >= this.getBoard().size() || pos.getY() >= this.getBoard().get(pos.getX()).size())
		{
			System.err.println("The specified position does not exist in the board.");
		}
		
		return this.getBoard().get(pos.getX()).get(pos.getY());
	}
	
	/**
	 * The winning indicator for the user.
	 * It runs after every move the user performs and prints a suitable message in case of winning. 
	 * @return true if the user wins the game, otherwise returns false.
	 */
	public boolean hallOfFame()
	{
		boolean flag = true;
		for(int i = 0 ; i < board.size() ; i++)
		{
			for( int j = 0 ; j < board.get(i).size() ; j++)
			{
				if(board.get(i).get(j) instanceof AnchorPoint)
				{
					if(!(((AnchorPoint)board.get(i).get(j)).getItem() instanceof Box))
					{
						flag = false;
						break;
					}
				}
			}
			if(!flag)
				break;
		}
		
		return flag;
	}
	
	
	/**
	 * @return the board of the current level.
	 */
	public ArrayList<ArrayList<ItemInWarehouse>> getBoard()
	{
		return board;
	}
	
	/**
	 * This method changes the current board according to the given "board".
	 * @param board - The specified board.
	 */
	public void setBoard(ArrayList<ArrayList<ItemInWarehouse>> board)
	{
		this.board = board;
	}
	 
	public int getSteps()
	{
		return steps;
	}

	public void setSteps(int steps)
	{
		this.steps = steps;
	}

	public int getSeconds()
	{
		return seconds;
	}

	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return a string representation of the current file.
	 */
	public String toString()
	{
		String desc = "";
		
		if (this.board != null)
		{
			for (ArrayList<ItemInWarehouse> x : this.board)
			{
				for (ItemInWarehouse y : x)
				{
					if (y == null)
						desc += " ";
					else
						desc += y;
				}
				desc += "\n";
			} 
		
			return desc;
		}
		
		else{
			System.out.println("board is null");
			return null;
		}
	}
}














