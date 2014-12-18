package server.model.players.content.skills;

import server.Config;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.model.players.PlayerHandler;

public class SkillHandler {

	public static final int WOODCUTTING_XP = Config.WOODCUTTING_EXPERIENCE;
	public static final int FIREMAKING_XP = Config.FIREMAKING_EXPERIENCE;
	public static final int COOKING_XP = Config.COOKING_EXPERIENCE;
	public static final int MINING_XP = Config.MINING_EXPERIENCE;
	public static final int FLETCHING_XP = Config.FLETCHING_EXPERIENCE;

	public static final boolean view190 = true;
	public static boolean[] isSkilling = new boolean[25];

	public static long lastSkillingAction;

	public static void deleteTime(Client c) {
		c.doAmount--;
	}

	public static boolean hasRequiredLevel(final Client c, int id, int lvlReq,
			String skill, String event) {
		if (c.playerLevel[id] < lvlReq) {
			c.sendMessage("You dont't have a high enough " + skill
					+ " level to " + event + "");
			c.sendMessage("You at least need the " + skill + " level of "
					+ lvlReq + ".");
			c.getPA().sendStatement(
					"You haven't got high enough " + skill + " level to "
							+ event + "!");
			return false;
		}
		return true;
	}

	public static boolean noInventorySpace(Client c, String skill) {
		if (c.getItems().freeSlots() == 0) {
			c.sendMessage("You haven't got enough inventory space to continue "
					+ skill + "!");
			c.getPA().sendStatement(
					"You haven't got enough inventory space to continue "
							+ skill + "!");
			return false;
		}
		return true;
	}

	public static void resetPlayerSkillVariables(Client c) {// Used to reduce
															// lagg for walking
		if (c.isMining && c.isWalking) {
			CycleEventHandler.getSingleton().stopEvents(c, 145); // let's stop
																	// the event
																	// .
			c.stopPlayerSkill = false;
			return;
		}
		if (c.stopPlayerSkill == true && c.playerSkilling[7] == true) {
			Cooking.resetCooking(c);
		}
		Client o = (Client) PlayerHandler.players[c.duelingWith];
		if (c.openDuel) {
			if (o != null) {
				o.openDuel = false;
				o.stopPlayerSkill = false;
				o.getPA().closeAllWindows();
				o.sendMessage("Anti-Dupe, All Windows Closed.");
			}
			if (c != null) {
				c.getPA().closeAllWindows();
				c.sendMessage("Anti-Dupe, All Windows Closed.");
				c.openDuel = false;
				c.stopPlayerSkill = false;
			}
		}
		if (c.isShopping == true) {
			c.isShopping = false;
			c.stopPlayerSkill = false;
			c.getPA().closeAllWindows();
			return;
		}
		if (c.isBanking) {
			c.getPA().closeAllWindows();
			return;
		}
		if (c.sit == true) {
			c.sit = false;
			c.stopPlayerSkill = false;
			c.startAnimation(4191);
		}
		if (c.isNpc == true) {
			c.isNpc = false;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.stopPlayerSkill = false;
			return;
		}
		if (c.playerIsWoodcutting) {
			Woodcutting.resetWoodcutting(c);
			c.stopPlayerSkill = false;
		}
		if (c.playerIsMining) {
			for (int i = 0; i < 2; i++) {
				c.miningProp[i] = -1;
				c.stopPlayerSkill = false;
			}
		}
		if (c.playerIsCooking) {
			for (int i2 = 0; i2 < 6; i2++) {
				c.cookingProp[i2] = -1;
				c.stopPlayerSkill = false;
			}
		}
		if (c.isWalkingAgilityRope == true && c.absX == 2483) {
			c.isWalkingAgilityRope = false;
			c.stopPlayerSkill = false;
			c.startAnimation(65535);
			c.isRunning = true;
			c.isRunning2 = true;
		} else {
			if (c.isWalkingAgilityRope == true && c.absX != 2483) {
				// c.sendMessage("You haven't finnished walking the object yet!");
				return;
			}
		}
		// c.getMining().resetMining();
		if (c.fishing == true || c.isCooking == true
				|| c.playerIsFiremaking == true) {
			c.fishing = false;
			c.isCooking = false;
			c.playerIsFiremaking = false;
			c.stopPlayerSkill = false;
		}
		if (c.isGoingThroughPipe == true && c.absY == 3437) {
			c.isGoingThroughPipe = false;
			c.stopPlayerSkill = false;
			c.startAnimation(65535);
			c.isRunning = true;
			c.isRunning2 = true;
		} else {
			if (c.isGoingThroughPipe == true && c.absY != 3437) {
				// c.sendMessage("You haven't finnished walking through the object yet!");
				return;
			}
		}
		if (c.isGoingOverPlank == true && c.absX == 2532) {
			c.isGoingOverPlank = false;
			c.stopPlayerSkill = false;
			c.startAnimation(65535);
			c.isRunning = true;
			c.isRunning2 = true;
		} else {
			if (c.isGoingOverPlank == true && c.absX != 2532) {
				// c.sendMessage("You haven't finnished walking through the object yet!");
				return;
			}
		}
		if (c.isDoingBarbLog == true && c.absX == 2541) {
			c.isDoingBarbLog = false;
			c.startAnimation(65535);
			c.stopPlayerSkill = false;
			c.isRunning = true;
			c.isRunning2 = true;
		} else {
			if (c.isDoingBarbLog == true && c.absX != 2541) {
				// c.sendMessage("You haven't finnished walking over the object yet!");
				return;
			}
		}
		if (c.isTalkingWithDungMaster == true) {
			c.isTalkingWithDungMaster = false;
			c.stopPlayerSkill = false;
		}
		if (c.isWalkingAgilityLog == true && c.absY == 3429) {
			c.isWalkingAgilityLog = false;
			c.stopPlayerSkill = false;
			c.startAnimation(65535);
			c.isRunning = true;
			c.isRunning2 = true;
		} else {
			if (c.isWalkingAgilityLog == true && c.absY != 3429) {
				// c.sendMessage("You haven't finnished walking the object yet!");
				return;

			}
		}
	}
}