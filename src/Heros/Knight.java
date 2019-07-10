package src.Heros;

import src.Equipment.*;

public class Knight extends Hero{
	public Knight(String name) {
		super(name, "Knight");
		this.attack = 50;
		this.defence = 20;
		this.health = 100;
		this.weapon = new Weapon(1, 10);
		this.armour = new Armour(0, 12);
		this.helm = new Helm(0, 10);
	}
}