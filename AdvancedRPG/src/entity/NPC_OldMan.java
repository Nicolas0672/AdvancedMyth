package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_OldMan extends Entity{

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		getImage();
		setDialogue();
	}
		
	public void getImage() {
			
		try {
				
			up1 = ImageIO.read(getClass().getResourceAsStream("/NPC/oldman_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/NPC/oldman_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/oldman_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/oldman_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/NPC/oldman_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/NPC/oldman_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/NPC/oldman_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/NPC/oldman_right_2.png"));
				
		}catch(IOException e) {
				e.printStackTrace();
		}
	}
	
	public void setDialogue() {
		dialogues.add("Ahh, I see the winds have brought a new soul \nto these troubled lands.") ;
		dialogues.add("Hrmph, the town... it suffers. A foul plague, \nborn from darkness, creeps through every \ncorner.");
		dialogues.add("But there's hope, young one. Hmph... \nThe cure, it lies in the strength of three.");
		dialogues.add("Three beasts, ancient and terrible, hold\n the ingredients to mend our fate.")  ;
		dialogues.add("Defeat them, and the path to salvation will be \nyours to walk.");
	}
	public void setAction() {
		
		actionLockCounter++;
		
		if(actionLockCounter == 120) {
			
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		}
		
	}
	
	public void speak() {
		
		super.speak();
	}
}

