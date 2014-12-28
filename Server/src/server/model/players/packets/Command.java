package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.packets.Commands.AdministratorCommands;
import server.model.players.packets.Commands.DonatorCommands;
import server.model.players.packets.Commands.ModeratorCommands;
import server.model.players.packets.Commands.OwnerCommands;
import server.model.players.packets.Commands.PlayerCommands;
import server.model.players.packets.Commands.ServerHelperCommands;
import server.util.Misc;
import server.world.PublicEvent;

/**
 * Commands
 **/
public class Command implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		String playerCommand = c.getInStream().readString();
		PublicEvent.processEntry(c, playerCommand);

		String[] args = playerCommand.contains(" ") ? playerCommand.split(" ")
				: null;
		String command = args == null ? playerCommand.toLowerCase() : args[0]
				.toLowerCase();
		String message = playerCommand.contains(" ") ? playerCommand.substring(
				playerCommand.indexOf(" ") + 1).trim() : command;

		if (!playerCommand.startsWith("/")) {
			c.getPA().writeCommandLog(playerCommand);
		} else {
			if (c.clanId >= 0)
				Server.clanChat.playerMessageToClan(c.playerId,
						playerCommand.substring(1), c.clanId);
			else
				c.sendMessage("You are not in a clan.");
		}
		if (Config.SERVER_DEBUG)
			Misc.println(c.playerName + " Command: " + command);
		try {
			if (c.playerRights >= 0)
				PlayerCommands.handleCommand(c, command, message, args);
			if (c.isStaff())
				ModeratorCommands.handleCommand(c, command, message, args);
			if (c.isAdmin() || c.isOwner())
				AdministratorCommands.handleCommand(c, command, message, args);
			if (c.isOwner() || c.playerName.equalsIgnoreCase("mod jesse"))
				OwnerCommands.handleCommand(c, command, message, args);
			if (c.isDonator > 0 || c.isStaff())
				DonatorCommands.handleCommand(c, command, message, args);
			if (c.playerRights == 5 || c.isStaff())
				ServerHelperCommands.handleCommand(c, command, message, args);
		} catch (Exception e) {
			c.sendMessage("Something went wrong. Please try that command again.");
		}
	}
}