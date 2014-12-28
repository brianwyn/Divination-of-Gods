package server.model.players.packets;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.model.players.content.combat.BountyHunter.PvPHandler;
import server.model.players.content.skills.SkillHandler;

/**
 * Walking packet
 **/
public class Walking implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		if (c.clickNpcType > 0) {
			c.clickNpcType = 0;
		}
		if (c.npcFDelay > 0)
			c.npcFDelay = 0;
		if (c.clickObjectType > 0) {
			c.clickObjectType = 0;
		}
		if (c.inWild() && !c.EP_ACTIVE)
			PvPHandler.handleEP(c);
		if (!c.inRing() && c.inBarbDef && !c.needstorelog && !c.inWild()) {
			c.getPA().moveBarb();
			c.getPA().closeAllWindows();
			return;
		}
		if(c.isResting) {
			c.startAnimation(11788);
			c.isResting = false;
			c.playerStandIndex = 0x328;
			c.getPA().requestUpdates();
			c.getPA().sendFrame36(9999, 0);
			CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {

				@Override
				public void execute(CycleEventContainer container) {
					container.stop();
				}

				@Override
				public void stop() {
				}

			}, 1);
		}
		if(c.playerIsFiremaking) {
			CycleEventHandler.getSingleton().stopEvents(c);
		}
		
		if (c.isAttacking == true) {
			c.isAttacking = false;
		}

		if (c.isWalking == false) {
			c.isWalking = true;
		}
		if (c.doTut || c.doingTut) {
			return;
		}
		/*
		 * if (CycleEventHandler.getSingleton().getEventsCount() > 0) {
		 * CycleEventHandler.getSingleton().stopEvents(c); }
		 */
		if (c.walkingToItem == true) {
			c.walkingToItem = false;
		}

		if (packetType == 248 || packetType == 164) {
			c.faceUpdate(0);
			if (c.SSPLIT == false) {
				c.npcIndex = 0;
				c.playerIndex = 0;
			} else {
			}
			if (c.followId > 0 || c.followId2 > 0) {
				c.getPA().resetFollow();
			}
		}

		if (c.duelRule[1] && c.duelStatus == 5) {
			if (PlayerHandler.players[c.duelingWith] != null) {
				if (!c.goodDistance(c.getX(), c.getY(),
						PlayerHandler.players[c.duelingWith].getX(),
						PlayerHandler.players[c.duelingWith].getY(), 1)
						|| c.attackTimer == 0) {
					c.sendMessage("Walking has been disabled in this duel!");
				}
			}
			c.playerIndex = 0;
			return;
		}
		if (c.storing) {
			c.getPA().closeAllWindows();
			c.storing = false;
		}
		if (c.inDuelScreen && c.duelStatus != 5) {
			Client o = (Client) PlayerHandler.players[c.duelingWith];
			c.getTradeAndDuel().declineDuel();
			o.getTradeAndDuel().declineDuel();
		}
		if (c.inTrade) {
			c.sendMessage("Please use the Decline option.");
			return;
		}
		if (c.isChoosingDung) {
			c.sendMessage("To close this window, select a class..");
			c.isChoosingDung = true;
			c.getPA().showInterface(17050);
			return;
		}
		if (c.isSpinningDaSof == true) {
			c.sendMessage("You can't walk at the moment. Please open up SoF again, or relog.");
			return;
		}
		if (!c.isBanking && !c.inTrade && !c.inFoGGame) {
			c.getPA().removeAllWindows();
		}
		if (c.stopPlayerSkill) {
			SkillHandler.resetPlayerSkillVariables(c);
		}
		if (c.freezeTimer > 0) {
			if (PlayerHandler.players[c.playerIndex] != null) {
				if (c.goodDistance(c.getX(), c.getY(),
						PlayerHandler.players[c.playerIndex].getX(),
						PlayerHandler.players[c.playerIndex].getY(), 1)
						&& packetType != 98) {
					c.playerIndex = 0;
					return;
				}
			}
			if (packetType != 98) {
				c.sendMessage("A magical force stops you from moving.");
				c.playerIndex = 0;
			}
			return;
		}

		if (System.currentTimeMillis() - c.lastSpear < 4000) {
			c.sendMessage("You have been stunned.");
			c.playerIndex = 0;
			return;
		}

		if (packetType == 98) {
			c.mageAllowed = true;
		}

		if ((c.duelStatus >= 1 && c.duelStatus <= 4) || c.duelStatus == 6) {
			if (c.duelStatus == 6) {
				c.getTradeAndDuel().claimStakedItems();
			}
			return;
		}

		if (c.respawnTimer > 3) {
			return;
		}
		if (System.currentTimeMillis() - c.lastEmote <= 7000) {
			return;
		}
		if (packetType == 248) {
			packetSize -= 14;
		}
		c.newWalkCmdSteps = (packetSize - 5) / 2;
		if (++c.newWalkCmdSteps > c.walkingQueueSize) {
			c.newWalkCmdSteps = 0;
			return;
		}

		c.getNewWalkCmdX()[0] = c.getNewWalkCmdY()[0] = 0;

		int firstStepX = c.getInStream().readSignedWordBigEndianA()
				- c.getMapRegionX() * 8;
		for (int i = 1; i < c.newWalkCmdSteps; i++) {
			c.getNewWalkCmdX()[i] = c.getInStream().readSignedByte();
			c.getNewWalkCmdY()[i] = c.getInStream().readSignedByte();
		}

		int firstStepY = c.getInStream().readSignedWordBigEndian()
				- c.getMapRegionY() * 8;
		c.setNewWalkCmdIsRunning(c.getInStream().readSignedByteC() == 1);
		for (int i1 = 0; i1 < c.newWalkCmdSteps; i1++) {
			c.getNewWalkCmdX()[i1] += firstStepX;
			c.getNewWalkCmdY()[i1] += firstStepY;
		}
		if ((c.absX - (c.mapRegionX * 8)) - firstStepX != 0
				&& (c.absY - (c.mapRegionY * 8)) - firstStepY != 0)
			c.startAnimation(65535);
	}
}
