package controller.controlling;

import controller.general.Controller;
import view.general.View;

/**
 * This class represents the command which exits the program 
 * and stops the routine of command input from the user.
 * 
 * @implements {@linkplain Command}
 * @author Sapir Shloush & Ben Ohayon
 */
public class ExitCommand extends SokobanCommand
{
	
	
	public ExitCommand(View v)
	{
		this.view = v;
	}
	/**
	 * This method is responsible for exiting the main program.
	 */
	public void execute()
	{
		view.exit();
	}
}
