package server.model.players.content.skills;

import server.model.players.Client;
import server.model.players.Player;

public class Agility {

	public static void AgilityWall(Client c, int level) { // agility course barb
		if (c.playerLevel[Player.playerAgility] < 80) {
			c.sendMessage("You need a Agility level of " + 80
					+ " to pass this object.");
			return;
		}

		if (c.absX == 2535 && c.absY == 3553) { // First wall
			c.obsticle(839, 40, 2, 0, 1200, 800,
					"You passed the obsticle succesfully.");
			c.ag5 = 1;
		}
		if (c.absX == 2538 && c.absY == 3553) { // First wall
			c.obsticle(839, 40, 2, 0, 1200, 800,
					"You passed the obsticle succesfully.");
			c.ag6 = 1;
		}
		if (c.absX == 2541 && c.absY == 3553) { // First wall
			if (c.ag1 == 1 && c.ag2 >= 1 && c.ag3 >= 1 && c.ag4 >= 1
					&& c.ag5 >= 1 && c.ag6 >= 1) {
				c.obsticle(839, 40, 2, 0, 1200, 800,
						"You passed the obsticle succesfully.");
				c.turnPlayerTo(c.getX(), c.getY() + 1);
				if (c.playerLevel[Player.playerAgility] >= 80)
					c.getPA().addSkillXP(32000, Player.playerAgility);
				else if (c.playerLevel[Player.playerAgility] >= 95)
					c.getPA().addSkillXP(39000, Player.playerAgility);
				c.getPA().refreshSkill(Player.playerAgility);
				c.sendMessage("You completed the course and been rewarded with 12 Agility Points!");
				c.SPoints += 12;
				c.ag1 = 0;
				c.ag2 = 0;
				c.ag3 = 0;
				c.ag4 = 0;
				c.ag5 = 0;
				c.ag6 = 0;
			} else {
				c.obsticle(839, 40, 2, 0, 1200, 800,
						"You passed the obsticle succesfully.");
				c.turnPlayerTo(c.getX(), c.getY() + 1);
				c.sendMessage("Next time finish the course, you are not getting any Points/exp.");
				c.ag1 = 0;
				c.ag2 = 0;
				c.ag3 = 0;
				c.ag4 = 0;
				c.ag5 = 0;
				c.ag6 = 0;
			}
		}
	}

	public Client client;
	public int agtimer = 10;
	public int ticketsid = 2996;
	public boolean bonus = false;

	public boolean isBalance = false;

	/**/
	/***
	 * Agility bugs fixed, 100% Gnome & Barbarien course bug free! /
	 **/
	public Agility(Client c) {
		client = c;
	}

	// Agility Branch make sure to set the correct exp's in the object
	public void AgilityBranch(Client c, String branch, int level, int x, int y,
			int h, int a, int b, int emote, int xp) {

		if (c.playerLevel[Player.playerAgility] < level) {
			c.sendMessage("You need a Agility level of " + level
					+ " to pass this " + branch + ".");
			return;
		}

		if (c.absX == a && c.absY == b) {
			c.teleportToX = x;
			c.teleportToY = y;
			c.heightLevel = h;
			c.getPA().addSkillXP(xp, Player.playerAgility);
			c.getPA().refreshSkill(Player.playerAgility);
		}
	}

	// Start of the Agility Logs
	public void AgilityLog(final Client c, String Object, int level,
			final int x, final int y, final int a, final int b, final int xp) {

		if (c.playerLevel[Player.playerAgility] < level) {
			c.sendMessage("You need a Agility level of " + level
					+ " to pass this " + Object + ".");
			return;
		}
		if (c.absY == 3429) {
			c.isWalkingAgilityLog = false;
		} else {
			if (c.absX == a && c.absY == b) {
				c.isWalkingAgilityLog = true;
				c.stopPlayerSkill = true;
				c.getPA().addSkillXP(xp, Player.playerAgility);
				c.getPA().refreshSkill(Player.playerAgility);
				c.fmwalkto22(x, y);
				if (c.absY == 3429) {
					c.isWalkingAgilityLog = false;
				}
			}
		}
	}

	public void AgilityNet(Client c, String net, int level, int a, int b,
			int h, int x, int y, int emote, int xp) {

		if (c.playerLevel[Player.playerAgility] < level) {
			c.sendMessage("You need a Agility level of " + level
					+ " to pass this " + net + ".");
			return;
		}
		if (c.absX == a && c.absY == b) {
			// Now we shall Call the X and Y points
			if (c.absX > 2470 && c.absX <= 2477) {
				c.getPA().movePlayer(2473, 3423, 1);
			} else if (c.absX > 2477) {
				c.getPA().movePlayer(x, y, h);
			}
			/*
			 * c.teleportToX = x; c.teleportToY = y; c.heightLevel = h;
			 */
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.getPA().addSkillXP(xp, Player.playerAgility);
			c.getPA().refreshSkill(Player.playerAgility);
			c.turnPlayerTo(c.getX() - 1, c.getY());
		}
	}

	public void AgilityPipe(Client c, String pipe, int level, int a, int b,
			int x, int y, int add, int amount, int xp) {

		if (c.playerLevel[Player.playerAgility] < level) {
			c.sendMessage("You need a Agility level of " + level
					+ " to pass this " + pipe + ".");
			return;
		}
		// if (c.absX == a && c.absY == b) {
		if (c.absX == 2484 || c.absX == 2487) {
			if (bonus && c.ag1 == 1 && c.ag2 >= 1 && c.ag3 >= 1 && c.ag4 >= 1
					&& c.ag5 >= 1 && c.ag6 >= 1) {
				c.isGoingThroughPipe = true;
				c.stopPlayerSkill = true;
				c.fmwalkto22(x, y);
				c.turnPlayerTo(c.getX(), c.getY() + 1);
				if (c.playerLevel[Player.playerAgility] < 25)
					c.getPA().addSkillXP(7000, Player.playerAgility);
				else if (c.playerLevel[Player.playerAgility] >= 25
						&& c.playerLevel[Player.playerAgility] < 50)
					c.getPA().addSkillXP(14000, Player.playerAgility);
				else if (c.playerLevel[Player.playerAgility] >= 50
						&& c.playerLevel[Player.playerAgility] < 75)
					c.getPA().addSkillXP(21000, Player.playerAgility);
				else if (c.playerLevel[Player.playerAgility] >= 75
						&& c.playerLevel[Player.playerAgility] < 95)
					c.getPA().addSkillXP(28000, Player.playerAgility);
				else if (c.playerLevel[Player.playerAgility] >= 95)
					c.getPA().addSkillXP(35000, Player.playerAgility);
				c.getPA().refreshSkill(Player.playerAgility);
				c.sendMessage("You completed the course and been rewarded with 7 Agility Points and 1 ticket!");
				c.SPoints += 7;
				bonus = false;
				c.ag1 = 0;
				c.ag2 = 0;
				c.ag3 = 0;
				c.ag4 = 0;
				c.ag5 = 0;
				c.ag6 = 0;
				c.getItems().addItem(ticketsid, 1);
			} else {
				c.isGoingThroughPipe = true;
				c.fmwalkto22(x, y);
				c.getPA().addSkillXP(xp, Player.playerAgility);
				c.getPA().refreshSkill(Player.playerAgility);
				c.turnPlayerTo(c.getX(), c.getY() + 1);
				bonus = false;
				c.sendMessage("Next time finish the course, you are not getting any tickets nor exp.");
				c.ag1 = 0;
				c.ag2 = 0;
				c.ag3 = 0;
				c.ag4 = 0;
				c.ag5 = 0;
				c.ag6 = 0;
				c.random = 4; // Makes sure that the next time the random event
								// timer hits 15 minutes a random event will be
								// started.
			}
		} else {
			c.sendMessage("Please stand infront of the pipe before doing this.");
			return;
		}
	}

	public void AgilityRope(final Client c, String Object, int level,
			final int x, final int y, final int a, final int b, final int xp) {

		if (c.playerLevel[Player.playerAgility] < level) {
			c.sendMessage("You need a Agility level of " + level
					+ " to pass this " + Object + ".");
			return;
		}
		if (c.absX == 2483) {
			c.isWalkingAgilityLog = false;
		} else {
			if (c.absX == a && c.absY == b) {
				c.isWalkingAgilityRope = true;
				c.stopPlayerSkill = true;
				c.getPA().addSkillXP(xp, Player.playerAgility);
				c.getPA().refreshSkill(Player.playerAgility);
				c.fmwalkto22(x, y);
				if (c.absX == 2483) {
					c.isWalkingAgilityRope = false;
				}
			}
		}
	}

	public void AgilityTicketCounter(Client c, String ticket, int remove,
			int amount, int xp) {
		if (c.getItems().playerHasItem(2996)) {
			if (ticket.equals("1")) {
				c.sendMessage("This has been disabled");
			}
		}
		if (c.getItems().playerHasItem(2996, 10)) {
			if (ticket.equals("10")) {
				c.sendMessage("This has been disabled");
			}
		}
		if (c.getItems().playerHasItem(2996, 25)) {
			if (ticket.equals("25")) {
				c.sendMessage("This has been disabled");
			}
		}
		if (c.getItems().playerHasItem(2996, 100)) {
			if (ticket.equals("100")) {
				c.sendMessage("This has been disabled");
			}
		}
		if (c.getItems().playerHasItem(2996, 1000)) {
			if (ticket.equals("1000")) {
				c.sendMessage("This has been disabled");
			}
		} else {
			c.sendMessage("You need more agility tickets to get this Exp!");
			return;
		}
	}

}