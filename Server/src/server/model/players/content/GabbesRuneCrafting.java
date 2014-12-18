package server.model.players.content;

import server.model.players.Client;
import server.util.Misc;

public class GabbesRuneCrafting {

	// Checks if they have a talisman bro
	public static boolean canEnter(Client c, String AltarName) {
		if (AltarName == "Air")
			if (c.getItems().playerHasItem(1438, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Mind")
			if (c.getItems().playerHasItem(1448, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Water")
			if (c.getItems().playerHasItem(1444, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Earth")
			if (c.getItems().playerHasItem(1440, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Fire")
			if (c.getItems().playerHasItem(1442, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Cosmic")
			if (c.getItems().playerHasItem(1454, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Chaos")
			if (c.getItems().playerHasItem(1452, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Nature")
			if (c.getItems().playerHasItem(1462, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Law")
			if (c.getItems().playerHasItem(1458, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Death")
			if (c.getItems().playerHasItem(1456, 1)) {
				return true;
			} else {
				return false;
			}
		if (AltarName == "Blood")
			if (c.getItems().playerHasItem(1450, 1)) {
				return true;
			} else {
				return false;
			}
		return false;
	}

	public static void handleInterfaceButtons(Client c, int packetType,
			int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		switch (actionButtonId) {
		case 144150:
			c.getPA().closeAllWindows();
			break;
		case 144138: // Air button
			if (canEnter(c, "Air") == true) {
				c.getPA().spellTeleport(2845, 4832, 0);
				c.sendMessage("You teleport to the Air Altar...");
			} else {
				c.sendMessage("You do not have an Air Talisman.");
			}
			break;
		case 144139: // Mind button
			if (canEnter(c, "Mind") == true) {
				c.getPA().spellTeleport(2796, 4818, 0);
				c.sendMessage("You teleport to the Mind Altar...");
			} else {
				c.sendMessage("You do not have a Mind Talisman.");
			}
			break;
		case 144140: // Water button
			if (canEnter(c, "Water") == true) {
				c.getPA().spellTeleport(2713, 4836, 0);
				c.sendMessage("You teleport to the Water Altar...");
			} else {
				c.sendMessage("You do not have a Water Talisman.");
			}
			break;
		case 144141: // Earth button
			if (canEnter(c, "Earth") == true) {
				c.getPA().spellTeleport(2660, 4839, 0);
				c.sendMessage("You teleport to the Earth Altar...");
			} else {
				c.sendMessage("You do not have a Earth Talisman.");
			}
			break;
		case 144142: // Fire button
			if (canEnter(c, "Fire") == true) {
				c.getPA().spellTeleport(2584, 4836, 0);
				c.sendMessage("You teleport to the Fire Altar...");
			} else {
				c.sendMessage("You do not have a Fire Talisman.");
			}
			break;
		case 144143: // Cosmic button
			if (canEnter(c, "Cosmic") == true) {
				c.getPA().spellTeleport(2162, 4833, 0);
				c.sendMessage("You teleport to the Cosmic Altar...");
			} else {
				c.sendMessage("You do not have a Cosmic Talisman.");
			}
			break;
		case 144144: // Chaos button
			if (canEnter(c, "Chaos") == true) {
				c.getPA().spellTeleport(2269, 4843, 0);
				c.sendMessage("You teleport to the Chaos Altar...");
			} else {
				c.sendMessage("You do not have a Chaos Talisman.");
			}
			break;
		case 144145: // Astral button
			c.sendMessage("The Astral Altar is currently unavailable.");
			break;
		case 144146: // Nature button
			if (canEnter(c, "Nature") == true) {
				c.getPA().spellTeleport(2398, 4841, 0);
				c.sendMessage("You teleport to the Nature Altar...");
			} else {
				c.sendMessage("You do not have a Nature Talisman.");
			}
			break;
		case 144147: // Law button
			if (canEnter(c, "Law") == true) {
				c.getPA().spellTeleport(2464, 4834, 0);
				c.sendMessage("You teleport to the Law Altar...");
			} else {
				c.sendMessage("You do not have a Law Talisman.");
			}
			break;
		case 144148: // Death button
			if (canEnter(c, "Death") == true) {
				c.getPA().spellTeleport(2207, 4836, 0);
				c.sendMessage("You teleport to the Death Altar...");
			} else {
				c.sendMessage("You do not have a Death Talisman.");
			}
			break;
		case 144149: // Blood button
			c.sendMessage("The Blood Altar is currently unavailable.");
			break;

		}
	}
}