package server.model.players.packets;

import server.Config;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;

/**
 * Trading
 */
public class Trade implements PacketType {
	public boolean inTrade;

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int tradeId = c.getInStream().readSignedWordBigEndian();
		c.getPA().resetFollow();
		if (tradeId > PlayerHandler.players.length) {
			return;
		}
		if (PlayerHandler.players[tradeId] == null) {
			return;
		}

		if (!c.enterdBankpin && c.hasBankPin && Config.ALLOWPINS) {
			c.getBankPin().openPin();
			c.openBank = false;
			c.sendMessage("Please enter your Bank-Pin before doing this!");
			return;
		}
		if (c.arenas()) {
			c.sendMessage("You can't trade inside the arena!");
			return;
		}
		if (c.playerName.equalsIgnoreCase("game")) {
			c.sendMessage("Your trading has been disabled.");
			return;
		}
		if (c.InDung()) {
			c.sendMessage("You cannot trade inside Dungoneering!");
			return;
		}
		if (c.inTrade) {
			c.sendMessage("Please relog before doing that.");
			return;
		}
		if (c.playerRights == 2) {
			c.sendMessage("Admins cannot trade.");
			return;
		}
		if (c.inWild()) {
			c.sendMessage("You can't trade in the wilderness!");
		}
		if (tradeId != c.playerId)
			c.getTradeAndDuel().requestTrade(tradeId);
	}

}