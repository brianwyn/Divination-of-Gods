package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Move Items
 **/
public class MoveItems implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int somejunk = c.getInStream().readUnsignedWordA(); // junk
		int itemFrom = c.getInStream().readUnsignedWordA();// slot1
		int itemTo = (c.getInStream().readUnsignedWordA() - 128);// slot2
		if (somejunk == 34176)
			c.getItems().toTab(0, itemFrom);
		else if (somejunk == 36224)
			c.getItems().toTab(1, itemFrom);
		else if (somejunk == 39552)
			c.getItems().toTab(2, itemFrom);
		else if (somejunk == 42880)
			c.getItems().toTab(3, itemFrom);
		else if (somejunk == 46208)
			c.getItems().toTab(4, itemFrom);
		else if (somejunk == 49536)
			c.getItems().toTab(5, itemFrom);
		else if (somejunk == 52864)
			c.getItems().toTab(6, itemFrom);
		else if (somejunk == 56192)
			c.getItems().toTab(7, itemFrom);
		else if (somejunk == 59520)
			c.getItems().toTab(8, itemFrom);
		// c.sendMessage("junk: " + somejunk);
		if (somejunk != 32668) {
			c.getItems().moveItems(itemFrom, itemTo, somejunk);

		}

		if (somejunk == 32668) {

			c.storeditems[itemTo] = c.storeditems[itemFrom];
			c.occupied[itemTo] = true;
			c.storeditems[itemFrom] = 0;
			c.occupied[itemFrom] = false;
		}
		if (c.inTrade) {
			c.getTradeAndDuel().declineTrade();
			return;
		}
		if (c.tradeStatus == 1) {
			c.getTradeAndDuel().declineTrade();
			return;
		}
		if (c.duelStatus == 1) {
			c.getTradeAndDuel().declineDuel();
			return;
		}
	}
}
