package server.model.players.content;

import server.model.players.Client;
import server.util.Misc;

public class DungInterface {

	public static void handleInterfaceButtons(Client c, int packetType,
			int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		switch (actionButtonId) {
		case 102117:
			if (c.InDung()) {
				c.getDH().sendDialogues(256, 1);
				return;
			}
			c.setSidebarInterface(1, 639);
			c.sendMessage("You abandon your party.");
			c.inParty = false;
			break;
		case 105249:
			c.setSidebarInterface(1, 26224);
			c.getPA().sendFrame126("" + Misc.optimizeText(c.playerName) + "",
					26226);
			c.getPA().sendFrame126("@red@Kills: " + c.dungn + "", 26237);
			c.getPA().sendFrame126("", 26238);
			c.inParty = true;
			break;
		case 102128:
			c.sendMessage("There's only one Floor at the moment.");
			break;
		case 102131:
			c.sendMessage("There's only one Complexity level at the moment.");
			break;
		case 102120:
			c.sendMessage("You don't want to do that...");
			break;

		}
	}

}