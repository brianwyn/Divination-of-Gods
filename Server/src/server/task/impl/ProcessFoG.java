package server.task.impl;

import server.core.GameEngine;
import server.task.Task;

public class ProcessFoG implements Task {

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				// FistOfGuthix.process();
			}
		});
	}

}