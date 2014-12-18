package server.model.players.content.skills.impl.ConstructionObjects;

import server.model.players.Client;
import server.model.players.content.skills.Construction;

public class Bank {

	public static void handleBankChest(Client c) {
		if (c.constructionObjects[7]) {
			c.getPA().checkObjectSpawn(11219, 1888, 5094, 2, 10);
			c.getPA().checkObjectSpawn(11219, 1888, 5095, 2, 10);
			c.startAnimation(898);
			c.constructionObjects[7] = false;
		} else {
			if (Construction.hasReqs(c, "Bank")) {
				c.getPA().checkObjectSpawn(27663, 1888, 5095, 1, 10);
				c.getPA().checkObjectSpawn(-1, 1888, 5094, 1, 10);
				c.constructionObjects[7] = true;
				c.constructionUpgrades[7] = 27663;
			}
			return;
		}
	}

	public static void init(Client c) {
		if (c.objectId == 27791)
			handleBankChest(c);

	}
}