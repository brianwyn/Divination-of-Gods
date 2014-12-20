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
	
	public enum RockData {
		CLAY(new int[] { 2109, 10949 }, 1, 5, 3, 5, 420),
		COPPER(new int[] {2090, 2091, 11936, 11938, 31080, 31081 }, 1, 18, 3, 5, 436),
		TIN(new int[] { 2094, 2095, 11933, 31077, 31078, 31079 }, 1, 18, 3, 5, 438),
		IRON(new int[] { 2092, 2093, 11954, 31071, 31072, 31073 }, 15, 35, 8, 20, 440),
		COAL(new int[] { 2096, 2097, 3181, 10948, 11963, 11964, 11965, 31070, 31068 }, 30, 50, 50, 70, 453),
		GOLD(new int[] { 2098, 2099, 11951, 31065, 31066 }, 40, 65, 100, 50, 444),
		SILVER(new int[] { 2100, 2101 }, 20, 40, 100, 50, 442),
		MITHRIL(new int[] { 2102, 2103, 11942, 11943, 11944, 11945, 11946, 11947, 31086, 31088, 31069 }, 55, 80, 200, 80, 447),
		ADAMANTITE(new int[] { 2104, 2105, 11939, 11941, 31083, 31085 }, 70, 95, 400, 90, 449),
		RUNITE(new int[] {2106, 2107, 14859 }, 85, 125, 1300, 100, 451),
		ESSENCE(new int[] { 2491 },15, 15, 0, 7, 7936),
		GRANITE(new int[] { 10947 }, 45, 50, 6, 12, 6979),
		SANDSTONE(new int[] { 10946 }, 35, 30, 6, 39, 6985);

		private int[] objectId;
		private int levelReq, exp, timer, delay, itemToGive;

		RockData(int[] objectId, int levelReq, int exp, int timer, int delay,
				int itemToGive) {
			this.objectId = objectId;
			this.levelReq = levelReq;
			this.exp = exp;
			this.timer = timer;
			this.delay = delay;
			this.itemToGive = itemToGive;
		}

		public int[] getObjectId() {
			return objectId;
		}

		public int getLevelReq() {
			return levelReq;
		}

		public int getTimer() {
			return timer;
		}

		public int getDelay() {
			return delay;
		}

		public int getExp() {
			return exp;
		}

		public int getItemToGive() {
			return itemToGive;
		}
	}

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

	private RockData getRock(int objectId) {
		for(RockData rock : RockData.values())
			for(int object : rock.getObjectId())
				if(objectId == object)
					return rock;
		return null;
	}

	public void startMining(final int x, final int y, final int clickType,
			final int objectId) {
		RockData rock = getRock(objectId);
		if (c == null || rock == null) {
			return;
		}
		if (c.isMining)
			return;
		if (c.mining)
			return;
		final int miningLevel = c.playerLevel[Player.playerMining];
		currentPick = -1;
		c.turnPlayerTo(x, y);
		if (rock.getLevelReq() > miningLevel) {
			c.sendMessage("You need a Mining level of " + rock.getLevelReq()
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
					c.getItems().addItem(rock.getItemToGive(), 1);
					if (lastGem == 0 && Misc.random(10) == 9) {
						handleGems(c);
					}
					if (rock.getItemToGive() == 440 && !c.task1[2]) {
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
					if (rock.getItemToGive() == 451 && !c.task3[2]
							&& c.rocksM >= 150) {
						GabbesAchievements.handleEliteTask(c, 2, 3,
								"Mine 150 Runite ores!");
					}
					c.getPA().addSkillXP(
							rock.getExp() * Config.MINING_EXPERIENCE * 2,
							Player.playerMining);
					c.stopPlayerSkill = true;
				}
				if (c.getItems().freeSlots() < 1) {
					c.sendMessage("You have ran out of inventory slots.");
					container.stop();
				}
				mineRock(c, Timers.getMiningRespawnTimer(objectId), x, y, clickType,
						objectId);
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
		}, Timers.getMiningTimer(objectId, pickAxe, miningLevel));
	}

}