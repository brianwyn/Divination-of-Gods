package server.model.minigames;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.util.Misc;

/**
 * Treasure Trails
 * 
 * @author Genc - Redone by Gabbe
 * 
 */

public class TreasureTrails {

	public static int lowLevelReward[] = { 7390, 7392, 7394, 7396, 7386, 7388,
			1099, 1135, 1065, 555, 560, 555, 851 };

	public static int mediemLevelReward[] = { 1123, 1161, 1199, 1301, 13661,
			2577, 2579, 9185, 6570, 11730, 10551, 1093, 1027, 1093 };
	public static int highLevelReward[] = { 11696, 1127, 10551, 1185, 1201,
			1275, 1303, 1333, 1359, 1373, 2491, 2497, 2503, 861, 859, 2581,
			2651, 1079, 1093, 1113, 1127, 1147, 1163, 1185, 1201, 1275, 1303,
			19669, 1333, 1359, 1373, 2491, 2497, 2503, 6570, 859, 2581, 2651, };

	public static int lowLevelStacks[] = { 995, 380, 561, 886, 555, 550 };
	public static int mediumLevelStacks[] = { 995, 374, 561, 563, 890, };
	public static int highLevelStacks[] = { 995, 386, 561, 563, 560, 892 };
	public static int allStacks[] = { 995, 380, 561, 886, 374, 561, 563, 890,
			386, 561, 563, 560, 892 };

	public static void addClueReward(Client c, int clueLevel) {
		int chanceReward = Misc.random(2);
		if (c != null) {
			if (c.getItems().freeSlots() > 3 && !c.InDung()
					&& !c.inDungBossRoom()) {
				if (clueLevel == 0) {
					if (chanceReward > -1) {
						switch (chanceReward) {
						case 0:
							displayReward(c, lowLevelReward[Misc.random(12)],
									1, lowLevelReward[Misc.random(12)], 1,
									lowLevelStacks[Misc.random(5)],
									1 + Misc.random(150));
							c.getItems().deleteItem(10233, 1);
							break;
						case 1:
							displayReward(c, lowLevelReward[Misc.random(12)],
									1, lowLevelStacks[Misc.random(5)],
									1 + Misc.random(150), -1, 1);
							c.getItems().deleteItem(10233, 1);
							break;
						case 2:
							displayReward(c, lowLevelReward[Misc.random(12)],
									1, -1, 1, -1, 1);
							c.getItems().deleteItem(10233, 1);
							break;
						}
					}
				} else if (clueLevel == 1) {
					if (chanceReward > -1) {
						switch (chanceReward) {
						case 0:
							displayReward(c,
									mediemLevelReward[Misc.random(10)], 1,
									mediemLevelReward[Misc.random(10)], 1,
									mediumLevelStacks[Misc.random(4)],
									1 + Misc.random(200));
							c.getItems().deleteItem(7318, 1);
							break;
						case 1:
							displayReward(c,
									mediemLevelReward[Misc.random(12)], 1,
									mediumLevelStacks[Misc.random(4)],
									1 + Misc.random(200), -1, 1);
							c.getItems().deleteItem(7318, 1);
							break;
						case 2:
							displayReward(c,
									mediemLevelReward[Misc.random(10)], 1, -1,
									1, -1, 1);
							c.getItems().deleteItem(7318, 1);
							break;
						}
					}
				} else if (clueLevel == 2) {
					if (chanceReward > -1) {
						switch (chanceReward) {
						case 0:
							displayReward(c, highLevelReward[Misc.random(37)],
									1, highLevelReward[Misc.random(37)], 1,
									highLevelStacks[Misc.random(5)],
									1 + Misc.random(350));
							c.getItems().deleteItem(405, 1);
							break;
						case 1:
							displayReward(c, highLevelReward[Misc.random(37)],
									1, highLevelStacks[Misc.random(5)],
									1 + Misc.random(350), -1, 1);
							c.getItems().deleteItem(405, 1);
							break;
						case 2:
							displayReward(c, highLevelReward[Misc.random(37)],
									1, -1, 1, -1, 1);
							c.getItems().deleteItem(405, 1);
							break;
						}
					}
				}
			} else {
				c.sendMessage("You need to have atleast 3 free inventory slots to do this.");
			}
		}
	}

	public static void displayReward(Client c, int item, int amount, int item2,
			int amount2, int item3, int amount3) {
		int[] items = { item, item2, item3 };
		int[] amounts = { amount, amount2, amount3 };
		if (c != null && item > -2 && amount > -2) {
			c.outStream.createFrameVarSizeWord(53);
			c.outStream.writeWord(6963);
			c.outStream.writeWord(items.length);
			for (int i = 0; i < items.length; i++) {
				if (c.playerItemsN[i] > 254) {
					c.outStream.writeByte(255);
					c.outStream.writeDWord_v2(amounts[i]);
				} else {
					c.outStream.writeByte(amounts[i]);
				}
				if (items[i] > 0) {
					c.outStream.writeWordBigEndianA(items[i] + 1);
				} else {
					c.outStream.writeWordBigEndianA(0);
				}
			}
			c.outStream.endFrameVarSizeWord();
			c.flushOutStream();
			c.getPA().showInterface(6960);
			if (c.getItems().freeSlots() > 3 && !c.InDung()
					&& !c.inDungBossRoom()) {
				c.getItems().addItem(item, amount);
				c.getItems().addItem(item2, amount2);
				c.getItems().addItem(item3, amount3);
				return;

			}
		}

	}

	public static void handleDig(final Client c) {
		if (c != null) {
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (c != null)
						c.startAnimation(830);
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 1);
		}
	}
}