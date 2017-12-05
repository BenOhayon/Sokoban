package model.data;

/**
 * This class represents a wall in the board.
 * 
 * @extends {@linkplain ItemInWarehouse}
 * @author Sapir Shloush & Ben Ohayon
 */
public class Wall extends ItemInWarehouse
{
	/**
	 * Empty constructor for serialization.
	 */
	public Wall(){}
	
	/**
	 * Constructs the current wall according to the given position instance "pos".
	 * @param pos
	 */
	public Wall(Position pos)
	{
		super(pos);
	}
	
	/**
	 * Presents a wall in the board ('#').
	 */
	public String toString()
	{
		return "#";
	}
}