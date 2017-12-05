package model.data;

import java.beans.XMLEncoder;
import java.io.*;

/**
 * This class is capable of saving a level to an xml file.
 * 
 * @implements {@linkplain LevelLoader}
 * @author Sapir Shloush & Ben Ohayon
 */
public class MyXMLLevelSaver implements LevelSaver
{
	private XMLEncoder xmlEncoder;
	
	/**
	 * Performs the actual saving to the file with the given OutputStream and the level to save.
	 */
	public void saveLevel(Level lvl, OutputStream os)
	{
		try
		{
			xmlEncoder = new XMLEncoder(new BufferedOutputStream(os));
			xmlEncoder.writeObject(lvl);
			xmlEncoder.close();
			os.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
