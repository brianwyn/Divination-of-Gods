package server.model.players.packets;

import server.Config;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;

/**
 * Challenge Player
 **/
public class ChallengePlayer implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		switch (packetType) {
		case 128:
			int answerPlayer = c.getInStream().readUnsignedWord();
			if (answerPlayer > PlayerHandler.players.length) {
				return;
			}
			if (PlayerHandler.players[answerPlayer] == null) {
				return;
			}
			Client o = (Client) PlayerHandler.players[answerPlayer];
			if (!c.enterdBankpin && c.hasBankPin && Config.ALLOWPINS) {
				c.getBankPin().openPin();
				c.sendMessage("Please enter your Bank-Pin before doing this!");
				return;
			}
			if (c.duelStatus != 0 || o.duelStatus != 0) {
				c.sendMessage("You are currently unable to challenge this player. Please try relogging.");
				c.getCombat().resetPlayerAttack();
				return;
			}
			if (o.duelStatus > 0) {
				c.sendMessage("That player is currently dueling someone else.");
				c.getCombat().resetPlayerAttack();
				return;
			}
			if (c.arenas() || c.duelStatus == 5) {
				c.sendMessage("You can't challenge inside the arena!");
				c.getCombat().resetPlayerAttack();
				return;
			}
			c.sendMessage("");

			c.getTradeAndDuel().requestDuel(answerPlayer);
			break;
		}
	}
}