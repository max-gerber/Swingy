package Swingy.views;

import Swingy.controllers.Game;
import java.util.Scanner;

public class Dungeon {
	public static void displayDungeon(Game game, Scanner in) {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("****************************************************");
		System.out.println("You are here:");
		for (int y = 0; y < game.getMapSize(); y++) {
			for (int x = 0; x < game.getMapSize(); x++) {
				if (y == game.getYPosition() && x == game.getXPosition()){
					System.out.print("X");
				} else {
					System.out.print(".");
				}
			}
			System.out.print("\n");
		}
		System.out.println("Type UP DOWN LEFT or RIGHT to move in those directions or EXIT to leave the dungeon");
	}
}