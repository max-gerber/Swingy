package src.Equipment;

public class Weapon extends Equipment{
	public	Weapon(int typeSelector, int bonus) {
		switch (typeSelector) {
			case 0:
				this.type = "Dagger";
				this.bonus = (int) Math.round(new Double(bonus) * 0.75);
				break;
			case 1:
				this.type = "Sword";
				this.bonus = bonus;
				break;
			case 2:
				this.type = "Spear";
				this.bonus = (int) Math.round(new Double(bonus) * 1.5);
				break;
			case 3:
				this.type = "War-Axe";
				this.bonus = (int) Math.round(new Double(bonus) * 2);
		}
	}
}