package server.model.players.content.skills;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.model.players.Player;

/**
 * Handles all ConstructionEvents cba spamming up client class
 * 
 * @author Gabbe; gabbe_impactpk@live.com
 * 
 **/

public class ConstructionEvents {
	static int conSkill = 23;

	public static void conAle(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 28) {
			c.sendMessage("You need a level 29 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 28) {
					c.sendMessage("You need a level 29 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 15)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.sendMessage("You build a Greenman's ale.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13571, c.absX, c.absY, 1, 10);
					c.getPA().addSkillXP(3000, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 steel nails and 2 oak planks.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void conAle2(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 28) {
			c.sendMessage("You need a level 29 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 28) {
					c.sendMessage("You need a level 29 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 15)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.sendMessage("You build a Greenman's ale.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13571, c.absX, c.absY, 2, 10);
					c.getPA().addSkillXP(3000, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 steel nails and 2 oak planks.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void conAle3(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 28) {
			c.sendMessage("You need a level 29 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 28) {
					c.sendMessage("You need a level 29 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 15)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.sendMessage("You build a Greenman's ale.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13571, c.absX, c.absY, 3, 10);
					c.getPA().addSkillXP(3000, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 steel nails and 2 oak planks.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void conAle4(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 28) {
			c.sendMessage("You need a level 29 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 28) {
					c.sendMessage("You need a level 29 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 15)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.sendMessage("You build a Greenman's ale.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13571, c.absX, c.absY, 4, 10);
					c.getPA().addSkillXP(3000, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 steel nails and 2 oak planks.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void conBook(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 25) {
			c.sendMessage("You need a level 26 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 25) {
					c.sendMessage("You need a level 26 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 15)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.sendMessage("You build a Bookcase.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13598, c.absX, c.absY, 1, 10);
					c.getPA().addSkillXP(2500, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 steel nails and 3 oak planks.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void conBook2(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 25) {
			c.sendMessage("You need a level 26 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 25) {
					c.sendMessage("You need a level 26 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 15)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.sendMessage("You build a Bookcase.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13598, c.absX, c.absY, 2, 10);
					c.getPA().addSkillXP(2500, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 steel nails and 3 oak planks.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void conBook3(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 25) {
			c.sendMessage("You need a level 26 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 25) {
					c.sendMessage("You need a level 26 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 15)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.sendMessage("You build a Bookcase.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13598, c.absX, c.absY, 3, 10);
					c.getPA().addSkillXP(2500, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 steel nails and 3 oak planks.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void conBook4(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 25) {
			c.sendMessage("You need a level 26 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 25) {
					c.sendMessage("You need a level 26 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 15)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.sendMessage("You build a Bookcase.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13598, c.absX, c.absY, 4, 10);
					c.getPA().addSkillXP(2500, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 steel nails and 3 oak planks.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	/**
	 *** Chairs /
	 **/
	public static void conChair(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 19) {
			c.sendMessage("You need a level 19 Construction to do that.");
			return;
		}
		c.sendMessage("Starting event...Hold on.");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 19) {
					c.sendMessage("You need a level 19 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 10)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 10);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.startAnimation(898);
					c.sendMessage("You build a Chair.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(1200, 23);
					c.getPA().checkObjectSpawn(13584, c.absX, c.absY, 1, 10);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need a hammer, 2 Oak Planks and 10 steel nails!");
					container.stop();
				}
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public static void conChair2(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 19) {
			c.sendMessage("You need a level 19 Construction to do that.");
			return;
		}
		c.sendMessage("Starting event...Hold on.");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 19) {
					c.sendMessage("You need a level 19 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 10)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 10);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.startAnimation(898);
					c.sendMessage("You build a Chair.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(1200, 23);
					c.getPA().checkObjectSpawn(13584, c.absX, c.absY, 2, 10);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need a hammer, 2 Oak Planks and 10 steel nails!");
					container.stop();
				}
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public static void conChair3(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 19) {
			c.sendMessage("You need a level 19 Construction to do that.");
			return;
		}
		c.sendMessage("Starting event...Hold on.");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 19) {
					c.sendMessage("You need a level 19 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 10)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 10);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.startAnimation(898);
					c.sendMessage("You build a Chair.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(1200, 23);
					c.getPA().checkObjectSpawn(13584, c.absX, c.absY, 3, 10);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need a hammer, 2 Oak Planks and 10 steel nails!");
					container.stop();
				}
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public static void conChair4(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 19) {
			c.sendMessage("You need a level 19 Construction to do that.");
			return;
		}
		c.sendMessage("Starting event...Hold on.");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 19) {
					c.sendMessage("You need a level 19 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 10)
						&& c.getItems().playerHasItem(8778, 1)
						&& c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 10);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.startAnimation(898);
					c.sendMessage("You build a Chair.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(1200, 23);
					c.getPA().checkObjectSpawn(13584, c.absX, c.absY, 4, 10);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need a hammer, 2 Oak Planks and 10 steel nails!");
					container.stop();
				}
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	/**
	 **** Fern
	 ***/

	public static void conFern(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 1) {
			c.sendMessage("You need a level 1 Construction to do that.");
			return;
		}
		c.sendMessage("Hold on.. Placing Event-Request..");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.getItems().playerHasItem(249, 1)
						&& c.getItems().playerHasItem(1511, 1)) {
					c.getItems().deleteItem2(249, 1);
					c.getItems().deleteItem2(1511, 1);
					c.sendMessage("You build a Fern.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.buryDelay = System.currentTimeMillis();
					c.getPA().addSkillXP(180, 23);
					container.stop();
					c.getPA().checkObjectSpawn(13432, c.absX, c.absY, 1, 10);
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 1 clean Guam and 1 normal log.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	// BOOKCASE

	public static void conFern2(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 1) {
			c.sendMessage("You need a level 1 Construction to do that.");
			return;
		}
		c.sendMessage("Hold on.. Placing Event-Request..");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.getItems().playerHasItem(249, 1)
						&& c.getItems().playerHasItem(1511, 1)) {
					c.getItems().deleteItem2(249, 1);
					c.getItems().deleteItem2(1511, 1);
					c.sendMessage("You build a Fern.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.buryDelay = System.currentTimeMillis();
					c.getPA().addSkillXP(180, 23);
					c.getPA().checkObjectSpawn(13432, c.absX, c.absY, 2, 10);
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 1 clean Guam and 1 normal log.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public static void conFern3(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 1) {
			c.sendMessage("You need a level 1 Construction to do that.");
			return;
		}
		c.sendMessage("Hold on.. Placing Event-Request..");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.getItems().playerHasItem(249, 1)
						&& c.getItems().playerHasItem(1511, 1)) {
					c.getItems().deleteItem2(249, 1);
					c.getItems().deleteItem2(1511, 1);
					c.sendMessage("You build a Fern.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().addSkillXP(180, 23);
					c.getPA().checkObjectSpawn(13432, c.absX, c.absY, 3, 10);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 1 clean Guam and 1 normal log.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public static void conFern4(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 1) {
			c.sendMessage("You need a level 1 Construction to do that.");
			return;
		}
		c.sendMessage("Hold on.. Placing Event-Request..");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.getItems().playerHasItem(249, 1)
						&& c.getItems().playerHasItem(1511, 1)) {
					c.getItems().deleteItem2(249, 1);
					c.getItems().deleteItem2(1511, 1);
					c.sendMessage("You build a Fern.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().addSkillXP(400, 23);
					c.getPA().checkObjectSpawn(13432, c.absX, c.absY, 4, 10);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer and 3 normal logs.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public static void conTree(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 5) {
			c.sendMessage("You need a level 5 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 5) {
					c.sendMessage("You need a level 5 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1511, 1)
						&& c.getItems().playerHasItem(1511, 1)
						&& c.getItems().playerHasItem(1511, 1)) {
					c.getItems().deleteItem2(1511, 1);
					c.getItems().deleteItem2(1511, 1);
					c.getItems().deleteItem2(1511, 1);
					c.sendMessage("You build a Tree.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13411, c.absX, c.absY, 1, 10);
					c.getPA().addSkillXP(400, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer and 3 normal logs.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	// GREENMANS ALE THING

	public static void conTree2(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 5) {
			c.sendMessage("You need a level 5 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 5) {
					c.sendMessage("You need a level 5 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1511, 1)
						&& c.getItems().playerHasItem(1511, 1)
						&& c.getItems().playerHasItem(1511, 1)) {
					c.getItems().deleteItem2(1511, 1);
					c.getItems().deleteItem2(1511, 1);
					c.getItems().deleteItem2(1511, 1);
					c.sendMessage("You build a Tree.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13411, c.absX, c.absY, 2, 10);
					c.getPA().addSkillXP(400, conSkill);
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer and 3 normal logs.");
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				}
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void conTree3(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 5) {
			c.sendMessage("You need a level 5 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 5) {
					c.sendMessage("You need a level 5 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1511, 1)
						&& c.getItems().playerHasItem(1511, 1)
						&& c.getItems().playerHasItem(1511, 1)) {
					c.getItems().deleteItem2(1511, 1);
					c.getItems().deleteItem2(1511, 1);
					c.getItems().deleteItem2(1511, 1);
					c.sendMessage("You build a Tree.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13411, c.absX, c.absY, 3, 10);
					c.getPA().addSkillXP(400, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer and 3 normal logs.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void conTree4(final Client c) {
		if (!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if (c.playerLevel[23] < 5) {
			c.sendMessage("You need a level 5 Construction to do that.");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					container.stop();
					return;
				}
				if (c.playerLevel[23] < 5) {
					c.sendMessage("You need a level 5 Construction to do that.");
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(1511, 1)
						&& c.getItems().playerHasItem(1511, 1)
						&& c.getItems().playerHasItem(1511, 1)) {
					c.getItems().deleteItem2(1511, 1);
					c.getItems().deleteItem2(1511, 1);
					c.getItems().deleteItem2(1511, 1);
					c.sendMessage("You build a Tree.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13411, c.absX, c.absY, 4, 10);
					c.getPA().addSkillXP(400, conSkill);
					c.buryDelay = System.currentTimeMillis();
					container.stop();
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer and 3 normal logs.");
					container.stop();
				}
				container.stop();
				if (c.disconnected) {
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public static void MakeIronNails(final Client c) { // creates nails - works
														// like boss, // Gabbe
		if (!c.getItems().playerHasItem(2353, 1)) {
			c.sendMessage("You do not have any steel bars to smelt..");
			return;
		}
		if (c.playerLevel[Player.playerSmithing] != 30) {
			c.sendMessage("You need atleast level 30 smithing to smith nails!");
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(2353, 1)) {
					c.sendMessage("You do not have any steel bars to smelt..");
					container.stop();
					return;
				}
				c.turnPlayerTo(c.objectX, c.objectY);
				c.startAnimation(896);
				c.getItems()
						.deleteItem(2353, c.getItems().getItemSlot(2353), 1);
				c.getItems().addItem(1539, 19);
				c.getPA().addSkillXP(200, Player.playerSmithing);
				// c.spinning = true;
				// container.stop();
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

}