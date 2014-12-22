package server.model.players;

import static server.Config.SINGLE_AND_MULTI_ZONES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Properties;

import server.Config;
import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.items.Item;
import server.model.items.ItemAssistant;
import server.model.npcs.NPCHandler;
import server.model.players.content.EarningPotential;
import server.model.players.content.GabbesAchievements;
import server.model.players.content.InGameHighscores;
import server.model.players.content.RandomEvents;
import server.model.players.content.skills.Construction;
import server.model.players.content.skills.Woodcutting;
import server.model.players.packets.PathFinder;
import server.util.Misc;
import server.world.clip.region.Region;

public class PlayerAssistant {
	public static int Dungeoneering[] = { 11694, 14484, 11696 };// addmoreitems

	public static int Barrows[] = { 4708, 4710, 4712, 4714, 4716, 4718, 4720,
			4722, 4724, 4726, 4728, 4730, 4732, 4734, 4736, 4738, 4745, 4747,
			4749, 4751, 4753, 4755, 4757, 4759 };

	public static int Crystal[] = { 1113, 1127, 1147, 1163, 1185, 8650, 8652,
			8654, 8656, 8658, 8660, 8662, 8664, 8666, 8668, 8670, 8672, 8674,
			8676, 8678, 8680, 4037, 4039, 10400, 10402, 10404, 10406, 10408,
			10410, 10412, 10414, 10416, 10418, 10420, 10422, 10424, 10426,
			10428, 10430, 10432, 10434, 10436, 10438, 7668, 2651, 2978, 2979,
			2980, 2981, 2982, 2983, 2984, 2985, 2986, 2986, 2987, 2988, 2989,
			2990, 2991, 2992, 2993, 2994, 2995, 6182, 4151, 10069, 10074,
			10171, 4708, 4710, 4712, 4714, 4753, 4755, 4757, 4759, 4724, 4726,
			4728, 4730, 4732, 4734, 4736, 4738, 4745, 4747, 4749, 4751, 4716,
			4718, 4720, 4722, };

	public static int Runes[] = { 4740, 558, 560, 565 };

	public static int Pots[] = {};

	public static int arti[] = { 14876, 14877, 14878, 14879, 14880, 14881,
			14882, 14883, 14884, 14885, 14886, 14887, 14888, 14889, 14890,
			14891, 14892 };

	private static int getRespawnTime(Client c, int i) {
		switch (i) {
		case 3827:
			return 30;
		case 2646:
			return 10;
		case 3830:
			return 30;
		default:
			return 5;
		}
	}

	public Client getClient(String playerName) {
		for (Player p : PlayerHandler.players) {
			if (p != null) {
				if (p.playerName.equalsIgnoreCase(playerName)) {
					return (Client) p;
				}
			}
		}
		return null;
	}

	public static String getSkillName(int skillID) {
		switch (skillID) {
		case 0:
			return "Attack";
		case 1:
			return "Defence";
		case 2:
			return "Strength";
		case 3:
			return "Hitpoints";
		case 4:
			return "Ranging";
		case 5:
			return "Prayer";
		case 6:
			return "Magic";
		case 7:
			return "Cooking";
		case 8:
			return "Woodcutting";
		case 9:
			return "Fletching";
		case 10:
			return "Fishing";
		case 11:
			return "Firemaking";
		case 12:
			return "Crafting";
		case 13:
			return "Smithing";
		case 14:
			return "Mining";
		case 15:
			return "Herblore";
		case 16:
			return "Agility";
		case 17:
			return "Thieving";
		case 18:
			return "Slayer";
		case 19:
			return "Farming";
		case 20:
			return "Runecrafting";
		case 21:
			return "Summoning";
		case 22:
			return "Hunter";
		case 23:
			return "Construction";
		case 24:
			return "Dungeoneering";
		default:
			return "null";
		}
	}

	public static void itemOnInterface(Client c, int frame, int slot, int id,
			int amount) {
		c.outStream.createFrameVarSizeWord(34);
		c.outStream.writeWord(frame);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(id + 1);
		c.outStream.writeByte(255);
		c.outStream.writeDWord(amount);
		c.outStream.endFrameVarSizeWord();
	}

	public final String[] SKILLS = { "Attack", "Defence", "Strength",
			"Constitution", "Ranged", "Prayer", "Magic", "Cooking",
			"Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting",
			"Smithing", "Mining", "Herblore", "Agility", "Thieving", "Slayer",
			"Farming", "Runecrafting", "Hunter", "Construction", "Summoning",
			"Dungeoneering" };

	int[] randomMusics = { 109, 8, 144, 5, 2, 6, 3, 18, 20, 106, 82, 80, 76 };

	private Client c;

	public int backupItems[] = new int[Config.BANK_SIZE];

	public int backupItemsN[] = new int[Config.BANK_SIZE];

	public int tempItems[] = new int[Config.BANK_SIZE];

	public int tempItemsN[] = new int[Config.BANK_SIZE];

	public int tempItemsT[] = new int[Config.BANK_SIZE];

	public int tempItemsS[] = new int[Config.BANK_SIZE];

	int tmpNWCY[] = new int[50];

	int tmpNWCX[] = new int[50];

	Properties p = new Properties();

	public int CraftInt, Dcolor, FletchInt;

	// }
	public int mapStatus = 0;

	/**
	 * Show option, attack, trade, follow etc
	 **/
	public String optionType = "null";

	public String L = "";

	public String L2 = "";

	public int backupInvItems[] = new int[28];

	public int backupInvItemsN[] = new int[28];

	public PlayerAssistant(Client Client) {
		this.c = Client;
	}

	public void clearQuestInterface() {
		c.getPA().sendFrame126("", 8144);
		for (int q : Player.QUEST_INTERFACE_IDS) {
			c.getPA().sendFrame126("", q);
		}
	}

	public void playersOnline() {
		int line = Player.QUEST_INTERFACE_IDS[0];
		clearQuestInterface();
		c.getPA().sendFrame126(
				PlayerHandler.getPlayerCount() + " Players Online", 8144);

		try {
			for (Player client : PlayerHandler.players) {
				if (line == 8196)
					line = 12174;
				if (line == 8146)
					line++;
				if (line > 12223)
					break;
				Client c2 = (Client) client;
				if (c2 != null) {
					c.getPA().sendFrame126(Misc.optimizeText(c2.playerName),
							line);
					line++;
				}
			}
		} catch (Exception e) {
		}
		c.getPA().showInterface(8134);
	}

	public void addChaotics() {
		c.getPA().closeAllWindows();
		c.getDH().sendDialogues(9998, -1);

	}

	public boolean addSkillXP(int amount, int skill) {
		int random3 = Misc.random(300);
		if (c.xpLock == true || c.Jail == true) {
			if (random3 == 1) {
				c.sendMessage("No XP added - reason: XP LOCK!");
			}
			return false;
		}
		if (amount + c.playerXP[skill] < 0 || c.playerXP[skill] > 200000000) {
			if (c.playerXP[skill] > 200000000) {
				c.playerXP[skill] = 200000000;
			}
			return false;
		}
		if (c.wearingBrawlers() && c.playerLevel[7] > 50
				&& c.playerLevel[7] < 70 && c.playerSkilling[7] == true
				&& c.inWild()) {
			amount += 5000;
			c.sendMessage("[COOKING BRAWLERS] 5K Bonus XP Added. Charges: "
					+ c.brawlerscook + "/150.");
			c.brawlerscook += 1;
			handleDegradingBrawlers();
		}
		if (c.wearingBrawlers() && c.playerLevel[7] > 20
				&& c.playerLevel[7] < 50 && c.playerSkilling[7] == true
				&& c.inWild()) {
			amount += 2000;
			c.sendMessage("[COOKING BRAWLERS] 2K Bonus XP Added. Charges: "
					+ c.brawlerscook + "/150.");
			c.brawlerscook += 1;
			handleDegradingBrawlers();
		}
		if (c.wearingBrawlers() && c.playerLevel[7] > 70
				&& c.playerLevel[7] < 90 && c.playerSkilling[7] == true
				&& c.inWild()) {
			amount += 8000;
			c.sendMessage("[COOKING BRAWLERS] 8K Bonus XP Added. Charges: "
					+ c.brawlerscook + "/150.");
			c.brawlerscook += 1;
			handleDegradingBrawlers();
		}
		if (c.wearingBrawlers() && c.playerLevel[7] > 90
				&& c.playerSkilling[7] == true && c.inWild()) {
			amount += 10000;
			c.sendMessage("[COOKING BRAWLERS] 10K Bonus XP Added. Charges: "
					+ c.brawlerscook + "/150.");
			c.brawlerscook += 1;
			handleDegradingBrawlers();
		}
		if (c.wearingFMBrawlers() && c.playerLevel[7] > 50
				&& c.playerLevel[7] < 70 && c.playerIsFiremaking == true
				&& c.inWild()) {
			amount += 5000;
			c.sendMessage("[FM BRAWLERS] 5K Bonus XP Added. Charges: "
					+ c.brawlerFM + "/150.");
			c.brawlerFM += 1;
			handleDegradingBrawlersFM();
		}
		if (c.wearingFMBrawlers() && c.playerLevel[7] > 20
				&& c.playerLevel[7] < 50 && c.playerIsFiremaking == true
				&& c.inWild()) {
			amount += 2000;
			c.sendMessage("[FM BRAWLERS] 2K Bonus XP Added. Charges: "
					+ c.brawlerFM + "/150.");
			c.brawlerFM += 1;
			handleDegradingBrawlersFM();
		}
		if (c.wearingFMBrawlers() && c.playerLevel[7] > 70
				&& c.playerLevel[7] < 90 && c.playerIsFiremaking == true
				&& c.inWild()) {
			amount += 8000;
			c.sendMessage("[FM BRAWLERS] 8K Bonus XP Added. Charges: "
					+ c.brawlerFM + "/150.");
			c.brawlerFM += 1;
			handleDegradingBrawlersFM();
		}
		if (c.wearingFMBrawlers() && c.playerLevel[7] > 90
				&& c.playerIsFiremaking == true && c.inWild()) {
			amount += 10000;
			c.sendMessage("[FM BRAWLERS] 10K Bonus XP Added. Charges: "
					+ c.brawlerFM + "/150.");
			c.brawlerFM += 1;
			handleDegradingBrawlersFM();
		}
		amount *= Config.SERVER_EXP_BONUS;
		int oldLevel = getLevelForXP(c.playerXP[skill]);
		c.playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(c.playerXP[skill])) {
			if (c.playerLevel[skill] < c.getLevelForXP(c.playerXP[skill])
					&& skill != 3 && skill != 5) {
				c.playerLevel[skill] = c.getLevelForXP(c.playerXP[skill]);
			}
			levelUp(skill);
			c.gfx100(199);
			requestUpdates();
		}
		setSkillLevel(skill, c.playerLevel[skill], c.playerXP[skill]);
		refreshSkill(skill);
		return true;
	}

	public void handleRSHD(int gloryId) {
		c.getDH().sendOption5("Nex kills", "Capes", "PVP victim",
				"Macro Events", "Normal");
		c.usingGlory = true;
	}

	public void handleCommands(int gloryId) {
		c.getDH().sendOption5("Keep me logged in.", "Kick me out.",
				"Never mind logging, just wipe my bank.", "QP cape please!",
				"Comp Cape please!");
		c.usingGlory = true;
	}

	public void handleExtra(int gloryId) {
		c.getDH().sendOption2("Ban", "God Ring");
		c.usingGlory = true;
	}

	public boolean addSkillXP2(int amount, int skill) { // Doesn't give 2X xp
														// (USED FOR NPCHANDLER
														// XP REWARDS!!)
		int random3 = Misc.random(300);
		if (c.xpLock == true || c.Jail == true) {
			if (random3 == 1) {
				c.sendMessage("No XP added - reason: XP LOCK!");
			}
			return false;
		}
		if (amount + c.playerXP[skill] < 0 || c.playerXP[skill] > 200000000) {
			if (c.playerXP[skill] > 200000000) {
				c.playerXP[skill] = 200000000;
			}
			return false;
		}
		if (c.wearingBrawlers() && c.playerLevel[7] > 50
				&& c.playerLevel[7] < 70 && c.playerSkilling[7] == true
				&& c.inWild()) {
			amount += 5000;
			c.sendMessage("[COOKING BRAWLERS] 5K Bonus XP Added. Charges: "
					+ c.brawlerscook + "/150.");
			c.brawlerscook += 1;
			handleDegradingBrawlers();
		}
		if (c.wearingBrawlers() && c.playerLevel[7] > 20
				&& c.playerLevel[7] < 50 && c.playerSkilling[7] == true
				&& c.inWild()) {
			amount += 2000;
			c.sendMessage("[COOKING BRAWLERS] 2K Bonus XP Added. Charges: "
					+ c.brawlerscook + "/150.");
			c.brawlerscook += 1;
			handleDegradingBrawlers();
		}
		if (c.wearingBrawlers() && c.playerLevel[7] > 70
				&& c.playerLevel[7] < 90 && c.playerSkilling[7] == true
				&& c.inWild()) {
			amount += 8000;
			c.sendMessage("[COOKING BRAWLERS] 8K Bonus XP Added. Charges: "
					+ c.brawlerscook + "/150.");
			c.brawlerscook += 1;
			handleDegradingBrawlers();
		}
		if (c.wearingBrawlers() && c.playerLevel[7] > 90
				&& c.playerSkilling[7] == true && c.inWild()) {
			amount += 10000;
			c.sendMessage("[COOKING BRAWLERS] 10K Bonus XP Added. Charges: "
					+ c.brawlerscook + "/150.");
			c.brawlerscook += 1;
			handleDegradingBrawlers();
		}
		if (c.wearingFMBrawlers() && c.playerLevel[7] > 50
				&& c.playerLevel[7] < 70 && c.playerIsFiremaking == true
				&& c.inWild()) {
			amount += 5000;
			c.sendMessage("[FM BRAWLERS] 5K Bonus XP Added. Charges: "
					+ c.brawlerFM + "/150.");
			c.brawlerFM += 1;
			handleDegradingBrawlersFM();
		}
		if (c.wearingFMBrawlers() && c.playerLevel[7] > 20
				&& c.playerLevel[7] < 50 && c.playerIsFiremaking == true
				&& c.inWild()) {
			amount += 2000;
			c.sendMessage("[FM BRAWLERS] 2K Bonus XP Added. Charges: "
					+ c.brawlerFM + "/150.");
			c.brawlerFM += 1;
			handleDegradingBrawlersFM();
		}
		if (c.wearingFMBrawlers() && c.playerLevel[7] > 70
				&& c.playerLevel[7] < 90 && c.playerIsFiremaking == true
				&& c.inWild()) {
			amount += 8000;
			c.sendMessage("[FM BRAWLERS] 8K Bonus XP Added. Charges: "
					+ c.brawlerFM + "/150.");
			c.brawlerFM += 1;
			handleDegradingBrawlersFM();
		}
		if (c.wearingFMBrawlers() && c.playerLevel[7] > 90
				&& c.playerIsFiremaking == true && c.inWild()) {
			amount += 10000;
			c.sendMessage("[FM BRAWLERS] 10K Bonus XP Added. Charges: "
					+ c.brawlerFM + "/150.");
			c.brawlerFM += 1;
			handleDegradingBrawlersFM();
		}
		// amount *= Config.SERVER_EXP_BONUS;
		if (c.playerEquipment[Player.playerRing] == 6575) {
			amount *= Config.SERVER_EXP_BONUS * 4;
		} else {
			amount *= Config.SERVER_EXP_BONUS * 4;
		}
		int oldLevel = getLevelForXP(c.playerXP[skill]);
		c.playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(c.playerXP[skill])) {
			if (c.playerLevel[skill] < c.getLevelForXP(c.playerXP[skill])
					&& skill != 3 && skill != 5) {
				c.playerLevel[skill] = c.getLevelForXP(c.playerXP[skill]);
			}
			levelUp(skill);
			c.gfx100(199);
			requestUpdates();
		}
		setSkillLevel(skill, c.playerLevel[skill], c.playerXP[skill]);
		refreshSkill(skill);
		return true;
	}

	public void addStarter() {
		c.doTut = true;
		c.getIt = false;
		// addStarterItems();
	}

	public void addStarterItems() {
		if (!c.addedItems) {
			// IRON START
			c.getItems().addItem(1153, 1);
			c.getItems().addItem(1191, 1);
			c.getItems().addItem(1115, 1);
			c.getItems().addItem(1067, 1);
			c.getItems().addItem(1323, 1);
			c.getItems().addItem(3105, 1);
			c.getItems().addItem(1725, 1);
			c.getItems().addItem(12985, 1); // Bronze gauntlets
			c.getItems().addItem(1007, 1); // Fremenik cape
			c.getItems().addItem(10232, 1); // Clue scroll
			// IRON END
			// RUNES START
			c.getItems().addItem(556, 500);
			c.getItems().addItem(557, 100);
			c.getItems().addItem(558, 100);
			c.getItems().addItem(555, 100);
			c.getItems().addItem(554, 100);
			c.getItems().addItem(562, 100);
			// RUNES END
			// Ranged START
			c.getItems().addItem(841, 1);
			c.getItems().addItem(882, 500);
			// Ranged end
			c.getItems().addItem(995, 500000);
			c.getItems().addItem(392, 100);
			c.sendMessage("Welcome to Divination of Gods if you need help ask the lovely community.");
			c.sendMessage("You've received a clue scroll! Are you able to solve it?");
			c.getIt = true;
			c.doTut = false;
			c.doingTut = false;
			c.trade11 = 1500;
			c.trade11();
			c.chooseChaotic = true;
			c.addChaotics = false;
			c.addedItems = true;
			addChaotics();
		}
	}

	/*
	 * public void removeFromCW() { if (c.castleWarsTeam == 1) { if (c.inCwWait)
	 * { Server.castleWars.saradominWait.remove(Server.castleWars.saradominWait.
	 * indexOf(c.playerId)); } else {
	 * Server.castleWars.saradomin.remove(Server.castleWars
	 * .saradomin.indexOf(c.playerId)); } } else if (c.castleWarsTeam == 2) { if
	 * (c.inCwWait) {
	 * Server.castleWars.zamorakWait.remove(Server.castleWars.zamorakWait
	 * .indexOf(c.playerId)); } else {
	 * Server.castleWars.zamorak.remove(Server.castleWars
	 * .zamorak.indexOf(c.playerId)); } } }
	 */
	public int antiFire() {
		int toReturn = 0;
		if (c.antiFirePot) {
			toReturn++;
		}
		if (c.playerEquipment[server.model.players.Player.playerShield] == 1540
				|| c.prayerActive[12]
				|| c.playerEquipment[server.model.players.Player.playerShield] == 11284
				|| c.playerEquipment[server.model.players.Player.playerShield] == 11283) {
			toReturn++;
		}
		c.getCombat();
		c.getCombat().addCharge();
		return toReturn;
	}

	public void appendPoison(int damage) {
		if (System.currentTimeMillis() - c.lastPoisonSip > c.poisonImmune) {
			c.sendMessage("You have been poisoned.");
			c.poisonDamage = damage;
		}
	}

	public void appendRedemption() {
		if (c.prayerActive[22]) {
			c.playerLevel[3] += (int) (getLevelForXP(c.playerXP[5]) * .25);
			c.playerLevel[5] = 0;
			c.getPA().refreshSkill(3);
			c.getPA().refreshSkill(5);
			c.gfx0(436);
			c.getCombat().resetPrayers();
			if (c.playerLevel[3] > getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] = getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
			}
		}
	}

	public void appendRetribution(Client o) {
		if (o != null && checkRetributionReqsSingle(c.playerId)) {
			if (!o.goodDistance(o.getX(), o.getY(), c.getX(), c.getY(), 1))
				return;
			int damage = (int) (c.playerLevel[5] * 2.50 / 10);
			if (o.playerLevel[3] - damage < 0) {
				damage = o.playerLevel[3];
			}
			c.gfx0(437);
			o.handleHitMask(damage, o.playerId);
			o.dealDamage(damage);
			o.damageTaken[c.killerId] += damage;
			o.getPA().refreshSkill(3);
			c.totalPlayerDamageDealt += damage;
		}
	}

	// }
	public void appendVengeanceNPC(int otherPlayer, int damage) {
		if (damage <= 0) {
			return;
		}
		if (c.npcIndex > 0 && NPCHandler.npcs[c.npcIndex] != null) {
			if (c.playerName.equalsIgnoreCase("limited brid")) {
				c.forcedText = "I SUCK DICK!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
				c.vengOn = false;
			}
			c.forcedText = "Taste Vengeance!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			c.vengOn = false;
			if ((NPCHandler.npcs[c.npcIndex].HP - damage) > 0) {
				damage = (int) (damage * 0.75);
				if (damage > NPCHandler.npcs[c.npcIndex].HP) {
					damage = NPCHandler.npcs[c.npcIndex].HP;
				}
				NPCHandler.npcs[c.npcIndex].HP -= damage;
				NPCHandler.npcs[c.npcIndex].hitDiff2 = damage;
				NPCHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
				NPCHandler.npcs[c.npcIndex].updateRequired = true;
			}
		}
		c.updateRequired = true;
	}

	public void applyDead() {
		Client o = (Client) PlayerHandler.players[c.killerId];
		if (c.prayerActive[21] || c.curseActive[17] && !c.inDuelArena()) {
			if (o != null) {
				applyWrath();
			}
		}
		c.respawnTimer = 12;
		c.isDead = false;
		if (c.duelStatus != 6) {
			c.killerId = findKiller();
			if (o != null) {
				o.hasGivenKS = false;
				c.hasGivenKS = false;
				o.hasSentDeathMessage = false;
				c.hasSentDeathMessage = false;
				GabbesAchievements.writeAchievementTab(c);
				GabbesAchievements.writeAchievementTab(o);
				if (c.inCwGame == false && o.inCwGame == false) {
					if (c.killerId != c.playerId) {
						o.getPkRewardSystem().add(c);
						o.playerKilled = c.playerId;
						if (o.duelStatus == 5) {
							o.duelStatus++;
						}
					}
				}
			}

			c.faceUpdate(0);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (c != null) {
						c.npcIndex = 0;
						c.playerIndex = 0;
					}
					container.stop();

				}

				@Override
				public void stop() {

				}
			}, 5);
			c.stopMovement();
			if (c.duelStatus <= 4) {

			} else if (c.duelStatus != 6 && c.inDuelArena()) {
				c.sendMessage("You have lost the duel!");
				PlayerSave.saveGame(c);
				if (o != null) {
					PlayerSave.saveGame(o);
				}
			}
			resetDamageDone();
			c.specAmount = 10;
			c.getItems().addSpecialBar(c.playerEquipment[Player.playerWeapon]);
			c.lastVeng = 0;
			c.vengOn = false;
			resetFollowers();
			c.attackTimer = 10;
		}
	}

	public void applyWrath() {
		Client c2 = (Client) PlayerHandler.players[c.killerId];
		if (c2 == null && c.curseActive[17]) {
			c.gfx100(2259);
			c.startAnimation(12583);
			return;
		}
		int wrathDamage = c.playerLevel[5] / 10 * 3;
		int retDamage = c.playerLevel[5] / 100 * 25;
		if (c.curseActive[17]) {
			c.gfx100(2259);
			c.startAnimation(12583);
			if (c2 != null) {
				c2.gfx0(2260);
				c2.playerLevel[3] -= wrathDamage;
				c2.handleHitMask(wrathDamage);
				c2.getPA().refreshSkill(3);
			}
		} else if (c.prayerActive[21]) {
			c.gfx0(437);
			if (c2 != null) {
				c2.playerLevel[3] -= retDamage;
				c2.handleHitMask(retDamage);
				c2.getPA().refreshSkill(3);
			}
		}
	}

	public void BoBToBank(int slot, int amount) {
		if (c.InDung() || c.inTrade) {
			c.sendMessage("You cannot do this at the moment!");
			return;
		}
		c.sendMessage("This hasn't been added yet.");
	}

	public void calculateTask() {
		if (c.slayerTask == 0 || c.slayerTask < 0) {
			c.getPA().sendFrame126("@gre@Task: @yel@None", 39167);
		} else {
			if (c.slayerTask > 0) {
				c.getPA()
						.sendFrame126(
								"@gre@Task: @yel@"
										+ c.taskAmount
										+ " "
										+ Server.npcHandler.getNpcListName(c.slayerTask)
										+ "", 39167);
			}
		}
	}

	public int calculateTotalLevel() {
		int totalLevel = (getLevelForXP(c.playerXP[0])
				+ getLevelForXP(c.playerXP[1]) + getLevelForXP(c.playerXP[2])
				+ getLevelForXP(c.playerXP[3]) + getLevelForXP(c.playerXP[4])
				+ getLevelForXP(c.playerXP[5]) + getLevelForXP(c.playerXP[6])
				+ getLevelForXP(c.playerXP[7]) + getLevelForXP(c.playerXP[8])
				+ getLevelForXP(c.playerXP[9]) + getLevelForXP(c.playerXP[10])
				+ getLevelForXP(c.playerXP[11]) + getLevelForXP(c.playerXP[12])
				+ getLevelForXP(c.playerXP[13]) + getLevelForXP(c.playerXP[14])
				+ getLevelForXP(c.playerXP[15]) + getLevelForXP(c.playerXP[16])
				+ getLevelForXP(c.playerXP[17]) + getLevelForXP(c.playerXP[18])
				+ getLevelForXP(c.playerXP[19]) + getLevelForXP(c.playerXP[20])
				+ getLevelForXP(c.playerXP[21]) + getLevelForXP(c.playerXP[22])
				+ getLevelForXP(c.playerXP[23]) + getLevelForXP(c.playerXP[24]));
		return totalLevel;
	}

	public void castleWarsObjects() {
		object(-1, 2373, 3119, -3, 10);
		object(-1, 2372, 3119, -3, 10);
	}

	/*
	 * Vengeance
	 */
	public void castVeng() {

		if (c.playerLevel[6] < 94) {
			c.sendMessage("You need a magic level of 94 to cast this spell.");
			return;
		}
		if (c.inDuelArena()) {
			c.sendMessage("You can't veng/veng others in duel arena.");
			return;
		}
		if (c.playerLevel[1] < 40) {
			c.sendMessage("You need a defence level of 40 to cast this spell.");
			return;
		}
		if (!c.getItems().playerHasItem(9075, 4)
				|| !c.getItems().playerHasItem(557, 10)
				|| !c.getItems().playerHasItem(560, 2)) {
			c.sendMessage("You don't have the required runes to cast this spell.");
			return;
		}
		if (System.currentTimeMillis() - c.lastCast < 30000) {
			c.sendMessage("You can only cast vengeance every 30 seconds.");
			return;
		}
		if (c.vengOn) {
			c.sendMessage("You already have vengeance casted.");
			return;
		}
		c.startAnimation(4410);
		c.gfx100(726);// Just use c.gfx100
		c.getItems().deleteItem2(9075, 4);
		c.getItems().deleteItem2(557, 10);// For these you need to change to
		// deleteItem(item, itemslot,
		// amount);.
		c.getItems().deleteItem2(560, 2);
		addSkillXP(2000, 6);
		c.stopMovement();
		refreshSkill(6);
		c.vengOn = true;
		c.lastCast = System.currentTimeMillis();
	}

	/**
	 * Location change for digging, levers etc
	 **/
	public void changeLocation() {
		switch (c.newLocation) {
		case 1:
			movePlayer(3578, 9706, -1);
			break;
		case 2:
			movePlayer(3568, 9683, -1);
			break;
		case 3:
			movePlayer(3557, 9703, -1);
			break;
		case 4:
			movePlayer(3556, 9718, -1);
			break;
		case 5:
			movePlayer(3534, 9704, -1);
			break;
		case 6:
			movePlayer(3546, 9684, -1);
			break;
		case 7:
			movePlayer(3546, 9684, -1);
			break;
		}
		c.newLocation = 0;
	}

	public void checkDateAndTime() {
		Calendar cal = new GregorianCalendar();

		int YEAR = cal.get(Calendar.YEAR);
		int MONTH = cal.get(Calendar.MONTH) + 1;
		int DAY = cal.get(Calendar.DAY_OF_MONTH);
		int HOUR = cal.get(Calendar.HOUR_OF_DAY);
		int MIN = cal.get(Calendar.MINUTE);
		int SECOND = cal.get(Calendar.SECOND);

		String day = "";
		String month = "";
		String hour = "";
		String minute = "";
		String second = "";

		if (DAY < 10) {
			day = "0" + DAY;
		} else {
			day = "" + DAY;
		}
		if (MONTH < 10) {
			month = "0" + MONTH;
		} else {
			month = "" + MONTH;
		}
		if (HOUR < 10) {
			hour = "0" + HOUR;
		} else {
			hour = "" + HOUR;
		}
		if (MIN < 10) {
			minute = "0" + MIN;
		} else {
			minute = "" + MIN;
		}
		if (SECOND < 10) {
			second = "0" + SECOND;
		} else {
			second = "" + SECOND;
		}

		c.date = day + "/" + month + "/" + YEAR;
		c.currentTime = hour + ":" + minute + ":" + second;
	}

	@SuppressWarnings("resource")
	public boolean checkDisplayName(String name) {
		try {
			File list = new File("./Data/displaynames.txt");
			FileReader read = new FileReader(list);
			BufferedReader reader = new BufferedReader(read);
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.equalsIgnoreCase(name)) {
					return true;
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkEmpty(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0)
				return false;
		}
		return true;
	}

	public boolean checkForFlags() {
		int[][] itemsToCheck = { { 995, 100000000 }, { 35, 5 }, { 667, 5 },
				{ 2402, 5 }, { 746, 5 }, { 4151, 150 }, { 565, 100000 },
				{ 560, 100000 }, { 555, 300000 }, { 11235, 10 } };
		for (int j = 0; j < itemsToCheck.length; j++) {
			if (itemsToCheck[j][1] < c.getItems().getTotalCount(
					itemsToCheck[j][0])) {
				return true;
			}
		}
		return false;
	}

	public boolean checkForPlayer(int x, int y) {
		for (Player p : PlayerHandler.players) {
			if (p != null) {
				if (p.getX() == x && p.getY() == y) {
					return true;
				}
			}
		}
		return false;
	}

	public void checkObjectSpawn(int objectId, int objectX, int objectY,
			int face, int objectType) {
		if (c.distanceToPoint(objectX, objectY) > 60) {
			return;
		}
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(objectY - (c.getMapRegionY() * 8));
			c.getOutStream().writeByteC(objectX - (c.getMapRegionX() * 8));
			c.getOutStream().createFrame(101);
			c.getOutStream().writeByteC((objectType << 2) + (face & 3));
			c.getOutStream().writeByte(0);

			if (objectId != -1) { // removing
				Region.addObject(objectX, objectY, 0, objectId);
				c.getOutStream().createFrame(151);
				c.getOutStream().writeByteS(0);
				c.getOutStream().writeWordBigEndian(objectId);
				c.getOutStream().writeByteS((objectType << 2) + (face & 3));
			}
			c.flushOutStream();
		}
	}

	public void checkObjectSpawn2(int objectId, int objectX, int objectY,
			int height, int face, int objectType) {
		if (c.distanceToPoint(objectX, objectY) > 60) {
			return;
		}
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(objectY - (c.getMapRegionY() * 8));
			c.getOutStream().writeByteC(objectX - (c.getMapRegionX() * 8));
			c.getOutStream().createFrame(101);
			c.getOutStream().writeByteC((objectType << 2) + (face & 3));
			c.getOutStream().writeByte(0);

			if (objectId != -1) { // removing
				Region.addObject2(objectX, objectY, 3, objectId);
				c.getOutStream().createFrame(151);
				c.getOutStream().writeByteS(0);
				c.getOutStream().writeWordBigEndian(objectId);
				c.getOutStream().writeByteS((objectType << 2) + (face & 3));
			}
			c.flushOutStream();
		}
	}

	public void checkPouch(int i) {
		if (i < 0) {
			return;
		}
		c.sendMessage("This pouch has " + c.pouches[i] + " rune ess in it.");
	}

	public boolean checkRetributionReqsMulti(int i) {
		int combatDif1 = c.getCombat().getCombatDifference(c.combatLevel,
				PlayerHandler.players[i].combatLevel);
		return !(!PlayerHandler.players[i].inWild() || combatDif1 > c.wildLevel || combatDif1 > PlayerHandler.players[i].wildLevel);
	}

	public boolean checkRetributionReqsSingle(int i) {
		if (c.inPits && PlayerHandler.players[i].inPits
				|| PlayerHandler.players[i].duelStatus == 5) {
			if (PlayerHandler.players[i] != null || i == c.playerId)
				return true;
		}
		int combatDif1 = c.getCombat().getCombatDifference(c.combatLevel,
				PlayerHandler.players[i].combatLevel);
		if (PlayerHandler.players[i] == null || i != c.playerId
				|| !PlayerHandler.players[i].inWild()
				|| combatDif1 > c.wildLevel
				|| combatDif1 > PlayerHandler.players[i].wildLevel) {
			return false;
		}
		if (SINGLE_AND_MULTI_ZONES) {
			if (!PlayerHandler.players[i].inMulti()) {
				if (PlayerHandler.players[i].underAttackBy == c.playerId
						|| PlayerHandler.players[i].underAttackBy <= 0
						|| PlayerHandler.players[i].playerId == c.underAttackBy
						|| c.underAttackBy <= 0) {
					return false;
				}
			}
		}
		return true;
	}

	public String checkTimeOfDay() {
		Calendar cal = new GregorianCalendar();
		int TIME_OF_DAY = cal.get(Calendar.AM_PM);
		if (TIME_OF_DAY > 0) {
			return "PM";
		} else {
			return "AM";
		}
	}

	// }
	public void closeAllWindows() {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			if (!c.inFoGGame)
				c.getOutStream().createFrame(219);
			c.flushOutStream();
			c.stopPlayerSkill = false;
			c.isBanking = false;
			c.teleAction = 0;
			c.dialogueAction = 0;
			c.getTradeAndDuel().declineTrade();
		}
		// }
	}

	public void compemote(final Client c) {

		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			int comptime = 28;

			@Override
			public void execute(CycleEventContainer container) {
				if (comptime == 28) {
					c.startAnimation(13190);
				}
				if (comptime == 27) {
					c.npcId2 = 8596;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(11197);
					c.playerStandIndex = 11195;
				}
				if (comptime == 23) {
					c.npcId2 = 8597;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(11202);
					c.playerStandIndex = 11200;
				}
				if (comptime == 20) {
					c.npcId2 = 8591;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.playerStandIndex = 9724;
				}
				if (comptime == 17) {
					c.npcId2 = 8281;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13192);
					c.startAnimation(10680);
					c.startAnimation(10681);
					c.playerStandIndex = 10665;
				}

				if (comptime == 13) {
					c.npcId2 = 10224;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13157);
					c.playerStandIndex = 13156;
				}
				if (comptime == 11) {
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13152);
					c.gfx100(2465);
					c.playerStandIndex = 13156;
				}
				if (comptime == 7) {
					c.npcId2 = 10770;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13156);
					c.playerStandIndex = 13156;
				}
				if (comptime == 0) {
					c.isNpc = false;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.playerStandIndex = 0x328;
					c.startAnimation(12567);
				}
				if (c == null || comptime <= 0) {
					container.stop();
					return;
				}
				if (comptime >= 0) {
					comptime--;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public void createDisplayName(String name) {
		BufferedWriter names = null;
		try {
			names = new BufferedWriter(new FileWriter(
					"./Data/displaynames.txt", true));
			names.write(name);
			names.newLine();
			names.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (names != null) {
				try {
					names.close();
				} catch (IOException e2) {
				}
			}
		}
	}

	// }
	public void createObjectHints(int x, int y, int height, int pos) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(254);
			c.getOutStream().writeByte(pos);
			c.getOutStream().writeWord(x);
			c.getOutStream().writeWord(y);
			c.getOutStream().writeByte(height);
			c.flushOutStream();
		}
	}

	// }
	public void createPlayerHints(int type, int id) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(254);
			c.getOutStream().writeByte(type);
			c.getOutStream().writeWord(id);
			c.getOutStream().write3Byte(0);
			c.flushOutStream();
		}
	}

	public void createPlayersObjectAnim(int X, int Y, int animationID,
			int tileObjectType, int orientation) {
		try {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(Y - (c.mapRegionY * 8));
			c.getOutStream().writeByteC(X - (c.mapRegionX * 8));
			int x = 0;
			int y = 0;
			c.getOutStream().createFrame(160);
			c.getOutStream().writeByteS(((x & 7) << 4) + (y & 7));// tiles away
																	// - could
																	// just send
																	// 0
			c.getOutStream().writeByteS(
					(tileObjectType << 2) + (orientation & 3));
			c.getOutStream().writeWordA(animationID);// animation id
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// }
	// projectiles for everyone within 25 squares
	public void createPlayersProjectile(int x, int y, int offX, int offY,
			int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int lockon, int time) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			Player p = PlayerHandler.players[i];
			if (p != null) {
				Client person = (Client) p;
				if (person != null) {
					if (person.getOutStream() != null) {
						if (person.distanceToPoint(x, y) <= 25) {
							if (p.heightLevel == c.heightLevel) {
								person.getPA().createProjectile(x, y, offX,
										offY, angle, speed, gfxMoving,
										startHeight, endHeight, lockon, time);
							}
						}
					}
				}
			}
		}
	}

	public void createPlayersProjectile2(int x, int y, int offX, int offY,
			int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int lockon, int time, int slope) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			Player p = PlayerHandler.players[i];
			if (p != null) {
				Client person = (Client) p;
				if (person != null) {
					if (person.getOutStream() != null) {
						if (person.distanceToPoint(x, y) <= 25) {
							person.getPA().createProjectile2(x, y, offX, offY,
									angle, speed, gfxMoving, startHeight,
									endHeight, lockon, time, slope);
						}
					}
				}
			}
		}
	}

	// creates gfx for everyone
	public void createPlayersStillGfx(int id, int x, int y, int height, int time) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			Player p = PlayerHandler.players[i];
			if (p != null) {
				Client person = (Client) p;
				if (person != null) {
					if (person.getOutStream() != null) {
						if (person.distanceToPoint(x, y) <= 25) {
							person.getPA().stillGfx(id, x, y, height, time);
						}
					}
				}
			}
		}
	}

	// }
	/**
	 * Creating projectile
	 **/
	public void createProjectile(int x, int y, int offX, int offY, int angle,
			int speed, int gfxMoving, int startHeight, int endHeight,
			int lockon, int time) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC((y - (c.getMapRegionY() * 8)) - 2);
			c.getOutStream().writeByteC((x - (c.getMapRegionX() * 8)) - 3);
			c.getOutStream().createFrame(117);
			c.getOutStream().writeByte(angle);
			c.getOutStream().writeByte(offY);
			c.getOutStream().writeByte(offX);
			c.getOutStream().writeWord(lockon);
			c.getOutStream().writeWord(gfxMoving);
			c.getOutStream().writeByte(startHeight);
			c.getOutStream().writeByte(endHeight);
			c.getOutStream().writeWord(time);
			c.getOutStream().writeWord(speed);
			c.getOutStream().writeByte(16);
			c.getOutStream().writeByte(64);
			c.flushOutStream();
		}
	}

	// }
	public void createProjectile2(int x, int y, int offX, int offY, int angle,
			int speed, int gfxMoving, int startHeight, int endHeight,
			int lockon, int time, int slope) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC((y - (c.getMapRegionY() * 8)) - 2);
			c.getOutStream().writeByteC((x - (c.getMapRegionX() * 8)) - 3);
			c.getOutStream().createFrame(117);
			c.getOutStream().writeByte(angle);
			c.getOutStream().writeByte(offY);
			c.getOutStream().writeByte(offX);
			c.getOutStream().writeWord(lockon);
			c.getOutStream().writeWord(gfxMoving);
			c.getOutStream().writeByte(startHeight);
			c.getOutStream().writeByte(endHeight);
			c.getOutStream().writeWord(time);
			c.getOutStream().writeWord(speed);
			c.getOutStream().writeByte(slope);
			c.getOutStream().writeByte(64);
			c.flushOutStream();
		}
	}

	/*
	 * public int getTotalLevel() { int totalLevel =
	 * (c.getLevelForXP(c.playerXP[0]) + c.getLevelForXP(c.playerXP[1]) +
	 * c.getLevelForXP(c.playerXP[2]) + c.getLevelForXP(c.playerXP[3]) +
	 * c.getLevelForXP(c.playerXP[4]) + c.getLevelForXP(c.playerXP[5]) +
	 * c.getLevelForXP(c.playerXP[6]) + c.getLevelForXP(c.playerXP[7]) +
	 * c.getLevelForXP(c.playerXP[8]) + c.getLevelForXP(c.playerXP[9]) +
	 * c.getLevelForXP(c.playerXP[10]) + c.getLevelForXP(c.playerXP[11]) +
	 * c.getLevelForXP(c.playerXP[12]) + c.getLevelForXP(c.playerXP[13]) +
	 * c.getLevelForXP(c.playerXP[14]) + c.getLevelForXP(c.playerXP[15]) +
	 * c.getLevelForXP(c.playerXP[16]) + c.getLevelForXP(c.playerXP[17]) +
	 * c.getLevelForXP(c.playerXP[18]) + c.getLevelForXP(c.playerXP[19]) +
	 * c.getLevelForXP(c.playerXP[20]) + c.getLevelForXP(c.playerXP[21]) +
	 * c.getLevelForXP(c.playerXP[22]) + c.getLevelForXP(c.playerXP[23]) +
	 * c.getLevelForXP(c.playerXP[24])); /* for (int i = 0; i < 24; i++) {
	 * totalLevel += getLevelForXP(c.playerXP[i]); }* return totalLevel - 2; }
	 */
	public void createProjectile3(int casterY, int casterX, int offsetY,
			int offsetX, int gfxMoving, int StartHeight, int endHeight,
			int speed, int AtkIndex) {
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				Client p = (Client) PlayerHandler.players[i];
				if (p.WithinDistance(c.absX, c.absY, p.absX, p.absY, 60)) {
					if (p.heightLevel == c.heightLevel) {
						if (PlayerHandler.players[i] != null
								&& !PlayerHandler.players[i].disconnected) {
							p.outStream.createFrame(85);
							p.outStream
									.writeByteC((casterY - (p.mapRegionY * 8)) - 2);
							p.outStream
									.writeByteC((casterX - (p.mapRegionX * 8)) - 3);
							p.outStream.createFrame(117);
							p.outStream.writeByte(50);
							p.outStream.writeByte(offsetY);
							p.outStream.writeByte(offsetX);
							p.outStream.writeWord(AtkIndex);
							p.outStream.writeWord(gfxMoving);
							p.outStream.writeByte(StartHeight);
							p.outStream.writeByte(endHeight);
							p.outStream.writeWord(51);
							p.outStream.writeWord(speed);
							p.outStream.writeByte(16);
							p.outStream.writeByte(64);
						}
					}
				}
			}
		}
	}

	/**
	 * Dieing
	 **/
	public void deathAnim() {
		c.startAnimation(836);
	}

	public void destroyBind(int itemId) {
		itemId = c.droppedItem;
		String itemName = c.getItems().getItemName(itemId);
		c.getItems().deleteItem(itemId, c.getItems().getItemSlot(itemId),
				c.playerItemsN[c.getItems().getItemSlot(itemId)]);
		c.sendMessage("Your " + itemName
				+ " vanishes as you drop it on the ground.");
		removeAllWindows();
		if (c.bind1 == c.droppedItem) {
			c.bind1 = -1;
			return;
		} else if (c.bind2 == c.droppedItem) {
			c.bind2 = -1;
			return;
		} else if (c.bind3 == c.droppedItem) {
			c.bind3 = -1;
			return;
		} else if (c.bind4 == c.droppedItem) {
			c.bind4 = -1;
			return;
		}
	}

	public void destroyBindInterface(int itemId) {
		itemId = c.droppedItem;
		String itemName = c.getItems().getItemName(c.droppedItem);
		String[][] info = {
				{ "Are you sure you want to destroy this item?", "14174" },
				{ "Yes.", "14175" }, { "No.", "14176" }, { "", "14177" },
				{ "This item will no longer be binded to you", "14182" },
				{ "after you destroy this ancient item.", "14183" },
				{ itemName, "14184" } };// make some kind of c.getItemInfo
		sendFrame34(itemId, 0, 14171, 1);
		for (int i = 0; i < info.length; i++)
			sendFrame126(info[i][0], Integer.parseInt(info[i][1]));
		sendFrame164(14170);
	}

	public void destroyItem(int itemId) {
		itemId = c.droppedItem;
		c.sendMessage("The item vanishes as you drop it on the ground.");
		System.out.println("DroppedItem ID: " + c.droppedItem + "");
		c.getItems().deleteItem(itemId, c.getItems().getItemSlot(itemId),
				c.playerItemsN[c.getItems().getItemSlot(itemId)]);
		removeAllWindows();
	}

	public void destroySearch() {
		c.lastSearch = false;
		c.isSearching = false;
		c.items = new int[500];
		c.itemsN = new int[500];
		c.searchName = "";
		c.getItems().resetTempItems();
		c.getItems().rearrangeBank();
		c.getItems().resetBank();
		c.getItems().resetKeepItems();
		sendFrame126("The bank of Divination of Gods.", 5383);
	}

	public void displayItemOnInterface(int frame, int item, int slot, int amount) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.outStream.createFrameVarSizeWord(34);
			c.outStream.writeWord(frame);
			c.outStream.writeByte(slot);
			c.outStream.writeWord(item + 1);
			c.outStream.writeByte(255);
			c.outStream.writeDWord(amount);
			c.outStream.endFrameVarSizeWord();
		}
	}

	public void dragonkinFormula(int skillId) { // credits to jason
		int currentLevel = getLevelForXP(c.playerXP[skillId]);
		Math.floor((Math.pow(currentLevel, 3) - 2 * Math.pow(currentLevel, 2) + 100 * currentLevel) / 20);
		if (System.currentTimeMillis() - c.lastEffigy < 5000) {
			return;
		}
		if (c.getItems().playerHasItem(18782, 1)) {
			// c.getPA().addSkillXP(formula, skillId);
			c.getPA().addSkillXP(60000, skillId);
			c.getItems().deleteItem2(18782, 1);
			c.sendMessage("You have gained 60000 experience in this skill.");
			c.getPA().removeAllWindows();
			c.lastEffigy = System.currentTimeMillis();
		}
	}

	/**
	 * Show an arrow icon on the selected player.
	 * 
	 * @Param i - Either 0 or 1; 1 is arrow, 0 is none.
	 * @Param j - The player/Npc that the arrow will be displayed above.
	 * @Param k - Keep this set as 0
	 * @Param l - Keep this set as 0
	 */
	public void drawHeadicon(int i, int j, int k, int l) {
		// synchronized(c) {
		c.outStream.createFrame(254);
		c.outStream.writeByte(i);

		if (i == 1 || i == 10) {
			c.outStream.writeWord(j);
			c.outStream.writeWord(k);
			c.outStream.writeByte(l);
		} else {
			c.outStream.writeWord(k);
			c.outStream.writeWord(l);
			c.outStream.writeByte(j);
		}
	}

	public void dropitems() {
		if (c.hasFollower > 0) {
			if (c.totalstored > 0) {
				c.firstslot();
				for (int i = 0; i < 29; i += 1) {
					Server.itemHandler.createGroundItem(c, c.storeditems[i],
							NPCHandler.npcs[c.summoningnpcid].absX,
							NPCHandler.npcs[c.summoningnpcid].absY, 1,
							c.playerId);
					c.storeditems[i] = -1;
					c.occupied[i] = false;
				}
			}
			c.hasFollower = -1;
			c.totalstored = 0;
			c.summoningnpcid = 0;
			c.summoningslot = 0;
			c.storing = false;
			c.sendMessage("Your BoB items have drop on the floor");
			c.SaveGame();
		}
	}

	public void dungBindStatItem(Client c, int frame, int item, int amount) {
		if (c == null) {
			return;
		}
		int[] items = { item };
		int[] amounts = { amount };
		c.outStream.createFrameVarSizeWord(53);
		c.outStream.writeWord(frame);
		c.outStream.writeWord(items.length);
		for (int i = 0; i < items.length; i++) {
			if (c.playerItemsN[i] > 254) {
				c.outStream.writeByte(255);
				c.outStream.writeDWord_v2(amounts[i]);
			} else {
				c.outStream.writeByte(amounts[i]);
			}
			if (items[i] > 0) {
				c.outStream.writeWordBigEndianA(items[i] + 1);
			} else {
				c.outStream.writeWordBigEndianA(0);
			}
		}
		c.outStream.endFrameVarSizeWord();
		c.flushOutStream();

	}

	public void dungemote(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			int dungtime = 16;

			@Override
			public void execute(CycleEventContainer container) {
				if (dungtime == 16) {
					c.gfx0(2442);
					c.startAnimation(13190);
				}
				if (dungtime == 15) {
					c.npcId2 = 11228;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13192);
				}
				if (dungtime == 10) {
					c.npcId2 = 11227;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13193);
				}
				if (dungtime == 6) {
					c.gfx0(2442);
				}
				if (dungtime == 5) {
					c.npcId2 = 11229;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13194);
				}
				if (dungtime == 0) {
					c.isNpc = false;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
				}
				if (c == null || dungtime <= 0) {
					container.stop();
					return;
				}
				if (dungtime >= 0) {
					dungtime--;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public void dungemote2(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			int dungtime = 16;

			@Override
			public void execute(CycleEventContainer container) {
				if (dungtime == 16) {
					c.gfx0(2442);
					c.startAnimation(13190);
				}
				if (dungtime == 15) {
					c.npcId2 = 11228;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13192);
				}
				if (dungtime == 10) {
					c.npcId2 = 11227;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13193);
				}
				if (dungtime == 6) {
					c.gfx0(2442);
				}
				if (dungtime == 5) {
					c.npcId2 = 11229;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.startAnimation(13194);
				}
				if (dungtime == 0) {
					c.isNpc = false;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
				}
				if (c == null || dungtime <= 0) {
					container.stop();
					return;
				}
				if (dungtime >= 0) {
					dungtime--;
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public int Dungeoneering() {
		return Dungeoneering[(int) (Math.random() * Dungeoneering.length)];
	}

	// }

	public void emptyPouch(int i) {
		if (i < 0) {
			return;
		}
		int toAdd = c.pouches[i];
		if (toAdd > c.getItems().freeSlots()) {
			toAdd = c.getItems().freeSlots();
		}
		if (toAdd > 0) {
			c.getItems().addItem(1436, toAdd);
			c.pouches[i] -= toAdd;
		}
	}

	public void enterCaves() {
		GabbesAchievements.writeAchievementTab(c);
		// c.getPA().movePlayer(2413,5117, c.playerId * 4);
		c.getPA().movePlayer(2408, 5087, c.playerId * 4);
		NPCHandler.deleteFCNpcs(c);
		// c.jadEvent();
		c.sendMessage("He will spawn in few seconds, pot up / pray!");
		c.sendMessage("To exit, run north and enter the cave!");
		c.waveId = 0;
		c.tzhaarToKill = -1;
		c.tzhaarKilled = -1;
		// Server.fightCaves.spawnNextWave(c);
		c.jadSpawn();
	}

	public void enterGoblin() {
		if (c.mageBankMinigameDone == false) {
			c.getPA().spellTeleport(3106, 3931, c.playerId * 4);
			c.waveId = 0;
			c.GoblinSpawn();
		}
	}

	public void enterNewCaves() {
		if (!c.getItems().playerHasItem(6570, 1)) {
			c.sendMessage("You don't have a FireCape in you're inventory to sacrifice!");
			c.getPA().closeAllWindows();
			return;
		} else {
			NPCHandler.deleteFCNpcs(c);
			// c.getPA().movePlayer(2413,5117, c.playerId * 4);
			c.getPA().movePlayer(2408, 5087, c.playerId * 4);
			// c.jadEvent();
			c.sendMessage("Waves will spawn in few seconds, pot up / pray!");
			c.sendMessage("To exit, run north and enter the cave!");
			c.sendMessage("IF YOU RELOG YOU WILL HAVE TO RE-DO EVERY WAVE!");
			c.getItems().deleteItem(6570, 1);
			c.hasFollower = -1;
			c.waveId = 0;
			c.sendMessage("NOTE: You CAN loose you're items sometimes!");
			// Server.fightCaves.spawnNextWave(c);
			c.HardWaveSpawn();
		}

	}

	public void enterNomad() {
		/*
		 * if (c.Nomad == true) {
		 * c.sendMessage("You have already finished this minigame!"); return; }
		 */
		c.getPA().movePlayer(2502, 3012, c.playerId * 4);
		c.waveId = 0;
		c.nomadSpawn();
	}

	public void enterRFD() {
		if (c.Culin == true) {
			c.sendMessage("You have already finished this minigame!");
			return;
		}
		if (c.Agrith == true && c.Flambeed == false) {
			c.waveId = 1;
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			Server.rfd.spawnNextWave(c);
			return;
		}
		if (c.Flambeed == true && c.Karamel == false) {
			c.waveId = 2;
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			Server.rfd.spawnNextWave(c);
			return;
		}
		if (c.Karamel == true && c.Dessourt == false) {
			c.waveId = 3;
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			Server.rfd.spawnNextWave(c);
			return;
		}
		if (c.Dessourt == true && c.Culin == false) {
			c.waveId = 4;
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			Server.rfd.spawnNextWave(c);
			return;
		}
		if (c.Agrith == false) {
			c.getPA().movePlayer(1899, 5363, c.playerId * 4 + 2);
			c.waveId = 0;
			c.RFDToKill = -1;
			c.RFDKilled = -1;
			Server.rfd.spawnNextWave(c);
		}
	}

	public void fillPouch(int i) {
		if (i < 0) {
			return;
		}
		int toAdd = c.POUCH_SIZE[i] - c.pouches[i];
		if (toAdd > c.getItems().getItemAmount(1436)) {
			toAdd = c.getItems().getItemAmount(1436);
		}
		if (toAdd > c.POUCH_SIZE[i] - c.pouches[i]) {
			toAdd = c.POUCH_SIZE[i] - c.pouches[i];
		}
		if (toAdd > 0) {
			c.getItems().deleteItem(1436, toAdd);
			c.pouches[i] += toAdd;
		}
	}

	public int findKiller() {
		int killer = c.playerId;
		int damage = 0;
		for (int j = 0; j < Config.MAX_PLAYERS; j++) {
			if (PlayerHandler.players[j] == null) {
				continue;
			}
			if (j == c.playerId) {
				continue;
			}
			if (c.goodDistance(c.absX, c.absY, PlayerHandler.players[j].absX,
					PlayerHandler.players[j].absY, 40)
					|| c.goodDistance(c.absX, c.absY + 9400,
							PlayerHandler.players[j].absX,
							PlayerHandler.players[j].absY, 40)
					|| c.goodDistance(c.absX, c.absY,
							PlayerHandler.players[j].absX,
							PlayerHandler.players[j].absY + 9400, 40)) {
				if (c.damageTaken[j] > damage) {
					damage = c.damageTaken[j];
					killer = j;
				}
			}
		}
		return killer;
	}

	public void fixAllBarrows() {
		int totalCost = 0;
		int cashAmount = c.getItems().getItemAmount(995);
		for (int j = 0; j < c.playerItems.length; j++) {
			boolean breakOut = false;
			c.getItems();
			for (int i = 0; i < ItemAssistant.brokenBarrows.length; i++) {
				c.getItems();
				if (c.playerItems[j] - 1 == ItemAssistant.brokenBarrows[i][1]) {
					if (totalCost + 80000 > cashAmount) {
						breakOut = true;
						c.sendMessage("You have run out of money.");
						break;
					} else {
						totalCost += 80000;
					}
					c.getItems();
					c.playerItems[j] = ItemAssistant.brokenBarrows[i][0] + 1;
				}
			}
			if (breakOut) {
				break;
			}
		}
		if (totalCost > 0) {
			c.getItems().deleteItem(995, c.getItems().getItemSlot(995),
					totalCost);
		}
	}

	public void fixBugs() { // Anti duel arena shit.
		c.getTradeAndDuel().declineDuel();
		c.getTradeAndDuel().declineTrade();
		c.clickedGate = false;// anti corp beast safespot
		c.stopMovement();
		Client o = (Client) PlayerHandler.players[c.duelingWith];
		if (o != null) {
			o.getTradeAndDuel().declineDuel();
			o.getTradeAndDuel().declineTrade();
			o.stopMovement();
		}
	}

	public void fmwalkto(int i, int j) {
		c.newWalkCmdSteps = 0;
		if (++c.newWalkCmdSteps > 50) {
			c.newWalkCmdSteps = 0;
		}
		int k = c.absX + i;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int l = c.absY + j;
		l -= c.mapRegionY * 8;
		c.isRunning2 = false;
		c.isRunning = false;
		c.getNewWalkCmdX()[0] += k;
		c.getNewWalkCmdY()[0] += l;
		c.poimiY = l;
		c.poimiX = k;
	}

	public void followNpc() {
		if (c.npcFDelay > 0)
			return;
		if (NPCHandler.npcs[c.followId2] == null
				|| NPCHandler.npcs[c.followId2].isDead) {
			c.followId2 = 0;
			return;
		}
		if (c.freezeTimer > 0) {
			return;
		}
		if (c.inWG()) {
			c.followId = 0;
			return;
		}
		if (c.inDuelArena()) {
			c.followId = 0;
			return;
		}
		if (c.inJail() && c.Jail == true) {
			c.sendMessage("You cannot follow in jail!");
			c.followId = 0;
			return;
		}
		if (c.Jail == true) {
			c.sendMessage("You cannot follow in jail!");
			c.followId = 0;
			return;
		}
		if (c.isDead || c.playerLevel[3] <= 0) {
			return;
		}

		int otherX = NPCHandler.npcs[c.followId2].getX();
		int otherY = NPCHandler.npcs[c.followId2].getY();
		boolean withinDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 2);
		c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1);
		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 2);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 8);
		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 4);
		boolean sameSpot = c.absX == otherX && c.absY == otherY;
		if (!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId2 = 0;
			return;
		}
		if (c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1)) {
			if (otherX != c.getX() && otherY != c.getY()) {
				stopDiagonal(otherX, otherY);
				return;
			}
		}

		if ((c.usingBow || c.mageFollow || (c.npcIndex > 0 && c.autocastId > 0))
				&& bowDistance && !sameSpot) {
			return;
		}

		if (c.getCombat().usingHally() && hallyDistance && !sameSpot) {
			return;
		}

		if (c.usingRangeWeapon && rangeWeaponDistance && !sameSpot) {
			return;
		}

		c.faceUpdate(c.followId2);
		if (otherX == c.absX && otherY == c.absY) {
			int r = Misc.random(3);
			switch (r) {
			case 0:
				walkTo(0, -1);
				break;
			case 1:
				walkTo(0, 1);
				break;
			case 2:
				walkTo(1, 0);
				break;
			case 3:
				walkTo(-1, 0);
				break;
			}
		} else if (c.isRunning2 && !withinDistance) {
			if (otherY > c.getY() && otherX == c.getX()) {
				walkTo(0,
						getMove(c.getY(), otherY - 1)
								+ getMove(c.getY(), otherY - 1));
			} else if (otherY < c.getY() && otherX == c.getX()) {
				walkTo(0,
						getMove(c.getY(), otherY + 1)
								+ getMove(c.getY(), otherY + 1));
			} else if (otherX > c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1)
						+ getMove(c.getX(), otherX - 1), 0);
			} else if (otherX < c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1)
						+ getMove(c.getX(), otherX + 1), 0);
			} else if (otherX < c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1)
						+ getMove(c.getX(), otherX + 1),
						getMove(c.getY(), otherY + 1)
								+ getMove(c.getY(), otherY + 1));
			} else if (otherX > c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1)
						+ getMove(c.getX(), otherX - 1),
						getMove(c.getY(), otherY - 1)
								+ getMove(c.getY(), otherY - 1));
			} else if (otherX < c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1)
						+ getMove(c.getX(), otherX + 1),
						getMove(c.getY(), otherY - 1)
								+ getMove(c.getY(), otherY - 1));
			} else if (otherX > c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1)
						+ getMove(c.getX(), otherX + 1),
						getMove(c.getY(), otherY - 1)
								+ getMove(c.getY(), otherY - 1));
			}
		} else {
			if (otherY > c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY - 1));
			} else if (otherY < c.getY() && otherX == c.getX()) {
				walkTo(0, getMove(c.getY(), otherY + 1));
			} else if (otherX > c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1), 0);
			} else if (otherX < c.getX() && otherY == c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1), 0);
			} else if (otherX < c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1),
						getMove(c.getY(), otherY + 1));
			} else if (otherX > c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1),
						getMove(c.getY(), otherY - 1));
			} else if (otherX < c.getX() && otherY > c.getY()) {
				walkTo(getMove(c.getX(), otherX + 1),
						getMove(c.getY(), otherY - 1));
			} else if (otherX > c.getX() && otherY < c.getY()) {
				walkTo(getMove(c.getX(), otherX - 1),
						getMove(c.getY(), otherY + 1));
			}
		}
		c.faceUpdate(c.followId2);
	}

	public void followPlayer() {
		if (PlayerHandler.players[c.followId] == null
				|| PlayerHandler.players[c.followId].isDead) {
			resetFollow();
			return;
		}
		if (c.npcFDelay > 0)
			return;
		if (c.freezeTimer > 0 || c.usingMagic == true) {
			return;
		}
		if (c.inWG()) {
			c.followId = 0;
			return;
		}
		if (inPitsWait()) {
			c.followId = 0;
		}
		if (c.InDung() || c.InDung2() || c.inDungBossRoom()) {
			c.sendMessage("you cannot follow in Dungoneering!");
			c.followId = 0;
			return;
		}
		if (c.inJail() && c.Jail == true) {
			c.sendMessage("You cannot follow in jail!");
			c.followId = 0;
			return;
		}
		if (c.Jail == true) {
			c.sendMessage("You cannot follow in jail!");
			c.followId = 0;
			return;
		}
		if (c.isDead || c.playerLevel[3] <= 0) {
			return;
		}

		int otherX = PlayerHandler.players[c.followId].getX();
		int otherY = PlayerHandler.players[c.followId].getY();

		boolean sameSpot = (c.absX == otherX && c.absY == otherY);

		boolean hallyDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 2);

		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 4);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 6);
		boolean mageDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 7);

		boolean castingMagic = (c.usingMagic || c.spellId > 0) && mageDistance;
		boolean playerRanging = (c.usingRangeWeapon) && rangeWeaponDistance;
		boolean playerBowOrCross = (c.usingBow) && bowDistance;

		if (!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId = 0;
			resetFollow();
			return;
		}
		c.faceUpdate(c.followId + 32768);
		if (!sameSpot) {
			if (c.playerIndex > 0 && !c.usingSpecial && c.inWild()) {
				if (c.usingSpecial && (playerRanging || playerBowOrCross)) {
					c.stopMovement();
					return;
				}
				if (castingMagic || playerRanging || playerBowOrCross) {
					c.stopMovement();
					return;
				}
				if (c.getCombat().usingHally() && hallyDistance) {
					c.stopMovement();
					return;
				}
			}
		}
		if (otherX == c.absX && otherY == c.absY) {
			int r = Misc.random(3);
			switch (r) {
			case 0:
				walkTo(0, -1);
				break;
			case 1:
				walkTo(0, 1);
				break;
			case 2:
				walkTo(1, 0);
				break;
			case 3:
				walkTo(-1, 0);
				break;
			}
		} else if (c.isRunning2
				&& Region.canAttack(c,
						(Client) PlayerHandler.players[c.followId])) {
			if (otherY > c.getY() && otherX == c.getX()) {
				playerWalk(otherX, otherY - 1);
			} else if (otherY < c.getY() && otherX == c.getX()) {
				playerWalk(otherX, otherY + 1);
			} else if (otherX > c.getX() && otherY == c.getY()) {
				playerWalk(otherX - 1, otherY);
			} else if (otherX < c.getX() && otherY == c.getY()) {
				playerWalk(otherX + 1, otherY);
			} else if (otherX < c.getX() && otherY < c.getY()) {
				playerWalk(otherX + 1, otherY + 1);
			} else if (otherX > c.getX() && otherY > c.getY()) {
				playerWalk(otherX - 1, otherY - 1);
			} else if (otherX < c.getX() && otherY > c.getY()) {
				playerWalk(otherX + 1, otherY - 1);
			} else if (otherX > c.getX() && otherY < c.getY()) {
				playerWalk(otherX + 1, otherY - 1);
			}
		} else {
			if (otherY > c.getY() && otherX == c.getX()) {
				playerWalk(otherX, otherY - 1);
			} else if (otherY < c.getY() && otherX == c.getX()) {
				playerWalk(otherX, otherY + 1);
			} else if (otherX > c.getX() && otherY == c.getY()) {
				playerWalk(otherX - 1, otherY);
			} else if (otherX < c.getX() && otherY == c.getY()) {
				playerWalk(otherX + 1, otherY);
			} else if (otherX < c.getX() && otherY < c.getY()) {
				playerWalk(otherX + 1, otherY + 1);
			} else if (otherX > c.getX() && otherY > c.getY()) {
				playerWalk(otherX - 1, otherY - 1);
			} else if (otherX < c.getX() && otherY > c.getY()) {
				playerWalk(otherX + 1, otherY - 1);
			} else if (otherX > c.getX() && otherY < c.getY()) {
				playerWalk(otherX - 1, otherY + 1);
			}
		}
		c.faceUpdate(c.followId + 32768);
	}

	// }
	/**
	 * Reseting animations for everyone
	 **/
	public void frame1() {
		// synchronized(c) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				Client person = (Client) PlayerHandler.players[i];
				if (person != null) {
					if (person.getOutStream() != null && !person.disconnected) {
						if (c.distanceToPoint(person.getX(), person.getY()) <= 25) {
							person.getOutStream().createFrame(1);
							person.flushOutStream();
							person.getPA().requestUpdates();
						}
					}
				}
			}
		}
	}

	public void frame174(int i1, int i2, int i3) // another thing, tested
	// doesn't logout, looks
	// like something to do with
	// music
	{
		c.outStream.createFrame(174);
		c.outStream.writeWord(i1);
		c.outStream.writeByte(i2);
		c.outStream.writeWord(i3);
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}

	// }
	public void Frame34(int frame, int item, int slot, int amount) {

		if (c.getOutStream() != null && c != null) {
			c.outStream.createFrameVarSizeWord(34);
			c.outStream.writeWord(frame);
			c.outStream.writeByte(slot);
			c.outStream.writeWord(item + 1);
			c.outStream.writeByte(255);
			c.outStream.writeDWord(amount);
			c.outStream.endFrameVarSizeWord();
		}

	}

	public void frame74(int songID) {

		c.outStream.createFrame(74);
		c.outStream.writeWordBigEndian(songID);
	}

	public boolean fullGuthans() {
		return c.playerEquipment[server.model.players.Player.playerHat] == 4724
				&& c.playerEquipment[server.model.players.Player.playerChest] == 4728
				&& c.playerEquipment[server.model.players.Player.playerLegs] == 4730
				&& c.playerEquipment[server.model.players.Player.playerWeapon] == 4726;
	}

	public boolean fullVeracs() {
		return c.playerEquipment[server.model.players.Player.playerHat] == 4753
				&& c.playerEquipment[server.model.players.Player.playerChest] == 4757
				&& c.playerEquipment[server.model.players.Player.playerLegs] == 4759
				&& c.playerEquipment[server.model.players.Player.playerWeapon] == 4755;
	}

	public int getBankItems(int tab) {
		int ta = 0, tb = 0, tc = 0, td = 0, te = 0, tf = 0, tg = 0, th = 0, ti = 0;
		for (int i = 0; i < c.bankItems.length; i++)
			if (c.bankItems[i] > 0)
				ta++;
		for (int i = 0; i < c.bankItems1.length; i++)
			if (c.bankItems1[i] > 0)
				tb++;
		for (int i = 0; i < c.bankItems2.length; i++)
			if (c.bankItems2[i] > 0)
				tc++;
		for (int i = 0; i < c.bankItems3.length; i++)
			if (c.bankItems3[i] > 0)
				td++;
		for (int i = 0; i < c.bankItems4.length; i++)
			if (c.bankItems4[i] > 0)
				te++;
		for (int i = 0; i < c.bankItems5.length; i++)
			if (c.bankItems5[i] > 0)
				tf++;
		for (int i = 0; i < c.bankItems6.length; i++)
			if (c.bankItems6[i] > 0)
				tg++;
		for (int i = 0; i < c.bankItems7.length; i++)
			if (c.bankItems7[i] > 0)
				th++;
		for (int i = 0; i < c.bankItems8.length; i++)
			if (c.bankItems8[i] > 0)
				ti++;
		if (tab == 0)
			return ta;
		if (tab == 1)
			return tb;
		if (tab == 2)
			return tc;
		if (tab == 3)
			return td;
		if (tab == 4)
			return te;
		if (tab == 5)
			return tf;
		if (tab == 6)
			return tg;
		if (tab == 7)
			return th;
		if (tab == 8)
			return ti;
		return ta + tb + tc + td + te + tf + tg + th + ti;// return total

	}

	public void getDragonClawHits(Client c, int i) {
		c.clawHit[0] = i + Misc.random(10) + 1;
		c.clawHit[1] = c.clawHit[0] / 2;
		c.clawHit[2] = c.clawHit[1] / 2;
		c.clawHit[3] = c.clawHit[2] + 1;
	}

	public void getHighscores(String type) {
		resetFrames();
		ArrayList<InGameHighscores> hs = new ArrayList<InGameHighscores>();
		if (type.equalsIgnoreCase("kills")) {
			InGameHighscores.type = "Kills";
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					hs.add(new InGameHighscores(Misc
							.optimizeText(c2.playerName), c2.totalkills));
				}
			}
		}
		if (type.equalsIgnoreCase("PLP")) {
			InGameHighscores.type = "Slayer Points";
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					hs.add(new InGameHighscores(Misc
							.optimizeText(c2.playerName), c2.pkinPoints));
				}
			}
		}
		if (type.equalsIgnoreCase("deaths")) {
			InGameHighscores.type = "Deaths";
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					hs.add(new InGameHighscores(Misc
							.optimizeText(c2.playerName), c2.totaldeath));
				}
			}
		}

		if (type.equalsIgnoreCase("LP")) {
			InGameHighscores.type = "Loyalty Points";
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					hs.add(new InGameHighscores(Misc
							.optimizeText(c2.playerName), c2.LoyaltyPoints));
				}
			}
		}
		Collections.sort(hs);
		for (int i = 1; i <= hs.size(); i++) {
			if (i != 11 && i < 12) {
				c.getPA().sendFrame126(
						"@or1@Rank " + i + ": @gre@" + hs.get((hs.size() - i)),
						12102 + i);
			}
		}
		c.getPA().showInterface(12100);
		c.flushOutStream();
		hs.clear();
	}

	public int getInterfaceModel(int slot, int[] array, int[] arrayN) {
		int model = array[slot] - 1;
		if (model == 995) {
			if (arrayN[slot] > 9999) {
				model = 1004;
			} else if (arrayN[slot] > 999) {
				model = 1003;
			} else if (arrayN[slot] > 249) {
				model = 1002;
			} else if (arrayN[slot] > 99) {
				model = 1001;
			} else if (arrayN[slot] > 24) {
				model = 1000;
			} else if (arrayN[slot] > 4) {
				model = 999;
			} else if (arrayN[slot] > 3) {
				model = 998;
			} else if (arrayN[slot] > 2) {
				model = 997;
			} else if (arrayN[slot] > 1) {
				model = 996;
			}
		}
		return model;
	}

	public int getItemValue(int ItemID) {
		int shopValue = 0;
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					shopValue = (int) Server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		return shopValue;
	}

	/**
	 * Dieing
	 **/
	public String getKM() {
		int kMCount = Misc.random(11);
		switch (kMCount) {
		case 0:
			return "With a crushing blow, you defeat " + c.playerName + ".";
		case 1:
			return "It's a humiliating defeat for " + c.playerName + ".";
		case 2:
			return c.playerName + " didn't stand a chance against you.";
		case 3:
			return "You've defeated " + c.playerName + ".";
		case 4:
			return c.playerName + " regrets the day they met you in combat.";
		case 5:
			return "It's all over for " + c.playerName + ".";
		case 6:
			return c.playerName + " falls before you might.";
		case 7:
			return "Can anyone defeat you? Certainly not " + c.playerName + ".";
		case 8:
			return c.playerName + " has fallen under your might.";
		case 9:
			return c.playerName + " crumbled under your power.";
		case 10:
			return "You have proven " + c.playerName
					+ " to be an unworthy opponent.";
		default:
			return "You were clearly a better fighter than " + c.playerName
					+ ".";
		}
	}

	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;
		if (exp > 104273167) {
			return 120;
		}
		for (int lvl = 1; lvl <= 120; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 0;
	}

	public int getMove(int place1, int place2) {
		if (System.currentTimeMillis() - c.lastSpear < 4000) {
			return 0;
		}
		if ((place1 - place2) == 0) {
			return 0;
		} else if ((place1 - place2) < 0) {
			return 1;
		} else if ((place1 - place2) > 0) {
			return -1;
		}
		return 0;
	}

	// }
	public int getNpcId(int id) {
		for (int i = 0; i < NPCHandler.maxNPCs; i++) {
			if (NPCHandler.npcs[i] != null) {
				if (NPCHandler.npcs[i].npcId == id) {
					return i;
				}
			}
		}
		return -1;
	}

	public String GetNpcName(int NpcID) {
		for (int i = 0; i < NPCHandler.maxListedNPCs; i++) {
			if (NPCHandler.NpcList[i] != null) {
				if (NPCHandler.NpcList[i].npcId == NpcID) {
					return NPCHandler.NpcList[i].npcName;
				}
			}
		}
		return "NPC Not Listed" + NpcID;
	}

	public void getPlayerRank() {
		if (c.playerRights == 0) {
			L = "Regular Player";
		} else {
			if (c.playerRights == 1) {
				L = "Moderator";
			} else {
				if (c.playerRights == 2) {
					L = "Administrator";
				} else {
					if (c.playerRights == 3) {
						L = "Owner";
					} else {
						if (c.playerRights == 4 && c.isDonator == 1) {
							L = "Donator";
						} else {
							if (c.isDonator == 3 || c.isDonator == 2
									|| c.isDonator == 4 && c.playerRights == 4) {
								L = "Extreme Donator";
							} else {
								if (c.playerRights == 5) {
									L = "Helper";
								}
							}
						}
					}
				}
			}
		}
		c.getPA().sendFrame126("@gre@Player rank: @yel@" + L + " ", 39166);
		c.getPA().sendFrame126("@gre@", 39165);
	}

	public int getRunningMove(int i, int j) {
		if (j - i > 2) {
			return 2;
		} else if (j - i < -2) {
			return -2;
		} else {
			return j - i;
		}
	}

	public void getSpeared(int otherX, int otherY) {
		int x = c.absX - otherX;
		int y = c.absY - otherY;
		if (x > 0) {
			x = 1;
		} else if (x < 0) {
			x = -1;
		}
		if (y > 0) {
			y = 1;
		} else if (y < 0) {
			y = -1;
		}
		moveCheck(x, y);
		c.lastSpear = System.currentTimeMillis();
	}

	// gets players 99s
	public int getStats() {
		int count = 0;

		for (int i = 0; i <= 24; i++) {
			if (c.getPA().getLevelForXP(c.playerXP[i]) == 99)
				count++;
		}

		return count;
	}

	public int getTabCount() {
		// count tabs
		int tabs = 0;
		if (!checkEmpty(c.bankItems1))
			tabs++;
		if (!checkEmpty(c.bankItems2))
			tabs++;
		if (!checkEmpty(c.bankItems3))
			tabs++;
		if (!checkEmpty(c.bankItems4))
			tabs++;
		if (!checkEmpty(c.bankItems5))
			tabs++;
		if (!checkEmpty(c.bankItems6))
			tabs++;
		if (!checkEmpty(c.bankItems7))
			tabs++;
		if (!checkEmpty(c.bankItems8))
			tabs++;
		return tabs;
	}

	public String getTotalAmount(Client c, int j) {
		if (j >= 10000 && j < 10000000) {
			return j / 1000 + "K";
		} else if (j >= 10000000 && j <= 2147483647) {
			return j / 1000000 + "M";
		} else {
			return "" + j + " gp";
		}
	}

	public int getTotalLevel() {
		int totalLevel = 0;
		for (int i = 0; i < 24; i++) {
			totalLevel += getLevelForXP(c.playerXP[i]);
		}
		return totalLevel;
	}

	public long getTotalXP() {
		long total = 0;
		for (int i = 0; i < 25; i++) {
			total += (long) c.playerXP[i];
		}
		return total;
	}

	public int getWearingAmount() {
		int count = 0;
		for (int j = 0; j < c.playerEquipment.length; j++) {
			if (c.playerEquipment[j] > 0) {
				count++;
			}
		}
		return count;
	}

	public int getWearingAmount2() {
		int totalCash = 0;
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] > 0) {
				totalCash += getItemValue(c.playerEquipment[i]);
			}
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] > 0) {
				totalCash += getItemValue(c.playerItems[i]);
			}
		}
		return totalCash;
	}

	/*
	 * public void refreshSkill(int i) { switch (i) { case 0: sendFrame126("" +
	 * c.playerLevel[0] + "", 4004); sendFrame126("" +
	 * getLevelForXP(c.playerXP[0]) + "", 4005); sendFrame126("" + c.playerXP[0]
	 * + "", 4044); sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[0])
	 * + 1) + "", 4045); break;
	 * 
	 * case 1: sendFrame126("" + c.playerLevel[1] + "", 4008); sendFrame126("" +
	 * getLevelForXP(c.playerXP[1]) + "", 4009); sendFrame126("" + c.playerXP[1]
	 * + "", 4056); sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[1])
	 * + 1) + "", 4057); break;
	 * 
	 * case 2: sendFrame126("" + c.playerLevel[2] + "", 4006); //sendFrame126(""
	 * + getLevelForXP(c.playerXP[2]) + "", 4007); sendFrame126("" +
	 * c.playerXP[2] + "", 4050); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[2]) + 1) + "", 4051); break;
	 * 
	 * case 3: sendFrame126("" + c.playerLevel[3] + "", 4016); sendFrame126("" +
	 * getLevelForXP(c.playerXP[3]) + "", 4017); sendFrame126("" + c.playerXP[3]
	 * + "", 4080); sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[3])
	 * + 1) + "", 4081); break;
	 * 
	 * case 4: sendFrame126("" + c.playerLevel[4] + "", 4010); sendFrame126("" +
	 * getLevelForXP(c.playerXP[4]) + "", 4011); sendFrame126("" + c.playerXP[4]
	 * + "", 4062); sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[4])
	 * + 1) + "", 4063); break;
	 * 
	 * case 5: sendFrame126("" + c.playerLevel[5] + "", 4012); sendFrame126("" +
	 * getLevelForXP(c.playerXP[5]) + "", 4013); sendFrame126("" + c.playerXP[5]
	 * + "", 4068); sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[5])
	 * + 1) + "", 4069); sendFrame126("" + c.playerLevel[5] + "/" +
	 * getLevelForXP(c.playerXP[5]) + "", 687);// Prayer frame break;
	 * 
	 * case 6: sendFrame126("" + c.playerLevel[6] + "", 4014); sendFrame126("" +
	 * getLevelForXP(c.playerXP[6]) + "", 4015); sendFrame126("" + c.playerXP[6]
	 * + "", 4074); sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[6])
	 * + 1) + "", 4075); break;
	 * 
	 * case 7: sendFrame126("" + c.playerLevel[7] + "", 4034); sendFrame126("" +
	 * getLevelForXP(c.playerXP[7]) + "", 4035); sendFrame126("" + c.playerXP[7]
	 * + "", 4134); sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[7])
	 * + 1) + "", 4135); break;
	 * 
	 * case 8: sendFrame126("" + c.playerLevel[8] + "", 4038); sendFrame126("" +
	 * getLevelForXP(c.playerXP[8]) + "", 4039); sendFrame126("" + c.playerXP[8]
	 * + "", 4146); sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[8])
	 * + 1) + "", 4147); break;
	 * 
	 * case 9: sendFrame126("" + c.playerLevel[9] + "", 4026); sendFrame126("" +
	 * getLevelForXP(c.playerXP[9]) + "", 4027); sendFrame126("" + c.playerXP[9]
	 * + "", 4110); sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[9])
	 * + 1) + "", 4111); break;
	 * 
	 * case 10: sendFrame126("" + c.playerLevel[10] + "", 4032); sendFrame126(""
	 * + getLevelForXP(c.playerXP[10]) + "", 4033); sendFrame126("" +
	 * c.playerXP[10] + "", 4128); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[10]) + 1) + "", 4129); break;
	 * 
	 * case 11: sendFrame126("" + c.playerLevel[11] + "", 4036); sendFrame126(""
	 * + getLevelForXP(c.playerXP[11]) + "", 4037); sendFrame126("" +
	 * c.playerXP[11] + "", 4140); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[11]) + 1) + "", 4141); break;
	 * 
	 * case 12: sendFrame126("" + c.playerLevel[12] + "", 4024); sendFrame126(""
	 * + getLevelForXP(c.playerXP[12]) + "", 4025); sendFrame126("" +
	 * c.playerXP[12] + "", 4104); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[12]) + 1) + "", 4105); break;
	 * 
	 * case 13: sendFrame126("" + c.playerLevel[13] + "", 4030); sendFrame126(""
	 * + getLevelForXP(c.playerXP[13]) + "", 4031); sendFrame126("" +
	 * c.playerXP[13] + "", 4122); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[13]) + 1) + "", 4123); break;
	 * 
	 * case 14: sendFrame126("" + c.playerLevel[14] + "", 4028); sendFrame126(""
	 * + getLevelForXP(c.playerXP[14]) + "", 4029); sendFrame126("" +
	 * c.playerXP[14] + "", 4116); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[14]) + 1) + "", 4117); break;
	 * 
	 * case 15: sendFrame126("" + c.playerLevel[15] + "", 4020); sendFrame126(""
	 * + getLevelForXP(c.playerXP[15]) + "", 4021); sendFrame126("" +
	 * c.playerXP[15] + "", 4092); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[15]) + 1) + "", 4093); break;
	 * 
	 * case 16: sendFrame126("" + c.playerLevel[16] + "", 4018); sendFrame126(""
	 * + getLevelForXP(c.playerXP[16]) + "", 4019); sendFrame126("" +
	 * c.playerXP[16] + "", 4086); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[16]) + 1) + "", 4087); break;
	 * 
	 * case 17: sendFrame126("" + c.playerLevel[17] + "", 4022); sendFrame126(""
	 * + getLevelForXP(c.playerXP[17]) + "", 4023); sendFrame126("" +
	 * c.playerXP[17] + "", 4098); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[17]) + 1) + "", 4099); break;
	 * 
	 * case 18: sendFrame126("" + c.playerLevel[18] + "", 12166);
	 * sendFrame126("" + getLevelForXP(c.playerXP[18]) + "", 12167);
	 * sendFrame126("" + c.playerXP[18] + "", 12171); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[18]) + 1) + "", 12172); break;
	 * 
	 * case 19: sendFrame126("" + c.playerLevel[19] + "", 13926);
	 * sendFrame126("" + getLevelForXP(c.playerXP[19]) + "", 13927);
	 * sendFrame126("" + c.playerXP[19] + "", 13921); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[19]) + 1) + "", 13922); break;
	 * 
	 * case 20: sendFrame126("" + c.playerLevel[20] + "", 4152); sendFrame126(""
	 * + getLevelForXP(c.playerXP[20]) + "", 4153); sendFrame126("" +
	 * c.playerXP[20] + "", 4157); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[20]) + 1) + "", 4158); break;
	 * 
	 * case 21: sendFrame126("" + c.playerLevel[21] + "", 29800); // hunter
	 * break;
	 * 
	 * case 22: //summoning sendFrame126("" + c.playerLevel[22] + "", 29801);
	 * //sendFrame126("" + c.playerLevel[22] + "", 29799); //sendFrame126("" +
	 * c.playerXP[22] + "", 29803); sendFrame126("" + c.playerLevel[22] + "",
	 * 7205); /*sendFrame126("@yel@" +d c.playerLevel[22] + "", 18167);
	 * sendFrame126("@yel@" + c.playerLevel[22] + "", 18171); sendFrame126("" +
	 * c.playerXP[22] + "", 29803);* break;
	 * 
	 * case 23: sendFrame126("" + c.playerLevel[23] + "", 7205); sendFrame126(""
	 * + getLevelForXP(c.playerXP[23]) + "", 7206);
	 * 
	 * break;
	 * 
	 * 
	 * case 24: if(c.playerXP[24] >= 0 && c.playerXP[24] <= 14391160) {
	 * sendFrame126((new
	 * StringBuilder()).append("").append(c.playerLevel[24]).append
	 * ("").toString(), 7157); sendFrame126((new
	 * StringBuilder()).append("").append
	 * (getLevelForXP(c.playerXP[24])).append("").toString(), 7158); } else
	 * if(c.playerXP[24] >= 14391161 && c.playerXP[24] <= 15889108) {
	 * sendFrame126("100", 7157); sendFrame126("100", 7158); } else
	 * if(c.playerXP[24] >= 15889109 && c.playerXP[24] <= 17542976) {
	 * sendFrame126("101", 7157); sendFrame126("101", 7158); } else
	 * if(c.playerXP[24] >= 17542977 && c.playerXP[24] <= 19368991) {
	 * sendFrame126("102", 7157); sendFrame126("102", 7158); } else
	 * if(c.playerXP[24] >= 19368992 && c.playerXP[24] <= 21385072) {
	 * sendFrame126("103", 7157); sendFrame126("103", 7158); } else
	 * if(c.playerXP[24] >= 21385073 && c.playerXP[24] <= 23611005) {
	 * sendFrame126("104", 7157); sendFrame126("104", 7158); } else
	 * if(c.playerXP[24] >= 23611006 && c.playerXP[24] <= 26068631) {
	 * sendFrame126("105", 7157); sendFrame126("105", 7158); } else
	 * if(c.playerXP[24] >= 26068632 && c.playerXP[24] <= 28782068) {
	 * sendFrame126("106", 7157); sendFrame126("106", 7158); } else
	 * if(c.playerXP[24] >= 28782069 && c.playerXP[24] <= 31777942) {
	 * sendFrame126("107", 7157); sendFrame126("107", 7158); } else
	 * if(c.playerXP[24] >= 31777943 && c.playerXP[24] <= 35085653) {
	 * sendFrame126("108", 7157); sendFrame126("108", 7158); } else
	 * if(c.playerXP[24] >= 35085654 && c.playerXP[24] <= 38737660) {
	 * sendFrame126("109", 7157); sendFrame126("109", 7158); } else
	 * if(c.playerXP[24] >= 38737661 && c.playerXP[24] <= 42769799) {
	 * sendFrame126("110", 7157); sendFrame126("110", 7158); } else
	 * if(c.playerXP[24] >= 42769800 && c.playerXP[24] <= 47221639) {
	 * sendFrame126("111", 7157); sendFrame126("111", 7158); } else
	 * if(c.playerXP[24] >= 47221640 && c.playerXP[24] <= 52136868) {
	 * sendFrame126("112", 7157); sendFrame126("112", 7158); } else
	 * if(c.playerXP[24] >= 52136869 && c.playerXP[24] <= 57563717) {
	 * sendFrame126("113", 7157); sendFrame126("113", 7158); } else
	 * if(c.playerXP[24] >= 57563718 && c.playerXP[24] <= 63555442) {
	 * sendFrame126("114", 7157); sendFrame126("114", 7158); } else
	 * if(c.playerXP[24] >= 63555443 && c.playerXP[24] <= 70170839) {
	 * sendFrame126("115", 7157); sendFrame126("115", 7158); } else
	 * if(c.playerXP[24] >= 70170840 && c.playerXP[24] <= 77474827) {
	 * sendFrame126("116", 7157); sendFrame126("116", 7158); } else
	 * if(c.playerXP[24] >= 77474828 && c.playerXP[24] <= 85539081) {
	 * sendFrame126("117", 7157); sendFrame126("117", 7158); } else
	 * if(c.playerXP[24] >= 85539082 && c.playerXP[24] <= 94442735) {
	 * sendFrame126("118", 7157); sendFrame126("118", 7157); } else
	 * if(c.playerXP[24] >= 94442736 && c.playerXP[24] <= 104273166) {
	 * sendFrame126("119", 7157); sendFrame126("119", 7158); } else
	 * if(c.playerXP[24] >= 104273167 && c.playerXP[24] <= 200000000) {
	 * sendFrame126("120", 7157); sendFrame126("120", 7158); } } }
	 */
	public int getXPForLevel(int level) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}

	public void giveLife() {
		c.isDead = false;
		resetDamageDone();
		c.faceUpdate(-1);
		c.freezeTimer = 0;

		if (c.duelStatus != 5 && !c.lostDuel && !c.getPA().inPitsWait()
				&& !c.inPits && !c.inFightCaves() && !c.inPcGame()
				&& !c.inFunPk()) {
			if (c.playerRights < 6) {
				if (!c.isSkulled && !c.isInArd()) {
					c.getItems().keepItem(0, true);
					c.getItems().keepItem(1, true);
					c.getItems().keepItem(2, true);
				}
				if (c.prayerActive[10] || c.curseActive[0]
						&& System.currentTimeMillis() - c.lastProtItem > 700) {
					c.getItems().keepItem(3, true);
				}
				c.getItems().dropAllItems();
				c.getItems().deleteAllItems();
				// GRAVE STONE
				/*
				 * if(!c.inWild()) { c.playerDeathX = c.absX; c.playerDeathY =
				 * c.absY; c.theDeadGuy = c.playerName; c.GSTimer = 120;
				 * c.startGSEvent(); }
				 */
				if (!c.isSkulled && !c.isInArd()) {
					for (int i1 = 0; i1 < 3; i1++) {
						if (c.itemKeptId[i1] > 0) {
							c.getItems().addItem(c.itemKeptId[i1], 1);
						}
					}
				}
				if (c.prayerActive[10] || c.isInArd()) { // if we have
					// protect items
					if (c.itemKeptId[3] > 0) {
						c.getItems().addItem(c.itemKeptId[3], 1);
					}
				}
			}
			c.getItems().resetKeepItems();
		}
		c.getCombat().resetPrayers();
		for (int i = 0; i < 25; i++) {
			c.playerLevel[i] = getLevelForXP(c.playerXP[i]);
			c.getPA().refreshSkill(i);
		}
		if (c.inBarbDef) {
			Server.barbDefence.endGame(c, false);
		} else if (c.pitsStatus == 1) {
			movePlayer(2399, 5173, 0);

		} else if (c.inFightCaves()) {
			c.getPA().resetTzhaar();
		} else if (c.duelStatus != 5 && !c.lostDuel) { // if we are not in a
														// duel repawn to wildy
			movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
			c.isSkulled = false;
			c.skullTimer = 0;
			c.attackedPlayers.clear();
		} else if (c.duelStatus == 5 || c.lostDuel) { // we are in a duel,
														// respawn outside of
														// arena
			Client o = (Client) PlayerHandler.players[c.duelingWith];
			if (o != null) {
				o.getPA().createPlayerHints(10, -1);
				if (o.duelStatus == 6 && c.duelStatus == 5) {
					o.getTradeAndDuel().duelVictory();
				}
			}
			c.getPA().movePlayer(
					Config.DUELING_RESPAWN_X
							+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
					Config.DUELING_RESPAWN_Y
							+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)), 0);
			assert o != null;
			if (o != null) {
				o.getPA().movePlayer(
						Config.DUELING_RESPAWN_X
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						Config.DUELING_RESPAWN_Y
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						0);
			}
			if (c.duelStatus != 6) { // if we have won but have died, don't
										// reset the duel status.
				c.getTradeAndDuel().resetDuel();
			}
			c.lostDuel = false;
		}
		PlayerSave.saveGame(c);
		c.getCombat().resetPlayerAttack();
		resetAnimation();
		c.startAnimation(-1);
		frame1();
		resetTb();
		c.isSkulled = false;
		c.attackedPlayers.clear();
		c.headIconPk = -1;
		c.skullTimer = -1;
		c.damageTaken = new int[Config.MAX_PLAYERS];
		c.getPA().requestUpdates();
		removeAllWindows();
		c.getTradeAndDuel().resetTrade();
		c.isFullHelm = Item
				.isFullHelm(c.playerEquipment[server.model.players.Player.playerHat]);
		c.isFullMask = Item
				.isFullMask(c.playerEquipment[server.model.players.Player.playerHat]);
		c.isFullBody = Item
				.isFullBody(c.playerEquipment[server.model.players.Player.playerChest]);

	}

	public void gloryDegrade() {
		if (c.gdegradeNow == true) {
			if (c.gloryValue == 3) {
				if (c.getItems().playerHasItem(1712, 1)) {
					c.getItems().addItem(1710, 1);
					c.getItems().deleteItem(1712, 1);
					c.gloryValue = 4;
					c.gdegradeNow = false;
				}
			} else {
				if (c.playerEquipment[server.model.players.Player.playerAmulet] == 1712) {
					c.playerEquipment[server.model.players.Player.playerAmulet] = 1710;
					c.gloryValue = 4;
					c.gdegradeNow = false;
				}
			}
			if (c.gloryValue == 2) {
				if (c.getItems().playerHasItem(1710, 1)) {
					c.getItems().addItem(1708, 1);
					c.getItems().deleteItem(1710, 1);
					c.gloryValue = 4;
					c.gdegradeNow = false;
				}
			} else {
				if (c.playerEquipment[server.model.players.Player.playerAmulet] == 1710) {
					c.playerEquipment[server.model.players.Player.playerAmulet] = 1708;
					c.gloryValue = 4;
					c.gdegradeNow = false;
				}
			}
			if (c.gloryValue == 1) {
				if (c.getItems().playerHasItem(1708, 1)) {
					c.getItems().addItem(1706, 1);
					c.getItems().deleteItem(1708, 1);
					c.gloryValue = 4;
					c.gdegradeNow = false;
				}
			} else {
				if (c.playerEquipment[server.model.players.Player.playerAmulet] == 1708) {
					c.playerEquipment[server.model.players.Player.playerAmulet] = 1706;
					c.gloryValue = 4;
					c.gdegradeNow = false;
				}
			}
			if (c.gloryValue == 0) {
				if (c.getItems().playerHasItem(1706, 1)) {
					c.getItems().addItem(1704, 1);
					c.getItems().deleteItem(1706, 1);
					c.sendMessage("Your amulet is out of charges! Recharge location not added yet.");
					c.gdegradeNow = false;
				}
			} else {
				if (c.playerEquipment[server.model.players.Player.playerAmulet] == 1706) {
					c.playerEquipment[server.model.players.Player.playerAmulet] = 1704;
					c.gloryValue = 4;
					c.gdegradeNow = false;
				}
			}
		}
	}

	public void GWKC() {
		walkableInterface(16220);
		sendFrame126("" + c.Arma + "", 16216);
		sendFrame126("" + c.Band + "", 16217);
		sendFrame126("" + c.Sara + "", 16218);
		sendFrame126("" + c.Zammy + "", 16219);
	}

	public void handleDegradingBrawlers() {
		if (c.wearingBrawlers() && c.brawlerscook == 150
				|| c.brawlerscook > 150) {
			c.getItems()
					.sendWeapon(
							c.playerEquipment[server.model.players.Player.playerHands],
							c.getItems()
									.getItemName(
											c.playerEquipment[server.model.players.Player.playerHands]));
			c.getCombat()
					.getPlayerAnimIndex(
							c.getItems()
									.getItemName(
											c.playerEquipment[server.model.players.Player.playerHands])
									.toLowerCase());
			c.getItems().resetBonus();
			c.getOutStream().createFrame(34);
			c.getOutStream().writeWord(6);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(server.model.players.Player.playerHands);
			c.getOutStream().writeWord(0);
			c.getOutStream().writeByte(0);
			c.getItems().resetBonus();
			c.getItems().getBonus();
			c.getItems().writeBonus();
			c.getPA().requestUpdates();
			c.setAppearanceUpdateRequired(true);
			c.sendMessage("Your brawlers degrade..");
			c.brawlerscook = 0;
			c.SaveGame();
		}
	}

	public void handleDegradingBrawlersFM() {
		if (c.wearingFMBrawlers() && c.brawlerFM == 150 || c.brawlerFM > 150) {
			c.getItems()
					.sendWeapon(
							c.playerEquipment[server.model.players.Player.playerHands],
							c.getItems()
									.getItemName(
											c.playerEquipment[server.model.players.Player.playerHands]));
			c.getCombat()
					.getPlayerAnimIndex(
							c.getItems()
									.getItemName(
											c.playerEquipment[server.model.players.Player.playerHands])
									.toLowerCase());
			c.getItems().resetBonus();
			c.getOutStream().createFrame(34);
			c.getOutStream().writeWord(6);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(server.model.players.Player.playerHands);
			c.getOutStream().writeWord(0);
			c.getOutStream().writeByte(0);
			c.getItems().resetBonus();
			c.getItems().getBonus();
			c.getItems().writeBonus();
			c.getPA().requestUpdates();
			c.setAppearanceUpdateRequired(true);
			c.sendMessage("Your brawlers degrade..");
			c.brawlerFM = 0;
			c.SaveGame();
		}
	}

	public void handleEPDrops() {
		if (PlayerHandler.players[c.killerId] != null
				&& PlayerHandler.players[c.playerKilled] != null) {
			if (!PlayerHandler.players[c.killerId].connectedFrom
					.equals(PlayerHandler.players[c.playerKilled].connectedFrom)) {
				Client p = (Client) PlayerHandler.players[c.killerId];
				if (!c.connectedFrom.equalsIgnoreCase(p.connectedFrom)) {
					Client o = (Client) PlayerHandler.players[c.killerId];
					EarningPotential.giveBonusDrops(o, c);
				}
			}
		}
	}

	public void handleFightCavesWinNewOne() {
		c.getItems().addItem(19111, 1);
		c.sendMessage("Congratulations! You've completed all waves! Enjoy the new cape!");
		c.getPA().resetTzhaar();
		c.WinsSFF += 1; // achievements
		if (c.WinsSFF == 5) {
			c.sendMessage("You've completed achievement: Finish 5 TookHar-Kar minigames!");
			c.sendMessage("You've been awarded 5 spin tokens!");
			c.spinsLe += 5;
		}
	}

	public void handleGlory(int gloryId) {
		c.getDH().sendOption4("Edgeville", "Al Kharid", "Draynor Village",
				"Mage Bank");
		c.usingGlory = true;
	}

	public void handleJungleDemon() {
		c.sendMessage("It is highly recommended to use prayers.");
		c.sendMessage("Tank the goblins while killing the demon!");
		c.getPA().startTeleport(2378, 4689, c.playerId * 4, "modern");
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				// Client c, int npcType, int x, int y, int heightLevel, int
				// WalkingType, int HP, int maxHit, int attack, int defence,
				// boolean attackPlayer, boolean headIcon

				Server.npcHandler.spawnNpc(c, 1472, 2379, 4692, c.playerId * 4,
						1, 200, 34, 350, 400, true, false);
				Server.npcHandler.spawnNpc(c, 3663, 2377, 4693, c.playerId * 4,
						1, 200, 34, 350, 400, true, false);
				Server.npcHandler.spawnNpc(c, 3663, 2381, 4693, c.playerId * 4,
						1, 200, 34, 350, 400, true, false);
				container.stop();
				if (c.disconnected) {
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 6);
	}

	/*
	 * public void applyDead() { Client o = (Client)
	 * PlayerHandler.players[c.killerId]; //c.sendMessage("WAS HERE ONE TIME");
	 * c.respawnTimer = 15; c.isDead = false; resetDamageDone(); c.isAttacking =
	 * false; if (c.duelStatus != 6) { c.killerId = findKiller(); if (o != null)
	 * { o.hasGivenKS = false; c.hasGivenKS = false; o.hasSentDeathMessage =
	 * false; c.hasSentDeathMessage = false; c.playerKilled = c.playerId; if
	 * (o.duelStatus == 5) { o.duelStatus++; } if
	 * (c.connectedFrom.equalsIgnoreCase(o.connectedFrom) ||
	 * (PlayerHandler.players[c.playerId].connectedFrom == o.lastKilled &&
	 * c.duelStatus == 0)) {
	 * o.sendMessage("FARMING SUSPECTED (Errorcode: Same host! "
	 * +o.connectedFrom+"! No reward!"); c.faceUpdate(0); c.npcIndex = 0;
	 * c.playerIndex = 0; c.stopMovement(); if (c.duelStatus <= 4) {
	 * c.sendMessage("Oh dear you are dead! Your killstreak has been reset!");
	 * handleEPDrops(); c.killstreak = 0; c.totaldeath += 1; o.killstreak++;
	 * o.isAttacking = false; o.totalkills++; o.PlayKil += 1; if(o.PlayKil ==
	 * 10) { o.sendMessage("You've completed achievement: Kill 10 players!");
	 * o.sendMessage("You've been awarded 5 spin tokens!"); o.spinsLe += 5; }
	 * handleKSYell(); // o.pkinPoints += 1; //
	 * o.sendMessage("You recieved a Divination Point! Killstreak: "
	 * +o.killstreak+""); c.getPA().writeTabs(); o.getPA().writeTabs(); } else
	 * if (c.duelStatus != 6) { c.sendMessage("You have lost the duel!");
	 * c.getPA().writeTabs(); o.getPA().writeTabs(); }
	 * 
	 * c.hasFollower = -1; deathAnim(); c.specAmount = 10; c.getItems()
	 * .addSpecialBar(
	 * c.playerEquipment[server.model.players.Player.playerWeapon]); c.lastVeng
	 * = 0; c.vengOn = false; resetFollowers(); c.attackTimer = 10;
	 * removeAllWindows(); c.getTradeAndDuel().resetTrade(); // } } } } //} }
	 */
	public void handleKSYell() {
		Client o = (Client) PlayerHandler.players[c.killerId];
		/*
		 * if (c.connectedFrom.equalsIgnoreCase(o.connectedFrom) ||
		 * (PlayerHandler.players[c.playerId].connectedFrom == o.lastKilled &&
		 * c.duelStatus == 0)) { return; }
		 */
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client ALLPLAYERS = (Client) PlayerHandler.players[j];
				if (o.killstreak == 0 || o.killstreak == 1 || o.killstreak == 2
						|| o.killstreak == 3) {
					if (o.hasGivenKS == false) {
						ALLPLAYERS.sendMessage("<shad=6081134>[PvP]"
								+ Misc.optimizeText(o.playerName)
								+ " has defeated "
								+ Misc.optimizeText(c.playerName) + "!");
						o.pkinPoints += 1;
						o.sendMessage("You recieved a Pking Point! Killstreak: "
								+ o.killstreak + "");
						o.hasGivenKS = true;
					}
				}
				if (o.killstreak == 4 || o.killstreak == 5) {
					if (o.hasGivenKS == false) {
						ALLPLAYERS
								.sendMessage("<shad=13369497>[KILLSTREAK YELL]<shad=132833>"
										+ Misc.optimizeText(o.playerName)
										+ " is UNSTOPPABLE! Killstreak: "
										+ o.killstreak + "!");
						o.pkinPoints += 2;
						o.sendMessage("You recieved 2 bonus Pking Points! Killstreak: "
								+ o.killstreak + "");
						o.hasGivenKS = true;
					}
				} else {
					if (o.killstreak == 6 || o.killstreak == 7) {
						if (o.hasGivenKS == false) {
							ALLPLAYERS
									.sendMessage("<shad=13369497>[KILLSTREAK YELL]<shad=132833>"
											+ Misc.optimizeText(o.playerName)
											+ " is LEGENDARY! Killstreak: "
											+ o.killstreak + "!");
							o.pkinPoints += 4;
							o.sendMessage("You recieved 4 bonus Pking Points! Killstreak: "
									+ o.killstreak + "");
							o.hasGivenKS = true;
						}
					} else {
						if (o.killstreak == 7 || o.killstreak == 8
								|| o.killstreak == 9) {
							if (o.hasGivenKS == false) {
								ALLPLAYERS
										.sendMessage("<shad=13369497>[KILLSTREAK YELL]<shad=132833>"
												+ Misc.optimizeText(o.playerName)
												+ " is GODLIKE!! Killstreak: "
												+ o.killstreak + "!");
								o.pkinPoints += 7;
								o.killstreak = 0;
								o.totalkills += 1;
								o.sendMessage("You reached maximun killstreak, your killstreak has been reset.");
								c.getPA().writeTabs();
								o.getPA().writeTabs();
								handleEPDrops();
								o.lastKilled = PlayerHandler.players[c.playerId].connectedFrom;
								o.hasGivenKS = true;
								handleEPDrops();
								o.sendMessage("As a reward of your killstreak, you have gotten 40 EP & 7 Pking Points!");
								o.earningPotential += 40;
								if (o.earningPotential > 100) {
									o.earningPotential = 100;
								}
							}
						}
					}
				}
			}
		}
	}

	public void handleLoginText() {
		c.getPA().sendFrame126("Monster Teleport", 13037);
		c.getPA().sendFrame126("Minigame Teleport", 13047);
		c.getPA().sendFrame126("Boss Teleport", 13055);
		c.getPA().sendFrame126("Pking Teleport", 13063);
		c.getPA().sendFrame126("Skill Teleport", 13071);
		c.getPA().sendFrame126("Monster Teleport", 1300);
		c.getPA().sendFrame126("Minigame Teleport", 1325);
		c.getPA().sendFrame126("Boss Teleport", 1350);
		c.getPA().sendFrame126("Pking Teleport", 1382);
		c.getPA().sendFrame126("Skill Teleport", 1415);
		c.getPA().sendFrame126("City Teleport", 1454);
		c.getPA().sendFrame126("Pking Areas", 7457);
		c.getPA().sendFrame126("Miasmic Barrage", 13097);
		c.getPA().sendFrame126("Pking Areas", 13089);
		c.getPA().sendFrame126("City Teleport", 13081);
		c.getPA().sendFrame126("Monster Teleport", 30064);
		c.getPA().sendFrame126("Skilling Teleport", 30114);
	}

	public void handleStatus(int i, int i2, int i3) {
		// Sanity u so smart
	}

	public void handleWeaponStyle() {
		if (c.fightMode == 0) {
			c.getPA().sendFrame36(43, c.fightMode);
		} else if (c.fightMode == 1) {
			c.getPA().sendFrame36(43, 3);
		} else if (c.fightMode == 2) {
			c.getPA().sendFrame36(43, 1);
		} else if (c.fightMode == 3) {
			c.getPA().sendFrame36(43, 2);
		}
	}

	public void hitDragonClaws(final Client c, int damage) {
		if (!c.usingClaws) {
			return;
		}
		if (c.clawHit[0] <= 0) {
			getDragonClawHits(c, damage);
		}
		if (c.npcIndex > 0) {
			c.getCombat().applyNpcMeleeDamage(c.npcIndex, 1, c.clawHit[0]);
			c.getCombat().applyNpcMeleeDamage(c.npcIndex, 2, c.clawHit[1]);
		} else if (c.playerIndex > 0) {
			c.getCombat()
					.applyPlayerMeleeDamage(c.playerIndex, 1, c.clawHit[0]);
			c.getCombat()
					.applyPlayerMeleeDamage(c.playerIndex, 2, c.clawHit[1]);
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.npcIndex > 0) {
					c.getCombat().applyNpcMeleeDamage(c.npcIndex, 1,
							c.clawHit[2]);
					c.getCombat().applyNpcMeleeDamage(c.npcIndex, 2,
							c.clawHit[3]);
				} else if (c.playerIndex > 0) {
					c.getCombat().applyPlayerMeleeDamage(c.playerIndex, 1,
							c.clawHit[2]);
					c.getCombat().applyPlayerMeleeDamage(c.playerIndex, 2,
							c.clawHit[3]);
				}
				resetDragonHits(c);
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public boolean inPitsWait() {
		return c.getX() <= 2404 && c.getX() >= 2394 && c.getY() <= 5175
				&& c.getY() >= 5169;
	}

	public void interfaceWithInventory(int interfaceId, int inventoryId) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(248);
			c.getOutStream().writeWordA(interfaceId);
			c.getOutStream().writeWord(inventoryId);
			c.flushOutStream();
		}
	}

	public boolean isInPM(long l) {
		for (int i = 0; i < c.friends.length; i++) {
			if (c.friends[i] != 0) {
				if (l == c.friends[i]) {
					return true;
				}
			}
		}
		return false;
	}

	public void leaveDung(Client c2) { // handles dung leaving
		for (int k = 0; k < 25; k++) {
			c2.playerLevel[k] = c2.getPA().getLevelForXP(c2.playerXP[k]);
			c2.getPA().refreshSkill(k);
		}
		if (!c2.completed) {
			c2.getDungeoneering().giveXP(c2, true);
		} else {
			c2.getDungeoneering().giveXP(c2, false);
		}

		c2.sendMessage("You received " + c2.getDungeoneering().giveXP(c2, true)
				+ " Dungeoneering XP.");
		Server.npcHandler.killAllDungNPCs(c2); // kills dung npcs
		c2.hasFollower = -1;
		c2.specAmount = 10;
		c2.getItems().addSpecialBar(
				c2.playerEquipment[server.model.players.Player.playerWeapon]);
		c2.lastVeng = 0;
		c2.vengOn = false;
		c2.prayerId = -1;
		c2.getItems().deleteAllItems();
		c2.hasChoosenDung = false;
		c2.getPA().movePlayer(3081, 3421, 0);
		c2.InDung = false;
		c2.needstorelog = true;
		c2.isDead = false;
		c2.getPA().resetDamageDone();
		c2.getPA().resetFollowers();
		c2.attackTimer = 10;
		c2.getTradeAndDuel().resetTrade();
		c2.getPA().closeAllWindows();
		c2.getPA().removeAllItems();
		c2.hasChoosenDung = false;
		c2.getDungeoneering();
		server.model.players.content.skills.Dungeoneering.newDungeon(c2, false);
		c2.getItems().deleteAllItems();
		c2.getCombat().resetPlayerAttack();
		c2.resetWalkingQueue();
		// c2.setSidebarInterface(1, 639);
		return;
	}

	public void levelUp(final int skill) {
		c.calcCombat();
		if (Config.SOUND) {
			c.sendSound(c.getSound().LEVELUP, 100);
		}
		GabbesAchievements.writeAchievementTab(c);
		c.SaveGame();
		sendFrame126("Levels: " + getTotalLevel(), 13983);
		if (!c.task2[4] && c.getPA().getStats() >= 5) {
			c.task2[4] = true;
			c.sendMessage("You've completed the task: Obtain 5 level 99s!");
			c.TPoints += 2;
			c.achievementInterface("Obtain 5 level 99s!");
			c.sendMessage("You've received two Task Points! You now have a total of "
					+ c.TPoints + " points!");
		}
		switch (skill) {
		case 0:
			sendFrame126("Congratulations, you just advanced an attack level!",
					6248);
			sendFrame126("Your attack level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6249);
			c.sendMessage("Congratulations, you just advanced an attack level.");
			c.getPA().sendFrame126("Combat Level: " + c.getCombatLevel() + "",
					3983);
			sendFrame164(6247);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Attack!");
					}
				}
			}
			break;

		case 1:
			sendFrame126("Congratulations, you just advanced a defence level!",
					6254);
			sendFrame126("Your defence level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6255);
			c.sendMessage("Congratulations, you just advanced a defence level.");
			c.getPA().sendFrame126("Combat Level: " + c.getCombatLevel() + "",
					3983);
			sendFrame164(6253);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Defence!");
					}
				}
			}
			break;

		case 2:
			sendFrame126(
					"Congratulations, you just advanced a strength level!",
					6207);
			sendFrame126("Your strength level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6208);
			c.sendMessage("Congratulations, you just advanced a strength level.");
			c.getPA().sendFrame126("Combat Level: " + c.getCombatLevel() + "",
					3983);
			sendFrame164(6206);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Strength!");
					}
				}
			}
			break;

		case 3:
			sendFrame126(
					"Congratulations, you just advanced a hitpoints level!",
					6217);
			sendFrame126("Your hitpoints level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6218);
			c.sendMessage("Congratulations, you just advanced a hitpoints level.");
			c.getPA().sendFrame126("Combat Level: " + c.getCombatLevel() + "",
					3983);
			sendFrame164(6216);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Hitpoints!");
					}
				}
			}
			break;

		case 4:
			sendFrame126("Congratulations, you just advanced a ranged level!",
					5453);
			sendFrame126("Your ranged level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6114);
			c.sendMessage("Congratulations, you just advanced a ranging level.");
			c.getPA().sendFrame126("Combat Level: " + c.getCombatLevel() + "",
					3983);
			sendFrame164(4443);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Ranged!");
					}
				}
			}
			break;

		case 5:
			sendFrame126("Congratulations, you just advanced a prayer level!",
					6243);
			sendFrame126("Your prayer level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6244);
			c.sendMessage("Congratulations, you just advanced a prayer level.");
			c.getPA().sendFrame126("Combat Level: " + c.getCombatLevel() + "",
					3983);
			sendFrame164(6242);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Prayer!");
					}
				}
			}
			break;

		case 6:
			sendFrame126("Congratulations, you just advanced a magic level!",
					6212);
			sendFrame126("Your magic level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6213);
			c.sendMessage("Congratulations, you just advanced a magic level.");
			c.getPA().sendFrame126("Combat Level: " + c.getCombatLevel() + "",
					3983);
			sendFrame164(6211);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Magic!");
					}
				}
			}
			break;

		case 7:
			sendFrame126("Congratulations, you just advanced a cooking level!",
					6227);
			sendFrame126("Your cooking level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6228);
			c.sendMessage("Congratulations, you just advanced a cooking level.");
			sendFrame164(6226);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Cooking!");
					}
				}
			}
			break;

		case 8:
			sendFrame126(
					"Congratulations, you just advanced a woodcutting level!",
					4273);
			sendFrame126("Your woodcutting level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4274);
			c.sendMessage("Congratulations, you just advanced a woodcutting level.");
			sendFrame164(4272);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Woodcutting!");
					}
				}
			}
			break;

		case 9:
			sendFrame126(
					"Congratulations, you just advanced a fletching level!",
					6232);
			sendFrame126("Your fletching level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6233);
			c.sendMessage("Congratulations, you just advanced a fletching level.");
			sendFrame164(6231);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Fletching!");
					}
				}
			}
			break;

		case 10:
			sendFrame126("Congratulations, you just advanced a fishing level!",
					6259);
			sendFrame126("Your fishing level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6260);
			c.sendMessage("Congratulations, you just advanced a fishing level.");
			sendFrame164(6258);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Fishing!");
					}
				}
			}
			break;

		case 11:
			sendFrame126(
					"Congratulations, you just advanced a fire making level!",
					4283);
			sendFrame126("Your firemaking level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4284);
			c.sendMessage("Congratulations, you just advanced a fire making level.");
			sendFrame164(4282);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Firemaking!");
					}
				}
			}
			break;

		case 12:
			sendFrame126(
					"Congratulations, you just advanced a crafting level!",
					6264);
			sendFrame126("Your crafting level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6265);
			c.sendMessage("Congratulations, you just advanced a crafting level.");
			sendFrame164(6263);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Crafting!");
					}
				}
			}
			break;

		case 13:
			sendFrame126(
					"Congratulations, you just advanced a smithing level!",
					6222);
			sendFrame126("Your smithing level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6223);
			c.sendMessage("Congratulations, you just advanced a smithing level.");
			sendFrame164(6221);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Smithing!");
					}
				}
			}
			break;

		case 14:
			sendFrame126("Congratulations, you just advanced a mining level!",
					4417);
			sendFrame126("Your mining level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4438);
			c.sendMessage("Congratulations, you just advanced a mining level.");
			sendFrame164(4416);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Mining!");
					}
				}
			}
			break;

		case 15:
			sendFrame126(
					"Congratulations, you just advanced a herblore level!",
					6238);
			sendFrame126("Your herblore level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 6239);
			c.sendMessage("Congratulations, you just advanced a herblore level.");
			sendFrame164(6237);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Herblore!");
					}
				}
			}
			break;

		case 16:
			sendFrame126("Congratulations, you just advanced a agility level!",
					4278);
			sendFrame126("Your agility level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4279);
			c.sendMessage("Congratulations, you just advanced an agility level.");
			sendFrame164(4277);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Agility!");
					}
				}
			}
			break;

		case 17:
			sendFrame126(
					"Congratulations, you just advanced a thieving level!",
					4263);
			sendFrame126("Your theiving level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4264);
			c.sendMessage("Congratulations, you just advanced a thieving level.");
			sendFrame164(4261);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Thieving!");
					}
				}
			}
			break;

		case 18:
			sendFrame126("Congratulations, you just advanced a slayer level!",
					12123);
			sendFrame126("Your slayer level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 12124);
			c.sendMessage("Congratulations, you just advanced a slayer level.");
			sendFrame164(12122);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Slayer!");
					}
				}
			}
			break;

		case 20:
			sendFrame126(
					"Congratulations, you just advanced a runecrafting level!",
					4268);
			sendFrame126("Your runecrafting level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations, you just advanced a runecrafting level.");
			sendFrame164(4267);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Runecrafting!");
					}
				}
			}
			break;

		case 22:
			c.sendMessage("Congratulations, you just advanced a hunter level!");
			c.sendMessage("Your hunter level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".");
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Hunter!");
					}
				}
			}
			break;

		case 21:
			sendFrame126(
					"Congratulations, you just advanced a Summoning level!",
					4268);
			sendFrame126("Your Summoning level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 4269);
			c.sendMessage("Congratulations, you just advanced a Summoning level.");
			sendFrame164(4267);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Summoning!");
					}
				}
			}
			break;

		case 23:
			sendFrame126(
					"Congratulations, you just advanced a Construction level!",
					22602);
			sendFrame126("Your Construction level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 22603);
			c.sendMessage("Congratulations, you just advanced a Construction level!");
			sendFrame164(22602);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Construction!");
					}
				}
			}
			break;

		case 24:
			sendFrame126(
					"Congratulations, you just advanced a Dungeoneering level!",
					12123);
			sendFrame126("Your Dungeoneering level is now "
					+ getLevelForXP(c.playerXP[skill]) + ".", 12124);
			c.sendMessage("Congratulations, you just advanced a Dungeoneering level.");
			sendFrame164(12122);
			if (getLevelForXP(c.playerXP[skill]) == 99) {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client) PlayerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Server] <shad=15536940>"
								+ Misc.optimizeText(c.playerName)
								+ " has just achieved level 99 Dungeoneering!");
					}
				}
			}
			break;
		}

		c.dialogueAction = 0;
		c.nextChat = 0;
		/*
		 * if(!c.atSmithingArea()) { if(getLevelForXP(c.playerXP[skill]) > 60 &&
		 * getLevelForXP(c.playerXP[skill]) < 95) {
		 * GabbesAchievements.writeAchievementTab(c); c.SaveGame();
		 * World.getWorld().submit(new Events(1000) { public void execute() {
		 * if(c.disconnected) { this.stop(); return; } walkableInterface(1000);
		 * sendFrame126(""+getLevelForXP(c.playerXP[skill])+"", 1002); //
		 * showInterface(44195); /*if(c.levelUpTimer < 4) { c.levelUpTimer += 1;
		 * walkableInterface(1000);
		 * sendFrame126(""+getLevelForXP(c.playerXP[skill])+"", 1002);
		 * if(c.levelUpTimer == 3 || c.levelUpTimer > 3) { this.stop(); return;
		 * }* this.stop(); return;
		 * 
		 * // } } }); } else { if(getLevelForXP(c.playerXP[skill]) > 94) {
		 * World.getWorld().submit(new Events(1000) { public void execute() {
		 * if(c.disconnected) { this.stop(); return; } walkableInterface(1003);
		 * sendFrame126(""+getLevelForXP(c.playerXP[skill])+"", 1005); //
		 * showInterface(44195); /* if(c.levelUpTimer < 4) { c.levelUpTimer +=
		 * 1; walkableInterface(1003);
		 * sendFrame126(""+getLevelForXP(c.playerXP[skill])+"", 1005);
		 * if(c.levelUpTimer == 3 || c.levelUpTimer > 3) { this.stop(); return;
		 * }* this.stop(); return;
		 * 
		 * 
		 * // } } }); } } }
		 */
	}

	public void loadAnnouncements() {
		try {
			loadIni();

			if (p.getProperty("announcement1").length() > 0) {
				c.sendMessage(p.getProperty("announcement1"));
			}
			if (p.getProperty("announcement2").length() > 0) {
				c.sendMessage(p.getProperty("announcement2"));
			}
			if (p.getProperty("announcement3").length() > 0) {
				c.sendMessage(p.getProperty("announcement3"));
			}
		} catch (Exception e) {
		}
	}

	private void loadIni() {
		try {
			p.load(new FileInputStream("Data/Announcements.ini"));
		} catch (Exception e) {
		}
	}

	// }
	public void loadPM(long playerName, int world) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			if (world != 0) {
				world += 9;
			} else if (!Config.WORLD_LIST_FIX) {
				world += 1;
			}
			c.getOutStream().createFrame(50);
			c.getOutStream().writeQWord(playerName);
			c.getOutStream().writeByte(world);
			c.flushOutStream();
		}
		// }
	}

	// }
	/**
	 * Private Messaging
	 **/
	public void logIntoPM() {
		setPrivateMessaging(2);
		for (int i1 = 0; i1 < Config.MAX_PLAYERS; i1++) {
			Player p = PlayerHandler.players[i1];
			if (p != null && p.isActive) {
				Client o = (Client) p;
				if (o != null) {
					o.getPA().updatePM(c.playerId, 1);
				}
			}
		}
		boolean pmLoaded = false;

		for (int i = 0; i < c.friends.length; i++) {
			if (c.friends[i] != 0) {
				for (int i2 = 1; i2 < Config.MAX_PLAYERS; i2++) {
					Player p = PlayerHandler.players[i2];
					if (p != null
							&& p.isActive
							&& Misc.playerNameToInt64(p.playerName) == c.friends[i]) {
						Client o = (Client) p;
						if (o != null) {
							if (c.playerRights >= 2
									|| p.privateChat == 0
									|| (p.privateChat == 1 && o
											.getPA()
											.isInPM(Misc
													.playerNameToInt64(c.playerName)))) {
								loadPM(c.friends[i], 1);
								pmLoaded = true;
							}
							break;
						}
					}
				}
				if (!pmLoaded) {
					loadPM(c.friends[i], 0);
				}
				pmLoaded = false;
			}
			for (int i1 = 1; i1 < Config.MAX_PLAYERS; i1++) {
				Player p = PlayerHandler.players[i1];
				if (p != null && p.isActive) {
					Client o = (Client) p;
					if (o != null) {
						o.getPA().updatePM(c.playerId, 1);
					}
				}
			}
		}
	}

	/**
	 * Magic on items
	 **/
	public void magicOnItems(int slot, int itemId, int spellId) {
		if (c.InDung() || c.inCwWait == true || c.inCwGame == true
				|| c.inDungBossRoom()) {
			c.sendMessage("You can't do this here!");
			return;
		}
		if (c.inFoGGame) {
			return;
		}
		c.getDungeoneering();
		if (server.model.players.content.skills.Dungeoneering.antiSmuggle(c,
				itemId)) {
			return;
		}
		switch (spellId) {
		case 1162: // low alch
			if (System.currentTimeMillis() - c.alchDelay > 1000) {
				if (!c.getCombat().checkMagicReqs(49)) {
					break;
				}
				if (itemId == 995) {
					c.sendMessage("You can't alch coins.");
					break;
				}
				c.getItems().deleteItem(itemId, slot, 1);
				c.getItems().addItem(995,
						c.getShops().getItemShopValue(itemId) / 3);
				c.startAnimation(c.MAGIC_SPELLS[49][2]);
				c.gfx100(c.MAGIC_SPELLS[49][3]);
				c.alchDelay = System.currentTimeMillis();
				sendFrame106(6);
				addSkillXP(c.MAGIC_SPELLS[49][7] * Config.MAGIC_EXP_RATE, 6);
				refreshSkill(6);
			}
			break;

		case 1178: // high alch
			if (System.currentTimeMillis() - c.alchDelay > 2000) {
				if (!c.getCombat().checkMagicReqs(50)) {
					break;
				}
				if (itemId == 995) {
					c.sendMessage("You can't alch coins.");
					break;
				}
				c.getItems().deleteItem(itemId, slot, 1);
				c.getItems().addItem(995,
						(int) (c.getShops().getItemShopValue(itemId) * .75));
				c.startAnimation(c.MAGIC_SPELLS[50][2]);
				c.gfx100(c.MAGIC_SPELLS[50][3]);
				c.alchDelay = System.currentTimeMillis();
				sendFrame106(6);
				addSkillXP(c.MAGIC_SPELLS[50][7] * Config.MAGIC_EXP_RATE, 6);
				refreshSkill(6);
			}
			break;
		}
	}

	public void moveAdmin() { // uusing this for admins
		c.playerLevel[1] = getLevelForXP(c.playerXP[1]);
		c.playerLevel[2] = getLevelForXP(c.playerXP[2]);
		c.playerLevel[3] = getLevelForXP(c.playerXP[3]);
		c.playerLevel[4] = getLevelForXP(c.playerXP[4]);
		c.playerLevel[5] = getLevelForXP(c.playerXP[5]);
		c.playerLevel[6] = getLevelForXP(c.playerXP[6]);
		refreshSkill(1);
		refreshSkill(2);
		refreshSkill(3);
		refreshSkill(4);
		refreshSkill(5);
		refreshSkill(6);
		movePlayer(3087, 3494, 0);
		// resetTzhaar();
		c.hasFollower = -1;
		c.specAmount = 10;
		c.getItems().addSpecialBar(
				c.playerEquipment[server.model.players.Player.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;
		c.isDead = false;
		resetDamageDone();
		resetFollowers();
		c.attackTimer = 10;
		removeAllWindows();
		c.getTradeAndDuel().resetTrade();
		closeAllWindows();
		// deathAnim();
		c.sendMessage("You've died as an Admin! Teleported safely.");
		return;
	}

	public void moveBarb() { // handles what happens when dying in barbarien
								// defence
		if (c.inBarbDef) {
			c.hasFollower = -1;
			c.specAmount = 10;
			c.getItems()
					.addSpecialBar(
							c.playerEquipment[server.model.players.Player.playerWeapon]);
			c.lastVeng = 0;
			c.vengOn = false;
			resetFollowers();
			c.attackTimer = 10;
			removeAllWindows();
			c.getTradeAndDuel().resetTrade();
			closeAllWindows();
			Server.barbDefence.endGame(c, false);
			c.playerLevel[1] = getLevelForXP(c.playerXP[1]);
			c.playerLevel[2] = getLevelForXP(c.playerXP[2]);
			c.playerLevel[3] = getLevelForXP(c.playerXP[3]);
			c.playerLevel[4] = getLevelForXP(c.playerXP[4]);
			c.playerLevel[5] = getLevelForXP(c.playerXP[5]);
			c.playerLevel[6] = getLevelForXP(c.playerXP[6]);
			refreshSkill(1);
			refreshSkill(2);
			refreshSkill(3);
			refreshSkill(4);
			refreshSkill(5);
			refreshSkill(6);
			c.getCombat().resetPlayerAttack();
			c.resetWalkingQueue();
			c.isDead = false;
			resetDamageDone();
			return;
		}
	}

	public void moveCheck(int xMove, int yMove) {
		movePlayer(c.absX + xMove, c.absY + yMove, c.heightLevel);
	}

	public void moveFunPK() { // handles funpk death
		c.hasFollower = -1;
		c.specAmount = 10;
		c.getItems().addSpecialBar(
				c.playerEquipment[server.model.players.Player.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;
		resetFollowers();
		c.attackTimer = 10;
		c.isDead = false;
		resetDamageDone();
		c.skullTimer = 0;
		removeAllWindows();
		c.getTradeAndDuel().resetTrade();
		closeAllWindows();
		movePlayer(3091, 3505, 0);

		c.playerLevel[1] = getLevelForXP(c.playerXP[1]);
		c.playerLevel[2] = getLevelForXP(c.playerXP[2]);
		c.playerLevel[3] = getLevelForXP(c.playerXP[3]);
		c.playerLevel[4] = getLevelForXP(c.playerXP[4]);
		c.playerLevel[5] = getLevelForXP(c.playerXP[5]);
		c.playerLevel[6] = getLevelForXP(c.playerXP[6]);
		refreshSkill(1);
		refreshSkill(2);
		refreshSkill(3);
		refreshSkill(4);
		refreshSkill(5);
		refreshSkill(6);
		return;
	}

	public void movePc() { // using this for pest Control by gabbe
		c.hasFollower = -1;
		c.specAmount = 10;
		c.getItems().addSpecialBar(
				c.playerEquipment[server.model.players.Player.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;
		c.isDead = false;
		resetDamageDone();
		resetFollowers();
		c.attackTimer = 10;
		removeAllWindows();
		c.getTradeAndDuel().resetTrade();
		closeAllWindows();
		movePlayer(2658, 2610, c.heightLevel);
		c.playerLevel[1] = getLevelForXP(c.playerXP[1]);
		c.playerLevel[2] = getLevelForXP(c.playerXP[2]);
		c.playerLevel[3] = getLevelForXP(c.playerXP[3]);
		c.playerLevel[4] = getLevelForXP(c.playerXP[4]);
		c.playerLevel[5] = getLevelForXP(c.playerXP[5]);
		c.playerLevel[6] = getLevelForXP(c.playerXP[6]);
		refreshSkill(1);
		refreshSkill(2);
		refreshSkill(3);
		refreshSkill(4);
		refreshSkill(5);
		refreshSkill(6);
		return;
	}

	public void movePlayer(int x, int y, int h) {
		GabbesAchievements.writeAchievementTab(c);
		// c.getPA().resetFollow();
		c.getPA().resetFishing();
		// c.getMining().resetMining();
		c.isCooking = false;
		c.playerIsFiremaking = false;
		c.resetWalkingQueue();
		c.teleportToX = x;
		c.dialogueAction = -1;
		c.teleportToY = y;
		c.heightLevel = h;
		requestUpdates();
		c.updateWalkEntities();
		c.updateTimer = 45;
		c.getTimers().updatePlayerLook(c);
	}

	public void moveSafe() { // using nthis for jad by gabbe
		c.playerLevel[1] = getLevelForXP(c.playerXP[1]);
		c.playerLevel[2] = getLevelForXP(c.playerXP[2]);
		c.playerLevel[3] = getLevelForXP(c.playerXP[3]);
		c.playerLevel[4] = getLevelForXP(c.playerXP[4]);
		c.playerLevel[5] = getLevelForXP(c.playerXP[5]);
		c.playerLevel[6] = getLevelForXP(c.playerXP[6]);
		refreshSkill(1);
		refreshSkill(2);
		refreshSkill(3);
		refreshSkill(4);
		refreshSkill(5);
		refreshSkill(6);
		// movePlayer(3087, 3494, 0);
		if (c.trade11 > 0) {
			c.getPA().movePlayer(3218, 3437, 0);
			c.sendMessage("You've been teleported away safely.");
		} else {
			resetTzhaar();
		}

		c.hasFollower = -1;
		c.specAmount = 10;
		c.getItems().addSpecialBar(
				c.playerEquipment[server.model.players.Player.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;
		resetFollowers();
		c.isDead = false;
		resetDamageDone();
		c.attackTimer = 10;
		removeAllWindows();
		c.getTradeAndDuel().resetTrade();
		closeAllWindows();
		// deathAnim();
		return;
	}

	/**
	 * MulitCombat icon
	 * 
	 * @param i1
	 *            0 = off 1 = on
	 */
	public void multiWay(int i1) {
		// synchronized(c) {
		c.outStream.createFrame(61);
		c.outStream.writeByte(i1);
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}

	/**
	 * Objects, add and remove
	 **/
	public void object(int objectId, int objectX, int objectY, int face,
			int objectType) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(objectY - (c.getMapRegionY() * 8));
			c.getOutStream().writeByteC(objectX - (c.getMapRegionX() * 8));
			c.getOutStream().createFrame(101);
			c.getOutStream().writeByteC((objectType << 2) + (face & 3));
			c.getOutStream().writeByte(0);

			if (objectId != -1) { // removing
				c.getOutStream().createFrame(151);
				c.getOutStream().writeByteS(0);
				c.getOutStream().writeWordBigEndian(objectId);
				c.getOutStream().writeByteS((objectType << 2) + (face & 3));
			}
			c.flushOutStream();
		}
	}

	public void objectAnim(int X, int Y, int animationID, int tileObjectType,
			int orientation) {
		for (Player p : PlayerHandler.players) {
			if (p != null) {
				Client players = (Client) p;
				if (players.distanceToPoint(X, Y) <= 25) {
					players.getPA().createPlayersObjectAnim(X, Y, animationID,
							tileObjectType, orientation);
				}
			}
		}
	}

	private void objectToRemove(int X, int Y) {
		object(-1, X, Y, 10, 10);
	}

	private void objectToRemove2(int X, int Y) {
		object(-1, X, Y, -1, 0);
	}

	/**
	 * Open bank
	 **/
	public void openUpBank(int tab) {
		if (c.storing) { // cheaphax DONT REMOVE
			c.Summoning().store();
			return;
		}
		if (c.searchTerm == "FLAG")
			return;
		if (tab != -1)
			c.searchTerm = "N/A";
		// synchronized(c) {
		if (c.inDuelScreen) {
			Client o = (Client) PlayerHandler.players[c.duelingWith];
			if (o != null) {
				c.getTradeAndDuel().declineDuel();
			}
		}
		if (!c.enterdBankpin && c.hasBankPin && Config.ALLOWPINS) {
			c.getBankPin().openPin();
			c.openBank = true;
			return;
		}
		if (c.inWild()) {
			c.sendMessage("You can't bank here!");
			return;
		}
		if (c.InDung() || c.inDungBossRoom()) {
			c.sendMessage("You can't bank here!");
			return;
		}
		if (c.playerIsWoodcutting) {
			Woodcutting.resetWoodcutting(c);
		}

		if (c.getOutStream() != null && c != null) {

			c.bankingTab = tab;
			sendTabs();
			if (c.bankingTab == 0) {
				c.bankingItems = c.bankItems;
				c.bankingItemsN = c.bankItemsN;
			}
			if (c.bankingTab == 1) {
				c.bankingItems = c.bankItems1;
				c.bankingItemsN = c.bankItems1N;
			}
			if (c.bankingTab == 2) {
				c.bankingItems = c.bankItems2;
				c.bankingItemsN = c.bankItems2N;
			}
			if (c.bankingTab == 3) {
				c.bankingItems = c.bankItems3;
				c.bankingItemsN = c.bankItems3N;
			}
			if (c.bankingTab == 4) {
				c.bankingItems = c.bankItems4;
				c.bankingItemsN = c.bankItems4N;
			}
			if (c.bankingTab == 5) {
				c.bankingItems = c.bankItems5;
				c.bankingItemsN = c.bankItems5N;
			}
			if (c.bankingTab == 6) {
				c.bankingItems = c.bankItems6;
				c.bankingItemsN = c.bankItems6N;
			}
			if (c.bankingTab == 7) {
				c.bankingItems = c.bankItems7;
				c.bankingItemsN = c.bankItems7N;
			}
			if (c.bankingTab == 8) {
				c.bankingItems = c.bankItems8;
				c.bankingItemsN = c.bankItems8N;
			}
			sendFrame126("The bank of Divination of Gods.", 5383);
			c.getItems().resetItems(5064);
			c.getItems().rearrangeBank();
			c.getItems().resetBank();
			c.getItems().resetTempItems();
			c.getOutStream().createFrame(248);
			c.getOutStream().writeWordA(5292);
			c.getOutStream().writeWord(5063);
			c.isBanking = true;
			c.stopPlayerSkill = true;
			c.flushOutStream();
		}
	}

	public void otherBank(Client c, Client o) {
		if (o == c || o == null || c == null) {
			return;
		}

		int[] bankTabItems = new int[Config.BANK_SIZE * 9];
		System.arraycopy(o.bankItems, 0, bankTabItems, 0, o.bankItems.length);
		System.arraycopy(o.bankItems1, 0, bankTabItems, o.bankItems.length,
				o.bankItems1.length);
		System.arraycopy(o.bankItems2, 0, bankTabItems, o.bankItems1.length,
				o.bankItems2.length);
		System.arraycopy(o.bankItems3, 0, bankTabItems, o.bankItems2.length,
				o.bankItems3.length);
		System.arraycopy(o.bankItems4, 0, bankTabItems, o.bankItems3.length,
				o.bankItems4.length);
		System.arraycopy(o.bankItems5, 0, bankTabItems, o.bankItems4.length,
				o.bankItems5.length);
		System.arraycopy(o.bankItems6, 0, bankTabItems, o.bankItems5.length,
				o.bankItems6.length);
		System.arraycopy(o.bankItems7, 0, bankTabItems, o.bankItems6.length,
				o.bankItems7.length);
		System.arraycopy(o.bankItems8, 0, bankTabItems, o.bankItems7.length,
				o.bankItems8.length);

		int[] bankTabItemsN = new int[Config.BANK_SIZE * 9];
		System.arraycopy(o.bankItemsN, 0, bankTabItemsN, 0, o.bankItemsN.length);
		System.arraycopy(o.bankItems1N, 0, bankTabItemsN, o.bankItemsN.length,
				o.bankItems1N.length);
		System.arraycopy(o.bankItems2N, 0, bankTabItemsN, o.bankItems1N.length,
				o.bankItems2N.length);
		System.arraycopy(o.bankItems3N, 0, bankTabItemsN, o.bankItems2N.length,
				o.bankItems3N.length);
		System.arraycopy(o.bankItems4N, 0, bankTabItemsN, o.bankItems3N.length,
				o.bankItems4N.length);
		System.arraycopy(o.bankItems5N, 0, bankTabItemsN, o.bankItems4N.length,
				o.bankItems5N.length);
		System.arraycopy(o.bankItems6N, 0, bankTabItemsN, o.bankItems5N.length,
				o.bankItems6N.length);
		System.arraycopy(o.bankItems7N, 0, bankTabItemsN, o.bankItems6N.length,
				o.bankItems7N.length);
		System.arraycopy(o.bankItems8N, 0, bankTabItemsN, o.bankItems7N.length,
				o.bankItems8N.length);

		/* backup player A main tab */
		int[] backupItems = Arrays.copyOf(c.bankItems, c.bankItems.length);
		int[] backupItemsN = Arrays.copyOf(c.bankItemsN, c.bankItemsN.length);
		/* fill player A's main tab with player B's main tab */
		c.bankItems = Arrays.copyOf(bankTabItems, bankTabItems.length);
		c.bankItemsN = Arrays.copyOf(bankTabItemsN, bankTabItemsN.length);
		openUpBank(0);
		/* restore player A main tab */
		c.bankItems = Arrays.copyOf(backupItems, backupItems.length);
		c.bankItemsN = Arrays.copyOf(backupItemsN, backupItemsN.length);

	}

	public void otherInv(Client c, Client o) {
		if (o == c || o == null || c == null) {
			return;
		}

		for (int i = 0; i < o.playerItems.length; i++) {
			backupInvItems[i] = c.playerItems[i];
			c.playerItemsN[i] = c.playerItemsN[i];
			c.playerItemsN[i] = o.playerItemsN[i];
			c.playerItems[i] = o.playerItems[i];
		}

		c.getItems().updateInventory();

		for (int i = 0; i < o.playerItems.length; i++) {
			c.playerItemsN[i] = backupInvItemsN[i];
			c.playerItems[i] = backupInvItems[i];
		}
	}

	/**
	 * Following
	 **/
	public void Player() {
		if (PlayerHandler.players[c.followId] == null
				|| PlayerHandler.players[c.followId].isDead) {
			c.getPA().resetFollow();
			return;
		}
		if (c.freezeTimer > 0) {
			return;
		}
		int otherX = PlayerHandler.players[c.followId].getX();
		int otherY = PlayerHandler.players[c.followId].getY();
		c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		c.goodDistance(otherX, otherY, c.getX(), c.getY(), 2);
		boolean bowDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 6);
		boolean rangeWeaponDistance = c.goodDistance(otherX, otherY, c.getX(),
				c.getY(), 2);
		boolean sameSpot = (c.absX == otherX && c.absY == otherY);
		if (!c.goodDistance(otherX, otherY, c.getX(), c.getY(), 25)) {
			c.followId = 0;
			c.getPA().resetFollow();
			return;
		}
		c.faceUpdate(c.followId + 32768);
		if ((c.usingBow || c.mageFollow || c.autocastId > 0
				&& (c.npcIndex > 0 || c.playerIndex > 0))
				&& bowDistance && !sameSpot) {
			c.stopMovement();
			return;
		}
		if (c.usingRangeWeapon && rangeWeaponDistance && !sameSpot
				&& (c.npcIndex > 0 || c.playerIndex > 0)) {
			c.stopMovement();
			return;
		}
		if (c.goodDistance(otherX, otherY, c.getX(), c.getY(), 1) && !sameSpot) {
			return;
		}
		c.outStream.createFrame(174);
		boolean followPlayer = c.followId > 0;
		if (c.freezeTimer <= 0) {
			if (followPlayer) {
				c.outStream.writeWord(c.followId);
			} else {
				c.outStream.writeWord(c.followId2);
			}
		} else {
			c.outStream.writeWord(0);
		}

		if (followPlayer) {
			c.outStream.writeByte(1);
		} else {
			c.outStream.writeByte(0);
		}
		if (c.usingBow && c.playerIndex > 0) {
			c.followDistance = 5;
		} else if (c.usingRangeWeapon && c.playerIndex > 0) {
			c.followDistance = 3;
		} else if (c.spellId > 0 && c.playerIndex > 0) {
			c.followDistance = 5;
		} else {
			c.followDistance = 1;
		}
		c.outStream.writeWord(c.followDistance);
	}

	public boolean playerNameExists(String name) {
		try {
			File names = new File("./Data/characters/" + name + ".txt");
			if (names.exists()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void playerWalk(int x, int y) { // clipped npcs
		PathFinder.getPathFinder().findRoute(c, x, y, true, 1, 1);
	}

	/**
	 * Drink AntiPosion Potions
	 * 
	 * @param itemId
	 *            The itemId
	 * @param itemSlot
	 *            The itemSlot
	 * @param newItemId
	 *            The new item After Drinking
	 * @param healType
	 *            The type of poison it heals
	 */
	public void potionPoisonHeal(int itemId, int itemSlot, int newItemId,
			int healType) {
		c.attackTimer = c
				.getCombat()
				.getAttackDelay(
						c.getItems()
								.getItemName(
										c.playerEquipment[server.model.players.Player.playerWeapon])
								.toLowerCase());
		if (c.duelRule[5]) {
			c.sendMessage("Potions has been disabled in this duel!");
			return;
		}
		if (!c.isDead && System.currentTimeMillis() - c.foodDelay > 2000) {
			if (c.getItems().playerHasItem(itemId, 1, itemSlot)) {
				c.sendMessage("You drink the "
						+ c.getItems().getItemName(itemId).toLowerCase() + ".");
				c.foodDelay = System.currentTimeMillis();
				// Actions
				if (healType == 1) {
					// Cures The Poison
				} else if (healType == 2) {
					// Cures The Poison + protects from getting poison again
				}
				c.startAnimation(0x33D);
				c.getItems().deleteItem(itemId, itemSlot, 1);
				c.getItems().addItem(newItemId, 1);
				requestUpdates();
			}
		}
	}

	public void processTeleport() {

		if (c.inRandomEvent() && RandomEvents.canTeleFromRandom == false) {
			c.sendMessage("Please finnish the random event first.");
			return;
		}
		if (c.inCwWait) {
			c.sendMessage("To leave, use the portal!");
			return;
		}
		GabbesAchievements.writeAchievementTab(c);
		// c.getPA().resetFollow();
		if (c.inBarbDef == true) {
			c.getPA().moveBarb();
			return;
		}
		fixBugs();
		c.teleportToX = c.teleX;
		c.teleportToY = c.teleY;
		c.dialogueAction = -1;
		c.playerStandIndex = 0x328;
		c.playerTurnIndex = 0x337;
		c.playerWalkIndex = 0x333;
		c.playerTurn180Index = 0x334;
		c.playerTurn90CWIndex = 0x335;
		c.playerTurn90CCWIndex = 0x336;
		c.playerRunIndex = 0x338;
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
		c.updateWalkEntities();
		c.heightLevel = c.teleHeight;
		if (c.teleEndAnimation > 0) {
			c.startAnimation(c.teleEndAnimation);
			c.updateWalkEntities();
		}
		if (c.teleEndGfx > 0) {
			c.gfx0(c.teleEndGfx);
			c.updateWalkEntities();
		}
	}

	public int randomBarrows() {
		return Barrows[(int) (Math.random() * Barrows.length)];
	}

	public int randomCrystal() {
		return Crystal[(int) (Math.random() * Crystal.length)];
	}

	public int randomMusic() {
		return randomMusics[(int) (Math.random() * randomMusics.length)];
	}

	public int randomPots() {
		return Pots[(int) (Math.random() * Pots.length)];
	}

	public int randomRunes() {
		return Runes[(int) (Math.random() * Runes.length)];
	}

	public void refillSupplies() {
		if (this.c.hasFollower == 3594) {
			this.c.rockTailCount = 30;
			this.c.sendMessage("Your familliar has been filled with 30 Rocktails");
		}

		if (this.c.hasFollower == 3590) {
			this.c.rockTailCount = 20;
			this.c.sendMessage("Your familliar has been filled with 30 Rocktails");
		}

		if (this.c.hasFollower == 3596) {
			this.c.rockTailCount = 30;
			this.c.sendMessage("Your familliar has been filled with 15 Rocktails");
		}

	}

	public void refreshSkill(int i) {
		if (!c.task2[4] && c.getPA().getStats() >= 5) {
			c.task2[4] = true;
			c.sendMessage("You've completed the task: Obtain 5 level 99s!");
			c.TPoints += 2;
			c.achievementInterface("Obtain 5 level 99s!");
			c.sendMessage("You've received two Task Points! You now have a total of "
					+ c.TPoints + " points!");
		}
		int attackLeft = (getXPForLevel(getLevelForXP(c.playerXP[0]) + 1) - c.playerXP[0]);
		int defenceLeft = (getXPForLevel(getLevelForXP(c.playerXP[1]) + 1) - c.playerXP[1]);
		int strengthLeft = (getXPForLevel(getLevelForXP(c.playerXP[2]) + 1) - c.playerXP[2]);
		int hitpointsLeft = (getXPForLevel(getLevelForXP(c.playerXP[3]) + 1) - c.playerXP[3]);
		int rangeLeft = (getXPForLevel(getLevelForXP(c.playerXP[4]) + 1) - c.playerXP[4]);
		int prayerLeft = (getXPForLevel(getLevelForXP(c.playerXP[5]) + 1) - c.playerXP[5]);
		int mageLeft = (getXPForLevel(getLevelForXP(c.playerXP[6]) + 1) - c.playerXP[6]);
		int cookingLeft = (getXPForLevel(getLevelForXP(c.playerXP[7]) + 1) - c.playerXP[7]);
		int woodcuttingLeft = (getXPForLevel(getLevelForXP(c.playerXP[8]) + 1) - c.playerXP[8]);
		int fletchingLeft = (getXPForLevel(getLevelForXP(c.playerXP[9]) + 1) - c.playerXP[9]);
		int firemakingLeft = (getXPForLevel(getLevelForXP(c.playerXP[11]) + 1) - c.playerXP[11]);
		int craftingLeft = (getXPForLevel(getLevelForXP(c.playerXP[12]) + 1) - c.playerXP[12]);
		int smithingLeft = (getXPForLevel(getLevelForXP(c.playerXP[13]) + 1) - c.playerXP[13]);
		int miningLeft = (getXPForLevel(getLevelForXP(c.playerXP[14]) + 1) - c.playerXP[14]);
		int herbloreLeft = (getXPForLevel(getLevelForXP(c.playerXP[15]) + 1) - c.playerXP[15]);
		int agilityLeft = (getXPForLevel(getLevelForXP(c.playerXP[16]) + 1) - c.playerXP[16]);
		int thievingLeft = (getXPForLevel(getLevelForXP(c.playerXP[17]) + 1) - c.playerXP[17]);
		int slayerLeft = (getXPForLevel(getLevelForXP(c.playerXP[18]) + 1) - c.playerXP[18]);
		int farmingLeft = (getXPForLevel(getLevelForXP(c.playerXP[19]) + 1) - c.playerXP[19]);
		int runecraftLeft = (getXPForLevel(getLevelForXP(c.playerXP[20]) + 1) - c.playerXP[20]);
		int summoningLeft = (getXPForLevel(getLevelForXP(c.playerXP[21]) + 1) - c.playerXP[21]);
		int hunterLeft = (getXPForLevel(getLevelForXP(c.playerXP[22]) + 1) - c.playerXP[22]);
		int constructionLeft = (getXPForLevel(getLevelForXP(c.playerXP[23]) + 1) - c.playerXP[23]);
		int dungeoneeringLeft = (getXPForLevel(getLevelForXP(c.playerXP[24]) + 1) - c.playerXP[23]);
		switch (i) {
		case 0:// atack
			sendFrame126("" + c.playerLevel[0] + "", 4004);
			sendFrame126("" + getLevelForXP(c.playerXP[0]) + "", 4005);
			sendFrame126("" + c.playerXP[0] + "", 4044);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[0]) + 1)
					+ "", 4045);
			sendFrame126("" + attackLeft + "", 18792);
			sendFrame126(c.playerLevel[0] + "/" + getLevelForXP(c.playerXP[0]),
					18790);
			break;

		case 1:// defence
			sendFrame126("" + c.playerLevel[1] + "", 4008);
			sendFrame126("" + getLevelForXP(c.playerXP[1]) + "", 4009);
			sendFrame126("" + c.playerXP[1] + "", 4056);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[1]) + 1)
					+ "", 4057);
			sendFrame126("" + defenceLeft + "", 18817);
			sendFrame126(c.playerLevel[1] + "/" + getLevelForXP(c.playerXP[1]),
					18815);
			break;

		case 2:// strength
			sendFrame126("" + c.playerLevel[2] + "", 4006);
			sendFrame126("" + getLevelForXP(c.playerXP[2]) + "", 4007);
			sendFrame126("" + c.playerXP[2] + "", 4050);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[2]) + 1)
					+ "", 4051);
			sendFrame126("" + strengthLeft + "", 18798);
			sendFrame126(c.playerLevel[2] + "/" + getLevelForXP(c.playerXP[2]),
					18796);
			break;

		case 3:// hitpoints
			sendFrame126("" + c.playerLevel[3] + "", 4016);
			sendFrame126("" + getLevelForXP(c.playerXP[3]) + "", 4017);
			sendFrame126("" + c.playerXP[3] + "", 18853);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[3]) + 1)
					+ "", 18854);
			sendFrame126("" + hitpointsLeft + "", 18859);
			sendFrame126(c.playerLevel[3] + "/" + getLevelForXP(c.playerXP[3]),
					18857);
			break;

		case 4:// range
			sendFrame126("" + c.playerLevel[4] + "", 4010);
			sendFrame126("" + getLevelForXP(c.playerXP[4]) + "", 4011);
			sendFrame126("" + c.playerXP[4] + "", 4062);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[4]) + 1)
					+ "", 4063);
			sendFrame126("" + rangeLeft + "", 18822);
			sendFrame126(c.playerLevel[4] + "/" + getLevelForXP(c.playerXP[4]),
					18820);
			break;

		case 5:// prayer
			sendFrame126("" + c.playerLevel[5] + "", 4012);
			sendFrame126("" + getLevelForXP(c.playerXP[5]) + "", 4013);
			sendFrame126("" + c.playerXP[5] + "", 4068);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[5]) + 1)
					+ "", 4069);
			sendFrame126("" + c.playerLevel[5] + "/"
					+ getLevelForXP(c.playerXP[5]) + "", 687);// Prayer frame
			sendFrame126("" + prayerLeft + "", 18827);
			sendFrame126(c.playerLevel[5] + "/" + getLevelForXP(c.playerXP[5]),
					18825);
			break;

		case 6:// magic
			sendFrame126("" + c.playerLevel[6] + "", 4014);
			sendFrame126("" + getLevelForXP(c.playerXP[6]) + "", 4015);
			sendFrame126("" + c.playerXP[6] + "", 18832);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[6]) + 1)
					+ "", 18833);
			sendFrame126("" + mageLeft + "", 18838);
			sendFrame126(c.playerLevel[6] + "/" + getLevelForXP(c.playerXP[6]),
					18836);
			break;

		case 7:// cooking
			sendFrame126("" + c.playerLevel[7] + "", 4034);
			sendFrame126("" + getLevelForXP(c.playerXP[7]) + "", 4035);
			sendFrame126("" + c.playerXP[7] + "", 19042);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[7]) + 1)
					+ "", 19043);
			sendFrame126("" + cookingLeft + "", 19048);
			sendFrame126(c.playerLevel[7] + "/" + getLevelForXP(c.playerXP[7]),
					19046);
			break;

		case 8:// woodcutting
			sendFrame126("" + c.playerLevel[8] + "", 4038);
			sendFrame126("" + getLevelForXP(c.playerXP[8]) + "", 4039);
			sendFrame126("" + c.playerXP[8] + "", 19084);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[8]) + 1)
					+ "", 19085);
			sendFrame126("" + woodcuttingLeft + "", 19090);
			sendFrame126(c.playerLevel[8] + "/" + getLevelForXP(c.playerXP[8]),
					19088);
			break;

		case 9:// fletching
			sendFrame126("" + c.playerLevel[9] + "", 4026);
			sendFrame126("" + getLevelForXP(c.playerXP[9]) + "", 4027);
			sendFrame126("" + c.playerXP[9] + "", 18958);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[9]) + 1)
					+ "", 18959);
			sendFrame126("" + fletchingLeft + "", 18964);
			sendFrame126(c.playerLevel[9] + "/" + getLevelForXP(c.playerXP[9]),
					18962);
			break;

		case 10:// fishing
			sendFrame126("" + c.playerLevel[10] + "", 4032);
			sendFrame126("" + getLevelForXP(c.playerXP[10]) + "", 4033);
			sendFrame126("" + c.playerXP[10] + "", 19021);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[10]) + 1)
					+ "", 19022);
			sendFrame126("" + fletchingLeft + "", 19027);
			sendFrame126(c.playerLevel[10] + "/"
					+ getLevelForXP(c.playerXP[10]), 19025);
			break;

		case 11:// firemaking
			sendFrame126("" + c.playerLevel[11] + "", 4036);
			sendFrame126("" + getLevelForXP(c.playerXP[11]) + "", 4037);
			sendFrame126("" + c.playerXP[11] + "", 19063);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[11]) + 1)
					+ "", 19064);
			sendFrame126("" + firemakingLeft + "", 19069);
			sendFrame126(c.playerLevel[11] + "/"
					+ getLevelForXP(c.playerXP[11]), 19067);
			break;

		case 12:// crafting
			sendFrame126("" + c.playerLevel[12] + "", 4024);
			sendFrame126("" + getLevelForXP(c.playerXP[12]) + "", 4025);
			sendFrame126("" + c.playerXP[12] + "", 18937);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[12]) + 1)
					+ "", 18938);
			sendFrame126("" + craftingLeft + "", 18943);
			sendFrame126(c.playerLevel[12] + "/"
					+ getLevelForXP(c.playerXP[12]), 18941);
			break;

		case 13:// smithing
			sendFrame126("" + c.playerLevel[13] + "", 4030);
			sendFrame126("" + getLevelForXP(c.playerXP[13]) + "", 4031);
			sendFrame126("" + c.playerXP[13] + "", 19422);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[13]) + 1)
					+ "", 19423);
			sendFrame126("" + smithingLeft + "", 19428);
			sendFrame126(c.playerLevel[13] + "/"
					+ getLevelForXP(c.playerXP[13]), 19426);
			break;

		case 14:// mining
			sendFrame126("" + c.playerLevel[14] + "", 4028);
			sendFrame126("" + getLevelForXP(c.playerXP[14]) + "", 4029);
			sendFrame126("" + c.playerXP[14] + "", 18979);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[14]) + 1)
					+ "", 18980);
			sendFrame126("" + miningLeft + "", 18985);
			sendFrame126(c.playerLevel[14] + "/"
					+ getLevelForXP(c.playerXP[14]), 18983);
			break;

		case 15:// herblore
			sendFrame126("" + c.playerLevel[15] + "", 4020);
			sendFrame126("" + getLevelForXP(c.playerXP[15]) + "", 4021);
			sendFrame126("" + c.playerXP[15] + "", 18895);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[15]) + 1)
					+ "", 18896);
			sendFrame126("" + herbloreLeft + "", 18901);
			sendFrame126(c.playerLevel[15] + "/"
					+ getLevelForXP(c.playerXP[15]), 18899);
			break;

		case 16:// agility
			sendFrame126("" + c.playerLevel[16] + "", 4018);
			sendFrame126("" + getLevelForXP(c.playerXP[16]) + "", 4019);
			sendFrame126("" + c.playerXP[16] + "", 18874);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[16]) + 1)
					+ "", 18875);
			sendFrame126("" + agilityLeft + "", 18880);
			sendFrame126(c.playerLevel[16] + "/"
					+ getLevelForXP(c.playerXP[16]), 18878);
			break;

		case 17:// thieving
			sendFrame126("" + c.playerLevel[17] + "", 4022);
			sendFrame126("" + getLevelForXP(c.playerXP[17]) + "", 4023);
			sendFrame126("" + c.playerXP[17] + "", 18916);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[17]) + 1)
					+ "", 18917);
			sendFrame126("" + thievingLeft + "", 18922);
			sendFrame126(c.playerLevel[17] + "/"
					+ getLevelForXP(c.playerXP[17]), 18920);
			break;

		case 18:// slayer
			sendFrame126("" + c.playerLevel[18] + "", 18809);
			sendFrame126("" + getLevelForXP(c.playerXP[18]) + "", 18810);
			sendFrame126("" + c.playerXP[18] + "", 19126);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[18]) + 1)
					+ "", 19127);
			sendFrame126("" + slayerLeft + "", 19132);
			sendFrame126(c.playerLevel[18] + "/"
					+ getLevelForXP(c.playerXP[18]), 19130);
			break;

		case 19:// farming
			sendFrame126("" + c.playerLevel[19] + "", 18811);
			sendFrame126("" + getLevelForXP(c.playerXP[19]) + "", 18812);
			sendFrame126("" + c.playerXP[19] + "", 19275);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[19]) + 1)
					+ "", 19276);
			sendFrame126("" + farmingLeft + "", 19281);
			sendFrame126(c.playerLevel[19] + "/"
					+ getLevelForXP(c.playerXP[19]), 19279);
			break;

		case 20:// runecraft
			sendFrame126("" + c.playerLevel[20] + "", 18807);
			sendFrame126("" + getLevelForXP(c.playerXP[20]) + "", 18808);
			sendFrame126("" + c.playerXP[20] + "", 19105);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[20]) + 1)
					+ "", 19106);
			sendFrame126("" + runecraftLeft + "", 19111);
			sendFrame126(c.playerLevel[20] + "/"
					+ getLevelForXP(c.playerXP[20]), 19109);
			break;

		case 21:// summoning
			sendFrame126("" + c.playerLevel[21] + "", 19178);
			sendFrame126("" + getLevelForXP(c.playerXP[21]) + "", 19179);
			sendFrame126("" + c.playerXP[21] + "", 19232);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[21]) + 1)
					+ "", 19233);
			sendFrame126("" + constructionLeft + "", 19238);
			sendFrame126(c.playerLevel[21] + "/"
					+ getLevelForXP(c.playerXP[21]), 19236);
			break;

		case 22:// hunting
			sendFrame126("" + c.playerLevel[22] + "", 19176);
			sendFrame126("" + getLevelForXP(c.playerXP[22]) + "", 19177);
			sendFrame126("" + c.playerXP[22] + "", 19211);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[22]) + 1)
					+ "", 19212);
			sendFrame126("" + hunterLeft + "", 19217);
			sendFrame126(c.playerLevel[22] + "/"
					+ getLevelForXP(c.playerXP[22]), 19215);
			break;

		case 23:// construction
			sendFrame126("" + c.playerLevel[23] + "", 19174);
			sendFrame126("" + getLevelForXP(c.playerXP[23]) + "", 19175);
			sendFrame126("" + c.playerXP[23] + "", 19190);
			sendFrame126("" + getXPForLevel(getLevelForXP(c.playerXP[23]) + 1)
					+ "", 19191);
			sendFrame126("" + summoningLeft + "", 19196);
			sendFrame126(c.playerLevel[23] + "/"
					+ getLevelForXP(c.playerXP[23]), 19194);
			break;
		case 24:
			if (c.playerXP[24] >= 0 && c.playerXP[24] <= 14391160) {
				sendFrame126("" + c.playerLevel[24] + "", 19180);
				sendFrame126("" + getLevelForXP(c.playerXP[24]) + "", 19181);
				sendFrame126("" + c.playerXP[24] + "", 19253);
				sendFrame126(
						"" + getXPForLevel(getLevelForXP(c.playerXP[24]) + 1)
								+ "", 19254);
				sendFrame126("" + dungeoneeringLeft + "", 19259);
				sendFrame126(c.playerLevel[24] + "/"
						+ getLevelForXP(c.playerXP[24]), 19257);
			} else if (c.playerXP[24] >= 14391161 && c.playerXP[24] <= 15889108) {
				sendFrame126("100", 19180);
				sendFrame126("100", 19181);
				sendFrame126("101", 19254);
				sendFrame126("N/A!", 19259);
				sendFrame126("100" + "/" + "100", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 15889109 && c.playerXP[24] <= 17542976) {
				sendFrame126("101", 19180);
				sendFrame126("102", 19254);
				sendFrame126("N/A!", 19259);
				sendFrame126("101" + "/" + "101", 19257);
				sendFrame126("101", 19181);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 17542977 && c.playerXP[24] <= 19368991) {
				sendFrame126("102", 19180);
				sendFrame126("103", 19254);
				sendFrame126("N/A!", 19259);
				sendFrame126("102" + "/" + "102", 19257);
				sendFrame126("102", 19181);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 19368992 && c.playerXP[24] <= 21385072) {
				sendFrame126("103", 19180);
				sendFrame126("104", 19254);
				sendFrame126("103", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("103/103", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 21385073 && c.playerXP[24] <= 23611005) {
				sendFrame126("104", 19180);
				sendFrame126("105", 19254);
				sendFrame126("104", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("104/104", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 23611006 && c.playerXP[24] <= 26068631) {
				sendFrame126("105", 19180);
				sendFrame126("106", 19254);
				sendFrame126("105", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("105/105", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 26068632 && c.playerXP[24] <= 28782068) {
				sendFrame126("106", 19180);
				sendFrame126("107", 19254);
				sendFrame126("106", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("106/106", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 28782069 && c.playerXP[24] <= 31777942) {
				sendFrame126("107", 19180);
				sendFrame126("108", 19254);
				sendFrame126("107", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("107/107", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 31777943 && c.playerXP[24] <= 35085653) {
				sendFrame126("108", 19180);
				sendFrame126("109", 19254);
				sendFrame126("108", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("108/108", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 35085654 && c.playerXP[24] <= 38737660) {
				sendFrame126("109", 19180);
				sendFrame126("110", 19254);
				sendFrame126("109", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("109/109", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 38737661 && c.playerXP[24] <= 42769799) {
				sendFrame126("110", 19180);
				sendFrame126("111", 19254);
				sendFrame126("110", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("110/110", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 42769800 && c.playerXP[24] <= 47221639) {
				sendFrame126("111", 19180);
				sendFrame126("112", 19254);
				sendFrame126("111", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("111/111", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 47221640 && c.playerXP[24] <= 52136868) {
				sendFrame126("112", 19180);
				sendFrame126("113", 19254);
				sendFrame126("112", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("112/112", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 52136869 && c.playerXP[24] <= 57563717) {
				sendFrame126("113", 19180);
				sendFrame126("114", 19254);
				sendFrame126("113", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("113/113", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 57563718 && c.playerXP[24] <= 63555442) {
				sendFrame126("114", 19180);
				sendFrame126("115", 19254);
				sendFrame126("114", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("114/114", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 63555443 && c.playerXP[24] <= 70170839) {
				sendFrame126("115", 19180);
				sendFrame126("116", 19254);
				sendFrame126("115", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("115/115", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 70170840 && c.playerXP[24] <= 77474827) {
				sendFrame126("116", 19180);
				sendFrame126("117", 19254);
				sendFrame126("116", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("116/116", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 77474828 && c.playerXP[24] <= 85539081) {
				sendFrame126("117", 19180);
				sendFrame126("118", 19254);
				sendFrame126("117", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("117/117", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 85539082 && c.playerXP[24] <= 94442735) {
				sendFrame126("118", 19180);
				sendFrame126("119", 19254);
				sendFrame126("118", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("118/118", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 94442736
					&& c.playerXP[24] <= 104273166) {
				sendFrame126("119", 19180);
				sendFrame126("120", 19254);
				sendFrame126("119", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("119/119", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			} else if (c.playerXP[24] >= 104273167
					&& c.playerXP[24] <= 200000000) {
				sendFrame126("120", 19180);
				sendFrame126("N/A", 19254);
				sendFrame126("120", 19181);
				sendFrame126("N/A!", 19259);
				sendFrame126("120/120", 19257);
				sendFrame126("" + c.playerXP[24] + "", 19253);
			}
			break;
		}
		c.calcCombat();
		totallevelsupdate();
	}

	public void removeAllItems() {
		for (int i = 0; i < c.playerItems.length; i++) {
			c.playerItems[i] = 0;
		}
		for (int i = 0; i < c.playerItemsN.length; i++) {
			c.playerItemsN[i] = 0;
		}
		c.getItems().resetItems(3214);
	}

	public void removeAllWindows() {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			if (!c.inFoGGame)
				c.storing = false;
			c.getPA().resetVariables();
			c.teleAction = 0;
			c.dialogueAction = 0;
			c.getOutStream().createFrame(219);
			c.flushOutStream();
		}
	}

	public void removeBoBItems(int slot, int amount) {
		if (c.InDung() || c.inTrade || c.isBanking) {
			c.sendMessage("You cannot do this at the moment!");
			return;
		}
		if (c.getItems().freeSlots() > 0) {
			c.getItems().addItem(c.storeditems[slot], amount);
			c.occupied[slot] = false;
			c.storeditems[slot] = 0;
			c.getItems().resetTempItems();
			c.getItems().resetBank();
			c.totalstored -= slot;
		} else {
			c.getItems().resetTempItems();
			c.getItems().resetBank();
			c.sendMessage("Not enough space in your inventory.");
			return;
		}
	}

	public void removeObject(int x, int y) {
		object(-1, x, y, 10, 10);
	}

	public void removeObjects() {
		objectToRemove2(2638, 4688);
		objectToRemove(2638, 4688);
		objectToRemove(2844, 3440);
		objectToRemove(2846, 3437);
		objectToRemove(2840, 3439);
		objectToRemove(2841, 3443);
		objectToRemove2(2635, 4693);
		objectToRemove2(2634, 4693);
		objectToRemove2(2794, 9327);
		objectToRemove(3206, 3263);
		objectToRemove(3193, 3274);
		objectToRemove(3193, 3273);
	}

	public void replaceObject(final Client c, final int i, final int x,
			final int y, final int face) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {

			public void execute(CycleEventContainer e) {
				Server.objectHandler.createAnObject(c, i, x, y, face);
				c.sendMessage("test.");
				e.stop();
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub
			}
		}, getRespawnTime(c, i));
	}

	public void requestUpdates() {
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
		c.updateWalkEntities();
	}

	public void resetAllSkills() {
		for (int j = 0; j < 24; j++) {
			setLevel(j, 1);
		}
	}

	/**
	 * reseting animation
	 **/
	public void resetAnimation() {
		c.getCombat()
				.getPlayerAnimIndex(
						c.getItems()
								.getItemName(
										c.playerEquipment[server.model.players.Player.playerWeapon])
								.toLowerCase());
		c.startAnimation(c.playerStandIndex);
		requestUpdates();
	}

	public void resetAutocast() {
		c.autocastId = -1;
		c.autocasting = false;
		c.setSidebarInterface(0, 328);
		c.getPA().sendFrame36(108, 0);
		c.getItems()
				.sendWeapon(
						c.playerEquipment[server.model.players.Player.playerWeapon],
						c.getItems()
								.getItemName(
										c.playerEquipment[server.model.players.Player.playerWeapon]));
	}

	public void resetBarrows() {
		c.barrowsNpcs[0][1] = 0;
		c.barrowsNpcs[1][1] = 0;
		c.barrowsNpcs[2][1] = 0;
		c.barrowsNpcs[3][1] = 0;
		c.barrowsNpcs[4][1] = 0;
		c.barrowsNpcs[5][1] = 0;
		c.barrowsKillCount = 0;
		c.randomCoffin = Misc.random(3) + 1;
	}

	/**
	 * Location change for digging, levers etc
	 **/
	public void resetDamageDone() {
		for (int i = 0; i < PlayerHandler.players.length; i++) {
			if (PlayerHandler.players[i] != null) {
				PlayerHandler.players[i].damageTaken[c.playerId] = 0;
			}
		}
	}

	public void resetDragonHits(Client c) {
		for (int i = 0; i < 4; i++) {
			c.clawHit[i] = -1;
		}
		c.usingClaws = false;
	}

	public void resetFishing() {
		c.fishtimer = -1;
		c.fishing = false;
	}

	public void resetFollow() {
		c.followId = 0;
		c.followId2 = 0;
		c.mageFollow = false;
		c.outStream.createFrame(174);
		c.outStream.writeWord(0);
		c.outStream.writeByte(0);
		c.outStream.writeWord(1);
	}

	public void resetFollowers() {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].followId == c.playerId) {
					Client c = (Client) PlayerHandler.players[j];
					c.getPA().resetFollow();
				}
			}
		}
	}

	public void resetFrames() {
		c.getPA().sendFrame126("Kills", 12118);
		c.getPA().sendFrame126("Points", 12119);
		c.getPA().sendFrame126("Deaths", 12122);
		c.getPA().sendFrame126("LP", 12123);

		c.getPA().sendFrame126("", 12100);
		c.getPA().sendFrame126("", 12102);
		c.getPA().sendFrame126("", 12103);
		c.getPA().sendFrame126("", 12104);
		c.getPA().sendFrame126("", 12105);
		c.getPA().sendFrame126("", 12106);
		c.getPA().sendFrame126("", 12107);
		c.getPA().sendFrame126("", 12108);
		c.getPA().sendFrame126("", 12109);
		c.getPA().sendFrame126("", 12110);
		c.getPA().sendFrame126("", 12111);
		c.getPA().sendFrame126("", 12112);
		c.getPA().sendFrame126("", 12113);
		c.getPA().sendFrame126("", 12114);
	}

	public void ResetGWKC() {
		if (c.inGWD()) {
			c.Arma = 0;
			c.Band = 0;
			c.Sara = 0;
			c.Zammy = 0;
			c.sendMessage("A magical force reseted your kill count!");
		}
	}

	public void resetRFD() {
		c.waveId = -1;
		c.RFDToKill = -1;
		c.RFDKilled = -1;
		c.getPA().movePlayer(3203, 3429, 0);
	}

	public void resetTb() {
		c.teleBlockLength = 0;
		c.teleBlockDelay = 0;
	}

	public void resetTzhaar() {
		// c.jadCave = false;
		c.waveId = -1;
		c.tzhaarToKill = 1;
		c.tzhaarKilled = -1;
		c.getPA().movePlayer(2438, 5168, 0);
	}

	public void resetVariables() {
		c.getCrafting().resetCrafting();
		c.usingGlory = false;
		c.smeltInterface = false;
		c.smeltType = 0;
		c.smeltAmount = 0;
		c.woodcut[0] = c.woodcut[1] = c.woodcut[2] = 0;
	}

	public void searchBank(Client c, String searchTerm) {

		if (!c.isBanking) {
			c.getPA().closeAllWindows();
			return;
		}
		if (searchTerm.equals("N/A")) {
			// c.sendMessage("N/A");
			openUpBank(c.bankingTab);
			return;
		}
		c.bankingTab = -1;
		c.getPA().sendFrame126(Integer.toString(-1), 27002);
		c.getPA().sendFrame126("1", 27000);
		tempItems = new int[Config.BANK_SIZE];
		tempItemsN = new int[Config.BANK_SIZE];
		tempItemsT = new int[Config.BANK_SIZE];
		tempItemsS = new int[Config.BANK_SIZE];
		for (int n = 0; n < 9; n++) {
			if (n == 0)
				updateResult(n, c.bankItems, c.bankItemsN);
			else if (n == 1)
				updateResult(n, c.bankItems1, c.bankItems1N);
			else if (n == 2)
				updateResult(n, c.bankItems2, c.bankItems2N);
			else if (n == 3)
				updateResult(n, c.bankItems3, c.bankItems3N);
			else if (n == 4)
				updateResult(n, c.bankItems4, c.bankItems4N);
			else if (n == 5)
				updateResult(n, c.bankItems5, c.bankItems5N);
			else if (n == 6)
				updateResult(n, c.bankItems6, c.bankItems6N);
			else if (n == 7)
				updateResult(n, c.bankItems7, c.bankItems7N);
			else if (n == 8)
				updateResult(n, c.bankItems8, c.bankItems8N);
		}
		c.getItems().resetItems(5064);
		c.getItems().resetBank(tempItems, tempItemsN);
	}

	public void searchBank(String str) {
		if (c != null && !c.storing)
			sendFrame126("The bank of Divination of Gods.", 5383);
		c.items = new int[500];
		c.itemsN = new int[500];
		int p = 0;
		int slot = 0;
		for (int j = 0; j < c.bankItems.length; j++) {
			if (c.bankItems[j] > 0) {
				if (c.getItems().getItemName(c.bankItems[j] - 1).toLowerCase()
						.contains(str.toLowerCase())) {
					c.items[slot] = c.bankItems[j];
					c.itemsN[slot] = c.bankItemsN[j];
					slot++;
					p++;
				}
			}
		}
		if (p > 0) {
			sendFrame126(
					"Bank of Divination of Gods - (search: '" + str + "')",
					5383);
			showItems(c.items, c.itemsN);
			c.getItems().resetTempItems();
			slot = 0;
			c.isSearching = true;
		} else {
			if (c.lastSearch & c.isSearching) {
				destroySearch();
				return;
			} else {
				sendFrame126("No results were found for '" + str + "'.", 5383);
				c.isSearching = false;
			}
		}
	}

	public void sendCrashFrame() { // used for crashing cheat clients
		// synchronized(c) {
		/*
		 * if (c.getOutStream() != null && c != null) {
		 * c.getOutStream().createFrame(123); c.flushOutStream(); }
		 */
	}

	public void sendFrame106(int sideIcon) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(106);
			c.getOutStream().writeByteC(sideIcon);
			c.flushOutStream();
			requestUpdates();
		}
	}

	// to this

	// }
	public void sendFrame107() {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(107);
			c.flushOutStream();
		}
	}

	public void sendFrame126(String s, int id) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null && s != null) {
			c.getOutStream().createFrameVarSizeWord(126);
			c.getOutStream().writeString(s);
			c.getOutStream().writeWordA(id);
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public void sendFrame164(int Frame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(164);
			c.getOutStream().writeWordBigEndian_dup(Frame);
			c.flushOutStream();
		}
	}

	// }
	public void sendFrame171(int MainFrame, int SubFrame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(171);
			c.getOutStream().writeByte(MainFrame);
			c.getOutStream().writeWord(SubFrame);
			c.flushOutStream();
		}
	}

	// }
	public void sendFrame185(int Frame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(185);
			c.getOutStream().writeWordBigEndianA(Frame);
		}
	}

	// }
	public void sendFrame200(int MainFrame, int SubFrame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(200);
			c.getOutStream().writeWord(MainFrame);
			c.getOutStream().writeWord(SubFrame);
			c.flushOutStream();
		}
	}

	/*
	 * case 24:// dungeoneering sendFrame126("" + c.playerLevel[24] + "",
	 * 19180); sendFrame126("" + getLevelForXP(c.playerXP[24]) + "", 19181);
	 * sendFrame126("" + c.playerXP[24] + "", 19253); sendFrame126("" +
	 * getXPForLevel(getLevelForXP(c.playerXP[24]) + 1) + "", 19254);
	 * sendFrame126("" + dungeoneeringLeft + "", 19259);
	 * sendFrame126(c.playerLevel[24] + "/" + getLevelForXP(c.playerXP[24]),
	 * 19257); break;
	 */

	// }
	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(246);
			c.getOutStream().writeWordBigEndian(MainFrame);
			c.getOutStream().writeWord(SubFrame);
			c.getOutStream().writeWord(SubFrame2);
			c.flushOutStream();
		}
	}

	public void sendFrame248(int MainFrame, int SubFrame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(248);
			c.getOutStream().writeWordA(MainFrame);
			c.getOutStream().writeWord(SubFrame);
			c.flushOutStream();
		}
	}

	public void sendFrame34(int id, int slot, int column, int amount) {
		// synchronized (c){
		if (c.getOutStream() != null && c != null) {
			c.outStream.createFrameVarSizeWord(34); // init item to smith screen
			c.outStream.writeWord(column); // Column Across Smith Screen
			c.outStream.writeByte(4); // Total Rows?
			c.outStream.writeDWord(slot); // Row Down The Smith Screen
			c.outStream.writeWord(id + 1); // item
			c.outStream.writeByte(amount); // how many there are?
			c.outStream.endFrameVarSizeWord();
		}
		// }
	}

	public void sendFrame34a(int frame, int item, int slot, int amount) {
		c.outStream.createFrameVarSizeWord(34);
		c.outStream.writeWord(frame);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(item + 1);
		c.outStream.writeByte(255);
		c.outStream.writeDWord(amount);
		c.outStream.endFrameVarSizeWord();
	}

	// }
	public void sendFrame36(int id, int state) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(36);
			c.getOutStream().writeWordBigEndian(id);
			c.getOutStream().writeByte(state);
			c.flushOutStream();
		}
	}

	// }
	public void sendFrame70(int i, int o, int id) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(70);
			c.getOutStream().writeWord(i);
			c.getOutStream().writeWordBigEndian(o);
			c.getOutStream().writeWordBigEndian(id);
			c.flushOutStream();
		}
	}

	public void sendFrame72(int MainFrame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(72);
			c.getOutStream().writeWordBigEndian(MainFrame);
			c.flushOutStream();
		}
	}

	// }
	public void sendFrame75(int MainFrame, int SubFrame) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(75);
			c.getOutStream().writeWordBigEndianA(MainFrame);
			c.getOutStream().writeWordBigEndianA(SubFrame);
			c.flushOutStream();
		}
		// }
	}

	// }
	public void sendFrame87(int id, int state) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(87);
			c.getOutStream().writeWordBigEndian_dup(id);
			c.getOutStream().writeDWord_v1(state);
			c.flushOutStream();
		}
	}

	// }
	public void sendLink(String s) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSizeWord(187);
			c.getOutStream().writeString(s);
		}
	}

	public void sendMp3(String mp3) {
		// synchronized(c) {
		// sendFrame126(":mp3:"+mp3+"", 24000);
		// }
	}

	// }
	public void sendPM(long name, int rights, byte[] chatmessage,
			int messagesize) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrameVarSize(196);
			c.getOutStream().writeQWord(name);
			c.getOutStream().writeDWord(c.lastChatId++);
			c.getOutStream().writeByte(rights);
			c.getOutStream().writeBytes(chatmessage, messagesize, 0);
			c.getOutStream().endFrameVarSize();
			c.flushOutStream();
			Misc.textUnpack(chatmessage, messagesize);
			Misc.longToPlayerName(name);
		}
	}

	public void sendQuest(String s, int id) {
		try {
			c.outStream.createFrameVarSizeWord(126);
			c.outStream.writeString(s);
			c.outStream.writeWordA(id);
			c.outStream.endFrameVarSizeWord();
		} catch (Exception e) {
		}
	}

	public void sendshh() {
		// synchronized(c) {
		// sendFrame126("silence", 24000);
		// }
	}

	// }
	public void sendStatement(String s) {
		sendFrame126(s, 357);
		sendFrame126("Click here to continue", 358);
		sendFrame164(356);
	}

	/**
	 * Sets the clan information for the player's clan.
	 */

	public void sendStillGraphics(int id, int heightS, int y, int x, int timeBCS) {
		c.getOutStream().createFrame(85);
		c.getOutStream().writeByteC(y - (c.mapRegionY * 8));
		c.getOutStream().writeByteC(x - (c.mapRegionX * 8));
		c.getOutStream().createFrame(4);
		c.getOutStream().writeByte(0);// Tiles away (X >> 4 + Y & 7)
										// //Tiles away from
		// absX and absY.
		c.getOutStream().writeWord(id); // Graphic ID.
		c.getOutStream().writeByte(heightS); // Height of the graphic when
												// cast.
		c.getOutStream().writeWord(timeBCS); // Time before the graphic
												// plays.
		c.flushOutStream();
	}

	public void sendTabs() {
		// remove empty tab
		boolean moveRest = false;
		if (checkEmpty(c.bankItems1)) { // tab 1 empty
			c.bankItems1 = Arrays.copyOf(c.bankItems2, c.bankingItems.length);
			c.bankItems1N = Arrays.copyOf(c.bankItems2N, c.bankingItems.length);
			c.bankItems2 = new int[Config.BANK_SIZE];
			c.bankItems2N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if (checkEmpty(c.bankItems2) || moveRest) {
			c.bankItems2 = Arrays.copyOf(c.bankItems3, c.bankingItems.length);
			c.bankItems2N = Arrays.copyOf(c.bankItems3N, c.bankingItems.length);
			c.bankItems3 = new int[Config.BANK_SIZE];
			c.bankItems3N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if (checkEmpty(c.bankItems3) || moveRest) {
			c.bankItems3 = Arrays.copyOf(c.bankItems4, c.bankingItems.length);
			c.bankItems3N = Arrays.copyOf(c.bankItems4N, c.bankingItems.length);
			c.bankItems4 = new int[Config.BANK_SIZE];
			c.bankItems4N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if (checkEmpty(c.bankItems4) || moveRest) {
			c.bankItems4 = Arrays.copyOf(c.bankItems5, c.bankingItems.length);
			c.bankItems4N = Arrays.copyOf(c.bankItems5N, c.bankingItems.length);
			c.bankItems5 = new int[Config.BANK_SIZE];
			c.bankItems5N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if (checkEmpty(c.bankItems5) || moveRest) {
			c.bankItems5 = Arrays.copyOf(c.bankItems6, c.bankingItems.length);
			c.bankItems5N = Arrays.copyOf(c.bankItems6N, c.bankingItems.length);
			c.bankItems6 = new int[Config.BANK_SIZE];
			c.bankItems6N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if (checkEmpty(c.bankItems6) || moveRest) {
			c.bankItems6 = Arrays.copyOf(c.bankItems7, c.bankingItems.length);
			c.bankItems6N = Arrays.copyOf(c.bankItems7N, c.bankingItems.length);
			c.bankItems7 = new int[Config.BANK_SIZE];
			c.bankItems7N = new int[Config.BANK_SIZE];
			moveRest = true;
		}
		if (checkEmpty(c.bankItems7) || moveRest) {
			c.bankItems7 = Arrays.copyOf(c.bankItems8, c.bankingItems.length);
			c.bankItems7N = Arrays.copyOf(c.bankItems8N, c.bankingItems.length);
			c.bankItems8 = new int[Config.BANK_SIZE];
			c.bankItems8N = new int[Config.BANK_SIZE];
		}
		if (c.bankingTab > getTabCount())
			c.bankingTab = getTabCount();
		c.getPA().sendFrame126(Integer.toString(getTabCount()), 27001);
		c.getPA().sendFrame126(Integer.toString(c.bankingTab), 27002);
		PlayerAssistant.itemOnInterface(c, 22035, 0,
				getInterfaceModel(0, c.bankItems1, c.bankItems1N), 1);
		PlayerAssistant.itemOnInterface(c, 22036, 0,
				getInterfaceModel(0, c.bankItems2, c.bankItems2N), 1);
		PlayerAssistant.itemOnInterface(c, 22037, 0,
				getInterfaceModel(0, c.bankItems3, c.bankItems3N), 1);
		PlayerAssistant.itemOnInterface(c, 22038, 0,
				getInterfaceModel(0, c.bankItems4, c.bankItems4N), 1);
		PlayerAssistant.itemOnInterface(c, 22039, 0,
				getInterfaceModel(0, c.bankItems5, c.bankItems5N), 1);
		PlayerAssistant.itemOnInterface(c, 22040, 0,
				getInterfaceModel(0, c.bankItems6, c.bankItems6N), 1);
		PlayerAssistant.itemOnInterface(c, 22041, 0,
				getInterfaceModel(0, c.bankItems7, c.bankItems7N), 1);
		PlayerAssistant.itemOnInterface(c, 22042, 0,
				getInterfaceModel(0, c.bankItems8, c.bankItems8N), 1);
		// tells client to update
		c.getPA().sendFrame126("1", 27000);
	}

	// }
	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(206);
			c.getOutStream().writeByte(publicChat);
			c.getOutStream().writeByte(privateChat);
			c.getOutStream().writeByte(tradeBlock);
			c.flushOutStream();
		}
	}

	public void setDungBindStats() {
		if (c.inDung) {
			showInterface(49300);
			if (c.bind1 > 0) {
				dungBindStatItem(c, 49303, c.bind1, 1);
				sendFrame126("" + c.getItems().getItemName(c.bind1) + "", 49311);
				sendFrame126("Bind 1:", 49312);
			} else {
				dungBindStatItem(c, 49303, c.bind1, 1);
				sendFrame126("", 49311);
				sendFrame126("", 49312);
			}
			if (c.bind2 > 0) {
				dungBindStatItem(c, 49304, c.bind2, 1);
				sendFrame126("" + c.getItems().getItemName(c.bind2) + "", 49314);
				sendFrame126("Bind 2:", 49313);
			} else {
				dungBindStatItem(c, 49304, c.bind2, 1);
				sendFrame126("", 49314);
				sendFrame126("", 49313);
			}
			if (c.bind3 > 0) {
				dungBindStatItem(c, 49305, c.bind3, 1);
				sendFrame126("" + c.getItems().getItemName(c.bind3) + "", 49315);
				sendFrame126("Bind 3:", 49316);
			} else {
				dungBindStatItem(c, 49305, c.bind3, 1);
				sendFrame126("", 49315);
				sendFrame126("", 49316);
			}
			if (c.bind4 > 0) {
				dungBindStatItem(c, 49306, c.bind4, 1);
				sendFrame126("" + c.getItems().getItemName(c.bind4) + "", 49318);
				sendFrame126("Bind 4:", 49317);
			} else {
				dungBindStatItem(c, 49306, c.bind4, 1);
				sendFrame126("", 49318);
				sendFrame126("", 49317);
			}
			int deathPenalty = c.dungDeaths * 2000;
			int npcsKillsBonus = c.npcsKilled * 4000;
			sendFrame126("      Deaths: " + c.dungDeaths + " (-" + deathPenalty
					+ " XP)", 49321);
			sendFrame126("Player: " + Misc.optimizeText(c.playerName) + "",
					49319);
			sendFrame126("         Killcount: " + c.npcsKilled + " (+"
					+ npcsKillsBonus + " XP)", 49320);
		}
	}

	public void setDungBindStats(Client c2) {
		if (c.inDung) {
			showInterface(49300);
			if (c2.bind1 > 0) {
				dungBindStatItem(c2, 49303, c2.bind1, 1);
				sendFrame126("" + c.getItems().getItemName(c.bind2) + "", 49311);
				sendFrame126("Bind 1:", 49312);
			} else {
				dungBindStatItem(c2, 49303, c2.bind1, 1);
				sendFrame126("", 49311);
				sendFrame126("", 49312);
			}
			if (c2.bind2 > 0) {
				dungBindStatItem(c2, 49304, c2.bind2, 1);
				sendFrame126("" + c.getItems().getItemName(c2.bind2) + "",
						49314);
				sendFrame126("Bind 2:", 49313);
			} else {
				dungBindStatItem(c2, 49304, c2.bind2, 1);
				sendFrame126("", 49314);
				sendFrame126("", 49313);
			}
			if (c2.bind3 > 0) {
				dungBindStatItem(c2, 49305, c2.bind3, 1);
				sendFrame126("" + c.getItems().getItemName(c2.bind3) + "",
						49315);
				sendFrame126("Bind 3:", 49316);
			} else {
				dungBindStatItem(c2, 49305, c2.bind3, 1);
				sendFrame126("", 49315);
				sendFrame126("", 49316);
			}
			if (c2.bind4 > 0) {
				dungBindStatItem(c2, 49306, c2.bind4, 1);
				sendFrame126("" + c.getItems().getItemName(c2.bind4) + "",
						49318);
				sendFrame126("Bind 4:", 49317);
			} else {
				dungBindStatItem(c2, 49306, c2.bind4, 1);
				sendFrame126("", 49318);
				sendFrame126("", 49317);
			}
			int deathPenalty = c2.dungDeaths * 2000;
			int npcsKillsBonus = c2.npcsKilled * 4000;
			sendFrame126("      Deaths: " + c2.dungDeaths + " (-"
					+ deathPenalty + " XP)", 49321);
			sendFrame126("Player: " + Misc.optimizeText(c2.playerName) + "",
					49319);
			sendFrame126("         Killcount: " + c2.npcsKilled + " (+"
					+ npcsKillsBonus + " XP)", 49320);
		}
	}

	public void setDungTab() {
		/*
		 * if(c.Partner.equalsIgnoreCase("None")) { c.setSidebarInterface(1,
		 * 49200); c.getPA().sendFrame126("6", 49208);
		 * c.getPA().sendFrame126("1", 49209);
		 * c.getPA().sendFrame126(""+Misc.optimizeText(c.playerName)+"", 49212);
		 * c.getPA().sendFrame126("", 49226); } else { for (Player p :
		 * PlayerHandler.players) { if (p != null) { Client ALLPLAYERS =
		 * (Client)p; if(ALLPLAYERS.playerName.equalsIgnoreCase(c.Partner)) {
		 * Client c2 = (Client)p; c.setSidebarInterface(1, 49200);
		 * c.getPA().sendFrame126("6", 49208); c.getPA().sendFrame126("1",
		 * 49209); c2.setSidebarInterface(1, 49200);
		 * c2.getPA().sendFrame126("6", 49208); c2.getPA().sendFrame126("1",
		 * 49209); //sets the owner highest in the interface
		 * if(Party.isPartyOwner(c, c.clanId)) {
		 * c.getPA().sendFrame126(""+Misc.optimizeText(c.playerName)+"", 49212);
		 * c.getPA().sendFrame126(""+Misc.optimizeText(c2.playerName)+"",
		 * 49226); } else {
		 * c.getPA().sendFrame126(""+Misc.optimizeText(c.playerName)+"", 49226);
		 * c.getPA().sendFrame126(""+Misc.optimizeText(c2.playerName)+"",
		 * 49212); } } } } }
		 */
	}

	public void setLevel(int skill, int level) { // BY GABBE!!
		if (level > 99) {
			level = 99;
		} else if (level < 0) {
			level = 1;
		}
		c.playerXP[skill] = c.getPA().getXPForLevel(level) + 5;
		c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
		c.getPA().refreshSkill(skill);
		c.calcCombat();
	}

	// }
	public void setPrivateMessaging(int i) { // friends and ignore list status
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(221);
			c.getOutStream().writeByte(i);
			c.flushOutStream();
		}
	}

	// }
	public void setSkillLevel(int skillNum, int currentLevel, int XP) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(134);
			c.getOutStream().writeByte(skillNum);
			c.getOutStream().writeDWord_v1(XP);
			c.getOutStream().writeByte(currentLevel);
			c.flushOutStream();
		}
	}

	public double shieldEffect(int damageType) {
		double meleeAbsorb = 1;
		double rangeAbsorb = 1;
		double magicAbsorb = 1;

		switch (c.playerEquipment[server.model.players.Player.playerShield]) {
		case 18359:// chaotickiteshield
			rangeAbsorb = .86;
			meleeAbsorb = .93;
			c.sendMessage("Your shield absorb's the damage");
			break;
		case 18361:// eagly-eyeshield
			magicAbsorb = .86;
			rangeAbsorb = .93;
			c.sendMessage("Your shield absorb's the damage");
			break;
		case 18363:// farseerkiteshield
			meleeAbsorb = .86;
			magicAbsorb = .93;
			c.sendMessage("Your shield absorb's the damage");
			break;
		}
		switch (damageType) {
		case 0: // melee
			return meleeAbsorb;
		case 1:// range
			return rangeAbsorb;
		case 2:// mage
			return magicAbsorb;
		default:
			return 1;
		}
	}

	// }
	public void showInterface(int interfaceid) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(97);
			c.getOutStream().writeWord(interfaceid);
			c.flushOutStream();
		}
	}

	private void showItems(int items[], int itemsN[]) {
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(5382);
		c.getOutStream().writeWord(Config.BANK_SIZE);
		for (int j = 0; j < items.length; j++) {
			if (items[j] > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord_v2(itemsN[j]);
			} else {
				c.getOutStream().writeByte(itemsN[j]);
			}
			if (itemsN[j] < 1) {
				items[j] = 0;
			}
			if (items[j] > Config.ITEM_LIMIT | items[j] < 0) {
				items[j] = Config.ITEM_LIMIT;
			}
			c.getOutStream().writeWordBigEndianA(items[j]);
		}
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
	}

	public void showOption(int i, int l, String s) {
		if (c != null) {
			if (c.getOutStream() != null) {
				if (!optionType.equalsIgnoreCase(s)) {
					optionType = s;
					c.getOutStream().createFrameVarSize(104);
					c.getOutStream().writeByteC(i);
					c.getOutStream().writeByteA(l);
					c.getOutStream().writeString(s);
					c.getOutStream().endFrameVarSize();
					c.flushOutStream();
				}
			}
		}
	}

	public void showOption(int i, int l, String s, int a) {
		if (c.getOutStream() != null && c != null) {
			if (!optionType.equalsIgnoreCase(s)) {
				optionType = s;
				c.getOutStream().createFrameVarSize(104);
				c.getOutStream().writeByteC(i);
				c.getOutStream().writeByteA(l);
				c.getOutStream().writeString(s);
				c.getOutStream().endFrameVarSize();
				c.flushOutStream();
			}
		}
	}

	public int skillcapeEmote(int cape) {
		int capeEmote[][] = { { 9747, 4959 }, { 9748, 4959 }, { 9750, 4981 },
				{ 9751, 4981 }, { 9753, 4961 }, { 9754, 4961 }, { 9756, 4973 },
				{ 9757, 4973 }, { 9759, 4979 }, { 9760, 4979 }, { 9762, 4939 },
				{ 9763, 4939 }, { 9765, 4947 }, { 9766, 4947 }, { 9768, 4971 },
				{ 9769, 4971 }, { 9771, 4977 }, { 9772, 4977 }, { 9774, 4969 },
				{ 9775, 4969 }, { 9777, 4965 }, { 9778, 4965 }, { 9780, 4949 },
				{ 9781, 4949 }, { 9783, 4937 }, { 9784, 4937 }, { 9786, 4967 },
				{ 9787, 4967 }, { 9789, 4953 }, { 9790, 4953 }, { 9792, 4941 },
				{ 9793, 4941 }, { 9795, 4943 }, { 9796, 4943 }, { 9798, 4951 },
				{ 9799, 4951 }, { 9801, 4955 }, { 9802, 4955 }, { 9804, 4975 },
				{ 9805, 4975 }, { 9807, 4957 }, { 9808, 4957 }, { 9810, 4963 },
				{ 9811, 4963 }, { 9948, 5158 }, { 9949, 5158 }, { 9813, 4945 },
				{ 12170, 8525 } };
		for (int i = 0; i < capeEmote.length; i++) {
			if (capeEmote[i][0] == cape) {
				return capeEmote[i][1];
			}
		}
		return -1;
	}

	public int skillcapeGfx(int cape) {
		int capeGfx[][] = { { 9747, 823 }, { 9748, 823 }, { 9750, 828 },
				{ 9751, 828 }, { 9753, 824 }, { 9754, 824 }, { 9756, 832 },
				{ 9757, 832 }, { 9759, 829 }, { 9760, 829 }, { 9762, 813 },
				{ 9763, 813 }, { 9765, 817 }, { 9766, 817 }, { 9768, 833 },
				{ 9769, 833 }, { 9771, 830 }, { 9772, 830 }, { 9774, 835 },
				{ 9775, 835 }, { 9777, 826 }, { 9778, 826 }, { 9780, 818 },
				{ 9781, 818 }, { 9783, 812 }, { 9784, 812 }, { 9786, 827 },
				{ 9787, 827 }, { 9789, 820 }, { 9790, 820 }, { 9792, 814 },
				{ 9793, 814 }, { 9795, 815 }, { 9796, 815 }, { 9798, 819 },
				{ 9799, 819 }, { 9801, 821 }, { 9802, 821 }, { 9804, 831 },
				{ 9805, 831 }, { 9807, 822 }, { 9808, 822 }, { 9810, 825 },
				{ 9811, 825 }, { 9948, 907 }, { 9949, 907 }, { 9813, 816 },
				{ 12170, 1515 } };
		for (int i = 0; i < capeGfx.length; i++) {
			if (capeGfx[i][0] == cape) {
				return capeGfx[i][1];
			}
		}
		return -1;
	}

	/**
	 * Teleporting
	 **/
	public void spellTeleport(int x, int y, int height) {
		if (c.inDung) {
			c.getDH().sendDialogues(256, -1);
			return;
		}
		if (c.insideDuelArena() && c.duelStatus == 5) {
			c.sendMessage("You cannot teleport from here.");
			return;
		}
		if (c.inFoGGame || c.inFoGWait) {
			c.sendMessage("You can't teleport at the moment.");
			return;
		}

		if (c.inRandomEvent() && RandomEvents.canTeleFromRandom == false) {
			c.sendMessage("Please finnish the random event first.");
			return;
		}
		if (c.inCwWait) {
			c.sendMessage("To leave, use the portal!");
			return;
		}
		if (c.isNpc == true) {
			c.sendMessage("Type ::return before you can do that!");
			return;
		}
		if (c.inTrade || c.InDung() || c.isBanking) {
			c.sendMessage("You can't teleport right now!");
			return;
		}

		fixBugs();
		c.getPA().startTeleport(x, y, height,
				c.playerMagicBook == 1 ? "ancient" : "modern");
		c.getTradeAndDuel().declineTrade();
		c.dialogueAction = -1;
		c.setAppearanceUpdateRequired(true);
		c.updateTimer = 45;
		c.getTimers().updatePlayerLook(c);
	}

	public void startMovement(int x, int y, int height) {
		if (c.inDung) {
			c.getDH().sendDialogues(256, -1);
			return;
		}
		if (c.inFoGGame || c.inFoGWait) {
			c.sendMessage("You can't teleport at the moment.");
			return;
		}
		if (c.inRandomEvent() && RandomEvents.canTeleFromRandom == false) {
			c.sendMessage("Please finnish the random event first.");
			return;
		}
		if (c.inCwWait) {
			c.sendMessage("To leave, use the portal!");
			return;
		}
		c.getPA().resetFishing();
		// c.getMining().resetMining();
		c.isCooking = false;
		c.playerIsFiremaking = false;
		if (c.isNpc == true) {
			c.sendMessage("Type ::return before you can do that!");
			return;
		}
		c.dialogueAction = 0;
		if (c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		/*
		 * if(c.inNomad()) {
		 * c.sendMessage("You can't teleport during Nomad Minigame"); return; }
		 * if(c.inGoblin()) {
		 * c.sendMessage("You can't teleport during Goblin Minigame"); return; }
		 */
		if (c.InDung() || c.InDung2() || c.inDungBossRoom()) {
			c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders or do ::enddung");
			return;
		}
		if (c.inRFD()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if (c.Jail == true) {
			c.sendMessage("You can't teleport out of prison idiot!");
			return;
		}
		if (c.inJail() && c.Jail == true) {
			c.sendMessage("You can't teleport out of prison idiot!");
			return;
		}
		if (c.inWarriorG() && c.heightLevel == 2) {
			c.sendMessage("You can't teleport out of Warrior Guild!");
			return;
		}
		if (c.inGWD()) {
			ResetGWKC();
		}
		if (c.inFightCaves()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if (c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "
					+ Config.NO_TELEPORT_WILD_LEVEL + " in the wilderness.");
			return;
		}
		if (System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if (!c.isDead && c.teleTimer == 0 && c.respawnTimer == -6) {
			if (c.playerIndex > 0 || c.npcIndex > 0) {
				c.getCombat().resetPlayerAttack();
			}
			c.stopMovement();
			c.setAppearanceUpdateRequired(true);
			c.updateTimer = 45;
			c.getTimers().updatePlayerLook(c);
			EarningPotential.checkTeleport(c);
			removeAllWindows();
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;

		}

	}

	public void startTeleport(int x, int y, int height, String teleportType) {
		if (c.inDung) {
			c.getDH().sendDialogues(256, -1);
			return;
		}
		if (c.insideDuelArena() && c.duelStatus == 5) {
			c.sendMessage("You cannot teleport from here.");
			return;
		}
		if (c.inRandomEvent() && RandomEvents.canTeleFromRandom == false) {
			c.sendMessage("Please finnish the random event first.");
			return;
		}
		if (c.inFoGGame || c.inFoGWait) {
			c.sendMessage("You can't teleport at the moment.");
			return;
		}
		if (Config.SOUND) {
			c.sendSound(c.getSound().TELEPORT);
		}
		if (c.inCwWait) {
			c.sendMessage("To leave, use the portal!");
			return;
		}
		if (c.inTrade || c.InDung() || c.isBanking) {
			c.sendMessage("You can't teleport right now!");
			return;
		}

		if (c.inBarbDef == true) {
			c.getPA().moveBarb();
			return;
		}
		if (c.inCwWait) {
			c.sendMessage("To leave, use the portal!");
			return;
		}
		if (c.isNpc == true) {
			c.sendMessage("Type ::return before you can do that!");
			return;
		}
		fixBugs();
		c.dialogueAction = -1;
		c.getPA().resetFishing();
		// c.getMining().resetMining();
		c.isCooking = false;
		c.playerIsFiremaking = false;
		if (c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL
				&& !c.inFunPk()) {
			c.sendMessage("You can't teleport above level "
					+ Config.NO_TELEPORT_WILD_LEVEL + " in the wilderness.");
			return;
		}
		if (c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if (c.InDung() || c.InDung2() || c.inDungBossRoom()) {
			c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders or do ::enddung");
			return;
		}
		if (c.inPits || c.viewingOrb || inPitsWait()) {
			c.sendMessage("You can't teleport in here!");
			return;
		}
		if (c.inGWD()) {
			ResetGWKC();
		}
		if (c.inJail() && c.Jail == true) {
			c.sendMessage("You can't teleport out of prison fucking fool!");
			return;
		}
		if (c.Jail == true) {
			c.sendMessage("You can't teleport out of prison fucking goon!");
			return;
		}
		if (c.inWarriorG() && c.heightLevel == 2) {
			c.sendMessage("You can't teleport out of Warrior Guild!");
			return;
		}
		if (c.inRFD()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if (c.inFightCaves()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if (c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "
					+ Config.NO_TELEPORT_WILD_LEVEL + " in the wilderness.");
			return;
		}
		if (System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if (!c.isDead && c.teleTimer == 0 && c.respawnTimer == -6) {
			if (c.playerIndex > 0 || c.npcIndex > 0) {
				c.getCombat().resetPlayerAttack();
			}
			c.stopMovement();
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			removeAllWindows();
			c.setAppearanceUpdateRequired(true);
			c.updateTimer = 45;
			c.getTimers().updatePlayerLook(c);
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;
			c.playerStandIndex = 0x328;
			c.playerTurnIndex = 0x337;
			c.playerWalkIndex = 0x333;
			c.playerTurn180Index = 0x334;
			c.playerTurn90CWIndex = 0x335;
			c.playerTurn90CCWIndex = 0x336;
			c.playerRunIndex = 0x338;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			if (teleportType.equalsIgnoreCase("modern")) {
				c.getTradeAndDuel().declineTrade();
				c.startAnimation(8939);
				c.teleTimer = 9;
				c.gfx0(1576);
				c.teleEndGfx = 1577;
				c.teleEndAnimation = 8941;
			}

			if (teleportType.equalsIgnoreCase("dungtele")) {
				c.getTradeAndDuel().declineTrade();
				c.startAnimation(13652);
				c.teleTimer = 16;
				c.gfx0(2602);
				c.teleEndAnimation = 13654;
			}

			if (teleportType.equalsIgnoreCase("ancient")) {
				c.getTradeAndDuel().declineTrade();
				c.startAnimation(9599);
				c.teleGfx = 0;
				c.teleTimer = 11;
				c.teleEndAnimation = 8941;
				c.gfx0(1681);
			}

		}
		c.updateWalkEntities();
		// c.updatePlayerLook();
	}

	public void startTeleport2(int x, int y, int height) {
		if (c.inDung) {
			c.getDH().sendDialogues(256, -1);
			return;
		}
		if (c.insideDuelArena() && c.duelStatus == 5) {
			c.sendMessage("You cannot teleport from here.");
			return;
		}
		if (c.inTrade || c.InDung() || c.isBanking) {
			c.sendMessage("You can't teleport right now!");
			return;
		}
		if (c.inFoGGame || c.inFoGWait) {
			c.sendMessage("You can't teleport at the moment.");
			return;
		}
		if (c.inRandomEvent() && RandomEvents.canTeleFromRandom == false) {
			c.sendMessage("Please finnish the random event first.");
			return;
		}
		if (c.inCwWait) {
			c.sendMessage("To leave, use the portal!");
			return;
		}

		if (c.isNpc == true) {
			c.sendMessage("Type ::return before you can do that!");
			return;
		}
		c.dialogueAction = 0;
		if (c.inBarbDef == true) {
			c.getPA().moveBarb();
			return;
		}
		fixBugs();
		c.getPA().resetFishing();
		// c.getMining().resetMining();
		c.isCooking = false;
		c.playerIsFiremaking = false;
		if (c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		/*
		 * if(c.inNomad()) {
		 * c.sendMessage("You can't teleport during Nomad Minigame"); return; }
		 * if(c.inGoblin()) {
		 * c.sendMessage("You can't teleport during Goblin Minigame"); return; }
		 */
		if (c.Jail == true) {
			c.sendMessage("You can't teleport out of prison idiot!");
			return;
		}
		if (c.InDung() || c.InDung2() || c.inDungBossRoom()) {
			c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders!");
			return;
		}
		if (c.inGWD()) {
			ResetGWKC();
		}
		if (c.inJail() && c.Jail == true) {
			c.sendMessage("You can't teleport out of prison fucking goon!");
			return;
		}
		if (System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if (!c.isDead && c.teleTimer == 0) {
			c.getTradeAndDuel().declineTrade();
			c.stopMovement();
			removeAllWindows();
			c.setAppearanceUpdateRequired(true);
			c.updateTimer = 45;
			c.getTimers().updatePlayerLook(c);
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;
			c.startAnimation(714);
			c.teleTimer = 11;
			c.teleGfx = 308;
			c.teleEndAnimation = 715;
			c.playerStandIndex = 0x328;
			c.playerTurnIndex = 0x337;
			c.playerWalkIndex = 0x333;
			c.playerTurn180Index = 0x334;
			c.playerTurn90CWIndex = 0x335;
			c.playerTurn90CCWIndex = 0x336;
			c.playerRunIndex = 0x338;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.updateWalkEntities();

		}
	}

	public void startTutorial() {

	}

	// }
	/**
	 ** GFX
	 **/
	public void stillGfx(int id, int x, int y, int height, int time) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC(y - (c.getMapRegionY() * 8));
			c.getOutStream().writeByteC(x - (c.getMapRegionX() * 8));
			c.getOutStream().createFrame(4);
			c.getOutStream().writeByte(0);
			c.getOutStream().writeWord(id);
			c.getOutStream().writeByte(height);
			c.getOutStream().writeWord(time);
			c.flushOutStream();
		}
	}

	public void stopDiagonal(int otherX, int otherY) {
		if (c.freezeDelay > 0) {
			return;
		}
		c.newWalkCmdSteps = 1;
		int xMove = otherX - c.getX();
		int yMove = 0;
		if (xMove == 0) {
			yMove = otherY - c.getY();
		}
		/*
		 * if (!clipHor) { yMove = 0; } else if (!clipVer) { xMove = 0; }
		 */

		int k = c.getX() + xMove;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		int l = c.getY() + yMove;
		l -= c.mapRegionY * 8;

		for (int n = 0; n < c.newWalkCmdSteps; n++) {
			c.getNewWalkCmdX()[n] += k;
			c.getNewWalkCmdY()[n] += l;
		}

	}

	public void Summon(int frame, int item, int slot, int amount) {

		if (c.getOutStream() != null && c != null) {
			c.outStream.createFrameVarSizeWord(34);
			c.outStream.writeWord(frame);
			c.outStream.writeByte(c.summoningslot);
			c.outStream.writeWord(item + 1);
			c.outStream.writeByte(255);
			c.outStream.writeDWord(amount);
			c.outStream.endFrameVarSizeWord();
		}

	}

	public void takeOut(int itemID, int fromSlot, int amount, boolean x) {
		for (int j = 0; j < Config.BANK_SIZE; j++) {
			if (c.bankItems[j] > 0) {
				if (c.bankItems[j] - 1 == itemID) {
					if (x) {// means their using remove x
						if (amount > c.getItems().freeSlots()
								& amount > c.bankItemsN[j]
								& c.bankItemsN[j] >= amount) {
							c.bankItemsN[j] -= c.getItems().freeSlots();
							c.getItems().addItem(itemID,
									c.getItems().freeSlots());
						} else {
							c.getItems().addItem(itemID, amount);
							c.bankItemsN[j] -= amount;
							if (c.bankItemsN[j] == 0) {
								c.bankItems[j] = 0;
							}
							c.itemsN[fromSlot] = 0;
						}
					} else if (amount == -1) {// their using remove all
						if (c.bankItemsN[j] > c.getItems().freeSlots()) {
							c.bankItemsN[j] -= c.getItems().freeSlots();
							c.itemsN[fromSlot] -= c.getItems().freeSlots();
							c.getItems().addItem(itemID,
									c.getItems().freeSlots());
						}
						c.getItems().addItem(itemID, c.bankItemsN[j]);
						c.bankItemsN[j] = 0;
						c.bankItems[j] = 0;
						c.items[fromSlot] = 0;
						c.itemsN[fromSlot] = 0;
						break;
					} else if ((c.bankItemsN[j] - amount) > 0) {
						if (amount > c.bankItemsN[j]) {
							if (!c.getItems().addItem(itemID, c.bankItemsN[j])) {
								break;
							}
							c.bankItemsN[j] -= c.bankItemsN[j];
							c.bankItems[j] = 0;
							c.itemsN[fromSlot] -= c.bankItemsN[j];
							c.items[fromSlot] = 0;
							break;
						}
						if (!c.getItems().addItem(itemID, amount)) {
							break;
						}
						c.bankItemsN[j] -= amount;
						c.itemsN[fromSlot] -= amount;
						c.items[fromSlot] = 0;
						if (c.bankItemsN[j] == 0) {
							c.bankItems[j] = 0;
						}
					} else {
						if (amount > c.bankItemsN[j]) {
							if (!c.getItems().addItem(itemID, c.bankItemsN[j])) {
								break;
							}
							c.bankItems[j] = 0;
							c.bankItemsN[j] -= c.bankItemsN[j];
							c.itemsN[fromSlot] -= c.bankItemsN[j];
							c.items[fromSlot] = 0;
							break;
						}
						if (!c.getItems().addItem(itemID, amount)) {
							break;
						}
						c.bankItems[j] = 0;
						c.bankItemsN[j] -= amount;
						c.items[fromSlot] = 0;
						c.itemsN[fromSlot] -= amount;
					}
				}
			}
		}
		c.getItems().resetTempItems();
		c.lastSearch = true;
		searchBank(c.searchName);
	}

	public void teleDungHome(int x, int y, int height, String teleportType) {
		if (c.completed) {
			c.getDH().sendDialogues(256, -1);
			return;
		}
		c.stopMovement();
		c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
				: c.playerMagicBook == 1 ? 12855
						: c.playerMagicBook == 2 ? 29999 : 12855);
		removeAllWindows();
		c.setAppearanceUpdateRequired(true);
		c.updateTimer = 45;
		c.getTimers().updatePlayerLook(c);
		c.teleX = x;
		c.teleY = y;
		c.npcIndex = 0;
		c.playerIndex = 0;
		c.faceUpdate(0);
		c.teleHeight = height;
		c.playerStandIndex = 0x328;
		c.playerTurnIndex = 0x337;
		c.playerWalkIndex = 0x333;
		c.playerTurn180Index = 0x334;
		c.playerTurn90CWIndex = 0x335;
		c.playerTurn90CCWIndex = 0x336;
		c.playerRunIndex = 0x338;
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
		if (teleportType.equalsIgnoreCase("modern")) {
			c.getTradeAndDuel().declineTrade();
			c.startAnimation(8939);
			c.teleTimer = 9;
			c.gfx0(1576);
			c.teleEndGfx = 1577;
			c.teleEndAnimation = 8941;
		}

		if (teleportType.equalsIgnoreCase("dungtele")) {
			c.getTradeAndDuel().declineTrade();
			c.startAnimation(13652);
			c.teleTimer = 16;
			c.gfx0(2602);
			c.teleEndAnimation = 13654;
		}

		if (teleportType.equalsIgnoreCase("ancient")) {
			c.getTradeAndDuel().declineTrade();
			c.startAnimation(9599);
			c.teleGfx = 0;
			c.teleTimer = 11;
			c.teleEndAnimation = 8941;
			c.gfx0(1681);
		}
		c.updateWalkEntities();
		c.updateTimer = 45;
		c.getTimers().updatePlayerLook(c);
	}

	public void teleTabTeleport(int x, int y, int height, String teleportType) {
		if (!c.isDead && c.teleTimer == 0 && c.respawnTimer == -6) {
			if (c.playerIndex > 0 || c.npcIndex > 0) {
				c.startAnimation(4069);
			}
			c.getCombat().resetPlayerAttack();
			c.stopMovement();
			removeAllWindows();
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;
			if (teleportType.equalsIgnoreCase("teleTab")) {
				c.foodDelay = System.currentTimeMillis();
				c.startAnimation(4731);
				c.gfx0(678);
				c.foodDelay = System.currentTimeMillis();
				c.teleTimer = 8;
				c.teleEndAnimation = 1;
			}
			if (teleportType.equalsIgnoreCase("House")) {
				c.foodDelay = System.currentTimeMillis();
				c.startAnimation(4731);
				c.gfx0(678);
				Construction.spawnObjects(c);
				Construction.spawnButler(c, 4241, 3, true, 4240);
				c.foodDelay = System.currentTimeMillis();
				c.teleTimer = 8;
				c.teleEndAnimation = 1;
			}
		}
	}

	// }
	public void totallevelsupdate() {
		sendFrame126("Levels: " + getTotalLevel(), 3984);
	}

	public void updatePM(int pID, int world) { // used for private chat updates
		Player p = PlayerHandler.players[pID];
		if (p == null || p.playerName == null || p.playerName.equals("null")) {
			return;
		}
		Client o = (Client) p;
		long l = Misc.playerNameToInt64(PlayerHandler.players[pID].playerName);

		if (p.privateChat == 0) {
			for (int i = 0; i < c.friends.length; i++) {
				if (c.friends[i] != 0) {
					if (l == c.friends[i]) {
						loadPM(l, world);
						return;
					}
				}
			}
		} else if (p.privateChat == 1) {
			for (int i = 0; i < c.friends.length; i++) {
				if (c.friends[i] != 0) {
					if (l == c.friends[i]) {
						if (o.getPA().isInPM(
								Misc.playerNameToInt64(c.playerName))) {
							loadPM(l, world);
							return;
						} else {
							loadPM(l, 0);
							return;
						}
					}
				}
			}
		} else if (p.privateChat == 2) {
			for (int i = 0; i < c.friends.length; i++) {
				if (c.friends[i] != 0) {
					if (l == c.friends[i] && c.playerRights < 2) {
						loadPM(l, 0);
						return;
					}
				}
			}
		}
	}

	public void updateResult(int tab, int[] array, int[] arrayN) {
		c.searchTerm = c.searchTerm.toLowerCase().replaceAll(" ", "_");
		// c.sendMessage("searching with " + searchTerm);
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (arrayN[i] > 0) {
				String itemName = (c.getItems().getItemName(array[i] - 1))
						.toLowerCase().replaceAll(" ", "_");
				if (itemName.indexOf(c.searchTerm) != -1) {
					// c.sendMessage("found" + c.bankItems[i]);
					tempItemsT[index] = tab;
					tempItemsS[index] = i;
					tempItems[index] = array[i];
					tempItemsN[index++] = arrayN[i];
				} else {
					// c.sendMessage("nah, " + itemName + " cant be " +
					// searchTerm);
				}
			}
		}
	}

	public void useOperate(int itemId) {
		switch (itemId) {
		case 1712:
		case 1710:
		case 1708:
		case 1706:
			handleGlory(itemId);
			break;
		case 11283:
		case 11284:
			c.sendMessage("Your shield has " + c.dfsCount + " charges");
			if (c.playerIndex > 0) {
				c.getCombat().handleDfs(c);
			} else if (c.npcIndex > 0) {
				c.getCombat().handleDfsNPC(c);
			}
			break;
		}
	}

	public void vengMe() {
		if (System.currentTimeMillis() - c.lastVeng > 30000) {
			if (c.getItems().playerHasItem(557, 10)
					&& c.getItems().playerHasItem(9075, 4)
					&& c.getItems().playerHasItem(560, 2)) {
				c.vengOn = true;
				c.lastVeng = System.currentTimeMillis();
				c.startAnimation(4410);
				c.gfx100(726);
				c.getItems().deleteItem(557, c.getItems().getItemSlot(557), 10);
				c.getItems().deleteItem(560, c.getItems().getItemSlot(560), 2);
				c.getItems()
						.deleteItem(9075, c.getItems().getItemSlot(9075), 4);
			} else {
				c.sendMessage("You do not have the required runes to cast this spell. (9075 for astrals)");
			}
		} else {
			c.sendMessage("You must wait 30 seconds before casting this again.");
		}
	}

	public void vengOther() {
		if (c.playerIndex > 0) {
			Player q = PlayerHandler.players[c.playerIndex];
			final int oX = q.getX();
			final int oY = q.getY();
			if (c.playerLevel[6] < 93) {
				c.sendMessage("You need a magic level of 93 to cast this spell.");
				c.getCombat().resetPlayerAttack();
				c.stopMovement();
				c.turnPlayerTo(oX, oY);
				return;
			}
			if (!q.acceptAid) {
				c.sendMessage("This player has their accept Aid off, therefore you cannot veng them!");
				return;
			}
			if (q.inDuelArena() || c.inDuelArena()) {
				c.sendMessage("You can't veng/veng others in duel arena.");
				return;
			}
			if (c.playerLevel[1] < 40) {
				c.sendMessage("You need a defence level of 40 to cast this spell.");
				c.getCombat().resetPlayerAttack();
				c.stopMovement();
				c.turnPlayerTo(oX, oY);
				return;
			}
			if (!c.getItems().playerHasItem(9075, 3)
					|| !c.getItems().playerHasItem(557, 10)
					|| !c.getItems().playerHasItem(560, 2)) {
				c.sendMessage("You don't have the required runes to cast this spell.");
				c.getCombat().resetPlayerAttack();
				c.stopMovement();
				c.turnPlayerTo(oX, oY);
				return;
			}
			if (System.currentTimeMillis() - c.lastCast < 30000) {
				c.sendMessage("You can only cast vengeance every 30 seconds.");
				c.getCombat().resetPlayerAttack();
				c.stopMovement();
				c.turnPlayerTo(oX, oY);
				return;
			}
			if (q.vengOn) {
				c.sendMessage("That player already have vengeance casted.");
				c.getCombat().resetPlayerAttack();
				c.stopMovement();
				c.turnPlayerTo(oX, oY);
				return;
			}
			c.startAnimation(4411);
			q.gfx100(725);// Just use c.gfx100
			c.getItems().deleteItem2(9075, 3);
			c.getItems().deleteItem2(557, 10);// For these you need to change to
			// deleteItem(item, itemslot,
			// amount);.
			c.getItems().deleteItem2(560, 2);
			q.vengOn = true;
			addSkillXP(2000, 6);
			c.turnPlayerTo(oX, oY);
			refreshSkill(6);
			c.getCombat().resetPlayerAttack();
			c.stopMovement();
			c.lastCast = System.currentTimeMillis();
		}
	}

	public void vetEmote(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.gfx0(450);
				c.startAnimation(352);
				c.isDoingVetCapeEmote = true;
				container.stop();

			}

			@Override
			public void stop() {
				c.isDoingVetCapeEmote = false;
			}
		}, 1);
	}

	public void viewInventory(Client o) {
		if (o == null)
			return;

		sendFrame126("Inventory of " + Misc.formatPlayerName(o.displayName),
				2806);

		c.getPA().sendFrame248(2800, 3213);

		for (int i = 0; i < o.playerItems.length; i++) {
			if (o.playerItems[i] > 0)
				itemOnInterface(c, 2802, i, o.playerItems[i] - 1,
						o.playerItemsN[i]);
			else
				itemOnInterface(c, 2802, i, -1, 0);
			// c.sendMessage("Item: "+c.getItems().getItemName(o.playerItems[i]
			// - 1).toLowerCase().replaceAll("_",
			// "")+" ("+o.playerItemsN[i]+")");
		}
		c.interfaceId = 0;
	}

	public void walkableInterface(int id) {
		// synchronized(c) {
		if (c.getOutStream() != null && c != null) {
			c.getOutStream().createFrame(208);
			c.getOutStream().writeWordBigEndian_dup(id);
			c.flushOutStream();
		}
	}

	public void walkTo(int i, int j) {
		c.newWalkCmdSteps = 0;
		if (++c.newWalkCmdSteps > 50) {
			c.newWalkCmdSteps = 0;
		}
		int k = c.getX() + i;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		int l = c.getY() + j;
		l -= c.mapRegionY * 8;

		for (int n = 0; n < c.newWalkCmdSteps; n++) {
			c.getNewWalkCmdX()[n] += k;
			c.getNewWalkCmdY()[n] += l;
		}
	}

	public void walkTo2(int i, int j) {
		if (c.freezeDelay > 0) {
			return;
		}
		c.newWalkCmdSteps = 0;
		if (++c.newWalkCmdSteps > 50) {
			c.newWalkCmdSteps = 0;
		}
		int k = c.getX() + i;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		int l = c.getY() + j;
		l -= c.mapRegionY * 8;

		for (int n = 0; n < c.newWalkCmdSteps; n++) {
			c.getNewWalkCmdX()[n] += k;
			c.getNewWalkCmdY()[n] += l;
		}
	}

	public void walkToCheck(int i, int j) {
		if (c.freezeDelay > 0) {
			return;
		}
		c.newWalkCmdSteps = 0;
		if (++c.newWalkCmdSteps > 50) {
			c.newWalkCmdSteps = 0;
		}
		int k = c.getX() + i;
		k -= c.mapRegionX * 8;
		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;
		int l = c.getY() + j;
		l -= c.mapRegionY * 8;

		for (int n = 0; n < c.newWalkCmdSteps; n++) {
			c.getNewWalkCmdX()[n] += k;
			c.getNewWalkCmdY()[n] += l;
		}
	}

	public boolean wearingBrawlersCooking(int brawlers) {
		int brawler[] = { 13857 };
		for (int i = 0; i < brawler.length; i++) {
			if (brawler[i] == brawlers) {
				return true;
			}
		}
		return false;
	}

	public boolean wearingCape(int cape) {
		int capes[] = { 9747, 9748, 9750, 9751, 9753, 9754, 9756, 9757, 9759,
				9760, 9762, 9763, 9765, 9766, 9768, 9769, 9771, 9772, 9774,
				9775, 9777, 9778, 9780, 9781, 9783, 9784, 9786, 9787, 9789,
				9790, 9792, 9793, 9795, 9796, 9798, 9799, 9801, 9802, 9804,
				9805, 9807, 9808, 9810, 9811, 9813, 9948, 9949, 12170 };
		for (int i = 0; i < capes.length; i++) {
			if (capes[i] == cape) {
				return true;
			}
		}
		return false;
	}

	public void writeChatLog(String data) {
		checkDateAndTime();
		String filePath = "Data/ChatLogs/" + c.playerName + ".txt";
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("[" + c.date + "]" + "-" + "[" + c.currentTime + " "
					+ checkTimeOfDay() + "]: " + "[" + c.connectedFrom + "]: "
					+ "" + data + " ");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
				}
			}
		}
	}

	public void writeCommandLog(String command) {
		checkDateAndTime();
		String filePath = "Data/Commands2.txt";
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("[" + c.date + "]" + "-" + "[" + c.currentTime + " "
					+ checkTimeOfDay() + "]: " + "[" + c.playerName + "]: "
					+ "[" + c.connectedFrom + "] " + "::" + command);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
				}
			}
		}
	}

	public void writeDonator() {
		String filePath = "Data/Donators/" + Misc.optimizeText(c.playerName)
				+ ".txt";
		BufferedWriter bw = null;

		try {
			String playername = c.playerName;
			bw = new BufferedWriter(new FileWriter(filePath, true));

			bw.write("" + Misc.optimizeText(playername) + " isDonator = "
					+ c.isDonator + ".");
			System.out.println("Wrote " + Misc.optimizeText(playername)
					+ " to Donator's list.");

			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
				}
			}
		}
	}

	public void writePMLog(String data) {
		checkDateAndTime();
		String filePath = "Data/PMLogs/" + c.playerName + ".txt";
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(filePath, true));
			bw.write("[" + c.date + "]" + "-" + "[" + c.currentTime + " "
					+ checkTimeOfDay() + "]: " + "[" + c.connectedFrom + "]: "
					+ "" + data + " ");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
				}
			}
		}
	}

	public void writeQuestTab() {
		getPlayerRank();
		calculateTask();
		c.getPA().sendFrame126("divinationofgods.com", 39164); // quest title
		c.getPA().sendFrame126("@gre@Points Handler", 39168);
		c.getPA().sendFrame126("@gre@Total Kills", 39169);
		c.getPA().sendFrame126("@gre@Total Deaths", 39170);
		c.getPA().sendFrame126("@gre@Save Account", 39171);
		// c.getPA().sendFrame126("@gre@Bonus Skill:@cya@ "+c.bonusSkill+", 2x XP!",
		// 39172);
		c.getPA().sendFrame126("", 39172);
	}

	public void writeTabs() {
		writeQuestTab();
		// writeDungTab();
	}
	
	public void clearClanChat() {
		c.clanId = -1;
		c.inAclan = false;
		c.getPA().sendFrame126("Talking in: ", 18139);
		c.getPA().sendFrame126("Owner: ", 18140);
		for (int j = 18144; j < 18244; j++)
			c.getPA().sendFrame126("", j);
		sendFrame36(724, 0);
	}
}