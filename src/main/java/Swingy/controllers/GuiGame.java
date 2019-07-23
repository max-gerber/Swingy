package Swingy.controllers;

import Swingy.models.Heros.*;
import Swingy.models.Enemies.*;
import Swingy.models.Equipment.*;
import Swingy.views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GuiGame extends JFrame {
	private boolean	buttonPressed = false;
	private int		gameLevel = 1;
	private Hero	player;
	private int		mapSize;
	private int		yPosition;
	private int		xPosition;
	JPanel panel1 = new JPanel();
	JTextField textField1 = new JTextField("Type your commands here", 20);
	JButton button1 = new JButton("Enter");

	public GuiGame() {
		this.startGame();
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

	public void startGame() {
		PrintStream origOut = System.out;
		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
		Random	rand = new Random();

		setSize(300, 100);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(textField1.getText());
			}
		});
		panel1.add(textField1);
		panel1.add(button1);
		add(panel1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


		HEROSELECTION:
		while (true){
			HeroCreation.creation();
			System.setOut(new java.io.PrintStream(out));
			while (out.toString().equals("Would you like to use an existing hero?") == true){
			}
			String choice = out.toString();
			System.setOut(origOut);
			switch (choice) {
				case "YES":
					HeroCreation.existingHeros();
					while (buttonPressed == false){
						try {
							Thread.sleep(200);
						}
						catch (InterruptedException e){
						}
					}
					buttonPressed = false;
					String selection =textField1.getText();
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
						break HEROSELECTION;
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					} catch(IOException e) {
						System.out.println(e.getMessage());
					} catch (NumberFormatException e) {
						continue HEROSELECTION;
					}
					break HEROSELECTION;
				case "NO":
					HeroCreation.newHeroName();
					while (buttonPressed == false){
						try {
							Thread.sleep(200);
						}
						catch (InterruptedException e){
						}
					}
					buttonPressed = false;
					String name =textField1.getText();
					while (true){
						HeroCreation.newHeroType();
						while (buttonPressed == false){
							try {
								Thread.sleep(200);
							}
							catch (InterruptedException e){
							}
						}
						buttonPressed = false;
						String type =textField1.getText();
						switch (type) {
							case "KNIGHT":
								this.player = new Knight(name);
								break HEROSELECTION;
							case "ORC":
								this.player = new Orc(name);
								break HEROSELECTION;
							case "ANGEL":
								this.player = new Angel(name);
								break HEROSELECTION;
						}
					}
			}
		}
		this.mapSize = this.player.getLevel() * 5 + 5 - this.player.getLevel() % 2;
		this.yPosition = (this.mapSize - 1) / 2;
		this.xPosition = this.yPosition;
		GAME:
		while (true){
			Dungeon.displayDungeon(mapSize, yPosition, xPosition);
			while (buttonPressed == false){
				try {
					Thread.sleep(200);
				}
				catch (InterruptedException e){
				}
			}
			buttonPressed = false;
			String movement =textField1.getText();
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
					break GAME;
				default:
					continue GAME;
			}
			if (this.yPosition < 0 || this.xPosition < 0 || this.yPosition == this.mapSize || this.xPosition == this.mapSize){
				this.mapSize = this.player.getLevel() * 5 + 5 - this.player.getLevel() % 2;
				this.yPosition = (this.mapSize - 1) / 2;
				this.xPosition = this.yPosition;
				this.gameLevel++;
			}
			if (rand.nextInt(4) == 0){
				Enemy	enemy = new Enemy(gameLevel);
				boolean	evasion = true;
				BATTLE:
				while (enemy.getHealth() > 0 && player.getHealth() > 0){
					Battle.battle(enemy, player, evasion);
					while (buttonPressed == false){
						try {
							Thread.sleep(200);
						}
						catch (InterruptedException e){
						}
					}
					buttonPressed = false;
					String action =textField1.getText();
					switch (action) {
						case "ATTACK":
							player.fight(enemy);
							evasion = false;
							break;
						case "DRINK POTION":
							player.usePotion();
							break;
						case "RUN":
							if (evasion){
								if (rand.nextInt(2) == 0){
									Battle.run();
									break BATTLE;
								} else {
									Battle.failRun();
									evasion = false;
								}
							}
							break;
						default:
							break;
					}
				}
				if (enemy.getHealth() <= 0){
					int experience = enemy.experienceDrop();
					player.gainExperience(experience);
					if (Battle.victory(enemy, experience, player) == 1){
						while (buttonPressed == false){
							try {
								Thread.sleep(200);
							}
							catch (InterruptedException e){
							}
						}
						buttonPressed = false;
						String pickUp =textField1.getText();
						switch (pickUp) {
							case "YES":
							case "Y":
								player.equip(enemy.getEquipment());
								break;
							default:
								Battle.curse();
								break;
						}
					}
					}
					if (rand.nextInt(5) == 0){
						Battle.potion(enemy);
						while (buttonPressed == false){
							try {
								Thread.sleep(200);
							}
							catch (InterruptedException e){
							}
						}
						buttonPressed = false;
						String pickUp =textField1.getText();
						switch (pickUp) {
							case "YES":
							case "Y":
								player.gainPotion();
								break;
						}
					}
					Battle.cont();
				while (buttonPressed == false){
					try {
						Thread.sleep(200);
					}
					catch (InterruptedException e){
					}
				}
				buttonPressed = false;
				} else if (player.getHealth() <= 0) {
					Battle.defeat();
					return;
				}
			}
		try {
			File file = new File("Saved.txt");
			FileWriter fr = new FileWriter(file, true);
			fr.write(player.getName() + "," + player.getType() + "," + player.getAttack() + "," + player.getDefence() + "," + player.getHealth() + "," + player.getWeapon().getType() + "," + player.getWeapon().getBonus() + "," + player.getArmour().getType() + "," + player.getArmour().getBonus() + "," + player.getHelm().getType() + "," + player.getHelm().getBonus() + "," + player.getPotions() + "," + player.getLevel() + "," + player.getExperience() + "," + this.gameLevel + "\n");
			fr.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}