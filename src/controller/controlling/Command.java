package controller.controlling;

import java.util.LinkedList;
import java.util.List;

import model.general.Model;
import view.general.View;

/**
 * A representation of a command behavior.
 * 
 * @author Sapir Shloush & Ben Ohayon
 */
public interface Command
{	
	void execute();
	void setParams(LinkedList<String> params);
}
