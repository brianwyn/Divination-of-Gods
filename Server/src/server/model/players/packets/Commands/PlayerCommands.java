package server.model.players.packets.Commands;

import org.Vote.MainLoader;
import org.Vote.VoteReward;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.util.Misc;

public class PlayerCommands {

	public static void handleCommand(Client player, String command,
			String message, String... args) {
		switch (command) {
		case "barbdamage":
			player.sendMessage(player.inBarbDef ? "Damage dealt : "
					+ player.barbDamage + "."
					: "You need to be in the minigame to use this command.");
			break;
		case "changepassword":
		case "changepass":
		case "newpass":
		case "newpassword":
			String password = message.trim();
			player.playerPass = password;
			player.sendMessage("Your new password is: " + player.playerPass);
			break;
		case "claim":
		case "reward":
			try {
				VoteReward reward = MainLoader.hasVoted(player.playerName
						.replaceAll(" ", "_"));
				if (reward != null) {
					switch (reward.getReward()) {
					case 0:
						player.votePoints += 10;
						player.getItems().addItem(995, 10000000);
						PlayerHandler
								.serverMessage(Misc
										.optimizeText(player.playerName)
										+ " just Voted With ::vote And Received 10 vote points + 10m");
						break;

					default:
						player.sendMessage("Reward not found.");
						break;
					}
					player.sendMessage("Thank you for voting.");
					player.sendMessage("You now have a total of "
							+ player.votePoints + " Voting points!");
				} else {
					player.sendMessage("You have no items waiting for you.");
				}
			} catch (Exception e) {
				player.sendMessage("[Vote] A SQL error has occurred PLEASE report on the forums/to staff.");
			}
			break;
		case "chill":
			player.getPA().startTeleport(3293, 4937, 0, "modern");
			break;
		case "commands":
			player.getPA().commandsList();
			break;
		case "donate":
			player.getPA().sendFrame126(
					"http://divinationofgods.com/donate.php", 12000);
			player.sendMessage("Donating keeps the server alive and has benefits.");
			break;
		case "empty":
			if (player.inWild())
				return;
			player.getPA().removeAllItems();
			break;
		case "endgame":
			if (player.inBarbDef)
				Server.barbDefence.endGame(player, false);
			else
				player.sendMessage("You need to be in the minigame to use this command.");

			break;
		case "forums":
		case "help":
			player.getPA().sendFrame126("divinationofgods.com/forums.php",
					12000);
			player.sendMessage("Our community is always willing to help.");
			break;
		case "highscores":
		case "hiscores":
			if (player.isAdmin() || player.isStaff())
				player.sendMessage("Staff members don't show up on the list.");
			else
				player.sendMessage("Are you #1?");
			player.getPA().sendFrame126(
					"divinationofgods.com/hs/highscores.php", 12000);
			break;
		case "maxhit":
		case "meleemax":
			player.sendMessage("Melee Max Hit: "
					+ player.getCombat().calculateMeleeMaxHit() + ".");
			break;
		case "players":
			player.getPA().playersOnline();
			player.sendMessage("There are currently "
					+ (PlayerHandler.getPlayerCount()) + " players online.");
			break;
		case "qpsk":
			player.startAnimation(4945);
			player.gfx0(816);
			break;
		case "resetatt":
		case "resetattack":
			player.getPA().resetPlayerSkill(Player.playerAttack, true);
			break;
		case "resetdef":
		case "resetdefence":
			player.getPA().resetPlayerSkill(Player.playerDefence, true);
			break;
		case "resetmage":
		case "resetmagic":
			player.getPA().resetPlayerSkill(Player.playerMagic, true);
			break;
		case "resetpray":
		case "resetprayer":
			player.getPA().resetPlayerSkill(Player.playerPrayer, true);
			break;
		case "resetrange":
		case "resetranged":
			player.getPA().resetPlayerSkill(Player.playerRanged, true);
			break;
		case "resetstr":
		case "resetstrength":
			player.getPA().resetPlayerSkill(Player.playerStrength, true);
			break;
		case "resettask":
			player.slayerTask = 0;
			player.taskAmount = 0;
			player.getPA().calculateTask();
			player.sendMessage("Your Slayer task has been reset.");
			break;
		case "rules":
			player.getPA().sendFrame126("divinationofgods.com/forums.php",
					12000);
			break;
		case "save":
			player.SaveGame();
			player.sendMessage("You account has been saved!");
			break;
		case "sit":
			if (player.InDung() || player.inWild() || player.inDuel) {
				player.sendMessage("This would be a bad area to sit.");
				return;
			}
			player.sit = !player.sit;
			player.stopPlayerSkill = true;
			player.playerWalkIndex = (!player.sit ? 0x328
					: player.isAdmin() ? 4117 : player.isMod() ? 4113
							: player.playerRights == 4 ? 4116 : 4115);
			player.sendMessage(player.sit ? "You sit in the seat that suddenly pops up"
					: "You stand your lazy ass up.");
			break;
		case "unsit":
		case "stand":
			player.sit = false;
			player.stopPlayerSkill = false;
			player.startAnimation(4191);
			player.playerWalkIndex = 0x328;
			player.sendMessage("You stand your lazy ass up.");
			break;
		case "train":
			player.getPA().startTeleport(2672, 3718, 0, "modern");
			break;
		case "vote":
			player.getPA()
					.sendFrame126(
							"http://runelocus.com/top-rsps-list/vote-40379-Divination%20Of%20Gods/",
							12000);
			player.sendMessage("Voting attracts more players, and there's rewards :3");
			break;
		case "withdraw":
			try {
				int amount = Integer.parseInt(args[1]);
				if (player.inWild()) {
					player.sendMessage("You cannot do this in the wilderness");
					player.getPA().sendFrame126("" + player.MoneyCash + "",
							8135);
					return;
				}
				if (amount < 1) {
					return;
				}
				if (amount == 0) {
					player.sendMessage("Why would I withdraw no coins?");
					return;
				}
				if (player.MoneyCash == 0) {
					player.sendMessage("You don't have any cash in the bag.");
					player.getPA().sendFrame126("" + player.MoneyCash + "",
							8135);
					return;
				}
				if (player.MoneyCash < amount) {
					if (amount == 1) {
						player.sendMessage("You withdraw 1 coin.");
					}
					if (amount > Config.MAXITEM_AMOUNT) {
						player.sendMessage("Nice try.");
						return;
					} else {
						player.sendMessage("You withdraw " + player.MoneyCash
								+ " coins.");
					}
					player.getItems().addItem(995, player.MoneyCash);
					player.MoneyCash = 0;
					player.getPA().sendFrame126("" + player.MoneyCash + "",
							8134);
					player.getPA().sendFrame126("" + player.MoneyCash + "",
							8135);
					return;
				}
				if (player.MoneyCash != 0) {
					if (amount == 1) {
						player.sendMessage("You withdraw 1 coin.");
					} else {
						player.sendMessage("You withdraw " + amount + " coins.");
					}
					player.MoneyCash -= amount;
					player.getItems().addItem(995, amount);
					player.getPA().sendFrame126("" + player.MoneyCash + "",
							8135);
					if (player.MoneyCash > 99999 && player.MoneyCash <= 999999) {
						player.getPA().sendFrame126(
								"" + player.MoneyCash / 1000 + "K", 8134);
					} else if (player.MoneyCash > 999999
							&& player.MoneyCash <= 2147483647) {
						player.getPA().sendFrame126(
								"" + player.MoneyCash / 1000000 + "M", 8134);
					} else {
						player.getPA().sendFrame126("" + player.MoneyCash + "",
								8134);
					}
					player.getPA().sendFrame126("" + player.MoneyCash + "",
							8135);
				}
			} catch (Exception e) {
				player.sendMessage("Invalid Number.");
			}
			break;
		case "yell":
			if (message.length() < 1 || message == null) {
				player.sendMessage("Error processing command. Please try again.");
				return;
			}
			if (Connection.isMuted(player)) {
				player.sendMessage("You may not yell since you are muted!");
				return;
			}
			/*
			 * This is the sensor for the yell command
			 */
			String[] bad = { "chalreq", "duelreq", "tradereq", ". com", "c0m",
					"org", "net", "biz", ". net", ". org", ". biz", ". no-ip",
					"- ip", ".no-ip.biz", "no-ip.org", "servegame", ".com",
					".net", ".org", "no-ip", "****", "is gay", "****", "crap",
					"rubbish", ". com", ". serva", ". no-ip", ". net", ". biz",
					"fuck", "Fuck" };
			for (int i = 0; i < bad.length; i++) {
				if (message.indexOf(bad[i]) >= 0 && Config.yellFilter) {
					player.sendMessage("Your yell message was censored by the yell filter");
					return;
				}
			}
			for (Player other : PlayerHandler.players) {
				Client play = ((Client) other);
				if (play != null) {
					play.sendMessage(player.getYellTitle() + player.playerName
							+ ": " + Misc.optimizeText(message));
				}
			}
			break;

		}
	}

}
