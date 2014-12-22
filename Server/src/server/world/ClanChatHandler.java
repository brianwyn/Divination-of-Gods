package server.world;

import server.Config;
import server.Connection;
import server.Server;
import server.model.items.GameItem;
import server.model.items.ItemAssistant;
import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.util.Misc;

/**
 * @author Sanity, PJNoMore
 */

public class ClanChatHandler {

	public ClanChatHandler() {

	}

	public Clan[] clans = new Clan[100];

	public void handleClanChat(Client c, String name) {
		if (c.clanId != -1) {
			c.sendMessage("You are already in a clan!");
			return;
		}
		for (int j = 0; j < clans.length; j++) {
			if (clans[j] != null) {
				if (clans[j].name.equalsIgnoreCase(name)) {
					if (checkPass(c, j)) {
						addToClan(c.playerId, j);
						return;
					} else {
						c.sendMessage("Incorrect password!");
						return;
					}
				}
			}
		}
		makeClan(c, name);
	}

	public void joinClan(Client c, String clanName) {
		if (clanName == null || clanName == "")
			return;
		if (c.clanId > -1) {
			c.sendMessage("You're already in a clan!");
			return;
		}
		for (int j = 0; j < clans.length; j++) {
			if (clans[j] != null) {
				if (clans[j].name.equalsIgnoreCase(clanName)) {
					if (checkPass(c, j))
						addToClan(c.playerId, j);
					else
						c.sendMessage("Incorrect password!");
				}
			}
		}
	}

	public void makeClan(Client c, String name) {

		String pass = null;
		boolean loot = false;

		if (openClan() >= 0) {
			if (hasClan(c)) {
				c.sendMessage("Please try that name again... Your clan's name is: <col=255>"
						+ getPlayerClan(c.getPlayerName()) + ".");
				return;
			}
			if (validName(name)) {
				c.clanId = openClan();
				createClan(c.playerName, name, pass, loot, false, c.friends);
				addToClan(c.playerId, c.clanId);
				Server.pJClans.saveClan(Server.clanChat.clans[c.clanId]);
				c.hasClan = true;
			} else {
				c.sendMessage("A clan with this name already exists.");
			}
		} else {
			c.sendMessage("Your clan chat request could not be completed.");
		}
	}

	private boolean hasClan(Client c) {
		return c.hasClan;
	}

	public void mutePlayer(Client c, String name) {
		if (!isOwner(c)) {
			c.sendMessage("You do not have the power to do that!");
			return;
		}
		if (clans[c.clanId] != null) {
			for (int j = 0; j < clans[c.clanId].mutedMembers.length; j++) {
				for (int i = 0; j < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i].playerName
							.equalsIgnoreCase(name)) {
						Client c2 = (Client) PlayerHandler.players[i];
						if (!isInClan(c2)) {
							c.sendMessage(c2.getPlayerName()
									+ " is not in your clan!");
							return;
						}
						if (clans[c.clanId].mutedMembers[j] <= 0) {
							clans[c.clanId].mutedMembers[j] = i;
							c2.sendMessage("You have been muted in: "
									+ clans[c.clanId].name);
						}
					} else {
						c.sendMessage("This person is not online!");
					}
				}
			}
		}
	}

	public void unmutePlayer(Client c, String name) {
		if (!isOwner(c)) {
			c.sendMessage("You do not have the power to do that!");
			return;
		}
		if (clans[c.clanId] != null) {
			for (int j = 0; j < clans[c.clanId].mutedMembers.length; j++) {
				for (int i = 0; j < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i].playerName
							.equalsIgnoreCase(name)) {
						Client c2 = (Client) PlayerHandler.players[i];
						if (!isInClan(c2)) {
							c.sendMessage(c2.getPlayerName()
									+ " is not in your clan!");
							return;
						}
						if (clans[c.clanId].mutedMembers[j] == i) {
							clans[c.clanId].mutedMembers[j] = -1;
							c2.sendMessage("You have been unmuted in: "
									+ clans[c.clanId].name);
						}
					} else {
						c.sendMessage("This person is not online!");
					}
				}
			}
		}
	}

	public boolean isOwner(Client c) {
		if (c.clanId < 0)
			return false;
		if (clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
			return true;
		}
		return false;
	}

	public boolean isInClan(Client c) {
		for (int i = 0; i < clans.length; i++) {
			if (clans[i] != null) {
				for (int j = 0; i < clans[i].members.length; j++) {
					if (clans[i].members[j] == c.playerId) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isClanMuted(Client c) {
		if (c.isOwner()) {
			return false;
		}
		for (int i = 0; i < clans[c.clanId].mutedMembers[i]; i++) {
			if (clans[c.clanId].members[i] == c.playerId) {
				return true;
			}
		}
		return false;
	}

	public void createClan(String owner, String name, String pass,
			boolean lootshare, boolean hasPass, long[] friendsList) {
		if (openClan() >= 0) {
			if (validName(name)) {
				clans[openClan()] = new Clan(owner, name, pass, lootshare,
						hasPass, friendsList);
			}
		}
	}

	public void setClanPassword(Client c, String pass, boolean hasPass) {
		if (isOwner(c)) {
			clans[c.clanId].password = pass;
			clans[c.clanId].hasPassword = hasPass;
			c.sendMessage("Your new clan chat password is: " + pass);
		} else {
			c.sendMessage("You do not have the rights to change this clan's password.");
		}
	}

	public void changeOwner(Client c, String name) {
		if (c.clanId < 0) {
			c.sendMessage("You are not in a clan!");
			return;
		}
		if (c.isOwner()) {
			clans[c.clanId].owner = name;
			updateClanChat(c.clanId);
		}
	}

	public void kickPlayerFromClan(Client c, String name) {
		if (c.clanId < 0) {
			c.sendMessage("You are not in a clan.");
			return;
		}
		if (!isOwner(c) && !c.isOwner()) {
			c.sendMessage("You do not have the power to kick players from this clan chat!");
			return;
		}
		if (c.playerName.equalsIgnoreCase(name)) {
			c.sendMessage("You may not kick yourself from a clan chat!");
			return;
		}

		Client playerToKick = Client.getClient(name);
		if (playerToKick == null)
			return;
		if (playerToKick.isStaff() && !c.isAdmin()) {
			c.sendMessage("You may NOT kick staff from you clan!");
			if (playerToKick.isOwner())
				playerToKick.sendMessage(c.getPlayerName()
						+ " has tried to kick you from his/her clan.");
			return;
		}
		playerToKick.clanId = -1;
		playerToKick.sendMessage("You have been kicked from "
				+ clans[c.clanId].name + " by " + c.getPlayerName());
		playerToKick.getPA().clearClanChat();
		c.sendMessage("You have kicked " + playerToKick.getPlayerName()
				+ " from your clan.");
		for (int j = 0; j < clans[c.clanId].members.length; j++) {
			if (clans[c.clanId].members[j] == playerToKick.playerId) {
				clans[c.clanId].members[j] = -1;
			}
		}

		updateClanChat(c.clanId);
	}

	public void removeIdFromClan(int clanId, int playerId) {
		if (clanId < 0)
			return;
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clans[clanId].members[j] == playerId) {
				clans[clanId].members[j] = -1;
			}
		}
		updateClanChat(clanId);
	}

	public void updateClanChat(int clanId) {
		if (clanId < 0)
			return;
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clans[clanId].members[j] <= 0)
				continue;
			if (PlayerHandler.players[clans[clanId].members[j]] != null) {
				Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
				if (isOwner(c))
					clans[clanId].friendsList = c.friends;
				c.getPA().sendFrame126("Talking in: " + clans[clanId].name,
						18139);
				c.getPA().sendFrame126(
						"Owner: " + Misc.formatPlayerName(clans[clanId].owner),
						18140);
				if (clans[clanId].lootshare)
					c.getPA().sendFrame36(724, 1);
				else
					c.getPA().sendFrame36(724, 0);
				int slotToFill = 18144;
				for (int i = 0; i < clans[clanId].members.length; i++) {
					if (clans[clanId].members[i] > 0) {
						Client clanMember = (Client) PlayerHandler.players[clans[clanId].members[i]];
						if (clanMember != null) {
							c.getPA().sendFrame126(
									getPlayerIcon(clanMember, clanId)
											+ clanMember.getPlayerName(),
									slotToFill);
							slotToFill++;
						} else {
							removeIdFromClan(clanId, clans[clanId].members[i]);
						}
					}
				}
				for (int k = slotToFill; k < 18244; k++)
					c.getPA().sendFrame126("", k);
			}
		}
	}

	private String getPlayerIcon(Client member, int clanId) {
		if (clans[clanId].owner.equalsIgnoreCase(member.getPlayerName()))
			return "<cc=7>";
		if (member.isStaff())
			return "<img=" + (member.playerRights - 1) + ">";
		for (long friend : clans[clanId].friendsList)
			if (Misc.playerNameToInt64(member.getPlayerName()) == friend)
				return "<cc=0>";
		return "";
	}

	public int openClan() {
		for (int j = 0; j < clans.length; j++) {
			if (clans[j] == null || clans[j].owner == "")
				return j;
		}
		return -1;
	}

	public String getPlayerClan(String playerName) {
		for (int j = 0; j < clans.length; j++) {
			if (clans[j] != null) {
				if (playerName.equalsIgnoreCase(clans[j].owner))
					return clans[j].name;
			}
		}
		return "";
	}

	public boolean validName(String name) {
		for (int j = 0; j < clans.length; j++) {
			if (clans[j] != null) {
				if (clans[j].name.equalsIgnoreCase(name))
					return false;
			}
		}
		return true;
	}

	public boolean checkPass(Client c, int clanId) {
		if (!clans[clanId].hasPassword) {
			return true;
		}
		if (c.isStaff()) {
			return true;
		}
		if (clans[clanId].owner.equalsIgnoreCase(c.playerName)) {
			return true;
		}
		if (clans[clanId].password.equalsIgnoreCase(c.clanPass)) {
			return true;
		}
		return false;
	}

	public void setLootshare(Client c, boolean status) {
		if (isOwner(c) || c.isOwner()) {
			clans[c.clanId].lootshare = status;
			sendLootShareMessage(c.clanId,
					"Lootshare has been turned "
							+ (Server.clanChat.clans[c.clanId].lootshare ? "ON"
									: "OFF") + " by the "
							+ (isOwner(c) ? "clan leader." : "Server Owner."));
			updateClanChat(c.clanId);
			Server.pJClans.saveClan(clans[c.clanId]);

		} else {
			c.sendMessage("You do not have the rights to use this button.");
		}
	}

	public boolean lootshareStatus(Client c) {
		if (c.clanId == -1) {
			return false;
		}
		return clans[c.clanId].lootshare;
	}

	public void addToClan(int playerId, int clanId) {
		if (clans[clanId] != null && PlayerHandler.players[playerId] != null) {
			PlayerHandler.players[playerId].clanId = clanId;
			PlayerHandler.players[playerId].clanName = clans[clanId].name;
			if (clans[clanId].lootshare)
				((Client) PlayerHandler.players[playerId]).getPA().sendFrame36(
						724, 1);
			else
				((Client) PlayerHandler.players[playerId]).getPA().sendFrame36(
						724, 0);
			for (int j = 0; j < clans[clanId].members.length; j++) {
				if (clans[clanId].members[j] <= 0) {
					clans[clanId].members[j] = playerId;
					messageToClan(
							(PlayerHandler.players[playerId].playerRights > 0 ? "<img="
									+ (PlayerHandler.players[playerId].playerRights - 1)
									+ ">"
									: "")
									+ PlayerHandler.players[playerId].getPlayerName()
									+ " has joined the channel.", clanId);
					updateClanChat(clanId);
					return;
				}
			}
		}
	}

	public void leaveClan(int playerId, int clanId) {
		Client c = (Client) PlayerHandler.players[playerId];
		if (c == null)
			return;
		if (clanId < 0 || clans[clanId] == null) {
			PlayerHandler.players[playerId].clanId = -1;
			c.sendMessage("You are not in a clan.");
			return;
		}
		if (clans[clanId].members.length < 1) {
			c.sendMessage("You have left the clan.");
			c.clanId = -1;
			c.clanName = "";
			updateClanChat(clanId);
			c.getPA().clearClanChat();
			return;
		}
		for (int j = 0; j < clans[clanId].members.length; j++) {
			Client c2 = (Client) PlayerHandler.players[clans[clanId].members[j]];
			if (clanId > -1 && c2 != null && c2.playerId != c.playerId) {
				c2.sendMessage(c.getPlayerName() + " has left the clan.");
			}
		}
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clans[clanId].members[j] == playerId) {
				clans[clanId].members[j] = -1;
			}
		}
		c.sendMessage("You have left the clan.");
		c.clanId = -1;
		c.clanName = "";
		updateClanChat(clanId);
		c.getPA().clearClanChat();
	}

	public void destructClan(int clanId) {
		if (clanId < 0)
			return;
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clanId < 0)
				continue;
			if (clans[clanId].members[j] <= 0)
				continue;
			if (PlayerHandler.players[clans[clanId].members[j]] != null) {
				Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
				c.clanId = -1;
				c.getPA().clearClanChat();
			}
		}
		clans[clanId].members = new int[50];
		clans[clanId].owner = "";
		clans[clanId].name = "";
		clans[clanId].password = null;
		clans[clanId].hasPassword = false;
	}

	public void messageToClan(String message, int clanId) {
		if (clanId < 0)
			return;
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clans[clanId].members[j] < 0)
				continue;
			if (PlayerHandler.players[clans[clanId].members[j]] != null) {
				Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
				c.sendMessage("<img=7> " + message);
			}
		}
	}

	public void playerMessageToClan(int playerId, String message, int clanId) {
		if (clanId < 0)
			return;
		if (Connection.isMuted((Client) PlayerHandler.players[playerId])) {
			Client c = (Client) PlayerHandler.players[playerId];
			c.sendMessage("You are muted and are not permitted to speak!");
			return;
		}
		if (isClanMuted((Client) PlayerHandler.players[playerId])) {
			Client c = (Client) PlayerHandler.players[playerId];
			c.sendMessage("You are muted in this clan and are not permitted to speak!");
			return;
		}
		for (int j = 0; j < clans[clanId].members.length; j++) {
			if (clans[clanId].members[j] <= 0)
				continue;
			if (PlayerHandler.players[clans[clanId].members[j]] != null) {
				Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
				c.sendClan(PlayerHandler.players[playerId].getPlayerName(),
						message, clans[clanId].name,
						PlayerHandler.players[playerId].playerRights);
			}
		}
	}

	public void sendLootShareMessage(int clanId, String message) {
		if (clanId >= 0) {
			for (int j = 0; j < clans[clanId].members.length; j++) {
				if (clans[clanId].members[j] <= 0)
					continue;
				if (PlayerHandler.players[clans[clanId].members[j]] != null) {
					Client c = (Client) PlayerHandler.players[clans[clanId].members[j]];
					c.sendClan("", message, clans[clanId].name, 7);
				}
			}
		}
	}

	public void handleLootShare(Client c, GameItem item) {
		sendLootShareMessage(c.clanId, c.getPlayerName() + " has received x"
				+ item.amount + " " + ItemAssistant.getItemName(item.id) + ".");
	}
}