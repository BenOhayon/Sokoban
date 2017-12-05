package model.data;

import java.io.*;

/**
 * This class is capable of saving a level to a binary file.
 * 
 * @implements {@linkplain LevelLoader}
 * @author Sapir Shloush & Ben Ohayon
 */
public class MyObjectLevelSaver implements LevelSaver
{
	private ObjectOutputStream oos;
	
	/**
	 * Performs the actual saving to the file with the given OutputStream and the level to save.
	 */
	public void saveLevel(Level lvl, OutputStream os)
	{
		try
		{
			oos = new ObjectOutputStream(os);
			oos.writeObject(lvl);
			oos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
