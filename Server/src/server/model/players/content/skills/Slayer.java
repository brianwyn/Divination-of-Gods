package server.model.players.content.skills;

import server.Server;
import server.model.players.Client;
import server.util.Misc;

/**
 * Slayer.java
 * 
 * @author Sanity - Redone by Gabbe. Fixed crap up a bit, added Pures support.
 * 
 **/

public class Slayer {

	private Client c;

	public Slayer(Client c) {
		this.c = c;
	}

	public void giveTask() {
		int slayerLevel = c.playerLevel[18];
		if (c.combatLevel < 50 || slayerLevel < 42)
			giveTask(1);
		else if (c.combatLevel >= 70 && c.combatLevel <= 105
				&& slayerLevel > 40 && slayerLevel < 62)
			giveTask(2);
		else if (c.combatLevel > 90 && c.combatLevel <= 121 && slayerLevel > 60
				&& slayerLevel < 81)
			giveTask(3);
		else if (c.combatLevel >= 122 && c.combatLevel <= 138
				&& slayerLevel >= 80)
			giveTask(4);
		else if (isAPure() && slayerLevel < 42)
			giveTask(1);
		else if (isAPure() && slayerLevel > 40 && slayerLevel < 70)
			giveTask(2);
		else if (isAPure() && slayerLevel > 60 && slayerLevel < 91)
			giveTask(3);
		else if (isAPure() && slayerLevel >= 90)
			giveTask(4);
		else if (slayerLevel < 40 && c.combatLevel > 90)
			giveTask(2);
		else
			giveTask(2);
	}

	public void giveTask(int taskLevel) {
		if (taskLevel == 1) {
			c.random = (int) (Math.random() * (c.lowTasks.length - 1));
			c.given = c.lowTasks[c.random];
			c.taskType = 1;
			c.hasGivenSlayReward = false;
		} else if (taskLevel == 2) {
			c.random = (int) (Math.random() * (c.medTasks.length - 1));
			c.given = c.medTasks[c.random];
			c.taskType = 2;
			c.hasGivenSlayReward = false;
		} else if (taskLevel == 3) {
			c.random = (int) (Math.random() * (c.highTasks.length - 1));
			c.given = c.highTasks[c.random];
			c.taskType = 3;
			c.hasGivenSlayReward = false;
		} else if (taskLevel == 4) {
			c.random = (int) (Math.random() * (c.eliteTasks.length - 1));
			c.given = c.eliteTasks[c.random];
			c.taskType = 4;
			c.hasGivenSlayReward = false;
		}
		if (taskLevel <= 3) {
			c.slayerTask = c.given;
			c.taskAmount = Misc.random(15) + 15;
			c.sendMessage("You have been assigned to kill " + c.taskAmount
					+ " " + Server.npcHandler.getNpcListName(c.given)
					+ " as a slayer task.");
			c.hasGivenSlayReward = false;
			c.sendMessage("Slayer task streak: " + c.slayerTaskStreak
					+ ". You receive extra points for higher task streaks.");
		} else {
			c.slayerTask = c.given;
			c.taskAmount = Misc.random(5) + 1;
			c.sendMessage("You have been assigned to kill " + c.taskAmount
					+ " " + Server.npcHandler.getNpcListName(c.given)
					+ " as an elite slayer task.");
			c.sendMessage("Slayer task streak: " + c.slayerTaskStreak
					+ ". You receive extra points for higher task streaks.");
			c.hasGivenSlayReward = false;
		}
		c.getPA().calculateTask();
	}

	public void giveTask2() {
		for (int j = 0; j < c.lowTasks.length; j++) {
			if (c.lowTasks[j] == c.slayerTask) {
				c.sendMessage("You already have an easy task... to kill "
						+ c.taskAmount + " "
						+ Server.npcHandler.getNpcListName(c.slayerTask) + ".");
				return;
			}
		}
		giveTask(1);
		c.sendMessage("Your new Slayer Task Streak is now: 0 ");
		c.slayerTaskStreak = 0;
	}

	public boolean isAPure() {
		if (c.playerLevel[1] > 1 && c.playerLevel[1] < 30) {
			return true;
		}
		return false;
	}
}