package server.core.event;

import java.util.concurrent.TimeUnit;

import server.core.GameEngine;

/**
 * A class that manages <code>Event</code>s for a specific
 * <code>GameEngine</code>.
 * 
 * @author Graham Edgecombe
 * 
 */
public class EventHandler {

	/**
	 * The <code>GameEngine</code> to manager events for.
	 */
	private GameEngine engine;

	/**
	 * Creates an <code>EventManager</code> for the specified
	 * <code>GameEngine</code>.
	 * 
	 * @param engine
	 *            The game engine the manager is managing events for.
	 */
	public EventHandler(GameEngine engine) {
		this.engine = engine;
	}

	/**
	 * Submits a new event to the <code>GameEngine</code>.
	 * 
	 * @param event
	 *            The event to submit.
	 */
	public void submit(final Events event) {
		submit(event, event.getDelay());
	}

	/**
	 * Schedules an event to run after the specified delay.
	 * 
	 * @param event
	 *            The event.
	 * @param delay
	 *            The delay.
	 */
	private void submit(final Events event, final long delay) {
		engine.scheduleLogic(new Runnable() {
			@Override
			public void run() {
				long start = System.nanoTime() / 1000000L;

				if (event.isRunning()) {
					event.execute();
				} else {
					return;
				}
				long elapsed = System.nanoTime() / 1000000L - start;
				long remaining = event.getDelay() - elapsed;
				if (remaining <= 0) {
					remaining = 0;
				}
				submit(event, remaining);
			}
		}, delay, TimeUnit.MILLISECONDS);
	}

}
