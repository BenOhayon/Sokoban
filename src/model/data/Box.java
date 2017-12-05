package model.data;

/**
 * This class represents a box in the board.
 * 
 * @extends {@linkplain ItemInWarehouse}
 * @author Sapir Shloush & Ben Ohayon
 */
public class Box extends ItemInWarehouse
{
	/**
	 * Empty constructor for serialization.
	 */
	public Box(){}
	
	/**
	 * Constructs a box in the specified position "pos".
	 * @param pos - The position in which the box will be constructed.
	 */
	public Box(Position pos)
	{
		super(pos);
	}
	
	/**
	 * Presents the current box in the board ('@').
	 */
	public String toString()
	{
		return "@";
	}
}
