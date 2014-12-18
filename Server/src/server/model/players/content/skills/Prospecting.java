package server.model.players.content.skills;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;

/**
 * Prospecting action. Part of the mining skill.
 * 
 * @author Fire cape.
 */
public class Prospecting {

	public void prospectRock(final Client c, final String itemName) {
		if (c == null) {
			return;
		}
		c.sendMessage("You examine the rock for ores...");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				c.sendMessage("This rock contains " + itemName + ".");
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 4);
	}
}