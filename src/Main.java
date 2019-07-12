package src;

import src.models.Enemies.*;
import src.models.Equipment.*;
import src.models.Heros.*;
// import models.Heros.*;

import java.lang.reflect.Constructor;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Scanner	in = new Scanner(System.in);
		Random	rand = new Random();
		boolean	running = true;
		boolean	evasion;
		int		gameLevel = 1;
		int		mapSize = 9;
		int		xPosition = 4;
		int		yPosition = 4;
		Hero	player;

		START:
		while (true){
			// System.out.print("\033[H\033[2J");
			// System.out.flush();
			System.out.println("Would you like to use an existing hero?");
			String choice = in.nextLine().toUpperCase();
			System.out.println("\"" + choice + "\"");
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
								System.out.println("(" + count + "): " + hero[0] + " the " + hero[1]);
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
							System.out.println("File " + args[0] + " not found.");
						} catch (IOException e) {
							System.out.println("There was an error reading file " + args[0]);
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
								String line;
								for (int i = 0; i < Integer.parseInt(selection); i++) {
									line = reader.readLine();
								}
								String hero[] = line.split(",");
								player = new ExistingHero(hero[0], hero[1], Integer.parseInt(hero[2]), Integer.parseInt(hero[3]), Integer.parseInt(hero[4]), new Weapon(hero[5], Integer.parseInt(hero[6])), new Armour(hero[7], Integer.parseInt(hero[8])), new Helm(hero[9], Integer.parseInt(hero[10])), Integer.parseInt(hero[11]), Integer.parseInt(hero[12]), Integer.parseInt(hero[13]));
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
								player = new Knight(name);
								break SELECTION;
							case "ORC":
								player = new Orc(name);
								break SELECTION;
							case "ANGEL":
								player = new Angel(name);
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

		GAME:
		while (running){
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println("****************************************************");
			System.out.println("You are here:");
			for (int y = 0; y < mapSize; y++) {
				for (int x = 0; x < mapSize; x++) {
					if (y == yPosition && x == xPosition){
						System.out.print("X");
					} else {
						System.out.print(".");
					}
				}
				System.out.print("\n");
			}
			System.out.println("Type UP DOWN LEFT or RIGHT to move in those directions or EXIT to leave the dungeon");
			String movement = in.nextLine().toUpperCase();
			switch (movement) {
				case "UP":
					yPosition--;
					break;
				case "DOWN":
					yPosition++;
					break;
				case "LEFT":
					xPosition--;
					break;
				case "RIGHT":
					xPosition++;
					break;
				case "EXIT":
					break GAME;
				default:
					continue GAME;
			}
			if (yPosition < 0 || xPosition < 0 || yPosition == mapSize || xPosition == mapSize){
				mapSize = player.getLevel() * 5 + 5 - player.getLevel() % 2;
				yPosition = (mapSize - 1) / 2;
				xPosition = yPosition;
				gameLevel++;
			}
			if (rand.nextInt(4) == 0){
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println("****************************************************");
				Enemy enemy = new Enemy(gameLevel);
				System.out.println("#\tA " + enemy.getType() + " has appeared!\t#\n");
				evasion = true;
	
				BATTLE:
				while (enemy.getHealth() > 0 && player.getHealth() > 0){
					System.out.println("\t### " + player.getName() + " ###");
					System.out.println("Attack: " + player.getAttack());
					System.out.println("Defence: " + player.getDefence());
					System.out.println("Health: " + player.getHealth());
					System.out.println("\n");
					System.out.println("\t### " + enemy.getType() + " ###");
					System.out.println("Attack: " + enemy.getAttack());
					System.out.println("Defence: " + enemy.getDefence());
					System.out.println("Health: " + enemy.getHealth());
					System.out.println("\n\n");
					System.out.println("Attack");
					System.out.println("Drink Potion");
					if (evasion){
						System.out.println("Run");
					}
					System.out.println("Exit");
					String action = in.nextLine().toUpperCase();
					System.out.print("\033[H\033[2J");
					System.out.flush();
					System.out.println("****************************************************");
					switch (action) {
						case "ATTACK":
							player.fight(enemy);
							evasion = false;
							break;
						case "DRINK POTION":
							player.usePotion();
							break;
						case "RUN":
							if (evasion){
								if (rand.nextInt(2) == 0){
									System.out.println("You ran away in terror");
									break BATTLE;
								} else {
									System.out.println("You failed to get away...");
									evasion = false;
								}
							} else {
								System.out.println("You must fight!");
							}
							break;
						case "EXIT":
							break GAME;
						default:
							break;
					}
				}
				if (enemy.getHealth() <= 0){
					System.out.println("#\tYou have vanquished the " + enemy.getType() + "\t#");
					int experience = enemy.experienceDrop();
					System.out.println("#\tYou have earned " + experience + " points\t#");
					player.gainExperience(experience);
					if (enemy.getEquipment() != null){
						Equipment droppedEquipment = enemy.getEquipment();
						System.out.println("#\tThe " + enemy.getType() + " has dropped a " + droppedEquipment.getType() + "(+" + droppedEquipment.getBonus() + ")\t#");
						System.out.println("#\tWould you like to equip it?\t#");
						System.out.println("\n");
						System.out.println("#\tYour inventory:\t#");
						System.out.println("Weapon:\t" + player.getWeapon().getType() + "(+" + player.getWeapon().getBonus() + ")");
						System.out.println("Armour:\t" + player.getArmour().getType() + "(+" + player.getArmour().getBonus() + ")");
						System.out.println("Helm:\t" + player.getHelm().getType() + "(+" + player.getHelm().getBonus() + ")");
						String pickUp = in.nextLine().toUpperCase();
						switch (pickUp) {
							case "YES":
							case "Y":
								player.equip(droppedEquipment);
								break;
							default:
								System.out.println("#\tIt was probbably cursed anyway\t#");
								break;
						}
					}
					if (rand.nextInt(5) == 0){
						System.out.println("#\tThe " + enemy.getType() + " has dropped a potion\t#");
						System.out.println("#\tWould you like to pick it up?\t#");
						String pickUp = in.nextLine().toUpperCase();
						switch (pickUp) {
							case "YES":
							case "Y":
								player.gainPotion();
								break;
						}
					}
				} else if (player.getHealth() <= 0) {
					System.out.println("#\tAlas you have died. Your adventure ends here.\t#");
					running = false;
				}
				System.out.println("Press enter to continue");
				in.nextLine();
			}
		}
		try {
			File file = new File("Saved.txt");
			FileWriter fr = new FileWriter(file, true);
			fr.write(player.getName() + "," + player.getType() + "," + player.getAttack() + "," + player.getDefence() + "," + player.getHealth() + "," + player.getWeapon().getType() + "," + player.getWeapon().getBonus() + "," + player.getArmour().getType() + "," + player.getArmour().getBonus() + "," + player.getHelm().getType() + "," + player.getHelm().getBonus() + "," + player.getPotions() + "," + player.getLevel() + "," + player.getExperience() + "," + gameLevel + "\n");
			fr.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		in.close();
	}
}