package server.model.players.content;

import server.model.players.Client;
import server.util.Misc;

public class GabbesSkillTeleport {

	/***/
	/****
	 * Author: Gabbe, used for Skill teleport
	 ***/

	public static void handleButtons(Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		String skillName = "None";
		switch (actionButtonId) {
		/** MINIGAME TELE TO **/
		case 164021: // duel
			c.getPA().spellTeleport(3358, 3270, 0);
			break;
		case 164022: // barrows
			c.getPA().spellTeleport(3565, 3314, 0);
			break;
		case 164023: // wg
			c.getPA().spellTeleport(2865, 3546, 0);
			break;
		case 164024: // pc
			c.getPA().spellTeleport(2662, 2650, 0);
			break;
		case 164025: // fire cape
			c.getPA().spellTeleport(2438, 5172, 0);
			break;
		case 164026: // FoG
			// c.getPA().spellTeleport(1716, 5600, 0);
			c.sendMessage("FoG is still under developement.");
			// c.sendMessage("There are many bugs!");
			break;
		case 164027:
			// c.getPA().spellTeleport(2442, 3089, 0);
			c.sendMessage("Castle Wars is currently disabled.");
			break;
		case 164028:
			c.getPA().spellTeleport(1895, 5408, 0);
			break;
		case 164029:
			c.getPA().spellTeleport(3222, 3222, 0);
			break;
		case 164030:
			c.getPA().spellTeleport(3217, 9621, 0);
			break;
		case 164031:
			c.getPA().spellTeleport(2967, 3206, 0);
			break;
		// end of teles minigames
		case 128241: // Mining
			skillName = "Mining";
			handleTeleport(c, skillName);
			break;
		case 128245: // Woodcutting
			skillName = "Woodcutting";
			handleTeleport(c, skillName);
			break;
		case 128253: // Agility
			skillName = "Agility";
			handleTeleport(c, skillName);
			break;
		case 129003: // Fletching
			skillName = "Fletching";
			handleTeleport(c, skillName);
			break;
		case 129013: // Thieving
			skillName = "Thieving";
			handleTeleport(c, skillName);
			break;
		case 128243: // Smithing
			skillName = "Smithing";
			handleTeleport(c, skillName);
			break;
		case 128247: // Firemaking
			skillName = "Firemaking";
			handleTeleport(c, skillName);
			break;
		case 128251: // Herblore
			skillName = "Herblore";
			handleTeleport(c, skillName);
			break;
		case 129015: // Slayer
			skillName = "Slayer";
			handleTeleport(c, skillName);
			break;
		case 129001: // Construction
			skillName = "Construction";
			handleTeleport(c, skillName);
			break;
		case 128237: // Fishing
			skillName = "Fishing";
			handleTeleport(c, skillName);
			break;
		case 129005: // Crafting
			skillName = "Crafting";
			handleTeleport(c, skillName);
			break;
		case 128249: // Farming
			skillName = "Farming";
			handleTeleport(c, skillName);
			break;
		case 128255: // Hunter
			skillName = "Hunter";
			handleTeleport(c, skillName);
			break;
		case 129007: // Summoning
			skillName = "Summoning";
			handleTeleport(c, skillName);
			break;
		case 129011: // Dungeoneering
			skillName = "Dungeoneering";
			handleTeleport(c, skillName);
			break;
		case 128239: // Cooking
			skillName = "Cooking";
			handleTeleport(c, "Cooking");
			break;
		case 129009: // Runecrafting
			skillName = "Runecrafting";
			handleTeleport(c, skillName);
			break;
		}
	}

	public static void handleTeleport(Client c, String Skill) {
		if (Skill == "Fishing") {
			c.getPA().spellTeleport(2604, 3408, 0);
			return;
		}
		if (Skill == "Cooking") {
			c.getPA().spellTeleport(2613, 3398, 0);
			return;
		}
		if (Skill == "Mining") {
			c.getPA().spellTeleport(3022, 9739, 0);
			return;
		}
		if (Skill == "Smithing") {
			c.getPA().spellTeleport(2972, 3373, 0);
			return;
		}
		if (Skill == "Firemaking") {
			c.getPA().spellTeleport(2905, 3463, 0);
			return;
		}
		if (Skill == "Hunter") {
			c.getPA().spellTeleport(2591, 4318, 0);
			return;
		}
		if (Skill == "Farming") {
			c.getPA().spellTeleport(2816, 3460, 0);
			return;
		}
		if (Skill == "Herblore") {
			c.getPA().spellTeleport(2816, 3460, 0);
			return;
		}
		if (Skill == "Construction") {
			c.getPA().spellTeleport(2736, 3502, 0);
			return;
		}
		if (Skill == "Agility") {
			c.getPA().spellTeleport(2480, 3437, 0);
			return;
		}
		if (Skill == "Fletching") {
			c.getPA().spellTeleport(2708, 3463, 0);
			return;
		}
		if (Skill == "Crafting") {
			c.getPA().spellTeleport(2739, 3446, 0);
			return;
		}
		if (Skill == "Summoning") {
			c.getPA().spellTeleport(2208, 5347, 0);
			return;
		}
		if (Skill == "Runecrafting") {
			c.getPA().spellTeleport(3493, 3489, 0);
			c.sendMessage("Speak to Aubury to get information on how to train RC.");
			return;
		}
		if (Skill == "Dungeoneering") {
			c.getPA().spellTeleport(2419, 3528, 0);
			return;
		}
		if (Skill == "Thieving") {
			c.getPA().spellTeleport(2661, 3309, 0);
			return;
		}
		if (Skill == "Slayer") {
			c.getPA().spellTeleport(3207, 3429, 0);
			return;
		}
		if (Skill == "Woodcutting") {
			c.getPA().spellTeleport(2664, 3349, 0);
			return;
		}

	}

	public static void openInterface(Client c) {
		c.getPA().showInterface(33000);
		c.getPA().sendFrame126(
				"@whi@Total Level: " + c.getPA().getTotalLevel2() + "", 33041);
	}
}