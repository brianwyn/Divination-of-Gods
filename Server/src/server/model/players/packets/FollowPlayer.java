package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;

/**
 * Follow Player
 **/
public class FollowPlayer implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int followPlayer = c.getInStream().readUnsignedWordBigEndian();
		if (followPlayer > PlayerHandler.players.length) {
			return;
		}
		if (PlayerHandler.players[followPlayer] == null) {
			return;
		}
		if (c.isNpc == true) {
			c.sendMessage("Type ::return first!");
			return;
		}
		if (c.inCwWait == true) {
			c.sendMessage("You cannot follow someone right now.");
			return;
		}
		if (c.usingMagic) {
			return;
		}
		c.playerIndex = 0;
		c.npcIndex = 0;
		c.mageFollow = false;
		c.usingBow = false;
		c.usingRangeWeapon = false;
		c.followDistance = 1;
		c.followId = followPlayer;
	}
}
