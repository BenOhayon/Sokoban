package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer
{
	private int port;
	private ClientHandler ch;
	private volatile boolean stop;
	
	
	public MyServer(int port,ClientHandler ch) {
		this.port = port;
		this.ch = ch;
		stop = false;
	}
	
	private void runServer() throws Exception {
		ServerSocket server = new ServerSocket(port);
		while(!stop){
			try{
				Socket aClient = server.accept();
				new Thread(new Runnable() { 
					public void run() {
						try{
							ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
							aClient.getInputStream().close();
							aClient.getOutputStream().close();
							aClient.close();
						} catch(IOException e) {}
					}
				}).start();
			}catch(SocketTimeoutException e) {}
		}
		server.close();
	}
	
	public void start(){
		new Thread(new Runnable() {
			public void run() {
				try{
					runServer();
				}
				catch(Exception e){}
			}
		}).start();
	}
	
	public void stop()
	{
		stop = true;
	}
}








