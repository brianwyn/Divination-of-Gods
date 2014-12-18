package server.task.impl;

import server.Server;
import server.core.GameEngine;
import server.core.World;
import server.task.Task;

public class ProcessPlayerHandler implements Task {

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				try {
					Server.playerHandler.process();
					// System.out.println("PlayerHandler processed at "+System.currentTimeMillis());
				} catch (Exception e) {
					World.getWorld().handleError(e);
				}
			}
		});
	}

}