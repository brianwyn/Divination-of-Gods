package server.model.players.content;

import server.model.players.Client;

public class PointsHandler {

	public static void handleEP(Client c) {
		if (c.earningPotential == 0 || c.earningPotential < 0) {
			c.getPA().sendFrame126("@gre@Earning Potential: @yel@0", 6974);
		} else {
			c.getPA().sendFrame126(
					"@gre@Earning Potential: @yel@" + c.earningPotential + "",
					6974);
		}
	}

	/***/
	/****
	 * Used for handling the Points Interface. // Gabbe
	 ***/
	public static void run(Client c) {
		handleEP(c);
		c.getPA().showInterface(6965);
		c.getPA().sendFrame126("@red@~ Points Handler ~", 6968);
		c.getPA().sendFrame126("@gre@Voting Points: @yel@" + c.votePoints + "",
				6969);
		c.getPA().sendFrame126("@gre@Agility Points: @yel@" + c.SPoints + "",
				6970);
		c.getPA().sendFrame126(
				"@gre@Dungeoneering Tokens: @yel@" + c.dungPoints + " ", 6971);
		c.getPA().sendFrame126(
				"@gre@Assault Points: @yel@" + c.barbPoints + " ", 6972);
		c.getPA().sendFrame126(
				"@gre@Slayer Points: @yel@" + c.slayerPoints + "", 6973);
		c.getPA().sendFrame126(
				"@gre@Slayer Task Streak: @yel@" + c.slayerTaskStreak + "",
				6974);
		c.getPA().sendFrame126(
				"@gre@Dominion Points: @yel@" + c.dominionPoints + "", 6975);
		c.getPA().sendFrame126("", 6976);
		c.getPA().sendFrame126("", 6977);
		c.getPA().sendFrame126("", 6978);
		c.getPA().sendFrame126("", 6979);
		c.getPA().sendFrame126("", 6980);
	}
}