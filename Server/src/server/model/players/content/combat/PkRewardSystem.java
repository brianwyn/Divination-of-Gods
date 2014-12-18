package server.model.players.content.combat;

import java.util.ArrayList;
import java.util.List;

import server.model.players.Client;
//import server.model.players.PlayerHandler;
import server.model.players.content.GabbesAchievements;

/**
 * Handles pk point rewards
 * 
 * @author Arithium
 * 
 */
public class PkRewardSystem {

	/**
	 * Constructs a new client instance
	 */
	private final Client client;

	/**
	 * The maximium amount of players you can kill before resetting your list
	 */
	private final int WAIT_LIMIT = 3;

	/**
	 * A list of all the players you have killed
	 */
	private List<String> killedPlayers = new ArrayList<String>();

	/**
	 * Constructs a new <code>PkRewardSystem</code> with a client instance
	 * 
	 * @param client
	 */
	public PkRewardSystem(Client client) {
		this.client = client;
	}

	/**
	 * Adds a client to the list
	 * 
	 * @param other
	 */
	public void add(Client other) {
		if (killedPlayers.size() >= WAIT_LIMIT) {
			/*
			 * Clears the list
			 */
			killedPlayers.clear();
			handleReward(other);
		} else {
			if (!killedPlayers.contains(other.playerName)) {
				handleReward(other);
			} else {
				client.sendMessage("You have recently defeated "
						+ other.playerName + ".");
			}
		}
	}

	public List<String> getKilledPlayers() {
		return killedPlayers;
	}

	/**
	 * Gives the player a reward for defeating his opponent
	 * 
	 * @param other
	 */
	private void handleReward(Client o) {
		if (!client.connectedFrom.equalsIgnoreCase(o.connectedFrom)
				&& !client.inDuelArena()) {
			o.getPA().handleEPDrops();
			killedPlayers.add(o.playerName);
			client.pkPoints += 1;
			client.newKil += 1;
			if (client.newKil == 5 && !client.task2[2]) {
				client.task2[1] = true;
				client.sendMessage("You've completed the task: Kill 5 Players!");
				client.TPoints += 2;
				client.achievementInterface("Kill 5 Players!");
				client.sendMessage("You've received two Task Points! You now have a total of "
						+ client.TPoints + " points!");
				client.SaveGame();
			}
			if (client.newKil == 15 && !client.task3[0]) {
				GabbesAchievements.handleEliteTask(o, 0, 3, "Kill 15 Players!");
			}
			client.killstreak += 1;
			client.isAttacking = false;
			client.totalkills += 1;
			o.getPA().handleKSYell();
		} else {
			client.sendMessage("You can not kill a player on the same ip address.");
		}
	}

	public void setKilledPlayers(List<String> list) {
		killedPlayers = list;
	}

}
