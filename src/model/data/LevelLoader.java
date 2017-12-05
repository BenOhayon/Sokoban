package model.data;

import java.io.InputStream;

/**
 * This interface represents every entity which capable of loading a level.
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public interface LevelLoader
{
	public Level loadLevel(InputStream is);
}

/*
 * a) The separation was accomplished by creating Level class which 
 *    stores the information about the level. The data management
 *    is carried out by the scheme of LevelLoader interface and the 
 *    MyTextLevelLoader class (which implements it).
 *    
 * b) This separation enables the Open/Close principle because we could
 *    change the way we load levels (in our case MyTextLevelLoader)
 *    without changing the Level class.
 *    
 * c) LevelLoader interface is dictating the behavior of level loading
 *    to all kinds of Level loaders.
 *    
 * d) We chose InputStream to be able to get any type of input.
 * 
 */
