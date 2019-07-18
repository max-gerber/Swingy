package Swingy.controllers;

import Swingy.models.Heros.*;
import Swingy.views.*;

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
	private int		gameLevel = 1;
	private Hero	player;
	private int		mapSize;
	private int		yPosition;
	private int		xPosition;

	public Game() {
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
		Scanner	in = new Scanner(System.in);
		Random	rand = new Random();
		HeroCreation.creation(this, in);
		this.mapSize = this.player.getLevel() * 5 + 5 - this.player.getLevel() % 2;
		this.yPosition = (this.mapSize - 1) / 2;
		this.xPosition = this.yPosition;
		GAME:
		while (true){
			Dungeon.displayDungeon(this, in);
			String movement = in.nextLine().toUpperCase();
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
				new Battle(this, in);
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
		in.close();
	}
}