package view.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.data.ItemInWarehouse;
import model.data.Level;

public class LevelDisplayer extends Canvas
{
	private Level level;
	private GraphicsContext gc;

	// flags used for displaying the character's moving animation.
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;

	// Images for actual displaying the character's drawing.
	private Image characterUp1 = null, characterUp2 = null, characterDown1 = null, characterDown2 = null, characterLeft1 = null, characterLeft2 = null,
			characterRight1 = null, characterRight2 = null;

	// Used for calculating the dimensions of a cell in the board according to the board size.
	private double CELL_WIDTH;
	private double CELL_HEIGHT;

	public Level getLevel()
	{
		return this.level;
	}

	public void setLevel(Level lvl)
	{
		if (lvl != null)
			this.level = lvl;
	}

	public void draw()
	{
		double CANVAS_WIDTH = this.getWidth();
		double CANVAS_HEIGHT = this.getHeight();

		CELL_WIDTH = CANVAS_WIDTH / level.getNumberOfColumns();
		CELL_HEIGHT = CANVAS_HEIGHT / level.getNumberOfRows();

		gc = getGraphicsContext2D();
		Image wall = null, box = null, anchor = null, character = null, floor = null;

		try
		{
			wall = new Image(new FileInputStream("./Resources/wall3.png"));
			box = new Image(new FileInputStream("./Resources/box4.png"));
			anchor = new Image(new FileInputStream("./Resources/anchor4.png"));
			character = new Image(new FileInputStream("./Resources/Character4.png"));
			floor = new Image(new FileInputStream("./Resources/GroundGravel_Concrete.png"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		for (int i = 0; i < level.getBoard().size(); i++)
		{
			for (int j = 0; j < level.getBoard().get(i).size(); j++)
			{
				ItemInWarehouse iiw = level.getBoard().get(i).get(j);
				gc.clearRect(j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
				
				if(iiw == null)
				{
					gc.drawImage(floor, j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
				}
				
				else
				{
					String itemSign = iiw.toString();
					switch (itemSign)
					{
					case "#":
						gc.drawImage(wall, j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
						break;

					case "@":
						
						gc.drawImage(box, j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
						break;

					case "A":
						gc.drawImage(character, j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
						break;

					case "o":
						gc.drawImage(anchor, j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
						break;
					}
				}
			}
		}
	}

	public void drawCharacter(String direction)
	{
		characterUp1 = new Image(new File("D:/Ben/My Documents/Java/Workspace/SokobanWindow/Resources/Character8.png").toURI().toString());
		characterUp2 = new Image(new File("D:/Ben/My Documents/Java/Workspace/SokobanWindow/Resources/Character9.png").toURI().toString());
		characterDown1 = new Image(new File("D:/Ben/My Documents/Java/Workspace/SokobanWindow/Resources/Character6.png").toURI().toString());
		characterDown2 = new Image(new File("D:/Ben/My Documents/Java/Workspace/SokobanWindow/Resources/Character5.png").toURI().toString());
		characterLeft1 = new Image(new File("D:/Ben/My Documents/Java/Workspace/SokobanWindow/Resources/Character1.png").toURI().toString());
		characterLeft2 = new Image(new File("D:/Ben/My Documents/Java/Workspace/SokobanWindow/Resources/Character10.png").toURI().toString());
		characterRight1 = new Image(new File("D:/Ben/My Documents/Java/Workspace/SokobanWindow/Resources/Character2.png").toURI().toString());
		characterRight2 = new Image(new File("D:/Ben/My Documents/Java/Workspace/SokobanWindow/Resources/Character3.png").toURI().toString());

		gc.clearRect(level.getCharacterPosition().getY() * CELL_WIDTH, level.getCharacterPosition().getX() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);

		switch(direction)
		{
			case "up":
				drawCharacterUp();
				break;

			case "down":
				drawCharacterDown();
				break;

			case "left":
				drawCharacterLeft();
				break;

			case "right":
				drawCharacterRight();
				break;
		}
	}
	
	public void drawWinImage()
	{
		gc.drawImage(new Image(new File("./Resources/win-pic3.jpg").toURI().toString()), 0, 0, getWidth(), getHeight());
	}

	private void drawCharacterUp(){
		if(up) {
			gc.drawImage(characterUp1, level.getCharacterPosition().getY() * CELL_WIDTH, level.getCharacterPosition().getX() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
			up = false;
		}
		else {
			gc.drawImage(characterUp2, level.getCharacterPosition().getY() * CELL_WIDTH, level.getCharacterPosition().getX() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
			up = true;
		}
	}

	private void drawCharacterDown(){
		if(down) {
			gc.drawImage(characterDown1, level.getCharacterPosition().getY() * CELL_WIDTH, level.getCharacterPosition().getX() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
			down = false;
		}
		else {
			gc.drawImage(characterDown2, level.getCharacterPosition().getY() * CELL_WIDTH, level.getCharacterPosition().getX() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
			down = true;
		}
	}

	private void drawCharacterLeft(){
		if(left) {
			gc.drawImage(characterLeft1, level.getCharacterPosition().getY() * CELL_WIDTH, level.getCharacterPosition().getX() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
			left = false;
		}
		else {
			gc.drawImage(characterLeft2, level.getCharacterPosition().getY() * CELL_WIDTH, level.getCharacterPosition().getX() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
			left = true;
		}
	}

	private void drawCharacterRight(){
		if(right) {
			gc.drawImage(characterRight1, level.getCharacterPosition().getY() * CELL_WIDTH, level.getCharacterPosition().getX() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
			right = false;
		}
		else {
			gc.drawImage(characterRight2, level.getCharacterPosition().getY() * CELL_WIDTH, level.getCharacterPosition().getX() * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
			right = true;
		}
	}
}
