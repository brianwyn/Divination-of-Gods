package server.world;

import java.util.ArrayList;

import server.Server;
import server.model.objects.Object;
import server.model.objects.Objects;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.skills.Construction;
import server.util.Misc;

/**
 * @author Sanity
 */

public class ObjectManager {

	public static void assault(Client c) {
		// east
		c.getPA().checkObjectSpawn(1, 1890, 5411, 1, 10);
		c.getPA().checkObjectSpawn(1, 1890, 5410, 1, 10);
		c.getPA().checkObjectSpawn(1, 1890, 5409, 1, 10);
		c.getPA().checkObjectSpawn(1, 1890, 5408, 1, 10);
		c.getPA().checkObjectSpawn(29537, 1890, 5407, 1, 10);
		c.getPA().checkObjectSpawn(1, 1890, 5406, 1, 10);
		c.getPA().checkObjectSpawn(1, 1890, 5405, 1, 10);
		c.getPA().checkObjectSpawn(1, 1890, 5404, 1, 10);
		// south
		c.getPA().checkObjectSpawn(1, 1883, 5404, 1, 10);
		c.getPA().checkObjectSpawn(1, 1884, 5404, 1, 10);
		c.getPA().checkObjectSpawn(1, 1885, 5404, 1, 10);
		c.getPA().checkObjectSpawn(1, 1886, 5404, 1, 10);
		c.getPA().checkObjectSpawn(1, 1887, 5404, 1, 10);
		c.getPA().checkObjectSpawn(1, 1888, 5404, 1, 10);
		c.getPA().checkObjectSpawn(1, 1889, 5404, 1, 10);
		c.getPA().checkObjectSpawn(1, 1810, 5404, 1, 10);
		// west
		c.getPA().checkObjectSpawn(1, 1883, 5405, 1, 10);
		c.getPA().checkObjectSpawn(1, 1883, 5406, 1, 10);
		c.getPA().checkObjectSpawn(1, 1883, 5407, 1, 10);
		c.getPA().checkObjectSpawn(1, 1883, 5408, 1, 10);
		c.getPA().checkObjectSpawn(1, 1883, 5409, 1, 10);
		c.getPA().checkObjectSpawn(1, 1883, 5410, 1, 10);
		c.getPA().checkObjectSpawn(1, 1883, 5411, 1, 10);
		// north
		c.getPA().checkObjectSpawn(1, 1884, 5411, 1, 10);
		c.getPA().checkObjectSpawn(1, 1885, 5411, 1, 10);
		c.getPA().checkObjectSpawn(1, 1886, 5411, 1, 10);
		c.getPA().checkObjectSpawn(1, 1887, 5411, 1, 10);
		c.getPA().checkObjectSpawn(1, 1888, 5411, 1, 10);
		c.getPA().checkObjectSpawn(1, 1889, 5411, 1, 10);

		Construction.spawnObjects(c);
	}

	public ArrayList<Object> objects = new ArrayList<Object>();

	private ArrayList<Object> toRemove = new ArrayList<Object>();

	public static int CANNONID = 6;

	public static final int[] obeliskIds = { 14829, 14830, 14827, 14828, 14826,
			14831 };

	public static void handleCannon(Client c) { // Respawns peoples cannons if
												// they enter new region
		// By Gabbe
		/*
		 * Objects cannon = null; for (server.model.objects.Objects o :
		 * Server.objectHandler.globalObjects) { if (o.objectX == c.cannonBaseX
		 * && o.objectY == c.cannonBaseY && o.objectHeight == c.cannonBaseH) {
		 * cannon = o; } } if (cannon == null) { return; }
		 */
		if (c.hasCannon == true) { // If player has cannon, respawns it on
									// teleport.
			Objects cannon = new Objects(CANNONID, c.cannonBaseX,
					c.cannonBaseY, 0, 0, 10, 0);
			if (cannon != null)
				Server.objectHandler.addObject(cannon);
			Server.objectHandler.placeObject(cannon);
			c.getPA().checkObjectSpawn(CANNONID, c.cannonBaseX, c.cannonBaseY,
					10, 0);
			Objects.belongsTo = c.playerName;
			c.oldCannon = cannon;
			if (c.cannonIsShooting) { // Let's rerun the event
				// DwarfMultiCannon.objectsNeedsRestart = true; // Restarts
				// event
				Client.stopEventCannon = true; // Restarts event
				c.cannonIsShooting = true;
				Client.restart = true;
			}
		}
	}

	public final int IN_USE_ID = 14825;

	public static final int[][] obeliskCoords = { { 3154, 3618 },
			{ 3225, 3665 }, { 3033, 3730 }, { 3104, 3792 }, { 2978, 3864 },
			{ 3305, 3914 } };

	public static void handleKeys(Client c) {
		if (c != null) {
			if (c.inDung) {
				if (c.dungeonId == 1) {
					if (!c.fullDung) {
						if (!Server.itemHandler.itemExists(18314, 1871, 5241)
								&& !c.getItems().playerHasItem(18314, 1)
								&& !c.door2)
							Server.itemHandler.createGroundItem(c, 18314, 1871,
									5241, 1, c.playerId);

						if (!Server.itemHandler.itemExists(18292, 1876, 5209)
								&& !c.getItems().playerHasItem(18292, 1)
								&& !c.door1)
							Server.itemHandler.createGroundItem(c, 18292, 1876,
									5209, 1, c.playerId);

						if (!Server.itemHandler.itemExists(18300, 1882, 5244)
								&& !c.getItems().playerHasItem(18300, 1)
								&& !c.door3)
							Server.itemHandler.createGroundItem(c, 18300, 1882,
									5244, 1, c.playerId);

					}
					if (c.fullDung) {
						if (!Server.itemHandler.itemExists(18268, 1858, 5201)
								&& !c.getItems().playerHasItem(18268, 1)
								&& !c.door1)
							Server.itemHandler.createGroundItem(c, 18268, 1858,
									5201, 1, c.playerId);
						if (!Server.itemHandler.itemExists(18296, 1911, 5203)
								&& !c.getItems().playerHasItem(18296, 1)
								&& !c.door3)
							Server.itemHandler.createGroundItem(c, 18296, 1911,
									5203, 1, c.playerId);
					}
				}
			}
		}
	}

	public boolean[] activated = { false, false, false, false, false, false };

	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}
	}

	// Creds to Jason for this:
	public void clearArea(Client c, final int x1, final int y1, final int x2,
			final int y2, final int height) {
		if (c.heightLevel == height)
			for (int x = x1; x <= x2; x++)
				for (int y = y1; y <= y2; y++)
					c.getPA().checkObjectSpawn(-1, x, y, 0, 10);
	} // clears area of objects within x1 & y1

	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}

	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}
		return null;
	}

	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}

	public void loadCustomSpawns(Client c) {
		c.getPA().checkObjectSpawn(2480, 3488, 3288, 0, 10);// Water Altar
		// cons
		c.getPA().checkObjectSpawn(11402, 3181, 3435, 1, 10);
		c.getPA().checkObjectSpawn(11402, 3190, 3444, 1, 10);
		c.getPA().checkObjectSpawn(11402, 3190, 3445, 1, 10);
		c.getPA().checkObjectSpawn(11402, 3181, 3433, 1, 10);
		c.getPA().checkObjectSpawn(11402, 3181, 3434, 1, 10);
		c.getPA().checkObjectSpawn(11402, 3190, 3434, 1, 10);
		c.getPA().checkObjectSpawn(15410, 1898, 5092, 2, 10); // replace Ladders
		c.getPA().checkObjectSpawn(24161, 2649, 4686, 1, 10);
		c.getPA().checkObjectSpawn(24161, 2650, 4681, 1, 10);
		c.getPA().checkObjectSpawn(409, 2648, 4684, 1, 10);
		c.getPA().checkObjectSpawn(6552, 2630, 4679, 1, 10);
		c.getPA().checkObjectSpawn(411, 2630, 4683, 1, 10);
		c.getPA().checkObjectSpawn(410, 2634, 4688, 1, 10);
		c.getPA().checkObjectSpawn(11402, 2645, 4681, 1, 10);
		c.getPA().checkObjectSpawn(11402, 2645, 4686, 1, 10);
		c.getPA().checkObjectSpawn(1306, 2643, 4681, 1, 10);
		c.getPA().checkObjectSpawn(1306, 2640, 4679, 1, 10);
		c.getPA().checkObjectSpawn(1306, 2640, 4678, 1, 10);
		c.getPA().checkObjectSpawn(1306, 2638, 4676, 1, 10);
		c.getPA().checkObjectSpawn(1306, 2645, 4688, 1, 10);
		// with Bank
		// Booths
		// c.getPA().checkObjectSpawn(15411, 1899, 5092, 2, 10); //replace
		// Ladders with Bank Booths
		// c.getPA().checkObjectSpawn(15411, 1900, 5092, 2, 10); //replace
		// Ladders with Bank Booths
		c.getPA().checkObjectSpawn(15411, 1901, 5092, 2, 10); // replace Ladders
																// with Bank
																// Booths
		c.getPA().checkObjectSpawn(15418, 1899, 5095, 1, 10); // rfire
		c.getPA().checkObjectSpawn(15416, 1896, 5088, 0, 10); // rfire
		c.getPA().checkObjectSpawn(15416, 1903, 5088, 2, 10); // rfire
		c.getPA().checkObjectSpawn(-1, 1902, 5088, 2, 10); // rfire
		c.getPA().checkObjectSpawn(-1, 1897, 5088, 2, 10); // rfire

		c.getPA().checkObjectSpawn(-1, 1871, 5093, 22, 10); // replace Ladders
															// with Bank Booths
		c.getPA().checkObjectSpawn(-1, 1871, 5090, 22, 10); // replace Ladders
															// with Bank Booths
		c.getPA().checkObjectSpawn(-1, 1871, 5090, 8, 10); // replace Ladders
															// with Bank Booths
		c.getPA().checkObjectSpawn(-1, 1871, 5090, 4, 10); // replace Ladders
															// with Bank Booths

		c.getPA().checkObjectSpawn(-1, 2975, 3371, 4, 10); // removing tables
															// smithing place
															// falador
		c.getPA().checkObjectSpawn(2783, 2975, 3371, 4, 10); // removing tables
																// smithing
																// place falador
		c.getPA().checkObjectSpawn(-1, 2971, 3370, 4, 10); // removing tables
															// smithing place
															// falador
		c.getPA().checkObjectSpawn(2783, 2971, 3370, 4, 10); // removing tables
																// smithing
																// place falador
		c.getPA().checkObjectSpawn(2783, 2972, 3376, 4, 10); // removing tables
																// smithing
																// place falador

		c.getPA().checkObjectSpawn(-1, 2834, 10213, 4, 10); //
		c.getPA().checkObjectSpawn(-1, 2835, 10212, 4, 10); //
		c.getPA().checkObjectSpawn(-1, 2837, 10214, 4, 10); //
		c.getPA().checkObjectSpawn(-1, 2838, 10215, 4, 10); //
		c.getPA().checkObjectSpawn(-1, 2840, 10213, 4, 10); //
		c.getPA().checkObjectSpawn(-1, 2841, 10241, 4, 10); //

		for (int i = 1888; i < 1895; i++)
			c.getPA().checkObjectSpawn(1, i, 5088, 0, 10); // replace Ladders
															// with Bank Booths
		for (int i = 1872; i < 1879; i++)
			c.getPA().checkObjectSpawn(1, i, 5088, 0, 10); // replace Ladders
															// with Bank Booths

		for (int i = 5096; i < 5103; i++)
			c.getPA().checkObjectSpawn(1, 1871, i, 0, 10); // replace Ladders
															// with Bank Booths

		for (int i = 1880; i < 1887; i++)
			c.getPA().checkObjectSpawn(1, i, 5087, 0, 10); // replace Ladders
															// with Bank Booths

		for (int i = 1896; i < 1903; i++)
			c.getPA().checkObjectSpawn(1, i, 5087, 0, 10); // replace Ladders
															// with Bank Booths
		for (int i = 5088; i < 5095; i++)
			c.getPA().checkObjectSpawn(1, 1904, i, 0, 10); // replace Ladders
															// with Bank Booths
		for (int i = 5096; i < 5103; i++)
			c.getPA().checkObjectSpawn(1, 1896, i, 0, 10); // replace Ladders
															// with Bank Booths
		for (int i = 5096; i < 5103; i++)
			c.getPA().checkObjectSpawn(1, 1887, i, 0, 10); // replace Ladders
															// with Bank Booths

		// START OF SAMY'S CUSTOM MINING ZONE

		c.getPA().checkObjectSpawn(11758, 3019, 9740, 0, 10); // replace Ladders
																// with Bank
																// Booths
		c.getPA().checkObjectSpawn(11758, 3020, 9739, 1, 10);
		c.getPA().checkObjectSpawn(11758, 3019, 9738, 2, 10);
		c.getPA().checkObjectSpawn(11758, 3018, 9739, 3, 10);

		// Replace ores
		c.getPA().checkObjectSpawn(11933, 3028, 9739, 1, 10); // Tin
		c.getPA().checkObjectSpawn(11933, 3032, 9737, 2, 10);
		c.getPA().checkObjectSpawn(11933, 3032, 9735, 0, 10);
		c.getPA().checkObjectSpawn(11933, 3035, 9742, 3, 10);
		c.getPA().checkObjectSpawn(11933, 3034, 9739, 0, 10);

		c.getPA().checkObjectSpawn(11936, 3028, 9737, 1, 10); // Copper
		c.getPA().checkObjectSpawn(11936, 3029, 9734, 2, 10);
		c.getPA().checkObjectSpawn(11936, 3031, 9739, 0, 10);
		c.getPA().checkObjectSpawn(11936, 3032, 9741, 3, 10);
		c.getPA().checkObjectSpawn(11936, 3035, 9734, 0, 10);

		c.getPA().checkObjectSpawn(11954, 3037, 9739, 1, 10); // Iron
		c.getPA().checkObjectSpawn(11954, 3037, 9735, 2, 10);
		c.getPA().checkObjectSpawn(11954, 3037, 9733, 0, 10);
		c.getPA().checkObjectSpawn(11954, 3039, 9741, 3, 10);
		c.getPA().checkObjectSpawn(11954, 3039, 9738, 0, 10);

		c.getPA().checkObjectSpawn(11963, 3039, 9733, 1, 10); // Coal
		c.getPA().checkObjectSpawn(11964, 3040, 9732, 2, 10);
		c.getPA().checkObjectSpawn(11965, 3042, 9734, 0, 10);
		c.getPA().checkObjectSpawn(11965, 3044, 9737, 3, 10);
		c.getPA().checkObjectSpawn(11963, 3042, 9739, 0, 10);
		c.getPA().checkObjectSpawn(11963, 3045, 9740, 1, 10);
		c.getPA().checkObjectSpawn(11965, 3043, 9742, 2, 10);
		c.getPA().checkObjectSpawn(11964, 3045, 9744, 0, 10);
		c.getPA().checkObjectSpawn(11965, 3048, 9747, 3, 10);

		c.getPA().checkObjectSpawn(11951, 3048, 9743, 0, 10); // Gold
		c.getPA().checkObjectSpawn(11951, 3049, 9740, 1, 10);
		c.getPA().checkObjectSpawn(11951, 3047, 9737, 2, 10);
		c.getPA().checkObjectSpawn(11951, 3050, 9738, 0, 10);
		c.getPA().checkObjectSpawn(11951, 3052, 9739, 3, 10);
		c.getPA().checkObjectSpawn(11951, 3051, 9735, 0, 10);

		c.getPA().checkObjectSpawn(11945, 3049, 9735, 1, 10); // Mithril
		c.getPA().checkObjectSpawn(11946, 3049, 9734, 2, 10);
		c.getPA().checkObjectSpawn(11945, 3047, 9733, 0, 10);
		c.getPA().checkObjectSpawn(11947, 3046, 9733, 3, 10);
		c.getPA().checkObjectSpawn(11946, 3046, 9735, 0, 10);

		c.getPA().checkObjectSpawn(11941, 3053, 9737, 1, 10); // Adamant
		c.getPA().checkObjectSpawn(11939, 3054, 9739, 2, 10);
		c.getPA().checkObjectSpawn(11941, 3053, 9742, 0, 10);

		c.getPA().checkObjectSpawn(14859, 3038, 9748, 3, 10); // Runite
		c.getPA().checkObjectSpawn(14859, 3044, 9753, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3048, 9754, 1, 10);
		c.getPA().checkObjectSpawn(14859, 3054, 9746, 2, 10);

		c.getPA().checkObjectSpawn(411, 2962, 3210, 3, 10); // new prayer books
															// praying
		c.getPA().checkObjectSpawn(409, 2953, 3206, 2, 10); // Normal praying
		c.getPA().checkObjectSpawn(-1, 2947, 3204, 1, 10); // remove table in
															// rimmington shop
		c.getPA().checkObjectSpawn(3192, 2962, 3220, 2, 10); // remove table in
																// rimmington
																// shop
		c.getPA().checkObjectSpawn(-1, 2966, 3212, 1, 10); // remove table in
															// rimmington shop
		c.getPA().checkObjectSpawn(-1, 2966, 3211, 1, 10); // remove table in
															// rimmington shop
		c.getPA().checkObjectSpawn(-1, 2967, 3211, 1, 10); // remove table in
															// rimmington shop
		c.getPA().checkObjectSpawn(-1, 2966, 3213, 1, 10); // remove table in
															// rimmington shop
		c.getPA().checkObjectSpawn(-1, 2967, 3213, 1, 10); // remove table in
															// rimmington shop
		c.getPA().checkObjectSpawn(-1, 2948, 3203, 1, 10); // remove table in
															// rimmington shop
		c.getPA().checkObjectSpawn(-1, 2948, 3213, 1, 10); // remove ladder in
															// rimmington shop
		c.getPA().checkObjectSpawn(-1, 2794, 2793, 1, 10); // remove cauldron in
															// rimmington shop
		c.getPA().checkObjectSpawn(-1, 2967, 3205, 1, 10); // remove cauldron in
															// rimmington shop
		c.getPA().checkObjectSpawn(-1, 2794, 9237, 1, 10); // remove cauldron in
															// rimmington shop
		c.getPA().checkObjectSpawn(2731, 2597, 4781, 1, 10); // remove bed in
																// falador
		c.getPA().checkObjectSpawn(1, 2386, 4690, 2, 10); // remove bed in
															// falador
		c.getPA().checkObjectSpawn(1, 2386, 4691, 2, 10); // remove bed in
															// falador
		c.getPA().checkObjectSpawn(1, 2386, 4692, 2, 10); // remove bed in
															// falador
		c.getPA().checkObjectSpawn(1, 2386, 4693, 2, 10); // remove bed in
															// falador
		c.getPA().checkObjectSpawn(1, 2386, 4689, 2, 10); // remove bed in
															// falador
		// End dung
		// varrock sewer
		c.getPA().checkObjectSpawn(-1, 3207, 3435, 2, 10); // remove stall
		c.getPA().checkObjectSpawn(-1, 3221, 3431, 2, 10); // remove stall
		c.getPA().checkObjectSpawn(-1, 3221, 3432, 2, 10); // remove stall
		c.getPA().checkObjectSpawn(-1, 3219, 3485, 2, 10); // remove stall
		c.getPA().checkObjectSpawn(-1, 3210, 9898, 2, 10); // remove gate
		c.getPA().checkObjectSpawn(-1, 3241, 9911, 2, 10); // remove gate
		c.getPA().checkObjectSpawn(-1, 3245, 9916, 2, 10); // remove gate
		c.getPA().checkObjectSpawn(-1, 3247, 9892, 2, 10); // remove gate
		// Donator zone
		c.getPA().checkObjectSpawn(-1, 3305, 9376, -1, 10);
		c.getPA().checkObjectSpawn(-1, 3305, 9375, -1, 10);

		// more edge spawns
		c.getPA().checkObjectSpawn(-1, 3090, 3494, 0, 10); // Edgeville bank
															// removeing objects
															// in bank Chair
		c.getPA().checkObjectSpawn(-1, 3090, 3496, 0, 10); // Edgeville bank
															// removeing objects
															// in bank Chair
		c.getPA().checkObjectSpawn(-1, 3092, 3496, 0, 10); // Edgeville bank
															// removeing objects
															// in bank Chair
		c.getPA().checkObjectSpawn(-1, 3091, 3495, 0, 10); // Edgeville bank
															// removeing objects
															// in bank Table
		// START OF SAMY'S CYSTOM ::DZONE

		c.getPA().checkObjectSpawn(8749, 2840, 10219, 0, 10); // Church
		c.getPA().checkObjectSpawn(409, 2835, 10219, 0, 10);
		c.getPA().checkObjectSpawn(411, 2837, 10219, 0, 10);
		/*
		 * c.getPA().checkObjectSpawn(); c.getPA().checkObjectSpawn();
		 */

		c.getPA().checkObjectSpawn(14859, 2849, 10210, 0, 10); // Mining
		c.getPA().checkObjectSpawn(14859, 2850, 10210, 0, 10); // Mining
		c.getPA().checkObjectSpawn(14859, 2851, 10210, 0, 10); // Mining
		c.getPA().checkObjectSpawn(14859, 2852, 10210, 0, 10); // Mining
		c.getPA().checkObjectSpawn(14859, 2853, 10210, 0, 10); // Mining
		c.getPA().checkObjectSpawn(14859, 2854, 10210, 0, 10); // Mining

		c.getPA().checkObjectSpawn(1306, 2846, 10211, 0, 10); // Woodcutting
		c.getPA().checkObjectSpawn(1306, 2846, 10208, 0, 10);
		c.getPA().checkObjectSpawn(1306, 2846, 10204, 0, 10);
		c.getPA().checkObjectSpawn(1306, 2846, 10215, 0, 10);

		c.getPA().checkObjectSpawn(4875, 2842, 10214, 0, 10); // Thieving
		c.getPA().checkObjectSpawn(6163, 2842, 10212, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2842, 10207, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2842, 10203, 1, 10);

		// END OF SAMY'S CUSTOM ::DZONE
		// Samy sucks big massive fucking dick

		c.getPA().checkObjectSpawn(14367, 2955, 3499, 0, 10); // Bank Booth's
																// Between
																// Altars
		c.getPA().checkObjectSpawn(14367, 2956, 3499, 0, 10);

		c.getPA().checkObjectSpawn(104, 2953, 3507, 1, 10); // Donator Chest
		c.getPA().checkObjectSpawn(104, 3200, 3431, 1, 10); // Donator Chest

		// END OF SAMY'S CUSTOM ::SZONE

		// START OF SAMY'S CUSTOM WOODCUTTING ZONE

		c.getPA().checkObjectSpawn(1276, 2663, 3357, 1, 10); // Normal Tree
		c.getPA().checkObjectSpawn(1276, 2661, 3352, 0, 10);
		c.getPA().checkObjectSpawn(1276, 2658, 3354, 2, 10);
		c.getPA().checkObjectSpawn(1276, 2656, 3353, 3, 10);
		c.getPA().checkObjectSpawn(1276, 2658, 3350, 1, 10);
		c.getPA().checkObjectSpawn(1276, 2665, 3354, 0, 10);
		c.getPA().checkObjectSpawn(1276, 2665, 3356, 0, 10);
		c.getPA().checkObjectSpawn(1276, 2670, 3356, 2, 10);
		c.getPA().checkObjectSpawn(1276, 2668, 3352, 3, 10);
		c.getPA().checkObjectSpawn(1276, 2671, 3353, 1, 10);

		c.getPA().checkObjectSpawn(1281, 2659, 3355, 0, 10); // Oak
		c.getPA().checkObjectSpawn(1281, 2667, 3355, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2672, 3351, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2673, 3354, 0, 10);

		c.getPA().checkObjectSpawn(5551, 2674, 3347, 3, 10); // Willow
		c.getPA().checkObjectSpawn(5551, 2671, 3341, 2, 10);
		c.getPA().checkObjectSpawn(1308, 2673, 3343, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2671, 3345, 2, 10);

		c.getPA().checkObjectSpawn(1307, 2667, 3341, 1, 10); // Maple
		c.getPA().checkObjectSpawn(1307, 2665, 3344, 2, 10);
		c.getPA().checkObjectSpawn(1307, 2668, 3345, 3, 10);

		c.getPA().checkObjectSpawn(1309, 2656, 3340, 3, 10); // Yew
		c.getPA().checkObjectSpawn(1309, 2661, 3340, 2, 10);
		c.getPA().checkObjectSpawn(1309, 2660, 3343, 1, 10);

		c.getPA().checkObjectSpawn(1306, 2657, 3345, 0, 10); // Magic
		c.getPA().checkObjectSpawn(1306, 2657, 3348, 0, 10);

		c.getPA().checkObjectSpawn(-1, 2668, 3356, 0, 10); // Removal of Objects
		c.getPA().checkObjectSpawn(-1, 2674, 3351, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2665, 3355, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2674, 3345, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2666, 3357, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2669, 3342, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2668, 3340, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2667, 3342, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2660, 3340, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2659, 3342, 0, 10);

		// END OF SAMY'S CUSTOM WOODCUTTING ZONE

		// START OF FARMIMG REMOVING PATCHES
		c.getPA().checkObjectSpawn(-1, 2809, 3463, 0, 10); // Farming patch
															// remove THE SINGLE
															// ONE
		c.getPA().checkObjectSpawn(-1, 2806, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2819, 3463, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3466, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3466, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2804, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2804, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2803, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2803, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2809, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2809, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2808, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2808, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2807, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2807, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2814, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2814, 3468, 0, 10);

		c.getPA().checkObjectSpawn(-1, 2813, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2813, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2812, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2812, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2810, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2810, 3468, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2811, 3467, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2811, 3468, 0, 10);

		c.getPA().checkObjectSpawn(-1, 2808, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2808, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2807, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2807, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3461, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2806, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3461, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2805, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2804, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2804, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2812, 3459, 0, 10); // Farming patch
															// remove
		c.getPA().checkObjectSpawn(-1, 2812, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2811, 3460, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2811, 3459, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2809, 3460, 0, 10); // Farming patch
															// remove
		c.getPA().checkObjectSpawn(-1, 2813, 3460, 0, 10); // Farming patch
															// remove
		c.getPA().checkObjectSpawn(-1, 2813, 3459, 0, 10); // Farming patch
															// remove
		c.getPA().checkObjectSpawn(-1, 2810, 3460, 0, 10); // Farming patch
															// remove
		c.getPA().checkObjectSpawn(-1, 2810, 3459, 0, 10); // Farming patch
															// remove
		c.getPA().checkObjectSpawn(-1, 2814, 3460, 0, 10); // Farming patch
															// remove
		c.getPA().checkObjectSpawn(-1, 2814, 3459, 0, 10); // Farming patch
															// remove
		c.getPA().checkObjectSpawn(-1, 2809, 3459, 0, 10); // Farming patch
															// remove
		c.getPA().checkObjectSpawn(-1, 2819, 3462, 0, 10); // Farming table
															// remove
		// END OF FARMING
		c.getPA().checkObjectSpawn(2644, 2737, 3445, 0, 10); // spinning wheel
		// DUEL ARENA FIX

		// new home
		c.getPA().checkObjectSpawn(11402, 2587, 2845, 1, 10); // bank
		c.getPA().checkObjectSpawn(11402, 2587, 2844, 1, 10); // bank
		c.getPA().checkObjectSpawn(11402, 2587, 2843, 1, 10); // bank
		c.getPA().checkObjectSpawn(11402, 2587, 2842, 1, 10); // bank
		c.getPA().checkObjectSpawn(11402, 2587, 2841, 1, 10); // bank
		c.getPA().checkObjectSpawn(11402, 2587, 2840, 1, 10); // bank
		c.getPA().checkObjectSpawn(11402, 2587, 2839, 1, 10); // bank
		c.getPA().checkObjectSpawn(2783, 2578, 2845, 1, 10); // bank
		c.getPA().checkObjectSpawn(11666, 2583, 2840, 0, 10); // bank

		c.getPA().checkObjectSpawn(2732, 2575, 2842, 1, 10); // bank

		c.getPA().checkObjectSpawn(409, 2546, 2842, 2, 10); // bank
		c.getPA().checkObjectSpawn(411, 2540, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(172, 2554, 2842, 2, 10); // bank
		c.getPA().checkObjectSpawn(27568, 2541, 2837, 2, 10); // bank

		c.getPA().checkObjectSpawn(1276, 2571, 2840, 2, 10); // bank
		c.getPA().checkObjectSpawn(1276, 2571, 2842, 2, 10); // bank
		c.getPA().checkObjectSpawn(1281, 2571, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(1281, 2571, 2844, 2, 10); // bank

		c.getPA().checkObjectSpawn(11933, 2584, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(11936, 2583, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(11933, 2582, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(11936, 2581, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(11933, 2582, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(11936, 2580, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(11933, 2579, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(11936, 2578, 2843, 2, 10); // bank
		c.getPA().checkObjectSpawn(11933, 2579, 2843, 2, 10); // bank

		c.getPA().checkObjectSpawn(-1, 2573, 2840, 0, 10); // bank
		c.getPA().checkObjectSpawn(-1, 2570, 2845, 0, 10); // bank
		c.getPA().checkObjectSpawn(-1, 2569, 2843, 0, 10); // bank
		c.getPA().checkObjectSpawn(-1, 2569, 2840, 0, 10); // bank
		// new home end

		// varrock
		// varrock home
		// c.getPA().checkObjectSpawn(15478, 3195, 3432, 2, 10); // Con Portal
		c.getPA().checkObjectSpawn(881, 3222, 3438, 2, 10); // sewer entrance
															// entrance
		c.getPA().checkObjectSpawn(-1, 3207, 3435, 2, 10); // remove stall
		c.getPA().checkObjectSpawn(-1, 3221, 3431, 2, 10); // remove stall
		c.getPA().checkObjectSpawn(-1, 3221, 3432, 2, 10); // remove stall
		c.getPA().checkObjectSpawn(-1, 3219, 3485, 2, 10); // remove stall
		c.getPA().checkObjectSpawn(-1, 3216, 3436, 2, 10); // VARROCK
		// c.getPA().checkObjectSpawn(14367, 3212, 3439, 2, 10); // VARROCk
		// HOMEBANK 1
		// c.getPA().checkObjectSpawn(14367, 3213, 3439, 2, 10); // VARROCk
		// HOMEBANK 2
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(-1, 3217, 3436, -1, 10); // remvoe portal
		c.getPA().checkObjectSpawn(-1, 3220, 3437, -1, 10); // remvoe stall
		c.getPA().checkObjectSpawn(-1, 3220, 3435, -1, 10); // remvoe stall
															// altar
		c.getPA().checkObjectSpawn(172, 3205, 3431, 1, 10); // crystal chest //
															// altar
		c.getPA().checkObjectSpawn(8151, 2809, 3463, 1, 10); // farming patch //
																// altar
		// varrock home end

		// lumby home
		c.getPA().checkObjectSpawn(409, 3215, 3251, 1, 10); // Normal praying //
															// altar
		c.getPA().checkObjectSpawn(172, 3215, 3253, 1, 10); // crystal chest
		c.getPA().checkObjectSpawn(411, 3215, 3254, 1, 10); // Curse Prayers
		c.getPA().checkObjectSpawn(27568, 3219, 3237, 2, 10); // portal
		c.getPA().checkObjectSpawn(6552, 3211, 3245, 2, 10); // ancient
		c.getPA().checkObjectSpawn(410, 3209, 3244, 2, 10); // lunar
		// end

		// CUSTOM EDGE OBJECTS
		c.getPA().checkObjectSpawn(411, 3099, 3488, 1, 10); // Curse Prayers
		c.getPA().checkObjectSpawn(409, 3099, 3495, 1, 10); // Normal praying
															// altar
		c.getPA().checkObjectSpawn(410, 3104, 3496, 3, 10); // lunar altar
		c.getPA().checkObjectSpawn(6552, 3099, 3491, 1, 10); // ancient altar

		c.getPA().checkObjectSpawn(7315, 3093, 3482, 1, 10);
		c.getPA().checkObjectSpawn(7315, 3094, 3482, 1, 10);
		c.getPA().checkObjectSpawn(7316, 3090, 3478, 1, 10);
		c.getPA().checkObjectSpawn(7316, 3097, 3478, 1, 10);

		c.getPA().checkObjectSpawn(1032, 3096, 3482, 0, 10);
		c.getPA().checkObjectSpawn(1032, 3091, 3482, 0, 10);
		c.getPA().checkObjectSpawn(1032, 3091, 3482, 0, 10);

		// END OF EDGE OBJECTS
		c.getPA().checkObjectSpawn(4874, 3094, 3500, 1, 10);// thiev staff
		c.getPA().checkObjectSpawn(4875, 3095, 3500, 1, 10);// thiev stall
		c.getPA().checkObjectSpawn(4876, 3096, 3500, 0, 10);// thiev stall
		c.getPA().checkObjectSpawn(4877, 3097, 3500, 0, 10);// thiev stall
		c.getPA().checkObjectSpawn(4878, 3098, 3500, 0, 10);// thiev stall
		c.getPA().checkObjectSpawn(-1, 3096, 3501, 0, 10);
		// dung remove rope
		c.getPA().checkObjectSpawn(-1, 3508, 9494, -1, 10); // remvoe portal
		c.getPA().checkObjectSpawn(-1, 3508, 9494, -2, 10); // remvoe portal
		// start of RC altar fix
		c.getPA().checkObjectSpawn(-1, 2841, 4828, -1, 10); // remvoe portal
		// END OF RUNECRAFT
		c.getPA().checkObjectSpawn(-1, 2574, 4850, -1, 10); // REMOVE fire altar
															// teleport rc
		c.getPA().checkObjectSpawn(-1, 3090, 3503, -1, 10); // REMOVE DEPOSIT
															// BOX
		c.getPA().checkObjectSpawn(-1, 3095, 3499, -1, 10); // REMOVE CHAIR IN
															// BANK
		c.getPA().checkObjectSpawn(-1, 3095, 3498, -1, 10); // REMOVE TABLE IN
															// BANK
		c.getPA().checkObjectSpawn(-1, 3098, 3496, -1, 10); // REMOVE TREE AT
															// HOME
		// START OF NOMAD MINIGAME
		c.getPA().checkObjectSpawn(400, 3487, 9948, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3488, 9948, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3493, 9938, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3493, 9937, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3482, 9937, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3481, 9937, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3480, 9937, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3477, 9945, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3477, 9944, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(400, 3477, 9943, 1, 10); // Nomad gravestones
		c.getPA().checkObjectSpawn(1, 2933, 9654, 1, 10); // Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2933, 9655, 1, 10); // Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2934, 9648, 1, 10); // Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2500, 3018, 1, 10); // Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2501, 3018, 1, 10); // Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2505, 3012, 1, 10); // Nomad CRATE
		c.getPA().checkObjectSpawn(1, 2505, 3011, 1, 10); // Nomad CRATE

		c.getPA().checkObjectSpawn(11, 2498, 3014, 1, 10); // Nomad SPIRIT TREE
		// END OF NOMAD MINIGAME
		// remoev edge well
		c.getPA().checkObjectSpawn(-1, 3084, 3502, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3085, 3502, 1, 10);
		// removed
		// START OF GOBLIN MINIGAME ANGRY
		c.getPA().checkObjectSpawn(672, 2539, 3032, 1, 10); // GOBLIN BARREL
		c.getPA().checkObjectSpawn(718, 2539, 3033, 1, 10); // GOBLIN BARREL
		c.getPA().checkObjectSpawn(2024, 2540, 3036, 1, 10); // GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2541, 3036, 1, 10); // GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2540, 3029, 1, 10); // GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2541, 3029, 1, 10); // GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2541, 3029, 1, 10); // GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2542, 3029, 1, 10); // GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2543, 3029, 1, 10); // GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2544, 3029, 1, 10); // GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2545, 3029, 1, 10); // GOBLIN ESCAPE
		c.getPA().checkObjectSpawn(2024, 2540, 3035, 1, 10); // GOBLIN ESCAPE
		// END OF GOBLIN MINIGAME
		c.getPA().checkObjectSpawn(4151, 2605, 3153, 1, 10); // portal home

		c.getPA().checkObjectSpawn(195, 2980, 5111, 0, 10); // escape ladder
		c.getPA().checkObjectSpawn(4412, 2867, 9527, 0, 10); // escape ladder
		c.getPA().checkObjectSpawn(4412, 3234, 9327, 0, 10); // escape ladder
		c.getPA().checkObjectSpawn(4412, 2387, 4721, 0, 10); // escape ladder
		c.getPA().checkObjectSpawn(4412, 2429, 4680, 0, 10); // escape ladder
		// start of dungkill
		c.getPA().checkObjectSpawn(3379, 2410, 3531, 0, 10); // dung kill cave

		// ore
		c.getPA().checkObjectSpawn(2094, 3032, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2094, 3033, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2091, 3034, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2091, 3035, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2092, 3036, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2092, 3037, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2103, 3038, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2103, 3039, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2097, 3040, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2097, 3041, 9836, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3042, 9836, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3043, 9836, 0, 10);
		c.getPA().checkObjectSpawn(3044, 3036, 9831, -1, 10);
		c.getPA().checkObjectSpawn(2213, 3037, 9835, -1, 10);
		c.getPA().checkObjectSpawn(2783, 3034, 9832, 0, 10);

		c.getPA().checkObjectSpawn(12356, 3219, 9622, 0, 10);// rfd portal
		c.getPA().checkObjectSpawn(2403, 3219, 9621, 0, 10);// rfd chest
		c.getPA().checkObjectSpawn(-1, 3216, 9621, 2, 10);// rfd chest

		c.getPA().checkObjectSpawn(6163, 2029, 4527, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2029, 4529, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2029, 4531, 1, 10);

		c.getPA().checkObjectSpawn(410, 2864, 2955, 1, 10);

		// CAMELOT WOODCUTTING
		c.getPA().checkObjectSpawn(1281, 2712, 3465, 0, 10);// OAK TREE CAMELOT
															// WC
		c.getPA().checkObjectSpawn(1277, 2709, 3466, 0, 10);// NORMAL TREE
															// CAMELOT WC
		c.getPA().checkObjectSpawn(1306, 2710, 3459, 0, 10);// MAGIC TREE

		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);
		assault(c);
		if (c.heightLevel == 0) {
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		} else {
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
		}
		c.getPA().checkObjectSpawn(-1, 2794, 9327, 1, 10);
	}

	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60
				&& c.heightLevel == o.height;
	}

	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o, c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face,
						o.type);
		}
		loadCustomSpawns(c);
		handleCannon(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			// c.getFarming().updateHerbPatch();
		}
	}

	public boolean objectExists(final int x, final int y) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y) {
				return true;
			}
		}
		return false;
	}

	public void placeObject(Object o) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client) PlayerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face,
							o.type);
			}
		}
	}

	public void process() {
		for (final Object o : objects) {
			if (o.tick > 0) {
				o.tick--;
			} else {
				updateObject(o);
				toRemove.add(o);
			}
		}
		for (final Object o : toRemove) {
			if (o.objectId == 2732) {
				for (final Player player : PlayerHandler.players) {
					if (player != null) {
						final Client c = (Client) player;
						Server.itemHandler.createGroundItem(c, 592, o.objectX,
								o.objectY, 1, c.playerId);
					}
				}
			}
			if (isObelisk(o.newId)) {
				final int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);
		}
		toRemove.clear();
	}

	public void removeObject(int x, int y) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client) PlayerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);
			}
		}
	}

	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0],
						obeliskCoords[index][1], 0, -1, 10, obeliskId, 16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4,
						obeliskCoords[index][1], 0, -1, 10, obeliskId, 16));
				addObject(new Object(14825, obeliskCoords[index][0],
						obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId, 16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4,
						obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId, 16));
			}
		}
	}

	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client) PlayerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(),
						obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2,
						1)) {
					c.getPA().startTeleport2(
							obeliskCoords[random][0] + xOffset,
							obeliskCoords[random][1] + yOffset, 0);
				}
			}
		}
	}

	public void updateObject(Object o) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client) PlayerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);
			}
		}
	}

}