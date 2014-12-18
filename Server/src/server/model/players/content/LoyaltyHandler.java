package server.model.players.content;

import server.model.players.Client;
import server.util.Misc;

public class LoyaltyHandler {

	public static void AsuraAura(Client c) {
		if (c.AsuraAura == false && Client.CoolDownTimer2 == -1) {
			c.AsuraAura = true;
			c.sendMessage("You activate your aura.. Time left: 60 Minutes.");
			Client.auraTimer2 = 5000;
			Client.CoolDownTimer2 = -1;
		} else {
			c.sendMessage("Your Asura aura is alredy active or it is recharging!");
			return;
		}
	}

	/***/
	/*** Loyalty Handler by Gabbe, handles all junk for Loyalty, events ect. ***/

	public static void HandleLoyaltyMisc(Client c, int packetType,
			int packetSize) { // Used for not spamming up clickingbuttons when
								// there's a content folder!!
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		switch (actionButtonId) {
		case 135007:
			c.getPA().closeAllWindows();
			break;
		case 134250:
			if (c.LoyaltyPoints == 1000 || c.LoyaltyPoints > 1000) {
				if (c.playerTitle == 0) {
					c.playerTitle = 2;
					c.LoyaltyPoints -= 1000;
					c.SaveGame();
					c.sendMessage("You've purchased a title! Please relog for it to start to show.");
				} else {
					c.sendMessage("You alredy have a title! You can reset it by typing ::titlereset");
				}
			} else {
				c.sendMessage("You don't have enough Loyalty Points! You have: "
						+ c.LoyaltyPoints + " Points.");
			}
			break;
		case 134253:
			if (c.LoyaltyPoints == 1000 || c.LoyaltyPoints > 1000) {
				if (c.playerTitle == 0) {
					c.playerTitle = 1;
					c.LoyaltyPoints -= 1000;
					c.SaveGame();
					c.sendMessage("You've purchased a title! Please relog for it to start to show.");
				} else {
					c.sendMessage("You alredy have a title! You can reset it by typing ::titlereset");
				}
			} else {
				c.sendMessage("You don't have enough Loyalty Points! You have: "
						+ c.LoyaltyPoints + " Points.");
			}
			break;
		case 135002:
			if (c.LoyaltyPoints == 1000 || c.LoyaltyPoints > 1000) {
				if (c.playerTitle == 0) {
					c.playerTitle = 24;
					c.LoyaltyPoints -= 1000;
					c.SaveGame();
					c.sendMessage("You've purchased a title! Please relog for it to start to show.");
				} else {
					c.sendMessage("You alredy have a title! You can reset it by typing ::titlereset");
				}
			} else {
				c.sendMessage("You don't have enough Loyalty Points! You have: "
						+ c.LoyaltyPoints + " Points.");
			}
			break;
		case 135012:
			if (c.LoyaltyPoints == 3000 || c.LoyaltyPoints > 3000) {
				if (c.playerTitle == 0) {
					c.playerTitle = 6;
					c.LoyaltyPoints -= 3000;
					c.SaveGame();
					c.sendMessage("You've purchased a title! Please relog for it to start to show.");
				} else {
					c.sendMessage("You alredy have a title! You can reset it by typing ::titlereset");
				}
			} else {
				c.sendMessage("You don't have enough Loyalty Points! You have: "
						+ c.LoyaltyPoints + " Points.");
			}
			break;
		case 135015:
			if (c.LoyaltyPoints == 5000 || c.LoyaltyPoints > 5000) {
				if (c.playerTitle == 0) {
					c.playerTitle = 34;
					c.LoyaltyPoints -= 5000;
					c.SaveGame();
					c.sendMessage("You've purchased a title! Please relog for it to start to show.");
				} else {
					c.sendMessage("You alredy have a title! You can reset it by typing ::titlereset");
				}
			} else {
				c.sendMessage("You don't have enough Loyalty Points! You have: "
						+ c.LoyaltyPoints + " Points.");
			}
			break;
		case 135020:
			/*
			 * if(c.getItems().freeSlots() == 1 || c.getItems().freeSlots() > 1)
			 * { if(c.LoyaltyPoints == 6000 || c.LoyaltyPoints > 6000) {
			 * c.getItems().addItem(4804, 1);
			 * 
			 * c.LoyaltyPoints -= 6000; c.SaveGame(); } else {
			 * c.sendMessage("You don't have enough Loyalty Points! You have: "
			 * +c.LoyaltyPoints+" Points."); } } else {
			 * c.sendMessage("You need atleast 1 free inventory space."); }
			 */
			c.sendMessage("Currently disabled..");
			break;
		case 135025:
			/*
			 * if(c.getItems().freeSlots() == 1 || c.getItems().freeSlots() > 1)
			 * { if(c.LoyaltyPoints == 6000 || c.LoyaltyPoints > 6000) {
			 * c.getItems().addItem(4805, 1); c.LoyaltyPoints -= 6000;
			 * c.SaveGame(); } else {
			 * c.sendMessage("You don't have enough Loyalty Points! You have: "
			 * +c.LoyaltyPoints+" Points."); } } else {
			 * c.sendMessage("You need atleast 1 free inventory space."); }
			 */
			c.sendMessage("Currently disabled..");
			break;
		}

	}

	public static void handleTimeRemaining(Client c) {
		if (Client.auraTimer > 0) {
			c.sendMessage("[Sharpshooter Aura] Time left: "
					+ (Client.auraTimer / 83) + " Minutes.");
		} else {
			if (Client.CoolDownTimer > 0) {
				c.sendMessage("[Sharpshooter Aura] Recharge time left: "
						+ (Client.CoolDownTimer / 83) + " Minutes.");
			}
		}
	}

	public static void handleTimeRemaining2(Client c) {
		if (Client.auraTimer2 > 0) {
			c.sendMessage("[Asura Aura] Time left: " + (Client.auraTimer2 / 83)
					+ " Minutes.");
		} else {
			if (Client.CoolDownTimer2 > 0) {
				c.sendMessage("[Asura Aura] Recharge time left: "
						+ (Client.CoolDownTimer2 / 83) + " Minutes.");
			}
		}
	}

	public static void SharpShooterAura(Client c) {
		if (c.SharpAuraActive == false && Client.CoolDownTimer == -1) {
			c.SharpAuraActive = true;
			c.sendMessage("You activate your aura.. Time left: 60 Minutes.");
			Client.auraTimer = 5000;
			Client.CoolDownTimer = -1;
		} else {
			c.sendMessage("Your aura is alredy active or it is recharging!");
			return;
		}
	}

}
