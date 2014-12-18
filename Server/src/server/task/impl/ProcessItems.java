package server.task.impl;

import server.Server;
import server.core.GameEngine;
import server.task.Task;

public class ProcessItems implements Task {

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				Server.itemHandler.process();
				// System.out.println("ItemHandler processed at "+System.currentTimeMillis());
			}
		});
	}

}