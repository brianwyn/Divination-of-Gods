package server.model.players.content.skills.impl;

import server.model.players.Client;
import server.util.Misc;

public class ImpLooting {

	// ITEM ID - MAX REWARD AMOUNT (It will randomize the amount)
	public static int[][] lowRewards = { { 374, 5 }, { 946, 1 }, { 2348, 2 },
			{ 441, 4 }, { 1442, 1 }, { 1746, 5 }, { 1724, 1 }, { 1837, 1 },
			{ 557, 10 }, { 527, 5 }, { 1267, 1 } };

	public static int[][] mediumRewards = { { 12158, 6 }, { 12159, 4 },
			{ 12160, 3 }, { 13469, 1 }, { 1271, 1 }, { 2489, 1 }, { 450, 10 },
			{ 454, 15 }, { 892, 30 }, { 9075, 37 }, { 995, 10000 },
			{ 961, 10 }, { 8779, 10 }, { 11137, 1 } };

	public static int[][] dragonImp = { { 1163, 1 }, { 452, 12 }, { 2364, 8 },
			{ 1275, 1 }, { 1434, 1 }, { 1514, 60 }, { 2497, 1 },
			{ 995, 600000 }, { 11137, 1 } };

	public static int[][] kingImp = { { 15503, 1 }, { 15505, 1 }, { 15509, 1 },
			{ 15507, 1 }, { 15511, 1 }, { 1163, 1 }, { 15503, 1 },
			{ 15505, 1 }, { 15509, 1 }, { 15507, 1 }, { 15511, 1 },
			{ 452, 12 }, { 2364, 8 }, { 1275, 1 }, { 1434, 1 }, { 1514, 60 },
			{ 2497, 1 }, { 995, 600000 }, { 11137, 1 } };

	public static int JarID = 11260;

	public static int[][] seeds = { { 5291, 4 }, { 5292, 4 }, { 5293, 3 },
			{ 5294, 3 }, { 5295, 4 }, { 5296, 3 }, { 5297, 3 }, { 5298, 3 },
			{ 5299, 4 }, { 5300, 2 }, { 5301, 2 }, { 5302, 2 }, { 5303, 1 },
			{ 5304, 1 } };

	public static void lootDragonRewards(Client c, int ID) {
		if (System.currentTimeMillis() - c.buryDelay < 1000) {
			return;
		}
		c.getItems().deleteItem(ID, 1);
		int reward = randomizeDrop(dragonImp);
		int amount = 1;
		// Search the array for the real reward amount
		for (int k = 0; k < dragonImp.length; k++) {
			if (dragonImp[k][0] == reward) {
				amount = dragonImp[k][1];
			}
		}

		if (amount == 1)
			c.getItems().addItem(reward, 1);
		else {
			c.getItems().addItem(reward, Misc.random(amount));
		}

		// if(c.getItems().freeSlots() > 0)
		// c.getItems().addItem(JarID, 1);
		c.buryDelay = System.currentTimeMillis();
	}

	public static void lootKinglyRewards(Client c, int ID) {
		if (System.currentTimeMillis() - c.buryDelay < 1000) {
			return;
		}
		c.getItems().deleteItem(ID, 1);
		int reward = randomizeDrop(kingImp);
		int amount = 1;
		// Search the array for the real reward amount
		for (int k = 0; k < kingImp.length; k++) {
			if (kingImp[k][0] == reward) {
				amount = kingImp[k][1];
			}
		}

		if (amount == 1)
			c.getItems().addItem(reward, 1);
		else {
			c.getItems().addItem(reward, Misc.random(amount));
		}

		// if(c.getItems().freeSlots() > 0)
		// c.getItems().addItem(JarID, 1);
		c.buryDelay = System.currentTimeMillis();
	}

	public static void lootLowRewards(Client c, int ID) {
		if (System.currentTimeMillis() - c.buryDelay < 1000) {
			return;
		}
		c.getItems().deleteItem(ID, 1);
		int reward = randomizeDrop(lowRewards);
		int amount = 1;
		// Search the array for the real reward amount
		for (int k = 0; k < lowRewards.length; k++) {
			if (lowRewards[k][0] == reward) {
				amount = lowRewards[k][1];
			}
		}
		if (amount == 1)
			c.getItems().addItem(reward, 1);
		else {
			c.getItems().addItem(reward, Misc.random(amount));
		}
		// if(c.getItems().freeSlots() > 0)
		// c.getItems().addItem(JarID, 1);
		c.buryDelay = System.currentTimeMillis();
	}

	public static void lootMediumRewards(Client c, int ID) {
		if (System.currentTimeMillis() - c.buryDelay < 1000) {
			return;
		}
		c.getItems().deleteItem(ID, 1);
		int reward = randomizeDrop(mediumRewards);
		int amount = 1;
		// Search the array for the real reward amount
		for (int k = 0; k < mediumRewards.length; k++) {
			if (mediumRewards[k][0] == reward) {
				amount = mediumRewards[k][1];
			}
		}

		if (amount == 1)
			c.getItems().addItem(reward, 1);
		else {
			c.getItems().addItem(reward, Misc.random(amount));
		}

		// if(c.getItems().freeSlots() > 0)
		// c.getItems().addItem(JarID, 1);
		c.buryDelay = System.currentTimeMillis();
	}

	public static void lootSeeds(Client c, int ID) {
		if (System.currentTimeMillis() - c.buryDelay < 1000) {
			return;
		}
		c.getItems().deleteItem(ID, 1);
		int reward = randomizeDrop(seeds);
		int amount = 1;
		// Search the array for the real reward amount
		for (int k = 0; k < seeds.length; k++) {
			if (seeds[k][0] == reward) {
				amount = seeds[k][1];
			}
		}

		if (amount == 1)
			c.getItems().addItem(reward, 1);
		else {
			c.getItems().addItem(reward, Misc.random(amount));
		}

		// if(c.getItems().freeSlots() > 0)
		// c.getItems().addItem(JarID, 1);
		c.buryDelay = System.currentTimeMillis();
	}

	static int randomizeDrop(int[][] array) {
		return array[(int) (Math.random() * array.length)][0];
	}

}