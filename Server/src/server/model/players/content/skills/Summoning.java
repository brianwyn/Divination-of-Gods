package server.model.players.content.skills;

import server.Config;
import server.Server;
import server.model.items.Item;
import server.model.items.ItemAssistant;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.content.GabbesAchievements;

public class Summoning {

	private static final int GOLD = 12158, GREEN = 12159, CRIMSON = 12160,
			ABYSSAL = 12161, TALON = 12162, BLUE = 12163, RAVAGER = 12164,
			SHIFTER = 12165, SPINNER = 12166, TORCHER = 12167,
			OBSIDIAN = 12168;// ALL CHARMS

	private final static int POUCH = 12155, SHARDS = 18016;

	public enum PouchData {
		SPIRIT_WOLF( new int[] {91184 }, 1, 7, GOLD, 2859, 12047, 4.8, 12425, 6829),
		DREADFOWL( new int[] {91192 }, 4, 8, GOLD, 2138, 12043, 9.3, 12445, 6825),
		SPIRIT_SPIDER( new int[] {91200 }, 10, 8, GOLD, 6291, 12059, 12.6, 12428, 6841),
		THORNY_SNAIL( new int[] {91208 }, 13, 9, GOLD, 3363, 12019, 12.6, 12459, 6806),
		GRANITE_CRAB( new int[] {91216 }, 16, 7, GOLD, 440, 12009, 21.6, 12533, 6796),
		SPIRIT_MOSQUITO( new int[] {91224 }, 17, 1, GOLD, 6319, 12778, 46.5, 12838, 7331),
		DESERT_WYRM( new int[] {91232 }, 18, 45, GREEN, 1783, 12049, 31.2, 12460, 6831),
		SPIRIT_SCORPION( new int[] {91240 }, 19, 57, CRIMSON, 3095, 12055, 83.2, 12432, 6837),
		SPIRIT_TZ_KIH( new int[] { 91248}, 22, 64, CRIMSON, OBSIDIAN, 12808, 96.8, 12839, 7361),
		ALBINO_RAT( new int[] {92000 }, 23, 75, BLUE, 2134, 12067, 202.4, 12430, 6847),
		SPIRIT_KALPHITE( new int[] {92008 }, 25, 51, BLUE, 3138, 12063, 220,  12446, 6994),
		COMPOST_MOUND( new int[] {92016 }, 28, 47, GREEN, 6032, 12091, 49.8, 12440, 6871),
		GIANT_CHINCHOMPA( new int[] {92024 }, 29, 84, BLUE, 9976, 12800, 255.2, 12834, 7353),
		VAMPIRE_BAT( new int[] {92032 }, 31, 81, CRIMSON, 3325, 12053, 136, 12447, 6835),
		HONEY_BADGER( new int[] {92040 }, 32, 84, CRIMSON, 12156, 12065, 140.8, 12433, 6845),
		BEAVER( new int[] {92048 }, 33, 72, GREEN, 1519, 12022, 57.6, 12429, 6807),
		VOID_RAVAGER( new int[] {92056 }, 34, 74, GREEN, RAVAGER, 12818, 59.6, 12443, 7370),
		VOID_SHIFTR( new int[] {92064 }, 34, 74, BLUE, SHIFTER, 12814, 59.6, 12443, 7367),
		VOID_SPINNER( new int[] {92072 }, 34, 74, BLUE, SPINNER, 12780, 59.6, 12443, 7333),
		VOID_TORCHER( new int[] {92080 }, 34, 74, BLUE, TORCHER, 12798, 59.6, 12443, 7333),
		BRONZE_MINOTAUR( new int[] {92088 }, 36, 102, BLUE, 2349, 12073, 316.8, 12461, 6853),
		BULL_ANT( new int[] {92096 }, 40, 11, GOLD, 6010, 12087, 52.8, 12431, 6867),					
		MACAW( new int[] {92112 }, 41, 78, GREEN, 249, 12071, 72.4, 12422, 6851),
		EVIL_TURNIP( new int[] {92120 }, 42, 104, CRIMSON, 12153, 12051, 184.8, 12448, 6833),
		IRON_MINOTAUR( new int[] {92176 }, 46, 125, BLUE, 2351, 12075, 404.8, 12462, 6855),
		PYRELORD( new int[] {92184 }, 46, 111, CRIMSON, 590, 12816, 202.4, 12829, 7377),
		MAGPIE( new int[] {92192 }, 47, 88, GREEN, 1635, 12041, 83.2, 12426, 6824),
		BLOATED_LEECH( new int[] {92200 }, 49, 117,  CRIMSON, 2132, 12061, 215.2, 12444, 6843),
		SPIRIT_TERRORBIRD( new int[] {92208 }, 52, 12, GOLD, 9978, 12007, 68.4, 12441, 3596),		
		ABYSSAL_PARASITE( new int[] {92216 }, 54, 106, GREEN, ABYSSAL, 12035, 94.8, 12454, 6818),
		SPIRIT_JELLY( new int[] {92224 }, 55, 151, BLUE, 1937, 12027, 484, 12453, 6922),
		STEEL_MINOTAUR( new int[] {92232 }, 56, 141, BLUE, 2353, 12077, 492.8, 12463, 6857),
		IBIS( new int[] {92240 }, 56, 109, GREEN, 311, 12531, 98.8, 12424, 6991),
		SPIRIT_GRAAHK( new int[] {92248 }, 57, 154, BLUE, 10099, 12810, 501.6, 12836, 3588),
		SPIRIT_KYATT( new int[] {93000 }, 57, 153, BLUE, 10103, 12812, 501.6, 12840, 7365),
		SPIRIT_LARUPIA( new int[] {93008 }, 57, 155, BLUE, 10095, 12784, 501.6, 12835, 7337),
		KARAMTHULHU_OVERLORD( new int[] {93016 }, 58, 144, BLUE, 6667, 12023, 510.4, 12455, 6809),
		SMOKE_DEVIL( new int[] {93024 }, 61, 141, CRIMSON, 9736, 12085, 268, 12468, 6865),
		ABYSSAL_LURKER( new int[] {93032 }, 62, 119, GREEN, ABYSSAL, 12037, 109.6, 12427, 6820),
		SPIRIT_COBRA( new int[] {93040 }, 63, 116, CRIMSON, 6287, 12015, 276.8, 12436, 6802),
		MITHRIL_MINOTAUR( new int[] {93048 }, 66, 152, BLUE, 2359, 12079, 580.8, 12464, 6859),
		BARKER_TOAD( new int[] {93064 }, 66, 11,  GOLD, 2150, 12123, 87, 12452, 6889),

		STEEL_TITAN( new int[] {94032 }, 99, 178, CRIMSON, 1119, 12790, 435.2, 12825, 3591),
		PACK_YAK( new int[] {94024 }, 96, 211, CRIMSON, 10818, 12093, 422.4, 12435, 3594),
		IRON_TITAN( new int[] {94016 }, 95, 198, CRIMSON, 1115, 12822, 417.6, 12828, 7375),
		ABYSSAL_TITAN( new int[] {94008 }, 93, 113, GREEN, 12161, 12796, 163.2, 12827, 7349),
		WOLPERTINGER( new int[] {94000 }, 92, 203, CRIMSON, new int[] {3226,2859}, 12089, 404.8, 12437, 3593),
		GEYSER_TITAN( new int[] {93248 }, 89, 222, BLUE, 1444, 12786, 783.2, 12833, 7339),
		UNICORN_STALLION( new int[] {93240 }, 88, 140, GREEN, 237, 12039, 154.4, 12434, 3592),
		RUNE_MINOTAUR( new int[] {93232 }, 86, 111, BLUE, 2363, 12083, 756.8, 12466, 6863),
		SWAMP_TITAN( new int[] {93224 }, 85, 150, CRIMSON, 10149, 12776, 373.6, 12832, 7329),
		LAVA_TITAN( new int[] {93216 }, 83, 219, BLUE, 12168, 12788, 730.4, 12837, 7341),
		SPIRIT_DAGANNOTH( new int[] {93208 }, 83, 122, CRIMSON, 6155, 12017, 364.8, 12456, 6804),
		HYDRA( new int[] {93200 }, 80, 128, GREEN, 571, 12025, 140.8, 12442, 6811),
		MOSS_TITAN( new int[] {93184 }, 79, 202, BLUE, 1440, 12804, 695.2, 12824, 7357),
		ICE_TITAN( new int[] {93192 }, 79, 198, BLUE, new int[] { 1438, 1444} , 12806, 695.2, 12824, 7359),
		FIRE_TITAN( new int[] {93176 }, 79, 198, BLUE, 1442, 12802, 695.2, 12824, 7355),
		GIANT_ENT( new int[] {93168 }, 78, 124, GREEN, 5933, 12013, 136.8, 12457, 6800),
		TALON_BEAST( new int[] {93160 }, 77, 174, CRIMSON, TALON, 12794, 1015.2, 12831, 7347),
		FORGE_REGENT( new int[] {93152 }, 76, 141, GREEN, 10020, 12782, 134, 12841, 7335),
		ADAMANT_MINOTAUR( new int[] {93144 }, 76, 144, BLUE, 2361, 12081, 668.8, 12465, 6861),
		PRAYING_MANTIS( new int[] {93136 }, 75, 168, CRIMSON, 2460, 12011, 329.6, 12450, 6798),
		GRANITE_LOBSTER( new int[] {93128 }, 74, 166, CRIMSON, 6979, 12069, 325.6, 12449, 6849),
		OBSIDIAN_GOLEM( new int[] {93120 }, 73, 195, BLUE, 12168, 12792, 642.4, 12826, 7345),
		PHOENIX( new int[] {93112 }, 72, 165, CRIMSON, 14616, 14623, 301, 14622, 1911),
		ARCTIC_BEAR( new int[] {93104 }, 71, 14, CRIMSON, 10117, 12057, 93.2, 12451, 6839),
		RAVENOUS_LOCUST( new int[] {93096 }, 70, 79, CRIMSON, 1933, 12820, 132, 12830, 7372),
		FRUIT_BAT( new int[] {93088 }, 69, 130, GREEN, 1963, 12033, 121.2, 12423, 6817),
		BUNYIP( new int[] {93080 }, 68, 110, GREEN, 383, 12029, 110, 12438, 6813),
		WAR_TORTOISE( new int[] {93072 }, 67, 1, GOLD, 7939, 12031, 58.6, 12431, 6815);

		private int levelReq, shardReq, charmId, pouchMade, scrollId, npcID;
		private int[] clickingIds, secondaryIds;
		private double xpGain;

		PouchData(int[] clickingIds, int levelReq, int shardReq, int charmId,
				int secondaryId, int pouchMade, double xpGain, int scrollId,
				int npcID) {
			this.clickingIds = clickingIds;
			this.levelReq = levelReq;
			this.shardReq = shardReq;
			this.charmId = charmId;
			this.secondaryIds = new int[] { secondaryId };
			this.pouchMade = pouchMade;
			this.xpGain = xpGain;
			this.scrollId = scrollId;
			this.npcID = npcID;
		}

		PouchData(int[] clickingIds, int levelReq, int shardReq, int charmId,
				int[] secondaryIds, int pouchMade, double xpGain, int scrollId,
				int npcID) {
			this.clickingIds = clickingIds;
			this.levelReq = levelReq;
			this.shardReq = shardReq;
			this.charmId = charmId;
			this.secondaryIds = secondaryIds;
			this.pouchMade = pouchMade;
			this.xpGain = xpGain;
			this.scrollId = scrollId;
			this.npcID = npcID;
		}

		/**
		 * @return the levelReq
		 */
		public int getLevelReq() {
			return levelReq;
		}

		/**
		 * @return the shardReq
		 */
		public int getShardReq() {
			return shardReq;
		}

		/**
		 * @return the charmId
		 */
		public int getCharmId() {
			return charmId;
		}

		/**
		 * @return the secondaryIds
		 */
		public int[] getSecondaryIds() {
			return secondaryIds;
		}

		/**
		 * @return the pouchMade
		 */
		public int getPouchMade() {
			return pouchMade;
		}

		/**
		 * @return the xpGain
		 */
		public double getXpGain() {
			return xpGain;
		}

		/**
		 * @return the scrollId
		 */
		public int getScrollId() {
			return scrollId;
		}

		/**
		 * @return the npcID
		 */
		public int getNpcID() {
			return npcID;
		}

		/**
		 * @return the clickingIds
		 */
		public int[] getClickingIds() {
			return clickingIds;
		}
	}

	public static PouchData getPouch(int clickId) {
		for (PouchData pouch : PouchData.values())
			for (int id : pouch.getClickingIds())
				if (clickId == id)
					return pouch;
		return null;
	}

	private static boolean playerHasItems(Client c, PouchData pouch) {
		if (!c.getItems().playerHasItem(POUCH))
			return false;
		if (!c.getItems().playerHasItem(SHARDS, pouch.getShardReq()))
			return false;
		if (!c.getItems().playerHasItem(pouch.getCharmId()))
			return false;
		if (pouch.getSecondaryIds().length > 1) {
			for (int item : pouch.getSecondaryIds())
				if (!c.getItems().playerHasItem(item))
					return false;
		} else {
			if (!c.getItems().playerHasItem(pouch.getSecondaryIds()[0]))
				return false;
		}
		return true;
	}
	
	private static String getSecondary(PouchData pouch) {
		String ret = "";
		if(pouch.getSecondaryIds().length == 1)
			return "and " + ItemAssistant.getItemName2(pouch.getSecondaryIds()[0]) + ".";
		else {
			for(int i = 0; i < pouch.getSecondaryIds().length;i++) {
				if(i != (pouch.getSecondaryIds().length - 1))
					ret += ItemAssistant.getItemName2(pouch.getSecondaryIds()[i]) + ", ";
				else
					ret += "and " + ItemAssistant.getItemName2(pouch.getSecondaryIds()[i]) + ".";
					
			}
		}
		return ret;
	}

	public static boolean createPouch(Client c, int actionButtonId) {
		PouchData pouchToMake = getPouch(actionButtonId);
		if (pouchToMake == null)
			return false;
		if (!playerHasItems(c, pouchToMake)) {
			c.sendMessage("You need: 1 pouch, 1 " + ItemAssistant.getItemName2(pouchToMake.getCharmId()) + ", "
					+ pouchToMake.getShardReq() + " shards, " + getSecondary(pouchToMake));
			return false;
		}
		int amountToMake = c.getItems().getItemAmount(SHARDS)
				/ pouchToMake.getShardReq();

		if (c.getItems().getItemAmount(pouchToMake.getCharmId()) < amountToMake)
			amountToMake = c.getItems().getItemAmount(pouchToMake.getCharmId());
		for (int item : pouchToMake.getSecondaryIds())
			if (c.getItems().getItemAmount(item) < amountToMake)
				amountToMake = c.getItems().getItemAmount(item);

		if (c.getItems().getItemAmount(POUCH) < amountToMake)
			amountToMake = c.getItems().getItemAmount(POUCH);

		if (amountToMake < 1)
			return false;
		c.getItems().deleteItem2(SHARDS,
				(pouchToMake.getShardReq() * amountToMake));
		c.getItems().deleteItem2(pouchToMake.getCharmId(), (1 * amountToMake));
		for (int item : pouchToMake.getSecondaryIds())
			c.getItems().deleteItem2(item, (1 * amountToMake));
		c.getItems().deleteItem2(POUCH, (1 * amountToMake));
		c.getItems().addItem(pouchToMake.getPouchMade(), (1 * amountToMake));
		c.getPA()
				.addSkillXP(
						((int) (pouchToMake.getXpGain() * Config.SUMMONING_EXP_BONUS) * amountToMake),
						21);
		c.sendMessage("You infused "
				+ amountToMake
				+ " "
				+ ItemAssistant.getItemName2(pouchToMake.getPouchMade())
				+ " for "
				+ ((int) (pouchToMake.getXpGain() * Config.SUMMONING_EXP_BONUS) * amountToMake)
				+ " Summoning experience.");
		c.getPA().removeAllWindows();
		return true;
	}

	Client c;

	public static void openInterface(Client c) {
		c.isBanking = true;
		c.storing = true;
		c.getItems().resetItems(5063);
		c.getItems().resetTempItems();
		refreshInterface(c);
		c.getPA().interfaceWithInventory(2700, 5063);
	}

	public static void refreshInterface(Client c) {
		for (int k = 0; k < 29; k++) {
			if (c.storeditems[k] > 0) {
				c.getPA().Frame34(2702, c.storeditems[k], k, 1);
			}

			if (c.storeditems[k] <= 0) {
				c.getPA().Frame34(2702, -1, k, 1);
			}

		}
	}

	public static void storeItem(Client c, int itemID, int fromSlot, int amount) {
		if (c.storing) {
			if (c.occupied[c.summoningslot] == false
					&& c.maxstore > c.summoningslot
					&& c.maxstore != c.totalstored
					&& !c.getItems().isStackable(itemID)) {
				for (int i : Config.ITEM_SELLABLE) {
					if (i == itemID) {
						c.sendMessage("You can't store untradeable item: "
								+ c.getItems().getItemName(itemID)
										.toLowerCase() + ".");
						openInterface(c);
						return;
					}
				}

				for (int i : Config.ITEM_TRADEABLE) {
					if (i == itemID) {
						c.sendMessage("You can't store untradeable item: "
								+ c.getItems().getItemName(itemID)
										.toLowerCase() + ".");
						openInterface(c);
						return;
					}
				}

				for (int i : Config.RUNE_ECC) {
					if (i == itemID) {
						c.sendMessage("You can't store untradeable item: "
								+ c.getItems().getItemName(itemID)
										.toLowerCase() + ".");
						openInterface(c);
						return;
					}
				}

				if (c.getItems().getItemAmount(itemID) < amount) {
					amount = c.getItems().getItemAmount(itemID);
					openInterface(c);
				}

				if (Item.itemStackable[itemID]) {
					c.sendMessage("You can't store - Stackable items.");
					openInterface(c);
					return;
				}

				if (itemID == 995) {
					c.sendMessage("You can't store money.");
					openInterface(c);
					return;
				}
				if (itemID == 1436) {
					c.sendMessage("You can't store - Rune eccense.");
					openInterface(c);
					return;
				}

				if (!c.getItems().playerHasItem(itemID, amount, fromSlot)) {
					openInterface(c);
					return;
				}

				if (c.maxstore() <= c.totalstored()) {
					c.sendMessage("You cannot store anymore");
					openInterface(c);
					return;
				}

				if (c.getItems().isStackable(itemID)) {

					c.firstSlot(itemID);
					if (c.maxstore() <= c.totalstored()) {
						c.sendMessage("You cannot store anymore");
						openInterface(c);
						return;
					}

					c.getPA().Frame34(2702, itemID, c.summoningslot, amount);
					c.getItems().deleteItem(itemID, amount);
					c.storeditems[c.summoningslot] = itemID;
					if (c.amount[c.summoningslot] < 0) {
						c.amount[c.summoningslot] = 0;
					}
					c.amount[c.summoningslot] = c.amount[c.summoningslot]
							+ amount;
					c.getBank().resetTempItems();
					c.occupied[c.summoningslot] = true;
					resetFrame(c);
					return;
				} else if (!c.getItems().isStackable(itemID)) {

					for (int i = 0; i < amount; i++) {
						if (c.maxstore() <= c.totalstored()) {
							c.sendMessage("You cannot store anymore");
							return;
						}
						c.firstSlot(itemID);
						c.getPA().Frame34(2702, itemID, c.summoningslot, 1);
						// c.sendMessage("Non-Stackable "+c.summoningslot);
						c.getItems().deleteItem(itemID, 1);
						c.occupied[c.summoningslot] = true;
						c.storeditems[c.summoningslot] = itemID;
						c.amount[c.summoningslot] = amount;
						c.summoningslot += 1;
						c.totalstored += 1;
						c.getBank().resetTempItems();
						resetFrame(c);

					}
				}

			}
		}

	}

	public static void resetFrame(Client c) {
		for (int k = 0; k < 28; k++) {
			if (c.storeditems[k] > 0) {
				c.getPA().Frame34(2702, c.storeditems[k], k, c.amount[k]);
			}

			if (c.storeditems[k] <= 0) {
				c.getPA().Frame34(2702, -1, k, c.amount[k]);
			}

		}
	}

	public int itemID;

	public int amount;

	public int fromSlot;

	public int req;
	
	public int pouchreq;

	public Summoning(Client c) {
		this.c = c;
	}

	public void ItemonItem(int itemUsed, int useWith) {
	}

	public void removeItems() {
		for (int i = 0; i < 29; i += 1) {
			if (NPCHandler.npcs[c.summoningnpcid] != null) {
				Server.itemHandler.createGroundItem(c, c.storeditems[i],
						NPCHandler.npcs[c.summoningnpcid].absX,
						NPCHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
				c.storeditems[i] = -1;
				// c.occupied[i] = false;
			}
		}
	}

	public void store() {
		if (c.InDung()) {
			c.sendMessage("You can't do this in dung idiot!");
			return;
		}
		if (c.inTrade) {
			c.sendMessage("You can't do this in a Trade!");
			c.getTradeAndDuel().declineTrade();
			c.getTradeAndDuel().declineDuel();
			return;
		}
		if (c.tradeStatus == 1) {
			c.getTradeAndDuel().declineTrade();
			return;
		}
		if (c.duelStatus == 1) {
			c.getTradeAndDuel().declineDuel();
			return;
		}
		openInterface(c);
	}

	public void SummonNewNPC(int npcID) {
		if (c.InDung()) {
			c.sendMessage("You can't do this here!");
			return;
		}
		if (c.isInPrivCon()) {
			c.sendMessage("You can't do this here!");
			return;
		}
		int maxhit = 0;
		int attack = 0;
		int defence = 0;

		switch (npcID) {
		case 6830:
			c.summonTime = -1;
			maxhit = 4;
			attack = 10;
			defence = 80;

			break;

		case 6826:
			c.summonTime = -1;
			maxhit = 6;
			attack = 10;
			defence = 80;
			break;

		case 6842:
			c.summonTime = -1;
			maxhit = 6;
			attack = 10;
			defence = 80;
			break;

		case 6807:
			c.summonTime = -1;
			maxhit = 5;
			attack = 20;
			defence = 80;
			c.maxstore = 3;
			c.summonTime = 1200;
			break;

		case 6797:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 7332:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 6832:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 6838:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 7362:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 6848:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 6995:
			c.summonTime = -1;
			maxhit = 10;
			attack = 20;
			defence = 80;
			break;

		case 6872:
			c.summonTime = -1;
			maxhit = 10;
			attack = 20;
			defence = 80;
			break;

		case 7354:
			c.summonTime = -1;
			maxhit = 11;
			attack = 20;
			defence = 80;
			break;

		case 6836:
			c.summonTime = -1;
			maxhit = 12;
			attack = 20;
			defence = 80;
			break;

		case 6846:
			c.summonTime = -1;
			maxhit = 14;
			attack = 40;
			defence = 80;
			break;

		case 6808:
			c.summonTime = -1;
			maxhit = 12;
			attack = 40;
			defence = 80;
			;
			break;

		case 7371:
			c.summonTime = -1;
		case 7369:
			c.summonTime = -1;
		case 7368:
			c.summonTime = -1;
		case 7370:
			c.summonTime = -1;
		case 7352:
			c.summonTime = -1;
			maxhit = 11;
			attack = 40;
			defence = 80;
			break;

		case 6854:
			c.summonTime = -1;
		case 68:
			c.summonTime = -1;
			maxhit = 12;
			attack = 40;
			defence = 80;
			break;

		case 6868:
			c.summonTime = -1;
			maxhit = 12;
			attack = 40;
			defence = 80;
			c.maxstore = 6;
			c.summonTime = 2400;
			break;

		case 6852:
			c.summonTime = -1;
			maxhit = 8;
			attack = 40;
			defence = 80;
			break;
		case 6834:
			c.summonTime = -1;
			maxhit = 14;
			attack = 40;
			defence = 80;
			break;

		case 6856:
			c.summonTime = -1;
			maxhit = 15;
			attack = 40;
			defence = 80;
			break;

		case 7378:
			c.summonTime = -1;
			maxhit = 14;
			attack = 40;
			defence = 80;
			break;

		case 6824:
			c.summonTime = -1;
			maxhit = 13;
			attack = 40;
			defence = 80;
			break;

		case 6844:
			c.summonTime = -1;
			maxhit = 12;
			attack = 40;
			defence = 80;
			break;

		case 6795:
			c.summonTime = -1;
			c.maxstore = 12;
			maxhit = 11;
			attack = 60;
			defence = 80;
			c.summonTime = 3000;
			break;

		case 6819:
			c.summonTime = -1;
			maxhit = 13;
			attack = 60;
			defence = 80;
			break;
		case 6993:
			c.summonTime = -1;
			maxhit = 15;
			attack = 60;
			defence = 80;
			break;

		case 6858:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6991:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 7364:
			c.summonTime = -1;
		case 7366:
			c.summonTime = -1;
		case 7338:
			c.summonTime = -1;
			maxhit = 20;
			attack = 60;
			defence = 80;
			break;

		case 6810:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6821:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6803:
			c.summonTime = -1;
			maxhit = 14;
			attack = 60;
			defence = 80;
			break;

		case 6828:
			c.summonTime = -1;
			maxhit = 18;
			attack = 60;
			defence = 80;
			break;

		case 6860:
			c.summonTime = -1;
			maxhit = 20;
			attack = 60;
			defence = 80;
			break;

		case 6890:
			c.summonTime = -1;
			maxhit = 20;
			attack = 60;
			defence = 80;
			break;

		case 6816:
			c.maxstore = 18;
			c.summonTime = 3800; // kills the npc - 60*2 = 120 = 1 minute
			maxhit = 21;
			attack = 60;
			defence = 80;
			break;

		case 6814:
			c.summonTime = -1;
			maxhit = 17;
			attack = 60;
			defence = 80;
			break;

		case 7372:
			c.summonTime = -1;
		case 7373:
			c.summonTime = -1;
		case 7374:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6840:
			c.summonTime = -1;
			pouchreq = 71;
			break;

		case 6817:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;
		case 8576:
			c.summonTime = -1;
			pouchreq = 99;
			break;

		case 7346:
			c.summonTime = -1;
			maxhit = 25;
			attack = 80;
			defence = 80;
			break;

		case 6799:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6850:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6862:
			c.summonTime = -1;
			maxhit = 22;
			attack = 60;
			defence = 80;
			break;

		case 7336:
			c.summonTime = -1;
			maxhit = 24;
			attack = 60;
			defence = 80;
			break;

		case 6801:
			maxhit = 11;
			attack = 60;
			defence = 80;
			c.summonTime = 3040;
			break;

		case 7356:
		case 7358:
		case 7360:
			maxhit = 26;
			attack = 60;
			defence = 80;
			c.summonTime = 4440;
			break;

		case 6812:
			c.summonTime = -1;
			maxhit = 28;
			attack = 60;
			defence = 80;
			break;

		case 6805:
		case 7342:
			maxhit = 30;
			attack = 60;
			defence = 80;
			c.summonTime = 4000;
			break;

		case 7330:
			maxhit = 31;
			attack = 60;
			defence = 80;
			c.summonTime = 4100;
			break;
		case 6864:
			maxhit = 32;
			attack = 60;
			defence = 80;
			c.summonTime = 4100;
			break;
		case 6823:
			maxhit = 33;
			attack = 60;
			defence = 80;
			c.summonTime = 2400;
			break;
		case 7340:
			maxhit = 34;
			attack = 60;
			defence = 80;
			c.summonTime = 3840;
			break;

		case 6870:
			maxhit = 35;
			attack = 60;
			defence = 80;
			c.summonTime = 3240;
			break;

		case 7350:
			maxhit = 36;
			attack = 60;
			defence = 80;
			c.summonTime = 4200;
			break;

		case 7376:
			maxhit = 37;
			attack = 60;
			defence = 80;
			break;
		case 6874:
			c.maxstore = 28;
			c.summonTime = 4800;
			maxhit = 38;
			attack = 60;
			defence = 80;
			break;
		case 7344:
			maxhit = 39;
			attack = 90;
			defence = 80;
			c.summonTime = 6000;
			break;
		}
		switch (npcID) {
		case 6830:
			c.summonTime = -1;
			pouchreq = 0;
			break;

		case 6826:
			c.summonTime = -1;
			pouchreq = 4;
			break;

		case 6842:
			c.summonTime = -1;
			pouchreq = 10;
			break;

		case 6807:
			c.summonTime = -1;
			pouchreq = 13;
			break;

		case 6797:
			c.summonTime = -1;
			pouchreq = 16;
			break;

		case 7332:
			c.summonTime = -1;
			pouchreq = 17;
			break;

		case 6832:
			c.summonTime = -1;
			pouchreq = 18;
			break;

		case 6838:
			c.summonTime = -1;
			pouchreq = 19;
			break;

		case 7362:
			c.summonTime = -1;
			pouchreq = 22;
			break;

		case 6848:
			c.summonTime = -1;
			pouchreq = 23;
			break;

		case 6995:
			c.summonTime = -1;
			pouchreq = 25;
			break;

		case 6872:
			c.summonTime = -1;
			pouchreq = 28;
			break;

		case 7354:
			c.summonTime = -1;
			pouchreq = 29;
			break;

		case 6836:
			c.summonTime = -1;
			pouchreq = 31;
			break;

		case 6846:
			c.summonTime = -1;
			pouchreq = 32;
			break;

		case 6808:
			c.summonTime = -1;
			pouchreq = 33;
			break;

		case 7371:
			c.summonTime = -1;
		case 7369:
			c.summonTime = -1;
		case 7368:
			c.summonTime = -1;
		case 7370:
			c.summonTime = -1;
		case 7352:
			c.summonTime = -1;
			pouchreq = 34;
			break;

		case 6854:
			c.summonTime = -1;
		case 68:
			c.summonTime = -1;
			pouchreq = 36;
			break;

		case 6868:
			c.summonTime = -1;
			pouchreq = 40;
			break;

		case 6852:
			c.summonTime = -1;
			pouchreq = 41;
			break;
		case 6834:
			c.summonTime = -1;
			pouchreq = 42;
			break;

		case 6856:
			c.summonTime = -1;
			pouchreq = 46;
			break;

		case 7378:
			c.summonTime = -1;
			pouchreq = 46;
			break;

		case 6824:
			c.summonTime = -1;
			pouchreq = 47;
			break;

		case 6844:
			c.summonTime = -1;
			pouchreq = 49;
			break;

		case 6795:
			c.summonTime = -1;
			pouchreq = 52;
			break;

		case 6819:
			c.summonTime = -1;
			pouchreq = 54;
			break;
		case 6993:
			c.summonTime = -1;
			pouchreq = 55;
			break;

		case 6858:
			c.summonTime = -1;
			pouchreq = 56;
			break;

		case 6991:
			c.summonTime = -1;
			pouchreq = 56;
			break;

		case 7364:
			c.summonTime = -1;
		case 7366:
			c.summonTime = -1;
		case 7338:
			c.summonTime = -1;
			pouchreq = 57;
			break;

		case 6810:
			c.summonTime = -1;
			pouchreq = 58;
			break;

		case 6866:
			c.summonTime = -1;
			pouchreq = 99;
			break;

		case 6821:
			c.summonTime = -1;
			pouchreq = 62;
			break;

		case 6803:
			c.summonTime = -1;
			pouchreq = 63;
			break;

		case 6828:
			c.summonTime = -1;
			pouchreq = 64;
			break;

		case 6860:
			c.summonTime = -1;
			pouchreq = 66;
			break;

		case 6890:
			c.summonTime = -1;
			pouchreq = 66;
			break;

		case 6816:
			c.summonTime = -1;
			pouchreq = 67;
			break;

		case 6814:
			c.summonTime = -1;
			pouchreq = 68;
			c.summonTime = 2000;
			break;

		case 7372:
			c.summonTime = -1;
		case 7373:
			c.summonTime = -1;
		case 7374:
			c.summonTime = -1;
			pouchreq = 70;
			break;

		case 6840:
			c.summonTime = -1;
			pouchreq = 71;
			break;

		case 6817:
			c.summonTime = -1;
			pouchreq = 69;
			break;
		case 8576:
			c.summonTime = -1;
			pouchreq = 99;
			break;

		case 7346:
			c.summonTime = -1;
			pouchreq = 73;
			break;

		case 6799:
			c.summonTime = -1;
			pouchreq = 75;
			break;

		case 6850:
			c.summonTime = -1;
			pouchreq = 74;
			break;

		case 6862:
			c.summonTime = -1;
			pouchreq = 76;
			break;

		case 7336:
			c.summonTime = -1;
			pouchreq = 76;
			break;

		case 6801:
			c.summonTime = -1;
			pouchreq = 78;
			break;

		case 7356:
			c.summonTime = -1;
		case 7358:
			c.summonTime = -1;
		case 7360:
			c.summonTime = -1;
			pouchreq = 79;
			break;

		case 6812:
			c.summonTime = -1;
			pouchreq = 80;
			break;

		case 6805:
			c.summonTime = -1;
		case 7342:
			c.summonTime = -1;
			pouchreq = 83;
			break;

		case 7330:
			c.summonTime = -1;
			pouchreq = 85;
			break;
		case 6864:
			c.summonTime = -1;
			pouchreq = 86;
			break;
		case 6823:
			c.summonTime = -1;
			pouchreq = 88;
			break;
		case 7340:
			c.summonTime = -1;
			pouchreq = 89;
			break;

		case 6870:
			c.summonTime = -1;
			pouchreq = 92;
			break;

		case 7350:
			c.summonTime = -1;
			pouchreq = 93;
			break;

		case 7376:
			c.summonTime = -1;
			pouchreq = 95;
			break;
		case 6874:
			c.summonTime = -1;
			pouchreq = 96;
			break;
		case 7344:
			c.summonTime = -1;
			pouchreq = 99;
			break;
		}
		if (c.playerLevel[21] >= pouchreq) {
			Server.npcHandler.Summon(c, npcID, c.absX, c.absY - 2,
					c.heightLevel, 0, 100, maxhit, false, attack, defence);
			if (npcID == 6830 && !c.task1[6]) {
				c.sendMessage("You've completed the task: Summon a Spirit Wolf!");
				c.task1[6] = true;
				c.TPoints += 1;
				if (c.TPoints == 1) {
					c.sendMessage("You've received a Task Point! You now have "
							+ c.TPoints + " point!");
				} else {
					c.sendMessage("You've received a Task Point! You now have a total of "
							+ c.TPoints + " points!");
				}
				c.achievementInterface("Summon a Spirit Wolf!");
			}
			c.sumfam += 1;
			if (c.sumfam >= 150 && !c.task3[5]) {
				GabbesAchievements.handleEliteTask(c, 5, 3,
						"Summon 150 Familiars!");
			}
			c.getItems().deleteItem(c.s, 1);
			for (int i = 0; i < NPCHandler.maxNPCs; i++) {
				if (NPCHandler.npcs[i] != null) {
					c.npcslot = NPCHandler.npcs[i].npcId;
				}
				// c.getPA().sendFrame126("   " + c.playerLevel[21] + "/"+
				// c.getLevelForXP(c.playerXP[21]), 17025);
				// c.getPA().sendFrame126("" + c.summonTime / 120 +
				// ".00 Min",17021);
				if (c.hasFollower == 6870 || c.hasFollower == 6814) {
					// c.startEvent5(c);
				}
				if (c.summonTime == -1) {
					c.summonTime = 0;
				}
			}
		} else {
			c.sendMessage("You need " + pouchreq
					+ " Summoning to summon this monster.");
		}
	}

	/**/
	/***
	 * Fixes the deleteing pouch problem, when a new npc is summoned because the
	 * player has teleported! /
	 **/

	public void summonNulledNPC(int npcID) {
		if (c.InDung()) {
			c.sendMessage("You can't do this in dung!");
			return;
		}
		int maxhit = 0;
		int attack = 0;
		int defence = 0;
		// c.getPA().sendFrame75(c.hasFollower, 17027);

		switch (npcID) {
		case 6830:
			c.summonTime = -1;
			maxhit = 4;
			attack = 10;
			defence = 80;

			break;

		case 6826:
			c.summonTime = -1;
			maxhit = 6;
			attack = 10;
			defence = 80;
			break;

		case 6842:
			c.summonTime = -1;
			maxhit = 6;
			attack = 10;
			defence = 80;
			break;

		case 6807:
			c.summonTime = -1;
			maxhit = 5;
			attack = 20;
			defence = 80;
			c.maxstore = 3;
			c.summonTime = 1200;
			break;

		case 6797:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 7332:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 6832:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 6838:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 7362:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 6848:
			c.summonTime = -1;
			maxhit = 8;
			attack = 20;
			defence = 80;
			break;

		case 6995:
			c.summonTime = -1;
			maxhit = 10;
			attack = 20;
			defence = 80;
			break;

		case 6872:
			c.summonTime = -1;
			maxhit = 10;
			attack = 20;
			defence = 80;
			break;

		case 7354:
			c.summonTime = -1;
			maxhit = 11;
			attack = 20;
			defence = 80;
			break;

		case 6836:
			c.summonTime = -1;
			maxhit = 12;
			attack = 20;
			defence = 80;
			break;

		case 6846:
			c.summonTime = -1;
			maxhit = 14;
			attack = 40;
			defence = 80;
			break;

		case 6808:
			c.summonTime = -1;
			maxhit = 12;
			attack = 40;
			defence = 80;
			;
			break;

		case 7371:
			c.summonTime = -1;
		case 7369:
			c.summonTime = -1;
		case 7368:
			c.summonTime = -1;
		case 7370:
			c.summonTime = -1;
		case 7352:
			c.summonTime = -1;
			maxhit = 11;
			attack = 40;
			defence = 80;
			break;

		case 6854:
			c.summonTime = -1;
		case 68:
			c.summonTime = -1;
			maxhit = 12;
			attack = 40;
			defence = 80;
			break;

		case 6868:
			c.summonTime = -1;
			maxhit = 12;
			attack = 40;
			defence = 80;
			c.maxstore = 6;
			c.summonTime = 2400;
			break;

		case 6852:
			c.summonTime = -1;
			maxhit = 8;
			attack = 40;
			defence = 80;
			break;
		case 6834:
			c.summonTime = -1;
			maxhit = 14;
			attack = 40;
			defence = 80;
			break;

		case 6856:
			c.summonTime = -1;
			maxhit = 15;
			attack = 40;
			defence = 80;
			break;

		case 7378:
			c.summonTime = -1;
			maxhit = 14;
			attack = 40;
			defence = 80;
			break;

		case 6824:
			c.summonTime = -1;
			maxhit = 13;
			attack = 40;
			defence = 80;
			break;

		case 6844:
			c.summonTime = -1;
			maxhit = 12;
			attack = 40;
			defence = 80;
			break;

		case 6795:
			c.summonTime = -1;
			c.maxstore = 12;
			maxhit = 11;
			attack = 60;
			defence = 80;
			c.summonTime = 3000;
			break;

		case 6819:
			c.summonTime = -1;
			maxhit = 13;
			attack = 60;
			defence = 80;
			break;
		case 6993:
			c.summonTime = -1;
			maxhit = 15;
			attack = 60;
			defence = 80;
			break;

		case 6858:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6991:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 7364:
			c.summonTime = -1;
		case 7366:
			c.summonTime = -1;
		case 7338:
			c.summonTime = -1;
			maxhit = 20;
			attack = 60;
			defence = 80;
			break;

		case 6810:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6821:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6803:
			c.summonTime = -1;
			maxhit = 14;
			attack = 60;
			defence = 80;
			break;

		case 6828:
			c.summonTime = -1;
			maxhit = 18;
			attack = 60;
			defence = 80;
			break;

		case 6860:
			c.summonTime = -1;
			maxhit = 20;
			attack = 60;
			defence = 80;
			break;

		case 6890:
			c.summonTime = -1;
			maxhit = 20;
			attack = 60;
			defence = 80;
			break;

		case 6816:
			c.summonTime = -1;
			c.maxstore = 18;
			c.summonTime = 3800; // kills the npc - 60*2 = 120 = 1 minute
			maxhit = 21;
			attack = 60;
			defence = 80;
			break;

		case 6814:
			c.summonTime = -1;
			maxhit = 17;
			attack = 60;
			defence = 80;
			break;

		case 7372:
			c.summonTime = -1;
		case 7373:
			c.summonTime = -1;
		case 7374:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6840:
			c.summonTime = -1;
			pouchreq = 71;
			break;

		case 6817:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;
		case 8576:
			c.summonTime = -1;
			pouchreq = 99;
			break;

		case 7346:
			c.summonTime = -1;
			maxhit = 25;
			attack = 80;
			defence = 80;
			break;

		case 6799:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6850:
			c.summonTime = -1;
			maxhit = 11;
			attack = 60;
			defence = 80;
			break;

		case 6862:
			c.summonTime = -1;
			maxhit = 22;
			attack = 60;
			defence = 80;
			break;

		case 7336:
			c.summonTime = -1;
			maxhit = 24;
			attack = 60;
			defence = 80;
			break;

		case 6801:
			maxhit = 11;
			attack = 60;
			defence = 80;
			c.summonTime = 3040;
			break;

		case 7356:
		case 7358:
		case 7360:
			maxhit = 26;
			attack = 60;
			defence = 80;
			c.summonTime = 4440;
			break;

		case 6812:
			c.summonTime = -1;
			maxhit = 28;
			attack = 60;
			defence = 80;
			break;

		case 6805:
		case 7342:
			maxhit = 30;
			attack = 60;
			defence = 80;
			c.summonTime = 4000;
			break;

		case 7330:
			maxhit = 31;
			attack = 60;
			defence = 80;
			c.summonTime = 4100;
			break;
		case 6864:
			maxhit = 32;
			attack = 60;
			defence = 80;
			c.summonTime = 4100;
			break;
		case 6823:
			maxhit = 33;
			attack = 60;
			defence = 80;
			c.summonTime = 2400;
			break;
		case 7340:
			maxhit = 34;
			attack = 60;
			defence = 80;
			c.summonTime = 3840;
			break;

		case 6870:
			maxhit = 35;
			attack = 60;
			defence = 80;
			c.summonTime = 3240;
			break;

		case 7350:
			maxhit = 36;
			attack = 60;
			defence = 80;
			c.summonTime = 4200;
			break;

		case 7376:
			maxhit = 37;
			attack = 60;
			defence = 80;
			break;
		case 6874:
			c.maxstore = 28;
			c.summonTime = 4800;
			maxhit = 38;
			attack = 60;
			defence = 80;
			break;
		case 7344:
			maxhit = 39;
			attack = 90;
			defence = 80;
			c.summonTime = 6000;
			break;
		}
		switch (npcID) {
		case 6830:
			c.summonTime = -1;
			pouchreq = 0;
			break;

		case 6826:
			c.summonTime = -1;
			pouchreq = 4;
			break;

		case 6842:
			c.summonTime = -1;
			pouchreq = 10;
			break;

		case 6807:
			c.summonTime = -1;
			pouchreq = 13;
			break;

		case 6797:
			c.summonTime = -1;
			pouchreq = 16;
			break;

		case 7332:
			c.summonTime = -1;
			pouchreq = 17;
			break;

		case 6832:
			c.summonTime = -1;
			pouchreq = 18;
			break;

		case 6838:
			c.summonTime = -1;
			pouchreq = 19;
			break;

		case 7362:
			c.summonTime = -1;
			pouchreq = 22;
			break;

		case 6848:
			c.summonTime = -1;
			pouchreq = 23;
			break;

		case 6995:
			c.summonTime = -1;
			pouchreq = 25;
			break;

		case 6872:
			c.summonTime = -1;
			pouchreq = 28;
			break;

		case 7354:
			c.summonTime = -1;
			pouchreq = 29;
			break;

		case 6836:
			c.summonTime = -1;
			pouchreq = 31;
			break;

		case 6846:
			c.summonTime = -1;
			pouchreq = 32;
			break;

		case 6808:
			c.summonTime = -1;
			pouchreq = 33;
			break;

		case 7371:
			c.summonTime = -1;
		case 7369:
			c.summonTime = -1;
		case 7368:
			c.summonTime = -1;
		case 7370:
			c.summonTime = -1;
		case 7352:
			c.summonTime = -1;
			pouchreq = 34;
			break;

		case 6854:
			c.summonTime = -1;
		case 68:
			c.summonTime = -1;
			pouchreq = 36;
			break;

		case 6868:
			c.summonTime = -1;
			pouchreq = 40;
			break;

		case 6852:
			c.summonTime = -1;
			pouchreq = 41;
			break;
		case 6834:
			c.summonTime = -1;
			pouchreq = 42;
			break;

		case 6856:
			c.summonTime = -1;
			pouchreq = 46;
			break;

		case 7378:
			c.summonTime = -1;
			pouchreq = 46;
			break;

		case 6824:
			c.summonTime = -1;
			pouchreq = 47;
			break;

		case 6844:
			c.summonTime = -1;
			pouchreq = 49;
			break;

		case 6795:
			c.summonTime = -1;
			pouchreq = 52;
			break;

		case 6819:
			c.summonTime = -1;
			pouchreq = 54;
			break;
		case 6993:
			c.summonTime = -1;
			pouchreq = 55;
			break;

		case 6858:
			c.summonTime = -1;
			pouchreq = 56;
			break;

		case 6991:
			c.summonTime = -1;
			pouchreq = 56;
			break;

		case 7364:
			c.summonTime = -1;
		case 7366:
			c.summonTime = -1;
		case 7338:
			c.summonTime = -1;
			pouchreq = 57;
			break;

		case 6810:
			c.summonTime = -1;
			pouchreq = 58;
			break;

		case 6866:
			c.summonTime = -1;
			pouchreq = 99;
			break;

		case 6821:
			c.summonTime = -1;
			pouchreq = 62;
			break;

		case 6803:
			c.summonTime = -1;
			pouchreq = 63;
			break;

		case 6828:
			c.summonTime = -1;
			pouchreq = 64;
			break;

		case 6860:
			c.summonTime = -1;
			pouchreq = 66;
			break;

		case 6890:
			c.summonTime = -1;
			pouchreq = 66;
			break;

		case 6816:
			c.summonTime = 3800;
			pouchreq = 67;
			break;

		case 6814:
			pouchreq = 68;
			c.summonTime = 2000;
			break;

		case 7372:
			c.summonTime = -1;
		case 7373:
			c.summonTime = -1;
		case 7374:
			c.summonTime = -1;
			pouchreq = 70;
			break;

		case 6840:
			c.summonTime = -1;
			pouchreq = 71;
			break;

		case 6817:
			c.summonTime = -1;
			pouchreq = 69;
			break;
		case 8576:
			c.summonTime = -1;
			pouchreq = 99;
			break;

		case 7346:
			c.summonTime = -1;
			pouchreq = 73;
			break;

		case 6799:
			c.summonTime = -1;
			pouchreq = 75;
			break;

		case 6850:
			c.summonTime = -1;
			pouchreq = 74;
			break;

		case 6862:
			c.summonTime = -1;
			pouchreq = 76;
			break;

		case 7336:
			c.summonTime = -1;
			pouchreq = 76;
			break;

		case 6801:
			c.summonTime = -1;
			pouchreq = 78;
			break;

		case 7356:
			c.summonTime = -1;
		case 7358:
			c.summonTime = -1;
		case 7360:
			c.summonTime = -1;
			pouchreq = 79;
			break;

		case 6812:
			c.summonTime = -1;
			pouchreq = 80;
			break;

		case 6805:
			c.summonTime = -1;
		case 7342:
			c.summonTime = -1;
			pouchreq = 83;
			break;

		case 7330:
			c.summonTime = -1;
			pouchreq = 85;
			break;
		case 6864:
			c.summonTime = -1;
			pouchreq = 86;
			break;
		case 6823:
			c.summonTime = -1;
			pouchreq = 88;
			break;
		case 7340:
			c.summonTime = -1;
			pouchreq = 89;
			break;

		case 6870:
			c.summonTime = -1;
			pouchreq = 92;
			break;

		case 7350:
			c.summonTime = -1;
			pouchreq = 93;
			break;

		case 7376:
			c.summonTime = -1;
			pouchreq = 95;
			break;
		case 6874:
			c.summonTime = -1;
			pouchreq = 96;
			break;
		case 7344:
			c.summonTime = -1;
			pouchreq = 99;
			break;
		}
		if (c.playerLevel[21] >= pouchreq) {
			Server.npcHandler.Summon(c, npcID, c.absX, c.absY - 2,
					c.heightLevel, 0, 100, maxhit, false, attack, defence);
			// c.getItems().deleteItem(c.s, 1);
			for (int i = 0; i < NPCHandler.maxNPCs; i++) {
				if (NPCHandler.npcs[i] != null) {
					c.npcslot = NPCHandler.npcs[i].npcId;
				}
				// c.getPA().sendFrame126("   " + c.playerLevel[21] + "/"+
				// c.getLevelForXP(c.playerXP[21]), 17025);
				// c.getPA().sendFrame126("" + c.summonTime / 120 +
				// ".00 Min",17021);
				if (c.hasFollower == 6870 || c.hasFollower == 6814) {
					// c.startEvent5(c);
				}
				if (c.summonTime == -1) {
					c.summonTime = 0;
				}
			}
		} else {
			c.sendMessage("You need " + pouchreq
					+ " Summoning to summon this monster.");
		}
	}
}
