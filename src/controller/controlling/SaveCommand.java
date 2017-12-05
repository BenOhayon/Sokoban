package controller.controlling;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.data.Level;
import model.data.LevelSaver;
import model.data.MyObjectLevelSaver;
import model.data.MyTextLevelSaver;
import model.data.MyXMLLevelSaver;
import model.data.UnknownCommandException;
import model.general.Model;

/**
 * This class represents the command of saving the game.
 * Uses the provided decoded saving command and with the 
 * appropriate saver class it saves the game in the suitable file type.
 * 
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public class SaveCommand extends SokobanCommand
{
	private Level levelToWrite;
	private Map<String, LevelSaver> savingCommands;
	private FileOutputStream fos;
	private String fileName;
	private LevelSaver saver;
	
	
	public SaveCommand(Model m)
	{
		this.model = m;
		saver = null;
		
		initSavers();
	}
	/**
	 * Constructs a saving command which saves the given level to the given file.
	 * @param fileName - The name of the file to write to.
	 * @param level - The level to write to the file.
	 * @throws UnknownCommandException - in case of inserting an invalid command.
	 * @throws StringIndexOutOfBoundsException - in case of inserting a valid command without the expected file name.
	 */

	
	public void initSavers()
	{
		savingCommands = new HashMap<String, LevelSaver>();
		savingCommands.put("txt", new MyTextLevelSaver());
		savingCommands.put("xml", new MyXMLLevelSaver());
		savingCommands.put("obj", new MyObjectLevelSaver());
	}
	
	/**
	 * This method is responsible for decoding the file suffix
	 * and with it executes the suitable saving method.
	 */
	public void execute()
	{	
		levelToWrite = model.getLevel();
		this.fileName = params.removeFirst();
		String[] strs = fileName.split("\\\\");
		
		try
		{
			fos = new FileOutputStream(fileName);
		}
		catch(IOException e)
		{
			System.err.println("Cannot write to the specified file.");
		}
		
		String file = strs[strs.length - 1]; 
		String suffix = file.substring(file.length() - 3, file.length());
		if(suffix.equals("txt") || suffix.equals("obj") || suffix.equals("xml"))
		{
			saver = savingCommands.get(suffix);
			saver.saveLevel(levelToWrite, fos);
		}
		
		else
			System.err.println("Cannot save the file. The specified suffix is invalid.");
	}
}