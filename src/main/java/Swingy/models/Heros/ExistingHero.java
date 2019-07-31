package Swingy.models.Heros;

import Swingy.models.Equipment.*;

public class ExistingHero extends Hero{
	public ExistingHero(String name, String type, int attack, int defence, int health, Weapon weapon, Armour armour, Helm helm, int potions, int level, int experience){
		super();
		this.name = name;
		this.type = type;
		this.attack = attack;
		this.defence = defence;
		this.health = health;
		this.weapon = weapon;
		this.armour = armour;
		this.helm = helm;
		this.potions = potions;
		this.level = level;
		this.experience = experience;
	}
}