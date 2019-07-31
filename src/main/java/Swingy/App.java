package Swingy;

import Swingy.controllers.*;

public class App {

	public static void main(String[] args) {
		if (args.length != 0){
			switch (args[0].toUpperCase()) {
				case "GUI":
					new GuiGame();
					break;
				case "CONSOLE":
				   new ConsoleGame();
					break;
			}
		}
		System.out.println("use gui or console as an argument");
	}
}
