package server.task.impl;

import server.Server;
import server.core.GameEngine;
import server.core.World;
import server.core.event.CycleEventHandler;
import server.task.ConsecutiveTask;
import server.task.Task;

public class ProcessServer implements Task {
	public static long finish = 0;

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				try {
					long start = System.currentTimeMillis();
					World.getWorld().submit(
							new ConsecutiveTask(new ProcessPlayerHandler(),
									new ProcessItems(), new ProcessObjects(),
									new ProcessShops(), new ProcessNPCs(),
									new ProcessPublicEvent(),
									new ProcessPestControl()));
					// Server.fightPits.process();
					Server.engineTimer.reset();
					if (Server.garbageCollector == 30) {
						System.gc();
						Server.garbageCollector = 0;
					}
					CycleEventHandler.getSingleton().process();
					Server.garbageCollector++;
					Server.cycleTime = Server.engineTimer.elapsed();
					Server.sleepTime = Server.cycleRate - Server.cycleTime;
					Server.totalCycleTime += Server.cycleTime;
					Server.cycles++;
					Server.debug();
					finish = System.currentTimeMillis() - start;
				} catch (Exception e) {
					World.getWorld().handleError(e);
				}
			}
		});
	}

}