package server.model.players.content.skills;

/**
 * @author Gabbe: gabbe_impactpk@live.com
 * Dungeoneering Minigame/Skill 
 */

import server.Config;
import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.skills.impl.Binding;
import server.util.Misc;

public class Dungeoneering {

	public static void addItems(Client c) {
		c.getItems().addItem(17489, 1);
		c.getItems().addItem(18201, 100000);
		Binding.addBindItems(c);
	}

	public static void addItemsDuo(Client c, Client c2) {
		c.getItems().addItem(17489, 1);
		c.getItems().addItem(18201, 100000);
		c2.getItems().addItem(18201, 100000);
		Binding.addBindItems(c);
		Binding.addBindItems(c2);
	}

	public static boolean antiSmuggle(Client c, int ID) {
		for (int i = 0; i < Config.NOT_ALLOWED.length; i++) {
			if (!c.inDung && ID == Config.NOT_ALLOWED[i]) {
				c.sendMessage("That item is a forbidden out-of-dung item!!");
				c.sendMessage("Staff members have been notified and the item has been removed.");
				PlayerHandler.notifyStaff(c);
				if (c.getItems().playerHasItem(Config.NOT_ALLOWED[i], 1))
					c.getItems().deleteItem(Config.NOT_ALLOWED[i], 28);
				return true;
			}
		}
		return false;
	}

	public static void deleteKeys(Client c) { // checks for ground items /
												// deletes for a new udng
		if (Server.itemHandler.itemExists(18314, 1871, 5241)) {
			Server.itemHandler.removeGroundItem(c, 18314, 1871, 5241, false);
		} else if (Server.itemHandler.itemExists(18300, 1882, 5244)) {
			Server.itemHandler.removeGroundItem(c, 18300, 1882, 5244, false);
		} else if (Server.itemHandler.itemExists(18292, 1876, 5209)) {
			Server.itemHandler.removeGroundItem(c, 18292, 1876, 5209, false);
		} else if (Server.itemHandler.itemExists(18268, 1858, 5201)) {
			Server.itemHandler.removeGroundItem(c, 18268, 1858, 5201, false);
		} else if (Server.itemHandler.itemExists(17489, c.GatestoneX,
				c.GatestoneY)) {
			Server.itemHandler.removeGroundItem(c, 17489, c.GatestoneX,
					c.GatestoneY, false);
		} else if (Server.itemHandler.itemExists(18298, 1904, 5191)) {
			Server.itemHandler.removeGroundItem(c, 18298, 1904, 5191, false);
		} else if (Server.itemHandler.itemExists(18268, 1858, 5201)) {
			Server.itemHandler.removeGroundItem(c, 18268, 1858, 5201, false);
		}
	}

	public static void drop(Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				NPCHandler.dontdrop = false;
			}

			@Override
			public void stop() {

			}
		}, 4);
	}

	public static void handleDeath(Client c) {
		// restore stats first
		for (int k = 0; k < 25; k++) {
			c.playerLevel[k] = c.getPA().getLevelForXP(c.playerXP[k]);
			c.getPA().refreshSkill(k);
		}
		c.inDung = true;
		c.InDung = true;
		c.startAnimation(836);
		c.dungDeaths += 1;
		c.sendMessage("You've died! You will receive less XP for this.");
		c.isDead = false;
		c.getPA().resetDamageDone();
		c.getPA().resetFollowers();
		c.attackTimer = 10;
		c.getPA().removeAllWindows();
		c.getTradeAndDuel().resetTrade();
		c.getPA().closeAllWindows();
		c.stopMovement();
		c.specAmount = 10;
		c.getItems().addSpecialBar(c.playerEquipment[Player.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;
		c.prayerId = -1;
		c.getCombat().resetPlayerAttack();
		c.resetWalkingQueue();
		c.getPA().movePlayer(1861, 5243, c.playerId * 4);
		c.resetWalkingQueue();
		if (c.bossing) {
			c.bossing = false;
			Server.npcHandler.killAllDungNPCs(c);
			if (c.fullDung) {
				spawnNpcs(c, true);
			} else {
				spawnNpcs(c, false);
			}
		}
	}

	public static void handleStart(Client c, boolean hard) {

		c.getPA().movePlayer(1862, 5242, c.playerId * 4);
		newDungeon(c, true); // sets the variables
		c.sendMessage("<shad=6081134>Welcome to Dungeoneering floor 1! Complexity: 6");
		c.sendMessage("<shad=6081134>Kill the NPCs for supplies and bonus XP!");
		// c.dungeonId = receiveNewDungeonId(c);
		c.dungeonId = 1;
		System.out.println("C.DUNGEONID = " + c.dungeonId + "");
		addItems(c);
		spawnObjects(c);

		if (hard) {
			c.fullDung = true;
			spawnKeys(c, true);
			spawnNpcs(c, true);
		} else if (!hard) {
			c.fullDung = false;
			spawnKeys(c, false);
			spawnNpcs(c, false);
		}

		// } else {
		/*
		 * for (Player p : PlayerHandler.players) { if (p != null) { Client
		 * ALLPLAYERS = (Client)p;
		 * if(ALLPLAYERS.playerName.equalsIgnoreCase(c.Partner)) { Client c2 =
		 * (Client)p; if(c2 != null) { newDungeon(c, true); // sets the
		 * variables newDungeon(c2, true); // sets the variables c.inDung =
		 * true; c.InDung = true; c.getPA().movePlayer(1862, 5242, c.playerId *
		 * 4); spawnKeys(c); spawnNpcs(c); spawnObjects(c); c.sendMessage(
		 * "<shad=6081134>Welcome to Dungeoneering floor 1! Complexity: 6");
		 * c.sendMessage
		 * ("<shad=6081134>Kill the NPCs for supplies and bonus XP!"); c2.inDung
		 * = true; c2.InDung = true; c2.getPA().movePlayer(1862, 5242,
		 * c.playerId * 4); spawnKeys(c2); spawnNpcs(c2); spawnObjects(c2);
		 * c2.sendMessage
		 * ("<shad=6081134>Welcome to Dungeoneering floor 1! Complexity: 6");
		 * c2.
		 * sendMessage("<shad=6081134>Kill the NPCs for supplies and bonus XP!"
		 * ); c.sendMessage(
		 * "You're the party owner. The groupstone is handled by you only.");
		 * addItemsDuo(c, c2);
		 * 
		 * } } } } }
		 */

	}

	public static void newDungeon(Client c, boolean inDung) {
		c.door1 = false;
		c.door2 = false;
		c.door3 = false;
		c.door4 = false;
		c.npcsKilled = 0;
		c.bossing = false;
		c.dungDeaths = 0;
		c.completed = false;

		if (inDung) {
			c.inDung = true;
			c.InDung = true;
			c.hasFollower = -1;
			c.getPA().setDungTab();
		} else {
			c.inDung = false;
			c.inDung = false;
			deleteKeys(c);
			c.Partner = "None";
			c.Inviter = "";
			c.inParty = false;
			c.answerParty = false;
		}

		c.receivedItems = false;
		c.GatestoneX = 0;
		c.GatestoneY = 0;
		c.dungeonId = 0;

		c.SaveGame();
	}

	public static void onLogin(Client c, boolean full) {
		c.getPA().setDungTab();
		if (c.dungeonId == 1) {
			if (!c.door2 && !c.getItems().playerHasItem(18314, 1)) {
				Server.itemHandler.createGroundItem(c, 18314, 1871, 5241, 1,
						c.playerId);
			}
			if (!c.door1 && !c.getItems().playerHasItem(18292, 1)) {
				Server.itemHandler.createGroundItem(c, 18292, 1876, 5209, 1,
						c.playerId);
			}
			if (!c.door3 && !c.getItems().playerHasItem(18300, 1)) {
				Server.itemHandler.createGroundItem(c, 18300, 1882, 5244, 1,
						c.playerId);
			}
			if (full) {
				if (!c.door2 && !c.getItems().playerHasItem(18268, 1)) {
					Server.itemHandler.createGroundItem(c, 18268, 1858, 5201,
							1, c.playerId);
				}
			}
		}
		if (c.dungeonId == 2) {
			if (!c.door1 && !c.getItems().playerHasItem(18314, 1)) {
				Server.itemHandler.createGroundItem(c, 18298, 1904, 5191, 1,
						c.playerId);
			}
			if (!c.door2 && !c.getItems().playerHasItem(18268, 1)) {
				Server.itemHandler.createGroundItem(c, 18302, 1901, 5245, 1,
						c.playerId);
			}
		}
		c.getItems().deleteItem(17489, 1);
		c.GatestoneX = 0;
		c.GatestoneY = 0;
		c.getItems().addItem(17489, 1);
		System.out.println("was here");
		spawnNpcs(c, full);
	}

	public static int receiveNewDungeonId(Client c) {
		int random = Misc.random(10);
		int id = 1;
		if (random >= 0 && random <= 5)
			id = 1;
		if (random >= 5 && random <= 10)
			id = 2;
		return id;
	}

	public static void spawnBossesV1(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@SuppressWarnings("unused")
			@Override
			public void execute(CycleEventContainer container) {
				int hp = c.getLevelForXP(c.playerXP[3]) * 3;
				int max = c.getLevelForXP(c.playerXP[1]) / 4;
				int attk = (c.getLevelForXP(c.playerXP[2])
						+ c.getLevelForXP(c.playerXP[0]) - 10) / 3;
				int def = c.getLevelForXP(c.playerXP[0]) / 3;

				if (max < 30) {
					max = 30;
				}
				if (attk < 60) {
					attk = 60;
				}
				if (def < 60) {
					def = 60;
				}
				Server.npcHandler.killAllDungNPCs(c);
				// NPCHandler.spawnDungNpcs(c, 7133, 1907, 5223, c.playerId * 4,
				// 0, 600, 40, 240, 230, true, false); // bork
				// NPCHandler.spawnDungNpcs(c, 10127, 1909, 5221, c.playerId *
				// 4, 0, 300, 20, 240, 230, true, false); // bork//
				c.InDung = true;
				c.inDung = true;
				c.bossing = true;
				drop(c);
				NPCHandler.spawnDungNpcs(c, 7133, c.absX, c.absY,
						c.playerId * 4, 0, 600, 40, 240, 120, true, false); // bork

				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public static void spawnBossesV2(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				// NPCHandler.spawnDungNpcs(c, 7133, 1907, 5223, c.playerId * 4,
				// 0, 600, 40, 240, 230, true, false); // bork
				// NPCHandler.spawnDungNpcs(c, 10127, 1909, 5221, c.playerId *
				// 4, 0, 300, 20, 240, 230, true, false); // bork//
				c.InDung = true;
				c.inDung = true;
				c.bossing = true;
				NPCHandler.spawnDungNpcs(c, 10127, c.absX, c.absY,
						c.playerId * 4, 0, 200, 30, 240, 130, true, false); // bork
				drop(c);
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public static void spawnKey(Client c, boolean hard) {
		if (c == null) {
			return;
		}
		if (c.dungeonId == 1) {
			if (!Server.itemHandler.itemExists(18314, 1871, 5241))
				Server.itemHandler.createGroundItem(c, 18314, 1871, 5241, 1,
						c.playerId);
			if (!Server.itemHandler.itemExists(18292, 1876, 5209))
				Server.itemHandler.createGroundItem(c, 18292, 1876, 5209, 1,
						c.playerId);
			if (!Server.itemHandler.itemExists(18300, 1882, 5244))
				Server.itemHandler.createGroundItem(c, 18300, 1882, 5244, 1,
						c.playerId);
			if (hard) {
				Server.itemHandler.createGroundItem(c, 18268, 1858, 5201, 1,
						c.playerId);
				Server.itemHandler.createGroundItem(c, 18296, 1911, 5203, 1,
						c.playerId);
			}
		} else if (c.dungeonId == 2) {
			Server.itemHandler.createGroundItem(c, 18298, 1904, 5191, 1,
					c.playerId);
			Server.itemHandler.createGroundItem(c, 18302, 1901, 5245, 1,
					c.playerId);
		} else if (c.dungeonId == 3) {
			// Server.itemHandler.createGroundItem(c, 18314, 1871, 5241, 1,
			// c.playerId);
			// Server.itemHandler.createGroundItem(c, 18268, 1858, 5201, 1,
			// c.playerId);
		} else if (c.dungeonId == 4) {
			// Server.itemHandler.createGroundItem(c, 18314, 1871, 5241, 1,
			// c.playerId);
			// Server.itemHandler.createGroundItem(c, 18268, 1858, 5201, 1,
			// c.playerId);
		}
	}

	public static void spawnKeys(final Client c, final boolean hard) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (hard) {
					spawnKey(c, true);
				} else if (!hard) {
					spawnKey(c, false);
				}
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public static void spawnMinions(final Client c, int x, int y, int type) {
		NPCHandler.spawnDungNpcs(c, type, x, y, c.playerId * 4, 0, 100, 20,
				240, 120, true, false); //

	}

	public static void spawnNpcs(final Client c, final boolean hard) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				// SETS THE NPCS STATS AFTER THE PLAYERS STATS :)
				int hp = c.getLevelForXP(c.playerXP[3]) * 2;
				int max = c.getLevelForXP(c.playerXP[1]) / 5;
				int attk = (c.getLevelForXP(c.playerXP[2])
						+ c.getLevelForXP(c.playerXP[0]) - 10) / 3;
				int def = c.getLevelForXP(c.playerXP[0]) / 4;

				if (max < 5) {
					max = 7;
				}
				if (attk < 10) {
					attk = 15;
				}
				if (def < 10) {
					def = 10;
				}
				System.out.println("was here");
				// zombies
				NPCHandler.spawnDungNpcs(c, 3622, 1887, 5205, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				NPCHandler.spawnDungNpcs(c, 3622, 1879, 5199, c.playerId * 4,
						0, hp, max, attk, def, true, false);

				NPCHandler.spawnDungNpcs(c, 3622, 1862, 5228, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				NPCHandler.spawnDungNpcs(c, 3622, 1862, 5223, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				NPCHandler.spawnDungNpcs(c, 3622, 1860, 5223, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				if (hard) {
					NPCHandler.spawnDungNpcs(c, 3622, 1863, 5205,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 3622, 1863, 5202,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 3622, 1861, 5200,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 3622, 1860, 5203,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 3622, 1859, 5206,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 3622, 1864, 5189,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 3622, 1869, 5190,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 3622, 1874, 5188,
							c.playerId * 4, 0, hp, max, attk, def, true, false);

					// ankou
					NPCHandler.spawnDungNpcs(c, 4382, 1890, 5225,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1895, 5230,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1892, 5217,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1890, 5214,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1901, 5207,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1901, 5200,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1903, 5192,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1897, 5189,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1897, 5196,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1886, 5189,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1890, 5195,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1892, 5188,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1874, 5213,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1878, 5216,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1877, 5220,
							c.playerId * 4, 0, hp, max, attk, def, true, false);
					NPCHandler.spawnDungNpcs(c, 4382, 1874, 5218,
							c.playerId * 4, 0, hp, max, attk, def, true, false);

				}
				NPCHandler.spawnDungNpcs(c, 3622, 1872, 5229, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				NPCHandler.spawnDungNpcs(c, 3622, 1872, 5235, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				NPCHandler.spawnDungNpcs(c, 3622, 1873, 5239, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				NPCHandler.spawnDungNpcs(c, 3622, 1872, 5242, c.playerId * 4,
						0, hp, max, attk, def, true, false);

				NPCHandler.spawnDungNpcs(c, 4382, 1884, 5231, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				NPCHandler.spawnDungNpcs(c, 4382, 1880, 5231, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				NPCHandler.spawnDungNpcs(c, 4382, 1882, 5241, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				// werewolves
				NPCHandler.spawnDungNpcs(c, 6212, 1892, 5242, c.playerId * 4,
						0, hp, max, attk, def, true, false);
				NPCHandler.spawnDungNpcs(c, 6212, 1893, 5237, c.playerId * 4,
						0, hp, max, attk, def, true, false);

				NPCHandler.spawnDungNpcs(c, 11226, 1863, 5243, c.playerId * 4,
						0, 100, 5, 30, 40, false, false);
				NPCHandler.dontdrop = false;
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public static void spawnObjects(Client c) {

	}

	public int dungeonId = 0;

	public Dungeoneering(Client Client) {
	}

	public int giveXP(Client c, boolean left) {
		int xp = 0;
		int bonus = 0;
		if (!left) {
			bonus += 42000;
		}
		if (c.npcsKilled > 0) {
			bonus += c.npcsKilled * 4000;
		}
		if (c.door1 || c.door2) {
			bonus += 5000;
		}
		if (c.dungDeaths > 0) {
			bonus -= c.dungDeaths * 2000;
		}
		if (bonus >= 0) {
			xp += bonus;
			c.getPA().addSkillXP(xp, 24);
		}
		return xp;
	}
}