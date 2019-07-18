package Swingy.models.Heros;

import Swingy.models.Equipment.*;

public class Angel extends Hero{
	public Angel(String name) {
		super();
		this.name = name;
		this.type = "Angel";
		this.attack = 60;
		this.defence = 30;
		this.health = 120;
		this.weapon = new Weapon("Spear", 10);
		this.armour = new Armour("Chainmail", 12);
		this.helm = new Helm("Cap", 10);
		this.potions = 5;
		this.level = 1;
		this.experience = 0;
	}
}