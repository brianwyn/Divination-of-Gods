package server.model.players.content;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.items.Item;
import server.model.players.Client;
import server.model.players.Player;

public class Timers {

	public boolean eventR = false;// Since the event keeps running.. And we
									// can't add a stop to the event /it wont
									// work then

	public static boolean runningf = false;

	public static void EPTimers(Client c) {
		if (c.inWild() && Client.EPCounter < 211) {
			Client.EPCounter++;
		}
		if (c.inWild() && Client.EPCounter == 210
				&& c.getItems().getCarriedWealth() > 5000000 || c.inWild()
				&& Client.EPCounter > 210
				&& c.getItems().getCarriedWealth() > 5000000) {
			c.sendMessage("<shad=15695415>You currently have "
					+ c.earningPotential + " EP.");
			c.sendMessage("<shad=15695415>Next EP raise: " + c.epDelay
					+ " Seconds.");
			Client.EPCounter = 0;
		} else {
			if (c.inWild() && Client.EPCounter == 210
					&& c.getItems().getCarriedWealth() < 5000000 || c.inWild()
					&& Client.EPCounter > 210
					&& c.getItems().getCarriedWealth() < 5000000) {
				Client.EPCounter = 0;
				c.sendMessage("<shad=15695415>You currently have "
						+ c.earningPotential + " EP.");
				c.sendMessage("<shad=15695415>To increase your EP, stay in wild & risk more then 5M!");
			}
		}

		if (c.epDelay > 0 || c.epDelay == 0)
			c.epDelay++;
		if (c.epDelay > 450 || c.epDelay == 450)
			EarningPotential.checkPotential(c);

	}

	public static void NoOPPotsInWildPlz(Client c) {
		if (c.inWild()) {
			if (c.playerLevel[0] > 120 || c.playerLevel[1] > 120
					|| c.playerLevel[2] > 120 || c.playerLevel[4] > 120
					|| c.playerLevel[6] > 120) {
				c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
				c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
				c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
				c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
				c.overload = 0;
				for (int i = 0; i <= 6 && i != 5; i++) {
					c.getPA().refreshSkill(i);
				}
				c.sendMessage("Your stats go back to normal.");

			}
		}
	}

	public void DFSTimer(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (c.dfsCounter > 0) {
					c.dfsCounter--;
				}
				if (c.dfsCounter == 0 || c.dfsCounter == 1 || c.dfsCounter < 0) {
					c.dfsCounter = 0;
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 2);

	}

	public void GWDTimer(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (c.gwdelay > 0) {
					c.gwdelay--;
				}
				if (c.gwdelay == 0 || c.gwdelay == 1 || c.gwdelay < 0) {
					c.gwdelay = 0;
					container.stop();
				}
			}

			@Override
			public void stop() {
				runningf = false;
			}
		}, 2);

	}

	/**/
	/**
	 * @Author: Gabbe // EVENTS
	 **/

	public void loyaltyPoints(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				c.LoyaltyScore += 1;
				if (c.LoyaltyScore == 200 || c.LoyaltyScore == 300) {
					c.sendMessage("Keep playing, for Loyalty Points!");
				}
				if (c.LoyaltyScore == 3600 || c.LoyaltyScore > 3600) {
					c.LoyaltyPoints += 200;
					c.LoyaltyScore = 0;
					c.timeOnline += 200;
					c.sendMessage("You've just gained 200 Loyalty Points! Continue to play for more.");
				}
			}

			@Override
			public void stop() {

			}
		}, 2);

	}

	public void overload(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (c.overload == 600 || c.overload == 598 || c.overload == 596
						|| c.overload == 594 || c.overload == 592) {
					c.overloadHit();
					c.getPA().refreshSkill(3);
				}
				if (c.overload == 570 || c.overload == 540 || c.overload == 510
						|| c.overload == 480 || c.overload == 450
						|| c.overload == 420 || c.overload == 390
						|| c.overload == 360 || c.overload == 330
						|| c.overload == 300 || c.overload == 270
						|| c.overload == 240 || c.overload == 210
						|| c.overload == 180 || c.overload == 150
						|| c.overload == 120 || c.overload == 90
						|| c.overload == 60 || c.overload == 30) {
					c.overload();
					c.getPA().refreshSkill(3);
				}
				c.overload--;
				if (c.overload == 0 || c.inWild()) {
					c.sendMessage("The effects of the overload potion have worn off...");
					// playerLevel[3] += 50;
					if (c.playerLevel[3] > c.playerLevel[Player.playerHitpoints]) {
						c.playerLevel[3] = c.playerLevel[Player.playerHitpoints];
					}
					c.getPA().refreshSkill(3);
					c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
					c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
					c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
					c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
					c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
					c.overload = 0;
					for (int i = 0; i <= 6 && i != 5; i++) {
						c.getPA().refreshSkill(i);
					}
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	public void seedtimer(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (c.seedtimer > 0) {
					c.seedtimer--;
				}
				if (c.seedtimer == 0 || c.seedtimer == 1 || c.seedtimer < 0) {
					c.seedtimer = 0;
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 2);

	}

	public void skullTimer(final Client c) {
		runningf = true;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				c.skullTimer--;
				if (c.skullTimer == 1 || c.skullTimer < 1 || c.skullTimer == 0) {
					c.isSkulled = false;
					c.attackedPlayers.clear();
					c.headIconPk = -1;
					c.skullTimer = -1;
					c.getPA().requestUpdates();
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 2);

	}

	public void updatePlayerLook(final Client c) {
		if (eventR) {
			return;
		}
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					eventR = false;
					container.stop();
					return;
				}
				eventR = true;
				c.updateTimer--;
				if (c.updateTimer == 39 || c.updateTimer < 38
						&& c.updateTimer > 1 || c.updateTimer == 0) {
					c.setAppearanceUpdateRequired(true);
					c.updateRequired = true;
					c.isFullHelm = Item
							.isFullHelm(c.playerEquipment[Player.playerHat]);
					c.isFullMask = Item
							.isFullMask(c.playerEquipment[Player.playerHat]);
					c.isFullBody = Item
							.isFullBody(c.playerEquipment[Player.playerChest]);
					c.getCombat()
							.getPlayerAnimIndex(
									c.getItems()
											.getItemName(
													c.playerEquipment[Player.playerWeapon])
											.toLowerCase());
					c.updateTimer = -1;
					container.stop();
				}
			}

			@Override
			public void stop() {
				eventR = false;
			}
		}, 2);

	}

	public void vestaTimer(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				c.vestaDelay--;
				if (c.vestaDelay == 1 || c.vestaDelay < 1 || c.vestaDelay == 0) {
					c.vestaDelay = -1;
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 2);

	}

	public void YellTimer(final Client c) {
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				if (c.disconnected) {
					container.stop();
					return;
				}
				if (c.yellTimer > 0) {
					c.yellTimer--;
				}
				if (c.yellTimer == 0 || c.yellTimer == 1 || c.yellTimer < 0) {
					c.yellTimer = 0;
					c.sendMessage("You're now able to send another yell message.");
					container.stop();
				}
			}

			@Override
			public void stop() {

			}
		}, 2);

	}
}