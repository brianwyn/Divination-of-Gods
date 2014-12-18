package server.task.impl;

import server.Server;
import server.core.GameEngine;
import server.task.Task;

public class ProcessObjects implements Task {

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				Server.objectManager.process();
				// System.out.println("ObjectHandler processed at "+System.currentTimeMillis());
			}
		});
	}

}