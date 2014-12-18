package server.model.players.content.skills;

import server.Config;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.GabbesAchievements;
import server.util.Misc;

/**
 * Thieving.java
 * 
 * @author Balla_
 * 
 **/

public class Thieving {

	public static void appendHit(int damage, Client c) {
		PlayerHandler.players[c.playerId].setHitDiff(damage);
		PlayerHandler.players[c.playerId].playerLevel[3] -= damage;
		c.getPA().refreshSkill(3);
		c.random = 4;
		PlayerHandler.players[c.playerId].setHitUpdateRequired(true);
		PlayerHandler.players[c.playerId].updateRequired = true;
	}

	private Client c;

	// npc, level, exp, coin amount
	public int[][] npcThieving = { { 1, 1, 8, 200, 1 }, { 2, 1, 8, 200, 1 },
			{ 18, 25, 26, 500, 1 }, { 9, 40, 47, 1000, 2 },
			{ 26, 55, 85, 1500, 3 }, { 20, 70, 152, 2000, 4 },
			{ 21, 80, 273, 3000, 5 } };

	public Thieving(Client c) {
		this.c = c;
	}

	public void stealFromNPC(int id) {
		if (System.currentTimeMillis() - c.lastThieve < 2000)
			return;
		for (int j = 0; j < npcThieving.length; j++) {
			if (npcThieving[j][0] == id) {
				if (c.playerLevel[Player.playerThieving] >= npcThieving[j][1]) {
					if (Misc.random(c.playerLevel[Player.playerThieving] + 2
							- npcThieving[j][1]) != 1) {
						c.getPA().addSkillXP(
								npcThieving[j][2] * Config.THIEVING_EXPERIENCE,
								Player.playerThieving);
						c.getItems().addItem(995, npcThieving[j][3]);
						c.random = 4;
						c.startAnimation(881);
						c.lastThieve = System.currentTimeMillis();
						c.sendMessage("You thieve some money...");
						break;
					} else {
						c.setHitDiff(npcThieving[j][4]);
						c.setHitUpdateRequired(true);
						c.playerLevel[3] -= npcThieving[j][4];
						c.getPA().refreshSkill(3);
						c.lastThieve = System.currentTimeMillis() + 2000;
						c.sendMessage("You fail to thieve the NPC.");
						c.random = 4;
						break;
					}
				} else {
					c.sendMessage("You need a thieving level of "
							+ npcThieving[j][1] + " to thieve from this NPC.");
				}
			}
		}
	}

	public void stealFromStall(int id, int xp, int level) {
		if (System.currentTimeMillis() - c.lastThieve < 2500)
			return;
		if (c.playerLevel[Player.playerThieving] >= level) {
			if (Misc.random(12) == 1) {
				c.sendMessage("You get caught trying to thieve the stall..");
				c.startAnimation(3679);
				if (c.playerLevel[3] <= 30) {
					appendHit(Misc.random(4), c);
				} else {
					appendHit(Misc.random(10), c);
					return;
				}
			}
			c.sendMessage("You attempt to steal something from the stall...");
			c.random = 4;
			c.getItems().addItem(id, 1);
			c.startAnimation(832);
			if (id == 1625 && !c.task1[1]) {
				c.task1[1] = true;
				c.sendMessage("You've completed the task: Steal an Opal!");
				c.TPoints += 1;
				if (c.TPoints == 1) {
					c.sendMessage("You've received a Task Point! You now have "
							+ c.TPoints + " point!");
				} else {
					c.sendMessage("You've received a Task Point! You now have a total of "
							+ c.TPoints + " points!");
				}
				c.achievementInterface("Steal an Opal!");
			}

			if (id == 1613) {
				c.redTop += 1;
				if (!c.task3[7] && c.redTop >= 150)
					GabbesAchievements.handleEliteTask(c, 7, 3,
							"Steal 150 Red Topaz!");

			}
			// c.getItems().addItem(995, c.playerLevel[c.playerThieving] * 150);
			c.getPA().addSkillXP(xp * Config.THIEVING_EXPERIENCE,
					Player.playerThieving);
			c.lastThieve = System.currentTimeMillis();
			c.sendMessage("You steal a "
					+ server.model.items.Item.getItemName(id) + ".");
		} else if (c.playerLevel[17] < level) {
			c.sendMessage("You need a theiving level of " + level
					+ " to theif from this stall.");
		}
	}

}