package model.data;

import java.io.*;

/**
 * This class represents a general item in the board.
 * An abstract class that implements the Serializable interface
 * to enable the class to be serialized to a file and be deserialized from a file.
 * 
 * @implements {@linkplain Serializable}
 * @author Sapir Shloush & Ben Ohayon
 */
public abstract class ItemInWarehouse implements Serializable
{
	private Position pos;
	
	/**
	 * Empty constructor for serialization.
	 */
	public ItemInWarehouse(){}
	
	/**
	 * Constructs a general item in the specified position "pos".
	 * @param pos - The position in which the current item will be constructed.
	 */
	public ItemInWarehouse(Position pos)
	{
		this.pos = pos;
	}
	
	/**
	 * @return the instance of the position of the current item.
	 */
	public Position getPosition()
	{
		return this.pos;
	}
	
	/**
	 * Changes the instance of the position  of the current item.
	 * @param pos
	 */
	public void setPosition(Position pos)
	{
		this.pos = pos;
	}
}
