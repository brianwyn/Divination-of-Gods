package server.model.players.packets.Commands;

import server.Connection;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.util.Misc;

public class ModeratorCommands {

	public static void handleCommand(Client player, String command,
			String message, String... args) {
		Client otherUser = message != null ? Client.getClient(message) : null;
		switch (command) {
		case "afk":
			PlayerHandler.serverMessage("[" + player.playerName
					+ "] is now AFK, don't message me; I won't reply");
			break;
		case "assist":
			player.getPA().startTeleport(2391, 9899, 0, "modern");
			break;
		case "ban":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			player.sendMessage("Player has been banned.");
			PlayerHandler.serverMessage(Misc
					.formatPlayerName(player.playerName)
					+ " has banned  "
					+ Misc.formatPlayerName(otherUser.playerName) + "!");
			Connection.addNameToBanList(otherUser.playerName);
			otherUser.disconnected = true;
			break;
		case "bank":
			if ((player.inDuelArena() || player.InDung() || player
					.inDungBossRoom()) && !player.isAdmin()) {
				player.sendMessage("You can't bank here!");
				return;
			}
			player.getPA().openUpBank(0);
			player.isBanking = true;
			break;
		case "god":
			if (player.InDung() || player.inDungBossRoom()
					|| player.inDuelArena()) {
				player.sendMessage("You can't do that here.");
				break;
			}
			if (player.playerStandIndex != 1501) {
				player.startAnimation(1500);
				player.playerStandIndex = 1501;
				player.playerTurnIndex = 1851;
				player.playerWalkIndex = 1851;
				player.playerTurn180Index = 1851;
				player.playerTurn90CWIndex = 1501;
				player.playerTurn90CCWIndex = 1501;
				player.playerRunIndex = 1851;
				player.updateRequired = true;
				player.appearanceUpdateRequired = true;
				player.sendMessage("You turn God mode on.");
			} else {
				player.playerStandIndex = 0x328;
				player.playerTurnIndex = 0x337;
				player.playerWalkIndex = 0x333;
				player.playerTurn180Index = 0x334;
				player.playerTurn90CWIndex = 0x335;
				player.playerTurn90CCWIndex = 0x336;
				player.playerRunIndex = 0x338;
				player.updateRequired = true;
				player.appearanceUpdateRequired = true;
				player.sendMessage("Godmode has been diactivated.");
			}
			break;
		case "ipmute":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message + ".");
				return;
			}
			Connection.addIpToMuteList(otherUser.connectedFrom);
			otherUser.sendMessage("You have been IP-Muted by "
					+ player.playerName + ".");
			player.sendMessage("Successfully IP-Muted " + otherUser.playerName
					+ ".");
			PlayerHandler.serverMessage(Misc
					.formatPlayerName(player.playerName)
					+ " has IP-Muted  "
					+ Misc.formatPlayerName(otherUser.playerName) + "!");
			break;
		case "mute":
			Connection.addNameToMuteList(message);
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message
						+ ". Name was still added to mute list.");
				return;
			}
			otherUser.isMuted = true;
			otherUser.sendMessage("You have been muted by " + player.playerName
					+ ".");
			player.sendMessage("Successfully Muted " + otherUser.playerName
					+ ".");
			break;
		case "saveall":
			for (Player p : PlayerHandler.players) {
				if (p != null) {
					((Client) p)
							.sendMessage("[Server] All accounts were just saved by "
									+ player.playerName);
					((Client) p).SaveGame();
				}
			}
			break;
		case "staffzone":
			player.getPA().startTeleport(2957, 3218, 0, "modern");
			break;
		case "unban":
			if (message == null || message.length() < 1)
				return;
			if (Connection.removeNameFromBanList(message))
				player.sendMessage(message + " has been unbanned.");
			else
				player.sendMessage("Couldn't find " + message
						+ " on the ban list.");
			break;
		case "unipmute":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message + ".");
				return;
			}
			Connection.unIPMuteUser(otherUser.connectedFrom);
			otherUser.isMuted = false;
			otherUser.sendMessage("You have been Un-IP-Muted by "
					+ player.playerName + ".");
			player.sendMessage("Successfully Un-IP-Muted "
					+ otherUser.playerName + ".");
			break;
		case "unmute":
			if (message == null || message.length() < 1)
				return;
			Connection.unMuteUser(message);
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message
						+ ". Name was still removed from mute list.");
				return;
			}
			otherUser.isMuted = false;
			otherUser.sendMessage("You have been Un-Muted by "
					+ player.playerName + ".");
			player.sendMessage("Successfully Un-Muted " + otherUser.playerName
					+ ".");
			break;
		case "xteleto":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message + ".");
				return;
			}
			if (player.InDung() && !player.isOwner()) {
				player.sendMessage("You can't teleport in dungeoneering.");
				return;
			}
			if (otherUser.InDung() && !player.isOwner()) {
				player.sendMessage("The other player is in dung, so you can't do that.");
				return;
			}
			player.getPA().movePlayer(otherUser.getX(), otherUser.getY(),
					otherUser.heightLevel);
			break;
		case "xteletome":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message + ".");
				return;
			}
			if ((player.inCwGame || player.inWild() || player.InDung())
					&& !player.isOwner()) {
				player.sendMessage("You're in a bad position to teleport them to. Try going home.");
				return;
			}
			if ((otherUser.inCwGame || otherUser.inWild() || otherUser.InDung())
					&& !player.isOwner()) {
				player.sendMessage("The other player is busy. Try again later...");
				return;
			}
			otherUser.getPA().movePlayer(player.getX(), player.getY(),
					player.heightLevel);
			break;
		}
	}
}
