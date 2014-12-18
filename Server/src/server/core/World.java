package server.core;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import server.Connection;
import server.core.event.EventHandler;
import server.core.event.Events;
import server.core.event.impl.CleanupEvent;
import server.core.event.impl.GameProcess;
import server.core.sql.SQLHandler;
import server.model.npcs.NPCDrops;
import server.task.Task;
import server.util.BlockingExecutorService;

/**
 * Holds data global to the game world.
 * 
 * @author Graham Edgecombe
 * 
 */
public class World {

	/**
	 * Logging class.
	 */
	private static final Logger logger = Logger
			.getLogger(World.class.getName());

	private SQLHandler mysqlPool;

	/**
	 * World instance.
	 */
	private static final World world = new World();

	/**
	 * Gets the world instance.
	 * 
	 * @return The world instance.
	 */
	public static World getWorld() {
		return world;
	}

	/**
	 * An executor service which handles background loading tasks.
	 */
	private BlockingExecutorService backgroundLoader = new BlockingExecutorService(
			Executors.newSingleThreadExecutor());

	/**
	 * The game engine.
	 */
	private GameEngine engine;

	/**
	 * The event manager.
	 */
	private EventHandler eventHandler;

	/**
	 * Creates the world and begins background loading tasks.
	 */

	public World() {
		backgroundLoader.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				NPCDrops.initialize();
				return null;
			}
		});
		backgroundLoader.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Connection.initialize();
				return null;
			}
		});
	}

	/**
	 * Gets the background loader.
	 * 
	 * @return The background loader.
	 */
	public BlockingExecutorService getBackgroundLoader() {
		return backgroundLoader;
	}

	/**
	 * Gets the game engine.
	 * 
	 * @return The game engine.
	 */
	public GameEngine getEngine() {
		return engine;
	}

	public SQLHandler getSQLPool() {
		return mysqlPool;
	}

	/**
	 * Handles an exception in any of the pools.
	 * 
	 * @param t
	 *            The exception.
	 */
	public void handleError(Exception e) {
		logger.severe("An error occurred in an executor service! The server will be halted immediately.");
		e.printStackTrace();
	}

	/**
	 * Initialises the world: loading configuration and registering global
	 * events.
	 * 
	 * @param engine
	 *            The engine processing this world's tasks.
	 * @throws IOException
	 *             if an I/O error occurs loading configuration.
	 * @throws ClassNotFoundException
	 *             if a class loaded through reflection was not found.
	 * @throws IllegalAccessException
	 *             if a class could not be accessed.
	 * @throws InstantiationException
	 *             if a class could not be created.
	 * @throws IllegalStateException
	 *             if the world is already initialised.
	 */
	public void init(GameEngine engine) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		if (this.engine != null) {
			throw new IllegalStateException(
					"The world has already been initialised.");
		} else {
			/*
			 * mysqlPool = new MySQLHandler(Executors.newFixedThreadPool(Runtime
			 * .getRuntime().availableProcessors()), "localhost:3306/server");
			 * mysqlPool.initializeDriver();
			 */
			this.engine = engine;
			this.eventHandler = new EventHandler(engine);
			this.registerGlobalEvents();
		}
	}

	/**
	 * Registers global events such as updating.
	 */
	private void registerGlobalEvents() {
		submit(new GameProcess());
		submit(new CleanupEvent());
	}

	/**
	 * Submits a new event.
	 * 
	 * @param event
	 *            The event to submit.
	 */
	public void submit(Events event) {
		this.eventHandler.submit(event);
	}

	/**
	 * Submits a new task.
	 * 
	 * @param task
	 *            The task to submit.
	 */
	public void submit(Task task) {
		this.engine.pushTask(task);
	}

}
