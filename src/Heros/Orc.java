package src.Heros;

import src.Equipment.*;

public class Orc extends Hero{
	public Orc(String name) {
		super(name, "Orc");
		this.attack = 80;
		this.defence = 15;
		this.health = 160;
		this.weapon = new Weapon(1, 10);
		this.armour = new Armour(0, 12);
		this.helm = new Helm(0, 10);
	}
}