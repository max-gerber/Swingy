package Swingy.views;

import Swingy.controllers.Game;
import Swingy.models.Equipment.*;
import Swingy.models.Heros.*;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HeroCreation {
	public static void creation(Game game, Scanner in) {
		START:
		while (true){
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println("Would you like to use an existing hero?");
			String choice = in.nextLine().toUpperCase();
			switch (choice) {
				case "YES":
					System.out.print("\033[H\033[2J");
					System.out.flush();
					SELECTION:
					while (true) {
						System.out.println("\nChoose a type of hero from the following:\n");
						try {
							BufferedReader reader = new BufferedReader(new FileReader("Saved.txt"));
							String line;
							int		count = 0;
							while ((line = reader.readLine()) != null){
								String hero[] = line.split(",");
								System.out.println("(" + count++ + "): " + hero[0] + " the " + hero[1]);
								System.out.println("Attack:\t\t" + hero[2]);
								System.out.println("Defence:\t" + hero[3]);
								System.out.println("Health:\t\t" + hero[4]);
								System.out.println("Weapon:\t\t" + hero[5] + "(+" + hero[6] + ")");
								System.out.println("Armour:\t\t" + hero[7] + "(+" + hero[8] + ")");
								System.out.println("Helm:\t\t" + hero[9] + "(+" + hero[10] + ")");
								System.out.println("Potions:\t" + hero[11]);
								System.out.println("Level:\t\t" + hero[12]);
								System.out.println("Experience:\t" + hero[13] + "\n");
							}
							reader.close();
						} catch (FileNotFoundException e) {
							System.out.println(e.getMessage());
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
						System.out.println("Back\n");
						String selection = in.nextLine().toUpperCase();
						if (selection.equals("BACK")){
							System.out.print("\033[H\033[2J");
							System.out.flush();
							continue START;
						} else {
							try {
								BufferedReader reader = new BufferedReader(new FileReader("Saved.txt"));
								String line = reader.readLine();
								for (int i = 1; i <= Integer.parseInt(selection); i++) {
									line = reader.readLine();
								}
								reader.close();
								String hero[] = line.split(",");
								game.setPlayer((Hero) new ExistingHero(hero[0], hero[1], Integer.parseInt(hero[2]), Integer.parseInt(hero[3]), Integer.parseInt(hero[4]), new Weapon(hero[5], Integer.parseInt(hero[6])), new Armour(hero[7], Integer.parseInt(hero[8])), new Helm(hero[9], Integer.parseInt(hero[10])), Integer.parseInt(hero[11]), Integer.parseInt(hero[12]), Integer.parseInt(hero[13])));
								game.setGameLevel(Integer.parseInt(hero[14]));
								break START;
							} catch (FileNotFoundException e) {
								System.out.println(e.getMessage());
							} catch(IOException e) {
								System.out.println(e.getMessage());
							} catch (NumberFormatException e) {
								continue SELECTION;
							}
						}
					}
				case "NO":
					System.out.print("\033[H\033[2J");
					System.out.flush();
					SELECTION:
					while (true){
						System.out.println("Enter your hero's name: \n");
						String name = in.nextLine();
						System.out.print("\033[H\033[2J");
						System.out.flush();
						System.out.println("\nChoose a type of hero from the following:\n");
						System.out.println("Knight\t\t\tOrc\t\t\tAngel\n");
						System.out.println("Attck: 50\t\tAttack: 80\t\tAttack: 60");
						System.out.println("Defence: 20\t\tDefence: 15\t\tDefence: 30");
						System.out.println("Health: 50\t\tHealth: 80\t\tHealth: 60\n");
						System.out.println("Back\n");

						String type = in.nextLine().toUpperCase();
						switch (type) {
							case "KNIGHT":
								game.setPlayer(new Knight(name));
								break SELECTION;
							case "ORC":
								game.setPlayer(new Orc(name));
								break SELECTION;
							case "ANGEL":
								game.setPlayer(new Angel(name));
								break SELECTION;
							case "BACK":
								System.out.print("\033[H\033[2J");
								System.out.flush();
								continue START;
							default:
								System.out.print("\033[H\033[2J");
								System.out.flush();
								System.out.println("\nSelect one of the available options");
								continue SELECTION;
						}
					}
					break START;
			}
		}
	}
}