package server.model.players.content.skills.impl;

/**
 * @Author Gabbe
 */

public class Timers {

	public static final int[][] ROCKS = {
			// ORE ID, RESPAWN, MINING TIMER
			// RUNE
			{ 14859, 70, 8 }, // RUNE
			{ 2106, 70, 8 }, // RUNE
			// ADDY
			{ 11939, 50, 7 }, // ADDY
			{ 2104, 50, 7 }, // ADDY
			{ 11941, 50, 7 }, // ADDY
			// MITHRIL
			{ 11943, 36, 6 }, // MITHRIL
			{ 11942, 36, 6 }, // MITHRIL
			{ 2102, 36, 6 }, // MITHRIL
			{ 11944, 36, 6 }, // MITHRIL
			{ 11945, 36, 6 }, { 11946, 36, 6 }, { 11947, 36, 6 },
			// GOLD
			{ 2098, 23, 5 }, // GOLD
			{ 2099, 23, 5 }, // GOLD
			// COAL
			{ 2096, 18, 5 }, // COAL
			{ 2097, 18, 5 }, // COAL
			{ 11930, 18, 5 }, // COAL
			{ 11931, 18, 5 }, // COAL
			// IRON
			{ 2093, 9, 3 }, // IRON
			{ 2092, 9, 3 }, // IRON
			{ 11954, 9, 3 }, // IRON
			// TIN
			{ 11933, 4, 2 }, // TIN
			{ 2094, 4, 2 }, // TIN
			// COPPER
			{ 11938, 4, 2 }, // COPPER
			{ 2090, 4, 2 }, // COPPER
			// COAL
			{ 11963, 18, 5 }, // Coal
			{ 11964, 18, 5 }, // Coal
			{ 11965, 18, 5 } // Coal
	};

	public static final int[][] Pickaxes = {
			// AXE ID, DELAY TIMER
			{ 1265, 7 }, // Bronze
			{ 1267, 7 }, // Iron
			{ 1269, 6 }, // Steel
			{ 1271, 5 }, // Addy
			{ 1273, 4 }, // Mithril
			{ 1275, 3 }, // Rune
			{ 15259, 2 }, // Dragon
			{ 13661, 1 } // Adze
	};

	public static int getMiningRespawnTimer(int ore) {
		for (int i = 0; i < ROCKS.length; i++) {
			if (ore == ROCKS[i][0])
				return ROCKS[i][1];
		}
		return 30;
	}

	public static int getMiningTimer(int ore, int pick, int level) {
		int k = -1;
		double extra = 0;
		int levelExtra = 0;
		for (int i = 0; i < ROCKS.length; i++) {
			if (ore == ROCKS[i][0]) {
				k = ROCKS[i][2];
			}
		}
		for (int j = 0; j < Pickaxes.length; j++) {
			if (pick == Pickaxes[j][0]) {
				extra = (Pickaxes[j][1] * 0.5);
				if (extra > 0)
					k += (int) extra;
			}
		}

		// I'll redo this when I've got time..
		levelExtra = (level / level);
		k += levelExtra;

		if (k < 2)
			k = 2;

		return k;
	}

	public static int handlePickAxe(int pick) {
		return Pickaxes[pick][0];
	}
}