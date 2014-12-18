package server.model.minigames;

import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.util.Misc;

/**
 * @author Gabbe
 */

// #1 Conventions :D?

public class DominionTowerByGabbe {
	public static void handleInterfacesButtons(Client c, int packetType,
			int packetSize) { // Clickingbuttons
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		handleShopButtons(c, packetType, packetSize);
		switch (actionButtonId) {
		case 180025:
			c.getPA().showInterface(48300);
			break;
		case 180029:
			c.getPA().showInterface(48300);
			break;
		case 154171:// close button in shop
			c.getPA().closeAllWindows();
			break;
		case 154165: // potions & titles button in rewards
			c.getPA().showInterface(17965);
			break;
		case 154168:
			c.getPA().showInterface(17957);
			break;
		case 154162: // special clothing button in rewards
			c.sendMessage("This is currently disabled.");
			// c.getPA().showInterface(17955);
			break;
		case 180022:
			c.getPA().closeAllWindows();
			break;
		case 181168: // not now button in win interface
			c.getPA().closeAllWindows();
			break;
		case 181171: // carry on button in win interface
			c.getPA().showInterface(46100);
			break;
		case 180037: // rewards in the modes interface
			// c.getPA().showInterface(17955);
			c.getPA().showInterface(17965);
			c.sendMessage("You currently have " + c.dominionPoints
					+ " Dominion Tower Points.");
			break;
		case 180033: // easy classes in the modes interface
			c.getPA().showInterface(48300);
			break;
		case 175226: // Not Now button in easy class interface
			c.getPA().closeAllWindows();
			break;
		case 188174:// start dad EASY CLASS
			c.getDH().sendDialogues(4997, 1);
			break;
		case 188177:// start tree spirit EASY CLASS
			c.getDH().sendDialogues(4999, 1);
			break;
		case 188180:// start Tok-mage guy 360 spirit EASY CLASS
			c.getDH().sendDialogues(5001, 1);
			break;
		case 188192:// start sigmund EASY CLASS
			c.getDH().sendDialogues(5003, 1);
			break;
		case 188183:// start treus_dayth EASY CLASS
			c.getDH().sendDialogues(5005, 1);
			break;
		case 188189:// start kendal bear EASY CLASS
			c.getDH().sendDialogues(5007, 1);
			break;
		case 188195:// start kendal bear EASY CLASS
			c.getDH().sendDialogues(5009, 1);
			break;
		}
	}

	public static void handlePointsReward(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				// Send the 'you've defeated a boss' interface showing their
				// points ect
				c.getPA().sendFrame126("" + c.dominionPoints + "", 46503);
				c.getPA().showInterface(46500);
				container.stop();
				if (c.disconnected) {
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 7);
	}

	public static void handleShopButtons(Client c, int packetType,
			int packetSize) { // Clickingbuttons
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		switch (actionButtonId) {
		case 155038:
			if (c.dominionPoints == 300 || c.dominionPoints > 300) {
				c.getItems().addItem(18335, 1);
				c.dominionPoints -= 300;
			} else {
				c.sendMessage("You need atleast 300 Dominion tower points to buy this item.");
			}
			break;
		case 155050:
			if (c.dominionPoints == 55 || c.dominionPoints > 55) {
				c.getItems().addItem(19892, 1);
				c.dominionPoints -= 55;
			} else {
				c.sendMessage("You need atleast 55 Dominion tower points to buy this item.");
			}
			break;
		case 155047:
			if (c.dominionPoints == 400 || c.dominionPoints > 400) {
				c.getItems().addItem(19892, 1);
				c.dominionPoints -= 400;
			} else {
				c.sendMessage("You need atleast 400 Dominion tower points to buy this item.");
			}
			break;
		case 155044:
			if (c.dominionPoints == 200 || c.dominionPoints > 200) {
				c.getItems().addItem(19887, 1);
				c.dominionPoints -= 200;
			} else {
				c.sendMessage("You need atleast 200 Dominion tower points to buy this item.");
			}
			break;
		case 155029:
			if (c.dominionPoints == 200 || c.dominionPoints > 200) {
				c.getItems().addItem(18371, 1);
				c.dominionPoints -= 200;
			} else {
				c.sendMessage("You need atleast 200 Dominion tower points to buy this item.");
			}
			break;
		case 155026:
			if (c.dominionPoints == 200 || c.dominionPoints > 200) {
				c.getItems().addItem(18369, 1);
				c.dominionPoints -= 200;
			} else {
				c.sendMessage("You need atleast 200 Dominion tower points to buy this item.");
			}
			break;
		case 155023:
			if (c.dominionPoints == 100 || c.dominionPoints > 100) {
				c.getItems().addItem(18367, 1);
				c.dominionPoints -= 100;
			} else {
				c.sendMessage("You need atleast 100 Dominion tower points to buy this item.");
			}
			break;
		case 155020:
			if (c.dominionPoints == 100 || c.dominionPoints > 100) {
				c.getItems().addItem(18365, 1);
				c.dominionPoints -= 100;
			} else {
				c.sendMessage("You need atleast 100 Dominion tower points to buy this item.");
			}
			break;
		case 155041:
			if (c.dominionPoints == 200 || c.dominionPoints > 200) {
				c.getItems().addItem(19886, 1);
				c.dominionPoints -= 200;
			} else {
				c.sendMessage("You need atleast 200 Dominion tower points to buy this item.");
			}
			break;
		case 155035:
			if (c.dominionPoints == 275 || c.dominionPoints > 275) {
				c.getItems().addItem(18334, 1);
				c.dominionPoints -= 275;
			} else {
				c.sendMessage("You need atleast 275 Dominion tower points to buy this item.");
			}
			break;
		case 155032:
			if (c.dominionPoints == 250 || c.dominionPoints > 250) {
				c.getItems().addItem(18333, 1);
				c.dominionPoints -= 250;
			} else {
				c.sendMessage("You need atleast 250 Dominion tower points to buy this item.");
			}
			break;
		case 155053:
			if (c.dominionPoints == 300 || c.dominionPoints > 300) {
				c.getItems().addItem(18341, 1);
				c.dominionPoints -= 300;
			} else {
				c.sendMessage("You need atleast 300 Dominion tower points to buy this item.");
			}
			break;
		case 155056: // blue cape
			if (c.dominionPoints == 35 || c.dominionPoints > 35) {
				c.getItems().addItem(15433, 1);
				c.dominionPoints -= 35;
			} else {
				c.sendMessage("You need atleast 35 Dominion tower points to buy this item.");
			}
			break;
		case 155059: // red cape
			if (c.dominionPoints == 35 || c.dominionPoints > 35) {
				c.getItems().addItem(15432, 1);
				c.dominionPoints -= 35;
			} else {
				c.sendMessage("You need atleast 35 Dominion tower points to buy this item.");
			}
			break;
		case 154177:
			if (c.dominionPoints == 5 || c.dominionPoints > 5) {
				c.getItems().addItem(15308, 1);
				c.dominionPoints -= 5;
			} else {
				c.sendMessage("You need atleast 5 Dominion tower points to buy this item.");
			}
			break;
		case 154180:
			if (c.dominionPoints == 5 || c.dominionPoints > 5) {
				c.getItems().addItem(15312, 1);
				c.dominionPoints -= 5;
			} else {
				c.sendMessage("You need atleast 5 Dominion tower points to buy this item.");
			}
			break;
		case 154183:
			if (c.dominionPoints == 5 || c.dominionPoints > 5) {
				c.getItems().addItem(15316, 1);
				c.dominionPoints -= 5;
			} else {
				c.sendMessage("You need atleast 5 Dominion tower points to buy this item.");
			}
			break;
		case 154186:
			if (c.dominionPoints == 5 || c.dominionPoints > 5) {
				c.getItems().addItem(15320, 1);
				c.dominionPoints -= 5;
			} else {
				c.sendMessage("You need atleast 5 Dominion tower points to buy this item.");
			}
			break;
		case 154189:
			if (c.dominionPoints == 5 || c.dominionPoints > 5) {
				c.getItems().addItem(15324, 1);
				c.dominionPoints -= 5;
			} else {
				c.sendMessage("You need atleast 5 Dominion tower points to buy this item.");
			}
			break;
		case 155133:
			c.sendMessage("This item is currently unavailable.");
			break;
		case 155136:
			if (c.dominionPoints == 12 || c.dominionPoints > 12) {
				c.getItems().addItem(2438, 1);
				c.dominionPoints -= 12;
			} else {
				c.sendMessage("You need atleast 12 Dominion tower points to buy this item.");
			}
			break;
		case 154192:
			if (c.dominionPoints == 300 || c.dominionPoints > 300) {
				c.playerTitle = 35;
				c.dominionPoints -= 300;
				c.sendMessage("You've purchased a title. Please relog for it to show.");
			} else {
				c.sendMessage("You need atleast 300 Dominion tower points to buy this item.");
			}
			break;
		case 154195:
			if (c.dominionPoints == 300 || c.dominionPoints > 300) {
				c.playerTitle = 36;
				c.dominionPoints -= 300;
				c.sendMessage("You've purchased a title. Please relog for it to show.");
			} else {
				c.sendMessage("You need atleast 300 Dominion tower points to buy this item.");
			}
			break;
		case 154198:
			if (c.dominionPoints == 500 || c.dominionPoints > 500) {
				c.playerTitle = 37;
				c.dominionPoints -= 500;
				c.sendMessage("You've purchased a title. Please relog for it to show.");
			} else {
				c.sendMessage("You need atleast 500 Dominion tower points to buy this item.");
			}
			break;
		case 154102: // Blue cap hat
			if (c.dominionPoints == 40 || c.dominionPoints > 40) {
				c.getItems().addItem(15021, 1);
				c.dominionPoints -= 40;
			} else {
				c.sendMessage("You need atleast 40 Dominion tower points to buy this item.");
			}
			break;
		case 154105: // Blue cap top
			if (c.dominionPoints == 100 || c.dominionPoints > 100) {
				c.getItems().addItem(15022, 1);
				c.dominionPoints -= 100;
			} else {
				c.sendMessage("You need atleast 100 Dominion tower points to buy this item.");
			}
			break;
		case 154108: // Blue cap legs
			if (c.dominionPoints == 80 || c.dominionPoints > 80) {
				c.getItems().addItem(15023, 1);
				c.dominionPoints -= 80;
			} else {
				c.sendMessage("You need atleast 80 Dominion tower points to buy this item.");
			}
			break;
		case 154111: // Blue cap gloves
			if (c.dominionPoints == 40 || c.dominionPoints > 40) {
				c.getItems().addItem(15026, 1);
				c.dominionPoints -= 40;
			} else {
				c.sendMessage("You need atleast 40 Dominion tower points to buy this item.");
			}
			break;
		case 154114: // Blue cap boots
			if (c.dominionPoints == 50 || c.dominionPoints > 50) {
				c.getItems().addItem(15025, 1);
				c.dominionPoints -= 50;
			} else {
				c.sendMessage("You need atleast 50 Dominion tower points to buy this item.");
			}
			break;
		case 154117: // Green commander hat
			if (c.dominionPoints == 40 || c.dominionPoints > 40) {
				c.getItems().addItem(15027, 1);
				c.dominionPoints -= 40;
			} else {
				c.sendMessage("You need atleast 40 Dominion tower points to buy this item.");
			}
			break;
		case 154120: // Green commander top
			if (c.dominionPoints == 100 || c.dominionPoints > 100) {
				c.getItems().addItem(15028, 1);
				c.dominionPoints -= 100;
			} else {
				c.sendMessage("You need atleast 100 Dominion tower points to buy this item.");
			}
			break;
		case 154123: // Green commander legs
			if (c.dominionPoints == 80 || c.dominionPoints > 80) {
				c.getItems().addItem(15029, 1);
				c.dominionPoints -= 80;
			} else {
				c.sendMessage("You need atleast 80 Dominion tower points to buy this item.");
			}
			break;
		case 154126: // Green commander gloves
			if (c.dominionPoints == 40 || c.dominionPoints > 40) {
				c.getItems().addItem(15032, 1);
				c.dominionPoints -= 40;
			} else {
				c.sendMessage("You need atleast 40 Dominion tower points to buy this item.");
			}
			break;
		case 154129: // Green commander boots
			if (c.dominionPoints == 50 || c.dominionPoints > 50) {
				c.getItems().addItem(15031, 1);
				c.dominionPoints -= 50;
			} else {
				c.sendMessage("You need atleast 50 Dominion tower points to buy this item.");
			}
			break;
		case 154132: // Blue-green war-chief hat
			if (c.dominionPoints == 40 || c.dominionPoints > 40) {
				c.getItems().addItem(15033, 1);
				c.dominionPoints -= 40;
			} else {
				c.sendMessage("You need atleast 40 Dominion tower points to buy this item.");
			}
			break;
		case 154135: // Blue-green war-chief top
			if (c.dominionPoints == 100 || c.dominionPoints > 100) {
				c.getItems().addItem(15034, 1);
				c.dominionPoints -= 100;
			} else {
				c.sendMessage("You need atleast 100 Dominion tower points to buy this item.");
			}
			break;
		case 154138: // Blue-green war-chief legs
			if (c.dominionPoints == 80 || c.dominionPoints > 80) {
				c.getItems().addItem(15035, 1);
				c.dominionPoints -= 80;
			} else {
				c.sendMessage("You need atleast 80 Dominion tower points to buy this item.");
			}
			break;
		case 154141: // Blue-green war-chief gloves
			if (c.dominionPoints == 40 || c.dominionPoints > 40) {
				c.getItems().addItem(15038, 1);
				c.dominionPoints -= 40;
			} else {
				c.sendMessage("You need atleast 40 Dominion tower points to buy this item.");
			}
			break;
		case 154144: // Blue-green war-chief boots
			if (c.dominionPoints == 50 || c.dominionPoints > 50) {
				c.getItems().addItem(15037, 1);
				c.dominionPoints -= 50;
			} else {
				c.sendMessage("You need atleast 50 Dominion tower points to buy this item.");
			}
			break;
		//
		case 154147: // red hat
			if (c.dominionPoints == 40 || c.dominionPoints > 40) {
				c.getItems().addItem(15039, 1);
				c.dominionPoints -= 40;
			} else {
				c.sendMessage("You need atleast 40 Dominion tower points to buy this item.");
			}
			break;
		case 154150: // red top
			if (c.dominionPoints == 100 || c.dominionPoints > 100) {
				c.getItems().addItem(15040, 1);
				c.dominionPoints -= 100;
			} else {
				c.sendMessage("You need atleast 100 Dominion tower points to buy this item.");
			}
			break;
		case 154153: // red legs
			if (c.dominionPoints == 80 || c.dominionPoints > 80) {
				c.getItems().addItem(15041, 1);
				c.dominionPoints -= 80;
			} else {
				c.sendMessage("You need atleast 80 Dominion tower points to buy this item.");
			}
			break;
		case 154156: // red gloves
			if (c.dominionPoints == 40 || c.dominionPoints > 40) {
				c.getItems().addItem(15044, 1);
				c.dominionPoints -= 40;
			} else {
				c.sendMessage("You need atleast 40 Dominion tower points to buy this item.");
			}
			break;
		case 154159: // red boots
			if (c.dominionPoints == 50 || c.dominionPoints > 50) {
				c.getItems().addItem(15043, 1);
				c.dominionPoints -= 50;
			} else {
				c.sendMessage("You need atleast 50 Dominion tower points to buy this item.");
			}
			break;

		}
	}

	public static void startDominionTowerWave(final Client c, final int npcId,
			final int x, final int y) {
		if (npcId == 1125) { // Dad
			c.getPA().closeAllWindows();
			c.getPA().startTeleport(2329, 9899, c.playerId * 4, "modern");
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {

					// Client c, int npcType, int x, int y, int heightLevel, int
					// WalkingType, int HP, int maxHit, int attack, int defence,
					// boolean attackPlayer, boolean headIcon
					Server.npcHandler.spawnNpc(c, npcId, x, y, c.playerId * 4,
							1, 200, 34, 300, 400, true, false);
					c.getCombat().resetPrayers();
					container.stop();
					if (!c.inDominionTower()) {
						container.stop();
					}
					if (c.disconnected) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 7);
		}
		if (npcId == 655) { // Tree spirit
			c.getPA().closeAllWindows();
			c.getPA().startTeleport(2329, 9899, c.playerId * 4, "modern");
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					// Client c, int npcType, int x, int y, int heightLevel, int
					// WalkingType, int HP, int maxHit, int attack, int defence,
					// boolean attackPlayer, boolean headIcon
					Server.npcHandler.spawnNpc(c, npcId, x, y, c.playerId * 4,
							1, 200, 34, 350, 400, true, false);
					c.getCombat().resetPrayers();
					container.stop();
					if (c.disconnected) {
						container.stop();
					}
					if (!c.inDominionTower()) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 7);
		}
		if (npcId == 1813) { // Kendalbear
			c.getPA().closeAllWindows();
			c.getPA().startTeleport(2329, 9899, c.playerId * 4, "modern");
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					// Client c, int npcType, int x, int y, int heightLevel, int
					// WalkingType, int HP, int maxHit, int attack, int defence,
					// boolean attackPlayer, boolean headIcon
					Server.npcHandler.spawnNpc(c, npcId, x, y, c.playerId * 4,
							1, 200, 34, 350, 400, true, false);
					c.getCombat().resetPrayers();
					container.stop();
					if (c.disconnected) {
						container.stop();
					}
					if (!c.inDominionTower()) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 7);
		}
		if (npcId == 753) { // melzar mad man
			c.getPA().closeAllWindows();
			c.getPA().startTeleport(2329, 9899, c.playerId * 4, "modern");
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					// Client c, int npcType, int x, int y, int heightLevel, int
					// WalkingType, int HP, int maxHit, int attack, int defence,
					// boolean attackPlayer, boolean headIcon
					Server.npcHandler.spawnNpc(c, npcId, x, y, c.playerId * 4,
							1, 200, 34, 350, 400, true, false);
					c.getCombat().resetPrayers();
					container.stop();
					if (c.disconnected) {
						container.stop();
					}
					if (!c.inDominionTower()) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 7);
		}
		if (npcId == 2743) { // Tok thing
			c.getPA().closeAllWindows();
			c.getPA().startTeleport(2329, 9899, c.playerId * 4, "modern");
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					// Client c, int npcType, int x, int y, int heightLevel, int
					// WalkingType, int HP, int maxHit, int attack, int defence,
					// boolean attackPlayer, boolean headIcon
					Server.npcHandler.spawnNpc(c, npcId, x, y, c.playerId * 4,
							1, 200, 34, 400, 400, true, false);
					c.getCombat().resetPrayers();
					container.stop();
					if (c.disconnected) {
						container.stop();
					}
					if (!c.inDominionTower()) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 7);
		}
		if (npcId == 2083) { // sigmund
			c.getPA().closeAllWindows();
			c.getPA().startTeleport(2329, 9899, c.playerId * 4, "modern");
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					// Client c, int npcType, int x, int y, int heightLevel, int
					// WalkingType, int HP, int maxHit, int attack, int defence,
					// boolean attackPlayer, boolean headIcon
					Server.npcHandler.spawnNpc(c, npcId, x, y, c.playerId * 4,
							1, 200, 34, 320, 400, true, false);
					c.getCombat().resetPrayers();
					container.stop();
					if (c.disconnected) {
						container.stop();
					}
					if (!c.inDominionTower()) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 7);
		}
		if (npcId == 1540) {// Treus_Dayth
			c.getPA().closeAllWindows();
			c.getPA().startTeleport(2329, 9899, c.playerId * 4, "modern");
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					// Client c, int npcType, int x, int y, int heightLevel, int
					// WalkingType, int HP, int maxHit, int attack, int defence,
					// boolean attackPlayer, boolean headIcon
					Server.npcHandler.spawnNpc(c, npcId, x, y, c.playerId * 4,
							1, 200, 34, 320, 400, true, false);
					c.getCombat().resetPrayers();
					container.stop();
					if (c.disconnected) {
						container.stop();
					}
					if (!c.inDominionTower()) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 7);
		}
		c.getCombat().resetPrayers();
	}

}