package server.model.players.content;

import server.model.players.Client;

public class RandomEvents {
	/* Author: Gabbe */

	// Current reward: XP Lamp

	public static boolean canTeleFromRandom = false;

	public static boolean cookedRawThing = false;

	public static boolean givenReward = false;
	public static int pheasentId = 0;
	public static int lastX;
	public static int lastY;
	public static int lastH;

	public static boolean hasCookedPheasent(Client c) {
		if (c.getItems().playerHasItem(2140, 1)) {
			return true;
		}
		return false;
	}

	public static boolean hasRawPheasent(Client c) {
		if (c.getItems().playerHasItem(6178, 1)) {
			return true;
		}
		return false;
	}

	// REMOVED REST!!
}
