package src.Heros;

import src.Equipment.*;

public class Angel extends Hero{
	public Angel(String name) {
		super(name, "Angel");
		this.attack = 60;
		this.defence = 30;
		this.health = 120;
		this.weapon = new Weapon(1, 10);
		this.armour = new Armour(0, 12);
		this.helm = new Helm(0, 10);
	}
}