package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;

import server.core.event.CycleEventHandler;
import server.core.event.task.Task;
import server.core.event.task.TaskScheduler;
//import server.util.MadTurnipConnection;
import server.model.minigames.BarbarianDefence;
import server.model.minigames.FightCaves;
import server.model.minigames.Goblin;
import server.model.minigames.HardCaves;
import server.model.minigames.Nomad;
import server.model.minigames.PestControl;
import server.model.minigames.RFD;
import server.model.npcs.NPCDrops;
import server.model.npcs.NPCHandler;
import server.model.players.PlayerHandler;
import server.model.players.SQL;
import server.model.players.content.ImpSpawn;
import server.model.players.packets.PJClans;
import server.net.PipelineFactory;
import server.util.SimpleTimer;
import server.util.log.Logger;
import server.world.ClanChatHandler;
import server.world.ItemHandler;
import server.world.ObjectHandler;
import server.world.ObjectManager;
import server.world.PlayerManager;
import server.world.PublicEvent;
import server.world.ShopHandler;
import server.world.StillGraphicsManager;
import server.world.clip.region.ObjectDef;
import server.world.clip.region.Region;

/**
 * Server.java
 * 
 * @author Sanity
 * @author Graham
 * @author Blake
 * @author Ryan Lmctruck30
 * 
 */

public class Server {

	// Update log
	// Disabled game engine because its not picking up a deadlock crash

	// Found deadlock, patched, had to do with NPCDrops

	// Debugged all nullpointers, there should be none left

	// Re enabled game engine since the deadlock was patched
	// Deadlock back

	// Server ran for 1 week with 10+ players on all the time same engine load
	// after a week

	// endoflog
	public static PlayerManager playerManager = null;
	private static StillGraphicsManager stillGraphicsManager = null;

	public static boolean sleeping;
	public static int cycleRate;
	public static boolean UpdateServer = false;
	public static long lastMassSave = System.currentTimeMillis();
	public static SimpleTimer engineTimer, debugTimer;
	public static long cycleTime, cycles, totalCycleTime, sleepTime;
	@SuppressWarnings("unused")
	private static DecimalFormat debugPercentFormat;
	public static boolean shutdownServer = false;
	public static boolean shutdownClientHandler;
	public static int serverlistenerPort;
	public static ItemHandler itemHandler = new ItemHandler();
	public static LinkedList<?> singleStarter = new LinkedList<Object>();
	public static PlayerHandler playerHandler = new PlayerHandler();
	public static BarbarianDefence barbDefence = new BarbarianDefence();
	public static NPCHandler npcHandler = new NPCHandler();
	public static ShopHandler shopHandler = new ShopHandler();
	public static ObjectHandler objectHandler = new ObjectHandler();
	public static ObjectManager objectManager = new ObjectManager();
	public static int days, hours, minutes, secundes;
	public static NPCDrops npcDrops = new NPCDrops();
	public static FightCaves fightCaves = new FightCaves();
	public static HardCaves hardCaves = new HardCaves();
	public static PJClans pJClans = new PJClans();
	public static ClanChatHandler clanChat = new ClanChatHandler();
	public static Nomad Nomad = new Nomad();
	public static Goblin Goblin = new Goblin();
	public static RFD rfd = new RFD();
	public static int garbageCollector = 0;
	public static long[] TIMES = new long[5];
	/**
	 * The task scheduler.
	 */
	private static final TaskScheduler scheduler = new TaskScheduler();

	static {
		serverlistenerPort = 43594;
		cycleRate = 575;
		shutdownServer = false;
		engineTimer = new SimpleTimer();
		debugTimer = new SimpleTimer();
		sleepTime = 0;
		debugPercentFormat = new DecimalFormat("0.0#%");
	}

	public static boolean playerExecuted = false;

	public static int count = 0;

	public static int count2 = 0;

	public static int totalExecution = 0;

	public static int totalExecution2 = 0;

	/**
	 * Starts the server.
	 */
	/**
	 * The task scheduler.
	 */
	private static void bind() {
		ServerBootstrap serverBootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		serverBootstrap.setPipelineFactory(new PipelineFactory(
				new HashedWheelTimer()));
		serverBootstrap.bind(new InetSocketAddress(serverlistenerPort));
	}

	public static void debug() {
		System.out.println("Players online: " + PlayerHandler.getPlayerCount()
				+ "");
		playerExecuted = false;
		count += 1;
		if (count == 10) {
			System.gc();
			System.runFinalization();
			count = 0;
			System.out.println("Garbage collector initialized");
			playerExecuted = false;
		}
	}

	public static PlayerManager getPlayerManager() {
		return playerManager;
	}

	public static long getSleepTimer() {
		return sleepTime;
	}

	public static StillGraphicsManager getStillGraphicsManager() {
		return stillGraphicsManager;
	}

	/**
	 * Gets the task scheduler.
	 * 
	 * @return The task scheduler.
	 */
	public static TaskScheduler getTaskScheduler() {
		return scheduler;
	}

	public static void main(java.lang.String args[])
			throws NullPointerException, IOException {
		System.setOut(new Logger(System.out));
		System.setErr(new Logger(System.err));
		ObjectDef.loadConfig();
		Region.load();
		// md = new MadTurnipConnection();
		// md.start();
		System.out
				.println("[DivinationofGods] Launching Divination of Gods..cuz jal knight is awesome");
		SQL.createConnection();
		if(SQL.connectionMade)
			System.out.println("Oh look! The Connection to HS has been made!");
		pJClans.initialize();
		Connection.initialize();
		ImpSpawn.spawnImps();

		playerManager = PlayerManager.getSingleton();
		playerManager.setupRegionPlayers();
		stillGraphicsManager = new StillGraphicsManager();
		/**
		 * Successfully loaded the server.
		 */
		bind();
		// Server.setupLoginChannels(); // set up login channels late to avoid
		// ppl logging in to early
		System.out
				.println("[divinationofgods.cu.cc] Launched Divination of Gods!");
		/**
		 * Main server tick.
		 */

		scheduler.schedule(new Task() {
			@Override
			protected void execute() {
				try {
					itemHandler.process();
					playerHandler.process();
					npcHandler.process();
					shopHandler.process();
					CycleEventHandler.getSingleton().process();
					objectManager.process();
					// fightPits.process();
					// CastleWars.process();
					PublicEvent.process();
					miniGames();
					count2 += 1;
					if (count2 == 60) {
						debug();
						count2 = 0;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void miniGames() {
		PestControl.staticProcess();
		for (int i = 0; i < PestControl.runningGames.length; i++) {
			if (PestControl.runningGames[i] != null) {
				PestControl.runningGames[i].process();
				if (PestControl.runningGames[i].getKnight() == null) {
					PestControl.runningGames[i] = null;
				}
			}
		}
	}

	public static void shutdown() {
		shutdownServer = true;
		System.exit(0);
	}

}
