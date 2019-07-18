package Swingy.models.Equipment;

public class Armour extends Equipment {
	public	Armour(String type, int bonus) {
		this.equipmentType = "Armour";
		this.type = type + " Armour";
		switch (type) {
			case "Cloth":
				this.bonus = (int) Math.round(new Double(bonus) * 0.75);
				break;
			case "Chainmail":
				this.bonus = bonus;
				break;
			case "Plate":
				this.bonus = (int) Math.round(new Double(bonus) * 1.5);
				break;
		}
	}
}