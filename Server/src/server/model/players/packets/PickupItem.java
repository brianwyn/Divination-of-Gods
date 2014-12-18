package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.skills.Firemaking;
import server.model.players.content.skills.SkillHandler;

/**
 * Pickup Item
 **/
public class PickupItem implements PacketType {

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		c.pItemY = c.getInStream().readSignedWordBigEndian();
		c.pItemId = c.getInStream().readUnsignedWord();
		c.pItemX = c.getInStream().readSignedWordBigEndian();
		if (Math.abs(c.getX() - c.pItemX) > 25
				|| Math.abs(c.getY() - c.pItemY) > 25) {
			c.resetWalkingQueue();
			return;
		}
		for (int i : Config.NON_PICKUPABLE_ITEMS) {
			if (c.pItemId == i) {
				c.sendMessage("You are not allowed to take this item.");
				return;
			}
		}

		if (c.playerIsFiremaking || SkillHandler.isSkilling[11]
				|| Firemaking.logNulled) {
			c.sendMessage("You're currently busy with something else.");
			Firemaking.logNulled = false;
			SkillHandler.isSkilling[11] = false;
			c.playerIsFiremaking = false;
			SkillHandler.lastSkillingAction = System.currentTimeMillis();
			c.startAnimation(65535);
			return;
		}
		if (!c.task2[3] && c.pItemId == 8850 && c.inWarriorG()) {
			c.task2[3] = true;
			c.sendMessage("You've completed the task: Obtain Rune Defender!");
			c.TPoints += 2;
			c.achievementInterface("Obtain Rune Defender!");
			c.sendMessage("You've received two Task Points! You now have a total of "
					+ c.TPoints + " points!");
			c.SaveGame();
		}
		c.getCombat().resetPlayerAttack();
		if (c.getX() == c.pItemX && c.getY() == c.pItemY) {
			Server.itemHandler.removeGroundItem(c, c.pItemId, c.pItemX,
					c.pItemY, true);
			c.getPA().requestUpdates();
			if (c.pItemId == 17489) {
				c.GatestoneX = 0;
				c.GatestoneY = 0;
				if (c.inParty) {
					// Server.clanChat.messageToClan("[Dungeoneering] "+Misc.optimizeText(c.playerName)+" has picked up the groupstone!",
					// c.clanId);
					for (Player p : PlayerHandler.players) {
						if (p != null) {
							Client ALLPLAYERS = (Client) p;
							if (ALLPLAYERS.playerName
									.equalsIgnoreCase(c.Partner)) {
								Client c2 = (Client) p;
								if (c2 != null) {
									Server.itemHandler.groupStone();
									c2.GatestoneX = 0;
									c2.GatestoneY = 0;

								}
							}
						}
					}
				}
			}

		} else {
			c.walkingToItem = true;
		}
	}
}