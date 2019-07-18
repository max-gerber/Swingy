package Swingy.models.Heros;

import Swingy.models.Equipment.*;

public class Knight extends Hero{
	public Knight(String name) {
		super();
		this.name = name;
		this.type = "Knight";
		this.attack = 50;
		this.defence = 20;
		this.health = 100;
		this.weapon = new Weapon("Sword", 10);
		this.armour = new Armour("Chainmail", 10);
		this.helm = new Helm("Mail Coif", 10);
		this.potions = 3;
		this.level = 1;
		this.experience = 0;
	}
}