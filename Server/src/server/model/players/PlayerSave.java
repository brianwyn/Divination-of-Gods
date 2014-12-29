package server.model.players;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.Config;
import server.model.players.content.RandomEvents; // Save the old location
import server.util.Misc;

public class PlayerSave {

	/**
	 * Returns a characters friends in the form of a long array.
	 * 
	 * @param name
	 * @return
	 */
	public static long[] getFriends(String name) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean end = false;
		int readMode = 0;
		BufferedReader file = null;
		boolean file1 = false;
		long[] readFriends = new long[200];
		long[] friends = null;
		int totalFriends = 0;
		try {
			file = new BufferedReader(new FileReader(Config.PLAYERDATA_PATH
					+ "/Characters/" + name + ".txt"));
			file1 = true;
		} catch (FileNotFoundException fileex1) {
		}

		if (file1) {
			new File(Config.PLAYERDATA_PATH + "/Characters/" + name + ".txt");
		} else {
			return null;
		}
		try {
			line = file.readLine();
		} catch (IOException ioexception) {
			return null;
		}
		while (end == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (readMode) {
				case 0:
					if (token.equals("character-friend")) {
						readFriends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
						totalFriends++;
					}
					break;
				}
			} else {
				if (line.equals("[FRIENDS]")) {
					readMode = 0;
				} else if (line.equals("[EOF]")) {
					try {
						file.close();
					} catch (IOException ioexception) {
					}
				}
			}
			try {
				line = file.readLine();
			} catch (IOException ioexception1) {
				end = true;
			}
		}
		try {
			if (totalFriends > 0) {
				friends = new long[totalFriends];
				for (int index = 0; index < totalFriends; index++) {
					friends[index] = readFriends[index];
				}
				return friends;
			}
			file.close();
		} catch (IOException ioexception) {
		}
		return null;
	}

	/**
	 * Tells use whether or not the specified name has the friend added.
	 * 
	 * @param name
	 * @param friend
	 * @return
	 */
	public static boolean isFriend(String name, String friend) {
		long nameLong = Misc.playerNameToInt64(friend);
		long[] friends = getFriends(name);
		if (friends != null && friends.length > 0) {
			for (int index = 0; index < friends.length; index++) {
				if (friends[index] == nameLong) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Loading
	 **/
	public static int loadGame(Client p, String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		boolean File1 = false;
		try {
			if (p != null) {
				if (playerExists(Misc.removeSpaces(playerName))
						&& !playerExists(playerName)) {
					p.disconnected = true;
					return 3;
				}
				characterfile = new BufferedReader(new FileReader(
						Config.PLAYERDATA_PATH + "/Characters/" + playerName
								+ ".txt"));
				File1 = true;
			}
		} catch (FileNotFoundException fileex1) {
		}

		if (File1) {
			// new File (Config.CHAR_PATH + "/" +playerName+".txt");
		} else {
			Misc.println(playerName + ": character file not found.");
			p.newPlayer = false;
			return 0;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			Misc.println(playerName + ": Error loading file!");
			return 3;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-password")) {
						if (playerPass.equalsIgnoreCase(token2)
								|| Misc.basicEncrypt(playerPass).equals(token2)
								|| Misc.md5Hash(playerPass).equals(token2)) {
							playerPass = token2;
						} else {
							return 3;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						p.heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						p.teleportToX = (Integer.parseInt(token2) <= 0 ? 3210
								: Integer.parseInt(token2));
					} else if (token.equals("character-posy")) {
						p.teleportToY = (Integer.parseInt(token2) <= 0 ? 3424
								: Integer.parseInt(token2));
					} else if (token.equals("character-rights")) {
						p.playerRights = Integer.parseInt(token2);
					} else if (token.equals("targetIndex")) {
						p.targetIndex = Integer.parseInt(token2);
					} else if (token.equals("EPMINUTES")) {
						p.EP_MINUTES = Integer.parseInt(token2);
					} else if (token.equals("EP")) {
						p.EP = Integer.parseInt(token2);
					} else if (token.equals("payment")) {
						p.payment = Integer.parseInt(token2);
					} else if (token.equals("payButlerReq")) {
						p.payButlerReq = Integer.parseInt(token2);
					} else if (token.equals("bountyIcon")) {
						p.bountyIcon = Integer.parseInt(token2);
					} else if (token.equals("safeTimer")) {
						p.safeTimer = Integer.parseInt(token2);
					} else if (token.equals("TargetPercentage")) {
						p.targetPercentage = Integer.parseInt(token2);
					} else if (token.equals("Commendations")) {
						p.Commendations = Integer.parseInt(token2);
					} else if (token.equals("summonTime")) {
						p.summonTime = Integer.parseInt(token2);
						/*
						 * } else if (token.equals("bhenter")) { p.enterBHTime =
						 * Integer.parseInt(token2); } else if
						 * (token.equals("bhleave")) { p.enterBHTime =
						 * Integer.parseInt(token2); } else if
						 * (token.equals("bhpickup")) { p.enterBHTime =
						 * Integer.parseInt(token2); } else if
						 * (token.equals("bhcash")) { p.enterBHTime =
						 * Integer.parseInt(token2); } else if
						 * (token.equals("bhcrater")) { p.enterBHTime =
						 * Integer.parseInt(token2); } else if
						 * (token.equals("bhrogue")) { p.rogueKill =
						 * Integer.parseInt(token2); } else if
						 * (token.equals("bhtarget")) { p.targetKill =
						 * Integer.parseInt(token2);
						 */

					} else if (token.equals("timeOnline")) {
						p.timeOnline = Integer.parseInt(token2);
					} else if (token.equals("player-god")) {
						p.setPlayerGod(Integer.parseInt(token2));
					} else if (token.equals("character-yellTag")) {
						p.customYellTag = token2;
					} else if (token.equals("bind1")) {
						p.bind1 = Integer.parseInt(token2);
					} else if (token.equals("bind2")) {
						p.bind2 = Integer.parseInt(token2);
					} else if (token.equals("bind3")) {
						p.bind3 = Integer.parseInt(token2);
					} else if (token.equals("bind4")) {
						p.bind4 = Integer.parseInt(token2);
					} else if (token.equals("npcsKilled")) {
						p.npcsKilled = Integer.parseInt(token2);
					} else if (token.equals("banned")) {
						p.banned = Integer.parseInt(token2);
					} else if (token.equals("dungeonId")) {
						p.dungeonId = Integer.parseInt(token2);
					} else if (token.equals("TPoints")) {
						p.TPoints = Integer.parseInt(token2);
					} else if (token.equals("firesL")) {
						p.firesL = Integer.parseInt(token2);
					} else if (token.equals("rocksM")) {
						p.rocksM = Integer.parseInt(token2);
					} else if (token.equals("smithM")) {
						p.smithM = Integer.parseInt(token2);
					} else if (token.equals("spinFL")) {
						p.spinFL = Integer.parseInt(token2);
					} else if (token.equals("pcGame")) {
						p.pcGame = Integer.parseInt(token2);
					} else if (token.equals("sumfam")) {
						p.sumfam = Integer.parseInt(token2);
					} else if (token.equals("bonesB")) {
						p.bonesB = Integer.parseInt(token2);
					} else if (token.equals("redTop")) {
						p.redTop = Integer.parseInt(token2);
					} else if (token.equals("impCat")) {
						p.impCat = Integer.parseInt(token2);
					} else if (token.equals("slayTa")) {
						p.slayTa = Integer.parseInt(token2);
					} else if (token.equals("door1")) {
						p.door1 = Boolean.parseBoolean(token2);
					} else if (token.equals("door2")) {
						p.door2 = Boolean.parseBoolean(token2);
					} else if (token.equals("door3")) {
						p.door3 = Boolean.parseBoolean(token2);
					} else if (token.equals("door4")) {
						p.door4 = Boolean.parseBoolean(token2);
					} else if (token.equals("receivedItems")) {
						p.receivedItems = Boolean.parseBoolean(token2);
					} else if (token.equals("inDung")) {
						p.inDung = Boolean.parseBoolean(token2);
					} else if (token.equals("completed")) {
						p.completed = Boolean.parseBoolean(token2);

					} else if (token.equals("mustPay")) {
						p.mustPay = Boolean.parseBoolean(token2);

					} else if (token.equals("character-title")) {
						p.playerTitle = Integer.parseInt(token2);
					} else if (token.equals("yellTimer")) {
						p.yellTimer = Integer.parseInt(token2);
					} else if (token.equals("dungPoints")) {
						p.dungPoints = Integer.parseInt(token2);
					} else if (token.equals("funPoints")) {
						p.funPoints = Integer.parseInt(token2);
					} else if (token.equals("votePoints")) {
						p.votePoints = Integer.parseInt(token2);
					} else if (token.equals("FoGRating")) {
						p.FoGRating = Integer.parseInt(token2);
					} else if (token.equals("effigy")) {
						p.effigy = Integer.parseInt(token2);
					} else if (token.equals("pkinPoints")) {
						p.pkinPoints = Integer.parseInt(token2);
					} else if (token.equals("slayerTaskStreak")) {
						p.slayerTaskStreak = Integer.parseInt(token2);
					} else if (token.equals("slayerPoints")) {
						p.slayerPoints = Integer.parseInt(token2);
					} else if (token.equals("randomEventTimer")) {
						p.randomEventTimer = Integer.parseInt(token2);
					} else if (token.equals("dominionPoints")) {
						p.dominionPoints = Integer.parseInt(token2);
						/*
						 * } else if (token.equals("Used-Puremaster")) { p.pure
						 * = Integer.parseInt(token2); } else if
						 * (token.equals("tutorial-progress")) { p.tutorial =
						 * Integer.parseInt(token2);
						 */
					} else if (token.equals("MoneyOrb")) {
						p.MoneyCash = Integer.parseInt(token2);
					} else if (token.equals("email")) {
						p.email = token2;
					} else if (token.equals("lastFighter")) {
						p.lastFighter = token2;
					} else if (token.equals("connectedFrom")) {
						p.connectedFrom = token2;
					} else if (token.equals("skull-timer")) {
						p.skullTimer = Integer.parseInt(token2);
					} else if (token.equals("character-lastkilled")) {
						p.lastKilled = token2;
					} else if (token.equals("EP")) {
						p.earningPotential = Integer.parseInt(token2);
					} else if (token.equals("epDelay")) {
						p.epDelay = Integer.parseInt(token2);
					} else if (token.equals("taskType")) {
						p.taskType = Integer.parseInt(token2);
					} else if (token.equals("clan-name")) {
						p.clanName = token2;
					} else if (token.equals("clan-pass")) {
						p.clanPass = token2;
					} else if (token.equals("clan-owner")) {
						p.hasClan = Boolean.parseBoolean(token2);
					} else if (token.equals("bankPin1")) {
						p.bankPin1 = Integer.parseInt(token2);
					} else if (token.equals("bankPin2")) {
						p.bankPin2 = Integer.parseInt(token2);
					} else if (token.equals("bankPin3")) {
						p.bankPin3 = Integer.parseInt(token2);
					} else if (token.equals("bankPin4")) {
						p.bankPin4 = Integer.parseInt(token2);
					} else if (token.equals("blackMark")) {
						p.blackMark = Integer.parseInt(token2);
					} else if (token.equals("cwDeaths")) {
						p.cwDeaths = Integer.parseInt(token2);
					} else if (token.equals("cwGames")) {
						p.cwGames = Integer.parseInt(token2);
					} else if (token.equals("cwKills")) {
						p.cwKills = Integer.parseInt(token2);
					} else if (token.equals("blackMark")) {
						p.blackMark = Integer.parseInt(token2);
					} else if (token.equals("hasBankPin")) {
						p.hasBankPin = Boolean.parseBoolean(token2);
					} else if (token.equals("chatId")) {
						p.chatId = Integer.parseInt(token2);
					} else if (token.equals("addChaotics")) {
						p.addChaotics = Boolean.parseBoolean(token2);
					} else if (token.equals("getIt")) {
						p.getIt = Boolean.parseBoolean(token2);
					} else if (token.equals("VL")) {
						p.vlsLeft2 = Integer.parseInt(token2);
					} else if (token.equals("CoolDownTimer")) {
						Client.CoolDownTimer = Integer.parseInt(token2);
					} else if (token.equals("CoolDownTimer2")) {
						Client.CoolDownTimer2 = Integer.parseInt(token2);
					} else if (token.equals("kingQuest")) {
						p.kingQuest = Integer.parseInt(token2);
						/*
						 * } else if (token.equals("runeQuest")) { p.rMQ =
						 * Integer.parseInt(token2); } else if
						 * (token.equals("herbQuest")) { p.bMQ =
						 * Integer.parseInt(token2);
						 */
					} else if (token.equals("character-longsword")) {
						p.vlsLeft = Integer.parseInt(token2);
					} else if (token.equals("character-warhammer")) {
						p.statLeft = Integer.parseInt(token2);
					} else if (token.equals("character-spear")) {
						p.vSpearLeft = Integer.parseInt(token2);
					} else if (token.equals("character-chainbody")) {
						p.vTopLeft = Integer.parseInt(token2);
					} else if (token.equals("shopcollect")) {
						p.playerCollect = Integer.parseInt(token2);
					} else if (token.equals("santaPrize")) {
						p.santaPrize = Integer.parseInt(token2);
						// }
					} else if (token.equals("character-chainskirt")) {
						p.vLegsLeft = Integer.parseInt(token2);
					} else if (token.equals("character-full helm")) {
						p.sHelmLeft = Integer.parseInt(token2);
					} else if (token.equals("character-platebody")) {
						p.sTopLeft = Integer.parseInt(token2);
					} else if (token.equals("character-platelegs")) {
						p.sLegsLeft = Integer.parseInt(token2);
					} else if (token.equals("character-hood")) {
						p.zHoodLeft = Integer.parseInt(token2);
					} else if (token.equals("character-staff")) {
						p.zStaffLeft = Integer.parseInt(token2);
					} else if (token.equals("character-robe top")) {
						p.zTopLeft = Integer.parseInt(token2);
					} else if (token.equals("character-robe bottom")) {
						p.zBottomLeft = Integer.parseInt(token2);
					} else if (token.equals("character-leather body")) {
						p.mBodyLeft = Integer.parseInt(token2);
					} else if (token.equals("character-chaps")) {
						p.mChapsLeft = Integer.parseInt(token2);
					} else if (token.equals("magic-book")) {
						p.playerMagicBook = Integer.parseInt(token2);
					} else if (token.equals("xpLock")) {
						p.xpLock = Boolean.parseBoolean(token2);
					} else if (token.equals("Jailed")) {
						p.Jail = Boolean.parseBoolean(token2);
					} else if (token.equals("isMuted")) {
						p.isMuted = Boolean.parseBoolean(token2);
					} else if (token.equals("hasYell")) {
						p.hasYell = Boolean.parseBoolean(token2);
					} else if (token.equals("hasHousee")) {
						p.hasHousee = Boolean.parseBoolean(token2);
					} else if (token.equals("hasChoosenDung")) {
						p.hasChoosenDung = Boolean.parseBoolean(token2);
					} else if (token.equals("canTeleFromRandom")) {
						RandomEvents.canTeleFromRandom = Boolean
								.parseBoolean(token2);
					} else if (token.equals("Agrith")) {
						p.Agrith = Boolean.parseBoolean(token2);
					} else if (token.equals("degrade")) {
						p.degradeTime = Integer.parseInt(token2);
					} else if (token.equals("Flambeed")) {
						p.Flambeed = Boolean.parseBoolean(token2);
					} else if (token.equals("mageBankMinigameDone")) {
						p.mageBankMinigameDone = Boolean.parseBoolean(token2);
					} else if (token.equals("killstreak")) {
						p.killstreak = Integer.parseInt(token2);
					} else if (token.equals("pheasentId")) {
						RandomEvents.pheasentId = Integer.parseInt(token2);
					} else if (token.equals("lastX")) {
						RandomEvents.lastX = Integer.parseInt(token2);
					} else if (token.equals("lastX")) {
						RandomEvents.lastX = Integer.parseInt(token2);
					} else if (token.equals("lastY")) {
						RandomEvents.lastY = Integer.parseInt(token2);
					} else if (token.equals("lastH")) {
						RandomEvents.lastH = Integer.parseInt(token2);
					} else if (token.equals("totaldeath")) {
						p.totaldeath = Integer.parseInt(token2);
					} else if (token.equals("Karamel")) {
						p.Karamel = Boolean.parseBoolean(token2);
					} else if (token.equals("Dessourt")) {
						p.Dessourt = Boolean.parseBoolean(token2);
					} else if (token.equals("culin")) {
						p.Culin = Boolean.parseBoolean(token2);
					} else if (token.equals("Nomad")) {
						p.Nomad = Boolean.parseBoolean(token2);
					} else if (token.equals("SharpAuraActive")) {
						p.SharpAuraActive = Boolean.parseBoolean(token2);
					} else if (token.equals("AsuraAura")) {
						p.AsuraAura = Boolean.parseBoolean(token2);
					} else if (token.equals("Goblin")) {
						p.Goblin = Boolean.parseBoolean(token2);
					} else if (token.equals("hasGivenSlayReward")) {
						p.hasGivenSlayReward = Boolean.parseBoolean(token2);
					} else if (token.equals("vote")) {
						p.vote = Integer.parseInt(token2);
					} else if (token.equals("hasHeardEasterDialogue")) {
						p.hasHeardEasterDialogue = Integer.parseInt(token2);
					} else if (token.equals("brother-info")) {
						p.barrowsNpcs[Integer.parseInt(token3[0])][1] = Integer
								.parseInt(token3[1]);
					} else if (token.equals("special-amount")) {
						p.specAmount = Double.parseDouble(token2);
					} else if (token.equals("selected-coffin")) {
						p.randomCoffin = Integer.parseInt(token2);
					} else if (token.equals("barrows-killcount")) {
						p.pkPoints = Integer.parseInt(token2);
					} else if (token.equals("auraTimer")) {
						Client.auraTimer = Integer.parseInt(token2);
					} else if (token.equals("auraTimer2")) {
						Client.auraTimer2 = Integer.parseInt(token2);
					} else if (token.equals("teleblock-length")) {
						p.teleBlockDelay = System.currentTimeMillis();
						p.teleBlockLength = Integer.parseInt(token2);
					} else if (token.equals("acceptAid")) {
						p.invViewEnabled = Boolean.parseBoolean(token2);
					} else if (token.equals("pc-points")) {
						p.pcPoints = Integer.parseInt(token2);
					} else if (token.equals("LoyaltyPoints")) {
						p.LoyaltyPoints = Integer.parseInt(token2);
					} else if (token.equals("LoyaltyScore")) {
						p.LoyaltyScore = Integer.parseInt(token2);
					} else if (token.equals("gwdelay")) {
						p.gwdelay = Integer.parseInt(token2);
					} else if (token.equals("hasRecov")) {
						p.hasRecov = Integer.parseInt(token2);
						/*
						 * } else if (token.equals("summonSpec")) { p.summonSpec
						 * = Integer.parseInt(token2);
						 */
					} else if (token.equals("dungRest")) {
						p.dungRest = Integer.parseInt(token2);
					} else if (token.equals("Altar")) {
						p.altarPrayed = Integer.parseInt(token2);
					} else if (token.equals("Arma-KC")) {
						p.Arma = Integer.parseInt(token2);
					} else if (token.equals("Band-KC")) {
						p.Band = Integer.parseInt(token2);
					} else if (token.equals("Zammy-KC")) {
						p.Zammy = Integer.parseInt(token2);
					} else if (token.equals("Sara-KC")) {
						p.Sara = Integer.parseInt(token2);
					} else if (token.equals("bossPoints")) {
						p.bossPoints = Integer.parseInt(token2);
					} else if (token.equals("pk-points")) {
						p.pkPoints = Integer.parseInt(token2);
					} else if (token.equals("killed-players")) {
						List<String> players = new ArrayList<String>();
						for (String s : token3) {
							players.add(s);
						}
						p.getPkRewardSystem().setKilledPlayers(players);
					} else if (token.equals("isDonator")) {
						p.isDonator = Integer.parseInt(token2);
					} else if (token.equals("donatorChest")) {
						p.donatorChest = Integer.parseInt(token2);
					} else if (token.equals("slayerTask")) {
						p.slayerTask = Integer.parseInt(token2);
					} else if (token.equals("taskAmount")) {
						p.taskAmount = Integer.parseInt(token2);
					} else if (token.equals("magePoints")) {
						p.magePoints = Integer.parseInt(token2);
						/*
						 * } else if (line.startsWith("KC")) { p.KC =
						 * Integer.parseInt(token2); } else if
						 * (line.startsWith("DC")) { p.DC =
						 * Integer.parseInt(token2);
						 */
					} else if (line.startsWith("totalstored")) {
						p.totalstored = Integer.parseInt(token2);
					} else if (token.equals("autoRet")) {
						p.autoRet = Integer.parseInt(token2);
					} else if (token.equals("barbPoints")) {
						p.barbPoints = Integer.parseInt(token2);
					} else if (token.equals("DonorPoint")) {
						p.DonorPoint = Integer.parseInt(token2);
						/*
						 * } else if (token.equals("grimPrize")) { p.grimPrize =
						 * Integer.parseInt(token2);
						 */
					} else if (token.equals("trade11")) {
						p.trade11 = Integer.parseInt(token2);
					} else if (token.equals("SpeDelay")) {
						p.SpecialDelay = Integer.parseInt(token2);
					} else if (token.equals("barrowskillcount")) {
						p.barrowsKillCount = Integer.parseInt(token2);
						/*
						 * } else if (token.equals("flagged")) {
						 * p.accountFlagged = Boolean.parseBoolean(token2); }
						 * else if (token.equals("Rules")) { p.readRules =
						 * Boolean.parseBoolean(token2);
						 */
					} else if (token.equals("claimLater")) {
						p.claimLater = Boolean.parseBoolean(token2);
					} else if (token.equals("SPoints")) {
						p.SPoints = Integer.parseInt(token2);
					} else if (token.equals("foodEat")) {
						p.foodEat = Integer.parseInt(token2);
					} else if (token.equals("nomadSk")) {
						p.nomadSk = Integer.parseInt(token2);
					} else if (token.equals("GoblinS")) {
						p.GoblinS = Integer.parseInt(token2);
					} else if (token.equals("Jadskls")) {
						p.Jadskls = Integer.parseInt(token2);
					} else if (token.equals("newKil")) {
						p.newKil = Integer.parseInt(token2);
					} else if (token.equals("crabsK")) {
						p.crabsK = Integer.parseInt(token2);
					} else if (token.equals("beastK")) {
						p.beastK = Integer.parseInt(token2);
					} else if (token.equals("spinsLe")) {
						p.spinsLe = Integer.parseInt(token2);
					} else if (token.equals("WinsSFF")) {
						p.WinsSFF = Integer.parseInt(token2);
					} else if (token.equals("WinsPCC")) {
						p.WinsPCC = Integer.parseInt(token2);
					} else if (token.equals("wave")) {
						p.waveId = Integer.parseInt(token2);
					} else if (token.equals("dfs-charges")) {
						p.dfsCount = Integer.parseInt(token2);
					} else if (token.equals("hasFollower")) {
						p.hasFollower = Integer.parseInt(token2);

					} else if (token.equals("summoningnpcid")) {
						p.summoningnpcid = Integer.parseInt(token2);

					} else if (token.equals("void")) {
						for (int j = 0; j < token3.length; j++) {
							p.voidStatus[j] = Integer.parseInt(token3[j]);
						}
					} else if (token.equals("fightMode")) {
						p.fightMode = Integer.parseInt(token2);
					} else if (token.equals("COaltar")) {
						p.COaltar = Integer.parseInt(token2);
					} else if (token.equals("amount2")) {
						Client.amount2 = Integer.parseInt(token2);
					} else if (token.equals("item2")) {
						Client.item2 = Integer.parseInt(token2);
					} else if (token.equals("COChair")) {
						p.COChair = Integer.parseInt(token2);
					} else if (token.equals("DUfood1")) {
						p.DUfood1 = Integer.parseInt(token2);
					} else if (token.equals("DUfood2")) {
						p.DUfood2 = Integer.parseInt(token2);
					} else if (token.equals("DUfood3")) {
						p.DUfood3 = Integer.parseInt(token2);
					} else if (token.equals("DUfood4")) {
						p.DUfood4 = Integer.parseInt(token2);
					} else if (token.equals("OWdungn")) {
						p.dungn = Integer.parseInt(token2);
					} else if (token.equals("COchest")) {
						p.COchest = Integer.parseInt(token2);
					} else if (token.equals("CObeddy")) {
						p.CObeddy = Integer.parseInt(token2);
					} else if (token.equals("COtreee")) {
						p.COtreee = Integer.parseInt(token2);
					} else if (token.equals("COLectt")) {
						p.COLectt = Integer.parseInt(token2);
					} else if (token.equals("COtelee")) {
						p.COtelee = Integer.parseInt(token2);
					} else if (token.equals("COcryst")) {
						p.COcryst = Integer.parseInt(token2);
						/*
						 * } else if (token.equals("Black-marks")) {
						 * p.BlackMarks = Integer.parseInt(token2);
						 */
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						p.playerEquipment[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.playerEquipmentN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						p.playerAppearance[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						p.playerLevel[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.playerXP[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						p.playerItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.playerItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						p.bankItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
						p.bankingItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankingItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					} else if (token.equals("character-bank1")) {
						p.bankItems1[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankItems1N[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					} else if (token.equals("character-bank2")) {
						p.bankItems2[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankItems2N[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					} else if (token.equals("character-bank3")) {
						p.bankItems3[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankItems3N[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					} else if (token.equals("character-bank4")) {
						p.bankItems4[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankItems4N[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					} else if (token.equals("character-bank5")) {
						p.bankItems5[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankItems5N[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					} else if (token.equals("character-bank6")) {
						p.bankItems6[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankItems6N[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					} else if (token.equals("character-bank7")) {
						p.bankItems7[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankItems7N[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					} else if (token.equals("character-bank8")) {
						p.bankItems8[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						p.bankItems8N[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						p.friends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				case 9:
					/*
					 * if (token.equals("character-ignore")) {
					 * ignores[Integer.parseInt(token3[0])] =
					 * Long.parseLong(token3[1]); }
					 */
					break;

				case 20:
					if (token.equals("stored")) {
						p.storeditems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				case 21:
					if (token.equals("occupy")) {
						p.occupied[Integer.parseInt(token3[0])] = Boolean
								.parseBoolean(token3[1]);
					}
					break;
				case 22:
					if (token.equals("task")) {
						p.task1[Integer.parseInt(token3[0])] = Boolean
								.parseBoolean(token3[1]);
					}
					break;
				case 23:
					if (token.equals("task2")) {
						p.task2[Integer.parseInt(token3[0])] = Boolean
								.parseBoolean(token3[1]);
					}
					break;
				case 24:
					if (token.equals("task3")) {
						p.task3[Integer.parseInt(token3[0])] = Boolean
								.parseBoolean(token3[1]);
					}
					break;
				case 25:
					if (token.equals("Objects")) {
						p.constructionObjects[Integer.parseInt(token3[0])] = Boolean
								.parseBoolean(token3[1]);
					}
					break;
				case 26:
					if (token.equals("Upgrades")) {
						p.constructionUpgrades[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				case 27:
					if (token.equals("Charges")) {
						p.chaoticCharges[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {
					ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {
					ReadMode = 2;
				} else if (line.equals("[AGILITY]")) {
					ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {
					ReadMode = 3;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 4;

				} else if (line.equals("[SKILLS]")) {
					ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {
					ReadMode = 6;
				} else if (line.equals("[BANK]")) {
					ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {
					ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 9;
				} else if (line.equals("[STORED]")) {
					ReadMode = 20;
				} else if (line.equals("[OCCUPY]")) {
					ReadMode = 21;
				} else if (line.equals("[ACHIEVEMENTS - EASY TASKS]")) {
					ReadMode = 22;
				} else if (line.equals("[ACHIEVEMENTS - HARD TASKS]")) {
					ReadMode = 23;
				} else if (line.equals("[ACHIEVEMENTS - ELITE TASKS]")) {
					ReadMode = 24;
				} else if (line.equals("[Construction]")) {
					ReadMode = 25;
				} else if (line.equals("[UPGRADES]")) {
					ReadMode = 26;
				} else if (line.equals("[CHARGES]")) {
					ReadMode = 27;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return 13;
	}

	public static boolean playerExists(String name) {
		File file = new File(Config.PLAYERDATA_PATH + "/Characters/" + name
				+ ".txt");
		return file.exists();
	}

	/**
	 * Saving
	 **/
	public static boolean saveGame(Client p) {
		if (!p.saveFile || p.newPlayer || !p.saveCharacter) {
			// System.out.println("first");
			return false;
		}
		if (p.playerName == null || PlayerHandler.players[p.playerId] == null) {
			// System.out.println("second");
			return false;
		}
		p.playerName = p.playerName2;
		int tbTime = (int) (p.teleBlockDelay - System.currentTimeMillis() + p.teleBlockLength);
		if (tbTime > 300000 || tbTime < 0) {
			tbTime = 0;
		}

		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter(
					Config.PLAYERDATA_PATH + "/Characters/" + p.playerName
							+ ".txt"));

			/* ACCOUNT */
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(p.playerName, 0, p.playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			String passToWrite = Misc.md5Hash(p.playerPass);
			characterfile.write(passToWrite, 0, passToWrite.length());
			characterfile.newLine();
			characterfile.newLine();

			/* CHARACTER */
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(p.heightLevel), 0, Integer
					.toString(p.heightLevel).length());
			characterfile.newLine();

			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(p.absX), 0,
					Integer.toString(p.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(p.absY), 0,
					Integer.toString(p.absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(p.playerRights), 0, Integer
					.toString(p.playerRights).length());
			characterfile.newLine();
			characterfile.write("summonTime = ", 0, 13);
			characterfile.write(Integer.toString(p.summonTime), 0, Integer
					.toString(p.summonTime).length());
			characterfile.newLine();
			characterfile.write("Commendations = ", 0, 16);
			characterfile.write(Integer.toString(p.Commendations), 0, Integer
					.toString(p.Commendations).length());
			characterfile.newLine();
			characterfile.write("EP = ", 0, 5);
			characterfile.write(Integer.toString(p.EP), 0,
					Integer.toString(p.EP).length());
			characterfile.newLine();
			characterfile.write("bountyIcon = ", 0, 13);
			characterfile.write(Integer.toString(p.bountyIcon), 0, Integer
					.toString(p.bountyIcon).length());
			characterfile.newLine();
			characterfile.write("EPMINUTES = ", 0, 12);
			characterfile.write(Integer.toString(p.EP_MINUTES), 0, Integer
					.toString(p.EP_MINUTES).length());
			characterfile.newLine();
			characterfile.write("safeTimer = ", 0, 12);
			characterfile.write(Integer.toString(p.safeTimer), 0, Integer
					.toString(p.safeTimer).length());
			characterfile.newLine();
			characterfile.write("TargetPercentage = ", 0, 19);
			characterfile.write(Integer.toString(p.targetPercentage), 0,
					Integer.toString(p.targetPercentage).length());
			characterfile.newLine();
			characterfile.write("targetIndex = ", 0, 14);
			characterfile.write(Integer.toString(p.targetIndex), 0, Integer
					.toString(p.targetIndex).length());
			characterfile.newLine();
			if (p.targetName != null) {
				characterfile.write("targetName = ", 0, 13);
				characterfile.write(p.targetName, 0, p.targetName.length());
				characterfile.newLine();
			} else {
				p.targetName = "None";
				characterfile.write("targetName = ", 0, 13);
				characterfile.write(p.targetName, 0, p.targetName.length());
				characterfile.newLine();

			}
			characterfile.write("payButlerReq = ", 0, 14);
			characterfile.write(Integer.toString(p.payButlerReq), 0, Integer
					.toString(p.payButlerReq).length());
			characterfile.newLine();
			characterfile.write("payment = ", 0, 10);
			characterfile.write(Integer.toString(p.payment), 0, Integer
					.toString(p.payment).length());
			characterfile.newLine();
			characterfile.write("hasHousee = ", 0, 12);
			characterfile.write(Boolean.toString(p.hasHousee), 0, Boolean
					.toString(p.hasHousee).length());
			characterfile.newLine();
			/*
			 * characterfile.write("bhenter = ", 0, 10);
			 * characterfile.write(Integer.toString(p.enterBHTime), 0,
			 * Integer.toString(p.enterBHTime).length());
			 * characterfile.newLine(); characterfile.write("bhleave = ", 0,
			 * 10); characterfile.write(Integer.toString(p.leavePenalty), 0,
			 * Integer.toString(p.leavePenalty).length());
			 * characterfile.newLine(); characterfile.write("bhpickup = ", 0,
			 * 11); characterfile.write(Integer.toString(p.pickupPenalty), 0,
			 * Integer.toString(p.pickupPenalty).length());
			 * characterfile.newLine(); characterfile.write("bhcash = ", 0, 9);
			 * characterfile.write(Integer.toString(p.bountyCash), 0,
			 * Integer.toString(p.bountyCash).length());
			 * characterfile.newLine(); characterfile.write("bhcrater = ", 0,
			 * 11); characterfile.write(Integer.toString(p.publicCrater), 0,
			 * Integer.toString(p.publicCrater).length());
			 * characterfile.newLine(); characterfile.write("bhrogue = ", 0,
			 * 10); characterfile.write(Integer.toString(p.bountyCash), 0,
			 * Integer.toString(p.bountyCash).length());
			 * characterfile.newLine(); characterfile.write("bhtarget = ", 0,
			 * 11); characterfile.write(Integer.toString(p.publicCrater), 0,
			 * Integer.toString(p.publicCrater).length());
			 * characterfile.newLine();
			 */
			characterfile.write("character-title = ", 0, 18);
			characterfile.write(Integer.toString(p.playerTitle), 0, Integer
					.toString(p.playerTitle).length());
			characterfile.newLine();
			characterfile.write("timeOnline = ", 0, 13);
			characterfile.write(Integer.toString(p.timeOnline), 0, Integer
					.toString(p.timeOnline).length());
			characterfile.newLine();
			characterfile.write("shopcollect = ", 0, 14);
			characterfile.write(Integer.toString(p.playerCollect), 0, Integer
					.toString(p.playerCollect).length());
			characterfile.newLine();
			characterfile.write("yellTimer = ", 0, 12);
			characterfile.write(Integer.toString(p.yellTimer), 0, Integer
					.toString(p.yellTimer).length());
			characterfile.newLine();
			characterfile.write("santaPrize = ", 0, 12);
			characterfile.write(Integer.toString(p.santaPrize), 0, Integer
					.toString(p.santaPrize).length());
			characterfile.newLine();
			characterfile.write("dungPoints = ", 0, 12);
			characterfile.write(Integer.toString(p.dungPoints), 0, Integer
					.toString(p.dungPoints).length());
			characterfile.newLine();
			characterfile.write("slayerPoints = ", 0, 15);
			characterfile.write(Integer.toString(p.slayerPoints), 0, Integer
					.toString(p.slayerPoints).length());
			characterfile.newLine();
			characterfile.write("slayerTaskStreak = ", 0, 19);
			characterfile.write(Integer.toString(p.slayerTaskStreak), 0,
					Integer.toString(p.slayerTaskStreak).length());
			characterfile.newLine();
			characterfile.write("crystal-bow-shots = ", 0, 20);
			characterfile.write(Integer.toString(p.crystalBowArrowCount), 0,
					Integer.toString(p.crystalBowArrowCount).length());
			characterfile.newLine();
			characterfile.write("VLS-hits = ", 0, 11);
			characterfile.write(Integer.toString(p.degradeTime), 0, Integer
					.toString(p.degradeTime).length());
			/*
			 * characterfile.newLine(); characterfile.write("dungPoints = ", 0,
			 * 20); characterfile.write(Integer.toString(p.dungPoints), 0,
			 * Integer.toString(p.dungPoints).length());
			 */
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("MoneyOrb = ", 0, 11);
			characterfile.write(Integer.toString(p.MoneyCash), 0, Integer
					.toString(p.MoneyCash).length());
			characterfile.newLine();
			characterfile.write("email = ");
			characterfile.write(p.email);
			characterfile.newLine();
			characterfile.write("lastFighter = ");
			characterfile.write(p.lastFighter);
			characterfile.newLine();
			characterfile.write("connectedFrom = ");
			characterfile.write(p.connectedFrom);
			characterfile.newLine();
			characterfile.write("skull-timer = ", 0, 14);
			characterfile.write(Integer.toString(p.skullTimer), 0, Integer
					.toString(p.skullTimer).length());
			characterfile.newLine();
			characterfile.write("EP = ", 0, 5);
			characterfile.write(Integer.toString(p.earningPotential), 0,
					Integer.toString(p.earningPotential).length());
			characterfile.newLine();
			characterfile.write("epDelay = ", 0, 10);
			characterfile.write(Integer.toString(p.epDelay), 0, Integer
					.toString(p.epDelay).length());
			characterfile.newLine();

			characterfile.write("chatId = ", 0, 9);
			characterfile.write(Integer.toString(p.chatId), 0, Integer
					.toString(p.chatId).length());
			characterfile.newLine();

			characterfile.write("addChaotics = ", 0, 14);
			characterfile.write(Boolean.toString(p.addChaotics), 0, Boolean
					.toString(p.addChaotics).length());
			characterfile.newLine();

			characterfile.write("getIt = ", 0, 8);
			characterfile.write(Boolean.toString(p.getIt), 0,
					Boolean.toString(p.getIt).length());
			characterfile.newLine();

			characterfile.write("taskType = ", 0, 11);
			characterfile.write(Integer.toString(p.taskType), 0, Integer
					.toString(p.taskType).length());
			characterfile.newLine();
			characterfile.write("VL = ", 0, 5);
			characterfile.write(Integer.toString(p.vlsLeft2), 0, Integer
					.toString(p.vlsLeft2).length());
			characterfile.newLine();
			characterfile.write("character-longsword = ", 0, 22);
			characterfile.write(Integer.toString(p.vlsLeft), 0, Integer
					.toString(p.vlsLeft).length());
			characterfile.newLine();
			characterfile.write("character-warhammer = ", 0, 22);
			characterfile.write(Integer.toString(p.statLeft), 0, Integer
					.toString(p.statLeft).length());
			characterfile.newLine();
			characterfile.write("character-spear = ", 0, 18);
			characterfile.write(Integer.toString(p.vSpearLeft), 0, Integer
					.toString(p.vSpearLeft).length());
			characterfile.newLine();
			characterfile.write("character-chainbody = ", 0, 22);
			characterfile.write(Integer.toString(p.vTopLeft), 0, Integer
					.toString(p.vTopLeft).length());
			characterfile.newLine();
			characterfile.write("character-chainskirt = ", 0, 23);
			characterfile.write(Integer.toString(p.vLegsLeft), 0, Integer
					.toString(p.vLegsLeft).length());
			characterfile.newLine();
			characterfile.write("character-full helm = ", 0, 22);
			characterfile.write(Integer.toString(p.sHelmLeft), 0, Integer
					.toString(p.sHelmLeft).length());
			characterfile.newLine();
			characterfile.write("character-platebody = ", 0, 22);
			characterfile.write(Integer.toString(p.sTopLeft), 0, Integer
					.toString(p.sTopLeft).length());
			characterfile.newLine();
			characterfile.write("character-platelegs = ", 0, 22);
			characterfile.write(Integer.toString(p.sLegsLeft), 0, Integer
					.toString(p.sLegsLeft).length());
			characterfile.newLine();
			characterfile.write("character-hood = ", 0, 17);
			characterfile.write(Integer.toString(p.zHoodLeft), 0, Integer
					.toString(p.zHoodLeft).length());
			characterfile.newLine();
			characterfile.write("character-staff = ", 0, 18);
			characterfile.write(Integer.toString(p.zStaffLeft), 0, Integer
					.toString(p.zStaffLeft).length());
			characterfile.newLine();
			characterfile.write("character-robe top = ", 0, 21);
			characterfile.write(Integer.toString(p.zTopLeft), 0, Integer
					.toString(p.zTopLeft).length());
			characterfile.newLine();
			characterfile.write("character-robe bottom = ", 0, 24);
			characterfile.write(Integer.toString(p.zBottomLeft), 0, Integer
					.toString(p.zBottomLeft).length());
			characterfile.newLine();
			characterfile.write("character-leather body = ", 0, 25);
			characterfile.write(Integer.toString(p.mBodyLeft), 0, Integer
					.toString(p.mBodyLeft).length());
			characterfile.newLine();
			characterfile.write("character-chaps = ", 0, 18);
			characterfile.write(Integer.toString(p.mChapsLeft), 0, Integer
					.toString(p.mChapsLeft).length());
			characterfile.newLine();
			characterfile.write("magic-book = ", 0, 13);
			characterfile.write(Integer.toString(p.playerMagicBook), 0, Integer
					.toString(p.playerMagicBook).length());
			characterfile.newLine();
			for (int b = 0; b < p.barrowsNpcs.length; b++) {
				characterfile.write("brother-info = ", 0, 15);
				characterfile.write(Integer.toString(b), 0, Integer.toString(b)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(
						p.barrowsNpcs[b][1] <= 1 ? Integer.toString(0)
								: Integer.toString(p.barrowsNpcs[b][1]), 0,
						Integer.toString(p.barrowsNpcs[b][1]).length());
				characterfile.newLine();
			}
			characterfile.write("special-amount = ", 0, 17);
			characterfile.write(Double.toString(p.specAmount), 0, Double
					.toString(p.specAmount).length());
			characterfile.newLine();
			characterfile.write("selected-coffin = ", 0, 18);
			characterfile.write(Integer.toString(p.randomCoffin), 0, Integer
					.toString(p.randomCoffin).length());
			characterfile.newLine();
			characterfile.write("barrows-killcount = ", 0, 20);
			characterfile.write(Integer.toString(p.barrowsKillCount), 0,
					Integer.toString(p.barrowsKillCount).length());
			characterfile.newLine();
			characterfile.write("auraTimer = ", 0, 12);
			characterfile.write(Integer.toString(Client.auraTimer), 0, Integer
					.toString(Client.auraTimer).length());
			characterfile.newLine();
			characterfile.write("CoolDownTimer = ", 0, 16);
			characterfile.write(Integer.toString(Client.CoolDownTimer), 0,
					Integer.toString(Client.CoolDownTimer).length());
			characterfile.newLine();
			characterfile.write("auraTimer2 = ", 0, 13);
			characterfile.write(Integer.toString(Client.auraTimer2), 0, Integer
					.toString(Client.auraTimer2).length());
			characterfile.newLine();
			characterfile.write("CoolDownTimer2 = ", 0, 17);
			characterfile.write(Integer.toString(Client.CoolDownTimer2), 0,
					Integer.toString(Client.CoolDownTimer2).length());
			characterfile.newLine();
			characterfile.write("teleblock-length = ", 0, 19);
			characterfile.write(Integer.toString(tbTime), 0,
					Integer.toString(tbTime).length());
			characterfile.newLine();
			characterfile.write("pc-points = ", 0, 12);
			characterfile.write(Integer.toString(p.pcPoints), 0, Integer
					.toString(p.pcPoints).length());
			characterfile.newLine();
			characterfile.write("LoyaltyScore = ", 0, 15);
			characterfile.write(Integer.toString(p.LoyaltyScore), 0, Integer
					.toString(p.LoyaltyScore).length());
			characterfile.newLine();
			characterfile.write("LoyaltyPoints = ", 0, 16);
			characterfile.write(Integer.toString(p.LoyaltyPoints), 0, Integer
					.toString(p.LoyaltyPoints).length());
			characterfile.newLine();
			characterfile.write("barbPoints = ", 0, 12);
			characterfile.write(Integer.toString(p.barbPoints), 0, Integer
					.toString(p.barbPoints).length());
			characterfile.newLine();
			characterfile.write("DonorPoint = ", 0, 13);
			characterfile.write(Integer.toString(p.DonorPoint), 0, Integer
					.toString(p.DonorPoint).length());
			characterfile.newLine();
			/*
			 * characterfile.write("grimPrize = ", 0, 12);
			 * characterfile.write(Integer.toString(p.grimPrize), 0,
			 * Integer.toString(p.grimPrize).length()); characterfile.newLine();
			 */
			characterfile.write("gwdelay = ", 0, 10);
			characterfile.write(Integer.toString(p.gwdelay), 0, Integer
					.toString(p.gwdelay).length());
			characterfile.newLine();
			characterfile.write("hasRecov = ", 0, 11);
			characterfile.write(Integer.toString(p.hasRecov), 0, Integer
					.toString(p.hasRecov).length());
			characterfile.newLine();
			/*
			 * characterfile.write("summonSpec = ", 0, 10);
			 * characterfile.write(Integer.toString(p.summonSpec), 0,
			 * Integer.toString(p.summonSpec).length());
			 * characterfile.newLine();
			 */
			characterfile.write("dungRest = ", 0, 10);
			characterfile.write(Integer.toString(p.dungRest), 0, Integer
					.toString(p.dungRest).length());
			characterfile.newLine();
			characterfile.write("Altar = ", 0, 8);
			characterfile.write(Integer.toString(p.altarPrayed), 0, Integer
					.toString(p.altarPrayed).length());
			characterfile.newLine();
			characterfile.write("Arma-KC = ", 0, 10);
			characterfile.write(Integer.toString(p.Arma), 0,
					Integer.toString(p.Arma).length());
			characterfile.newLine();
			characterfile.write("Band-KC = ", 0, 10);
			characterfile.write(Integer.toString(p.Band), 0,
					Integer.toString(p.Band).length());
			characterfile.newLine();
			characterfile.write("Zammy-KC = ", 0, 11);
			characterfile.write(Integer.toString(p.Zammy), 0,
					Integer.toString(p.Zammy).length());
			characterfile.newLine();
			characterfile.write("Sara-KC = ", 0, 10);
			characterfile.write(Integer.toString(p.Sara), 0,
					Integer.toString(p.Sara).length());
			characterfile.newLine();
			characterfile.write("bossPoints = ", 0, 12);
			characterfile.write(Integer.toString(p.bossPoints), 0, Integer
					.toString(p.bossPoints).length());
			// characterfile.newLine();
			characterfile.newLine();
			characterfile.write("pk-points = ", 0, 12);
			characterfile.write(Integer.toString(p.pkPoints), 0, Integer
					.toString(p.pkPoints).length());
			characterfile.newLine();
			characterfile.write("killed-players = ", 0, 17);
			if (p.getPkRewardSystem().getKilledPlayers().size() > 0) {
				for (String s : p.getPkRewardSystem().getKilledPlayers()) {
					characterfile.write(s + "\t");
				}
			}
			characterfile.newLine();
			characterfile.write("isDonator = ", 0, 12);
			characterfile.write(Integer.toString(p.isDonator), 0, Integer
					.toString(p.isDonator).length());
			characterfile.newLine();
			characterfile.write("donatorChest = ", 0, 15);
			characterfile.write(Integer.toString(p.donatorChest), 0, Integer
					.toString(p.donatorChest).length());
			characterfile.newLine();
			characterfile.write("slayerTask = ", 0, 13);
			characterfile.write(Integer.toString(p.slayerTask), 0, Integer
					.toString(p.slayerTask).length());
			characterfile.newLine();
			characterfile.write("xpLock = ", 0, 9);
			characterfile.write(Boolean.toString(p.xpLock), 0, Boolean
					.toString(p.xpLock).length());
			characterfile.newLine();
			characterfile.write("Agrith = ", 0, 9);
			characterfile.write(Boolean.toString(p.Agrith), 0, Boolean
					.toString(p.Agrith).length());
			characterfile.newLine();
			characterfile.write("Flambeed = ", 0, 11);
			characterfile.write(Boolean.toString(p.Flambeed), 0, Boolean
					.toString(p.Flambeed).length());
			characterfile.newLine();
			characterfile.write("mageBankMinigameDone = ", 0, 23);
			characterfile.write(Boolean.toString(p.mageBankMinigameDone), 0,
					Boolean.toString(p.mageBankMinigameDone).length());
			characterfile.newLine();
			characterfile.write("killstreak = ", 0, 13);
			characterfile.write(Integer.toString(p.killstreak), 0, Integer
					.toString(p.killstreak).length());
			characterfile.newLine();
			characterfile.write("totaldeath = ", 0, 13);
			characterfile.write(Integer.toString(p.totaldeath), 0, Integer
					.toString(p.totaldeath).length());
			characterfile.newLine();
			characterfile.write("totalkills = ", 0, 13);
			characterfile.write(Integer.toString(p.totalkills), 0, Integer
					.toString(p.totalkills).length());
			characterfile.newLine();
			characterfile.write("canTeleFromRandom = ", 0, 20);
			characterfile.write(
					Boolean.toString(RandomEvents.canTeleFromRandom), 0,
					Boolean.toString(RandomEvents.canTeleFromRandom).length());
			characterfile.newLine();
			characterfile.write("pheasentId = ", 0, 13);
			characterfile.write(Integer.toString(RandomEvents.pheasentId), 0,
					Integer.toString(RandomEvents.pheasentId).length());
			characterfile.newLine();
			characterfile.write("lastX = ", 0, 8);
			characterfile.write(Integer.toString(RandomEvents.lastX), 0,
					Integer.toString(RandomEvents.lastX).length());
			characterfile.newLine();
			characterfile.write("lastY = ", 0, 8);
			characterfile.write(Integer.toString(RandomEvents.lastY), 0,
					Integer.toString(RandomEvents.lastY).length());
			characterfile.newLine();
			characterfile.write("lastH = ", 0, 8);
			characterfile.write(Integer.toString(RandomEvents.lastH), 0,
					Integer.toString(RandomEvents.lastH).length());
			characterfile.newLine();
			characterfile.write("effigy = ", 0, 9);
			characterfile.write(Integer.toString(p.effigy), 0, Integer
					.toString(p.effigy).length());
			characterfile.newLine();
			characterfile.write("votePoints = ", 0, 13);
			characterfile.write(Integer.toString(p.votePoints), 0, Integer
					.toString(p.votePoints).length());
			characterfile.newLine();
			// Dung
			characterfile.write("banned = ", 0, 9);
			characterfile.write(Integer.toString(p.banned), 0, Integer
					.toString(p.banned).length());
			characterfile.newLine();
			characterfile.write("dungeonId = ", 0, 12);
			characterfile.write(Integer.toString(p.dungeonId), 0, Integer
					.toString(p.dungeonId).length());
			characterfile.newLine();
			characterfile.write("TPoints = ", 0, 10);
			characterfile.write(Integer.toString(p.TPoints), 0, Integer
					.toString(p.TPoints).length());
			characterfile.newLine();
			characterfile.write("firesL = ", 0, 9);
			characterfile.write(Integer.toString(p.firesL), 0, Integer
					.toString(p.firesL).length());
			characterfile.newLine();
			characterfile.write("rocksM = ", 0, 9);
			characterfile.write(Integer.toString(p.rocksM), 0, Integer
					.toString(p.rocksM).length());
			characterfile.newLine();
			characterfile.write("smithM = ", 0, 9);
			characterfile.write(Integer.toString(p.smithM), 0, Integer
					.toString(p.smithM).length());
			characterfile.newLine();
			characterfile.write("spinFL = ", 0, 9);
			characterfile.write(Integer.toString(p.spinFL), 0, Integer
					.toString(p.spinFL).length());
			characterfile.newLine();
			characterfile.write("pcGame = ", 0, 9);
			characterfile.write(Integer.toString(p.pcGame), 0, Integer
					.toString(p.pcGame).length());
			characterfile.newLine();
			characterfile.write("sumfam = ", 0, 9);
			characterfile.write(Integer.toString(p.sumfam), 0, Integer
					.toString(p.sumfam).length());
			characterfile.newLine();
			characterfile.write("bonesB = ", 0, 9);
			characterfile.write(Integer.toString(p.bonesB), 0, Integer
					.toString(p.bonesB).length());
			characterfile.newLine();
			characterfile.write("redTop = ", 0, 9);
			characterfile.write(Integer.toString(p.redTop), 0, Integer
					.toString(p.redTop).length());
			characterfile.newLine();
			characterfile.write("impCat = ", 0, 9);
			characterfile.write(Integer.toString(p.impCat), 0, Integer
					.toString(p.impCat).length());
			characterfile.newLine();
			characterfile.write("slayTa = ", 0, 9);
			characterfile.write(Integer.toString(p.slayTa), 0, Integer
					.toString(p.slayTa).length());
			characterfile.newLine();
			characterfile.write("bind1 = ", 0, 8);
			characterfile.write(Integer.toString(p.bind1), 0,
					Integer.toString(p.bind1).length());
			characterfile.newLine();
			characterfile.write("bind2 = ", 0, 8);
			characterfile.write(Integer.toString(p.bind2), 0,
					Integer.toString(p.bind2).length());
			characterfile.newLine();
			characterfile.write("bind3 = ", 0, 8);
			characterfile.write(Integer.toString(p.bind3), 0,
					Integer.toString(p.bind3).length());
			characterfile.newLine();
			characterfile.write("bind4 = ", 0, 8);
			characterfile.write(Integer.toString(p.bind4), 0,
					Integer.toString(p.bind4).length());
			characterfile.newLine();
			characterfile.write("npcsKilled = ", 0, 13);
			characterfile.write(Integer.toString(p.npcsKilled), 0, Integer
					.toString(p.npcsKilled).length());
			characterfile.newLine();
			characterfile.write("door1 = ", 0, 8);
			characterfile.write(Boolean.toString(p.door1), 0,
					Boolean.toString(p.door1).length());
			characterfile.newLine();
			characterfile.write("door2 = ", 0, 8);
			characterfile.write(Boolean.toString(p.door2), 0,
					Boolean.toString(p.door2).length());
			characterfile.newLine();
			characterfile.write("door3 = ", 0, 8);
			characterfile.write(Boolean.toString(p.door3), 0,
					Boolean.toString(p.door3).length());
			characterfile.newLine();
			characterfile.write("door4 = ", 0, 8);
			characterfile.write(Boolean.toString(p.door4), 0,
					Boolean.toString(p.door4).length());
			characterfile.newLine();
			characterfile.write("receivedItems = ", 0, 16);
			characterfile.write(Boolean.toString(p.receivedItems), 0, Boolean
					.toString(p.receivedItems).length());
			characterfile.newLine();
			characterfile.write("inDung = ", 0, 9);
			characterfile.write(Boolean.toString(p.inDung), 0, Boolean
					.toString(p.inDung).length());
			characterfile.newLine();
			characterfile.write("completed = ", 0, 12);
			characterfile.write(Boolean.toString(p.completed), 0, Boolean
					.toString(p.completed).length());
			characterfile.newLine();
			// dung end
			characterfile.write("character-yellTag = ", 0, 20);
			characterfile.write(p.customYellTag, 0, p.customYellTag.length());
			characterfile.newLine();
			characterfile.write("player-god = " + p.getPlayerGod());
			characterfile.newLine();
			characterfile.write("mustPay = ", 0, 10);
			characterfile.write(Boolean.toString(p.mustPay), 0, Boolean
					.toString(p.mustPay).length());
			characterfile.newLine();
			characterfile.write("Karamel = ", 0, 10);
			characterfile.write(Boolean.toString(p.Karamel), 0, Boolean
					.toString(p.Karamel).length());
			characterfile.newLine();
			characterfile.write("Dessourt = ", 0, 11);
			characterfile.write(Boolean.toString(p.Dessourt), 0, Boolean
					.toString(p.Dessourt).length());
			characterfile.newLine();
			characterfile.write("culin = ", 0, 8);
			characterfile.write(Boolean.toString(p.Culin), 0,
					Boolean.toString(p.Culin).length());
			characterfile.newLine();
			characterfile.write("Nomad = ", 0, 8);
			characterfile.write(Boolean.toString(p.Nomad), 0,
					Boolean.toString(p.Nomad).length());
			characterfile.newLine();
			characterfile.write("SharpAuraActive = ", 0, 18);
			characterfile.write(Boolean.toString(p.SharpAuraActive), 0, Boolean
					.toString(p.SharpAuraActive).length());
			characterfile.newLine();
			characterfile.write("AsuraAura = ", 0, 12);
			characterfile.write(Boolean.toString(p.AsuraAura), 0, Boolean
					.toString(p.AsuraAura).length());
			characterfile.newLine();
			characterfile.write("Goblin = ", 0, 9);
			characterfile.write(Boolean.toString(p.Goblin), 0, Boolean
					.toString(p.Goblin).length());
			characterfile.newLine();
			characterfile.write("vote = ", 0, 7);
			characterfile.write(Integer.toString(p.vote), 0,
					Integer.toString(p.vote).length());
			characterfile.newLine();
			characterfile.write("randomEventTimer = ", 0, 19);
			characterfile.write(Integer.toString(p.randomEventTimer), 0,
					Integer.toString(p.randomEventTimer).length());
			characterfile.newLine();
			characterfile.write("hasHeardEasterDialogue = ", 0, 25);
			characterfile.write(Integer.toString(p.hasHeardEasterDialogue), 0,
					Integer.toString(p.hasHeardEasterDialogue).length());
			characterfile.newLine();
			characterfile.write("character-lastkilled = ", 0, 23);
			characterfile.write(p.lastKilled, 0, p.lastKilled.length());
			characterfile.newLine();
			characterfile.write("hasBankPin = ", 0, 13);
			characterfile.write(Boolean.toString(p.hasBankPin), 0, Boolean
					.toString(p.hasBankPin).length());
			characterfile.newLine();
			characterfile.write("clan-name = " + p.clanName);
			characterfile.newLine();
			characterfile.write("clan-pass = " + p.clanPass);
			characterfile.newLine();
			characterfile.write("clan-owner = " + p.hasClan);
			characterfile.newLine();
			characterfile.write("bankPin1 = ", 0, 11);
			characterfile.write(Integer.toString(p.bankPin1), 0, Integer
					.toString(p.bankPin1).length());
			characterfile.newLine();
			characterfile.write("bankPin2 = ", 0, 11);
			characterfile.write(Integer.toString(p.bankPin2), 0, Integer
					.toString(p.bankPin2).length());
			characterfile.newLine();
			characterfile.write("bankPin3 = ", 0, 11);
			characterfile.write(Integer.toString(p.bankPin3), 0, Integer
					.toString(p.bankPin3).length());
			characterfile.newLine();
			characterfile.write("bankPin4 = ", 0, 11);
			characterfile.write(Integer.toString(p.bankPin4), 0, Integer
					.toString(p.bankPin4).length());
			characterfile.newLine();
			characterfile.write("blackMark = ", 0, 12);
			characterfile.write(Integer.toString(p.blackMark), 0, Integer
					.toString(p.blackMark).length());
			characterfile.newLine();
			characterfile.write("cw-games = ", 0, 11);
			characterfile.write(Integer.toString(p.cwGames), 0, Integer
					.toString(p.cwGames).length());
			characterfile.newLine();
			characterfile.write("taskAmount = ", 0, 13);
			characterfile.write(Integer.toString(p.taskAmount), 0, Integer
					.toString(p.taskAmount).length());
			characterfile.newLine();
			characterfile.write("magePoints = ", 0, 13);
			characterfile.write(Integer.toString(p.magePoints), 0, Integer
					.toString(p.magePoints).length());
			characterfile.newLine();
			characterfile.write("acceptAid = ", 0, 12);
			characterfile.write(Boolean.toString(p.invViewEnabled), 0, Boolean
					.toString(p.invViewEnabled).length());
			characterfile.newLine();
			/*
			 * characterfile.write("KC = ", 0, 4);
			 * characterfile.write(Integer.toString(p.KC), 0,
			 * Integer.toString(p.KC).length()); characterfile.newLine();
			 * characterfile.write("DC = ", 0, 4);
			 * characterfile.write(Integer.toString(p.DC), 0,
			 * Integer.toString(p.DC).length()); characterfile.newLine();
			 */
			characterfile.write("kingQuest = ", 0, 12);
			characterfile.write(Integer.toString(p.kingQuest), 0, Integer
					.toString(p.kingQuest).length());
			characterfile.newLine();
			/*
			 * characterfile.write("runeQuest = ", 0, 12);
			 * characterfile.write(Integer.toString(p.rMQ), 0,
			 * Integer.toString(p.rMQ).length()); characterfile.newLine();
			 * characterfile.write("herbQuest = ", 0, 12);
			 * characterfile.write(Integer.toString(p.bMQ), 0,
			 * Integer.toString(p.bMQ).length()); characterfile.newLine();
			 */
			characterfile.write("totalstored = ", 0, 14);
			characterfile.write(Integer.toString(p.totalstored), 0, Integer
					.toString(p.totalstored).length());
			characterfile.newLine();
			characterfile.write("autoRet = ", 0, 10);
			characterfile.write(Integer.toString(p.autoRet), 0, Integer
					.toString(p.autoRet).length());
			characterfile.newLine();
			characterfile.write("trade11 = ", 0, 10);
			characterfile.write(Integer.toString(p.trade11), 0, Integer
					.toString(p.trade11).length());
			characterfile.newLine();
			characterfile.write("SpeDelay = ", 0, 11);
			characterfile.write(Long.toString(p.SpecialDelay), 0, Long
					.toString(p.SpecialDelay).length());
			characterfile.newLine();
			characterfile.write("barrowskillcount = ", 0, 19);
			characterfile.write(Integer.toString(p.barrowsKillCount), 0,
					Integer.toString(p.barrowsKillCount).length());
			characterfile.newLine();
			/*
			 * characterfile.write("flagged = ", 0, 10);
			 * characterfile.write(Boolean.toString(p.accountFlagged), 0,
			 * Boolean.toString(p.accountFlagged).length());
			 * characterfile.newLine(); characterfile.write("Rules = ", 0, 8);
			 * characterfile.write(Boolean.toString(p.readRules), 0,
			 * Boolean.toString(p.readRules).length()); characterfile.newLine();
			 */
			characterfile.write("foodEat = ", 0, 10);
			characterfile.write(Integer.toString(p.foodEat), 0, Integer
					.toString(p.foodEat).length());
			characterfile.newLine();
			characterfile.write("nomadSk = ", 0, 10);
			characterfile.write(Integer.toString(p.nomadSk), 0, Integer
					.toString(p.nomadSk).length());
			characterfile.newLine();
			characterfile.write("GoblinS = ", 0, 10);
			characterfile.write(Integer.toString(p.GoblinS), 0, Integer
					.toString(p.GoblinS).length());
			characterfile.newLine();
			characterfile.write("Jadskls = ", 0, 10);
			characterfile.write(Integer.toString(p.Jadskls), 0, Integer
					.toString(p.Jadskls).length());
			characterfile.newLine();
			characterfile.write("newKil = ", 0, 9);
			characterfile.write(Integer.toString(p.newKil), 0, Integer
					.toString(p.newKil).length());
			characterfile.newLine();
			characterfile.write("beastK = ", 0, 9);
			characterfile.write(Integer.toString(p.beastK), 0, Integer
					.toString(p.beastK).length());
			characterfile.newLine();
			characterfile.write("crabsK = ", 0, 9);
			characterfile.write(Integer.toString(p.crabsK), 0, Integer
					.toString(p.crabsK).length());
			characterfile.newLine();
			characterfile.write("spinsLe = ", 0, 10);
			characterfile.write(Integer.toString(p.spinsLe), 0, Integer
					.toString(p.spinsLe).length());
			characterfile.newLine();
			characterfile.write("WinsSFF = ", 0, 10);
			characterfile.write(Integer.toString(p.WinsSFF), 0, Integer
					.toString(p.WinsSFF).length());
			characterfile.newLine();
			characterfile.write("WinsPCC = ", 0, 10);
			characterfile.write(Integer.toString(p.WinsPCC), 0, Integer
					.toString(p.WinsPCC).length());
			characterfile.newLine();
			characterfile.write("claimLater = ", 0, 13);
			characterfile.write(Boolean.toString(p.claimLater), 0, Boolean
					.toString(p.claimLater).length());
			characterfile.newLine();
			characterfile.write("isMuted = ", 0, 10);
			characterfile.write(Boolean.toString(p.isMuted), 0, Boolean
					.toString(p.isMuted).length());
			characterfile.newLine();
			characterfile.write("hasYell = ", 0, 10);
			characterfile.write(Boolean.toString(p.hasYell), 0, Boolean
					.toString(p.hasYell).length());
			characterfile.newLine();
			characterfile.write("hasChoosenDung = ", 0, 9);
			characterfile.write(Boolean.toString(p.hasChoosenDung), 0, Boolean
					.toString(p.hasChoosenDung).length());
			characterfile.newLine();
			characterfile.write("hasGivenSlayReward = ", 0, 21);
			characterfile.write(Boolean.toString(p.hasGivenSlayReward), 0,
					Boolean.toString(p.hasGivenSlayReward).length());
			characterfile.newLine();
			characterfile.write("wave = ", 0, 7);
			characterfile.write(Integer.toString(p.waveId), 0, Integer
					.toString(p.waveId).length());
			characterfile.newLine();
			characterfile.write("dfs-charges = ", 0, 14);
			characterfile.write(Integer.toString(p.dfsCount), 0, Integer
					.toString(p.dfsCount).length());
			characterfile.newLine();
			characterfile.write("hasFollower = ", 0, 14);
			characterfile.write(Integer.toString(p.hasFollower), 0, Integer
					.toString(p.hasFollower).length());
			characterfile.newLine();
			characterfile.write("summoningnpcid = ", 0, 17);
			characterfile.write(Integer.toString(p.summoningnpcid), 0, Integer
					.toString(p.summoningnpcid).length());
			characterfile.newLine();
			characterfile.write("fightMode = ", 0, 12);
			characterfile.write(Integer.toString(p.fightMode), 0, Integer
					.toString(p.fightMode).length());
			characterfile.newLine();
			characterfile.write("COaltar = ", 0, 10);
			characterfile.write(Integer.toString(p.COaltar), 0, Integer
					.toString(p.COaltar).length());
			characterfile.newLine();
			characterfile.write("amount2 = ", 0, 10);
			characterfile.write(Integer.toString(Client.amount2), 0, Integer
					.toString(Client.amount2).length());
			characterfile.newLine();
			characterfile.write("item2 = ", 0, 8);
			characterfile.write(Integer.toString(Client.item2), 0, Integer
					.toString(Client.item2).length());
			characterfile.newLine();
			characterfile.write("COChair = ", 0, 10);
			characterfile.write(Integer.toString(p.COChair), 0, Integer
					.toString(p.COChair).length());
			characterfile.newLine();
			characterfile.write("SPoints = ", 0, 10);
			characterfile.write(Integer.toString(p.SPoints), 0, Integer
					.toString(p.SPoints).length());
			characterfile.newLine();
			characterfile.write("DUfood1 = ", 0, 10);
			characterfile.write(Integer.toString(p.DUfood1), 0, Integer
					.toString(p.DUfood1).length());
			characterfile.newLine();
			characterfile.write("DUfood2 = ", 0, 10);
			characterfile.write(Integer.toString(p.DUfood2), 0, Integer
					.toString(p.DUfood2).length());
			characterfile.newLine();
			characterfile.write("DUfood3 = ", 0, 10);
			characterfile.write(Integer.toString(p.DUfood3), 0, Integer
					.toString(p.DUfood3).length());
			characterfile.newLine();
			characterfile.write("DUfood4 = ", 0, 10);
			characterfile.write(Integer.toString(p.DUfood4), 0, Integer
					.toString(p.DUfood4).length());
			characterfile.newLine();
			characterfile.write("OWdungn = ", 0, 10);
			characterfile.write(Integer.toString(p.dungn), 0,
					Integer.toString(p.dungn).length());
			characterfile.newLine();
			characterfile.write("COchest = ", 0, 10);
			characterfile.write(Integer.toString(p.COchest), 0, Integer
					.toString(p.COchest).length());
			characterfile.newLine();
			characterfile.write("COtreee = ", 0, 10);
			characterfile.write(Integer.toString(p.COtreee), 0, Integer
					.toString(p.COtreee).length());
			characterfile.newLine();
			characterfile.write("COLectt = ", 0, 10);
			characterfile.write(Integer.toString(p.COLectt), 0, Integer
					.toString(p.COLectt).length());
			characterfile.newLine();
			characterfile.write("COtelee = ", 0, 10);
			characterfile.write(Integer.toString(p.COtelee), 0, Integer
					.toString(p.COtelee).length());
			characterfile.newLine();
			characterfile.write("COcryst = ", 0, 10);
			characterfile.write(Integer.toString(p.COcryst), 0, Integer
					.toString(p.COcryst).length());
			characterfile.newLine();
			characterfile.write("CObeddy = ", 0, 10);
			characterfile.write(Integer.toString(p.CObeddy), 0, Integer
					.toString(p.CObeddy).length());
			characterfile.newLine();
			characterfile.write("dungPoints = ", 0, 13);
			characterfile.write(Integer.toString(p.dungPoints), 0, Integer
					.toString(p.dungPoints).length());
			characterfile.newLine();
			characterfile.write("funPoints = ", 0, 12);
			characterfile.write(Integer.toString(p.funPoints), 0, Integer
					.toString(p.funPoints).length());
			characterfile.newLine();
			characterfile.write("FoGRating = ", 0, 12);
			characterfile.write(Integer.toString(p.FoGRating), 0, Integer
					.toString(p.FoGRating).length());
			characterfile.newLine();
			characterfile.write("pkinPoints = ", 0, 13);
			characterfile.write(Integer.toString(p.pkinPoints), 0, Integer
					.toString(p.pkinPoints).length());
			characterfile.newLine();
			characterfile.write("dominionPoints = ", 0, 17);
			characterfile.write(Integer.toString(p.dominionPoints), 0, Integer
					.toString(p.dominionPoints).length());
			characterfile.newLine();
			/*
			 * characterfile.write("Black-marks = ", 0, 12);
			 * characterfile.write(Integer.toString(p.BlackMarks), 0,
			 * Integer.toString(p.BlackMarks).length());
			 * characterfile.newLine(); characterfile.write("void = ", 0, 7);
			 * String toWrite = p.voidStatus[0] + "\t" + p.voidStatus[1] + "\t"
			 * + p.voidStatus[2] + "\t" + p.voidStatus[3] + "\t" +
			 * p.voidStatus[4]; characterfile.write(toWrite);
			 */

			/* EQUIPMENT */
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < p.playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipment[i]), 0,
						Integer.toString(p.playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipmentN[i]), 0,
						Integer.toString(p.playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();

			/* LOOK */
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.playerAppearance.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerAppearance[i]), 0,
						Integer.toString(p.playerAppearance[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			/* SKILLS */
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerLevel[i]), 0,
						Integer.toString(p.playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerXP[i]), 0, Integer
						.toString(p.playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			/* ITEMS */
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < p.playerItems.length; i++) {
				if (p.playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItems[i]), 0,
							Integer.toString(p.playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItemsN[i]), 0,
							Integer.toString(p.playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/* BANK */
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.bankItems.length; i++) {
				if (p.bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems[i]), 0,
							Integer.toString(p.bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItemsN[i]), 0,
							Integer.toString(p.bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems1.length; i++) {
				if (p.bankItems1[i] > 0) {
					characterfile.write("character-bank1 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems1[i]), 0,
							Integer.toString(p.bankItems1[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems1N[i]), 0,
							Integer.toString(p.bankItems1N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems2.length; i++) {
				if (p.bankItems2[i] > 0) {
					characterfile.write("character-bank2 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems2[i]), 0,
							Integer.toString(p.bankItems2[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems2N[i]), 0,
							Integer.toString(p.bankItems2N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems3.length; i++) {
				if (p.bankItems3[i] > 0) {
					characterfile.write("character-bank3 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems3[i]), 0,
							Integer.toString(p.bankItems3[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems3N[i]), 0,
							Integer.toString(p.bankItems3N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems4.length; i++) {
				if (p.bankItems4[i] > 0) {
					characterfile.write("character-bank4 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems4[i]), 0,
							Integer.toString(p.bankItems4[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems4N[i]), 0,
							Integer.toString(p.bankItems4N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems5.length; i++) {
				if (p.bankItems5[i] > 0) {
					characterfile.write("character-bank5 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems5[i]), 0,
							Integer.toString(p.bankItems5[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems5N[i]), 0,
							Integer.toString(p.bankItems5N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems6.length; i++) {
				if (p.bankItems6[i] > 0) {
					characterfile.write("character-bank6 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems6[i]), 0,
							Integer.toString(p.bankItems6[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems6N[i]), 0,
							Integer.toString(p.bankItems6N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems7.length; i++) {
				if (p.bankItems7[i] > 0) {
					characterfile.write("character-bank7 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems7[i]), 0,
							Integer.toString(p.bankItems7[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems7N[i]), 0,
							Integer.toString(p.bankItems7N[i]).length());
					characterfile.newLine();
				}
			}
			for (int i = 0; i < p.bankItems8.length; i++) {
				if (p.bankItems8[i] > 0) {
					characterfile.write("character-bank8 = ", 0, 18);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems8[i]), 0,
							Integer.toString(p.bankItems8[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems8N[i]), 0,
							Integer.toString(p.bankItems8N[i]).length());
					characterfile.newLine();
				}
			}

			characterfile.newLine();

			/* FRIENDS */
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < p.friends.length; i++) {
				if (p.friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write("" + p.friends[i]);
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/* Storeditems */
			characterfile.write("[STORED]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.storeditems.length; i++) {
				characterfile.write("stored = ", 0, 9);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.storeditems[i]), 0,
						Integer.toString(p.storeditems[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			/* Storeditems */
			characterfile.write("[OCCUPY]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.occupied.length; i++) {
				characterfile.write("occupy = ", 0, 9);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Boolean.toString(p.occupied[i]), 0, Boolean
						.toString(p.occupied[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			characterfile.write("[ACHIEVEMENTS - EASY TASKS]", 0, 27);
			characterfile.newLine();
			for (int i = 0; i <= 9; i++) {
				characterfile.write("task = ", 0, 7);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Boolean.toString(p.task1[i]), 0, Boolean
						.toString(p.task1[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			characterfile.write("[ACHIEVEMENTS - HARD TASKS]", 0, 27);
			characterfile.newLine();
			for (int i = 0; i <= 9; i++) {
				characterfile.write("task2 = ", 0, 7);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Boolean.toString(p.task2[i]), 0, Boolean
						.toString(p.task2[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			characterfile.write("[ACHIEVEMENTS - ELITE TASKS]", 0, 28);
			characterfile.newLine();
			for (int i = 0; i <= 9; i++) {
				characterfile.write("task3 = ", 0, 7);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Boolean.toString(p.task3[i]), 0, Boolean
						.toString(p.task3[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			characterfile.write("[Construction]", 0, 14);
			characterfile.newLine();
			for (int i = 0; i < p.constructionObjects.length; i++) {
				characterfile.write("Objects = ", 0, 10);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Boolean.toString(p.constructionObjects[i]),
						0, Boolean.toString(p.constructionObjects[i]).length());
				characterfile.newLine();
			}

			characterfile.newLine();

			characterfile.write("[UPGRADES]", 0, 10);
			characterfile.newLine();
			for (int i = 0; i < p.constructionUpgrades.length; i++) {
				characterfile.write("Upgrades = ", 0, 11);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(
						Integer.toString(p.constructionUpgrades[i]), 0, Integer
								.toString(p.constructionUpgrades[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			characterfile.write("[CHARGES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < p.chaoticCharges.length; i++) {
				characterfile.write("Charges = ", 0, 10);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.chaoticCharges[i]), 0,
						Integer.toString(p.chaoticCharges[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* IGNORES */
			/*
			 * characterfile.write("[IGNORES]", 0, 9); characterfile.newLine();
			 * for (int i = 0; i < ignores.length; i++) { if (ignores[i] > 0) {
			 * characterfile.write("character-ignore = ", 0, 19);
			 * characterfile.write(Integer.toString(i), 0,
			 * Integer.toString(i).length()); characterfile.write("	", 0, 1);
			 * characterfile.write(Long.toString(ignores[i]), 0,
			 * Long.toString(ignores[i]).length()); characterfile.newLine(); } }
			 * characterfile.newLine();
			 */
			/* EOF */
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			Misc.println(p.playerName + ": error writing file.");
			return false;
		}
		return true;
	}
}