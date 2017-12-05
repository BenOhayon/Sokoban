package model.data;

/**
 * This class represents the character which the user controls in the board.
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public class Player extends ItemInWarehouse
{
	/**
	 * Empty constructor for serialization.
	 */
	public Player(){}
	
	/**
	 * Constructs the character in the specified position "pos".
	 * @param pos - The position in which the character will be constructed.
	 */
	public Player(Position pos)
	{
		super(pos);
	}
	
	/**
	 * Presents the character in the board ('A').
	 */
	public String toString()
	{
		return "A";
	}
}
