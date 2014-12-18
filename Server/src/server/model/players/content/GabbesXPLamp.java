package server.model.players.content;

import server.model.players.Client;
import server.util.Misc;

public class GabbesXPLamp {

	/***/
	/****
	 * Author: Gabbe, used for SoF Lamp.
	 ***/
	public static String skillName = "Choose XP Type...";
	public static int skillChosen = -1;
	public static int lampOpened = 0;

	public static void confirmSelectionOfSkill(Client c, int skillChosen) {
		if (skillChosen == -1) {
			c.sendMessage("You haven't selected a skill you wish to advance in!");
		}
		if (lampOpened == 18782 && skillChosen != -1) {
			c.getPA().dragonkinFormula(skillChosen);
			return;
		}

		if (lampOpened == 11137) { // random event lamp
			if (c.antiCheatClient == true && c.hasGivenXP == false
					&& skillChosen != -1) {

				if (c.playerLevel[skillChosen] > 0
						&& c.playerLevel[skillChosen] < 40)
					c.getPA().addSkillXP(30000, skillChosen);
				else if (c.playerLevel[skillChosen] > 39
						&& c.playerLevel[skillChosen] < 60)
					c.getPA().addSkillXP(60000, skillChosen);
				else if (c.playerLevel[skillChosen] > 59
						&& c.playerLevel[skillChosen] < 71)
					c.getPA().addSkillXP(90000, skillChosen);
				else if (c.playerLevel[skillChosen] > 70
						&& c.playerLevel[skillChosen] < 80)
					c.getPA().addSkillXP(100000, skillChosen);
				else if (c.playerLevel[skillChosen] > 80
						&& c.playerLevel[skillChosen] < 90)
					c.getPA().addSkillXP(100000, skillChosen);
				else if (c.playerLevel[skillChosen] > 89
						&& c.playerLevel[skillChosen] < 99)
					c.getPA().addSkillXP(100000, skillChosen);

				c.getItems().deleteItem(11137, 1);
				c.getPA().refreshSkill(skillChosen);
				c.getPA().closeAllWindows();
				lampOpened = 0;
				c.hasGivenXP = true;
				c.sendMessage("Enjoy your XP Reward!");
			}
		} else {
			if (lampOpened == 7498) { // random event lamp
				if (c.antiCheatClient == true && c.hasGivenXP == false
						&& skillChosen != -1) {

					if (c.playerLevel[skillChosen] > 0
							&& c.playerLevel[skillChosen] < 40)
						c.getPA().addSkillXP(15000, skillChosen);
					else if (c.playerLevel[skillChosen] > 39
							&& c.playerLevel[skillChosen] < 60)
						c.getPA().addSkillXP(50000, skillChosen);
					else if (c.playerLevel[skillChosen] > 59
							&& c.playerLevel[skillChosen] < 71)
						c.getPA().addSkillXP(80000, skillChosen);
					else if (c.playerLevel[skillChosen] > 70
							&& c.playerLevel[skillChosen] < 80)
						c.getPA().addSkillXP(100000, skillChosen);
					else if (c.playerLevel[skillChosen] > 80
							&& c.playerLevel[skillChosen] < 90)
						c.getPA().addSkillXP(100000, skillChosen);
					else if (c.playerLevel[skillChosen] > 89
							&& c.playerLevel[skillChosen] < 99)
						c.getPA().addSkillXP(100000, skillChosen);

					c.getItems().deleteItem(7498, 1);
					lampOpened = 0;
					c.getPA().refreshSkill(skillChosen);
					c.getPA().closeAllWindows();
					c.hasGivenXP = true;
					c.sendMessage("Enjoy your XP Reward!");
				}
			}
		}
	}

	public static void handleXPLampButtons(Client c, int packetType,
			int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);

		switch (actionButtonId) {

		case 137013: // CONFIRM BUTTON!
			confirmSelectionOfSkill(c, skillChosen);
			break;
		case 136191: // Attack
			skillName = "Attack";
			skillChosen = 0;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Attack.");
			break;
		case 136194: // Mage
			skillName = "Magic";
			skillChosen = 6;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Magic.");
			break;
		case 136197: // Mining
			skillName = "Mining";
			skillChosen = 14;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Mining.");
			break;
		case 136200: // Woodcutting
			skillChosen = 8;
			skillName = "Woodcutting";
			// c.sendMessage("YOU'VE CHOSEN SKILL: Woodcutting.");
			break;
		case 136203: // Agility
			skillName = "Agility";
			skillChosen = 16;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Agility.");
			break;
		case 136206: // Fletching
			skillName = "Fletching";
			skillChosen = 9;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Fletching.");
			break;
		case 136209: // Thieving
			skillName = "Thieving";
			skillChosen = 17;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Thieving.");
			break;
		case 136212: // Strength
			skillName = "Strength";
			skillChosen = 17;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Strength.");
			break;
		case 136215: // Ranged
			skillName = "Ranged";
			skillChosen = 4;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Ranged.");
			break;
		case 136218: // Smithing
			skillName = "Smithing";
			skillChosen = 13;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Smithing.");
			break;
		case 136221: // Firemaking
			skillChosen = 11;
			skillName = "Firemaking";
			// c.sendMessage("YOU'VE CHOSEN SKILL: Firemaking.");
			break;
		case 136224: // Herblore
			skillChosen = 15;
			skillName = "Herblore";
			// c.sendMessage("YOU'VE CHOSEN SKILL: Herblore.");
			break;
		case 136227: // Slayer
			skillChosen = 18;
			skillName = "Slayer";
			// c.sendMessage("YOU'VE CHOSEN SKILL: Slayer.");
			break;
		case 136230: // Construction
			skillName = "Construction";
			skillChosen = 23;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Construction.");
			break;
		case 136233: // Defence
			skillChosen = 1;
			skillName = "Defence";
			// c.sendMessage("YOU'VE CHOSEN SKILL: Defence.");
			break;
		case 136236: // Prayer
			skillChosen = 5;
			skillName = "Prayer";
			// c.sendMessage("YOU'VE CHOSEN SKILL: Prayer.");
			break;
		case 136239: // Fishing
			skillName = "Fishing";
			skillChosen = 10;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Fishing.");
			break;
		case 136242: // Crafting
			skillName = "Crafting";
			skillChosen = 12;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Crafting.");
			break;
		case 136245: // Farming
			skillChosen = 19;
			skillName = "Farming";
			// c.sendMessage("YOU'VE CHOSEN SKILL: Farming.");
			break;
		case 136248: // Hunter
			skillName = "Hunter";
			skillChosen = 22;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Hunter.");
			break;
		case 136251: // Summoning
			skillName = "Summoning";
			skillChosen = 21;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Summoning.");
			break;
		case 136254: // Hitpoints
			skillName = "Hitpoints";
			skillChosen = 3;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Hitpoints.");
			break;
		case 137001: // Dungeoneering
			skillName = "Dungeoneering";
			skillChosen = 3;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Dungeoneering.");
			break;
		case 137004: // Cooking
			skillName = "Cooking";
			skillChosen = 7;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Cooking.");
			break;
		case 137007: // Runecrafting
			skillName = "Runecrafting";
			skillChosen = 20;
			// c.sendMessage("YOU'VE CHOSEN SKILL: Runecrafting.");
			break;
		}
		c.getPA().sendFrame126("" + skillName + "", 35006);
	}

	public static void openInterface(Client c) {
		if (lampOpened == 0) {
			c.sendMessage("Please relog before doing this.");
			return;
		}
		c.getPA().showInterface(35000);
		c.antiCheatClient = true;
		skillChosen = -1;
		c.hasGivenXP = false;
	}
}