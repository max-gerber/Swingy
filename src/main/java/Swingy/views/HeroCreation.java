package Swingy.views;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HeroCreation {
	public static void creation() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("Would you like to use an existing hero?");
	}
	public static void existingHeros() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
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
	}
	public static void newHeroName() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("Enter your hero's name: \n");
	}
	public static void newHeroType() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("\nChoose a type of hero from the following:\n");
		System.out.println("Knight\t\t\tOrc\t\t\tAngel\n");
		System.out.println("Attck: 50\t\tAttack: 80\t\tAttack: 60");
		System.out.println("Defence: 20\t\tDefence: 15\t\tDefence: 30");
		System.out.println("Health: 50\t\tHealth: 80\t\tHealth: 60\n");
	}
}