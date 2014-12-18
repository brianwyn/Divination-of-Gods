package server.task.impl;

import server.Server;
import server.core.GameEngine;
import server.task.Task;

public class ProcessShops implements Task {

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				Server.shopHandler.process();
			}
		});
	}

}