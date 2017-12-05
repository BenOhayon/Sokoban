package model.general;

import java.util.Observable;

import model.data.Level;

public class MyModel extends Observable implements Model
{
	private Level level;
	
	public MyModel()
	{
		level = new Level();
	}

	public Level getLevel()
	{
		return this.level;
	}

	public void setLevel(Level returnedLevel)
	{
		this.level = returnedLevel;
	}
}
