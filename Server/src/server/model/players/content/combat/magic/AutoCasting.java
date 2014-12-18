package server.model.players.content.combat.magic;

import server.model.players.Client;

public class AutoCasting {

	/***
	 * \ /*** 100% Autocasting By Gabbe /
	 ***/
	static int[] spellIds = { 4128, 4130, 4132, 4134, 4136, 4139, 4142, 4145,
			4148, 4151, 4153, 4157, 4159, 4161, 4164, 4165, 4129, 4133, 4137,
			6006, 6007, 6026, 6036, 6046, 6056, 4147, 6003, 47005, 4166, 4167,
			4168, 48157, 50193, 50187, 50101, 50061, 50163, 50211, 50119,
			50081, 50151, 50199, 50111, 50071, 50175, 50223, 50129, 50091 };

	public static void handleButtons(final Client c, int packetType,
			int packetSize, int actionButtonId) {
		if (actionButtonId == 26010)
			reset(c);
		for (int i = 0; i < spellIds.length; i++) {
			if (actionButtonId == spellIds[i]) {
				c.autocasting = true;
				c.autocastId = i;
			}

		}
	}

	public static void reset(Client c) {
		if (c.autocasting) {
			c.autocasting = false;
			c.getPA().resetAutocast();
		}
	}

}