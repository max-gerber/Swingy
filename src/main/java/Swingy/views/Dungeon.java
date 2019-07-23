package Swingy.views;

public class Dungeon {
	public static void displayDungeon(int mapSize, int yPosition, int xPosition) {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("****************************************************");
		System.out.println("You are here:");
		for (int y = 0; y < mapSize; y++) {
			for (int x = 0; x < mapSize; x++) {
				if (y == yPosition && x == xPosition){
					System.out.print("X");
				} else {
					System.out.print(".");
				}
			}
			System.out.print("\n");
		}
		System.out.println("Type UP DOWN LEFT or RIGHT to move in those directions or EXIT to leave the dungeon");
	}
}