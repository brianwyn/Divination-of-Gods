package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;

public class LeaveChat implements PacketType {

	public void processPacket(Client c, int packetType, int packetSize) {

		// long owner = c.getInStream().readQWord();
		System.out.println("hi");
		c.packetType = -1;
		c.packetSize = 0;
		return;

	}

}
