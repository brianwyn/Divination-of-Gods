package server.model.players.packets;

import server.model.items.UseItem;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.content.skills.Cooking;

/**
 * @author Jeff :D
 */

public class ItemOnObject implements PacketType {

	@SuppressWarnings("unused")
	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		/*
		 * a = ? b = ?
		 */

		int a = c.getInStream().readUnsignedWord();
		int objectId = c.getInStream().readSignedWordBigEndian();
		final int objectY = c.getInStream().readSignedWordBigEndianA();
		int b = c.getInStream().readUnsignedWord();
		final int objectX = c.getInStream().readSignedWordBigEndianA();
		int itemId = c.getInStream().readUnsignedWord();
		UseItem.ItemonObject(c, objectId, objectX, objectY, itemId);
		c.turnPlayerTo(objectX, objectY);
		c.cookingCoords[0] = objectX;
		c.cookingCoords[1] = objectY;
		if (itemId == 2134 && objectId == 2142) { // HERBLORE QUEST
			c.startAnimation(1670, 0);
			c.sendMessage("You dip the meat... It turns all blue?");
			c.getItems().deleteItem(2134, 1);
			c.getItems().addItem(523, 1);
		}

		if (itemId == 1779 && objectId == 2644) { // FLAX STRING!
			c.spinFlax();
		}

		if (itemId == 2136 && objectId == 2142) { // HERBLORE QUEST
			c.startAnimation(1670, 0);
			c.sendMessage("You dip the meat... It turns all blue?");
			c.getItems().deleteItem(2136, 1);
			c.getItems().addItem(524, 1);
		}

		if (itemId == 2138 && objectId == 2142) { // HERBLORE QUEST
			c.startAnimation(1670, 0);
			c.sendMessage("You dip the meat... It turns all blue?");
			c.getItems().deleteItem(2138, 1);
			c.getItems().addItem(525, 1);
		}

		if (itemId == 1438 && objectId == 2452) { // Air Alter
			c.startAnimation(1670, 0);
			c.sendMessage("A mysterious force grabs hold of you.");
			c.getPA().movePlayer(2841, 4829, 0);
		}

		if (itemId == 1440 && objectId == 2455) { // Earth Alter
			c.startAnimation(1670, 0);
			c.sendMessage("A mysterious force grabs hold of you.");
			c.getPA().movePlayer(2655, 4830, 0);
		}

		if (itemId == 1442 && objectId == 3312) { // Fire Alter
			c.startAnimation(1670, 0);
			c.sendMessage("A mysterious force grabs hold of you.");
			c.getPA().movePlayer(2574, 4848, 0);
		}
		if (itemId == 1444 && objectId == 3184) { // Water Alter
			c.startAnimation(1670, 0);
			c.sendMessage("A mysterious force grabs hold of you.");
			c.getPA().movePlayer(2727, 4833, 0);
		}
		if (itemId == 1446 && objectId == 3052) { // Body Alter
			c.startAnimation(1670, 0);
			c.sendMessage("A mysterious force grabs hold of you.");
			c.getPA().movePlayer(2522, 4825, 0);
		}
		if (itemId == 1448 && objectId == 2981) { // Mind Alter
			c.startAnimation(1670, 0);
			c.sendMessage("A mysterious force grabs hold of you.");
			c.getPA().movePlayer(2792, 4827, 0);
		}
		switch (objectId) {

		case 12269:
		case 114:
		case 9374:
		case 2728:
		case 25465:
		case 11404:
		case 11405:
		case 11406:
		case 2732:
			Cooking.cookThisFood(c, itemId, objectId);
			break;

		}
	}

}
