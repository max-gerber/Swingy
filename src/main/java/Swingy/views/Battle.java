package Swingy.views;

import Swingy.controllers.ConsoleGame;
import Swingy.models.Equipment.*;
import Swingy.models.Enemies.*;
import Swingy.models.Heros.*;

import java.util.Random;
import java.util.Scanner;

public class Battle {
	public static void battle(Enemy enemy, Hero player, boolean evasion) {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("****************************************************");
		System.out.println("#\tYou are fighting a " + enemy.getType() + "!\t#\n");
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
	}
	public static void run() {
		System.out.println("You ran away in terror");
	}
	public static void failRun() {
		System.out.println("You failed to get away...");
	}
	public static int victory(Enemy enemy, int experience, Hero player) {
		System.out.println("#\tYou have vanquished the " + enemy.getType() + "\t#");
			System.out.println("#\tYou have earned " + experience + " points\t#");
			if (enemy.getEquipment() != null){
				Equipment droppedEquipment = enemy.getEquipment();
				System.out.println("#\tThe " + enemy.getType() + " has dropped a " + droppedEquipment.getType() + "(+" + droppedEquipment.getBonus() + ")\t#");
				System.out.println("#\tWould you like to equip it?\t#");
				System.out.println("\n");
				System.out.println("#\tYour inventory:\t#");
				System.out.println("Weapon:\t" + player.getWeapon().getType() + "(+" + player.getWeapon().getBonus() + ")");
				System.out.println("Armour:\t" + player.getArmour().getType() + "(+" + player.getArmour().getBonus() + ")");
				System.out.println("Helm:\t" + player.getHelm().getType() + "(+" + player.getHelm().getBonus() + ")");
				return 1;
			}
			return 0;
	}
	public static void curse() {
		System.out.println("#\tIt was probbably cursed anyway\t#");
	}
	public static void potion(Enemy enemy) {
		System.out.println("#\tThe " + enemy.getType() + " has dropped a potion\t#");
		System.out.println("#\tWould you like to pick it up?\t#");
	}
	public static void cont() {
		System.out.println("Press enter to continue");
	}
	public static void defeat() {
		System.out.println("#\tAlas you have died. Your adventure ends here.\t#");
	}
}