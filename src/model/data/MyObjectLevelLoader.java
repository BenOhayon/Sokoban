package model.data;

import java.io.*;

/**
 * This class is capable of loading a level from a binary file.
 * 
 * @implements {@linkplain LevelLoader}
 * @author Sapir Shloush & Ben Ohayon
 */
public class MyObjectLevelLoader implements LevelLoader
{
	private ObjectInputStream ois;
	
	/**
	 * Performs the actual loading of the file with the given InputStream.
	 */
	public Level loadLevel(InputStream is)
	{
		Level newLevel = null;
		try
		{
			ois = new ObjectInputStream(is);
			newLevel = (Level)ois.readObject();
			ois.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return newLevel;
	}
}
