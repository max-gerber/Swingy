package src.Equipment;

/**
 * Helm
 */
public class Helm extends Equipment{
	public	Helm(int typeSelector, int bonus) {
		switch (typeSelector) {
			case 0:
				this.type = "Cap";
				this.bonus = (int) Math.round(new Double(bonus) * 0.5);
				break;
			case 1:
				this.type = "Mail Coif";
				this.bonus = bonus;
				break;
			case 2:
				this.type = "Great Helm";
				this.bonus = (int) Math.round(new Double(bonus) * 1.5);
				break;
		}
	}
}