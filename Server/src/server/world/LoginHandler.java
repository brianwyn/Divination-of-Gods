package server.world;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.GabbesAchievements;
import server.util.Misc;

/**
 *** Handles Logging in, basicly just sets all sidebar interfaces/ect.. By Gabbe.
 **/
public class LoginHandler {
	public static void handleAllLoginCRAP(Client c) {
		/*
		 * if(c.playerRights > 0 && c.playerRights != 4) {
		 * System.out.println("Player "
		 * +c.playerName+" just logged in with rights "+c.playerRights+"!"); }
		 */
		c.sendMessage("<shad=16105973>[Server]</shad> <shad=12606464>Welome to Divination of Gods, please type ::vote and ::donate to support us!</shad>");
		c.sendMessage("<shad=16105973>[Update]</shad> <shad=12606464>12/7/2014 At 11:36P.M. Est - A new client has been uploaded!</shad>");
		c.sendMessage("<shad=16105973>[Update]</shad> <shad=12606464>Please download it at www.divinationofgods.com</shad>");
		/*
		 * c.sendMessage(
		 * "<shad=2320482>[Divination of Gods]</shad> <shad=258024802>Type ::donate & ::vote daily for great rewards!</shad>"
		 * ); if(c.hasRecov == 0) { c.sendMessage(
		 * "<shad=2320482>[Server]</shad> <shad=258024802>To be able to recover your account, type ::setemail MAIL!!!</shad>"
		 * ); } else { c.sendMessage(
		 * "<shad=2320482>[Server]</shad> <shad=258024802>Your email is: "
		 * +c.email+""); c.sendMessage(
		 * "<shad=2320482>[Server]</shad> <shad=258024802>If the email you've set is invalid, ask for a change on ::forums!"
		 * ); }
		 */
		if (!c.task2[5] && c.hasRecov == 1) {
			c.task2[5] = true;
			c.sendMessage("You've completed the task: Set an Email!");
			c.TPoints += 2;
			c.achievementInterface("Set an Email!");
			c.sendMessage("You've received two Task Points! You now have a total of "
					+ c.TPoints + " points!");

		}
		if (!c.task2[6] && c.hasBankPin) {
			c.sendMessage("You've completed the task: Set a Bank PIN!");
			c.TPoints += 2;
			c.task2[6] = true;
			c.achievementInterface("Set a Bank PIN!");
			c.sendMessage("You've received two Task Points! You now have "
					+ c.TPoints + " points!");
		}
		if (!c.task2[7] && c.pcGame >= 10) {
			c.task2[7] = true;
			c.sendMessage("You've completed the task: Complete 10 PC Games!");
			c.TPoints += 2;
			c.achievementInterface("Complete 10 PC Games!");
			c.sendMessage("You've received two Task Points! You now have a total of "
					+ c.TPoints + " points!");
		}
		if (c.timeOnline >= 60 && c.timeOnline < 120) {
			c.sendMessage("You've played for 1 minute.");
		} else if (c.timeOnline >= 3600 && c.timeOnline <= 7200) {
			c.sendMessage("You've played for 1 hour.");
		} else if (c.timeOnline >= 3600 && c.timeOnline <= 179999) {
			c.sendMessage("You've played for " + (c.timeOnline / 60)
					+ " minutes.");
		} else if (c.timeOnline >= 720000) {
			c.sendMessage("Welcome, veteran " + Misc.optimizeText(c.playerName)
					+ "!");
		}
		c.calcCombat();
		Misc.println("[REGISTERED]: " + Misc.optimizeText(c.playerName)
				+ ", PR: " + c.playerRights + ", T11: " + c.trade11 + ", CB: "
				+ c.combatLevel + "");
		GabbesAchievements.writeAchievementTab(c);
		c.getPA().sendFrame126("   N/A", 17021);
		c.getPA()
				.sendFrame126(
						" " + c.playerLevel[21] + "/"
								+ c.getLevelForXP(c.playerXP[21]), 17025);
		if (c.inDuelArena()) {
			c.getPA().movePlayer(
					Config.DUELING_RESPAWN_X
							+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
					Config.DUELING_RESPAWN_Y
							+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
		}
		if (c.inBarbDef) {
			Server.barbDefence.endGame(c, false);
		}
		if (c.displayName.equalsIgnoreCase("notset")) {
			c.displayName = c.playerName;
		}
		c.outStream.createFrame(249);
		c.outStream.writeByteA(1); // 1 for members, zero for free
		c.outStream.writeWordBigEndianA(c.playerId);
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (j == c.playerId)
				continue;
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].playerName
						.equalsIgnoreCase(c.playerName))
					c.disconnected = true;
			}
		}
		for (int i = 0; i < 25; i++) {
			c.getPA().setSkillLevel(i, c.playerLevel[i], c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
		for (int p = 0; p < Player.PRAYER.length; p++) { // reset prayer glows
			c.prayerActive[p] = false;
			c.getPA().sendFrame36(Player.PRAYER_GLOW[p], 0);
		}
		for (int p = 0; p < Player.CURSE.length; p++) { // reset prayer glows
			c.curseActive[p] = false;
			c.getPA().sendFrame36(Player.CURSE_GLOW[p], 0);
		}
		c.getPA().handleWeaponStyle();
		c.getPA().handleLoginText();
		c.accountFlagged = c.getPA().checkForFlags();
		c.getPA().sendFrame36(505, 0);
		c.getPA().sendFrame36(506, 0);
		c.getPA().sendFrame36(507, 0);
		c.getPA().sendFrame36(508, 1);
		c.getPA().sendFrame36(166, 4);
		c.getPA().sendFrame36(108, 0);// resets autocast button
		c.getPA().sendFrame36(172, 1);
		c.getPA().sendFrame36(287, 1);
		c.getPA().sendFrame107(); // reset screen
		c.getPA().setChatOptions(0, 0, 0);
		c.setSidebarInterface(1, 639);// QUEST TAB
		c.setSidebarInterface(2, 24999); // DUNG TAB
		c.setSidebarInterface(3, 3213);
		c.setSidebarInterface(4, 1644);
		c.setSidebarInterface(5, 5608);
		c.getPA().sendFrame36(505, 0);
		c.getPA().sendFrame36(506, 0);
		c.getPA().sendFrame36(507, 0);
		c.getPA().sendFrame36(508, 1);
		c.getPA().sendFrame36(166, 4);
		c.getPA().sendFrame36(108, 0);// resets autocast button
		c.getPA().sendFrame36(172, 1);
		c.getPA().sendFrame36(287, 1);
		c.getPA().totallevelsupdate();
		if (c.playerMagicBook == 0) {
			c.setSidebarInterface(6, 1151); // modern
		}
		if (c.playerMagicBook == 1) {
			c.setSidebarInterface(6, 12855); // ancient
		}
		if (c.playerMagicBook == 2) {
			c.setSidebarInterface(6, 29999);
		}
		if (c.altarPrayed == 0) {
			c.setSidebarInterface(5, 5608);
		} else {
			c.setSidebarInterface(5, 22500);
		}
		c.correctCoordinates();
		c.setSidebarInterface(7, 18128);
		c.setSidebarInterface(8, 5065);
		c.setSidebarInterface(9, 5715);
		c.setSidebarInterface(10, 2449);
		c.setSidebarInterface(11, 904); // wrench tab
		c.setSidebarInterface(12, 147); // run tab
		c.setSidebarInterface(13, 17011);
		c.setSidebarInterface(14, 3917);
		c.setSidebarInterface(0, 2423);
		if (c.isDonator > 0 && c.isDonator < 6 && c.playerRights > 1
				&& c.playerRights < 5) {
			c.setSidebarInterface(15, 12855);// donor
		} else {
			c.sendMessage("You need to be donator to open this tab");
			c.setSidebarInterface(15, 1);// donor

		}
		c.setSidebarInterface(16, 3333);// achievmenets
		if (c.isDonator > 0 && c.isDonator < 5) {
			c.setSidebarInterface(15, 12855);// donor
		} else {
			c.sendMessage("You need to be donator to open this tab");
		}
		c.handleSummoningOnLogin();
		if (c.hasFollower == 6874) {
			c.hasFollower = 6874;
			c.yak = true;
		}
		if (c.isDonator > 0 && c.isDonator < 5 && c.playerRights == 0) {
			c.playerRights = 4;
			c.disconnected = true;
		}
		c.hasYakSummoned();
		if (c.inWarriorG() && c.heightLevel == 2) {
			c.getPA().movePlayer(2846, 3540, 2);
		}
		if (c.InDung) {
			c.InDung = true;
		}
		if (c.inPits()) {
			c.getPA().movePlayer(2399, 5177, 0);
			c.inPits = false;
		}
		if (c.trade11 > 0) {
			c.trade11();
		}
		if (c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
			c.getPA().sendFrame126("" + c.MoneyCash / 1000 + "K", 8134);
		} else if (c.MoneyCash > 999999 && c.MoneyCash <= 2147483647) {
			c.getPA().sendFrame126("" + c.MoneyCash / 1000000 + "M", 8134);
		} else {
			c.getPA().sendFrame126("" + c.MoneyCash + "", 8134);
		}
		c.getPA().sendFrame126("" + c.MoneyCash + "", 8135);
		c.getPA().writeTabs();
		handleStaffTabLogin(c);
		c.getPA().showOption(4, 0, "Follow", 4);
		c.getPA().showOption(5, 0, "Trade With", 3);
		c.healingEvent();
		c.healingEventW();
		if (c.isInPrivCon()) {
			c.sendMessage("Starting house events - hold on..");
			c.handleAllEvents();
		}
		c.getItems().resetItems(3214);
		c.getItems().sendWeapon(
				c.playerEquipment[Player.playerWeapon],
				c.getItems()
						.getItemName(c.playerEquipment[Player.playerWeapon]));
		c.getItems().resetBonus();
		c.getItems().getBonus();
		c.getPA()
				.sendFrame126("Combat Level: " + c.getCombatLevel() + "", 3983);
		for (int equip = 0; equip < c.playerEquipment.length; equip++)
			c.getItems().setEquipment(c.playerEquipment[equip],
					c.playerEquipmentN[equip], equip);
		c.getItems().writeBonus();
		c.getCombat().getPlayerAnimIndex(
				c.getItems()
						.getItemName(c.playerEquipment[Player.playerWeapon])
						.toLowerCase());
		c.getPA().logIntoPM();
		c.getPA().sendFrame75(c.npcType, 17027);
		c.getItems().addSpecialBar(c.playerEquipment[Player.playerWeapon]);
		c.saveTimer = Config.SAVE_TIMER;
		c.saveCharacter = true;
		c.handler.updatePlayer(c, c.outStream);
		c.handler.updateNPC(c, c.outStream);
		c.flushOutStream();
		handleLoginMessage(c);
		if (c.addStarter)

			c.getPA().addStarter();
		if (c.autoRet == 1)
			c.getPA().sendFrame36(172, 1);
		else
			c.getPA().sendFrame36(172, 0);
		// }
		if (c.acceptAid) {
			c.acceptAid = false;
			c.getPA().sendFrame36(503, 0);
			c.getPA().sendFrame36(427, 0);

		} else

			c.acceptAid = true;
		c.getPA().sendFrame36(503, 1);
		c.getPA().sendFrame36(427, 1);

	}

	public static void handleLoginMessage(Client c) {
	}

	public static void handleStaffTabLogin(Client c) {
		for (Player p : PlayerHandler.players) {
			if (p != null) {
				Client c2 = (Client) p;
				String staffNames[] = { "Mod Jesse", "Mod Abby", /* ADMINSTART */
				"Mod Braedyn", "Mod Ash", "Mod Brandon" /* MODSTART */,
						"Mod Andrew", "Mod Brad", "mystic", "deathmagicia" };
				for (int i = 0, i2 = 28000; i < staffNames.length; i++, i2 += 2) {
					c2.getPA().sendFrame126(staffNames[i], i2);
					c2.getPA()
							.sendFrame126(
									((PlayerHandler.isPlayerOn(staffNames[i])) ? "@gre@Online"
											: "@red@Offline"), i2 + 1);
				}
			}
		}

	}

	public static void handleWelcomeInterface(Client c) {
		/*
		 * c.getPA().showInterface(450);
		 * c.getPA().sendFrame126("@bla@Castle wars BETA is out!!", 21332); //
		 * msg of the week line 1
		 * c.getPA().sendFrame126("@bla@Use the minigame teleport!", 21333);//
		 * msg of the week line 2 c.getPA().sendFrame126("Blackmarks:",
		 * 21336);// recovery question box
		 * c.getPA().sendFrame126(""+c.blackMark+"", 21337);// recovery question
		 * box c.getPA().sendFrame126("", 21338);// recovery question box //
		 * c.getPA().sendFrame126("@bla@Stay active on the forums", 21332); //
		 * c.getPA().sendFrame126("@bla@To find out about updates!", 21333);
		 * if(c.hasRecov == 0) { c.getPA().sendFrame126("Make sure to use:",
		 * 21341); c.getPA().sendFrame126("::setemail", 21342);
		 * c.getPA().sendFrame126("", 21343);
		 * c.getPA().sendFrame126("You don't have", 21328);
		 * c.getPA().sendFrame126("an email set!", 21329);
		 * c.getPA().sendFrame126("", 21330);// removes 'adress' in email box..
		 * } else { c.getPA().sendFrame126("Your email is", 21341);
		 * c.getPA().sendFrame126("Registered.", 21342);
		 * c.getPA().sendFrame126("Good!", 21343);
		 * c.getPA().sendFrame126("You have an", 21328);
		 * c.getPA().sendFrame126("Email set!", 21329);
		 * c.getPA().sendFrame126("", 21330); c.getPA().sendFrame126("", 21331);
		 * } if(c.isDonator > 0 && c.playerRights == 4) {
		 * c.getPA().sendFrame126("Your a donator.", 21314);
		 * c.getPA().sendFrame126("Thanks <3", 21315);
		 * c.getPA().sendFrame126("", 21316); } if(c.hasBankPin) {
		 * c.getPA().sendFrame126("You have a", 21339);
		 * c.getPA().sendFrame126("Bank PIN set!", 21340); }
		 */
	}

}
