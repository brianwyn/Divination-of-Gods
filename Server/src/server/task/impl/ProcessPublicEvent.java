package server.task.impl;

import server.core.GameEngine;
import server.task.Task;
import server.world.PublicEvent;

public class ProcessPublicEvent implements Task {

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				PublicEvent.process();
			}
		});
	}

}