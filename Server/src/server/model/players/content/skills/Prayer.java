package server.model.players.content.skills;

import server.Config;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.model.players.content.GabbesAchievements;

public class Prayer {

	public static void bones1(final Client c, final int id) {
		if (!c.getItems().playerHasItem(id, 1)) {
			c.sendMessage("You do not have any bones left.");
			c.getPA().closeAllWindows();
			return;
		}
		c.isWalking = false;
		c.getPA().closeAllWindows();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(id, 1)) {
					c.sendMessage("You do not have any bones left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(id, 1) && c.bone > -1) {
					// c.turnPlayerTo(c.objectX, c.objectY);
					c.startAnimation(896);
					c.gfx0(437);
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					c.getPA().addSkillXP(
							getExp2(id) * 2 * Config.PRAYER_EXPERIENCE, 5);
					c.bone = -1;
					container.stop();
					// spinning = true;
					// container.stop();
				}
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		}, 2);
	}

	public static void bones10(final Client c, final int id) {
		if (!c.getItems().playerHasItem(id, 10)) {
			c.sendMessage("You do not have enough bones to do that.");
			c.getPA().closeAllWindows();
			return;
		}
		c.isWalking = false;
		c.boneOffered = 0;
		c.getPA().closeAllWindows();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(id, 1)) {
					c.sendMessage("You do not have any bones left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(id, 1) && c.boneOffered < 11
						&& c.bone > -1) {
					// c.turnPlayerTo(c.objectX, c.objectY);
					c.startAnimation(896);
					c.gfx0(437);
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					c.getPA().addSkillXP(
							getExp2(id) * 2 * Config.PRAYER_EXPERIENCE, 5);
					c.boneOffered += 1;
					if (c.boneOffered == 10 || c.boneOffered > 10) {
						c.boneOffered = 0;
						c.bone = -1;
						container.stop();
						return;
					}
					// spinning = true;
					// container.stop();
				}
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		}, 2);
	}

	/** Author: Gabbe, 100% Prayer bones on altar ect **/
	Client c;

	public int[][] bonesExp = { { 526, 5 }, { 532, 15 }, { 534, 30 },
			{ 536, 72 }, { 6729, 125 }, { 18830, 600 } };

	public static int[][] bonesExp2 = { { 526, 5 }, { 532, 15 }, { 534, 30 },
			{ 536, 72 }, { 6729, 125 }, { 18830, 600 } };

	public static void bones20(final Client c, final int id) {
		if (!c.getItems().playerHasItem(id, 20)) {
			c.sendMessage("You do not have enough bones to do that.");
			c.getPA().closeAllWindows();
			return;
		}
		c.isWalking = false;
		c.boneOffered = 0;
		c.getPA().closeAllWindows();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(id, 1)) {
					c.sendMessage("You do not have any bones left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(id, 1) && c.boneOffered < 21
						&& c.bone > -1) {
					// c.turnPlayerTo(c.objectX, c.objectY);
					c.startAnimation(896);
					c.gfx0(437);
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					c.getPA().addSkillXP(
							getExp2(id) * 2 * Config.PRAYER_EXPERIENCE, 5);
					c.boneOffered += 1;
					if (c.boneOffered == 20 || c.boneOffered > 20) {
						c.boneOffered = 0;
						c.bone = -1;
						container.stop();
						return;
					}
					// spinning = true;
					// container.stop();
				}
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		}, 2);
	}

	public static void bones5(final Client c, final int id) {
		if (!c.getItems().playerHasItem(id, 5)) {
			c.sendMessage("You do not have enough bones to do that.");
			c.getPA().closeAllWindows();
			return;
		}
		c.isWalking = false;
		c.boneOffered = 0;
		c.getPA().closeAllWindows();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(id, 1)) {
					c.sendMessage("You do not have any bones left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(id, 1) && c.boneOffered < 6
						&& c.bone > -1) {
					// c.turnPlayerTo(c.objectX, c.objectY);
					c.startAnimation(896);
					c.gfx0(437);
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					c.getPA().addSkillXP(
							getExp2(id) * 2 * Config.PRAYER_EXPERIENCE, 5);
					c.boneOffered += 1;
					if (c.boneOffered == 5 || c.boneOffered > 5) {
						c.boneOffered = 0;
						c.bone = -1;
						container.stop();
						return;
					}
					// spinning = true;
					// container.stop();
				}
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		}, 2);
	}

	public static void bonesALL(final Client c, final int id) {
		if (!c.getItems().playerHasItem(id, 1)) {
			c.sendMessage("You do not have any bones left.");
			c.getPA().closeAllWindows();
			return;
		}
		c.isWalking = false;
		c.getPA().closeAllWindows();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(id, 1)) {
					c.sendMessage("You do not have any bones left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(id, 1)) {
					c.startAnimation(896);
					c.gfx0(437);
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					c.getPA().addSkillXP(
							getExp2(id) * 2 * Config.PRAYER_EXPERIENCE, 5);
					// spinning = true;
					// container.stop();
				}
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}
		}, 2);
	}

	public static int getExp2(int id) {
		for (int j = 0; j < bonesExp2.length; j++) {
			if (bonesExp2[j][0] == id)
				return bonesExp2[j][1];
		}
		return 0;
	}

	public Prayer(Client c) {
		this.c = c;
	}

	public void bonesOnAltar(int id) {
		c.getItems().deleteItem(id, c.getItems().getItemSlot(id), 1);
		c.sendMessage("The gods are pleased with your offering.");
		c.getPA().addSkillXP(getExp(id) * 2 * Config.PRAYER_EXPERIENCE, 5);
	}

	public void bonesOnHouseAltar(int id) {
		if (c.COLectt > 3) {
			c.getItems().deleteItem(id, c.getItems().getItemSlot(id), 1);
			c.sendMessage("The gods are pleased with your offering.");
			c.sendMessage("You receive more XP from you're lecterns.");
			c.getPA().addSkillXP(
					getExp(id) * 2 * Config.PRAYER_EXPERIENCEINHOUSE, 5);
		} else {
			c.sendMessage("You need to have Lecterns built to do this.");
		}
	}

	public void buryBone(int id, int slot) {
		if (System.currentTimeMillis() - c.buryDelay > 1500) {
			if (id == 526 && !c.task1[0]) {
				c.task1[0] = true;
				c.sendMessage("You've completed the task: Bury a regular bone!");
				c.TPoints += 1;

				c.achievementInterface("Bury a regular bone!");
				if (c.TPoints == 1) {
					c.sendMessage("You've received a Task Point! You now have "
							+ c.TPoints + " point!");
				} else {
					c.sendMessage("You've received a Task Point! You now have a total of "
							+ c.TPoints + " points!");
				}

			}
			c.bonesB += 1;
			if (c.bonesB >= 150 && !c.task3[6]) {
				GabbesAchievements.handleEliteTask(c, 6, 3, "Bury 150 Bones!");
			}
			c.getItems().deleteItem(id, slot, 1);
			c.sendMessage("You bury the bones.");
			c.getPA().addSkillXP(getExp(id) * Config.PRAYER_EXPERIENCE, 5);
			c.buryDelay = System.currentTimeMillis();
			c.startAnimation(827);
		}
	}

	public int getExp(int id) {
		for (int j = 0; j < bonesExp.length; j++) {
			if (bonesExp[j][0] == id)
				return bonesExp[j][1];
		}
		return 0;
	}

	public boolean isBone(int id) {
		for (int j = 0; j < bonesExp.length; j++)
			if (bonesExp[j][0] == id)
				return true;
		return false;
	}
}