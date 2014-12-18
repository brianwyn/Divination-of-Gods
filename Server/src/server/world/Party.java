package server.world;

import server.model.players.Client;
import server.model.players.PlayerHandler;

/**
 * @author Gabbe
 */

public class Party {

	public static Party[] partys = new Party[100];

	public static void addToParty(Client c, int id, int playerId) {
		if (partys[id] != null) {
			for (int j = 0; j < partys[id].members.length; j++) {
				if (partys[id].members[j] <= 0) {
					if (PlayerHandler.players[playerId].playerName != null)
						partys[id].members[j] = playerId;

					// Client c = (Client)PlayerHandler.players[playerId];

				}
			}
		}
	}
	public static boolean isInParty(Client c, int id) {
		if (partys[id] != null) {
			for (int j = 0; id < partys[id].members.length; j++) {
				if (partys[id].members[j] == c.playerId
						|| partys[id].owner.equalsIgnoreCase(c.playerName)) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean isPartyOwner(Client c, int id) {
		if (partys[id] != null) {
			if (partys[id].owner.equalsIgnoreCase(c.playerName)) {
				return true;
			}
		}
		return false;
	}
	public int[] members = new int[50];

	public String owner;

	public int partyId;

	public Party(String owner, int id) {
		this.owner = owner;
		this.partyId = id;
	}

	public void createOwner(Client c, int id) {
		if (c == null) {
			return;
		}
		if (isInParty(c, id)) {
			if (partys[id] != null) {
				for (int j = 0; id < partys[id].members.length; j++) {
					if (partys[id].members[j] == c.playerId) {
						partys[id].owner = c.playerName;

					}
				}
			}
		}

	}

}