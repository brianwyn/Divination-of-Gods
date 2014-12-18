package server.task.impl;

import server.core.GameEngine;
import server.task.Task;

/**
 * Performs garbage collection and finalization.
 * 
 * @author Graham Edgecombe
 * 
 */
public class CleanupTask implements Task {

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				System.gc();
				System.runFinalization();
				System.out.println("System resources cleaned");
			}
		});
	}

}
