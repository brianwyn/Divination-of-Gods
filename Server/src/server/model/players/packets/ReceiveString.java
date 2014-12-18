package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerSave;

public class ReceiveString implements PacketType {

	@Override
	public void processPacket(Client player, int packetType, int packetSize) {
		if (packetType == 0) {
			System.out.println("clans");
			return;
		}
		String text = player.getInStream().readString();
		int index = text.indexOf(",");
		int id = Integer.parseInt(text.substring(0, index));
		String string = text.substring(index + 1);
		switch (id) {
		case 1:
			if (string.length() == 0) {
				break;
			} else if (string.length() > 15) {
				string = string.substring(0, 15);
			}

			break;
		case 2:
			if (string.length() == 0) {
				break;
			} else if (string.length() > 12) {
				string = string.substring(0, 12);
			}
			if (string.equalsIgnoreCase(player.playerName)) {
				break;
			}
			if (!PlayerSave.playerExists(string)) {
				player.sendMessage("This player doesn't exist!");
				break;
			}
			break;
		case 3:
			if (string.length() == 0) {
				break;
			} else if (string.length() > 12) {
				string = string.substring(0, 12);
			}
			if (string.equalsIgnoreCase(player.playerName)) {
				break;
			}
			if (!PlayerSave.playerExists(string)) {
				player.sendMessage("This player doesn't exist!");
				break;
			}
			break;
		default:
			System.out.println("Received string: identifier=" + id
					+ ", string=" + string);
			break;
		}
	}

}
