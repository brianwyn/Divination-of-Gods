package server.model.players.content.combat.BountyHunter;

import server.Server;
import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.util.Misc;

public class PvPDrops {

	/*
	 * ItemId, amount (Leave amount 0 if its a non stackable item)
	 */
	static int[][] LOW_DROPS = { { 11118, 0 }, { 554, 15 }, { 557, 15 },
			{ 555, 9 }, { 556, 10 }, { 558, 20 }, { 559, 12 }, { 560, 9 },
			{ 561, 10 }, { 562, 20 }, { 563, 15 }, { 564, 12 }, { 565, 5 },
			{ 1073, 0 }, { 1091, 0 }, { 1111, 0 }, { 1123, 0 }, { 1145, 0 },
			{ 1161, 0 }, { 1183, 0 }, { 1199, 0 }, { 1211, 1 }, { 1317, 0 },
			{ 1430, 0 }, { 1185, 0 }, { 1213, 0 }, { 1147, 0 } };

	/*
	 * Medium drops itemId max item amount
	 */
	static int[][] MEDIUM_DROPS = { { 1215, 0 }, { 1434, 0 }, { 1079, 0 },
			{ 1093, 0 }, { 1113, 0 }, { 1127, 0 }, { 1163, 0 }, { 1201, 0 },
			{ 1319, 0 }, { 1373, 0 }, { 6524, 0 }, { 6525, 0 }, { 1704, 0 },
			{ 1706, 0 }, { 1708, 0 }, { 1710, 0 }, { 1712, 0 }, { 4675, 0 },
			{ 3751, 0 } };

	/*
	 * high drops same shit as above..
	 */
	static int[][] HIGH_DROPS = { { 1377, 0 }, { 3204, 0 }, { 1149, 0 },
			{ 4587, 0 }, { 1187, 0 }, { 4093, 0 }, { 4091, 0 }, { 4101, 0 },
			{ 4103, 0 }, { 4111, 0 }, { 4113, 0 }, { 6568, 0 } };
	/*
	 * Contain a few food drops.. itemId max item amount
	 */
	static int[][] FOOD_DROPS = { { 379, 4 }, { 385, 2 }, { 391, 2 },
			{ 373, 4 }, { 7946, 4 } };

	/*
	 * Artefacts
	 */
	static int[][] LOW_ARTEFACTS = { { 14888, 0 }, { 14889, 0 }, { 14890, 0 },
			{ 14891, 0 }, { 14892, 0 } };
	static int[][] MED_ARTEFACTS = { { 14883, 0 }, { 14884, 0 }, { 14885, 0 },
			{ 14886, 0 } };
	static int[][] HIGH_ARTEFACTS = { { 14878, 0 }, { 14879, 0 }, { 14880, 0 },
			{ 14881, 0 }, { 14882, 0 } };
	static int[][] EXR_ARTEFACTS = { { 14876, 0 }, { 14877, 0 } };

	/*
	 * PvP armour
	 */

	static int[][] ANCIENT_ARMOUR = { { 13858, 0 }, { 13861, 0 }, { 13864, 0 },
			{ 13867, 0 }, { 13870, 0 }, { 13873, 0 }, { 13876, 0 },
			{ 13879, 25 }, { 13880, 25 }, { 13881, 25 }, { 13882, 25 },
			{ 13883, 25 }, { 13884, 0 }, { 13887, 0 }, { 13890, 0 },
			{ 13893, 0 }, { 13896, 0 }, { 13899, 0 }, { 13902, 0 },
			{ 13905, 0 }, { 13908, 0 }, { 13911, 0 }, { 13914, 0 },
			{ 13917, 0 }, { 13920, 0 }, { 13923, 0 }, { 13926, 0 },
			{ 13929, 0 }, { 13932, 0 }, { 13935, 0 }, { 13938, 0 },
			{ 13941, 0 }, { 385, 2 }, { 391, 2 }, { 373, 4 }, { 385, 2 },
			{ 391, 2 }, { 373, 4 }, { 385, 2 }, { 391, 2 }, { 373, 4 },
			{ 385, 2 }, { 391, 2 }, { 373, 4 }, { 13944, 0 }, { 13947, 0 },
			{ 13950, 0 }, { 13953, 25 }, { 13954, 25 }, { 13955, 25 },
			{ 13956, 25 }, { 13957, 25 } };

	/**
	 * Creates a drop for:
	 * 
	 * @param player
	 *            player dying
	 * @param item
	 *            item send (randomizeLowDrop for example)
	 * @param array
	 *            the array of the item (LOW_DROPS for example)
	 */
	static void createDrop(Client player, Client o, int item, int[][] array) {
		int amount = 0;
		for (int i = 0; i < array.length; i++)
			if (array[i][0] == item)
				amount = 1 + Misc.random(array[i][1]);
		Server.itemHandler.createGroundItem(o, item, player.getX(),
				player.getY(), amount, o.playerId);
		o.dropWealth += player.getShops().getItemShopValue(item) * amount;
	}

	/**
	 * 
	 * @param ep
	 * @param target
	 * @return
	 */
	static int getAncientArmourChance(int ep, boolean target) {
		if (target && ep >= 50)
			return 5;
		else if (target && ep >= 0)
			return 10;
		else if (ep >= 20 && ep < 50)
			return 15;
		else if (ep >= 50)
			return 10;
		return 40;
	}

	/**
	 * Decreases the earned potential
	 * 
	 * @param totalValue
	 *            total drop value
	 * @return decrease amount
	 */
	static int getEarnedPotentialDecrease(int totalValue) {
		if (totalValue > 10000 && totalValue <= 50000)
			return 4;
		else if (totalValue > 50000 && totalValue <= 100000)
			return 6;
		else if (totalValue > 100000 && totalValue <= 200000)
			return 8;
		else if (totalValue > 200000 && totalValue <= 500000)
			return 11;
		else if (totalValue > 500000 && totalValue <= 1000000)
			return 16;
		else if (totalValue > 1000000 && totalValue <= 2000000)
			return 24;
		else if (totalValue >= 2000000)
			return 30;
		return 2;
	}

	/**
	 * 
	 * @param ep
	 * @param target
	 * @return
	 */
	static int getExtremeArtefactChance(int ep, boolean target) {
		if (target && ep >= 50)
			return 5;
		else if (target && ep >= 0)
			return 15;
		else if (ep >= 20 && ep < 50)
			return 40;
		else if (ep >= 50)
			return 30;
		return 50;
	}

	/**
	 * 
	 * @param ep
	 * @param target
	 * @return
	 */
	static int getHighArtefactChance(int ep, boolean target) {
		if (target && ep >= 50)
			return 2;
		else if (target && ep >= 10)
			return 5;
		else if (ep >= 20 && ep < 50)
			return 8;
		else if (ep >= 50)
			return 6;
		return 20;
	}

	/**
	 * 
	 * @param ep
	 * @param target
	 * @return
	 */
	static int getLowArtefactChance(int ep, boolean target) {
		if (target && ep >= 50)
			return 0;
		else if (target && ep >= 0)
			return 0;
		else if (ep >= 20 && ep < 50)
			return 1;
		else if (ep >= 50)
			return 0;
		return 2;
	}

	/**
	 * 
	 * @param ep
	 * @param target
	 * @return
	 */
	static int getMedArtefactChance(int ep, boolean target) {
		if (target && ep >= 50)
			return 0;
		else if (target && ep >= 10)
			return 1;
		else if (ep >= 20 && ep < 50)
			return 1;
		else if (ep >= 50)
			return 0;
		return 10;
	}

	/**
	 * Gets the total net wealth from the
	 * 
	 * @param player
	 * @return
	 */
	public static int getTotalNet(Client player) {
		int totalAmount = 0;
		for (int j = 0; j < player.playerEquipment.length; j++) {
			totalAmount += player.getShops().getItemShopValue(
					player.playerEquipment[j])
					* player.playerEquipmentN[j];
		}
		for (int i = 0; i < player.playerItems.length; i++) {
			if (player.playerItems[i] - 1 > 0) {
				int inventoryItemValue = player.getShops().getItemShopValue(
						player.playerItems[i] - 1);
				if (inventoryItemValue > 0) {
					totalAmount += inventoryItemValue;
				}
			}
		}
		return totalAmount;
	}

	/**
	 * Handles the drops
	 * 
	 * @param player
	 *            player dying
	 */
	public static void handleDrops(Client player) {
		Client o = (Client) PlayerHandler.players[player.killerId];
		if (o != null) {
			/*
			 * Drops 1 item from inventory
			 */
			for (int i = 0; i < player.playerItems.length; i++) {
				if (1 + Misc.random(27) == i
						&& !player.getItems().tradeable(
								player.playerItems[i] - 1)) {
					Server.itemHandler.createGroundItem(o,
							player.playerItems[i] - 1, player.getX(),
							player.getY(), player.playerItemsN[i], o.playerId);
					o.dropWealth += player.getShops().getItemShopValue(
							player.playerItems[i] - 1)
							* player.playerItemsN[i];
				}
			}
			/*
			 * Drops 1 item from equipment
			 */
			for (int i = 0; i < player.playerEquipment.length; i++) {
				if (1 + Misc.random(13) == i
						&& !player.getItems().tradeable(
								player.playerEquipment[i])) {
					Server.itemHandler.createGroundItem(o,
							player.playerEquipment[i], player.getX(),
							player.getY(), player.playerEquipmentN[i],
							o.playerId);
					o.dropWealth += player.getShops().getItemShopValue(
							player.playerEquipment[i])
							* player.playerEquipmentN[i];
				}
			}
			/*
			 * Creates a low item drop everything x2 :) for these
			 */
			if (getTotalNet(player) >= 76000 && getTotalNet(o) >= 76000)
				createDrop(player, o, randomizeDrop(MEDIUM_DROPS), MEDIUM_DROPS);
			if (Misc.random(getLowArtefactChance(o.EP,
					PvPHandler.playerHasTarget(o))) == 0)
				createDrop(player, o, randomizeDrop(LOW_ARTEFACTS),
						LOW_ARTEFACTS);
			if (Misc.random(getAncientArmourChance(o.EP,
					PvPHandler.playerHasTarget(o))) == 0
					&& getTotalNet(player) >= 76000 && getTotalNet(o) >= 76000)
				createDrop(player, o, randomizeDrop(ANCIENT_ARMOUR),
						ANCIENT_ARMOUR);
			if (Misc.random(getMedArtefactChance(o.EP,
					PvPHandler.playerHasTarget(o))) == 0
					&& getTotalNet(player) >= 76000 && getTotalNet(o) >= 76000)
				createDrop(player, o, randomizeDrop(MED_ARTEFACTS),
						MED_ARTEFACTS);
			if (Misc.random(getHighArtefactChance(o.EP,
					PvPHandler.playerHasTarget(o))) == 0
					&& getTotalNet(player) >= 76000 && getTotalNet(o) >= 76000)
				createDrop(player, o, randomizeDrop(HIGH_ARTEFACTS),
						HIGH_ARTEFACTS);
			if (getTotalNet(player) >= 76000 && getTotalNet(o) >= 76000
					&& o.EP > 10 || o.playerId == player.targetIndex)
				createDrop(player, o, randomizeDrop(HIGH_DROPS), HIGH_DROPS);
			if ((getTotalNet(player) >= 76000 && getTotalNet(o) >= 76000
					&& o.EP > 25 || o.playerId == player.targetIndex)
					&& Misc.random(getExtremeArtefactChance(player.EP,
							PvPHandler.playerHasTarget(player))) == 0)
				createDrop(player, o, randomizeDrop(EXR_ARTEFACTS),
						EXR_ARTEFACTS);
			createDrop(player, o, randomizeDrop(LOW_DROPS), LOW_DROPS);
			createDrop(player, o, randomizeDrop(FOOD_DROPS), FOOD_DROPS);
			createDrop(player, o, randomizeDrop(LOW_DROPS), LOW_DROPS);
			createDrop(player, o, randomizeDrop(FOOD_DROPS), FOOD_DROPS);
			/*
			 * removes the ep
			 */
			int decreaseAmount = getEarnedPotentialDecrease(o.dropWealth)
					+ Misc.random(getEarnedPotentialDecrease(o.dropWealth));
			if (o.EP - decreaseAmount < 0)
				o.EP = 0;
			else
				o.EP -= decreaseAmount;
			/*
			 * resets the target
			 */
			if (o.playerId == player.targetIndex) {
				PvPHandler.resetTarget2(player);
				PvPHandler.setBountyIcon(o, o.bountyIcon + 1);
				player.bountyIcon = 2;
				o.targetPercentage = 0;
			}
		}
	}

	/**
	 * Randomizes a random low drop
	 * 
	 * @return a low item drop
	 */
	static int randomizeDrop(int[][] array) {
		return array[(int) (Math.random() * array.length)][0];
	}
}