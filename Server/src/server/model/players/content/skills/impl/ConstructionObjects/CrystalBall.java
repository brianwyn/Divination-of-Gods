package server.model.players.content.skills.impl.ConstructionObjects;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;

public class CrystalBall {
	public static void handleCrystal(Client c) {
		if (c.constructionObjects[10]) {
			c.getPA().checkObjectSpawn(15422, 1893, 5100, 0, 10);
			c.startAnimation(898);
			c.constructionObjects[10] = false;
		} else {
			c.getDH().sendOption2("Crystal ball (Lvl 45)",
					"Crystal of Power (Lvl 70)");
			c.teleAction = 835;
			return;
		}
	}

	public static void init(Client c) {
		if (c.objectId == 15550 || c.objectId == 13789 || c.objectId == 13787) {
			handleCrystal(c);
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