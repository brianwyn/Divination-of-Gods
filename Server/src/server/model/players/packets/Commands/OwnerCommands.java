package server.model.players.packets.Commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import server.Config;
import server.Server;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;

public class OwnerCommands {

	public static void handleCommand(Client player, String command,
			String message, String... args) {
		Client otherUser = message != null ? Client.getClient(message) : null;
		switch (command) {
		case "alert":
			for (Player p : PlayerHandler.players) {
				if (p != null)
					((Client) p).sendMessage("Alert##Notification##" + message
							+ "##By: " + player.playerName);
			}
			break;
		case "allspins":
			if (player.playerName.equalsIgnoreCase("mod jesse"))
				PlayerHandler.giveAllSpins();
			break;
		case "alltome":
			for (Player p : PlayerHandler.players) {
				if (p != null) {
					((Client) p).getPA().movePlayer(player.getX(),
							player.getY(), player.getHeightLevel());
					((Client) p).sendMessage("Mass teleport to "
							+ player.playerName);
				}
			}
			break;
		case "ally":
			for (Player p : PlayerHandler.players) {
				if (p != null) {
					p.forcedChat("#1 Game out there! Divination of Gods rocks!");
					p.gfx0(1600);
					p.startAnimation(352);
				}
			}
			break;
		case "bounty":
			player.getPA().movePlayer(3179, 3684, 0);
			break;
		case "clearinv":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			otherUser.getItems().removeAllItems();
			otherUser
					.sendMessage("Your inventory has been cleared by a staff member.");
			player.sendMessage("You cleared " + otherUser.playerName
					+ "'s inventory.");
			break;
		case "demote":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			if (otherUser.playerName.equalsIgnoreCase("mod jesse")) {
				player.sendMessage("You cannot demote mod jesse");
				otherUser.sendMessage(player.playerName
						+ " has tried to demote you.");
				return;
			}
			otherUser.playerRights = 0;
			player.sendMessage("You have demoted " + otherUser.playerName);
			otherUser.sendMessage("You've just been demoted!");
			break;
		case "divinationofgods":
			for (Player p : PlayerHandler.players) {
				if (p != null) {
					p.forcedChat("#1 Game out there! JOIN NOW AT divinationofgods.com!");
					p.startAnimation(866);
				}
			}
			break;
		case "dungpoints":
			player.dungPoints += 15000;
			break;
		case "fixnomad":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			otherUser.Nomad = false;
			otherUser.sendMessage("Your nomad kills have been reset by "
					+ player.playerName);
			break;
		case "getip":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			player.sendMessage(otherUser.playerName + "'s host: "
					+ otherUser.connectedFrom);
			break;
		case "givedonor":
		case "giveextreme":
		case "givesuper":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			if (!otherUser.isStaff())
				otherUser.playerRights = 4;
			otherUser.isDonator = command.equals("giveextreme") ? 3 : command
					.equals("givesuper") ? 2 : 1;
			player.sendMessage("You have gave "
					+ (command.equals("giveextreme") ? "extreme" : command
							.equals("givesuper") ? "super" : "")
					+ " donator to " + otherUser.playerName);
			otherUser.sendMessage("You've just been given "
					+ (command.equals("giveextreme") ? "extreme" : command
							.equals("givesuper") ? "super" : "")
					+ " donator by " + player.playerName);
			break;
		case "givemod":
		case "giveadmin":
		case "giveowner":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			if (!player.playerName.equalsIgnoreCase("mod jesse")) {
				player.sendMessage("You do not have the rights to access this command :(");
				return;
			}
			otherUser.playerRights = (command.equals("giveowner") ? 3 : command
					.equals("giveadmin") ? 2 : 1);
			player.sendMessage("You have gave "
					+ (command.equals("giveowner") ? "owner" : command
							.equals("giveadmin") ? "admin" : "mod") + " to "
					+ otherUser.playerName);
			otherUser.sendMessage("You've just been given "
					+ (command.equals("giveowner") ? "owner" : command
							.equals("giveadmin") ? "admin" : "mod")
					+ " by Mod Jesse");
			break;
		case "givedp":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			otherUser.DonorPoint += 5;
			otherUser.sendMessage(player.playerName
					+ " Just gave you 5 Donor Points.");
			player.sendMessage("You just gave " + otherUser.playerName
					+ " 5 Donor Points.");
			break;
		case "giveitem":
			if (message == null || args.length < 4) {
				player.sendMessage("Try ::takeitem itemid amount playername");
				return;
			}
			String oth = "";
			int ite = -1;
			int amt = 0;
			try {
				ite = Integer.parseInt(args[1]);
				amt = Integer.parseInt(args[2]);
				if (args.length == 4)
					oth = args[4].replace("_", " ");
				else if (args.length > 4) {
					for (int i = 4; i < args.length; i++)
						oth += args[i] + " ";
				}
				oth = oth.trim();
				otherUser = Client.getClient(oth);
				if (otherUser == null) {
					player.sendMessage("Cannot find " + oth);
					return;
				}
				if (ite < 1 || amt < 1)
					return;
				player.sendMessage("You have just gave " + ite
						+ " of item number: " + amt + ".");
				otherUser
						.sendMessage("A staff member has just gave you some items!");
				otherUser.getItems().addItem(ite, amt);
			} catch (Exception e) {
				player.sendMessage("Try ::giveitem itemid amount playername");
				return;
			}
			break;
		case "givevote":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			otherUser.votePoints += 10;
			otherUser.sendMessage(player.playerName
					+ " Just gave you 10 Vote Points.");
			player.sendMessage("You just gave " + otherUser.playerName
					+ " 10 Vote Points.");
			break;
		case "kill":
			if (otherUser == null) {
				player.sendMessage("Cannot find player " + message);
				return;
			}
			otherUser.isDead = true;
			otherUser.sendMessage("You have been killed by "
					+ player.playerName);
			break;
		case "npc":
			if (message.length() < 1 || message == null
					|| !Client.isInteger(args[1]))
				return;
			if (Integer.parseInt(args[1]) > 0
					&& Integer.parseInt(args[1]) <= NPCHandler.maxListedNPCs) {
				Server.npcHandler.spawnNpc(player, Integer.parseInt(message),
						player.absX, player.absY, player.heightLevel, 0, 120,
						7, 70, 70, false, false);
				player.sendMessage("You spawn a "
						+ Server.npcHandler.getNpcListName(Integer
								.parseInt(args[1])) + ".");
			} else {
				player.sendMessage("No such NPC.");
			}
			break;
		case "potato":
			for (int i = 0; i < 7; i++) {
				player.playerLevel[3] = 9999;
				player.getPA().refreshSkill(3);
				player.getItems().addItem(5733, 1);
				player.specAmount = 5000.0;
				player.getItems().updateSpecialBar();
				player.sendMessage("You've used the magical potato :o");
			}
			break;
		case "pring":
			for (int i = 0; i < 7; i++) {
				player.playerLevel[i] = 9999;
				player.getPA().refreshSkill(i);
			}
			break;
		case "reloaditems":
			Server.itemHandler.reloadAllItems();
			player.sendMessage("Item list refreshed");
			break;
		case "reloadspawns":
			Server.npcHandler = null;
			Server.npcHandler = new server.model.npcs.NPCHandler();
			PlayerHandler.serverMessage("[" + player.playerName
					+ "] has reloaded NPC SPawns.");
			break;
		case "shutdown":
			for (Player p : PlayerHandler.players) {
				if (p != null) {
					PlayerHandler.updateSeconds = 30;
					PlayerHandler.updateAnnounced = false;
					PlayerHandler.updateRunning = true;
					PlayerHandler.updateStartTime = System.currentTimeMillis();
					((Client) p)
							.sendMessage("<col=00FFFF><shad=0>[Server] <shad=15536940>Automatically saved you're account!");
					((Client) p).SaveGame();
				}
			}
			break;
		case "spawn":
			if (!Client.isInteger(args[1]) || message.length() < 1
					|| message == null)
				return;
			int npcId = Integer.parseInt(args[1]);
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(
					Config.DATA_PATH + "/cfg/spawn-config.cfg", true))) {
				Server.npcHandler.spawnNpc(player, npcId, player.getX(),
						player.getY(), player.getHeightLevel(), 0, 120, 7, 70,
						70, false, false);
				writer.newLine();
				writer.write("spawn = " + npcId + "\t" + player.getX() + "\t"
						+ player.getY() + "\t" + player.getHeightLevel()
						+ "\t0\t0\t0\t0\t Generated_by_"
						+ player.playerName.replace(" ", "_"));
				player.sendMessage("You spawn an NPC. Note: You may have to change it's health/walk type in the cfg.");
			} catch (IOException e) {
				player.sendMessage("Error writing spawn! try ::spawn npcid");
			}
			break;
		case "takeitem":
			if (message == null || args.length < 4) {
				player.sendMessage("Try ::takeitem itemid amount playername");
				return;
			}
			String other = "";
			int item = -1;
			int amount = 0;
			try {
				item = Integer.parseInt(args[1]);
				amount = Integer.parseInt(args[2]);
				if (args.length == 4)
					other = args[4].replace("_", " ");
				else if (args.length > 4) {
					for (int i = 4; i < args.length; i++)
						other += args[i] + " ";
				}
				other = other.trim();
				otherUser = Client.getClient(other);
				if (otherUser == null) {
					player.sendMessage("Cannot find " + other);
					return;
				}
				if (item < 1 || amount < 1)
					return;
				player.sendMessage("You have just removed " + item
						+ " of item number: " + amount + ".");
				otherUser
						.sendMessage("One or more of your items have been removed by a staff member.");
				otherUser.getItems().deleteItem(item, amount);
			} catch (Exception e) {
				player.sendMessage("Try ::takeitem itemid amount playername");
				return;
			}
			break;
		}
	}
}
