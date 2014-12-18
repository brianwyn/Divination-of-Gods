package server.core;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import server.world.clip.region.ObjectDef;
import server.world.clip.region.Region;

/**
 * 
 * @author Graham
 * 
 */
public class RS2Server {

	/**
	 * The <code>GameEngine</code> instance.
	 */
	private static final GameEngine engine = new GameEngine();

	/**
	 * Gets the <code>GameEngine</code>.
	 * 
	 * @return The game engine.
	 */
	public static GameEngine getEngine() {
		return engine;
	}

	/**
	 * Creates the server and the <code>GameEngine</code> and initializes the
	 * <code>World</code>.
	 * 
	 * @throws IOException
	 *             if an I/O error occurs loading the world.
	 * @throws ClassNotFoundException
	 *             if a class the world loads was not found.
	 * @throws IllegalAccessException
	 *             if a class loaded by the world was not accessible.
	 * @throws InstantiationException
	 *             if a class loaded by the world was not created.
	 */
	public RS2Server() throws IOException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		World.getWorld().init(engine);
		// acceptor.getFilterChain().addFirst("throttleFilter", new
		// ConnectionThrottleFilter());
	}

	/**
	 * Starts the <code>GameEngine</code>.
	 * 
	 * @throws ExecutionException
	 *             if an error occurred during background loading.
	 */
	public void start() throws ExecutionException {
		if (World.getWorld().getBackgroundLoader().getPendingTaskAmount() > 0) {
			// logger.info("Waiting for pending background loading tasks...");
			World.getWorld().getBackgroundLoader().waitForPendingTasks();
		}
		World.getWorld().getBackgroundLoader().shutdown();
		engine.start();
		// logger.info("Setting up login channels...");
		// Server.setupLoginChannels();
		ObjectDef.loadConfig();
		Region.load();
		// ImpSpawn.spawnImps();
	}

}
