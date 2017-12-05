package model.data;

import java.io.*;
import java.beans.XMLDecoder;

/**
 * This class is capable of loading a level from an xml file.
 * 
 * @implements {@linkplain LevelLoader}
 * @author Sapir Shloush & Ben Ohayon
 */
public class MyXMLLevelLoader implements LevelLoader
{
	private XMLDecoder xmlDecoder;
	
	/**
	 * Performs the actual loading of the file with the given InputStream.
	 */
	public Level loadLevel(InputStream is)
	{
		Level newLevel = null;
		xmlDecoder = new XMLDecoder(new BufferedInputStream(is));
		newLevel = (Level)xmlDecoder.readObject();
		xmlDecoder.close();
		return newLevel;
	}
}
