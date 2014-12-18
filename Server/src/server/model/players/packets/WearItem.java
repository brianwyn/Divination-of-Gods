package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;

/**
 * Wear Item
 **/

public class WearItem implements PacketType {

	@SuppressWarnings("unused")
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.wearId = c.getInStream().readUnsignedWord();
		c.wearSlot = c.getInStream().readUnsignedWordA();
		c.interfaceId = c.getInStream().readUnsignedWordA();
		c.getPA().resetAutocast();
		c.getItems().sendWeapon(
				c.playerEquipment[Player.playerWeapon],
				c.getItems()
						.getItemName(c.playerEquipment[Player.playerWeapon]));

		int oldCombatTimer = c.attackTimer;
		if (c.wearId == 4565) {
			c.basket = true;
		} else {
			c.basket = false;
		}
		if (c.playerIndex > 0 || c.npcIndex > 0)
			c.getCombat().resetPlayerAttack();
		if (c.wearId >= 5509 && c.wearId <= 5515) {
			int pouch = -1;
			int a = c.wearId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().emptyPouch(pouch);
			return;
		}

		if (c.wearId == 15262) {
			c.getItems().deleteItem(15262, 1);
			c.getItems().addItem(18016, 15000);
		}
		if (c.wearId == 13899) {
			c.degWep = true;
			c.sendMessage("Your now wearing a normal VLS, it might degrade!");
		}
		if (c.wearSlot == c.playerEquipment[Player.playerWeapon]) {

		}
		// c.attackTimer = oldCombatTimer;
		if (c.isNpc == true) {
			c.sendMessage("Type ::unpc before you do that!");
		} else {
			if (c.isNpc == false) {
				c.getItems().wearItem(c.wearId, c.wearSlot);
				c.getItems().sendWeapon(
						c.playerEquipment[Player.playerWeapon],
						c.getItems().getItemName(
								c.playerEquipment[Player.playerWeapon]));
			}
		}
	}
}