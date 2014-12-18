package server.model.players.content.skills;

import server.model.players.Client;
import server.model.players.Player;

/*
 * @Author Gooroo + Ace
 */

public class Grinding {
	public static void handleGrinding(Client c, int itemUsed, int usedWith) {
		if ((itemUsed == 237 && usedWith == 233)
				|| (itemUsed == 233 && usedWith == 237)) {
			c.sendMessage("You grind the " + c.getItems().getItemName(237)
					+ ".");
			c.startAnimation(364);
			c.getPA().addSkillXP(45, Player.playerHerblore);
			c.getItems().deleteItem(237, 1);
			c.getItems().addItem(235, 1);
		}
		if ((itemUsed == 1973 && usedWith == 233)
				|| (itemUsed == 233 && usedWith == 1973)) {
			c.sendMessage("You grind the " + c.getItems().getItemName(1973)
					+ ".");
			c.startAnimation(364);
			c.getPA().addSkillXP(45, Player.playerHerblore);
			c.getItems().deleteItem(1973, 1);
			c.getItems().addItem(1975, 1);
		}
		if ((itemUsed == 5075 && usedWith == 233)
				|| (itemUsed == 233 && usedWith == 5075)) {
			c.sendMessage("You grind the " + c.getItems().getItemName(5075)
					+ ".");
			c.startAnimation(364);
			c.getPA().addSkillXP(45, Player.playerHerblore);
			c.getItems().deleteItem(5075, 1);
			c.getItems().addItem(6693, 1);
		}
		if ((itemUsed == 243 && usedWith == 233)
				|| (itemUsed == 233 && usedWith == 243)) {
			c.sendMessage("You grind the " + c.getItems().getItemName(243)
					+ ".");
			c.startAnimation(364);
			c.getPA().addSkillXP(45, Player.playerHerblore);
			c.getItems().deleteItem(243, 1);
			c.getItems().addItem(241, 1);
		}
	}
}