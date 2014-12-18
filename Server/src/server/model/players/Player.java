package server.model.players;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import server.Config;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.objects.Objects;
import server.util.ISAACCipher;
import server.util.Misc;
import server.util.Stream;

public abstract class Player {
	/* Cannon Variables */
	public boolean settingUpCannon, hasCannon, cannonIsShooting, setUpBase,
			setUpStand, setUpBarrels, setUpFurnace;
	public int cannonBalls, cannonBaseX, cannonBaseY, cannonBaseH, rotation,
			cannonID;
	public Objects oldCannon;
	public int yellTimer = 0;
	// DUNG - BIND
	public int bind1 = -1, bind2 = -1, bind3 = -1, bind4 = -1, destroy = 0,
			droppedItem = -1;

	// PLAYERS LOOK UPDATING
	public int updateTimer = -1;
	// DUNG
	public boolean fullDung = false;
	public int bindedItem = -1;
	public int dungeonId = 0;
	public int dungDeaths = 0;
	public boolean inParty = false;
	public boolean receivedItems = false;
	public boolean door1 = false;
	public boolean bossing = false;
	public boolean door2 = false;
	public boolean door3 = false;
	public boolean door4 = false;
	public boolean completed = false;
	public boolean inDung = false;
	public boolean answerParty = false;
	public String Inviter = "";
	public int GatestoneX = 0;
	public int GatestoneY = 0;
	public int npcsKilled = 0;
	public String Partner = "None";
	public int banned = -1;

	private int playerGod = -1;

	// FOG
	public boolean inFoGWait = false;

	public boolean inFoGGame = false;

	public boolean isAttacker = false;
	public boolean isStoner = false;
	public int targetIndex;
	public String targetName = "None";
	public int charges = 0;
	public int FoGRating = 0;
	public String lastFighter = "";
	// NEW SLAYER
	public int slayerPoints = 0;
	public int slayerTaskStreak = 0;
	// effigies thx to Jason!!
	public int effigy, lastClicked;
	public long lastEffigy;

	public int getHeightLevel;
	// pk
	public int pkinPoints = 0;

	// vote
	public int votePoints = 0;

	public ArrayList<String> lastKilledPlayers = new ArrayList<String>();

	public String bonusSkill = "Mining";

	public int dominionPoints = 0;

	// mining
	public boolean isMining;

	public boolean mining;
	public int rockX, rockY;

	public int Commendations = 0;
	// drop dupe fix
	public boolean justDropped = false;

	// magebank
	public boolean mageBankMinigameDone = false;

	public boolean doingMageBankMinigame = false;

	public String searchTerm = "";

	/**/
	/*** Slayer ***/
	/**/
	// THE SLAYER TASKS
	public int[] lowTasks = { 1265, 5529, 1648, 1618, 1643, 1624, 1610, };

	public int[] medTasks = { 941, 55, 84, 82, 117, 50, 125, 125 };

	public int[] highTasks = { 1624, 1610, 1613, 1615, 55, 84, 49, 941, 2783,
			50, 1158, 53 };
	public int[] eliteTasks = { 9467, 9465, 9463, 6260, 8349, 50, 1158, 10530,
			5666 };
	public int given = 0;
	public int taskType = 0; // For rewarding XP!!
	public int random = 0;
	// RECOVERY
	public int hasRecov = 0;
	public String email = "";
	// summon
	public boolean summoned2 = false;
	// sof
	public boolean dialogueAntiFuckUp = false;
	/* Curse Prayers */
	public int attackMultiplier, rangedMultiplier, defenceMultiplier,
			magicMultiplier, strengthMultiplier;
	/**/
	/***
	 * Prayer /
	 **/
	public int bone = 0;
	public int boneOffered = 0;
	/**/
	/***
	 * SOF /
	 **/
	public static int amount2;
	public static int item2;
	public boolean claimLater = false;
	public boolean antiFuckUp = false;
	public boolean antiFuckUp2 = false;
	public boolean isSpinningDaSof = false;
	public int delay = 24;
	public boolean hasYell = false;
	/** LOYALTY **/
	public int LoyaltyPoints = 0;
	public int LoyaltyScore = 0;

	/** AURAS **/
	public boolean SharpAuraActive = false;
	public boolean AsuraAura = false;
	public boolean finnishedRecharge = false;
	public boolean finnishedRechargeAsuraAura = false;
	// Firemaking
	public int tick = 0;
	public String lastKilled;
	public boolean antispampush = false;

	public boolean hasGivenSlayReward = false;
	public int OverloadCounter;
	public boolean hasGivenKS = false;

	public int dfsCounter;
	public int[] clawHit = new int[4];
	public int playerTradeWealth;
	public int curseDefence = 0;

	public int curseAttack = 0;
	public int MoneyCash = 0;
	public int curseStrength = 0;
	public int killstreak = 0;
	public int totalkills = 0;
	public int totaldeath = 0;
	public int curseMagic = 0;
	public boolean hasBeenRewarded = false;
	public boolean isAttacking = false;
	public int clickAction = 0;
	public int turkeydelay = -1;
	public int curseRange = 0;
	public static int claimed_item = 0;
	public static int spins = 0;
	public int DonorPoint = 0;
	public boolean inCombat = false;
	public int hasHeardEasterDialogue = 0;
	public boolean hasSentDiceClan = false;
	public boolean isTalkingWithDungMaster = false;
	public boolean hasNotDeleted = false;
	public boolean isWalkingAgilityLog = false;
	public boolean isWalkingAgilityRope = false;
	public boolean antiCheatClient = false;
	public boolean hasGivenXP = false;
	public boolean isGoingThroughPipe = false;
	public boolean isDoingBarbLog = false;
	public boolean isGoingThroughPipe2 = false;
	public boolean antiSpamNet1 = false;
	public boolean isGoingOverPlank = false;
	public boolean antiSpamRope1 = false;
	public boolean isDoingVetCapeEmote = false;
	public int DUfood1 = 0;
	public int DUfood2 = 0;
	public int DUfood3 = 0;
	public int DUfood4 = 0;
	public int HouseTeleX = 3354;
	public boolean hasHousee = false;
	public int HouseHLevel = 0;
	public int HouseTeleY = 3343;
	public int IsIDung = 0;
	public boolean hasConstructedAnAltarInHouse = false;
	public boolean hasSearchedCrateInDung = false;
	public boolean[] playerSkilling = new boolean[20];
	public int[] cookingProp = new int[7];
	public int[] cookingCoords = new int[2];
	public int[] miningProp = new int[6];
	public int[] fletchingProp = new int[10];
	public boolean tanning;
	public boolean stopPlayerSkill;
	public int[][] playerSkillProp = new int[20][15];
	public long lastFire;
	public int woodcuttingTree, doAmount;
	public boolean playerFletch, playerIsFletching, fletchLevelReq,
			playerIsMining, playerIsFiremaking, playerIsFishing,
			playerIsCooking, playerIsWoodcutting;
	// aura
	public int AuraEquiped = -1;
	public int AuraEffect = 0;
	/** SOF **/
	public int spinsLe = 0;
	/** achievments **/
	public int foodEat = 0;
	public int nomadSk = 0;
	public int GoblinS = 0;
	public int Jadskls = 0;
	public int PlayKil = 0;
	public int WinsSFF = 0;
	public int WinsPCC = 0;

	// SUMMONING
	public int summonTime = 0;
	public int stopSpam = 0;

	public int stopSpam2 = 0;

	public int stopSpam3 = 0;
	public int hasHouse = 0;
	public int WillKeepAmt1, WillKeepAmt2, WillKeepAmt3, WillKeepAmt4,
			WillKeepItem1, WillKeepItem2, WillKeepItem3, WillKeepItem4,
			WillKeepItem1Slot, WillKeepItem2Slot, WillKeepItem3Slot,
			WillKeepItem4Slot, EquipStatus;
	public boolean isWoodcutting;
	public boolean inBarbDef = false;
	public boolean lastBDWave = false;
	public boolean spinning = false;

	public boolean isWalking = false;

	public boolean spawned1 = true;
	public boolean sentDatMsg = false;
	public int barbsToKill = 0;
	public int barbsKilled = 0;
	public int barbPoints = 0;

	public int brawlerscook = 0;

	public int brawlerFM = 0;

	public int barbWave = 0;
	public int barbDamage = 0;
	public int barbLeader = 0;
	public boolean needstorelog = false;
	public boolean hassentrelogmessage = false;
	public boolean juststarted = false;
	// start of bankpin
	public String bankPin = "";
	public int attempts = 3;
	public boolean setPin = false;
	public boolean summonedNpc = false;
	public boolean itsover = false;
	public boolean deletedone = false;
	public boolean hasSentMessage = false;
	public int playerBankPin;
	public String pinStatus = "No PIN Set";
	public int recoveryDelay = 3, attemptsRemaining = 3, lastPinSettings = -1,
			setPinDate = -1, changePinDate = -1, deletePinDate = -1,
			firstPin = 0, secondPin = 0, thirdPin = 0, fourthPin = 0,
			bankPin1 = 0, bankPin2 = 0, bankPin3 = 0, bankPin4 = 0;
	public boolean banking, enterdBankpin, firstPinEnter, secondPinEnter,
			thirdPinEnter, hasBankPin, fourthPinEnter;
	// end of bankpin
	// public long ignores[] = new long[200];
	public int barrageorb = 0;
	public long lastEmote;
	public boolean isChoosingDung = false;
	public boolean hasChoosenDung = false;
	public boolean yak = false;
	public boolean degWep = false;
	public int WG = 0;
	public boolean selectStarterr = false;
	public boolean chosenChaotics = false;
	public boolean chosenStarter = false;

	public boolean inWG = false;
	public boolean quickPray, quickCurse;
	public boolean quickCurseActive;
	public boolean isChoosing = false;
	public long lastHelp;
	public int SOL_PROTECTION;
	public int playerTitle;
	public int santaPrize = 0;
	public boolean jadSpawn = false;
	// public boolean DCDamg = false, playerIsFiremaking;
	public int DCdown = 0, trade11;
	public int constructionID = 0; // construction!
	// public Hit hitDiff2;
	// public Hit hitDiff;
	/* Party Room */
	public boolean inPartyRoom = false;
	public int[] party = new int[8];
	public int[] partyN = new int[8];
	/* Party Room End */
	// public int SpeDelay = 0;
	public int SpeDelay = 0;
	public int barrageOrb = 0, dungRest;
	public boolean agilityEmote = false;
	public int gnomeObsticle = 0;
	public int FishID;
	public int rabbitNeedSpawn;
	public int rabbitTimer;
	public int catchButterFly;
	public int catchImpling;
	public int Arma, Zammy, Sara;
	public int Dfscharges;
	public int dfsCount;

	public int dungPoints;

	public int funPoints;
	public int dungn;

	public int skillXpToAdd = 0;
	public int vote = 0; // vote interface
	public int cookingTimer = -1;
	public int cooksLeft = 0;
	public int CSkill = 23;
	// public long lastFire;
	public boolean hasOverloadBoost;
	public int COaltar;
	public int SPoints;
	public int COtelee;
	public int COcryst;
	public int CObeddy;
	public int COtreee;
	public int COchest;
	public int COChair;
	public int COLectt;
	public int isBalance;
	public int Band;
	public int overloadCount = 0;
	public boolean SSPLIT = false;
	public boolean openDuel = false;
	public boolean spawned = false;
	public int soulSplitDelay;
	public int leechSpecialDelay;
	public int leechEnergyDelay;
	public int leechStrengthDelay;
	public int leechDefenceDelay;
	public int leechMagicDelay;
	public int leechAttackDelay;
	public int leechRangedDelay;
	public int said = 0;
	public int cookedFishID;
	public int CookingEmote;
	public int npcId2 = 0;
	public int gwdelay;
	public int GSTimer = 0;
	public int conaltar;
	public int vlsLeft;
	public int vlsLeft2;
	public int summonSpec;
	public double getstr, getatt, getdef, getranged, getmagic;

	public boolean isNpc;
	public String CookFishName;
	public int burnFishID;
	public boolean clanDice = false;
	public long diceDelay;
	public int diceID = 15084;
	public int cDice = 0;
	public boolean xpLock;
	public boolean kamfreenaDone;
	public boolean inCyclops = false;
	public boolean hasNpc = false;
	public int sellingId;
	public int sellingN;
	public int sellingS;
	public int playerCollect;
	public int playerShop[] = new int[10];
	public int playerShopN[] = new int[10];
	public int playerShopP[] = new int[10];
	public Client myShopClient;
	public boolean hasNpc2 = false;
	public int primalBoost = 40;
	public int summonId;
	public boolean Agrith;
	public int earningPotential;
	public int epDelay;
	public boolean Flambeed;
	public int UsedTimer;
	public int UsedTimer2;
	public boolean Karamel;
	public boolean Nomad;
	public boolean Goblin;
	public boolean Dessourt;
	public boolean Culin;
	public int succeslvl;
	public int xamount;
	public int monkeyk0ed;
	public int recoilHits = 0;
	// public int vlsLeft = 1000;
	public int statLeft = 1000;
	public int vTopLeft = 1000;
	public int vLegsLeft = 1000;
	public int sTopLeft = 1000;
	public int sLegsLeft = 1000;
	public int sHelmLeft = 1000;
	public int zHoodLeft = 1000;
	public int zStaffLeft = 1000;
	public int zTopLeft = 1000;
	public int zBottomLeft = 1000;
	public int mBodyLeft = 1000;
	public int mChapsLeft = 1000;
	public int vSpearLeft = 1000;
	public int grimPrize = 0;
	public int grimChat = 0;
	public transient int ArmadylPitch = 0;
	public int playerMac;
	public boolean isCooking = false;
	public int completedSetsMar, usedOnObjectID, usedOnobjectX, usedOnobjectY,
			CWPlayerIndex, TrownSpellTimer;
	public int playerHunter = 21;
	/**
	 * A list of players in this region.
	 */
	public List<Player> players = new LinkedList<Player>();
	public boolean isDoingSkillcapeAnim = false;
	public boolean varrockTeleSelected = false;
	public boolean edgevileTeleSelected = false;
	public boolean faladorTeleSelected = false;
	public boolean catherbyTeleSelected = false;
	public boolean camelotTeleSelected = false;
	public boolean barbarianTeleSelected = false;
	public long lastCast = 0;
	public boolean yanilleTeleSelected = false;
	public ArrayList<String> killedPlayers = new ArrayList<String>();
	public ArrayList<Integer> attackedPlayers = new ArrayList<Integer>();

	public boolean initialized = false, disconnected = false,
			ruleAgreeButton = false, RebuildNPCList = false, isActive = false,
			isKicked = false, sit = false, Jail = false, isSkulled = false,
			friendUpdate = false, newPlayer = false, hasMultiSign = false,
			saveCharacter = false, mouseButton = false, splitChat = false,
			chatEffects = true, acceptAid = false, nextDialogue = false,
			autocasting = false, viewingOrb = false, usedSpecial = false,
			mageFollow = false, dbowSpec = false, craftingLeather = false,
			properLogout = false, secDbow = false, maxNextHit = false,
			ssSpec = false, vengOn = false, addStarter = false,
			accountFlagged = false, readRules = false, msbSpec = false,
			newCmb = false, isBanking = false;
	public int degradeTime, KC, DC, recoil = 0, kingQuest = 0, rMQ = 0,
			bMQ = 0, ag1, puremaster, ag2, ag3, ag4, ag5, ag6, headIconHints,
			cwKills, cwDeaths, cwGames, dbowDelay = 0, pure, saveDelay,
			altarPrayed = 0, playerKilled, pkPoints, bossPoints, wcLevelReq,
			totalPlayerDamageDealt, killedBy, lastChatId = 1, vestaDelay = 0,
			privateChat, friendSlot = 0, SolProtect = 0, dialogueId,
			gloryValue, fishingLevelReq, processPackets = 0, randomCoffin,
			newLocation, CSLS = 0, lastNpcAttacked = 0, specEffect, specBarId,
			processTimer, attackLevelReq, dungLevelReq, slayerLevelReq,
			fletchingLevelReq, Donatorreq, defenceLevelReq, dclaw1, dclaw2,
			dclaw3, dclaw4, strengthLevelReq, rangeLevelReq, magicLevelReq,
			followId, skullTimer, votingPoints, nextChat = 0, talkingNpc = -1,
			dialogueAction = 0, autocastId, followDistance, followId2,
			barrageCount = 0, delayedDamage = 0, delayedDamage2 = 0, isDonator,
			WildTimer = 0, magePoints = 0, desertTreasure = 0,
			lastArrowUsed = -1, autoRet = 0, pcDamage = 0, xInterfaceId = 0,
			xRemoveId = 0, xRemoveSlot = 0, tzhaarToKill = 0, RFDToKill = 0,
			tzhaarKilled = 0, RFDKilled = 0, waveId, frozenBy = 0,
			poisonDamage = 0, teleAction = 0, prayerAction = 0,
			bonusAttack = 0, newCombat = 0;
	public String currentTime, date;
	public boolean musicOn = true;
	public boolean soundsOn = false;
	public boolean DONOTADDTHIS = false;
	public String properName;
	public int[] voidStatus = new int[5];

	public int[] itemKeptId = new int[4];
	public int[] pouches = new int[4];
	public final int[] POUCH_SIZE = { 3, 6, 9, 12 };

	public boolean[] invSlot = new boolean[28], equipSlot = new boolean[14];

	public long friends[] = new long[200];
	public long lastButton;
	public int clawDelay = 0;
	public int previousDamage = 0;
	public boolean usingClaws = false;
	public double specAmount = 0;
	public double specAccuracy = 1;
	public double specDamage = 1;
	public double prayerPoint = 1.0;
	public int teleGrabItem, teleGrabX, teleGrabY, duelCount, underAttackBy,
			underAttackBy2, wildLevel, teleTimer, respawnTimer, saveTimer = 0,
			teleBlockLength, poisonDelay;
	public long lastPlayerMove, lastPoison, lastoverload, lastPoisonSip,
			poisonImmune, lastSpear, lastProtItem, dfsDelay, lastVeng,
			lastYell, teleGrabDelay, protMageDelay, protMeleeDelay,
			protRangeDelay, lastAction, lastThieve, lastLockPick, alchDelay,
			specDelay = System.currentTimeMillis(), duelDelay, teleBlockDelay,
			godSpellDelay, singleCombatDelay, singleCombatDelay2, reduceStat,
			restoreStatsDelay, logoutDelay, buryDelay, foodDelay, potDelay;
	public boolean canChangeAppearance = false;
	public boolean IsDueling = false;
	public boolean inPits2 = false;
	public boolean mageAllowed;
	public long SpecialDelay = 0;
	public int follow2 = 0;
	/**
	 * Jef's PvP stuff
	 */
	public int EP, EP_MINUTES, targetPercentage, dropWealth;
	public boolean EP_ACTIVE;
	public int safeTimer = 500;
	public boolean leftWild = false;
	public int logoutTimer;
	public byte poisonMask = 0;
	// Setting up the woodcutting variables.
	public boolean chopping = false;
	public int choptimer = 0;
	public int logID = 0;
	public int donatorChest = 0;
	public int chopreq = 0;
	public int chopXP = 0;
	public int treeX = -1;
	public int treeY = -1;
	public int treechance = 0;
	public int treetimer = 0;

	public int treetype = 0;
	public int wcemote = 0;
	/* Start of combat variables */
	public boolean below459 = true, defaultWealthTransfer, updateInventory,
			oldSpec, playerStun, stopPlayerPacket;
	public boolean playerBFishing, finishedBarbarianTraining, ignoreDefence,
			secondFormAutocast, usingArrows, magicDef, spellSwap, recoverysSet;
	public int rangeEndGFX, boltDamage, teleotherType, stageT;
	public boolean protectItem = false;
	public boolean multiAttacking, rangeEndGFXHeight;
	public double crossbowDamage;
	/* End of combat variables */
	public static final int[] BOWS = { 18357, 15241, 9185, 839, 845, 847, 851,
			855, 859, 841, 843, 849, 853, 857, 861, 19146, 19143, 19149, 14990,
			4212, 4214, 4215, 11235, 4216, 4217, 4218, 4219, 4220, 4221, 4222,
			4223, 6724, 4734, 4934, 4935, 4936, 4937 };
	public int BlackMarks = 0;
	public static final int[] ARROWS = { 882, 19157, 19162, 19152, 884, 886,
			888, 890, 892, 4740, 11212, 9140, 9141, 4142, 9143, 9144, 9240,
			9241, 9242, 9243, 9244, 9245 };
	public static final int[] NO_ARROW_DROP = { 15243, 4212, 4214, 4215, 4216,
			4217, 4218, 4219, 4220, 4221, 4222, 4223, 4734, 4934, 4935, 4936,
			4937 };
	public static final int[] Bolts = { 877, 4740, 9139, 9140, 9141, 9142,
			9143, 9144, 9145, 9236, 9237, 9238, 9239, 9240, 9241, 9242, 9243,
			9244, 9245 }; // Arrows, use with bows
	public static final int[] OTHER_RANGE_WEAPONS = { 863, 864, 865, 866, 867,
			868, 869, 806, 807, 808, 809, 810, 811, 825, 826, 827, 828, 829,
			830, 800, 801, 802, 803, 804, 805, 6522, 13879, 15016 };
	public final int[][] MAGIC_SPELLS = {
			// example {magicId, level req, animation, startGFX, projectile Id,
			// endGFX, maxhit, exp gained, rune 1, rune 1 amount, rune 2, rune 2
			// amount, rune 3, rune 3 amount, rune 4, rune 4 amount}

			// Modern Spells
			{ 1152, 1, 711, 90, 91, 92, 2, 5, 556, 1, 558, 1, 0, 0, 0, 0 }, // wind
																			// strike
			{ 1154, 5, 711, 93, 94, 95, 4, 7, 555, 1, 556, 1, 558, 1, 0, 0 }, // water
																				// strike
			{ 1156, 9, 711, 96, 97, 98, 6, 9, 557, 2, 556, 1, 558, 1, 0, 0 },// earth
																				// strike
			{ 1158, 13, 711, 99, 100, 101, 8, 11, 554, 3, 556, 2, 558, 1, 0, 0 }, // fire
																					// strike
			{ 1160, 17, 711, 117, 118, 119, 9, 13, 556, 2, 562, 1, 0, 0, 0, 0 }, // wind
																					// bolt
			{ 1163, 23, 711, 120, 121, 122, 10, 16, 556, 2, 555, 2, 562, 1, 0,
					0 }, // water bolt
			{ 1166, 29, 711, 123, 124, 125, 11, 20, 556, 2, 557, 3, 562, 1, 0,
					0 }, // earth bolt
			{ 1169, 35, 711, 126, 127, 128, 12, 22, 556, 3, 554, 4, 562, 1, 0,
					0 }, // fire bolt
			{ 1172, 41, 711, 132, 133, 134, 13, 25, 556, 3, 560, 1, 0, 0, 0, 0 }, // wind
																					// blast
			{ 1175, 47, 711, 135, 136, 137, 14, 28, 556, 3, 555, 3, 560, 1, 0,
					0 }, // water blast
			{ 1177, 53, 711, 138, 139, 140, 15, 31, 556, 3, 557, 4, 560, 1, 0,
					0 }, // earth blast
			{ 1181, 59, 711, 129, 130, 131, 16, 35, 556, 4, 554, 5, 560, 1, 0,
					0 }, // fire blast
			{ 1183, 62, 727, 158, 159, 160, 17, 36, 556, 5, 565, 1, 0, 0, 0, 0 }, // wind
																					// wave
			{ 1185, 65, 727, 161, 162, 163, 18, 37, 556, 5, 555, 7, 565, 1, 0,
					0 }, // water wave
			{ 1188, 70, 727, 164, 165, 166, 19, 40, 556, 5, 557, 7, 565, 1, 0,
					0 }, // earth wave
			{ 1189, 75, 727, 155, 156, 157, 20, 42, 556, 5, 554, 7, 565, 1, 0,
					0 }, // fire wave
			{ 1153, 3, 716, 102, 103, 104, 0, 13, 555, 3, 557, 2, 559, 1, 0, 0 }, // confuse
			{ 1157, 11, 716, 105, 106, 107, 0, 20, 555, 3, 557, 2, 559, 1, 0, 0 }, // weaken
			{ 1161, 19, 716, 108, 109, 110, 0, 29, 555, 2, 557, 3, 559, 1, 0, 0 }, // curse
			{ 1542, 66, 729, 167, 168, 169, 0, 76, 557, 5, 555, 5, 566, 1, 0, 0 }, // vulnerability
			{ 1543, 73, 729, 170, 171, 172, 0, 83, 557, 8, 555, 8, 566, 1, 0, 0 }, // enfeeble
			{ 1562, 80, 729, 173, 174, 107, 0, 90, 557, 12, 555, 12, 556, 1, 0,
					0 }, // stun
			{ 1572, 20, 710, 177, 178, 181, 0, 30, 557, 3, 555, 3, 561, 2, 0, 0 }, // bind
			{ 1582, 50, 710, 177, 178, 180, 2, 60, 557, 4, 555, 4, 561, 3, 0, 0 }, // snare
			{ 1592, 79, 710, 177, 178, 179, 4, 90, 557, 5, 555, 5, 561, 4, 0, 0 }, // entangle
			{ 1171, 39, 724, 145, 146, 147, 15, 25, 556, 2, 557, 2, 562, 1, 0,
					0 }, // crumble undead
			{ 1539, 50, 708, 87, 88, 89, 25, 42, 554, 5, 560, 1, 0, 0, 0, 0 }, // iban
																				// blast
			{ 12037, 50, 1576, 327, 328, 329, 19, 30, 560, 1, 558, 4, 0, 0, 0,
					0 }, // magic dart
			{ 1190, 60, 811, 0, 0, 76, 20, 60, 554, 2, 565, 2, 556, 4, 0, 0 }, // sara
																				// strike
			{ 1191, 60, 811, 0, 0, 77, 20, 60, 554, 1, 565, 2, 556, 4, 0, 0 }, // cause
																				// of
																				// guthix
			{ 1192, 60, 811, 0, 0, 78, 20, 60, 554, 4, 565, 2, 556, 1, 0, 0 }, // flames
																				// of
																				// zammy
			{ 12445, 85, 1819, 0, 344, 345, 0, 65, 563, 1, 562, 1, 560, 1, 0, 0 }, // teleblock

			// Ancient Spells
			{ 12939, 50, 1978, 0, 384, 385, 13, 30, 560, 2, 562, 2, 554, 1,
					556, 1 }, // smoke rush
			{ 12987, 52, 1978, 0, 378, 379, 14, 31, 560, 2, 562, 2, 566, 1,
					556, 1 }, // shadow rush
			{ 12901, 56, 1978, 0, 0, 373, 15, 33, 560, 2, 562, 2, 565, 1, 0, 0 }, // blood
																					// rush
			{ 12861, 58, 1978, 0, 360, 361, 16, 34, 560, 2, 562, 2, 555, 2, 0,
					0 }, // ice rush
			{ 12963, 62, 1979, 0, 0, 389, 19, 36, 560, 2, 562, 4, 556, 2, 554,
					2 }, // smoke burst
			{ 13011, 64, 1979, 0, 0, 382, 20, 37, 560, 2, 562, 4, 556, 2, 566,
					2 }, // shadow burst
			{ 12919, 68, 1979, 0, 0, 376, 21, 39, 560, 2, 562, 4, 565, 2, 0, 0 }, // blood
																					// burst
			{ 12881, 70, 1979, 0, 0, 363, 22, 40, 560, 2, 562, 4, 555, 4, 0, 0 }, // ice
																					// burst
			{ 12951, 74, 1978, 0, 386, 387, 23, 42, 560, 2, 554, 2, 565, 2,
					556, 2 }, // smoke blitz
			{ 12999, 76, 1978, 0, 380, 381, 24, 43, 560, 2, 565, 2, 556, 2,
					566, 2 }, // shadow blitz
			{ 12911, 80, 1978, 0, 374, 375, 25, 45, 560, 2, 565, 4, 0, 0, 0, 0 }, // blood
																					// blitz
			{ 12871, 82, 1978, 366, 0, 367, 26, 46, 560, 2, 565, 2, 555, 3, 0,
					0 }, // ice blitz
			{ 12975, 86, 1979, 0, 0, 391, 27, 48, 560, 4, 565, 2, 556, 4, 554,
					4 }, // smoke barrage
			{ 13023, 88, 1979, 0, 0, 383, 28, 49, 560, 4, 565, 2, 556, 4, 566,
					3 }, // shadow barrage
			{ 12929, 92, 1979, 0, 0, 377, 29, 51, 560, 4, 565, 4, 566, 1, 0, 0 }, // blood
																					// barrage
			{ 12891, 94, 1979, 0, 0, 369, 30, 52, 560, 4, 565, 2, 555, 6, 0, 0 }, // ice
																					// barrage
			{ 13095, 97, 10518, 1853, 0, 1854, 31, 53, 563, 14, 555, 15, 0, 0,
					0, 0 }, // miasmic barrage
			{ -1, 80, 811, 301, 0, 0, 0, 0, 554, 3, 565, 3, 556, 3, 0, 0 }, // charge
			{ -1, 21, 712, 112, 0, 0, 0, 10, 554, 3, 561, 1, 0, 0, 0, 0 }, // low
																			// alch
			{ -1, 55, 713, 113, 0, 0, 0, 20, 554, 5, 561, 1, 0, 0, 0, 0 }, // high
																			// alch
			{ 13095, 97, 10518, 1853, 0, 1854, 31, 53, 563, 14, 555, 15, 0, 0,
					0, 0 }, // miasmic barrage
	// {-1,33,728,142,143,144,0,35,556,1,563,1,0,0,0,0} // telegrab

	};
	public boolean stakeFix = false;
	public static final int[] autocastIds = { 51133, 32, 51185, 33, 51091, 34,
			24018, 35, 51159, 36, 51211, 37, 51111, 38, 51069, 39, 51146, 40,
			51198, 41, 51102, 42, 51058, 43, 51172, 44, 51224, 45, 51122, 46,
			51080, 47, 7038, 0, 7039, 1, 7040, 2, 7041, 3, 7042, 4, 7043, 5,
			7044, 6, 7045, 7, 7046, 8, 7047, 9, 7048, 10, 7049, 11, 7050, 12,
			7051, 13, 7052, 14, 7053, 15, 47019, 27, 47020, 25, 47021, 12,
			47022, 13, 47023, 14, 47024, 15 };
	public int[][] barrowsNpcs = { { 2030, 0 }, // verac
			{ 2029, 0 }, // toarg
			{ 2028, 0 }, // karil
			{ 2027, 0 }, // guthan
			{ 2026, 0 }, // dharok
			{ 2025, 0 } // ahrim
	};

	public static final int[] QUEST_INTERFACE_IDS = { 8145, 8147, 8148, 8149,
			8150, 8151, 8152, 8153, 8154, 8155, 8156, 8157, 8158, 8159, 8160,
			8161, 8162, 8163, 8164, 8165, 8166, 8167, 8168, 8169, 8170, 8171,
			8172, 8173, 8174, 8175, 8176, 8177, 8178, 8179, 8180, 8181, 8182,
			8183, 8184, 8185, 8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193,
			8194, 8195, 12174, 12175, 12176, 12177, 12178, 12179, 12180, 12181,
			12182, 12183, 12184, 12185, 12186, 12187, 12188, 12189, 12190,
			12191, 12192, 12193, 12194, 12195, 12196, 12197, 12198, 12199,
			12200, 12201, 12202, 12203, 12204, 12205, 12206, 12207, 12208,
			12209, 12210, 12211, 12212, 12213, 12214, 12215, 12216, 12217,
			12218, 12219, 12220, 12221, 12222, 12223 };

	public int barrowsKillCount;

	public int reduceSpellId;

	public final int[] REDUCE_SPELL_TIME = { 250000, 250000, 250000, 500000,
			500000, 500000 }; // how long does the other player stay immune to
	// the spell
	public long[] reduceSpellDelay = new long[6];
	public static final int[] REDUCE_SPELLS = { 1153, 1157, 1161, 1542, 1543,
			1562 };
	public boolean[] canUseReducingSpell = { true, true, true, true, true, true };
	public int slayerTask, taskAmount;
	public int prayerId = -1;

	public int headIcon = -1;
	public boolean hasBountyIcon = false;

	public long stopPrayerDelay, prayerDelay;

	public boolean openBank = false;

	public boolean usingPrayer;

	public final int[] PRAYER_DRAIN_RATE = { 500, 500, 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500 };

	public static final int[] PRAYER_LEVEL_REQUIRED = { 1, 4, 7, 8, 9, 10, 13,
			16, 19, 22, 25, 26, 27, 28, 31, 34, 37, 40, 43, 44, 45, 46, 49, 52,
			60, 70 };

	public static final int[] PRAYER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
			12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };

	public static final String[] PRAYER_NAME = { "Thick Skin",
			"Burst of Strength", "Clarity of Thought", "Sharp Eye",
			"Mystic Will", "Rock Skin", "Superhuman Strength",
			"Improved Reflexes", "Rapid Restore", "Rapid Heal", "Protect Item",
			"Hawk Eye", "Mystic Lore", "Steel Skin", "Ultimate Strength",
			"Incredible Reflexes", "Protect from Magic",
			"Protect from Missiles", "Protect from Melee", "Eagle Eye",
			"Mystic Might", "Retribution", "Redemption", "Smite", "Chivalry",
			"Piety" };

	public static final int[] PRAYER_GLOW = { 83, 84, 85, 601, 602, 86, 87, 88,
			89, 90, 91, 603, 604, 92, 93, 94, 95, 96, 97, 605, 606, 98, 99,
			100, 607, 608 };

	public static final int[] PRAYER_HEAD_ICONS = { -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 1, 0, -1, -1, 3, 5, 4, -1,
			-1 };
	// {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3,2,1,4,6,5};

	public boolean[] prayerActive = { false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false };

	/* Curse Prayers */
	public static final int[] CURSE_DRAIN_RATE = { 500, 500, 500, 500, 500,
			500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500, 500,
			500, 500 };

	public static final int[] CURSE_LEVEL_REQUIRED = { 50, 50, 52, 54, 56, 59,
			62, 65, 68, 71, 74, 76, 78, 80, 82, 84, 86, 89, 92, 95 };

	// end of clipped npcs

	public static final int[] CURSE = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
			12, 13, 14, 15, 16, 17, 18, 19 };

	public static final String[] CURSE_NAME = { "Protect Item", "Sap Warrior",
			"Sap Ranger", "Sap Mage", "Sap Spirit", "Berserker",
			"Deflect Summoning", "Deflect Magic", "Deflect Missiles",
			"Deflect Melee", "Leech Attack", "Leech Ranged", "Leech Magic",
			"Leech Defence", "Leech Strength", "Leech Energy",
			"Leech Special Attack", "Wrath", "Soul Split", "Turmoil" };

	public static final int[] CURSE_GLOW = { 610, 611, 612, 613, 614, 615, 616,
			617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629 };

	public static final int[] CURSE_HEAD_ICONS = { -1, -1, -1, -1, -1, -1, 12,
			10, 11, 9, -1, -1, -1, -1, -1, -1, -1, 16, 17, -1 };

	public boolean[] curseActive = { false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false };

	/* End of curse prayers */
	public int duelTimer, duelTeleX, duelTeleY, duelSlot, duelSpaceReq,
			duelOption, duelingWith, duelStatus;

	public int headIconPk = -1, headIconhint;

	public int bountyIcon = -1;

	public int headIconPray = 0;

	public boolean duelRequested;

	public boolean[] duelRule = new boolean[22];

	public final int[] DUEL_RULE_ID = { 1, 2, 16, 32, 64, 128, 256, 512, 1024,
			4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 2097152,
			8388608, 16777216, 67108864, 134217728 };

	public boolean doubleHit, usingSpecial, npcDroppingItems, usingRangeWeapon,
			usingBow, usingMagic, castingMagic;

	public int specMaxHitIncrease, freezeDelay, freezeTimer = -6, killerId,
			playerIndex, oldPlayerIndex, lastWeaponUsed, projectileStage,
			crystalBowArrowCount, playerMagicBook, teleGfx, teleEndGfx,
			teleEndAnimation, teleHeight, teleX, teleY, rangeItemUsed,
			killingNpcIndex, totalDamageDealt, oldNpcIndex, fightMode,
			attackTimer, npcIndex, npcClickIndex, npcType, npcID,
			castingSpellId, oldSpellId, spellId, hitDelay;

	public boolean magicFailed, oldMagicFailed;

	public int bowSpecShot, clickNpcType, clickObjectType, objectId, objectX,
			objectY, objectXOffset, objectYOffset, objectDistance;

	public int pItemX, pItemY, pItemId;

	public boolean isMoving, walkingToItem;

	public boolean isShopping, updateShop;

	public int myShopId;

	public int tradeStatus, tradeWith;

	public boolean forcedChatUpdateRequired, inDuel, tradeAccepted, goodTrade,
			inTrade, tradeRequested, tradeResetNeeded, tradeConfirmed,
			tradeConfirmed2, canOffer, acceptTrade, acceptedTrade;

	public int attackAnim, animationRequest = -1, animationWaitCycles;
	public int[] playerBonus = new int[12];

	public boolean isRunning2 = true;
	public boolean takeAsNote;
	public int combatLevel;
	public boolean saveFile = false;
	public int playerAppearance[] = new int[13];

	public int apset;

	public int actionID;
	public int wearItemTimer, wearId, wearSlot, interfaceId;
	public int XremoveSlot, XinterfaceID, XremoveID, Xamount;
	public int tutorial = 15;
	public boolean usingGlory = false;
	public int[] woodcut = new int[3];
	public int wcTimer = 0;
	public int miningTimer = 0;
	// Setting up the fishing variables.
	public boolean fishing = false;
	public int fishtimer = 0;
	public int fishXP;
	public int fishies = 0;

	public int fishreqt = 0;
	public int fishitem = 0;
	public int fishemote = 0;
	public int fishies2 = 0;
	public int fishreq2 = 0;
	public boolean isfishing = false;
	public boolean attemptingfish = false;
	public int smeltType; // 1 = bronze, 2 = iron, 3 = steel, 4 = gold, 5 =
	// mith, 6 = addy, 7 = rune
	public int smeltAmount;
	public int smeltTimer = 0;
	public boolean smeltInterface;
	public boolean patchCleared;
	public int[] farm = new int[2];
	public boolean antiFirePot = false;
	/**
	 * Castle Wars
	 */
	public int castleWarsTeam;

	public boolean inCwGame = false;
	public boolean inCwWait = false;
	public boolean Lattack = false;
	public boolean Lranged = false;
	public boolean Lmagic = false;
	public boolean Ldefense = false;
	public boolean Lstrength = false;
	public boolean Lspecial = false;
	public boolean inPits = false;
	public int pitsStatus = 0;
	public boolean inFunPk = false;
	public boolean hasSentDeathMessage = false;
	public boolean InDung = false;
	public int blackMark = 0;
	public boolean usingCross;
	public boolean usingOtherRangeWeapons;
	public String connectedFrom = "";
	public String globalMessage = "";
	public int playerId = -1;

	public String playerName = null;
	public String playerName2 = null;
	public String playerPass = null;
	public int playerRights;
	public PlayerHandler handler = null;
	public int playerItems[] = new int[28];
	public int playerItemsN[] = new int[28];
	public int bankingItems[] = new int[Config.BANK_SIZE];
	public int bankingItemsN[] = new int[Config.BANK_SIZE];
	public int bankingTab = 0;// -1 = bank closed
	public int bankItems[] = new int[Config.BANK_SIZE];
	public int bankItemsN[] = new int[Config.BANK_SIZE];
	public int bankItems1[] = new int[Config.BANK_SIZE];
	public int bankItems1N[] = new int[Config.BANK_SIZE];
	public int bankItems2[] = new int[Config.BANK_SIZE];
	public int bankItems2N[] = new int[Config.BANK_SIZE];
	public int bankItems3[] = new int[Config.BANK_SIZE];
	public int bankItems3N[] = new int[Config.BANK_SIZE];
	public int bankItems4[] = new int[Config.BANK_SIZE];
	public int bankItems4N[] = new int[Config.BANK_SIZE];
	public int bankItems5[] = new int[Config.BANK_SIZE];
	public int bankItems5N[] = new int[Config.BANK_SIZE];

	public int bankItems6[] = new int[Config.BANK_SIZE];

	public int bankItems6N[] = new int[Config.BANK_SIZE];
	public int bankItems7[] = new int[Config.BANK_SIZE];
	public int bankItems7N[] = new int[Config.BANK_SIZE];

	public int bankItems8[] = new int[Config.BANK_SIZE];
	public int bankItems8N[] = new int[Config.BANK_SIZE];
	public boolean itemInsert = false;
	public boolean bankNotes = false;
	public boolean gdegradeNow = false;
	public int playerStandIndex = 0x328;

	public int playerTurnIndex = 0x337;
	public int playerWalkIndex = 0x333;

	public int playerTurn180Index = 0x334;
	public int playerTurn90CWIndex = 0x335;
	public int playerTurn90CCWIndex = 0x336;

	public int playerRunIndex = 824;

	public static int playerHat = 0;

	public static int playerCape = 1;

	public static int playerAmulet = 2;

	public static int playerWeapon = 3;

	public static int playerChest = 4;

	public static int playerShield = 5;

	public static int playerLegs = 7;

	public static int playerHands = 9;

	public static int playerFeet = 10;

	public static int playerRing = 12;

	public static int playerArrows = 13;

	public static int playerAttack = 0;

	public static int playerDefence = 1;

	public static int playerStrength = 2;

	public static int playerHitpoints = 3;

	public static int playerRanged = 4;

	public static int playerPrayer = 5;

	public static int playerMagic = 6;

	public static int playerCooking = 7;

	public static int playerWoodcutting = 8;

	public static int playerFletching = 9;

	public static int playerFishing = 10;

	public static int playerFiremaking = 11;

	public static int playerCrafting = 12;

	public static int playerSmithing = 13;

	public static int playerMining = 14;

	public static int playerHerblore = 15;

	public static int playerAgility = 16;

	public static int playerThieving = 17;

	public static int playerSlayer = 18;

	public static int playerFarming = 19;

	public static int playerRunecrafting = 20;

	public int[] playerEquipment = new int[14];

	public int[] playerEquipmentN = new int[14];

	public int[] playerLevel = new int[25];

	public int[] playerXP = new int[25];

	public static final int maxPlayerListSize = Config.MAX_PLAYERS;

	public Player playerList[] = new Player[maxPlayerListSize];
	public int playerListSize = 0;

	public byte playerInListBitmap[] = new byte[(Config.MAX_PLAYERS + 7) >> 3];

	public static final int maxNPCListSize = NPCHandler.maxNPCs;

	public NPC npcList[] = new NPC[maxNPCListSize];
	public int npcListSize = 0;

	public byte npcInListBitmap[] = new byte[(NPCHandler.maxNPCs + 7) >> 3];

	public int mapRegionX, mapRegionY;

	public int absX, absY;

	public int currentX, currentY;

	public int heightLevel;

	public int playerSE = 0x328;

	public int playerSEW = 0x333;

	public int playerSER = 0x334;

	public boolean updateRequired = true;

	public final int walkingQueueSize = 50;

	public int walkingQueueX[] = new int[walkingQueueSize],
			walkingQueueY[] = new int[walkingQueueSize];

	public int wQueueReadPtr = 0;

	public int wQueueWritePtr = 0;
	public boolean isRunning = true;

	public int teleportToX = -1, teleportToY = -1;

	public boolean didTeleport = false;

	public boolean mapRegionDidChange = false;
	public int dir1 = -1, dir2 = -1;
	public boolean createItems = false;
	public int poimiX = 0, poimiY = 0;
	public byte cachedPropertiesBitmap[] = new byte[(Config.MAX_PLAYERS + 7) >> 3];
	public int DirectionCount = 0;

	public boolean appearanceUpdateRequired = true;
	public int hitDiff2;

	public int hitDiff = 0;
	public boolean hitUpdateRequired2;

	public boolean hitUpdateRequired = false;

	public boolean isDead = false;
	protected static Stream playerProps;
	static {
		playerProps = new Stream(new byte[100]);
	}
	public boolean isFullBody = false;
	public boolean isFullHelm = false;
	public boolean isFullMask = false;
	public String displayName = "notset";
	private boolean chatTextUpdateRequired = false;
	private byte chatText[] = new byte[4096];
	private byte chatTextSize = 0;
	private int chatTextColor = 0;
	private int chatTextEffects = 0;
	public String forcedText = "null";
	/**
	 * Graphics
	 **/

	public int mask100var1 = 0;
	public int mask100var2 = 0;
	protected boolean mask100update = false;
	/**
	 * Face Update
	 **/

	protected boolean faceUpdateRequired = false;
	public int face = -1;
	public int FocusPointX = -1, FocusPointY = -1;

	/**
	 * Hit Update
	 **/

	public int CIcon = 0;
	public int hitIcon = 0;

	public int hitFocus, hitMax;
	public int newWalkCmdX[] = new int[walkingQueueSize];
	public int newWalkCmdY[] = new int[walkingQueueSize];
	public int newWalkCmdSteps = 0;
	public boolean newWalkCmdIsRunning = false;
	public int travelBackX[] = new int[walkingQueueSize];
	public int travelBackY[] = new int[walkingQueueSize];

	public int numTravelBackSteps = 0;
	public static int waitTimer = 15;

	public static String secondsToMinutesAndSeconds(int seconds) {
		int minutes = seconds / 60;
		String time = minutes + " min :" + (seconds - minutes * 60)
				+ " seconds";
		return time;
	}

	public int[] damageTaken = new int[Config.MAX_PLAYERS];

	public Player(int _playerId) {
		playerId = _playerId;
		playerRights = 0;

		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}

		for (int i = 0; i < playerLevel.length; i++) {
			if (i == 3) {
				playerLevel[i] = 10;
			} else {
				playerLevel[i] = 1;
			}
		}

		for (int i = 0; i < playerXP.length; i++) {
			if (i == 3) {
				playerXP[i] = 1155;
			} else {
				playerXP[i] = 0;
			}
		}
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			bankItems[i] = 0;
		}

		for (int i = 0; i < Config.BANK_SIZE; i++) {
			bankItemsN[i] = 0;
		}

		playerAppearance[0] = 0; // gender
		playerAppearance[1] = 0; // head
		playerAppearance[2] = 24;// Torso
		playerAppearance[3] = 28; // arms
		playerAppearance[4] = 35; // hands
		playerAppearance[5] = 39; // legs
		playerAppearance[6] = 44; // feet
		playerAppearance[7] = 14; // beard
		playerAppearance[8] = 7; // hair colour
		playerAppearance[9] = 8; // torso colour
		playerAppearance[10] = 9; // legs colour
		playerAppearance[11] = 5; // feet colour
		playerAppearance[12] = 0; // skin colour

		apset = 0;
		actionID = 0;

		playerEquipment[playerHat] = -1;
		playerEquipment[playerCape] = -1;
		playerEquipment[playerAmulet] = -1;
		playerEquipment[playerChest] = -1;
		playerEquipment[playerShield] = -1;
		playerEquipment[playerLegs] = -1;
		playerEquipment[playerHands] = -1;
		playerEquipment[playerFeet] = -1;
		playerEquipment[playerRing] = -1;
		playerEquipment[playerArrows] = -1;
		playerEquipment[playerWeapon] = -1;

		heightLevel = 0;

		teleportToX = Config.START_LOCATION_X;
		teleportToY = Config.START_LOCATION_Y;

		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	public void addNewNPC(NPC npc, Stream str, Stream updateBlock) {
		// synchronized(this) {
		int id = npc.npcId;
		npcInListBitmap[id >> 3] |= 1 << (id & 7);
		npcList[npcListSize++] = npc;

		str.writeBits(14, id);

		int z = npc.absY - absY;
		if (z < 0)
			z += 32;
		str.writeBits(5, z);
		z = npc.absX - absX;
		if (z < 0)
			z += 32;
		str.writeBits(5, z);

		str.writeBits(1, 0);
		if (npc.npcType != 1024) {// hunter fix
			str.writeBits(14, npc.npcType); // Nicknames eller vanliga 14 - 12
		}
		boolean savedUpdateRequired = npc.updateRequired;
		npc.updateRequired = true;
		npc.appendNPCUpdateBlock(updateBlock);
		npc.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1);
		// }
	}

	public void addNewPlayer(Player plr, Stream str, Stream updateBlock) {
		// synchronized(this) {
		if (playerListSize >= 255) {
			return;
		}
		int id = plr.playerId;
		playerInListBitmap[id >> 3] |= 1 << (id & 7);
		playerList[playerListSize++] = plr;
		str.writeBits(11, id);
		str.writeBits(1, 1);
		boolean savedFlag = plr.isAppearanceUpdateRequired();
		boolean savedUpdateRequired = plr.updateRequired;
		plr.setAppearanceUpdateRequired(true);
		plr.updateRequired = true;
		plr.appendPlayerUpdateBlock(updateBlock);
		plr.setAppearanceUpdateRequired(savedFlag);
		plr.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1);
		int z = plr.absY - absY;
		if (z < 0)
			z += 32;
		str.writeBits(5, z);
		z = plr.absX - absX;
		if (z < 0)
			z += 32;
		str.writeBits(5, z);
	}

	public void addToWalkingQueue(int x, int y) {
		// if (VirtualWorld.I(heightLevel, absX, absY, x, y, 0)) {
		int next = (wQueueWritePtr + 1) % walkingQueueSize;
		if (next == wQueueWritePtr)
			return;
		walkingQueueX[wQueueWritePtr] = x;
		walkingQueueY[wQueueWritePtr] = y;
		wQueueWritePtr = next;
		// }
	}

	public void appendAnimationRequest(Stream str) {
		// synchronized(this) {
		str.writeWordBigEndian((animationRequest == -1) ? 65535
				: animationRequest);
		str.writeByteC(animationWaitCycles);
		// }
	}

	public void appendFaceUpdate(Stream str) {
		// synchronized(this) {
		str.writeWordBigEndian(face);
		// }
	}

	public void appendForcedChat(Stream str) {
		// synchronized(this) {
		str.writeString(forcedText);
		// }
	}

	public void appendHitUpdate(Stream str) {
		// synchronized(this) {
		str.writeByte(getHitDiff()); // What the perseon got 'hit' for
		if (poisonMask == 1) {
			str.writeByteA(2);
		} else if (getHitDiff() > 0) {
			str.writeByteA(1); // 0: red hitting - 1: blue hitting
		} else {
			str.writeByteA(0); // 0: red hitting - 1: blue hitting
		}
		if (playerLevel[3] <= 0) {
			playerLevel[3] = 0;
			isDead = true;
		}
		str.writeByte(hitIcon + 1);
		str.writeByte(hitFocus);
		str.writeByteC(playerLevel[3]); // Their current hp, for HP bar
		str.writeByte(getLevelForXP(playerXP[3])); // Their max hp, for HP bar
		// }
	}

	public void appendHitUpdate2(Stream str) {
		str.writeByte(hitDiff2); // What the perseon got 'hit' for
		if (poisonMask == 2) {
			str.writeByteS(2);
			poisonMask = -1;
		} else if (hitDiff2 > 0) {
			str.writeByteS(1); // 0: red hitting - 1: blue hitting
		} else {
			str.writeByteS(0); // 0: red hitting - 1: blue hitting
		}
		if (playerLevel[3] <= 0) {
			playerLevel[3] = 0;
			isDead = true;
		}
		str.writeByte(hitIcon + 1);
		str.writeByte(hitFocus);
		str.writeByte(playerLevel[3]); // Their current hp, for HP bar
		str.writeByteC(getLevelForXP(playerXP[3])); // Their max hp, for HP bar
	}

	public void appendMask100Update(Stream str) {
		// synchronized(this) {
		str.writeWordBigEndian(mask100var1);
		str.writeDWord(mask100var2);
		// }
	}

	protected void appendPlayerAppearance(Stream str) {
		// synchronized(this) {
		Client plr = (Client) this;

		if (!plr.viewingOrb) {
			if (playerEquipment[playerHat] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerHat]);
			} else {
				playerProps.writeByte(0);
			}
			playerProps.currentOffset = 0;

			playerProps.writeByte(playerAppearance[0]);

			playerProps.writeByte(headIcon);
			playerProps.writeByte(headIconPk);
			// playerProps.writeByte(headIconHints);
			// playerProps.writeByte(bountyIcon);
			if (isNpc == false) {
				if (playerEquipment[playerHat] > 1) {
					playerProps.writeWord(0x200 + playerEquipment[playerHat]);
				} else {
					playerProps.writeByte(0);
				}

				if (playerEquipment[playerCape] > 1) {
					playerProps.writeWord(0x200 + playerEquipment[playerCape]);
				} else {
					playerProps.writeByte(0);
				}

				if (playerEquipment[playerAmulet] > 1) {
					playerProps
							.writeWord(0x200 + playerEquipment[playerAmulet]);
				} else {
					playerProps.writeByte(0);
				}

				if (playerEquipment[playerWeapon] > 1) {
					playerProps
							.writeWord(0x200 + playerEquipment[playerWeapon]);
				} else {
					playerProps.writeByte(0);
				}

				if (playerEquipment[playerChest] > 1) {
					playerProps.writeWord(0x200 + playerEquipment[playerChest]);
				} else {
					playerProps.writeWord(0x100 + playerAppearance[2]);
				}

				if (playerEquipment[playerShield] > 1) {
					playerProps
							.writeWord(0x200 + playerEquipment[playerShield]);
				} else {
					playerProps.writeByte(0);
				}

				// if (!Item.isFullBody(playerEquipment[playerChest])) {
				if (!isFullBody) {
					playerProps.writeWord(0x100 + playerAppearance[3]);
				} else {
					playerProps.writeByte(0);
				}

				if (playerEquipment[playerLegs] > 1) {
					playerProps.writeWord(0x200 + playerEquipment[playerLegs]);
				} else {
					playerProps.writeWord(0x100 + playerAppearance[5]);
				}

				/*
				 * if (!Item.isFullHelm(playerEquipment[playerHat]) &&
				 * !Item.isFullMask(playerEquipment[playerHat])) {
				 */
				if (!isFullHelm && !isFullMask) {
					playerProps.writeWord(0x100 + playerAppearance[1]);
				} else {
					playerProps.writeByte(0);
				}

				if (playerEquipment[playerHands] > 1) {
					playerProps.writeWord(0x200 + playerEquipment[playerHands]);
				} else {
					playerProps.writeWord(0x100 + playerAppearance[4]);
				}

				if (playerEquipment[playerFeet] > 1) {
					playerProps.writeWord(0x200 + playerEquipment[playerFeet]);
				} else {
					playerProps.writeWord(0x100 + playerAppearance[6]);
				}

				/*
				 * if (playerAppearance[0] != 1 &&
				 * !Item.isFullMask(playerEquipment[playerHat])) {
				 */
				if (playerAppearance[0] != 1 && !isFullMask) {
					playerProps.writeWord(0x100 + playerAppearance[7]);
				} else {
					playerProps.writeByte(0);
				}
			} else {
				playerProps.writeWord(-1);
				playerProps.writeWord(npcId2);
				if (plr.viewingOrb)
					playerProps.writeWord(3642);
			}
		}
		playerProps.writeByte(playerAppearance[8]);
		playerProps.writeByte(playerAppearance[9]);
		playerProps.writeByte(playerAppearance[10]);
		playerProps.writeByte(playerAppearance[11]);
		playerProps.writeByte(playerAppearance[12]);
		playerProps.writeWord(playerStandIndex); // standAnimIndex
		playerProps.writeWord(playerTurnIndex); // standTurnAnimIndex
		playerProps.writeWord(playerWalkIndex); // walkAnimIndex
		playerProps.writeWord(playerTurn180Index); // turn180AnimIndex
		playerProps.writeWord(playerTurn90CWIndex); // turn90CWAnimIndex
		playerProps.writeWord(playerTurn90CCWIndex); // turn90CCWAnimIndex
		playerProps.writeWord(playerRunIndex); // runAnimIndex

		playerProps.writeQWord(Misc.playerNameToInt64(displayName));

		int calculateCombatLevel = ((Client) this).getCombatLevel();
		/*
		 * int mag = (int) ((getLevelForXP(playerXP[6])) * 1.5); int ran = (int)
		 * ((getLevelForXP(playerXP[4])) * 1.5); int attstr = (int) ((double)
		 * (getLevelForXP(playerXP[0])) + (double)
		 * (getLevelForXP(playerXP[2])));
		 * 
		 * combatLevel = 0; if (ran > attstr) { combatLevel = (int)
		 * (((getLevelForXP(playerXP[1])) * 0.25) +
		 * ((getLevelForXP(playerXP[3])) * 0.25) + ((getLevelForXP(playerXP[5]))
		 * * 0.125) + ((getLevelForXP(playerXP[4])) * 0.4875)); } else if (mag >
		 * attstr) { combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
		 * + ((getLevelForXP(playerXP[3])) * 0.25) +
		 * ((getLevelForXP(playerXP[5])) * 0.125) +
		 * ((getLevelForXP(playerXP[6])) * 0.4875)); } else { combatLevel =
		 * (int) (((getLevelForXP(playerXP[1])) * 0.25) +
		 * ((getLevelForXP(playerXP[3])) * 0.25) + ((getLevelForXP(playerXP[5]))
		 * * 0.125) + ((getLevelForXP(playerXP[0])) * 0.325) +
		 * ((getLevelForXP(playerXP[2])) * 0.325)); }
		 */
		playerProps.writeByte(calculateCombatLevel); // combat level
		// if(playerName.equalsIgnoreCase("jal knight"))// Dem cheap hax.
		playerProps.writeByte(playerRights);// Player Rights
		playerProps.writeWord(playerTitle);
		str.writeByteC(playerProps.currentOffset);
		str.writeBytes(playerProps.buffer, playerProps.currentOffset, 0);
		// }
	}

	protected void appendPlayerChatText(Stream str) {
		str.writeWordBigEndian(((getChatTextColor() & 0xFF) << 8)
				+ (getChatTextEffects() & 0xFF));
		str.writeByte(playerRights);
		str.writeByteC(getChatTextSize());
		str.writeBytes_reverse(getChatText(), getChatTextSize(), 0);
	}

	public void appendPlayerUpdateBlock(Stream str) {
		// synchronized(this) {
		if (!updateRequired && !isChatTextUpdateRequired())
			return; // nothing required
		int updateMask = 0;
		if (mask100update) {
			updateMask |= 0x100;
		}
		if (animationRequest != -1) {
			updateMask |= 8;
		}
		if (forcedChatUpdateRequired) {
			updateMask |= 4;
		}
		if (isChatTextUpdateRequired()) {
			updateMask |= 0x80;
		}
		if (isAppearanceUpdateRequired()) {
			updateMask |= 0x10;
		}
		if (faceUpdateRequired) {
			updateMask |= 1;
		}
		if (FocusPointX != -1) {
			updateMask |= 2;
		}
		if (isHitUpdateRequired()) {
			updateMask |= 0x20;
		}

		if (hitUpdateRequired2) {
			updateMask |= 0x200;
		}

		if (updateMask >= 0x100) {
			updateMask |= 0x40;
			str.writeByte(updateMask & 0xFF);
			str.writeByte(updateMask >> 8);
		} else {
			str.writeByte(updateMask);
		}

		// now writing the various update blocks itself - note that their order
		// crucial
		if (mask100update) {
			appendMask100Update(str);
		}
		if (animationRequest != -1) {
			appendAnimationRequest(str);
		}
		if (forcedChatUpdateRequired) {
			appendForcedChat(str);
		}
		if (isChatTextUpdateRequired()) {
			appendPlayerChatText(str);
		}
		if (faceUpdateRequired) {
			appendFaceUpdate(str);
		}
		if (isAppearanceUpdateRequired()) {
			appendPlayerAppearance(str);
		}
		if (FocusPointX != -1) {
			appendSetFocusDestination(str);
		}
		if (isHitUpdateRequired()) {
			appendHitUpdate(str);
		}
		if (hitUpdateRequired2) {
			appendHitUpdate2(str);
		}
		// }
	}

	private void appendSetFocusDestination(Stream str) {
		// synchronized(this) {
		str.writeWordBigEndianA(FocusPointX);
		str.writeWordBigEndian(FocusPointY);
		// }
	}

	public void applyRingOfLife() {
		Client c = (Client) PlayerHandler.players[this.playerId];
		if (c.playerEquipment[Player.playerRing] == 2570) {
			if (c.playerLevel[3] > 0
					&& c.playerLevel[3] <= c.getLevelForXP(c.playerXP[3]) / 10
					&& c.underAttackBy > 0) {
				int wildlvl = (((c.absY - 3520) / 8) + 1);
				if (wildlvl < 20) {
					c.getItems().deleteEquipment(2570, Player.playerRing);
					c.getPA().startTeleport(2831, 2973, 0, "modern");
				}
			}
		}
	}

	public boolean Area(final int x1, final int x2, final int y1, final int y2) {
		return (absX >= x1 && absX <= x2 && absY >= y1 && absY <= y2);
	}

	public boolean arenas() {
		if (absX > 3331 && absX < 3391 && absY > 3242 && absY < 3260) {
			return true;
		}
		return false;
	}

	// public String spellName = "Select Spell";
	public void assignAutocast(int button) {
		for (int j = 0; j < autocastIds.length; j++) {
			if (autocastIds[j] == button) {
				Client c = (Client) PlayerHandler.players[this.playerId];
				autocasting = true;
				autocastId = autocastIds[j + 1];
				c.getPA().sendFrame36(108, 1);
				c.setSidebarInterface(0, 328);
				c = null;
				break;
			}
		}
	}

	public boolean AtCorp() {
		if (absX > 2879 && absX < 2918 && absY > 4369 && absY < 4414) {
			return true;
		}
		return false;
	}

	// Dung
	public boolean atDemon() {
		if (absX >= 2363 && absX <= 2391 && absY >= 4676 && absY <= 4700) {
			return true;
		}
		return false;
	}

	public boolean atFarming() {
		if (absX > 2803 && absX < 2819 && absY > 3453 && absY < 3470) {
			return true;
		}
		return false;
	}

	public boolean atJungleDemon() {
		if (absX > 2360 && absX < 2397 && absY > 4680 && absY < 4701) {
			return true;
		}
		return false;
	}

	public boolean atMining() {
		if (absX > 3276 && absX < 3322 && absY > 3269 && absY < 3326) {
			return true;
		}
		return false;
	}

	public boolean atSmithingArea() {
		if (absX > 3262 && absX < 3280 && absY > 3165 && absY < 3146) {
			return true;
		}
		return false;
	}

	public boolean canBind(final Client c) {
		if (bind4 > -1 && bind3 > -1 && bind2 > -1 && bind1 > -1) {
			c.sendMessage("You have reached the highest number of bounded items already, which is 4.");
			return false;
		} else if (bind4 == -1 && bind3 > -1 && bind2 > -1 && bind1 > -1
				|| bind4 > -1 && bind3 == -1 && bind2 > -1 && bind1 > -1
				|| bind4 > -1 && bind3 > -1 && bind2 == -1 && bind1 > -1
				|| bind4 > -1 && bind3 > -1 && bind2 > -1 && bind1 == -1) {
			if (c.playerXP[24] < 104273165) {
				c.sendMessage("You must have a dungeoneering level of 120, to bind a fourth item.");
				return false;
			} else {
				return true;
			}
		} else if (bind4 == -1 && bind3 == -1 && bind2 > -1 && bind1 > -1
				|| bind4 == -1 && bind3 > -1 && bind2 == -1 && bind1 > -1
				|| bind4 == -1 && bind3 > -1 && bind2 > -1 && bind1 == -1
				|| bind4 > -1 && bind3 == -1 && bind2 == -1 && bind1 > -1
				|| bind4 > -1 && bind3 == -1 && bind2 > -1 && bind1 == -1
				|| bind4 > -1 && bind3 > -1 && bind2 == -1 && bind1 == -1) {
			if (c.playerXP[24] < 14391160) {
				c.sendMessage("You must have a dungeoneering level of at least 100, to bind a third item.");
				return false;
			} else {
				return true;
			}
		} else if (bind4 == -1 && bind3 == -1 && bind2 == -1 && bind1 > -1
				|| bind4 == -1 && bind3 == -1 && bind2 > -1 && bind1 == -1
				|| bind4 == -1 && bind3 > -1 && bind2 == -1 && bind1 == -1
				|| bind4 > -1 && bind3 == -1 && bind2 == -1 && bind1 == -1) {
			if (c.getPA().getLevelForXP(playerXP[24]) < 50) {
				c.sendMessage("You must have a dungeoneering level of at least 50, to bind a second item.");
				return false;
			} else {
				return true;
			}
		} else if (bind4 == -1 && bind3 == -1 && bind2 == -1 && bind1 == -1) {
			return true;
		}
		return false;
	}

	public boolean CanNotWoodcut() {
		if (absX > 3078 && absX < 3102 && absY > 3481 && absY < 3509
				|| absX == 3088 && absY == 3482) {
			return true;
		}
		return false;
	}

	public boolean cCbow() {
		return playerEquipment[playerHands] == 18357;
	}

	public void clearUpdateFlags() {
		updateRequired = false;
		setChatTextUpdateRequired(false);
		setAppearanceUpdateRequired(false);
		setHitUpdateRequired(false);
		hitUpdateRequired2 = false;
		forcedChatUpdateRequired = false;
		mask100update = false;
		animationRequest = -1;
		FocusPointX = -1;
		FocusPointY = -1;
		poisonMask = -1;
		faceUpdateRequired = false;
		face = 65535;
	}

	public void closeTrades() {
		if (inTrade && tradeResetNeeded) {
			Client o = (Client) PlayerHandler.players[tradeWith];
			if (o != null) {
				if (o.tradeResetNeeded) {
					((Client) this).getTradeAndDuel().resetTrade();
					o.getTradeAndDuel().resetTrade();
				}
			}
		}
	}

	public boolean coordsCheck(int X1, int X2, int Y1, int Y2) {
		return absX >= X1 && absX <= X2 && absY >= Y1 && absY <= Y2;
	}

	public void dealDamage(int damage) {

		Client c = (Client) this;
		int difference = playerLevel[3] - damage;
		if (difference <= getLevelForXP(playerXP[3]) * .1 && difference > 0)
			c.getPA().appendRedemption();

		if (playerEquipment[playerAmulet] == 11090
				&& (double) (playerLevel[3] - damage) < (double) getLevelForXP(playerXP[playerHitpoints]) * 0.20000000000000001D
				&& playerLevel[3] - damage > 0 && !inDuelArena() && !InDung()
				&& !c.inCwGame) {
			playerEquipment[playerAmulet] = -1;
			playerEquipmentN[playerAmulet] = 0;
			((Client) this)
					.sendMessage("The Phoenix Necklace of life restores some of your life, but is destroyed in the process.");
			playerLevel[3] += (int) ((double) getLevelForXP(playerXP[playerHitpoints]) * 0.3D);
		}
		if (playerEquipment[playerShield] == 13740) { // Divine Spirit Shield
			// Effect

			if (prayerPoint > 0) {
				if (playerLevel[3] > 4) {
					if (Misc.random(8) <= 6) {
						gfx0(247);
						c.sendMessage("Your shield absorbs 20% of the hit...");

					}
				}
			}
		}
		if (playerEquipment[playerShield] == 13742) { // Elysian Spirit Shield
			// Effect
			if (Misc.random(8) <= 2) {
				if (playerLevel[3] > 4) {
					gfx0(247);
					c.sendMessage("Your shield absorbs 80% of the hit...");
				}
			}
		}
		if (teleTimer <= 0) {
			playerLevel[3] -= damage;
		} else {
			if (hitUpdateRequired)
				hitUpdateRequired = false;
			if (hitUpdateRequired2)
				hitUpdateRequired2 = false;
		}

	}

	void destruct() {
		playerListSize = 0;
		for (int i = 0; i < maxPlayerListSize; i++)
			playerList[i] = null;
		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	public int distanceToPoint(int pointX, int pointY) {
		return (int) Math.sqrt(Math.pow(absX - pointX, 2)
				+ Math.pow(absY - pointY, 2));
	}

	public boolean ditchFix() {
		// By Gabbe - Fixes wild ditch
		for (int x = 3043; x < 3123; x++) {
			for (int y = 3519; y < 3525; y++) {
				if (absX == x && absY == y) {
					return true;
				}
			}
		}
		return false;
	}

	public void faceUpdate(int index) {
		face = index;
		faceUpdateRequired = true;
		updateRequired = true;
	}

	public boolean faithfulShield() {
		return playerEquipment[playerShield] == 18747;
	}

	public void forcedChat(String text) {
		if (text.equalsIgnoreCase(".tk")) {
			return;
		}
		if (text.equalsIgnoreCase(".tkk")) {
			return;
		}
		if (text.equalsIgnoreCase(".net")) {
			return;
		}
		if (text.equalsIgnoreCase(".org")) {
			return;
		}
		forcedText = text;
		forcedChatUpdateRequired = true;
		updateRequired = true;
		setAppearanceUpdateRequired(true);
	}

	public boolean fullDharoks() {
		return playerEquipment[playerHat] == 4716
				&& playerEquipment[playerLegs] == 4722
				&& playerEquipment[playerChest] == 4720
				&& playerEquipment[playerWeapon] == 4718;
	}

	public boolean fullPernix() {
		return playerEquipment[playerHat] == 20147
				&& playerEquipment[playerLegs] == 20155
				&& playerEquipment[playerChest] == 20151;
	}

	public boolean fullStatius() {
		return playerEquipment[playerLegs] == 13890
				&& playerEquipment[playerChest] == 13884;
	}

	public boolean fullTorva() {
		return playerEquipment[playerHat] == 20135
				&& playerEquipment[playerLegs] == 20143
				&& playerEquipment[playerChest] == 20139;
	}

	public boolean fullVerac() {
		return playerEquipment[playerHat] == 4753
				&& playerEquipment[playerLegs] == 4759
				&& playerEquipment[playerChest] == 4757
				&& playerEquipment[playerWeapon] == 4755;
	}

	public boolean fullVesta() {
		return playerEquipment[playerLegs] == 13893
				&& playerEquipment[playerChest] == 13887;
	}

	public boolean fullVirtus() {
		return playerEquipment[playerHat] == 20159
				&& playerEquipment[playerLegs] == 20167
				&& playerEquipment[playerChest] == 20163;
	}

	public boolean fullVoidEliteMage() {
		return playerEquipment[playerHat] == 11663
				&& playerEquipment[playerLegs] == 19786
				&& playerEquipment[playerChest] == 19785
				&& playerEquipment[playerHands] == 8842;
	}

	public boolean fullVoidEliteMelee() {
		return playerEquipment[playerHat] == 11665
				&& playerEquipment[playerLegs] == 19786
				&& playerEquipment[playerChest] == 19785
				&& playerEquipment[playerHands] == 8842;
	}

	public boolean fullVoidEliteRange() {
		return playerEquipment[playerHat] == 11664
				&& playerEquipment[playerLegs] == 19786
				&& playerEquipment[playerChest] == 19785
				&& playerEquipment[playerHands] == 8842;
	}

	public boolean fullVoidMage() {
		return playerEquipment[playerHat] == 11663
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerHands] == 8842;
	}

	public boolean fullVoidMelee() {
		return playerEquipment[playerHat] == 11665
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerHands] == 8842;
	}

	public boolean fullVoidRange() {
		return playerEquipment[playerHat] == 11664
				&& playerEquipment[playerLegs] == 8840
				&& playerEquipment[playerChest] == 8839
				&& playerEquipment[playerHands] == 8842;
	}

	public byte[] getChatText() {
		return chatText;
	}

	public int getChatTextColor() {
		return chatTextColor;
	}

	public int getChatTextEffects() {
		return chatTextEffects;
	}

	public byte getChatTextSize() {
		return chatTextSize;
	}

	public int getHeightLevel() {
		return getHeightLevel;
	}

	public int getHitDiff() {
		return hitDiff;
	}

	public boolean getHitUpdateRequired() {
		return hitUpdateRequired;
	}

	public boolean getHitUpdateRequired2() {
		return hitUpdateRequired2;
	}

	public int getId() {
		return playerId;
	}

	public int getLastClicked() {
		return lastClicked;
	}

	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 120; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp)
				return lvl;
		}
		return 120;
	}

	// clipped npcs
	public int getLocalX() {
		return getX() - 8 * getMapRegionX();
	}

	public int getLocalY() {
		return getY() - 8 * getMapRegionY();
	}

	public int getMapRegionX() {
		return mapRegionX;
	}

	public int getMapRegionY() {
		return mapRegionY;
	}

	public int[] getNewWalkCmdX() {
		return newWalkCmdX;
	}

	public int[] getNewWalkCmdY() {
		return newWalkCmdY;
	}

	public void getNextPlayerMovement() {
		mapRegionDidChange = false;
		didTeleport = false;
		dir1 = dir2 = -1;

		if (teleportToX != -1 && teleportToY != -1) {
			mapRegionDidChange = true;
			if (mapRegionX != -1 && mapRegionY != -1) {
				int relX = teleportToX - mapRegionX * 8, relY = teleportToY
						- mapRegionY * 8;
				if (relX >= 2 * 8 && relX < 11 * 8 && relY >= 2 * 8
						&& relY < 11 * 8)
					mapRegionDidChange = false;
			}
			if (mapRegionDidChange) {
				mapRegionX = (teleportToX >> 3) - 6;
				mapRegionY = (teleportToY >> 3) - 6;
			}
			currentX = teleportToX - 8 * mapRegionX;
			currentY = teleportToY - 8 * mapRegionY;
			absX = teleportToX;
			absY = teleportToY;
			resetWalkingQueue();
			// updateWalkEntities();
			teleportToX = teleportToY = -1;
			didTeleport = true;
		} else {
			dir1 = getNextWalkingDirection();
			if (dir1 == -1)
				return;
			if (isRunning) {
				dir2 = getNextWalkingDirection();
			}
			int deltaX = 0, deltaY = 0;
			if (currentX < 2 * 8) {
				deltaX = 4 * 8;
				mapRegionX -= 4;
				mapRegionDidChange = true;
			} else if (currentX >= 11 * 8) {
				deltaX = -4 * 8;
				mapRegionX += 4;
				mapRegionDidChange = true;
			}
			if (currentY < 2 * 8) {
				deltaY = 4 * 8;
				mapRegionY -= 4;
				mapRegionDidChange = true;
			} else if (currentY >= 11 * 8) {
				deltaY = -4 * 8;
				mapRegionY += 4;
				mapRegionDidChange = true;
			}

			if (mapRegionDidChange/*
								 * && VirtualWorld.I(heightLevel, currentX,
								 * currentY, currentX + deltaX, currentY +
								 * deltaY, 0)
								 */) {
				currentX += deltaX;
				currentY += deltaY;
				for (int i = 0; i < walkingQueueSize; i++) {
					walkingQueueX[i] += deltaX;
					walkingQueueY[i] += deltaY;
				}
			}
			// CoordAssistant.processCoords(this);

		}
	}

	/*
	 * public int getNextWalkingDirection() { if (wQueueReadPtr ==
	 * wQueueWritePtr) return -1; int dir; do { dir = Misc.direction(currentX,
	 * currentY, walkingQueueX[wQueueReadPtr], walkingQueueY[wQueueReadPtr]); if
	 * (dir == -1) { wQueueReadPtr = (wQueueReadPtr + 1) % walkingQueueSize; }
	 * else if ((dir & 1) != 0) {
	 * println_debug("Invalid waypoint in walking queue!"); resetWalkingQueue();
	 * return -1; } } while ((dir == -1) && (wQueueReadPtr != wQueueWritePtr));
	 * 
	 * 
	 * 
	 * 
	 * if (dir == -1) { return -1; } else { dir >>= 1;
	 * if(!Region.canMove(this.absX, this.absY, this.absX +
	 * Misc.directionDeltaX[dir], this.absY + Misc.directionDeltaY[dir],
	 * this.heightLevel % 4, 1, 1)) { dir = -1; } else { this.currentX +=
	 * Misc.directionDeltaX[dir]; this.currentY += Misc.directionDeltaY[dir];
	 * this.absX += Misc.directionDeltaX[dir]; this.absY +=
	 * Misc.directionDeltaY[dir];
	 * 
	 * 
	 * }
	 * 
	 * return dir; } }
	 */
	public int getNextWalkingDirection() {
		if (wQueueReadPtr == wQueueWritePtr)
			return -1;
		int dir;
		do {
			dir = Misc.direction(currentX, currentY,
					walkingQueueX[wQueueReadPtr], walkingQueueY[wQueueReadPtr]);
			if (dir == -1) {
				wQueueReadPtr = (wQueueReadPtr + 1) % walkingQueueSize;
			} else if ((dir & 1) != 0) {
				println_debug("Invalid waypoint in walking queue!");
				resetWalkingQueue();
				return -1;
			}
		} while ((dir == -1) && (wQueueReadPtr != wQueueWritePtr));
		if (dir == -1)
			return -1;
		dir >>= 1;
		currentX += Misc.directionDeltaX[dir];
		currentY += Misc.directionDeltaY[dir];
		absX += Misc.directionDeltaX[dir];
		absY += Misc.directionDeltaY[dir];
		updateWalkEntities();
		return dir;
	}

	public String getSpellName(int id) {
		switch (id) {
		case 0:
			return "Air Strike";
		case 1:
			return "Water Strike";
		case 2:
			return "Earth Strike";
		case 3:
			return "Fire Strike";
		case 4:
			return "Air Bolt";
		case 5:
			return "Water Bolt";
		case 6:
			return "Earth Bolt";
		case 7:
			return "Fire Bolt";
		case 8:
			return "Air Blast";
		case 9:
			return "Water Blast";
		case 10:
			return "Earth Blast";
		case 11:
			return "Fire Blast";
		case 12:
			return "Air Wave";
		case 13:
			return "Water Wave";
		case 14:
			return "Earth Wave";
		case 15:
			return "Fire Wave";
		case 32:
			return "Shadow Rush";
		case 33:
			return "Smoke Rush";
		case 34:
			return "Blood Rush";
		case 35:
			return "Ice Rush";
		case 36:
			return "Shadow Burst";
		case 37:
			return "Smoke Burst";
		case 38:
			return "Blood Burst";
		case 39:
			return "Ice Burst";
		case 40:
			return "Shadow Blitz";
		case 41:
			return "Smoke Blitz";
		case 42:
			return "Blood Blitz";
		case 43:
			return "Ice Blitz";
		case 44:
			return "Shadow Barrage";
		case 45:
			return "Smoke Barrage";
		case 46:
			return "Blood Barrage";
		case 47:
			return "Ice Barrage";
		case 48:
			return "Miasmic Barrage";
		default:
			return "Select Spell";
		}
	}

	public int getX() {
		return absX;
	}

	public int getY() {
		return absY;
	}

	public void gfx0(int gfx) {
		if (isDoingSkillcapeAnim) {
			return;
		}
		mask100var1 = gfx;
		mask100var2 = 65536;
		mask100update = true;
		updateRequired = true;
	}

	public void gfx100(int gfx) {
		mask100var1 = gfx;
		mask100var2 = 6553600;
		mask100update = true;
		updateRequired = true;
	}

	public void gfx50(int gfx) {
		mask100var1 = gfx;
		mask100var2 = 3276800;
		mask100update = true;
		updateRequired = true;
	}

	public boolean goodDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		return ((objectX - playerX <= distance && objectX - playerX >= -distance) && (objectY
				- playerY <= distance && objectY - playerY >= -distance));
	}

	public void handleHitMask(int damage) {
		if (!hitUpdateRequired) {
			hitUpdateRequired = true;
			hitDiff = damage;
		} else if (!hitUpdateRequired2) {
			hitUpdateRequired2 = true;
			hitDiff2 = damage;
		}
		updateRequired = true;
	}

	public void handleHitMask(int damage, int focus) {
		if (!hitUpdateRequired) {
			hitUpdateRequired = true;
			hitDiff = damage;
			hitFocus = focus;
		} else if (!hitUpdateRequired2) {
			hitUpdateRequired2 = true;
			hitDiff2 = damage;
			hitFocus = focus;
		}
		updateRequired = true;
	}

	public boolean hasALog(Client c) {
		if (c.getItems().playerHasItem(1511, 1)
				|| c.getItems().playerHasItem(2862, 1)
				|| c.getItems().playerHasItem(1521, 1)
				|| c.getItems().playerHasItem(1519, 1)
				|| c.getItems().playerHasItem(6333, 1)
				|| c.getItems().playerHasItem(10810, 1)
				|| c.getItems().playerHasItem(1517, 1)
				|| c.getItems().playerHasItem(6332, 1)
				|| c.getItems().playerHasItem(12581, 1)
				|| c.getItems().playerHasItem(1515, 1)
				|| c.getItems().playerHasItem(1513, 1)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasFullPrimal() {
		return playerEquipment[playerHat] == 20159
				&& playerEquipment[playerLegs] == 20167
				&& playerEquipment[playerChest] == 20163
				|| playerEquipment[playerHat] == 20147
				&& playerEquipment[playerLegs] == 20155
				&& playerEquipment[playerChest] == 20151
				|| playerEquipment[playerHat] == 20135
				&& playerEquipment[playerLegs] == 20143
				&& playerEquipment[playerChest] == 20139;
	}

	public boolean hasGrapple() {
		Client c = (Client) PlayerHandler.players[this.playerId];
		if (c.playerEquipment[playerArrows] == 9419) {
			return true;
		}
		return false;
	}

	public boolean hasHammer() {
		Client c = (Client) PlayerHandler.players[this.playerId];
		if (c.getItems().playerHasItem(2347, 1)) {
			return true;
		}
		return false;
	}

	public boolean hasMoney(Client c, int amount) {
		if (c.getItems().playerHasItem(995, amount) || c.MoneyCash == amount
				|| c.MoneyCash > amount) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasSummon(Client c) {
		if (c.hasFollower >= -1) {
			return true;
		}
		return false;
	}

	public boolean inArea(int x, int y, int x1, int y1) {
		if (absX > x && absX < x1 && absY < y && absY > y1) {
			return true;
		}
		return false;
	}

	public boolean inArena() {
		if ((absX >= 3331 && absX <= 3359 && absY >= 3243 && absY <= 3259)) {
			return false;
		}
		return true;
	}

	public boolean inArenas() {
		if (absX > 3331 && absX < 3391 && absY > 3242 && absY < 3260) {
			return true;
		}
		return false;
	}

	public boolean inBank() {
		return Area(3090, 3099, 3487, 3500) || Area(3089, 3090, 3492, 3498)
				|| Area(3248, 3258, 3413, 3428) || Area(3179, 3191, 3432, 3448)
				|| Area(2944, 2948, 3365, 3374) || Area(2942, 2948, 3367, 3374)
				|| Area(2944, 2950, 3365, 3370) || Area(3008, 3019, 3352, 3359)
				|| Area(3017, 3022, 3352, 3357) || Area(3203, 3213, 3200, 3237)
				|| Area(3212, 3215, 3200, 3235) || Area(3215, 3220, 3202, 3235)
				|| Area(3220, 3227, 3202, 3229) || Area(3227, 3230, 3208, 3226)
				|| Area(3226, 3228, 3230, 3211) || Area(3227, 3229, 3208, 3226);
	}

	public boolean inBarrows() {
		if (absX > 3520 && absX < 3598 && absY > 9653 && absY < 9750) {
			return true;
		}
		return false;
	}

	public boolean inCastleWars() { // Cw
		if (absX > 2437 && absX < 2362 && absY > 3065 && absY < 3142) {
			return true;
		}
		return false;
	}

	public boolean inConstruction() {
		if (absX > 1851 && absX < 1987 && absY > 5044 && absY < 5125) {
			return true;
		}
		return false;
	}

	public boolean inCwLaddersRoom() {
		if (absX > 2390 && absX < 2409 && absY > 9495 && absY < 9512) {
			return true;
		}
		return false;
	}

	public boolean inCwZammyLaddersRoom() {
		if (absX > 2366 && absX < 2378 && absY > 9518 && absY < 9530) {
			return true;
		}
		return false;
	}

	public boolean inDominionTower() {
		if ((absX > 2316 && absX < 2341 && absY > 9914 && absY < 9798)
				|| (absX > 2341 && absX < 2306 && absY >= 9916 && absY < 9878)
				|| (absX > 2306 && absX < 2341 && absY >= 9878 && absY < 9916)
				|| (absX > 2341 && absX < 2316 && absY >= 9798 && absY < 9914)) {
			return true;
		}
		return false;
	}

	public boolean inDuelArena() {
		if ((absX > 3322 && absX < 3394 && absY > 3195 && absY < 3291)
				|| (absX > 3311 && absX < 3323 && absY > 3223 && absY < 3248)) {
			return true;
		}
		return false;
	}

	public boolean inDuelArena2() {
		return ((absX > 3322 && absX < 3394 && absY > 3195 && absY < 3291) || (absX > 3311
				&& absX < 3323 && absY > 3223 && absY < 3248));
	}

	public boolean InDung() {
		if (absX > 3198 && absX < 3262 && absY > 9273 && absY < 9344) {
			return true;
		}
		return false;
	}

	public boolean InDung2() {
		if (absX > 3198 && absX < 3273 && absY > 9273 && absY < 9347) {
			return true;
		}
		return false;
	}

	public boolean inDungBossRoom() {
		if (absX > 3007 && absX < 3041 && absY > 5213 && absY < 5253) {
			return true;
		}
		return false;
	}

	public boolean InDungv() {
		if (absX > 1854 && absX < 1922 && absY > 5179 && absY < 5248) {
			return true;
		}
		return false;
	}

	public boolean inEdge() {
		if (absX > 3040 && absX < 3143 && absY > 3462 && absY < 3538) {
			return true;
		}
		return false;
	}

	public boolean inFightCaves() {
		return absX >= 2360 && absX <= 2445 && absY >= 5045 && absY <= 5125;
	}

	public boolean inFM() {
		return absX >= 2901 && absX <= 2933 && absY >= 3460 && absY <= 3466;
	}

	public boolean inFunPk() {
		if (absX > 2582 && absX < 2602 && absY > 3152 && absY < 3171
				|| absX > 3085 && absX < 3098 && absY > 3468 && absY < 3482
				|| absX > 2601 && absX < 2607 && absY > 3156 && absY < 3171) {
			return true;
		}
		return false;
	}

	public boolean inGoblin() {
		if (absX > 2537 && absX < 3027 && absY > 2545 && absY < 3038) {
			return true;
		}
		return false;
	}

	public boolean inGWD() {
		if (absX > 2820 && absX < 2955 && absY > 5250 && absY < 5370) {
			return true;
		}
		return false;
	}

	public abstract void initialize();

	public boolean inJail() {
		if (absX > 3050 && absX < 3062 && absY > 4967 && absY < 4980) {
			return true;
		}
		return false;
	}

	public boolean inMulti() {
		if (isInBalanceEles())
			return true;
		if (isInArd() || inBarbDef || inCwGame == true) {
			return true;
		}
		if (atDemon()) {
			return true;
		}
		if (AtCorp()) {
			return true;
		}
		if (InDung() || InDungv() || inDung) {
			return true;
		}
		if ((absX >= 3136 && absX <= 3327 && absY >= 3519 && absY <= 3607)
				|| (absX >= 3460 && absX <= 3505 && absY >= 9480 && absY <= 9520)
				|| absX > 3343
				&& absX < 3384
				&& absY > 9619
				&& absY < 9660
				|| (absX >= 3210 && absX <= 3339 && absY >= 9333 && absY <= 9424)
				|| (absX >= 2607 && absX <= 2644 && absY >= 3296 && absY <= 3332)
				|| (absX >= 3359 && absX <= 3367 && absY >= 9636 && absY <= 9644)
				|| (absX >= 2949 && absX <= 3001 && absY >= 3370 && absY <= 3392)
				|| (absX >= 3250 && absX <= 3342 && absY >= 9800 && absY <= 9870)
				|| (absX >= 3190 && absX <= 3327 && absY >= 3648 && absY <= 3839)
				|| (absX >= 3200 && absX <= 3390 && absY >= 3840 && absY <= 3967)
				|| (absX >= 2992 && absX <= 3007 && absY >= 3912 && absY <= 3967)
				|| (absX >= 2946 && absX <= 2959 && absY >= 3816 && absY <= 3831)
				|| (absX >= 3008 && absX <= 3199 && absY >= 3856 && absY <= 3903)
				|| (absX >= 3008 && absX <= 3071 && absY >= 3600 && absY <= 3711)
				|| (absX >= 3072 && absX <= 3327 && absY >= 3608 && absY <= 3647)
				|| (absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619)
				|| (absX >= 2371 && absX <= 2422 && absY >= 5062 && absY <= 5117)
				|| (absX >= 2896 && absX <= 2927 && absY >= 3595 && absY <= 3630)
				|| (absX >= 2820 && absX <= 2955 && absY >= 5250 && absY <= 5370)
				|| (absX >= 2892 && absX <= 2932 && absY >= 4435 && absY <= 4464)
				|| (absX >= 2453 && absX <= 2476 && absY >= 4768 && absY <= 4790)
				|| (absX >= 2947 && absX <= 3002 && absY >= 9475 && absY <= 9528)
				|| (absX >= 2312 && absX <= 2332 && absY >= 3783 && absY <= 3801)
				|| (absX >= 2256 && absX <= 2287 && absY >= 4680 && absY <= 4711)
				|| (absX >= 2660 && absX <= 2690 && absY >= 3690 && absY <= 3735)
				|| (absX >= 2909 && absX <= 2939 && absY >= 5189 && absY <= 5220)
				|| (absX >= 2784 && absX <= 2802 && absY >= 9321 && absY <= 9341)
				|| (absX >= 2980 && absX <= 2993 && absY >= 9629 && absY <= 9644)) {
			return true;
		}
		return false;
	}

	public boolean inNomad() {
		if (absX > 2493 && absX < 3007 && absY > 2503 && absY < 3022) {
			return true;
		}
		return false;
	}

	public boolean inPcBoat() {
		return absX >= 2660 && absX <= 2663 && absY >= 2638 && absY <= 2643;
	}

	public boolean inPcGame() {
		return absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619;
	}

	public boolean inPirateHouse() {
		return absX >= 3038 && absX <= 3044 && absY >= 3949 && absY <= 3959;
	}

	public boolean inPits() {
		if (absX >= 2370 && absX <= 2425 && absY >= 5133 && absY <= 5165) {
			return true;
		}
		return false;
	}

	public boolean inRandomEvent() {
		if (heightLevel == playerId * 4 && absX > 2583 && absX < 2615
				&& absY > 4756 && absY < 4790) {
			return true;
		}
		return false;
	}

	public boolean inRFD() {
		return absX >= 1885 && absX <= 1913 && absY >= 5340 && absY <= 5369;
	}

	public boolean inRimmington() {
		if (absX > 2937 && absX < 3012 && absY > 3197 && absY < 3278) {
			return true;
		}
		return false;
	}

	public boolean inRing() {
		return (coordsCheck(1882, 1891, 5403, 5412));
	}

	public boolean inSarasWaitRoom() { // Cw
		if (absX > 2407 && absX < 2432 && absY > 9510 && absY < 9536) {
			return true;
		}
		return false;
	}

	public boolean insideDuelArena() {
		return absX > 3331 && absX < 3391 && absY > 3242 && absY < 3260;
	}

	public boolean inWarriorG() {
		return absX >= 2835 && absX <= 2877 && absY >= 3532 && absY <= 3559;
	}

	public boolean inWG() {
		return absX >= 2837 && absX <= 2847 && absY >= 3535 && absY <= 3543;
	}

	public boolean inWild() {
		if (isInFala() || inFunPk()) {
			return true;
		}
		if (ditchFix()) {
			return false;
		}
		if (absX > 2941 && absX < 3392 && absY > 3518 && absY < 3968
				|| absX > 2941 && absX < 3392 && absY > 9918 && absY < 10367) {
			return true;
		}
		return false;
	}

	public boolean inZammysWaitRoom() {
		if (absX > 2414 && absX < 2433 && absY > 9512 && absY < 9535) {
			return true;
		}
		return false;
	}

	public boolean isAdmin() {
		if (playerRights == 2) {
			return true;
		}
		return false;
	}

	public boolean isAppearanceUpdateRequired() {
		return appearanceUpdateRequired;
	}

	public boolean isAutoButton(int button) {
		for (int j = 0; j < autocastIds.length; j += 2) {
			if (autocastIds[j] == button)
				return true;
		}
		return false;
	}

	public boolean isChatTextUpdateRequired() {
		return chatTextUpdateRequired;
	}

	// Dying fix
	public boolean isDead2(Client c) {
		if (c.playerLevel[3] == 0 || c.playerLevel[3] < 0) {
			return true;
		}
		return false;
	}

	public boolean isDonator() {
		if (isDonator == 1 || isDonator == 2) {
			return true;
		}
		return false;
	}

	public boolean isExtreme() {
		if (isDonator == 3 || isDonator == 4) {
			return true;
		}
		return false;
	}

	public boolean isHitUpdateRequired() {
		return hitUpdateRequired;
	}

	public boolean isInArd() {
		if (absX > 3000 && absX < 3000 && absY > 3000 && absY < 3000) {
			return true;
		}
		return false;
	}

	public boolean isInBalanceEles() {
		return absX > 2784 && absX < 2802 && absY > 9321 && absY < 9341;
	}

	public boolean isInEdge() {
		if (absX > 3084 && absX < 3111 && absY > 3483 && absY < 3509) {
			return true;
		}
		return false;
	}

	public boolean isInFala() {
		if (absX > 3002 && absX < 3004 && absY > 3002 && absY < 3004) {
			return true;
		}
		return false;
	}

	public boolean isInGiantmole() {
		if (absX > 1733 && absX < 5133 && absY > 1787 && absY < 5244) {
			return true;
		}
		return false;
	}

	public boolean isInJail() {
		if (absX >= 3093 && absX <= 3112 && absY >= 9507 && absY <= 9526) {
			return true;
		}
		return false;
	}

	public boolean isInKq() {
		if (absX > 3465 && absX < 9481 && absY > 3508 && absY < 9518) {
			return true;
		}
		return false;
	}

	public boolean isInPbox() {
		if (absX > 3343 && absX < 3384 && absY > 9619 && absY < 9660) {
			return true;
		}
		return false;
	}

	public boolean isInPrivCon() {
		if (absX > 2049 && absX < 2098 && absY > 3131 && absY < 3266
				&& heightLevel > 2) {
			return true;
		}
		return false;
	}

	public boolean isInTut() {
		if (absX >= 2625 && absX <= 2687 && absY >= 4670 && absY <= 4735) {
			return true;
		}
		return false;
	}

	public boolean isMod() {
		if (playerRights == 1) {
			return true;
		}
		return false;
	}

	public boolean isNewWalkCmdIsRunning() {
		return newWalkCmdIsRunning;
	}

	public boolean isOwner() {
		if (playerRights == 3) {
			return true;
		}
		return false;
	}

	/*
	 * public void postProcessing() { if (newWalkCmdSteps > 0) { int firstX =
	 * getNewWalkCmdX()[0], firstY = getNewWalkCmdY()[0];
	 * 
	 * int lastDir = 0; boolean found = false; numTravelBackSteps = 0; int ptr =
	 * wQueueReadPtr; int dir = Misc.direction(currentX, currentY, firstX,
	 * firstY); if (dir != -1 && (dir & 1) != 0) { do { lastDir = dir; if (--ptr
	 * < 0) ptr = walkingQueueSize - 1;
	 * 
	 * travelBackX[numTravelBackSteps] = walkingQueueX[ptr];
	 * travelBackY[numTravelBackSteps++] = walkingQueueY[ptr]; dir =
	 * Misc.direction(walkingQueueX[ptr], walkingQueueY[ptr], firstX, firstY);
	 * if (lastDir != dir) { found = true; break; }
	 * 
	 * } while (ptr != wQueueWritePtr); } else found = true;
	 * 
	 * if (!found)
	 * println_debug("Fatal: couldn't find connection vertex! Dropping packet."
	 * ); else { wQueueWritePtr = wQueueReadPtr;
	 * 
	 * addToWalkingQueue(currentX, currentY);
	 * 
	 * if (dir != -1 && (dir & 1) != 0) {
	 * 
	 * for (int i = 0; i < numTravelBackSteps - 1; i++) {
	 * addToWalkingQueue(travelBackX[i], travelBackY[i]); } int wayPointX2 =
	 * travelBackX[numTravelBackSteps - 1], wayPointY2 =
	 * travelBackY[numTravelBackSteps - 1]; int wayPointX1, wayPointY1; if
	 * (numTravelBackSteps == 1) { wayPointX1 = currentX; wayPointY1 = currentY;
	 * } else { wayPointX1 = travelBackX[numTravelBackSteps - 2]; wayPointY1 =
	 * travelBackY[numTravelBackSteps - 2]; }
	 * 
	 * dir = Misc.direction(wayPointX1, wayPointY1, wayPointX2, wayPointY2); if
	 * (dir == -1 || (dir & 1) != 0) {
	 * println_debug("Fatal: The walking queue is corrupt! wp1=(" + wayPointX1 +
	 * ", " + wayPointY1 + "), " + "wp2=(" + wayPointX2 + ", " + wayPointY2 +
	 * ")"); } else { dir >>= 1; found = false; int x = wayPointX1, y =
	 * wayPointY1; while (x != wayPointX2 || y != wayPointY2) { x +=
	 * Misc.directionDeltaX[dir]; y += Misc.directionDeltaY[dir]; if
	 * ((Misc.direction(x, y, firstX, firstY) & 1) == 0) { found = true; break;
	 * } } if (!found) {
	 * println_debug("Fatal: Internal error: unable to determine connection vertex!"
	 * + "  wp1=(" + wayPointX1 + ", " + wayPointY1 + "), wp2=(" + wayPointX2 +
	 * ", " + wayPointY2 + "), " + "first=(" + firstX + ", " + firstY + ")"); }
	 * else addToWalkingQueue(wayPointX1, wayPointY1); } } else { for (int i =
	 * 0; i < numTravelBackSteps; i++) { addToWalkingQueue(travelBackX[i],
	 * travelBackY[i]); } }
	 * 
	 * for (int i = 0; i < newWalkCmdSteps; i++) {
	 * addToWalkingQueue(getNewWalkCmdX()[i], getNewWalkCmdY()[i]); }
	 * 
	 * }
	 * 
	 * isRunning = isNewWalkCmdIsRunning() || isRunning2; } }
	 */

	public boolean isSponsor() {
		if (isDonator == 5 || isDonator == 6 || isDonator == 4) {
			return true;
		}
		return false;
	}

	public int playersInBoat() {
		int count = 0;
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].inPcBoat()) {
					count++;
				}
			}
		}
		return count;
	}

	public void postProcessing() {
		if (newWalkCmdSteps > 0) {
			int firstX = getNewWalkCmdX()[0], firstY = getNewWalkCmdY()[0];

			int lastDir = 0;
			boolean found = false;
			numTravelBackSteps = 0;
			int ptr = wQueueReadPtr;
			int dir = Misc.direction(currentX, currentY, firstX, firstY);
			if (dir != -1 && (dir & 1) != 0) {
				do {
					lastDir = dir;
					if (--ptr < 0)
						ptr = walkingQueueSize - 1;

					travelBackX[numTravelBackSteps] = walkingQueueX[ptr];
					travelBackY[numTravelBackSteps++] = walkingQueueY[ptr];
					dir = Misc.direction(walkingQueueX[ptr],
							walkingQueueY[ptr], firstX, firstY);
					if (lastDir != dir) {
						found = true;
						break;
					}

				} while (ptr != wQueueWritePtr);
			} else
				found = true;

			if (!found)
				println_debug("Walking error (Fatal)");
			else {
				wQueueWritePtr = wQueueReadPtr;

				addToWalkingQueue(currentX, currentY);

				if (dir != -1 && (dir & 1) != 0) {

					for (int i = 0; i < numTravelBackSteps - 1; i++) {
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
					int wayPointX2 = travelBackX[numTravelBackSteps - 1], wayPointY2 = travelBackY[numTravelBackSteps - 1];
					int wayPointX1, wayPointY1;
					if (numTravelBackSteps == 1) {
						wayPointX1 = currentX;
						wayPointY1 = currentY;
					} else {
						wayPointX1 = travelBackX[numTravelBackSteps - 2];
						wayPointY1 = travelBackY[numTravelBackSteps - 2];
					}

					dir = Misc.direction(wayPointX1, wayPointY1, wayPointX2,
							wayPointY2);
					if (dir == -1 || (dir & 1) != 0) {
						// println_debug("Fatal: The walking queue is corrupt! wp1=("+wayPointX1+", "+wayPointY1+"), "+
						// "wp2=("+wayPointX2+", "+wayPointY2+")");
					} else {
						dir >>= 1;
						found = false;
						int x = wayPointX1, y = wayPointY1;
						while (x != wayPointX2 || y != wayPointY2) {
							x += Misc.directionDeltaX[dir];
							y += Misc.directionDeltaY[dir];
							if ((Misc.direction(x, y, firstX, firstY) & 1) == 0) {
								found = true;
								break;
							}
						}
						if (!found) {
							// println_debug("Fatal: Internal error: unable to determine connection vertex!"+
							// " wp1=("+wayPointX1+", "+wayPointY1+"), wp2=("+wayPointX2+", "+wayPointY2+"), "+
							// "first=("+firstX+", "+firstY+")");
						} else
							addToWalkingQueue(wayPointX1, wayPointY1);
					}
				} else {
					for (int i = 0; i < numTravelBackSteps; i++) {
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
				}

				for (int i = 0; i < newWalkCmdSteps; i++) {
					addToWalkingQueue(getNewWalkCmdX()[i], getNewWalkCmdY()[i]);
				}

			}

			isRunning = isNewWalkCmdIsRunning() || isRunning2;
		}
	}

	public void preProcessing() {
		newWalkCmdSteps = 0;
	}

	public void println(String str) {
		System.out.println("[player-" + playerId + "]: " + str);
	}

	public void println_debug(String str) {
		System.out.println("[player-" + playerId + "]: "
				+ Misc.optimizeText(playerName) + " - " + str);
	}

	public abstract void process();

	public abstract boolean processQueuedPackets();

	public void putInCombat(int attacker) {
		underAttackBy = attacker;
		logoutDelay = System.currentTimeMillis();
		SpecialDelay = System.currentTimeMillis();
		singleCombatDelay = System.currentTimeMillis();
	}

	public void resetWalkingQueue() {
		wQueueReadPtr = wQueueWritePtr = 0;

		for (int i = 0; i < walkingQueueSize; i++) {
			walkingQueueX[i] = currentX;
			walkingQueueY[i] = currentY;
		}
	}

	public boolean safeZone() {
		if (absX > 2612 && absX < 2622 && absY > 3330 && absY < 3335
				|| absX > 2601 && absX < 2620 && absY > 3140 && absY < 3157
				|| absX > 2648 && absX < 2658 && absY > 3279 && absY < 3287
				|| absX > 2520 && absX < 2526 && absY > 4774 && absY < 4780) {
			return true;
		}
		return false;
	}

	public boolean samePlayer() {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (j == playerId)
				continue;
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].playerName
						.equalsIgnoreCase(playerName)) {
					disconnected = true;
					return true;
				}
			}
		}
		return false;
	}

	public void setAppearanceUpdateRequired(boolean appearanceUpdateRequired) {
		this.appearanceUpdateRequired = appearanceUpdateRequired;
	}

	public void setChatText(byte chatText[]) {
		this.chatText = chatText;
	}

	public void setChatTextColor(int chatTextColor) {
		this.chatTextColor = chatTextColor;
	}

	public void setChatTextEffects(int chatTextEffects) {
		this.chatTextEffects = chatTextEffects;
	}

	public void setChatTextSize(byte chatTextSize) {
		this.chatTextSize = chatTextSize;
	}

	public void setChatTextUpdateRequired(boolean chatTextUpdateRequired) {
		this.chatTextUpdateRequired = chatTextUpdateRequired;
	}

	public void setHitDiff(int hitDiff) {
		this.hitDiff = hitDiff;
	}

	public void setHitDiff2(int hitDiff2) {
		this.hitDiff2 = hitDiff2;
	}

	public void setHitUpdateRequired(boolean hitUpdateRequired) {
		this.hitUpdateRequired = hitUpdateRequired;
	}

	public void setHitUpdateRequired2(boolean hitUpdateRequired2) {
		this.hitUpdateRequired2 = hitUpdateRequired2;
	}

	public void setInStreamDecryption(ISAACCipher inStreamDecryption) {
	}

	public void setLastClicked(int lastClicked) {
		this.lastClicked = lastClicked;
	}

	public void setNewWalkCmdIsRunning(boolean newWalkCmdIsRunning) {
		this.newWalkCmdIsRunning = newWalkCmdIsRunning;
	}

	public void setNewWalkCmdX(int newWalkCmdX[]) {
		this.newWalkCmdX = newWalkCmdX;
	}

	public void setNewWalkCmdY(int newWalkCmdY[]) {
		this.newWalkCmdY = newWalkCmdY;
	}

	public void setOutStreamDecryption(ISAACCipher outStreamDecryption) {
	}

	/**
	 * Animations
	 **/
	/*
	 * public boolean UseDefender() { switch(playerEquipment[playerWeapon]) {
	 * case 8844: case 8845: case 8846: case 8847: case 8848: case 8849: case
	 * 8850: break; } return true; }
	 */
	public void startAnimation(int animId) {
		/*
		 * if (wearing2h() && isMoving && animId == 829) return; if
		 * (UseDefender() && isMoving && animId == 4177) return;
		 */
		animationRequest = animId;
		animationWaitCycles = 0;
		updateRequired = true;
	}

	public void startAnimation(int animId, int time) {
		animationRequest = animId;
		animationWaitCycles = time;
		updateRequired = true;
	}

	public void stopMovement() {
		if (teleportToX <= 0 && teleportToY <= 0) {
			teleportToX = absX;
			teleportToY = absY;
		}
		newWalkCmdSteps = 0;
		getNewWalkCmdX()[0] = getNewWalkCmdY()[0] = travelBackX[0] = travelBackY[0] = 0;
		getNextPlayerMovement();
	}

	public boolean torva() {
		if (playerEquipment[playerHat] == 13362
				&& playerEquipment[playerLegs] == 13360
				&& playerEquipment[playerChest] == 13358) {
			return true;
		}
		return false;
	}

	public void turnPlayerTo(int pointX, int pointY) {
		FocusPointX = 2 * pointX + 1;
		FocusPointY = 2 * pointY + 1;
		updateRequired = true;
	}

	public abstract void update();

	public void updatePlayerMovement(Stream str) {
		// (synchronized(this) {
		if (dir1 == -1) {
			if (updateRequired || isChatTextUpdateRequired()) {

				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else
				str.writeBits(1, 0);
		} else if (dir2 == -1) {

			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
			str.writeBits(1, (updateRequired || isChatTextUpdateRequired()) ? 1
					: 0);
		} else {

			str.writeBits(1, 1);
			str.writeBits(2, 2);
			str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
			str.writeBits(3, Misc.xlateDirectionToClient[dir2]);
			str.writeBits(1, (updateRequired || isChatTextUpdateRequired()) ? 1
					: 0);
		}
		// }
	}

	public void updateshop(int i) {
		Client p = (Client) PlayerHandler.players[playerId];
		p.getShops().resetShop(i);
	}

	public void updateThisPlayerMovement(Stream str) {
		// synchronized(this) {
		if (mapRegionDidChange) {
			str.createFrame(73);
			str.writeWordA(mapRegionX + 6);
			str.writeWord(mapRegionY + 6);
		}

		if (didTeleport) {
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);
			str.writeBits(2, 3);
			str.writeBits(2, heightLevel);
			str.writeBits(1, 1);
			str.writeBits(1, (updateRequired) ? 1 : 0);
			str.writeBits(7, currentY);
			str.writeBits(7, currentX);
			return;
		}

		if (dir1 == -1) {
			// don't have to update the character position, because we're just
			// standing
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			isMoving = false;
			if (updateRequired) {
				// tell client there's an update block appended at the end
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
			if (DirectionCount < 50) {
				DirectionCount++;
			}
		} else {
			DirectionCount = 0;
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);

			if (dir2 == -1) {
				isMoving = true;
				str.writeBits(2, 1);
				str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
				if (updateRequired)
					str.writeBits(1, 1);
				else
					str.writeBits(1, 0);
			} else {
				isMoving = true;
				str.writeBits(2, 2);
				str.writeBits(3, Misc.xlateDirectionToClient[dir1]);
				str.writeBits(3, Misc.xlateDirectionToClient[dir2]);
				if (updateRequired)
					str.writeBits(1, 1);
				else
					str.writeBits(1, 0);
			}
		}

	}

	public abstract void updateWalkEntities();

	public boolean wearing2h() {
		Client c = (Client) this;
		String s = c.getItems().getItemName(
				c.playerEquipment[Player.playerWeapon]);
		if (s.contains("2h"))
			return true;
		else if (s.contains("godsword"))
			return true;
		return false;
	}

	public boolean wearingBrawlers() {
		Client c = (Client) this;
		if (c.brawlerscook == 150 || c.brawlerscook > 150) {
			c.getItems().sendWeapon(
					c.playerEquipment[Player.playerHands],
					c.getItems().getItemName(
							c.playerEquipment[Player.playerHands]));
			c.getCombat().getPlayerAnimIndex(
					c.getItems()
							.getItemName(c.playerEquipment[Player.playerHands])
							.toLowerCase());
			c.getItems().resetBonus();
			c.getOutStream().createFrame(34);
			c.getOutStream().writeWord(6);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(Player.playerHands);
			c.getOutStream().writeWord(0);
			c.getOutStream().writeByte(0);
			c.getItems().resetBonus();
			c.getItems().getBonus();
			c.getItems().writeBonus();
			c.getPA().requestUpdates();
			c.setAppearanceUpdateRequired(true);
			c.sendMessage("Your brawlers degrade..");
			c.brawlerscook = 0;
			playerEquipment[playerHands] = -1;
			c.SaveGame();
			return false;
		}
		String s = c.getItems().getItemName(
				c.playerEquipment[Player.playerHands]);
		if (s.contains("cooking"))
			return true;
		return false;
	}

	public boolean wearingFMBrawlers() {
		Client c = (Client) this;
		if (c.brawlerFM == 150 || c.brawlerFM > 150) {
			c.getItems().sendWeapon(
					c.playerEquipment[Player.playerHands],
					c.getItems().getItemName(
							c.playerEquipment[Player.playerHands]));
			c.getCombat().getPlayerAnimIndex(
					c.getItems()
							.getItemName(c.playerEquipment[Player.playerHands])
							.toLowerCase());
			c.getItems().resetBonus();
			c.getOutStream().createFrame(34);
			c.getOutStream().writeWord(6);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(Player.playerHands);
			c.getOutStream().writeWord(0);
			c.getOutStream().writeByte(0);
			c.getItems().resetBonus();
			c.getItems().getBonus();
			c.getItems().writeBonus();
			c.getPA().requestUpdates();
			c.setAppearanceUpdateRequired(true);
			c.sendMessage("Your brawlers degrade..");
			c.brawlerFM = 0;
			playerEquipment[playerHands] = -1;
			c.SaveGame();
			return false;
		}
		String s = c.getItems().getItemName(
				c.playerEquipment[Player.playerHands]);
		if (s.contains("fm"))
			return true;
		return false;
	}

	public boolean withinDistance(int absX, int getY, int getHeightLevel) {
		if (this.getHeightLevel() != getHeightLevel)
			return false;
		int deltaX = this.getX() - absX, deltaY = this.getY() - getY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public boolean withinDistance(NPC npc) {
		if (heightLevel != npc.heightLevel)
			return false;
		if (npc.needRespawn == true)
			return false;
		int deltaX = npc.absX - absX, deltaY = npc.absY - absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public boolean withinDistance(Player otherPlr) {
		if (heightLevel != otherPlr.heightLevel)
			return false;
		int deltaX = otherPlr.absX - absX, deltaY = otherPlr.absY - absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public boolean WithinDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX + i) == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if ((objectX - i) == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectX == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean withinDungInviteRange() {
		if (absX > 3069 && absX < 3404 && absY > 3404 && absY < 3455) {
			return true;
		}
		return false;
	}

	public boolean withinInteractionDistance(int x, int y, int z) {
		if (heightLevel != z)
			return false;
		Client c = (Client) this;
		int deltaX = x - c.getX(), deltaY = y - c.getY();
		return deltaX <= 4 && deltaX >= -4 && deltaY <= 4 && deltaY >= -4;
	}

	public boolean zaryteBow() {
		return playerEquipment[playerHands] == 20171;
	}

	public int getPlayerGod() {
		return playerGod;
	}

	public void setPlayerGod(int god) {
		this.playerGod = god;
	}

	public String getGod() {
		switch (playerGod) {
		case 0:// Zammy
			return "Zamorak";
		case 1:// Arma
			return "Armadyl";
		case 2:// Bandos
			return "Bandos";
		case 3:// Nex
			return "Nex";
		case 4:// Sara
			return "Saradomin";
		case 5:// guthix
			return "Guthix";
		default:
			return "none";
		}
	}

	public boolean isStaff() {
		return playerRights > 0 && playerRights < 4;
	}

	public String getPlayerTitle() {
		switch (playerRights) {
		case 5:
			return "<col=FF0000><shad=0>[Helper]";
		case 4:
			return "<col=D9D919><shad=0>[Donator]";
		case 3:
			return "<shad=15733302><shad=0>[Owner]";
		case 2:
			return "<col=FFFF64><shad=0>[Admin]";
		case 1:
			return "<col=00FFFF><shad=0>[Mod]";
		default:
			return "<col=FF0000><shad=0>[Player]";
		}
	}
	
	public String getYellTitle() {
		switch (playerRights) {
		case 0:
		default:
			if (getPlayerGod() > -1)
				return "<shad=6081134>[" + getGod() + "]";
			else
				return "<shad=6081134>[Godless]";
		case 1:
			return "<col=20B2AA><shad=0>[Moderator]";
		case 2:
			return "<col=FFFF64><shad=0>[Admin]";
		case 3:
			return "<col=00FFFF><shad=0>[Owner]";
		case 4:
			if (getPlayerGod() > -1)
				return "<col=D9D919><shad=0>[" + getGod() + "]";
			else
				return "<col=D9D919><shad=0>[Donator]";
		}
	}
}