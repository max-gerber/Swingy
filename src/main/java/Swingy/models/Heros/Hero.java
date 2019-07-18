package Swingy.models.Heros;

import Swingy.models.Equipment.*;
import Swingy.models.Enemies.*;

import java.util.Random;

public class Hero {
	protected String	name;
	protected String	type;
	protected int		attack;
	protected int		defence;
	protected int		health;
	protected Weapon	weapon;
	protected Armour	armour;
	protected Helm		helm;
	protected int		potions;
	protected int		level;
	protected int		experience;

	public Hero() {
	}

	public void equip(Equipment newEquipment) {
		if (newEquipment.getEquipmentType() == "Weapon"){
			attack += newEquipment.getBonus() - this.weapon.getBonus();
			this.weapon = (Weapon) newEquipment;
		}
		if (newEquipment.getEquipmentType() == "Armour"){
			attack += newEquipment.getBonus() - this.armour.getBonus();
			this.armour = (Armour) newEquipment;
		}
		if (newEquipment.getEquipmentType() == "Helm"){
			attack += newEquipment.getBonus() - this.helm.getBonus();
			this.helm = (Helm) newEquipment;
		}
	}
	public void		gainExperience(int experience) {
		if ((this.experience + experience) > ((level * 1000) + (Math.pow(level - 1, 2) * 450))){
			System.out.println("#\tYou have leveled up to level " + level + "\t#");
			this.experience = (int) ((this.experience + experience) - ((level * 1000) + (Math.pow(level - 1, 2) * 450)));
			level++;
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
	public void gainPotion() {
		this.potions++;
	}
	
	public String	getName() {
		return this.name;
	}
	public String	getType() {
		return this.type;
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