package controller.controlling;

import java.util.LinkedList;

import model.general.Model;
import view.general.View;

public abstract class SokobanCommand implements Command
{
	protected LinkedList<String> params;
	protected Model model;
	protected View view;
	
	public void setParams(LinkedList<String> params)
	{
		this.params = params;
	}
}
