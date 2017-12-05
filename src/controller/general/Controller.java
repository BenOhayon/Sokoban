package controller.general;

import java.util.Observer;

import controller.controlling.Command;

public interface Controller extends Observer
{
	void start();
	void stop();
	void insertCommand(Command cmd);
}