package server.model.players;

import java.awt.Event;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Future;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

import server.Config;
import server.Connection;
import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.items.Banking;
import server.model.items.Item;
import server.model.items.ItemAssistant;
import server.model.minigames.Gambling;
import server.model.minigames.WarriorsGuild;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.players.content.BankPin;
import server.model.players.content.Dying;
import server.model.players.content.Food;
import server.model.players.content.GabbesAchievements;
import server.model.players.content.Potions;
import server.model.players.content.Timers;
import server.model.players.content.TradeLog;
import server.model.players.content.combat.CombatAssistant;
import server.model.players.content.combat.Curse;
import server.model.players.content.combat.DwarfMultiCannon;
import server.model.players.content.combat.PkRewardSystem;
import server.model.players.content.combat.PlayerKilling;
//import server.util.MadTurnipConnection;
import server.model.players.content.combat.BountyHunter.PvPHandler;
import server.model.players.content.skills.Agility;
import server.model.players.content.skills.Construction;
import server.model.players.content.skills.Crafting;
import server.model.players.content.skills.Dungeoneering;
import server.model.players.content.skills.Farming;
import server.model.players.content.skills.Fishing;
import server.model.players.content.skills.FlaxStringer;
import server.model.players.content.skills.Fletching;
import server.model.players.content.skills.Herblore;
import server.model.players.content.skills.Mining;
import server.model.players.content.skills.Prayer;
import server.model.players.content.skills.Prospecting;
import server.model.players.content.skills.Slayer;
import server.model.players.content.skills.Smithing;
import server.model.players.content.skills.SmithingInterface;
import server.model.players.content.skills.Summoning;
import server.model.players.content.skills.Thieving;
import server.model.players.content.skills.impl.Binding;
import server.model.players.content.skills.impl.PotionMixing;
import server.model.shops.ShopAssistant;
import server.model.sounds.Sound;
import server.net.Packet;
import server.net.Packet.Type;
import server.util.Misc;
import server.util.Stream;
import server.world.LoginHandler;

public class Client extends Player {
	public int mineID = -1;
	public int chatId = -1;
	public boolean clickedGate = false;
	public boolean searchingBank = false;
	private DwarfMultiCannon cannon = new DwarfMultiCannon(this);

	public boolean hasCannon = false;

	public boolean cannonIsShooting = false;
	public int soulSplitHeal = 0;

	private PkRewardSystem pkReward = new PkRewardSystem(this);

	public String customYellTag = "Premium Donator";

	public int payButlerReq = 0;

	public int payment = -1;

	public boolean mustPay = false;

	public int npcFDelay = 0;
	private Queue<Packet> queuedPackets = new LinkedList<Packet>();
	public int overload;
	private final Binding binding = new Binding();

	/**
	 * The queue of pending {@link Event}s.
	 */
	/*
	 * private final BlockingQueue<Packet> eventQueue = new
	 * ArrayBlockingQueue<Packet>(Config.MAX_PACKET_CONSTANT); public void
	 * processomg() { Packet p; timeOutCounter++; synchronized (eventQueue) {
	 * while ((p = eventQueue.poll()) != null) { try { inStream.currentOffset =
	 * 0; packetType = p.getId(); packetSize = p.getLength(); inStream.buffer =
	 * p.getData(); if (packetType > 0) { boolean continueLoop =
	 * PacketHandler.processPacket(this, packetType, packetSize);
	 * if(!continueLoop) { break; } } timeOutCounter = 0; } catch (Exception e)
	 * { break; } } }
	 * 
	 * }
	 */
	public boolean inDuelScreen;

	public String duelName;

	public int duelLevel;

	public boolean lostDuel;

	public int floweritem = 0;

	public int seedtimer = 0;

	public boolean inParty = false;

	private Dungeoneering dungeoneering = new Dungeoneering(this);

	public byte buffer[] = null;

	public int cannonTimer = 0;

	public int s;

	public Stream inStream = null, outStream = null;

	public static PlayerSave save;

	public static Client cliento2;

	public int totalstored;
	public int currentDamage = 0;
	public int followPlayer;
	public boolean callFamilliar;

	public boolean selectStarter = false;
	public int hasFollower = -1;

	public int butler = -1;

	public int npcslot;

	public int summoningnpcid;

	public int timer;

	private TradeLog tradeLog = new TradeLog(this);
	private ItemAssistant itemAssistant = new ItemAssistant(this);
	private ShopAssistant shopAssistant = new ShopAssistant(this);
	private TradeAndDuel tradeAndDuel = new TradeAndDuel(this);
	private PlayerAssistant playerAssistant = new PlayerAssistant(this);
	private CombatAssistant combatAssistant = new CombatAssistant(this);
	private ActionHandler actionHandler = new ActionHandler(this);
	private PlayerKilling playerKilling = new PlayerKilling(this);
	private DialogueHandler dialogueHandler = new DialogueHandler(this);
	private Potions potion = new Potions(this);
	private WarriorsGuild warriorsGuild = new WarriorsGuild();
	private Timers timers = new Timers();
	private PotionMixing potionMixing = new PotionMixing(this);
	private Food food = new Food(this);
	private Gambling gamble = new Gambling(this);

	public boolean isSearching;
	public boolean lastSearch;
	public boolean isSearching2 = true;
	public int[] items = new int[500];
	public int[] itemsN = new int[500];// the amount of the item
	public String searchName;
	/**
	 * Skill instances
	 */
	private Slayer slayer = new Slayer(this);
	private Banking bank = new Banking(this);
	// private Runecrafting runecrafting = new Runecrafting(this);
	// private Woodcutting woodcutting = new Woodcutting(this);
	private Mining mine = new Mining(this);
	public int interfaceIdOpen;
	public Agility ag = new Agility(this);
	// private Cooking cooking = new Cooking(this);
	private Fishing fish = new Fishing(this);
	private Crafting crafting = new Crafting(this);
	private BankPin bankpin = new BankPin(this);
	private Smithing smith = new Smithing(this);
	private Prayer prayer = new Prayer(this);
	private Curse curse = new Curse(this);
	private Fletching fletching = new Fletching(this);
	private SmithingInterface smithInt = new SmithingInterface(this);
	private Farming farming = new Farming(this);
	private Thieving thieving = new Thieving(this);
	// private Firemaking firemaking = new Firemaking(this);
	private FlaxStringer flax = new FlaxStringer(this);
	private Herblore herblore = new Herblore(this);
	public Summoning Summoning = new Summoning(this);
	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;
	public int dungRest = 0;
	public int returnCode = 2;
	public int clawDamage;
	public int clawIndex;
	public int clawType = 0;
	private Future<?> currentTask;
	public boolean officialClient = true;
	public boolean basket = false;
	public boolean slayerHelmetEffect;
	public String lastKilled = "";
	public int been = 0;
	public boolean inFoGHouse = false;
	public int adventureLogs = 0;
	int[] randomreward2 = { 4151, 4151, 4131, 4131, 4131, 4093, 4093, 4093,
			4087, 4087, 4087, 2577, 2577, 2577, 5525, 5525, 5525, 4734, 4720,
			4730, 4745, 4712, 4753, 4755, 4738, 6524, 6524, 6524, 6524, 6524,
			6524, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6585, 6585,
			6731, 6731, 6731, 6731, 6731, 6733, 6733, 6733, 6733, 6733, 6733,
			6733, 6733, 6735, 6735, 6735, 6735, 6735, 6735, 6735, 6737, 6737,
			6737, 6737, 6737, 6737, 6737, 6739, 6739, 6739, 6739, 6739, 6739,
			6739, 6739, 6739, 6739, 6739, 6739, 15312, 15312, 15312, 15312,
			15312, 15312, 15312, 15312, 15312, 15312, 15312, 15312, 15315,
			15315, 11690, 11690, 11690, 11690, 11690, 11690, 11690, 11690,
			11690, 11690, 11690, 11690, 11690, 3140, 3140, 3140, 3140, 3140,
			3140, 3140, 3140 };
	public static int sendTrade11 = 0;
	public static int auraTimer = 0; // Sharpshooter
	public static int auraTimer2 = 0; // Asura
	public static int CoolDownTimer = 0; // Sharpshooter
	public static int CoolDownTimer2 = 0; // Asura
	public int maxstore = 0;
	public int summoningslot = 0;
	public int storeditems[] = new int[30];
	public int amount[] = new int[30];
	public boolean occupied[] = new boolean[30];
	private Channel session;
	public int[] pvpHelms = new int[4];
	public int[] pvpChests = new int[4];

	public int[] pvpLegs = new int[4];

	public int[] pvpWeapons = new int[4];

	public boolean picking = false;

	public boolean storing = false;

	public int attackingplayer;

	public boolean summon;

	public boolean eventRunning2 = false;
	public int soundVolume = 10;

	private Sound sound = new Sound(this);

	public boolean specGfx = false;

	public int specRestore = 0;

	public int currentRegion = 0;

	public static final int PACKET_SIZES[] = { 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, // 0
			0, 0, 0, 0, 8, 0, 6, 2, 2, 0, // 10
			0, 2, 0, 6, 0, 12, 0, 0, 0, 0, // 20
			0, 0, 0, 0, 0, 8, 4, 0, 0, 2, // 30
			2, 6, 0, 6, 0, -1, 0, 0, 0, 0, // 40
			0, 0, 0, 12, 0, 0, 0, 8, 8, 12, // 50
			8, 8, 0, 0, 0, 0, 0, 0, 0, 0, // 60
			6, 0, 2, 2, 8, 6, 0, -1, 0, 6, // 70
			0, 0, 0, 0, 0, 1, 4, 6, 0, 0, // 80
			0, 0, 0, 0, 0, 3, 0, 0, -1, 0, // 90
			0, 13, 0, -1, 0, 0, 0, 0, 0, 0,// 100
			0, 0, 0, 0, 0, 0, 0, 6, 0, 0, // 110
			1, 0, 6, 0, 0, 0, -1, 0, 2, 6, // 120
			0, 4, 6, 8, 0, 6, 0, 0, 0, 2, // 130
			0, 0, 0, 0, 0, 6, 0, 0, 0, 0, // 140
			0, 0, 1, 2, 0, 2, 6, 0, 0, 0, // 150
			0, 0, 0, 0, -1, -1, 0, 0, 0, 0,// 160
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
			0, 8, 0, 3, 0, 2, 0, 0, 8, 1, // 180
			0, 0, 12, 0, 0, 0, 0, 0, 0, 0, // 190
			2, 0, 0, 0, 0, 0, 0, 0, 4, 0, // 200
			4, 0, 0, 0, 7, 8, 0, 0, 10, 0, // 210
			0, 0, 0, 0, 0, 0, -1, 0, 6, 0, // 220
			1, 0, 0, 0, 6, 0, 6, 8, 1, 0, // 230
			0, 4, 0, 0, 0, 0, -1, 0, -1, 4,// 240
			0, 0, 6, 6, 0, 0, 0 // 250
	};

	public boolean doNotCCLog = false;

	public String[] qCS = { "Attack", "Strength", "Defence", "Ranged",
			"Prayer", "Magic", "Runecrafting", "Hitpoint", "Agility",
			"Herblore", "Thieving", "Crafting", "Fletching", "Slayer",
			"Mining", "Smithing", "Fishing", "Cooking", "Firemaking",
			"Woodcutting", "Farming" };

	public int[][] qCAB = { { 33206, 0 }, { 33209, 2 }, { 33212, 1 },
			{ 33215, 4 }, { 33218, 5 }, { 33221, 6 }, { 33224, 20 },
			{ 33207, 3 }, { 33210, 16 }, { 33213, 15 }, { 33216, 17 },
			{ 33219, 12 }, { 33222, 9 }, { 47130, 18 }, { 33208, 14 },
			{ 33211, 13 }, { 33214, 10 }, { 33217, 7 }, { 33220, 11 },
			{ 33223, 8 }, { 54104, 19 } };

	public String qC = "[Quick Chat] ";

	public int spellID = 0;

	public int huntIMPY = 0;

	public int huntIMPX = 0;

	public int pcPoints = 0;

	public int interfaceButtonAction = 0;

	public static int amount2;

	public static int item2;

	public static int rFruit() {
		return FRUIT[(int) (Math.random() * FRUIT.length)];
	}

	public boolean claimLater = false;

	public boolean antiFuckUp = false;

	public boolean needsItems = false;
	public boolean antiFuckUp2 = false;
	public boolean leave = false;
	public int logId = 0;
	public int logsOffered = 0;

	public boolean addedItems = false;

	public boolean isMuted = false;

	public boolean doingTut = false;

	public boolean doTut = false;

	public boolean getIt = false;

	public int dropItemReceived = 0;

	public int dropItemReceivedRare = 0;

	public int dropItemReceivedRareAmount = 0;

	public int timeOnline = 0;

	public static boolean stopEventCannon = false;

	public static boolean restart = false;

	public boolean bankCannonBalls = false;

	public int packetSize = 0, packetType = -1;

	public long saveGameDelay;

	public static int processCounter = 0;

	public static int EPCounter = 0;
	public boolean needsToSpawn;
	public int healEventCounter = 0;
	public int printed = 0;

	public int randomEventTimer = 0; // Random event timer

	public int random = Misc.random(10); // Random event randomizer..

	public int banTimer = 0; // Timer for clan bans

	public int fogtimer = 0; // Timer for fog

	public boolean invViewEnabled = true;

	public int tmpNWCY[] = new int[walkingQueueSize];

	public int tmpNWCX[] = new int[walkingQueueSize];

	/**
	 * Second skill instances.
	 */
	private Prospecting prospecting = new Prospecting();

	public int playerDeathX = 0;

	public int playerDeathY = 0;

	public String theDeadGuy = "";

	public boolean notNow = false;

	public boolean endGame = false;

	public int gameTimer = -1;

	public int totalLevel = (getPA().getLevelForXP(playerXP[0])
			+ getPA().getLevelForXP(playerXP[1])
			+ getPA().getLevelForXP(playerXP[2])
			+ getPA().getLevelForXP(playerXP[3])
			+ getPA().getLevelForXP(playerXP[4])
			+ getPA().getLevelForXP(playerXP[5])
			+ getPA().getLevelForXP(playerXP[6])
			+ getPA().getLevelForXP(playerXP[7])
			+ getPA().getLevelForXP(playerXP[8])
			+ getPA().getLevelForXP(playerXP[9])
			+ getPA().getLevelForXP(playerXP[10])
			+ getPA().getLevelForXP(playerXP[11])
			+ getPA().getLevelForXP(playerXP[12])
			+ getPA().getLevelForXP(playerXP[13])
			+ getPA().getLevelForXP(playerXP[14])
			+ getPA().getLevelForXP(playerXP[15])
			+ getPA().getLevelForXP(playerXP[16])
			+ getPA().getLevelForXP(playerXP[17])
			+ getPA().getLevelForXP(playerXP[18])
			+ getPA().getLevelForXP(playerXP[19])
			+ getPA().getLevelForXP(playerXP[20])
			+ getPA().getLevelForXP(playerXP[21])
			+ getPA().getLevelForXP(playerXP[22])
			+ getPA().getLevelForXP(playerXP[23]) + getPA().getLevelForXP(
			playerXP[24]));

	public long banStart;

	public boolean finished;

	public long nextTitan;

	public long nextHeal;

	public long nextSpirit;

	public int summoningSpecTime;

	public int rockTailCount;

	public static final int[] FRUIT = { 1963, 2120, 2108, 2114, 2120, 5972 };

	public int followerNpcId;

	public int specialMove = 60;

	public boolean healUni = false;

	public boolean steelTitan = false;

	public int bookPage = 0;

	public int maxPages = 0;
	public String bookName = "Book";
	public String[][] bookPages; // Each page(String key) must contain 22 pages.
	public boolean task1[] = { false, false, false, false, false, false, false,
			false, false, false };

	public boolean task2[] = { false, false, false, false, false, false, false,
			false, false, false };

	public boolean task3[] = { false, false, false, false, false, false, false,
			false, false, false };

	public int TPoints = 0;

	public int crabsK = 0;
	public int beastK = 0;

	public int newKil = 0;

	public int firesL = 0;

	public int rocksM = 0;

	public int smithM = 0;

	public int spinFL = 0;

	public int pcGame = 0;

	public int sumfam = 0;

	public int bonesB = 0;

	public int redTop = 0;

	public int impCat = 0;

	public int slayTa = 0;

	public boolean constructionObjects[] = { false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false };

	// 11 - 11, 12 - 12
	public int constructionUpgrades[] = { -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1 };

	public int lastAttacked;

	public boolean korasi;

	public boolean addChaotics;

	public boolean chooseChaotic = false;

	public boolean boosed[] = { false, false, false, false, false, false, false };

	public int boostTimer = 0;

	public int chaoticCharges[] = { 3000, 3000, 3000 };

	// Chaotic rapier first 18349, Chaotic longsword after 18351, Chaotic maul
	// 18353

	public Client(Channel s, int _playerId) {
		super(_playerId);
		this.session = s;
		synchronized (this) {
			outStream = new Stream(new byte[Config.BUFFER_SIZE]);
			outStream.currentOffset = 0;

			inStream = new Stream(new byte[Config.BUFFER_SIZE]);
			inStream.currentOffset = 0;
			buffer = new byte[Config.BUFFER_SIZE];
		}
	}

	/*
	 * public int getCombatLevel() { int mag = (int)
	 * ((getLevelForXP(playerXP[6])) * 1.5); int ran = (int)
	 * ((getLevelForXP(playerXP[4])) * 1.5); int attstr = (int) ((double)
	 * (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2]))); if
	 * (ran > attstr) { combatLevel = (int) (((getLevelForXP(playerXP[1])) *
	 * 0.25) + ((getLevelForXP(playerXP[3])) * 0.25) +
	 * ((getLevelForXP(playerXP[5])) * 0.125) + ((getLevelForXP(playerXP[4])) *
	 * 0.4875)); } else if (mag > attstr) { combatLevel = (int)
	 * (((getLevelForXP(playerXP[1])) * 0.25) + ((getLevelForXP(playerXP[3])) *
	 * 0.25) + ((getLevelForXP(playerXP[5])) * 0.125) +
	 * ((getLevelForXP(playerXP[6])) * 0.4875)); } else { combatLevel = (int)
	 * (((getLevelForXP(playerXP[1])) * 0.25) + ((getLevelForXP(playerXP[3])) *
	 * 0.25) + ((getLevelForXP(playerXP[5])) * 0.125) +
	 * ((getLevelForXP(playerXP[0])) * 0.325) + ((getLevelForXP(playerXP[2])) *
	 * 0.325)); } return combatLevel; }
	 */

	public void achievementInterface(final String taskName) {
		CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
			public void execute(CycleEventContainer e) {
				getPA().walkableInterface(20000);
				getPA().sendFrame126(taskName, 20004);
				getPA().sendFrame126("" + TPoints, 48005);
				e.stop();
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public void agilityDelay(int Emote, final int X, final int Y, final int H,
			int Req, int amtEXP, String message) {
		if (playerLevel[16] >= Req) {
			sendMessage(message);
			startAnimation(Emote);
			agilityEmote = true;
			getPA().addSkillXP(amtEXP, playerAgility);
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				public void execute(CycleEventContainer e) {
					getPA().movePlayer(X, Y, H);
					agilityEmote = false;
					e.stop();
				}

				@Override
				public void stop() {

				}
			}, 2);
		} else {
			sendMessage("You Need " + Req + " Agility To Do This Obsticle");
		}
	}

	public boolean allTasksComplete() {
		for (int i = 0; i <= 9; i++) {
			if (task1[i] && task2[i] && task3[i])
				return true;
		}
		return false;
	}

	public void applyRange(int num) {
		hitDiff = num;
		playerLevel[3] -= hitDiff;
		updateRequired = true;
		hitUpdateRequired = true;
	}

	public void banPlayer() {
		Connection.addNameToBanList(this.playerName);
		Connection.addNameToFile(this.playerName);
		this.disconnected = true;
	}

	public void BestItem1() {
		int BestValue = 0;
		int NextValue = 0;
		int ItemsContained = 0;
		WillKeepItem1 = 0;
		WillKeepItem1Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				ItemsContained += 1;
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue) {
					BestValue = NextValue;
					WillKeepItem1 = playerItems[ITEM] - 1;
					WillKeepItem1Slot = ITEM;
					if (playerItemsN[ITEM] > 2 && !prayerActive[10]) {
						WillKeepAmt1 = 3;
					} else if (playerItemsN[ITEM] > 3 && prayerActive[10]) {
						WillKeepAmt1 = 4;
					} else {
						WillKeepAmt1 = playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				ItemsContained += 1;
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue) {
					BestValue = NextValue;
					WillKeepItem1 = playerEquipment[EQUIP];
					WillKeepItem1Slot = EQUIP + 28;
					if (playerEquipmentN[EQUIP] > 2 && !prayerActive[10]) {
						WillKeepAmt1 = 3;
					} else if (playerEquipmentN[EQUIP] > 3 && prayerActive[10]) {
						WillKeepAmt1 = 4;
					} else {
						WillKeepAmt1 = playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!isSkulled && ItemsContained > 1
				&& (WillKeepAmt1 < 3 || (prayerActive[10] && WillKeepAmt1 < 4))) {
			BestItem2(ItemsContained);
		}
	}

	public void BestItem2(int ItemsContained) {
		int BestValue = 0;
		int NextValue = 0;
		WillKeepItem2 = 0;
		WillKeepItem2Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue
						&& !(ITEM == WillKeepItem1Slot && playerItems[ITEM] - 1 == WillKeepItem1)) {
					BestValue = NextValue;
					WillKeepItem2 = playerItems[ITEM] - 1;
					WillKeepItem2Slot = ITEM;
					if (playerItemsN[ITEM] > 2 - WillKeepAmt1
							&& !prayerActive[10]) {
						WillKeepAmt2 = 3 - WillKeepAmt1;
					} else if (playerItemsN[ITEM] > 3 - WillKeepAmt1
							&& prayerActive[10]) {
						WillKeepAmt2 = 4 - WillKeepAmt1;
					} else {
						WillKeepAmt2 = playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == WillKeepItem1Slot && playerEquipment[EQUIP] == WillKeepItem1)) {
					BestValue = NextValue;
					WillKeepItem2 = playerEquipment[EQUIP];
					WillKeepItem2Slot = EQUIP + 28;
					if (playerEquipmentN[EQUIP] > 2 - WillKeepAmt1
							&& !prayerActive[10]) {
						WillKeepAmt2 = 3 - WillKeepAmt1;
					} else if (playerEquipmentN[EQUIP] > 3 - WillKeepAmt1
							&& prayerActive[10]) {
						WillKeepAmt2 = 4 - WillKeepAmt1;
					} else {
						WillKeepAmt2 = playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!isSkulled
				&& ItemsContained > 2
				&& (WillKeepAmt1 + WillKeepAmt2 < 3 || (prayerActive[10] && WillKeepAmt1
						+ WillKeepAmt2 < 4))) {
			BestItem3(ItemsContained);
		}
	}

	public void BestItem3(int ItemsContained) {
		int BestValue = 0;
		int NextValue = 0;
		WillKeepItem3 = 0;
		WillKeepItem3Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue
						&& !(ITEM == WillKeepItem1Slot && playerItems[ITEM] - 1 == WillKeepItem1)
						&& !(ITEM == WillKeepItem2Slot && playerItems[ITEM] - 1 == WillKeepItem2)) {
					BestValue = NextValue;
					WillKeepItem3 = playerItems[ITEM] - 1;
					WillKeepItem3Slot = ITEM;
					if (playerItemsN[ITEM] > 2 - (WillKeepAmt1 + WillKeepAmt2)
							&& !prayerActive[10]) {
						WillKeepAmt3 = 3 - (WillKeepAmt1 + WillKeepAmt2);
					} else if (playerItemsN[ITEM] > 3 - (WillKeepAmt1 + WillKeepAmt2)
							&& prayerActive[10]) {
						WillKeepAmt3 = 4 - (WillKeepAmt1 + WillKeepAmt2);
					} else {
						WillKeepAmt3 = playerItemsN[ITEM];
					}
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == WillKeepItem1Slot && playerEquipment[EQUIP] == WillKeepItem1)
						&& !(EQUIP + 28 == WillKeepItem2Slot && playerEquipment[EQUIP] == WillKeepItem2)) {
					BestValue = NextValue;
					WillKeepItem3 = playerEquipment[EQUIP];
					WillKeepItem3Slot = EQUIP + 28;
					if (playerEquipmentN[EQUIP] > 2 - (WillKeepAmt1 + WillKeepAmt2)
							&& !prayerActive[10]) {
						WillKeepAmt3 = 3 - (WillKeepAmt1 + WillKeepAmt2);
					} else if (playerEquipmentN[EQUIP] > 3 - WillKeepAmt1
							&& prayerActive[10]) {
						WillKeepAmt3 = 4 - (WillKeepAmt1 + WillKeepAmt2);
					} else {
						WillKeepAmt3 = playerEquipmentN[EQUIP];
					}
				}
			}
		}
		if (!isSkulled && ItemsContained > 3 && prayerActive[10]
				&& ((WillKeepAmt1 + WillKeepAmt2 + WillKeepAmt3) < 4)) {
			BestItem4();
		}
	}

	public void BestItem4() {
		int BestValue = 0;
		int NextValue = 0;
		WillKeepItem4 = 0;
		WillKeepItem4Slot = 0;
		for (int ITEM = 0; ITEM < 28; ITEM++) {
			if (playerItems[ITEM] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerItems[ITEM] - 1));
				if (NextValue > BestValue
						&& !(ITEM == WillKeepItem1Slot && playerItems[ITEM] - 1 == WillKeepItem1)
						&& !(ITEM == WillKeepItem2Slot && playerItems[ITEM] - 1 == WillKeepItem2)
						&& !(ITEM == WillKeepItem3Slot && playerItems[ITEM] - 1 == WillKeepItem3)) {
					BestValue = NextValue;
					WillKeepItem4 = playerItems[ITEM] - 1;
					WillKeepItem4Slot = ITEM;
				}
			}
		}
		for (int EQUIP = 0; EQUIP < 14; EQUIP++) {
			if (playerEquipment[EQUIP] > 0) {
				NextValue = (int) Math.floor(getShops().getItemShopValue(
						playerEquipment[EQUIP]));
				if (NextValue > BestValue
						&& !(EQUIP + 28 == WillKeepItem1Slot && playerEquipment[EQUIP] == WillKeepItem1)
						&& !(EQUIP + 28 == WillKeepItem2Slot && playerEquipment[EQUIP] == WillKeepItem2)
						&& !(EQUIP + 28 == WillKeepItem3Slot && playerEquipment[EQUIP] == WillKeepItem3)) {
					BestValue = NextValue;
					WillKeepItem4 = playerEquipment[EQUIP];
					WillKeepItem4Slot = EQUIP + 28;
				}
			}
		}
	}

	public void calcCombat() {
		int mag = (int) ((getLevelForXP(playerXP[6])) * 1.5);
		int ran = (int) ((getLevelForXP(playerXP[4])) * 1.5);
		int attstr = (int) ((double) (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2])));
		// if (inWild()) {

		if (ran > attstr) {
			combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
					+ ((getLevelForXP(playerXP[3])) * 0.25)
					+ ((getLevelForXP(playerXP[5])) * 0.125) + ((getLevelForXP(playerXP[4])) * 0.4875));
		} else if (mag > attstr) {
			combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
					+ ((getLevelForXP(playerXP[3])) * 0.25)
					+ ((getLevelForXP(playerXP[5])) * 0.125) + ((getLevelForXP(playerXP[6])) * 0.4875));
		} else {
			combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
					+ ((getLevelForXP(playerXP[3])) * 0.25)
					+ ((getLevelForXP(playerXP[5])) * 0.125)
					+ ((getLevelForXP(playerXP[0])) * 0.325) + ((getLevelForXP(playerXP[2])) * 0.325));
		}
	}

	public int calcCombatDUNG() {
		int mag = (int) ((getLevelForXP(playerXP[6])) * 1.5);
		int ran = (int) ((getLevelForXP(playerXP[4])) * 1.5);
		int attstr = (int) ((double) (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2])));
		// if (inWild()) {

		if (ran > attstr) {
			combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
					+ ((getLevelForXP(playerXP[3])) * 0.25)
					+ ((getLevelForXP(playerXP[5])) * 0.125) + ((getLevelForXP(playerXP[4])) * 0.4875));
		} else if (mag > attstr) {
			combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
					+ ((getLevelForXP(playerXP[3])) * 0.25)
					+ ((getLevelForXP(playerXP[5])) * 0.125) + ((getLevelForXP(playerXP[6])) * 0.4875));
		} else {
			combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
					+ ((getLevelForXP(playerXP[3])) * 0.25)
					+ ((getLevelForXP(playerXP[5])) * 0.125)
					+ ((getLevelForXP(playerXP[0])) * 0.325) + ((getLevelForXP(playerXP[2])) * 0.325));
		}
		return combatLevel;
	}

	public boolean cannotSummon() {
		if (inDuelArena() || inCwGame == true || inCwWait == true
				|| absX >= 3169 && absX <= 3269 && absY >= 3376 && absY <= 3500) {
			return true;
		}
		return false;
	}

	public void CatchHunterNpc(String npcName, int Net, int npcId, int itemId,
			int AmtExp, int Req, int playerId) {
		npcName = Server.npcHandler.getNpcListName(npcId);
		if (foodDelay > System.currentTimeMillis())
			return;
		if (playerLevel[22] >= Req && getItems().playerHasItem(11260, 1)) {
			if (playerEquipment[playerWeapon] == 10010
					|| playerEquipment[playerWeapon] == 11259) { // player
				// got
				// net?
				if (playerLevel[22] + Misc.random(10) >= Misc.random(20) + Req) { // catch
																					// chance
					if (Misc.random(1000) == 1) {
						sendMessage("You catched a GIGANTIC butterfly and gained triple Experience!");
						getItems().addItem(722, 1);
						getItems().deleteItem(11260, 1);
						startAnimation(6999);
						getPA().addSkillXP(AmtExp * 3, 22);
					} else {
						sendMessage("You Catched a Butterfly!");
						getItems().addItem(itemId, 1);
						getItems().deleteItem(11260, 1);
						startAnimation(6999);
						getPA().addSkillXP(AmtExp, 22);
					}
				} else {
					sendMessage("You Failed To Catch The Butterfly");
					startAnimation(6999);
				}
			} else { // player got net?
				sendMessage("You need to wear a butterfly net!");
				return;
			}
		} else {
			sendMessage("You need atleast "
					+ Req
					+ " Hunter To catch that Butterfly! You also need an Impling Jar!");
			return;
		}
		foodDelay = System.currentTimeMillis() + 2500;
	} // end of hunt , resume of starting

	public void CatchimpNpc(int npcIndex, int npcId, int itemId, int AmtExp,
			int Req, int playerId) {
		NPC npc = NPCHandler.npcs[npcIndex];
		if (foodDelay > System.currentTimeMillis() || npc == null)
			return;
		if (!getItems().playerHasItem(11260, 1)) {
			sendMessage("You do not have an empty impling jar.");
			return;
		}

		if (playerEquipment[playerWeapon] != 10010
				&& playerEquipment[playerWeapon] != 11259) {

			sendMessage("You do not have a butterfly net equipped!");
			return;
		}
		if (getPA().getLevelForXP(playerXP[22]) < Req) {
			sendMessage("You need a Hunter level of atleast " + Req
					+ " to catch this imp.");
			return;
		}
		startAnimation(6999);

		if (playerLevel[22] + Misc.random(10) >= Misc.random(20) + Req) {
			sendMessage("You failed To catch the imp!");
			return;
		}
		impCat += 1;
		if (impCat >= 150 && !task3[8]) {
			GabbesAchievements.handleEliteTask(this, 8, 3, "Catch 150 Imps!");
		}
		boolean caught = false;
		if (npcId == 6064 || npcId == 6055 || npcId == 6056 || npcId == 6057
				|| npcId == 6058 || npcId == 6059 || npcId == 6060
				|| npcId == 6061 || npcId == 6062 || npcId == 6063
				|| npcId == 7903) {
			caught = Server.npcHandler.startNPCImpLoops(npc);
		}
		if (caught) {
			getItems().deleteItem(11260, 1);
			getItems().addItem(itemId, 1);
			getPA().addSkillXP(AmtExp, 22);
		}
		foodDelay = System.currentTimeMillis() + 2500;
	}

	public boolean checkEmpty(Player player) {
		for (int i = 0; i < player.playerEquipment.length; i++) {
			if (player.playerEquipment[i] != -1)
				return false;
		}
		for (int i = 0; i < player.playerItems.length; i++) {
			if (player.playerItems[i] != 0)
				return false;
		}
		return true;
	}

	public boolean checkEmpty44(Player player) {
		if ((getItems().freeSlots() == 28 && playerEquipment[playerHat] == -1)
				&& (playerEquipment[playerCape] == -1)
				&& (playerEquipment[playerAmulet] == -1)
				&& (playerEquipment[playerChest] == -1)
				&& (playerEquipment[playerShield] == -1)
				&& (playerEquipment[playerLegs] == -1)
				&& (playerEquipment[playerHands] == -1)
				&& (playerEquipment[playerFeet] == -1)
				&& (playerEquipment[playerWeapon] == -1)) {
			// getDungeoneering().startfloor1();
			return true;
		} else {
			sendMessage("Please bank your items if you want to enter Dungeoneering");
			return false;
		}
	}

	/*
	 * public synchronized boolean processQueuedPackets() { Packet p = null;
	 * synchronized (queuedPackets) { p = queuedPackets.poll(); } if (p == null)
	 * { return false; } inStream.currentOffset = 0; packetType = p.getId();
	 * packetSize = p.getLength(); inStream.buffer = p.getData(); if (packetType
	 * > 0) { // sendMessage("PacketType: " + packetType);
	 * PacketHandler.processPacket(this, packetType, packetSize);
	 * processPackets++; } timeOutCounter = 0; /*if (processPackets >
	 * Config.MAX_PROCESS_PACKETS) { return false; }* return true; }
	 */
	public void correctCoordinates() {
		if (inPcGame()) {
			getPA().movePlayer(2657, 2639, 0);
		}
		if (inFightCaves()) {
			getPA().movePlayer(absX, absY, playerId * 4);
			sendMessage("Your wave will start in 10 seconds.");
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					Server.fightCaves
							.spawnNextWave((Client) PlayerHandler.players[playerId]);
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 20);

		}

		if (inRFD()) {
			getPA().movePlayer(1899, 5363, playerId * 4 + 2);
			sendMessage("Your wave will start in 10 seconds.");
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					Server.rfd
							.spawnNextWave((Client) PlayerHandler.players[playerId]);
					container.stop();
				}

				@Override
				public void stop() {

				}
			}, 20);
		}
	}

	public void degradeMBody() {// morrigans body
		if (playerEquipment[playerChest] == 13872 && mBodyLeft < 1) {
			playerEquipment[playerChest] = -1;
			playerEquipmentN[playerChest] = 0;
			getItems().wearItem(-1, 1, playerChest);
			sendMessage("Your Morrigans leather body crumbles to dust!");
			mBodyLeft = 1000;
		}
	}

	public void degradeMChaps() {// morrigans chaps
		if (playerEquipment[playerLegs] == 13875 && mChapsLeft < 1) {
			playerEquipment[playerLegs] = -1;
			playerEquipmentN[playerLegs] = 0;
			getItems().wearItem(-1, 1, playerLegs);
			sendMessage("Your Morrigans chaps crumbles to dust!");
			mChapsLeft = 1000;
		}
	}

	public void degradeSHelm() {// statius helm
		if (playerEquipment[playerHat] == 13898 && sHelmLeft < 1) {
			playerEquipment[playerHat] = -1;
			playerEquipmentN[playerHat] = 0;
			getItems().wearItem(-1, 1, playerHat);
			sendMessage("Your Statius full helm crumbles to dust!");
			sHelmLeft = 1000;
		}
	}

	public void degradeSLegs() {// statius legs
		if (playerEquipment[playerLegs] == 13892 && sLegsLeft < 1) {
			playerEquipment[playerLegs] = -1;
			playerEquipmentN[playerLegs] = 0;
			getItems().wearItem(-1, 1, playerLegs);
			sendMessage("Your Statius platelegs crumbles to dust!");
			sLegsLeft = 1000;
		}
	}

	public void degradeStat() {
		if (playerEquipment[playerWeapon] == 13904 && statLeft < 1) {
			playerEquipment[playerWeapon] = -1;
			playerEquipmentN[playerWeapon] = 0;
			getItems().wearItem(-1, 1, 3);
			sendMessage("Your Statius warhammer crumbles to dust!");
			statLeft = 1000;
		}
	}

	public void degradeSTop() {// statius top
		if (playerEquipment[playerChest] == 13886 && sTopLeft < 1) {
			playerEquipment[playerChest] = -1;
			playerEquipmentN[playerChest] = 0;
			getItems().wearItem(-1, 1, playerChest);
			sendMessage("Your Statius platebody crumbles to dust!");
			sTopLeft = 1000;
		}
	}

	public void degradeVLegs() {// vesta legs
		if (playerEquipment[playerLegs] == 13895 && vLegsLeft < 1) {
			playerEquipment[playerLegs] = -1;
			playerEquipmentN[playerLegs] = 0;
			getItems().wearItem(-1, 1, playerLegs);
			sendMessage("Your Vesta plateskirt crumbles to dust!");
			vLegsLeft = 1000;
		}
	}

	public void degradeVls() {
		if (playerEquipment[playerWeapon] == 13901 && vlsLeft < 1) {
			playerEquipment[playerWeapon] = -1;
			playerEquipmentN[playerWeapon] = 0;
			getItems().wearItem(-1, 1, 3);
			sendMessage("Your Vesta longsword crumbles to dust!");
			vlsLeft = 1000;
		}
	}

	public void degradeVSpear() {
		if (playerEquipment[playerWeapon] == 13907 && vSpearLeft < 1) {
			playerEquipment[playerWeapon] = -1;
			playerEquipmentN[playerWeapon] = 0;
			getItems().wearItem(-1, 1, 3);
			sendMessage("Your Vesta spear crumbles to dust!");
			vSpearLeft = 1000;
		}
	}

	public void degradeVTop() {// vesta top
		if (playerEquipment[playerChest] == 13889 && vTopLeft < 1) {
			playerEquipment[playerChest] = -1;
			playerEquipmentN[playerChest] = 0;
			getItems().wearItem(-1, 1, playerChest);
			sendMessage("Your Vesta chainbody crumbles to dust!");
			vTopLeft = 1000;
		}
	}

	public void degradeZBottom() {// zuriel hood
		if (playerEquipment[playerLegs] == 13863 && zBottomLeft < 1) {
			playerEquipment[playerLegs] = -1;
			playerEquipmentN[playerLegs] = 0;
			getItems().wearItem(-1, 1, playerLegs);
			sendMessage("Your Zuriel robe bottom crumbles to dust!");
			zBottomLeft = 1000;
		}
	}

	public void degradeZHood() {// zuriel hood
		if (playerEquipment[playerHat] == 13866 && zHoodLeft < 1) {
			playerEquipment[playerHat] = -1;
			playerEquipmentN[playerHat] = 0;
			getItems().wearItem(-1, 1, playerHat);
			sendMessage("Your Zuriel hood crumbles to dust!");
			zHoodLeft = 1000;
		}
	}

	public void degradeZStaff() {// zuriel staff
		if (playerEquipment[playerWeapon] == 13870 && zStaffLeft < 1) {
			playerEquipment[playerWeapon] = -1;
			playerEquipmentN[playerWeapon] = 0;
			getItems().wearItem(-1, 1, 3);
			sendMessage("Your Zuriel staff crumbles to dust!");
			zStaffLeft = 1000;
		}
	}

	public void degradeZTop() {// zuriel top
		if (playerEquipment[playerChest] == 13860 && zTopLeft < 1) {
			playerEquipment[playerChest] = -1;
			playerEquipmentN[playerChest] = 0;
			getItems().wearItem(-1, 1, playerChest);
			sendMessage("Your Zuriel robe top crumbles to dust!");
			zTopLeft = 1000;
		}
	}

	public void destruct() {
		PvPHandler.handleLogout(this);
		if (inDung) {
			if (Partner.equalsIgnoreCase("None")) {
				getDungeoneering();
				Dungeoneering.deleteKeys(this);
				Server.npcHandler.killAllDungNPCs(this);
			} else {
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;
						if (ALLPLAYERS.playerName.equalsIgnoreCase(Partner)) {
							Client c2 = (Client) p;
							if (c2.inDung && inDung) {
								c2.getPA().leaveDung(c2);
								getPA().leaveDung(this);
								c2.sendMessage("Your partner has logged out. The dungeon has been abandoned.");
								getDungeoneering();
								Dungeoneering.deleteKeys(this);
								Server.npcHandler.killAllDungNPCs(this);
								getDungeoneering();
								Dungeoneering.deleteKeys(c2);
								Server.npcHandler.killAllDungNPCs(c2);
								getDungeoneering();
								Dungeoneering.newDungeon(c2, false);
								getDungeoneering();
								Dungeoneering.newDungeon(this, false);
							}
						}
					}
				}
			}
		}

		if (session == null)
			return;
		PlayerSave.saveGame(this);
		PlayerSave.saveGame(this);
		if (disconnected == true) {
			saveCharacter = true;
		}
		if (duelStatus == 6) {
			getTradeAndDuel().claimStakedItems();
		}
		if (disconnected == true) {
			getTradeAndDuel().declineTrade();
		}
		PlayerSave.saveGame(this);

		Misc.println("[DEREGISTERED]: " + Misc.optimizeText(playerName) + "");
		PlayerSave.saveGame(this);
		saveCharacter = true;
		disconnected = true;
		session.close();
		session = null;
		inStream = null;
		outStream = null;
		isActive = false;
		buffer = null;
		super.destruct();
	}

	public void FetchDice() {
		int rnd;
		String Message = "";
		if (cDice == 0 || (System.currentTimeMillis() - diceDelay <= 1000)) {
			return;
		}
		switch (cDice) {
		// Dice
		case 15096:
			rnd = Misc.random(19) + 1;
			Message = ("rolled " + rnd + " on a twenty-sided die.");
			break;
		case 15094:
			rnd = Misc.random(11) + 1;
			Message = ("rolled " + rnd + " on a twelve-sided die.");
			break;
		case 15092:
			rnd = Misc.random(9) + 1;
			Message = ("rolled " + rnd + " on a ten-sided die.");
			break;
		case 15090:
			rnd = Misc.random(7) + 1;
			Message = ("rolled " + rnd + " on an eight-sided die.");
			break;
		case 15100:
			rnd = Misc.random(60) + 1;
			Message = ("rolled A " + rnd + " on the percentile dice.");
			break;
		case 15086:
			rnd = Misc.random(5) + 1;
			Message = ("rolled " + rnd + " on a six-sided die.");
			break;
		case 15088:
			rnd = Misc.random(11) + 1;
			Message = ("rolled " + rnd + " on two six-sided dice.");
			break;
		case 15098:
			rnd = Misc.random(99) + 1;
			Message = ("rolled A " + rnd + " on the percentile dice.");
			break;
		}
		forcedChat("" + Misc.optimizeText(playerName.toLowerCase()) + " "
				+ Message);
	}

	public int findBoBslot(int itemId) {
		for (int i = 0; i < 28; i += 1) {
			if (storeditems[i] == itemId) {

				return i;
			}
		}
		return -1;
	}

	public void FindItemKeptInfo() {
		if (isSkulled && prayerActive[10])
			ItemKeptInfo(1);
		else if (!isSkulled && !prayerActive[10])
			ItemKeptInfo(3);
		else if (!isSkulled && prayerActive[10])
			ItemKeptInfo(4);
		else if (inPits || inFightCaves()) {
			ItemKeptInfo(5);
			if (isInFala() || isInArd()) {
				ItemKeptInfo(6);
			}
		}
	}

	public void firstslot() {
		for (summoningslot = 0; occupied[summoningslot] == true; summoningslot += 1) {

		}
	}

	public void firstSlot(int itemId) {
		boolean itemFound = false;
		if (!getItems().isStackable(itemId)) {
			for (summoningslot = 0; storeditems[summoningslot] > 0; summoningslot += 1) {

			}
		} else if (getItems().isStackable(itemId)) {
			for (summoningslot = 0; summoningslot < 28; summoningslot += 1) {
				if (storeditems[summoningslot] == itemId) {
					itemFound = true;
					break;
				}
			}
		}

		if (itemFound == false) {
			for (summoningslot = 0; storeditems[summoningslot] > 0; summoningslot += 1) {

			}
		}
	}

	public void giverewards() {
		if (getItems().playerHasItem(6199)) {
			getItems().deleteItem(6199, 1);
			getItems().addItem(randomprize(), 1);
		} else {
			sendMessage("You need 2 free slots.");
		}
	}

	public static int randomprize[] = { 15000, 15001, 15002, 15003, 15004,
			15005, 15006, 15007, 15008, 15009, 15010, 15011, 15012, 15013,
			15014, 15015, 15016, 15017, 15018, 15019, 15020, 11694 };

	public static int randomprize() {
		return randomprize[(int) (Math.random() * randomprize.length)];
	}

	public void flushOutStream() {
		if (!session.isConnected() || disconnected
				|| outStream.currentOffset == 0)
			return;

		byte[] temp = new byte[outStream.currentOffset];
		System.arraycopy(outStream.buffer, 0, temp, 0, temp.length);
		Packet packet = new Packet(-1, Type.FIXED,
				ChannelBuffers.wrappedBuffer(temp));
		session.write(packet);
		outStream.currentOffset = 0;

	}

	public void fmwalkto(int i, int j) {
		newWalkCmdSteps = 0;
		// System.out.println("WALKING TO "+i+", "+j+"");
		if (++newWalkCmdSteps > 50)
			newWalkCmdSteps = 0;
		int k = absX + i;
		k -= mapRegionX * 8;
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int l = absY + j;
		l -= mapRegionY * 8;
		isRunning2 = false;
		isRunning = false;
		// for(this.i = 0; this.i < newWalkCmdSteps; this.i++)
		// {
		newWalkCmdX[0] += k;
		newWalkCmdY[0] += l;
		// }
		// lastWalk = System.currentTimeMillis();
		// walkDelay = 1;
		poimiY = l;
		poimiX = k;
	}

	public void fmwalkto22(int i, int j) {
		// startAnimation(733);
		newWalkCmdSteps = 0;
		if (++newWalkCmdSteps > 50)
			newWalkCmdSteps = 0;
		int k = absX + i;
		k -= mapRegionX * 8;
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int l = absY + j;
		l -= mapRegionY * 8;
		isRunning2 = false;
		isRunning = false;
		// for(this.i = 0; this.i < newWalkCmdSteps; this.i++)
		// {
		newWalkCmdX[0] += k;
		newWalkCmdY[0] += l;
		// }
		// lastWalk = System.currentTimeMillis();
		// walkDelay = 1;
		poimiY = l;
		poimiX = k;
		isRunning2 = true;
		isRunning = true;
		this.startAnimation(748);
	}

	public void frame1() // cancels all player and npc emotes within area!
	{
		for (Player p : PlayerHandler.players) {
			if (p != null) {
				Client c = (Client) p;
				c.outStream.createFrame(1);
			}
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame174(int i1, int i2, int i3) // another thing, tested
	// doesn't logout, looks
	// like something to do with
	// music
	{
		if (!soundsOn) {
			return;
		}
		outStream.createFrame(174);
		outStream.writeWord(i1);
		outStream.writeByte(i2);
		outStream.writeWord(i3);
	}

	public ActionHandler getActions() {
		return actionHandler;
	}

	public Agility getAgil() {
		return ag;
	}

	public Banking getBank() {
		return bank;
	}

	public BankPin getBankPin() {
		return bankpin;
	}

	public Binding getBind() {
		return binding;
	}

	public DwarfMultiCannon getCannon() {
		return cannon;
	}

	public Client getClient(int id) {
		return (Client) PlayerHandler.players[id];
	}

	public Client getClient(String name) {
		name = name.toLowerCase();
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (validClient(i)) {
				Client client = getClient(i);
				if (client.playerName.toLowerCase().equalsIgnoreCase(name)) {
					return client;
				}
			}
		}
		return null;
	}

	public CombatAssistant getCombat() {
		return combatAssistant;
	}

	public int getCombatLevel() {
		int mag = (int) ((getLevelForXP(playerXP[6])) * 1.5);
		int ran = (int) ((getLevelForXP(playerXP[4])) * 1.5);
		int attstr = (int) ((double) (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2])));
		if (inWild()) {

			if (ran > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125) + ((getLevelForXP(playerXP[4])) * 0.4875));
			} else if (mag > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125) + ((getLevelForXP(playerXP[6])) * 0.4875));
			} else {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[0])) * 0.325) + ((getLevelForXP(playerXP[2])) * 0.325));
			}
		} else {
			if (ran > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[4])) * 0.4875) + ((getLevelForXP(playerXP[21])) * 0.125));
			} else if (mag > attstr) {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[6])) * 0.4875) + ((getLevelForXP(playerXP[21])) * 0.125));
			} else {
				combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
						+ ((getLevelForXP(playerXP[3])) * 0.25)
						+ ((getLevelForXP(playerXP[5])) * 0.125)
						+ ((getLevelForXP(playerXP[0])) * 0.325)
						+ ((getLevelForXP(playerXP[2])) * 0.325) + ((getLevelForXP(playerXP[21])) * 0.125));
			}
		}
		return combatLevel;
	}

	public Crafting getCrafting() {
		return crafting;
	}

	public Future<?> getCurrentTask() {
		return currentTask;
	}

	public Curse getCurse() {
		return curse;
	}

	public DialogueHandler getDH() {
		return dialogueHandler;
	}

	public Dungeoneering getDungeoneering() {
		return dungeoneering;
	}

	public Farming getFarming() {
		return farming;
	}

	public Fishing getFishing() {
		return fish;
	}

	public FlaxStringer getFlaxStringer() {
		return flax;
	}

	public Fletching getFletching() {
		return fletching;
	}

	public Food getFood() {
		return food;
	}

	public Gambling getGamble() {
		return gamble;
	}

	public Herblore getHerblore() {
		return herblore;
	}

	public synchronized Stream getInStream() {
		return inStream;
	}

	public ItemAssistant getItems() {
		return itemAssistant;
	}

	public PlayerKilling getKill() {
		return playerKilling;
	}

	public Mining getMining() {
		return mine;
	}

	public NPC getNpc(int index) {
		return ((NPC) NPCHandler.npcs[index]);
	}

	public synchronized Stream getOutStream() {
		return outStream;
	}

	public PlayerAssistant getPA() {
		return playerAssistant;
	}

	public synchronized int getPacketSize() {
		return packetSize;
	}

	public synchronized int getPacketType() {
		return packetType;
	}

	public int getPayment() {
		int l = 13157 * Misc.random(10);
		return l;
	}

	public PkRewardSystem getPkRewardSystem() {
		return pkReward;
	}

	public Potions getPotions() {
		return potion;
	}

	public PotionMixing getPotMixing() {
		return potionMixing;
	}

	public Prayer getPrayer() {
		return prayer;
	}

	/**
	 * Gets the prospecting class.
	 * 
	 * @return The prospecting class.
	 */
	public Prospecting getProspecting() {
		return prospecting;
	}

	public Channel getSession() {
		return session;
	}

	public ShopAssistant getShops() {
		return shopAssistant;
	}

	/**
	 * Skill Constructors
	 */
	public Slayer getSlayer() {
		return slayer;
	}

	public Smithing getSmithing() {
		return smith;
	}

	public SmithingInterface getSmithingInt() {
		return smithInt;
	}

	public Sound getSound() {
		return sound;
	}

	public int getSpecAmount() {
		switch (hasFollower) {
		case 3592:
			return 20;

		case 3591:
			return 12;

		case 3593:
			return 20;

		case 7357:
		case 7355:
		case 7359:
			return 20;

		case 7354:
			return 20;

		default:
			return 10;
		}
	}

	public int getStoredAmount(int itemId) {
		if (!getItems().isStackable(itemId)) {
			int toReturn = 0;

			for (int i = 0; i < 29; i++) {
				if (storeditems[i] == itemId) {
					toReturn += 1;

				}
			}

			return toReturn;
		}

		if (getItems().isStackable(itemId)) {
			int toReturn = amount[findBoBslot(itemId)];

			return toReturn;
		}

		return 0;
	}

	public Thieving getThieving() {
		return thieving;
	}

	public Timers getTimers() {
		return timers;
	}

	public TradeAndDuel getTradeAndDuel() {
		return tradeAndDuel;
	}

	public TradeLog getTradeLog() {
		return tradeLog;
	}

	public WarriorsGuild getWarriorsGuild() {
		return warriorsGuild;
	}

	public void GoblinSpawn() {
		// c.getPA().movePlayer(3258,9517, c.playerId * 4);
		CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				doingMageBankMinigame = true;
				Server.Goblin
						.spawnNextWave((Client) PlayerHandler.players[playerId]);
				container.stop();
				if (disconnected) {
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 20);
	}

	public void guidePlayer1() {
		doingTut = true;
		getPA().movePlayer(3218, 3437, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(626, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer10() {
		doingTut = true;
		getPA().movePlayer(2416, 3526, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(643, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer11() {
		doingTut = true;
		getPA().movePlayer(2928, 5203, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(645, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer12() {
		getPA().movePlayer(3213, 3434, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {
				getDH().sendDialogues(648, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer2() {
		doingTut = true;
		getPA().movePlayer(3216, 3416, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(628, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer3() {
		doingTut = true;
		getPA().movePlayer(3205, 3417, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(630, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer4() {
		doingTut = true;
		getPA().movePlayer(3207, 3431, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(632, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer5() {
		doingTut = true;
		getPA().movePlayer(2440, 3089, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(634, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer6() {
		doingTut = true;
		getPA().movePlayer(2967, 3205, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(636, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer7() {
		doingTut = true;
		getPA().movePlayer(2662, 2648, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(638, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer8() {
		doingTut = true;
		getPA().movePlayer(3365, 3268, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(636, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void guidePlayer9() {
		doingTut = true;
		getPA().movePlayer(3448, 3515, 0);
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(13, this, new CycleEvent() {
			public void execute(CycleEventContainer e) {

				getDH().sendDialogues(641, 1);
				this.stop();
			}

			@Override
			public void stop() {

			}
		}, 2);
	}

	public void handCannonDestory() {
		cannonTimer = 0;
		int chance = playerLevel[playerFiremaking] * 5 + 25;
		if (specGfx)
			chance /= 2;
		if (Misc.random(chance) == 1)
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (cannonTimer <= 0) {
						gfx0(2140);
						playerEquipment[playerWeapon] = -1;
						sendMessage("Your hand cannon explodes!");
						int damage = Misc.random(15) + 1;
						setHitDiff(damage);
						setHitUpdateRequired(true);
						dealDamage(Misc.random(15) + 1);
						updateRequired = true;
						getItems().sendWeapon(
								playerEquipment[playerWeapon],
								getItems().getItemName(
										playerEquipment[playerWeapon]));
						getCombat().getPlayerAnimIndex(
								getItems().getItemName(
										playerEquipment[playerWeapon])
										.toLowerCase());
						getItems().resetBonus();
						getItems().getBonus();
						getItems().writeBonus();
						getPA().requestUpdates();
						getOutStream().createFrame(34);
						getOutStream().writeWord(6);
						getOutStream().writeWord(1688);
						getOutStream().writeByte(playerWeapon);
						getOutStream().writeWord(0);
						getOutStream().writeByte(0);
						updateRequired = true;
						setAppearanceUpdateRequired(true);
						container.stop();
					} else {
						cannonTimer--;
						container.stop();
					}
					if (disconnected) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 2);
	}

	public void handCannonSpec() {
		cannonTimer = 0;
		CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
			public void execute(CycleEventContainer e) {
				cannonTimer--;
				if (cannonTimer == 0) {
					gfx0(2141);
					specGfx = true;
					handCannonDestory();
					e.stop();
				}
				if (cannonTimer == 1) {
					if (playerIndex > 0)
						getCombat().fireProjectilePlayer();
					else if (npcIndex > 0)
						getCombat().fireProjectileNpc();
					handCannonDestory();
					e.stop();
				}
				e.stop();
				if (disconnected) {
					e.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 1);
	}

	public void handleAllEvents() {

	}

	public void handlePartyForm() {
		setSidebarInterface(1, 27124);
		getPA().sendFrame126("", 27127);
		sendMessage("Manage your party via the Quest Tab.");
	}

	public void handleRemoval() {
	}

	public void handleSummoningOnLogin() { // ADDING MORE SOON
		if (hasFollower > 0) {
			Summoning().summonNulledNPC(hasFollower);
			summoned2 = true;
		}
	}

	public void handleSumSpec() {
		int pouchRequired = 0;// Summoning.getSummonedScroll(hasFollower);

		if (pouchRequired == -1) {
			return;
		}
		if (hasFollower <= 0) {
			sendMessage("You have no follower");
			return;
		}
		if (hasFollower == 3588) {
			getDH().sendDialogues(28, -1);
		}
		NPC follower = null;
		if (followerNpcId > 0) {
			if (NPCHandler.npcs[followerNpcId] != null) {
				follower = NPCHandler.npcs[followerNpcId];
			}
		}

		if (!this.getItems().playerHasItem(pouchRequired)) {
			this.sendMessage("You do not have the required scroll to cast this.");
			return;
		}
		if (this.specialMove < this.getSpecAmount()) {
			this.sendMessage("You do not have the required special energy points to do this.");

			return;
		}

		this.getItems().deleteItem(pouchRequired, 1);

		if (hasFollower == 6829) {

			if (specReq(true)) {
				if (underAttackBy > 0) {
					summoningSpecTime = 25;
					// getCombat().summoningSpec(this);
				}
			}
		}

		if (hasFollower == 7341) {
			startAnimation(7660);
			gfx0(1316);
			this.getDH().sendDialogues(300, 7341);
		}

		if (hasFollower == 6833) {

			if (specReq(true)) {
				if (underAttackBy > 0) {
					summoningSpecTime = 25;
					// getCombat().summoningSpec(this);
				}
			}
		}
		if (hasFollower == 6841) {

			if (specReq(false)) {
				startAnimation(7660);
				gfx0(1316);
				summoningSpecTime = 25;
				summoningTimeEvent(this);
				Server.itemHandler.createGroundItem(this, 223, absX + 1,
						absY + 1, 1, playerId);
				Server.itemHandler.createGroundItem(this, 223, absX + 1, absY,
						1, playerId);
				Server.itemHandler.createGroundItem(this, 223, absX + 1,
						absY - 1, 1, playerId);
				Server.itemHandler.createGroundItem(this, 223, absX, absY + 1,
						1, playerId);

			}

		}

		if (hasFollower == 6817) {

			if (specReq(false)) {
				startAnimation(7660);
				follower.gfx0(1332);
				summoningSpecTime = 25;
				summoningTimeEvent(this);
				Server.itemHandler.createGroundItem(this, rFruit(), absX + 1,
						absY + 1, 1, playerId);
				Server.itemHandler.createGroundItem(this, rFruit(), absX + 1,
						absY, 1, playerId);
				Server.itemHandler.createGroundItem(this, rFruit(), absX + 1,
						absY - 1, 1, playerId);
				Server.itemHandler.createGroundItem(this, rFruit(), absX,
						absY + 1, 1, playerId);

				getPA().stillGfx(1331, absX + 1, absY + 1, heightLevel, 1);
				getPA().stillGfx(1331, absX + 1, absY, heightLevel, 1);
				getPA().stillGfx(1331, absX + 1, absY - 1, heightLevel, 1);
				getPA().stillGfx(1331, absX, absY + 1, heightLevel, 1);
			}

		}

		if (hasFollower == 6813) {

			if (specReq(false)) {
				startAnimation(7660);
				follower.gfx0(1434);
				summoningSpecTime = 50;
				summoningTimeEvent(this);
				Server.itemHandler.createGroundItem(this, 391, absX + 1,
						absY + 1, 1, playerId);
				Server.itemHandler.createGroundItem(this, 391, absX + 1, absY,
						1, playerId);
				Server.itemHandler.createGroundItem(this, 391, absX + 1,
						absY - 1, 1, playerId);
				Server.itemHandler.createGroundItem(this, 391, absX, absY + 1,
						1, playerId);

			}

		}

		if (hasFollower == 7345) {
			if (specReq(false)) {

				if (getLevelForXP(this.playerXP[Player.playerStrength]) >= playerLevel[playerStrength] + 9) {
					sendMessage("You are already under the influance of the war tortoise");
					return;
				}
				startAnimation(7660);
				gfx0(1316);
				playerLevel[playerStrength] += 9;
				getPA().refreshSkill(playerStrength);
				summoningSpecTime = 300;
				summoningTimeEvent(this);

			}
		}

		if (hasFollower == 6796) {

			if (specReq(false)) {

				startAnimation(7660);
				NPCHandler.npcs[followerNpcId].gfx0(1326);
				summoningSpecTime = 120;
				summoningTimeEvent(this);
				playerLevel[1] += 4;
				getPA().refreshSkill(1);

			}

		}

		if (hasFollower == 6851) {
			if (specReq(false)) {
				if (NPCHandler.npcs[followerNpcId] != null) {
					startAnimation(7660);
					follower.gfx0(1321);
					Server.itemHandler.createGroundItem(this, Herblore.rHerb(),
							follower.absX, follower.absY, 1, playerId);
					Server.itemHandler.createGroundItem(this, Herblore.rHerb(),
							follower.absX, follower.absY, 1, playerId);
					Server.itemHandler.createGroundItem(this, Herblore.rHerb(),
							follower.absX, follower.absY, 1, playerId);
					Server.itemHandler.createGroundItem(this, Herblore.rHerb(),
							follower.absX, follower.absY, 1, playerId);
					summoningSpecTime = 35;
					summoningTimeEvent(this);

				}
			}
		}

		if (hasFollower == 6991) {
			if (specReq(false)) {
				startAnimation(7660);
				follower.gfx0(1339);
				startAnimation(7660);

				summoningSpecTime = 120;
				playerLevel[playerThieving] += 4;
				playerLevel[playerAgility] += 4;
				getPA().refreshSkill(playerThieving);
				getPA().refreshSkill(playerAgility);
				summoningTimeEvent(this);

			}
		}

		if (hasFollower == 6815 || hasFollower == 3587) {
			if (specReq(false)) {
				startAnimation(7660);
				follower.gfx0(1414);
				if (this.getLevelForXP(this.playerXP[1]) >= playerLevel[1] + 9) {
					this.sendMessage("You are already under the influance of a stat booster");
					return;
				}
				playerLevel[1] += 9;
				getPA().refreshSkill(1);
				summoningSpecTime = 300;

				summoningTimeEvent(this);

			}
		}

		if (hasFollower == 7359) {
			if (specReq(false)) {
				startAnimation(7660);
				follower.gfx0(1512);

				summoningSpecTime = 35;
				healSummon(8 + Misc.random(8));
				gfx0(1517);
				summoningTimeEvent(this);

			}
		}

		if (hasFollower == 7357) {
			if (specReq(false)) {
				startAnimation(7660);
				follower.gfx0(1513);

				summoningSpecTime = 35;
				healSummon(8 + Misc.random(6));
				gfx0(1517);
				summoningTimeEvent(this);

			}
		}

		if (hasFollower == 7355) {
			if (specReq(false)) {
				startAnimation(7660);
				follower.gfx0(1513);

				summoningSpecTime = 35;
				healSummon(8 + Misc.random(10));
				gfx0(1517);
				summoningTimeEvent(this);

			}
		}

		if (hasFollower == 6824) {
			if (specReq(false)) {
				startAnimation(7660); // 385
				follower.gfx0(1336);

				playerLevel[17] += 2;
				getPA().refreshSkill(17);

				summoningSpecTime = 35;
				summoningTimeEvent(this);

			}
		}

		if (hasFollower == 6843) {
			if (specReq(false)) {
				startAnimation(7660);
				follower.gfx0(1324);
				poisonDamage = 0;
				sendMessage("Your poison is cured");

				summoningSpecTime = 35;
				summoningTimeEvent(this);

			}
		}

		if (hasFollower == 3591) {
			if (duelStatus == 5) {
				sendMessage("You can't do this in duel");
				return;
			}

			if (underAttackBy == 0) {
				sendMessage("You can only use this in combat.");
				return;
			}
			if (!inMulti()) {
				sendMessage("You have to be in multi to use this.");
				return;
			}
			// n = Server.npcHandler.npcs[npcClickIndex];
			// startAnimation(8229);

		}
		if (hasFollower == 3592) {
			if (duelStatus == 5) {
				sendMessage("You can't do this in duel");
				return;
			}
			gfx0(1310);
			startAnimation(7660);
			if (playerLevel[3] <= getLevelForXP(playerXP[3])) {
				playerLevel[3] += getLevelForXP((int) (playerXP[3] * .15));
				getPA().refreshSkill(3);

				healUni = true;

				sendMessage("The unicorn heals you");

			}

		}
		if (hasFollower == 3593) {
			if (duelStatus == 5) {
				sendMessage("You can't do this in duel");
				return;
			}

			if (System.currentTimeMillis() - nextHeal > 5000) {
				if (playerLevel[6] <= getPA().getLevelForXP(playerXP[6]) + 15) {
					playerLevel[6] = getPA().getLevelForXP(playerXP[6]) + 15;
					getPA().refreshSkill(6);
					nextHeal = System.currentTimeMillis();
					sendMessage("The wolpertinger boosts your magic");
					gfx0(1311);
					startAnimation(7660);
				} else {
					sendMessage("You have to wait 5 seconds before performing another special move.");
				}
			}
		}

		if (hasFollower == 3594 || hasFollower == 3590 || hasFollower == 3596) {
			if (getItems().freeSlots() > 0) {
				if (rockTailCount > 0) {
					rockTailCount -= 1;
					sendMessage("There are another " + rockTailCount
							+ " Rocktails in your BoB");
					getItems().addItem(15272, 1);
				}
			}
			if (rockTailCount <= 0) {
				sendMessage("Your BoB Has no more supplies left, refill them at the summoning master.");

			}
			if (getItems().freeSlots() <= 0) {
				sendMessage("Not enough inventory space");
			}
		}

		specialMove -= getSpecAmount();
		this.getPA()
				.sendFrame126("Special Move: " + specialMove + "/60", 25617);

	}

	public void HardWaveSpawn() {
		getDH().sendDialogues(41, 2618);
		CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				Server.hardCaves
						.spawnNextWave((Client) PlayerHandler.players[playerId]);
				container.stop();
				if (disconnected) {
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 20);
	}

	public boolean hasBankItem(int itemID) {
		for (int i1 = 0; i1 < bankItems.length; i1++) {
			if (bankItems[i1] - 1 == itemID && bankItemsN[i1] >= 1) {
				return true;
			}
		}
		for (int i1 = 0; i1 < bankItems1.length; i1++) {
			if (bankItems1[i1] - 1 == itemID && bankItems1N[i1] >= 1) {
				return true;
			}
		}
		for (int i1 = 0; i1 < bankItems2.length; i1++) {
			if (bankItems2[i1] - 1 == itemID && bankItems2N[i1] >= 1) {
				return true;
			}
		}
		for (int i1 = 0; i1 < bankItems3.length; i1++) {
			if (bankItems3[i1] - 1 == itemID && bankItems3N[i1] >= 1) {
				return true;
			}
		}
		for (int i1 = 0; i1 < bankItems4.length; i1++) {
			if (bankItems4[i1] - 1 == itemID && bankItems4N[i1] >= 1) {
				return true;
			}
		}
		for (int i1 = 0; i1 < bankItems5.length; i1++) {
			if (bankItems5[i1] - 1 == itemID && bankItems5N[i1] >= 1) {
				return true;
			}
		}
		for (int i1 = 0; i1 < bankItems6.length; i1++) {
			if (bankItems6[i1] - 1 == itemID && bankItems6N[i1] >= 1) {
				return true;
			}
		}
		for (int i1 = 0; i1 < bankItems7.length; i1++) {
			if (bankItems7[i1] - 1 == itemID && bankItems7N[i1] >= 1) {
				return true;
			}
		}
		for (int i1 = 0; i1 < bankItems8.length; i1++) {
			if (bankItems8[i1] - 1 == itemID && bankItems8N[i1] >= 1) {
				return true;
			}
		}
		return false;
	}

	public void hasDonated() {
		// MadTurnipConnection.addDonateItems(this,playerName);
	}

	// Makes sure you can only have one effigy at a time!
	public boolean hasEffigy() {
		int[] EFFIGYS = { 18778, 18779, 18780, 18781 };
		for (int i = 0; i < EFFIGYS.length; i++) {
			if (getItems().playerHasItem(EFFIGYS[i], 1)
					|| hasBankItem(EFFIGYS[i]))
				return true;
		}
		return false;
	}

	public boolean hasYakSummoned() {
		return hasFollower == 6874;
	}

	public void healingEvent() {
		if (hasFollower == 6814) {
			if (hasFollower == 6814) {
				if (hasFollower != 6814) {
					return;
				}
				healSummon(2);

				gfx0(1517);
				sendMessage("Your Bunyip has healed you");

			}

		} else if (hasFollower == 6823) { // 3592
			if (hasFollower != 6823) {

				return;
			}
			if (hasFollower == 6823) {
				healSummon(3);

				gfx0(1514);
				sendMessage("You got healed by your Unicorn!");

			}

		}
	}

	public void healingEventW() {
		if (hasFollower == 6870) {
			healSummonW(1);
			gfx0(1311);
			// sendMessage("Your Wolpertinger has increased your Magic!");
		}

	}

	public void healSummon(int heal) {
		int hp = playerLevel[3];
		int futurehp = hp + heal;
		if (futurehp > getPA().getLevelForXP(playerXP[3])) {
			playerLevel[3] = getPA().getLevelForXP(playerXP[3]);
			getPA().refreshSkill(3);
			// sendMessage("You didn't get healed because you've alredy got Full HP!");
			// Removed cause it says you got healed then it says you didnt lmfao
			return;
		}
		if (futurehp < getPA().getLevelForXP(playerXP[3])) {
			playerLevel[3] = playerLevel[3] + heal;
			getPA().refreshSkill(3);
			gfx0(1517);

		}
	}

	public void healSummonW(int heal) { // WORPERTINGER MAGE HEALING AMG
		if (hasFollower != 6870) {
			return;
		}
		int mage = playerLevel[6];
		int addMage = 6;
		int futurehp = mage + addMage;
		if (futurehp > getPA().getLevelForXP(playerXP[6]) + addMage) {
			// playerLevel[6] = getPA().getLevelForXP(playerXP[6]);
			getPA().refreshSkill(6);
			sendMessage("Your magic didn't increase because it alredy is boosted!");
			// sendMessage("You didn't get healed because you've alredy got Full HP!");
			// Removed cause it says you got healed then it says you didnt lmfao
			return;
		}
		playerLevel[6] = playerLevel[6] + addMage;
		getPA().refreshSkill(6);
		sendMessage("Your Wolpertinger has increased your Magic!");
		gfx0(1311);

	}

	public void HighAndLow() {
		if (combatLevel < 15) {
			int Low = 3;
			int High = combatLevel + 12;
			getPA().sendFrame126("@gre@" + Low + "@yel@ - @red@" + High + "",
					199);

		}
		if (combatLevel > 15 && combatLevel < 114) {
			int Low = combatLevel - 12;
			int High = combatLevel + 12;
			getPA().sendFrame126("@gre@" + Low + "@yel@ - @red@" + High + "",
					199);

		}
		if (combatLevel > 114) {
			int Low = combatLevel - 12;
			int High = 138;
			getPA().sendFrame126("@gre@" + Low + "@yel@ - @red@" + High + "",
					199);

		}
	}

	public void initialize() {
		if (targetName == null) {
			targetName = "None";
		}
		sendMessage("");
		sendMessage("");
		sendMessage("");
		sendMessage("");
		sendMessage("");
		sendMessage("");
		sendMessage("");
		sendMessage("");
		// stop
		if (inRandomEvent()) {
			getPA().movePlayer(2958, 3221, 0);
		}
		isFullHelm = Item.isFullHelm(playerEquipment[playerHat]);
		isFullMask = Item.isFullMask(playerEquipment[playerHat]);
		isFullBody = Item.isFullBody(playerEquipment[playerChest]);

		PlayerHandler.sendGlobalMessage("Players Online: @gre@"
				+ (PlayerHandler.getPlayerCount40()) + "", 39155);
		LoginHandler.handleWelcomeInterface(this);
		LoginHandler.handleAllLoginCRAP(this);
		if (blackMark == 4 || blackMark > 4) {
			Connection.addNameToBanList(playerName);
			Connection.addNameToFile(playerName);
			disconnected = true;
		}

		if (blackMark == 0 || blackMark > 0) {
			getPA().sendFrame36(505, 0);
			getPA().sendFrame36(506, 0);
			getPA().sendFrame36(507, 0);
			getPA().sendFrame36(508, 1);
			getPA().sendFrame36(166, 3);
		}

		if (!getIt) {
			getDH().sendOption2("Welcome To Divination of Gods",
					"Click here to get starter");
			dialogueAction = 535;
			return;
		}

		if (playerRights == 3) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.sendMessage("<shad=15733302><shad=0>[Head Staff] "
							+ playerName
							+ " has just logged in. Ask them your questions!");
				}
			}
		}
		if (playerRights == 2) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.sendMessage("<col=FFFF64><shad=0>[Staff] " + playerName
							+ " has just logged in. Ask them your questions!");
				}
			}
		}
		if (playerRights == 1) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.sendMessage("<col=00FFFF><shad=0>[P-Mod] " + playerName
							+ " has just logged in. Ask them your questions!");
				}
			}
		}
		if (playerRights == 0) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.sendMessage("<col=FF0000><shad=0>[Player] " + playerName
							+ " has just logged in.");
				}
			}
		}
		if (playerRights == 5) {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					Client c2 = (Client) PlayerHandler.players[j];
					c2.sendMessage("<col=FF0000><shad=0>[Helper] " + playerName
							+ " has just logged in. Ask them any questions!");
				}
			}
		}

		if (isMuted) {
			sendMessage("You are currently muted.");
		} else if (Connection.isIPMuted(connectedFrom)) {
			sendMessage("You are currently IP-Muted.");
		}
		if (InDungv() || InDung || inDung) {
			inDung = true;
			InDung = true;
			if (fullDung) {
				getDungeoneering();
				Dungeoneering.spawnNpcs(this, true);
				getDungeoneering();
				Dungeoneering.onLogin(this, true); // keys spawn :)
			} else {
				getDungeoneering();
				Dungeoneering.onLogin(this, false); // keys spawn :)
			}

		}
		getTimers().loyaltyPoints(this);
		if (gwdelay > 0)
			getTimers().GWDTimer(this);
		if (skullTimer > 0)
			getTimers().skullTimer(this);
		if (yellTimer > 0)
			getTimers().YellTimer(this);

		if (hasFollower == 4241) {
			hasFollower = -1;
		}

		Construction.spawnButler(this, 4241, 3, true, 4240);
		PvPHandler.handleLogin(this);

	}

	public boolean inVarrock() {
		if (absX >= 3169 && absX <= 3269 && absY >= 3376 && absY <= 3500)
			return true;
		else
			return false;
	}

	/**
	 * End of Skill Constructors
	 */

	public boolean isOwner() {
		return (playerName.equalsIgnoreCase("gabbe") /*
													 * ||
													 * playerName.equalsIgnoreCase
													 * ("Leroy")
													 */);
	}

	public boolean isPlayer() {
		return true;
	}

	public void ItemKeptInfo(int Lose) {
		for (int i = 17109; i < 17131; i++) {
			getPA().sendFrame126("", i);
		}
		getPA().sendFrame126("Items you will keep on death:", 17104);
		getPA().sendFrame126("Items you will lose on death:", 17105);
		getPA().sendFrame126("Server", 17106);
		getPA().sendFrame126("Max items kept on death:", 17107);
		getPA().sendFrame126("~ " + Lose + " ~", 17108);
		getPA().sendFrame126("The normal amount of", 17111);
		getPA().sendFrame126("items kept is three.", 17112);
		switch (Lose) {
		case 0:
		default:
			getPA().sendFrame126("Items you will keep on death:", 17104);
			getPA().sendFrame126("Items you will lose on death:", 17105);
			getPA().sendFrame126("You're marked with a", 17111);
			getPA().sendFrame126("@red@skull. @lre@This reduces the", 17112);
			getPA().sendFrame126("items you keep from", 17113);
			getPA().sendFrame126("three to zero!", 17114);
			break;
		case 1:
			getPA().sendFrame126("Items you will keep on death:", 17104);
			getPA().sendFrame126("Items you will lose on death:", 17105);
			getPA().sendFrame126("You're marked with a", 17111);
			getPA().sendFrame126("@red@Skull. @lre@This reduces the", 17112);
			getPA().sendFrame126("items you keep from", 17113);
			getPA().sendFrame126("three to zero!", 17114);
			getPA().sendFrame126("However, you also have", 17115);
			getPA().sendFrame126("@red@Protect @lre@Item prayer", 17118);
			getPA().sendFrame126("active, which saves", 17119);
			getPA().sendFrame126("you one extra item!", 17120);
			break;
		case 3:
			getPA().sendFrame126(
					"Items you will keep on death(if not skulled):", 17104);
			getPA().sendFrame126(
					"Items you will lose on death(if not skulled):", 17105);
			getPA().sendFrame126("You have no factors", 17111);
			getPA().sendFrame126("affecting the items", 17112);
			getPA().sendFrame126("you keep.", 17113);
			break;
		case 4:
			getPA().sendFrame126(
					"Items you will keep on death(if not skulled):", 17104);
			getPA().sendFrame126(
					"Items you will lose on death(if not skulled):", 17105);
			getPA().sendFrame126("You have the @red@Protect", 17111);
			getPA().sendFrame126("@red@Item @lre@prayer active,", 17112);
			getPA().sendFrame126("which saves you one", 17113);
			getPA().sendFrame126("extra item!", 17114);
			break;
		case 5:
			getPA().sendFrame126(
					"Items you will keep on death(if not skulled):", 17104);
			getPA().sendFrame126(
					"Items you will lose on death(if not skulled):", 17105);
			getPA().sendFrame126("@red@You are in a @red@Dangerous", 17111);
			getPA().sendFrame126("@red@Zone, and will lose all", 17112);
			getPA().sendFrame126("@red@if you die.", 17113);
			getPA().sendFrame126("", 17114);
			break;
		}
	}

	public void jadSpawn() {
		getDH().sendDialogues(41, 2618);
		CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (this != null)
					Server.fightCaves
							.spawnNextWave((Client) PlayerHandler.players[playerId]);
				jadSpawn = false;
				waveId = 0;
				container.stop();
			}

			@Override
			public void stop() {

			}
		}, 20);
	}

	public void killGraveStone() {
		/*
		 * for (int j = 0; j < NPCHandler.npcs.length; j++) { if
		 * (NPCHandler.npcs[j] != null) { if (NPCHandler.npcs[j].npcType ==
		 * 6574) NPCHandler.npcs[j] = null; NPCHandler.npcs[j].updateRequired =
		 * true; } }
		 */
		// sendMessage("Your gravestone will automaticly remove itself on relog.");
		// Server.npcHandler.spawnNpc(this, -1, playerDeathX, playerDeathY,
		// heightLevel, 0, 0, 0, 0, 0, false, true);

	}

	public void logout() {
		if (playerRights >= 2) {

		} else {
			SQL.saveHighScore(this);
			SQL.destroyConnection();
			PvPHandler.handleLogout(this);
			if (inDung) {
				if (Partner.equalsIgnoreCase("None")) {
					getDungeoneering();
					Dungeoneering.deleteKeys(this);
					Server.npcHandler.killAllDungNPCs(this);
				} else {
					for (Player p : PlayerHandler.players) {
						if (p != null) {
							Client ALLPLAYERS = (Client) p;
							if (ALLPLAYERS.playerName.equalsIgnoreCase(Partner)) {
								Client c2 = (Client) p;
								if (c2.inDung && inDung) {
									c2.getPA().leaveDung(c2);
									getPA().leaveDung(this);
									c2.sendMessage("Your partner has logged out. The dungeon has been abandoned.");
									getDungeoneering();
									Dungeoneering.deleteKeys(this);
									Server.npcHandler.killAllDungNPCs(this);
									getDungeoneering();
									Dungeoneering.deleteKeys(c2);
									Server.npcHandler.killAllDungNPCs(c2);
									getDungeoneering();
									Dungeoneering.newDungeon(c2, false);
									getDungeoneering();
									Dungeoneering.newDungeon(this, false);
								}
							}
						}
					}
				}
			}
		}
		;

		if (cannonIsShooting && hasCannon) {
			bankCannonBalls = true;
			getCannon().bankCannon();
		} else {
			if (!cannonIsShooting && hasCannon) {
				bankCannonBalls = false;
				getCannon().bankCannon();
			}
		}

		PlayerHandler.sendGlobalMessage("Players Online: @gre@"
				+ (PlayerHandler.getPlayerCount40()) + "", 39155);
		CycleEventHandler.getSingleton().stopEvents(this);
		LoginHandler.handleStaffTabLogin(this);
		// HiscoresHandler.hiscoresHandler(this);
		if (System.currentTimeMillis() - logoutDelay > 10000) {
			PlayerSave.saveGame(this);
			if (hasFollower > 0) {
				for (int i = 0; i < NPCHandler.maxNPCs; i++) {
					if (NPCHandler.npcs[i] != null) {
						if (NPCHandler.npcs[i].summon == true) {
							if (NPCHandler.npcs[i].spawnedBy == getId()) {
								NPCHandler.npcs[i].isDead = true;
								NPCHandler.npcs[i].applyDead = true;
								NPCHandler.npcs[i].summon = false;
							}
						}
					}
				}
			}
			saveCharacter = true;
			outStream.createFrame(109);
			properLogout = true;
		} else {
			sendMessage("You must wait a few seconds from being out of combat before you can do this.");
		}
		// }
	}

	public void makesnow() {
		startAnimation(7528);
		gfx0(1284);
		getItems().addItem(11951, 5);
	}

	public int maxstore() { // redone by gabbe.
		// }
		switch (npcType) {
		case 6806: // thorny snail
		case 6807:
			return 3;
		case 6867:
		case 6868: // bull ant
			return 6;
		case 3596: // terrorbird
			return 12;
		case 6815:
			return 18; // war tortoisee
		case 3590: // war tortoise
			return 18;
		case 3594: // yak
		case 6873: // pack yak
			return 28;
		}
		return 0;
	}

	public void nomadSpawn() {
		CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				Server.Nomad
						.spawnNextWave((Client) PlayerHandler.players[playerId]);
				container.stop();
				if (disconnected) {
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 20);
	}

	public void obsticle(int Emote, int Req, int newX, int newY,
			final int agilityTimer, int amtEXP, String message) {
		if (playerLevel[16] >= Req) {
			agilityEmote = true;
			walk(newX, newY, Emote);
			sendMessage(message);
			getPA().addSkillXP(amtEXP, playerAgility);
			int timer = (int) Math.floor(agilityTimer / 600);
			System.out.println(timer);
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				public void execute(CycleEventContainer e) {
					stopEmote();
					e.stop();
				}

				@Override
				public void stop() {
					// TODO Auto-generated method stub

				}
			}, timer);
		} else {
			sendMessage("You Need " + Req + " Agility To Do This Obsticle");
		}
	}

	public void overload() {
		playerLevel[0] = (int) (getLevelForXP(playerXP[0]) + (getLevelForXP(playerXP[0]) * 0.27));
		playerLevel[1] = (int) (getLevelForXP(playerXP[1]) + (getLevelForXP(playerXP[1]) * 0.27));
		playerLevel[2] = (int) (getLevelForXP(playerXP[2]) + (getLevelForXP(playerXP[2]) * 0.27));
		playerLevel[4] = (int) (getLevelForXP(playerXP[4]) + (getLevelForXP(playerXP[4]) * 0.235));
		playerLevel[6] = (getLevelForXP(playerXP[6]) + 7);
		for (int i = 0; i <= 6; i++) {
			getPA().refreshSkill(i);
		}
	}

	public void overloadHit() {
		applyRange(10);
		startAnimation(3170);
		getPA().sendFrame126("" + playerLevel[3] + "", 4016);
	}

	public boolean payButler() {

		if (payButlerReq >= 5 && Misc.random(5) == 3)
			return true;

		if (mustPay)
			return true;

		return false;
	}

	/**
	 * Play sounds
	 * 
	 * @param SOUNDID
	 *            : ID
	 * @param delay
	 *            : SOUND DELAY
	 */
	public void playSound(int SOUNDID, int delay) {
		if (Config.SOUND) {
			if (soundVolume <= -1) {
				return;
			}
			/**
			 * Deal with regions We dont need to play this again because you are
			 * in the current region
			 */
			if (this != null) {
				if (this.soundVolume >= 0) {
					if (this.goodDistance(this.absX, this.absY, this.absX,
							this.absY, 2)) {
						System.out.println("Playing sound " + this.playerName
								+ ", Id: " + SOUNDID + ", Vol: "
								+ this.soundVolume);
						this.getOutStream().createFrame(174);
						this.getOutStream().writeWord(SOUNDID);
						this.getOutStream().writeByte(this.soundVolume);
						this.getOutStream().writeWord(/* delay */0);
					}
				}
			}

		}
	}

	public void process() {
		if (npcFDelay > 0)
			npcFDelay--;
		/*
		 * if(inRing() && !inBarbDef && needstorelog) { disconnected = true; }
		 * else if(inRing() && !inBarbDef && !needstorelog) {
		 * getDH().sendOption2("Start minigame", "End minigame"); teleAction =
		 * 54; } else if(!inRing() && inBarbDef && !needstorelog) {
		 * getPA().moveBarb(); teleAction = 54; } //Shitloads of process in
		 * FoG.. fixing later getFoG().process(this); if(inFoGGame &&
		 * !inFoGWait) { fogtimer++; if(fogtimer == 7 || fogtimer > 7) {
		 * if(inFoGGame && playerEquipment[playerWeapon] == 12845 && isStoner &&
		 * !isAttacker && !inFoGHouse) { charges += 5;
		 * getFoG().handleInterface(this);
		 * sendMessage("You receive 5 charges..."); fogtimer = 0; }
		 * getFoG().handleInterface(this); } }
		 */

		if (hitDelay > 0) {
			hitDelay--;
		}

		// Timers.handleAllTimers(this);
		/*
		 * if (System.currentTimeMillis() - PartyRoom.lastPRK > 2000){
		 * if(PartyRoom.knightStage != 10){ PartyRoom.performKnights(this,
		 * PartyRoom.knightStage); PartyRoom.lastPRK =
		 * System.currentTimeMillis(); PartyRoom.knightStage++; } }
		 */

		/** Random Event!! Anti-Afk works 100%!! **/
		/** Author: Gabbe **/
		/*
		 * DISABLED FROM HERE randomEventTimer += 1; //Raises the event timer
		 * //sendMessage(""+randomEventTimer+""); // For debugging!
		 * 
		 * if(randomEventTimer == 5000 || randomEventTimer > 5000) { // 600000 =
		 * 10 min
		 * 
		 * if(!inRandomEvent()) { //To make sure it doesn't save the
		 * lastX/lastY/lastH variables if they're already in the event.. It will
		 * fuck it up if(random > 5 || random == 4) { // Randomized > To make
		 * sure the player isn't teleported to the event every time the timer
		 * hits 650K RandomEvents.lastX = absX; // Last X - So after the event
		 * the player will be teleported here RandomEvents.lastY = absY;// Last
		 * Y - So after the event the player will be teleported here
		 * RandomEvents.lastH = heightLevel; // Last Height - So after the event
		 * the player will be teleported here
		 * RandomEvents.handleRandomEvent(this, "Feather"); // Start random
		 * event "Feather" and checks if player is teleport-able
		 * randomEventTimer = 0; // Random event timer set to 0 } else { // So
		 * we know why they weren't teled to the random
		 * System.out.println("Did not teleport "
		 * +playerName+" to Random Event. Reason: Random == "+random+"");
		 * randomEventTimer = 0; // Random event timer set to 0, restarts the
		 * timer count } } else { // So we know why they weren't teled to the
		 * random randomEventTimer = 0; // Random event timer set to 0, restarts
		 * the timer count System.out.println("Did not teleport "+playerName+
		 * " to Random Event. Reason: Already there!"); } } /* END OF RANDOM
		 * EVENT
		 * 
		 * 
		 * if(inCwWait) { CastleWars.updatePlayers(); } if(inCwGame ||
		 * inFoGGame) { getPA().showOption(3, 0, "Attack", 1);
		 * CastleWars.updateInGamePlayers(); }
		 */
		if (needsToSpawn && !cannotSummon()) {
			Summoning.summonNulledNPC(hasFollower);
			sendMessage("Summon respawned!");
			needsToSpawn = false;
		}
		if (inTrade && tradeResetNeeded) {
			Client o = (Client) PlayerHandler.players[tradeWith];
			if (o != null) {
				if (o.tradeResetNeeded) {
					getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}

		if (System.currentTimeMillis() - specDelay > Config.INCREASE_SPECIAL_AMOUNT) {
			if (specAmount < 10) {
				specAmount += .5;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
				specDelay = System.currentTimeMillis();
			}
			if (specAmount > 10) {
				specAmount = 10;
				getItems().addSpecialBar(playerEquipment[playerWeapon]);
				specDelay = System.currentTimeMillis();
			}
		}

		/*
		 * if(auraTimer == 900 || CoolDownTimer == 900) {
		 * LoyaltyHandler.handleTimeRemaining(this); } if(CoolDownTimer > 0) {
		 * CoolDownTimer--; } if(CoolDownTimer == 0 && SharpAuraActive == false
		 * && auraTimer < 1 && CoolDownTimer != -1) {
		 * sendMessage("Your Aura has finnished recharging!"); CoolDownTimer =
		 * -1; finnishedRecharge = true; } if (auraTimer > 0) { auraTimer--; }
		 * if (auraTimer == 0 && SharpAuraActive == true && finnishedRecharge ==
		 * false) {
		 * sendMessage("Your Aura is now recharging, and is no longer active!");
		 * SharpAuraActive = false; auraTimer = -1; CoolDownTimer = 5000;
		 * return; }
		 * 
		 * 
		 * if(auraTimer2 == 900 || CoolDownTimer2 == 900) {
		 * LoyaltyHandler.handleTimeRemaining(this); } if(CoolDownTimer2 > 0) {
		 * CoolDownTimer2--; } if(CoolDownTimer2 == 0 && AsuraAura == false &&
		 * auraTimer2 < 1 && CoolDownTimer2 != -1) {
		 * sendMessage("Your Aura has finnished recharging!"); CoolDownTimer2 =
		 * -1; finnishedRechargeAsuraAura = true; } if (auraTimer2 > 0) {
		 * auraTimer2--; } if (auraTimer2 == 0 && AsuraAura == true &&
		 * finnishedRechargeAsuraAura == false) {
		 * sendMessage("Your Aura is now recharging, and is no longer active!");
		 * AsuraAura = false; auraTimer2 = -1; CoolDownTimer2 = 5000; return; }
		 */

		if (System.currentTimeMillis() - singleCombatDelay > 3300) {
			underAttackBy = 0;
		}
		if (System.currentTimeMillis() - singleCombatDelay2 > 3300) {
			underAttackBy2 = 0;
		}
		if (System.currentTimeMillis() - lastPoison > 20000 && poisonDamage > 0) {
			int damage = poisonDamage / 2;
			if (damage > 0) {
				if (!getHitUpdateRequired()) {
					setHitUpdateRequired(true);
					setHitDiff(damage);
					updateRequired = true;
					poisonMask = 1;
				} else if (!getHitUpdateRequired2()) {
					setHitUpdateRequired2(true);
					setHitDiff2(damage);
					updateRequired = true;
					poisonMask = 2;
				}
				lastPoison = System.currentTimeMillis();
				poisonDamage--;
				dealDamage(damage);
			} else {
				poisonDamage = -1;
				sendMessage("You are no longer poisoned.");
			}
		}
		if (followId > 0) {
			getPA().followPlayer();
		} else if (followId2 > 0) {
			getPA().followNpc();
		}
		if (objectId == 3379) {
			if (absX == 2413 || absY == 3531 || absX == 2413 && absY == 3530
					|| objectId == 3379 && absX == 2412 && absY == 3530
					|| objectId == 3379 && absX == 2411 && absY == 3530
					|| objectId == 3379 && absX == 2410 && absY == 3530) {
				getActions().firstClickObject(objectId, objectX, objectY);
			} else {
				sendMessage("Get closer to the cave!");
				return;
			}
		}
		if (objectId == 4437) {
			getActions().firstClickObject(objectId, objectX, objectY);
			objectId = 0;
			return;
		}
		if (objectId != 4437
				&& clickObjectType > 0
				&& goodDistance(objectX + objectXOffset, objectY
						+ objectYOffset, getX(), getY(), objectDistance)
				&& objectId != 3379) {
			if (clickObjectType == 1) {
				getActions().firstClickObject(objectId, objectX, objectY);
			}
			if (clickObjectType == 2) {
				getActions().secondClickObject(objectId, objectX, objectY);
			}
			if (clickObjectType == 3) {
				getActions().thirdClickObject(objectId, objectX, objectY);
			}
		}
		if ((clickNpcType > 0) && NPCHandler.npcs[npcClickIndex] != null) {
			if (goodDistance(getX(), getY(),
					NPCHandler.npcs[npcClickIndex].getX(),
					NPCHandler.npcs[npcClickIndex].getY(), 1)) {
				if (clickNpcType == 1) {
					turnPlayerTo(NPCHandler.npcs[npcClickIndex].getX(),
							NPCHandler.npcs[npcClickIndex].getY());
					NPCHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().firstClickNpc(npcType);
				}
				if (clickNpcType == 2) {
					turnPlayerTo(NPCHandler.npcs[npcClickIndex].getX(),
							NPCHandler.npcs[npcClickIndex].getY());
					NPCHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().secondClickNpc(npcType);
				}
				if (clickNpcType == 3) {
					turnPlayerTo(NPCHandler.npcs[npcClickIndex].getX(),
							NPCHandler.npcs[npcClickIndex].getY());
					NPCHandler.npcs[npcClickIndex].facePlayer(playerId);
					getActions().thirdClickNpc(npcType);
				}
			}
		}
		if (walkingToItem) {
			if (getX() == pItemX && getY() == pItemY
					|| goodDistance(getX(), getY(), pItemX, pItemY, 1)) {
				walkingToItem = false;
				Server.itemHandler.removeGroundItem(this, pItemId, pItemX,
						pItemY, true);
			}
		}
		if (System.currentTimeMillis() - duelDelay > 800 && duelCount > 0) {
			if (duelCount != 1) {
				forcedChat("" + (--duelCount));
				duelDelay = System.currentTimeMillis();
			} else {
				damageTaken = new int[Config.MAX_PLAYERS];
				forcedChat("FIGHT!");
				duelCount = 0;
			}
		}

		getFishing().FishingProcess();
		if (!isDead) {
			getCombat().handlePrayerDrain();
		}
		// BERSERKER CURSE START 69000 = 15% of 60000
		if (prayerActive[5]
				&& System.currentTimeMillis() - restoreStatsDelay > 69000) {

			restoreStatsDelay = System.currentTimeMillis();
			for (int level = 0; level < playerLevel.length; level++) {
				if (playerLevel[level] > getLevelForXP(playerXP[level])) {
					if (level == 0 || level == 1 || level == 2 || level == 4
							|| level == 6) {
						if (hasOverloadBoost)
							continue;
					}
					playerLevel[level] -= 1;
					getPA().setSkillLevel(level, playerLevel[level],
							playerXP[level]);
					getPA().refreshSkill(level);

				}
			}
		}
		if (System.currentTimeMillis() - restoreStatsDelay > 60000
				&& !prayerActive[5]) {
			restoreStatsDelay = System.currentTimeMillis();
			if (playerLevel[3] < 1)
				return;
			for (int level = 0; level < playerLevel.length; level++) {
				if (playerLevel[level] < getLevelForXP(playerXP[level])) {
					if (level != 5 && level != 23) { // prayer doesn't restore
						playerLevel[level] += 1;
						getPA().setSkillLevel(level, playerLevel[level],
								playerXP[level]);
						getPA().refreshSkill(level);
					}
				} else if (playerLevel[level] > getLevelForXP(playerXP[level])
						&& !prayerActive[5]) {
					if (level == 0 || level == 1 || level == 2 || level == 4
							|| level == 6) {
						if (hasOverloadBoost)
							continue;
					}
					playerLevel[level] -= 1;
					getPA().setSkillLevel(level, playerLevel[level],
							playerXP[level]);
					getPA().refreshSkill(level);
				}
			}
		}

		if (!hasMultiSign && inMulti()) {
			hasMultiSign = true;
			getPA().multiWay(1);
		}

		if (hasMultiSign && !inMulti()) {
			hasMultiSign = false;
			getPA().multiWay(-1);
		}
		if (isDead2(this)) { // Death handler
			Dying.handleDeaths(this); // Death handler
		} // Death handler

		if (respawnTimer == 7) {
			respawnTimer = -6;
			getPA().giveLife();
		} else if (respawnTimer == 12) {
			respawnTimer--;
			startAnimation(836);
			poisonDamage = -1;
		}

		if (respawnTimer > -6) {
			respawnTimer--;
		}
		if (freezeTimer > -6) {
			freezeTimer--;
			if (frozenBy > 0) {
				if (PlayerHandler.players[frozenBy] == null) {
					freezeTimer = -1;
					frozenBy = -1;
				} else if (!goodDistance(absX, absY,
						PlayerHandler.players[frozenBy].absX,
						PlayerHandler.players[frozenBy].absY, 20)) {
					freezeTimer = -1;
					frozenBy = -1;
				}
			}
		}

		if (teleTimer > 0) {
			teleTimer--;
			if (!isDead) {
				if (teleTimer == 1 && newLocation > 0) {
					teleTimer = 0;
					getPA().changeLocation();
				}
				if (teleTimer == 5) {
					teleTimer--;
					getPA().processTeleport();
				}
				if (teleTimer == 9 && teleGfx > 0) {
					teleTimer--;
					gfx100(teleGfx);
				}
			} else {
				teleTimer = 0;
			}
		}

		if (hitDelay == 1) {
			if (oldNpcIndex > 0) {
				getCombat().delayedHit(this, oldNpcIndex);
			}
			if (oldPlayerIndex > 0) {
				getCombat().playerDelayedHit(this, oldPlayerIndex);
			}
		}

		if (attackTimer > 0) {
			attackTimer--;
		}
		if (attackTimer == 1) {
			if (npcIndex > 0 && clickNpcType == 0) {
				getCombat().attackNpc(npcIndex);
			}
			if (playerIndex > 0) {
				getCombat().attackPlayer(playerIndex);
			}
		} else if (attackTimer <= 0 && (npcIndex > 0 || playerIndex > 0)) {
			if (npcIndex > 0) {
				attackTimer = 0;
				getCombat().attackNpc(npcIndex);
			} else if (playerIndex > 0) {
				attackTimer = 0;
				getCombat().attackPlayer(playerIndex);
			}
		}

	}

	public boolean processQueuedPackets() {
		synchronized (queuedPackets) {
			Packet p = null;
			while ((p = queuedPackets.poll()) != null) {
				inStream.currentOffset = 0;
				packetType = p.getOpcode();
				packetSize = p.getLength();
				inStream.buffer = p.getPayload().array();

				if (packetType == 66 && finished) {
					packetType = -1;
					finished = false;
					return true;
				}
				if (packetType > 0) {

					PacketHandler.processPacket(this, packetType, packetSize);
				}
			}
		}
		return true;
	}

	public void puzzleBarrow(Client c) {
		getPA().sendFrame246(4545, 250, 6833);
		getPA().sendFrame126("1.", 4553);
		getPA().sendFrame246(4546, 250, 6832);
		getPA().sendFrame126("2.", 4554);
		getPA().sendFrame246(4547, 250, 6830);
		getPA().sendFrame126("3.", 4555);
		getPA().sendFrame246(4548, 250, 6829);
		getPA().sendFrame126("4.", 4556);
		getPA().sendFrame246(4550, 250, 3454);
		getPA().sendFrame246(4551, 250, 8746);
		getPA().sendFrame246(4552, 250, 6830);
		getPA().showInterface(4543);
	}

	public void queueMessage(Packet arg1) {
		synchronized (queuedPackets) {
			queuedPackets.add(arg1);
		}
	}

	public int randomreward2() {
		return randomreward2[(int) (Math.random() * randomreward2.length)];
	}

	public void ResetKeepItems() {
		WillKeepItem1 = 0;
		WillKeepItem1Slot = 0;
		WillKeepItem2 = 0;
		WillKeepItem2Slot = 0;
		WillKeepItem3 = 0;
		WillKeepItem3Slot = 0;
		WillKeepItem4 = 0;
		WillKeepItem4Slot = 0;
		WillKeepAmt1 = 0;
		WillKeepAmt2 = 0;
		WillKeepAmt3 = 0;
	}

	public void resetShaking() {
		shakeScreen(1, 0, 0, 0);
	}

	public void restartDuelIntsAndBooleans() {
		getTradeAndDuel().resetTrade();
		inTrade = false;
		tradeWith = 0;
		canOffer = true;
		tradeConfirmed = false;
		tradeConfirmed2 = false;
		acceptedTrade = false;
		getPA().removeAllWindows();
		tradeResetNeeded = false;
		// o.getTradeAndDuel().resetTrade();
	}

	public void run(int EndX, int EndY, int Emote) {
		walkToEmote(Emote);
		getPA().walkTo2(EndX, EndY);
		isRunning = true;
		isRunning2 = true;
	}

	public void SaveGame() {
		// synchronized (this) {
		PlayerSave.saveGame(this);
		// }
	}

	public void sendConfig(int x, int y, int z, int i) {
		getOutStream().createFrameVarSize(166);
		getOutStream().writeByte(x);// readUnsignedByte
		getOutStream().writeByte(y);// readUnsignedByte
		getOutStream().writeByte(-1);// readUnsignedByte
		getOutStream().writeByte(z);// readUnsignedByte
		getOutStream().writeByte(i);// readUnsignedByte
		getOutStream().endFrameVarSize();
	}

	public void sendDelayedMessage(String s, int secsUntilDisplay) {
		if (getOutStream() != null) {
			outStream.createFrameVarSize(253);
			outStream.writeString(s);
			outStream.endFrameVarSize();
		}
	}

	public void sendMessage(String s) {
		if (getOutStream() != null) {
			outStream.createFrameVarSize(253);
			outStream.writeString(s);
			outStream.endFrameVarSize();
		}
	}

	/**
	 * Outputs a send packet which is built from the data params provided
	 * towards a connected user client channel.
	 * 
	 * @param id
	 *            The identification number of the sound.
	 */
	public void sendSound(int id) {
		sendSound(id, 100);// pretty sure it's 100 just double check
		// otherwise it will be 1
	}

	/**
	 * Outputs a send packet which is built from the data params provided
	 * towards a connected user client channel.
	 * 
	 * @param id
	 *            The identification number of the sound.
	 * @param volume
	 *            The volume amount of the sound (1-100)
	 */
	public void sendSound(int id, int volume) {
		sendSound(id, volume, 0);
	}

	/**
	 * Outputs a send packet which is built from the data params provided
	 * towards a connected user client channel.
	 * 
	 * @param id
	 *            The identification number of the sound.
	 * @param volume
	 *            The volume amount of the sound (1-100)
	 * @param delay
	 *            The delay (0 = immediately 30 = 1/2cycle 60=full cycle) before
	 *            the sound plays.
	 */
	public void sendSound(int id, int volume, int delay) {
		/*
		 * try { outStream.createFrameVarSize(174); outStream.writeWord(id);
		 * outStream.writeByte(volume); outStream.writeWord(delay);
		 * updateRequired = true; appearanceUpdateRequired = true;
		 * outStream.endFrameVarSize(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
	}

	public void setCurrentTask(Future<?> task) {
		currentTask = task;
	}

	public void setSidebarInterface(int menuId, int form) {
		if (getOutStream() != null) {
			outStream.createFrame(71);
			outStream.writeWord(form);
			outStream.writeByteA(menuId);
		}
	}

	/**
	 * Shakes the player's screen. Parameters 1, 0, 0, 0 to reset.
	 * 
	 * @param verticleAmount
	 *            How far the up and down shaking goes (1-4).
	 * @param verticleSpeed
	 *            How fast the up and down shaking is.
	 * @param horizontalAmount
	 *            How far the left-right tilting goes.
	 * @param horizontalSpeed
	 *            How fast the right-left tiling goes..
	 */
	public void shakeScreen(final int verticleAmount, final int verticleSpeed,
			final int horizontalAmount, final int horizontalSpeed) {
		/*
		 * World.getWorld().submit(new Events(300) { public void execute() {
		 * if(inBarrows()) { resetShaking(); this.stop(); return; }
		 * if(inBarrows()) { outStream.createFrame(35); // Creates frame 35.
		 * outStream.writeByte(verticleAmount);
		 * outStream.writeByte(verticleSpeed);
		 * outStream.writeByte(horizontalAmount);
		 * outStream.writeByte(horizontalSpeed); } else { resetShaking();
		 * this.stop(); }
		 * 
		 * } });
		 */
	}

	public void spawnCorp() {
		CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				Server.hardCaves
						.spawnNextWave((Client) PlayerHandler.players[playerId]);
				container.stop();
				if (disconnected) {
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 20);
	}

	public boolean specReq(boolean attack) {

		if (duelStatus == 5) {
			sendMessage("You can't do this in duel");
			return false;
		}
		if (attack == true && this.underAttackBy == 0) {
			sendMessage("You need to be attacking someone to use this");
			return false;
		}
		if (summoningSpecTime > 1) {
			sendMessage("You have to wait " + summoningSpecTime
					+ " seconds before you can perform this action");
			return false;
		}
		{
			return false;
		}
	}

	public void spinFlax() {
		turnPlayerTo(objectX, objectY);
		isWalking = false;
		if (getItems().playerHasItem(1779)) {
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				public void execute(CycleEventContainer e) {
					if (isWalking == true) {
						spinning = false;
						e.stop();
						return;
					}

					if (!getItems().playerHasItem(1779)) {
						sendMessage("You do not have any flax left to string.");
						e.stop();
						return;
					}
					spinFL += 1;
					if (spinFL >= 150 && !task3[4]) {

						task3[4] = true;
						sendMessage("You've completed the task: Spin 150 Flax!");
						TPoints += 3;
						sendMessage("You've received 3 Task Points! You now have a total of "
								+ TPoints + " points!");

						achievementInterface("Spin 150 Flax!");

					}
					startAnimation(896);
					getItems()
							.deleteItem(1779, getItems().getItemSlot(1779), 1);
					getItems().addItem(1777, 1);
					getPA().addSkillXP(150, playerCrafting);
					spinning = true;
				}

				@Override
				public void stop() {

				}
			}, 3);
		} else {
			sendMessage("You don't have any flax left to string.");
		}
	}

	/**/
	/*** Items kept on death ***/
	public void StartBestItemScan() {
		if (isSkulled && !prayerActive[10]) {
			ItemKeptInfo(0);
			return;
		}
		FindItemKeptInfo();
		ResetKeepItems();
		BestItem1();
	}

	public void startEvent7(final Client c) {
		if (barbLeader > 0 && inBarbDef) {
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (barbLeader > 0 && inBarbDef) {
						NPC n = NPCHandler.npcs[barbLeader];
						if (n != null) {
							n.facePlayer(playerId);
							if (Misc.random(50) == 0) {
								n.requestAnimation(6728, 0);
								n.forceChat(n.barbRandom(c, Misc.random(5)));
							} else if (barbLeader > 0 && inBarbDef
									&& lastBDWave && sentDatMsg == false) {
								n.requestAnimation(6728, 0);
								n.forceChat("I SHALL KILL YOU MYSELF!");
								n.forceChat("I SHALL KILL YOU MYSELF!");
								n.forceChat("I SHALL KILL YOU MYSELF!");
								sentDatMsg = true;
							} else if (barbLeader > 0 && inBarbDef
									&& lastBDWave && sentDatMsg == true) {
								if (Misc.random(100) < 3) {
									n.requestAnimation(6728, 0);
									n.forceChat("Ghosts, ATTACK HIM!!");
									n.forceChat("Ghosts, ATTACK HIM!!");
									n.forceChat("Ghosts, ATTACK HIM!!");
									sentDatMsg = true;
								}
							}
						} else {
							container.stop();
						}
					} else {

						container.stop();
						return;
					}
					if (disconnected) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 2);
		}
	}

	public void startGSEvent() { // GRAVESTONE HANDLER
		/*
		 * if (GSTimer > 0) { if(playerDeathX != 0 && playerDeathY != 0) {
		 * Server.npcHandler.spawnNpc(this, 6574, playerDeathX, playerDeathY,
		 * heightLevel, 0, 0, 0, 0, 0, false, true); } sendMessage(
		 * "You have 2 minutes to pick up your items before they disappear.");
		 * getPA().walkableInterface(25400); World.getWorld().submit(new
		 * Events(1000) { public void execute() { if (GSTimer > 0) { GSTimer -=
		 * 1; //( getPA().sendFrame126(""+GSTimer/60+"/"+GSTimer+"", 25402);
		 * getPA().sendFrame126(""+GSTimer+"", 25402); } if(GSTimer == 0 ||
		 * GSTimer < 0) { GSTimer = 0; getPA().walkableInterface(-1);
		 * getPA().closeAllWindows(); killGraveStone(); playerDeathX = 0;
		 * playerDeathY = 0; this.stop(); return; } if(disconnected) {
		 * this.stop(); } } }); }
		 */
	}

	public void stopEmote() {
		playerWalkIndex = 0x333;
		agilityEmote = false;
		getPA().requestUpdates(); // this was needed to make the agility work
	}

	public void stopGuideEvent() {
		CycleEventHandler.getSingleton().stopEvents(this, 13); // let's stop the
																// event .
	}

	public void storesummon(int npcType) {
		switch (npcType) {
		case 6807:
			if (hasFollower > 0) {
				for (int i = 0; i < NPCHandler.maxNPCs; i++) {
					if (NPCHandler.npcs[i] != null) {
						if (NPCHandler.npcs[i].summon == true) {
							if (NPCHandler.npcs[i].spawnedBy == getId()
									&& NPCHandler.npcs[i].npcId == npcslot) {
								sendMessage("You are now storing items inside your npc");
								Summoning().store();
							}
						}
					}
				}
			}
			break;
		}
	}

	public void summonEvent(final Client c) {
		if (hasFollower == 6870 || hasFollower == 6814 || hasFollower == 6823
				&& eventRunning2 == false) {
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (hasFollower != 6870 && hasFollower != 6814
							&& hasFollower != 6823) {
						eventRunning2 = false;
						container.stop();
						return;
					}
					if (eventRunning2 == false) {
						eventRunning2 = true;
					}
					if (healEventCounter > -1 && healEventCounter != 101) {
						if (hasFollower == 6870 || hasFollower == 6814
								|| hasFollower == 6823) {
							healEventCounter += 1;
						}
					} else {
						if (healEventCounter == 100 || healEventCounter > 100) {
							if (hasFollower == 6870 || hasFollower == 6814
									|| hasFollower == 6823) {
								healingEvent();
								healingEventW();
								healEventCounter = 0;
							}
						}
					}
					if (hasFollower != 6870 && hasFollower != 6814
							&& hasFollower != 6823) {
						eventRunning2 = false;
						container.stop();
						return;
					}
					if (disconnected) {
						container.stop();
					}
				}

				@Override
				public void stop() {

				}
			}, 2);
		}
	}

	public Summoning Summoning() {
		return Summoning;
	}

	public void summoningTimeEvent(final Client c) {

		if (c.summoningSpecTime == 0) {
		}

	}

	public void summonVanish(final Client c) {
		CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (hasFollower > 0 && summonTime > 0) {
					if (disconnected) {
						container.stop();
					}
					if (hasFollower > 0) {
						if (summonTime <= 60 && summonTime >= 55
								&& stopSpam3 == 0 && hasFollower > 0) {
							sendMessage("<shad=15733302>Your familiar will vanish in 60 Seconds!");
							stopSpam3 = 1;
						}
						if (summonTime <= 30 && summonTime >= 25
								&& stopSpam2 == 0 && hasFollower > 0) {
							sendMessage("<shad=15733302>Your familiar will vanish in 30 Seconds!");
							stopSpam2 = 1;
						}
						if (summonTime > 0) {
							summonTime--;
							stopSpam = 1;
						}
						if (summonTime == 0 && stopSpam == 1 && hasFollower > 0) {
							firstslot();
							Summoning.removeItems();
							hasFollower = -1;
							hasFollower = -1;
							totalstored = 0;
							summoningnpcid = 0;
							summoningslot = 0;
							getPA().sendFrame126("", 17017);
							sendMessage("<shad=15733302>Your familiar has vanished & dropped any stored items.");
							summonTime = 0;
							stopSpam = 0;
							stopSpam2 = 0;
							stopSpam3 = 0;
							container.stop();
							return;

						}
					} else {
						container.stop();
						return;

					}
				} else {

					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 2);

	}

	public int totalstored() {
		int a = 0;
		for (int i = 0; i < 28; i++) {
			if (storeditems[i] > 0) {
				a += 1;
			}

		}
		return a;
	}

	// HS
	public int totalXP() {
		return playerXP[0] + playerXP[1] + playerXP[2] + playerXP[3]
				+ playerXP[4] + playerXP[5] + playerXP[6] + playerXP[7]
				+ playerXP[8] + playerXP[9] + playerXP[10] + playerXP[11]
				+ playerXP[12] + playerXP[13] + playerXP[14] + playerXP[15]
				+ playerXP[16] + playerXP[17] + playerXP[18] + playerXP[19]
				+ playerXP[20] + playerXP[21] + playerXP[22] + playerXP[23]
				+ playerXP[24];
	}

	public void trade11() {
		CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (disconnected) {
					container.stop();
					return;
				}
				sendTrade11++;
				if (trade11 > 0 && sendTrade11 == 50) {
					sendMessage("You can now Trade, stake and PK!");
					container.stop();
				}
				if (trade11 > 0) {
					trade11--;
				} else if (trade11 < 0 || trade11 == 0) {
					sendMessage("You can now Trade, stake and PK!");
					container.stop();
					return;
				}
			}

			@Override
			public void stop() {

			}
		}, 2);

	}

	public void update() {
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();
	}

	public void updateWalkEntities() {

		if (inWild() && !hasBountyIcon) {
			PvPHandler.setBountyIcon(this, this.bountyIcon);
		}
		if (!inWild() && hasBountyIcon) {
			PvPHandler.setBountyIcon(this, 0);
		}

		if (inFoGGame) {
			getPA().showOption(3, 0, "Attack", 1);
			return;
		}
		if (inWild() && !isInPbox() && !isInArd() && !isInFala() && !inFunPk()) {
			int modY = absY > 6400 ? absY - 6400 : absY;
			// Potions.NoOPPotsInWildPlz(this);
			// getTimers().EPTimers(this);
			wildLevel = (((modY - 3520) / 8) + 1 + 6);
			getPA().walkableInterface(197);
			if (Config.SINGLE_AND_MULTI_ZONES) {
				if (inMulti()) {
					getPA().sendFrame126("@yel@Level: " + wildLevel, 199);
				} else {
					getPA().sendFrame126("@yel@Level: " + wildLevel, 199);
				}
			} else {
				getPA().multiWay(-1);
				getPA().sendFrame126("@yel@Level: " + wildLevel, 199);
			}
			PvPHandler.handleInterfaces(this);
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inFunPk()) {
			getPA().walkableInterface(197);
			getPA().sendFrame126("@yel@FunPk", 199);
			getPA().showOption(3, 0, "Attack", 1);
			wildLevel = 55;
		} else if (inDuelArena()) {
			getPA().walkableInterface(201);
			if (duelStatus == 5) {
				getPA().showOption(3, 0, "Attack", 1);
			} else {
				getPA().showOption(3, 0, "Challenge", 1);
			}
		} else if (inFunPk()) {
			getPA().walkableInterface(197);
			getPA().sendFrame126("@yel@FunPk", 199);
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inBarrows()) {
			getPA().sendFrame126("Kill Count: " + barrowsKillCount, 4536);
			getPA().walkableInterface(4535);
		} else if (inGWD()) {
			getPA().GWKC();
		} else if (safeZone()) {
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if (Config.SINGLE_AND_MULTI_ZONES) {
				if (inMulti()) {
					getPA().sendFrame126("@gre@SafeZone", 199);
				} else {
					getPA().sendFrame126("@gre@SafeZone", 199);
				}
			} else {
				getPA().multiWay(-1);
				getPA().sendFrame126("@gre@SafeZone", 199);
			}

		} else if (isInFala()) {
			wildLevel = 12;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if (Config.SINGLE_AND_MULTI_ZONES) {
				if (inMulti()) {
					HighAndLow();
				} else {
					HighAndLow();
				}
			}
		} else if (isInArd()) {
			wildLevel = 12;
			getPA().walkableInterface(197);
			getPA().showOption(3, 0, "Attack", 1);
			if (Config.SINGLE_AND_MULTI_ZONES) {
				if (inMulti()) {
					HighAndLow();
				} else {
					HighAndLow();
				}
			} else {
				getPA().multiWay(-1);
				HighAndLow();
			}
			getPA().showOption(3, 0, "Attack", 1);
		} else if (inPits) {
			getPA().showOption(3, 0, "Attack", 1);
		} else if (getPA().inPitsWait()) {
			getPA().showOption(3, 0, "Null", 1);
		} else if (!inWild() && !isInArd() && !isInFala() && !inFunPk()
				&& !safeZone() && !inDuelArena() && !arenas() && !inBarrows()
				&& !inPits()) {
			getPA().walkableInterface(-1);
			getPA().showOption(3, 0, "Null", 1);
			/*
			 * if(withinDungInviteRange()) { getPA().showOption(3, 0, "Invite",
			 * 1); } else { getPA().showOption(3, 0, "Null", 1); }
			 */
		}
	}

	public void useDice(int itemId, boolean clan) {
		if (System.currentTimeMillis() - diceDelay >= 3000) {
			sendMessage("Rolling...");
			FetchDice();
			startAnimation(11900);
			diceDelay = System.currentTimeMillis();
			cDice = itemId;
			clanDice = clan;
			switch (itemId) {
			// Gfx's
			case 15086:
				gfx0(2072);
				break;
			case 15088:
				gfx0(2074);
				break;
			case 15090:
				gfx0(2071);
				break;
			case 15092:
				gfx0(2070);
				break;
			case 15094:
				gfx0(2073);
				break;
			case 15096:
				gfx0(2068);
				break;
			case 15098:
				gfx0(2075);
				break;
			case 15100:
				gfx0(2069);
				break;
			}
		}
	}

	public boolean validClient(Client client) {
		return (client != null && !client.disconnected);
	}

	public boolean validClient(int id) {
		if (id < 0 || id > Config.MAX_PLAYERS) {
			return false;
		}
		return validClient(getClient(id));
	}

	public boolean validClient(String name) {
		return validClient(getClient(name));
	}

	public boolean validNpc(int index) {
		if (index < 0 || index >= Config.MAX_NPCS) {
			return false;
		}
		NPC n = getNpc(index);
		if (n != null) {
			return true;
		}
		return false;
	}

	public void walk(int EndX, int EndY, int Emote) {
		walkToEmote(Emote);
		getPA().walkTo2(EndX, EndY);
	}

	public void walk2(int EndX, int EndY, int Emote) {
		// walkToEmote(Emote);
		getPA().walkTo2(EndX, EndY);
	}

	public void WalkTo(int x, int y) {
		newWalkCmdSteps = (Math.abs((x + y)));
		if (newWalkCmdSteps % 1 != 0)
			newWalkCmdSteps /= 1;
		if (++newWalkCmdSteps > walkingQueueSize) {
			println("Warning: WalkTo command contains too many steps ("
					+ newWalkCmdSteps + ").");
			newWalkCmdSteps = 0;
		}
		int firstStepX = absX;
		firstStepX -= mapRegionX * 8;

		for (int i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
		}
		newWalkCmdX[0] = newWalkCmdY[0];
		int firstStepY = absY;
		firstStepY -= mapRegionY * 8;
		newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1));
		for (int q = 0; q < newWalkCmdSteps; q++) {
			newWalkCmdX[q] += firstStepX;
			newWalkCmdY[q] += firstStepY;
		}
	}

	public void walkToEmote(int id) {
		isRunning2 = false;
		playerWalkIndex = id;
		getPA().requestUpdates();
	}

	public boolean wearingArmor() {
		if (playerEquipment[playerHat] > 0)
			return true;
		else if (playerEquipment[playerChest] > 0)
			return true;
		else if (playerEquipment[playerLegs] > 0)
			return true;
		else if (playerEquipment[playerFeet] > 0)
			return true;
		else if (playerEquipment[playerWeapon] > 0)
			return true;
		else if (playerEquipment[playerCape] > 0)
			return true;
		else if (playerEquipment[playerArrows] > 0)
			return true;
		else if (playerEquipment[playerAmulet] > 0)
			return true;
		else if (playerEquipment[playerHands] > 0)
			return true;
		else if (playerEquipment[playerShield] > 0)
			return true;
		else if (playerEquipment[playerRing] > 0)
			return true;
		else
			return false;
	}

	public void WGEvent() { // if you leave room it will continue to remove
		// tokens == to fix
		if (inWarriorG() && heightLevel == 2) {
			CycleEventHandler.getSingleton().addEvent(this, new CycleEvent() {
				@Override
				public void execute(CycleEventContainer container) {
					if (disconnected) {
						container.stop();
						return;
					}
					if (inWG == false) {
						container.stop();
						return;
					}
					getItems().deleteItem(8851, getItems().getItemSlot(8851),
							10);
					gfx0(1358);
					sendMessage("Something happens and you notice that 10 of your tokens crumbled to dust!");

					if (!inWarriorG() && heightLevel != 2) {
						container.stop();
						return;
					}
					if (!getItems().playerHasItem(8851, 10)) {
						getPA().movePlayer(2846, 3540, 2);
						sendMessage("You do not have any more tokens!");
						container.stop();
						return;
					}
				}

				@Override
				public void stop() {

				}
			}, 30);
		}

	}

	public void WildDitch(int Emote, int Req, int newX, int newY,
			final int agilityTimer, int amtEXP, String message) {
		walk2(newX, newY, Emote);
		sendMessage(message);
		isRunning = true;
		isRunning2 = true;
	}

	public boolean withdrawConstructionItem(int itemID, int amount) {
		// By Gabbe- withdraws a construction item from bank.

		if (this.getItems().freeSlots() < amount && !Item.itemStackable[itemID])
			return false;
		if (Item.itemStackable[itemID] && getItems().freeSlots() == 0)
			return false;

		int slot = -1;

		// Search for the items slot in the bank
		for (int i1 = 0; i1 < bankItems.length; i1++) {
			if (bankItems[i1] - 1 == itemID && bankItemsN[i1] >= 1) {
				slot = i1;
			}
		}

		if (slot != -1) {
			getItems().fromBank(itemID, slot, amount);
			return true; // The bank slot was found :)
		}

		return false; // slot = -1, slot wasn't found :3
	}

	public void yell(String s) {
		for (int i = 0; i < Config.MAX_PLAYERS; i++) {
			if (validClient(i)) {
				getClient(i).sendMessage(s);
			}
		}
	}
}