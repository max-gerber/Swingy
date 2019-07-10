package src.Equipment;

public class Armour extends Equipment {
	public	Armour(int typeSelector, int bonus) {
		switch (typeSelector) {
			case 0:
				this.type = "Cloth";
				this.bonus = (int) Math.round(new Double(bonus) * 0.75);
				break;
			case 1:
				this.type = "Chainmail Armour";
				this.bonus = bonus;
				break;
			case 2:
				this.type = "Plate Armour";
				this.bonus = (int) Math.round(new Double(bonus) * 1.5);
				break;
		}
	}
}