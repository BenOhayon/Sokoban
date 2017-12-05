package model.general;

import javafx.beans.property.StringProperty;
import model.data.Level;

public interface Model
{
	Level getLevel();
	void setLevel(Level returnedLevel);
}
