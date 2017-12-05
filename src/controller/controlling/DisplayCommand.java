package controller.controlling;

import java.util.LinkedList;

import model.data.Level;
import model.general.Model;
import view.general.View;

/**
 * This class represnts the command of displaying the current game state to the user.
 * With the given level to dislay, this command prints it on the screen.
 * 
 * @implements {@linkplain Command}
 * @author Sapir Shloush & Ben Ohayon
 */
public class DisplayCommand extends SokobanCommand
{
	//private Level levelToDisplay;
	
	/**
	 * Constructs a display command with the level to display, "lvl".
	 * @param lvl - The requested level to display on the screen.
	 */
	
	public DisplayCommand(Model m, View v)
	{
		this.model = m;
		this.view = v;
	}
	
	/**
	 * This method displays the requested level to the screen.
	 */
	public void execute()
	{
		Level level = model.getLevel();
		view.viewLevel(level);
	}
}

