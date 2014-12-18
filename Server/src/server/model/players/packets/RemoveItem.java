package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;

/**
 * Remove Item
 **/
public class RemoveItem implements PacketType {

	@SuppressWarnings("unused")
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int interfaceId = c.getInStream().readUnsignedWordA();
		int removeSlot = c.getInStream().readUnsignedWordA();
		int removeId = c.getInStream().readUnsignedWordA();
		int shop = 0;
		int value = 0;
		String name = "null";

		switch (interfaceId) {
		case 2700:
			if (c.occupied[removeSlot] == true
					&& c.storeditems[removeSlot] == removeId) {
				c.getPA().Frame34(2702, -1, removeSlot, 1);
				c.getItems().addItem(removeId, 1);
				c.occupied[removeSlot] = false;
				c.storeditems[removeSlot] = 0;
				c.getItems().resetTempItems();
				c.getItems().resetBank();
				c.totalstored -= 1;
			}

			break;
		case 1688:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
				return;
			}
			if (removeSlot == Player.playerCape) {
				if (c.inCwWait == true || c.inCwGame == true) {
					c.sendMessage("You cannot unequip your cape at the moment.");
					return;
				}
			}

			c.getItems().removeItem(removeId, removeSlot);
			break;

		case 5064:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
				return;
			}
			if (removeId != -1) {
				if (c.isBanking) {
					c.getItems().bankItem(removeId, removeSlot, 1);
					c.getPA().searchBank(c, c.searchTerm);
				}
			}
			/*
			 * if(c.storing){ } else { if (removeId == 995) {
			 * c.sendMessage("You cannot deposit coins"); return; }
			 * PartyRoom.depositItem(c, removeId, 1); }
			 */
			break;
		case 2274:
			if (removeId == 995) {
				c.sendMessage("You cannt deposit coins");
				return;
			}
			// PartyRoom.withdrawItem(c, removeSlot);
			break;

		case 5382:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
				return;
			}
			if (removeId == 995) {
				int l = c.getItems().getItemAmount(995);
				int k = l + 1;
				if (k >= 2147483646) {
					c.sendMessage("You cannot withdraw any more coins as your inventory is already loaded!");
					return;
				}
			}
			c.getItems().fromBank(removeId, removeSlot, 1);
			c.getPA().searchBank(c, c.searchTerm);
			break;

		case 3900:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
				return;
			}
			c.getShops().buyFromShopPrice(removeId, removeSlot);
			break;

		case 3823:
			if (c.inTrade) {
				c.getTradeAndDuel().declineTrade(true);
				return;
			}
			c.getShops().sellToShopPrice(removeId, removeSlot);
			break;

		case 3322:
			if (c.duelStatus <= 0) {
				if (!c.inTrade) {
					return;
				}
				c.getTradeAndDuel().tradeItem(removeId, removeSlot, 1);
			} else {
				if (!c.inDuelScreen) {
					return;
				}
				c.getTradeAndDuel().stakeItem(removeId, removeSlot, 1);
			}
			break;

		case 3415:
			if (c.duelStatus <= 0) {
				c.getTradeAndDuel().fromTrade(removeId, 1);
			}
			break;

		case 6669:
			c.getTradeAndDuel().fromDuel(removeId, removeSlot, 1);
			break;

		case 1119:
		case 1120:
		case 1121:
		case 1122:
		case 1123:
			c.getSmithing().readInput(c.playerLevel[Player.playerSmithing],
					Integer.toString(removeId), c, 1);
			break;
		}
	}

}
