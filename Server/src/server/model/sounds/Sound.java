package server.model.sounds;

import server.Server;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.util.Misc;

public class Sound {

	public static String getItemName(int ItemID) {
		return Server.itemHandler.ItemList[ItemID].itemName;
	}

	public static int getNpcAttackSounds(int NPCID) {
		String npc = GetNpcName(NPCID).toLowerCase();
		if (npc.contains("bat")) {
			return 1;
		}
		if (npc.contains("cow")) {
			return 4;
		}
		if (npc.contains("imp")) {
			return 11;
		}
		if (npc.contains("rat")) {
			return 17;
		}
		if (npc.contains("duck")) {
			return 26;
		}
		if (npc.contains("wolf") || npc.contains("bear")) {
			return 28;
		}
		if (npc.contains("dragon")) {
			return 47;
		}
		if (npc.contains("ghost")) {
			return 57;
		}
		if (npc.contains("goblin")) {
			return 88;
		}
		if (npc.contains("skeleton") || npc.contains("demon")
				|| npc.contains("ogre") || npc.contains("giant")
				|| npc.contains("tz-") || npc.contains("jad")) {
			return 48;
		}
		if (npc.contains("zombie")) {
			return 1155;
		}
		if (npc.contains("man") || npc.contains("woman")
				|| npc.contains("monk")) {
			return 417;
		}
		return Misc.random(6) > 3 ? 398 : 394;

	}

	public static int getNpcBlockSound(int NPCID) {
		String npc = GetNpcName(NPCID).toLowerCase();
		if (npc.contains("bat")) {
			return 7;
		}
		if (npc.contains("cow")) {
			return 5;
		}
		if (npc.contains("imp")) {
			return 11;
		}
		if (npc.contains("rat")) {
			return 16;
		}
		if (npc.contains("duck")) {
			return 24;
		}
		if (npc.contains("wolf") || npc.contains("bear")) {
			return 34;
		}
		if (npc.contains("dragon")) {
			return 45;
		}
		if (npc.contains("ghost")) {
			return 53;
		}
		if (npc.contains("goblin")) {
			return 87;
		}
		if (npc.contains("skeleton") || npc.contains("demon")
				|| npc.contains("ogre") || npc.contains("giant")
				|| npc.contains("tz-") || npc.contains("jad")) {
			return 1154;
		}
		if (npc.contains("zombie")) {
			return 1151;
		}
		if (npc.contains("man") && !npc.contains("woman")) {
			return 816;
		}
		if (npc.contains("monk")) {
			return 816;
		}

		if (!npc.contains("man") && npc.contains("woman")) {
			return 818;
		}
		return 791;

	}

	public static int getNpcDeathSounds(int NPCID) {
		String npc = GetNpcName(NPCID).toLowerCase();
		if (npc.contains("bat")) {
			return 7;
		}
		if (npc.contains("cow")) {
			return 3;
		}
		if (npc.contains("imp")) {
			return 9;
		}
		if (npc.contains("rat")) {
			return 15;
		}
		if (npc.contains("duck")) {
			return 25;
		}
		if (npc.contains("wolf") || npc.contains("bear")) {
			return 35;
		}
		if (npc.contains("dragon")) {
			return 44;
		}
		if (npc.contains("ghost")) {
			return 60;
		}
		if (npc.contains("goblin")) {
			return 125;
		}
		if (npc.contains("skeleton") || npc.contains("demon")
				|| npc.contains("ogre") || npc.contains("giant")
				|| npc.contains("tz-") || npc.contains("jad")) {
			return 70;
		}
		if (npc.contains("zombie")) {
			return 1140;
		}
		return 70;

	}

	public static String GetNpcName(int NpcID) {
		return NPCHandler.NpcList[NpcID].npcName;
	}

	public static int getPlayerBlockSounds(Client c) {

		int blockSound = 511;

		if (c.playerEquipment[Player.playerChest] == 2499
				|| c.playerEquipment[Player.playerChest] == 2501
				|| c.playerEquipment[Player.playerChest] == 2503
				|| c.playerEquipment[Player.playerChest] == 4746
				|| c.playerEquipment[Player.playerChest] == 4757
				|| c.playerEquipment[Player.playerChest] == 10330) {// Dragonhide
																	// sound
			blockSound = 24;
		} else if (c.playerEquipment[Player.playerChest] == 10551 || // Torso
				c.playerEquipment[Player.playerChest] == 10438) {// 3rd age
			blockSound = 32;// Weird sound
		} else if (c.playerEquipment[Player.playerChest] == 10338 || // 3rd age
				c.playerEquipment[Player.playerChest] == 7399 || // Enchanted
				c.playerEquipment[Player.playerChest] == 6107 || // Ghostly
				c.playerEquipment[Player.playerChest] == 4091 || // Mystic
				c.playerEquipment[Player.playerChest] == 4101 || // Mystic
				c.playerEquipment[Player.playerChest] == 4111 || // Mystic
				c.playerEquipment[Player.playerChest] == 1035 || // Zamorak
				c.playerEquipment[Player.playerChest] == 12971) {// Combat
			blockSound = 14;// Robe sound
		} else if (c.playerEquipment[Player.playerShield] == 4224) {// Crystal
																	// Shield
			blockSound = 30;// Crystal sound
		} else if (c.playerEquipment[Player.playerChest] == 1101
				|| // Chains
				c.playerEquipment[Player.playerChest] == 1103
				|| c.playerEquipment[Player.playerChest] == 1105
				|| c.playerEquipment[Player.playerChest] == 1107
				|| c.playerEquipment[Player.playerChest] == 1109
				|| c.playerEquipment[Player.playerChest] == 1111
				|| c.playerEquipment[Player.playerChest] == 1113
				|| c.playerEquipment[Player.playerChest] == 1115
				|| // Plates
				c.playerEquipment[Player.playerChest] == 1117
				|| c.playerEquipment[Player.playerChest] == 1119
				|| c.playerEquipment[Player.playerChest] == 1121
				|| c.playerEquipment[Player.playerChest] == 1123
				|| c.playerEquipment[Player.playerChest] == 1125
				|| c.playerEquipment[Player.playerChest] == 1127
				|| c.playerEquipment[Player.playerChest] == 4720
				|| // Barrows armour
				c.playerEquipment[Player.playerChest] == 4728
				|| c.playerEquipment[Player.playerChest] == 4749
				|| c.playerEquipment[Player.playerChest] == 4712
				|| c.playerEquipment[Player.playerChest] == 11720
				|| // Godwars armour
				c.playerEquipment[Player.playerChest] == 11724
				|| c.playerEquipment[Player.playerChest] == 3140
				|| // Dragon
				c.playerEquipment[Player.playerChest] == 2615
				|| // Fancy
				c.playerEquipment[Player.playerChest] == 2653
				|| c.playerEquipment[Player.playerChest] == 2661
				|| c.playerEquipment[Player.playerChest] == 2669
				|| c.playerEquipment[Player.playerChest] == 2623
				|| c.playerEquipment[Player.playerChest] == 3841
				|| c.playerEquipment[Player.playerChest] == 1127) {// Metal
																	// armour
																	// sound
			blockSound = 15;
		} else {
			blockSound = 511;
		}
		return blockSound;
	}

	public static int GetWeaponSound(Client c) {

		String wep = getItemName(c.playerEquipment[Player.playerWeapon])
				.toLowerCase();

		if (c.playerEquipment[Player.playerWeapon] == 4718) {// Dharok's
																// Greataxe
			return 1320;
		}
		if (c.playerEquipment[Player.playerWeapon] == 4734) {// Karil's Crossbow
			return 1081;
		}
		if (c.playerEquipment[Player.playerWeapon] == 4747) {// Torag's Hammers
			return 1330;
		}
		if (c.playerEquipment[Player.playerWeapon] == 4710) {// Ahrim's Staff
			return 2555;
		}
		if (c.playerEquipment[Player.playerWeapon] == 4755) {// Verac's Flail
			return 1323;
		}
		if (c.playerEquipment[Player.playerWeapon] == 4726) {// Guthan's
																// Warspear
			return 2562;
		}

		if (c.playerEquipment[Player.playerWeapon] == 772
				|| c.playerEquipment[Player.playerWeapon] == 1379
				|| c.playerEquipment[Player.playerWeapon] == 1381
				|| c.playerEquipment[Player.playerWeapon] == 1383
				|| c.playerEquipment[Player.playerWeapon] == 1385
				|| c.playerEquipment[Player.playerWeapon] == 1387
				|| c.playerEquipment[Player.playerWeapon] == 1389
				|| c.playerEquipment[Player.playerWeapon] == 1391
				|| c.playerEquipment[Player.playerWeapon] == 1393
				|| c.playerEquipment[Player.playerWeapon] == 1395
				|| c.playerEquipment[Player.playerWeapon] == 1397
				|| c.playerEquipment[Player.playerWeapon] == 1399
				|| c.playerEquipment[Player.playerWeapon] == 1401
				|| c.playerEquipment[Player.playerWeapon] == 1403
				|| c.playerEquipment[Player.playerWeapon] == 1405
				|| c.playerEquipment[Player.playerWeapon] == 1407
				|| c.playerEquipment[Player.playerWeapon] == 1409
				|| c.playerEquipment[Player.playerWeapon] == 9100) { // Staff
																		// wack
			return 394;
		}
		if (c.playerEquipment[Player.playerWeapon] == 839
				|| c.playerEquipment[Player.playerWeapon] == 841
				|| c.playerEquipment[Player.playerWeapon] == 843
				|| c.playerEquipment[Player.playerWeapon] == 845
				|| c.playerEquipment[Player.playerWeapon] == 847
				|| c.playerEquipment[Player.playerWeapon] == 849
				|| c.playerEquipment[Player.playerWeapon] == 851
				|| c.playerEquipment[Player.playerWeapon] == 853
				|| c.playerEquipment[Player.playerWeapon] == 855
				|| c.playerEquipment[Player.playerWeapon] == 857
				|| c.playerEquipment[Player.playerWeapon] == 859
				|| c.playerEquipment[Player.playerWeapon] == 861
				|| c.playerEquipment[Player.playerWeapon] == 4734
				|| c.playerEquipment[Player.playerWeapon] == 2023 // RuneC'Bow
				|| c.playerEquipment[Player.playerWeapon] == 4212
				|| c.playerEquipment[Player.playerWeapon] == 4214
				|| c.playerEquipment[Player.playerWeapon] == 4934
				|| c.playerEquipment[Player.playerWeapon] == 9104
				|| c.playerEquipment[Player.playerWeapon] == 9107) { // Bows/Crossbows
			return 370;
		}
		if (c.playerEquipment[Player.playerWeapon] == 1363
				|| c.playerEquipment[Player.playerWeapon] == 1365
				|| c.playerEquipment[Player.playerWeapon] == 1367
				|| c.playerEquipment[Player.playerWeapon] == 1369
				|| c.playerEquipment[Player.playerWeapon] == 1371
				|| c.playerEquipment[Player.playerWeapon] == 1373
				|| c.playerEquipment[Player.playerWeapon] == 1375
				|| c.playerEquipment[Player.playerWeapon] == 1377
				|| c.playerEquipment[Player.playerWeapon] == 1349
				|| c.playerEquipment[Player.playerWeapon] == 1351
				|| c.playerEquipment[Player.playerWeapon] == 1353
				|| c.playerEquipment[Player.playerWeapon] == 1355
				|| c.playerEquipment[Player.playerWeapon] == 1357
				|| c.playerEquipment[Player.playerWeapon] == 1359
				|| c.playerEquipment[Player.playerWeapon] == 1361
				|| c.playerEquipment[Player.playerWeapon] == 9109) { // BattleAxes/Axes
			return 399;
		}
		if (c.playerEquipment[Player.playerWeapon] == 4718
				|| c.playerEquipment[Player.playerWeapon] == 7808) { // Dharok
																		// GreatAxe
			return 400;
		}
		if (c.playerEquipment[Player.playerWeapon] == 6609
				|| c.playerEquipment[Player.playerWeapon] == 1307
				|| c.playerEquipment[Player.playerWeapon] == 1309
				|| c.playerEquipment[Player.playerWeapon] == 1311
				|| c.playerEquipment[Player.playerWeapon] == 1313
				|| c.playerEquipment[Player.playerWeapon] == 1315
				|| c.playerEquipment[Player.playerWeapon] == 1317
				|| c.playerEquipment[Player.playerWeapon] == 1319) { // 2h
			return 425;
		}
		if (c.playerEquipment[Player.playerWeapon] == 1321
				|| c.playerEquipment[Player.playerWeapon] == 1323
				|| c.playerEquipment[Player.playerWeapon] == 1325
				|| c.playerEquipment[Player.playerWeapon] == 1327
				|| c.playerEquipment[Player.playerWeapon] == 1329
				|| c.playerEquipment[Player.playerWeapon] == 1331
				|| c.playerEquipment[Player.playerWeapon] == 1333
				|| c.playerEquipment[Player.playerWeapon] == 4587) { // Scimitars
			return 396;
		}
		if (wep.contains("halberd")) {
			return 420;
		}
		if (wep.contains("long")) {
			return 396;
		}
		if (wep.contains("knife")) {
			return 368;
		}
		if (wep.contains("javelin")) {
			return 364;
		}

		if (c.playerEquipment[Player.playerWeapon] == 9110) {
			return 401;
		}
		if (c.playerEquipment[Player.playerWeapon] == 4755) {
			return 1059;
		}
		if (c.playerEquipment[Player.playerWeapon] == 4153) {
			return 1079;
		}
		if (c.playerEquipment[Player.playerWeapon] == 9103) {
			return 385;
		}
		if (c.playerEquipment[Player.playerWeapon] == -1) { // fists
			return 417;
		}
		if (c.playerEquipment[Player.playerWeapon] == 2745
				|| c.playerEquipment[Player.playerWeapon] == 2746
				|| c.playerEquipment[Player.playerWeapon] == 2747
				|| c.playerEquipment[Player.playerWeapon] == 2748) { // Godswords
			return 390;
		}
		if (c.playerEquipment[Player.playerWeapon] == 4151) {
			return 1080;
		} else {
			return 398; // Daggers(this is enything that isn't added)
		}
	}

	public static int specialSounds(int id) {
		if (id == 4151) // whip
		{
			return 1081;
		}
		if (id == 5698) // dds
		{
			return 793;
		}
		if (id == 1434)// Mace
		{
			return 387;
		}
		if (id == 3204) // halberd
		{
			return 420;
		}
		if (id == 4153) // gmaul
		{
			return 1082;
		}
		if (id == 7158) // d2h
		{
			return 426;
		}
		if (id == 4587) // dscim
		{
			return 1305;
		}
		if (id == 1215) // Dragon dag
		{
			return 793;
		}
		if (id == 1305) // D Long
		{
			return 390;
		}
		if (id == 861) // MSB
		{
			return 386;
		}
		if (id == 11235) // DBow
		{
			return 386;
		}
		if (id == 6739) // D Axe
		{
		}
		if (id == 1377) // DBAxe
		{
			return 389;
		}
		return -1;
	}

	Client c;
	/**
	 * Singular sound variables.
	 */

	public final int LEVELUP = 67;
	public final int DUELWON = 77;
	public final int DUELLOST = 76;

	public final int FOODEAT = 317;

	public final int DROPITEM = 376;

	public final int COOKITEM = 357;

	public final int SHOOT_ARROW = 370;

	public final int TELEPORT = 202;

	public final int BONE_BURY = 380;

	public final int DRINK_POTION = 334;

	public Sound(Client c) {
		this.c = c;
	}
}