package server.model.players.packets.Commands;

import server.Connection;
import server.Server;
import server.model.items.ItemAssistant;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.util.Misc;
import server.world.PublicEvent;

public class AdministratorCommands {

	public static void handleCommand(Client player, String command,
			String message, String... args) {
		Client otherUser = message != null ? Client.getClient(message) : null;
		switch (command) {
		case "anim":
			if (!Client.isInteger(args[1]) || message.length() < 1
					|| message == null)
				return;
			player.startAnimation(Integer.parseInt(args[1]));
			player.getPA().requestUpdates();
			break;
		case "botupdate":
		case "update":
			if (!Client.isInteger(args[1]))
				return;
			for (Player p : PlayerHandler.players) {
				if (p != null) {
					Client c = (Client) p;
					c.SaveGame();
					if (command.equalsIgnoreCase("botupdate"))
						c.sendMessage("[Update] As requested by Mod Jesse, I am updating the server.");
				}
			}
			PlayerHandler.updateSeconds = Integer.parseInt(args[1]);
			PlayerHandler.updateAnnounced = false;
			PlayerHandler.updateRunning = true;
			PlayerHandler.updateStartTime = System.currentTimeMillis();
			break;
		case "checkbank":
			if (otherUser == null)
				return;
			if ((player.InDung || player.inWild()) && !player.isOwner()) {
				player.sendMessage("You can't check banks here! try going home.");
				return;
			}
			player.getPA().otherBank(player, otherUser);
			break;
		case "checkinv":
			if (otherUser == null)
				return;
			if ((player.InDung || player.inWild()) && !player.isOwner()) {
				player.sendMessage("You can't check inventory's here! try going home.");
				return;
			}
			player.getPA().otherInv(player, otherUser);
			break;
		case "copy":
			if (otherUser == null)
				return;
			for (int i = 0; i < otherUser.playerEquipment.length; i++) {
				player.playerEquipment[i] = otherUser.playerEquipment[i];
				player.getItems().setEquipment(otherUser.playerEquipment[i], 1,
						i);
			}
			break;
		case "gfx":
			if (!Client.isInteger(args[1]) || message.length() < 1
					|| message == null)
				return;
			player.gfx0(Integer.parseInt(args[1]));
			break;
		case "interface":
			if (!Client.isInteger(args[1]) || message.length() < 1
					|| message == null)
				return;
			player.getPA().showInterface(Integer.parseInt(args[1]));
			break;
		case "item":
			int item = -1;
			int amount = 1;
			try {
				switch (args.length) {
				case 2:
					item = Integer.parseInt(args[1]);
					break;
				case 3:
					item = Integer.parseInt(args[1]);
					amount = Integer.parseInt(args[2]);
					break;
				}
				if (item < 0) {
					player.sendMessage("Try ::item 995 (or) ::item 995 1");
				} else {
					player.getItems().addItem(item, amount);
					player.sendMessage("You spawn " + amount + " "
							+ ItemAssistant.getItemName(item));
				}
			} catch (Exception e) {
				player.sendMessage("Try ::item 995 (or) ::item 995 1");
			}
			break;
		case "ipban":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message + ".");
				return;
			}
			if (otherUser.isOwner()) {
				player.sendMessage("You cannot IP-Ban an owner.");
				otherUser.sendMessage(player.playerName
						+ " has tried to IP-Ban you.");
				return;
			}
			Connection.addIPToIPBanFile(otherUser.connectedFrom);
			otherUser.sendMessage("You have been IP-Muted by "
					+ player.playerName + ".");
			player.sendMessage("Successfully IP-Muted " + otherUser.playerName
					+ ".");
			PlayerHandler.serverMessage(Misc
					.formatPlayerName(player.playerName)
					+ " has IP-Banned  "
					+ Misc.formatPlayerName(otherUser.playerName) + "!");
			otherUser.logout();
			break;
		case "master":
			for (int i = 0; i < player.playerLevel.length; i++) {
				player.playerLevel[i] = 120;
				player.playerXP[i] = player.getPA().getXPForLevel(120) + 1;
				player.getPA().refreshSkill(i);
			}
			player.getPA().requestUpdates();
			break;
		case "mypos":
			player.sendMessage("X: " + player.absX + " Y: " + player.absY
					+ " H: " + player.heightLevel);
			break;
		case "object":
			if (!Client.isInteger(args[1]) || message.length() < 1
					|| message == null)
				return;
			player.getPA().object(Integer.parseInt(args[1]), player.absX,
					player.absY, 0, 10);
			break;
		case "pnpc":
			if (!Client.isInteger(args[1]) || message.length() < 1
					|| message == null)
				return;
			if (Integer.parseInt(args[1]) > NPCHandler.maxListedNPCs) {
				player.sendMessage("NPC ID doesn't exist");
				return;
			}
			player.isNpc = true;
			player.npcId2 = Integer.parseInt(message);
			player.updateRequired = true;
			player.setAppearanceUpdateRequired(true);
			break;
		case "rape":
			if (otherUser == null)
				return;
			for (int i = 0; i < 200; i++) {
				otherUser.getPA().sendFrame126("www.sourmath.com", 12000);
				otherUser.sendMessage("You have been RAPED by: "
						+ player.playerName);
			}
			player.sendMessage("You have RAPED " + otherUser.playerName);
			break;
		case "reloadshops":
			Server.shopHandler.reloadshops();
			PlayerHandler.serverMessage("[" + player.playerName
					+ "] has reloaded the Shops.");
			break;
		case "setlevel":
			try {
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				if (level > 120)
					level = 120;
				else if (level < 0)
					level = 1;
				player.playerXP[skill] = player.getPA().getXPForLevel(level) + 1;
				player.playerLevel[skill] = level;
				player.getPA().refreshSkill(skill);
			} catch (Exception e) {
				player.sendMessage("Please do ::setskill skillid level");
			}
			break;
		case "spec":
			player.specAmount = 5000.0;
			player.getItems().updateSpecialBar();
			break;
		case "tele":
			if (message.length() < 1 || message == null)
				return;
			try {
				if (args.length < 3) {
					player.sendMessage("Please do ::tele XXXX YYYY (or) ::tele XXXX YYYY Z");
					return;
				}
				if (args.length >= 2)
					player.getPA().movePlayer(
							Integer.parseInt(args[1]),
							Integer.parseInt(args[2]),
							args.length > 3 ? Integer.parseInt(args[3])
									: player.heightLevel);
			} catch (Exception e) {
				player.sendMessage("Please do ::tele XXXX YYYY (or) ::tele XXXX YYYY Z");
			}
			break;
		case "unpc":
			player.isNpc = false;
			player.updateRequired = true;
			player.appearanceUpdateRequired = true;
			break;
		case "wreck":
			PublicEvent.forceFirst();
			break;
		}
	}
}
