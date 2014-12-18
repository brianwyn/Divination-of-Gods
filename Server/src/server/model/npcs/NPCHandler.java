package server.model.npcs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import server.Config;
import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.minigames.DominionTowerByGabbe;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.GabbesAchievements;
import server.model.players.content.ImpSpawn;
import server.model.players.content.skills.Dungeoneering;
import server.model.players.content.skills.impl.Binding;
import server.util.Misc;
import server.world.clip.region.Region;

public class NPCHandler {

	public static int corpRewards[] = { 6585, 1127, 13979, 2651, 1185, 1275,
			2581, 13958, 15509, 2651, 1079, 1093, 1113, 20001, 20002, 20003, 1185, 1201, 1275, 1303,
			19669, 1333, 13344, 1373, 2491, 2497, 2503, 1303, 13748, 2581,
			2651, 13750, 560, 555, 13752, 13734, 13736, 2, 19368, 19370, 13884,
			13982, 20000 };
	public static void deleteFCNpcs(Client c) {

	}
	/****
	 ****** FIXED THE FUCKING RESPAWNING PROBLEM TOOK 2 HOURS TO FIND THE FUCKING
	 * PROBLEM - LMFAO
	 ****/
	public String NexTalk = "";
	public static int maxNPCs = 10000;
	public static int maxNPCs2 = 4000;
	public static int maxNPCs3 = 8135;
	public static int zombieNPC = 3622;
	public static int maxListedNPCs = 10000;
	public static int random;
	public static int maxNPCDrops = 10000;
	public static NPC npcs[] = new NPC[maxNPCs];
	public static NPC npcs2[] = new NPC[maxNPCs2];

	public static NPC npcs3[] = new NPC[maxNPCs3];

	public static NPCList NpcList[] = new NPCList[maxListedNPCs];

	public static int lastImpDeathX = 0;

	public static int lastImpDeathY = 0;
	public static boolean dontdrop = false;

	public static int nexRewards[] = { 6585, 13355, 2651, 1185, 1275, 2581,
			13958, 15509, 2651, 1079, 1093, 1113, 1185, 1201, 1275, 1303,
			19669, 1333, 13344, 1373, 2491, 2497, 2503, 1303, 13748, 13360,
			2651, 13750, 560, 555, 13752, 18377, 13736, 2, 19368, 19370, 13884,
			13979, 13982, 14048, 19007 };

	public static void deletePCShifters(Client c) {
		destroyPCNpcs2(c);
		c.hasNotDeleted = false;
	}

	/**
	 * Summon npc, barrows, etc
	 **/

	public static void destroyDungNpcs(Client c) {

	}

	public static void destroyPCNpcs2(Client c) {

	}

	public static int dungBossDrop() {
		return Binding.getBindedItems()[Misc.random(20)][0];
	}

	/***/
	/*****
	 * PEST CONTROL
	 *****/
	public static NPC forType(int type) {
		NPC[] var4 = npcs;
		int var3 = npcs.length;

		for (int var2 = 0; var2 < var3; ++var2) {
			NPC n = var4[var2];
			if (n != null && n.npcType == type) {
				return n;
			}
		}

		return null;
	}

	/**
	 * Emotes
	 **/

	public static int getAttackEmote(int i) {
		switch (NPCHandler.npcs[i].npcType) {

		case 8283:
			if (npcs[i].attackType == 1)
			return 10681;
		case 8282:
			return 10669;
		case 1374:
			return 15477;
		case 1381:// gano beast
			return 15466;

		case 10127:
			return 13176;
		case 10110:// bulwark beast
			if (npcs[i].attackType == 0)
				return 13004;
			else if (npcs[i].attackType == 1)
				return 13003;
			else if (npcs[i].attackType == 2)
				return 13002;
		case 9780:// NPC
			return 13703;
		case 9752:// warzone
			if (npcs[i].attackType == 0)
				return 13420;
			else if (npcs[i].attackType == 1)
				return 13422;
			else if (npcs[i].attackType == 2)
				return 13423;
		case 10141:// bal'lak
		case 10128:
		case 10129:
		case 10130:
		case 10131:
		case 10132:
		case 10133:
		case 10134:
		case 10135:
		case 10136:
		case 10137:
			int rand = Misc.random(1);
			if (npcs[i].attackType == 2)
				return 13606;
			else if (npcs[i].attackType == 0)
				return 13600;
			else if (npcs[i].attackType == 1 && rand == 1)
				return 13601;
			else if (npcs[i].attackType == 1 && rand == 0)
				return 13603;
		case 7133: // Bork
			return 8754;
		case 9767:
			return 13703;
		case 2743:
			return 1;
		case 1540:
			return 1;
		case 1813:
			return 1;
		case 1125:// dad
			return 284;
		case 10040:
			return 10816;
		case 2779:
			return 426;
		case 3067:
			return 7041; // 2h emote
		case 5529:
			return 5782;
		case 3622:// Zombies!!
			return 5578;
		case 3732:
		case 3734:
		case 3736:
		case 3738:
		case 3740:
			return 3901;
		case 751: // the spirit
			return 6728;
		case 5213:
		case 5214:
		case 5215:
		case 5216:
		case 5217:
		case 5218:
		case 5219: // Penance fighter
			return 5097;

		case 5229:
		case 5230:
		case 5231:
		case 5232:
		case 5233:
		case 5234:
		case 5235:
		case 5236:
		case 5237: // Penance ranger
			return 5396;
			// end of barb assault

		case 2589:
		case 2588:
		case 2587:
		case 2586:
			return 1979;
		case 8528:
			return 12696;
		case 6260:
			return 7060;
		case 8597:
			return 11202;// avatar of desc
		case 10530: // forgotten warrior
			return 2661;
		case 5666: // barrelschest
			if (npcs[i].attackType == 0)
				return 5894;
			else
				return 5895;
		case 5248:
			return 6354 + Misc.random(1);
		case 2892:
		case 2894:
			return 2868;

		case 4477:
			// startAnimation(5556);
			// c.gfx0(369);
			return 5556;
		case 9463:
			return 12791;

		case 9467:
			return 12791;

		case 9465:
			return 12791;

		case 8133: // Corporeal beast
			if (npcs[i].attackType == 2)
				return 10058;
			else if (npcs[i].attackType == 1)
				return 10058;
			else if (npcs[i].attackType == 0)
				return 10057;
		case 3101: // Melee
			return 10058;
		case 3102: // Range
		case 3103: // Mage
			return 10057;
		case 4278:
		case 4279:
		case 4280:
		case 4281:
		case 4282:
		case 4284:
			return 451;
		case 93:
			return 5499;
		case 4283:
			return 7048;
			// dung npcs
		case 4382: // ankou
			return 422;
		case 5247: // PENANCE QUEEN
			return 5411;
			// GODWARS
		case 3247: // Hobgoblin
			return 6184;

		case 6270: // Cyclops
		case 6269: // Ice cyclops
		case 4291: // Cyclops
		case 4292: // Ice cyclops
			return 4652;

		case 6219: // Spiritual Warrior
		case 6255: // Spiritual Warrior
			return 451;

		case 6229: // Spirtual Warrior arma
			return 6954;

		case 6218: // Gorak
			return 4300;

		case 6212: // Werewolf
			return 6536;

		case 6220: // Spirtual Ranger
		case 6256: // Spirtual Ranger
			return 426;

		case 6257: // Spirtual Mage
		case 6221: // Spirtual Mage
			return 811;

		case 6276: // Spirtual Ranger
		case 6278: // Spirtual Mage
		case 6272: // Ork
		case 6274: // Ork
		case 6277: // Spirtual Warrior bandos
			return 4320;

		case 6230: // Spirtual Ranger
		case 6233: // Aviansie
		case 6239: // Aviansie
		case 6232: // Aviansie
			return 6953;

		case 6254: // Saradomin Priest
			return 440;
		case 6258: // Saradomin Knight
			return 7048;
		case 6231: // Spirtual Mage
			return 6952;

		case 3248: // HellHound
			return 6579;
			// ENDS

		case 6204:
			return 64;
		case 6208:
			return 6947;
		case 6206:
			return 6945;
		case 3494:
			return 1750;
		case 3491:
			return 1979;
		case 3496:
			if (npcs[i].attackType == 0)
				return 3508;
			else
				return 3507;
		case 6203:
			if (npcs[i].attackType == 0)
				return 6945;
			else
				return 6947; // end
		case 2627:
			return 2621;
		case 2630:
			return 2625;
		case 2631:
			return 2633;
		case 2741:
			return 2637;
		case 2746:
			return 2637;
		case 2607:
			return 2611;
		case 3493:
			if (npcs[i].attackType == 0)
				return 3501;
			else
				return 3502; // end
		case 50:
		case 53:
		case 54:
		case 742:
		case 55:
		case 941:
		case 4677:
		case 1590:
		case 1591:
		case 1592:
			if (npcs[i].attackType == 0)
				return 80;
			else
				return 81;
			// bandos gwd
		case 6261:
		case 6263:
		case 6265:
			return 6154;
			// end of gwd
			// arma gwd

		case 6222:
			return 6976;
		case 6225:
			return 6953;
		case 6223:
			return 6952;
		case 6227:
			return 6954;
			// end of arma gwd
			// sara gwd
		case 6247:
			return 6964;
		case 6248:
			return 6376;
		case 6250:
			return 7018;
		case 6252:
			return 7009;
			// end of sara gwd
		case 13: // wizards
			return 711;
		case 103:
			return 5540;
		case 655:
		case 104:
			return 5535;

		case 1624:
			return 1557;

		case 1648:
			return 1590;

		case 2783: // dark beast
			return 2731;

		case 1615: // abby demon
			return 1537;

		case 1613: // nech
			return 1528;

		case 1610:
		case 1611: // garg
			return 1519;

		case 1616: // basilisk
			return 1546;

		case 90: // skele
			return 260;

		case 124: // earth warrior
			return 390;

		case 803: // monk
			return 422;

		case 52: // baby drag
			return 25;

		case 58: // Shadow Spider
		case 59: // Giant Spider
		case 60: // Giant Spider
		case 61: // Spider
		case 62: // Jungle Spider
		case 63: // Deadly Red Spider
		case 64: // Ice Spider
		case 134:
			return 5327;

		case 105: // Bear
		case 106: // Bear
			return 4925;

		case 412:
		case 78:
			return 4915;

		case 2033: // rat
			return 4933;

		case 2031: // bloodworm
			return 2070;

		case 101: // goblin
		case 3663:
			return 6188;

		case 81: // cow
			return 5849;

		case 21: // hero
			return 451;

		case 41: // chicken
			return 55;

		case 9: // guard
		case 32: // guard
		case 20: // paladin
			return 451;

		case 1338: // dagannoth
		case 1340:
		case 1342:
			return 1341;

		case 19: // white knight
			return 406;

		case 110:
		case 111: // ice giant
		case 112:
		case 117:
			return 4672;

		case 2452:
			return 1312;

		case 2889:
			return 2859;

		case 118:
		case 119:
			return 99;

		case 82:// Lesser Demon
		case 83:// Greater Demon
		case 84:// Black Demon
		case 1472:// jungle demon
		case 10039:
			return 64;

		case 1267:
		case 1265:
			return 1312;

		case 125: // ice warrior
		case 178:
			return 451;

		case 1154: // Kalphite Soldier
			return 6225;
		case 1156: // Kalphite worker
		case 1160:// kalphite queen flying one
			return 6236;
		case 1157: // Kalphite guardian
			return 6226;
		case 1153: // Kalphite Worker
		case 1158: // kalphite queen no fly
			// case 1160:// kalphite queen flying one
			return 6223;

		case 123:
		case 122:
			return 164;

		case 2028: // karil
			return 2075;

		case 2025: // ahrim
			return 729;

		case 2026: // dharok
			return 2067;

		case 2027: // guthan
			return 2080;

		case 2029: // torag
			return 0x814;

		case 2030: // verac
			return 2062;

		case 2881: // supreme
			return 2855;

		case 2882: // prime
			return 2854;

		case 2883: // rex
			return 2851;

		case 3200:
			return 3146;

		case 7334:
			return 8172;
		case 7336:
			return 7871;
		case 5228:
			return 5228;

		case 7340:
			return 7879;

		case 7342:
			return 7879;

		case 7344:
			return 8190;

		case 7346:
			return 8048;

		case 7348:
			return 5989;

		case 7350:
			return 7693;

		case 6795:
			return 1010;

		case 10775:
			return 13151;

		case 2037:
			return 5485;

		case 6797:
			return 8104;

		case 6799:
			return 8069;

		case 6801:
			return 7853;

		case 6803:
			return 8159;

		case 6805:
			return 7786;

		case 6807:
			return 8148;

		case 6810:
			return 7970;

		case 6812:
			return 7935;

		case 6814:
			return 7741;

		case 6816:
			return 8288;

		case 6819:
			return 7667;

		case 6821:
			return 7680;

		case 6823:
			return 6376;

		case 6826:
			return 5387;

		case 6828:
			return 8208;

		case 6830:
			return 8292;
		case 6832:
			return 7795;
		case 6834:
			return 8248;
		case 6836:
			return 8275;
		case 6838:
			return 6254;
		case 6856:
			return 4921;
		case 6858:
			return 5327;

		case 6860:
		case 6862:
		case 6864:
			return 7656;

		case 6868:
			return 7896;

		case 6870:
			return 8303;

		case 6872:
			return 7769;

		case 6874:
			return 5782;

		case 6890:
			return 7260;

		case 7330:
			return 8223;

		case 7332:
			return 8032;

		case 7338:
			return 5228;

		case 7352:
			return 8234;

		case 7354:
			return 7755;

		case 7355:
			return 7834;

		case 7358:
			return 7844;

		case 7359:
			return 8183;

		case 7362:
			return 8257;

		case 7364:
		case 7366:
			return 5228;

		case 7368:
		case 7369:

			return 8130;

		case 7371:
			return 8093;

		case 7374:
			return 7994;

		case 7376:
			return 7946;

		case 8349:
			if (npcs[i].attackType == 2)
				return 10917;
			else if (npcs[i].attackType == 1)
				return 10919;
			else if (npcs[i].attackType == 0)
				return 10922;

		case 8350:
			if (npcs[i].attackType == 2)
				return 10917;
			else if (npcs[i].attackType == 1)
				return 10919;
			else if (npcs[i].attackType == 0)
				return 10922;

		case 2745:
			if (npcs[i].attackType == 2)
				return 9300;
			else if (npcs[i].attackType == 1)
				return 9276;
			else if (npcs[i].attackType == 0)
				return 9277;

		case 2636://
			if (npcs[i].attackType == 5) // Thelife
				return 6948;
			else if (npcs[i].attackType == 4)
				return 6326;
			else if (npcs[i].attackType == 3)
				return 6354;
			else if (npcs[i].attackType == 2)
				return 6355;
			else if (npcs[i].attackType == 1)
				return 6326;
			else if (npcs[i].attackType == 0)
				return 6326;

		default:
			return 0x326;
		}
	}

	public static int getHitIcon(int i) {
		switch (npcs[i].npcType) {
		case 6231:
			return 2;
		}
		return 0;
	}

	/**
	 * Npc Follow Player
	 **/
	public static int GetMove(int Place1, int Place2) {
		if ((Place1 - Place2) == 0) {
			return 0;
		} else if ((Place1 - Place2) < 0) {
			return 1;
		} else if ((Place1 - Place2) > 0) {
			return -1;
		}
		return 0;
	}

	public static int getTimer(int type) {
		if (type == 6064)
			return 15000; // 15 sec- dragon
		if (type == 6055)
			return 7000; // 7 sec - baby
		if (type == 6056)
			return 8000; // 8 sec - young impling
		if (type == 6057) // 8 sec - gourment impling
			return 8000;
		if (type == 6058) // 8 sec - earth
			return 8000;
		if (type == 6059) // 8 sec essence
			return 8000;
		if (type == 6061)
			return 9000; // 9 sec - nature imp
		if (type == 6062)
			return 10000; // 10 sec magpie
		if (type == 6063)
			return 12000; // 12 sec ninja
		if (type == 7903)
			return 25000; // 25 sec KINGLY

		return 6000; // default 6
	}

	public static boolean isHunterIMP(int i) {
		if (i >= 6055 && i <= 6064) {
			return true;
		}
		if (i == 7903) {
			return true;
		}
		return false;
	}

	public static void killDungNPC(NPC n) {
		if (n == null)
			return;
		if (n.InDung() || n.inDungBossRoom())
			n.isDead = true;
		n.applyDead = true;
		n.updateRequired = true;
		dontdrop = true;
	}

	public static void killNpc(int Npc) {
		for (int x = 0; x < npcs.length; x++) {
			if (npcs[x] != null && npcs[x].npcType == Npc) {
				npcs[x].isDead = true;
				npcs[x].applyDead = true;
				npcs[x].updateRequired = true;
				npcs[x].dirUpdateRequired = true;
				npcs[x].getNextWalkingDirection();
			}
		}
	}

	public static void killNPC(NPC n, int type, int x, int y) {
		if (n == null)
			return;
		if (n.npcType == type && n.absX == x && n.absY == y) {
			lastImpDeathX = n.absX;
			lastImpDeathX = n.absY;
			n.isDead = true;
			n.applyDead = true;
			n.updateRequired = true;
			dontdrop = true;
			if (lastImpDeathX > 0 && lastImpDeathY > 0) {
				ImpSpawn.spawnNewImp(type, getTimer(type) * 1000,
						lastImpDeathX, lastImpDeathY);
				return;
			}
		}
	}

	public static void mageIcon(int npc) {

	}

	public static void spawnDungNpcs(Client c, int npcType, int x, int y,
			int heightLevel, int WalkingType, int HP, int maxHit, int attack,
			int defence, boolean attackPlayer, boolean headIcon) {
		int slot = -1;
		if (c == null) {
			System.out.println("C Returned null @ NPCHandler.spawnDungNpcs");
			return;
		}
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return;
		}
		npcs[slot] = new NPC(slot, npcType);
		npcs[slot].absX = x;
		npcs[slot].absY = y;
		npcs[slot].makeX = x;
		npcs[slot].makeY = y;
		npcs[slot].heightLevel = heightLevel;
		npcs[slot].walkingType = WalkingType;
		npcs[slot].HP = HP;
		npcs[slot].MaxHP = HP;
		npcs[slot].maxHit = maxHit;
		npcs[slot].attack = attack;
		npcs[slot].defence = defence;
		npcs[slot].spawnedBy = c.getId();
		c.juststarted = false;
		if (headIcon)
			c.getPA().drawHeadicon(1, slot, 0, 0);
		if (attackPlayer) {
			npcs[slot].underAttack = true;
		}
	}

	public static void spawnNewNPC(int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, int attack, int defence,
			boolean needSpawn) {
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return;
		}
		npcs[slot] = new NPC(slot, npcType);
		npcs[slot].absX = x;
		npcs[slot].absY = y;
		npcs[slot].makeX = x;
		npcs[slot].makeY = y;
		npcs[slot].heightLevel = heightLevel;
		npcs[slot].walkingType = WalkingType;
		npcs[slot].HP = HP;
		npcs[slot].MaxHP = HP;
		npcs[slot].maxHit = maxHit;
		npcs[slot].attack = attack;
		npcs[slot].defence = defence;
		npcs[slot].NeedsRespawn = needSpawn;
		npcs[slot] = npcs[slot];
		if (npcs[slot].npcType == 1160) {
			npcs[slot].startAnim(1181);// spawn anim
		}
	}

	/*
	 * public static void deletePCShifters(Client c) { for (int i = 0; i <
	 * maxNPCs; i++) { if (npcs[i] != null) {
	 * 
	 * Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy]; for (NPC n
	 * : NPCHandler.shifter1) { n.isDead = true; } for (NPC k :
	 * NPCHandler.shifter2) { k.isDead = true; } for (NPC l :
	 * NPCHandler.shifter3) { l.isDead = true; } for (NPC o :
	 * NPCHandler.shifter4) { o.isDead = true; } } } }
	 */
	public static void SpawnPCNPCs(Client c, int npcType, int x, int y,
			int heightLevel, int WalkingType, int HP, int maxHit, int attack,
			int defence, boolean attackPlayer, boolean headIcon) {
		int slot = -1;
		if (c == null) {
			System.out.println("C Returned null @ NPCHandler.SpawnPCNPCs");
			return;
		}
		c.hasNotDeleted = true;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return;
		}
		npcs[slot] = new NPC(slot, npcType);
		npcs[slot].absX = x;
		npcs[slot].absY = y;
		npcs[slot].makeX = x;
		npcs[slot].makeY = y;
		npcs[slot].heightLevel = heightLevel;
		npcs[slot].walkingType = WalkingType;
		npcs[slot].HP = HP;
		npcs[slot].MaxHP = HP;
		npcs[slot].maxHit = maxHit;
		npcs[slot].attack = attack;
		npcs[slot].defence = defence;
		npcs[slot].spawnedBy = c.getId();
		if (headIcon)
			c.getPA().drawHeadicon(1, slot, 0, 0);
		if (attackPlayer) {
			npcs[slot].underAttack = true;
		}
	}

	public static void startAnimation(int animId, int i) {
		npcs[i].animNumber = animId;
		npcs[i].animUpdateRequired = true;
		npcs[i].updateRequired = true;
	}

	public static boolean Summon(int Npc, Client c) {
		for (int x = 0; x < npcs.length; x++) {
			if (npcs[x] != null && npcs[x].npcType == Npc) {
				if (npcs[Npc].spawnedBy == c.playerId) {
					return true;
				}
			}
		}
		return false;
	}

	public static void useDart(Client c, NPC n, int type, int x, int y) {
		if (c == null)
			return;
		if (n == null)
			return;
		if (n.npcType == type && n.absX == x && n.absY == y) {
			n.isDead = true;
			n.applyDead = true;
			n.updateRequired = true;
			n.killedBy = c.playerId;
		}
	}

	public boolean runningEv;

	public NPCHandler() {
		for (int i = 0; i < maxNPCs; i++) {
			npcs[i] = null;
		}
		for (int i = 0; i < maxListedNPCs; i++) {
			NpcList[i] = null;
		}
		loadNPCList("Data/cfg/npc.cfg");
		loadAutoSpawn("Data/cfg/spawn-config.cfg");
	}

	public NPCHandler(Client Client) {
	}

	public void appendJailKc(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			int[] Jail = { 132 };
			for (int j : Jail) {
				if (npcs[i].npcType == j) {
					c.monkeyk0ed++;
					c.sendMessage("You now have " + c.monkeyk0ed
							+ " Monkey kills!");
				} else {
					c.sendMessage("Woah man slow down.. you already have 20 monkey kills..");
					break;
				}
			}
		}
	}

	/**
	 * Slayer Experience
	 **/

	public void appendSlayerExperience(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			if (c.taskAmount <= 0 || c.taskAmount == 0 || c.taskAmount == -1
					&& c.slayerTask > 0) {
				appendSlayerRewards(i);
			}
			if (c.slayerTask == npcs[i].npcType) {
				c.taskAmount--;
				if (c.taskAmount <= 0 || c.taskAmount == 0
						|| c.taskAmount == -1 && c.slayerTask > 0) {
					appendSlayerRewards(i);
				}
				c.getPA().addSkillXP2(npcs[i].MaxHP * Config.SLAYER_EXPERIENCE,
						18);
			}
		}
	}

	public void appendSlayerRewards(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {

			if (c.hasGivenSlayReward == false) {
				if (c.taskAmount <= 0 || c.taskAmount == 0
						|| c.taskAmount == -1 && c.slayerTask > 0
						&& c.taskType > -1) {
					if (!c.task1[9]) {
						c.sendMessage("You've completed the task: Complete a Slayer Task!");
						c.task1[9] = true;
						c.TPoints += 1;
						if (c.TPoints == 1) {
							c.sendMessage("You've received a Task Point! You now have "
									+ c.TPoints + " point!");
						} else {
							c.sendMessage("You've received a Task Point! You now have a total of "
									+ c.TPoints + " points!");
						}
						c.achievementInterface("Complete a Slayer Task!");
					}
					c.slayTa += 1;
					if (c.slayTa >= 100 && !c.task3[9]) {
						GabbesAchievements.handleEliteTask(c, 9, 3,
								"Complete 100 Slayer tasks!");
					}
					if (isMediumTask(i)) {
						if (c.taskType == 2 && c.taskAmount == 0
								|| c.taskAmount == -1 || c.taskAmount < 0
								&& c.slayerTask > 0) {
							c.getPA().addSkillXP2(
									(npcs[i].MaxHP * 10)
											* Config.SLAYER_EXPERIENCE, 18);
							c.slayerTask = -1;
							c.taskType = -1;
							c.SaveGame();
							c.taskAmount = -1;
							c.hasGivenSlayReward = true;
							c.sendMessage("You completed your MEDIUM slayer task. Please see a slayer master to get a new one.");
							if (c.slayerTaskStreak == 0) {
								c.slayerTaskStreak += 1;
								c.slayerPoints += 10;
								c.sendMessage("You have gained 10 Slayer Points and one task streak.");
							} else {
								giveSlayerPointsReward(c, 10);
							}

						}
					}

					if (isHardTask(i)) {
						if (c.taskType == 3 && c.taskAmount == 0
								|| c.taskAmount == -1 || c.taskAmount < 0
								&& c.slayerTask > 0) {
							c.getPA().addSkillXP2(
									(npcs[i].MaxHP * 12)
											* Config.SLAYER_EXPERIENCE, 18);
							c.slayerTask = -1;
							c.SaveGame();
							c.taskType = -1;
							c.taskAmount = -1;
							c.hasGivenSlayReward = true;
							c.sendMessage("You completed your HARD slayer task. Please see a slayer master to get a new one.");
							if (c.slayerTaskStreak == 0) {
								c.slayerTaskStreak += 1;
								c.slayerPoints += 14;
								c.sendMessage("You have gained 14 Slayer Points and one task streak.");
							} else {
								giveSlayerPointsReward(c, 14);
							}
						}
					}
					if (isEXTREMETask(i)) {
						if (c.taskType == 4 && c.taskAmount == 0
								|| c.taskAmount == -1 || c.taskAmount < 0
								&& c.slayerTask > 0) {
							c.getPA().addSkillXP2(
									(npcs[i].MaxHP * 14)
											* Config.SLAYER_EXPERIENCE, 18);
							c.slayerTask = -1;
							c.taskType = -1;
							c.SaveGame();
							c.taskAmount = -1;
							c.hasGivenSlayReward = true;
							c.sendMessage("You completed your EXTREME slayer task. Please see a slayer master to get a new one.");
							if (c.slayerTaskStreak == 0) {
								c.slayerTaskStreak += 1;
								c.slayerPoints += 19;
								c.sendMessage("You have gained 19 Slayer Points and one task streak.");
							} else {
								giveSlayerPointsReward(c, 19);
							}
						}
					}
					if (isEasyTask(i)) {
						if (c.taskType == 1 && c.taskAmount == 0
								|| c.taskAmount == -1 || c.taskAmount < 0
								&& c.slayerTask > 0) {
							c.getPA().addSkillXP2(
									(npcs[i].MaxHP * 8)
											* Config.SLAYER_EXPERIENCE, 18);
							c.slayerTask = -1;
							c.taskType = -1;
							c.taskAmount = -1;
							c.hasGivenSlayReward = true;
							c.SaveGame();
							c.sendMessage("You completed your EASY slayer task. Please see a slayer master to get a new one.");
							if (c.slayerTaskStreak == 0) {
								c.slayerTaskStreak += 1;
								c.slayerPoints += 6;
								c.sendMessage("You have gained 6 Slayer Points and one task streak.");
							} else {
								giveSlayerPointsReward(c, 6);
							}
						}
					}
				}
			}
		}
	}

	public void applyDamage(int i) {
		if (npcs[i] != null) {
			if (PlayerHandler.players[npcs[i].oldIndex] == null) {
				return;
			}
			if (npcs[i].isDead)
				return;
			Client c = (Client) PlayerHandler.players[npcs[i].oldIndex];
			if (c == null) {
				System.out.println("C Returned null @ NPCHandler.applyDamage");
				return;
			}
			if (multiAttacks(i)) {
				multiAttackDamage(i);
				return;
			}
			if (c.playerIndex <= 0 && c.npcIndex <= 0)
				if (c.autoRet == 1)
					c.npcIndex = i;
			if (c.attackTimer <= 3 || c.attackTimer == 0 && c.npcIndex == 0
					&& c.oldNpcIndex == 0) {
				c.startAnimation(c.getCombat().getBlockEmote());
			}
			if (c.respawnTimer <= 0) {
				int damage = 0;
				if (npcs[i].attackType == 0) {
					damage = Misc.random(npcs[i].maxHit);
					if (10 + Misc.random(c.getCombat().calculateMeleeDefence()) > Misc
							.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
					}
					if (c.prayerActive[18] || c.curseActive[9]) { // protect
																	// from
																	// melee
						damage = (int) damage / 10;
					}
					if (c.SolProtect >= 1) { // protect from melee
						damage = (int) damage / 2;
					}
					if (c.playerEquipment[Player.playerShield] == 13740) {
						damage = (int) damage * 70 / 100;
					}
					if (c.playerEquipment[Player.playerShield] == 13742) {
						if (Misc.random(4) == 3) {
							damage = (int) damage * 65 / 100;
						}
					}
					if (c.playerLevel[3] - damage < 0) {
						damage = c.playerLevel[3];
					}
				}

				if (npcs[i].attackType == 1) { // range
					damage = Misc.random(npcs[i].maxHit);
					if (10 + Misc.random(c.getCombat().calculateRangeDefence()) > Misc
							.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
					}
					if (c.prayerActive[17] || c.curseActive[8]) { // protect
																	// from
																	// range
						damage = (int) damage / 10;
					}
					if (c.SolProtect >= 1) { // protect from melee
						damage = (int) damage / 2;
					}
					if (c.playerEquipment[Player.playerShield] == 13740) {
						damage = (int) damage * 70 / 100;
					}
					if (c.playerEquipment[Player.playerShield] == 13742) {
						if (Misc.random(4) == 3) {
							damage = (int) damage * 65 / 100;
						}
					}
					if (c.playerLevel[3] - damage < 0) {
						damage = c.playerLevel[3];
					}
				}

				if (npcs[i].attackType == 2) { // magic
					damage = Misc.random(npcs[i].maxHit);
					boolean magicFailed = false;
					if (10 + Misc.random(c.getCombat().mageDef()) > Misc
							.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
						magicFailed = true;
					}
					if (c.prayerActive[16] || c.curseActive[7]) { // protect
																	// from
																	// magic
						if (npcs[i].npcType != 2745) {
							damage = (damage / 2);
						} else {
							damage = 0;
						}
						magicFailed = true;
					}
					if (c.SolProtect >= 1) { // protect from melee
						damage = (int) damage / 2;
					}
					if (c.playerEquipment[Player.playerShield] == 13740) {
						damage = (int) damage * 70 / 100;
					}
					if (c.playerEquipment[Player.playerShield] == 13742) {
						if (Misc.random(4) == 3) {
							damage = (int) damage * 65 / 100;
						}
					}
					if (c.playerLevel[3] - damage < 0) {
						damage = c.playerLevel[3];
					}
					if (damage == 0 && !isRFDNpc2(i)) {
						c.gfx100(85);
					}
					if (npcs[i].endGfx > 0
							&& (!magicFailed || isFightCaveNpc(i))) {
						c.gfx100(npcs[i].endGfx);
					} else {
						// c.gfx100(85);
					}
				}

				if (npcs[i].attackType == 3) { // fire breath
					int anti = c.getPA().antiFire();
					if (anti == 0) {
						damage = Misc.random(30) + 10;
						NPCHandler.npcs[i].updateRequired = true;
						c.doubleHit = false;
						c.getPA().refreshSkill(3);
						if (npcs[i].npcType == 2636 || npcs[i].npcType == 2586
								|| npcs[i].npcType == 2587
								|| npcs[i].npcType == 2588
								|| npcs[i].npcType == 2589) {
						} else {
							c.sendMessage("You are badly burnt by the dragon fire!");
						}
						c.getCombat();
						c.getCombat().addCharge();
					} else if (anti == 1)
						damage = Misc.random(12);

					else if (anti == 2)
						damage = Misc.random(6);

					if (c.playerLevel[3] - damage < 0)
						damage = c.playerLevel[3];
					// c.gfx100(npcs[i].endGfx);

				}
				handleSpecialEffects(c, i, damage);
				if (c.vengOn && damage > 0) {
					c.getCombat().appendVengeanceNPC(i, damage);
				}
				c.logoutDelay = System.currentTimeMillis(); // logout delay
				if (npcs[i].npcType == 8283)
					npcs[i].attackType = npcs[i].setIcon = c.hitIcon = 1;
				if (npcs[i].setIcon > -1) {
					c.CIcon = npcs[i].setIcon;
					npcs[i].setIcon = -1;
				}
				// c.setHitDiff(damage);
				c.handleHitMask(damage, c.playerId);
				c.playerLevel[3] -= damage;
				c.getPA().refreshSkill(3);
				c.updateRequired = true;
				// c.CIcon = npcs[i].setIcon;
				// c.setHitUpdateRequired(true);

			}
		}
	}

	public boolean ArmadylKC(int i) {
		switch (npcs[i].npcType) {
		case 6222:
		case 6223:
		case 6225:
		case 6230:
		case 6239: // Aviansie
		case 6227:
		case 6232:
		case 6229:
		case 6233:
		case 6231:
			return true;

		}

		return false;
	}

	public void attacknpc(int i) {
		if (isHunterIMP(npcs[i].npcType)) {
			System.out.println("returned null");
			return;
		}
		if (npcs[i] != null) {
			if (npcs[i].isDead)
				return;
			if (!npcs[i].inMulti() && npcs[i].underAttackBy > 0) {
				npcs[i].killerId = 0;
				return;
			}

			boolean special = false;// specialCase(c,i);
			if (goodDistance(npcs[i].getX(), npcs[i].getY(),
					NPCHandler.npcs[npcs[i].attacknpc].getX(),
					NPCHandler.npcs[npcs[i].attacknpc].getY(), 1)
					|| special) {
				if (npcs[i].actionTimer <= 0 && npcs[i].isDead == false
						&& NPCHandler.npcs[npcs[i].attacknpc].isDead == false) {
					if (npcs[i].npcType != 6574
							&& !isHunterIMP(npcs[i].npcType)) { // gravestone
						npcs[i].facePlayer(NPCHandler.npcs[npcs[i].attacknpc].npcId);
						NPCHandler.npcs[npcs[i].attacknpc]
								.facePlayer(npcs[i].npcId);
					}
					npcs[i].attackTimer = getNpcDelay(i);
					npcs[i].hitDelayTimer = getHitDelay(i);
					npcs[i].attackType = 0;
					if (special)
						loadSpell2(i);
					else
						loadSpell(i);
					if (npcs[i].attackType == 3)
						npcs[i].hitDelayTimer += 2;
					if (multiAttacks(i)) {
						multiAttackGfx(i, npcs[i].projectileId);
						startAnimation(getAttackEmote(i), i);
						npcs[i].oldIndex = NPCHandler.npcs[npcs[i].attacknpc].npcId;
						return;
					}
					NPCHandler.npcs[npcs[i].attacknpc].handleHitMask(
							Misc.random(npcs[i].maxHit), npcs[i].attacknpc);
					if (NPCHandler.npcs[npcs[i].attacknpc].actionTimer <= 0
							&& npcs[i].isDead == false
							&& NPCHandler.npcs[npcs[i].attacknpc].isDead == false) {
						NPCHandler.npcs[npcs[i].attacknpc].actionTimer = 7;
						NPCHandler.npcs[npcs[i].attacknpc]
								.handleHitMask(
										Misc.random(NPCHandler.npcs[npcs[i].attacknpc].maxHit),
										npcs[i].attacknpc);
						startAnimation(getAttackEmote(npcs[i].attacknpc),
								npcs[i].attacknpc);
					}
					npcs[i].actionTimer = 7;
					npcs[i].npcIndex = NPCHandler.npcs[npcs[i].attacknpc].npcId;
					// npcs[i].facenpc(Server.npcHandler.npcs[npcs[i].attacknpc].npcId);
					// Server.npcHandler.npcs[npcs[i].attacknpc].facenpc(npcs[i].npcId);
					startAnimation(getAttackEmote(i), i);
					NPCHandler.npcs[npcs[i].attacknpc].attacknpc = i;

				}
			}
		}
	}

	public void attackNPC(int c, int i) {
		if (npcs[i] != null) {
			if (npcs[i].isDead)
				return;
			if (!npcs[i].inMulti() && npcs[i].underAttackBy > 0
					&& npcs[i].underAttackBy != npcs[c].npcId) {
				npcs[i].killerId = 0;
				return;
			}
			if (!npcs[i].inMulti()
					&& (npcs[c].underAttackBy > 0 || (npcs[c].underAttackBy2 > 0 && npcs[c].underAttackBy2 != i))) {
				npcs[i].killerId = 0;
				return;
			}
			if (npcs[i].heightLevel != npcs[c].heightLevel) {
				npcs[i].killerId = 0;
				return;
			}
			follownpc(i, c);
			if (npcs[i].npcType != 6574 && !isHunterIMP(npcs[i].npcType)) { // gravestone
				npcs[i].facePlayer(npcs[c].npcId);
				npcs[i].facenpc(npcs[c].npcId);
			}
			boolean special = false;// specialCase(c,i);
			if (goodDistance(npcs[i].getX(), npcs[i].getY(), npcs[c].getX(),
					npcs[c].getY(), distanceRequired(i)) || special) {
				if (npcs[c].actionTimer <= 0) {
					if (npcs[i].npcType != 6574
							&& !isHunterIMP(npcs[i].npcType)) { // gravestone
						npcs[i].facePlayer(npcs[c].npcId);
					}
					npcs[i].attackTimer = getNpcDelay(i);
					npcs[i].hitDelayTimer = getHitDelay(i);
					npcs[i].attackType = 0;

					if (special)
						loadSpell2(i);
					else

						loadSpell(i);

					npcs[c].underAttackBy2 = i;
					npcs[c].actionTimer = 7;
					npcs[i].actionTimer = 5;
					int damg = Misc.random(npcs[i].maxHit);
					npcs[c].handleHitMask(damg, c);
					npcs[c].HP -= damg;
					npcs[c].hitUpdateRequired2 = true;
					npcs[c].hitUpdateRequired = true;

					npcs[i].oldIndexNPC = npcs[c].npcId;
					startAnimation(getAttackEmote(i), i);
					/*
					 * if (Config.SOUND) {
					 * c.sendSound(Sound.getNpcAttackSounds(npcs[i].npcType)); }
					 */
					// c.getPA().removeAllWindows();
				}
			}
		}
	}

	public boolean AttackNPC(int NPCID) {
		if (isHunterIMP(npcs[NPCID].npcType)) {
			return false;
		}
		if (NPCHandler.npcs[npcs[NPCID].attacknpc] != null) {
			int EnemyX = NPCHandler.npcs[npcs[NPCID].attacknpc].absX;
			int EnemyY = NPCHandler.npcs[npcs[NPCID].attacknpc].absY;
			int EnemyHP = NPCHandler.npcs[npcs[NPCID].attacknpc].HP;
			int hitDiff = 0;

			hitDiff = Misc.random(npcs[NPCID].maxHit);
			if (goodDistance(EnemyX, EnemyY, npcs[NPCID].absX,
					npcs[NPCID].absY, 1) == true) {
				if (NPCHandler.npcs[npcs[NPCID].attacknpc].isDead == true) {
					// ResetAttackNPC(NPCID);
					// npcs[NPCID].textUpdate = "Oh yeah I win!";
					// npcs[NPCID].textUpdateRequired = true;
					npcs[NPCID].animNumber = 2103;
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
				} else {
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					if (npcs[NPCID].npcType == 9)
						npcs[NPCID].animNumber = 386;
					if (npcs[NPCID].npcType == 3200)
						npcs[NPCID].animNumber = 0x326; // drags: chaos ele
					// emote ( YESSS )
					if ((npcs[NPCID].npcType == 1605)
							|| (npcs[NPCID].npcType == 1472)) {
						npcs[NPCID].animNumber = 386; // drags: abberant
						// spector death ( YAY )
					}
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
					NPCHandler.npcs[npcs[NPCID].attacknpc].hitDiff = hitDiff;
					NPCHandler.npcs[npcs[NPCID].attacknpc].attacknpc = NPCID;
					NPCHandler.npcs[npcs[NPCID].attacknpc].updateRequired = true;
					NPCHandler.npcs[npcs[NPCID].attacknpc].hitUpdateRequired = true;
					npcs[NPCID].actionTimer = 7;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * NPC Attacking Player
	 **/

	public void attackPlayer(Client c, int i) {
		if (isHunterIMP(npcs[i].npcType)) {
			System.out.println("returned null");
			return;
		}
		if (c == null) {
			System.out.println("C Returned null @ NPCHandler.attackPlayer");
			return;
		}
		if (npcs[i] != null) {
			if (npcs[i].isDead)
				return;
			if (!npcs[i].inMulti() && npcs[i].underAttackBy > 0
					&& npcs[i].underAttackBy != c.playerId) {
				npcs[i].killerId = 0;
				return;
			}

			if (npcs[i].lastX != npcs[i].getX()
					|| npcs[i].lastY != npcs[i].getY()) {
				return;
			}

			if (!npcs[i].inMulti()
					&& (c.underAttackBy > 0 || (c.underAttackBy2 > 0 && c.underAttackBy2 != i))) {
				npcs[i].killerId = 0;
				return;
			}
			if (npcs[i].heightLevel != c.heightLevel) {
				npcs[i].killerId = 0;
				return;
			}
			if (!goodDistance(npcs[i].getX(), npcs[i].getY(), c.getY(),
					c.getX(), 1)
					&& npcs[i].npcType == 8133 && npcs[i].attackType == 0) {
				npcs[i].attackType = 1 + Misc.random(1);
				return;
			}

			if (!goodDistance(npcs[i].getX(), npcs[i].getY(), c.getY(),
					c.getX(), 1)
					&& npcs[i].npcType == 10127 && npcs[i].attackType == 0) {
				npcs[i].attackType = 2;
				return;
			}

			if (!goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
					c.getY(), 1)
					&& npcs[i].npcType == 8349 && npcs[i].attackType == 0) {
				npcs[i].attackType = 1 + Misc.random(1);
				return;
			}
			if (npcs[i].npcType != 6574 && !isHunterIMP(npcs[i].npcType)) { // gravestone
				npcs[i].facePlayer(c.playerId);
			}

			npcs[i].hitIcon = getHitIcon(i); // Gets hit icon :)
			c.hitIcon = getHitIcon(i); // Gets hit icon :)

			boolean special = false;// specialCase(c,i);
			if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
					c.getY(), distanceRequired(i)) || special) {
				if (c.respawnTimer <= 0) {
					if (npcs[i].npcType != 6574
							&& !isHunterIMP(npcs[i].npcType)) { // gravestone
						npcs[i].facePlayer(c.playerId);
					}
					npcs[i].attackTimer = getNpcDelay(i);
					npcs[i].hitDelayTimer = getHitDelay(i);
					npcs[i].attackType = 0;
					if (special)
						loadSpell2(i);
					else
						loadSpell(i);
					if (npcs[i].attackType == 3)
						npcs[i].hitDelayTimer += 2;
					if (multiAttacks(i)) {
						multiAttackGfx(i, npcs[i].projectileId);
						startAnimation(getAttackEmote(i), i);
						npcs[i].oldIndex = c.playerId;
						return;
					}
					if (npcs[i].projectileId > 0) {
						int nX = NPCHandler.npcs[i].getX() + offset(i);
						int nY = NPCHandler.npcs[i].getY() + offset(i);
						int pX = c.getX();
						int pY = c.getY();
						int offX = (nY - pY) * -1;
						int offY = (nX - pX) * -1;
						c.getPA().createPlayersProjectile(nX, nY, offX, offY,
								50, getProjectileSpeed(i),
								npcs[i].projectileId, 43, 31, -c.getId() - 1,
								65);
					}
					c.underAttackBy2 = i;
					c.singleCombatDelay2 = System.currentTimeMillis();
					npcs[i].oldIndex = c.playerId;
					startAnimation(getAttackEmote(i), i);
					c.getPA().removeAllWindows();
				}
			}
		}
	}

	public boolean BandosKC(int i) {
		switch (npcs[i].npcType) {
		case 6260:
		case 6261:
		case 6263:
		case 6265:
		case 6277:
		case 6269:
		case 6270:
		case 3247:
		case 6276:
		case 6272:
		case 6274:
		case 6278:
			return true;

		}

		return false;
	}

	// id of bones dropped by npcs
	public int boneDrop(int type) {
		switch (type) {
		case 1:// normal bones

		case 9:
		case 100:
		case 12:
		case 17:
		case 803:
		case 18:
		case 81:
		case 101:
		case 41:
		case 19:
		case 90:
		case 75:
		case 86:
		case 78:
		case 912:
		case 913:
		case 914:
		case 1648:
		case 1643:
		case 1618:
		case 1624:
		case 181:
		case 119:
		case 49:
		case 26:
		case 1341:

		case 3247:
		case 6233:
		case 6232:
		case 3248:
		case 6212:
		case 6254:
		case 6258:
			return 526;
		case 117:
		case 6270: // Cyclops
		case 6269: // Ice cyclops
		case 4291: // Cyclops
		case 4292: // Ice cyclops
		case 5529:
			return 532;// big bones
		case 50:// drags
		case 53:
		case 54:
		case 55:
		case 941:
		case 1590:
		case 4677:
		case 1591:
		case 1592:
		case 6218:
		case 6272:
		case 6274:
			return 536;
		case 84:
		case 1615:
		case 1613:
		case 82:
		case 3200:
		case 6208:
		case 6206:
		case 6204:
		case 6203:
			return 592;
		case 2881:
		case 2882:
		case 2883:
			return 6729;
		default:
			return -1;
		}
	}

	public void bosses() {
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] == null)
				continue;
		}
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {
				if (npcs[i].npcType == 3067 || npcs[i].npcType == 2779
						|| npcs[i].npcType == 2780) {
					int r = Misc.random(30);
					if (r == 5) {
						if (npcs[i].HP < 250) {
							npcs[i].HP += 35;
							npcs[i].animNumber = 829;
							npcs[i].animUpdateRequired = true;
							npcs[i].updateRequired = true;
							if (Misc.random(10) == 1) {
								npcs[i].forceChat("Rofl someone's gone mad, you think ur gonna win?");
							} else {
								npcs[i].forceChat("Stupid noob, im a legend, run away like a chicken!");
							}
						}
					}
				}
			}
		}
	}

	public void butt(Client c, int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, boolean attackPlayer,
			int attack, int defence) {
		if (c == null) {
			System.out.println("C Returned null @ NPCHandler.Summon");
			return;
		}
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return;
		}
		if (c != null) {
			npcs[slot] = new NPC(slot, npcType);
			npcs[slot].absX = x;
			npcs[slot].absY = y;
			npcs[slot].makeX = x;
			npcs[slot].makeY = y;
			npcs[slot].heightLevel = heightLevel;
			npcs[slot].walkingType = WalkingType;
			npcs[slot].HP = HP;
			npcs[slot].MaxHP = HP;
			npcs[slot].maxHit = maxHit;
			npcs[slot].attack = attack;
			npcs[slot].defence = defence;
			npcs[slot].spawnedBy = c.getId();
			npcs[slot].followPlayer = c.getId();
			npcs[slot].summon = true;
			npcs[slot].npcslot = slot;
			npcs[slot].facePlayer(c.playerId);
			c.followerNpcId = slot;

			c.butler = npcType;

			c.summon = true;
			c.summoningnpcid = slot;
			c.butler = npcType;
			c.summon = true;
		}

	}

	/**
	 * Distanced required to attack
	 **/
	public int distanceRequired(int i) {
		switch (npcs[i].npcType) {
		case 2025:
		case 2028:
			return 6;
		case 53:
		case 742: // maybe this?
		case 54:
		case 55:
		case 941:
		case 1590:
		case 4677:
		case 1591:
		case 1592:
			return 2;
		case 1158:
		case 1160:
			return 8;
		case 2586:
		case 2587:
		case 2588:
		case 10141:
		case 10127:
		case 10110:
		case 9752:
			return 20;
		case 6247:
			return 2;
		case 2881:// dag kings
		case 2882:
		case 3200:// chaos ele
		case 2743:
		case 2631:
		case 2745:
		case 50:
			return 8;
		case 6220:
		case 6276:
		case 6256:
		case 6230:
		case 6239: // Aviansie
		case 6221:
		case 6231:
		case 6257:
		case 6278:
		case 8133:
		case 6233:
		case 6232:
			return 5;
		case 3102:
		case 3103:
			return 2;
		case 8349:
		case 8350:
		case 8351:
			return 1;
		case 2883:// rex
		case 4291: // Cyclops
		case 4292: // Ice cyclops
			return 2;
		case 6263:
		case 6265:
		case 6206:
		case 6208:
		case 6222:
		case 6223:
		case 6225:
		case 6250:
		case 6252:
			return 15;
			// things around dags
		case 2892:
		case 2894:
			return 10;

		case 2636:
			if (npcs[i].attackType == 3) {
				return 3;
			} else {
				return 4;
			}
		default:
			return 1;
		}
	}

	public void dropItems(int i) {
		// if (!npcs[i].inBarbDef() || !npcs[i].InDung()) {
		// long start = System.currentTimeMillis();
		boolean hasGottenClueScroll = false;
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			if (c.inPcGame()) {
				return;
			}
			hasGottenClueScroll = false;
			c.isAttacking = false;
			GabbesAchievements.writeAchievementTab(c);
			c.getPA().writeTabs();
			if (npcs[i].npcType == 912 || npcs[i].npcType == 913
					|| npcs[i].npcType == 914)
				c.magePoints += 1;
			if (NPCDrops.constantDrops.get(npcs[i].npcType) != null) {
				for (int item : NPCDrops.constantDrops.get(npcs[i].npcType)) {
					if (c.getItems().playerHasItem(18337, 1)) {
						/* NORMAL BONES */
						if (item == 526) {
							c.sendMessage("Your Bonecrusher grants you 600 Prayer XP..");
							c.getPA().addSkillXP2(600, 5);
						}
						/* BIG BONES */
						if (item == 532) {
							c.sendMessage("Your Bonecrusher grants you 1800 Prayer XP..");
							c.getPA().addSkillXP2(1800, 5);
						}
						/* Dragon BONES */
						if (item == 536) {
							c.sendMessage("Your Bonecrusher grants you 8640 Prayer XP..");
							c.getPA().addSkillXP2(8640, 5);
						}
						/* BABY DRAGON BONES */
						if (item == 534) {
							c.sendMessage("Your Bonecrusher grants you 3600 Prayer XP..");
							c.getPA().addSkillXP2(3600, 5);
						}
						/* FROST DRAGON BONES */
						if (item == 18830) {
							c.sendMessage("Your Bonecrusher grants you 72000 Prayer XP..");
							c.getPA().addSkillXP2(72000, 5);
						}
					} else {
						Server.itemHandler.spawnNPCDrop(c, item, npcs[i].absX,
								npcs[i].absY, 1, c.playerId);
					}
				}
			}
			if (npcs[i].npcType > 0) {
				int random2 = Misc.random(8);
				int random1 = Misc.random(200);
				int random3 = Misc.random(300);
				int random4 = Misc.random(400);
				// CRABS ACHIEVEMENTS
				if (npcs[i].npcType == 1265 && !c.task2[0]) {
					c.crabsK += 1;
					if (c.crabsK == 40 || c.crabsK == 41) {
						c.task2[0] = true;
						c.sendMessage("You've completed the task: Kill: 40 Rock crabs!");
						c.TPoints += 2;
						c.achievementInterface("Kill 40 Rock crabs!");
						c.sendMessage("You've received two Task Points! You now have a total of "
								+ c.TPoints + " points!");
						c.SaveGame();
					}
				}
				// DARK BEASTS
				if (npcs[i].npcType == 2783 && !c.task2[1]) {
					c.beastK += 1;
					if (c.beastK == 20 || c.beastK == 21) {
						c.task2[1] = true;
						c.sendMessage("You've completed the task: Kill 20 Dark beasts!");
						c.TPoints += 2;
						c.achievementInterface("Kill 20 Dark beasts!");
						c.sendMessage("You've received two Task Points! You now have a total of "
								+ c.TPoints + " points!");
						c.SaveGame();
					}
				}

				if (!c.inRandomEvent()) {
					if (random2 == 4) {
						Server.itemHandler.spawnNPCDrop(c, 12158, npcs[i].absX,
								npcs[i].absY, 1, c.playerId);
					}
					if (random2 == 3) {
						Server.itemHandler.spawnNPCDrop(c, 12159, npcs[i].absX,
								npcs[i].absY, 1, c.playerId);
					}
					if (random2 == 2) {
						Server.itemHandler.spawnNPCDrop(c, 12160, npcs[i].absX,
								npcs[i].absY, 1, c.playerId);
					}
					if (random2 == 0) {
						Server.itemHandler.spawnNPCDrop(c, 12163, npcs[i].absX,
								npcs[i].absY, 1, c.playerId);
					}

					if (random1 == 1 && hasGottenClueScroll == false) {
						Server.itemHandler.spawnNPCDrop(c, 10232, npcs[i].absX,
								npcs[i].absY, 1, c.playerId);
						c.sendMessage("You have found a Clue Scroll!");
						hasGottenClueScroll = true;
						c.SaveGame();
					}
					if (random3 == 1 && hasGottenClueScroll == false) {
						// ropScroll(2, c);
						Server.itemHandler.spawnNPCDrop(c, 13078, npcs[i].absX,
								npcs[i].absY, 1, c.playerId);
						c.sendMessage("You have found a Clue Scroll!");
						hasGottenClueScroll = true;
						c.SaveGame();
					}
					if (random4 == 1 && hasGottenClueScroll == false) {
						// dropScroll(3, c);
						Server.itemHandler.spawnNPCDrop(c, 13040, npcs[i].absX,
								npcs[i].absY, 1, c.playerId);
						c.sendMessage("You have found a Clue Scroll!");
						hasGottenClueScroll = true;
						c.SaveGame();
					}
					if (random4 == 1 && hasGottenClueScroll == false
							&& !c.hasEffigy()) {
						// dropScroll(3, c);
						Server.itemHandler.spawnNPCDrop(c, 18778, npcs[i].absX,
								npcs[i].absY, 1, c.playerId);
						hasGottenClueScroll = true;
						c.SaveGame();
					}
				}
			}
			/*
			 * if ((npcs[i].npcType == 2462 || npcs[i].npcType == 2461 // Random
			 * event || npcs[i].npcType == 2460 || npcs[i].npcType == 2459)) {
			 * if(npcs[i].npcType == RandomEvents.pheasentId) { // Only give the
			 * raw pheasent if they kill correct bird!
			 * if(!c.getItems().playerHasItem(6178, 1) &&
			 * !c.getItems().playerHasItem(2140, 1)) {
			 * Server.itemHandler.createGroundItem(c, 6178, npcs[i].absX,
			 * npcs[i].absY, 1, c.playerId); } } else {
			 * if(RandomEvents.pheasentId != 0) {
			 * c.sendMessage("You've killed the wrong bird."); } } }
			 */
			if ((npcs[i].npcType == 4278 || npcs[i].npcType == 4279
					|| npcs[i].npcType == 4280 || npcs[i].npcType == 4281
					|| npcs[i].npcType == 4282 || npcs[i].npcType == 4283 || npcs[i].npcType == 4284)) {
				c.sendMessage("You gain some tokens.");
				c.spawned = false;
			}
			if (npcs[i].npcType == 4291 || npcs[i].npcType == 4292
					&& c.inCyclops) {
				int random2 = Misc.random(25);
				if (random2 == 1) {
					Server.itemHandler.createGroundItem(c, c.getWarriorsGuild()
							.getCyclopsDrop(c), npcs[i].absX, npcs[i].absY, 1,
							c.playerId);
				}
			}
			if (npcs[i].npcType == 4278) {
				c.getItems().addItem(8851, 5);
			}
			// dragon slayer
			if (npcs[i].npcType == 4677) {
				c.sendMessage("<shad=15695415>You killed the dragon, now go talk to sedridor, at lumbridge's graveyard");
				c.kingQuest = 7;
				c.getPA().startTeleport(3211, 3422, 0, "ancient");
			}
			// end
			/**
			 **** Start of Dungeoneering KillDung! Author Gabbe\Ace
			 **/
			if (npcs[i].npcType == 8597) {
				c.getPA().addSkillXP2(300000, 24);
				c.dungPoints += 16;
				c.sendMessage("You've gained 30k Dungeoneering XP & 16 Tokens for killing the huge monster!");
			}
			if (npcs[i].npcType == 125) {
				c.funPoints += 2;
				c.sendMessage("You've gained 2 FunPoints for killing the monster!");
			}
			if (npcs[i].npcType == 6260) {
				c.bossPoints += 1; //bymodjesse
				c.sendMessage("You've killed the General! You earn 1 Boss Point!");
			}
			if (npcs[i].npcType == 4477) {
				c.bossPoints += 2; //bymodjesse
				c.sendMessage("You've killed Barebones! You earn 2 Boss Points!");
			}
			if (npcs[i].npcType == 8133) {
				c.bossPoints += 3; //bymodjesse
				c.sendMessage("You've killed the mighty Corporal Beast! You earn 3 Boss Points!");
			}
			if (npcs[i].npcType == 5666) {
				c.bossPoints += 1; //bymodjesse
				c.sendMessage("You've killed Barrelchest! You earn 1 Boss Point!");
			}
			if (npcs[i].npcType == 50) {
				c.bossPoints += 1; //bymodjesse
				c.sendMessage("You've killed the King Black Dragon! You earn 1 Boss Point!");
			}
			if (npcs[i].npcType == 6247) {
				c.bossPoints += 1; //bymodjesse
				c.sendMessage("You've killed the god Saradomin!! You earn 1 Boss Points!");
			}
			if (npcs[i].npcType == 6222) {
				c.bossPoints += 1; //bymodjesse
				c.sendMessage("You've killed the god Armadyl!! You earn 1 Boss Points!");
			}
			if (npcs[i].npcType == 3340) {
				c.bossPoints += 1; //bymodjesse
				c.sendMessage("You've killed the giant mole!! You earn 1 Boss Points!");
			}
			if (npcs[i].npcType == 2636) {
				c.bossPoints += 1; //bymodjesse
				c.sendMessage("You've killed the tormented demon!! You earn 1 Boss Points!");
			}
			if (npcs[i].npcType == 2883) {
				c.bossPoints += 2; //bymodjesse
				c.sendMessage("You've killed the Dagannoth Rex! You earn 2 Boss Points!");
			}
			if (npcs[i].npcType == 6363) {
				c.bossPoints += 2; //bymodjesse
				c.sendMessage("You've killed the god Zamorak!! You earn 2 Boss Points!");
			}
			if (npcs[i].npcType == 2882) {
				c.bossPoints += 2; //bymodjesse
				c.sendMessage("You've killed the Dagannoth Prime! You earn 2 Boss Points!");
			}
			if (npcs[i].npcType == 2881) {
				c.bossPoints += 2; //bymodjesse
				c.sendMessage("You've killed the Dagannoth Supreme! You earn 2 Boss Points!");
			}
			if (npcs[i].npcType == 2636) {
				c.bossPoints += 5; //bymodjesse
				c.sendMessage("You've killed the god nex!! You earn 5 Boss Points!");
			}
			if (npcs[i].npcType == 5529) {
				c.funPoints += 3;
				c.sendMessage("You've gained 3 FunPoints for killing the monster!");
			}
			if (npcs[i].npcType == 1265) {
				c.funPoints += 1;
				c.sendMessage("You've gained 1 FunPoints for killing the monster!");
			}
			if (npcs[i].npcType == 10141) {
				c.getPA().addSkillXP2(300000, 24);
				c.dungPoints += 40;
				c.sendMessage("You've gained 300k Dungeoneering XP & 40 Tokens for killing the huge monster!");
			}
			if (npcs[i].npcType == 10110) {
				c.getPA().addSkillXP2(300000, 24);
				c.dungPoints += 40;
				c.sendMessage("You've gained 300k Dungeoneering XP & 40 Tokens for killing the huge monster!");
			}
			if (npcs[i].npcType == 8282) {
				c.slayerPoints += 20;
				c.sendMessage("You've gained 20 Slayer Points for killing the huge monster!");
			}
			if (npcs[i].npcType == 8283) {
				c.slayerPoints += 20;
				c.sendMessage("You've gained 20 Slayer Points for killing the huge monster!");
			}
			if (npcs[i].npcType == 9752) {
				c.getPA().addSkillXP2(100000, 24);
				c.dungPoints += 20;
				c.sendMessage("You've gained 100k Dungeoneering XP & 20 Tokens for killing the huge monster!");
			}
			if (npcs[i].npcType == 10127) {
				c.getPA().addSkillXP2(200000, 24);
				c.dungPoints += 30;
				c.sendMessage("You've gained 200k Dungeoneering XP & 30 Tokens for killing the huge monster!");
			}
			if (npcs[i].npcType == 3068) {
				c.getPA().addSkillXP2(22000, 24);
				c.dungPoints += 14;
				c.sendMessage("You've gained 22k Dungeoneering XP & 14 Tokens for killing the huge monster!");
			}
			if (npcs[i].npcType == 2780) { // 3475 9492
				c.getPA().addSkillXP2(19000, 24);
				c.dungPoints += 13;
				c.sendMessage("You've gained 19K Dungeoneering XP & 13 Tokens for killing the mage!");
			}
			if (npcs[i].npcType == 111) { // 3475 9492
				c.getPA().addSkillXP2(11000, 24);
				c.dungPoints += 7;
				c.sendMessage("You've gained 11K Dungeoneering XP & 7 Tokens for killing the Ice Giant!");
			}
			if (npcs[i].npcType == 1904) { // 3475 9492
				c.sendMessage("You gain another kill..You've now killed "
						+ c.Zammy + ".");
			}
			if (npcs[i].npcType == 1633) { // 3475 9492
				c.sendMessage("You gain another kill..You've now killed "
						+ c.Zammy + ".");
			}
			if (npcs[i].npcType == 1157) { // 3475 9492
				c.sendMessage("You gain another kill..You've now killed "
						+ c.Zammy + ".");
			}
			if (npcs[i].npcType == 1154) { // 3475 9492
				c.sendMessage("You gain another kill..You've now killed "
						+ c.Zammy + ".");
			}
			if (npcs[i].npcType == 1153) { // 3475 9492
				c.sendMessage("You gain another kill..You've now killed "
						+ c.Zammy + ".");
			}
			/**
			 **** END of Dungeoneering KillDung!
			 **/
			if (ArmadylKC(i)) {
				c.Arma += 1;
				// c.getPA().sendFrame126(""+c.Arma+"", 16216);
			}
			if (npcs[i].npcType == 132) {
				appendJailKc(i);
			}
			if (BandosKC(i)) {
				c.Band += 1;
				// c.getPA().sendFrame126(""+c.Bandos+"", 16217);
			}
			if (SaraKC(i)) {
				c.Sara += 1;
				// c.getPA().sendFrame126(""+c.Sara+"", 16218);
			}
			if (ZammyKC(i)) {
				c.Zammy += 1;
				// c.getPA().sendFrame126(""+c.Zammy+"", 16219);
			}
			if (npcs[i].npcType == 3493) {
				c.Agrith = true;
			}
			if (npcs[i].npcType == 3494) {
				c.Flambeed = true;
				c.Agrith = false;
			}
			if (npcs[i].npcType == 3495) {
				c.Karamel = true;
				c.Flambeed = false;
			}
			if (npcs[i].npcType == 3496) {
				c.Dessourt = true;
				c.Karamel = false;
			}
			if (npcs[i].npcType == 3491) {
				c.Culin = true;
				c.Karamel = false;
				finishRFD(i);
			}
			if (npcs[i].npcType == 3663) {
				c.Goblin = true;
			}
			if (npcs[i].npcType == 4279) {
				c.getItems().addItem(8851, 10);
			}
			if (npcs[i].npcType == 4280) {
				c.getItems().addItem(8851, 15);
			}
			if (npcs[i].npcType == 4281) {
				c.getItems().addItem(8851, 20);
			}
			if (npcs[i].npcType == 4282) {
				c.getItems().addItem(8851, 25);
			}
			if (npcs[i].npcType == 4283) {
				c.getItems().addItem(8851, 30);
			}
			if (npcs[i].npcType == 4284) {
				c.getItems().addItem(8851, 40);
			}
		}
		if (c != null) {
			if (NPCDrops.dropRarity.get(npcs[i].npcType) != null) { // Deadlock
																	// fixed By
																	// Gabbe...

				try {
					if (rareDrops(i)) {
						int random = Misc.random(NPCDrops.rareDrops
								.get(npcs[i].npcType).length - 1);
						int item = NPCDrops.rareDrops.get(npcs[i].npcType)[random][0];
						Server.itemHandler
								.spawnNPCDrop(
										c,
										item,
										npcs[i].absX,
										npcs[i].absY,
										NPCDrops.rareDrops.get(npcs[i].npcType)[random][1],
										c.playerId);
						if (npcs[i].npcType == 2636) {
							PlayerHandler.yell(""
									+ Misc.optimizeText(c.playerName)
									+ " just slayed Nex and received "
									+ c.getItems().getItemName(item) + "!");
						}
						if (npcs[i].npcType == 8133) {
							PlayerHandler
									.yell(""
											+ Misc.optimizeText(c.playerName)
											+ " just slayed the Corporeal Beast and received "
											+ c.getItems().getItemName(item)
											+ "!");
						}
					} else {
						int random = Misc.random(NPCDrops.normalDrops
								.get(npcs[i].npcType).length - 1);
						Server.itemHandler
								.spawnNPCDrop(
										c,
										NPCDrops.normalDrops
												.get(npcs[i].npcType)[random][0],
										npcs[i].absX,
										npcs[i].absY,
										NPCDrops.normalDrops
												.get(npcs[i].npcType)[random][1],
										c.playerId);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public boolean failed() {
		if (Misc.random(10) == 0) {
			return true;
		}
		return false;
	}

	public void finishRFD(int i) {
		final Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			c.waveId = -1;
			c.RFDToKill = -1;
			c.RFDKilled = -1;
			c.getPA().movePlayer(3203, 3429, 0);
		}
	}

	public int followDistance(int i) {
		switch (npcs[i].npcType) {
		case 6260:
		case 6261:
		case 6247:
		case 6248:
		case 6223:
		case 6225:
		case 6227:
		case 6203:
		case 6204:
		case 6206:
		case 6208:
		case 6250:
		case 6252:
		case 6263:
		case 8133:
		case 6265:
			return 15;
		case 3247:
		case 6270:
		case 6219:
		case 6255:
		case 6229:
		case 6277:
		case 6233:
		case 6232:
		case 6218:
		case 6269:
		case 3248:
		case 6212:
		case 6220:
		case 6276:
		case 6256:
		case 6230:
		case 6239:
		case 6221:
		case 6231:
		case 6257:
		case 6278:
		case 6272:
		case 6274:
		case 6254:
		case 4291: // Cyclops
		case 4292: // Ice cyclops
		case 6258:
		case 8349:
		case 8350:
		case 8351:
			return 7;
		case 50:
			return 18;
		case 2883:
			return 4;
		case 2881:
		case 2882:
			return 1;

		}
		return 0;

	}

	public void follownpc(int i, int playerId) {
		if (npcs[playerId] == null) {
			return;
		}

		if (!followPlayer(i)) {
			if (npcs[i].npcType != 6574 && !isHunterIMP(npcs[i].npcType)) { // gravestone
				npcs[i].facePlayer(playerId);
				return;
			}
		}

		if (!goodDistance(npcs[i].getX(), npcs[i].getY(),
				npcs[playerId].getX(), npcs[playerId].getY(), 1)
				&& npcs[i].npcType == 10127 && npcs[i].attackType == 0) {
			npcs[i].attackType = 2;
			return;
		}

		npcs[i].randomWalk = false;

		if (goodDistance(npcs[i].getX(), npcs[i].getY(), npcs[playerId].absX,
				npcs[playerId].absY, distanceRequired(i)))
			return;

		if ((npcs[i].spawnedBy > 0)
				|| ((npcs[i].absX < npcs[i].makeX + Config.NPC_FOLLOW_DISTANCE)
						&& (npcs[i].absX > npcs[i].makeX
								- Config.NPC_FOLLOW_DISTANCE)
						&& (npcs[i].absY < npcs[i].makeY
								+ Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absY > npcs[i].makeY
						- Config.NPC_FOLLOW_DISTANCE))) {
			if (npcs[i].heightLevel == npcs[playerId].heightLevel) {
				if (npcs[playerId] != null && npcs[i] != null) {
					if (npcs[playerId].absY < npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX,
								npcs[playerId].absX);
						npcs[i].moveY = GetMove(npcs[i].absY,
								npcs[playerId].absY);
					} else if (npcs[playerId].absY > npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX,
								npcs[playerId].absX);
						npcs[i].moveY = GetMove(npcs[i].absY,
								npcs[playerId].absY);
					} else if (npcs[playerId].absX < npcs[i].absX) {
						npcs[i].moveX = GetMove(npcs[i].absX,
								npcs[playerId].absX);
						npcs[i].moveY = GetMove(npcs[i].absY,
								npcs[playerId].absY);
					} else if (npcs[playerId].absX > npcs[i].absX) {
						npcs[i].moveX = GetMove(npcs[i].absX,
								npcs[playerId].absX);
						npcs[i].moveY = GetMove(npcs[i].absY,
								npcs[playerId].absY);
					} else if (npcs[playerId].absX == npcs[i].absX
							|| npcs[playerId].absY == npcs[i].absY) {
						int o = Misc.random(3);
						switch (o) {
						case 0:
							npcs[i].moveX = GetMove(npcs[i].absX,
									npcs[playerId].absX);
							npcs[i].moveY = GetMove(npcs[i].absY,
									npcs[playerId].absY + 1);
							break;

						case 1:
							npcs[i].moveX = GetMove(npcs[i].absX,
									npcs[playerId].absX);
							npcs[i].moveY = GetMove(npcs[i].absY,
									npcs[playerId].absY - 1);
							break;

						case 2:
							npcs[i].moveX = GetMove(npcs[i].absX,
									npcs[playerId].absX + 1);
							npcs[i].moveY = GetMove(npcs[i].absY,
									npcs[playerId].absY);
							break;

						case 3:
							npcs[i].moveX = GetMove(npcs[i].absX,
									npcs[playerId].absX - 1);
							npcs[i].moveY = GetMove(npcs[i].absY,
									npcs[playerId].absY);
							break;
						}
					}
					if (npcs[i].npcType != 6574
							&& !isHunterIMP(npcs[i].npcType)) { // gravestone
						npcs[i].facePlayer(playerId);
					}
					if (!npcs[i].inDominionTower() && !npcs[i].inDungBossRoom()
							&& !npcs[i].atDemon() && npcs[i].npcType != 3363
							&& !npcs[i].AtCorp()
							&& !isHunterIMP(npcs[i].npcType)) {
						handleClipping(i);
					}
					npcs[i].getNextNPCMovement(i);
					if (npcs[i].npcType != 6574
							&& !isHunterIMP(npcs[i].npcType)) { // gravestone
						npcs[i].facePlayer(playerId);
					}
					npcs[i].updateRequired = true;
				}
			}
		} else {
			npcs[i].facePlayer(0);
			if (npcs[i].npcType != 312 && npcs[i].npcType != 313
					&& npcs[i].npcType != 310 && npcs[i].npcType != 309) {
				npcs[i].randomWalk = true;
				npcs[i].underAttack = false;
			}
		}
	}

	public boolean followPlayer(int i) {
		switch (npcs[i].npcType) {
		case 2892:
		case 2586:
		case 2587:
		case 2588:
		case 2589:
		case 10141:
		case 10110:
		case 10127:
		case 9752:
			return false;
		}
		return true;
	}

	public void followPlayer(int i, int playerId) {
		if (isHunterIMP(npcs[i].npcType)) {
			System.out.println("returned null2");
			return;
		}
		if (PlayerHandler.players[playerId] == null) {
			return;
		}
		if (PlayerHandler.players[playerId].respawnTimer > 0) {
			if (npcs[i].npcType != 6574 && !isHunterIMP(npcs[i].npcType)) { // gravestone
				npcs[i].facePlayer(0);
			}
			npcs[i].randomWalk = true;
			npcs[i].underAttack = false;
			return;
		}

		if (!followPlayer(i)) {
			if (npcs[i].npcType != 6574 && !isHunterIMP(npcs[i].npcType)) { // gravestone
				npcs[i].facePlayer(playerId);
				return;
			}
		}

		if (npcs[i].npcType == 2025) {
			return;
		}

		int playerX = PlayerHandler.players[playerId].absX;
		int playerY = PlayerHandler.players[playerId].absY;
		npcs[i].randomWalk = false;
		if (goodDistance(npcs[i].getX(), npcs[i].getY(), playerX, playerY,
				distanceRequired(i)))
			return;
		if ((npcs[i].spawnedBy > 0)
				|| ((npcs[i].absX < npcs[i].makeX + Config.NPC_FOLLOW_DISTANCE)
						&& (npcs[i].absX > npcs[i].makeX
								- Config.NPC_FOLLOW_DISTANCE)
						&& (npcs[i].absY < npcs[i].makeY
								+ Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absY > npcs[i].makeY
						- Config.NPC_FOLLOW_DISTANCE))) {
			if (npcs[i].heightLevel == PlayerHandler.players[playerId].heightLevel) {
				if (PlayerHandler.players[playerId] != null && npcs[i] != null) {
					if (playerY < npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if (playerY > npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if (playerX < npcs[i].absX) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if (playerX > npcs[i].absX) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if (playerX == npcs[i].absX
							|| playerY == npcs[i].absY) {
						int o = Misc.random(3);
						switch (o) {
						case 0:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY + 1);
							break;

						case 1:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY - 1);
							break;

						case 2:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX + 1);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
							break;

						case 3:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX - 1);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
							break;
						}
					}
					if (npcs[i].npcType != 6574
							&& !isHunterIMP(npcs[i].npcType)) {
						npcs[i].facePlayer(playerId);
					}
					if (!npcs[i].inDominionTower() && !npcs[i].inDungBossRoom()
							&& !npcs[i].atDemon() && npcs[i].npcType != 3363
							&& !npcs[i].AtCorp()
							&& !isHunterIMP(npcs[i].npcType)) {
						handleClipping(i);
					}
					npcs[i].getNextNPCMovement(i);
					if (npcs[i].npcType != 6574
							&& !isHunterIMP(npcs[i].npcType)) {
						npcs[i].facePlayer(playerId);
					}
					npcs[i].updateRequired = true;
				}
			}
		} else {
			if (npcs[i].npcType != 312 && npcs[i].npcType != 313
					&& npcs[i].npcType != 310 && npcs[i].npcType != 309
					&& npcs[i].npcType != 311) {
				if (npcs[i].npcType != 6574 && !isHunterIMP(npcs[i].npcType)) { // gravestone
					npcs[i].facePlayer(0);
				}
				npcs[i].randomWalk = true;
				npcs[i].underAttack = false;
			}
		}
	}

	public void freezeIMP(final Client c, final int i, final int timer, int x,
			int y) {
		if (runningEv) {
			return;
		}
		CycleEventHandler.getSingleton().stopEvents(c, 42); // let's stop the
															// event first.
		CycleEventHandler.getSingleton().addEvent(42, c, new CycleEvent() {
			public void execute(CycleEventContainer e) {
				c.faceUpdate(i);
				if (npcs[i] != null) {
					int pX = c.getX();
					int pY = c.getY();
					int nX = npcs[i].getX();
					int nY = npcs[i].getY();
					int offX = (pY - nY) * -1;
					int offY = (pX - nX) * -1;
					NPC n = NPCHandler.npcs[i];
					if (n != null) {
						if (n.npcType == 7903) {
							c.sendMessage("This powerful imp is immune to spells!");
							e.stop();
							return;
						}
						// if(n.absX == x && n.absY == y) { // dont freeze all
						// npcs n!
						if (isHunterIMP(npcs[i].npcType) && n.npcType != 7903)
							c.startAnimation(710);
						c.gfx0(177);
						c.getPA().createPlayersProjectile(pX, pY, offX, offY,
								50, 78, 178, c.getCombat().getStartHeight(),
								c.getCombat().getEndHeight(), i + 1, 50);
						n.freezeTimer = timer;
						runningEv = true;

						if (!failed()) {
							n.freezeTimer = timer;
							c.sendMessage("Sucessfully snared the imp!");
						} else {
							c.sendMessage("You failed to snare the imp!");
							n.freezeTimer = 0;
						}

						e.stop();
						// }

					}
				}
			}

			@Override
			public void stop() {
				if (npcs[i] != null) {
					NPC n = NPCHandler.npcs[i];
					if (n != null) {
						if (isHunterIMP(npcs[i].npcType))
							n.gfx0(179);
					}
				}
				runningEv = false;
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
			}
		}, 1);
	}

	public int getClosePlayer(int i) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (j == npcs[i].spawnedBy)
					return j;
				if (goodDistance(PlayerHandler.players[j].absX,
						PlayerHandler.players[j].absY, npcs[i].absX,
						npcs[i].absY, 2 + distanceRequired(i)
								+ followDistance(i))
						|| isFightCaveNpc(i) || isRFDNpc(i)) {
					if ((PlayerHandler.players[j].underAttackBy <= 0 && PlayerHandler.players[j].underAttackBy2 <= 0)
							|| PlayerHandler.players[j].inMulti())
						if (PlayerHandler.players[j].heightLevel == npcs[i].heightLevel)
							return j;
				}
			}
		}
		return 0;
	}

	public int getCloseRandomPlayer(int i) {
		ArrayList<Integer> players = new ArrayList<Integer>();
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (goodDistance(PlayerHandler.players[j].absX,
						PlayerHandler.players[j].absY, npcs[i].absX,
						npcs[i].absY, 2 + distanceRequired(i)
								+ followDistance(i))
						|| isFightCaveNpc(i) || isRFDNpc(i)) {
					if ((PlayerHandler.players[j].underAttackBy <= 0 && PlayerHandler.players[j].underAttackBy2 <= 0)
							|| PlayerHandler.players[j].inMulti())
						if (PlayerHandler.players[j].heightLevel == npcs[i].heightLevel)
							players.add(j);
				}
			}
		}
		if (players.size() > 0)
			return players.get(Misc.random(players.size() - 1));
		else
			return 0;
	}

	public int getDeadEmote(int i) {
		switch (npcs[i].npcType) {
		case 8282: // elemental balance beast
		case 8283: // elemental balance beast
			return 10673;
		case 1381: // gano beast
			return 15473;
		case 1374:
			return 15477;

		case 10110:// bulwark
			return 13005;
		case 9780:// npc
			return 13702;
		case 9752:// warzone
			return 13424;
		case 10141:
		case 10128:// ballak
		case 10129:
		case 10130:
		case 10131:
		case 10132:
		case 10133:
		case 10134:
		case 10135:
		case 10136:
		case 10137:
			return 13602;
		case 7133: // Bork
			return 8756;
		case 10127:
			return 13171;
		case 9767:
			return 13702;
		case 2437:
			return 1814;
		case 1813:
			return 1814;
		case 1125:// dad
			return 287;
		case 10040:
			return 10815;
		case 3622:// Zombies!!
			return 5575;
		case 5529:
			return 5784;
		case 3732:
		case 3734:
		case 3736:
		case 3738:
		case 3740:
			return 3903;

		case 5229:
		case 5230:
		case 5231:
		case 5232:
		case 5233:
		case 5234:
		case 5235:
		case 5236:
		case 5237: // Penance ranger
			return 5397;

		case 5213:
		case 5214:
		case 5215:
		case 5216:
		case 5217:
		case 5218:
		case 5219: // Penance fighter

			return 5098;

			// end of barbarien assault

		case 2636:
			return 6951;
		case 10530:
			return 0; // forgotten warrior
		case 8596:
			return 11197;
		case 8597:
			return 11199;
		case 3068:
			return 2987;
		case 6142:
		case 6143:
		case 6144:
		case 6145:
			return 6;
			// START OF Dungeoneering
		case 1622:
			return 1597;
		case 1631:
		case 1632:
			return 1568;
		case 1260:
			return 1563;
		case 4382: // ankou
			return 836;
		case 5247: // penance queen
			return 5412;
			// END OF DUNGEONEERING
		case 8528:
			return 12694;
		case 10775:
			return 13153;
		case 9463:
			return 12793;
		case 5248:
			return 6951;
		case 9467:
			return 12793;
		case 9465:
			return 12793;
		case 8349:
		case 8350:
			return 10924;
		case 111: // ice giant
		case 112:
		case 117:
			return 4668;
		case 93:
			return 5503;
		case 5666:
			return 5898;
		case 110:
			return 4673;
		case 8133: // Corporeal beast
		case 3101: // Melee
		case 3102: // Range
		case 3103: // Mage
			return 10059;
			// GODWARS

		case 3247: // Hobgoblin
			return 6182;

		case 6270: // Cyclops
		case 6269: // Ice cyclops
		case 4291: // Cyclops
		case 4292: // Ice cyclops
			return 4653;

		case 6218: // Gorak
			return 4302;

		case 6212: // Werewolf
			return 6537;

		case 6276: // Spirtual Ranger
		case 6278: // Spirtual Mage
		case 6272: // Ork
		case 6274: // Ork
		case 6277: // Spirtual Warrior bandos
			return 4321;

		case 6230: // Spirtual Ranger
		case 6233: // Aviansie
		case 6239: // Aviansie
		case 6232: // Aviansie
		case 6231: // Spirtual Mage
		case 6229: // Spirtual Warrior arma

		case 6223: // Aviansie
		case 6225: // Spirtual Mage
		case 6227: // Spirtual Warrior arma
			return 6956;

		case 3248: // HellHound
			return 6576;

			// ENDS
			// sara gwd
		case 6247:
			return 6965;
		case 1265:
			return 1314;
		case 103:
			return 5542;
		case 655:
		case 104:
			return 5534;
		case 6248:
			return 6377;
		case 6250:
			return 7016;
		case 3491:
			return 3357;
		case 6222:
			return 6975;
		case 6203:
		case 6204:
		case 6206:
		case 6208:
			return 6946;
		case 3493:
			return 3503;
		case 2565:
			return 7011;
		case 3494:
			return 1752;
		case 3496:
			return 3509;
		case 6252:
			return 7011;
			// bandos gwd
		case 6261:
		case 6263:
		case 6265:
			return 6156;
		case 6260:
			return 7062;
		case 2550:
			return 7062;
		case 2892:
		case 2894:
			return 2865;
		case 1612: // banshee
			return 1524;
		case 2559:
		case 2560:
		case 2561:
			return 6956;
		case 2607:
			return 2607;
		case 2627:
			return 2620;
		case 2630:
			return 2627;
		case 2631:
			return 2630;
		case 2738:
			return 2627;
		case 2741:
			return 2638;
		case 2746:
			return 2638;
		case 2743:
			return 2055;
		case 1540:
			return 2055;
		case 2745:
			return 9279;

		case 3777:
		case 3778:
		case 3779:
		case 3780:
			return -1;

		case 3200:
			return 3147;

		case 2035: // spider
			return 5329;

		case 2033: // rat
			return 4935;

		case 2031: // bloodvel
			return 2073;

		case 101: // goblin
		case 3663:
			return 6190;

		case 81: // cow
			return 5851;

		case 41: // chicken
			return 5389;

		case 1338: // dagannoth
		case 1340:
		case 1342:
			return 1342;

		case 2881:
		case 2882:
		case 2883:
			return 2856;

		case 125: // ice warrior
			return 843;

		case 751:// Zombies!!
			return 6727;

		case 1626:
		case 1627:
		case 1628:
		case 1629:
		case 1630:
			return 1597;

		case 1616: // basilisk
			return 1548;

		case 1653: // hand
			return 1590;

		case 82:// demons
		case 83:
		case 84:
		case 10039:
			return 67;

		case 1605:// abby spec
			return 1508;

		case 51:// baby drags
		case 52:
		case 1589:
		case 3376:
			return 28;

		case 1610:
		case 1611:
			return 1518;

		case 1618:
		case 1619:
			return 1553;

		case 1620:
		case 1621:
			return 1563;

		case 2783:
			return 2733;

		case 1615:
			return 1538;

		case 1624:
			return 1558;

		case 1613:
			return 1530;

		case 1633:
		case 1634:
		case 1635:
		case 1636:
			return 1580;

		case 1648:
		case 1649:
		case 1650:
		case 1651:
		case 1652:
		case 1654:
		case 1655:
		case 1656:
		case 1657:
			return 1590;

		case 100:
		case 102:
			return 6182;

		case 105:
		case 106:
			return 4929;

		case 412:
		case 78:
			return 4917;

		case 122:
		case 123:
			return 167;

		case 58:
		case 59:
		case 60:
		case 61:
		case 62:
		case 63:
		case 64:
		case 134:
			return 5329;

		case 1153:
		case 1154:
		case 1156:
		case 1157:
			return 6230;

		case 118:
		case 119:
			return 102;

		case 50:// drags
		case 53:
		case 742:
		case 54:
		case 55:
		case 941:
		case 4677:
		case 1590:
		case 1591:
		case 1592:
			return 92;

		default:
			return 2304;
		}
	}

	/**
	 * Hit delays
	 **/
	public int getHitDelay(int i) {
		switch (npcs[i].npcType) {
		case 2881:
		case 2882:
		case 3200:
		case 2892:
		case 2894:
		case 6208:
		case 6206:
		case 6203:
			return 3;
		case 1158:
		case 1160:
			if (npcs[i].attackType == 1 || npcs[i].attackType == 2)
				return 3;
			else
				return 2;
		case 2743:
		case 2631:
		case 6222:
		case 6223:
		case 6225:

		case 6239: // Aviansie
		case 6230:
		case 6233:
		case 6232:
		case 6276:
		case 6257:
		case 6221:

			return 3;

		case 2745:
			if (npcs[i].attackType == 1 || npcs[i].attackType == 2) {
				return 5;
			} else {
				return 2;
			}
		case 2025:
			return 4;
		case 2028:
			return 3;
		case 3495:
			return 2;

		case 2636:
			if (npcs[i].attackType == 5) {// 2=2,1=4,0=4,4=4,5=6,3=2
				return 6;
			} else if (npcs[i].attackType == 4) {
				return 4;
			} else if (npcs[i].attackType == 3) {
				return 2;
			} else if (npcs[i].attackType == 2) {
				return 2;
			} else if (npcs[i].attackType == 1) {
				return 4;
			} else if (npcs[i].attackType == 0) {
				return 4;
			}

		default:
			return 2;
		}
	}

	public int getMaxHit(int i) {
		switch (npcs[i].npcType) {
		case 6222:
			if (npcs[i].attackType == 2)
				return 28;
			else
				return 68;
		case 5247: // penancae queen
			return 30;
		case 3663: // Angry fucking gobl
			return 55;
		case 8133:
			if (npcs[i].attackType == 0)
				return 48;
			else if (npcs[i].attackType == 1)
				return 48;
			else if (npcs[i].attackType == 2)
				return 60;
		case 6203:
			if (npcs[i].attackType == 0)
				return 40;
			else
				return 35;
		case 8349:
		case 8350:
		case 8351:
			if (npcs[i].attackType == 0)
				return 20;
			else
				return 27;
		case 6247:
			return 31;
		case 6260:
			return 36;
		}
		return 1;
	}

	/**
	 * Attack delays
	 **/
	public int getNpcDelay(int i) {
		switch (npcs[i].npcType) {
		case 2025:
		case 2028:
			return 7;
		case 1158:
		case 1160:
			return 6;
		case 2586:
		case 2587:
		case 2588:
		case 2589:
			return 9;
		case 8133: // Corporeal beast
		case 3101: // Melee
		case 3102: // Range
		case 3103: // Mage
			return 7;
		case 9947:
		case 3847:
			return 6;
		case 8349:
		case 8350:
		case 8351:
			if (npcs[i].attackType == 2)
				return 4;
			else if (npcs[i].attackType == 1)
				return 6;
			else if (npcs[i].attackType == 0)
				return 7;
		case 3495:
			return 3;
		case 2745:
			return 8;
		case 50:
		case 53:
		case 54:
		case 55:
		case 941:
		case 4677:
		case 1590:
		case 1591:
		case 1592:
			return 8;

		case 6222:
		case 6223:
		case 6206:
		case 6208:
		case 6204:
		case 6225:
		case 6227:
		case 6260:
			return 6;
			// saradomin gw boss
		case 6247:
			return 3;

		case 2636:
			if (npcs[i].attackType == 5)
				return 12;
			else if (npcs[i].attackType == 4)
				return 7;
			else if (npcs[i].attackType == 3)
				return 3;
			else if (npcs[i].attackType == 2)
				return 7;
			else if (npcs[i].attackType == 1)
				return 7;
			else if (npcs[i].attackType == 0)
				return 7;

		default:
			return 5;
		}
	}

	public int getNpcDeleteTime(int i) {
		switch (npcs[i].npcType) {
		case 1265:
		case 90:
		case 1648:
		case 1341:
		case 1851:
		case 1857:
		case 1854:
			return 2;
		case 82:
			return 3;
		case 103:

			return 0;
		case 117:
			return 6;
		default:
			return 4;
		}
	}

	/**
	 * Npc killer id?
	 **/

	public int getNpcKillerId(int npcId) {
		int oldDamage = 0;
		int killerId = 0;
		for (int p = 1; p < Config.MAX_PLAYERS; p++) {
			if (PlayerHandler.players[p] != null) {
				if (PlayerHandler.players[p].lastNpcAttacked == npcId) {
					if (PlayerHandler.players[p].totalDamageDealt > oldDamage) {
						oldDamage = PlayerHandler.players[p].totalDamageDealt;
						killerId = p;
					}
					PlayerHandler.players[p].totalDamageDealt = 0;
				}
			}
		}
		return killerId;
	}

	public int getNpcListHP(int npcId) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == npcId) {
					return NpcList[i].npcHealth;
				}
			}
		}
		return 0;
	}

	public String getNpcListName(int npcId) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == npcId) {
					return NpcList[i].npcName;
				}
			}
		}
		return "nothing";
	}

	public NPC[] getNPCs() {
		return npcs;
	}

	public int getProjectileSpeed(int i) {
		switch (npcs[i].npcType) {
		case 2881:
		case 2882:
		case 3200:
			return 85;

		case 2745:
			return 115;
		case 1158:
		case 1160:
			return 90;

		case 50:
		case 53:
		case 742:
		case 54:
		case 55:
		case 941:
		case 4677:
		case 1590:
		case 1591:
		case 1592:
			return 85;

		case 2025:
			return 85;

		case 3493:
			return 85;

		case 2028:
			return 80;

		default:
			return 85;
		}
	}

	/**
	 * Npc respawn time
	 **/
	public int getRespawnTime(int i) {
		switch (npcs[i].npcType) {
		case 6261:
		case 6263:
		case 6265:
		case 8349:
		case 8350:
		case 8351:
			return 100;
		case 1158:
			return 70;
		case 6203:
		case 6204:
		case 6206:
		case 2881:
		case 2882:
		case 2883:
		case 6222:
		case 6223:
		case 6225:
		case 6227:
		case 6247:
		case 6248:
		case 6250:
		case 6260:
		case 2636:
		case 6208:
		case 2586:
		case 2587:
		case 2588:
		case 2589:
			return 140;
		case 8133: // Corporeal beast
			return 65;
		case 3101: // Melee
		case 3102: // Range
		case 3103: // Mage
			return 80;

		case 3247: // Godwars
		case 6270:
		case 6219:
		case 6255:
		case 6229:
		case 6277:
		case 6233:
		case 6232:
		case 6218:
		case 6269:
		case 3248:
		case 6212:
		case 6220:
		case 6276:
		case 6256:
		case 6239: // Aviansie
		case 6230:
		case 6221:
		case 6231:
		case 6257:
		case 6278:
		case 6272:
		case 6274:
		case 6254:
		case 6258:
			return 180;
		case 50:// drags
		case 53:
		case 54:
		case 55:
		case 941:
		case 4677:
		case 1590:
		case 1591:
		case 4291: // Cyclops
		case 4292: // Ice cyclops
		case 1592:
			return 65;
		case 6142:
		case 6143:
		case 6144:
		case 6145:
		case 3732:
		case 3734:
		case 3736:
		case 3738:
			return 1000;

		case 9947:
		case 3847:
			return 200;
		default:
			return 25;

		}
	}

	public boolean getsPulled(int i) {
		switch (npcs[i].npcType) {
		case 6260:
			if (npcs[i].firstAttacker > 0)
				return false;
			break;
		}
		return true;
	}

	public int getStackedDropAmount(int itemId, int npcId) {
		switch (itemId) {
		case 995:
			switch (npcId) {
			case 1:
				return 50 + Misc.random(50);
			case 9:
				return 133 + Misc.random(100);
			case 1624:
				return 1000 + Misc.random(300);
			case 1618:
				return 1000 + Misc.random(300);
			case 1643:
				return 1000 + Misc.random(300);
			case 1610:
				return 1000 + Misc.random(1000);
			case 1613:
				return 1500 + Misc.random(1250);
			case 1615:
				return 3000;
			case 18:
				return 500;
			case 101:
				return 60;
			case 913:
			case 912:
			case 914:
				return 750 + Misc.random(500);
			case 1612:
				return 250 + Misc.random(500);
			case 1648:
				return 250 + Misc.random(250);
			case 90:
				return 200;
			case 82:
				return 1000 + Misc.random(455);
			case 52:
				return 400 + Misc.random(200);
			case 49:
				return 1500 + Misc.random(2000);
			case 1341:
				return 1500 + Misc.random(500);
			case 26:
				return 500 + Misc.random(100);
			case 20:
				return 750 + Misc.random(100);
			case 21:
				return 890 + Misc.random(125);
			case 117:
				return 500 + Misc.random(250);
			case 2607:
				return 500 + Misc.random(350);
			}
			break;
		case 11212:
			return 10 + Misc.random(4);
		case 565:
		case 561:
			return 10;
		case 560:
		case 563:
		case 562:
			return 15;
		case 555:
		case 554:
		case 556:
		case 557:
			return 20;
		case 892:
			return 40;
		case 886:
			return 100;
		case 6522:
			return 6 + Misc.random(5);

		}

		return 1;
	}

	public void giveSlayerPointsReward(Client c, int amount) {
		int extra = 0;
		if (c != null) {
			if (c.slayerTaskStreak == 1) {
				extra = 2;
				c.slayerPoints += amount + 2;
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("You have gained " + amount + " and " + extra
						+ " bonus Slayer Points.");
				c.slayerTaskStreak += 1;
				c.sendMessage("Current Slayer Tasks streak: "
						+ c.slayerTaskStreak + ".");
				int lol = extra + 1;
				c.sendMessage("If you finish another task, you will receive "
						+ lol + " bonus Slayer points.");
				return;
			}
			if (c.slayerTaskStreak == 2) {
				extra = 3;
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.slayerPoints += amount + 3;
				c.sendMessage("You have gained " + amount + " and " + extra
						+ " bonus Slayer Points.");
				c.slayerTaskStreak += 1;
				c.sendMessage("Current Slayer Tasks streak: "
						+ c.slayerTaskStreak + ".");
				int lol = extra + 1;
				c.sendMessage("If you finish another task, you will receive "
						+ lol + " bonus Slayer points.");
				return;
			}
			if (c.slayerTaskStreak == 3) {
				extra = 4;
				c.slayerPoints += amount + 4;
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("You have gained " + amount + " and " + extra
						+ " bonus Slayer Points.");
				c.slayerTaskStreak += 1;
				c.sendMessage("Current Slayer Tasks streak: "
						+ c.slayerTaskStreak + ".");
				int lol = extra + 1;
				c.sendMessage("If you finish another task, you will receive "
						+ lol + " bonus Slayer points.");
				return;
			}
			if (c.slayerTaskStreak == 4) {
				extra = 5;
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.slayerPoints += amount + 5;
				c.sendMessage("You have gained " + amount + " and " + extra
						+ " bonus Slayer Points.");
				c.slayerTaskStreak += 1;
				c.sendMessage("Current Slayer Tasks streak: "
						+ c.slayerTaskStreak + ".");
				int lol = extra + 1;
				c.sendMessage("If you finish another task, you will receive "
						+ lol + " bonus Slayer points.");
				if (!c.task2[9]) {
					c.sendMessage("You've completed the task: Achieve 5 Slayer task streak!");
					c.task2[9] = true;
					c.TPoints += 2;
					c.sendMessage("You've received two Task Points! You now have "
							+ c.TPoints + " points!");
					c.achievementInterface("Achieve 5 Slayer task streak!");
				}
				return;
			}
			if (c.slayerTaskStreak == 5) {
				extra = 6;
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.sendMessage("");
				c.slayerPoints += amount + 6;
				c.sendMessage("You have gained " + amount + " and " + extra
						+ " bonus Slayer Points.");
				c.sendMessage("You reached maximum Slayer Task Streak. Your new Task Streak is 0.");
				c.slayerTaskStreak = 0;
				return;
			}
		}
	}

	public boolean goodDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		return ((objectX - playerX <= distance && objectX - playerX >= -distance) && (objectY
				- playerY <= distance && objectY - playerY >= -distance));
	}

	public void handleBandosDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			if (c.inFightCaves()) {
				c.getPA().movePlayer(2401, 5086, c.playerId * 4);
				c.sendMessage("You're teleported back into the middle..");
				Server.hardCaves
						.spawnNextWave((Client) PlayerHandler.players[c.playerId]);
			} else {
				if (!c.inFightCaves()) {
					return;
				}
			}
		}
	}

	public void handleChaosEleDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			if (c.inFightCaves()) {
				c.getPA().movePlayer(2401, 5086, c.playerId * 4);
				c.sendMessage("You're teleported back into the middle..");
				Server.hardCaves
						.spawnNextWave((Client) PlayerHandler.players[c.playerId]);
			} else {
				if (c.InDung()) {
					return;
				} else {
					if (!c.InDung() && !c.inFightCaves()) {
						return;
					}
				}
			}
		}
	}

	public void handleClipping(int i) {
		NPC npc = npcs[i];
		if (npc.moveX == 1 && npc.moveY == 1 && npc != null) {
			if ((Region
					.getClipping(npc.absX + 1, npc.absY + 1, npc.heightLevel) & 0x12801e0) != 0) {
				npc.moveX = 0;
				npc.moveY = 0;
				if ((Region
						.getClipping(npc.absX, npc.absY + 1, npc.heightLevel) & 0x1280120) == 0)
					npc.moveY = 1;
				else
					npc.moveX = 1;
			}
		} else if (npc.moveX == -1 && npc.moveY == -1 && npc != null) {
			if ((Region
					.getClipping(npc.absX - 1, npc.absY - 1, npc.heightLevel) & 0x128010e) != 0) {
				npc.moveX = 0;
				npc.moveY = 0;
				if ((Region
						.getClipping(npc.absX, npc.absY - 1, npc.heightLevel) & 0x1280102) == 0)
					npc.moveY = -1;
				else
					npc.moveX = -1;
			}
		} else if (npc.moveX == 1 && npc.moveY == -1 && npc != null) {
			if ((Region
					.getClipping(npc.absX + 1, npc.absY - 1, npc.heightLevel) & 0x1280183) != 0) {
				npc.moveX = 0;
				npc.moveY = 0;
				if ((Region
						.getClipping(npc.absX, npc.absY - 1, npc.heightLevel) & 0x1280102) == 0)
					npc.moveY = -1;
				else
					npc.moveX = 1;
			}
		} else if (npc.moveX == -1 && npc.moveY == 1 && npc != null) {
			if ((Region
					.getClipping(npc.absX - 1, npc.absY + 1, npc.heightLevel) & 0x128013) != 0) {
				npc.moveX = 0;
				npc.moveY = 0;
				if ((Region
						.getClipping(npc.absX, npc.absY + 1, npc.heightLevel) & 0x1280120) == 0)
					npc.moveY = 1;
				else
					npc.moveX = -1;
			}
		} // Checking Diagonal movement.

		if (npc.moveY == -1 && npc != null) {
			if ((Region.getClipping(npc.absX, npc.absY - 1, npc.heightLevel) & 0x1280102) != 0)
				npc.moveY = 0;
		} else if (npc.moveY == 1 && npc != null) {
			if ((Region.getClipping(npc.absX, npc.absY + 1, npc.heightLevel) & 0x1280120) != 0)
				npc.moveY = 0;
		} // Checking Y movement.
		if (npc.moveX == 1 && npc != null) {
			if ((Region.getClipping(npc.absX + 1, npc.absY, npc.heightLevel) & 0x1280180) != 0)
				npc.moveX = 0;
		} else if (npc.moveX == -1 && npc != null) {
			if ((Region.getClipping(npc.absX - 1, npc.absY, npc.heightLevel) & 0x1280108) != 0)
				npc.moveX = 0;
		} // Checking X movement.
	}

	public void handleCorpDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			if (c.inFightCaves()) {
				c.getPA().handleFightCavesWinNewOne();
				return;
			} /*
			 * else { if (!c.inFightCaves() && c.AtCorp() && c.clickedGate) {
			 * //Do nothing atm } }
			 */
		}
	}

	public void handleDadDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.inDominionTower()) {
				c.getPA().startTeleport(3223, 3221, 0, "modern");
				c.sendMessage("You've killed an easy classed NPC and been rewarded with 5 points.");
				c.dominionPoints += 5;
				DominionTowerByGabbe.handlePointsReward(c);

			}
		}
	}

	public void handleDungeoneeringDrops(int i) {
		// Client k = (Client) PlayerHandler.players[npcs[i].killedBy];
		Client k = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (dontdrop) {
			return;
		}
		if (k != null) {
			if (npcs[i] != null) {
				if (npcs[i].npcType == 3622 && k.bossing
						&& npcs[i].inDungBossRoom()) {
					k.npcsKilled += 1;
					return;
				}
				if (npcs[i].npcType == 7133 && k.bossing) { // bork
					k.getDungeoneering();
					Dungeoneering.spawnBossesV2(k);
					Server.itemHandler.createGroundItem(k, 18169,
							npcs[i].getX(), npcs[i].getY(), 2, k.getId());
					k.npcsKilled += 1;
					k.bossing = true;
					return;
				} else if (npcs[i].npcType == 10127 && k.bossing) {
					k.sendMessage("You've completed the Dungeon. You can leave by using the ladder.");
					killAllDungNPCs(k);
					k.npcsKilled += 1;
					k.bossing = false;
					k.completed = true;
					Server.itemHandler.createGroundItem(k, dungBossDrop(),
							npcs[i].getX(), npcs[i].getY(), 1, k.getId());
					return;
				}

				if (npcs[i].npcType == 3622 || npcs[i].npcType == 439
						|| npcs[i].npcType == 10530 || npcs[i].npcType == 3200
						|| npcs[i].npcType == 4477 || npcs[i].npcType == 3067
						|| npcs[i].npcType == 10040 || npcs[i].npcType == 2881
						|| npcs[i].npcType == 4382 || npcs[i].npcType == 6212
						&& k.InDung) {
					k.npcsKilled += 1;
					k.sendMessage("Your killcount has increased to "
							+ k.npcsKilled + ".");

					if (k.npcsKilled == 3) {
						k.sendMessage("Some gates in the room have been unlocked.");
					}
					int random = Misc.random(50);
					// COINS DROPS
					if (random >= 0 && random < 20) {
						Server.itemHandler
								.createGroundItem(k, 18201, npcs[i].getX(),
										npcs[i].getY(), 2000, k.getId());
					} else if (random >= 20 && random < 30) {
						Server.itemHandler
								.createGroundItem(k, 18201, npcs[i].getX(),
										npcs[i].getY(), 5000, k.getId());
					} else if (random >= 31 && random < 35) {
						Server.itemHandler.createGroundItem(k, 18201,
								npcs[i].getX(), npcs[i].getY(), 10000,
								k.getId());
					} else if (random >= 40 && random < 50) {
						Server.itemHandler.createGroundItem(k, 18201,
								npcs[i].getX(), npcs[i].getY(), 700, k.getId());
					}
					// ITEMS DROP
					int random4 = Misc.random(200);
					if (random4 > 100 && random4 < 150) {
						Server.itemHandler.createGroundItem(k, 19892,
								npcs[i].getX(), npcs[i].getY(), 1, k.getId());
					}
					if (random4 > 159 && random4 < 180) {
						Server.itemHandler.createGroundItem(k, 19893,
								npcs[i].getX(), npcs[i].getY(), 1, k.getId());
					}
					if (random4 > 50 && random4 < 70) {
						Server.itemHandler.createGroundItem(k, 16432,
								npcs[i].getX(), npcs[i].getY(), 55, k.getId());
					} else if (random4 > 64 && random4 < 80) {
						Server.itemHandler.createGroundItem(k, 16462,
								npcs[i].getX(), npcs[i].getY(), 55, k.getId());
					}
					if (random4 > 10 && random4 < 30) {
						Server.itemHandler.createGroundItem(k, 16341,
								npcs[i].getX(), npcs[i].getY(), 1, k.getId());
					}
					if (random4 >= 30 && random4 < 35) {
						Server.itemHandler.createGroundItem(k, 16343,
								npcs[i].getX(), npcs[i].getY(), 1, k.getId());
					}
					if (random4 >= 40 && random4 < 50) {
						Server.itemHandler.createGroundItem(k, 16285,
								npcs[i].getX(), npcs[i].getY(), 1, k.getId());
					}
					// FOOD > 18169
					int random2 = Misc.random(50);
					if (random2 >= 0 && random2 < 15) {
						Server.itemHandler.createGroundItem(k, 18169,
								npcs[i].getX(), npcs[i].getY(), 1, k.getId());
					} else if (random2 >= 15 && random2 < 20) {
						Server.itemHandler.createGroundItem(k, 18169,
								npcs[i].getX(), npcs[i].getY(), 1, k.getId());
					} else if (random2 >= 20 && random2 < 30) {
						Server.itemHandler.createGroundItem(k, 18169,
								npcs[i].getX(), npcs[i].getY(), 1, k.getId());
					}
					if (!k.door4 && !k.getItems().playerHasItem(18252, 1)
							&& !k.fullDung && k.dungeonId == 1) {
						if (k.npcsKilled >= 5 && Misc.random(3) == 1) {
							Server.itemHandler.createGroundItem(k, 18252,
									npcs[i].getX(), npcs[i].getY(), 1,
									k.getId());
							k.sendMessage("The guardian dropped a key.");
						}
					}
				}
			}
		}
	}

	public void handleGoblinDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.inFightCaves()) {
				c.getPA().movePlayer(2401, 5086, c.playerId * 4);
				c.sendMessage("You're teleported back into the middle..");
				Server.hardCaves
						.spawnNextWave((Client) PlayerHandler.players[c.playerId]);
			} else {
				if (!c.atJungleDemon()) {
					c.mageBankMinigameDone = true;
					c.doingMageBankMinigame = false;
					c.getPA().movePlayer(2538, 4716, 0);
					c.getDH().sendDialogues(1414, 1);
				}
			}
		}
	}

	public void handleJadDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.getItems().freeSlots() > 0) {
				c.getItems().addItem(6570, 1);
			} else {
				Server.itemHandler.createGroundItem(c, 6570, c.absX, c.absY, 1,
						c.playerId);
			}
			c.Jadskls += 1;
			if (c.Jadskls == 10) {
				c.sendMessage("You've completed achievement: Kill Jad 10 times!");
				c.sendMessage("You've been awarded 5 spin tokens!");
				c.spinsLe += 5;
			}
			c.sendMessage("Congratulations on completing the fight caves minigame!");
			c.getPA().resetTzhaar();
			c.waveId = -1;
		}
	}

	public void handleJungleDemonDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			if (c.atDemon()) {
				c.getPA().addSkillXP2(150000, 24);
				c.dungPoints += 80;
				c.sendMessage("You've been given 150K Dungeoneering XP and 80 Tokens!");
				c.getPA().startTeleport(2411, 3530, 0, "modern");
				return;
			} else {
				if (!c.atDemon()) {
					return;
				}
			}
		}
	}

	public void handleKbdDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			if (c.inFightCaves()) {
				c.getPA().movePlayer(2401, 5086, c.playerId * 4);
				c.sendMessage("You're teleported back into the middle..");
				Server.hardCaves
						.spawnNextWave((Client) PlayerHandler.players[c.playerId]);
			} else {
				if (!c.inFightCaves()) {
					return;
				}
			}
		}
	}

	public void handleKendalDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.inDominionTower()) {
				c.getPA().startTeleport(3223, 3221, 0, "modern");
				c.sendMessage("You've killed an easy classed NPC and been rewarded with 7 points.");
				c.dominionPoints += 7;
				DominionTowerByGabbe.handlePointsReward(c);

			}
		}
	}

	public void handleMadManDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.inDominionTower()) {
				c.getPA().startTeleport(3223, 3221, 0, "modern");
				c.sendMessage("You've killed an easy classed NPC and been rewarded with 7 points.");
				c.dominionPoints += 7;
				DominionTowerByGabbe.handlePointsReward(c);

			}
		}
	}

	public void handleNexDeath(int i, int reward) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			// int reward = nexRewards[Misc.random(32)];
			// Server.itemHandler.createGroundItem(c, reward, npcs[i].absX,
			// npcs[i].absY, 1, c.playerId);

			// if (isRareItem(reward)) {
			PlayerHandler.yell("" + Misc.optimizeText(c.playerName)
					+ " just slayed Nex and received "
					+ c.getItems().getItemName(reward) + "!");

			// }
		}
	}

	public void handleNomadDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.inFightCaves()) {
				c.getPA().movePlayer(2401, 5086, c.playerId * 4);
				c.sendMessage("You're teleported back into the middle..");
				Server.hardCaves
						.spawnNextWave((Client) PlayerHandler.players[c.playerId]);
			} else {
				c.Nomad = true;
				c.nomadSk += 1;
				if (c.nomadSk == 5) {
					c.sendMessage("You've completed achievement: Kill 5 nomads!");
					c.sendMessage("You've been awarded 3 spin tokens!");
					c.spinsLe += 3;
					c.getPA().writeTabs();
				}
				c.getPA().movePlayer(3211, 3422, 0);
				c.getItems().addItem(15432, 1);
				c.getItems().addItem(15435, 1);
				c.sendMessage("Congratulations on completing the Nomad minigame!");
				c.sendMessage("Enjoy your new sexy capes!");
			}
		}
	}

	public void handleNPCDeaths(int i) { // disabled it fucked up things
		/*
		 * if (npcs[i].npcType == 2745) handleJadDeath(i); if (npcs[i].npcType
		 * == 1125) handleDadDeath(i); if (npcs[i].npcType == 2743)
		 * handleTokDeath(i); if (npcs[i].npcType == 753) handleMadManDeath(i);
		 * if (npcs[i].npcType == 1813) handleKendalDeath(i); if
		 * (npcs[i].npcType == 2083) handleSigmundDeath(i); if (npcs[i].npcType
		 * == 1540) handleTruesDeath(i); if (npcs[i].npcType == 655)
		 * handleSpiritDeath(i); if (npcs[i].npcType == 8528)
		 * handleNomadDeath(i); if (npcs[i].npcType == 6260)
		 * handleBandosDeath(i); if (npcs[i].npcType == 1472)
		 * handleJungleDemonDeath(i); if (npcs[i].npcType == 50)
		 * handleKbdDeath(i); if (npcs[i].npcType == 3200)
		 * handleChaosEleDeath(i); if (npcs[i].npcType == 8133)
		 * handleCorpDeath(i); if (npcs[i].npcType == 3663)
		 * handleGoblinDeath(i);
		 */
	}

	public void handleRFDDeath(int i) {
		final Client c2 = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c2 != null) {
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (c2 != null) {
						Server.rfd.spawnNextWave(c2);
					}
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 20);
		}
	}

	public void handleSigmundDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.inDominionTower()) {
				c.getPA().startTeleport(3223, 3221, 0, "modern");
				c.sendMessage("You've killed an easy classed NPC and been rewarded with 6 points.");
				c.dominionPoints += 6;
				DominionTowerByGabbe.handlePointsReward(c);

			}
		}
	}

	public void handleSpecialEffects(Client c, int i, int damage) {
		if (npcs[i].npcType == 2892 || npcs[i].npcType == 2894) {
			if (damage > 0) {
				if (c != null) {
					if (c.playerLevel[5] > 0) {
						c.playerLevel[5]--;
						c.getPA().refreshSkill(5);

					}
				}
			}
		}

	}

	public void handleSpiritDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.inDominionTower()) {
				c.getPA().startTeleport(3223, 3221, 0, "modern");
				c.sendMessage("You've killed an easy classed NPC and been rewarded with 10 points.");
				c.dominionPoints += 10;
				DominionTowerByGabbe.handlePointsReward(c);

			}
		}
	}

	public void handleTokDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.inDominionTower()) {
				c.getPA().startTeleport(3223, 3221, 0, "modern");
				c.sendMessage("You've killed an easy classed NPC and been rewarded with 9 points.");
				c.dominionPoints += 9;
				DominionTowerByGabbe.handlePointsReward(c);

			}
		}
	}

	public void handleTruesDeath(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c != null) {
			if (c.inDominionTower()) {
				c.getPA().startTeleport(3223, 3221, 0, "modern");
				c.sendMessage("You've killed an easy classed NPC and been rewarded with 13 points.");
				c.dominionPoints += 13;
				DominionTowerByGabbe.handlePointsReward(c);

			}
		}
	}

	public boolean isAggressive(int i) {
		switch (npcs[i].npcType) {
		case 7133:
		case 10127:
		case 10110:// bulwark
		case 10141:// bulwark
		case 9780:// bulwark
		case 9752:// bulwark
		case 2026:
		case 3200:
		case 1472:
		case 10040:
		case 913:
		case 912:
		case 914:
		case 3067:
		case 3622:
		case 4382:
		case 8528:
		case 3663:
		case 751:
		case 6212:
		case 4477:
		case 2636:
		case 8283:
		case 8282:
		case 6260:
		case 53:
		case 2589:
		case 2588:
		case 2587:
		case 2586:
		case 10775:
		case 3732:
		case 3734:
		case 3736:
		case 3738:
		case 3740:
		case 742:
		case 4677:
		case 6261:
		case 6263:
		case 6265:
		case 6222:
		case 6223:
		case 6225:
		case 6227:
		case 6998:
		case 6692:
		case 6247:
		case 6248:
		case 6250:
		case 6252:
		case 6713:
		case 1156:
		case 795:

		case 8133:
		case 3101:
		case 3102:
		case 5666:
		case 3103:

		case 2892:
		case 2894:
		case 2881:
		case 50:
		case 2882:
		case 2883:

		case 6203:
		case 6206:
		case 6208:
		case 6204:
		case 9947:
		case 3847:
			return true;
		}
		if (npcs[i].inWild() && npcs[i].MaxHP > 0 && npcs[i].npcType != 941)
			return false;

		if (isBarbNpc(i))
			return true;
		if (isFightCaveNpc(i))
			return true;
		if (isRFDNpc(i))
			return true;
		return false;
	}

	public boolean isBarbNpc(int i) {
		return Server.barbDefence.killableNpcs(i);
	}

	public boolean isEasyTask(int i) {
		switch (npcs[i].npcType) {
		case 117:
		case 1265:
		case 103:
		case 78:
		case 119:
		case 181:
			return true;
		}
		return false;
	}

	public boolean isEXTREMETask(int i) {
		switch (npcs[i].npcType) {
		case 9467:
		case 9465:
		case 9463:
		case 6260:
		case 8349:
		case 50:
		case 1158:
		case 1160:
		case 10530:
		case 5666:
			return true;
		}
		return false;
	}

	public boolean isFightCaveNpc(int i) {
		switch (npcs[i].npcType) {
		case 2627:
		case 2630:
		case 2631:
		case 2741:
		case 2743:
		case 2745:
			return true;
		}
		return false;
	}

	public boolean isHardTask(int i) {
		switch (npcs[i].npcType) {
		case 50:
		case 1624:
		case 1610:
		case 1613:
		case 1615:
		case 55:
		case 84:
		case 49:
		case 941:
		case 2783:
		case 1158:
			return true;
		}
		return false;
	}

	public boolean isMediumTask(int i) {
		switch (npcs[i].npcType) {
		case 1643:
		case 1618:
		case 941:
		case 119:
		case 82:
		case 52:
		case 125:
		case 1341:
		case 53:
			return true;
		}
		return false;
	}

	public boolean isRareItem(int i) {
		// First corp drops
		if (i == 13958 || i == 15509 || i == 19669 || i == 13344 || i == 13748
				|| i == 13750 || i == 13752 || i == 13734 || i == 13736
				|| i == 19368 || i == 19370 || i == 13884 || i == 13979
				|| i == 13982) {
			return true;
		}
		// Now nex drops
		if (i == 13360 || i == 13750 || i == 13752 || i == 18377 || i == 13736
				|| i == 19368 || i == 19370 || i == 13884 || i == 13979
				|| i == 13982) {
			return true;
		}
		return false;
	}

	public boolean isRFDNpc(int i) {
		switch (npcs[i].npcType) {
		case 3493:
		case 3494:
		case 3495:
		case 3496:
		case 3491:
			return true;
		}
		return false;
	}

	public boolean isRFDNpc2(int i) {
		switch (npcs[i].npcType) {
		case 3495:
			return true;
		}
		return false;
	}

	public boolean isSummonNPC(int npcType) {
		switch (npcType) {
		case 12007:
		case 12009:
		case 12011:
		case 12013:
		case 12015:
		case 12017:
		case 12019:
		case 12021:
		case 12023:
		case 12025:
		case 12027:
		case 12029:
		case 12031:
		case 12033:
		case 12035:
		case 12037:
		case 12039:
		case 12041:
		case 12043:
		case 12045:
		case 12047:
		case 12049:
		case 12051:
		case 12053:
		case 12055:
		case 12057:
		case 12059:
		case 12061:
		case 12063:
		case 12065:
		case 12067:
		case 12069:
		case 12071:
		case 12073:
		case 12075:
		case 12077:
		case 12079:
		case 12081:
		case 12083:
		case 12085:
		case 12087:
		case 12089:
		case 12091:
		case 12093:
		case 12123:
		case 12776:
		case 12778:
		case 12780:
		case 12782:
		case 12784:
		case 12786:
		case 12788:
		case 12790:
		case 12792:
		case 12794:
		case 12796:
		case 12798:
		case 6830: // fucking wolf
		case 12800:
		case 12802:
		case 12804:
		case 12806:
		case 12808:
		case 12810:
		case 12812:
		case 12814:
		case 12816:
		case 12818:
		case 12820:
			return true;
		}
		return false;
	}

	public void killAllDungNPCs(Client c) {
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {
				NPC n = NPCHandler.npcs[i];
				if (n != null) {

					if (n.inDungBossRoom() && n.spawnedBy == c.getId()
							|| n.InDung() && n.spawnedBy == c.getId())
						killDungNPC(n);

				}
			}
		}
	}

	private void killedBarb(int i) {
		final Client c2 = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c2 != null) {
			c2.barbsKilled++;
			if (c2.barbsKilled == c2.barbsToKill) {
				c2.barbWave++;
				if (c2 != null) {
					CycleEventHandler.getSingleton().addEvent(this,
							new CycleEvent() {
								@Override
								public void execute(
										CycleEventContainer container) {
									if (c2 != null) {
										Server.barbDefence.spawnWave(c2);
										c2.sendMessage("Starting next wave.");
									}
									container.stop();
								}

								@Override
								public void stop() {

								}
							}, 20);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void killedBarrow(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killedBy];
		if (c != null) {
			for (int o = 0; o < c.barrowsNpcs.length; o++) {
				if (npcs[i].npcType == c.barrowsNpcs[o][0]) {
					c.barrowsNpcs[o][1] = 2; // 2 for dead
					c.barrowsKillCount++;

				}
			}
		}
	}

	private void killedTzhaar(int i) {
		final Client c2 = (Client) PlayerHandler.players[npcs[i].spawnedBy];
		if (c2 != null) {
			c2.tzhaarKilled++;
			if (c2.tzhaarKilled == c2.tzhaarToKill) {
				c2.waveId++;
				if (c2 != null) {
					CycleEventHandler.getSingleton().addEvent(this,
							new CycleEvent() {
								@Override
								public void execute(
										CycleEventContainer container) {
									if (c2 != null) {
										Server.fightCaves.spawnNextWave(c2);
									}
									container.stop();
								}

								@Override
								public void stop() {

								}
							}, 20);
				}
			}
		}
	}

	@SuppressWarnings("resource")
	public boolean loadAutoSpawn(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		} catch (FileNotFoundException fileex) {
			Misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			Misc.println(FileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("spawn")) {
					newNPC(Integer.parseInt(token3[0]),
							Integer.parseInt(token3[1]),
							Integer.parseInt(token3[2]),
							Integer.parseInt(token3[3]),
							Integer.parseInt(token3[4]),
							getNpcListHP(Integer.parseInt(token3[0])),
							Integer.parseInt(token3[5]),
							Integer.parseInt(token3[6]),
							Integer.parseInt(token3[7]));

				}
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return true;
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
		return false;
	}

	@SuppressWarnings("resource")
	public boolean loadNPCList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		} catch (FileNotFoundException fileex) {
			Misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			Misc.println(FileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("npc")) {
					newNPCList(Integer.parseInt(token3[0]), token3[1],
							Integer.parseInt(token3[2]),
							Integer.parseInt(token3[3]));
				}
			} else {
				if (line.equals("[ENDOFNPCLIST]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return true;
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
		return false;
	}

	public void loadSpell(int i) {
		Client c = (Client) PlayerHandler.players[npcs[i].killerId];
		switch (npcs[i].npcType) {

		case 2586:
			int C = 0;
			npcs[i].hitIcon = 2;
			C = Misc.random(1);
			if (C == 0) {
				npcs[i].projectileId = 393; // red
				npcs[i].attackType = 3;
			} else if (C == 1) {
				npcs[i].projectileId = 394; // green
				npcs[i].attackType = 2;
			}
			break;

		case 2587:
			int C2 = 0;
			npcs[i].hitIcon = 2;
			C2 = Misc.random(1);
			if (C2 == 0) {
				npcs[i].projectileId = 393; // red
				npcs[i].attackType = 3;
			} else if (C2 == 1) {
				npcs[i].projectileId = 394; // green
				npcs[i].attackType = 2;
			}
			break;
		case 2588:
			int C3 = 0;
			npcs[i].hitIcon = 2;
			C3 = Misc.random(1);
			npcs[i].hitIcon = 2;
			if (C3 == 0) {
				npcs[i].projectileId = 393; // red
				npcs[i].attackType = 3;
			} else if (C3 == 1) {
				npcs[i].projectileId = 394; // green
				npcs[i].attackType = 2;
			}
			break;

		case 2589:
			int C4 = 0;
			C4 = Misc.random(1);
			npcs[i].hitIcon = 2;
			c.hitIcon = 2;
			if (C4 == 0) {
				npcs[i].projectileId = 393; // red
				npcs[i].attackType = 3;
			} else if (C4 == 1) {
				npcs[i].projectileId = 394; // green
				npcs[i].attackType = 2;
			}
			break;
		case 2636:
			if (c == null) {
				System.out
						.println("C Returned null @ NPCHandler.loadSpell.2636");
				return;
			}
			int nexRandom = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX, c.absY, 2)) {
				nexRandom = 1;
			} else {
				nexRandom = Misc.random(5);
			}
			if (nexRandom == 0) {// Fumus/Cruor/Glacies/Umbra don't fail me
				npcs[i].attackType = 5;
				npcs[i].endGfx = -1;
				npcs[i].projectileId = -1;//
				c.gfx0(1968);
				NexTalk2(c);
				npcs[i].hitIcon = 1;
				npcs[i].forceChat("" + NexTalk + "");
			} else if (nexRandom == 1) {// Ice barrage from nex here
				npcs[i].projectileId = -1; // mage
				npcs[i].endGfx = -1;
				npcs[i].attackType = 4;
				c.freezeTimer = 15;
				c.gfx0(369);
				NexTalk1(c);
				npcs[i].forceChat("" + NexTalk + "");
				c.sendMessage("You have been Frozen!");
				npcs[i].hitIcon = 2;
			} else if (nexRandom == 2) {
				npcs[i].attackType = 3;
				npcs[i].endGfx = -1;
				npcs[i].projectileId = -1;
				// No sounds or text here - Regular attack
				npcs[i].hitIcon = 0;
			} else if (nexRandom == 3) {
				npcs[i].attackType = 2;
				npcs[i].gfx100(1978);
				npcs[i].projectileId = -1;
				c.gfx0(1982);
				NexTalk5(c);
				npcs[i].forceChat("" + NexTalk + "");
				npcs[i].hitIcon = 2;
			} else if (nexRandom == 4) {
				npcs[i].attackType = 1;
				npcs[i].endGfx = -1;
				npcs[i].projectileId = 1846;
				c.gfx0(1847);
				NexTalk3(c);
				npcs[i].forceChat("" + NexTalk + "");
				npcs[i].hitIcon = 2;
			} else if (nexRandom == 5) {
				npcs[i].attackType = 0;
				npcs[i].gfx100(1942);
				npcs[i].projectileId = -1;
				c.gfx0(1932);
				NexTalk4(c);
				npcs[i].forceChat("" + NexTalk + "");
				npcs[i].hitIcon = 1;
			}
			break;
		case 5666: // barrelschest
			random = Misc.random(1);
			if (random == 0) {
				npcs[i].attackType = 1;
			} else {
				npcs[i].attackType = 2;

			}
			break;
		case 2892:
			npcs[i].projectileId = 94;
			npcs[i].attackType = 2;
			npcs[i].endGfx = 95;
			break;

		// case 9947:
		case 753:
		case 655:
		case 2083:
			int k3 = 0;
			if (c == null) {
				System.out
						.println("C Returned null @ NPCHandler.loadSpell.753");
				return;
			}
			if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX, c.absY, 2)) {
				k3 = Misc.random(2);
			} else {
				k3 = Misc.random(1);
			}
			if (k3 == 0) {
				npcs[i].projectileId = 280; // mage
				npcs[i].requestAnimation(10819, 0);
				npcs[i].endGfx = 281;
				npcs[i].attackType = 2;
				npcs[i].forceChat("Take that!");
				c.freezeTimer = 10;
				c.gfx0(369);
				c.sendMessage("You have been Frozen!");
				c.hitIcon = 2;
				npcs[i].requestAnimation(10817, 0);
			} else if (k3 == 1) {
				npcs[i].attackType = 0; // range
				npcs[i].endGfx = 281;
				npcs[i].projectileId = 473;
				npcs[i].forceChat("MUHAHAHAH");
				npcs[i].requestAnimation(10816, 0);
				c.hitIcon = 1;
			} else if (k3 == 2) {
				npcs[i].attackType = 0; // melee
				npcs[i].projectileId = -1;
				// npcs[i].forceChat("You will all perish by my WRATH!");
				// c.sendMessage("You have been Infected with Deadly Poisen");
				c.hitIcon = 0;
			}
			break;
		case 1540:
			if (c == null) {
				System.out
						.println("C Returned null @ NPCHandler.loadSpell.1540");
				return;
			}
			npcs[i].projectileId = 280; // mage
			npcs[i].requestAnimation(10819, 0);
			npcs[i].endGfx = 281;
			npcs[i].attackType = 2;
			// npcs[i].forceChat("Take that!");
			c.freezeTimer = 10;
			c.gfx0(369);
			// c.sendMessage("You have been Frozen!");
			npcs[i].requestAnimation(10817, 0);
			npcs[i].hitIcon = 2;
			break;
		case 10040:
			if (c == null) {
				System.out
						.println("C Returned null @ NPCHandler.loadSpell.10040");
				return;
			}
			int k1 = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX, c.absY, 2)) {
				k1 = Misc.random(2);
			} else {
				k1 = Misc.random(1);
			}
			if (k1 == 0) {
				npcs[i].projectileId = 280; // mage
				npcs[i].requestAnimation(10819, 0);
				npcs[i].endGfx = 281;
				npcs[i].attackType = 2;
				npcs[i].forceChat("Take that!");
				c.freezeTimer = 10;
				c.gfx0(369);
				c.sendMessage("You have been Frozen!");
				npcs[i].requestAnimation(10817, 0);
			} else if (k1 == 1) {
				npcs[i].attackType = 0; // range
				npcs[i].endGfx = 281;
				npcs[i].projectileId = 473;
				npcs[i].forceChat("How dare you!");
				npcs[i].requestAnimation(10816, 0);
			} else if (k1 == 2) {
				npcs[i].attackType = 0; // melee
				npcs[i].projectileId = -1;
				// npcs[i].forceChat("You will all perish by my WRATH!");
				// c.sendMessage("You have been Infected with Deadly Poisen");
			}
			break;

		case 4477:
			if (c == null) {
				System.out
						.println("C Returned null @ NPCHandler.loadSpell.4477");
				return;
			}
			if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX, c.absY, 2)) {
				k1 = Misc.random(2);
			} else {
				k1 = Misc.random(1);
			}
			if (k1 == 0) {
				npcs[i].projectileId = 280; // mage
				npcs[i].endGfx = 281;
				npcs[i].attackType = 2;
				npcs[i].forceChat("Muahahahah!");
				c.freezeTimer = 15;
				c.gfx0(369);
				c.sendMessage("You have been frozen!");
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
			} else if (k1 == 1) {
				npcs[i].attackType = 0; // range
				npcs[i].endGfx = 281;
				npcs[i].projectileId = 473;
				npcs[i].forceChat("Leave my dungeon!");
				c.hitIcon = 1;
				npcs[i].hitIcon = 1;
			} else if (k1 == 2) {
				npcs[i].attackType = 0; // melee
				npcs[i].projectileId = -1;
				npcs[i].forceChat("You will all perish by my WRATH!");
				// c.sendMessage("You have been Infected with deadly poison");
				c.hitIcon = 0;
				npcs[i].hitIcon = 0;
			}
			break;
		case 10127:
			if (c == null) {
				System.out
						.println("C Returned null @ NPCHandler.loadSpell.4477");
				return;
			}
			k3 = Misc.random(22);
			if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX, c.absY, 2)) {
				k1 = Misc.random(2);
			} else {
				k1 = Misc.random(1);
			}
			if (k1 == 0) {
				npcs[i].projectileId = 280; // mage
				npcs[i].endGfx = 281;
				npcs[i].attackType = 2;
				npcs[i].forceChat("Hahaha!");
				c.freezeTimer = 15;
				c.gfx0(369);
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
			} else if (k1 == 1) {
				npcs[i].attackType = 0; // range
				npcs[i].endGfx = 281;
				npcs[i].projectileId = 473;
				npcs[i].forceChat("Leave my dungeon!");
				c.hitIcon = 1;
				npcs[i].hitIcon = 1;
			} else if (k1 == 2) {
				npcs[i].attackType = 0; // melee
				npcs[i].projectileId = -1;
				npcs[i].forceChat("You will suffer!");
				// c.sendMessage("You have been Infected with Deadly Poisen");
				c.hitIcon = 0;
				npcs[i].hitIcon = 0;
			}
			if (k3 > 1 && k3 < 6) {
				c.getDungeoneering();
				Dungeoneering.spawnMinions(c, npcs[i].absX, npcs[i].absY, 3622);
				c.getDungeoneering();
				Dungeoneering.spawnMinions(c, npcs[i].absX, npcs[i].absY, 3622);
				npcs[i].forceChat("My dear minions, help me!");
			}
			break;

		case 9947:
			if (c == null) {
				System.out
						.println("C Returned null @ NPCHandler.loadSpell.2636");
				return;
			}
			int o1 = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX, c.absY, 2)) {
				o1 = Misc.random(2);
			} else {
				k1 = Misc.random(1);
			}
			if (o1 == 0) {
				if (npcs[i].setIcon > -1) {
					c.CIcon = npcs[i].setIcon;
					npcs[i].setIcon = -1;

				}
				npcs[i].projectileId = 280; // mage
				npcs[i].endGfx = 281;
				npcs[i].attackType = 2;
				// npcs[i].forceChat("Muahahahah!");
				c.freezeTimer = 15;
				c.gfx0(369);

				c.CIcon = 2;
				c.getPA().refreshSkill(3);
				c.updateRequired = true;
				// c.CIcon = npcs[i].setIcon;
			}
			break;

		case 1158:

			if (c == null) {
				System.out
						.println("C Returned null @ NPCHandler.loadSpell.1158");
				return;
			}
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					int kq1 = 0;
					if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX,
							c.absY, 2)) {
						kq1 = Misc.random(2);
					} else {
						kq1 = Misc.random(1);
					}
					if (kq1 == 0) {
						npcs[i].projectileId = 280; // mage
						npcs[i].endGfx = 281;
						npcs[i].attackType = 2;
						c.hitIcon = 2;
						npcs[i].hitIcon = 2;
					} else if (kq1 == 1) {
						npcs[i].attackType = 1; // range
						npcs[i].endGfx = 281;
						npcs[i].projectileId = 473;
						c.hitIcon = 1;
						npcs[i].hitIcon = 1;
						/*
						 * } else if (kq1 == 2) { npcs[i].attackType = 0; //
						 * melee npcs[i].projectileId = -1;
						 */
					}
				}
			}
			break;

		case 1160:
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					int kq1 = 0;
					if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX,
							c.absY, 2)) {
						kq1 = Misc.random(2);
					} else {
						kq1 = Misc.random(1);
					}
					if (kq1 == 0) {
						npcs[i].projectileId = 280; // mage
						npcs[i].endGfx = 281;
						npcs[i].attackType = 2;
						c.hitIcon = 2;
						npcs[i].hitIcon = 2;
					} else if (kq1 == 1) {
						npcs[i].attackType = 1; // range
						npcs[i].endGfx = 281;
						npcs[i].projectileId = 473;
						c.hitIcon = 1;
						npcs[i].hitIcon = 1;
					}
				}
			}
			break;

		case 10039:
			int k4 = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX, c.absY, 2)) {
				k4 = Misc.random(2);
			} else {
				k4 = Misc.random(1);
			}
			if (k4 == 0) {
				npcs[i].projectileId = 280; // mage
				npcs[i].endGfx = 281;
				npcs[i].attackType = 2;
				npcs[i].forceChat("BE FROZEN BY MY POWER!");
				c.freezeTimer = 15;
				c.gfx0(369);
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
				c.sendMessage("You have been frozen!");
			} else if (k4 == 1) {
				npcs[i].attackType = 1; // range
				npcs[i].endGfx = 281;
				npcs[i].projectileId = 473;
				npcs[i].forceChat("AAAAAAAAAAAAAAAARHHHH?!");
				c.hitIcon = 1;
				npcs[i].hitIcon = 1;
			} else if (k4 == 2) {
				npcs[i].attackType = 0; // melee
				npcs[i].projectileId = -1;
				npcs[i].forceChat("LEAVE MY FLAMING PRISON!!");
				c.hitIcon = 0;
				npcs[i].hitIcon = 0;
			}
			break;
		case 3663:
			if (c != null) {
				if (c.atDemon()) {
					return;
				}
			}
			if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX, c.absY, 2)) {
				k4 = Misc.random(2);
			} else {
				k4 = Misc.random(1);
			}
			if (k4 == 0) {
				npcs[i].projectileId = 280; // mage
				npcs[i].endGfx = 281;
				npcs[i].attackType = 2;
				c.freezeTimer = 10;
				c.gfx0(369);
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
				c.sendMessage("You have been frozen!");
			} else if (k4 == 1) {
				npcs[i].attackType = 1; // range
				npcs[i].endGfx = 281;
				npcs[i].projectileId = 473;
				c.hitIcon = 1;
				npcs[i].hitIcon = 1;
			} else if (k4 == 2) {
				npcs[i].attackType = 0; // melee
				npcs[i].projectileId = -1;
				npcs[i].forceChat("I'ma beat the shit outta you nubby!");
				c.sendMessage("You suddenly feel alot weaker!");
				c.hitIcon = 0;
				npcs[i].hitIcon = 0;
			}
			break;
		case 3847:
			random = Misc.random(2);
			if (random == 0 || random == 1) {
				npcs[i].attackType = 0;
			} else {
				c.gfx0(369);
				npcs[i].forceChat("Be Frozen by the Tides!");
				c.freezeTimer = 15;
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
				npcs[i].attackType = 2;
			}
			break;

		case 8528:
			c.hitIcon = 0;
			npcs[i].hitIcon = 0;
			random = Misc.random(1);
			if (random == 1) {
				npcs[i].attackType = 0;
			} else {
				npcs[i].forceChat("You will never defeat me!");
				npcs[i].attackType = 1;
			}
			break;

		case 6692:
			c.hitIcon = 0;
			npcs[i].hitIcon = 0;
			random = Misc.random(1);
			if (random == 1) {
				npcs[i].attackType = 0;
			} else {
				npcs[i].attackType = 1;
			}
			break;

		case 3943:
			random = Misc.random(1);
			if (random == 1) {
				npcs[i].attackType = 0;
			} else {
				npcs[i].forceChat("Sssss Leave my realm now!");
				npcs[i].attackType = 1;
			}
			break;

		case 6713:
			random = Misc.random(2);
			if (random == 0 || random == 1) {
				npcs[i].attackType = 0;
			} else {
				c.gfx0(369);
				c.freezeTimer = 20;
				c.sendMessage("You have been Frozen!");
				npcs[i].attackType = 2;
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
			}
			break;

		case 10775:
			int r5 = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY,
					PlayerHandler.players[npcs[i].killerId].absX,
					PlayerHandler.players[npcs[i].killerId].absY, 2)) {
				r5 = Misc.random(5);
			} else {
				r5 = Misc.random(3);
			}
			if (r5 == 0) {
				npcs[i].projectileId = 393; // red
				npcs[i].attackType = 3;
			} else if (r5 == 1) {
				npcs[i].projectileId = 394; // green
				npcs[i].attackType = 2;
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
			} else if (r5 == 2) {
				npcs[i].projectileId = 395; // white
				npcs[i].attackType = 2;
				if (c.freezeTimer <= 0) {
					c.freezeTimer = 19;
					c.sendMessage("You have been frozen!");
					c.hitIcon = 2;
					npcs[i].hitIcon = 2;
				}
			} else if (r5 == 3) {
				npcs[i].projectileId = 396; // blue
				npcs[i].attackType = 2;
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
			} else if (r5 == 4) {
				npcs[i].projectileId = -1; // melee
				npcs[i].attackType = 0;
				c.hitIcon = 0;
				npcs[i].hitIcon = 0;
			} else if (r5 == 5) {
				npcs[i].projectileId = -1; // melee
				npcs[i].attackType = 0;
				c.hitIcon = 0;
				npcs[i].hitIcon = 0;
			}
			break;
		case 6998:
			int r4 = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY,
					PlayerHandler.players[npcs[i].killerId].absX,
					PlayerHandler.players[npcs[i].killerId].absY, 2)) {
				r4 = Misc.random(5);
			} else {
				r4 = Misc.random(3);
			}
			if (r4 == 0) {
				npcs[i].projectileId = 393; // red
				npcs[i].attackType = 3;
			} else if (r4 == 1) {
				npcs[i].projectileId = 394; // green
				npcs[i].attackType = 2;

			} else if (r4 == 2) {
				npcs[i].projectileId = 395; // white
				npcs[i].attackType = 2;
				if (c.freezeTimer <= 0) {
					c.freezeTimer = 19;
					c.sendMessage("You have been Frozen!");
				}
			} else if (r4 == 3) {
				npcs[i].projectileId = 396; // blue
				npcs[i].attackType = 2;
			} else if (r4 == 4) {
				npcs[i].projectileId = -1; // melee
				npcs[i].attackType = 0;
			} else if (r4 == 5) {
				npcs[i].projectileId = -1; // melee
				npcs[i].attackType = 0;
			}
			break;
		case 8596:
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					int kq1 = 0;
					if (goodDistance(npcs[i].absX, npcs[i].absY, c.absX,
							c.absY, 2)) {
						kq1 = Misc.random(2);
					} else {
						kq1 = Misc.random(1);
					}
					if (kq1 == 0) {
						npcs[i].projectileId = 280; // mage
						npcs[i].endGfx = 281;
						npcs[i].attackType = 2;
						npcs[i].forceChat("Aheheh, Why Do you Attempt?");
					} else if (kq1 == 1) {
						npcs[i].attackType = 1; // range
						npcs[i].endGfx = 281;
						npcs[i].projectileId = 473;
						npcs[i].forceChat("I will crush you puny Bugs!");
					} else if (kq1 == 2) {
						npcs[i].forceChat("Feel my POWER!");
						npcs[i].attackType = 0; // melee
						npcs[i].projectileId = -1;
					}
				}
			}
			break;

		case 2894:
			npcs[i].projectileId = 298;
			npcs[i].attackType = 1;
			break;
		case 6203:
			random = Misc.random(2);
			if (random == 0 || random == 1) {
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
			} else {
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1211;
			}
			break;
		case 6206:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1209;
			break;
		case 6208:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1213;
			break;
		case 6256:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 16;
			break;
		case 6220:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 17;
			break;

		case 53:
		case 54:
		case 55:
		case 4677:
		case 1590:
		case 1591:
		case 1592:
			int r6 = 0;

			r6 = Misc.random(2);
			if (r6 == 0) {
				npcs[i].projectileId = 393; // fire breath this time.
				npcs[i].attackType = 3;
				npcs[i].hitIcon = 2;
				c.hitIcon = 2;

			} else if (r6 == 1) {
				npcs[i].projectileId = -1; // melee
				npcs[i].attackType = 0;
				npcs[i].hitIcon = 0;
				c.hitIcon = 0;

			} else if (r6 == 2) {
				npcs[i].projectileId = -1; // melee
				npcs[i].attackType = 0;
				npcs[i].hitIcon = 0;
				c.hitIcon = 2;
			}

			break;
		// arma npcs
		case 6227:// kilisa
			npcs[i].attackType = 0;
			break;
		case 6225:// geerin
		case 6233:
		case 6230:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1190;
			break;
		case 6239:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1191;
			break;
		case 6232:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1191;
			break;
		case 6276:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1195;
			break;
		case 6223:// skree
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1199;
			break;
		case 6257:// saradomin strike
			npcs[i].attackType = 2;
			npcs[i].endGfx = 76;
			break;
		case 6221:// zamorak strike
			npcs[i].attackType = 2;
			npcs[i].endGfx = 78;
			break;
		case 6231:// arma
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1199;
			break;
		case 6222:// kree
			random = Misc.random(1);
			npcs[i].attackType = 1 + random;
			if (npcs[i].attackType == 1) {
				npcs[i].projectileId = 1197;
			} else {
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1198;
			}
			break;
		// sara npcs
		case 6247: // sara
			random = Misc.random(1);
			if (random == 0) {
				npcs[i].attackType = 2;
				npcs[i].endGfx = 1224;
				npcs[i].projectileId = -1;
			} else if (random == 1)
				npcs[i].attackType = 0;
			break;
		case 6248: // star
			npcs[i].attackType = 0;
			break;
		case 6250: // growler
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1203;
			break;
		case 6252: // bree
			npcs[i].attackType = 1;
			npcs[i].projectileId = 9;
			break;
		// bandos npcs
		case 6260:// bandos
			random = Misc.random(2);
			if (random == 0 || random == 1) {
				npcs[i].attackType = 0;
			} else {
				npcs[i].attackType = 1;
				// npcs[i].projectileId = 1200;
			}
			break;
		case 9463:
			random = Misc.random(2);
			if (random == 0 || random == 1)
				npcs[i].attackType = 0;

			else {
				npcs[i].attackType = 2;
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
			}
			break;
		case 9467:
			random = Misc.random(2);
			if (random == 0 || random == 1)
				npcs[i].attackType = 0;

			else {
				npcs[i].attackType = 2;
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
			}
		case 9465:
			random = Misc.random(2);
			if (random == 0 || random == 1) {
				npcs[i].attackType = 0;
				c.hitIcon = 0;
				npcs[i].hitIcon = 0;
			} else {
				c.playerLevel[5] -= (c.playerLevel[5] * .22);
				npcs[i].attackType = 2;
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
				c.sendMessage("Your prayers have been drain.");
				c.getPA().refreshSkill(5);
			}
			break;
		case 10530:
			random = Misc.random(2);
			if (random == 0 || random == 1)
				npcs[i].attackType = 0;
			else {
				c.playerLevel[5] -= (c.playerLevel[5] * .22);
				npcs[i].attackType = 2;
				c.sendMessage("The Warrior Drained Your Prayer Points!");
				c.getPA().refreshSkill(5);
			}
			break;
		case 795:
			random = Misc.random(2);
			if (random == 0 || random == 1)
				npcs[i].attackType = 0;
			else {
				c.gfx0(369);
				npcs[i].forceChat("Muhahaha");
				c.freezeTimer = 15;
				npcs[i].attackType = 2;
			}
			break;
		case 3495:
			random = Misc.random(2);
			if (random == 0 || random == 1)
				npcs[i].attackType = 0;
			else {
				c.gfx0(369);
				npcs[i].forceChat("Semolina-Go!");
				c.freezeTimer = 10;
				npcs[i].attackType = 2;
			}
			break;
		case 3493:
			random = Misc.random(2);
			if (random == 0 || random == 1) {
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
			} else {
				npcs[i].gfx100(129);
				npcs[i].projectileId = 130;
				npcs[i].endGfx = 131;
				npcs[i].attackType = 2;
			}
			break;
		case 3496:
			random = Misc.random(2);
			if (random == 0 || random == 1) {
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
			} else {
				npcs[i].forceChat("Hssssssssssss");
				npcs[i].gfx100(550);
				npcs[i].projectileId = 551;
				npcs[i].endGfx = 552;
				npcs[i].attackType = 2;
			}
			break;
		case 3491:
			npcs[i].projectileId = 106;
			npcs[i].attackType = 2;
			break;
		case 6261:// strongstack
			npcs[i].attackType = 0;
			break;
		case 6263:// steelwill
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1203;
			break;
		case 6265:// grimspike
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1206;
			break;
		case 2025:
			npcs[i].attackType = 2;
			int r = Misc.random(3);
			if (r == 0) {
				npcs[i].gfx100(158);
				npcs[i].projectileId = 159;
				npcs[i].endGfx = 160;
			}
			if (r == 1) {
				npcs[i].gfx100(161);
				npcs[i].projectileId = 162;
				npcs[i].endGfx = 163;
			}
			if (r == 2) {
				npcs[i].gfx100(164);
				npcs[i].projectileId = 165;
				npcs[i].endGfx = 166;
			}
			if (r == 3) {
				npcs[i].gfx100(155);
				npcs[i].projectileId = 156;
			}
			break;
		case 2881:// supreme
			npcs[i].attackType = 1;
			npcs[i].projectileId = 298;
			break;

		case 2882:// prime
			npcs[i].attackType = 2;
			npcs[i].projectileId = 162;
			npcs[i].endGfx = 477;
			break;

		case 2028:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 27;
			break;

		case 3200:
			int r2 = Misc.random(1);
			if (r2 == 0) {
				npcs[i].attackType = 1;
				npcs[i].gfx100(550);
				npcs[i].projectileId = 551;
				npcs[i].endGfx = 552;
			} else {
				npcs[i].attackType = 2;
				npcs[i].gfx100(553);
				npcs[i].projectileId = 554;
				npcs[i].endGfx = 555;
			}
			break;
		case 2745:
			int r3 = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY,
					PlayerHandler.players[npcs[i].spawnedBy].absX,
					PlayerHandler.players[npcs[i].spawnedBy].absY, 1))
				r3 = Misc.random(2);
			else
				r3 = Misc.random(1);
			if (r3 == 0) {
				npcs[i].attackType = 2;
				npcs[i].endGfx = 157;
				npcs[i].projectileId = 1627;
				// MAGIC ICON
				c.hitIcon = 2;
				npcs[i].hitIcon = 2;
			} else if (r3 == 1) {
				npcs[i].attackType = 1;
				npcs[i].endGfx = 451;
				npcs[i].gfx100(1625);
				npcs[i].projectileId = -1;
				// RANGED ICON
				c.hitIcon = 1;
				npcs[i].hitIcon = 1;
			} else if (r3 == 2) {
				// MELEE ICON
				c.hitIcon = 0;
				npcs[i].hitIcon = 0;
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
			}
			break;
		case 8133:
			if (goodDistance(npcs[i].absX, npcs[i].absY,
					PlayerHandler.players[npcs[i].killerId].absX,
					PlayerHandler.players[npcs[i].killerId].absY, 3))
				r3 = Misc.random(2);
			else
				r3 = Misc.random(1);
			if (r3 == 0) {
				npcs[i].attackType = 2;
				npcs[i].endGfx = -1;
				npcs[i].projectileId = 1828;
			} else if (r3 == 1) {
				npcs[i].attackType = 1;
				npcs[i].endGfx = -1;
				npcs[i].projectileId = 1839;
			} else if (r3 == 2) {
				npcs[i].attackType = 0;
				npcs[i].gfx100(1834);
				npcs[i].projectileId = -1;
			}
			break;
		case 3102:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 1839;
			break;
		case 3103:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 1828;
			break;
		case 8349:
		case 8350:
		case 8351:
			if (goodDistance(npcs[i].absX, npcs[i].absY,
					PlayerHandler.players[npcs[i].killerId].absX,
					PlayerHandler.players[npcs[i].killerId].absY, 2))
				r3 = Misc.random(2);
			else
				r3 = Misc.random(1);
			if (r3 == 0) {
				npcs[i].attackType = 2;
				npcs[i].gfx100(1885);
				npcs[i].projectileId = 1884;
			} else if (r3 == 1) {
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1889;
			} else if (r3 == 2) {
				npcs[i].attackType = 0;
				npcs[i].gfx100(1886);
				npcs[i].projectileId = -1;
			}
			break;
		case 2743:
			npcs[i].attackType = 2;
			npcs[i].projectileId = 445;
			npcs[i].endGfx = 446;
			break;

		case 2631:
			npcs[i].attackType = 1;
			npcs[i].projectileId = 443;
			break;
		}
	}

	/**
	 * load spell
	 **/
	public void loadSpell2(int i) {
		npcs[i].attackType = 3;
		int random = Misc.random(3);
		if (random == 0) {
			npcs[i].projectileId = 393; // red
			npcs[i].endGfx = 430;
		} else if (random == 1) {
			npcs[i].projectileId = 394; // green
			npcs[i].endGfx = 429;
		} else if (random == 2) {
			npcs[i].projectileId = 395; // white
			npcs[i].endGfx = 431;
		} else if (random == 3) {
			npcs[i].projectileId = 396; // blue
			npcs[i].endGfx = 428;
		}
	}

	public void multiAttackDamage(int i) {
		int max = getMaxHit(i);
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client) PlayerHandler.players[j];
				if (c.isDead || c.heightLevel != npcs[i].heightLevel)
					continue;
				if (PlayerHandler.players[j].goodDistance(c.absX, c.absY,
						npcs[i].absX, npcs[i].absY, 15)) {
					if (npcs[i].attackType == 2) {
						if (!c.prayerActive[16] && !c.curseActive[7]) {
							if (Misc.random(500) + 200 > Misc.random(c
									.getCombat().mageDef())) {
								int dam = Misc.random(max);
								c.dealDamage(dam);
								c.handleHitMask(dam, c.playerId);
							} else {
								c.dealDamage(0);
								c.handleHitMask(0, c.playerId);
							}
						} else {
							c.dealDamage(0);
							c.handleHitMask(0, c.playerId);
						}
					} else if (npcs[i].attackType == 1) {
						if (!c.prayerActive[17] && !c.curseActive[8]) {
							int dam = Misc.random(max);
							if (Misc.random(500) + 200 > Misc.random(c
									.getCombat().calculateRangeDefence())) {
								c.dealDamage(dam);
								c.handleHitMask(dam, c.playerId);
							} else {
								c.dealDamage(0);
								c.handleHitMask(0, c.playerId);
							}
						} else {
							c.dealDamage(0);
							c.handleHitMask(0, c.playerId);
						}
					}
					if (npcs[i].endGfx > 0) {
						c.gfx0(npcs[i].endGfx);
					}
				}
				c.getPA().refreshSkill(3);
			}
		}
	}

	public void multiAttackGfx(int i, int gfx) {
		if (npcs[i].projectileId < 0)
			return;
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client) PlayerHandler.players[j];
				if (c.heightLevel != npcs[i].heightLevel)
					continue;
				if (PlayerHandler.players[j].goodDistance(c.absX, c.absY,
						npcs[i].absX, npcs[i].absY, 15)) {
					int nX = NPCHandler.npcs[i].getX() + offset(i);
					int nY = NPCHandler.npcs[i].getY() + offset(i);
					int pX = c.getX();
					int pY = c.getY();
					int offX = (nY - pY) * -1;
					int offY = (nX - pX) * -1;
					c.getPA().createPlayersProjectile(nX, nY, offX, offY, 50,
							getProjectileSpeed(i), npcs[i].projectileId, 43,
							31, -c.getId() - 1, 65);
				}
			}
		}
	}

	public boolean multiAttacks(int i) {
		switch (npcs[i].npcType) {
		case 6222:// bandos?
			return true;

		case 8133: // Corporeal beast
			if (npcs[i].attackType == 2)
				return true;

		case 6247:// saradomin?
			if (npcs[i].attackType == 2)
				return true;

		case 6260:// armadyl?
			if (npcs[i].attackType == 1)
				return true;

		default:
			return false;
		}

	}

	public void newNPC(int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, int attack, int defence) {
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1)
			return;
		npcs[slot] = new NPC(slot, npcType);
		npcs[slot].absX = x;
		npcs[slot].absY = y;
		npcs[slot].makeX = x;
		npcs[slot].makeY = y;
		npcs[slot].heightLevel = heightLevel;
		npcs[slot].walkingType = WalkingType;
		npcs[slot].HP = HP;
		npcs[slot].MaxHP = HP;
		npcs[slot].maxHit = maxHit;
		npcs[slot].attack = attack;
		npcs[slot].defence = defence;
	}

	public void newNPC2(int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, int attack, int defence) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if (slot == -1)
			return; // no free slot found

		npcs[slot] = new NPC(slot, npcType);
		npcs[slot].absX = x;
		npcs[slot].absY = y;
		npcs[slot].makeX = x;
		npcs[slot].makeY = y;
		npcs[slot].heightLevel = heightLevel;
		npcs[slot].walkingType = WalkingType;
		npcs[slot].HP = HP;
		npcs[slot].MaxHP = HP;
		npcs[slot].maxHit = maxHit;
		npcs[slot].attack = attack;
		npcs[slot].defence = defence;
	}

	public void newNPCList(int npcType, String npcName, int combat, int HP) {
		int slot = -1;
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1)
			return;
		NpcList[slot] = new NPCList(npcType);
		NpcList[slot].npcName = npcName;
		NpcList[slot].npcCombat = combat;
		NpcList[slot].npcHealth = HP;
	}

	public void NexTalk1(Client c) {
		int RandomNexTalk = Misc.random(2);
		if (RandomNexTalk == 0) {
			NexTalk = "Die now, in a prison of ice!";
		} else if (RandomNexTalk == 1) {
			NexTalk = "There is...NO ESCAPE!";
		} else if (RandomNexTalk == 2) {
			NexTalk = "Infuse me with the power of ice";
		}
	}

	public void NexTalk2(Client c) {
		int RandomNexTalk = Misc.random(3);
		if (RandomNexTalk == 0) {
			NexTalk = "Fumus, don't fail me!";
		} else if (RandomNexTalk == 1) {
			NexTalk = "Cruor, don't fail me!";
		} else if (RandomNexTalk == 2) {
			NexTalk = "Glacies, don't fail me!";
		} else if (RandomNexTalk == 3) {
			NexTalk = "Umbra, don't fail me!";
		}
	}

	public void NexTalk3(Client c) {
		int RandomNexTalk = Misc.random(2);
		if (RandomNexTalk == 0) {
			NexTalk = "Let the virus flow through you!";
		} else if (RandomNexTalk == 1) {
			NexTalk = "Fill my soul with smoke!";
		} else if (RandomNexTalk == 2) {
			NexTalk = "Contain this!";
		}
	}

	public void NexTalk4(Client c) {
		int RandomNexTalk = Misc.random(2);
		if (RandomNexTalk == 0) {
			NexTalk = "I demand a blood sacrifice!";
		} else if (RandomNexTalk == 1) {
			NexTalk = "Flood my lungs with blood!";
		} else if (RandomNexTalk == 2) {
			NexTalk = "AT LAST!";
		}
	}

	public void NexTalk5(Client c) {

		NexTalk = "AT LAST!";
	}

	public int npcSize(int i) {
		switch (npcs[i].npcType) {
		case 2883:
		case 2882:
		case 2881:
		case 3493:
			return 3;
		case 3494:
			return 5;
		}
		return 0;
	}

	public int offset(int i) {
		switch (npcs[i].npcType) {
		case 2881:
		case 2882:
			return 1;
		case 2745:
		case 8349:
		case 8350:
		case 8351:
		case 2743:
		case 8133:
		case 50:
			return 1;
		case 1158:
		case 1160:
			return 2;
		}
		return 0;
	}

	public void process() {
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] == null)
				continue;
			npcs[i].clearUpdateFlags();

		}

		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {

				if (npcs[i].summon == true) {
					Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];

					/*
					 * if (c != null && c.hasFollower > 0) {
					 * followPlayer(c.hasFollower, c.npcIndex); }
					 */

					if (c != null && c.npcIndex > 0) {

						follownpc(i, c.npcIndex);
					}

					if (c != null && c.playerIndex < 1
							&& npcs[i].summon == true) {
						if (!npcs[i].underAttack) {
							if (!PlayerHandler.players[npcs[i].spawnedBy]
									.goodDistance(
											npcs[i].getX(),
											npcs[i].getY(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getX(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getY(), 2)
									&& c.npcIndex < 1)
								followPlayer(i, c.playerId);
						}
					} else {
						if (c != null && npcs[i].summon == true) {
							if (!PlayerHandler.players[npcs[i].spawnedBy]
									.goodDistance(
											npcs[i].getX(),
											npcs[i].getY(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getX(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getY(), 5)
									&& c.playerIndex < 1 || c.npcIndex < 1) {
								followPlayer(i, c.playerId);
							}
						}

					}

					if (c != null
							&& c.hasFollower > 0
							&& npcs[i].npcType != c.butler
							&& !PlayerHandler.players[npcs[i].spawnedBy]
									.goodDistance(
											npcs[i].getX(),
											npcs[i].getY(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getX(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getY(), 10)
							&& npcs[i].summon == true && !npcs[i].isDead) {

						npcs[i].isDead = true;
						npcs[i].applyDead = true;
						c.summoned2 = false;
						if (!c.isInPrivCon() && c.summoned2 == false) {
							c.Summoning().summonNulledNPC(c.hasFollower);
							npcs[i].gfx100(1315);
							npcs[i].underAttackBy2 = -1;
							npcs[i].updateRequired = true;
							npcs[i].dirUpdateRequired = true;
							npcs[i].getNextWalkingDirection();
						}
					}
					if (c != null
							&& c.butler > 0
							&& !PlayerHandler.players[npcs[i].spawnedBy]
									.goodDistance(
											npcs[i].getX(),
											npcs[i].getY(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getX(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getY(), 10)
							&& npcs[i].summon == true && !npcs[i].isDead
							&& c.inConstruction()) {

						npcs[i].isDead = true;
						npcs[i].applyDead = true;
						c.summoned2 = false;
						butt(c, c.butler, c.absX, c.absY - 2, c.heightLevel, 0,
								100, 0, false, 0, 0);
						npcs[i].underAttackBy2 = -1;
						npcs[i].updateRequired = true;
						npcs[i].dirUpdateRequired = true;
						npcs[i].getNextWalkingDirection();
					}

					if (c != null && c.hasFollower < 0
							&& npcs[i].npcType != c.butler || c == null) {
						npcs[i].isDead = true;
						npcs[i].applyDead = true;
						npcs[i].summon = false;
						npcs[i].underAttackBy2 = -1;
					}
					if (c != null && c.butler < 0
							&& npcs[i].npcType != c.hasFollower || c == null) {
						npcs[i].isDead = true;
						npcs[i].applyDead = true;
						npcs[i].summon = false;
						npcs[i].underAttackBy2 = -1;
					}
					if (c != null && c.butler > 0 && !c.inConstruction()) {
						npcs[i].isDead = true;
						npcs[i].applyDead = true;
						npcs[i].summon = false;
						npcs[i].underAttackBy2 = -1; // KILLS BUTLER
					}

					if (c != null && npcs[i].actionTimer < 1
							&& npcs[i].summon == true) {
						if (c.playerIndex > 0) {
							Client o = (Client) PlayerHandler.players[c.playerIndex];
							if (o != null) {
								if (npcs[i].IsAttackingPerson = true && o
										.inMulti()) {
									followPlayer(i, o.playerId);
									attackPlayer(o, i);
									npcs[i].index = o.playerId;
									npcs[i].actionTimer = 7;
								}
							}
						}
					}
				}
				if (npcs[i].npcType == 751) {
					Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
					if (Misc.random2(2) > 500) {
						npcs[i].updateRequired = true;
						npcs[i].forceChat("Get HIM!!!!");

					} else {
						if (Misc.random2(2) > 450) {
							npcs[i].updateRequired = true;
							npcs[i].forceChat("WHAT ARE YOU ALL DOING? KILL HIM");
						} else {
							if (Misc.random2(2) > 390) {
								npcs[i].updateRequired = true;
								npcs[i].forceChat("YOU SHALL SCREAM IN PAIN "
										+ c.playerName.toUpperCase() + "!");
							}
						}
					}
				}

				if (npcs[i].actionTimer > 0) {
					npcs[i].actionTimer--;
				}
				if (npcs[i].freezeTimer > 0) {
					npcs[i].freezeTimer--;
				}
				if (npcs[i].hitDelayTimer > 0) {
					npcs[i].hitDelayTimer--;
				}

				if (npcs[i].hitDelayTimer == 1) {
					npcs[i].hitDelayTimer = 0;
					applyDamage(i);
				}

				if (npcs[i].attackTimer > 0) {
					npcs[i].attackTimer--;
				}
				if (npcs[i].lastX != npcs[i].getX()
						|| npcs[i].lastY != npcs[i].getY()) {
					npcs[i].lastX = npcs[i].getX();
					npcs[i].lastY = npcs[i].getY();
				}
				if (npcs[i].spawnedBy > 0) {
					if (PlayerHandler.players[npcs[i].spawnedBy] == null
							|| PlayerHandler.players[npcs[i].spawnedBy].heightLevel != npcs[i].heightLevel
							|| PlayerHandler.players[npcs[i].spawnedBy].respawnTimer > 0
							|| !PlayerHandler.players[npcs[i].spawnedBy]
									.goodDistance(
											npcs[i].getX(),
											npcs[i].getY(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getX(),
											PlayerHandler.players[npcs[i].spawnedBy]
													.getY(), 10)) {

						if (PlayerHandler.players[npcs[i].spawnedBy] != null) {
							for (int o = 0; o < PlayerHandler.players[npcs[i].spawnedBy].barrowsNpcs.length; o++) {
								if (npcs[i].npcType != 3622) {
									if (npcs[i].npcType == PlayerHandler.players[npcs[i].spawnedBy].barrowsNpcs[o][0]) {
										if (PlayerHandler.players[npcs[i].spawnedBy].barrowsNpcs[o][1] == 1)
											PlayerHandler.players[npcs[i].spawnedBy].barrowsNpcs[o][1] = 0;
									}
								}
							}
						}

						if (/* !isHunterIMP(npcs[i].npcType) */npcs[i].npcType != 6574
								&& !npcs[i].InDung()
								&& !npcs[i].inRandomEvent()
								&& !npcs[i].inDungBossRoom()
								&& !npcs[i].inPcGame()
								&& !npcs[i].inBarbDef()
								&& !npcs[i].inFightCaves()) {
							npcs[i] = null;
						}
						if (npcs[i] != null) { // gravestones!!
							Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
							if (npcs[i].npcType == 6574) {
								if (c != null) {
									if (c.GSTimer == 0 || c.GSTimer < 0) {
										c.GSTimer = 0;
										npcs[i] = null;
									}
								}
							}
						}
						if (npcs[i] != null) {
							Client c = (Client) PlayerHandler.players[npcs[i].spawnedBy];
							if (c != null) {
								if (npcs[i].inFightCaves() == true
										&& c.inFightCaves() == false) {
									npcs[i] = null;
								}
							}
						}
						if (npcs[i] != null) {
							Client c2 = (Client) PlayerHandler.players[npcs[i].spawnedBy];
							if (c2 != null) {
								if (npcs[i].inBarbDef() == true
										&& c2.inBarbDef == false) {
									npcs[i] = null;
								}
							}
						}
					}
				}

				if (npcs[i] == null)
					continue;

				/**
				 * Attacking player
				 **/
				if (isAggressive(i) && !npcs[i].underAttack && !npcs[i].isDead
						&& !switchesAttackers(i)) {
					npcs[i].killerId = getCloseRandomPlayer(i);
				} else if (isAggressive(i) && !npcs[i].underAttack
						&& !npcs[i].isDead && switchesAttackers(i)) {
					npcs[i].killerId = getCloseRandomPlayer(i);
				}

				if (System.currentTimeMillis() - npcs[i].lastDamageTaken > 5000)
					npcs[i].underAttackBy = 0;

				if ((npcs[i].killerId > 0 || npcs[i].underAttack)
						&& !npcs[i].walkingHome && retaliates(npcs[i].npcType)) {
					if (!npcs[i].isDead) {
						int p = npcs[i].killerId;
						if (PlayerHandler.players[p] != null) {
							Client c = (Client) PlayerHandler.players[p];
							if (c != null && !c.doingTut
									&& !isHunterIMP(npcs[i].npcType)) {
								followPlayer(i, c.playerId);
								if (npcs[i] == null)
									continue;
								if (npcs[i].attackTimer == 0
										&& !isHunterIMP(npcs[i].npcType)) {
									attackPlayer(c, i);
								}
							}
						} else {
							npcs[i].killerId = 0;
							npcs[i].underAttack = false;
							if (npcs[i].npcType != 6574
									&& !isHunterIMP(npcs[i].npcType)) {
								npcs[i].facePlayer(0);
							}
						}
					}
				}
				/**
				 * Random walking and walking home
				 **/
				if (npcs[i] == null)
					continue;
				if ((!npcs[i].underAttack || npcs[i].walkingHome)
						&& npcs[i].walkingType > 0 && !npcs[i].isDead) {
					if (npcs[i].npcType != 6574
							&& !isHunterIMP(npcs[i].npcType)) {
						npcs[i].facePlayer(0);
					}
					npcs[i].killerId = 0;
					if (npcs[i].spawnedBy == 0) {
						if ((npcs[i].absX > npcs[i].makeX
								+ Config.NPC_RANDOM_WALK_DISTANCE)
								|| (npcs[i].absX < npcs[i].makeX
										- Config.NPC_RANDOM_WALK_DISTANCE)
								|| (npcs[i].absY > npcs[i].makeY
										+ Config.NPC_RANDOM_WALK_DISTANCE)
								|| (npcs[i].absY < npcs[i].makeY
										- Config.NPC_RANDOM_WALK_DISTANCE)) {
							npcs[i].walkingHome = true;
						}
					}

					if (npcs[i].walkingHome && npcs[i].absX == npcs[i].makeX
							&& npcs[i].absY == npcs[i].makeY) {
						npcs[i].walkingHome = false;
					} else if (npcs[i].walkingHome) {// could it be something
														// here
						npcs[i].moveX = GetMove(npcs[i].absX, npcs[i].makeX);
						npcs[i].moveY = GetMove(npcs[i].absY, npcs[i].makeY);
						handleClipping(i);
						npcs[i].getNextNPCMovement(i);
						npcs[i].updateRequired = true;
					}
					if (npcs[i].walkingType > 0) {
						if (Misc.random(3) == 1 && !npcs[i].walkingHome) {
							int MoveX = 0;
							int MoveY = 0;
							int Rnd = Misc.random(9);
							if (Rnd == 1) {
								MoveX = 1;
								MoveY = 1;
							} else if (Rnd == 2) {
								MoveX = -1;
							} else if (Rnd == 3) {
								MoveY = -1;
							} else if (Rnd == 4) {
								MoveX = 1;
							} else if (Rnd == 5) {
								MoveY = 1;
							} else if (Rnd == 6) {
								MoveX = -1;
								MoveY = -1;
							} else if (Rnd == 7) {
								MoveX = -1;
								MoveY = 1;
							} else if (Rnd == 8) {
								MoveX = 1;
								MoveY = -1;
							}

							if (MoveX == 1) {
								if (npcs[i].absX + MoveX < npcs[i].makeX + 1) {
									npcs[i].moveX = MoveX;
								} else {
									npcs[i].moveX = 0;
								}
							}

							if (MoveX == -1) {
								if (npcs[i].absX - MoveX > npcs[i].makeX - 1) {
									npcs[i].moveX = MoveX;
								} else {
									npcs[i].moveX = 0;
								}
							}

							if (MoveY == 1) {
								if (npcs[i].absY + MoveY < npcs[i].makeY + 1) {
									npcs[i].moveY = MoveY;
								} else {
									npcs[i].moveY = 0;
								}
							}

							if (MoveY == -1) {
								if (npcs[i].absY - MoveY > npcs[i].makeY - 1) {
									npcs[i].moveY = MoveY;
								} else {
									npcs[i].moveY = 0;
								}
							}
							if (!npcs[i].inDungBossRoom()) {
								handleClipping(i);
							}
							npcs[i].getNextNPCMovement(i);
							npcs[i].updateRequired = true;
						}
					}
				}

				if (npcs[i].isDead == true) {
					if (npcs[i].actionTimer == 0 && npcs[i].applyDead == false
							&& npcs[i].needRespawn == false) {
						npcs[i].updateRequired = true;
						if (npcs[i].npcType != 6574
								&& !isHunterIMP(npcs[i].npcType)) { // gravestone
							npcs[i].facePlayer(0);
						}
						npcs[i].killedBy = getNpcKillerId(i);
						npcs[i].animNumber = getDeadEmote(i); // dead emote
						npcs[i].animUpdateRequired = true;
						npcs[i].freezeTimer = 0;
						npcs[i].applyDead = true;
						killedBarrow(i);
						if (isFightCaveNpc(i))
							killedTzhaar(i);
						if (isBarbNpc(i))
							killedBarb(i);
						if (isRFDNpc(i))
							handleRFDDeath(i);
						if (npcs[i].summon == true)
							npcs[i].summon = false;
						npcs[i].actionTimer = 4;
						resetPlayersInCombat(i);
					} else if (npcs[i].actionTimer == 0
							&& npcs[i].applyDead == true
							&& npcs[i].needRespawn == false) {
						npcs[i].needRespawn = true;
						npcs[i].actionTimer = getRespawnTime(i);
						appendSlayerExperience(i);
						if (npcs[i].InDung() || npcs[i].inDungBossRoom()) {
							handleDungeoneeringDrops(i);
						}
						if (!npcs[i].inBarbDef() && !npcs[i].InDung()
								&& !npcs[i].inDominionTower()
								&& !npcs[i].inFightCaves()
								&& !npcs[i].inDungBossRoom()) {
							dropItems(i);
						}
						npcs[i].absX = npcs[i].makeX;
						npcs[i].absY = npcs[i].makeY;
						npcs[i].HP = npcs[i].MaxHP;
						npcs[i].animNumber = 0x328;
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
						if (npcs[i].npcType >= 2440 && npcs[i].npcType <= 2446) {
							Server.objectManager.removeObject(npcs[i].absX,
									npcs[i].absY);
						}
						// Handles Dungeoneering, Jad, Nomad, Goblin, And all
						// Dominion Tower NPC's.
						if (npcs[i].npcType == 2745) {
							handleJadDeath(i);
							return;
						}
						if (npcs[i].npcType == 1125) {
							handleDadDeath(i);
							return;
						}
						if (npcs[i].npcType == 2743) {
							handleTokDeath(i);
							return;
						}
						if (npcs[i].npcType == 753) {
							handleMadManDeath(i);
							return;
						}
						if (npcs[i].npcType == 1813) {
							handleKendalDeath(i);
							return;
						}
						if (npcs[i].npcType == 2083) {
							handleSigmundDeath(i);
							return;
						}
						if (npcs[i].npcType == 1540) {
							handleTruesDeath(i);
							return;
						}
						if (npcs[i].npcType == 655) {
							handleSpiritDeath(i);
							return;
						}
						if (npcs[i].npcType == 8528) {
							handleNomadDeath(i);
							return;
						}
						if (npcs[i].npcType == 6260) {
							handleBandosDeath(i);
							return;
						}
						if (npcs[i].npcType == 50) {
							handleKbdDeath(i);
							return;
						}
						if (npcs[i].npcType == 3200) {
							handleChaosEleDeath(i);
							return;
						}
						if (npcs[i].npcType == 8133) {
							handleCorpDeath(i);
							return;
						}
						if (npcs[i].npcType == 3663) {
							handleGoblinDeath(i);
							return;
						}
						if (npcs[i].npcType == 1472) {
							handleJungleDemonDeath(i);
							return;
						}
						// end off npc deaths
					} else if (npcs[i].actionTimer == 0
							&& npcs[i].needRespawn == true
							&& npcs[i].npcType != 6142
							&& npcs[i].npcType != 6143
							&& npcs[i].npcType != 6144
							&& npcs[i].npcType != 3732
							&& npcs[i].npcType != 3734
							&& npcs[i].npcType != 3736
							&& npcs[i].npcType != 3738
							&& npcs[i].npcType != 6145) {
						if (npcs[i].spawnedBy > 0 && !npcs[i].InDung()
								&& !npcs[i].inDungBossRoom()
								&& !npcs[i].inPcGame()) {
							npcs[i] = null;
						} else {
							int old1 = npcs[i].npcType;
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].walkingType;
							int old6 = npcs[i].MaxHP;
							int old7 = npcs[i].maxHit;
							int old8 = npcs[i].attack;
							int old9 = npcs[i].defence;
							if (!npcs[i].InDung() && !npcs[i].inDungBossRoom()
									&& !npcs[i].inBarbDef()
									&& !npcs[i].inFightCaves()) {
								npcs[i] = null;
								newNPC(old1, old2, old3, old4, old5, old6,
										old7, old8, old9);

							}
						}

					}
				}
			}
		}
	}

	/**
	 * Dropping Items!
	 **/
	public boolean rareDrops(int i) {
		return Misc.random(NPCDrops.dropRarity.get(npcs[i].npcType)) == 0;
	}

	/**
	 * Resets players in combat
	 */

	public void resetPlayersInCombat(int i) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null)
				if (PlayerHandler.players[j].underAttackBy2 == i)
					PlayerHandler.players[j].underAttackBy2 = 0;
		}
	}

	public boolean retaliates(int npcType) {
		return npcType < 6142 || npcType > 6145
				&& !(npcType >= 2440 && npcType <= 2446);
	}

	public boolean SaraKC(int i) {
		switch (npcs[i].npcType) {
		case 6247:
		case 6248:
		case 6250:
		case 6254:
		case 6252:
		case 6257:
		case 6255:
		case 6256:
		case 6258:
			return true;

		}

		return false;
	}

	public void SendPRMessage(String chatToForce, int id) {
		int ic = 0;
		for (int x = 0; x < npcs.length; x++) {
			if (npcs[x] != null && npcs[x].npcType == id) {
				if (ic == 3) {
					npcs[x].forceChat(chatToForce);
					return;
				} else if (id == 660) {
					ic++;
					continue;
				} else
					npcs[x].forceChat(chatToForce);

			}
		}
	}

	public void spawnNpc(Client c, int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, int attack, int defence,
			boolean attackPlayer, boolean headIcon) {
		if (c == null) {
			System.out.println("C Returned null @ NPCHandler.spawnNpc");
			return;
		}
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return;
		}
		npcs[slot] = new NPC(slot, npcType);
		npcs[slot].absX = x;
		npcs[slot].absY = y;
		npcs[slot].makeX = x;
		npcs[slot].makeY = y;
		npcs[slot].heightLevel = heightLevel;
		npcs[slot].walkingType = WalkingType;
		npcs[slot].HP = HP;
		npcs[slot].MaxHP = HP;
		npcs[slot].maxHit = maxHit;
		npcs[slot].attack = attack;
		npcs[slot].defence = defence;
		npcs[slot].spawnedBy = c.getId();
		if (headIcon)
			c.getPA().drawHeadicon(1, slot, 0, 0);
		if (attackPlayer) {
			npcs[slot].underAttack = true;
			if (c != null) {
				if (server.model.minigames.Barrows.COFFIN_AND_BROTHERS[c.randomCoffin][1] != npcs[slot].npcType) {
					if (npcs[slot].npcType == 2025
							|| npcs[slot].npcType == 2026
							|| npcs[slot].npcType == 2027
							|| npcs[slot].npcType == 2028
							|| npcs[slot].npcType == 2029
							|| npcs[slot].npcType == 2030) {
						npcs[slot].forceChat("You dare disturb my rest!");
					}
				}
				if (npcs[slot].npcType >= 4278 && npcs[slot].npcType <= 4284) {
					npcs[slot].forceAnim(4410);
					npcs[slot].forceChat("I'M ALIVE!");
				}
				if (server.model.minigames.Barrows.COFFIN_AND_BROTHERS[c.randomCoffin][1] == npcs[slot].npcType) {
					npcs[slot].forceChat("You dare steal from us!");
				}

				npcs[slot].killerId = c.playerId;
			}
		}
	}

	public void spawnNpc2(int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, int attack, int defence) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			// Misc.println("No Free Slot");
			return; // no free slot found
		}
		npcs[slot] = new NPC(slot, npcType);
		npcs[slot].absX = x;
		npcs[slot].absY = y;
		npcs[slot].makeX = x;
		npcs[slot].makeY = y;
		npcs[slot].heightLevel = heightLevel;
		npcs[slot].walkingType = WalkingType;
		npcs[slot].HP = HP;
		npcs[slot].MaxHP = HP;
		npcs[slot].maxHit = maxHit;
		npcs[slot].attack = attack;
		npcs[slot].defence = defence;
	}

	public void spawnNpc22(int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, int attack, int defence) {
		boolean hasSpawned = false;
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			// Misc.println("No Free Slot");
			return; // no free slot found
		}
		if (hasSpawned == false) {
			npcs[slot] = new NPC(slot, npcType);
			npcs[slot].absX = x;
			npcs[slot].absY = y;
			npcs[slot].makeX = x;
			npcs[slot].makeY = y;
			npcs[slot].heightLevel = heightLevel;
			npcs[slot].walkingType = WalkingType;
			npcs[slot].HP = HP;
			npcs[slot].MaxHP = HP;
			npcs[slot].maxHit = maxHit;
			npcs[slot].attack = attack;
			npcs[slot].defence = defence;
			hasSpawned = true;
		}
	}

	/***/
	/*****
	 * Using these methods for Barbarian assult. / Gabbe
	 ****/

	public void spawnNpc55(Client c, int npcType, int x, int y,
			int heightLevel, int WalkingType, int HP, int maxHit, int attack,
			int defence, boolean attackPlayer, boolean headIcon) {
		// first, search for a free slot
		if (c == null) {
			System.out.println("C Returned null @ NPCHandler.spawnNpc55");
			return;
		}
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			// Misc.println("No Free Slot");
			return; // no free slot found
		}
		npcs[slot] = new NPC(slot, npcType);
		npcs[slot].absX = x;
		npcs[slot].absY = y;
		npcs[slot].makeX = x;
		npcs[slot].makeY = y;
		npcs[slot].heightLevel = heightLevel;
		npcs[slot].walkingType = WalkingType;
		npcs[slot].HP = HP;
		npcs[slot].MaxHP = HP;
		npcs[slot].maxHit = maxHit;
		npcs[slot].attack = attack;
		npcs[slot].defence = defence;
		npcs[slot].spawnedBy = c.getId();
		if (headIcon)
			c.getPA().drawHeadicon(1, slot, 0, 0);
		if (attackPlayer) {
			npcs[slot].underAttack = true;
			if (c != null) {
				if (server.model.minigames.Barrows.COFFIN_AND_BROTHERS[c.randomCoffin][1] != npcs[slot].npcType) {
					if (npcs[slot].npcType == 2025
							|| npcs[slot].npcType == 2026
							|| npcs[slot].npcType == 2027
							|| npcs[slot].npcType == 2028
							|| npcs[slot].npcType == 2029
							|| npcs[slot].npcType == 2030) {
						npcs[slot].forceChat("You dare disturb my rest!");
					}
				}
				if (server.model.minigames.Barrows.COFFIN_AND_BROTHERS[c.randomCoffin][1] == npcs[slot].npcType) {
					npcs[slot].forceChat("You dare steal from us!");
				}

				npcs[slot].killerId = c.playerId;
			}
		}
	}

	public void spawnNpc56(Client c, int npcType, int x, int y,
			int heightLevel, int WalkingType, int HP, int maxHit, int attack,
			int defence, boolean attackPlayer, boolean headIcon) {
		int slot = -1;
		if (c == null) {
			System.out.println("C Returned null @ NPCHandler.spawnNpc56");
			return;
		}
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				c.barbLeader = slot = i;
				break;
			}
		}
		if (slot == -1) {
			return;
		}
		npcs[slot] = new NPC(slot, npcType);
		npcs[slot].absX = x;
		npcs[slot].absY = y;
		npcs[slot].makeX = x;
		npcs[slot].makeY = y;
		npcs[slot].heightLevel = heightLevel;
		npcs[slot].walkingType = WalkingType;
		npcs[slot].HP = HP;
		npcs[slot].MaxHP = HP;
		npcs[slot].maxHit = maxHit * 10;
		npcs[slot].attack = attack;
		npcs[slot].defence = defence;
		npcs[slot].spawnedBy = c.getId();
		if (headIcon)
			c.getPA().drawHeadicon(1, slot, 0, 0);
		if (attackPlayer) {
			npcs[slot].underAttack = true;
			if (c != null) {
				if (server.model.minigames.Barrows.COFFIN_AND_BROTHERS[c.randomCoffin][1] != npcs[slot].npcType) {
					if (npcs[slot].npcType == 2025
							|| npcs[slot].npcType == 2026
							|| npcs[slot].npcType == 2027
							|| npcs[slot].npcType == 2028
							|| npcs[slot].npcType == 2029
							|| npcs[slot].npcType == 2030) {
						npcs[slot].forceChat("You dare disturb my rest!");
					}
				}
				if (server.model.minigames.Barrows.COFFIN_AND_BROTHERS[c.randomCoffin][1] == npcs[slot].npcType) {
					npcs[slot].forceChat("You dare steal from us!");
				}

				npcs[slot].killerId = c.playerId;
			}
		}
	}

	public NPC spawnNpc77(int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, int attack, int defence) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			// Misc.println("No Free Slot");
			return null; // no free slot found
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		npcs[slot] = newNPC;
		return newNPC;
	}

	public boolean specialCase(Client c, int i) { // responsible for npcs that
		if (c == null) {
			System.out.println("C Returned null @ NPCHandler.specialCase");
			return false;
		} // much
		if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(), c.getY(), 8)
				&& !goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(),
						c.getY(), distanceRequired(i)))
			return true;
		return false;
	}

	public void startNPCImpLoops(int type, int x, int y) {
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {
				NPC n = NPCHandler.npcs[i];
				if (n != null) {
					// if(isHunterIMP(n.npcType)) {
					killNPC(n, type, x, y);
				}

			}
		}
	}

	public void Summon(Client c, int npcType, int x, int y, int heightLevel,
			int WalkingType, int HP, int maxHit, boolean attackPlayer,
			int attack, int defence) {
		if (c == null) {
			System.out.println("C Returned null @ NPCHandler.Summon");
			return;
		}
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) {
			return;
		}
		if (c != null) {
			c.sendMessage("" + c.butler + ", " + c.hasFollower + "");
			if (!c.cannotSummon()) {
				npcs[slot] = new NPC(slot, npcType);
				npcs[slot].absX = x;
				npcs[slot].absY = y;
				npcs[slot].makeX = x;
				npcs[slot].makeY = y;
				npcs[slot].heightLevel = heightLevel;
				npcs[slot].walkingType = WalkingType;
				npcs[slot].HP = HP;
				npcs[slot].MaxHP = HP;
				npcs[slot].maxHit = maxHit;
				npcs[slot].attack = attack;
				npcs[slot].defence = defence;
				npcs[slot].spawnedBy = c.getId();
				npcs[slot].followPlayer = c.getId();
				npcs[slot].summon = true;
				npcs[slot].npcslot = slot;
				npcs[slot].gfx100(1315);
				c.followerNpcId = slot;
				c.getPA().sendFrame75(npcs[slot].npcType, 17027);

				if (npcType != c.butler)
					c.hasFollower = npcType;

				c.summon = true;
				c.summoningnpcid = slot;
			} else {
				c.sendMessage("Your Summoned familiar will spawn again when out of Varrock & Duel arena.");
				c.needsToSpawn = true;
				if (npcType != c.butler)
					c.hasFollower = npcType;
				c.summon = true;
			}
			if (c.hasFollower == 6814 || c.hasFollower == 6823
					|| c.hasFollower == 6870) {
				c.healEventCounter = 0;
				c.summonEvent(c);
				c.sendMessage("Healing event started!");

			}

		}
	}

	public boolean switchesAttackers(int i) {
		switch (npcs[i].npcType) {
		case 6261:
		case 2589:
		case 10141:
		case 10110:
		case 10127:
		case 9752:
		case 2588:
		case 2587:
		case 2586:
		case 6263:
		case 6265:
		case 6223:
		case 6225:
		case 6227:
		case 6248:
		case 6250:
		case 8133:
		case 6252:
		case 8596:
		case 2892:
		case 2894:
		case 50:
		case 6206:
		case 6208:
		case 6204:
		case 9947:
		case 3847:
		case 2636:
		case 8283:
		case 8282:
			return true;

		}

		return false;
	}

	public boolean ZammyKC(int i) {
		switch (npcs[i].npcType) {
		case 8350:
		case 6203:
		case 3065:
		case 6204:
		case 6206:
		case 6208:
		case 10039:
		case 5247:
		case 6219:
		case 1904:
		case 1977:
		case 6218:
		case 3067:
		case 6212:
		case 205:
		case 3248:
		case 6220:
		case 6221:
		case 6272:
		case 10903:
		case 1157:
		case 1154:
		case 1153:
		case 10093:
		case 3665:
		case 1622:
		case 1633:
			return true;

		}

		return false;
	}

}