package server.model.players.packets.Commands;

import server.model.players.Client;
import server.util.Misc;

public class DonatorCommands {

	public static void handleCommand(Client player, String command,
			String message, String... args) {
		switch (command) {
		case "bank":
			if (player.isStaff())
				return;
			if (player.inDuelArena() || player.InDung()
					|| player.inDungBossRoom()) {
				player.sendMessage("You can't bank here!");
				return;
			}
			player.getPA().openUpBank(0);
			player.isBanking = true;
			break;
		case "dzone":
			player.getPA().startTeleport(2838, 10209, 0, "modern");
			break;
		case "god":
			if (player.isDonator < 2)
				return;
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
		case "settag":
			if (player.isDonator < 4 && !player.isStaff()) {
				player.sendMessage("Only Premium donators may use this feature.");
				return;
			}
			if (message.length() < 1 || message.length() > 12) {
				player.sendMessage("Yell tag must be 1-12 characters long!");
				return;
			}
			String[] blocked = { "owner", "mod", "moderator", "admin", "join",
					"shit", "fuck" };
			for (int i = 0; i < blocked.length; i++) {
				if (message.toLowerCase().contains(blocked[i])) {
					player.sendMessage("Tag Blocked. Abuse = Ban");
					return;
				}
			}
			player.customYellTag = Misc.optimizeText(message);
			player.sendMessage("You've changed your Yell-tag to: "
					+ player.customYellTag);
			break;
		case "szone":
			player.getPA().startTeleport(2340, 3684, 0, "modern");
			break;
		}
	}
}
