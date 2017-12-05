package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;


public class MyClientHandler extends Observable implements ClientHandler
{
	
	public void handleClient(InputStream in, OutputStream out) 
	{
		try
		{
			while (true)
			{
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(in));
				PrintWriter writer = new PrintWriter(out);
				String cmd = inFromClient.readLine();
				setChanged();
				notifyObservers(cmd);
				if (cmd.equals("out"))
				{
					writer.println("bye");
					writer.flush();
				}
			}
		}
		catch (Exception e) 
		{
			
		}
		
		finally
		{
			try
			{
				in.close();
				out.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
