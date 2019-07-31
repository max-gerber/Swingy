package Swingy.controllers;

import Swingy.models.Heros.*;
import Swingy.models.Enemies.*;
import Swingy.models.Equipment.*;
import Swingy.views.*;

import javax.swing.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class GuiGame extends JFrame {
	private boolean		evasion = true;
	private Random		rand = new Random();
	private String		gameState = "heroSelection";
	@NotNull (message = "'Name' cannot be Null")
	@NotEmpty
	@NotBlank
	private String		name;
	private Enemy		enemy = new Enemy(1);
	private Hero		player = new Hero();
	private int			gameLevel = 1;
	private int			mapSize;
	private int			yPosition;
	private	int			xPosition;
	JTextField			textField1 = new JTextField("Type your commands here", 20);
	JButton				button1 = new JButton("Enter");
	JPanel				panel1 = new JPanel();

	public GuiGame() {
		this.setUp();
	}

	public void heroSelection(String input) {
		HeroCreation.creation();
		String choice = input.toUpperCase();
		switch (choice) {
			case "YES":
				HeroCreation.existingHeros();
				this.gameState = "existingHeros";
				break;
			case "NO":
				HeroCreation.newHeroName();
				this.gameState = "newHeroName";
				break;
			default:
				HeroCreation.creation();
		}
	}

	public void existingHeros(String input) {
		String selection = input.toUpperCase();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Saved.txt"));
			String line = reader.readLine();
			for (int i = 1; i <= Integer.parseInt(selection); i++) {
				line = reader.readLine();
			}
			reader.close();
			String hero[] = line.split(",");
			this.player = (Hero) new ExistingHero(hero[0], hero[1], Integer.parseInt(hero[2]), Integer.parseInt(hero[3]), Integer.parseInt(hero[4]), new Weapon(hero[5], Integer.parseInt(hero[6])), new Armour(hero[7], Integer.parseInt(hero[8])), new Helm(hero[9], Integer.parseInt(hero[10])), Integer.parseInt(hero[11]), Integer.parseInt(hero[12]), Integer.parseInt(hero[13]));
			this.gameLevel = Integer.parseInt(hero[14]);
			this.startGame();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(selection + " is not a valid option");
		} catch(IOException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			HeroCreation.existingHeros();
		}
	}

	public void newHeroType(String input) {
		HeroCreation.newHeroType();
		String type = input.toUpperCase();
		switch (type) {
			case "KNIGHT":
				this.player = new Knight(this.name);
				this.startGame();
			case "ORC":
				this.player = new Orc(this.name);
				this.startGame();
			case "ANGEL":
				this.player = new Angel(this.name);
				this.startGame();
		}
	}

	public void startGame() {
		this.mapSize = this.player.getLevel() * 5 + 5 - this.player.getLevel() % 2;
		this.yPosition = (this.mapSize - 1) / 2;
		this.xPosition = this.yPosition;
		Dungeon.displayDungeon(mapSize, yPosition, xPosition);
		this.gameState = "movement";
	}

	public void battle(String input) {
		String action = input.toUpperCase();
		if (this.enemy.getHealth() > 0 && this.player.getHealth() > 0){	//	Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
			Battle.battle(this.enemy, this.player, this.evasion);
			switch (action) {
				case "ATTACK":
					Battle.battle(this.enemy, this.player, this.evasion);
					this.player.fight(this.enemy);
					this.evasion = false;
					break;
				case "DRINK POTION":
					this.player.usePotion();
					break;
				case "RUN":
					if (this.evasion){
						if (this.rand.nextInt(2) == 0){
							Battle.run();
							Dungeon.displayDungeon(mapSize, yPosition, xPosition);
							this.gameState = "movement";
							while (this.gameState == "movement"){
							}
						} else {
							Battle.failRun();
							this.evasion = false;
						}
					}
			}
		}
		else {
			if (this.player.getHealth() <= 0) {
				Battle.defeat();
				return;
			}
			int experience = this.enemy.experienceDrop();
			this.player.gainExperience(experience);
			if (Battle.victory(this.enemy, experience, this.player) == 1){
				this.gameState = "pickUpEquipment";
			} else if (this.rand.nextInt(5) == 0){
				Battle.droppedPotion(this.enemy);
				this.gameState = "pickUpPotion";
			} else {
				Battle.cont();
				this.gameState = "cont";
			}
		}
	}

	public void cont() {
		Dungeon.displayDungeon(mapSize, yPosition, xPosition);
		this.gameState = "movement";
	}

	public void pickUpEquipment(String input) {
		String pickUp = input.toUpperCase();
		switch (pickUp) {
			case "YES":
			case "Y":
				this.player.equip(this.enemy.getEquipment());
				break;
			default:
				Battle.curse();
		}
		Dungeon.displayDungeon(mapSize, yPosition, xPosition);
		this.gameState = "movement";
	}

	public void pickUpPotion(String input) {
		System.out.println(input);
		String pickUp = input.toUpperCase();
		switch (pickUp) {
			case "YES":
			case "Y":
				this.player.gainPotion();
		}
		Dungeon.displayDungeon(mapSize, yPosition, xPosition);
		this.gameState = "movement";
	}

	public void movement(String input) {
		String movement = input.toUpperCase();
		switch (movement) {
			case "UP":
				this.yPosition--;
				break;
			case "DOWN":
				this.yPosition++;
				break;
			case "LEFT":
				this.xPosition--;
				break;
			case "RIGHT":
				this.xPosition++;
				break;
			case "EXIT":
				this.exit();
		}
		if (this.yPosition < 0 || this.xPosition < 0 || this.yPosition == this.mapSize || this.xPosition == this.mapSize){
			this.mapSize = this.player.getLevel() * 5 + 5 - this.player.getLevel() % 2;	//	Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
			this.yPosition = (this.mapSize - 1) / 2;
			this.xPosition = this.yPosition;
			this.gameLevel++;
		}
		Dungeon.displayDungeon(mapSize, yPosition, xPosition);
		if (this.rand.nextInt(4) == 0){
			this.enemy = new Enemy(gameLevel);
			Battle.battle(this.enemy, this.player, true);
			this.gameState = "battle";
		}
	}

	public void exit() {
		try {
			File file = new File("Saved.txt");
			FileWriter fr = new FileWriter(file, true);
			fr.write(this.player.getName() + "," + this.player.getType() + "," + this.player.getAttack() + "," + this.player.getDefence() + "," + this.player.getHealth() + "," + this.player.getWeapon().getType() + "," + this.player.getWeapon().getBonus() + "," + this.player.getArmour().getType() + "," + this.player.getArmour().getBonus() + "," + this.player.getHelm().getType() + "," + this.player.getHelm().getBonus() + "," + this.player.getPotions() + "," + this.player.getLevel() + "," + this.player.getExperience() + "," + this.gameLevel + "\n");
			fr.close();
			Dungeon.exit();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void setUp() {
		
		setSize(300, 100);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (gameState) {
					case "heroSelection":
						heroSelection(textField1.getText());
						break;
					case "existingHeros":
						existingHeros(textField1.getText());
						break;
					case "newHeroName":
						if (textField1.getText().equals("")){
							System.out.println("'Name' cannot be Null");
							return;
						}
						name = textField1.getText();

						HeroCreation.newHeroType();
						gameState = "newHeroType";
						break;
					case "newHeroType":
						newHeroType(textField1.getText());
						break;
					case "movement":
						movement(textField1.getText());
						break;
					case "battle":
						battle(textField1.getText());
						break;
					case "pickUpEquipment":
						pickUpEquipment(textField1.getText());
						break;
					case "pickUpPotion":
						pickUpPotion(textField1.getText());
					case "cont":
						cont();
				}
			}
		});
		panel1.add(textField1);
		panel1.add(button1);
		add(panel1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		HeroCreation.creation();
		while(gameState == "heroSelection") {

		}
	}

	public int getGameLevel() {
		return this.gameLevel;
	}
	public Hero getPlayer() {
		return this.player;
	}
	public int getMapSize() {
		return this.mapSize;
	}
	public int getYPosition() {
		return this.yPosition;
	}
	public int getXPosition() {
		return this.xPosition;
	}
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public void setGameLevel(int gameLevel) {
		this.gameLevel = gameLevel;
	}
	public void setPlayer(Hero player) {
		this.player = player;
	}
}