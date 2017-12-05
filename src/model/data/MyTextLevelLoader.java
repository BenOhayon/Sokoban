package model.data;

import java.io.*;
import java.util.ArrayList;


/**
 * This class is capable of loading a level from a textual file.
 * 
 * @implements {@linkplain LevelLoader}
 * @author Sapir Shloush & Ben Ohayon
 */
public class MyTextLevelLoader implements LevelLoader
{
	private BufferedReader br;
	
	/**
	 * Performs the actual loading of the file with the given InputStream.
	 */
	public Level loadLevel(InputStream is)
	{
		Level newLevel = new Level();
		ArrayList<ArrayList<ItemInWarehouse>> board = new ArrayList<ArrayList<ItemInWarehouse>>();
		br = new BufferedReader(new InputStreamReader(is));
		String line;
		int i = 0;
		
		
		try
		{
			while((line = br.readLine()) != null)
			{
				board.add(new ArrayList<ItemInWarehouse>());
				
				for(int j = 0 ; j < line.length() ; j++)
				{
					switch(line.charAt(j))
					{
					case '#':
						board.get(i).add(new Wall(new Position(i, j)));
						break;
						
					case '@':
						board.get(i).add(new Box(new Position(i, j)));
						break;
						
					case 'o':
						board.get(i).add(new AnchorPoint(new Position(i, j)));
						break;
						
					case 'A':
						board.get(i).add(new Player(new Position(i, j)));
						break;
						
					case ' ':
						board.get(i).add(null);
						break;
					}
				}
				i++;
			}
			
			newLevel.setBoard(board);
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return newLevel;
	}
}
