package server.model.players.content.skills;

import server.Config;
import server.model.players.Client;
import server.model.players.Player;
import server.util.Misc;

/**
 * Farming Class
 * 
 * @author Josh© The class from the video, <3
 **/
public class Farming {

	private Client c;

	private final static int[] VALID_SEEDS = { 5291, 5292, 5293, 5294, 5295,
			5296, 5297, 5298, 5299, 5300, 5301, 5302, 5303, 5304 };
	private final static int[] HERBS = { 199, 201, 203, 205, 207, 3049, 209,
			211, 213, 3051, 215, 2485, 217, 219 };
	private final static int[] SEED_PLANT_EXP = { 11, 14, 16, 22, 27, 34, 43,
			55, 69, 88, 107, 135, 171, 200 };
	private final static int[] HERB_EXPS = { 13, 15, 18, 24, 31, 39, 49, 62,
			78, 99, 120, 152, 192, 225 };
	private final static int[] FARMING_REQS = { 1, 14, 19, 26, 32, 38, 44, 50,
			56, 62, 67, 73, 79, 85 };
	/* all of our arrays are fine */

	/**
	 * Single integars
	 */
	private final static int PATCH_HERBS = 8143;
	private final static int PATCH_CLEAN = 8132;
	private final static int PATCH_WEEDS = 8389;
	private final static int WATER_CAN = 5340;
	private final static int RAKE = 5341;
	private final static int SEED_DIBBER = 5343;

	/* all of our static integers are fine */

	private boolean seedPlanted = false, seedWatered = false,
			patchRaked = false, patchCleaned = false;

	/* all booleans are false, cool */

	/**
	 * Constructor
	 */
	public Farming(Client c) {
		this.c = c;
	}

	public void checkItemOnObject(int itemId) {
		for (int j = 0; j < VALID_SEEDS.length; j++) {
			if (itemId == VALID_SEEDS[j]) {
				plantSeed(VALID_SEEDS[j], HERBS[j], HERB_EXPS[j], j);
			} // so if you use valid seeds on the patch it does plantSeed, cool.
		}
		if (itemId == WATER_CAN && !seedWatered) {
			waterSeed(); // watering is called
		} else if (itemId == RAKE && !patchRaked) {
			rakePatch(); // raking is called
		} else if (c.getItems().playerHasItem(SEED_DIBBER, 0))
			;
		{
			c.sendMessage("You need a seed dibber to plant seeds."); // if you
																		// dont
																		// have
																		// a
																		// seed
																		// dibber
																		// it
																		// gets
																		// mad
																		// at
																		// you.
		}

	}

	private void cleanPatch() {
		if (!patchCleaned) {
			c.getPA().object(PATCH_CLEAN, 2813, 3463, -1, 10);
			patchCleaned = true;
		} else {
			c.sendMessage("You have already cleaned the patch.");
		}
	}

	/* the old handlefarming method, works fine */

	/* seed watering, cool */
	public int getExp() {
		for (int j = 0; j < HERBS.length; j++) {
			if (HERBS[j] == c.farm[0]) {
				return HERB_EXPS[j];
			}
		}
		return 0;
	}

	public void pickHerb() { // / Final step, picks the herbs from the grown
								// herb patch.
		if (c.farm[0] > 0 && c.farm[1] > 0) {
			if (System.currentTimeMillis() - c.alchDelay > 2000) {
				if (c.getItems().addItem(c.farm[0], 1)) {
					c.getPA().addSkillXP(getExp() * Config.FARMING_EXPERIENCE,
							Player.playerFarming);
					c.farm[1]--;
					if (c.farm[1] == 0) {
						c.farm[0] = -1;
					}
					c.startAnimation(2286);
					c.sendMessage("You pick a herb.");
					updateHerbPatch(); // finish, lets try this.
				}
			}
		}
	}

	/* this works awesome!!! */

	private void plantSeed(int seedId, int herbId, int exp, int slot) {
		if (c.playerLevel[Player.playerFarming] < FARMING_REQS[slot]) {
			c.sendMessage("You require a farming level of "
					+ FARMING_REQS[slot] + " to farm this seed.");
		} else if (!seedPlanted && patchRaked
				&& c.getItems().playerHasItem(seedId, 1)
				&& c.getItems().playerHasItem(SEED_DIBBER, 1)) {
			c.getItems()
					.deleteItem(seedId, c.getItems().getItemSlot(seedId), 1);
			c.getPA().addSkillXP(
					SEED_PLANT_EXP[slot] * Config.FARMING_EXPERIENCE,
					Player.playerFarming);
			c.startAnimation(2291);
			c.getPA().refreshSkill(Player.playerFarming);
			int herbAmount = Misc.random(5) + 3;
			c.farm[0] = herbId;
			c.farm[1] = herbAmount;
			c.sendMessage("You plant your seed. Water it and watch it grow.");
			seedPlanted = true;
		} else {
			c.sendMessage("You need to rake the patch or you've already planted a seed!");
		}
	}

	/* cleans off the patch during raking, awesome */

	/* updates to herb or weed. the clean patch should be part too but fuck it */
	private void rakePatch() { // /step three, once you've planted and watered
								// you can rake the patch clean to pick your
								// herbs.
		if (System.currentTimeMillis() - c.alchDelay > 2000) {
			c.startAnimation(2273);
			cleanPatch(); // calls the clear patch.
			c.getPA().addSkillXP(getExp() * Config.FARMING_EXPERIENCE,
					Player.playerFarming);
			patchRaked = true;
		} else {
			c.sendMessage("You must plant and water a seed before you can rake here!");
		}
	}

	public void updateHerbPatch() {
		if (c.farm[0] > 0 && c.farm[1] > 0) {
			c.getPA().object(PATCH_HERBS, 2813, 3463, -1, 10);
		} else {
			c.getPA().object(PATCH_WEEDS, 2813, 3463, -1, 10);
			patchRaked = false;
			seedWatered = false;
			seedPlanted = false;
			patchCleaned = false;
		}
	}

	private void waterSeed() {
		if (seedPlanted && !seedWatered) {
			c.startAnimation(2293);
			updateHerbPatch();
			seedWatered = true;
		} else {
			c.sendMessage("You must plant a seed before you can water the patch!");
		}
	}
}