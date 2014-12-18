package server.model.players.content.skills.impl.ConstructionObjects;

import server.model.players.Client;

public class Chairs {

	public static void handleChair(Client c) {
		if (c.constructionObjects[0]) {
			c.getPA().checkObjectSpawn(15410, 1898, 5092, 2, 10);
			c.startAnimation(898);
			c.constructionObjects[0] = false;
		} else {
			c.getDH().sendOption4("Wooden Chair (Lvl 1)", "Oak Chair (Lvl 19)",
					"Teak Chair (Lvl 45)", "Mahogany Chair (Lvl 70)");
			c.teleAction = 6;
		}
	}

	public static void handleChair2(Client c) {
		if (c.constructionObjects[1]) {
			c.getPA().checkObjectSpawn(15410, 1901, 5092, 2, 10);
			c.startAnimation(898);
			c.constructionObjects[1] = false;
		} else {
			c.getDH().sendOption4("Wooden Chair (Lvl 1)", "Oak Chair (Lvl 19)",
					"Teak Chair (Lvl 45)", "Mahogany Chair (Lvl 70)");
			c.teleAction = 7;
		}
	}

	public static void init(Client c) {
		if (c.objectId == 15538 && c.objectY == 2026) {
			handleChair(c);
		} else if (c.objectId == 13710 && c.objectY == 2026) {
			handleChair(c);
		} else if (c.objectId == 13712 && c.objectY == 2026) {
			handleChair(c);
		} else if (c.objectId == 13715 && c.objectY == 2026) {
			handleChair(c);
		} else if (c.objectId == 15539 && c.objectY == 2029) {
			handleChair2(c);
		} else if (c.objectId == 15538 && c.objectY == 2029) {
			handleChair2(c);
		} else if (c.objectId == 13710 && c.objectY == 2029) {
			handleChair2(c);
		} else if (c.objectId == 13712 && c.objectY == 2029) {
			handleChair2(c);
		} else if (c.objectId == 13715 && c.objectY == 2029) {
			handleChair2(c);
		} else if (c.objectId == 13709 && c.objectY == 2029) {
			handleChair2(c);
		} else if (c.objectId == 13709 && c.objectY == 2026) {
			handleChair(c);
		}
	}
}