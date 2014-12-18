package server.model.players.content;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.util.Misc;

/*
 * @Author: Killerj012 and Robbie'
 * @Date: 1-9-2011
 */

public class CrystalChest {

	private static final int[] CHEST_REWARDS = { 13040, 13040, 13040, 4151,
			6585, 1079, 1093, 526, 1969, 371, 2363, 451 };
	public static final int[] KEY_HALVES = { 985, 987 };
	public static final int KEY = 989;
	private static final int DRAGONSTONE = 1631;

	// private static final int OPEN_ANIMATION = 881;

	public static boolean canOpen(Client c) {
		if (c.getItems().playerHasItem(989)) {
			return true;
		} else {
			c.sendMessage("You need a Crystal Key to open this chest!");
			c.sendMessage("Get them from killing monsters at funzone!");
			return false;
		}
	}

	public static int getLength() {
		return CHEST_REWARDS.length;
	}

	public static int loopHalf() {
		return KEY_HALVES[1];
	}

	public static void makeKey(Client c) {
		if (c.getItems().playerHasItem(toothHalf(), 1)
				&& c.getItems().playerHasItem(loopHalf(), 1)) {
			c.getItems().deleteItem(toothHalf(), 1);
			c.getItems().deleteItem(loopHalf(), 1);
			c.getItems().addItem(KEY, 1);
		}
	}

	public static void searchChest(final Client c, final int id, final int x,
			final int y) {
		if (canOpen(c)) {
			c.sendMessage("You unlock the chest with your key.");
			c.getItems().deleteItem(KEY, 1);
			// c.startAnimation(OPEN_ANIMATION);
			c.getPA().checkObjectSpawn(id + 1, x, y, 2, 10);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (c == null || c.disconnected) {
						container.stop();
						return;
					}
					c.getItems().addItem(DRAGONSTONE, 1);
					c.getItems().addItem(995, Misc.random(8230));
					c.getItems().addItem(
							CHEST_REWARDS[Misc.random(getLength() - 1)], 1);
					c.sendMessage("You find some treasure in the chest.");
					c.getPA().checkObjectSpawn(id, x, y, 2, 10);

					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 3);
		}
	}

	public static int toothHalf() {
		return KEY_HALVES[0];
	}

}