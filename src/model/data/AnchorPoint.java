package model.data;

/**
 * This class represents an anchor point to a box in the board.
 * Provides information about what item is currently placed on it.
 * 
 * @extends {@linkplain ItemInWarehouse}
 * @author Sapir Shloush & Ben Ohayon
 */
public class AnchorPoint extends ItemInWarehouse
{
	private ItemInWarehouse item;

	/**
	 * Empty constuctor for serialization.
	 */
	public AnchorPoint(){}

	/**
	 * Constructs an anchor point in the specified position "pos".
	 * @param pos - The position in which the current anchor point will be constructed.
	 */
	public AnchorPoint(Position pos)
	{
		super(pos);
		item = null;
	}
	
	/**
	 * @return the item which is placed on the current anchor point.
	 */
	public ItemInWarehouse getItem()
	{
		return item;
	}

	/**
	 * Changes the item which is currently placed on the current anchor point
	 * to another item.
	 * @param item - The new item which is about to be placed on the current anchor point.
	 */
	public void setItem(ItemInWarehouse item)
	{
		this.item = item;
	}
	
	/**
	 * Presents the current anchor point according to the item which is placed on it:
	 *  o - in case that nothing is placed on it, @, A - in case that a box or a character is placed on it.
	 */
	public String toString()
	{
		if(item == null)
			return "o";
		
		else if(item instanceof Player)
			return "A";
		
		return "@";
	}
}
