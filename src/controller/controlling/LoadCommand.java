package controller.controlling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import model.data.Level;
import model.data.LevelLoader;
import model.data.MyObjectLevelLoader;
import model.data.MyTextLevelLoader;
import model.data.MyXMLLevelLoader;
import model.data.UnknownCommandException;
import model.general.Model;
import view.general.View;

/**
 * This class represents the command of loading the game.
 * Uses the provided decoded loading command from the User(CLI)
 * and decodes the type of file from which the user wishes
 * to load the file.
 * 
 * 
 * @implements {@linkplain Command}
 * @author Sapir Shloush & Ben Ohayon
 */
public class LoadCommand extends SokobanCommand
{
	private Level returnedLevel;
	private LevelLoader loader;
	private String fileName;
	private Map<String, LevelLoader> commandDecoder;
	private FileInputStream fis;
	
	/**
	 * Constructs a loading command with the requested file to load from.
	 * 
	 * @param fileName - The specified file's name to load from.
	 * @throws FileNotFoundException - in case of not finding the requested file.
	 * @throws UnknownCommandException - in case of inserting a load command with no following file name.
	 */
	
	public LoadCommand(Model m, View v)
	{
		this.view = v;
		this.model = m;
		returnedLevel = null;
		loader = null;
		
		initLoaders();
	}
	
	public void initLoaders()
	{
		commandDecoder = new HashMap<String, LevelLoader>();
		commandDecoder.put("txt", new MyTextLevelLoader());
		commandDecoder.put("xml", new MyXMLLevelLoader());
		commandDecoder.put("obj", new MyObjectLevelLoader());
	}
	
	/**
	 * @return the loaded level from the suitable loader.
	 */
	public Level getReturnedLevel()
	{
		return returnedLevel;
	}
	
	/**
	 * This method executes the command by calling the suitable loader.
	 */
	public void execute()
	{
		fileName = params.removeFirst();
		String[] strs = fileName.split("\\\\");
		
		try
		{
			fis = new FileInputStream(fileName);
		}
		catch(NumberFormatException nfe)
		{
			System.out.println("Problem with passing the file path.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Problem with openning the file.");
		}
		
		String file = strs[strs.length - 1];
		String suffix = file.substring(file.length() - 3, file.length());
		if(suffix.equals("txt") || suffix.equals("obj") || suffix.equals("xml"))
		{
			loader = commandDecoder.get(suffix);
			returnedLevel = loader.loadLevel(fis);
			returnedLevel.setName(file.replaceFirst("."+suffix, ""));
			model.setLevel(returnedLevel);
			view.viewLevel(returnedLevel);
		}
		
		else
		{
			System.err.println("Cannot load the file. The specified suffix is invalid.");
		}
	}
}