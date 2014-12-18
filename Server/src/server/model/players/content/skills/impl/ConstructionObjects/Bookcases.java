package server.model.players.content.skills.impl.ConstructionObjects;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;

public class Bookcases {
	public static void handleBookcase(Client c) {
		if (c.constructionObjects[4]) {
			c.getPA().checkObjectSpawn(15425, 1896, 5088, 0, 10);
			c.startAnimation(898);
			c.constructionObjects[4] = false;
		} else {
			c.getDH().sendOption4("Wooden Bookcase (Lvl 15)",
					"Oak Bookcase (Lvl 35)", "Teak Bookcase (Lvl 47)",
					"Mahogany Bookcase (Lvl 60)");
			c.teleAction = 4;
		}
	}

	public static void handleBookcase2(Client c) {
		if (c.constructionObjects[5]) {
			c.getPA().checkObjectSpawn(15416, 1903, 5088, 2, 10);
			c.startAnimation(898);
			c.constructionObjects[5] = false;
		} else {
			c.getDH().sendOption4("Wooden Bookcase (Lvl 15)",
					"Oak Bookcase (Lvl 35)", "Teak Bookcase (Lvl 47)",
					"Mahogany Bookcase (Lvl 60)");
			c.teleAction = 5;
		}
	}

	public static void handleBookcase3(Client c) {
		if (c.constructionObjects[13]) {
			c.getPA().checkObjectSpawn(15416, 1892, 5103, 1, 10);
			c.startAnimation(898);
			c.constructionObjects[13] = false;
		} else {
			c.getDH().sendOption4("Wooden Bookcase (Lvl 15)",
					"Oak Bookcase (Lvl 35)", "Teak Bookcase (Lvl 47)",
					"Mahogany Bookcase (Lvl 60)");
			c.teleAction = 131;
		}
	}

	public static void handleBookcase4(Client c) {
		if (c.constructionObjects[14]) {
			c.getPA().checkObjectSpawn(15416, 1891, 5103, 1, 10);
			c.startAnimation(898);
			c.constructionObjects[14] = false;
		} else {
			c.getDH().sendOption4("Wooden Bookcase (Lvl 15)",
					"Oak Bookcase (Lvl 35)", "Teak Bookcase (Lvl 47)",
					"Mahogany Bookcase (Lvl 60)");
			c.teleAction = 132;
		}
	}

	public static void init(Client c) {
		if (c.objectId == 15544 || c.objectId == 13726 || c.objectId == 13729
				|| c.objectId == 13725 || c.objectId == 13727
				|| c.objectId == 15553 || c.objectId == 13725
				|| c.objectId == 13726 || c.objectId == 13728) {
			if (c.objectY == 2024) {
				Bookcases.handleBookcase(c);
			} else if (c.objectY == 2031) {
				Bookcases.handleBookcase2(c);
			} else if (c.objectY == 2020) {
				Bookcases.handleBookcase3(c);
			} else if (c.objectY == 2019) {
				Bookcases.handleBookcase4(c);
			}
			return;
		}
	}

	public static void searchBookcase(final Client c) {
		c.sendMessage("You search the bookcase..");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				c.sendMessage("You find no intereseting books.");
				e.stop();
				if (c.disconnected)
					e.stop();

			}

			@Override
			public void stop() {
			}
		}, 2);
	}
}