package Swingy.models.Enemies;

import java.util.Random;
import Swingy.models.Equipment.*;
import Swingy.views.*;

public class Enemy {
	private String	type;
	private int		power;
	private int		attack;
	private int		defence;
	private int		health;
	private Equipment	equipment;

	public	Enemy(int level) {
		Random		rand = new Random();

		String[]	enemyTypes = {"Skeleton", "Zombie", "Mad Man", "Brute", "Warrior", "Imp", "Ross"};
		String[]	weaponTypes = {"Dagger", "Sword", "Spear", "War-Axe"};
		String[]	armourTypes = {"Cloth", "Chainmail", "Plate"};
		String[]	helmTypes = {"Cap", "Mail Coif", "Great Helm"};

		this.type = enemyTypes[rand.nextInt(enemyTypes.length)];
		this.power = level * rand.nextInt(5);
		this.attack = this.power * rand.nextInt(5);
		this.defence = this.power * rand.nextInt(3);
		this.health = this.power * rand.nextInt(10) + 1;
		if (rand.nextInt(7) == 0) {
			this.equipment = new Weapon(weaponTypes[rand.nextInt(4)], this.power * rand.nextInt(20));
			this.attack += this.equipment.getBonus();
		} else if (rand.nextInt(7) == 0) {
			this.equipment = new Armour(armourTypes[rand.nextInt(3)], this.power * rand.nextInt(10));
			this.defence += this.equipment.getBonus();
		} else if (rand.nextInt(7) == 0) {
			this.equipment = new Helm(helmTypes[rand.nextInt(3)], this.power * rand.nextInt(15));
			this.health += this.equipment.getBonus();
		}
	}

	public void		hit(int damage) {
		if (damage > this.defence){
			this.health -= (damage - this.defence);
			Battle.damage(this.type);
		}
	}
	
	public String	getType() {
		return this.type;
	}
	public int		experienceDrop() {
		return this.power * 37;
	}
	public int		getAttack() {
		return this.attack;
	}
	public int		getDefence() {
		return this.defence;
	}
	public int		getHealth() {
		return this.health;
	}
	public Equipment getEquipment() {
		return this.equipment;
	}
}