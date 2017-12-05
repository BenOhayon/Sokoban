package model.data;

import java.io.*;

/**
 * This interface represents every entity which capable of saving a level.
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public interface LevelSaver
{
	public void saveLevel(Level lvl, OutputStream os);
}
