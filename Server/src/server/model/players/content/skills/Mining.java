package server.model.players.content.skills;

import server.Config;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.objects.Object;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.GabbesAchievements;
import server.model.players.content.skills.impl.Timers;
import server.util.Misc;

/**
 * @Author Null++
 */

public class Mining {

	private Client c;

	public final int[][] Pick_Settings = { { 1265, 1, 1, 6753 }, // Bronze
			{ 1267, 1, 2, 6754 }, // Iron
			{ 1269, 6, 3, 6755 }, // Steel
			{ 1273, 21, 4, 6757 }, // Mithril
			{ 1271, 31, 5, 6756 }, // Addy
			{ 1275, 41, 6, 6752 }, // Rune
			{ 15259, 61, 7, 12188 }, // Dragon
			{ 13661, 80, 7, 10222 } // Adze
	};

	public final int[][] Rock_Settings = { { 11938, 1, 40, 3, 436 }, // Copper
			{ 2090, 1, 40, 3, 436 }, // Copper
			{ 11933, 1, 40, 3, 438 }, // Tin
			{ 2094, 1, 40, 3, 438 }, // Tin
			{ 2093, 15, 60, 7, 440 }, // Iron
			{ 2092, 15, 60, 7, 440 }, // Iron
			{ 2097, 30, 80, 38, 453 }, // Coal
			{ 2096, 30, 80, 38, 453 }, // Coal
			{ 11963, 30, 80, 38, 453 }, // Coal
			{ 11964, 30, 80, 38, 453 }, // Coal
			{ 11965, 30, 80, 38, 453 }, // Coal
			{ 2100, 20, 40, 78, 442 }, // Silver
			{ 2101, 20, 40, 78, 442 }, // Silver
			{ 2098, 40, 65, 78, 444 }, // Gold
			{ 2099, 40, 65, 78, 444 }, // Gold
			{ 2102, 55, 100, 155, 447 }, // Mithril
			{ 11944, 55, 100, 70, 447 }, // Mithril
			{ 11945, 55, 100, 155, 447 }, // Mithril
			{ 11946, 55, 100, 155, 447 }, // Mithril
			{ 11947, 55, 100, 155, 447 }, // Mithril
			{ 11942, 55, 80, 70, 447 }, // Mithril
			{ 11943, 55, 80, 70, 447 }, // Mithril
			{ 11939, 50, 95, 150, 449 }, // Addy
			{ 2104, 75, 125, 315, 449 }, // Addy
			{ 11941, 75, 125, 150, 449 }, // Addy
			{ 14859, 85, 145, 170, 451 }, // Rune
			{ 2106, 50, 145, 970, 451 }, // Rune
	};

	int currentPick = -1;
	
	public static int gems[] = { 1617, 1615, 1619, 1621, 1625 };

	public static int lastGem = 0;

	public static void handleGems(Client c) {
		int reward = gems[Misc.random(4)];
		if (c.getItems().freeSlots() > 1)
			c.getItems().addItem(reward, 1);
		c.sendMessage("You found a " + c.getItems().getItemName(reward) + "!");
		lastGem = Misc.random(20);
	}

	public Mining(Client c) {
		this.c = c;
	}

	public void mineRock(Client c, int respawnTime, int x, int y, int type,
			int i) {
		if (c == null) {
			return;
		}
		new Object(452, x, y, 0, type, 10, i, respawnTime);
		for (int t = 0; t < PlayerHandler.players.length; t++) {
			if (PlayerHandler.players[t] != null) {
				if (PlayerHandler.players[t].rockX == x
						&& PlayerHandler.players[t].rockY == y) {
					PlayerHandler.players[t].isMining = false;
					PlayerHandler.players[t].startAnimation(65535);
					PlayerHandler.players[t].rockX = 0;
					PlayerHandler.players[t].rockY = 0;
					PlayerHandler.players[t].isWalking = true;

				}
			}
		}
	}

	public void startMining(final int j, final int x, final int y,
			final int type, final int oreType) {
		if (c == null) {
			return;
		}
		if (c.isMining)
			return;
		if (c.mining)
			return;
		final int miningLevel = c.playerLevel[Player.playerMining];
		currentPick = -1;
		c.turnPlayerTo(x, y);
		if (Rock_Settings[j][1] > miningLevel) {
			c.sendMessage("You need a Mining level of " + Rock_Settings[j][1]
					+ " to mine this rock.");
			return;
		}
		for (int i = 0; i < Pick_Settings.length; i++) {
			if (c.getItems().playerHasItem(Pick_Settings[i][0])
					|| c.playerEquipment[Player.playerWeapon] == Pick_Settings[i][0]) {
				if (Pick_Settings[i][1] <= miningLevel) {
					currentPick = i;
				}
			}
		}
		if (currentPick == -1) {
			c.sendMessage("You need a pickaxe to mine this rock.");
			return;
		}
		if (c.getItems().freeSlots() < 1) {
			c.sendMessage("You do not have enough inventory slots to do that.");
			return;
		}
		c.isWalking = false;
		c.stopPlayerSkill = true;
		c.startAnimation(Pick_Settings[currentPick][3]);
		c.isMining = true;
		c.rockX = x;
		c.rockY = y;
		c.mining = true;
		int pickAxe = Timers.handlePickAxe(currentPick);
		if (c.isWalking) {
			return;
		}
		CycleEventHandler.getSingleton().addEvent(145, c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (!c.isMining) {
					container.stop();
					c.startAnimation(65535);
					return;
				}
				if (lastGem > 0) {
					lastGem--;
				}

				if (c.isMining) {
					c.startAnimation(Pick_Settings[currentPick][3]);
					c.getItems().addItem(Rock_Settings[j][4], 1);
					if (lastGem == 0 && Misc.random(10) == 9) {
						handleGems(c);
					}
					if (Rock_Settings[j][4] == 440 && !c.task1[2]) {
						c.task1[2] = true;
						c.sendMessage("You've completed the task: Mine some iron!");
						c.TPoints += 1;
						if (c.TPoints == 1) {
							c.sendMessage("You've received a Task Point! You now have "
									+ c.TPoints + " point!");
						} else {
							c.sendMessage("You've received a Task Point! You now have a total of "
									+ c.TPoints + " points!");
						}
						c.achievementInterface("Mine some iron!");
					}
					c.rocksM += 1;
					if (Rock_Settings[j][4] == 451 && !c.task3[2]
							&& c.rocksM >= 150) {
						GabbesAchievements.handleEliteTask(c, 2, 3,
								"Mine 150 Runite ores!");
					}
					c.getPA().addSkillXP(
							Rock_Settings[j][2] * Config.MINING_EXPERIENCE * 2,
							Player.playerMining);
					c.stopPlayerSkill = true;
				}
				if (c.getItems().freeSlots() < 1) {
					c.sendMessage("You have ran out of inventory slots.");
					container.stop();
				}
				mineRock(c, Timers.getMiningRespawnTimer(oreType), x, y, type,
						oreType);
				c.isMining = false;
				container.stop();
			}

			@Override
			public void stop() {
				c.getPA().closeAllWindows();
				c.startAnimation(65535);
				c.isMining = false;
				c.rockX = 0;
				c.rockY = 0;
				c.mining = false;
				return;
			}
		}, Timers.getMiningTimer(oreType, pickAxe, miningLevel));
	}

}