package server.model.players.content.skills.impl.ConstructionObjects;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;

public class Plants {
	public static boolean spawn = false;

	public static int tick = 0;

	// not added yet
	public static void cutDownTree(final Client c) {
		if (!c.constructionObjects[6] || c.constructionUpgrades[6] == -1) {
			return;
		}
		if (!c.getItems().playerHasItem(590, 1)
				|| !c.getItems().playerHasItem(1511, 1)) {
			c.sendMessage("You need a tinderbox and some logs to light this.");
			return;
		}
		c.startAnimation(733);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			public void execute(CycleEventContainer e) {
				tick++;
				if (c.disconnected)
					e.stop();
				if (!spawn) {
					c.getPA().checkObjectSpawn(c.constructionUpgrades[6] + 1,
							1899, 5095, 1, 10);
					c.getItems().deleteItem(1511, 1);
					c.startAnimation(65535);
					spawn = true;
				}
				if (tick == 25)
					e.stop();
			}

			@Override
			public void stop() {
				spawn = false;
				tick = 0;
				c.getPA().checkObjectSpawn(c.constructionUpgrades[6], 1899,
						5095, 1, 10);
				c.sendMessage("Your fireplace has burned out.");
			}
		}, 2);
	}

	public static void handleTree(Client c) {
		if (c.constructionObjects[7]) {
			c.getPA().checkObjectSpawn(15362, 1889, 5093, 1, 10);
			c.startAnimation(898);
			c.constructionObjects[6] = false;
		} else {
			c.getDH().sendOption5("Normal Tree (Lvl 8)", "Oak Tree (Lvl 20)",
					"Willow Tree (Lvl 35)", "Yew Tree (Lvl 62)",
					"Magic Tree (Lvl 85)");
			c.teleAction = 146;
		}
	}

	public static void init(Client c) {
		if (c.objectId == 15490) {
			handleTree(c);
		}

	}

}