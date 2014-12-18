package server.model.players.content.combat.BountyHunter;

import server.model.players.Client;

/**
 * @author Gabbe
 */
public class Artifacts {

	public static int artifacts[] = { 14876, 14877, 14878, 14879, 14880, 14881,
			14882, 14883, 14884, 14885, 14886, 14887, 14888, 14889, 14890,
			14891, 14892 };
	public static int reward = 0;

	public static int getArtifactPrice(Client c, int artifactID) {
		int ShopValue = (int) Math.floor(c.getShops().getItemShopValue(
				artifactID, 0, 0));
		ShopValue *= 1;
		return ShopValue;
	}

	public static boolean hasArtifact(Client c) {
		for (int k = 0; k < artifacts.length; k++) {
			if (c.getItems().playerHasItem(artifacts[k], 1))
				return true;
		}
		return false;
	}

	public static void reward(Client c) {
		reward = 0;
		for (int k = 0; k < artifacts.length; k++) {
			for (int i = 0; i < 28; i++) {
				if (c.getItems().playerHasItem(artifacts[k], 1)) {
					if (c.getItems().freeSlots() >= 1) {
						c.getItems().deleteItem(artifacts[k], 1);
						c.getItems().addItem(995,
								getArtifactPrice(c, artifacts[k]));
						c.getItems().updateInventory();
						reward += getArtifactPrice(c, artifacts[k]);
					}
				}
			}
		}
		if (reward > 0)
			c.sendMessage("You received " + reward
					+ " coins for your artifacts.");
	}

}