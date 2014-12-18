package server.model.players.content;

import server.Server;
import server.core.event.task.Task;
import server.model.players.Client;
import server.util.Misc;

public class ImpSpawn {

	// Author:
	// Gabbe

	public static void passWall(Client c) {
		if (c != null) {
			if (c.objectId != 24901)
				return;
			// First west wall
			if (c.absX == 2584) {
				c.getPA().movePlayer(2582, c.absY, 0);
			} else if (c.absX == 2582) { // WAY BACK
				c.getPA().movePlayer(2584, c.absY, 0);
			}
			//

			// SOUTH WALL FIRST
			if (c.absY == 4312) {
				c.getPA().movePlayer(c.absX, 4310, 0);
			} else if (c.absY == 4310) { // WAY BACK
				c.getPA().movePlayer(c.absX, 4312, 0);
			}
			//

			// FIRST EAST WALL
			if (c.absX == 2599) {
				c.getPA().movePlayer(2601, c.absY, 0);
			} else if (c.absX == 2601) { // WAY BACK
				c.getPA().movePlayer(2599, c.absY, 0);
			}
			//

			// FIRST NORTH WALL
			if (c.absY == 4327) {
				c.getPA().movePlayer(c.absX, 4329, 0);
			} else if (c.absY == 4329) { // WAY BACK
				c.getPA().movePlayer(c.absX, 4327, 0);
			}
			//

		}
	}

	public static void spawnBaby(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6055, X, Y, 0, 8, 1000, 4, 4, 4);
	}

	public static void spawnDragon(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6057, 2605, 4316, 0, 8, 1000, 4, 4, 4);
		return;
	}

	public static void spawnEarth(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6058, X, Y, 0, 8, 1000, 4, 4, 1000);
		return;
	}

	public static void spawnElectic(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6060, X, Y, 0, 8, 1000, 4, 4, 1000);
		return;
	}

	public static void spawnEssence(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6059, X, Y, 0, 8, 1000, 4, 4, 1000);
		return;
	}

	public static void spawnGourment(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6057, X, Y, 0, 8, 1000, 4, 4, 4);
		return;
	}

	public static void spawnImps() {
		Server.getTaskScheduler().schedule(new Task(1, true) {

			@Override
			protected void execute() {

				// D IMPS - THEY HAVE 8 AS WALKING - WILL WALK AROUND
				Server.npcHandler.spawnNpc2(6064, 2613, 4341, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6064, 2585, 4337, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6064, 2576, 4319, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6064, 2576, 4294, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6064, 2592, 4305, 0, 8, 1000, 4, 4,
						1000);

				// GOURMENT IMPS - THEY HAVE 8 AS WALKING - WILL WALK AROUND
				Server.npcHandler.spawnNpc2(6057, 2573, 4339, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6057, 2567, 4328, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6057, 2593, 4297, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6057, 2618, 4305, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6057, 2605, 4316, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6057, 2596, 4333, 0, 8, 1000, 4, 4,
						1000);

				// EARTH IMPS - THEY HAVE 8 AS WALKING - WILL WALK AROUND
				Server.npcHandler.spawnNpc2(6058, 2592, 4338, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6058, 2611, 4345, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6058, 2617, 4339, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6058, 2614, 4301, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6058, 2606, 4295, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6058, 2581, 4299, 0, 8, 1000, 4, 4,
						1000);

				// NINJA IMPS - THEY HAVE 8 AS WALKING - WILL WALK AROUND
				Server.npcHandler.spawnNpc2(6063, 2570, 4347, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6063, 2572, 4327, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6063, 2578, 4318, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6063, 2610, 4312, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6063, 2594, 4341, 0, 8, 1000, 4, 4,
						1000);

				// ESSENCE IMPS - THEY HAVE 8 AS WALKING - WILL WALK AROUND
				Server.npcHandler.spawnNpc2(6059, 2602, 4328, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6059, 2608, 4333, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6059, 2609, 4296, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6059, 2581, 4304, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6059, 2570, 4318, 0, 8, 1000, 4, 4,
						1000);

				// ELECTIC IMPS - THEY HAVE 8 AS WALKING - WILL WALK AROUND
				Server.npcHandler.spawnNpc2(6060, 2611, 4310, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6060, 2617, 4319, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6060, 2600, 4347, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6060, 2570, 4326, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6060, 2579, 4310, 0, 8, 1000, 4, 4,
						1000);

				// NATURE IMPS - THEY HAVE 8 AS WALKING - WILL WALK AROUND
				Server.npcHandler.spawnNpc2(6061, 2581, 4310, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6061, 2601, 4300, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6061, 2603, 4333, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6061, 2576, 4335, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6061, 2588, 4345, 0, 8, 1000, 4, 4,
						1000);

				// MAGPIE IMPS - THEY HAVE 8 AS WALKING - WILL WALK AROUND
				Server.npcHandler.spawnNpc2(6062, 2612, 4324, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6062, 2602, 4323, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6062, 2587, 4348, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6062, 2564, 4320, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6062, 2566, 4295, 0, 8, 1000, 4, 4,
						1000);

				// BABY IMPS - THEY HAVE 8 AS WALKING - WILL WALK AROUND
				Server.npcHandler.spawnNpc2(6055, 2602, 4314, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2612, 4318, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2610, 4338, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2582, 4344, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2578, 4344, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2568, 4311, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2583, 4295, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2582, 4330, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2600, 4303, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2611, 4301, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6055, 2618, 4329, 0, 8, 1000, 4, 4,
						1000);

				// YOUNG IMP
				Server.npcHandler.spawnNpc2(6056, 2591, 4332, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6056, 2600, 4338, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6056, 2595, 4345, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6056, 2610, 4327, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6056, 2617, 4314, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6056, 2619, 4294, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6056, 2599, 4294, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6056, 2575, 4303, 0, 8, 1000, 4, 4,
						1000);
				Server.npcHandler.spawnNpc2(6056, 2570, 4299, 0, 8, 1000, 4, 4,
						1000);

				// KINGLY
				spawnKingly2();

				System.out.println("Hunter implings successfully spawned.");

				stop();

			}
		});
	}

	public static void spawnKingly(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(7903, 2576, 4305, 0, 8, 1000, 4, 4, 1000);
		return;
	}

	public static void spawnKingly2() {
		int random = Misc.random(4);
		// System.out.println(random);
		if (random == 0) {
			Server.npcHandler.spawnNpc2(7903, 2596, 4351, 0, 8, 1000, 4, 4,
					1000);
			return;
		} else if (random == 1) {
			Server.npcHandler.spawnNpc2(7903, 2624, 4352, 0, 8, 1000, 4, 4,
					1000);
			return;
		} else if (random == 2) {
			Server.npcHandler.spawnNpc2(7903, 2607, 4321, 0, 8, 1000, 4, 4,
					1000);
			return;
		} else if (random == 3) {
			Server.npcHandler.spawnNpc2(7903, 2588, 4289, 0, 8, 1000, 4, 4,
					1000);
			return;
		} else if (random == 4 || random > 4) {
			Server.npcHandler.spawnNpc2(7903, 2576, 4305, 0, 8, 1000, 4, 4,
					1000);
			return;
		}
	}

	public static void spawnMagpie(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6062, 2566, 4295, 0, 8, 1000, 4, 4, 1000);
		return;
	}

	public static void spawnNature(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6061, X, Y, 0, 8, 1000, 4, 4, 1000);
		return;
	}

	public static void spawnNewImp(final int type, int timer, final int X,
			final int Y) {
		Server.getTaskScheduler().schedule(new Task(timer, true) {

			@Override
			protected void execute() {
				if (type == 6055) {
					spawnBaby(X, Y);
					stop();
				}
				if (type == 6056) {
					spawnYoung(X, Y);
					stop();
				}
				if (type == 6064) {
					spawnDragon(X, Y);
					stop();
				}
				if (type == 6057) {
					spawnGourment(X, Y);
					stop();
				}
				if (type == 6058) {
					spawnEarth(X, Y);
					stop();
				}
				if (type == 6059) {
					spawnEssence(X, Y);
					stop();
				}
				if (type == 6060) {
					spawnElectic(X, Y);
					stop();
				}
				if (type == 6061) {
					spawnNature(X, Y);
					stop();
				}
				if (type == 6062) {
					spawnMagpie(X, Y);
					stop();
				}
				if (type == 6063) {
					spawnNinja(X, Y);
					stop();
				}
				if (type == 7903) {
					spawnKingly(X, Y);
					stop();
				}
				stop();

			}
		});
	}

	public static void spawnNinja(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6063, X, Y, 0, 8, 1000, 4, 4, 1000);
		return;
	}

	public static void spawnYoung(int X, int Y) {
		if (X == 0 || Y == 0) {
			return;
		}
		Server.npcHandler.spawnNpc2(6056, X, Y, 0, 8, 1000, 4, 4, 4);
		return;
	}

}