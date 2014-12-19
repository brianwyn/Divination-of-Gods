package server.model.players.content.skills;

import server.Config;
import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.objects.Object;
import server.model.players.Client;
import server.model.players.content.GabbesAchievements;
import server.model.players.content.skills.LogData.logData;
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

	public static logData getLogData(int logId) {
		for (final LogData.logData l : LogData.logData.values()) {
			if (l.getLogId() == logId)
				return l;
		}
		return null;
	}

	public static logData getLogData(Client c) {
		for (final logData log : logData.values())
			if (c.getItems().playerHasItem(log.getLogId()))
				return log;
		return null;
	}

	public static boolean handleBonfire(Client c, int logId) {
		if (getLogData(logId) == null || c.playerIsFiremaking
				|| !c.getItems().playerHasItem(logId))
			return false;

		c.startAnimation(827);
		c.getItems().deleteItem(getLogData(logId).getLogId(), 1);
		c.getPA().addSkillXP((int) (getLogData(logId).getXp()), 11);
		c.playerIsFiremaking = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			logData usedLog = getLogData(logId);

			@Override
			public void execute(CycleEventContainer container) {
				if (!c.playerIsFiremaking) {
					container.stop();
					return;
				}
				if (!c.getItems().playerHasItem(usedLog.getLogId(), 1))
					usedLog = getLogData(c);
				if (usedLog == null) {
					c.sendMessage("You do not have any logs left.");
					c.getPA().closeAllWindows();
					container.stop();
					return;
				}
				c.startAnimation(827);
				c.getItems().deleteItem(usedLog.getLogId(), 1);
				c.getPA().addSkillXP((int) (usedLog.getXp()), 11);
			}

			@Override
			public void stop() {
				c.startAnimation(65535);
				c.playerIsFiremaking = false;
			}
		}, 4);
		return true;
	}
}