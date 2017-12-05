package view.general;

import db.PersPlayer;
import db.QueryResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LeaderboardsController
{
	private TableView<PersPlayer> table;
	
	private TableColumn<PersPlayer, String> username;
	
	private TableColumn<PersPlayer, String> levelName;
	
	private TableColumn<PersPlayer, Integer> numOfSteps;
	
	private TableColumn<PersPlayer, Integer> numOfSeconds;
	
	private ObservableList<PersPlayer> players;
	
	private QueryResultSet qrs;
	
	
	public LeaderboardsController()
	{
		qrs = new QueryResultSet();
	}
	
	public TableView<PersPlayer> loadEntriesBySeconds(int seconds)
	{
		qrs.getEntriesBySeconds(seconds);
		initializeTableElements();
		initializeObservableList();
		
		return table;
	}
	
	public TableView<PersPlayer> loadEntriesBySteps(int steps)
	{
		qrs.getEntriesBySteps(steps);
		initializeTableElements();
		initializeObservableList();
		
		return table;
	}
	
	public TableView<PersPlayer> loadEntriesByUsername(String username)
	{
		qrs.getEntriesByUsername(username);
		initializeTableElements();
		initializeObservableList();
		
		return table;
	}
	
	public TableView<PersPlayer> loadEntriesByLevel(String level)
	{
		qrs.getEntriesByLevel(level);
		initializeTableElements();
		initializeObservableList();
		
		return table;
	}
	
	public TableView<PersPlayer> loadAllEntries()
	{
		qrs.getAllEntries();
		initializeTableElements();
		initializeObservableList();
		
		return table;
	}
	
	private void initializeObservableList()
	{
		players = FXCollections.observableArrayList();

		for(int i = 0 ; i < qrs.getResultSet().size() ; i++)
		{
			players.add(qrs.getResultSet().get(i));
		}

		table.setItems(players);
	}
	
	@SuppressWarnings("unchecked")
	private void initializeTableElements()
	{
		table = new TableView<PersPlayer>();
		table.setEditable(true);
		
		username = new TableColumn<>("Username");
		username.setCellValueFactory(new PropertyValueFactory<PersPlayer, String>("username"));
		
		levelName = new TableColumn<>("Level");
		levelName.setCellValueFactory(new PropertyValueFactory<PersPlayer, String>("levelName"));
		
		numOfSteps = new TableColumn<>("Steps");
		numOfSteps.setCellValueFactory(new PropertyValueFactory<PersPlayer, Integer>("steps"));
		
		numOfSeconds = new TableColumn<>("Elapsed Time");
		numOfSeconds.setCellValueFactory(new PropertyValueFactory<PersPlayer, Integer>("seconds"));
		
		table.getColumns().addAll(username, levelName, numOfSteps, numOfSeconds);
	}
}
