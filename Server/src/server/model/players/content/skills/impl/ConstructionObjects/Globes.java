package server.model.players.content.skills.impl.ConstructionObjects;

import server.model.players.Client;

public class Globes {

	public static void handleGlobe(Client c) {
		if (c.constructionObjects[11]) {
			c.getPA().checkObjectSpawn(15421, 1889, 5100, 1, 10);
			c.startAnimation(898);
			c.constructionObjects[11] = false;
		} else {
			c.teleAction = 123;
			c.getDH().sendOption5("Wooden Globe (Lvl 41)",
					"Ornamental Globe (Lvl 50)", "Lunar Globe (Lvl 60)",
					"Celestial Globe (Lvl 70)", "Large Orrery (Lvl 80)");
			return;
		}
	}

	public static void init(Client c) {
		if (c.objectId == 15549 || c.objectId == 13777 || c.objectId == 13778
				|| c.objectId == 13779 || c.objectId == 13780
				|| c.objectId == 13783)
			handleGlobe(c);

	}
}