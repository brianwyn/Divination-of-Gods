package server.model.players.content.skills;

import server.model.items.ItemAssistant;
import server.model.players.Client;
import server.util.Misc;

public class Fishing {

	private Client c;

	public Fishing(Client c) {
		this.c = c;
	}

	public void FishingProcess() {
		// Fishing <3
		if (c.fishtimer > 0) {
			c.fishtimer--;
		}

		if (c.fishing && c.getItems().freeSlots() <= 0) {
			c.fishing = false;
			c.sendMessage("Your inventory is full");
			c.frame1();
		}
		if (c.fishing && c.fishtimer <= 0 && c.getItems().freeSlots() > 0) {
			if (c.getItems().playerHasItem(c.fishitem)) {
				if (c.playerLevel[10] >= c.fishreqt) {
					if (c.fishitem == 307 && !c.getItems().playerHasItem(313)) {
						c.sendMessage("You need bait to fish here!");
						c.fishing = false;
					} else if (c.fishitem == 309
							&& !c.getItems().playerHasItem(314)) {
						c.sendMessage("You need feathers to fish here!");
						c.fishing = false;
					} else {
						if (c.fishreq2 != 0 && c.playerLevel[10] >= c.fishreq2
								&& Misc.random(1) == 1) {
							c.getItems().addItem(c.fishies2, 1);
							c.getPA().addSkillXP(c.fishXP, 10);
						} else {
							c.getItems().addItem(c.fishies, 1);
							c.getPA().addSkillXP(c.fishXP, 10);
						}
						if (c.fishitem == 307)
							c.getPA().addSkillXP(c.fishXP, 10);
						c.fishtimer = Misc.random(fishtime(c.fishies,
								c.fishreqt));
					}
				} else {
					c.fishing = false;
					c.sendMessage("You need a fishing level of " + c.fishreqt
							+ " to fish for "
							+ ItemAssistant.getItemName(c.fishies));
					c.stopPlayerSkill = false;
				}
			} else {
				c.fishing = false;
				c.sendMessage("You need a "
						+ ItemAssistant.getItemName(c.fishitem) + " to fish "
						+ ItemAssistant.getItemName(c.fishies));
				c.stopPlayerSkill = false;
			}
		}

		if (c.fishing) {
			c.startAnimation(c.fishemote);
			c.stopPlayerSkill = true;
			// frame174(378, 3);
		}
		if (c.attemptingfish)
			if (c.clickObjectType > 0
					&& c.goodDistance(c.objectX + c.objectXOffset, c.objectY
							+ c.objectYOffset, c.getX(), c.getY(),
							c.objectDistance)) {
				c.attemptingfish = false;
				c.fishing = true;
				c.stopPlayerSkill = true;
			}
	}

	public int fishtime(int fish, int req) {
		int time = 10;
		if (fish == 317) {// Shrimp 1
			time = 20;
		}
		if (fish == 327) {// Sardine 5
			time = 25;
		}
		if (fish == 355) {// Trout 20
			time = 30;
		}
		if (fish == 341) {// Cods 23
			time = 35;
		}
		if (fish == 349) {// Pike 25
			time = 38;
		}
		if (fish == 359) {// Tuna 35
			time = 40;
		}
		if (fish == 377) {// Lobsters 40
			time = 45;
		}
		if (fish == 383) {// Sharks 76
			time = 50;

		}
		int LevelXP = c.playerLevel[10] - req;
		if (LevelXP > req / 3)
			LevelXP = req / 3;
		time -= LevelXP;
		return time;
	}

	public void resetFishing() {
		c.fishtimer = -1;
		c.fishing = false;
	}
}