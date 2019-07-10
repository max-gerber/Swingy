package src.Heros;

import src.Equipment.*;
import src.Enemies.*;

import java.util.Random;

public class Hero {
	private	String		name;
	private String		type;
	private int			level;
	private int			experience;
	protected int		attack;
	protected int		defence;
	protected int		health;
	protected Weapon	weapon;
	protected Armour	armour;
	protected Helm		helm;
	private int			potions;

	public Hero(String name, String type) {
		this.name = name;
		this.type = type;
		this.level = 1;
		this.experience = 0;
		this.potions = 2;
	}

	public void		equipWeapon(Weapon newWeapon) {
		attack += newWeapon.getBonus() - this.weapon.getBonus();
		this.weapon = newWeapon;
	}
	public void		equipArmour(Armour newArmour) {
		attack += newArmour.getBonus() - this.armour.getBonus();
		this.armour = newArmour;
	}
	public void		equipHelm(Helm newHelm) {
		attack += newHelm.getBonus() - this.helm.getBonus();
		this.helm = newHelm;
	}
	public void		gainExperience(int experience) {
		if ((this.experience + experience) > ((level * 1000) + (Math.pow(level - 1, 2) * 450))){
			level++;
			System.out.println("#\tYou have leveled up to level " + level + "\t#");
			this.experience = (int) ((this.experience + experience) - ((level * 1000) + (Math.pow(level - 1, 2) * 450)));
			return;
		}
		this.experience += experience;
	}
	public void		fight(Enemy enemy) {
		Random rand = new Random();
		if (rand.nextInt(2) == 0){
			enemy.hit(this.attack);
		}
		if (rand.nextInt(2) == 0){
			if (enemy.getAttack() > this.defence){
				this.health -= (enemy.getAttack() - defence);
				System.out.println("#\tYou were hit\t#");
			}
		}
	}
	public void		usePotion() {
		if (this.potions == 0){
			System.out.println("You have no potions available");
			return;
		}
		this.health += 100;
		this.potions--;
	}
	public String	getName() {
		return this.name;
	}
	public int		getLevel() {
		return this.level;
	}
	public int		getExperience() {
		return this.experience;
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
	public Weapon	getWeapon() {
		return this.weapon;
	}
	public Armour	getArmour() {
		return this.armour;
	}
	public Helm		getHelm() {
		return this.helm;
	}
	public int		getPotions() {
		return this.potions;
	}
}