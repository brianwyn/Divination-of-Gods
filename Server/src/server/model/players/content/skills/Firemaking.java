package server.model.players.content.skills;

import server.Config;
import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.objects.Object;
import server.model.players.Client;
import server.model.players.content.GabbesAchievements;
import server.util.Misc;
import server.world.clip.region.Region;

public class Firemaking extends SkillHandler {
	public static boolean logNulled = false;

	public static String nameOfLog = "";

	public static void attemptFire(final Client c, final int itemUsed,
			final int usedWith, final int x, final int y,
			final boolean groundObject) {
		if (c == null) {
			return;
		}
		if (!c.getItems().playerHasItem(590)) {
			c.sendMessage("You need a tinderbox to light a fire.");
			return;
		}
		if (isSkilling[11] == true) {
			return;
		}
		if (c.inRimmington()) {
			c.sendMessage("You cannot light fires in the home area!");
			return;
		}
		for (final LogData.logData l : LogData.logData.values()) {
			final int logId = usedWith == 590 ? itemUsed : usedWith;
			if (logId == l.getLogId()) {
				if (c.playerLevel[11] < l.getLevel()) {
					c.sendMessage("You need a firemaking level of "
							+ l.getLevel() + " to light "
							+ c.getItems().getItemName(logId));
					return;
				}
				if (c.inBank()) {
					c.sendMessage("You cannot light a fire in a bank.");
					return;
				}
				if (c.inEdge()) {
					c.sendMessage("You cannot light a fire in edgeville.");
					return;
				}
				if (Server.objectManager.objectExists(c.absX, c.absY)) {
					c.sendMessage("You cannot light a fire here.");
					return;
				}
				isSkilling[11] = true;
				boolean notInstant = (System.currentTimeMillis() - lastSkillingAction) > 2500;
				int cycle = 2;
				if (notInstant) {
					c.sendMessage("You attempt to light a fire.");
					if (groundObject == false) {
						c.getItems().deleteItem(logId,
								c.getItems().getItemSlot(logId), 1);
						Server.itemHandler.createGroundItem(c, logId, c.absX,
								c.absY, 1, c.playerId);
						logNulled = true;
					}
					cycle = 3 + Misc.random(6);
				} else {
					if (groundObject == false) {
						c.getItems().deleteItem(logId,
								c.getItems().getItemSlot(logId), 1);
					}
				}
				final boolean walk;
				if (Region.getClipping((x - 1), y, c.heightLevel, -1, 0)) {
					walk = true;
				} else {
					walk = false;
				}
				c.startAnimation(733);
				c.getPA().walkTo(walk == true ? -1 : 1, 0);
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						if (c.disconnected) {
							container.stop();
							return;
						}
						if (isSkilling[11] == true) {
							Server.itemHandler.removeGroundItem(c, logId, x,
									c.absY, false);
							new Object(2732, x, y, 0, 0, 10, -1, 60 + Misc
									.random(30));
							c.sendMessage("The fire catches and the log beings to burn.");
							logNulled = false;
							c.firesL += 1;
							if (!c.task1[5]) {
								c.task1[5] = true;
								c.sendMessage("You've completed the task: Light a fire!");
								c.TPoints += 1;
								if (c.TPoints == 1) {
									c.sendMessage("You've received a Task Point! You now have "
											+ c.TPoints + " point!");
								} else {
									c.sendMessage("You've received a Task Point! You now have a total of "
											+ c.TPoints + " points!");
								}
								c.achievementInterface("Light a fire!");
							}
							if (c.firesL >= 150 && !c.task3[1]) {
								GabbesAchievements.handleEliteTask(c, 1, 3,
										"Light 150 fires!");
							}
							CycleEventHandler.getSingleton().addEvent(c,
									new CycleEvent() {
										@Override
										public void execute(
												CycleEventContainer container) {
											if (c.disconnected) {
												container.stop();
												return;
											}
											c.turnPlayerTo(
													walk == true ? (x + 1)
															: (x - 1), y);
											container.stop();
										}

										@Override
										public void stop() {

										}
									}, 2);
							container.stop();
						} else {
							return;
						}
					}

					@Override
					public void stop() {
						c.startAnimation(65535);
						c.getPA()
								.addSkillXP(
										(int) (l.getXp() * Config.FIREMAKING_EXPERIENCE),
										11);
						lastSkillingAction = System.currentTimeMillis();
						isSkilling[11] = false;
					}
				}, cycle);
			}
		}
	}

	// ADD ON FIRES

	public static void handle10Logs(final Client c, final int id) {
		if (c == null) {
			return;
		}
		if (id == 0) {
			c.sendMessage("An error occured. Please try again!");
			return;
		}
		if (!c.getItems().playerHasItem(id, 1)) {
			c.sendMessage("You do not have any logs left.");
			c.getPA().closeAllWindows();
			return;
		}
		c.isWalking = false;
		c.logsOffered = 0;
		c.getPA().closeAllWindows();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(id, 1)) {
					c.sendMessage("You do not have any logs left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(id, 1) && c.logsOffered < 11) {
					// c.turnPlayerTo(c.objectX, c.objectY);
					c.startAnimation(827);
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					for (final LogData.logData l : LogData.logData.values()) {
						c.getPA().addSkillXP((int) (l.getXp()), 11);
					}
					c.logsOffered += 1;
					if (c.logsOffered == 10 || c.logsOffered > 10) {
						c.logsOffered = 0;
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

	public static void handle1Log(final Client c, final int id) {
		if (c == null) {
			return;
		}
		if (id == 0) {
			c.sendMessage("An error occured. Please try again!");
			return;
		}
		if (!c.getItems().playerHasItem(id, 1)) {
			c.sendMessage("You do not have any logs left.");
			c.getPA().closeAllWindows();
			return;
		}
		c.isWalking = false;
		c.getPA().closeAllWindows();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(id, 1)) {
					c.sendMessage("You do not have any logs left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(id, 1)) {
					// c.turnPlayerTo(c.objectX, c.objectY);
					c.startAnimation(827);
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					for (final LogData.logData l : LogData.logData.values()) {
						c.getPA().addSkillXP((int) (l.getXp()), 11);
					}
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

	public static void handle20Logs(final Client c, final int id) {
		if (c == null) {
			return;
		}
		if (id == 0) {
			c.sendMessage("An error occured. Please try again!");
			return;
		}
		if (!c.getItems().playerHasItem(id, 1)) {
			c.sendMessage("You do not have any logs left.");
			c.getPA().closeAllWindows();
			return;
		}
		c.isWalking = false;
		c.logsOffered = 0;
		c.getPA().closeAllWindows();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(id, 1)) {
					c.sendMessage("You do not have any logs left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(id, 1) && c.logsOffered < 21) {
					// c.turnPlayerTo(c.objectX, c.objectY);
					c.startAnimation(827);
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					for (final LogData.logData l : LogData.logData.values()) {
						c.getPA().addSkillXP((int) (l.getXp()), 11);
					}
					c.logsOffered += 1;
					if (c.logsOffered == 20 || c.logsOffered > 20) {
						c.logsOffered = 0;
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

	public static void handle5Logs(final Client c, final int id) {
		if (c == null) {
			return;
		}
		if (id == 0) {
			c.sendMessage("An error occured. Please try again!");
			return;
		}
		if (!c.getItems().playerHasItem(id, 1)) {
			c.sendMessage("You do not have any logs left.");
			c.getPA().closeAllWindows();
			return;
		}
		c.isWalking = false;
		c.logsOffered = 0;
		c.getPA().closeAllWindows();
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (c.isWalking == true) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(id, 1)) {
					c.sendMessage("You do not have any logs left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				if (c.getItems().playerHasItem(id, 1) && c.logsOffered < 6) {
					// c.turnPlayerTo(c.objectX, c.objectY);
					c.startAnimation(827);
					c.getItems()
							.deleteItem(id, c.getItems().getItemSlot(id), 1);
					for (final LogData.logData l : LogData.logData.values()) {
						c.getPA().addSkillXP((int) (l.getXp()), 11);
					}
					c.logsOffered += 1;
					if (c.logsOffered == 5 || c.logsOffered > 5) {
						c.logsOffered = 0;
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

	public static void handleLogsOnFire(final Client c) {

	}

	public static int hasALog(Client c) {
		if (c.getItems().playerHasItem(1511, 1)) {
			nameOfLog = "Logs";
			return 1511;
		}
		if (c.getItems().playerHasItem(2862, 1)) {
			nameOfLog = "Achey tree Logs";
			return 2862;
		}
		if (c.getItems().playerHasItem(1521, 1)) {
			nameOfLog = "Oak Logs";
			return 1521;
		}
		if (c.getItems().playerHasItem(1519, 1)) {
			nameOfLog = "Willow Logs";
			return 1519;
		}
		if (c.getItems().playerHasItem(6333, 1)) {
			nameOfLog = "Teak Logs";
			return 6333;
		}
		if (c.getItems().playerHasItem(10810, 1)) {
			nameOfLog = "Arctic pine Logs";
			return 10810;
		}
		if (c.getItems().playerHasItem(1517, 1)) {
			nameOfLog = "Maple logs";
			return 1517;
		}
		if (c.getItems().playerHasItem(6332, 1)) {
			nameOfLog = "Mahogany logs";
			return 6332;
		}
		if (c.getItems().playerHasItem(12581, 1)) {
			nameOfLog = "";
			return 12581;
		}
		if (c.getItems().playerHasItem(1515, 1)) {
			nameOfLog = "Yew logs";
			return 1515;
		}
		if (c.getItems().playerHasItem(1513, 1)) {
			nameOfLog = "Magic logs";
			return 1513;
		}
		return 0;
	}
}