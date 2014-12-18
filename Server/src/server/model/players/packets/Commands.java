package server.model.players.packets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.Vote.MainLoader;
import org.Vote.VoteReward;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.RapeCommand;
import server.util.Misc;
import server.world.PublicEvent;

/**
 * Commands
 **/
public class Commands implements PacketType {
	public static String authcode;

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		String playerCommand = c.getInStream().readString();
		PublicEvent.processEntry(c, playerCommand);
		if (c.playerRights >= 2) {
			if (playerCommand.startsWith("wreck"))
				PublicEvent.forceFirst();
		}
		playerCommand.split("-");
		if (!playerCommand.startsWith("/")) {
			c.getPA().writeCommandLog(playerCommand);
		}

		if (Config.SERVER_DEBUG)
			Misc.println(c.playerName + " playerCommand: " + playerCommand);

		if (c.playerRights >= 0)
			playerCommands(c, playerCommand);
		if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 3)
			moderatorCommands(c, playerCommand);
		if (c.playerRights == 2 || c.playerRights == 3)
			administratorCommands(c, playerCommand);
		if (c.playerRights == 3)
			ownerCommands(c, playerCommand);
		if (c.isDonator > 0 && c.isDonator != 0)
			DonatorCommands(c, playerCommand);
		if (c.playerRights == 5)
			HelperCommands(c, playerCommand);
	}

	public void playerCommands(Client c, String playerCommand) {


		if (playerCommand.equalsIgnoreCase("resettask")) {
			c.slayerTask = 0;
			c.taskAmount = 0;
			c.getPA().calculateTask();
			c.sendMessage("Your Slayer task has been reset.");
		}

		
		if (playerCommand.startsWith("maxhitmelee")) {
			c.sendMessage("Melee Max Hit: "
					+ c.getCombat().calculateMeleeMaxHit() + "");
		}

		if (playerCommand.equalsIgnoreCase("resetdisplay")) {
			Connection.deleteFromFile("./Data/displaynames.txt", c.displayName);
			c.displayName = c.playerName;
			c.sendMessage("You reset your display name to your original name!");
			c.getPA().requestUpdates();
		}

		if (playerCommand.startsWith("display")) {
			String displayName = playerCommand.substring(8);
			if (displayName.length() > 12) {
				c.sendMessage("Your display name can not be more than 12 characters!");
				return;
			}
			if (!displayName.matches("[A-Za-z0-9]+")) {
				c.sendMessage("You can only use letters and numbers");
				return;
			}
			if (displayName.endsWith(" ") || displayName.startsWith(" ")) {
				displayName = displayName.trim();
				c.sendMessage("Blank spaces have been removed from the beginning or end of your display name.");
			}
			if (c.getPA().checkDisplayName(displayName)) {
				c.sendMessage("This username is already taken!");
				return;
			}
			if (c.getPA().playerNameExists(displayName)) {
				c.sendMessage("This username is already taken!");
				return;
			}
			if (c.playerName != c.displayName) {
				Connection.deleteFromFile("./Data/displaynames.txt",
						c.displayName);
			}
			if (!c.getItems().playerHasItem(995, 250000000)) {
				c.sendMessage("You need to have atleast 250M GP to do this!");
				return;
			}
			c.getPA().createDisplayName(displayName);
			c.displayName = displayName;
			c.getPA().requestUpdates();
			c.sendMessage("Your display name is now " + c.displayName + ". ");
			c.getItems().deleteItem(995, 250000000);
		}

		if (playerCommand.startsWith("staffzone")) {
			c.getPA().startTeleport(2957, 3218, 0, "modern"); // rimmngton
		}

		if (playerCommand.equalsIgnoreCase("check")
				|| playerCommand.equalsIgnoreCase("reward")) {
			try {
				VoteReward reward = MainLoader.hasVoted(c.playerName
						.replaceAll(" ", "_"));
				if (reward != null) {
					switch (reward.getReward()) {
					case 0:
						c.votePoints += 10;
						c.getItems().addItem(995, 10000000);
						PlayerHandler
								.yell("<col=00FFFF><shad=0>"
										+ Misc.optimizeText(c.playerName)
										+ " just Voted With ::vote And Received 10 vote points + 10m");
						break;

					default:
						c.sendMessage("Reward not found.");
						break;
					}
					c.sendMessage("Thank you for voting.");
					c.sendMessage("You now have a total of " + c.votePoints
							+ " Voting points!");
				} else {
					c.sendMessage("You have no items waiting for you.");
				}
			} catch (Exception e) {
				c.sendMessage("[GTL Vote] A SQL error has occurred PLEASE REPORT TO ADMIN/OWNER.");
			}
		}

		if (playerCommand.equalsIgnoreCase("titlereset")) {
			c.getDH().sendOption2("Reset Title", "Never mind");
			c.dialogueAction = 0;
			c.teleAction = 1;
		}

		playerCommand.split("-");
		if (playerCommand.startsWith("save")
				&& !playerCommand.startsWith("savehs")) {
			c.SaveGame();
			c.sendMessage("You account has been saved!");
		}
		if (playerCommand.startsWith("donate")) {
			c.getPA().sendFrame126("http://divinationofgods.com/donate.php",
					12000);
			c.sendMessage("Donating helps the server in MANY WAYS!");
		}
		if (playerCommand.startsWith("commands")) {
			c.sendMessage("The Commands Are: ::vote, ::donate, ::train, ::highscores,");
			c.sendMessage("::resetdisplay, ::titlereset, ::claimpayment, ::check, ::sit, ::unsit ::changepassword (pw)");
			c.sendMessage("::qpsk, ::resetcommands, ::chill, ::download, ::save, ::help, ::help, ::display");
		}
		if (playerCommand.startsWith("unpc")) {
			c.isNpc = false;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (playerCommand.startsWith("vote")) {
			c.getPA()
					.sendFrame126(
							"http://runelocus.com/top-rsps-list/vote-40379-Divination%20Of%20Gods/",
							12000);
			c.sendMessage("Donating helps the server in MANY WAYS!");
		}
		if (playerCommand.startsWith("highscores")) {
			c.getPA().sendFrame126("divinationofgods.com/hs/highscores.php",
					12000);
			c.sendMessage("Donating helps the server in MANY WAYS!");
		}
		if (playerCommand.startsWith("help")) {
			c.getPA().sendFrame126("divinationofgods.com/forums.php", 12000);
			c.sendMessage("Donating helps the server in MANY WAYS!");
		}
		if (playerCommand.startsWith("download")) {
			c.getPA().sendFrame126("divinationofgods.com", 12000);
		}

		if (playerCommand.equalsIgnoreCase("deletepin")) {
			if (c.enterdBankpin) {
				c.firstPin = 0;
				c.secondPin = 0;
				c.thirdPin = 0;
				c.fourthPin = 0;
				c.playerBankPin = 0;
				c.fourthPinEnter = false;
				c.thirdPinEnter = false;
				c.secondPinEnter = false;
				c.firstPinEnter = false;
				c.hasBankPin = false;
				c.firstPin = 0;
				c.secondPin = 0;
				c.thirdPin = 0;
				c.fourthPin = 0;
				c.attemptsRemaining = 3;
				c.lastPinSettings = -1;
				c.deletePinDate = -1;
				c.openBank = false;
				c.sendMessage("PIN HAS BEEN DELETED - HIGHLY RECOMMENDED TO SET A NEW");
			} else {
				c.sendMessage("Please enter your PIN first - Visit the nearest bank and enter PIN.");
				c.openBank = false;
			}
		}
		if (playerCommand.startsWith("real")) {
			c.getPA().sendFrame126("desolace.org", 12000);
		}
		if (playerCommand.equalsIgnoreCase("barbdamage")) {
			if (c.inBarbDef) {
				c.sendMessage("Damage dealt : " + c.barbDamage + " ");
			} else {
				c.sendMessage("Your not in the minigame!");
			}
		}

		if (playerCommand.equalsIgnoreCase("endgame")) {
			if (c.inBarbDef) {
				Server.barbDefence.endGame(c, false);
			} else {
				c.sendMessage("Your not in the minigame!");
			}
		}
		// lol i assume that will work :P

		if (playerCommand.equalsIgnoreCase("players")) {
			c.sendMessage("There are currently "
					+ (PlayerHandler.getPlayerCount()) + " players online.");
		}

		if (playerCommand.startsWith("changepassword")
				&& playerCommand.length() > 15) {
			c.playerPass = playerCommand.substring(15);
			c.sendMessage("Your password is now: " + c.playerPass);
			if (c.hasRecov >= 1) {
				try {
					String urlString = "http://insanityx.net/recovery/update.php?key=3293029420439231&username="
							+ c.playerName + "&new_pass=" + c.playerPass + "";
					urlString = urlString.replaceAll(" ", "%20");
					URL url = new URL(urlString);
					new BufferedReader(new InputStreamReader(url.openStream()));
				} catch (MalformedURLException e) {
					System.out
							.println("Something is wrong with email URL. Website maybe down?");
				} catch (IOException e) {
					System.out.println("IO Exception in inserting email");
				}
			}
		}

		if (playerCommand.startsWith("sit") && c.sit == false) {
			if (c.InDung()) {
				c.sendMessage("You cannot sit in Dungoneering");
				return;
			}
			if (c.inWild()) {
				c.sendMessage("You cannot do this in wilderness");
				return;
			}
			c.sit = true;
			c.stopPlayerSkill = true;
			if (c.playerRights == 1) {
				c.startAnimation(4113);
			}
			if (c.playerRights == 2 || c.playerRights == 3) {
				c.startAnimation(4117);
			}
			if (c.isDonator == 0) {
				c.startAnimation(4115);
			}
			if (c.playerRights == 4) {
				c.startAnimation(4116);
			}
		}
		if (playerCommand.startsWith("unsit") && c.sit == true) {
			if (c.InDung()) {
				c.sendMessage("You cannot un-sit in Dungoneering");
				return;
			}
			if (c.inWild()) {
				c.sendMessage("You cannot do this in the wilderness.");
				return;
			}
			c.sit = false;
			c.stopPlayerSkill = false;
			c.startAnimation(4191);
		}
		if (playerCommand.startsWith("qpsk")) {
			c.startAnimation(4945);
			c.gfx0(816);
		}
		if (playerCommand.equals("empty")) {
			if (c.inWild())
				return;
			c.getPA().removeAllItems();
		}

		/*
		 * if (playerCommand.equalsIgnoreCase("save")) { c.SaveGame();
		 * c.sendMessage("Your acc has been saved."); }
		 */

		if (playerCommand.startsWith("resetdef")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 1;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resetrange")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 4;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resetmage")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 6;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resetattack")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 0;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resetstrength")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 2;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}
		if (playerCommand.startsWith("resetprayer")) {
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.sendMessage("Please take all your armour and weapons off before using this command.");
					return;
				}
			}
			try {
				int skill = 5;
				int level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}
		}

		if (playerCommand.equals("agility")) {
			c.getPA().startTeleport(2480, 3437, 0, "modern");
		}
		if (playerCommand.startsWith("rules")) {
			c.getPA().sendFrame126("divinationofgods.com/forums.php", 12000);

		}

		if (playerCommand.startsWith("resetcommands")) {
			c.sendMessage("::Resetattack");
			c.sendMessage("::Resetdef");
			c.sendMessage("::resetstrength");
			c.sendMessage("::resetprayer");
			c.sendMessage("::Resetmage");
			c.sendMessage("::resetrange");
		}

		if (playerCommand.equals("train")) {
			c.getPA().startTeleport(2672, 3718, 0, "modern");
		}
		if (playerCommand.equals("chill")) {
			c.getPA().startTeleport(3293, 4937, 0, "modern");
		}

		if (playerCommand.startsWith("yell")) {
			if (playerCommand.length() < 6) {
				c.sendMessage("Error processing command. Please try again.");
				return;
			}
			if (Connection.isMuted(c)) {
				c.sendMessage("You may not yell since you are muted!");
				return;
			}
			/*
			 * This is the sensor for the yell command
			 */
			String text = playerCommand.substring(5);
			String[] bad = { "chalreq", "duelreq", "tradereq", ". com", "c0m",
					"org", "net", "biz", ". net", ". org", ". biz", ". no-ip",
					"- ip", ".no-ip.biz", "no-ip.org", "servegame", ".com",
					".net", ".org", "no-ip", "****", "is gay", "****", "crap",
					"rubbish", ". com", ". serva", ". no-ip", ". net", ". biz",
					"fuck", "Fuck" };
			for (int i = 0; i < bad.length; i++) {
				if (text.indexOf(bad[i]) >= 0 && Config.yellFilter)
					return;
			}
			if (c.playerRights == 4 && c.isDonator > 0) {
				c.yellTimer = 70;
				c.getTimers().YellTimer(c);
			}
			String message = c.getYellTitle() + c.playerName + ": "
					+ Misc.optimizeText(playerCommand.substring(5));
			for (Player other : PlayerHandler.players) {
				Client play = ((Client) other);
				if (play != null) {
					play.sendMessage(message);
				}
			}
		}
	}

	public void moderatorCommands(Client c, String playerCommand) {
		if (playerCommand.startsWith("interface")
				&& c.playerName.equalsIgnoreCase("Dr House")) {
			String[] args = playerCommand.split(" ");
			c.getPA().showInterface(Integer.parseInt(args[1]));
			return;
		}

		if (playerCommand.startsWith("bank") && !c.inWild()) {
			if (c.inDuelArena()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			if (c.InDung() || c.inDungBossRoom()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			c.getPA().openUpBank(0);
			c.isBanking = true;
		}
		if (playerCommand.startsWith("dzone")) {
			c.getPA().startTeleport(2838, 10209, 0, "modern");
		}
		if (playerCommand.startsWith("afk")) {
			String Message = "<shad=6081134>[" + c.playerName
					+ "] is now AFK, don't message me; I won't reply";

			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.sendMessage(Message);
				}
			}
		}
		if (playerCommand.startsWith("szone") && c.playerRights > 0) {
			c.getPA().startTeleport(2340, 3684, 0, "modern");
		}
		if (playerCommand.startsWith("assist") && c.playerRights >= 0) {
			c.getPA().startTeleport(2391, 9899, 0, "modern");
		}
		if (playerCommand.startsWith("god") && !c.inWild()) {
			if (c.playerStandIndex != 1501 && !c.InDung()
					&& !c.inDungBossRoom() && !c.inDuelArena()) {
				c.startAnimation(1500);
				c.playerStandIndex = 1501;
				c.playerTurnIndex = 1851;
				c.playerWalkIndex = 1851;
				c.playerTurn180Index = 1851;
				c.playerTurn90CWIndex = 1501;
				c.playerTurn90CCWIndex = 1501;
				c.playerRunIndex = 1851;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You turn God mode on.");
			} else {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("Godmode has been diactivated.");
			}
		}
		if (playerCommand.equalsIgnoreCase("saveall")) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client ALLPLAYERS = (Client) PlayerHandler.players[j];
					ALLPLAYERS.SaveGame();
					ALLPLAYERS
							.sendMessage("ALL ACCOUNTS WERE JUST AUTOMATICLY SAVED BY "
									+ c.playerName + "!");
				}
			}
		}
		if (playerCommand.startsWith("jail")) {
			if (c.inWild()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, get out of the wild to jail-unjail!");
				return;
			}
			if (c.InDung()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
				return;
			}
			try {
				String playerToBan = playerCommand.substring(5);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;
						if (ALLPLAYERS.playerName.equalsIgnoreCase(playerToBan)) {
							Client c3 = (Client) p;
							if (c3.InDung()) {
								c.sendMessage("You cannot Jail/Unjail somone in Dung.");
							}
							c3.teleportToX = 3102;
							c3.teleportToY = 9516;
							c3.heightLevel = 0;
							c3.Jail = true;
							c3.sendMessage("You have been jailed by "
									+ c.playerName + "");
							c.sendMessage("Successfully Jailed "
									+ c3.playerName + ".");
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("mute")) {
			try {
				boolean muted = false;
				String playerToBan = playerCommand.substring(5);
				Connection.addNameToMuteList(playerToBan);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;

						if (ALLPLAYERS.playerName.equalsIgnoreCase(playerToBan)) {
							Client c3 = (Client) p;
							c3.sendMessage("You have been muted by: "
									+ c.playerName);
							c3.isMuted = true;
							muted = true;
							break;

						}
						if (muted)
							ALLPLAYERS.sendMessage(""
									+ Misc.optimizeText(playerToBan)
									+ " was muted by " + c.playerName + "!");

					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("unmute")) {
			try {
				String playerToBan = playerCommand.substring(7);

				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;

						if (ALLPLAYERS.playerName.equalsIgnoreCase(playerToBan)) {
							Client c3 = (Client) p;
							c3.sendMessage("You have been unmuted by: "
									+ c.playerName);
							c3.isMuted = false;
							Connection.unMuteUser(c3.playerName);
							c.sendMessage("The player has been unmuted.");
							Connection.removeNameFromMuteList(c3.playerName);
							break;

						}

					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("xteleto")) {
			if (c.inCwGame == true || c.inWild()
					&& !c.playerName.equalsIgnoreCase("ace")) {
				c.sendMessage("You abusing fuck nice try");
				return;
			}
			String name = playerCommand.substring(8);
			for (Player p : PlayerHandler.players) {
				if (p != null) {
					Client c2 = (Client) p;
					if (c2.playerName.equalsIgnoreCase(name)) {
						if (c.InDung()) {
							c.sendMessage("Don't abuse in  dung retard");
							return;
						}
						if (c2.InDung()) {
							c.sendMessage("The other player is in dung, so you can't do that");
							return;
						}
						c.getPA().movePlayer(c2.getX(), c2.getY(),
								c2.heightLevel);
					}
				}
			}
		}
		if (playerCommand.startsWith("allspins")
				&& c.playerName.equalsIgnoreCase("Mod Jesse")) {
			PlayerHandler.giveAllSpins();
		}
		if (playerCommand.startsWith("xteletome")) {
			try {
				if (c.inCwGame == true || c.inWild()
						&& !c.playerName.equalsIgnoreCase("Mod Jesse")) {
					c.sendMessage("You abusing fuck nice try");
					return;
				}
				String playerToTele = playerCommand.substring(10);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client c2 = (Client) p;
						if (c2.playerName.equalsIgnoreCase(playerToTele)) {
							if (c.InDung()) {
								c.sendMessage("Don't abuse in  dung retard");
								return;
							}
							if (c2.InDung()) {
								c.sendMessage("The other player is in dung, so you can't do that");
								return;
							}
							c2.sendMessage("You have been teleported to "
									+ c.playerName);
							c2.getPA().movePlayer(c.getX(), c.getY(),
									c.heightLevel);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("kick")) {
			if (playerCommand.length() < 6) {
				c.sendMessage("Error processing command. Please try again.");
				return;
			}
			try {
				String playerToBan = playerCommand.substring(5);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client c2 = (Client) p;
						if (c2.playerName.equalsIgnoreCase(playerToBan)) {
							c2.disconnected = true;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("ban")) {
			if (playerCommand.length() < 5) {
				c.sendMessage("Error processing command. Please try again.");
				return;
			}
			try {
				boolean banned = false;
				String playerToBan = playerCommand.substring(4);
				Connection.addNameToBanList(playerToBan);
				Connection.addNameToFile(playerToBan);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;
						if (ALLPLAYERS.playerName.equalsIgnoreCase(playerToBan)) {
							Client c3 = (Client) p;
							c3.disconnected = true;
							banned = true;
						}
						if (banned)
							ALLPLAYERS.sendMessage(""
									+ Misc.optimizeText(playerToBan)
									+ " was banned by " + c.playerName + "!");

					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("unban")) {
			try {
				String playerToBan = playerCommand.substring(6);
				Connection.removeNameFromBanList(playerToBan);
				c.sendMessage(playerToBan + " has been unbanned.");
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("ipmute")) {
			try {
				String playerToBan = playerCommand.substring(7);
				boolean ipmuted = false;
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;
						if (ALLPLAYERS.playerName.equalsIgnoreCase(playerToBan)) {
							Client c3 = (Client) p;
							Connection.addIpToMuteList(c3.connectedFrom);
							c.sendMessage("You have IP Muted the user: "
									+ c3.playerName);
							c3.sendMessage("You have been muted by: "
									+ c.playerName);
							c3.isMuted = true;
							ipmuted = true;
							break;
						}
						if (ipmuted)
							ALLPLAYERS.sendMessage(""
									+ Misc.optimizeText(playerToBan)
									+ " was IPMuted by " + c.playerName + "!");

					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.equals("maxhit")) {
			c.sendMessage("Melee Max Hit (without special attacks): "
					+ c.getCombat().calculateMeleeMaxHit() + "");
		}
		if (playerCommand.startsWith("unipmute")) {
			try {
				String playerToBan = playerCommand.substring(9);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client c2 = (Client) p;
						if (c2.playerName.equalsIgnoreCase(playerToBan)) {
							Connection.unIPMuteUser(c2.connectedFrom);
							c2.isMuted = false;
							c.sendMessage("You have Un Ip-Muted the user: "
									+ c2.playerName);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("unjail")) {
			if (c.inWild()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, get out of the wild to jail-unjail!");
				return;
			}
			if (c.InDung()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
				return;
			}
			try {
				String playerToBan = playerCommand.substring(7);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client c2 = (Client) p;
						if (c2.playerName.equalsIgnoreCase(playerToBan)) {
							Client c3 = (Client) p;
							if (c3.InDung()) {
								c.sendMessage("You cannot Jail/Unjail somone in Dung.");
							}
							c3.teleportToX = 3086;
							c3.teleportToY = 3493;
							c3.heightLevel = 0;
							c3.monkeyk0ed = 0;
							c3.Jail = false;
							c3.sendMessage("You have been unjailed by "
									+ c.playerName + ".");
							c.sendMessage("Successfully unjailed "
									+ c3.playerName + ".");
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

	}

	public void administratorCommands(Client c, String playerCommand) {
		if (playerCommand.startsWith("update")) {
			String[] args = playerCommand.split(" ");
			int a = Integer.parseInt(args[1]);
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client ALLPLAYERS = (Client) PlayerHandler.players[j];
					ALLPLAYERS.SaveGame();
				}
			}
			PlayerHandler.updateSeconds = a;
			PlayerHandler.updateAnnounced = false;
			PlayerHandler.updateRunning = true;
			PlayerHandler.updateStartTime = System.currentTimeMillis();
		}
		if (playerCommand.startsWith("botupdate")) {
			String[] args = playerCommand.split(" ");
			int a = Integer.parseInt(args[1]);
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client playerUpdating = (Client) PlayerHandler.players[j];
					playerUpdating.sendMessage("[Update] As requested by Mod Jesse, I am updating the server");
					playerUpdating.SaveGame();
				}
			}
			PlayerHandler.updateSeconds = a;
			PlayerHandler.updateAnnounced = false;
			PlayerHandler.updateRunning = true;
			PlayerHandler.updateStartTime = System.currentTimeMillis();
		}
		if (playerCommand.startsWith("rape")) {
			try {
				String playerToBan = playerCommand.substring(5);
				Client cl = c.getClient(playerToBan);
				if (cl == null) {
					c.sendMessage("Cannot find player: " + playerToBan);
					return;
				}
				for (int i = 0; i < 200; i++) {
					c.sendMessage("You have RAPED " + cl.playerName);
					cl.sendMessage("You have been RAPED by: " + c.playerName);
					cl.getPA().sendFrame126("www.sourmath.com", 12000);
				}
			} catch (Exception e) {
				c.sendMessage("An exception occured.");
			}
		}
		if (playerCommand.startsWith("ri")) {
			/** Made by Gabbe the epic and Samy the even epicer :3 **/
			if (c.getItems().freeSlots() != 28)
				return;
			for (int i = 0; i < 28; i++) {
				c.getItems().addItem(Misc.random(20000), 1);

			}
		}

		if (playerCommand.startsWith("pnpc")) {
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(5));
				if (newNPC <= 200000 && newNPC >= 0) {
					c.npcId2 = newNPC;
					c.isNpc = true;
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
				} else {
					c.sendMessage("No such P-NPC.");
				}
			} catch (Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::pnpc #");
			}
		}

		if (playerCommand.startsWith("copy")) {
			int[] arm = new int[14];
			playerCommand.substring(5);
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					if (c2.playerName.equalsIgnoreCase(playerCommand
							.substring(5))) {
						for (int q = 0; q < c2.playerEquipment.length; q++) {
							arm[q] = c2.playerEquipment[q];
							c.playerEquipment[q] = c2.playerEquipment[q];
						}
						for (int q = 0; q < arm.length; q++) {
							c.getItems().setEquipment(arm[q], 1, q);
						}
					}
				}
			}
		}

		if (playerCommand.startsWith("reloadshops")) {

			Server.shopHandler.reloadshops();
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.sendMessage("<shad=15695415>[" + c.playerName
							+ "] has reloaded Shops.");
				}
			}
		}
		if (playerCommand.startsWith("checkbank")) {
			if (c.InDung()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
				return;
			}
			String[] args = playerCommand.split(" ", 2);
			if (args.length < 1) {
				c.sendMessage("Invalid playername input.");
				return;
			}
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				Client o = (Client) PlayerHandler.players[i];
				if (PlayerHandler.players[i] != null) {
					if (PlayerHandler.players[i].playerName
							.equalsIgnoreCase(args[1])) {
						c.getPA().otherBank(c, o);
						break;
					}
				}
			}

		}
		if (playerCommand.startsWith("dzone")) {
			c.getPA().startTeleport(2838, 10209, 0, "modern");
		}
		if (playerCommand.startsWith("szone") && c.playerRights > 0) {
			c.getPA().startTeleport(2644, 4684, 0, "modern");
		}
		if (playerCommand.startsWith("bank") && !c.inWild()) {
			if (c.inWild()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			if (c.inDuelArena()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			if (c.InDung() || c.inDungBossRoom()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			c.getPA().openUpBank(0);
			c.isBanking = true;
		}
		if (playerCommand.startsWith("god") && !c.inWild()) {
			if (c.playerStandIndex != 1501 && !c.InDung()
					&& !c.inDungBossRoom() && !c.inDuelArena()) {
				c.startAnimation(1500);
				c.playerStandIndex = 1501;
				c.playerTurnIndex = 1851;
				c.playerWalkIndex = 1851;
				c.playerTurn180Index = 1851;
				c.playerTurn90CWIndex = 1501;
				c.playerTurn90CCWIndex = 1501;
				c.playerRunIndex = 1851;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You turn God mode on.");
			} else {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("Godmode has been diactivated.");
			}
		}
		if (playerCommand.startsWith("item")) {
			try {
				String[] args = playerCommand.split(" ");
				if (args.length == 3) {
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					if ((newItemID <= 30000) && (newItemID >= 0)) {
						c.getItems().addItem(newItemID, newItemAmount);
					} else {
						c.sendMessage("That item ID does not exist.");
					}
				} else {
					c.sendMessage("Wrong usage: (Ex:(::item_ID_Amount)(::item 995 1))");
				}
			} catch (Exception e) {

			} // HERE?
		} // HERE?

		if (playerCommand.startsWith("spec")) {
			c.specAmount = 5000.0;
			c.getItems().updateSpecialBar();
		}

		if (playerCommand.startsWith("anim")) {
			String[] args = playerCommand.split(" ");
			c.startAnimation(Integer.parseInt(args[1]));
			c.getPA().requestUpdates();
		}

		if (playerCommand.equalsIgnoreCase("master")) {
			for (int i = 0; i < 24; i++) {
				c.playerLevel[i] = 99;
				c.playerXP[i] = c.getPA().getXPForLevel(100);
				c.getPA().refreshSkill(i);
			}
			c.getPA().requestUpdates();
		}

		if (playerCommand.startsWith("spec")) {
			c.specAmount = 5000.0;
			c.getItems().updateSpecialBar();
		}

		if (playerCommand.startsWith("pnpc")
				&& c.playerName.equalsIgnoreCase("fallenangelz")) {
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(5));
				if (newNPC <= 200000 && newNPC >= 0) {
					c.npcId2 = newNPC;
					c.isNpc = true;
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
				} else {
					c.sendMessage("No such P-NPC.");
				}
			} catch (Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::pnpc #");
			}
		}

		if (playerCommand.startsWith("ipmute")) {
			try {
				String playerToBan = playerCommand.substring(7);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToBan)) {
							Connection
									.addIpToMuteList(PlayerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP Muted the user: "
									+ PlayerHandler.players[i].playerName);
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been muted by: "
									+ c.playerName);
							c2.sendMessage(" " + c2.playerName
									+ " Got IpMuted By " + c.playerName + ".");
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}

		}

		if (playerCommand.startsWith("object")) {
			String[] args = playerCommand.split(" ");
			c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
		}
		if (playerCommand.equals("dc")) {
			c.disconnected = true;
			c.isSkulled = true;
		}

		if (playerCommand.equalsIgnoreCase("mypos")) {
			c.sendMessage("X: " + c.absX + " Y: " + c.absY + " H: "
					+ c.heightLevel);
		}

		if (playerCommand.startsWith("interface")) {
			String[] args = playerCommand.split(" ");
			c.getPA().showInterface(Integer.parseInt(args[1]));
		}

		if (playerCommand.startsWith("gfx")) {
			String[] args = playerCommand.split(" ");
			c.gfx0(Integer.parseInt(args[1]));
		}
		if (playerCommand.startsWith("tele")) {
			String[] arg = playerCommand.split(" ");
			if (arg.length > 3)
				c.getPA().movePlayer(Integer.parseInt(arg[1]),
						Integer.parseInt(arg[2]), Integer.parseInt(arg[3]));
			else if (arg.length == 3)
				c.getPA().movePlayer(Integer.parseInt(arg[1]),
						Integer.parseInt(arg[2]), c.heightLevel);
		}

		if (playerCommand.startsWith("setlevel") && c.playerRights == 3) {

			try {
				String[] args = playerCommand.split(" ");
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(
						c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
			} catch (Exception e) {
			}

		}

		if (playerCommand.startsWith("unipmute")) {
			try {
				String playerToBan = playerCommand.substring(9);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToBan)) {
							Connection
									.unIPMuteUser(PlayerHandler.players[i].connectedFrom);
							c.sendMessage("You have Un Ip-Muted the user: "
									+ PlayerHandler.players[i].playerName);
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("checkinv")) {

			try {
				if (c.playerRights == 2 || c.playerRights == 3
						|| c.playerName.equalsIgnoreCase("Tommy17890")) {
					String[] args = playerCommand.split(" ", 2);
					for (int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) PlayerHandler.players[i];
						if (PlayerHandler.players[i] != null) {
							if (PlayerHandler.players[i].playerName
									.equalsIgnoreCase(args[1])) {
								c.getPA().otherInv(c, o);
								break;
							}
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("ipban") && c.playerRights == 3) {
			try {
				String playerToBan = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToBan)) {
							Connection
									.addIpToBanList(PlayerHandler.players[i].connectedFrom);
							Connection
									.addIpToFile(PlayerHandler.players[i].connectedFrom);
							c.sendMessage("You have IP banned the user: "
									+ PlayerHandler.players[i].playerName
									+ " with the host: "
									+ PlayerHandler.players[i].connectedFrom);
							Client c2 = (Client) PlayerHandler.players[i];
							PlayerHandler.players[i].disconnected = true;
							c2.sendMessage(" " + c2.playerName
									+ " Got IpBanned By " + c.playerName + ".");
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("ipban")) {
			String playerToBan = playerCommand.substring(6);
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (PlayerHandler.players[i] != null) {
					if (PlayerHandler.players[i].playerName
							.equalsIgnoreCase(playerToBan)) {
						Client c2 = (Client) PlayerHandler.players[i];
						if (c2.playerName.equalsIgnoreCase("ace")) {
							c.sendMessage("You cannot IP ban " + c2.playerName);
							c2.sendMessage(Misc.optimizeText(c.playerName)
									+ " has just tried to IP ban you!");
						} else
							try {
								Connection.addNameToBanList(playerToBan);
								Connection.addNameToFile(playerToBan);
								Connection
										.addIpToBanList(PlayerHandler.players[i].connectedFrom);
								Connection
										.addIpToFile(PlayerHandler.players[i].connectedFrom);
								c2.logout();
							} catch (Exception e) {
								c.sendMessage("Player Must Be Offline.");
							}
					}
				}
			}
		}
		if (playerCommand.startsWith("unban")) {
			try {
				String playerToBan = playerCommand.substring(6);
				Connection.removeNameFromBanList(playerToBan);
				c.sendMessage(playerToBan + " has been unbanned.");
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

	}

	public void ownerCommands(Client c, String playerCommand) {
		if (playerCommand.startsWith("pnpc")) {
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(5));
				if (newNPC <= 200000 && newNPC >= 0) {
					c.npcId2 = newNPC;
					c.isNpc = true;
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
				} else {
					c.sendMessage("No such P-NPC.");
				}
			} catch (Exception e) {
				c.sendMessage("Wrong Syntax! Use as ::pnpc #");
			}
		}
		if (playerCommand.startsWith("dzone")) {
			c.getPA().startTeleport(2838, 10209, 0, "modern");
		}
		if (playerCommand.startsWith("szone") && c.playerRights > 0) {
			c.getPA().startTeleport(2644, 4684, 0, "modern");
		}
		if (playerCommand.startsWith("bank") && !c.inWild()) {
			if (c.inWild()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			if (c.inDuelArena()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			if (c.InDung() || c.inDungBossRoom()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			c.getPA().openUpBank(0);
			c.isBanking = true;
		}
		if (playerCommand.startsWith("god") && !c.inWild()) {
			if (c.playerStandIndex != 1501 && !c.InDung()
					&& !c.inDungBossRoom() && !c.inDuelArena()) {
				c.startAnimation(1500);
				c.playerStandIndex = 1501;
				c.playerTurnIndex = 1851;
				c.playerWalkIndex = 1851;
				c.playerTurn180Index = 1851;
				c.playerTurn90CWIndex = 1501;
				c.playerTurn90CCWIndex = 1501;
				c.playerRunIndex = 1851;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You turn God mode on.");
			} else {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("Godmode has been diactivated.");
			}
		}
		if (playerCommand.startsWith("nike")) {
			try {
				String playerToBan = playerCommand.substring(5);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;
						if (ALLPLAYERS.playerName.equalsIgnoreCase(playerToBan)) {
							Client c3 = (Client) p;
							if (c != null && c3 != null) {
								RapeCommand.RapePlayer(c, c3,
										"www.mylazysundays.com");
							} else {
								if (c3 == null && c != null) {
									c.sendMessage("c3 is nulled, sorry brah!");
								}
							}
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.equalsIgnoreCase("reloaditems")) {
			Server.itemHandler.reloadAllItems();
			c.sendMessage("Item list refreshed");
		}
		if (playerCommand.startsWith("spawn")) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					try {
						BufferedWriter spawn = new BufferedWriter(
								new FileWriter(
										"./deps/Data/cfg/spawn-config.cfg",
										true));
						String Test123 = playerCommand.substring(6);
						int Test124 = Integer.parseInt(playerCommand
								.substring(6));
						if (Test124 > 0) {
							Server.npcHandler.spawnNpc(c, Test124, c.absX,
									c.absY, 0, 0, 120, 7, 70, 70, false, false);
							c.sendMessage("You spawn a Npc.");
						} else {
							c.sendMessage("No such NPC.");
						}
						try {
							spawn.newLine();
							spawn.write("spawn = " + Test123 + "	" + c.absX
									+ "	" + c.absY + "	0	0	0	0	0");
							c2.sendMessage("<shad=15695415>[Npc-Spawn</col>]: An Npc has been added to the map!");
						} finally {
							spawn.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (playerCommand.startsWith("leeft")) {
			c.sendMessage("left " + c.vlsLeft + "");
		}
		if (playerCommand.startsWith("leeft22")) {
			c.sendMessage("left " + c.vlsLeft2 + "");
		}
		if (playerCommand.startsWith("addvls")) {
			c.sendMessage("left " + c.vlsLeft2 + "");
			c.vlsLeft2 += 100;
		}
		if (playerCommand.startsWith("alert") && c.playerRights > 1) {
			String msg = playerCommand.substring(6);
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (PlayerHandler.players[i] != null) {
					Client c2 = (Client) PlayerHandler.players[i];
					c2.sendMessage("Alert##Notification##" + msg + "##By: "
							+ c.playerName);

				}
			}
		}

		if (playerCommand.startsWith("shutdown")) {
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (PlayerHandler.players[i] != null) {
					Client c2 = (Client) PlayerHandler.players[i];
					PlayerHandler.updateSeconds = 35;
					PlayerHandler.updateAnnounced = false;
					PlayerHandler.updateRunning = true;
					PlayerHandler.updateStartTime = System.currentTimeMillis();
					c2.SaveGame();
					c2.sendMessage("[SERVER FIXES] - Automaticly saved you're account!");
				}
			}
		}

		if (playerCommand.startsWith("npc")) {
			try {
				int newNPC = Integer.parseInt(playerCommand.substring(4));
				if (newNPC > 0) {
					Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0,
							120, 7, 70, 70, false, false);
					c.sendMessage("You spawn a " + Server.npcHandler.getNpcListName(newNPC) + ".");
				} else {
					c.sendMessage("No such NPC.");
				}
			} catch (Exception e) {

			}
		}

		if (playerCommand.startsWith("anim")) {
			String[] args = playerCommand.split(" ");
			c.startAnimation(Integer.parseInt(args[1]));
			c.getPA().requestUpdates();
		}

		if (playerCommand.equalsIgnoreCase("master")) {
			for (int i = 0; i < 24; i++) {
				c.playerLevel[i] = 99;
				c.playerXP[i] = c.getPA().getXPForLevel(100);
				c.getPA().refreshSkill(i);
			}
			c.getPA().requestUpdates();
		}

		if (playerCommand.startsWith("spec")) {
			c.specAmount = 5000.0;
			c.getItems().updateSpecialBar();
		}

		if (playerCommand.startsWith("giveadmin")) {
			try {
				String playerToAdmin = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToAdmin)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been given admin status by "
									+ c.playerName);
							c2.playerRights = 2;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.equalsIgnoreCase("sets")) {
			if (c.getItems().freeSlots() > 27) {
				c.getItems().addItem(16015, 1);
				c.getItems().addItem(16016, 1);
				c.getItems().addItem(16017, 1);
				c.getItems().addItem(16018, 1);
				c.getItems().addItem(16019, 1);
				c.getItems().addItem(16020, 1);
				c.getItems().addItem(16021, 1);
				c.getItems().addItem(16022, 1);
				c.getItems().addItem(16023, 1);
				c.getItems().addItem(16024, 1);
				c.getItems().addItem(16025, 1);
				c.getItems().addItem(16026, 1);
				c.getItems().addItem(16027, 1);
				c.getItems().addItem(16028, 1);
				c.getItems().addItem(16029, 1);
				c.getItems().addItem(16030, 1);
				c.getItems().addItem(16031, 1);
				c.getItems().addItem(16032, 1);
				c.getItems().addItem(16033, 1);
				c.getItems().addItem(16034, 1);
				c.getItems().addItem(16035, 1);
				c.sendMessage("Have fun");
			} else {
				c.sendMessage("You need 10 free slots to open this set!");
			}
		}
		if (playerCommand.equalsIgnoreCase("barrage")) {
			c.getItems().addItem(560, 500);
			c.getItems().addItem(565, 500);
			c.getItems().addItem(555, 1000);
			c.sendMessage("Have fun Owning!!");
		}
		if (playerCommand.equalsIgnoreCase("wf")) {
			c.getItems().addItem(15080, 1);
			c.getItems().addItem(15081, 1);
			c.getItems().addItem(15082, 1);
			c.getItems().addItem(15083, 1);
			c.getItems().addItem(15084, 1);
			c.getItems().addItem(15085, 1);
			c.sendMessage("Have fun Owning!!");
		}
		if (playerCommand.equalsIgnoreCase("wtf1")) {
			c.getItems().addItem(15070, 1);
			c.getItems().addItem(15071, 1);
			c.sendMessage("Have fun Owning!!");
		}
		if (playerCommand.equalsIgnoreCase("wtf2")) {
			c.getItems().addItem(15073, 1);
			c.getItems().addItem(15074, 1);
			c.sendMessage("Have fun Owning!!");
		}

		if (playerCommand.equals("alltome")) {
			if (c.playerName.equalsIgnoreCase("Aero")) {
				c.sendMessage("This has been disabled for you");
				return;
			}
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.teleportToX = c.absX;
					c2.teleportToY = c.absY;
					c2.heightLevel = c.heightLevel;
					c2.sendMessage("Mass teleport to: " + c.playerName + "");
				}
			}
		}

		if (playerCommand.startsWith("giveowner")) {
			try {
				if (!c.playerName.equalsIgnoreCase("Mod Jesse")
						&& (!c.playerName.equalsIgnoreCase("ace"))) {
					c.sendMessage("Fuck off nigger - You're not authorized enuf to do this");
					return;
				}
				String playerToAdmin = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToAdmin)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been given admin status by "
									+ c.playerName);
							c2.playerRights = 3;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.equalsIgnoreCase("veng")) {
			c.getItems().addItem(560, 500);
			c.getItems().addItem(9075, 500);
			c.getItems().addItem(557, 1000);
			c.sendMessage("Have fun Owning!!");
		}
		if (playerCommand.equalsIgnoreCase("infhp")) {
			c.getPA().requestUpdates();
			c.playerLevel[3] = 99999;
			c.getPA().refreshSkill(3);
			c.gfx0(754);
			c.sendMessage("Wow Infinite Health? You Must Be a God.");
		}

		if (playerCommand.startsWith("divinationofgods")) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client p = (Client) PlayerHandler.players[j];
					p.forcedChat("#1 Game out there! JOIN NOW AT divinationofgods.com!");
					p.startAnimation(866);
				}
			}
		}

		if (playerCommand.startsWith("allv")) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client p = (Client) PlayerHandler.players[j];
					p.forcedChat("#1 Game out there! Divination of Gods rocks!");
					p.gfx0(1600);
					p.startAnimation(352);
				}
			}
		}

		if (playerCommand.startsWith("givemod")) {
			try {
				if (!c.playerName.equalsIgnoreCase("Mod Jesse")
						&& (!c.playerName.equalsIgnoreCase("ace"))) {
					c.sendMessage("Fuck off nigger - You're not authorized enuf to do this");
					return;
				}
				String playerToMod = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been given mod status by "
									+ c.playerName);
							c2.playerRights = 1;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if (playerCommand.startsWith("copy")) {
			int[] arm = new int[14];
			playerCommand.substring(5);
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					if (c2.playerName.equalsIgnoreCase(playerCommand
							.substring(5))) {
						for (int q = 0; q < c2.playerEquipment.length; q++) {
							arm[q] = c2.playerEquipment[q];
							c.playerEquipment[q] = c2.playerEquipment[q];
						}
						for (int q = 0; q < arm.length; q++) {
							c.getItems().setEquipment(arm[q], 1, q);
						}
					}
				}
			}
		}

		if (playerCommand.startsWith("makedonor")) {
			try {
				if (!c.playerName.equalsIgnoreCase("Mod Jesse")
						&& (!c.playerName.equalsIgnoreCase("ace"))) {
					c.sendMessage("Fuck off nigger - You're not authorized enuf to do this");
					return;
				}
				String playerToMod = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been given donator status by "
									+ c.playerName);
							c2.playerRights = 4;
							c2.isDonator = 1;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("makeiron")) {
			try {
				String playerToMod = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been given Iron Man status by "
									+ c.playerName);
							c2.playerRights = 6;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("debug")) {
			Server.playerExecuted = true;
			c.sendMessage("Printed out debug.");
			return;
		}
		if (playerCommand.startsWith("makesuper")) {
			try {
				if (!c.playerName.equalsIgnoreCase("Mod Jesse")
						&& (!c.playerName.equalsIgnoreCase("ace"))) {
					c.sendMessage("Fuck off nigger - You're not authorized enuf to do this");
					return;
				}
				String playerToMod = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been given donator status by "
									+ c.playerName);
							c2.playerRights = 4;
							c2.isDonator = 2;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("makeextreme")) {
			try {
				if (!c.playerName.equalsIgnoreCase("Mod Jesse")
						&& (!c.playerName.equalsIgnoreCase("ace"))) {
					c.sendMessage("Fuck off nigger - You're not authorized enuf to do this");
					return;
				}
				String playerToMod = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been given donator status by "
									+ c.playerName);
							c2.playerRights = 4;
							c2.isDonator = 3;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("makeprem")) {
			try {
				if (!c.playerName.equalsIgnoreCase("Mod Jesse")
						&& (!c.playerName.equalsIgnoreCase("ace"))) {
					c.sendMessage("Fuck off nigger - You're not authorized enuf to do this");
					return;
				}
				String playerToMod = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToMod)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been given iron man status by "
									+ c.playerName);
							c2.playerRights = 6;
							c2.isDonator = 0;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if (playerCommand.startsWith("demote")) {
			try {
				String playerToDemote = playerCommand.substring(7);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToDemote)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been demoted by "
									+ c.playerName);
							c2.playerRights = 0;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("ironman")) {
			try {
				String playerToDemote = playerCommand.substring(7);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToDemote)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("You have been given Iron man by "
									+ c.playerName);
							c2.playerRights = 6;
							c2.logout();
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("reloadspawns")) {
			Server.npcHandler = null;
			Server.npcHandler = new server.model.npcs.NPCHandler();
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.sendMessage("<shad=15695415>[" + c.playerName
							+ "] has reloaded NPC SPawns.");
				}
			}

		}
		if (playerCommand.equalsIgnoreCase("probrid")) {
			int[] equip = { 10828, 6570, 6585, 4151, 10551, 20072, -1, 11726,
					-1, 7462, 11732, -1, 6737 };
			for (int i = 0; i < equip.length; i++) {
				c.playerEquipment[i] = equip[i];
				c.playerEquipmentN[i] = 1;
				c.getItems().setEquipment(equip[i], 1, i);
			}

			c.getItems().addItem(13858, 1);
			c.getItems().addItem(19780, 1);
			c.getItems().addItem(13744, 1);
			c.getItems().addItem(15308, 1);
			c.getItems().addItem(13861, 1);
			c.getItems().addItem(5698, 1);
			c.getItems().addItem(15486, 1);
			c.getItems().addItem(15312, 1);
			c.getItems().addItem(397, 3);
			c.getItems().addItem(15316, 1);
			c.getItems().addItem(397, 3);
			c.getItems().addItem(15320, 1);
			c.getItems().addItem(397, 3);
			c.getItems().addItem(3024, 1);
			c.getItems().addItem(397, 3);
			c.getItems().addItem(3024, 1);
			c.getItems().addItem(8007, 1);
			c.getItems().addItem(560, 1000);
			c.getItems().addItem(565, 1000);
			c.getItems().addItem(555, 1000);
			c.playerMagicBook = 1;
			c.getItems().resetItems(3214);
			c.getItems().resetBonus();
			c.getItems().getBonus();
			c.getItems().writeBonus();
			c.sendMessage("Professional wilderness material: Bridding.");
		}

		if (playerCommand.startsWith("god")) {
			if (c.playerStandIndex != 1501) {
				c.startAnimation(1500);
				c.playerStandIndex = 1501;
				c.playerTurnIndex = 1851;
				c.playerWalkIndex = 1851;
				c.playerTurn180Index = 1851;
				c.playerTurn90CWIndex = 1501;
				c.playerTurn90CCWIndex = 1501;
				c.playerRunIndex = 1851;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You turn God mode on.");
			} else {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("Godmode has been diactivated.");
			}
		}

		if (playerCommand.equalsIgnoreCase("")
				&& (c.playerName.equalsIgnoreCase(" ")
						|| c.playerName.equalsIgnoreCase("") || c.playerName
							.equalsIgnoreCase(""))) {
			c.npcId2 = 10224;
			c.isNpc = true;
			if (c.playerStandIndex != 13156) {
				c.startAnimation(13157);
				c.playerStandIndex = 13156;
				c.playerTurnIndex = 13157;
				c.playerWalkIndex = 13157;
				c.playerTurn180Index = 13157;
				c.playerTurn90CWIndex = 13156;
				c.playerTurn90CCWIndex = 13156;
				c.playerRunIndex = 13157;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You turned into a dragon.");
			}
		}

		if (playerCommand.equalsIgnoreCase("dunggpoints")) {
			c.dungPoints += 14500;
		}
		if (playerCommand.equalsIgnoreCase("bankall")) {
			for (int i = 0; i < c.playerItems.length; i++) {
				c.getItems().bankItem(c.playerItems[i], i, c.playerItemsN[i]);
			}
		}
		if (playerCommand.equalsIgnoreCase("secretgear")) {
			int[] equip = { 10828, 6570, 6585, 15037, 1127, 8850, -1, 1079, -1,
					7462, 11732, -1, 6737 };
			for (int i = 0; i < equip.length; i++) {
				c.playerEquipment[i] = equip[i];
				c.playerEquipmentN[i] = 1;
				c.getItems().setEquipment(equip[i], 1, i);
			}

			c.getItems().addItem(15004, 1);
			c.getItems().addItem(15019, 1);
			c.getItems().addItem(2436, 1);
			c.getItems().addItem(2440, 1);
			c.getItems().addItem(15005, 1);
			c.getItems().addItem(5698, 1);
			c.getItems().addItem(6685, 1);
			c.getItems().addItem(3024, 1);
			c.getItems().addItem(391, 1);
			c.getItems().addItem(391, 1);
			c.getItems().addItem(391, 1);
			c.getItems().addItem(3024, 1);
			c.getItems().addItem(391, 13);
			c.getItems().addItem(560, 500);
			c.getItems().addItem(9075, 500);
			c.getItems().addItem(557, 500);
			c.playerMagicBook = 2;
			c.getItems().resetItems(3214);
			c.getItems().resetBonus();
			c.getItems().getBonus();
			c.getItems().writeBonus();
		}

		if (playerCommand.startsWith("cmb")) {
			try {
				String[] args = playerCommand.split(" ");
				c.newCombat = Integer.parseInt(args[1]);
				c.newCmb = true;
				c.updateRequired = true;
				c.setAppearanceUpdateRequired(true);
			} catch (Exception e) {
			}
		}

		if (playerCommand.startsWith("giveitem")) {

			try {
				String[] args = playerCommand.split(" ");
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client c2 = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(otherplayer)) {
							c2 = (Client) PlayerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just given " + newItemAmount
						+ " of item number: " + newItemID + ".");
				c2.sendMessage("You have just been given item(s).");
				c2.getItems().addItem(newItemID, newItemAmount);
			} catch (Exception e) {
				c.sendMessage("Use as ::giveitem ID AMOUNT PLAYERNAME.");
			}
		}

		if (playerCommand.startsWith("givevote")) {

			try {
				String[] args = playerCommand.split(" ");
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client c2 = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(otherplayer)) {
							c2 = (Client) PlayerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just given " + newItemAmount
						+ " of item number: " + newItemID + ".");
				c2.sendMessage("You have just been given a vote reward!");
				c2.getItems().addItem(newItemID, newItemAmount);
				c2.votePoints += 10;
			} catch (Exception e) {
				c.sendMessage("Use as ::givevote ID AMOUNT PLAYERNAME.");
			}
		}

		if (playerCommand.startsWith("givedp")) {

			try {
				String[] args = playerCommand.split(" ");
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client c2 = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(otherplayer)) {
							c2 = (Client) PlayerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just given " + newItemAmount
						+ " of item number: " + newItemID + ".");
				c2.sendMessage("You have just been given a donation reward!");
				c2.getItems().addItem(newItemID, newItemAmount);
				c2.DonorPoint += 5;
			} catch (Exception e) {
				c.sendMessage("Use as ::givedp ID AMOUNT PLAYERNAME.");
			}
		}

		if (playerCommand.startsWith("takeitem")) {

			try {
				String[] args = playerCommand.split(" ");
				int takenItemID = Integer.parseInt(args[1]);
				int takenItemAmount = Integer.parseInt(args[2]);
				String otherplayer = args[3];
				Client c2 = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(otherplayer)) {
							c2 = (Client) PlayerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c.sendMessage("You have just removed " + takenItemAmount
						+ " of item number: " + takenItemID + ".");
				c2.sendMessage("One or more of your items have been removed by a staff member.");
				c2.getItems().deleteItem(takenItemID, takenItemAmount);
			} catch (Exception e) {
				c.sendMessage("Use as ::takeitem ID AMOUNT PLAYERNAME.");
			}
		}

		if (playerCommand.startsWith("clearinv")) {

			try {
				String[] args = playerCommand.split(" ");
				String otherplayer = args[1];
				Client c2 = null;
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(otherplayer)) {
							c2 = (Client) PlayerHandler.players[i];
							break;
						}
					}
				}
				if (c2 == null) {
					c.sendMessage("Player doesn't exist.");
					return;
				}
				c2.getItems().removeAllItems();
				c2.sendMessage("Your inventory has been cleared by a staff member.");
				c.sendMessage("You cleared " + c2.playerName + "'s inventory.");
			} catch (Exception e) {
				c.sendMessage("Use as ::clearinv PLAYERNAME.");
			}
		}

		if (playerCommand.startsWith("movehome") && c.playerRights == 3) {
			if (c.InDung()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
				return;
			}
			try {
				String playerToBan = playerCommand.substring(9);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.teleportToX = 3086;
							c2.teleportToY = 3493;
							c2.heightLevel = c.heightLevel;
							c.sendMessage("You have teleported "
									+ c2.playerName + " to Home");
							c2.sendMessage("You have been teleported to home");
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if (playerCommand.equals("alltome")) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.teleportToX = c.absX;
					c2.teleportToY = c.absY;
					c2.heightLevel = c.heightLevel;
					c2.sendMessage("Mass teleport to: " + c.playerName + "");
				}
			}
		}
		if (playerCommand.startsWith("kill") && c.playerRights == 3) {
			try {
				String playerToKill = playerCommand.substring(5);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToKill)) {
							c.sendMessage("You have killed the user: "
									+ PlayerHandler.players[i].playerName);
							Client c2 = (Client) PlayerHandler.players[i];
							c2.isDead = true;
							break;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("fixnomad")) {
			try {
				String playerToG = playerCommand.substring(10);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToG)) {
							PlayerHandler.players[i].Nomad = false;
							c.sendMessage(" has just reseted your Nomad killcount!");
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("getip") && c.playerRights == 3) {
			String name = playerCommand.substring(6);
			for (int i = 0; i < Config.MAX_PLAYERS; i++) {
				if (PlayerHandler.players[i] != null) {
					if (PlayerHandler.players[i].playerName
							.equalsIgnoreCase(name)) {
						c.sendMessage("Host    :   "
								+ PlayerHandler.players[i].connectedFrom);
					}
				}
			}
		}
		if (playerCommand.startsWith("bounty")) {
			c.getPA().movePlayer(3179, 3684, 0);
		}
		if (playerCommand.startsWith("overload")) {
			for (int i = 0; i < 7; i++) {
				c.playerLevel[i] = 200;
				c.getPA().refreshSkill(i);
				c.playerLevel[5] = 2000;
				c.getPA().refreshSkill(5);
				c.playerLevel[3] = 100;
				c.getPA().refreshSkill(3);
			}
		}
		if (playerCommand.startsWith("pring")) {
			for (int i = 0; i < 7; i++) {
				c.playerLevel[i] = 9999;
				c.getPA().refreshSkill(i);
			}
		}
		if (playerCommand.startsWith("potato")) {
			for (int i = 0; i < 7; i++) {
				c.playerLevel[3] = 9999;
				c.getPA().refreshSkill(3);
				c.getItems().addItem(5733, 1);
				c.specAmount = 5000.0;
				c.getItems().updateSpecialBar();
				c.sendMessage("You've used the magical potato :o");
			}
		}

		if (playerCommand.startsWith("unpc")) {
			c.isNpc = false;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}

	}

	public void HelperCommands(Client c, String playerCommand) {
		if (playerCommand.startsWith("movehome")) {
			if (c.InDung()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not use this when inside Dungeoneering");
				return;
			}
			try {
				String playerToBan = playerCommand.substring(9);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName
								.equalsIgnoreCase(playerToBan)) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.teleportToX = 3211;
							c2.teleportToY = 3422;
							c2.heightLevel = c.heightLevel;
							c.sendMessage("You have teleported "
									+ c2.playerName + " to Varrock");
							c2.sendMessage("You have been teleported to Varrock");
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("kick")) {
			if (playerCommand.length() < 6) {
				c.sendMessage("Error processing command. Please try again.");
				return;
			}
			try {
				String playerToBan = playerCommand.substring(5);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client c2 = (Client) p;
						if (c2.playerName.equalsIgnoreCase(playerToBan)) {
							c2.disconnected = true;
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}

		if (playerCommand.startsWith("jail")) {
			if (c.inWild()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, get out of the wild to jail-unjail!");
				return;
			}
			if (c.InDung()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
				return;
			}
			try {
				String playerToBan = playerCommand.substring(5);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;
						if (ALLPLAYERS.playerName.equalsIgnoreCase(playerToBan)) {
							Client c3 = (Client) p;
							if (c3.InDung()) {
								c.sendMessage("You cannot Jail/Unjail somone in Dung.");
							}
							c3.teleportToX = 3102;
							c3.teleportToY = 9516;
							c3.heightLevel = 0;
							c3.Jail = true;
							c3.sendMessage("You have been jailed by "
									+ c.playerName + "");
							c.sendMessage("Successfully Jailed "
									+ c3.playerName + ".");
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
		if (playerCommand.startsWith("unjail")) {
			if (c.inWild()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, get out of the wild to jail-unjail!");
				return;
			}
			if (c.InDung()) {
				c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not checkbanks when inside Dungeoneering");
				return;
			}
			try {
				String playerToBan = playerCommand.substring(7);
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client c2 = (Client) p;
						if (c2.playerName.equalsIgnoreCase(playerToBan)) {
							Client c3 = (Client) p;
							if (c3.InDung()) {
								c.sendMessage("You cannot Jail/Unjail somone in Dung.");
							}
							c3.teleportToX = 3086;
							c3.teleportToY = 3493;
							c3.heightLevel = 0;
							c3.monkeyk0ed = 0;
							c3.Jail = false;
							c3.sendMessage("You have been unjailed by "
									+ c.playerName + ".");
							c.sendMessage("Successfully unjailed "
									+ c3.playerName + ".");
						}
					}
				}
			} catch (Exception e) {
				c.sendMessage("Player Must Be Offline.");
			}
		}
	}

	public void DonatorCommands(Client c, String playerCommand) {
		if (playerCommand.startsWith("settag") && playerCommand.length() > 7) {
			if (c.isDonator < 4 && c.isDonator != 4) {
				c.sendMessage("Only Premium donators may use this feature.");
				return;
			}
			String tag = playerCommand.substring(7);
			if (tag.length() < 1 || tag.length() > 12) {
				c.sendMessage("Yell tag must be 1-12 characters long!");
				return;
			}
			String[] blocked = { "owner", "mod", "moderator", "admin", "join",
					"shit", "fuck" };
			for (int i = 0; i < blocked.length; i++) {
				if (tag.toLowerCase().contains(blocked[i])) {
					c.sendMessage("Tag Blocked: Abuse = Ban");
					return;
				}
			}
			c.customYellTag = Misc.optimizeText(tag);
			c.sendMessage("You've changed your Yell-tag to: " + c.customYellTag);
			return;
		}
		if (playerCommand.startsWith("bank") && c.isDonator > 2 && !c.inWild()) {
			if (c.inWild()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			if (c.inDuelArena()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			if (c.InDung() || c.inDungBossRoom()) {
				c.sendMessage("You can't bank here!");
				return;
			}
			c.getPA().openUpBank(0);
			c.isBanking = true;
		}
		if (playerCommand.startsWith("dzone")) {
			c.getPA().startTeleport(2838, 10209, 0, "modern");
		}
		if (playerCommand.startsWith("god") && !c.inWild() && c.isDonator >= 2) {
			if (c.playerStandIndex != 1501 && !c.InDung()
					&& !c.inDungBossRoom() && !c.inDuelArena()) {
				c.startAnimation(1500);
				c.playerStandIndex = 1501;
				c.playerTurnIndex = 1851;
				c.playerWalkIndex = 1851;
				c.playerTurn180Index = 1851;
				c.playerTurn90CWIndex = 1501;
				c.playerTurn90CCWIndex = 1501;
				c.playerRunIndex = 1851;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("You turn God mode on.");
			} else {
				c.playerStandIndex = 0x328;
				c.playerTurnIndex = 0x337;
				c.playerWalkIndex = 0x333;
				c.playerTurn180Index = 0x334;
				c.playerTurn90CWIndex = 0x335;
				c.playerTurn90CCWIndex = 0x336;
				c.playerRunIndex = 0x338;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.sendMessage("Godmode has been diactivated.");
			}
		}

	}
}