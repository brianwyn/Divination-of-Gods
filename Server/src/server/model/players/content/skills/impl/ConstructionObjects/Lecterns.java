package server.model.players.content.skills.impl.ConstructionObjects;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;

public class Lecterns {

	public static void createTablet(final Client c, final int ID,
			final int anim, final int XP, final int item, final int amount) {
		if (!c.getItems().playerHasItem(1761, 1)
				|| !c.getItems().playerHasItem(item, 1)) {
			if (amount == 1)
				c.sendMessage("<col=ff3000>You need some Soft clay and "
						+ amount + " " + c.getItems().getItemName(item)
						+ " to create this tablet.");
			if (amount > 1)
				c.sendMessage("<col=ff3000>You need some Soft clay and "
						+ amount + " " + c.getItems().getItemName(item)
						+ "s to create this tablet.");
			return;
		}
		c.isWalking = false;
		c.getPA().closeAllWindows();
		c.startAnimation(anim);
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			public void execute(CycleEventContainer e) {
				if (!c.getItems().playerHasItem(1761, 1)
						|| !c.getItems().playerHasItem(item, 1)) {
					if (amount == 1)
						c.sendMessage("<col=ff3000>You need some Soft clay and "
								+ amount
								+ " "
								+ c.getItems().getItemName(item)
								+ " to create this tablet.");
					if (amount > 1)
						c.sendMessage("<col=ff3000>You need some Soft clay and "
								+ amount
								+ " "
								+ c.getItems().getItemName(item)
								+ "s to create this tablet.");
					e.stop();
					return;
				}
				if (c == null || c.disconnected || c.isWalking)
					e.stop();
				if (c.getItems().freeSlots() > 0) {
					c.startAnimation(anim);
					c.getPA().closeAllWindows();
					c.getItems().deleteItem(item, amount);
					c.getItems().deleteItem(1761, 1);
					c.getItems().addItem(ID, 1);
					c.getPA().addSkillXP2(XP, 6);
				}

			}

			@Override
			public void stop() {
			}
		}, 3);
	}

	public static void handleAction(Client c, int ID) {
		if (ID == 13642)
			c.getPA().showInterface(45454);
		if (ID == 13644)
			c.getPA().showInterface(54544);
		if (ID == 13643)
			c.getPA().showInterface(65454);
	}

	public static void handleLectern(Client c) {
		if (c.constructionObjects[9]) {
			c.getPA().checkObjectSpawn(15420, 1890, 5098, 2, 10);
			c.startAnimation(898);
			c.constructionObjects[9] = false;
		} else {
			c.getDH().sendOption4("Oak Lectern (Lvl 45)",
					"Demon Lectern (Lvl 60)", "Eagle Lectern (Lvl 75)", "None");
			c.teleAction = 1335;
		}
	}

	public static void init(Client c) {
		if (c.objectId == 15548 || c.objectId == 13770 || c.objectId == 13772
				|| c.objectId == 13771) {
			handleLectern(c);
		}
		return;
	}
}