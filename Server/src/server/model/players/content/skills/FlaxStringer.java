package server.model.players.content.skills;

import server.Config;
import server.model.players.Client;
import server.model.players.Player;

/**
 * Flax Stringer Class
 * 
 * @Author Notepad/01053
 * 
 **/

public class FlaxStringer {

	private Client c;

	private final int FLAX[][] = { { 1779, 1777, 1777, 40, 120 } };

	private final int EMOTE = 896;
	public FlaxStringer(Client c) {
		this.c = c;
	}

	public void handleStringing(int id, int slot) {
		if (c.playerLevel[12] < 40) {
			c.sendMessage("You need a Crafting level of " + FLAX[slot][3]
					+ " to Craft this.");
			return;
		}
		if (c.getItems().playerHasItem(id, 1)) {
			if (c.playerLevel[Player.playerCrafting] >= FLAX[slot][3]) {
				c.startAnimation(EMOTE);
				for (int j = 0; j < 28; j++) {
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					c.getItems().addItem(FLAX[slot][1], 1);
					c.getPA().addSkillXP(
							FLAX[slot][4] * Config.CRAFTING_EXPERIENCE,
							Player.playerCrafting);
				}
			}
		}
	}

	public void itemOnObject(int itemId) {
		for (int j = 0; j < FLAX.length; j++) {
			if (FLAX[j][0] == itemId)
				handleStringing(FLAX[j][0], j);
		}
	}
}
