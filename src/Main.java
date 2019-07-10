package src;

import src.Enemies.*;
import src.Equipment.*;
import src.Heros.*;

import java.lang.reflect.Constructor;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner	in = new Scanner(System.in);
		Random	rand = new Random();
		boolean	running = true;
		boolean	evasion;
		Hero	player;

		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("Welcome to Hell\n\n");
		System.out.println("Enter your hero's name: \n");
		String name = in.nextLine();
		System.out.print("\033[H\033[2J");
		System.out.flush();

		SELECTION:
		while (true){
			System.out.println("\nChoose a type of hero from the following:\n");
			System.out.println("Knight\t\t\tOrc\t\t\tAngel\n");
			System.out.println("Attck: 50\t\tAttack: 80\t\tAttack: 60");
			System.out.println("Defence: 20\t\tDefence: 15\t\tDefence: 30");
			System.out.println("Health: 50\t\tHealth: 80\t\tHealth: 60\n");

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
				default:
					System.out.print("\033[H\033[2J");
					System.out.flush();
					System.out.println("\nSelect one of the available options");
					continue;
			}
		}
		System.out.print("\033[H\033[2J");
		System.out.flush();

		GAME:
		while (running){
			System.out.println("****************************************************");
			
			Enemy enemy = new Enemy();
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

				String action = in.nextLine().toUpperCase();
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
								break BATTLE;
							} else {
								System.out.println("You failed to get away...");
								evasion = false;
							}
						} else {
							System.out.println("You must fight!");
						}
						break;
					default:
						System.out.println("Select one of the available options");
						break;
				}
			}
			if (enemy.getHealth() <= 0){
				System.out.println("#\tYou have vanquished the " + enemy.getType() + "\t#");
				int experience = enemy.experienceDrop();
				System.out.println("#\tYou have earned " + experience + " points\t#");
				player.gainExperience(experience);
				if (enemy.getWeapon() != null){
					Weapon droppedWeapon = enemy.getWeapon();
					System.out.println("#\tThe " + enemy.getType() + " has dropped a " + droppedWeapon.getType() + "(+" + droppedWeapon.getBonus() + ")\t#");
					System.out.println("#\tWould you like to exchange your " + player.getWeapon().getType() + "(+" + player.getWeapon().getBonus() + ") for it?\t#");
					String pickUp = in.nextLine().toUpperCase();
					switch (pickUp) {
						case "YES":
						case "Y":
							player.equipWeapon(droppedWeapon);
							break;
						default:
							System.out.println("#\tIt was probbably cursed anyway\t#");
							break;
					}
				}
				if (enemy.getArmour() != null){
					Armour droppedArmour = enemy.getArmour();
					System.out.println("#\tThe " + enemy.getType() + " has dropped a " + droppedArmour.getType() + "(+" + droppedArmour.getBonus() + ")\t#");
					System.out.println("#\tWould you like to exchange your " + player.getArmour().getType() + "(+" + player.getArmour().getBonus() + ") for it?\t#");
					String pickUp = in.nextLine().toUpperCase();
					switch (pickUp) {
						case "YES":
						case "Y":
							player.equipArmour(droppedArmour);
							break;
						default:
							System.out.println("#\tIt was probbably cursed anyway\t#");
							break;
					}
				}
				if (enemy.getHelm() != null){
					Helm droppedHelm = enemy.getHelm();
					System.out.println("#\tThe " + enemy.getType() + " has dropped a " + droppedHelm.getType() + "(+" + droppedHelm.getBonus() + ")\t#");
					System.out.println("#\tWould you like to exchange your " + player.getHelm().getType() + "(+" + player.getHelm().getBonus() + ") for it?\t#");
					String pickUp = in.nextLine().toUpperCase();
					switch (pickUp) {
						case "YES":
						case "Y":
							player.equipHelm(droppedHelm);
							break;
						default:
							System.out.println("#\tIt was probbably cursed anyway\t#");
							break;
					}
				}
			} else if (player.getHealth() <= 0) {
				System.out.println("#\tAlas you have died. Your adventure ends here.\t#");
				running = false;
			}
		}
	}
}