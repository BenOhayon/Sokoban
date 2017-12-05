package model.data;

import java.io.*;

/**
 * This class is capable of saving a level to a textual file.
 * 
 * @implements {@linkplain LevelLoader}
 * @author Sapir Shloush & Ben Ohayon
 */
public class MyTextLevelSaver implements LevelSaver
{
	private BufferedWriter bw;
	
	/**
	 * Performs the actual saving to the file with the given OutputStream and the level to save.
	 */
	public void saveLevel(Level lvl, OutputStream os)
	{
		String line = "";
		bw = new BufferedWriter(new OutputStreamWriter(os));
		
		for(int i = 0; i < lvl.getBoard().size() ; i++)
		{
			for(int j = 0 ; j < lvl.getBoard().get(i).size() ; j++)
			{
				if(lvl.getBoard().get(i).get(j) instanceof Wall)
					line += '#';
				
				else if(lvl.getBoard().get(i).get(j) instanceof Player)
					line += 'A';
				
				else if(lvl.getBoard().get(i).get(j) instanceof Box)
					line += '@';
				
				else if(lvl.getBoard().get(i).get(j) instanceof AnchorPoint)
					line += 'o';
				
				else
					line += ' ';
			}
			
			try
			{
				bw.write(line, 0, line.length());
				bw.newLine();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			line = "";
		}
		
		try
		{
			bw.close();
		} 
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
