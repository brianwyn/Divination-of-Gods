package server.model.players.content;

import server.Server;
import server.model.players.Client;
import server.util.Misc;

public class EarningPotential {

	private static int[][] EPDrops = { { 14876 }, { 14877 }, { 13851 },
			{ 14879 }, { 14880 }, { 14881 }, { 14882 }, { 14883 }, { 14884 },
			{ 14885 }, { 14886 }, { 14887 }, { 13851 }, { 14890 }, { 14890 },
			{ 14891 }, { 14892 }, { 13857 }, { 14892 } };

	/***/
	/****
	 * Redone by Gabbe, also only EP drops are now brawling gloves and Statues,
	 * no PVP ARMOURS!
	 ***/
	public static void checkPotential(Client c) {
		if (c.inWild()) {
			if (c.epDelay == 450) {
				if (!c.inFunPk()) {
					if (c.getItems().getCarriedWealth() > 5000000
							|| c.getPA().getWearingAmount2() > 200000) {
						c.earningPotential += 13 + Misc.random(12);
						if (c.earningPotential > 100)
							c.earningPotential = 100;
						c.sendMessage("Your EP increases to: "
								+ c.earningPotential + ".");
					} else {
						c.sendMessage("<shad=77777>You must risk more than 5M to increase your EP!");
					}
				}
				c.epDelay = 0;
			}
		}
	}

	public static void checkTeleport(Client c) {
		if (c.inWild())
			if (c.underAttackBy > 0) {
				c.earningPotential -= 10 + Misc.random(5);
				c.sendMessage("You now have " + c.earningPotential
						+ " EP since you teleported in combat!");
				if (c.earningPotential < 0)
					c.earningPotential = 0;
			}
	}

	public static void giveBonusDrops(Client c, Client c2) {
		if (c.inWild() && c2.inWild()) {
			if (c2.getItems().getCarriedWealth() > 300000
					|| c2.getPA().getWearingAmount2() > 200000) {
				if (c.earningPotential >= 60) {
					c.earningPotential -= 35;
					int random = (int) (Math.random() * (EPDrops.length - 1));
					Server.itemHandler.createGroundItem(c, EPDrops[random][0],
							c2.absX, c2.absY, 1, c.playerId);
					c.sendMessage("You've gotten an EP Drop!!");
					c.sendMessage("Your EP decreased to: " + c.earningPotential
							+ ".");
				}
			}
		}
	}
}