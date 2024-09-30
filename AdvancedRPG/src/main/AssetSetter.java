package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 31 * gp.tileSize; // need changing in the future
		gp.obj[0].worldY = 9 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key();
		gp.obj[1].worldX = 9 * gp.tileSize; // need changing in the future
		gp.obj[1].worldY = 16 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 21 * gp.tileSize; // need changing in the future
		gp.obj[2].worldY = 3 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Key();
		gp.obj[7].worldX = 2 * gp.tileSize; // need changing in the future
		gp.obj[7].worldY = 13 * gp.tileSize;
		
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 15 * gp.tileSize; // need changing in the future
		gp.obj[3].worldY = 13 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 8 * gp.tileSize; // need changing in the future
		gp.obj[4].worldY = 28 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Door();
		gp.obj[5].worldX = 14 * gp.tileSize; // need changing in the future
		gp.obj[5].worldY = 41 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Chest();
		gp.obj[6].worldX = 2 * gp.tileSize; // need changing in the future
		gp.obj[6].worldY = 15 * gp.tileSize;
		
		gp.obj[8] = new OBJ_Door();
		gp.obj[8].worldX = 41 * gp.tileSize; // need changing in the future
		gp.obj[8].worldY = 20 * gp.tileSize;
		
		gp.obj[9] = new OBJ_Chest();
		gp.obj[9].worldX = 15 * gp.tileSize; // need changing in the future
		gp.obj[9].worldY = 10 * gp.tileSize;
		
		gp.obj[10] = new OBJ_Chest();
		gp.obj[10].worldX = 5 * gp.tileSize; // need changing in the future
		gp.obj[10].worldY = 35 * gp.tileSize;
		
		gp.obj[11] = new OBJ_Chest();
		gp.obj[11].worldX = 37 * gp.tileSize; // need changing in the future
		gp.obj[11].worldY = 13 * gp.tileSize;
		
		gp.obj[12] = new OBJ_Chest();
		gp.obj[12].worldX = 42 * gp.tileSize; // need changing in the future
		gp.obj[12].worldY = 1 * gp.tileSize;
		
		gp.obj[13] = new OBJ_Chest();
		gp.obj[13].worldX = 46 * gp.tileSize; // need changing in the future
		gp.obj[13].worldY = 43 * gp.tileSize;
		
		gp.obj[14] = new OBJ_Chest();
		gp.obj[14].worldX = 46 * gp.tileSize; // need changing in the future
		gp.obj[14].worldY = 7 * gp.tileSize;
		
		gp.obj[15] = new OBJ_Boots();
		gp.obj[15].worldX = 16 * gp.tileSize; // need changing in the future
		gp.obj[15].worldY = 5 * gp.tileSize;
	}
	
	public void setNPC() {
		
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 10;
		gp.npc[0].worldY = gp.tileSize * 5;
	}
	public void setMonster() {
		
		gp.monster[0] = new MON_GreenSlime(gp);
		gp.monster[0].worldX = gp.tileSize * 3;
		gp.monster[0].worldY = gp.tileSize * 28;
		
		
	}
}
