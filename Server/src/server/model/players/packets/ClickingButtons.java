package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.items.GameItem;
import server.model.items.ItemAssistant;
import server.model.minigames.DominionTowerByGabbe;
import server.model.minigames.Gambling;
import server.model.minigames.PestControl;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.GabbesAchievements;
import server.model.players.content.GabbesSkillTeleport;
import server.model.players.content.GabbesXPLamp;
import server.model.players.content.LoyaltyHandler;
import server.model.players.content.PartyRoom;
import server.model.players.content.PointsHandler;
import server.model.players.content.combat.BountyHunter.Artifacts;
import server.model.players.content.combat.BountyHunter.PvPHandler;
import server.model.players.content.combat.magic.AutoCasting;
import server.model.players.content.interfaces.DrHousesShop;
import server.model.players.content.skills.Construction;
import server.model.players.content.skills.Cooking;
import server.model.players.content.skills.Dungeoneering;
import server.model.players.content.skills.Prayer;
import server.model.players.content.skills.Smithing;
import server.model.players.content.skills.Summoning;
import server.model.players.content.skills.impl.Cons;
import server.model.players.content.skills.impl.ConstructionObjects.Lecterns;
import server.model.players.content.skills.impl.ConstructionObjects.Rugs;
import server.util.Misc;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {

	public static boolean sentBankMes = false;

	public boolean hasbobNpc(Client c) {
		switch (c.hasFollower) {
		case 6806: // thorny snail
		case 6807:
		case 6994: // spirit kalphite
		case 6995:
		case 6867: // bull ant
		case 6868:
		case 6794: // spirit terrorbird
		case 6795:
		case 6815: // war tortoise
		case 6816:
		case 6874:// pack yak
		case 6873: // pack yak
		case 3594: // yak
		case 3590: // war tortoise
		case 3596: // terrorbird
			return true;
		}
		return false;
	}

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		GabbesXPLamp.handleXPLampButtons(c, packetType, packetSize);
		Cons.HandleConstructionInterface(c, packetType, packetSize);
		LoyaltyHandler.HandleLoyaltyMisc(c, packetType, packetSize);
		DrHousesShop.handleButtons(c, packetType, packetSize);
		DominionTowerByGabbe.handleInterfacesButtons(c, packetType, packetSize);
		GabbesSkillTeleport.handleButtons(c, packetType, packetSize);
		AutoCasting.handleButtons(c, packetType, packetSize, actionButtonId);

		// int actionButtonId = c.getInStream().readShort();
		if (c.isDead)
			return;
		if (c.playerRights == 3 && c.playerRights != 1) {
			c.sendMessage(c.playerName + " - actionbutton: " + actionButtonId);
			Misc.println(c.playerName + " - actionbutton: " + actionButtonId);
		}

		if (Summoning.createPouch(c, actionButtonId))
			return;

		for (int i = 0; i < c.qCAB.length; i++) {
			if (actionButtonId == c.qCAB[i][0]) {
				for (int j = 0; j < c.qCS.length; j++) {
					if (j == i) {
						c.forcedText = c.qC + "My " + c.qCS[j] + " Level is "
								+ c.getLevelForXP(c.playerXP[c.qCAB[i][1]])
								+ ".";
						c.forcedChatUpdateRequired = true;
						c.updateRequired = true;
					}
				}
			}
		}

		switch (actionButtonId) { // 7002
		
		case 70212:
			Server.clanChat.leaveClan(c.playerId, c.clanId);
			break;
			
		case 71074:
			if (Server.clanChat.isOwner(c)) {
				if (System.currentTimeMillis() - c.lastEmote >= 1500) {
					Server.clanChat.setLootshare(c,
							(Server.clanChat.clans[c.clanId].lootshare ? false
									: true));
					Server.clanChat.updateClanChat(c.clanId);
					c.lastEmote = System.currentTimeMillis();
				}
			} else {
				if (c.clanId > 0)
					c.sendMessage("Only the owner of the clan has the power to do that.");
				else
					c.sendMessage("You must be in a clan to use this button.");
			}
			break;

		// Monster Teleports Start
		case 132211:
			c.getPA().spellTeleport(2676, 3715, 0);

			if (c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); // modern
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 16640);
			}
			break;
		case 132214:
			c.getPA().spellTeleport(2884, 9798, 0);

			if (c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); // modern
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 16640);
			}
			break;
		case 132217:
			c.getPA().spellTeleport(3428, 3537, 0);

			if (c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); // modern
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 16640);
			}
			break;
		/*
		 * Rotten potato shit
		 */

		case 132220:
			c.getPA().spellTeleport(2710, 9466, 0);
			c.sendMessage("You have teleported to Brimhaven Dungeon, be sure to bring an antifire-shield!");

			if (c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); // modern
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 16640); // Lunar
			}
			break;

		case 132227:
			if (c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); // modern
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 16640); // Lunar
			}
			break;

		case 132230:
			c.setSidebarInterface(6, 39000);
			break;

		case 152099:
			if (c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); // modern
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 16640); // Lunar
			}
			break;
		case 152102:
			c.setSidebarInterface(6, 34000);
			break;

		case 152091:
			c.getPA().spellTeleport(3560, 9947, 0);

			if (c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); // modern
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 16640);
			}
			break;

		case 152094:
			c.getPA().spellTeleport(2515, 4632, 0);

			if (c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); // modern
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 16640);
			}
			break;
		// Monster Teleports end

		case 177147:
		case 213021:
		case 255179:
			Lecterns.createTablet(c, 8010, 4413, 700, 556, 5);
			break;
		case 177150:
		case 212190:
		case 255182:
			Lecterns.createTablet(c, 8009, 4413, 700, 555, 8);
			break;
		case 212193:
		case 255185:
			Lecterns.createTablet(c, 8007, 4413, 700, 554, 6);
			break;
		case 212196:
		case 255188:
			Lecterns.createTablet(c, 8008, 4413, 700, 557, 4);
			break;
		case 255191:
			Lecterns.createTablet(c, 8011, 4413, 700, 563, 3);
			break;
		case 255194:
			Lecterns.createTablet(c, 8013, 4413, 700, 560, 10);
			break;
		case 255176:
		case 177144:
		case 39178:
		case 213018:
			c.getPA().closeAllWindows();
			break;
		case 187130:
			c.stopMovement();
			GabbesAchievements.openBook(c, "Easy");
			break;
		case 187131:
			c.stopMovement();
			GabbesAchievements.openBook(c, "Advanced");
			break;
		case 187132:
			c.stopMovement();
			GabbesAchievements.openBook(c, "Elite");
			break;
		case 152244:
			c.getPA().sendFrame126("" + c.TPoints, 48005);
			c.setSidebarInterface(2, 48000);
			break;
		case 187134:
			c.setSidebarInterface(2, 639);
			break;
		case 3073:
		case 3071:
			c.sendMessage("More tasks are coming in the future.");
			break;
		case 157037:
			break;
		case 157040:
			break;
		case 192073:
			if (c.inDung) {
				if (c.Partner.equalsIgnoreCase("None")) {
					c.getPA().setDungBindStats();
				} else {
					for (Player p : PlayerHandler.players) {
						if (p != null) {
							Client ALLPLAYERS = (Client) p;
							if (ALLPLAYERS.playerName
									.equalsIgnoreCase(c.Partner)) {
								Client c2 = (Client) p;
								if (c2 != null) {
									if (c2.inDung) {
										c.getDH()
												.sendOption2(
														"View my stats",
														"View "
																+ Misc.optimizeText(c2.playerName)
																+ "'s stats.");
										// c.getPA().setDungBindStats(c2);
										c.dialogueAction = 214;
									}
								}
							}
						}
					}
				}
			}
			break;
		case 192050:
			c.getDH().sendDialogues(256, -1);
			break;
		case 157056:
			if (!c.Inviter.equalsIgnoreCase("")) {
				try {
					for (Player p : PlayerHandler.players) {
						if (p != null) {
							Client ALLPLAYERS = (Client) p;
							if (ALLPLAYERS.playerName
									.equalsIgnoreCase(c.Inviter)) {
								Client c2 = (Client) p;
								if (c2 != null) {
									if (c.answerParty) {
										c.sendMessage("You will no longer receive any invitations from "
												+ Misc.optimizeText(c2.playerName)
												+ "!");
										c.sendMessage("To undo this, type ::receive #Playername!");
										c.getPA().closeAllWindows();
										c.banned = c2.playerId;
										return;
									}
								}
							}
						}
					}

				} catch (Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			break;
		// Runecrafting Start

		case 105122: // Air
			c.getPA().spellTeleport(2846, 4834, 0);
			c.getPA().closeAllWindows();
			break;
		case 105123: // Mind
			c.getPA().spellTeleport(2786, 4838, 0);
			c.getPA().closeAllWindows();
			break;
		case 105124: // Water
			c.sendMessage("The Water altar is currently unavailable.");
			c.getPA().closeAllWindows();
			break;
		case 105125: // Earth
			c.getPA().spellTeleport(2660, 4839, 0);
			c.getPA().closeAllWindows();
			break;
		case 105126: // Fire
			c.getPA().spellTeleport(2584, 4836, 0);
			c.getPA().closeAllWindows();
			break;
		case 105127: // Cosmic
			c.getPA().spellTeleport(2162, 4833, 0);
			c.getPA().closeAllWindows();
			break;
		case 105128: // Chaos
			c.getPA().spellTeleport(2268, 4842, 0);
			c.getPA().closeAllWindows();
			break;
		case 105129: // Astral
			c.getPA().spellTeleport(2142, 4831, 0);
			c.getPA().closeAllWindows();
			break;
		case 105130: // Nature
			c.getPA().spellTeleport(2397, 4841, 0);
			c.getPA().closeAllWindows();
			break;
		case 105131: // Law
			c.getPA().spellTeleport(2464, 4834, 0);
			c.getPA().closeAllWindows();
			break;
		case 105132: // Death
			c.getPA().spellTeleport(2205, 4834, 0);
			c.getPA().closeAllWindows();
			break;
		case 105133: // Blood
			c.getPA().spellTeleport(2462, 4891, 1);
			c.getPA().closeAllWindows();
			break;
		case 105134: // Close Button
			c.getPA().closeAllWindows();
			break;
		// Runecrafting End

		/* Pest Control Interface Start */
		case 73040:
			c.getPA().closeAllWindows();
			break;
		case 73055:
			c.getPA().showInterface(18746);
			break; // Equipment Void
		case 73085:
			c.getPA().showInterface(18700);
			break; // Experience
		case 73060:
		case 73086:
			c.getPA().showInterface(18730);
			break; // Equipment
		case 73087:
			c.getPA().showInterface(18753);
			break; // Consumables
		case 73088:
			c.getPA().showInterface(18763);
			break; // extra

		// Experience Tab

		case 73028:
			PestControl.giveReward(c, false, 0, 5250, 1);
			break;// Attack
		case 73032:
			PestControl.giveReward(c, false, 0, 5250, 10);
			break;
		case 73038:
			PestControl.giveReward(c, false, 0, 5250, 100);
			break;
		case 73018:
			PestControl.giveReward(c, false, 1, 5600, 1);
			break;// Defence
		case 73020:
			PestControl.giveReward(c, false, 1, 5600, 10);
			break;
		case 73022:
			PestControl.giveReward(c, false, 1, 5600, 100);
			break;
		case 73029:
			PestControl.giveReward(c, false, 2, 5600, 1);
			break;// Strength
		case 73035:
			PestControl.giveReward(c, false, 2, 5600, 10);
			break;
		case 73015:
			PestControl.giveReward(c, false, 2, 5600, 100);
			break;
		case 73024:
			PestControl.giveReward(c, false, 3, 5600, 1);
			break;// Constitution
		case 73034:
			PestControl.giveReward(c, false, 3, 5600, 10);
			break;
		case 73039:
			PestControl.giveReward(c, false, 3, 5600, 100);
			break;
		case 73030:
			PestControl.giveReward(c, false, 4, 5120, 1);
			break;// Ranged
		case 73036:
			PestControl.giveReward(c, false, 4, 5120, 10);
			break;
		case 73016:
			PestControl.giveReward(c, false, 4, 5120, 100);
			break;
		case 73031:
			PestControl.giveReward(c, false, 5, 1980, 1);
			break;// Prayer
		case 73037:
			PestControl.giveReward(c, false, 5, 1980, 10);
			break;
		case 73017:
			PestControl.giveReward(c, false, 5, 1980, 100);
			break;
		case 73026:
			PestControl.giveReward(c, false, 6, 4480, 1);
			break;// Magic
		case 73021:
			PestControl.giveReward(c, false, 6, 4480, 10);
			break;
		case 73023:
			PestControl.giveReward(c, false, 6, 4480, 100);
			break;

		// Equipment Tab
		case 73045:
			PestControl.giveReward(c, true, 11665, 1, 200);
			break;// melee helm
		case 73047:
			PestControl.giveReward(c, true, 11664, 1, 200);
			break;// ranger helm
		case 73053:
			PestControl.giveReward(c, true, 11663, 1, 200);
			break;// mage helm
		case 73046:
			PestControl.giveReward(c, true, 8839, 1, 250);
			break;// top
		case 73049:
			PestControl.giveReward(c, true, 8840, 1, 250);
			break;// robes
		case 73054:
			PestControl.giveReward(c, true, 8842, 1, 150);
			break;// gloves
		case 73052:
			PestControl.giveReward(c, true, 19711, 1, 350);
			break;// deflector

		// Equipment Tab void
		case 73061:
			PestControl.giveReward(c, true, 19785, 1, 125);
			break;// elite top
		case 73062:
			PestControl.giveReward(c, true, 19786, 1, 125);
			break;// elite legs

		// Consumables Tab
		case 73067:
			PestControl.giveReward(c, true, 6687, 1, 5);
			break;// saradomin brew
		case 73068:
			PestControl.giveReward(c, true, 9475, 1, 6);
			break;// mint cake
		case 73069:
			PestControl.giveReward(c, true, 12163, 1, 1);
			break;// Blue charm
		case 73070:
			PestControl.giveReward(c, true, 3016, 1, 2);
			break;// super energy
		case 73071:
			PestControl.giveReward(c, true, 3040, 1, 2);
			break;// magic potion
		case 73072:
			PestControl.giveReward(c, true, 397, 1, 1);
			break;// sea turtle

		// extra
		case 73077:
			PestControl.giveReward(c, true, 88, 1, 35);
			break;// boots of lightness
		case 73078:
			PestControl.giveReward(c, true, 10553, 1, 50);
			break;// Penance Gloves
		case 73079:
			PestControl.giveReward(c, true, 10548, 1, 45);
			break;// Fighter hat
		case 73080:
			PestControl.giveReward(c, true, 18335, 1, 150);
			break;// Arcane Stream
		case 73081:
			PestControl.giveReward(c, true, 19888, 1, 95);
			break;// Demon horn Necklace
		case 73082:
			PestControl.giveReward(c, true, 10551, 1, 65);
			break;// Fighter Torso

		/* Pest Control Interface End */

		/* Bank tabs buttons */
		case 86008:
			c.getPA().openUpBank(0);
			break;
		case 86009:
			if (c.bankItems1[0] > 0)
				c.getPA().openUpBank(1);
			break;
		case 86010:
			if (c.bankItems2[0] > 0)
				c.getPA().openUpBank(2);
			break;
		case 86011:
			if (c.bankItems3[0] > 0)
				c.getPA().openUpBank(3);
			break;
		case 86012:
			if (c.bankItems4[0] > 0)
				c.getPA().openUpBank(4);
			break;
		case 86013:
			if (c.bankItems5[0] > 0)
				c.getPA().openUpBank(5);
			break;
		case 86014:
			if (c.bankItems6[0] > 0)
				c.getPA().openUpBank(6);
			break;
		case 86015:
			if (c.bankItems7[0] > 0)
				c.getPA().openUpBank(7);
			break;
		case 86016:
			if (c.bankItems8[0] > 0)
				c.getPA().openUpBank(8);
			break;

		case 66122:
			c.Summoning().store();
			break;

		case 39012:
			if (!hasbobNpc(c)) {
				c.sendMessage("You do not have a BoB follower.");
				return;
			}
			if (c.InDung() || c.inDuelArena()) {
				return;
			}
			for (int i = 0; i < c.maxstore; i++) {
				c.getPA().removeBoBItems(i, 1);
				c.startAnimation(827);
				c.stopMovement();
			}

			break;

		case 86004:
			if (!hasbobNpc(c)) {
				c.sendMessage("You do not have a BoB follower.");
				return;
			}
			if (c.isBanking) {
				c.getPA().BoBToBank(1, 1);
				c.stopMovement();
			}
			break;
		case 83053:
			c.getPA().closeAllWindows();
			break;
		case 17200:
			if (c.absX == 3563 && c.absY == 9694) {
				c.sendMessage("You hear the doors locking mechanism grind open.");
				c.getPA().object(6725, c.objectX, c.objectY, -1, 0);
				c.getPA().removeAllWindows();
				c.getPA().walkTo(-1, 0);
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						c.getPA().object(6725, c.objectX, c.objectY, -2, 0);
						container.stop();
					}

					@Override
					public void stop() {

					}
				}, 1);
			} else {
				c.sendMessage("You hear the doors locking mechanism grind open.");
				c.getPA().object(6725, c.objectX, c.objectY, -2, 0);
				c.getPA().removeAllWindows();
				c.getPA().walkTo(0, 1);
				CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
					@Override
					public void execute(CycleEventContainer container) {
						c.getPA().object(6725, c.objectX, c.objectY, -1, 0);
						container.stop();
					}

					@Override
					public void stop() {

					}
				}, 1);
			}
			break;
		case 17199:
			c.getPA().removeAllWindows();
			c.sendMessage("You got the riddle wrong, and it locks back up.");
			server.model.minigames.Barrows.wrongPuzzle = true;
			break;
		case 17198:
			c.getPA().removeAllWindows();
			c.sendMessage("You got the riddle wrong, and it locks back up.");
			server.model.minigames.Barrows.wrongPuzzle = true;
			break;
		case 47085:
			c.getPA().resetFrames();
			c.getPA().getHighscores("kills");
			c.getPA().sendFrame126("Divination of Gods Highscores: Top Kills",
					12102);
			break;
		case 47084:
			c.getPA().resetFrames();
			c.getPA().getHighscores("PLP");
			c.getPA().sendFrame126(
					"Divination of Gods Highscores: Divination Points", 12102);
			break;
		case 47088:
			c.getPA().resetFrames();
			c.getPA().getHighscores("deaths");
			c.getPA().sendFrame126("Divination of Gods Highscores: Top Deaths",
					12102);
			break;
		case 47089:
			c.getPA().resetFrames();
			c.getPA().getHighscores("LP");
			c.getPA().sendFrame126(
					"    Divination of Gods Highscores: Loyalty Points", 12102);
			break;
		// Request Staff Assistance
		case 109114:
			if (Connection.isMuted(c)) {
				c.sendMessage("You can't ask for help when you are muted.");
				return;
			}
			if (c.Jail == true) {
				c.sendMessage("You can't ask for help in jail.");
				return;
			}
			if (System.currentTimeMillis() - c.lastButton < 300000) {
				c.sendMessage("You can only call for assistance once every 5 minutes.");
				break;
			} else {
				c.lastButton = System.currentTimeMillis();
			}

			if (c.playerRights >= 0) {
				c.sendMessage("You have requested staff assistance.");
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerRights == 1
								|| PlayerHandler.players[i].playerRights == 2
								|| PlayerHandler.players[i].playerRights == 3
								|| PlayerHandler.players[i].playerRights == 4) {
							Client c2 = (Client) PlayerHandler.players[i];
							c2.sendMessage("<shad=13369497>[HELP MESSAGE] <shad=15536940>"
									+ Misc.optimizeText(c.playerName)
									+ "<shad=132833> Has requested help. Any staff, please help "
									+ Misc.optimizeText(c.playerName) + "!");

						}
					}
				}
			}
			break;
		case 153001:
			c.sendMessage("You currently have " + c.totalkills + " kills.");
			break;

		case 153002:
			c.sendMessage("You currently have " + c.totaldeath + " deaths.");
			break;
		case 153000:
			PointsHandler.run(c);
			break;
		case 192012:
			// if(!c.getItems().playerHasIte
			if (System.currentTimeMillis() - c.buryDelay > 1500) {
				c.constructionID = 1088;
				c.getDH().sendDialogues(28168, 1);
			}
			break;
		case 192013:
			c.sendMessage("Total food eaten so far: " + c.foodEat + "");
			break;
		case 129142:
			c.sendMessage("Total Players killed so far: " + c.PlayKil + "");
			break;
		/*
		 * case 129143:
		 * c.sendMessage("Total Goblins killed so far: "+c.GoblinS+""); break;
		 * case 129144:
		 * c.sendMessage("Total Jads killed so far: "+c.Jadskls+""); break;
		 */
		/*
		 * case 129145: c.sendMessage(
		 * "Players killed so far, achievements kills, not total kills: "
		 * +c.PlayKil+""); break; case 129146:
		 * c.sendMessage("Total TookHar-Kal minigames finnished so far: "
		 * +c.WinsSFF+""); break; case 129147:
		 * c.sendMessage("Total Pest-Control minigames finished so far: "
		 * +c.WinsPCC+""); break;
		 */
		case 85244: // Search Bank
			c.searchTerm = "N/A";
			if (!c.searchingBank) {
				c.searchingBank = true;
				c.getPA().sendFrame126(
						"Enter the name of the item you wish to search for:",
						9695);
				c.getOutStream().createFrame(187);
				c.flushOutStream();
			} else {
				c.searchingBank = false;
				c.bankingTab = 0;
				c.getPA().openUpBank(c.bankingTab);
			}
			break;
		/*
		 * case 85244:// search button. c.isSearching = true; c.isSearching2 =
		 * !c.isSearching2; if (!c.isSearching2) { if (c.getInStream() != null &
		 * c != null) { c.getOutStream().createFrame(187); c.flushOutStream(); }
		 * c.isSearching = true; c.isSearching2 = false; } else { c.isSearching
		 * = false; c.isSearching2 = true; } break;
		 */
		case 53152:
			Cooking.getAmount(c, 1);
			break;
		case 53151:
			Cooking.getAmount(c, 5);
			break;
		case 53150:
			Cooking.getAmount(c, 10);
			break;
		case 53149:
			Cooking.getAmount(c, 28);
			break;
		case 108005:
			c.getPA().closeAllWindows();
			c.getPA().showInterface(19148);
			break;
		case 8198:
			PartyRoom.accept(c);
			break;

		case 39014:
			c.sendMessage("You're familiar will automaticly aid you in multi.");
			break;
		case 39013:
			c.sendMessage("No need to do this!");
			break;
		case 39011:
			c.getDH().Kill();
			break;
		case 39010:
			c.sendMessage("Familiar called.");
			break;

		/*
		 * case 82028: if(c.hasFollower == 6874 || c.hasFollower == 3596 ||
		 * c.hasFollower == 6815 || c.hasFollower == 3594 || c.hasFollower ==
		 * 3590 || c.hasFollower == 6816 || c.hasFollower == 6873 ||
		 * c.hasFollower == 6806 || c.hasFollower == 6807 || c.hasFollower ==
		 * 6994 || c.hasFollower == 6995 || c.hasFollower == 6867 ||
		 * c.hasFollower == 6868 || c.hasFollower == 6794 || c.hasFollower ==
		 * 6795 && c.totalstored > 0) {
		 * 
		 * for (int i = 0; i < c.maxstore; i++) { if (c.storeditems[i] > 0) {
		 * c.getPA().BoBToBank(i, 1); //c.startAnimation(827); c.stopMovement();
		 * } } } else {
		 * c.sendMessage("You do not have a follower that's holding items!"); }
		 * break;
		 * 
		 * case 58074: c.getBankPin().closeBankPin(); break;
		 * 
		 * case 58073: c.getBankPin().resetBankPin(); break;
		 * 
		 * case 58025: case 58026: case 58027: case 58028: case 58029: case
		 * 58030: case 58031: case 58032: case 58033: case 58034:
		 * c.getBankPin().bankPinEnter(actionButtonId); break; case 152253:
		 * c.getDH().sendOption2("Send Game Mode Quich Chat",
		 * "Change Game Mode"); c.dialogueAction = 942; break; // end of quests
		 * n dung tab // quickpray /* case 67079: c.getQC().clickConfirm();
		 * c.sendMessage(
		 * "Confirmed QuickPrayers/QuickCurses! Activate them by pushing the orb!"
		 * ); break;
		 * 
		 * case 67050: case 67051: case 67052: case 67053: case 67054: case
		 * 67055: case 67056: case 67057: case 67058: case 67059: case 67060:
		 * case 67061: case 67062: case 67063: case 67064: case 67065: case
		 * 67066: case 67067: case 67068: case 67069: case 67070: case 67071:
		 * case 67072: case 67073: case 67074: case 67075:
		 * c.sendMessage("Prayer selected."); if (c.altarPrayed == 0)
		 * c.getQP().clickPray(actionButtonId); else
		 * c.getQC().clickCurse(actionButtonId); break;
		 * 
		 * case 70080: c.sendMessage("Prayers turned to: ON");
		 * c.getQC().turnOnQuicks(); break;
		 * 
		 * case 70081: c.getQC().turnOffQuicks();
		 * c.sendMessage("Prayers turned to: OFF"); break;
		 * 
		 * case 70082: c.getQC().selectQuickInterface();
		 * c.getPA().sendFrame106(5); break;
		 */
		case 95185:
		case 95191:
		case 95203:
		case 95206:
		case 95194:
		case 95209:
		case 95188:
		case 95212:
		case 95197:
		case 95215:
		case 95200:
		case 95218:
		case 96078:
		case 96174:
		case 96074:
		case 96082:
		case 96182:
		case 96030:
		case 96130:
		case 96034:
		case 96134:
		case 96038:
		case 96138:
		case 96042:
		case 96142:
		case 96046:
		case 96146:
		case 96050:
		case 96150:
		case 96058:
		case 96158:
		case 96070:
		case 96170:
		case 96062:
		case 96162:
		case 96086:
		case 96186:
		case 96089:
		case 96189:
		case 95223:
		case 95227:
		case 95231:
		case 95235:
		case 95239:
		case 95243:
		case 213230:
		case 209254:
		case 95221:
		case 95225:
		case 95229:
		case 95233:
		case 95237:
		case 95241:
		case 214016:
		case 210040:
			break;
		case 85240:
			if (c.isBanking) {

				if (c.MoneyCash > 0 && c.MoneyCash != 0)
					c.getItems().addItemToBank(995, c.MoneyCash);
				c.sendMessage("You bank " + c.MoneyCash + " GP.");
				c.MoneyCash = 0;
				if (c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
					c.getPA().sendFrame126("" + c.MoneyCash / 1000 + "K", 8134);
				} else if (c.MoneyCash > 999999 && c.MoneyCash <= 2147483647) {
					c.getPA().sendFrame126("" + c.MoneyCash / 1000000 + "M",
							8134);
				} else {
					c.getPA().sendFrame126("" + c.MoneyCash + "", 8134);
				}
				c.getPA().sendFrame126("" + c.MoneyCash + "", 8135);
			}
			break;
		case 93196:
			c.getPA().closeAllWindows();
			break;
		// votecash interface
		case 74111:
			if (c.vote == 1 && c.getItems().freeSlots() >= 1) {
				c.getItems().addItem(995, 5000000);
				c.startAnimation(3543);
				c.sendMessage("Your receive 5m cash! Thank you for voting!");
				c.getPA().closeAllWindows();
				c.vote = 0;
				c.isChoosing = false;
			} else if (c.vote == 1 && c.getItems().freeSlots() <= 1) {
				Server.itemHandler.createGroundItem(c, 995, c.getX(), c.getY(),
						5000000, c.getId());
				c.startAnimation(3543);
				c.sendMessage("Your inventory is full so the item appears on the ground.");
				c.sendMessage("Your receive 5m cash! Thank you for voting!");
				c.getPA().closeAllWindows();
				c.vote = 0;
				c.isChoosing = false;
			}
			break;
		case 74108:
			if (c.vote == 1 && c.getItems().freeSlots() >= 1) {
				c.getItems().addItem(c.randomreward2(), 1);
				c.startAnimation(3543);
				c.sendMessage("Your receive a misc reward! Thank you for voting!");
				c.getPA().closeAllWindows();
				c.vote = 0;
				c.isChoosing = false;
			} else if (c.vote == 1 && c.getItems().freeSlots() <= 1) {
				// Server.itemHandler.createGroundItem(c, c.miscpackage1(),
				// c.getX(), c.getY(), 1, c.getId());
				Server.itemHandler.createGroundItem(c, c.randomreward2(),
						c.getX(), c.getY(), 1, c.getId());
				c.startAnimation(3543);
				c.sendMessage("Your inventory is full so the item appears on the ground.");
				c.sendMessage("Your receive a misc reward! Thank you for voting!");
				c.getPA().closeAllWindows();
				c.vote = 0;
				c.isChoosing = false;
			}
			break;
		// end of votecash
		// RING OF KINSHIPPP
		case 116109:
			c.getPA().closeAllWindows();
			c.getPA().showInterface(29812);
			break;
		case 116134:
			if (c.getItems().playerHasItem(995, 5000000)
					&& c.getItems().playerHasItem(15707, 1)) {
				c.getItems().deleteItem(995, 5000000);
				c.getItems().deleteItem(15707, 1);
				c.getItems().addItem(18821, 1);
				c.sendMessage("You change the colour of the ring to: Green!");
			} else {
				c.sendMessage("Get 5m GP and a ring of kinship (normal one)!");
			}
			break;
		case 116137:
			c.sendMessage("This colour is disabled, since it's the normal ring!");
			break;
		case 116140:
			if (c.getItems().playerHasItem(995, 5000000)
					&& c.getItems().playerHasItem(15707, 1)) {
				c.getItems().deleteItem(995, 5000000);
				c.getItems().deleteItem(15707, 1);
				c.getItems().addItem(18817, 1);
				c.sendMessage("You change the colour of the ring to: Red!");
			} else {
				c.sendMessage("Get 5m GP and a ring of kinship (normal one)!");
			}
			break;
		case 116143:
			if (c.getItems().playerHasItem(995, 5000000)
					&& c.getItems().playerHasItem(15707, 1)) {
				c.getItems().deleteItem(995, 5000000);
				c.getItems().deleteItem(15707, 1);
				c.getItems().addItem(18823, 1);
				c.sendMessage("You change the colour of the ring to: Blue!");
			} else {
				c.sendMessage("Get 5m GP and a ring of kinship (normal one)!");
			}
			break;
		/*
		 * case 86000:
		 * 
		 * break;
		 */
		case 20174:
			c.getDH().sendDialogues(119, 8275);
			// crafting + fletching interface:
			// case 89223: //Deposit Inventory old client
			/*
			 * case 66117: switch(c.hasFollower) { case 6870: //wolpertinger
			 * if(c.getItems().playerHasItem(12437, 1)) {
			 * c.getItems().deleteItem(12437, 1); c.gfx0(1311);
			 * if(c.playerLevel[6] > c.getLevelForXP(c.playerXP[6]))
			 * c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]); else
			 * c.playerLevel[6] += (c.getLevelForXP(c.playerXP[6]) * .1);
			 * c.getPA().refreshSkill(6);
			 * c.sendMessage("Your Magic bonus has increased!"); } else
			 * c.sendMessage("You don't have a scroll for that NPC!"); break;
			 */
		case 150:
			if (c.autoRet == 0)
				c.autoRet = 1;
			else
				c.autoRet = 0;
			break;
		case 70146:
			if (c.playerLevel[24] > 98) {
				c.getItems().addItem(18509, 1);
			} else {
				c.sendMessage("You must be 99 Dungeoneering to Recieve This.");
			}
			break;

		/*
		 * case 66122: switch(c.npcType) { case 6807: case 6874: case 6868: case
		 * 6795: case 6816: case 6873:
		 * 
		 * c.sendMessage("You are now storing items inside your npc");
		 * c.Summoning().store(); } break;
		 */
		case 66127:
			c.getDH().Kill();
			break;
		case 21010:
			c.takeAsNote = true;
			break;
		case 21011:
			c.takeAsNote = false;
			break;
		case 85248:
			c.takeAsNote = !c.takeAsNote;
			break;
		case 68244:
			c.getPA().startTeleport(2676, 3711, 0, "modern");
			break;
		case 54221:
			c.getPA().startTeleport(2897, 3618, 12, "modern");
			c.sendMessage("Welcome to The God Bandos's chamber");
			break;

		case 54231:
			// c.getPA().startTeleport(2897, 3618, 4, "modern");
			c.getPA().startTeleport(2897, 3618, 8, "modern");
			c.sendMessage("Welcome to The God Saradomin's chamber");
			break;

		case 54228:
			c.getPA().startTeleport(2897, 3618, 4, "modern");
			// c.getPA().startTeleport(2897, 3618, 8, "modern");
			c.sendMessage("Welcome to The God Armadyl's chamber");
			break;
		case 68247:
			c.getPA().startTeleport(2884, 9798, 0, "modern");
			break;
		case 68250:
			c.getPA().startTeleport(3428, 3537, 0, "modern");
			break;
		case 68253:
			c.getPA().startTeleport(2710, 9466, 0, "modern");
			break;
		case 69000:
			c.getPA().startTeleport(2905, 9730, 0, "modern");
			break;
		case 69003:
			c.getPA().startTeleport(2908, 9694, 0, "modern");
			break;
		/*
		 * case 69006: if((c.playerLevel[21] < 90) && (c.playerLevel[16] < 90))
		 * { c.sendMessage(
		 * "You need 90 Agility And 90 Hunter to enter the Strykworm's Cave"); }
		 * else { if((c.playerLevel[21] > 89) && (c.playerLevel[16] < 90)) {
		 * c.sendMessage("You need 90 Agility to enter the Strykworm's Cave"); }
		 * else { if((c.playerLevel[21] < 90) && (c.playerLevel[16] > 89)) {
		 * c.sendMessage("You need 90 Hunter to enter the Strykworm's Cave"); }
		 * else { if((c.playerLevel[21] > 89) && (c.playerLevel[16] >89)) {
		 * c.getPA().startTeleport(2515, 4632, 0, "modern");
		 * c.sendMessage("A sense of nervousness fills your body..");
		 * c.sendMessage("you find yourself in a mystery cave!"); } } } }
		 */

		case 69006:
			if ((c.playerLevel[21] < 90) && (c.playerLevel[16] < 90)) {
				c.sendMessage("You need 90 Agility And 90 Hunter to enter the Strykworm's Cave");
			} else {
				if ((c.playerLevel[21] > 89) && (c.playerLevel[16] < 90)) {
					c.sendMessage("You need 90 Agility to enter the Strykworm's Cave");
				} else {
					if ((c.playerLevel[21] < 90) && (c.playerLevel[16] > 89)) {
						c.sendMessage("You need 90 Hunter to enter the Strykworm's Cave");
					} else {
						if ((c.playerLevel[21] > 89)
								&& (c.playerLevel[16] > 89)) {
							c.getPA().startTeleport(2515, 4632, 0, "modern");
							c.sendMessage("A sense of nervousness fills your body..");
							c.sendMessage("you find yourself in a mystery cave!");
						}
					}
				}
			}

			break;
		case 153003:
			c.SaveGame();
			c.sendMessage("Your account has been saved");
			break;

		/*
		 * case 114112://melee set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 10) {
		 * c.sendMessage("You need atleast 10 free slot's to use this feature."
		 * ); } else if (c.getItems().playerHasItem(995, 350000)) {
		 * c.getItems().deleteItem2(995, 350000); c.getItems().addItem(10828,
		 * 1); c.getItems().addItem(1127, 1); c.getItems().addItem(1079, 1);
		 * c.getItems().addItem(3842, 1); c.getItems().addItem(4587, 1);
		 * c.getItems().addItem(1231, 1); c.getItems().addItem(1725, 1);
		 * c.getItems().addItem(3105, 1); c.getItems().addItem(2550, 1); } else
		 * {
		 * c.sendMessage("You need atleast 350,000 coins to use this feature.");
		 * } break; case 46230: c.getItems().addItem(10828, 1);
		 * c.getItems().addItem(10551, 1); c.getItems().addItem(4087, 1);
		 * c.getItems().addItem(11732, 1); c.getItems().addItem(13006, 1);
		 * c.getItems().addItem(1725, 1); c.getItems().addItem(6737, 1);
		 * c.getItems().addItem(8850, 1); c.getItems().addItem(4151, 1);
		 * c.getItems().addItem(995, 50000000); c.getPA().addChaotics();
		 * c.getPA().addSkillXP((15000000), 0); c.getPA().addSkillXP((15000000),
		 * 1); c.getPA().addSkillXP((15000000), 2);
		 * c.getPA().addSkillXP((15000000), 3); c.getPA().addSkillXP((15000000),
		 * 4); c.getPA().addSkillXP((15000000), 5);
		 * c.getPA().addSkillXP((15000000), 6); c.playerXP[3] =
		 * c.getPA().getXPForLevel(99)+5; c.playerLevel[3] =
		 * c.getPA().getLevelForXP(c.playerXP[3]); c.getPA().refreshSkill(3);
		 * c.puremaster = 1; break; case 46234: c.getItems().addItem(10941, 1);
		 * c.getItems().addItem(10939, 1); c.getItems().addItem(10940, 1);
		 * c.getItems().addItem(10933, 1); c.getItems().addItem(18508, 1);
		 * c.getItems().addItem(2462, 1); c.getItems().addItem(995, 50000000);
		 * c.getPA().addChaotics(); break; case 46227:
		 * c.getItems().addItem(12222, 1); c.getItems().addItem(6107, 1);
		 * c.getItems().addItem(2497, 1); c.getItems().addItem(3105, 1);
		 * c.getItems().addItem(12988, 1); c.getItems().addItem(10498, 1);
		 * c.getItems().addItem(1725, 1); c.getItems().addItem(861, 1);
		 * c.getItems().addItem(4151, 1); c.getItems().addItem(892, 1000);
		 * c.getItems().addItem(995, 50000000); c.getPA().addChaotics();
		 * c.getPA().addSkillXP((15000000), 0); c.getPA().addSkillXP((15000000),
		 * 2); c.getPA().addSkillXP((15000000), 3);
		 * c.getPA().addSkillXP((15000000), 4); c.getPA().addSkillXP((15000000),
		 * 6); c.playerXP[3] = c.getPA().getXPForLevel(99)+5; c.playerLevel[3] =
		 * c.getPA().getLevelForXP(c.playerXP[3]); c.getPA().refreshSkill(3);
		 * c.puremaster = 1; break;
		 * 
		 * case 114113://mage set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 7) {
		 * c.sendMessage("You need atleast 7 free slot's to use this feature.");
		 * } else if (c.getItems().playerHasItem(995, 300000)) {
		 * c.getItems().deleteItem2(995, 300000); c.getItems().addItem(4091, 1);
		 * c.getItems().addItem(4093, 1); c.getItems().addItem(3755, 1);
		 * c.getItems().addItem(2550, 1); c.getItems().addItem(1704, 1);
		 * c.getItems().addItem(3842, 1); c.getItems().addItem(4675, 1); } else
		 * {
		 * c.sendMessage("You need atleast 300,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114114://range set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 13) {
		 * c.sendMessage("You need atleast 13 free slot's to use this feature."
		 * ); } else if (c.getItems().playerHasItem(995, 450000)) {
		 * c.getItems().deleteItem2(995, 450000); c.getItems().addItem(3749, 1);
		 * c.getItems().addItem(1704, 1); c.getItems().addItem(2503, 1);
		 * c.getItems().addItem(2497, 1); c.getItems().addItem(2491, 1);
		 * c.getItems().addItem(6328, 1); c.getItems().addItem(2550, 1);
		 * c.getItems().addItem(9185, 1); c.getItems().addItem(9243, 100);
		 * c.getItems().addItem(10499, 1); c.getItems().addItem(861, 1);
		 * c.getItems().addItem(892, 100); } else {
		 * c.sendMessage("You need atleast 450,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114115://hybrid set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 14) {
		 * c.sendMessage("You need atleast 14 free slot's to use this feature."
		 * ); } else if (c.getItems().playerHasItem(995, 450000)) {
		 * c.getItems().deleteItem2(995, 450000); c.getItems().addItem(555,
		 * 300); c.getItems().addItem(560, 200); c.getItems().addItem(565, 100);
		 * c.getItems().addItem(4675, 1); c.getItems().addItem(2497, 1);
		 * c.getItems().addItem(2415, 1); c.getItems().addItem(10828, 1);
		 * c.getItems().addItem(3841, 1); c.getItems().addItem(2503, 1);
		 * c.getItems().addItem(7460, 1); c.getItems().addItem(1704, 1);
		 * c.getItems().addItem(2550, 1); c.getItems().addItem(4091, 1);
		 * c.getItems().addItem(4093, 1); c.getItems().addItem(3105, 1); } else
		 * {
		 * c.sendMessage("You need atleast 450,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114118://runes set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 10) {
		 * c.sendMessage("You need atleast 10 free slot's to use this feature."
		 * ); } else if (c.getItems().playerHasItem(995, 300000)) {
		 * c.getItems().deleteItem2(995, 300000);
		 * c.getItems().addItem(560,1000); c.getItems().addItem(555,1000);
		 * c.getItems().addItem(565,1000); c.getItems().addItem(9075,1000);
		 * c.getItems().addItem(557,1000); c.getItems().addItem(556,1000);
		 * c.getItems().addItem(554,1000); c.getItems().addItem(562,1000);
		 * c.getItems().addItem(561,1000); c.getItems().addItem(563,1000); }
		 * else {
		 * c.sendMessage("You need atleast 300,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114119://barrage set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 3) {
		 * c.sendMessage("You need atleast 3 free slot's to use this feature.");
		 * } else if (c.getItems().playerHasItem(995, 2000000)) {
		 * c.getItems().deleteItem2(995, 2000000);
		 * c.getItems().addItem(555,6000); c.getItems().addItem(560,4000);
		 * c.getItems().addItem(565,2000); } else {
		 * c.sendMessage("You need atleast 2,000,000 coins to use this feature."
		 * ); } break;
		 * 
		 * case 114120://veng set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 3) {
		 * c.sendMessage("You need atleast 3 free slot's to use this feature.");
		 * } else if (c.getItems().playerHasItem(995, 100000)) {
		 * c.getItems().deleteItem2(995, 100000);
		 * c.getItems().addItem(557,1000); c.getItems().addItem(560,200);
		 * c.getItems().addItem(9075,400); } else {
		 * c.sendMessage("You need atleast 100,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114123://shark set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 1) {
		 * c.sendMessage("You need atleast 1 free slot's to use this feature.");
		 * } else if (c.getItems().playerHasItem(995, 100000)) {
		 * c.getItems().deleteItem2(995, 100000);
		 * c.getItems().addItem(385,1000); } else {
		 * c.sendMessage("You need atleast 100,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114124://tuna pot set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 1) {
		 * c.sendMessage("You need atleast 1 free slot's to use this feature.");
		 * } else if (c.getItems().playerHasItem(995, 150000)) {
		 * c.getItems().deleteItem2(995, 150000);
		 * c.getItems().addItem(7060,1000); } else {
		 * c.sendMessage("You need atleast 150,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114125://super set if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 1) {
		 * c.sendMessage("You need atleast 1 free slot's to use this feature.");
		 * } else if (c.getItems().playerHasItem(995, 80000)) {
		 * c.getItems().deleteItem2(995, 80000); c.getItems().addItem(146,100);
		 * c.getItems().addItem(158,100); c.getItems().addItem(164,100); } else
		 * {
		 * c.sendMessage("You need atleast 80,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114126://super restores biatch if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 1) {
		 * c.sendMessage("You need atleast 1 free slot's to use this feature.");
		 * } else if (c.getItems().playerHasItem(995, 30000)) {
		 * c.getItems().deleteItem2(995, 30000); c.getItems().addItem(3025,100);
		 * } else {
		 * c.sendMessage("You need atleast 30,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114127://mage pots if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 1) {
		 * c.sendMessage("You need atleast 1 free slot's to use this feature.");
		 * } else if (c.getItems().playerHasItem(995, 30000)) {
		 * c.getItems().deleteItem2(995, 30000); c.getItems().addItem(3041,100);
		 * } else {
		 * c.sendMessage("You need atleast 30,000 coins to use this feature.");
		 * } break;
		 * 
		 * case 114128://range pots if (c.inWild() && c.isBanking) {
		 * c.sendMessage("You cannot do this right now"); } else
		 * if(c.getItems().freeSlots() <= 1) {
		 * c.sendMessage("You need atleast 1 free slot's to use this feature.");
		 * } else if (c.getItems().playerHasItem(995, 36000)) {
		 * c.getItems().deleteItem2(995, 36000); c.getItems().addItem(2445,100);
		 * } else {
		 * c.sendMessage("You need atleast 36,000 coins to use this feature.");
		 * } break;
		 */

		case 17111:// stop viewing viewing orb
			if (c.InDung() || c.InDung2() || c.inDungBossRoom()
					|| c.inDuelArena() || !c.inPits()) {
				c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders!");
				return;
			}
			c.setSidebarInterface(10, 2449);
			c.viewingOrb = false;
			c.teleportToX = 2399;
			c.teleportToY = 5171;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
			break;

		case 59139:// viewing orb southwest
			if (c.InDung() || c.InDung2() || c.inDungBossRoom()
					|| c.inDuelArena() || !c.inPits()) {
				c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders!");
				return;
			}
			c.viewingOrb = true;
			c.teleportToX = 2388;
			c.teleportToY = 5138;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
			break;

		case 59138:// viewing orb southeast
			if (c.InDung() || c.InDung2() || c.inDungBossRoom()
					|| c.inDuelArena() || !c.inPits()) {
				c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders!");
				return;
			}
			c.viewingOrb = true;
			c.teleportToX = 2411;
			c.teleportToY = 5137;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
			break;

		case 59137:// viewing orb northeast
			if (c.InDung() || c.InDung2() || c.inDungBossRoom()
					|| c.inDuelArena() || !c.inPits()) {
				c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders!");
				return;
			}
			c.viewingOrb = true;
			c.teleportToX = 2409;
			c.teleportToY = 5158;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
			break;

		case 59136:// viewing orb northwest
			if (c.InDung() || c.InDung2() || c.inDungBossRoom()
					|| c.inDuelArena() || !c.inPits()) {
				c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders!");
				return;
			}
			c.viewingOrb = true;
			c.teleportToX = 2384;
			c.teleportToY = 5157;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
			break;

		case 59135:// viewing orb middle
			if (c.InDung() || c.InDung2() || c.inDungBossRoom()
					|| c.inDuelArena() || !c.inPits()) {
				c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders!");
				return;
			}
			c.viewingOrb = true;
			c.teleportToX = 2398;
			c.teleportToY = 5150;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 107229:
			if (c.isDonator == 1 && c.slayerTask <= 0) {
				c.getDH().sendDialogues(11, 8275);
				c.sendMessage("Your magical donator rank makes you able to contact Duradel!");
			} else {
				c.sendMessage("You alredy have a task!");
			}
			break;

		case 108003:
			/*
			 * if (c.isDonator == 1) { c.setSidebarInterface(4, 27620); } else {
			 * c.sendMessage("You must be an donator to view this tab!");
			 * return; }
			 */
			if (c.xpLock == false) {
				c.xpLock = true;
				c.sendMessage("<col=255>Your XP is now LOCKED!</col>");
			} else {
				c.xpLock = false;
				c.sendMessage("<col=255>Your XP is now UNLOCKED!</col>");
			}
			break;

		case 85252:
		case 82008:
		case 89223: // Deposit Inventory
			if (c.isShopping) {
				c.sendMessage("You cannot bank items while shopping! - Walk then try.");
				return;
			}
			if (c.isBanking)
				c.getItems().depositInventory();
			break;
		case 86000: // Deposit Worn Items
			if (!c.isBanking) {
				return;
			}
			if (c.isShopping) {
				c.sendMessage("You cannot bank items while shopping! - Walk then try.");
				return;
			}
			c.getItems().bankEquipment();
			c.getPA().searchBank(c, c.searchTerm);
			c.sendMessage("You bank your equipment.");
			break;
		case 107231:
			if (c.isDonator == 1) {
				c.getPA().spellTeleport(2524, 4777, 0);
				c.sendMessage("<img=0>You teleported to donator Island a place to chill/relax, theres also alot of benefits.<img=0>");
			} else {
				c.sendMessage("You must be an donator to teleport to the donator Island!");
				return;
			}
			break;
		/*
		 * case 108006: if(!c.isSkulled) { c.getItems().resetKeepItems();
		 * c.getItems().keepItem(0, false); c.getItems().keepItem(1, false);
		 * c.getItems().keepItem(2, false); c.getItems().keepItem(3, false);
		 * c.sendMessage(
		 * "You can keep three items and a fourth if you use the protect item prayer."
		 * ); } else { c.getItems().resetKeepItems(); c.getItems().keepItem(0,
		 * false); c.sendMessage(
		 * "You are skulled and will only keep one item if you use the protect item prayer."
		 * ); } c.getItems().sendItemsKept(); c.getPA().showInterface(6960);
		 * c.getItems().resetKeepItems(); break;
		 */
		case 108006: // items kept on death?
			c.StartBestItemScan();
			c.EquipStatus = 0;
			for (int k = 0; k < 4; k++)
				c.getPA().sendFrame34a(10494, -1, k, 1);
			for (int k = 0; k < 39; k++)
				c.getPA().sendFrame34a(10600, -1, k, 1);
			if (c.WillKeepItem1 > 0)
				c.getPA().sendFrame34a(10494, c.WillKeepItem1, 0,
						c.WillKeepAmt1);
			if (c.WillKeepItem2 > 0)
				c.getPA().sendFrame34a(10494, c.WillKeepItem2, 1,
						c.WillKeepAmt2);
			if (c.WillKeepItem3 > 0)
				c.getPA().sendFrame34a(10494, c.WillKeepItem3, 2,
						c.WillKeepAmt3);
			if (c.WillKeepItem4 > 0)
				c.getPA().sendFrame34a(10494, c.WillKeepItem4, 3, 1);
			for (int ITEM = 0; ITEM < 28; ITEM++) {
				if (c.playerItems[ITEM] - 1 > 0
						&& !(c.playerItems[ITEM] - 1 == c.WillKeepItem1 && ITEM == c.WillKeepItem1Slot)
						&& !(c.playerItems[ITEM] - 1 == c.WillKeepItem2 && ITEM == c.WillKeepItem2Slot)
						&& !(c.playerItems[ITEM] - 1 == c.WillKeepItem3 && ITEM == c.WillKeepItem3Slot)
						&& !(c.playerItems[ITEM] - 1 == c.WillKeepItem4 && ITEM == c.WillKeepItem4Slot)) {
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM] - 1,
							c.EquipStatus, c.playerItemsN[ITEM]);
					c.EquipStatus += 1;
				} else if (c.playerItems[ITEM] - 1 > 0
						&& (c.playerItems[ITEM] - 1 == c.WillKeepItem1 && ITEM == c.WillKeepItem1Slot)
						&& c.playerItemsN[ITEM] > c.WillKeepAmt1) {
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM] - 1,
							c.EquipStatus,
							c.playerItemsN[ITEM] - c.WillKeepAmt1);
					c.EquipStatus += 1;
				} else if (c.playerItems[ITEM] - 1 > 0
						&& (c.playerItems[ITEM] - 1 == c.WillKeepItem2 && ITEM == c.WillKeepItem2Slot)
						&& c.playerItemsN[ITEM] > c.WillKeepAmt2) {
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM] - 1,
							c.EquipStatus,
							c.playerItemsN[ITEM] - c.WillKeepAmt2);
					c.EquipStatus += 1;
				} else if (c.playerItems[ITEM] - 1 > 0
						&& (c.playerItems[ITEM] - 1 == c.WillKeepItem3 && ITEM == c.WillKeepItem3Slot)
						&& c.playerItemsN[ITEM] > c.WillKeepAmt3) {
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM] - 1,
							c.EquipStatus,
							c.playerItemsN[ITEM] - c.WillKeepAmt3);
					c.EquipStatus += 1;
				} else if (c.playerItems[ITEM] - 1 > 0
						&& (c.playerItems[ITEM] - 1 == c.WillKeepItem4 && ITEM == c.WillKeepItem4Slot)
						&& c.playerItemsN[ITEM] > 1) {
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM] - 1,
							c.EquipStatus, c.playerItemsN[ITEM] - 1);
					c.EquipStatus += 1;
				}
			}
			for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
				if (c.playerEquipment[EQUIP] > 0
						&& !(c.playerEquipment[EQUIP] == c.WillKeepItem1 && EQUIP + 28 == c.WillKeepItem1Slot)
						&& !(c.playerEquipment[EQUIP] == c.WillKeepItem2 && EQUIP + 28 == c.WillKeepItem2Slot)
						&& !(c.playerEquipment[EQUIP] == c.WillKeepItem3 && EQUIP + 28 == c.WillKeepItem3Slot)
						&& !(c.playerEquipment[EQUIP] == c.WillKeepItem4 && EQUIP + 28 == c.WillKeepItem4Slot)) {
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP],
							c.EquipStatus, c.playerEquipmentN[EQUIP]);
					c.EquipStatus += 1;
				} else if (c.playerEquipment[EQUIP] > 0
						&& (c.playerEquipment[EQUIP] == c.WillKeepItem1 && EQUIP + 28 == c.WillKeepItem1Slot)
						&& c.playerEquipmentN[EQUIP] > 1
						&& c.playerEquipmentN[EQUIP] - c.WillKeepAmt1 > 0) {
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP],
							c.EquipStatus,
							c.playerEquipmentN[EQUIP] - c.WillKeepAmt1);
					c.EquipStatus += 1;
				} else if (c.playerEquipment[EQUIP] > 0
						&& (c.playerEquipment[EQUIP] == c.WillKeepItem2 && EQUIP + 28 == c.WillKeepItem2Slot)
						&& c.playerEquipmentN[EQUIP] > 1
						&& c.playerEquipmentN[EQUIP] - c.WillKeepAmt2 > 0) {
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP],
							c.EquipStatus,
							c.playerEquipmentN[EQUIP] - c.WillKeepAmt2);
					c.EquipStatus += 1;
				} else if (c.playerEquipment[EQUIP] > 0
						&& (c.playerEquipment[EQUIP] == c.WillKeepItem3 && EQUIP + 28 == c.WillKeepItem3Slot)
						&& c.playerEquipmentN[EQUIP] > 1
						&& c.playerEquipmentN[EQUIP] - c.WillKeepAmt3 > 0) {
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP],
							c.EquipStatus,
							c.playerEquipmentN[EQUIP] - c.WillKeepAmt3);
					c.EquipStatus += 1;
				} else if (c.playerEquipment[EQUIP] > 0
						&& (c.playerEquipment[EQUIP] == c.WillKeepItem4 && EQUIP + 28 == c.WillKeepItem4Slot)
						&& c.playerEquipmentN[EQUIP] > 1
						&& c.playerEquipmentN[EQUIP] - 1 > 0) {
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP],
							c.EquipStatus, c.playerEquipmentN[EQUIP] - 1);
					c.EquipStatus += 1;
				}
			}

			c.ResetKeepItems();
			c.getPA().showInterface(17100);

			break;
		case 107230:
			if (c.isDonator == 0 || c.inWild()) {
				c.sendMessage("You must be outside wilderness and be a donator to use this!");
				return;
			}
			if (c.playerMagicBook == 0 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 12855);
				c.setSidebarInterface(0, 328);
				c.sendMessage("An ancient wisdomin fills your mind.");
				c.getPA().resetAutocast();
				c.dialogueAction = 0;
				return;
			}
			if (c.playerMagicBook == 1 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 2;
				c.setSidebarInterface(0, 328);
				c.setSidebarInterface(6, 29999);
				c.sendMessage("Your mind becomes stirred with thoughs of dreams.");
				c.getPA().resetAutocast();
				return;
			}
			if (c.playerMagicBook == 2 && c.isDonator == 1 && !c.inWild()) {
				c.setSidebarInterface(6, 1151); // modern
				c.playerMagicBook = 0;
				c.setSidebarInterface(0, 328);
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
				return;
			}
			break;
		case 94142:
			if (c != null) {
				if (c.hasFollower > 0) {
					for (int i = 0; i < 29; i += 1) {
						if (c.storeditems[i] > 0 && c != null
								&& NPCHandler.npcs[c.summoningnpcid] != null) {
							Server.itemHandler.createGroundItem(c,
									c.storeditems[i],
									NPCHandler.npcs[c.summoningnpcid].absX,
									NPCHandler.npcs[c.summoningnpcid].absY, 1,
									c.playerId);
						}
						c.SaveGame();
						c.firstslot();
						c.storeditems[i] = -1;
						c.occupied[i] = false;
						c.yak = false;
						c.hasFollower = -1;
						c.totalstored = 0;
						c.summoningnpcid = 0;
						c.summoningslot = 0;
						c.yak = false;
						c.getPA().sendFrame126(
								"   " + c.playerLevel[21] + "/"
										+ c.getLevelForXP(c.playerXP[21]),
								17025);
						c.getPA().sendFrame126(
								"" + c.summonTime / 120 + ".00 Min", 17021);
						boolean sent = false;
						if (!sent) {
							c.sendMessage("Your BoB's items have dropped to the floor.");
							c.SaveGame();
							sent = true;
						}

					}
				} else {
					c.sendMessage("You do not have a summoned NPC at the moment.");
				}
			}

		case 129141:
			GabbesAchievements.openBook(c, "Easy");
			break;
		case 9190:
			if (c.teleAction == 244) {
				if (c != null)
					Construction.fetchItem(c, 4820, 100);
				return;
			}
			if (c.teleAction == 4287) {
				if (c != null)
					Construction.fetchItem(c, 1775, 1);
				return;
			}
			if (c.teleAction == 4714) {
				if (c != null)
					Construction.fetchItem(c, 4692, 1);
				return;
			}
			if (c.teleAction == 1645) {
				if (c != null)
					Construction.fetchItem(c, 1539, 100);
				return;
			}
			if (c.teleAction == 146) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Tree")) {
					c.getPA().checkObjectSpawn(1276, 1889, 5093, 1, 10);
					c.constructionObjects[7] = true;
					c.constructionUpgrades[7] = 1276;
				}
				return;
			}
			if (c.teleAction == 123) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Wooden Globe")) {
					c.getPA().checkObjectSpawn(13649, 1889, 5100, 1, 10);
					c.constructionObjects[11] = true;
					c.constructionUpgrades[11] = 13649;
				}
				return;
			}
			if (c.teleAction == 4286) {
				if (c != null)
					Construction.fetchItem(c, 960, 1);
				return;
			}
			if (c.teleAction == 4296) {
				if (c != null)
					Construction.fetchItem(c, 8778, 1);
				return;
			}
			if (c.teleAction == 4297) {
				if (c != null)
					Construction.fetchItem(c, 8780, 1);
				return;
			}
			if (c.teleAction == 4298) {
				if (c != null)
					Construction.fetchItem(c, 8782, 1);
				return;
			}
			if (c.teleAction == 53) {
				Prayer.bones1(c, c.bone);
				c.getPA().closeAllWindows();
				c.teleAction = 0;
				return;
			}
			if (c.dialogueAction == 61) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You don't have a hammer with you!");
					c.getPA().closeAllWindows();
					return;
				}
				if (c.getItems().playerHasItem(2349, 1)) {
					c.getSmithingInt().showSmithInterface(2349);
					return;
				} else {
					c.getPA().closeAllWindows();
					c.sendMessage("You don't have any Bronze bars.");
				}
			}

			if (c.dialogueAction == 51) {
				if (c.chosenChaotics == false) {
					c.getItems().addItem(18353, 1);
					c.addChaotics = true;
					c.chooseChaotic = false;
					c.getPA().closeAllWindows();
					c.selectStarter = false;
					c.stopPlayerSkill = false;
					c.chosenChaotics = true;
				}
			}
			if (c.teleAction == 43) {
				c.getPA().startTeleport(3222, 3218, 0, "normal");
			}
			if (c.dialogueAction == 106) {
				if (c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID,
							c.getItems().getItemSlot(c.diceID), 1);
					c.getItems().addItem(15086, 1);
					c.sendMessage("You get a six-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 50) {
				if (c.chosenStarter == false) {
					c.trade11 = 1400;
					c.trade11();
					c.getItems().addItem(1704, 1);
					// c.getItems().addItem(18349, 1);
					c.getItems().addItem(995, 5000000);
					c.getPA().closeAllWindows();
					c.getItems().addItem(6950, 1);
					c.getItems().addItem(6107, 1);
					c.getItems().addItem(6108, 1);
					c.getItems().addItem(3106, 10);
					c.getItems().addItem(4676, 10);
					c.getItems().addItem(2413, 1);
					c.getItems().addItem(11119, 10);
					c.getItems().addItem(3842, 1);
					c.getItems().addItem(2941, 10);
					c.getItems().addItem(10499, 1);
					c.getItems().addItem(5699, 10);
					c.getItems().addItem(4154, 10);
					c.getItems().addItem(555, 1000);
					c.getItems().addItem(560, 1000);
					c.getItems().addItem(565, 1000);
					c.getItems().addItem(9341, 100);
					c.getItems().addItem(386, 1500);
					c.getItems().addItem(9186, 10);
					c.getItems().addItem(2498, 10);
					c.getItems().addItem(146, 100);
					c.getItems().addItem(158, 100);
					c.getItems().addItem(2435, 100);
					c.getItems().addItem(170, 100);
					c.getItems().addItem(3041, 100);
					/*
					 * c.getPA().addSkillXP(737632, 4);
					 * c.getPA().addSkillXP(2421092, 6);
					 * c.getPA().addSkillXP(127660, 5);
					 * c.getPA().addSkillXP(104005, 0);
					 * c.getPA().addSkillXP(1986073, 2);
					 * c.getPA().addSkillXP(3358594, 3);
					 */
					// c.getPA().setLevel(6, 82);
					// c.getPA().setLevel(0, 50);
					// c.getPA().setLevel(2, 80);
					// c.getPA().setLevel(4, 70);
					// c.getPA().setLevel(3, 75);
					// c.sendMessage("<shad=15369497>To get RFD gloves, do the Recipe for Disaster minigame south of edgeville general store!!<shad=15369497>");
					c.sendMessage("This server is one of the best.. Real summoning.. Ect.. Enjoy it & Give it a chance!");
					c.dialogueAction = -1;
					c.getPA().requestUpdates();
					//
					c.chosenStarter = true;
					c.canChangeAppearance = true;
				}
			} else if (c.teleAction == 6) {
				// kalphite queen
				c.getPA().spellTeleport(2895, 3618, 4);
				c.sendMessage("WARNING THERE IS A HUGE CHANCE YOU WILL BE 1 SHOT WHEN YOU GO IN");
				c.sendMessage("MAKE SURE YOU HAVE A TEAM WITH YOU GOOD LUCK");
				c.sendMessage("THE MONSTERS HAVE TAKEN OVER THIS LAND! FIGHT BACK FOR IT");
			} else if (c.dialogueAction == 107) {
				if (c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID,
							c.getItems().getItemSlot(c.diceID), 1);
					c.getItems().addItem(15092, 1);
					c.sendMessage("You get a ten-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			}
			if (c.teleAction == 1) {
				// rock crabs
				c.getPA().spellTeleport(2676, 3715, 0);
			} else if (c.teleAction == 2) {
				// barrows
				c.getPA().spellTeleport(3565, 3314, 0);
				// c.sendMessage("DISABLED ATM DUE TO BUGS, FIXING THIS WEEKEND");
			} else if (c.teleAction == 3) {
				c.sendMessage("GodWars is under construction atm please wait untill its fixed");
			} else if (c.teleAction == 4) {
				// varrock wildy
				c.getPA().spellTeleport(2539, 4716, 0);
			} else if (c.teleAction == 5) {
				c.getDH().sendOption2("Crafting", "Hunter");
				// c.getPA().spellTeleport(3298,3287,0);
				// c.sendMessage("You can mine almost everything here.. Enjoy.");
			} else if (c.teleAction == 20) {
				// lum
				c.getPA().spellTeleport(2606, 3093, 0);// 3222 3218
			} else if (c.teleAction == 8) {
				c.getPA().spellTeleport(2960, 9477, 0);// sea troll queen
			}

			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2845, 4832, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				c.getPA().spellTeleport(2786, 4839, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 12) {
				c.getPA().spellTeleport(2398, 4841, 0);
				c.dialogueAction = -1;
			} else if (c.teleAction == 21) {
				c.getPA().spellTeleport(2604, 3154, 0);// 2480 3437
				c.dialogueAction = -1;
			}
			break;

		case 66126:
			if (c.hasFollower > 0) {
				// c.sumTimer = 3;
				c.SaveGame();
				c.callFamilliar = true;
				c.sendMessage("Familliar called");

			}

			if (c.hasFollower <= 0) {
				c.sendMessage("You don't have a summoned follower.");
			}
			break;

		case 154:
			if (System.currentTimeMillis() - c.logoutDelay < 8000) {
				c.sendMessage("You cannot do skillcape emotes in combat!");
				return;
			}
			if (System.currentTimeMillis() - c.lastEmote >= 7000) {
				if (c.getPA().wearingCape(c.playerEquipment[Player.playerCape])) {
					c.stopMovement();
					c.gfx0(c.getPA().skillcapeGfx(
							c.playerEquipment[Player.playerCape]));
					c.startAnimation(c.getPA().skillcapeEmote(
							c.playerEquipment[Player.playerCape]));
				} else if (c.playerEquipment[Player.playerCape] == 19710) {
					c.getPA().dungemote(c);
				} else if (c.playerEquipment[Player.playerCape] == 12747) {
					c.getPA().compemote(c);
				} else if (c.playerEquipment[Player.playerCape] == 12744) {
					c.getPA().compemote(c);
				} else if (c.playerEquipment[Player.playerCape] == 18509) {
					c.getPA().dungemote2(c);
				} else if (c.playerEquipment[Player.playerCape] == 19709) {
					c.getPA().dungemote(c);
				} else if (c.playerEquipment[Player.playerCape] == 18508) {
					c.getPA().dungemote(c);
				} else if (c.playerEquipment[Player.playerCape] == 15117) {

					c.getPA().vetEmote(c);
				} else if (c.playerEquipment[Player.playerCape] == 15121) {

					c.getPA().compemote(c);
				} else {
					c.sendMessage("You must be wearing a Skillcape to do this emote.");
				}
				c.lastEmote = System.currentTimeMillis();
			}
			break;

		// 2nd tele option
		case 9191:
			if (c.teleAction == 4287) {
				if (c != null)
					Construction.fetchItem(c, 1775, 5);
				return;
			}
			if (c.teleAction == 4714) {
				if (c != null)
					Construction.fetchItem(c, 4692, 5);
				return;
			}
			if (c.teleAction == 1645) {
				if (c != null)
					Construction.fetchItem(c, 1539, 500);
				return;
			}
			if (c.teleAction == 244) {
				if (c != null)
					Construction.fetchItem(c, 4820, 500);
				return;
			}
			if (c.teleAction == 123) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Ornamental Globe")) {
					c.getPA().checkObjectSpawn(13650, 1889, 5100, 1, 10);
					c.constructionObjects[11] = true;
					c.constructionUpgrades[11] = 13650;
				}
				return;
			}
			if (c.teleAction == 146) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Oak tree")) {
					c.getPA().checkObjectSpawn(1281, 1899, 5095, 1, 10);
					c.constructionObjects[7] = true;
					c.constructionUpgrades[7] = 1281;
				}
				return;
			}
			if (c.teleAction == 4286) {
				if (c != null)
					Construction.fetchItem(c, 960, 5);
				return;
			}
			if (c.teleAction == 4296) {
				if (c != null)
					Construction.fetchItem(c, 8778, 5);
				return;
			}
			if (c.teleAction == 4297) {
				if (c != null)
					Construction.fetchItem(c, 8780, 5);
				return;
			}
			if (c.teleAction == 4298) {
				if (c != null)
					Construction.fetchItem(c, 8782, 5);
				return;
			}
			if (c.dialogueAction == 35) {
				c.getItems().handleEffigies(12);
				return;
			} else if (c.dialogueAction == 36) {
				c.getItems().handleEffigies(20);
				return;
			} else if (c.dialogueAction == 37) {
				c.getItems().handleEffigies(7);
				return;
			} else if (c.dialogueAction == 38) {
				c.getItems().handleEffigies(19);
				return;
			} else if (c.dialogueAction == 39) {
				c.getItems().handleEffigies(9);
				return;
			} else if (c.dialogueAction == 40) {
				c.getItems().handleEffigies(15);
				return;
			} else if (c.dialogueAction == 41) {
				c.getItems().handleEffigies(13);
				return;
			}

			if (c.teleAction == 53) {
				Prayer.bones5(c, c.bone);
				c.getPA().closeAllWindows();
				c.teleAction = 0;
				return;
			}
			if (c.dialogueAction == 61) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You don't have a hammer with you!");
					c.getPA().closeAllWindows();
					return;
				}
				if (c.getItems().playerHasItem(2351, 1)) {
					c.getSmithingInt().showSmithInterface(2351);
					return;
				} else {
					c.getPA().closeAllWindows();
					c.sendMessage("You don't have any Iron bars.");
					return;
				}
			}

			if (c.dialogueAction == 51) {
				if (c.chosenChaotics == false) {
					c.addChaotics = true;
					c.chooseChaotic = false;
					c.getItems().addItem(18349, 1);
					c.getPA().closeAllWindows();
					c.selectStarter = false;
					c.stopPlayerSkill = false;
					c.chosenChaotics = true;
				}
			}

			if (c.teleAction == 199) {
				c.getPA().spellTeleport(2724, 3500, 0);
				c.sendMessage("To start, enter the portal!");
				c.sendMessage("Everytime you kill a boss in the minigame, a pair of gloves will be unlocked.");
				c.sendMessage("To buy gloves click the chest.");
			}
			if (c.dialogueAction == 106) {
				if (c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID,
							c.getItems().getItemSlot(c.diceID), 1);
					c.getItems().addItem(15088, 1);
					c.sendMessage("You get two six-sided dice out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 50) {
				if (c.chosenStarter == false) {
					c.trade11 = 1400;
					c.trade11();
					/*
					 * if (Server.singleStarter.contains(c.connectedFrom)) {
					 * c.sendMessage(
					 * "You have already received a starter recently. You may receive another later."
					 * ); // c.getPA().closeAllWindows(); return; }
					 */
					c.getItems().addItem(1704, 1);
					c.getItems().addItem(995, 5000000);
					// Server.singleStarter.add(c.connectedFrom);
					c.getPA().closeAllWindows();
					// c.getItems().addItem(6950, 1);
					c.getItems().addItem(4588, 10);
					c.getItems().addItem(1080, 10);
					c.getItems().addItem(1128, 10);
					c.getItems().addItem(3106, 10);
					c.getItems().addItem(4676, 10);
					c.getItems().addItem(2413, 1);
					c.getItems().addItem(11119, 10);
					c.getItems().addItem(3842, 1);
					c.getItems().addItem(3752, 10);
					c.getItems().addItem(10499, 1);
					c.getItems().addItem(5699, 10);
					c.getItems().addItem(4154, 10);
					c.getItems().addItem(555, 1000);
					c.getItems().addItem(560, 1000);
					c.getItems().addItem(565, 1000);
					c.getItems().addItem(4094, 10);
					c.getItems().addItem(4092, 10);
					c.getItems().addItem(386, 1500);
					// c.getItems().addItem(18349, 1);
					c.getItems().addItem(2498, 10);
					c.getItems().addItem(146, 100);
					c.getItems().addItem(158, 100);
					c.getItems().addItem(2435, 100);
					c.getItems().addItem(170, 100);
					c.getItems().addItem(3041, 100);
					// c.getPA().setLevel(5, 52);
					// c.getPA().setLevel(6, 82);
					// c.getPA().setLevel(0, 50);
					// c.getPA().setLevel(1, 45);
					// c.getPA().setLevel(2, 80);
					// c.getPA().setLevel(4, 70);
					// c.getPA().setLevel(3, 80);
					// c.sendMessage("<shad=15369497>To get RFD gloves, do the Recipe for Disaster minigame south of edgeville general store!!<shad=15369497>");
					c.sendMessage("This server is one of the best.. Real summoning.. Ect.. Enjoy it & Give it a chance!");
					c.dialogueAction = -1;
					c.getPA().requestUpdates();
					// c.getDH().sendDialogues(22, -1);
					// c.selectStarter = false; c.stopPlayerSkill = false;
					c.chosenStarter = true;
					c.selectStarterr = false;
					c.getPA().addChaotics();
					c.canChangeAppearance = true;
				} else {
					c.getPA().addChaotics();
				}
			} else if (c.dialogueAction == 107) {
				if (c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID,
							c.getItems().getItemSlot(c.diceID), 1);
					c.getItems().addItem(15094, 1);
					c.sendMessage("You get a twelve-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			}
			if (c.teleAction == 1) {
				// tav dungeon
				c.getPA().spellTeleport(2884, 9798, 0);
			} else if (c.teleAction == 2) {
				// pest control
				c.getPA().spellTeleport(2662, 2650, 0);
			} else if (c.teleAction == 3) {
				// kbd
				c.getPA().spellTeleport(3007, 3849, 0);
			} else if (c.teleAction == 6) {
				// Giant Mole REDONE TO FORGOTTEN WARRIOR
				c.getPA().spellTeleport(2916, 5269, 0);
				// }
			} else if (c.teleAction == 4) {
				// graveyard
				c.getPA().spellTeleport(3243, 3517, 0);
			} else if (c.teleAction == 5) {
				// c.getPA().spellTeleport(3300, 3302,0);
				c.getDH().sendOption2("Mining", "Smithing");
				c.teleAction = 999;

			} else if (c.teleAction == 8) {
				c.getPA().spellTeleport(2984, 9630, 0);
				c.sendMessage("Beware: Recommended team of 5 Players or More");

			} else if (c.teleAction == 20) {
				c.getPA().spellTeleport(3210, 3424, 0);// 3210 3424
			}
			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2796, 4818, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				c.getPA().spellTeleport(2527, 4833, 0);
				c.dialogueAction = -1;
			}
			if (c.teleAction == 21) { // hunter
				c.getPA().spellTeleport(3243, 3582, 0);
				c.dialogueAction = -1;
			}
			break;
		// 3rd tele option

		case 9192:
			if (c.teleAction == 4287) {
				if (c != null)
					Construction.fetchItem(c, 1775, 10);
				return;
			}
			if (c.teleAction == 1645) {
				if (c != null)
					Construction.fetchItem(c, 1539, 1000);
				return;
			}
			if (c.teleAction == 4714) {
				if (c != null)
					Construction.fetchItem(c, 4692, 10);
				return;
			}
			if (c.teleAction == 123) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Lunar Globe")) {
					c.getPA().checkObjectSpawn(13651, 1889, 5100, 1, 10);
					c.constructionObjects[11] = true;
					c.constructionUpgrades[11] = 13651;
				}
				return;
			}
			if (c.teleAction == 146) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Willow tree")) {
					c.getPA().checkObjectSpawn(1308, 1899, 5095, 1, 10);
					c.constructionObjects[7] = true;
					c.constructionUpgrades[7] = 1308;
				}
				return;
			}
			if (c.teleAction == 244) {
				if (c != null)
					Construction.fetchItem(c, 4820, 1000);
				return;
			}
			if (c.teleAction == 4286) {
				if (c != null)
					Construction.fetchItem(c, 960, 10);
				return;
			}
			if (c.teleAction == 4296) {
				if (c != null)
					Construction.fetchItem(c, 8778, 10);
				return;
			}
			if (c.teleAction == 4297) {
				if (c != null)
					Construction.fetchItem(c, 8780, 10);
				return;
			}
			if (c.teleAction == 4298) {
				if (c != null)
					Construction.fetchItem(c, 8782, 10);
				return;
			}
			if (c.teleAction == 53) {
				Prayer.bones10(c, c.bone);
				c.getPA().closeAllWindows();
				c.teleAction = 0;
				return;
			}
			if (c.dialogueAction == 61) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You don't have a hammer with you!");
					c.getPA().closeAllWindows();
					return;
				}
				if (c.getItems().playerHasItem(2353, 1)) {
					c.getSmithingInt().showSmithInterface(2353);
					return;
				} else {
					c.getPA().closeAllWindows();
					c.sendMessage("You don't have any Steel bars.");
				}
			}
			if (c.dialogueAction == 51) {
				if (c.chosenChaotics == false) {
					c.getItems().addItem(18351, 1);
					c.addChaotics = true;
					c.chooseChaotic = false;
					c.getPA().closeAllWindows();
					c.selectStarter = false;
					c.stopPlayerSkill = false;
					c.chosenChaotics = true;
				}
			}
			if (c.teleAction == 199) { // Nomad
				c.getPA().spellTeleport(2967, 3206, 0);
			}
			if (c.teleAction == 21) { // Summoning
				c.getPA().spellTeleport(3450, 3515, 0);
				c.dialogueAction = -1;
			}
			if (c.dialogueAction == 106) {
				if (c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID,
							c.getItems().getItemSlot(c.diceID), 1);
					c.getItems().addItem(15100, 1);
					c.sendMessage("You get a four-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();

			} else if (c.dialogueAction == 50) {
				if (c.chosenStarter == false) {
					c.trade11 = 1400;
					c.trade11();
					/*
					 * if (Server.singleStarter.contains(c.connectedFrom)) {
					 * c.sendMessage(
					 * "You have already received a starter recently. You may receive another later."
					 * ); // c.getPA().closeAllWindows(); return; }
					 */
					// c.getItems().addItem(18349, 1);
					c.getItems().addItem(1704, 1);
					c.getItems().addItem(995, 5000000);
					// Server.singleStarter.add(c.connectedFrom);
					c.getPA().closeAllWindows();
					// c.getItems().addItem(6950, 1);
					c.getItems().addItem(6329, 10);
					c.getItems().addItem(2504, 10);
					c.getItems().addItem(892, 10000);
					c.getItems().addItem(9186, 10);
					c.getItems().addItem(862, 10);
					c.getItems().addItem(9244, 300);
					c.getItems().addItem(2492, 10);
					c.getItems().addItem(3750, 10);
					c.getItems().addItem(10499, 1);
					c.getItems().addItem(9075, 1000);
					c.getItems().addItem(557, 1000);
					c.getItems().addItem(560, 1000);
					c.getItems().addItem(386, 1500);
					c.getItems().addItem(2498, 10);
					c.getItems().addItem(2435, 100);
					c.getItems().addItem(170, 100);
					c.getItems().addItem(3041, 100);
					// c.getPA().setLevel(3, 80);
					// c.getPA().setLevel(4, 80);
					// c.getPA().setLevel(5, 52);
					// c.getPA().setLevel(1, 80);
					// c.getPA().setLevel(6, 80);
					// c.sendMessage("<shad=15369497>To get RFD gloves, do the Recipe for Disaster minigame south of edgeville general store!!<shad=15369497>");
					c.sendMessage("This server is one of the best.. Real summoning.. Ect.. Enjoy it & Give it a chance!");
					c.dialogueAction = -1;
					// c.getDH().sendDialogues(22, -1);
					c.getPA().requestUpdates();
					//
					c.selectStarterr = false;
					c.chosenStarter = true;
					c.canChangeAppearance = true;
					// };
				}

			} else if (c.dialogueAction == 107) {
				if (c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID,
							c.getItems().getItemSlot(c.diceID), 1);
					c.getItems().addItem(15096, 1);
					c.sendMessage("You get a twenty-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			}
			if (c.teleAction == 1) {
				// slayer tower
				c.getPA().spellTeleport(3428, 3537, 0);
			} else if (c.teleAction == 2) {
				// tzhaar
				c.getPA().spellTeleport(2438, 5168, 0);
				c.sendMessage("To fight Jad, enter the cave.");
			} else if (c.teleAction == 3) {
				// dag kings
				c.getPA().spellTeleport(1910, 4367, 0);
				c.sendMessage("Climb down the ladder to get into the lair.");
			} else if (c.teleAction == 6) {
				// MAD MUMMY
				c.getPA().spellTeleport(2962, 9631, 0);
				c.sendMessage("He mages & melees!");
			} else if (c.teleAction == 4) {
				// Lava Crossing
				// c.getPA().spellTeleport(3367, 3935, 0);
				c.sendMessage("Disabled atm due to people missclicking & going 50 wild..");
			} else if (c.teleAction == 5) {
				c.getPA().spellTeleport(2597, 3408, 0);

			} else if (c.teleAction == 20) {
				c.getPA().spellTeleport(2757, 3477, 0);
			}

			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2713, 4836, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				c.getPA().spellTeleport(2162, 4833, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 12) {
				c.getPA().spellTeleport(2207, 4836, 0);
				c.dialogueAction = -1;
			}
			if (c.teleAction == 8) {
				c.getPA().spellTeleport(2882, 5310, 2);
			}
			break;
		case 152252:
			c.getPA().sendFrame126("www.divinationofgods.cu.cc", 12000);
			break;
		case 15062:
			c.getPA().closeAllWindows();
			break;
		case 9193:
			if (c.teleAction == 4287) {
				if (c != null)
					Construction.fetchItem(c, 1775, 15);
				return;
			}
			if (c.teleAction == 4714) {
				if (c != null)
					Construction.fetchItem(c, 4692, 15);
				return;
			}
			if (c.teleAction == 1645) {
				if (c != null)
					Construction.fetchItem(c, 1539, 3000);
				return;
			}
			if (c.teleAction == 244) {
				if (c != null)
					Construction.fetchItem(c, 4820, 3000);
				return;
			}
			if (c.teleAction == 123) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Celestial Globe")) {
					c.getPA().checkObjectSpawn(13652, 1889, 5100, 1, 10);
					c.constructionObjects[11] = true;
					c.constructionUpgrades[11] = 13652;
				}
				return;
			}
			if (c.teleAction == 146) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Yew tree")) {
					c.getPA().checkObjectSpawn(1309, 1899, 5095, 1, 10);
					c.constructionObjects[7] = true;
					c.constructionUpgrades[7] = 1309;
				}
				return;
			}
			if (c.teleAction == 4286) {
				if (c != null)
					Construction.fetchItem(c, 960, 15);
				return;
			}
			if (c.teleAction == 4296) {
				if (c != null)
					Construction.fetchItem(c, 8778, 15);
				return;
			}
			if (c.teleAction == 4297) {
				if (c != null)
					Construction.fetchItem(c, 8780, 15);
				return;
			}
			if (c.teleAction == 4298) {
				if (c != null)
					Construction.fetchItem(c, 8782, 15);
				return;
			}
			if (c.dialogueAction == 35) {
				c.getItems().handleEffigies(16);
				return;
			} else if (c.dialogueAction == 36) {
				c.getItems().handleEffigies(17);
				return;
			} else if (c.dialogueAction == 37) {
				c.getItems().handleEffigies(11);
				return;
			} else if (c.dialogueAction == 38) {
				c.getItems().handleEffigies(10);
				return;
			} else if (c.dialogueAction == 39) {
				c.getItems().handleEffigies(8);
				return;
			} else if (c.dialogueAction == 40) {
				c.getItems().handleEffigies(5);
				return;
			} else if (c.dialogueAction == 41) {
				c.getItems().handleEffigies(14);
				return;
			}
			if (c.teleAction == 199) {
				c.getPA().spellTeleport(3222, 3219, 0);
				return;
			}
			if (c.teleAction == 53) {
				Prayer.bones20(c, c.bone);
				c.getPA().closeAllWindows();
				c.teleAction = 0;
				return;
			}
			if (c.dialogueAction == 61) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You don't have a hammer with you!");
					c.getPA().closeAllWindows();
					return;
				}
				if (c.getItems().playerHasItem(2359, 1)) {
					c.getSmithingInt().showSmithInterface(2359);
					return;
				} else {
					c.getPA().closeAllWindows();
					c.sendMessage("You don't have any Mithril bars.");
				}
			}
			if (c.dialogueAction == 51) {
				if (c.chosenChaotics == false) {
					c.getItems().addItem(18357, 1);
					c.addChaotics = true;
					c.chooseChaotic = false;
					c.getPA().closeAllWindows();
					c.selectStarter = false;
					c.stopPlayerSkill = false;
					c.chosenChaotics = true;
				}
			}
			if (c.teleAction == 21) { // hunter
				c.getPA().spellTeleport(2416, 3526, 0);
				c.dialogueAction = -1;
			}
			if (c.dialogueAction == 106) {
				if (c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID,
							c.getItems().getItemSlot(c.diceID), 1);
					c.getItems().addItem(15090, 1);
					c.sendMessage("You get an eight-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();

			} else if (c.dialogueAction == 50) {
				if (c.chosenStarter == false) {
					c.trade11 = 1400;
					c.trade11();
					/*
					 * if (Server.singleStarter.contains(c.connectedFrom)) {
					 * c.sendMessage(
					 * "You have already received a starter recently. You may receive another later."
					 * ); // c.getPA().closeAllWindows(); return; }
					 */
					c.getItems().addItem(1704, 1);
					c.getItems().addItem(995, 5000000);
					// Server.singleStarter.add(c.connectedFrom);
					c.getPA().closeAllWindows();
					// c.getItems().addItem(6950, 1);
					c.getItems().addItem(4587, 1);
					// //c.getItems().addItem(18349, 1);
					c.getItems().addItem(1079, 1);
					c.getItems().addItem(1127, 1);
					c.getItems().addItem(1540, 1);
					c.getItems().addItem(10828, 1);
					c.getItems().addItem(11732, 1);
					c.getItems().addItem(6568, 1);
					c.getItems().addItem(2497, 1);
					c.getItems().addItem(2503, 1);
					c.getItems().addItem(11118, 1);
					c.getItems().addItem(10499, 1);
					c.getItems().addItem(2570, 1);
					c.getItems().addItem(5698, 1);
					c.getItems().addItem(386, 1500);
					c.getItems().addItem(892, 1000);
					c.getItems().addItem(9185, 1);
					c.getItems().addItem(861, 1);
					c.getItems().addItem(9244, 300);

					// c.getPA().setLevel(3, 80);
					// c.getPA().setLevel(1, 80);
					// c.getPA().setLevel(0, 80);
					// c.getPA().setLevel(2, 80);
					// c.getPA().setLevel(4, 80);
					// c.getPA().setLevel(5, 80);
					// c.getPA().setLevel(6, 80);
					// c.sendMessage("<shad=15369497>To get RFD gloves, do the Recipe for Disaster minigame south of edgeville general store!!<shad=15369497>");
					c.sendMessage("This server is one of the best.. Real summoning.. Ect.. Enjoy it & Give it a chance!");
					c.dialogueAction = -1;
					c.getPA().requestUpdates();
					// c.getDH().sendDialogues(22, -1);
					//
					c.selectStarterr = false;
					c.chosenStarter = true;
					c.canChangeAppearance = true;
				}
			} else if (c.dialogueAction == 107) {
				if (c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID,
							c.getItems().getItemSlot(c.diceID), 1);
					c.getItems().addItem(15098, 1);
					c.sendMessage("You get the percentile dice out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			}
			if (c.teleAction == 1) {
				// brimhaven dungeon
				// c.getPA().spellTeleport(2710, 9466, 0);
				// c.sendMessage("You  to brimhaven dungeon, be sure to bring antifire-shield.");
				c.sendMessage("Brimhaven is disteleportedabled at the moment.");
			} else if (c.teleAction == 2) {
				// duel arena
				// c.getPA().spellTeleport(3358, 3269, 0);
				c.sendMessage("Duel Arena is disabled at the moment.");
			} else if (c.teleAction == 6) {
				// Barrelschest
				c.getPA().spellTeleport(2374, 4949, 0);
				c.sendMessage("Barrelschest DORP ANCHOR GOOD LUCK.");
				// }
			} else if (c.teleAction == 3) {
				// chaos elemental
				c.getPA().spellTeleport(2717, 9805, 0);
				// c.getPA().spellTeleport(2611,3396,0);
			} else if (c.teleAction == 4) {
				// Fala
				c.getPA().spellTeleport(3211, 3422, 0);

			} else if (c.teleAction == 5) {
				c.getDH().sendOption2("WoodCutting", "FireMaking");
				c.teleAction = 22;
				// c.getPA().spellTeleport(2710,3462,0);
				// c.sendMessage("Good luck WCing!");
			}
			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2660, 4839, 0);
				c.dialogueAction = -1;
			} else if (c.teleAction == 20) {
				c.getPA().spellTeleport(2964, 3378, 0);
			}
			if (c.teleAction == 8) {
				c.getPA().startTeleport(2465, 4770, 0, "ancient");
				c.sendMessage("Beware of the Snakes!.");
			}
			break;
		case 9194:
			if (c.teleAction == 4287) {
				if (c != null)
					Construction.fetchItem(c, 1775, 20);
				return;
			}
			if (c.teleAction == 4714) {
				if (c != null)
					Construction.fetchItem(c, 4692, 20);
				return;
			}

			if (c.teleAction == 1645) {
				if (c != null)
					Construction.fetchItem(c, 1539, 5000);
				return;
			}
			if (c.teleAction == 244) {
				if (c != null)
					Construction.fetchItem(c, 4820, 5000);
				return;
			}
			if (c.teleAction == 123) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Large Orrery")) {
					c.getPA().checkObjectSpawn(13655, 1889, 5100, 1, 10);
					c.constructionObjects[11] = true;
					c.constructionUpgrades[11] = 13655;
				}
				return;
			}
			if (c.teleAction == 146) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Yew tree")) {
					c.getPA().checkObjectSpawn(1306, 1899, 5095, 1, 10);
					c.constructionObjects[7] = true;
					c.constructionUpgrades[7] = 1306;
				}
				return;
			}
			if (c.teleAction == 4298) {
				if (c != null)
					Construction.fetchItem(c, 8782, 20);
				return;
			}
			if (c.teleAction == 4296) {
				if (c != null)
					Construction.fetchItem(c, 8778, 20);
				return;
			}
			if (c.teleAction == 4297) {
				if (c != null)
					Construction.fetchItem(c, 8780, 20);
				return;
			}
			if (c.teleAction == 4286) {
				if (c != null)
					Construction.fetchItem(c, 960, 20);
				return;
			}
			if (c.teleAction == 199) {
				c.getPA().spellTeleport(2440, 3089, 0);
				return;
			}
			if (c.teleAction == 53) {
				Prayer.bonesALL(c, c.bone);
				c.getPA().closeAllWindows();
				c.teleAction = 0;
				return;
			}
			if (c.teleAction == 51) {
				c.getDH().sendOption5("Remove Burners", "Remove Teleporter",
						"Remove Crystal", "Remove Bankchest", "Back");
				c.teleAction = 52;
				return;
			}
			if (c.teleAction == 52) {
				c.getDH().sendOption5("Remove Chair", "Remove Tree",
						"Remove Bed", "Remove Altar", "More..");
				c.teleAction = 51;
				return;
			}
			if (c.dialogueAction == 61) {
				c.getDH().sendOption2("Adamant", "Runite");
				return;
			}
			if (c.dialogueAction == 51) {
				if (c.chosenChaotics == false) {
					c.getItems().addItem(18355, 1);
					c.addChaotics = true;
					c.chooseChaotic = false;
					c.getPA().closeAllWindows();
					c.selectStarter = false;
					c.stopPlayerSkill = false;
					c.chosenChaotics = true;
				}
			}
			if (c.teleAction == 21) {
				c.getDH().sendOption2("Fletching", "Thieving");
			}
			if (c.dialogueAction == 107) {
				c.getDH().sendDialogues(106, 4289);
				return;
			}
			if (c.dialogueAction == 106) {
				c.getDH().sendDialogues(107, 4289);
				return;
			}
			if (c.teleAction == 1) {
				// island
				c.getPA().spellTeleport(3117, 9847, 0);

			} else if (c.dialogueAction == 50) {
				if (c.chosenStarter == false) {
					c.trade11 = 1400;
					c.trade11();
					c.getItems().addItem(1704, 1);
					c.getItems().addItem(995, 5000000);
					// Server.singleStarter.add(c.connectedFrom);
					c.getPA().closeAllWindows();
					c.getItems().addItem(995, 5000000);
					c.getItems().addItem(590, 1);
					c.getItems().addItem(6739, 1);
					c.getItems().addItem(1704, 1);
					c.getItems().addItem(554, 200);
					c.getItems().addItem(555, 200);
					c.getItems().addItem(556, 200);
					c.getItems().addItem(558, 600);
					// c.getItems().addItem(18349, 1);
					c.getItems().addItem(1381, 1);
					c.getItems().addItem(1323, 1);
					c.getItems().addItem(1067, 1);
					c.getItems().addItem(1115, 1);
					c.getItems().addItem(1153, 1);
					c.getItems().addItem(3105, 1);
					c.getItems().addItem(841, 1);
					c.getItems().addItem(882, 500);
					c.getItems().addItem(380, 100);
					c.getItems().addItem(11118, 1);
					c.getItems().addItem(379, 5);
					Connection
							.addIpToStarterList1(PlayerHandler.players[c.playerId].connectedFrom);
					Connection
							.addIpToStarter1(PlayerHandler.players[c.playerId].connectedFrom);
					c.sendMessage("You have recieved 1 out of 2 starter packages on this IP address.");
					c.dialogueAction = -1;
					c.getPA().requestUpdates();
					// c.getDH().sendDialogues(22, -1);
					c.getPA().requestUpdates();
					// c.selectStarter = false; c.stopPlayerSkill = false;
					c.selectStarterr = false;
					c.chosenStarter = true;
					c.getPA().addChaotics();
					c.canChangeAppearance = true;
					// }
				} else {
					c.getPA().addChaotics();
				}
			} else if (c.teleAction == 2) {
				// last minigame spot
				c.getPA().spellTeleport(2865, 3546, 0);
				// c.getPA().closeAllWindows();
			} else if (c.teleAction == 3) {
				c.getPA().spellTeleport(3302, 9372, 0);
				c.sendMessage("Enter the gate to fight the mighty Corporeal Beast!");
				c.sendMessage("Note: Magic protect, Ruby bolts (e) and Diamond bolts (e) are recommended!");
			} else if (c.teleAction == 6) {
				c.sendMessage("If there is no floor/maps are gone RESTART CLIENT!");
				c.getPA().spellTeleport(2882, 5310, 2);
				c.getPA().closeAllWindows();
			} else if (c.teleAction == 4) {
				c.getPA().spellTeleport(2980, 3617, 0);
			} else if (c.teleAction == 5) {
				c.getPA().spellTeleport(2812, 3463, 0);
			}
			if (c.dialogueAction == 10 || c.dialogueAction == 11) {
				c.dialogueId++;
				c.getDH().sendDialogues(c.dialogueId, 0);
			} else if (c.dialogueAction == 12) {
				c.dialogueId = 17;
				c.getDH().sendDialogues(c.dialogueId, 0);

			} else if (c.teleAction == 20) {
				c.getPA().spellTeleport(3493, 3484, 0);

			} else if (c.teleAction == 8) {
				c.getPA().startTeleport(2916, 3628, 12, "ancient");
				c.sendMessage("The Brutal Avatar of Destruction, Good Luck!");
				c.sendMessage("He has 2x hp bars, more then 2 ppl recommended!");
			}
			break;
		case 34185:
		case 34184:
		case 34183:
		case 34182:
		case 34189:
		case 34188:
		case 34187:
		case 34186:
		case 34193:
		case 34192:
		case 34191:
		case 34190:
			if (c.craftingLeather)
				c.getCrafting().handleCraftingClick(actionButtonId);
			if (c.getFletching().fletching)
				c.getFletching().handleFletchingClick(actionButtonId);
			break;

		case 15147:
			if (c.smeltInterface) {
				c.getSmithing();
				Smithing.smeltBronze(c);
			}
			break;
		case 15146:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 5;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 10247:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 10;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 9110:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 28;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;

		case 15151:
			if (c.smeltInterface) {
				c.getSmithing();
				Smithing.smeltIron(c);
			}
			break;
		case 15150:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 5;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 15149:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 10;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 15148:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 28;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;

		case 15159:
			if (c.smeltInterface) {
				c.getSmithing();
				Smithing.smeltSteel(c);
			}
			break;
		case 15155:
			if (c.smeltInterface) {
				c.getSmithing();
				Smithing.smeltSilver(c);
			}
			break;
		case 15163:
			if (c.smeltInterface) {
				c.getSmithing();
				Smithing.smeltGold(c);
			}
			break;
		case 15158:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 5;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 15157:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 10;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 15156:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 28;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;

		case 29017:
			if (c.smeltInterface) {
				c.getSmithing();
				Smithing.smeltMithril(c);
			}
			break;
		case 29016:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 5;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 24253:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 10;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 16062:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 28;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;

		case 29022:
			if (c.smeltInterface) {
				c.getSmithing();
				Smithing.smeltAdamant(c);
			}
			break;
		case 29020:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 5;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 29019:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 10;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 29018:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 28;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 29026:
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 1;
				c.getSmithing();
				Smithing.smeltRune(c);
			}

			break;
		case 29025:// smelt 5
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 5;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;
		case 29024:// smelt 10
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 10;
				// c.getSmithing().startSmelting(c.smeltType);
			}
			break;

		case 59004:
			c.getPA().removeAllWindows();
			break;

		case 62137:
			break;
		case 9178:// 4 line chat interface OPTION 1
			if (c.dialogueAction == 11138) {
				if (c.getPlayerGod() < 0) {
					c.setPlayerGod(0);// ZAMORAK
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You already worship " + c.getGod() + ".");
					c.getPA().removeAllWindows();
				}
			}
			if (c.teleAction == 4282) {
				c.getDH().sendOption2("Iron nails", "Steel nails");
				c.teleAction = 1442;
				return;
			}
			if (c.teleAction == 1335) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Oak lectern")) {
					c.getPA().checkObjectSpawn(13642, 1890, 5098, 2, 10);
					c.constructionObjects[9] = true;
					c.constructionUpgrades[9] = 13642;
				}
				return;
			}
			if (c.teleAction == 4285) {
				c.getDH().sendOption5("1", "5", "10", "15", "20");
				c.teleAction = 4286;
				return;
			}
			if (c.teleAction == 5) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Wooden bookcase")) {
					c.getPA().checkObjectSpawn(13597, 1903, 5088, 2, 10);
					c.constructionObjects[5] = true;
					c.constructionUpgrades[1] = 13597;
				}
				return;
			}
			if (c.teleAction == 131) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Wooden bookcase")) {
					c.getPA().checkObjectSpawn(13597, 1892, 5103, 1, 10);
					c.constructionObjects[13] = true;
					c.constructionUpgrades[13] = 13597;
				}
				return;
			}
			if (c.teleAction == 132) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Wooden bookcase")) {
					c.getPA().checkObjectSpawn(13597, 1891, 5103, 1, 10);
					c.constructionObjects[14] = true;
					c.constructionUpgrades[14] = 13597;
				}
				return;
			}
			if (c.teleAction == 11) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Clay fireplace")) {
					c.getPA().checkObjectSpawn(13609, 1899, 5095, 1, 10);
					c.constructionObjects[6] = true;
					c.constructionUpgrades[6] = 13609;
				}
				return;
			}
			if (c.teleAction == 4) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Wooden bookcase")) {
					c.getPA().checkObjectSpawn(13597, 1896, 5088, 0, 10);
					c.constructionObjects[4] = true;
					c.constructionUpgrades[0] = 13597;
				}
				return;
			}
			if (c.teleAction == 6) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Wooden chair")) {
					c.getPA().checkObjectSpawn(13581, 1898, 5092, 2, 10);
					c.constructionObjects[0] = true;
					c.constructionUpgrades[2] = 13581;
				}
				return;
			}
			if (c.teleAction == 7) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Wooden chair")) {
					c.getPA().checkObjectSpawn(13581, 1901, 5092, 2, 10);
					c.constructionObjects[1] = true;
					c.constructionUpgrades[3] = 13581;
				}
				return;
			}
			if (c.teleAction == 8) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Wooden chair")) {
					c.getPA().checkObjectSpawn(13581, 1861, 5116, 2, 10);
					c.constructionObjects[2] = true;
					c.constructionUpgrades[4] = 13581;
				}
				return;
			}
			if (c.teleAction == 10) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Brown rug")) {
					Rugs.rugSpawning(c, false, 13590);
					c.constructionObjects[3] = true;
					c.constructionUpgrades[5] = 13590;
				}
				return;
			}
			if (c.teleAction == 535) {
				c.getPA().closeAllWindows();
				GabbesAchievements.checkReqs(c);
			}

			if (c.dialogueAction == 24524) { // Tickets exchange
				c.getPA().closeAllWindows();
				if (c.getItems().playerHasItem(2996, 1)) {
					c.getItems().deleteItem(2996, 1);
					c.getPA().addSkillXP2(20000, 16);
					c.getPA().refreshSkill(16);
					c.sendMessage("You receive 20000 Agility XP.");
					return;
				} else {
					c.sendMessage("You don't have enough Agility tickets.");
					c.sendMessage("You can receive tickets by completing the course!");
					return;
				}
			}

			if (c.dialogueAction == 224) {
				if (c.LoyaltyPoints == 500 || c.LoyaltyPoints > 500) {
					c.LoyaltyPoints -= 500;
					c.spinsLe += 1;
					c.sendMessage("You've succesfully purchased 1 spin.");
					c.getPA().sendFrame126("" + c.spinsLe + "", 16510);
					c.getPA().closeAllWindows();
					return;
				} else {
					c.sendMessage("You don't have enough loyalty points to buy this.");
					c.getPA().closeAllWindows();
					return;
				}
			}
			if (c.dialogueAction == 223) {
				c.getPA().closeAllWindows();
				c.getPA().showInterface(16450);
				c.getPA().sendFrame126("" + c.LoyaltyPoints + "", 16482);
				c.getPA().sendFrame126("" + c.LoyaltyPoints + "", 40482);
				c.sendMessage("You currently have " + c.LoyaltyPoints
						+ " Loyalty points.");
				c.sendMessage("To receive Loyalty points, simply stay online!");
				// c.sendMessage("<shad=15695415>Sharpshooter: Increases your ranged Accuracy by 7%. Does not work in PvP!");
				return;
			}

			int npcType = 6138;
			if (c.teleAction == 43) {
				c.getPA().startTeleport(3085, 3491, 0, "normal");
			}
			if (c.dialogueAction == 42) {

				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.getPA().closeAllWindows();
						c.getDH().sendDialogues(420, npcType);
						return;
					}
				}
				try {
					int skilld = 1;
					int leveld = 1;
					c.playerXP[skilld] = c.getPA().getXPForLevel(leveld) + 5;
					c.playerLevel[skilld] = c.getPA().getLevelForXP(
							c.playerXP[skilld]);
					c.getPA().refreshSkill(skilld);
					// c.getPA().closeAllWindows();
					c.getDH().sendDialogues(230, npcType);
				} catch (Exception e) {
				}
			}
			if (c.usingGlory)
				c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y,
						0, "modern");
			c.gdegradeNow = true;
			c.getPA().gloryDegrade();
			if (c.dialogueAction == 2)
				c.getPA().startTeleport(3428, 3538, 0, "modern");
			if (c.dialogueAction == 3)
				c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y,
						0, "modern");
			if (c.dialogueAction == 4)
				c.getPA().startTeleport(3565, 3314, 0, "modern");
			if (c.dialogueAction == 20) {
				c.getPA().startTeleport(2897, 3618, 4, "modern");
			}
			if (c.dialogueAction == 100) {
				c.getDH().sendDialogues(25, 946);
			}

			break;

		case 9179:// 4 line chat interface OPTION 2
			if (c.dialogueAction == 11138) {
				if (c.getPlayerGod() < 0) {
					c.setPlayerGod(1);// Arma
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You already worship " + c.getGod() + ".");
					c.getPA().removeAllWindows();
				}
			}
			if (c.teleAction == 4282) {
				c.getDH().sendDialogues(4245, 1);
				return;
			}
			if (c.teleAction == 1335) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Demon lectern")) {
					c.getPA().checkObjectSpawn(13644, 1890, 5098, 2, 10);
					c.constructionObjects[9] = true;
					c.constructionUpgrades[9] = 13644;
				}
				return;
			}
			if (c.teleAction == 11) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Stone fireplace")) {
					c.getPA().checkObjectSpawn(13611, 1899, 5095, 1, 10);
					c.constructionObjects[6] = true;
					c.constructionUpgrades[6] = 13611;
				}
				return;
			}
			if (c.teleAction == 6) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Oak chair")) {
					c.getPA().checkObjectSpawn(13582, 1898, 5092, 2, 10);
					c.constructionObjects[0] = true;
					c.constructionUpgrades[2] = 13582;
				}
				return;
			}
			if (c.teleAction == 7) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Oak chair")) {
					c.getPA().checkObjectSpawn(13582, 1901, 5092, 2, 10);
					c.constructionObjects[1] = true;
					c.constructionUpgrades[3] = 13582;
				}
				return;
			}
			if (c.teleAction == 4296) {
				if (c != null)
					Construction.fetchItem(c, 8778, 5);
				return;
			}
			if (c.teleAction == 4297) {
				if (c != null)
					Construction.fetchItem(c, 8780, 5);
				return;
			}
			if (c.teleAction == 4285) {
				c.getDH().sendOption5("1", "5", "10", "15", "20");
				c.teleAction = 4296;
				return;
			}
			if (c.teleAction == 4286) {
				if (c != null)
					Construction.fetchItem(c, 960, 5);
				return;
			}
			if (c.teleAction == 4298) {
				if (c != null)
					Construction.fetchItem(c, 8782, 5);
				return;
			}

			if (c.teleAction == 10) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Red rug")) {
					Rugs.rugSpawning(c, false, 13595);
					c.constructionObjects[3] = true;
					c.constructionUpgrades[5] = 13595;
				}
				return;
			}
			if (c.teleAction == 5) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Oak bookcase")) {
					c.getPA().checkObjectSpawn(13598, 1903, 5088, 2, 10);
					c.constructionObjects[5] = true;
					c.constructionUpgrades[1] = 13598;
				}
				return;
			}
			if (c.teleAction == 131) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Oak bookcase")) {
					c.getPA().checkObjectSpawn(13598, 1892, 5103, 1, 10);
					c.constructionObjects[13] = true;
					c.constructionUpgrades[13] = 13598;
				}
				return;
			}
			if (c.teleAction == 132) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Oak bookcase")) {
					c.getPA().checkObjectSpawn(13598, 1891, 5103, 1, 10);
					c.constructionObjects[14] = true;
					c.constructionUpgrades[14] = 13598;
				}
				return;
			}
			if (c.teleAction == 4) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Oak bookcase")) {
					c.getPA().checkObjectSpawn(13598, 1896, 5088, 0, 10);
					c.constructionObjects[4] = true;
					c.constructionUpgrades[0] = 13598;
				}
				return;
			}
			if (c.dialogueAction == 1317) {
				c.getPA().closeAllWindows();
				c.sendMessage("<shad=6081134>You currently have "
						+ c.dungPoints + " Dungeoneering Tokens.");
				c.getShops().openShop(85);

				return;
			}
			if (c.teleAction == 535) {
				c.getPA().closeAllWindows();
				GabbesAchievements.vetCape(c);
				return;
			}
			if (c.dialogueAction == 24524) { // Tickets exchange
				c.getPA().closeAllWindows();
				if (c.getItems().playerHasItem(2996, 5)) {
					c.getItems().deleteItem(2996, 5);
					c.getPA().addSkillXP2(110000, 16);
					c.getPA().refreshSkill(16);
					c.sendMessage("You receive 110000 Agility XP.");
					return;
				} else {
					c.sendMessage("You don't have enough Agility tickets.");
					c.sendMessage("You can receive tickets by completing the course!");
					return;
				}
			}
			if (c.dialogueAction == 224) {
				if (c.LoyaltyPoints == 2000 || c.LoyaltyPoints > 2000) {
					c.LoyaltyPoints -= 2000;
					c.spinsLe += 5;
					c.sendMessage("You've succesfully purchased 5 spins.");
					c.getPA().sendFrame126("" + c.spinsLe + "", 16510);
					c.getPA().closeAllWindows();
					return;
				} else {
					c.sendMessage("You don't have enough loyalty points to buy this.");
					c.getPA().closeAllWindows();
					return;
				}
			}
			if (c.dialogueAction == 223) {
				c.getShops().openShop(55);
				c.sendMessage("You currently have " + c.DonorPoint
						+ " Donator Points!");
				return;
			}
			if (c.dialogueAction == 90) {
				c.getPA().closeAllWindows();
				if (c.playerLevel[24] >= 49) {
					c.getPA().closeAllWindows();
					c.getPA().movePlayer(3467, 9495, 0);
					c.sendMessage("<shad=16112652>Kill NPC's here for fast Dung XP/Tokens!");
					// c.sendMessage("<shad=16112652>You are taken to a underground cave!");
					return;
				} else {
					c.sendMessage("You need atleast a level of 49 Dungeoneering to enter this cave!");
					c.getPA().closeAllWindows();
					return;
				}
			}
			npcType = 6138;
			if (c.teleAction == 43) {
				c.getPA().startTeleport(3210, 3424, 0, "normal");
			}
			if (c.dialogueAction == 42) { // prayer
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.getPA().closeAllWindows();
						c.getDH().sendDialogues(420, npcType);
						return;
					}
				}
				try {
					int skillp = 5;
					int levelp = 1;
					c.playerXP[skillp] = c.getPA().getXPForLevel(levelp) + 5;
					c.playerLevel[skillp] = c.getPA().getLevelForXP(
							c.playerXP[skillp]);
					c.getPA().refreshSkill(skillp);
					// c.getPA().closeAllWindows();
					c.getDH().sendDialogues(260, npcType);
				} catch (Exception e) {
				}
			}
			if (c.usingGlory)
				c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y,
						0, "modern");
			c.gdegradeNow = true;
			c.getPA().gloryDegrade();
			if (c.dialogueAction == 2)
				c.getPA().startTeleport(2884, 3395, 0, "modern");
			if (c.dialogueAction == 3)
				c.getPA().startTeleport(3243, 3513, 0, "modern");
			if (c.dialogueAction == 4)
				c.getPA().startTeleport(2444, 5170, 0, "modern");
			if (c.dialogueAction == 20) {
				c.getPA().startTeleport(2897, 3618, 12, "modern");
			}
			if (c.dialogueAction == 101) {
				c.getDH().sendDialogues(21, 946);
			}
			if (c.dialogueAction == 100) {
				c.getGamble();
				Gambling.gambleBlackJack(c);
			}
			break;

		case 9180:// 4 line chat interface OPTION 3
			if (c.dialogueAction == 11138) {
				if (c.getPlayerGod() < 0) {
					c.setPlayerGod(2);// Bandos
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You already worship " + c.getGod() + ".");
					c.getPA().removeAllWindows();
				}
			}
			if (c.teleAction == 4282) {
				c.getDH().sendOption5("1", "5", "10", "15", "20");
				c.teleAction = 4714;
				return;
			}
			if (c.teleAction == 1335) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Eagle lectern")) {
					c.getPA().checkObjectSpawn(13643, 1890, 5098, 2, 10);
					c.constructionObjects[9] = true;
					c.constructionUpgrades[9] = 13643;
				}
				return;
			}
			if (c.teleAction == 11) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Marble fireplace")) {
					c.getPA().checkObjectSpawn(13613, 1899, 5095, 1, 10);
					c.constructionObjects[6] = true;
					c.constructionUpgrades[6] = 13613;
				}
				return;
			}
			if (c.teleAction == 6) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Teak chair")) {
					c.getPA().checkObjectSpawn(13584, 1898, 5092, 2, 10);
					c.constructionObjects[0] = true;
					c.constructionUpgrades[2] = 13584;
				}
				return;
			}
			if (c.teleAction == 7) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Teak chair")) {
					c.getPA().checkObjectSpawn(13584, 1901, 5092, 2, 10);
					c.constructionObjects[1] = true;
					c.constructionUpgrades[3] = 13584;
				}
				return;
			}
			if (c.teleAction == 4285) { // teak
				c.getDH().sendOption5("1", "5", "10", "15", "20");
				c.teleAction = 4297;
				return;
			}
			if (c.teleAction == 4296) {
				if (c != null)
					Construction.fetchItem(c, 8778, 10);
				return;
			}
			if (c.teleAction == 4297) {
				if (c != null)
					Construction.fetchItem(c, 8780, 10);
				return;
			}
			if (c.teleAction == 4286) {
				if (c != null)
					Construction.fetchItem(c, 960, 10);
				return;
			}
			if (c.teleAction == 4298) {
				if (c != null)
					Construction.fetchItem(c, 8782, 10);
				return;
			}
			if (c.teleAction == 5) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Teak bookcase")) {
					c.getPA().checkObjectSpawn(13598, 1903, 5088, 2, 10);
					c.constructionObjects[5] = true;
					c.constructionUpgrades[1] = 13598;
				}
				return;
			}
			if (c.teleAction == 131) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Teak bookcase")) {
					c.getPA().checkObjectSpawn(13598, 1892, 5103, 1, 10);
					c.constructionObjects[13] = true;
					c.constructionUpgrades[13] = 13598;
				}
				return;
			}
			if (c.teleAction == 132) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Teak bookcase")) {
					c.getPA().checkObjectSpawn(13598, 1891, 5103, 1, 10);
					c.constructionObjects[14] = true;
					c.constructionUpgrades[14] = 13598;
				}
				return;
			}
			if (c.teleAction == 4) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Teak bookcase")) {
					c.getPA().checkObjectSpawn(13598, 1896, 5088, 0, 10);
					c.constructionObjects[4] = true;
					c.constructionUpgrades[0] = 13598;
				}
				return;
			}
			if (c.teleAction == 535) {
				c.getPA().closeAllWindows();
				GabbesAchievements.handleMaxCape(c);
				return;
			}
			if (c.dialogueAction == 24524) { // Tickets exchange
				c.getPA().closeAllWindows();
				if (c.getItems().playerHasItem(2996, 10)) {
					c.getItems().deleteItem(2996, 10);
					c.getPA().addSkillXP2(220000, 16);
					c.getPA().refreshSkill(16);
					c.sendMessage("You receive 220000 Agility XP.");
					return;
				} else {
					c.sendMessage("You don't have enough Agility tickets.");
					c.sendMessage("You can receive tickets by completing the course!");
					return;
				}
			}
			if (c.dialogueAction == 224) {
				if (c.LoyaltyPoints == 4000 || c.LoyaltyPoints > 4000) {
					c.LoyaltyPoints -= 4000;
					c.spinsLe += 10;
					c.sendMessage("You've succesfully purchased 10 spins.");
					c.getPA().closeAllWindows();
					c.getPA().sendFrame126("" + c.spinsLe + "", 16510);
					return;
				} else {
					c.sendMessage("You don't have enough loyalty points to buy this.");
					c.getPA().closeAllWindows();
					return;
				}
			}
			if (c.dialogueAction == 223) {
				c.getPA().closeAllWindows();
				// c.getDH().sendOption2("Reset Title", "Never mind");
				// c.dialogueAction = 0;
				// c.teleAction = 1;
				c.getShops().openShop(14);
				return;
			}
			if (c.dialogueAction == 90) {
				c.getPA().removeAllWindows();
				c.sendMessage("<shad=6081134>Your current Dungeoneering level: "
						+ c.getPA().getLevelForXP(c.playerXP[24]) + "");
				c.sendMessage("<shad=6081134>Total amount of tokens: "
						+ c.dungPoints + "");
			}
			if (c.teleAction == 43) {
				c.getPA().startTeleport(2060, 3146, 0, "normal");
			}
			npcType = 6138;
			if (c.dialogueAction == 42) { // attack
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.getPA().closeAllWindows();
						c.getDH().sendDialogues(420, npcType);
						return;
					}
				}
				try {
					int skill = 0;
					int levela = 1;
					c.playerXP[skill] = c.getPA().getXPForLevel(levela) + 5;
					c.playerLevel[skill] = c.getPA().getLevelForXP(
							c.playerXP[skill]);
					c.getPA().refreshSkill(skill);
					// c.getPA().closeAllWindows();
					c.getDH().sendDialogues(240, npcType);
				} catch (Exception e) {
				}
			}
			if (c.usingGlory)
				c.getPA().startTeleport(Config.DRAYNOR_X, Config.DRAYNOR_Y, 0,
						"modern");
			c.gdegradeNow = true;
			c.getPA().gloryDegrade();
			if (c.dialogueAction == 2)
				c.getPA().startTeleport(2471, 10137, 0, "modern");
			if (c.dialogueAction == 3)
				c.getPA().startTeleport(3363, 3676, 0, "modern");
			if (c.dialogueAction == 4)
				c.getPA().startTeleport(2659, 2676, 0, "modern");
			if (c.dialogueAction == 20) {
				c.getPA().startTeleport(2897, 3618, 8, "modern");
			}
			if (c.dialogueAction == 101) {
				c.getDH().sendDialogues(23, 946);
			}
			if (c.dialogueAction == 100) {
				if (!c.getItems().playerHasItem(995, 1000000)) {
					c.sendMessage("You need at least 1M coins to play this game!");
					c.getPA().removeAllWindows();
					break;
				}
				c.getGamble();
				Gambling.playGame(c);
			}
			break;

		case 9181:// 4 line chat interface OPTION 4
			if (c.dialogueAction == 11138) {// NEXT CHAT
				c.getDH().sendDialogues(11139, 949);
				return;
			}
			if (c.teleAction == 4282) {
				c.getDH().sendOption5("1", "5", "10", "15", "20");
				c.teleAction = 4287;
				return;
			}
			if (c.teleAction == 5) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Mahogany bookcase")) {
					c.getPA().checkObjectSpawn(13599, 1903, 5088, 2, 10);
					c.constructionObjects[5] = true;
					c.constructionUpgrades[1] = 13599;
				}
				return;
			}
			if (c.teleAction == 4) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Mahogany bookcase")) {
					c.getPA().checkObjectSpawn(13599, 1896, 5088, 0, 10);
					c.constructionObjects[4] = true;
					c.constructionUpgrades[0] = 13599;
				}
				return;
			}
			if (c.teleAction == 131) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Mahogany bookcase")) {
					c.getPA().checkObjectSpawn(13599, 1892, 5103, 1, 10);
					c.constructionObjects[13] = true;
					c.constructionUpgrades[13] = 13599;
				}
				return;
			}
			if (c.teleAction == 132) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Mahogany bookcase")) {
					c.getPA().checkObjectSpawn(13599, 1891, 5103, 1, 10);
					c.constructionObjects[14] = true;
					c.constructionUpgrades[14] = 13599;
				}
				return;
			}
			if (c.teleAction == 6) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Mahogany chair")) {
					c.getPA().checkObjectSpawn(13587, 1898, 5092, 2, 10);
					c.constructionObjects[0] = true;
					c.constructionUpgrades[2] = 13587;
				}
				return;
			}
			if (c.teleAction == 7) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Mahogany chair")) {
					c.getPA().checkObjectSpawn(13587, 1901, 5092, 2, 10);
					c.constructionObjects[1] = true;
					c.constructionUpgrades[3] = 13587;
				}
				return;
			}
			if (c.teleAction == 4285) { // mahogany
				c.getDH().sendOption5("1", "5", "10", "15", "20");
				c.teleAction = 4298;
				return;
			}
			if (c.teleAction == 4298) {
				if (c != null)
					Construction.fetchItem(c, 8782, 15);
				return;
			}
			if (c.teleAction == 4296) {
				if (c != null)
					Construction.fetchItem(c, 8778, 15);
				return;
			}
			if (c.teleAction == 4297) {
				if (c != null)
					Construction.fetchItem(c, 8780, 15);
				return;
			}
			if (c.teleAction == 4286) {
				if (c != null)
					Construction.fetchItem(c, 960, 15);
				return;
			}
			if (c.teleAction == 535) {
				c.getPA().closeAllWindows();
				GabbesAchievements.handleMilestoneCapes(c);
				return;
			}
			if (c.dialogueAction == 24524) { // Tickets exchange
				c.getPA().closeAllWindows();
				if (c.getItems().playerHasItem(2996, 15)) {
					c.getItems().deleteItem(2996, 15);
					c.getPA().addSkillXP2(330000, 16);
					c.getPA().refreshSkill(16);
					c.sendMessage("You receive 330000 Agility XP.");
					return;
				} else {
					c.sendMessage("You don't have enough Agility tickets.");
					c.sendMessage("You can receive tickets by completing the course!");
					return;
				}
			}
			if (c.dialogueAction == 224) {
				if (c.LoyaltyPoints == 6000 || c.LoyaltyPoints > 6000) {
					c.LoyaltyPoints -= 6000;
					c.spinsLe += 20;
					c.sendMessage("You've succesfully purchased 20 spins.");
					c.getPA().closeAllWindows();
					c.getPA().sendFrame126("" + c.spinsLe + "", 16510);
					return;
				} else {
					c.sendMessage("You don't have enough loyalty points to buy this.");
					c.getPA().closeAllWindows();
					return;
				}
			}
			if (c.dialogueAction == 223) {
				// c.getPA().closeAllWindows();
				// c.dialogueAction = 224;
				// c.getDH().sendOption4("1 Spin, 500 Loyalty Points",
				// "5 Spins, 2000 Loyalty Points",
				// "10 Spins, 4000 Loyalty Points",
				// "20 Spins, 6000 Loyalty Points");
				return;
			}
			if (c.dialogueAction == 90) {
				c.sendMessage("<shad=6081134>You currently have "
						+ c.dungPoints + " Dungeoneering Tokens.");
				c.getShops().openShop(85);
				return;
			}
			if (c.teleAction == 43) {
				c.getPA().startTeleport(2757, 3477, 0, "normal");
			}
			npcType = 6138;
			if (c.dialogueAction == 42) { // allstats
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.getPA().closeAllWindows();
						c.getDH().sendDialogues(420, npcType);
						return;
					}
				}
				try {
					int skill1 = 0;
					int level = 1;
					c.playerXP[skill1] = c.getPA().getXPForLevel(level) + 5;
					c.playerLevel[skill1] = c.getPA().getLevelForXP(
							c.playerXP[skill1]);
					c.getPA().refreshSkill(skill1);
					int skill2 = 1;
					// int level = 1;
					c.playerXP[skill2] = c.getPA().getXPForLevel(level) + 5;
					c.playerLevel[skill2] = c.getPA().getLevelForXP(
							c.playerXP[skill2]);
					c.getPA().refreshSkill(skill2);
					int skill3 = 2;
					// int level = 1;
					c.playerXP[skill3] = c.getPA().getXPForLevel(level) + 5;
					c.playerLevel[skill3] = c.getPA().getLevelForXP(
							c.playerXP[skill3]);
					c.getPA().refreshSkill(skill3);
					int skill4 = 3;
					level = 10;
					c.playerXP[skill4] = c.getPA().getXPForLevel(level) + 5;
					c.playerLevel[skill4] = c.getPA().getLevelForXP(
							c.playerXP[skill4]);
					c.getPA().refreshSkill(skill4);
					int skill5 = 4;
					level = 1;
					c.playerXP[skill5] = c.getPA().getXPForLevel(level) + 5;
					c.playerLevel[skill5] = c.getPA().getLevelForXP(
							c.playerXP[skill5]);
					c.getPA().refreshSkill(skill5);
					int skill6 = 5;
					// int level = 1;
					c.playerXP[skill6] = c.getPA().getXPForLevel(level) + 5;
					c.playerLevel[skill6] = c.getPA().getLevelForXP(
							c.playerXP[skill6]);
					c.getPA().refreshSkill(skill6);
					int skill7 = 6;
					// int level = 1;
					c.playerXP[skill7] = c.getPA().getXPForLevel(level) + 5;
					c.playerLevel[skill7] = c.getPA().getLevelForXP(
							c.playerXP[skill7]);
					c.getPA().refreshSkill(skill7);
					// c.getPA().closeAllWindows();
					c.getDH().sendDialogues(250, npcType);
				} catch (Exception e) {
				}
			}
			if (c.usingGlory)
				c.getPA().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y,
						0, "modern");
			c.gdegradeNow = true;
			c.getPA().gloryDegrade();
			if (c.dialogueAction == 2)
				c.getPA().startTeleport(2669, 3714, 0, "modern");
			if (c.dialogueAction == 3)
				c.getPA().startTeleport(2540, 4716, 0, "modern");
			if (c.dialogueAction == 4) {
				// c.getPA().startTeleport(3358, 3269, 0, "modern");
				c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
				c.sendMessage("If you get a black screen or freeze just type ::train.");
			}
			if (c.dialogueAction == 20) {
				// c.getPA().startTeleport(3366, 3266, 0, "modern");
				// c.killCount = 0;
				c.sendMessage("This will be added shortly");
			} else if (c.dialogueAction == 10 || c.dialogueAction == 101) {
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
			} else {
				c.getPA().removeAllWindows();
			}
			c.dialogueAction = 0;
			break;

		case 1093:
		case 1094:
		case 1097:
		case 15486:
			if (c.autocastId > 0) {
				c.getPA().resetAutocast();
			} else {
				if (c.playerMagicBook == 1) {
					if (c.playerEquipment[Player.playerWeapon] == 4675
							|| c.playerEquipment[Player.playerWeapon] == 20402
							|| c.playerEquipment[Player.playerWeapon] == 20022
							|| c.playerEquipment[Player.playerWeapon] == 20021
							|| c.playerEquipment[Player.playerWeapon] == 20020
							|| c.playerEquipment[Player.playerWeapon] == 20019
							|| c.playerEquipment[Player.playerWeapon] == 20012
							|| c.playerEquipment[Player.playerWeapon] == 15486
							|| c.playerEquipment[Player.playerWeapon] == 18355)
						c.setSidebarInterface(0, 1689);
					else
						c.sendMessage("You can't autocast ancients without an ancient, chaotic staff or a SOL.");
				} else if (c.playerMagicBook == 0) {
					if (c.playerEquipment[Player.playerWeapon] == 4170
							|| c.playerEquipment[Player.playerWeapon] == 15486
							|| c.playerEquipment[Player.playerWeapon] == 15040) {
						c.setSidebarInterface(0, 12050);
					} else {
						c.setSidebarInterface(0, 1829);
					}
				}

			}
			break;
		case 115121:
			c.getPA().closeAllWindows();
			break;
		case 9167:// 3 line chat interface option1
			switch (c.dialogueAction) {
			case 11139:// God chat
				if (c.getPlayerGod() < 0) {
					c.setPlayerGod(3);// Nex
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You already worship " + c.getGod() + ".");
					c.getPA().removeAllWindows();
				}
				break;
			}
			break;
		case 9168:// 3 line chat interface option 2
			switch (c.dialogueAction) {
			case 11139:// God chat
				if (c.getPlayerGod() < 0) {
					c.setPlayerGod(4);// Sara
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You already worship " + c.getGod() + ".");
					c.getPA().removeAllWindows();
				}
				break;
			}
			break;
		case 9169:// 3 line chat interface option 3
			switch (c.dialogueAction) {
			case 11139:// God chat
				if (c.getPlayerGod() < 0) {
					c.setPlayerGod(5);// Guthix
					c.getPA().removeAllWindows();
				} else {
					c.sendMessage("You already worship " + c.getGod() + ".");
					c.getPA().removeAllWindows();
				}
				break;
			}
			break;
		case 9157:// barrows tele to tunnels
			if (c.dialogueAction == 4381) {
				c.getShops().openShop(55);
				return;
			} else if (c.teleAction == 428) {
				c.getPA().closeAllWindows();
				if (Artifacts.hasArtifact(c)) {
					Artifacts.reward(c);
				} else {
					c.sendMessage("No artifacts were found in your inventory.");
				}
				return;
			}

			if (c.dialogueAction == 893) {
				c.getShops().openShop(37);
				return;
			}

			if (c.dialogueAction == 894) {
				c.getShops().openShop(37);
				return;
			}
			if (c.dialogueAction == 896) {
				c.getShops().openShop(2);
				return;
			}
			if (c.dialogueAction == 895) {
				c.getShops().openShop(2);
				return;
			}
			if (c.dialogueAction == 898) {
				c.getShops().openShop(1);
				return;
			}
			if (c.dialogueAction == 897) {
				c.getShops().openShop(1);
				return;
			}

			if (c.teleAction == 4325) {
				c.getPA().closeAllWindows();
				if (c.payment > 0) {
					if (c.getItems().playerHasItem(995, c.payment)) {
						c.getItems().deleteItem(995, c.payment);
						c.getDH().sendDialogues(4251, 1);
						c.payment = -1;
						c.mustPay = false;
						c.payButlerReq = 0;
					} else {
						c.sendMessage("You do not have enough coins. You need to pay the Butler "
								+ c.payment + " coins.");
						return;

					}
				}
				return;
			}
			if (c.teleAction == 3552) {
				c.getPA().closeAllWindows();
				PvPHandler.handleTeleTab(c, true);
				return;
			}
			if (c.teleAction == 4284) {
				c.getDH().sendOption4("Planks", "Oak planks", "Teak planks",
						"Mahogany planks");
				c.teleAction = 4285;
				return;
			}
			if (c.teleAction == 1442) {
				c.getDH().sendOption5("100", "500", "1000", "3000", "5000");
				c.teleAction = 244;
				return;
			}
			if (c.teleAction == 835) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Crystal ball")) {
					c.getPA().checkObjectSpawn(13659, 1893, 5100, 0, 10);
					c.constructionObjects[10] = true;
					c.constructionUpgrades[10] = 13659;
				}
				return;
			}
			if (c.dialogueAction == 551) {
				c.getPA().leaveDung(c);
				return;
			}

			if (c.dialogueAction == 893) {
				c.getShops().openShop(37);
				return;
			}

			if (c.dialogueAction == 894) {
				c.getShops().openShop(37);
				return;
			}

			if (c.teleAction == 444) {
				c.getPA().closeAllWindows();
				c.getDH().sendDialogues(444, 1);

				return;
			}

			if (c.dialogueAction == 579) {
				c.getPA().closeAllWindows();
				c.getDungeoneering();
				Dungeoneering.handleStart(c, false);

				return;
			}
			if (c.dialogueAction == 214) {
				c.getPA().setDungBindStats();
				return;
			}
			if (c.dialogueAction == 853) {
				c.getPA().movePlayer(1907, 5219, c.playerId * 4);
				// Server.npcHandler.dungNPCS();
				c.getPA().closeAllWindows();
				c.getDungeoneering();
				Dungeoneering.spawnBossesV1(c);
				return;
			}
			if (c.teleAction == 54) {
				c.getPA().closeAllWindows();
				Server.barbDefence.enter(c);
				c.sendMessage("Every once in a while you'll receive messages of your score.");
				return;
			}
			if (c.dialogueAction == 535) {
				c.getPA().closeAllWindows();
				c.getPA().addStarterItems();
				return;
			}
			if (c.dialogueAction == 948) {
				c.getPA().closeAllWindows();
				c.getPA().startTeleport(3339, 3663, 0, "modern");
				c.sendMessage("It's recommended to wear an Anti-dragon shield when figthing a Dragon!");
				return;
			}
			if (c.dialogueAction == 935) {
				c.getPA().closeAllWindows();
				c.getPA().startTeleport(2870, 5354, 6, "modern");
				return;
			}
			if (c.dialogueAction == 949) {
				c.getPA().closeAllWindows();
				c.getPA().startTeleport(3202, 3860, 0, "modern");
				c.sendMessage("Enter the gate to the south!");
				c.sendMessage("It's recommended to wear an Anti-dragon shield when figthing a Dragon!");
				return;
			}
			if (c.dialogueAction == 532) {
				c.getPA().closeAllWindows();
				c.getShops().openShop(6);
				return;
			}

			if (c.dialogueAction == 2000) {
				c.getItems().addItem(c.floweritem, 1);
				c.getPA().removeObject(ClickItem.flowerX, ClickItem.flowerY);
				ClickItem.flowerX = 0;
				ClickItem.flowerY = 0;
				ClickItem.flowers = 0;
				c.getPA().closeAllWindows();

			}

			if (c.dialogueAction == 1436) {
				/*
				 * if(c.LoyaltyPoints == 10000 || c.LoyaltyPoints > 10000) {
				 * c.getPA().closeAllWindows(); c.hasYell = true;
				 * c.sendMessage("You can now use the ::yell command! Enjoy!");
				 * c.LoyaltyPoints -= 10000; } else {
				 * c.sendMessage("You don't have enough Loyalty points."); }
				 */
				c.getPA().closeAllWindows();
				c.sendMessage("Sorry but this feature has been disabled because of people abusing it.");
				return;
			}
			if (c.dialogueAction == 201) {
				c.getPA().closeAllWindows();
				if (PartyRoom.counterDelay != 0) {
					c.sendMessage("There is already a drop going on!");
					return;
				}
				for (int x = 0; x < PartyRoom.roomItems.length;) {
					if (PartyRoom.roomItemsN[x] > 0) {
						if (c.getItems().playerHasItem(995, 1000)) {
							c.getItems().deleteItem(995,
									c.getItems().getItemSlot(995), 1000);
							c.sendMessage("You pull the lever!");
							c.startAnimation(798);
							PartyRoom.getCounterDelay(c);
						} else
							c.sendMessage("You don't have enough money.");
						return;
					} else {
						c.sendMessage("There are no items in the chest!");
						return;
					}
				}
			}

			if (c.dialogueAction == 2001) {
				c.getShops().openShop(11);
				return;
			}

			if (c.dialogueAction == 13813) {
				c.getPA().closeAllWindows();
				// c.getPA().showInterface(24500);
				c.sendMessage("Grand Exchange will be added into Divination of Gods V4 Client instead.");
				return;
			}
			if (c.dialogueAction == 2452) {
				c.getPA().closeAllWindows();
				c.dialogueAction = 24524;
				c.getDH().sendOption4("20K XP, 1 Ticket", "110K XP, 5 Tickets",
						"220K XP, 10 Tickets", "330K XP, 15 Tickets");
				return;
			}
			if (c.dialogueAction == 524) {
				c.getShops().openShop(4);
				return;
			}
			if (c.dialogueAction == 2521) {
				if (c.playerLevel[24] > 85) {
					c.getPA().closeAllWindows();
					c.getPA().spellTeleport(3052, 9577, 0);
					c.dialogueAction = 0;
					return;
				} else {
					c.sendMessage("You need atleast level 85 Dungeoneering to face Frost dragons!");
				}
			}

			if (c.dialogueAction == 3842) {
				c.playerMagicBook = 1;
				c.getPA().closeAllWindows();
				c.setSidebarInterface(6, 12855);
				c.sendMessage("An ancient wisdomin fills your mind.");
				c.getPA().resetAutocast();
				return;
			}

			if (c.dialogueAction == 3844) {
				c.setSidebarInterface(6, 1151); // modern
				c.playerMagicBook = 0;
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
				c.getPA().closeAllWindows();
			}

			if (c.dialogueAction == 3848) {
				if (c.playerLevel[5] < c.getPA().getLevelForXP(c.playerXP[5])) {
					c.startAnimation(645);
					c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
					c.getPA().getLevelForXP(c.playerXP[5]);
					c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
					c.getPA().getLevelForXP(c.playerXP[3]);
					c.sendMessage("You recharge your prayer points & HP...");
					c.getPA().refreshSkill(5);
					c.getPA().refreshSkill(3);
					c.dialogueAction = 0;
					return;
				} else {
					c.sendMessage("You already have full prayer points.");
				}
				return;
			}
			if (c.teleAction == 4998) {
				DominionTowerByGabbe
						.startDominionTowerWave(c, 1125, 2327, 9899); // start
																		// dad
				c.teleAction = 0;
			}
			if (c.teleAction == 1227) {
				c.getPA().movePlayer(3467, 9495, 0);
				c.sendMessage("<shad=16112652>Kill NPC's here for fast Dung XP/Tokens!");
				c.sendMessage("<shad=16112652>You are taken to a underground cave!");
				c.teleAction = 0;
			}
			if (c.teleAction == 5000) {
				DominionTowerByGabbe.startDominionTowerWave(c, 655, 2327, 9899); // start
																					// tree
																					// spirit
				c.teleAction = 0;
			}
			if (c.teleAction == 5002) {
				DominionTowerByGabbe
						.startDominionTowerWave(c, 2743, 2327, 9899); // start
																		// Ket-Zek
				c.teleAction = 0;
			}
			if (c.teleAction == 5004) {
				DominionTowerByGabbe
						.startDominionTowerWave(c, 2083, 2327, 9899); // start
																		// Sigmund
				c.teleAction = 0;
			}
			if (c.teleAction == 5006) {
				DominionTowerByGabbe
						.startDominionTowerWave(c, 1540, 2327, 9899); // start
																		// trays_dyths
				c.teleAction = 0;
			}
			if (c.teleAction == 5008) {
				DominionTowerByGabbe
						.startDominionTowerWave(c, 1813, 2327, 9899); // kendal
																		// bear
				c.teleAction = 0;
			}
			if (c.teleAction == 5010) {
				DominionTowerByGabbe.startDominionTowerWave(c, 753, 2327, 3381); // melzar
																					// mad
																					// man
				c.teleAction = 0;
			}
			if (c.teleAction == 1) {
				c.playerTitle = 0;
				c.sendMessage("Your title has been reset.");
				c.getPA().closeAllWindows();
				c.dialogueAction = 0;
				c.teleAction = 0;
				return;
			}
			if (c.dialogueAction == 61) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You don't have a hammer with you!");
					c.getPA().closeAllWindows();
					return;
				}
				if (c.getItems().playerHasItem(2361, 1)) {
					c.getSmithingInt().showSmithInterface(2361);
					return;
				} else {
					c.getPA().closeAllWindows();
					c.sendMessage("You don't have any Adamantite bars.");
				}
			}
			npcType = 7197;
			if (c.dialogueAction == 188) {
				c.getDH().sendDialogues(2225, npcType);
				// c.dialogueAction = 0;
				return;
			}
			if (c.dialogueAction == 458) {
				if (c.getItems().playerHasItem(995, 1000000)) {
					c.getDH().sendDialogues(2245, npcType);
				} else {
					c.getDH().sendDialogues(2235, npcType);
					// c.dialogueAction = 0;
					return;
				}
			}

			if (c.dialogueAction == 4445) {
				if (c.getItems().playerHasItem(995, 250000)) {
					c.getPA().closeAllWindows();
					c.getItems().deleteItem(995, 250000);
					c.getItems().addItem(15121, 1);
					c.sendMessage("You've purchased a Milestone Cape!");
				} else {
					c.sendMessage("You need atleast 250K to buy the Milestone Cape!");
					c.getPA().closeAllWindows();
				}
			}
			if (c.dialogueAction == 333) {
				c.getPA().spellTeleport(3374, 9811, 0);
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 222) {
				c.getPA().spellTeleport(2146 + Misc.random(2),
						5262 + Misc.random(2), 0);
			}
			if (c.dialogueAction == 111) {
				c.getPA().enterCaves();
				c.getPA().closeAllWindows();
				return;
			}
			if (c.dialogueAction == 112) {
				c.getPA().enterNewCaves();
				c.getPA().closeAllWindows();
				return;
			}
			if (c.dialogueAction == 77) {
				if (c.hasHouse > 0) {
					c.sendMessage("You alredy have a house! Use a tablet to teleport to it!");
					return;
				}
				if (c.getItems().playerHasItem(995, 1000000)) {
					c.getDH().sendDialogues(29170, 4547);
					c.getItems().deleteItem(995, 1000000);
					c.hasHousee = true;
					c.SaveGame();
					break;
				} else {
					if (!c.getItems().playerHasItem(995, 1000000)) {
						c.sendMessage("You don't have enough money with you!");
						c.getPA().closeAllWindows();
					}
				}
			}

			if (c.teleAction == 966) {
				c.getPA().spellTeleport(2928, 5203, 0);
				// //c.getPA().sendMp3("Music");
			}
			if (c.teleAction == 21) { // FLETCHING
				c.getPA().spellTeleport(2709, 3463, 0);
			}
			if (c.teleAction == 999) {
				c.getPA().spellTeleport(3300, 3302, 0);
				c.sendMessage("Have fun Mining!");
			}
			if (c.teleAction == 5) {
				c.getPA().spellTeleport(2741, 3444, 0);
			}
			if (c.teleAction == 22) { // wcing woodcutting
				c.getPA().spellTeleport(2710, 3462, 0);
			}
			if (c.dialogueAction == 510) {
				c.getDH().sendDialogues(1055, 278);
				return;
			}
			if (c.dialogueAction == 120) {
				if (Config.ALLOWPINS) {
					if (!c.hasBankPin) {
						c.getBankPin().openPin();
					} else {
						c.sendMessage("You already have a bank pin!");
						c.getPA().closeAllWindows();
					}
				} else {
					c.sendMessage("You may not set a bank pin now!");
					c.getPA().closeAllWindows();
				}
				// }
				break;
			} else if (c.dialogueAction == 1) {
				int r = 4;
				// int r = Misc.random(3);
				switch (r) {
				case 0:
					c.getPA().movePlayer(3534, 9677, 0);
					break;

				case 1:
					c.getPA().movePlayer(3534, 9712, 0);
					break;

				case 2:
					c.getPA().movePlayer(3568, 9712, 0);
					break;

				case 3:
					c.getPA().movePlayer(3568, 9677, 0);
					break;
				case 4:
					c.getPA().movePlayer(3551, 9694, 0);
					break;
				}
			} else if (c.dialogueAction == 2) {
				c.getPA().movePlayer(2507, 4717, 0);
			} else if (c.dialogueAction == 5) {
				c.getSlayer().giveTask();
			} else if (c.dialogueAction == 6) {
				c.getSlayer().giveTask2();
			} else if (c.dialogueAction == 211) {
				c.sendMessage("Dialogue Closed");
			} else if (c.dialogueAction == 7) {
				c.getPA().closeAllWindows();
				c.getPA().enterGoblin();
				c.sendMessage("The minigame will start in 5 seconds.");
			} else if (c.dialogueAction == 50) {
				c.sendMessage("This is ");
			} else if (c.dialogueAction == 34) {
				c.sendMessage("Coming soon ");
			} else if (c.dialogueAction == 524) {
				c.getPA().closeAllWindows();
				c.getShops().openShop(5);
			} else if (c.dialogueAction == 8) {
				c.getPA().resetBarrows();
				c.sendMessage("Your barrows have been reset.");
			} else if (c.dialogueAction == 200) {
				c.sendMessage("The dragon is soon home, pot up!!");
				// c.getPA().movePlayer(2855, 9637, c.playerId * 4);
			} else if (c.dialogueAction == 555) {
				c.getDH().sendDialogues(556, 553);
				c.sendMessage("You've now started Rune Mysteries Quest.");
			} else if (c.dialogueAction == 259) {
				c.getPA().removeAllWindows();
			} else if (c.nextChat == 999 && c.teleAction == 11) {
				c.teleAction = 0;
				if (c != null) {
					if (c.hasFollower > 0) {
						for (int i = 0; i < 29; i += 1) {
							if (c.storeditems[i] > 0
									&& c != null
									&& NPCHandler.npcs[c.summoningnpcid] != null) {
								Server.itemHandler.createGroundItem(c,
										c.storeditems[i],
										NPCHandler.npcs[c.summoningnpcid].absX,
										NPCHandler.npcs[c.summoningnpcid].absY,
										1, c.playerId);
							}
							c.SaveGame();
							c.firstslot();
							c.storeditems[i] = -1;
							c.occupied[i] = false;
							c.yak = false;
							c.hasFollower = -1;
							c.totalstored = 0;
							c.summoningnpcid = 0;
							c.summoningslot = 0;
							c.yak = false;
							c.getPA().sendFrame126(
									"   " + c.playerLevel[21] + "/"
											+ c.getLevelForXP(c.playerXP[21]),
									17025);
							c.getPA().sendFrame126(
									"" + c.summonTime / 120 + ".00 Min", 17021);
							boolean sent = false;
							if (!sent) {
								c.sendMessage("Your BoB's items have dropped to the floor.");
								c.SaveGame();
								sent = true;
							}

						}
					} else {
						c.sendMessage("You do not have a summoned NPC at the moment.");
					}
					c.teleAction = 0;
				}

			} else if (c.dialogueAction == 27) {
				c.getPA().movePlayer(3086, 3493, 0);
				c.monkeyk0ed = 0;
				c.Jail = false;
				c.forcedText = "I swear to god that i will never break the rules anymore!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			}
			c.dialogueAction = 0;
			c.teleAction = 0;
			c.getPA().removeAllWindows();
			break;

		case 9158:
			if (c.dialogueAction == 4381) {
				c.getShops().openShop(56);
				return;
			}

			else if (c.teleAction == 428) {
				c.getShops().openShop(92);
				return;
			}

			if (c.dialogueAction == 894) {
				c.getShops().openShop(37);
				return;
			}

			if (c.dialogueAction == 893) {
				c.getShops().openShop(3);
				return;
			}
			if (c.dialogueAction == 896) {
				c.getShops().openShop(2);
				return;
			}
			if (c.dialogueAction == 895) {
				c.getShops().openShop(6);
				return;
			}
			if (c.dialogueAction == 898) {
				c.getShops().openShop(43);
				return;
			}
			if (c.dialogueAction == 897) {
				c.getShops().openShop(1);
				return;
			}

			if (c.teleAction == 4325) {
				c.getPA().closeAllWindows();
				c.mustPay = true;
				return;
			}
			if (c.teleAction == 1442) {
				c.getDH().sendOption5("100", "500", "1000", "3000", "5000");
				c.teleAction = 1645;
				return;
			}
			if (c.teleAction == 4284) {
				c.getDH().sendOption4("Nails", "Bars", "Gold leaves",
						"Molten glass");
				c.teleAction = 4282;
				return;
			}
			if (c.teleAction == 835) {
				c.getPA().closeAllWindows();
				if (Construction.hasReqs(c, "Crystal of Power")) {
					c.getPA().checkObjectSpawn(13661, 1893, 5100, 0, 10);
					c.constructionObjects[10] = true;
					c.constructionUpgrades[10] = 13661;
				}
				return;
			}
			if (c.teleAction == 444) {
				c.getPA().closeAllWindows();
				c.getShops().openShop(77);

				return;
			} // här
			if (c.dialogueAction == 894) {
				c.getShops().openShop(37);
				return;
			}

			if (c.dialogueAction == 893) {
				c.getShops().openShop(3);
				return;
			}
			if (c.dialogueAction == 579) {
				c.getPA().closeAllWindows();
				// c.getDungeoneering().handleStart(c, true);
				c.sendMessage("Dungeoneering is currently only in testing-mode.");
				c.sendMessage("This floor will be released soon.");
				return;
			}
			if (c.dialogueAction == 214) {
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;
						if (ALLPLAYERS.playerName.equalsIgnoreCase(c.Partner)) {
							Client c2 = (Client) p;
							if (c2.inDung && c.inDung) {
								c.getPA().setDungBindStats(c2);
								return;
							}
						}
					}
				}
			}
			if (c.teleAction == 54) {
				c.getPA().moveBarb();
				return;
			}
			if (c.dialogueAction == 535) {
				c.getPA().closeAllWindows();
				c.getPA().addStarterItems();
				return;
			}
			if (c.dialogueAction == 942) {
				c.getPA().closeAllWindows();
				c.getDH().sendDialogues(41, 1);
				return;
			}
			if (c.dialogueAction == 532) {
				c.getPA().closeAllWindows();
				c.getShops().openShop(1);
				return;
			}
			if (c.dialogueAction == 2000) {
				ClickItem.flowerX = 0;
				ClickItem.flowerY = 0;
				ClickItem.flowers = 0;
				c.getPA().closeAllWindows();

			}
			if (c.dialogueAction == 1436) {
				c.getPA().closeAllWindows();
			}
			if (c.dialogueAction == 2001) {
				c.getShops().openShop(14);
				return;
			}
			if (c.dialogueAction == 201) {
				if (PartyRoom.knightStage == 10) {
					if (c.getItems().playerHasItem(995, 500)) {
						c.getItems().deleteItem(995,
								c.getItems().getItemSlot(995), 500);
						PartyRoom.knightStage = 0;// Spawns Knights
					} else
						c.sendMessage("You don't have enough money.");
				} else
					c.sendMessage("The knights are busy.");
				c.getPA().closeAllWindows();
				return;
			}
			if (c.dialogueAction == 13813) {
				c.getPA().closeAllWindows();
				c.getPA().openUpBank(0);
				return;
			}
			if (c.dialogueAction == 2452) {
				c.sendMessage("You have: " + c.SPoints
						+ " Agility Points. Get more by completing the course");
				c.getShops().openShop(16);
				return;
			}
			if (c.dialogueAction == 2521) {
				c.getPA().closeAllWindows();
				c.getPA().spellTeleport(3202, 3860, 0);
				c.dialogueAction = 0;
				return;
			}
			if (c.dialogueAction == 524) {
				c.getPA().closeAllWindows();
				c.getShops().openShop(5);
				return;
			}
			if (c.dialogueAction == 3842) {
				c.getPA().closeAllWindows();
				c.playerMagicBook = 2;
				c.setSidebarInterface(6, 29999);
				c.sendMessage("Your mind becomes stirred with thoughs of dreams.");
				c.getPA().resetAutocast();
				c.dialogueAction = 0;
				return;
			}
			if (c.dialogueAction == 3844) {
				c.getPA().closeAllWindows();
				c.dialogueAction = 3842;
				c.getDH().sendOption2("Ancients spellbook", "Lunar spellbook");
				c.dialogueAction = 3842;
				return;
			}
			if (c.dialogueAction == 3848) {
				c.getPA().closeAllWindows();
				c.dialogueAction = 3844;
				c.getDH().sendOption2("Normal spellbook",
						"Ancients/Lunar spellbook");
				return;
			}
			if (c.teleAction == 1227) {
				if (c.playerLevel[24] < 70) {
					c.sendMessage("You need atleast 70 Dungeoneering to fight this demon.");
					return;
				}
				c.getPA().handleJungleDemon();
				c.teleAction = 0;
			}
			if (c.teleAction == 1) {
				c.getPA().closeAllWindows();
				c.dialogueAction = 0;
				c.teleAction = 0;
				return;
			}
			if (c.dialogueAction == 61) {
				if (!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You don't have a hammer with you!");
					c.getPA().closeAllWindows();
					return;
				}
				if (c.getItems().playerHasItem(2363, 1)) {
					c.getSmithingInt().showSmithInterface(2363);
					return;
				} else {
					c.getPA().closeAllWindows();
					c.sendMessage("You don't have any Runite bars.");
				}
			}
			if (c.dialogueAction == 222) {
				c.getPA().spellTeleport(3109, 9832, 0);
			}
			if (c.dialogueAction == 111) {
				// c.getPA().enterNewCaves();
				c.getDH().sendDialogues(2222, 111);
				return;
			}
			if (c.teleAction == 999) {
				c.getPA().spellTeleport(3277, 3158, 0);
			}
			if (c.teleAction == 21) { // Thieving
				c.getPA().spellTeleport(2662, 3308, 0);
				c.sendMessage("Pickpocket the NPC's until you can train on stalls.");
			}
			if (c.teleAction == 5) {
				c.getPA().spellTeleport(2604, 4772, 0);
				c.sendMessage("<shad=6081134>Sell the impling Jar's to the general shop for money");
				c.sendMessage("<shad=6081134>Buy a Butterfly Net from Tamayu if you dont have one");
			}
			if (c.teleAction == 22) { // Firemaking Fm tele
				// c.getPA().spellTeleport(2904,3463,0);
				c.sendMessage("You can firemake all over the world!");
			}
			if (c.dialogueAction == 510) {
				c.getDH().sendDialogues(1061, 278);
				return;
			}
			if (c.dialogueAction == 50) {
				c.getPA().startTeleport(2559, 3089, 0, "modern");
				c.sendMessage("This is PVP!");
			} else if (c.dialogueAction == 259) { // Drop item warning
				c.getPA().destroyItem(c.droppedItem);
				c.droppedItem = -1;
			} else if (c.dialogueAction == 257) { // Dung leave warning
				if (c.Partner.equalsIgnoreCase("None")) {
					c.getPA().leaveDung(c);
					c.getPA().closeAllWindows();
				} else {
					for (Player p : PlayerHandler.players) {
						if (p != null) {
							Client ALLPLAYERS = (Client) p;
							if (ALLPLAYERS.playerName
									.equalsIgnoreCase(c.Partner)) {
								Client c3 = (Client) p;
								if (c3 != null) {
									c3.getPA().leaveDung(c3);
									c.getPA().leaveDung(c);
									c3.sendMessage("Your friend abandoned the dungeon.");
									c3.sendMessage("The party has been deleted.");
									c.sendMessage("The party has been deleted.");

								}
							}
						}
					}
				}
			} else if (c.dialogueAction == 8591) { // NOMAD DIALOGUE
				c.getPA().enterNomad();
				c.sendMessage("<col=255>If you do not have 2 free slots you will not get anything!</col>");
				c.sendMessage("<col=255>NOMAD WILL SPAWN WITHIN 10 SECONDS, Pot up and activate prayers</col>");
				c.sendMessage("<col=255>IF YOU DIE YOU WILL LOOSE YOUR ITEMS!</col>");
				c.sendMessage("<col=255>To escape climb the ladder! Remember - Nomad has alot of hp</col>");
				c.sendMessage("Nomad minigame made by Samy and Gabbe!");
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 3664) { // ANGRY GOBLIN
				c.getPA().enterGoblin();
				c.sendMessage("<col=255>If you do not have 2 free slots you will not get anything!</col>");
				c.sendMessage("<col=255>The Goblin WILL SPAWN WITHIN 10 SECONDS, Pot up and activate prayers</col>");
				c.sendMessage("<col=255>If you die you will loose items! Made By Samy & Gabbe</col>");
				c.sendMessage("<col=255>Drink from couldrons to escape!</col>");
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 13) {
				c.getPA().spellTeleport(3202, 3859, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 34) {
				c.getPA().removeAllWindows();
				c.dialogueAction = -1;
			}

			if (c.dialogueAction == 8) {
				c.getPA().fixAllBarrows();
			} else {
				c.dialogueAction = 0;
				c.teleAction = 0;
				c.getPA().removeAllWindows();
			}
			break;

		case 9159:
			if (c.dialogueAction == 51) {
				c.getPA().startTeleport(3351, 3659, 0, "modern");
			}
			if (c.dialogueAction == 201) {
				c.getPA().closeAllWindows();
			}
			break;

		case 107243:
			c.setSidebarInterface(4, 1644);
			break;

		case 107215:
			c.setSidebarInterface(11, 904);
			break;

		/** Specials **/
		case 29188:
			c.specBarId = 7636; // the special attack text - sendframe126(S P E
								// C I A L A T T A C K, c.specBarId);
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29038:
			c.usingSpecial = true;
			c.getItems().updateSpecialBar();
			if (c.playerEquipment[Player.playerWeapon] == 13902) {
				c.specBarId = 7486;
				c.usingSpecial = !c.usingSpecial;
				c.getItems().updateSpecialBar();
			} else {
				c.specBarId = 7486;
				if (c.specAmount >= 4.5 && c.isAttacking == true
						&& c.playerEquipment[Player.playerWeapon] == 4153) {
					c.attackTimer = 0;
					c.getCombat().attackPlayer(c.playerIndex);
					c.usingSpecial = true;
					c.getCombat().handleGmaulPlayer();
					c.getItems().updateSpecialBar();
				}
			}
			break;

		case 29063:
			if (c.getCombat().checkSpecAmount(
					c.playerEquipment[Player.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2])
						+ (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;

		case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 30108:
			c.specBarId = 7812;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29138:
			if (c.playerEquipment[Player.playerWeapon] == 15486) {
				if (c.getCombat().checkSpecAmount(
						c.playerEquipment[Player.playerWeapon])) {
					c.gfx0(1958);
					c.SolProtect = 120;
					c.startAnimation(10518);
					c.getItems().updateSpecialBar();
					c.usingSpecial = !c.usingSpecial;
					c.sendMessage("All damage will be split into half for 1 minute.");
					c.forcedChat("I am Protected By the Light!");
					c.getPA().sendFrame126("@bla@S P E C I A L  A T T A C K",
							7562);
				} else {
					c.sendMessage("You don't have the required special energy to use this attack.");
				}
			}
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;

		/** Dueling **/
		case 26065: // no forfeit
			c.sendMessage("Forfeiting IN DUELS IS DISABLED DUE TO BUGS!!");
			c.sendMessage("Forfeiting IN DUELS IS DISABLED DUE TO BUGS!!");
			c.sendMessage("Forfeiting IN DUELS IS DISABLED DUE TO BUGS!!");
			c.sendMessage("Forfeiting IN DUELS IS DISABLED DUE TO BUGS!!");
			return;

		case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;

		case 26066: // no movement
		case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;

		case 26069: // no range
		case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;

		case 26070: // no melee
		case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;

		case 26071: // no mage
		case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;

		case 26072: // no drinks
		case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;

		case 26073: // no food
		case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;

		case 26074: // no prayer
		case 26047:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			c.getCombat().resetPrayers();
			c.getCurse().resetCurse();
			break;

		case 26076: // obsticals
		case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;

		case 2158: // fun weapons
			c.sendMessage("FUN WEAPONS IN DUELS ARE DISABLED DUE TO BUGS!!");
			c.sendMessage("FUN WEAPONS IN DUELS ARE DISABLED DUE TO BUGS!!");
			c.sendMessage("FUN WEAPONS IN DUELS ARE DISABLED DUE TO BUGS!!");
			c.sendMessage("FUN WEAPONS IN DUELS ARE DISABLED DUE TO BUGS!!");
			return;

		case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			c.getCombat().resetPrayers();
			c.getCurse().resetCurse();
			break;

		case 30136: // sp attack
		case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;

		case 53245: // no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;

		case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;

		case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;

		case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;

		case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;

		case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;

		case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;

		case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;

		case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;

		case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;

		case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;

		case 26018:
			Client o = (Client) PlayerHandler.players[c.duelingWith];
			if (o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			if (c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if (c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if (o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}

			if (c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;

		case 25120:
			if (c.duelStatus == 5) {
				break;
			}
			Client o1 = (Client) PlayerHandler.players[c.duelingWith];
			if (o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			c.duelStatus = 4;
			if (o1.duelStatus == 4 && c.duelStatus == 4) {
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;
		case 47083:
			c.getPA().closeAllWindows();
		case 4169: // god spell charge
			c.usingMagic = true;
			if (!c.getCombat().checkMagicReqs(48)) {
				break;
			}

			if (System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay = System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
			break;

		case 28164: // item kept on death
			break;

		/*
		 * case 152: if (c.isRunning = true) { c.isRunning = false; }
		 * c.sendMessage("You're alredy walking.."); return; case 153:
		 * c.isRunning = c.isRunning; c.isRunning = true; break;
		 */
		case 152:
			c.isRunning = !c.isRunning;
			break;
		case 153:
			c.isRunning = !c.isRunning;
			break;

		case 9154:
			c.logout();
			break;

		case 82016:
			c.takeAsNote = !c.takeAsNote;
			break;

		// home teleports

		// home teleports
		case 4171:
		case 117048:
		case 50056:
			if (c.bossing && c.inDung || c.inDungBossRoom()) {
				c.sendMessage("You're not able to teleport out of the boss room!");
				return;
			}
			if (c.inDung) {
				if (c.Partner.equalsIgnoreCase("None")) {
					c.SaveGame();
					c.getPA()
							.teleDungHome(1861, 5242, c.playerId * 4, "modern");
					return;
				} else {
					for (Player p : PlayerHandler.players) {
						if (p != null) {
							Client ALLPLAYERS = (Client) p;
							if (ALLPLAYERS.playerName
									.equalsIgnoreCase(c.Partner)) {
								Client c2 = (Client) p;
								if (c2 != null) {
									c.SaveGame();
									c.getPA().teleDungHome(1861, 5242,
											c2.heightLevel, "modern");
									//

								}
							}
						}
					}
				}
				return;
			}
			String type = c.playerMagicBook == 0 ? "modern" : "ancient";
			c.SaveGame();
			c.getPA().startTeleport(3165, 3482, 0, type); // varrock
			// c.getPA().sendshh();
			// c.getPA().startTeleport(2957, 3218, 0, type); // rimmngton
			c.getPA().resetFishing();
			break;

		// case 4171:
		/*
		 * case 50056: String type = c.playerMagicBook == 0 ? "modern" :
		 * "ancient"; c.getPA().startTeleport(3086, 3493, 0, type); break;
		 */

		/*
		 * case 50235: case 4140: case 117112: c.setSidebarInterface(6, 45300);
		 * break;
		 */

		case 4143:
		case 50245:
		case 117123:
			c.getPA().showInterface(42000);
			/*
			 * c.getDH().sendOption5("Assault", "Recipe for Disaster", "Nomad",
			 * "Dominion Tower", "@red@Castle Wars"); c.teleAction = 199;
			 * c.setSidebarInterface(6, 45200);
			 */
			break;

		case 50253:
		case 117131:
		case 4146:
			c.setSidebarInterface(6, 45500);
			c.getDH().sendOption5("War Zone (MASSIVE PLAYER V NPC WAR)",
					"Mighty Saradomin", "Mad Mummy", "BarrelsChest", "GodWars");
			c.teleAction = 6;
			break;

		case 51005:
		case 117154:
		case 4150:
			// c.setSidebarInterface(6, 45600);
			c.getPA().spellTeleport(3087, 3515, 0);
			break;

		case 50235:
		case 4140:
		case 117112:
			c.getDH().sendOption2("Polypore Dungeon", "Blue Dragons");
			c.dialogueAction = 222;
			c.setSidebarInterface(6, 34000);
			break;
		/*
		 * case 4143: case 50245: case 117123: c.getDH().sendOption5("Barrows",
		 * "Pest Control", "TzHaar Cave", "Duel Arena", "Warrior Guild");
		 * c.teleAction = 2; break;
		 * 
		 * case 50253: case 117131: case 4146: c.getDH().sendOption5("Godwars",
		 * "King Black Dragon (Wild)", "Dagannoth Kings", "Tormented Demons",
		 * "Corporeal Beast"); c.teleAction = 3; break;
		 * 
		 * 
		 * case 51005: case 117154: case 4150:
		 * c.getDH().sendOption5("Mage Bank", "Varrock PK",
		 * "Lava Crossing (Multi)", "Edgeville", "Green Dragons"); c.teleAction
		 * = 4; break;
		 */case 51013:
		case 6004:
		case 117162:
			/*
			 * c.getDH().sendOption5("Crafting & Hunter", "Mining & Smithing",
			 * "Fishing & Cooking", "Woodcutting & Firemaking",
			 * "Farming & Herblore");
			 */
			GabbesSkillTeleport.openInterface(c);
			break;

		case 117186:
			c.getDH().sendOption5("Sea Troll Queen", "Kalphite Queen",
					"Godwars", "Giant sea Snake", "Avatar of Destruction");
			c.teleAction = 8;
			break;

		case 51023:
		case 6005:
			c.getDH().sendOption5("Yanille", "Varrock", "Camelot", "Falador",
					"Canafis");
			c.teleAction = 20;
			break;

		case 51031:
		case 29031:
			c.setSidebarInterface(6, 45600);
			c.getDH().sendOption5("Fun Pk", "Multi Zone", "Suggest on Forums",
					"Coming Soon", "Coming SOon");
			c.teleAction = 21;
			break;

		/*
		 * case 72038: case 51039: // c.getDH().sendOption5("Sea Troll Queen",
		 * "Lakhrahnaz", "Nomad & Angry Goblin", "Giant sea Snake",
		 * "Avatar of Destruction"); //c.teleAction = 8;
		 * c.sendMessage("This teleport leads nowhere!"); break;
		 */

		case 9125: // Accurate
		case 6221: // range accurate
		case 48010: // flick (whip)
		case 21200: // spike (pickaxe)
		case 1080: // bash (staff)
		case 6168: // chop (axe)
		case 6236: // accurate (long bow)
		case 17102: // accurate (darts)
		case 8234: // stab (dagger)
		case 22230: // unarmed
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

		case 9126: // Defensive
		case 48008: // deflect (whip)
		case 21201: // block (pickaxe)
		case 1078: // focus - block (staff)
		case 6169: // block (axe)
		case 33019: // fend (hally)
		case 18078: // block (spear)
		case 8235: // block (dagger)
					// case 22231: //unarmed
		case 22228: // unarmed
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

		case 9127: // Controlled
		case 48009: // lash (whip)
		case 33018: // jab (hally)
		case 6234: // longrange (long bow)
		case 6219: // longrange
		case 18077: // lunge (spear)
		case 18080: // swipe (spear)
		case 18079: // pound (spear)
		case 17100: // longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

		case 9128: // Aggressive
		case 6220: // range rapid
		case 21203: // impale (pickaxe)
		case 21202: // smash (pickaxe)
		case 1079: // pound (staff)
		case 6171: // hack (axe)
		case 6170: // smash (axe)
		case 33020: // swipe (hally)
		case 6235: // rapid (long bow)
		case 17101: // repid (darts)
		case 8237: // lunge (dagger)
		case 8236: // slash (dagger)
		case 22229: // unarmed
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

		/** Prayers **/
		/*
		 * case 87231: // thick skin if(c.trade11 > 1) { for(int p = 0; p <
		 * c.PRAYER.length; p++) { // reset prayer glows c.prayerActive[p] =
		 * false; c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0); }
		 * c.sendMessage("You must wait 15 minutes before using this!");
		 * c.trade11(); return; } c.getCurse().activateCurse(0); break; case
		 * 87233: // burst of str c.getCurse().activateCurse(1); break; case
		 * 87235: // charity of thought c.getCurse().activateCurse(2); break;
		 * case 87237: // range c.getCurse().activateCurse(3); break; case
		 * 87239: // mage c.getCurse().activateCurse(4); break; case 87241: //
		 * berserker if(c.altarPrayed == 0) { return; }
		 * c.getCurse().activateCurse(5); break; case 87243: // super human
		 * c.getCurse().activateCurse(6); break; case 87245: // improved
		 * reflexes c.getCurse().activateCurse(7); break; case 87247: //hawk eye
		 * c.getCurse().activateCurse(8); break; case 87249:
		 * c.getCurse().activateCurse(9); break; case 87251: // protect Item
		 * c.getCurse().activateCurse(10); break; case 87253: // 26 range
		 * c.getCurse().activateCurse(11); break; case 87255: // 27 mage
		 * c.getCurse().activateCurse(12); break; case 88001: // steel skin
		 * c.getCurse().activateCurse(13); break; case 88003: // ultimate str
		 * c.getCurse().activateCurse(14); break; case 88005: // incredible
		 * reflex c.getCurse().activateCurse(15); break; case 88007: // protect
		 * from magic c.getCurse().activateCurse(16); break; case 88009: //
		 * protect from range c.getCurse().activateCurse(17); break; case 88011:
		 * // protect from melee c.getCurse().activateCurse(18); break; case
		 * 88013: // 44 range c.getCurse().activateCurse(19); break; /**End of
		 * curse prayers**
		 * 
		 * 
		 * *Prayers** case 97168: // thick skin c.getCombat().activatePrayer(0);
		 * break; case 97170: // burst of str c.getCombat().activatePrayer(1);
		 * break; case 97172: // charity of thought
		 * c.getCombat().activatePrayer(2); break; case 97174: // range
		 * c.getCombat().activatePrayer(3); break; case 97176: // mage
		 * c.getCombat().activatePrayer(4); break; case 97178: // rockskin
		 * c.getCombat().activatePrayer(5); break; case 97180: // super human
		 * c.getCombat().activatePrayer(6); break; case 97182: // improved
		 * reflexes c.getCombat().activatePrayer(7); break; case 97184: //hawk
		 * eye c.getCombat().activatePrayer(8); break; case 97186:
		 * c.getCombat().activatePrayer(9); break; case 97188: // protect Item
		 * /*if(c.trade11 > 1) { for(int p = 0; p < c.PRAYER.length; p++) { //
		 * reset prayer glows c.prayerActive[p] = false;
		 * c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0); }
		 * c.sendMessage("You must wait 15 minutes before using this!"); return;
		 * }* c.getCombat().activatePrayer(10); break; case 97190: // 26 range
		 * c.getCombat().activatePrayer(11); break; case 97192: // 27 mage
		 * c.getCombat().activatePrayer(12); break; case 97194: // steel skin
		 * c.getCombat().activatePrayer(13); break; case 97196: // ultimate str
		 * c.getCombat().activatePrayer(14); break; case 97198: // incredible
		 * reflex c.getCombat().activatePrayer(15); break; case 97200: //
		 * protect from magic c.getCombat().activatePrayer(16); break; case
		 * 97202: // protect from range c.getCombat().activatePrayer(17); break;
		 * case 97204: // protect from melee c.getCombat().activatePrayer(18);
		 * break; case 97206: // 44 range c.getCombat().activatePrayer(19);
		 * break; case 97208: // 45 mystic c.getCombat().activatePrayer(20);
		 * break; case 97210: // retrui c.getCombat().activatePrayer(21); break;
		 * case 97212: // redem c.getCombat().activatePrayer(22); break; case
		 * 97214: // smite c.getCombat().activatePrayer(23); break; case 97216:
		 * // chiv c.getCombat().activatePrayer(24); break; case 97218: // piety
		 * c.getCombat().activatePrayer(25); break;
		 */

		/** CURSE Prayers **/
		case 87231: // protect Item
			/*
			 * if(c.trade11 > 1) { for(int p = 0; p < c.PRAYER.length; p++) { //
			 * reset prayer glows c.prayerActive[p] = false;
			 * c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0); }
			 * c.sendMessage("You must wait 15 minutes before using this!");
			 * return; }
			 */
			c.getCurse().activateCurse(0);
			break;
		case 87233: // sap warrior
			c.getCurse().activateCurse(1);

			break;
		case 87235: // sap ranger
			c.getCurse().activateCurse(2);

			break;
		case 87237: // sap mage
			c.getCurse().activateCurse(3);
			break;
		case 87239: // sap spirit
			c.getCurse().activateCurse(4);

			break;
		case 87241: // berserker
			c.startAnimation(12589);
			c.gfx0(2266);
			c.getCurse().activateCurse(5);
			break;
		case 87243: // deflect summoning
			c.getCurse().activateCurse(6);
			break;
		case 87245: // deflect mage
			c.getCurse().activateCurse(7);
			break;
		case 87247: // deflect range
			c.getCurse().activateCurse(8);
			break;
		case 87249: // deflect meele
			c.getCurse().activateCurse(9);
			break;
		case 87251: // Leech attack
			c.getCurse().activateCurse(10);

			break;
		case 87253: // leech range
			c.getCurse().activateCurse(11);

			break;
		case 87255: // leech mage
			c.getCurse().activateCurse(12);
			break;
		case 88001: // leech def
			c.getCurse().activateCurse(13);

			break;
		case 88003: // leech str
			c.getCurse().activateCurse(14);

			break;
		case 88005: // leech run
			c.getCurse().activateCurse(15);
			c.sendMessage("Everyone has unlimited energy. There is no point doing this!u dumb nub");
			c.forcedText = "Im dumb hahaha i thought i could leech unlimited run!";
			break;
		case 88007: // leech spec
			c.getCurse().activateCurse(16);

			break;
		case 88009: // Wrath
			c.getCurse().activateCurse(17);
			break;
		case 88011: // SS
			c.getCurse().activateCurse(18);
			break;
		case 88013: // turmoil
			c.getCurse().activateCurse(19);
			break;
		/** End of curse prayers **/

		/** Prayers **/
		case 21233: // thick skin
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(0);
			break;
		case 21234: // burst of str
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(1);
			break;
		case 21235: // charity of thought
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(2);
			break;
		case 70080: // range
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(3);
			break;
		case 70082: // mage
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(4);
			break;
		case 21236: // rockskin
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(5);
			break;
		case 21237: // super human
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(6);
			break;
		case 21238: // improved reflexes
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(7);
			break;
		case 21239: // hawk eye
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(8);
			break;
		case 21240:
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(9);
			break;
		case 21241: // protect Item
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(10);
			break;
		case 70084: // 26 range
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(11);
			break;
		case 70086: // 27 mage
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(12);
			break;
		case 21242: // steel skin
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(13);
			break;
		case 21243: // ultimate str
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(14);
			break;
		case 21244: // incredible reflex
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(15);
			break;
		case 21245: // protect from magic
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(16);
			break;
		case 21246: // protect from range
			if (c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(17);
			break;
		case 21247:// case 97204: // protect from melee
			c.getCombat().activatePrayer(18);
			break;
		case 70088:// case 97206: // 44 range
			c.getCombat().activatePrayer(19);
			break;
		case 70090:
			// case 97208: // 45 mystic
			c.getCombat().activatePrayer(20);
			break;
		// case 97210: // retrui
		case 2171:
			c.getCombat().activatePrayer(21);
			break;
		// case 97212: // redem
		case 2172:
			c.getCombat().activatePrayer(22);
			break;
		// case 97214: // smite
		case 2173:
			c.getCombat().activatePrayer(23);
			break;
		// case 97216: // chiv
		case 70092:
			c.getCombat().activatePrayer(24);
			break;
		// case 97218: // piety
		case 70094:
			c.getCombat().activatePrayer(25);
			break;

		case 13092:
			if (System.currentTimeMillis() - c.lastButton < 400) {

				c.lastButton = System.currentTimeMillis();

				break;

			} else {

				c.lastButton = System.currentTimeMillis();

			}
			Client ot = (Client) PlayerHandler.players[c.tradeWith];
			if (ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);
			c.goodTrade = true;
			ot.goodTrade = true;

			for (GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if (ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems
							.size()) {
						c.sendMessage(ot.playerName
								+ " only has "
								+ ot.getItems().freeSlots()
								+ " free slots, please remove "
								+ (c.getTradeAndDuel().offeredItems.size() - ot
										.getItems().freeSlots()) + " items.");
						ot.sendMessage(c.playerName
								+ " has to remove "
								+ (c.getTradeAndDuel().offeredItems.size() - ot
										.getItems().freeSlots())
								+ " items or you could offer them "
								+ (c.getTradeAndDuel().offeredItems.size() - ot
										.getItems().freeSlots()) + " items.");
						c.goodTrade = false;
						ot.goodTrade = false;
						c.getPA().sendFrame126("Not enough inventory space...",
								3431);
						ot.getPA().sendFrame126(
								"Not enough inventory space...", 3431);
						break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...",
								3431);
						ot.getPA().sendFrame126("Other player has accepted",
								3431);
						c.goodTrade = true;
						ot.goodTrade = true;
					}
				}
			}
			if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
				c.tradeConfirmed = true;
				if (ot.tradeConfirmed) {
					c.getTradeAndDuel().confirmScreen();
					ot.getTradeAndDuel().confirmScreen();
					break;
				}

			}

			break;

		case 13218:
			if (System.currentTimeMillis() - c.lastButton < 400) {

				c.lastButton = System.currentTimeMillis();

				break;

			} else {

				c.lastButton = System.currentTimeMillis();

			}
			c.tradeAccepted = true;
			Client ot1 = (Client) PlayerHandler.players[c.tradeWith];
			if (ot1 == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}

			if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed
					&& !c.tradeConfirmed2) {
				c.tradeConfirmed2 = true;
				if (ot1.tradeConfirmed2) {
					c.acceptedTrade = true;
					ot1.acceptedTrade = true;
					c.getTradeAndDuel().giveItems();
					ot1.getTradeAndDuel().giveItems();
					c.sendMessage("Trade accepted.");
					c.SaveGame();
					ot1.SaveGame();
					ot1.sendMessage("Trade accepted.");
					c.tradeStatus = 0;
					ot1.tradeStatus = 0;
					break;
				}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
			}

			break;
		/* Rules Interface Buttons */
		case 125011: // Click agree
			if (!c.ruleAgreeButton) {
				c.ruleAgreeButton = true;
				c.getPA().sendFrame36(701, 1);
			} else {
				c.ruleAgreeButton = false;
				c.getPA().sendFrame36(701, 0);
			}
			break;
		case 67100:// Accept
			c.getPA().addChaotics();
			c.newPlayer = false;
			c.sendMessage("You need to click on you agree before you can continue on.");
			break;
		case 67103:// Decline
			c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
			break;
		/* End Rules Interface Buttons */
		/* Player Options */
		case 74176:
			if (!c.mouseButton) {
				c.mouseButton = true;
				c.getPA().sendFrame36(500, 1);
				c.getPA().sendFrame36(170, 1);
			} else if (c.mouseButton) {
				c.mouseButton = false;
				c.getPA().sendFrame36(500, 0);
				c.getPA().sendFrame36(170, 0);
			}
			break;
		case 74184:
			if (!c.splitChat) {
				c.splitChat = true;
				c.getPA().sendFrame36(502, 1);
				c.getPA().sendFrame36(287, 1);
			} else {
				c.splitChat = false;
				c.getPA().sendFrame36(502, 0);
				c.getPA().sendFrame36(287, 0);
			}
			break;
		case 100231:
			if (!c.chatEffects) {
				c.chatEffects = true;
				c.getPA().sendFrame36(501, 1);
				c.getPA().sendFrame36(171, 0);
			} else {
				c.chatEffects = false;
				c.getPA().sendFrame36(501, 0);
				c.getPA().sendFrame36(171, 1);
			}
			break;
		case 100237:
			if (!c.acceptAid) {
				c.acceptAid = true;
				c.getPA().sendFrame36(503, 1);
				c.getPA().sendFrame36(427, 1);
			} else {
				c.acceptAid = false;
				c.getPA().sendFrame36(503, 0);
				c.getPA().sendFrame36(427, 0);
			}
			break;
		case 74201:// brightness1
			c.getPA().sendFrame36(505, 1);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 1);
			break;
		case 74203:// brightness2
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 1);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 2);
			break;

		case 74204:// brightness3
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 1);
			c.getPA().sendFrame36(508, 0);
			c.getPA().sendFrame36(166, 3);
			break;

		case 74205:// brightness4
			c.getPA().sendFrame36(505, 0);
			c.getPA().sendFrame36(506, 0);
			c.getPA().sendFrame36(507, 0);
			c.getPA().sendFrame36(508, 1);
			c.getPA().sendFrame36(166, 4);
			break;
		case 74206:// area1
			c.getPA().sendFrame36(509, 1);
			c.getPA().sendFrame36(510, 0);
			c.getPA().sendFrame36(511, 0);
			c.getPA().sendFrame36(512, 0);
			break;
		case 74207:// area2
			c.getPA().sendFrame36(509, 0);
			c.getPA().sendFrame36(510, 1);
			c.getPA().sendFrame36(511, 0);
			c.getPA().sendFrame36(512, 0);
			break;
		case 74208:// area3
			c.getPA().sendFrame36(509, 0);
			c.getPA().sendFrame36(510, 0);
			c.getPA().sendFrame36(511, 1);
			c.getPA().sendFrame36(512, 0);
			break;
		case 74209:// area4
			c.getPA().sendFrame36(509, 0);
			c.getPA().sendFrame36(510, 0);
			c.getPA().sendFrame36(511, 0);
			c.getPA().sendFrame36(512, 1);
			break;
		case 168:
			c.startAnimation(855);
			break;
		case 169:
			c.startAnimation(856);
			break;
		case 162:
			c.startAnimation(857);
			break;
		case 164:
			c.startAnimation(858);
			break;
		case 165:
			c.startAnimation(859);
			break;
		case 161:
			c.startAnimation(860);
			break;
		case 170:
			c.startAnimation(861);
			break;
		case 171:
			c.startAnimation(862);
			break;
		case 163:
			c.startAnimation(863);
			break;
		case 167:
			c.startAnimation(864);
			break;
		case 172:
			c.startAnimation(865);
			break;
		case 166:
			c.startAnimation(866);
			break;
		case 52050:
			c.startAnimation(2105);
			break;
		case 52051:
			c.startAnimation(2106);
			break;
		case 52052:
			c.startAnimation(2107);
			break;
		case 52053:
			c.startAnimation(2108);
			break;
		case 52054:
			c.startAnimation(2109);
			break;
		case 52055:
			c.startAnimation(2110);
			break;
		case 52056:
			c.startAnimation(2111);
			break;
		case 52057:
			c.startAnimation(2112);
			break;
		case 52058:
			c.startAnimation(2113);
			break;
		case 43092:
			c.startAnimation(0x558);
			c.stopMovement();
			c.gfx0(574);
			break;
		case 2155:
			c.startAnimation(0x46B);
			break;
		case 25103:
			c.startAnimation(0x46A);
			break;
		case 25106:
			c.startAnimation(0x469);
			break;
		case 2154:
			c.startAnimation(0x468);
			break;
		case 52071:
			c.startAnimation(0x84F);
			break;
		case 52072:
			c.startAnimation(0x850);
			break;
		case 59062:
			c.startAnimation(4280);
			break;
		case 72032:
			c.startAnimation(3544);
			break;
		case 72033:
			c.startAnimation(4278);
			break;
		case 72254:
			c.startAnimation(4275);
			break;
		case 72255:
			c.startAnimation(6111);
			c.stopMovement();
			break;
		case 88058:
			c.startAnimation(7531);
			c.stopMovement();
			break;
		case 88062:
			c.startAnimation(10530);
			c.stopMovement();
			c.gfx0(1864);
			break;
		case 88063:
			c.startAnimation(11044);
			c.stopMovement();
			c.gfx0(1973);
			break;
		case 88060:
			c.startAnimation(8770);
			c.stopMovement();
			c.gfx0(1553);
			break;
		case 88061:
			c.startAnimation(9990);
			c.stopMovement();
			c.gfx0(1734);
			break;
		case 73004:
			c.startAnimation(7272);
			c.stopMovement();
			c.gfx0(1244);
			break;
		case 88059:
			if (System.currentTimeMillis() - c.logoutDelay < 8000) {
				c.sendMessage("You cannot do this emote in combat!");
				return;
			}
			c.startAnimation(2414);
			c.stopMovement();
			c.gfx0(1537);
			break;
		case 88064: // turkey
			c.startAnimation(10994);
			break;
		case 73003:
			c.startAnimation(2836);
			c.stopMovement();
			break;
		case 73000:
			if (System.currentTimeMillis() - c.logoutDelay < 8000) {
				c.sendMessage("You cannot do skillcape emotes in combat!");
				return;
			}
			c.startAnimation(3543);
			c.stopMovement();
			break;
		case 73001:
			c.startAnimation(3544);
			c.stopMovement();
			break;
		case 88066:
			c.startAnimation(12658);
			c.gfx0(780);
			c.stopMovement();
			break;
		case 88065:
			c.startAnimation(11542);
			c.gfx0(2037);
			c.stopMovement();
			break;
		/* END OF EMOTES */
		/* END OF EMOTES */
		case 28166:

			break;
		case 118098:
			c.getPA().castVeng();
			break;

		case 47130:
			c.forcedText = "[QC] My Slayer level is  "
					+ c.getPA().getLevelForXP(c.playerXP[18])
					+ " and I must slay another " + c.taskAmount + " "
					+ Server.npcHandler.getNpcListName(c.slayerTask);
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;

		case 27211:
			c.forcedText = "[QC] My Hunter level is  "
					+ c.getPA().getLevelForXP(c.playerXP[21]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27190:
			c.forcedText = "[QC] My Attack level is  "
					+ c.getPA().getLevelForXP(c.playerXP[0]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27193:
			c.forcedText = "[QC] My Strength level is  "
					+ c.getPA().getLevelForXP(c.playerXP[2]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27196:
			c.forcedText = "[QC] My Defence level is  "
					+ c.getPA().getLevelForXP(c.playerXP[1]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27191:
			c.forcedText = "[QC] My Hitpoints level is  "
					+ c.getPA().getLevelForXP(c.playerXP[3]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27199:
			c.forcedText = "[QC] My Range level is  "
					+ c.getPA().getLevelForXP(c.playerXP[4]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27202:
			c.forcedText = "[QC] My Prayer level is  "
					+ c.getPA().getLevelForXP(c.playerXP[5]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27205:
			c.forcedText = "[QC] My Magic level is  "
					+ c.getPA().getLevelForXP(c.playerXP[6]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27201:
			c.forcedText = "[QC] My Cooking level is  "
					+ c.getPA().getLevelForXP(c.playerXP[7]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27207:
			c.forcedText = "[QC] My Woodcutting level is  "
					+ c.getPA().getLevelForXP(c.playerXP[8]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27206:
			c.forcedText = "[QC] My Fletching level is  "
					+ c.getPA().getLevelForXP(c.playerXP[9]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27198:
			c.forcedText = "[QC] My Fishing level is  "
					+ c.getPA().getLevelForXP(c.playerXP[10]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27204:
			c.forcedText = "[QC] My Firemaking level is  "
					+ c.getPA().getLevelForXP(c.playerXP[11]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27203:
			c.forcedText = "[QC] My Crafting level is  "
					+ c.getPA().getLevelForXP(c.playerXP[12]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27195:
			c.forcedText = "[QC] My Smithing level is  "
					+ c.getPA().getLevelForXP(c.playerXP[13]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27192:
			c.forcedText = "[QC] My Mining level is  "
					+ c.getPA().getLevelForXP(c.playerXP[14]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27197:
			c.forcedText = "[QC] My Herblore level is  "
					+ c.getPA().getLevelForXP(c.playerXP[15]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27194:
			c.forcedText = "[QC] My Agility level is  "
					+ c.getPA().getLevelForXP(c.playerXP[16]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27200:
			c.forcedText = "[QC] My Thieving level is  "
					+ c.getPA().getLevelForXP(c.playerXP[17]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27210:
			c.forcedText = "[QC] My Farming level is  "
					+ c.getPA().getLevelForXP(c.playerXP[19]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 27208:
			c.forcedText = "[QC] My Runecrafting level is  "
					+ c.getPA().getLevelForXP(c.playerXP[20]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 74240:
			c.forcedText = "[QC] My Summoning level is  "
					+ c.getPA().getLevelForXP(c.playerXP[21]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 74239:
			c.forcedText = "[QC] My Hunter level is  "
					+ c.getPA().getLevelForXP(c.playerXP[22]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 74241:
			c.forcedText = "[QC] My Dungeoneering level is  "
					+ c.getPA().getLevelForXP(c.playerXP[24]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 74328:
			c.forcedText = "[QC] My Construction level is  "
					+ c.getPA().getLevelForXP(c.playerXP[23]) + ".";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			break;
		case 77036:
			if (c != null) {
				if (c.hasFollower > 0) {
					for (int i = 0; i < 29; i += 1) {
						if (c.storeditems[i] > 0 && c != null
								&& NPCHandler.npcs[c.summoningnpcid] != null) {
							Server.itemHandler.createGroundItem(c,
									c.storeditems[i],
									NPCHandler.npcs[c.summoningnpcid].absX,
									NPCHandler.npcs[c.summoningnpcid].absY, 1,
									c.playerId);
						}
						c.SaveGame();
						c.firstslot();
						c.storeditems[i] = -1;
						c.occupied[i] = false;
						c.yak = false;
						c.hasFollower = -1;
						c.totalstored = 0;
						c.summoningnpcid = 0;
						c.summoningslot = 0;
						c.yak = false;
						c.getPA().sendFrame126(
								"   " + c.playerLevel[21] + "/"
										+ c.getLevelForXP(c.playerXP[21]),
								17025);
						c.getPA().sendFrame126(
								"" + c.summonTime / 120 + ".00 Min", 17021);
						boolean sent = false;
						if (!sent) {
							c.sendMessage("Your BoB's items have dropped to the floor.");
							c.SaveGame();
							sent = true;
						}

					}
				} else {
					c.sendMessage("You do not have a summoned NPC at the moment.");
				}
			}
		case 55095:
			if (c.destroy == 1) {
				c.getPA().destroyBind(c.droppedItem);
				c.droppedItem = -1;
				c.destroy = 0;
			} else if (c.destroy == 2) {
				// c.getPA().dropItem(c.droppedItem);
				c.droppedItem = -1;
				c.destroy = 0;
				break;
			}
			break;

		case 55096:
			c.getPA().removeAllWindows();
			c.droppedItem = -1;
			c.destroy = 0;
			break;
		case 66156:
			if (!c.InDung) {
				c.getPA().closeAllWindows();
				c.sendMessage("Please re-enter Dungeoneering.");
				return;
			}
			if (c.hasChoosenDung == true) {
				return;
			}
			if (c.playerLevel[6] <= 9) {
				c.sendMessage("You must be 10+ Magic To Choose Magic Class");
				return;
			}
			c.getItems().addItem(19893, 1);
			c.getItems().addItem(19892, 1);
			c.getItems().addItem(15786, 1);
			c.getItems().addItem(15797, 1);
			c.getItems().addItem(15837, 1);
			c.getItems().addItem(15892, 1);
			c.getItems().addItem(16185, 1);
			c.getItems().addItem(16153, 1);
			c.getItems().addItem(391, 3);
			c.getItems().addItem(554, 10000);
			c.getItems().addItem(555, 10000);
			c.getItems().addItem(556, 10000);
			c.getItems().addItem(557, 10000);
			c.getItems().addItem(558, 10000);
			c.getItems().addItem(559, 10000);
			c.getItems().addItem(560, 10000);
			c.needstorelog = true;
			c.getItems().addItem(561, 10000);
			c.getItems().addItem(562, 10000);
			c.getItems().addItem(563, 10000);
			c.getItems().addItem(565, 10000);
			c.getItems().addItem(564, 10000);
			c.getItems().addItem(566, 10000);
			c.getItems().addItem(2434, 1);
			c.getItems().addItem(3040, 1);
			c.playerMagicBook = 1;
			c.setSidebarInterface(6, 12855);
			c.getPA().closeAllWindows();
			c.isSkulled = true;
			c.dungn = 0;
			// c.gwdelay = 1000;
			c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415> Magic Equipment chosen.");
			c.stopPlayerSkill = false;
			c.isChoosingDung = false;
			c.hasChoosenDung = true;
			break;
		case 66157:
			if (!c.InDung) {
				c.getPA().closeAllWindows();
				c.sendMessage("Please re-enter Dungeoneering.");
				return;
			}
			if (c.hasChoosenDung == true) {
				return;
			}
			c.getItems().addItem(15808, 1);
			c.getItems().addItem(15914, 1);
			c.getItems().addItem(15925, 1);
			c.getItems().addItem(15936, 1);
			c.getItems().addItem(16013, 1);
			c.getItems().addItem(16035, 1);
			c.getItems().addItem(16127, 1);
			c.getItems().addItem(16262, 1);
			c.getItems().addItem(19893, 1);
			c.getItems().addItem(19892, 1);
			c.getItems().addItem(391, 3);
			c.getItems().addItem(2434, 1);
			c.getItems().addItem(2440, 1);
			c.getItems().addItem(2442, 1);
			c.getItems().addItem(2436, 1);
			c.getItems().addItem(2503, 1);
			c.getItems().addItem(2497, 1);
			c.getItems().addItem(4587, 1);
			// c.getItems().addItem(18349, 1);
			c.getPA().closeAllWindows();
			c.needstorelog = true;
			c.isSkulled = true;
			c.dungn = 0;
			// c.gwdelay = 1000;
			c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415> Melee Equipment chosen.");
			c.stopPlayerSkill = false;
			c.isChoosingDung = false;
			c.hasChoosenDung = true;
			// }
			// }
			break;

		case 66158:
			if (!c.InDung) {
				c.getPA().closeAllWindows();
				c.sendMessage("Please re-enter Dungeoneering.");
				return;
			}
			if (c.hasChoosenDung == true) {
				return;
			}
			if (c.playerLevel[4] <= 9) {
				c.sendMessage("You must be 10+ Ranged To Choose Ranged Class");
				return;
			}
			c.getItems().addItem(16002, 1);
			c.getItems().addItem(16046, 1);
			c.needstorelog = true;
			c.getItems().addItem(16057, 1);
			c.getItems().addItem(16068, 1);
			c.getItems().addItem(16105, 1);
			c.getItems().addItem(19893, 1);
			c.getItems().addItem(19892, 1);
			c.getItems().addItem(861, 1);
			c.getItems().addItem(892, 10000);
			c.getItems().addItem(397, 3);
			c.getItems().addItem(3024, 1);
			c.getItems().addItem(2444, 1);
			c.getItems().addItem(2503, 1);
			c.getItems().addItem(2497, 1);
			c.dungn = 0;
			c.isSkulled = true;
			// c.gwdelay = 1000;
			c.getPA().closeAllWindows();
			c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415> Ranged Equipment chosen.");
			c.stopPlayerSkill = false;
			c.isChoosingDung = false;
			c.hasChoosenDung = true;
			break;
		// Dungeoneering finish
		case 177190:
			// c.getPA().showInterface(14040);
			// godwars
			// c.sendMessage("If there is no floor/maps are gone RESTART CLIENT!");
			// c.getPA().spellTeleport(2882, 5310, 2);
			// c.getPA().spellTeleport(2909, 3613, 0);
			// //c.getPA().sendMp3("Music");
			c.getDH().sendDialogues(5555, 1);
			// c.sendMessage("Nex currently disabled / will be back soon!");
			break;
		case 177206:
			c.getPA().spellTeleport(3007, 3849, 0);
			break;
		case 177209:
			// c.getPA().spellTeleport(2488, 10148, 0);
			c.sendMessage("Kings currently disabled / will be back soon!");
			break;
		case 177212:
			c.getPA().spellTeleport(2835, 9562, 0);
			break;
		case 177221:
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 176177:
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 178065:
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 178034:
			c.getPA().spellTeleport(2539, 4716, 0);
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 178050:
			c.getPA().spellTeleport(3243, 3517, 0);
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 178053:
			c.getPA().spellTeleport(3158, 3667, 0);
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 178056:
			c.getPA().spellTeleport(3086, 3487, 0);
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 178059:
			c.getPA().spellTeleport(3344, 3667, 0);
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 176162:
			c.getPA().spellTeleport(3565, 3314, 0);
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 176168:
			c.getPA().spellTeleport(2438, 5172, 0);
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 176146:
			c.sendMessage("DUELING IS AT YOUR OWN RISK!! ANY SCAMS/BUGS OCCURED ARE NOT REFUNDED! - Shit happens!");
			c.getPA().startTeleport(3358, 3269, 0, "modern");
			break;
		case 176165:
			c.getPA().spellTeleport(2662, 2650, 0);
			break;
		case 176171:
			c.getPA().spellTeleport(2865, 3546, 0);
			// c.sendMessage("Warriors Guild Will be released in V4.");
			break;
		case 176246:
			c.getPA().spellTeleport(2676, 3715, 0);
			break;

		// NEW MONSTER TELEPORT TAB

		case 45132: // crabs
			c.getDH().sendOption2("Yak's (Neitznot)", "Rock Crabs");
			c.dialogueAction = 222;
			// c.getPA().spellTeleport(2676, 3715, 0);
			break;

		case 45153: // stop viewing
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;

		case 45135: // taverly dung
			c.getPA().spellTeleport(2884, 9798, 0);
			break;

		case 45138: // slayer tower
			c.getPA().spellTeleport(3428, 3537, 0);
			break;

		case 45141: // brim haven dung
			c.getPA().spellTeleport(2710, 9466, 0);
			break;

		case 45144: // Hill giants
			c.getPA().spellTeleport(2705, 9442, 0);
			break;

		case 45147: // dark beasts
			c.getPA().spellTeleport(2908, 9696, 0);
			break;

		case 45150: // strykewyrms
			if ((c.playerLevel[22] < 90) && (c.playerLevel[16] < 90)) {
				c.sendMessage("You need 90 Agility And 90 Hunter to enter the Strykworm's Cave");
			} else {
				if ((c.playerLevel[22] > 89) && (c.playerLevel[16] < 90)) {
					c.sendMessage("You need 90 Hunter And Agility to enter the Strykworm's Cave");
				} else {
					if ((c.playerLevel[22] < 90) && (c.playerLevel[16] > 89)) {
						c.sendMessage("You need 90 Hunter And Agility to enter the Strykworm's Cave");
					} else {
						if ((c.playerLevel[22] > 89)
								&& (c.playerLevel[16] > 89)) {
							c.getPA().startTeleport(2515, 4632, 0, "modern");
						}
					}
				}
			}
			break;
		// END OF NEW MONSTER TELEPORT TAB

		case 177006:
			c.getPA().spellTeleport(2884, 9798, 0);
			break;
		case 177009:
			c.getPA().spellTeleport(2710, 9466, 0);
			break;
		case 177012:
			c.getPA().spellTeleport(3428, 3527, 0);
			break;
		case 177015:
			c.getPA().spellTeleport(3117, 9847, 0);
			break;
		case 177021:
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			break;
		case 177215:
			c.getPA().spellTeleport(3302, 9372, 0);
			break;

		case 69009:
			if (c.playerMagicBook == 0) {
				c.setSidebarInterface(6, 1151); // modern
			} else if (c.playerMagicBook == 1) {
				c.setSidebarInterface(6, 12855); // ancient
			} else {
				c.setSidebarInterface(6, 29999);
			}
			break;

		case 24017:
			c.getPA().resetAutocast();
			c.getItems()
					.sendWeapon(
							c.playerEquipment[Player.playerWeapon],
							ItemAssistant
									.getItemName(c.playerEquipment[Player.playerWeapon]));
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}
}
