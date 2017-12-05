package view.general;


import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.data.Level;

public class MainWindowController extends Observable implements Initializable, View
{
	@FXML
	private LevelDisplayer ld;
	
	@FXML
	private Text levelTime;
	
	@FXML
	private Text stepsTotal;
	private int stepsC;
	
	private StringProperty Counter, StepsCounter;
	private Timer t;
	private int count;
	
	private MediaPlayer mediaplayer;
	
	
	public MainWindowController()
	{
		Counter = new SimpleStringProperty();
	}
	
	public void startMusic()
	{
		mediaplayer = new MediaPlayer(new Media(new File("./Resources/inGame-music.mp3").toURI().toString()));
		mediaplayer.play();
	}

	@FXML
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		ld.addEventFilter(MouseEvent.ANY, (e) -> ld.requestFocus());
		ld.setOnKeyPressed(event -> {
			String moveCmd;
			if(event.getCode() == KeyCode.UP)
			{
				moveCmd = "move up";
				setChanged();
				notifyObservers(moveCmd);
				ld.drawCharacter("up");
			}

			if(event.getCode() == KeyCode.DOWN)
			{
				moveCmd = "move down";
				setChanged();
				notifyObservers(moveCmd);
				ld.drawCharacter("down");
			}

			if(event.getCode() == KeyCode.LEFT)
			{
				moveCmd = "move left";
				setChanged();
				notifyObservers(moveCmd);
				ld.drawCharacter("left");
			}

			if(event.getCode() == KeyCode.RIGHT)
			{
				moveCmd = "move right";
				setChanged();
				notifyObservers(moveCmd);
				ld.drawCharacter("right");
			}

			StepsCounter.set("" + (++stepsC));
		});
	}
	
	public void viewLevel(Level lvl)
	{	
		ld.setLevel(lvl);
		ld.draw();
	}

	@FXML
	public void openFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("open level file");
		fc.setInitialDirectory(new File("./"));
		FileChooser.ExtensionFilter ef1 = new FileChooser.ExtensionFilter("All Files", "*.*");
		FileChooser.ExtensionFilter ef2 = new FileChooser.ExtensionFilter("Text Files (.txt)", "*.txt");
		FileChooser.ExtensionFilter ef3 = new FileChooser.ExtensionFilter("XML Files (.xml)", "*.xml");
		FileChooser.ExtensionFilter ef4 = new FileChooser.ExtensionFilter("Binary Files (.obj)", "*.obj");
		fc.getExtensionFilters().add(ef1);
		fc.getExtensionFilters().add(ef2);
		fc.getExtensionFilters().add(ef3);
		fc.getExtensionFilters().add(ef4);
		fc.setSelectedExtensionFilter(ef1);
		File choosedFile = fc.showOpenDialog(null);
		if(choosedFile != null)
		{
			
			String cmd = "load " + choosedFile.getAbsolutePath();
			setChanged();
			notifyObservers(cmd);
			
			Counter = new SimpleStringProperty();

            if(t != null)
                t.cancel();

			t = new Timer();
			t.scheduleAtFixedRate(new TimerTask() {

				public void run() {
					Counter.set(""+(++count));
				}
			}, 0, 1000);
			count = 0;
			levelTime.textProperty().bind(Counter);
			
			StepsCounter = new SimpleStringProperty("0");
			stepsTotal.textProperty().bind(StepsCounter);
			stepsC = 0;
		}
	}

	@FXML
	public void startServer()
	{
		setChanged();
		notifyObservers("start Server");
	}

	@FXML
	public void stopServer()
	{
		setChanged();
		notifyObservers("stop Server");
	}

	@FXML
	public void stopMusic()
	{
		mediaplayer.stop();
	}

	@FXML
	public void playPauseMusic()
	{
		if(mediaplayer.getStatus().toString().equals("PLAYING"))
			mediaplayer.pause();
		if(mediaplayer.getStatus().toString().equals("PAUSED") || mediaplayer.getStatus().toString().equals("STOPPED"))
			mediaplayer.play();
	}

	@FXML
	public void chooseMusicFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("open level file");
		fc.setInitialDirectory(new File("./Resources"));
		FileChooser.ExtensionFilter ef1 = new FileChooser.ExtensionFilter("mp3 Files (.mp3)", "*.mp3");
		fc.getExtensionFilters().add(ef1);
		fc.setSelectedExtensionFilter(ef1);
		File choosedMusicFile = fc.showOpenDialog(null);
		if(choosedMusicFile != null)
		{
			mediaplayer.stop();
			mediaplayer = new MediaPlayer(new Media(choosedMusicFile.toURI().toString()));
			mediaplayer.play();
		}
	}


	public void about()
	{
		display("About Sokoban", "Sokoban game version 1.0\n\nCreated by Sapir Shloush and Ben Ohayon.", messageType.Information);
	}

	@FXML
	public void saveFile()
	{
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter ef1 = new FileChooser.ExtensionFilter("All Files", "*.*");
		FileChooser.ExtensionFilter ef2 = new FileChooser.ExtensionFilter("Text Files (.txt)", "*.txt");
		FileChooser.ExtensionFilter ef3 = new FileChooser.ExtensionFilter("XML Files (.xml)", "*.xml");
		FileChooser.ExtensionFilter ef4 = new FileChooser.ExtensionFilter("Binary Files (.obj)", "*.obj");
		fc.getExtensionFilters().add(ef1);
		fc.getExtensionFilters().add(ef2);
		fc.getExtensionFilters().add(ef3);
		fc.getExtensionFilters().add(ef4);
		fc.setSelectedExtensionFilter(ef1);
		fc.setTitle("save level file");
		fc.setInitialDirectory(new File("./"));
		File fileToSave = fc.showSaveDialog(null);
		if(fileToSave != null)
		{
			String cmd = "save " + fileToSave.getAbsolutePath();
			setChanged();
			notifyObservers(cmd);
		}
	}

	public void win()
	{
		mediaplayer.stop();
		if(mediaplayer.getStatus().toString().equals("STOPPED"))
		{
			mediaplayer = new MediaPlayer(new Media(new File("./Resources/inGame2-music.mp3").toURI().toString()));
			mediaplayer.play();
		}
		t.cancel();
		t.purge();
		ld.drawWinImage();
		
		setChanged();
		notifyObservers("secondsUpdate,"+count);
		
		try
		{
			Thread.sleep(1000);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Platform.runLater(() -> {
			Alert message = new Alert(AlertType.CONFIRMATION, null, ButtonType.YES, ButtonType.NO);
			message.setTitle("World Record");
			message.setHeaderText(null);
			message.setContentText("Do you want to enter the World Record?");
			Optional<ButtonType> result = message.showAndWait();

			if(result.get() == ButtonType.YES)
			{
				Pane root = new Pane();
				Scene scene = new Scene(root, 250, 250);
				Stage stage = new Stage();

				TextField userName = new TextField();
				userName.setPromptText("Enter your username here");
				Button ok = new Button("OK");

				VBox vbButtons = new VBox();
				vbButtons.setSpacing(60);
				vbButtons.setPadding(new Insets(0, 20, 10, 20));
				vbButtons.getChildren().addAll(userName, ok);

				ok.setOnAction(event -> {
					stage.close();
					setChanged();
					notifyObservers("addToDatabase" + "," + userName.getText());
				});

				root.getChildren().add(vbButtons);
				stage.setScene(scene);
				stage.setTitle("Entering the World Records");
				stage.show();
			}
		});
	}
	
	public void ShowLeaderboards()
	{
		setChanged();
		notifyObservers("show leaderboards");
	}

	public void exit()
	{
		Alert message = new Alert(AlertType.CONFIRMATION, null, ButtonType.YES, ButtonType.NO);
		message.setTitle("Exit Sokoban");
		message.setHeaderText(null);
		message.setContentText("Are you sure you want to exit?");
		Optional<ButtonType> result = message.showAndWait();
		
		if (result.get() == ButtonType.YES) 
		{
		    System.exit(0);
		}
	}
	
	public void start()
	{
		
	}

	public void display(String title, String content, messageType mt)
	{
		Alert message = null;
		switch(mt)
		{
			case Information:
				message = new Alert(AlertType.INFORMATION);
				break;

			case Error:
				message = new Alert(AlertType.INFORMATION);
				break;
		}

		message.setTitle(title);
		message.setHeaderText(null);
		message.setContentText(content);
		message.showAndWait();
	}
}
