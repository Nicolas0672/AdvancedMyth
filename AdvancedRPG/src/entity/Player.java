package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{


	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler KeyH) {
		
		super(gp);
		this.keyH = KeyH;
		
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2) ;
		
		solidArea = new Rectangle(8, 16, 32, 32);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues(); 
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = 50; // later changed
		worldY = 50; // later changed
		speed = 4;  
		direction = "down";
		
		// PLAYER STATUS
		maxLife = 6;
		life = maxLife;
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			 
		
		
		if(keyH.upPressed == true) {
			direction = "up";
			
			
		}
		else if(keyH.downPressed == true) {
			direction = "down";
			
		}
		else if(keyH.leftPressed == true) {
			direction = "left";
			
		}
		else if(keyH.rightPressed == true) {
			direction = "right";
			
		}
		// CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		// CHECK OBJECT COLLISION
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		// CHECK NPC COLLISION
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		// CHECK MONSTER COLLISION
		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
		contactMonster(monsterIndex);
		
		// CHECK EVENT
		gp.eHandler.checkEvent();
		gp.keyH.enterPressed = false;
		
		if(collisionOn == false) {
			
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX-= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
		
		spriteCounter++; 
		if(spriteCounter > 15) {	
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		if(invincible == true) {
			invincibleCounter++;
			
			if(invincibleCounter > 60) {
				
				invincible = false;
				invincibleCounter = 0;
			}
		}
		}
		
	}
	public void pickUpObject(int index) {
		if(index != 999) {
			
			String objectName = gp.obj[index].name;
			
			switch(objectName) {
			case "Key":
				hasKey++;
				gp.obj[index] = null;
				gp.ui.showMessage("You got a key");
				break;
			case "Door":
				if(hasKey > 0) {
					gp.obj[index] = null;
					hasKey--;
					gp.ui.showMessage("You opened the door!");
				}
				else {
					gp.ui.showMessage("You need a key!");
				}
				break;
			case "Boots":
				speed += 2;
				gp.obj[index] = null;
				gp.ui.showMessage("Speed up!");
				break;
			case "Chest":
				break;
			}
				
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1)
			{
				image = up1;
			}
			if(spriteNum == 2)
			{
				image = up2;
			}	
			
			break;
		case "down":
			if(spriteNum == 1)
			{
				image = down1;
			}
			if(spriteNum == 2)
			{
				image = down2;
			}	
			break;
		case "left":
			if(spriteNum == 1)
			{
				image = left1;
			}
			if(spriteNum == 2)
			{
				image = left2;
			}	
			break;
		case "right":
			if(spriteNum == 1)
			{
				image = right1;
			}
			if(spriteNum == 2)
			{
				image = right2;
			}	
			break;	
		
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
	
	public void interactNPC(int i) {
		
		if(i != 999) {
			
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.currentNPC = gp.npc[i];
				gp.npc[i].speak();
			}
			
		}
		
	}
	public void contactMonster(int i) {
		
		if(i != 999) {
			
			if(invincible == false) {
				life -= 1;
				invincible = true;
			}
			
		}
	}
	
	
}
