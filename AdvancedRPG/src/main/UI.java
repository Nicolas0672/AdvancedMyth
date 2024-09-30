package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Heart;
import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue;
	BufferedImage heart_full, heart_half, heart_blank;
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		//OBJ_Key key = new OBJ_Key();
		//keyImage = key.image;
		
		// CREATE HUD OBJECT
		OBJ_Heart heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		
		if(gp.gameState == gp.playState) {
			
			drawPlayerLife();
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			//g2.drawString("x " + gp.player.hasKey, 74, 65);
				
			// MESSAGE
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		
		}
		if (gp.gameState == gp.pauseState){
			drawPauseScreen();
			drawPlayerLife();
		
		}
		
		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
			drawPlayerLife();
		}
	}
	
	public void drawDialogueScreen() {
		
		// WINDOW
		int x = gp.tileSize * 2, y = gp.tileSize / 2;;
		int width = gp.screenWidth - (gp.tileSize*4), height = gp.tileSize * 4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
		x+= gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y+= 40;
		}		
		
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0 ,0, 210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke((new BasicStroke(5)));
		g2.drawRoundRect(x  + 5, y + 5, width - 10, height - 10, 25, 25);
	}
	
	public void drawPauseScreen() {
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
			String text = "PAUSED";
			int x = getXforCenteredText(text), y = gp.screenHeight /2;
			g2.drawString(text, x, y);
	}
	
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length/2;
		return x;
		
	}
	
	public void drawPlayerLife() {
		
		int x = gp.tileSize/ 2;
		int y = gp.tileSize/2;
		int i = 0;
		
		// DRAW MAX LIFE
		while(i < gp.player.maxLife / 2) {
			g2.drawImage(heart_blank, x, y, gp.tileSize, gp.tileSize, null);
			i++;
			
			x+= gp.tileSize;
				
		}
		
		// RESET
		x = gp.tileSize/ 2;
		y = gp.tileSize/2;
		i = 0;
		
		// DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, gp.tileSize, gp.tileSize, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, gp.tileSize, gp.tileSize, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		
	}
	
	
}
