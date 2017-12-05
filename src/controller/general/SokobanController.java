package controller.general;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import controller.controlling.Command;
import controller.controlling.DisplayCommand;
import controller.controlling.ExitCommand;
import controller.controlling.LoadCommand;
import controller.controlling.MoveCommand;
import controller.controlling.SaveCommand;
import controller.server.MyClientHandler;
import controller.server.MyServer;
import db.PersPlayer;
import db.QueryResultSet;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.general.Model;
import view.general.LeaderboardsController;
import view.general.View;

public class SokobanController implements Controller
{
	private View view;
	private Model model;
	private MyServer server;
	private BlockingQueue<Command> queue;
	private Map<String, Command> commands;
	private boolean stop = false;
	
	private LeaderboardsController lbc;
	private QueryResultSet qrs;
	private boolean isExist = false;

	public SokobanController(View v, Model m)
	{
		this.view = v;
		this.model = m;
		queue = new ArrayBlockingQueue<>(50);
		initCommands();
		
		qrs = new QueryResultSet();
		lbc = new LeaderboardsController();
	}
	
	private void initCommands()
	{
		commands = new HashMap<>();
		commands.put("load", new LoadCommand(model, view));
		commands.put("save", new SaveCommand(model));
		commands.put("move", new MoveCommand(model, view));
		commands.put("display", new DisplayCommand(model, view));
		commands.put("exit", new ExitCommand(view));
	}
	
	public void insertCommand(Command cmd) 
	{
		try {
			queue.put(cmd);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	public void update(Observable o, Object obj)
	{	
		String objStr = (String)obj;
		
		if(objStr.startsWith("addToDatabase"))
		{
			String[] strings = objStr.split(",");
			
			qrs.getAllEntries();
				
			for(int i = 0 ; i < qrs.getResultSet().size() ; i++)
			{
				if(qrs.getResultSet().get(i).getUsername().equals(strings[1]) && qrs.getResultSet().get(i).getLevelName().equals(model.getLevel().getName()))
				{
					isExist = true;
					
					if(qrs.getResultSet().get(i).getSteps() > model.getLevel().getSteps())
					{
						qrs.updateSteps(strings[1], model.getLevel().getSteps());
					}
					
					if(qrs.getResultSet().get(i).getSeconds() > model.getLevel().getSeconds())
					{
						qrs.updateSeconds(strings[1], model.getLevel().getSeconds());
					}
				}
			}
			
			
			if (!isExist)
			{
				PersPlayer player = new PersPlayer(strings[1], model.getLevel().getName(), model.getLevel().getSteps(),
						model.getLevel().getSeconds());
				qrs.addNewEntry(player);
			}
		}
		
		else if(objStr.equals("show leaderboards"))
		{
			try
			{
				Stage primaryStage = new Stage();
				StackPane pane = new StackPane();
				
				TableView<PersPlayer> table;
				if (model.getLevel().getBoard() == null)
					table = lbc.loadAllEntries();
				
				else
					table = lbc.loadEntriesByLevel(model.getLevel().getName());
				
				VBox vbox = new VBox(20);
				
				TextField searchKeyWord = new TextField();
				searchKeyWord.setPromptText("search value");
				searchKeyWord.setPrefWidth(100);
				searchKeyWord.setEditable(false);
				
				ChoiceBox<String> ddSearch = new ChoiceBox<>();
				ddSearch.getItems().addAll("All", "Username", "Level Name", "Steps", "Seconds");
				ddSearch.setPrefWidth(100);
				ddSearch.setValue("All");
				ddSearch.getSelectionModel().selectedItemProperty().addListener((v, oldItem, newItem) -> {
					
					if(newItem.equals("All"))
						searchKeyWord.setEditable(false);
					else
						searchKeyWord.setEditable(true);
				});
				
				Button searchButton = new Button("Search");
				searchButton.setPrefWidth(100);
				searchButton.setOnAction(e -> {
					
					String choice = ddSearch.getValue();
					String inputText = searchKeyWord.getText();
					
					switch(choice)
					{
					case "All":
						TableView<PersPlayer> t1 = lbc.loadAllEntries();
						vbox.getChildren().set(0, t1);
						break;
						
					case "Username":
						
						if(isValid("", inputText))
						{
							TableView<PersPlayer> t2 = lbc.loadEntriesByUsername(inputText);
							vbox.getChildren().set(0, t2);
						}
						
						else displayErrorMessage("Type mismatch has occured.");
						
						break;
						
					case "Level Name":
						if(isValid("", inputText))
						{
							TableView<PersPlayer> t2 = lbc.loadEntriesByLevel(inputText);
							vbox.getChildren().set(0, t2);
						}
						
						else displayErrorMessage("Type mismatch has occured.");
						break;
						
					case "Steps":
						if(isValid(0, inputText))
						{
							TableView<PersPlayer> t2 = lbc.loadEntriesBySteps(Integer.parseInt(inputText));
							vbox.getChildren().set(0, t2);
						}
						
						else displayErrorMessage("Type mismatch has occured.");
						break;
						
					case "Seconds":
						if(isValid(0, inputText))
						{
							TableView<PersPlayer> t2 = lbc.loadEntriesBySeconds(Integer.parseInt(inputText));
							vbox.getChildren().set(0, t2);
						}
						
						else displayErrorMessage("Type mismatch has occured.");
						break;
					}
					
					searchKeyWord.clear();
					
				});
				
				HBox hbox = new HBox(ddSearch, searchKeyWord, searchButton);
				hbox.setSpacing(20);
				hbox.setPadding(new Insets(0,0,0,65));
				
				vbox.getChildren().addAll(table, hbox);
				pane.getChildren().addAll(vbox);
				Scene scene = new Scene(pane, 500, 450);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Leaderboards");
				primaryStage.setResizable(false);
				
				primaryStage.show();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		else if(objStr.startsWith("secondsUpdate"))
		{
			String[] strings = objStr.split(",");
			model.getLevel().setSeconds(Integer.parseInt(strings[1]));
		}
		
		else if(objStr.equals("stop Server"))
		{
			stopServer();
			Alert message = new Alert(AlertType.INFORMATION);
			message.setTitle("Closing server");
			message.setHeaderText(null);
			message.setContentText("The server was closed.");
			message.showAndWait();
		}
		
		else if(objStr.equals("start Server"))
		{
			if(stop) stop = false;
			
			StackPane root = new StackPane();
			Scene scene = new Scene(root, 250, 125);
			Stage stage = new Stage();
			stage.setTitle("Start Server");
			
			TextField port = new TextField("port");
			Button start = new Button("Start");

			VBox vbButtons = new VBox();
			HBox hbox = new HBox(start);
			hbox.setPadding(new Insets(0,0,0,75));
			vbButtons.setSpacing(40);
			vbButtons.setPadding(new Insets(15, 20, 10, 20));
			vbButtons.getChildren().addAll(port, hbox);
			
			start.setOnAction(event -> {
				if(!port.getText().equals(""))
				{
					startServer(Integer.parseInt(port.getText()));
					Alert message = new Alert(AlertType.INFORMATION);
					message.setTitle("Starting server");
					message.setHeaderText(null);
					message.setContentText("The server is up and running...");
					message.showAndWait();
					stage.close();
				}
				else
				{
					Alert message = new Alert(AlertType.INFORMATION);
					message.setTitle("Starting server");
					message.setHeaderText(null);
					message.setContentText("The server is up and running...");
					message.showAndWait();
				}
			});
			
			root.getChildren().add(vbButtons);
			stage.setScene(scene);
			stage.show();
		}
		
		else
		{
			String[] seperate = objStr.split(" ", 2);

			LinkedList<String> params = new LinkedList<>();
			Collections.addAll(params, seperate);

			String cmd = params.removeFirst();
			Command c = commands.get(cmd);
			if (c != null)
			{
				c.setParams(params);
				insertCommand(c);
			} 
		}
	}
	
	private void displayErrorMessage(String message)
	{
		Alert a = new Alert(AlertType.ERROR);
		a.setTitle("Error");
		a.setHeaderText(null);
		a.setContentText(message);
		a.showAndWait();
	}
	
	private boolean isValid(Object type, String inputText)
	{
		try
		{
			Integer.parseInt(inputText);

			return !(type instanceof String);
		}
		catch (Exception e1)
		{
			return (type instanceof String);
		}
	}

	public void start() {
		Thread thread = new Thread(() -> {
			while (!stop)
			{
				try {
					Command cmd = queue.take();
					if (cmd != null)
						cmd.execute();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	public void stop()
	{
		stop = true;
	}

	private void startServer(int port)
	{
		MyClientHandler ch = new MyClientHandler();
		ch.addObserver(this);
		server = new MyServer(port, ch);
		server.start();
	}

	private void stopServer()
	{
		server.stop();
	}
}
