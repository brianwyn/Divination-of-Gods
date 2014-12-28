package server.model.players.packets.Commands;

import server.Config;
import server.model.players.Client;
import server.util.Misc;

public class ServerHelperCommands {

	public static void handleCommand(Client player, String command,
			String message, String... args) {
		Client otherUser = message != null ? Client.getClient(message) : null;
		switch (command) {
		case "jail":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			if (otherUser.InDung() && !player.isOwner()) {
				player.sendMessage("You cannot Jail/Unjail somone in Dung.");
				return;
			}
			if (otherUser.isOwner()) {
				player.sendMessage("You cannot jail an owner!");
				return;
			}
			otherUser.teleportToX = 3102;
			otherUser.teleportToY = 9516;
			otherUser.heightLevel = 0;
			otherUser.Jail = true;
			otherUser.sendMessage("You have been jailed by "
					+ player.playerName + ".");
			player.sendMessage("Successfully Jailed " + otherUser.playerName
					+ ".");
			break;
		case "kick":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			otherUser.disconnected = true;
			player.sendMessage("Player has been kicked.");
			break;
		case "movehome":
		case "sendhome":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			otherUser.getPA().movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
			otherUser.sendMessage("You've been sent home by "
					+ player.playerName);
			player.sendMessage("Sent " + otherUser.playerName + " home.");
			break;
		case "unjail":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message + ".");
				return;
			}
			otherUser.monkeyk0ed = 0;
			otherUser.Jail = false;
			otherUser.getPA().movePlayer(3086, 3493, 0);
			otherUser.sendMessage("You have been unjailed by "
					+ Misc.formatPlayerName(player.playerName) + ".");
			player.sendMessage("Successfully unjailed "
					+ Misc.formatPlayerName(otherUser.playerName) + ".");
			break;
		}
	}
}
