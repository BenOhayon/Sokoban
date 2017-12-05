package view.general;

import model.data.Level;

public interface View
{
	enum messageType {Error, Information}

	void start();
	void display(String title, String content, messageType mt);
	void win();
	void viewLevel(Level lvl);
	void exit();
}
