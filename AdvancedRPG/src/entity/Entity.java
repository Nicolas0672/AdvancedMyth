package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;

public class Entity {

	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter;
	List<String> dialogues = new ArrayList<>();
	public boolean invincible = false;
	public int invincibleCounter;
	public int type;
	
	public int dialogueIndex = 0;
	
	// CHARACTER STATUS
	public int life;
	public int maxLife;
	public String name;
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	
	public void speak() {
		
		
		if(dialogueIndex < dialogues.size()) {
			gp.ui.currentDialogue = dialogues.get(dialogueIndex);
			dialogueIndex++;
			
		}
				
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	
	public boolean isLastDialogue() {
		
		if(dialogueIndex >= dialogues.size()) {
			dialogueIndex = 0;
			return true;
		}
		return false;
	}
	
	
	
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
		
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
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Only draw tiles that are on the screen (optimization)
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
        	
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
        	
        	if(invincible == true) {
        		
        		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        	}
        	
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            // Reset Alpha
            //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
	}
}
