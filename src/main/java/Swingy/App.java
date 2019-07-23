package Swingy;

import Swingy.controllers.*;

public class App {

	public static void main(String[] args) {
		 switch (args[0].toUpperCase()) {
		 	case "GUI":
		 		new GuiGame();
		 		break;
		 	case "CONSOLE":
			default:
				new ConsoleGame();
			 	break;
		 }
	}
}
