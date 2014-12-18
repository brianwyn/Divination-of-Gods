package server.model.players.content.skills;

import server.model.players.Client;
import server.model.players.Player;

public class Coconut {
	public static void handleCoconut(Client c, int itemUsed, int usedWith) {
		if ((itemUsed == 2347 && usedWith == 5974)
				|| (itemUsed == 5974 && usedWith == 2347)) {
			c.sendMessage("You crush the coconut with your hammer.");
			c.getPA().addSkillXP(45, Player.playerHerblore);
			c.getItems().deleteItem(5974, 1);
			c.getItems().addItem(5976, 1);
		}
		if ((itemUsed == 229 && usedWith == 5976)
				|| (itemUsed == 5976 && usedWith == 229)) {
			c.sendMessage("You overturn the cocounut into a vial.");
			c.getPA().addSkillXP(45, Player.playerHerblore);
			c.getItems().deleteItem(229, 1);
			c.getItems().deleteItem(5976, 1);
			c.getItems().addItem(5978, 1);
			c.getItems().addItem(5935, 1);
		}
	}
}