package server.model.players.content.skills.impl.ConstructionObjects;

import server.model.players.Client;

public class Rugs {

	public static void handleRug(Client c) {
		if (c.constructionObjects[3]) {
			rugSpawning(c, true, 15413);
			c.constructionObjects[3] = false;
		} else {
			c.getDH().sendOption4("Brown rug (Lvl 20)", "Red rug (Lvl 40)",
					"None", "None");
			c.teleAction = 10;
		}
	}

	public static void init(Client c) {
		if (c.objectId == 13723 || c.objectId == 15543 || c.objectId == 15542
				|| c.objectId == 15541 || c.objectId == 15545
				|| c.objectId == 13718) {
			Rugs.handleRug(c);
			return;
		}
	}

	public static void rugSpawning(Client c, boolean delete, int ID) {
		// Spawns a 4X4 Red Rug
		if (!delete) {
			for (int i = 1898; i < 1898 + 4; i++) {
				for (int k = 5091; k < 5091 + 4; k++) {
					c.getPA().checkObjectSpawn(ID, i, k, 0, 22);
				}
			}
		} else {
			if (delete) {
				for (int i = 1898; i < 1898 + 4; i++) {
					for (int k = 5091; k < 5091 + 4; k++) {
						c.getPA().checkObjectSpawn(ID, i, k, 0, 22);
					}
				}
			}
		}
	}

}