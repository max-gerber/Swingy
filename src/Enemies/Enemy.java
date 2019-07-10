package src.Enemies;

import java.util.Random;
import src.Equipment.*;

public class Enemy {
	private String	type;
	private int		power;
	private int		attack;
	private int		defence;
	private int		health;
	private Weapon	weapon;
	private Armour	armour;
	private Helm	helm;

	public	Enemy() {
		Random		rand = new Random();

		String[]	types = {"Skeleton", "Zombie", "Mad Man", "Brute", "Warrior", "Imp", "Ross"};

		this.type = types[rand.nextInt(types.length)];
		this.power = rand.nextInt(5);
		this.attack = this.power * rand.nextInt(20);
		this.defence = this.power * rand.nextInt(10) + 1;
		this.health = this.power * rand.nextInt(40);
		if (rand.nextInt(7) == 0){
			this.weapon = new Weapon(rand.nextInt(4), this.power * rand.nextInt(20));
			this.attack += this.weapon.getBonus();
		}
		if (rand.nextInt(10) == 0){
			this.armour = new Armour(rand.nextInt(3), this.power * rand.nextInt(10));
			this.defence += this.armour.getBonus();
		}
		if (rand.nextInt(12) == 0){
			this.helm = new Helm(rand.nextInt(4), this.power * rand.nextInt(15));
			this.health += this.helm.getBonus();
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
	public void		hit(int damage) {
		if (damage > this.defence){
			this.health -= (damage - this.defence);
			System.out.println("#\tYou hit the " + this.type + "\t#");
		}
	}
	public Weapon	getWeapon() {
		return this.weapon;
	}
	public Armour	getArmour() {
		return this.armour;
	}
	public Helm		getHelm() {
		return this.helm;
	}
}