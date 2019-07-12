package src.models.Equipment;

public class Helm extends Equipment{
	public	Helm(String type, int bonus) {
		this.equipmentType = "Helm";
		this.type = type;
		switch (type) {
			case "Cap":
				this.bonus = (int) Math.round(new Double(bonus) * 0.5);
				break;
			case "Mail Coif":
				this.bonus = bonus;
				break;
			case "Great Helm":
				this.bonus = (int) Math.round(new Double(bonus) * 1.5);
				break;
		}
	}
}