package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "World_Records")
public class PersPlayer
{
	@Id
	@Column(name = "Player_Username")
	private String username;
	
	@Column(name = "Level_Name")
	private String levelName;
	
	@Column(name = "Player_steps")
	private int steps;
	
	@Column(name = "Player_seconds")
	private int seconds;

	public PersPlayer() {}
	
	public PersPlayer(String username, String levelName, int steps, int seconds) 
	{
		setUsername(username);
		setLevelName(levelName);
		setSteps(steps);
		setSeconds(seconds);
	}
	
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public int getSteps()
	{
		return steps;
	}

	public void setSteps(int steps)
	{
		this.steps = steps;
	}

	public int getSeconds()
	{
		return seconds;
	}

	public void setSeconds(int seconds)
	{
		this.seconds = seconds;
	}

	public String getLevelName()
	{
		return levelName;
	}

	public void setLevelName(String levelName)
	{
		this.levelName = levelName;
	}
	
	@Override
	public String toString()
	{
		return "PersPlayer [username=" + username + ", levelName=" + levelName + ", steps=" + steps + ", seconds="
				+ seconds + "]";
	}
	
}
