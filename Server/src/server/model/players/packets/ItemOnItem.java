package server.model.players.packets;

/**
 * @author Ryan / Lmctruck30
 */

import server.model.items.UseItem;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.content.skills.Summoning;
import server.util.Misc;

public class ItemOnItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int usedWithSlot = c.getInStream().readUnsignedWord();
		int itemUsedSlot = c.getInStream().readUnsignedWordA();
		int useWith = c.playerItems[usedWithSlot] - 1;
		@SuppressWarnings("unused")
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		int itemUsed = c.playerItems[itemUsedSlot] - 1;

		Summoning.makeSummoningPouch(c, useWith, itemUsed);
		UseItem.ItemonItem(c, itemUsed, useWith);
	}
}