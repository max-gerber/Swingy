package Swingy.models.Heros;

import Swingy.models.Equipment.*;

public class Orc extends Hero{
	public Orc(String name) {
		super();
		this.name = name;
		this.type = "Orc";
		this.attack = 80;
		this.defence = 15;
		this.health = 160;
		this.weapon = new Weapon("Dagger", 20);
		this.armour = new Armour("Cloth", 14);
		this.helm = new Helm("Cap", 10);
		this.potions = 1;
		this.level = 1;
		this.experience = 0;
	}
}