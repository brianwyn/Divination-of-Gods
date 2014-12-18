package server.task.impl;

import java.util.LinkedList;
import java.util.List;

import server.Server;
import server.core.GameEngine;
import server.task.Task;

/**
 * @author Jinrake Region Processing NPCs
 */

public class ProcessNPCs implements Task {

	List<Integer> localNPCs = new LinkedList<Integer>();
	List<Integer> finalNPCs = new LinkedList<Integer>();

	@Override
	public void execute(GameEngine context) {
		context.submitWork(new Runnable() {
			public void run() {
				Server.npcHandler.process();
			}
		});
	}

}