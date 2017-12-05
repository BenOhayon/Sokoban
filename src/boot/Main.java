package boot;

import java.io.File;

import controller.general.SokobanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.general.MyModel;
import view.general.MainWindowController;


public class Main extends Application 
{
	public void start(Stage primaryStage) 
	{
		try
		{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/general/MainWindow.fxml"));
			BorderPane root = (BorderPane)fxmlLoader.load();
			File f = new File("./Resources/sokoban_icon.jpg");
			Image image = new Image(f.toURI().toString());
			primaryStage.getIcons().add(image);
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("/view/general/application.css").toExternalForm());
			
			MainWindowController view = fxmlLoader.getController();
			view.initialize(null, null);
			MyModel model = new MyModel();
			SokobanController controller = new SokobanController(view, model);
			controller.start();
			
			view.addObserver(controller);
			model.addObserver(controller);
			
			view.startMusic();
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Sokoban");
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
			    view.exit();
			});
			primaryStage.show();


		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
