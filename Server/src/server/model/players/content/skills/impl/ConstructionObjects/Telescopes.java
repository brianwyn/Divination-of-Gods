package server.model.players.content.skills.impl.ConstructionObjects;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.model.players.content.skills.Construction;

public class Telescopes {

	public static void handlePanicInterface(final Client c) {
		if (c != null) {
			c.getPA().showInterface(18681);
			CycleEventHandler.getSingleton().stopEvents(c, 521); // let's stop
																	// the event
																	// first.
			CycleEventHandler.getSingleton().addEvent(521, c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					container.stop();
				}

				@Override
				public void stop() {
					c.getPA().closeAllWindows();
					c.startAnimation(857);

				}
			}, 2);
		}
	}

	public static void handleTeleScope(Client c) {
		if (c.constructionObjects[12]) {
			c.getPA().checkObjectSpawn(15424, 1893, 5103, 2, 10);
			c.startAnimation(898);
			c.constructionObjects[12] = false;
		} else {
			if (Construction.hasReqs(c, "Telescope")) {
				c.getPA().checkObjectSpawn(13656, 1893, 5103, 2, 10);
				c.constructionObjects[12] = true;
				c.constructionUpgrades[12] = 13656;
			}
			return;
		}
	}

	public static void init(Client c) {
		if (c.objectId == 15552 || c.objectId == 13784)
			handleTeleScope(c);
	}
}