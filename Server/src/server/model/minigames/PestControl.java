package server.model.minigames;

import server.Server;
import server.model.items.ItemAssistant;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.PathFinder;
import server.model.players.Player;
import server.model.players.PlayerAssistant;
import server.model.players.PlayerHandler;

/**
 * @author Ruuhkis
 */

/*
 * add id variable and putting it to slot in array fix getnpcmvoement add
 * spawncheck fix portal following n attacking splatter exploding distance
 * method in npchandler fix player dealdamage method add splatter dead emote :D
 * add braweler attack emote ^^ remove pc random walking RAVAGER(3742, 3746),
 * portal giving health to void knight npc try variable static process and
 * processing every pc thru serba fix doors
 */

public class PestControl {

	public enum PestControlNPC {
		SPINNER(3747, 3751), SPLATTER(3727, 3731), SHIFTER(3732, 3741), TORCHER(
				3752, 3761), DEFILER(3762, 3771), BRAWLER(3772, 3776);

		private final int lowestNPCID, highestNPCID;

		PestControlNPC(int lowestNPCID, int highestNPCID) {
			this.lowestNPCID = lowestNPCID;
			this.highestNPCID = highestNPCID;
		}

		public int getHighestNPCID() {
			return highestNPCID;
		}

		public int getLowestNPCID() {
			return lowestNPCID;
		}

	}

	public static PestControl[] runningGames = new PestControl[1];

	public static void giveReward(Client c, boolean item, int id, int amount,
			int cost) {
		if (c.Commendations < cost) {
			c.sendMessage("You don't have " + cost
					+ " commendations to purchase this.");
			return;
		}
		if (!item) {
			c.Commendations -= cost;
			c.getPA().addSkillXP(amount * cost, id);
			c.sendMessage("You recieve " + (amount * cost) + " XP in "
					+ PlayerAssistant.getSkillName(id) + ".");
		} else {
			int id2 = 0;
			if (id > 19784 && id < 19787) {
				if (id == 19785)
					id2 = 8839;
				if (id == 19786)
					id2 = 8840;
				if (c.getItems().playerHasItem(id2))
					c.getItems().deleteItem(id2, 1);
				else {
					c.sendMessage("You need a " + ItemAssistant.getItemName(id2)
							+ " for this.");
					return;
				}
			}
			if (c.getItems().freeSlots() > 0 || id2 != 0) {
				c.Commendations -= cost;
				c.getItems().addItem(id, amount);
				c.sendMessage("You receive a " + ItemAssistant.getItemName(id)
						+ ".");
			} else
				c.sendMessage("Your inventory is full.");
		}
		c.getPA().sendFrame126(Integer.toString(c.Commendations), 18729);
	}

	public static void staticProcess() {
		setBoatInterface();
		if (waitTimer > 0 && playersInBoat() > 1) {
			waitTimer--;

		}
		if (waitTimer <= 0) {
			int id = -1;
			for (int i = 0; i < runningGames.length; i++) {
				if (runningGames[i] == null) {
					id = i;
					break;
				}
			}
			if (id != -1) {
				PestControl pc = new PestControl(id);
				runningGames[id] = pc;
				pc.startGame();
			} else {
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						if (PlayerHandler.players[j].inPcBoat()) {
							Client c = (Client) PlayerHandler.players[j];
							c.sendMessage("Theres already game running!");
						}
					}
				}
			}
			waitTimer = WAIT_TIMER;
		}
	}

	private int id;

	public final int GAME_TIMER = 300; // 5 minutes
	public final static int WAIT_TIMER = 40;

	public int gameTimer = -1;
	public static int waitTimer = WAIT_TIMER;

	public static boolean gameExists(int id) {
		for (PestControl pc : runningGames) {
			if (pc.getId() == id)
				return true;
		}
		return false;
	}

	public static boolean isPCNPC(int npcType) {
		for (PestControlNPC type : PestControlNPC.values()) {
			if (npcType >= type.getLowestNPCID()
					&& npcType <= type.getHighestNPCID()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPCNPC(int npcType, PestControlNPC type) {
		return (npcType >= type.getLowestNPCID() && npcType <= type
				.getHighestNPCID());
	}

	public static int playersInBoat() {
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

	public static void setBoatInterface() {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].inPcBoat()) {
					Client c = (Client) PlayerHandler.players[j];

					// c.getPA().sendFrame126("Not Enough Players.", 21120);
					c.getPA().walkableInterface(21005);
					c.getPA().sendFrame126("Next Departure: " + waitTimer + "",
							21006);
					c.getPA().sendFrame126(
							"Players Ready: " + playersInBoat() + "", 21007);
					c.getPA().sendFrame126("(Need 3 to 25 players)", 21008);
					c.getPA().sendFrame126(
							"Commendations: " + c.Commendations + "", 21009);
				}
			}
		}
	}

	public int properTimer = 0;

	private NPC[] portals = new NPC[4];

	private NPC knight;

	@SuppressWarnings("unused")
	private int[][] SkillRewardXP = { { 0, 3500 }, { 10034, 7000 } };

	public PestControl(int id) {
		this.id = id;
	}

	public boolean allPortalsDead() {
		int count = 0;
		for (int j = 0; j < NPCHandler.npcs.length; j++) {
			if (NPCHandler.npcs[j] != null) {
				if (NPCHandler.npcs[j].npcType >= 6142
						&& NPCHandler.npcs[j].npcType <= 6145
						&& NPCHandler.npcs[j].heightLevel == id * 4)
					if (NPCHandler.npcs[j].needRespawn)
						count++;
			}
		}
		return count >= 4;
	}

	private boolean attack(NPC npc, NPC knight, int anim, int maxhit) {
		npc.turnNpc(knight.absX, knight.absY);
		if (npc.attackTimer == 0) {
			int damage = ((int) (Math.random() * maxhit));
			npc.animNumber = anim;
			knight.hitDiff2 = damage;
			knight.HP -= damage;
			if (knight.HP < 0) {
				knight.HP = 0;
			}
			knight.hitUpdateRequired = true;
			knight.updateRequired = true;
			npc.animUpdateRequired = true;
			npc.updateRequired = true;
			npc.attackTimer = 4;
			return true;
		}
		return false;
	}

	private int distance(int x, int y, int dx, int dy) {
		int xdiff = x - dx;
		int ydiff = y - dy;
		return (int) Math.sqrt(xdiff * xdiff + ydiff * ydiff);
	}

	public void endGame(boolean won) {
		gameTimer = -1;
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].inPcGame()
						&& PlayerHandler.players[j].heightLevel == id * 4) {
					Client c = (Client) PlayerHandler.players[j];
					c.getPA().movePlayer(2657, 2639, 0);
					if (won && c.pcDamage >= 0) {
						c.sendMessage("You have won the pest control game and have been awarded 30 Commendations.");
						c.Commendations += 30;
						c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
						c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
						c.specAmount = 10;
						c.pcGame += 1;
						if (!c.task2[7] && c.pcGame >= 10) {
							c.task2[7] = true;
							c.sendMessage("You've completed the task: Complete 10 PC Games!");
							c.TPoints += 2;
							c.achievementInterface("Complete 10 PC Games!");
							c.sendMessage("You've received two Task Points! You now have a total of "
									+ c.TPoints + " points!");
						}
						c.getItems().addItem(995, c.combatLevel * 100);
						c.getPA().refreshSkill(3);
						c.getPA().refreshSkill(5);
					} else if (won) {
						c.sendMessage("You didn't participate enough to receive a reward.");
					} else {
						c.sendMessage("You failed to kill all the portals in time.");
					}
					c.pcDamage = 0;
					c.getItems().addSpecialBar(
							c.playerEquipment[Player.playerWeapon]);
					// c.getCombat().resetPrayers();
				}
			}
		}

		for (int j = 0; j < NPCHandler.npcs.length; j++) {
			if (NPCHandler.npcs[j] != null
					&& NPCHandler.npcs[j].heightLevel == id * 4) {
				if (inPcGame(NPCHandler.npcs[j].absX, NPCHandler.npcs[j].absY)) {
					NPCHandler.npcs[j].isDead = true;
					NPCHandler.npcs[j].HP = 0;
					NPCHandler.npcs[j] = null;
				}
			}
		}
		for (int i = 0; i < portals.length; i++) {
			if (portals[i] != null) {
				portals[i].isDead = true;
				portals[i] = null;
			}
		}
		if (knight != null) {
			knight.isDead = true;
			knight = null;
		}
	}

	public int getId() {
		return id;
	}

	public NPC getKnight() {
		return knight;
	}

	private String getPortalText(int i) {
		return (portals[i] != null && (portals[i].HP > 0 && !portals[i].isDead)) ? Integer
				.toString(portals[i].HP) : "Dead";
	}

	public void handleNPCs() {

	}

	private boolean inPcGame(int absX, int absY) {
		return absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619;
	}

	private boolean isFree(NPC npc) {
		if (PlayerHandler.players[npc.killerId] == null) {
			return true;
		} else {
			if (PlayerHandler.players[npc.killerId].killerId == npc.npcId) {
				return false;
			} else {
				if (npc.tries++ >= 12) {
					npc.tries = 0;
					npc.killerId = 0;
					return true;
				} else {
					return false;
				}
			}
		}
	}

	public void movePlayer(int index) {
		Client c = (Client) PlayerHandler.players[index];
		if (c.combatLevel < 30) {
			c.sendMessage("You must be at least 30 to enter this boat.");
			return;
		}
		c.getPA().movePlayer(2658, 2611, id * 4);
		gameTimer = 300;
	}

	public void process() {
		setInterface();
		processNPCs();
		if (Math.random() < 0.1) {
			spawnRandomNPC();
		}
		if (properTimer > 0) {
			properTimer--;
			return;
		} else {
			properTimer = 4;
		}
		if (gameTimer > 0) {
			if ((knight != null && (knight.HP == 0 || knight.isDead))) {
				endGame(false);
			}
			gameTimer--;
			if (allPortalsDead()) {
				endGame(true);
			}
		} else if (gameTimer == 0)
			endGame(false);
	}

	private void processBrawler(NPC npc) {
		int lowest = Integer.MAX_VALUE;
		int playerId = 0;
		Player player = null;
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				int lowestCandidate = distance(npc.absX, npc.absY,
						PlayerHandler.players[j].absX,
						PlayerHandler.players[j].absY);
				if (lowestCandidate < lowest) {
					lowest = lowestCandidate;
					player = PlayerHandler.players[j];
					playerId = j;
				}
			}
		}
		if (player != null) {
			npc.killerId = playerId;
		}
	}

	private void processDefiler(NPC npc) {

		if (isFree(npc)) {
			if (distance(npc.absX, npc.absY, knight.absX, knight.absY) > 20) {
				npc.moveX = NPCHandler.GetMove(npc.absX, knight.absX);
				npc.moveY = NPCHandler.GetMove(npc.absY, knight.absY);
				PathFinder.getPathFinder();
				if (PathFinder.canMove(npc.absX, npc.absY, npc.heightLevel,
						npc.moveX, npc.moveY)) {
					npc.getNextNPCMovement(npc.npcId);
					npc.updateRequired = true;
				}
			} else {
				attack(npc, knight, 3920, 5);
			}
		}
	}

	private void processNPCs() {
		for (int i = 0; i < NPCHandler.npcs.length; i++) {
			NPC npc = NPCHandler.npcs[i];
			if (npc != null && inPcGame(npc.absX, npc.absY) && !npc.isDead
					&& npc.heightLevel == id * 4) {
				for (PestControlNPC PCNPC : PestControlNPC.values()) {
					if (npc.npcType >= PCNPC.lowestNPCID
							&& npc.npcType <= PCNPC.highestNPCID) {
						processPCNPC(npc, PCNPC);
						break;
					}
				}
			}
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void processPCNPC(NPC npc, PestControlNPC PCNPC) {
		if (knight == null || npc == null) {
			return;
		}
		switch (PCNPC) {
		case SPINNER:
			processSpinner(npc);
			break;
		case SHIFTER:
			processShifter(npc);
			break;
		case BRAWLER:
			processBrawler(npc);
			break;
		case TORCHER:
			processTorcher(npc);
			break;
		case DEFILER:
			processDefiler(npc);
			break;
		}
	}

	private void processShifter(NPC npc) {
		if (npc == null) {
			return;
		}
		if (npc != null) {
			if (isFree(npc)) {
				if (distance(npc.absX, npc.absY, knight.absX, knight.absY) > 8) {
					npc.absX = npc.absY = 0;
					npc.isDead = true;
					npc.applyDead = true;
					npc.updateRequired = true;
					npc.dirUpdateRequired = true;
					npc.getNextWalkingDirection();
					Server.npcHandler.spawnNpc77(npc.npcType,
							2654 + ((int) (Math.random() * 5)),
							2595 - ((int) (Math.random() * 5)), id * 4, 0, 200,
							0, 0, 100).gfx100(1315);
				} else {
					if (distance(npc.absX, npc.absY, knight.absX, knight.absY) > 1) {
						npc.moveX = NPCHandler.GetMove(npc.absX, knight.absX);
						npc.moveY = NPCHandler.GetMove(npc.absY, knight.absY);
						PathFinder.getPathFinder();
						if (PathFinder.canMove(npc.absX, npc.absY,
								npc.heightLevel, npc.moveX, npc.moveY)) {
							npc.getNextNPCMovement(npc.npcId);
							npc.updateRequired = true;
						}
					} else {
						npc.killerId = 0;
						attack(npc, knight, 3901, 5);
					}
				}
			}
		}
	}

	private void processSpinner(NPC npc) {
		NPC closestPortal = null;
		int distance = Integer.MAX_VALUE;
		for (int i = 0; i < portals.length; i++) {
			if (portals[i] != null && !portals[i].isDead && portals[i].HP > 0) {
				int distanceCandidate = distance(npc.absX, npc.absY,
						portals[i].absX, portals[i].absY);
				if (distanceCandidate < distance) {
					closestPortal = portals[i];
					distance = distanceCandidate;
				}
			}
		}
		if (distance < 2 && closestPortal.HP < closestPortal.MaxHP) {
			closestPortal.HP += 2;
			// TODO make lvl of spinner to change this :P
			if (closestPortal.HP > closestPortal.MaxHP)
				closestPortal.HP = closestPortal.MaxHP;
			npc.animNumber = 3911;
			npc.turnNpc(closestPortal.absX, closestPortal.absY);
			npc.animUpdateRequired = true;
			npc.updateRequired = true;
		} else if (closestPortal != null) {
			npc.moveX = NPCHandler.GetMove(npc.absX, closestPortal.absX);
			npc.moveY = NPCHandler.GetMove(npc.absY, closestPortal.absY);
			PathFinder.getPathFinder();
			if (PathFinder.canMove(npc.absX, npc.absY, npc.heightLevel,
					npc.moveX, npc.moveY)) {
				npc.getNextNPCMovement(npc.npcId);
				npc.updateRequired = true;
			}
			return;
		}
	}

	private void processTorcher(NPC npc) {
		if (isFree(npc)) {
			if (distance(npc.absX, npc.absY, knight.absX, knight.absY) > 20) {
				npc.moveX = NPCHandler.GetMove(npc.absX, knight.absX);
				npc.moveY = NPCHandler.GetMove(npc.absY, knight.absY);
				PathFinder.getPathFinder();
				if (PathFinder.canMove(npc.absX, npc.absY, npc.heightLevel,
						npc.moveX, npc.moveY)) {
					npc.getNextNPCMovement(npc.npcId);
					npc.updateRequired = true;
				}
			} else {
				attack(npc, knight, 3882, 5);
			}
		}
	}

	public void setInterface() {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (PlayerHandler.players[j].inPcGame()
						&& PlayerHandler.players[j].heightLevel == id * 4) {
					Client c = (Client) PlayerHandler.players[j];
					c.getPA().walkableInterface(21100);
					c.getPA().sendFrame126(getPortalText(0), 21111);
					c.getPA().sendFrame126(getPortalText(1), 21112);
					c.getPA().sendFrame126(getPortalText(2), 21113);
					c.getPA().sendFrame126(getPortalText(3), 21114);
					c.getPA()
							.sendFrame126(
									knight != null && knight.HP > 0 ? Integer.toString(knight.HP)
											: "Dead", 21115);
					c.getPA().sendFrame126("Damage :" + c.pcDamage + "", 21116);
					c.getPA().sendFrame126("Time remaining: " + gameTimer + "",
							21117);
				}
			}
		}
	}

	public void spawnNpcs() {
		// System.out.println("SPAWNING FUCKFACES!!");
		knight = Server.npcHandler.spawnNpc77(3782, 2656, 2592, id * 4, 0,
				1200, 0, 0, 100);
		portals[0] = Server.npcHandler.spawnNpc77(6142, 2628, 2591, id * 4, 0,
				200, 0, 0, 100); // purple
		portals[1] = Server.npcHandler.spawnNpc77(6143, 2680, 2588, id * 4, 0,
				200, 0, 0, 100);
		portals[2] = Server.npcHandler.spawnNpc77(6144, 2669, 2570, id * 4, 0,
				200, 0, 0, 100); // blue
		portals[3] = Server.npcHandler.spawnNpc77(6145, 2645, 2569, id * 4, 0,
				200, 0, 0, 100); // yellow

	}

	private void spawnRandomNPC() {
		for (int i = 0; i < portals.length; i++) {
			if (portals[i] != null && Math.random() > 0.5) {
				PestControlNPC luckiest = PestControlNPC.values()[((int) (Math
						.random() * PestControlNPC.values().length))];
				if (luckiest != null) {
					// TODO add npc stats :3
					Server.npcHandler.spawnNpc77(
							luckiest.getLowestNPCID()
									+ ((int) (Math.random() * (luckiest
											.getHighestNPCID() - luckiest
											.getLowestNPCID()))),
							portals[i].absX, portals[i].absY - 1, id * 4, 0,
							200, 5, 100, 100);
				}
			}
		}
	}

	public void startGame() {
		if (playersInBoat() > 1) {
			gameTimer = GAME_TIMER;
			// spawn npcs

			// move players into game
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					if (PlayerHandler.players[j].inPcBoat()) {
						movePlayer(j);
					}
				}
			}
			System.out.println("SPAWNING NPCS@@");
			spawnNpcs();
		} else {
			for (int j = 0; j < PlayerHandler.players.length; j++) {
				if (PlayerHandler.players[j] != null) {
					if (PlayerHandler.players[j].inPcBoat()) {
						Client c = (Client) PlayerHandler.players[j];
						c.sendMessage("There need to be atleast 2 players in the boat to start a game of pest control.");
					}
				}
			}
		}
	}

}