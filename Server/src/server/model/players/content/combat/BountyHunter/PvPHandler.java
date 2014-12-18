package server.model.players.content.combat.BountyHunter;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.util.Misc;

/**
 * dead drops pjing your target
 * 
 * @author Jeffrey
 * 
 */
public class PvPHandler {

	/*
	 * Standard interface & frame Id for the interface used
	 */
	static int INTERFACE = 25347, FRAME = 25350, PVP_LEVEL_FRAME = 28502,
			PVP_EP_FRAME = 28503;

	public static boolean set = false;

	public static boolean running1 = false;

	public static boolean running2 = false;

	public static int tick2 = 50;

	public static int tick = 50;

	/**
	 * Assigns a target for
	 * 
	 * @param player
	 */
	public static void assignTarget(Client player) {
		for (Player players : PlayerHandler.players) {
			if (players != null) {
				Client p = (Client) players;
				if (p.logoutTimer <= 0
						&& player.logoutTimer <= 0
						&& p.playerId != player.playerId
						&& isFree(p)
						&& isFree(player)
						&& player.inWild()
						&& p.inWild()
						&& p.targetPercentage >= PvPConfig.TARGET_PERCENTAGE_REQUIRED
						&& (p.combatLevel == player.combatLevel
								|| p.combatLevel == player.combatLevel - 1 || p.combatLevel == player.combatLevel + 1)) {
					setTarget(player, p.playerId, p.playerName);
					setTarget(p, player.playerId, player.playerName);
					p.safeTimer = 500;
					player.safeTimer = 500;
				}
			}
		}
	}

	/**
	 * Gets the colour for the ep percentage
	 * 
	 * @param ep
	 *            player's earned potential
	 * @return colour
	 */
	static String getColourForEp(int ep) {
		if (ep >= 75)
			return "gre";
		if (ep >= 50 && ep < 75)
			return "yel";
		if (ep >= 25 && ep < 50)
			return "or1";
		return "red";
	}

	/**
	 * calculates the difference
	 * 
	 * @param player
	 * @param up
	 *            if you want to calculate up or down
	 * @return level difference
	 */
	static int getLevelDifference(Client player, boolean up) {
		int difference = 0;
		if (up)
			difference = (int) player.getCombatLevel() + player.wildLevel;
		else
			difference = (int) player.getCombatLevel() - player.wildLevel;
		return difference < 3 ? 3 : difference > 126 && up ? 126 : difference;
	}

	/**
	 * Gets the interface Id for your
	 * 
	 * @param percentage
	 * @return interface ID
	 */
	static int getTargetBar(int percentage) {
		if (percentage >= 1 && percentage < 5)
			return INTERFACE + 6;
		else if (percentage >= 5 && percentage < 9)
			return INTERFACE + 12;
		else if (percentage >= 9 && percentage < 20)
			return INTERFACE + 18;
		else if (percentage >= 20 && percentage < 30)
			return INTERFACE + 24;
		else if (percentage >= 30 && percentage < 40)
			return INTERFACE + 30;
		else if (percentage >= 40 && percentage < 50)
			return INTERFACE + 36;
		else if (percentage >= 50 && percentage < 59)
			return INTERFACE + 42;
		else if (percentage >= 59)
			return 26389;
		return INTERFACE;
	}

	/**
	 * Gets the frame for target percentage
	 * 
	 * @param percentage
	 *            target percentage
	 * @return frame ID
	 */
	static int getTargetFrame(int percentage) {
		if (percentage >= 1 && percentage < 5)
			return FRAME + 6;
		else if (percentage >= 5 && percentage < 9)
			return FRAME + 12;
		else if (percentage >= 9 && percentage < 20)
			return FRAME + 18;
		else if (percentage >= 20 && percentage < 30)
			return FRAME + 24;
		else if (percentage >= 30 && percentage < 40)
			return FRAME + 30;
		else if (percentage >= 40 && percentage < 50)
			return FRAME + 36;
		else if (percentage >= 50 && percentage < 59)
			return FRAME + 42;
		else if (percentage >= 59)
			return 26392;
		return FRAME;
	}

	/**
	 * Gets the target's player index by
	 * 
	 * @param name
	 * @return target player index
	 */
	public static int getTargetIndex(String name) {
		for (Player p : PlayerHandler.players)
			if (p != null && p.playerName.equalsIgnoreCase(name))
				return p.playerId;
		return 0;
	}

	/**
	 * Handles player to gain earned potential
	 * 
	 * @param player
	 */
	public static void handleEP(final Client player) {
		player.EP_ACTIVE = true;
		CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
			int timer = 0;
			int tick = 0;

			@Override
			public void execute(CycleEventContainer container) {
				tick++;
				if (tick >= 7) {
					// handleInterfaces(player);
					setBountyIcon(player, player.bountyIcon);
					tick = 0;
				}
				// player.sendMessage(""+player.logoutTimer);
				// player.sendMessage(""+targetIsNull(player.targetName));
				/*
				 * if (playerHasTarget(player) &&
				 * targetIsNull(player.targetName)) { LogTimer(player); }
				 */
				/*
				 * ep system
				 */
				timer++;
				if (timer == 100) {
					if (player.inWild()) {
						player.EP_MINUTES++;
						;
						if (player.targetPercentage < 100)
							player.targetPercentage++;
					}
					timer = 0;
				}
				if (player.EP_MINUTES == PvPConfig.EP_AMOUNT_REQUIRED) {
					player.EP_MINUTES = 0;
					if (player.inWild()
							&& PvPDrops.getTotalNet(player) >= 76000) {
						if (player.EP + PvPConfig.EP_INCREASE_AMOUNT <= 100)
							player.EP += PvPConfig.EP_INCREASE_AMOUNT;
						else
							player.EP = 100;

						player.sendMessage("You've gained "
								+ PvPConfig.EP_INCREASE_AMOUNT + " EP.");
					} else {
						player.sendMessage("You did not gain any EP because you don't have any risk.");

					}
				}
				if (isFree(player) && !player.inWild()) {
					player.getPA().closeAllWindows();
					container.stop();
					return;
				}
				// player.sendMessage("Current timer: "+timer+" total minutes: "+player.EP_MINUTES);
				// player.sendMessage("target percentage: "+player.targetPercentage+" total EP: "+player.EP);
				// player.sendMessage("logout timer: "+player.logoutTimer);
				if (isFree(player)
						&& player.targetPercentage >= PvPConfig.TARGET_PERCENTAGE_REQUIRED)
					assignTarget(player);
				if (!player.inWild()) {
					if (!playerHasTarget(player))
						container.stop();
					else {
						safeTimer(player);
					}
				}
			}

			@Override
			public void stop() {
				player.EP_ACTIVE = false;
			}
		}, 1);
	}

	/**
	 * Handles the interfaces
	 * 
	 * @param player
	 *            player sending the interfaces
	 */
	public static void handleInterfaces(Client player) {
		player.getPA().walkableInterface(getTargetBar(player.targetPercentage));
		player.getPA().sendFrame126(
				playerHasTarget(player) ? player.targetName : "None",
				getTargetFrame(player.targetPercentage));
		player.getPA().sendFrame126(
				"EP: @" + getColourForEp(player.EP) + "@" + player.EP + "%",
				PVP_EP_FRAME);
		player.getPA().sendFrame126(
				getLevelDifference(player, false) + " - "
						+ getLevelDifference(player, true), PVP_LEVEL_FRAME);
	}

	/**
	 * Handles the login
	 * 
	 * @param player
	 *            player loggin in
	 */
	public static void handleLogin(Client player) {
		Client target = (Client) PlayerHandler.players[player.targetIndex];
		if (target != null && target.logoutTimer > 0) {
			target.sendMessage("Your target has logged in and you resigned him as a target!");
			setTarget(target, player.playerId, player.playerName);
			setTarget(player, target.playerId, target.playerName);
			safeTimer(player);
		} else {
			setTarget(player, 0, null);
		}
		if (target != null && target.logoutTimer <= 2) {
			resetTarget(player, target);
		}
		if (player.inWild())
			handleEP(player);
	}

	/**
	 * Resets the target
	 * 
	 * @param player
	 */
	/**
	 * Handles the logout
	 * 
	 * @param player
	 */
	public static void handleLogout(Client player) {
		Client target = (Client) PlayerHandler.players[player.targetIndex];
		if (target != null) {
			target.sendMessage("Your target has left this world. You will receive a new target.");
			// target.targetIndex = 0;
			// target.safeTimer = 500;
			resetTarget(player, target);
			// target.logoutTimer = 300;
		}
	}

	public static void handleSafe(Client player) {
		Client target = (Client) PlayerHandler.players[player.targetIndex];
		if (target != null) {
			target.sendMessage("Your target spent to much time in safe-area and has been reset.");
			target.targetIndex = 0;
			resetTarget(player, target);
		}
	}

	public static void handleTeleTab(Client c, boolean confirmed) {
		Client target = (Client) PlayerHandler.players[c.targetIndex];
		if (target != null) {
			if (target.wildLevel >= 20 && !confirmed) {
				c.getDH().sendDialogues(3551, 1);
				c.teleAction = 535;
				return;
			}
			c.getPA().teleTabTeleport(target.absX, target.absY - 1, 0,
					"teleTab");
			c.getItems().deleteItem(8012, c.getItems().getItemSlot(8012), 1);
			return;
		}
	}

	public static boolean isFree(Client c) {
		if (c.targetIndex == 0 || c.targetIndex == -1)
			return true;
		if (c.targetName == null || c.targetName.equalsIgnoreCase("None"))
			return true;

		return false;

	}

	public static void LogTimer(final Client player) {
		if (!running2) {
			CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					running2 = true;
					player.sendMessage("" + player.logoutTimer + "");
					if (playerHasTarget(player)
							&& targetIsNull(player.targetName)) {
						if (player.logoutTimer >= 0
								&& targetIsNull(player.targetName))
							player.logoutTimer--;
						tick++;
						if (targetIsNull(player.targetName) && tick >= 50) {
							player.sendMessage("Your target has "
									+ player.logoutTimer
									/ 100
									+ " "
									+ (player.logoutTimer == 100 ? "minute"
											: "minutes")
									+ " to return else you will be assigned anothern target.");
							tick = 0;
						}
						if (player.logoutTimer == 1
								&& targetIsNull(player.targetName)) {
							player.sendMessage("Your target left this world and did not return.");
							player.getPA().createPlayerHints(-1,
									player.targetIndex);
							container.stop();

						}
						if (player.disconnected || !playerHasTarget(player)
								|| !targetIsNull(player.targetName)) {
							container.stop();
							return;
						}
					}
				}

				@Override
				public void stop() {
					running2 = false;
				}
			}, 2);

		}
	}

	/**
	 * Checks if you have a target
	 * 
	 * @param player
	 * @return
	 */
	static boolean playerHasTarget(Client player) {
		return player.targetName != "" || player.targetName != null
				|| !player.targetName.equalsIgnoreCase("None");
	}

	static void resetTarget(Client player, Client target) {
		if (target == null || player == null) {
			return;
		}
		target.getPA().createPlayerHints(-1, player.targetIndex);
		player.getPA().createPlayerHints(-1, player.playerId);
		player.targetIndex = 0;
		player.targetName = "None";
		player.targetPercentage = 0;
		player.safeTimer = 500;
		player.logoutTimer = 300;
		target.logoutTimer = 300;
		target.safeTimer = 500;
		target.targetIndex = 0;
		target.targetName = "None";
	}

	static void resetTarget2(Client player) {
		if (PlayerHandler.players[player.targetIndex] != null) {
			player.getPA().createPlayerHints(-1, player.targetIndex);
			((Client) PlayerHandler.players[player.targetIndex]).getPA()
					.createPlayerHints(-1, player.playerId);
			PlayerHandler.players[player.targetIndex].targetIndex = 0;
			PlayerHandler.players[player.targetIndex].safeTimer = 1000;
			PlayerHandler.players[player.targetIndex].targetName = "None";
			PlayerHandler.players[player.targetIndex].targetPercentage = 0;
		}
		player.targetIndex = 0;
		player.targetPercentage = 0;
		player.targetName = "None";
		player.safeTimer = 1000;
	}

	public static void safeTimer(final Client player) {
		if (!running1) {
			CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					running1 = true;
					if (isFree(player)) {
						container.stop();
						return;
					}
					if (!player.inWild()) {
						if (!playerHasTarget(player))
							container.stop();
						else {
							if (player.safeTimer <= 1 && !isFree(player)) {
								player.sendMessage("You've lost your target because you spent to much time in a safe-area.");
								handleSafe(player);
								container.stop();
							}
							if (player.safeTimer > 0)
								player.safeTimer--;
							tick2++;
							if (tick2 >= 50) {
								player.sendMessage("You have "
										+ player.safeTimer
										/ 100
										+ " minutes to enter the wild or you will loose your target!");
								tick2 = 0;
								Client target = (Client) PlayerHandler.players[player.targetIndex];
								if (target != null)
									target.sendMessage("Your target is in a safe zone and have "
											+ player.safeTimer
											/ 100
											+ " minutes to return.");
							}
						}
					}
					if (player.disconnected || player.inWild()
							|| !isFree(player)) {
						container.stop();
						return;
					}

				}

				@Override
				public void stop() {
					running1 = false;
				}
			}, 2);

		}
	}

	public static void setBountyIcon(Client c, int ID) {
		if (c == null) {
			return;
		}

		if (Misc.random(50) == 1 && ID != 0) {
			c.sendMessage("Note: You are skulled.");
		}

		if (ID > 1 && ID < 7) {
			c.isSkulled = true;
			c.skullTimer = 1000;
			c.headIconPk = ID;
			c.bountyIcon = ID;
			c.hasBountyIcon = true;
			set = false;
			// if(!c.getTimers().runningf)
			// c.getTimers().skullTimer(c);
		}

		if (ID == -1) {
			c.isSkulled = true;
			c.skullTimer = 1000;
			c.bountyIcon = 2;
			c.headIconPk = 2;
			c.hasBountyIcon = true;
			set = false;
			// if(!c.getTimers().runningf)
			// c.getTimers().skullTimer(c);
		}

		if (ID == 0) { // Removes icon!
			c.isSkulled = false;
			c.skullTimer = -1;
			c.attackedPlayers.clear();
			c.headIconPk = -1;
			c.hasBountyIcon = false;
			set = false;
		}

		if (!set) {
			update(c);
			set = true;
		}

		return;
	}

	/**
	 * Sets a player target
	 * 
	 * @param player
	 * @param targetPlayerId
	 * @param targetName
	 */
	static void setTarget(Client player, int targetPlayerId, String targetName) {
		player.targetIndex = targetPlayerId;
		player.targetName = targetName;
		if (PlayerHandler.players[targetPlayerId] != null) {
			player.getPA().createPlayerHints(10, player.targetIndex);
		}
	}

	/**
	 * Checks if the target is a null
	 * 
	 * @param targetName
	 *            players target name
	 * @return null or not
	 */
	static boolean targetIsNull(String targetName) {
		if (targetName == null || targetName.equalsIgnoreCase("None"))
			return false;
		for (Player p : PlayerHandler.players)
			if (p != null && p.playerName.equalsIgnoreCase(targetName))
				return false;

		return true;
	}

	public static void update(Client c) {
		c.getPA().requestUpdates();
	}
}