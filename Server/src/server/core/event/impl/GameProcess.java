package server.core.event.impl;

import server.core.World;
import server.core.event.Events;
import server.task.impl.ProcessServer;

public class GameProcess extends Events {

	public static final int CYCLE_TIME = 600;

	public GameProcess() {
		super(CYCLE_TIME);
	}

	@Override
	public void execute() {
		if (600 - ProcessServer.finish > 0) {
			this.setDelay(600 - ProcessServer.finish);
			World.getWorld().submit(new ProcessServer());
		}
	}

}
