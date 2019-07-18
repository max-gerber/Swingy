package Swingy.views;

import Swingy.controllers.Game;
import Swingy.models.Equipment.*;
import Swingy.models.Enemies.*;

import java.util.Random;
import java.util.Scanner;

public class Battle {
	public Battle(Game game, Scanner in) {
		Enemy	enemy = new Enemy(game.getGameLevel());
		Random	rand = new Random();
		boolean	evasion = true;

		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("****************************************************");
		System.out.println("#\tA " + enemy.getType() + " has appeared!\t#\n");

		BATTLE:
		while (enemy.getHealth() > 0 && game.getPlayer().getHealth() > 0){
			System.out.println("\t### " + game.getPlayer().getName() + " ###");
			System.out.println("Attack: " + game.getPlayer().getAttack());
			System.out.println("Defence: " + game.getPlayer().getDefence());
			System.out.println("Health: " + game.getPlayer().getHealth());
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
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println("****************************************************");

			switch (action) {
				case "ATTACK":
					game.getPlayer().fight(enemy);
					evasion = false;
					break;
				case "DRINK POTION":
					game.getPlayer().usePotion();
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
				default:
					break;
			}
		}
		if (enemy.getHealth() <= 0){
			System.out.println("#\tYou have vanquished the " + enemy.getType() + "\t#");
			int experience = enemy.experienceDrop();
			System.out.println("#\tYou have earned " + experience + " points\t#");
			game.getPlayer().gainExperience(experience);
			if (enemy.getEquipment() != null){
				Equipment droppedEquipment = enemy.getEquipment();
				System.out.println("#\tThe " + enemy.getType() + " has dropped a " + droppedEquipment.getType() + "(+" + droppedEquipment.getBonus() + ")\t#");
				System.out.println("#\tWould you like to equip it?\t#");
				System.out.println("\n");
				System.out.println("#\tYour inventory:\t#");
				System.out.println("Weapon:\t" + game.getPlayer().getWeapon().getType() + "(+" + game.getPlayer().getWeapon().getBonus() + ")");
				System.out.println("Armour:\t" + game.getPlayer().getArmour().getType() + "(+" + game.getPlayer().getArmour().getBonus() + ")");
				System.out.println("Helm:\t" + game.getPlayer().getHelm().getType() + "(+" + game.getPlayer().getHelm().getBonus() + ")");
				String pickUp = in.nextLine().toUpperCase();
				switch (pickUp) {
					case "YES":
					case "Y":
						game.getPlayer().equip(droppedEquipment);
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
						game.getPlayer().gainPotion();
						break;
				}
			}
		} else if (game.getPlayer().getHealth() <= 0) {
			System.out.println("#\tAlas you have died. Your adventure ends here.\t#");
			return;
		}
		System.out.println("Press enter to continue");
		in.nextLine();
	}
}