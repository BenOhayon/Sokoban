package model.data;

import java.io.*;

/**
 * A representation of a position of an item in the board. 
 * Consists of two coordinates: x, y.
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public class Position implements Serializable
{
	private int x, y;
	
	/**
	 * Empty constructor for serialization.
	 */
	public Position(){}
	
	/**
	 * Constructs an instance of a position according to the given position instance "p".
	 * @param p - The instance of a position which the current position constructs according to.
	 */
	public Position(Position p)
	{
		this.x = p.x;
		this.y = p.y;
	}
	
	/**
	 * Constructs the current position with the given two coordinates.
	 * @param x - x coordinate.
	 * @param y - y coordinate.
	 */
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x coordinate of the current position.
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Changes the x coordiante of the current position.
	 * @param x - The new x coordinate to be assigned instead of the current position x coordinate.
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * @return the y coordinate of the current position.
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Changes the y coordiante of the current position.
	 * @param y - The new y coordinate to be assigned instead of the current position y coordinate.
	 */
	public void setY(int y)
	{
		this.y = y;
	}
}
