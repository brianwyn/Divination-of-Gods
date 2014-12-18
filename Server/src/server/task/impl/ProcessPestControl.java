package server.task.impl;

import server.core.GameEngine;
import server.model.minigames.PestControl;
import server.task.Task;

public class ProcessPestControl implements Task {

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				PestControl.staticProcess();
				for (int i = 0; i < PestControl.runningGames.length; i++) {
					if (PestControl.runningGames[i] != null) {
						PestControl.runningGames[i].process();
						if (PestControl.runningGames[i].getKnight() == null) {
							PestControl.runningGames[i] = null;
						}
					}
				}
			}
		});
	}

}