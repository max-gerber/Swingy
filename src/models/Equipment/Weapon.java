package src.models.Equipment;

public class Weapon extends Equipment{
	public	Weapon(String type, int bonus) {
		this.equipmentType = "Weapon";
		this.type = type;
		switch (type) {
			case "Dagger":
				this.bonus = (int) Math.round(new Double(bonus) * 0.75);
				break;
			case "Sword":
				this.bonus = bonus;
				break;
			case "Spear":
				this.bonus = (int) Math.round(new Double(bonus) * 1.5);
				break;
			case "War-Axe":
				this.bonus = (int) Math.round(new Double(bonus) * 2);
		}
	}
}