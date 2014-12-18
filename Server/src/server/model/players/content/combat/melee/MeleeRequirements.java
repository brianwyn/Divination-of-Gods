package server.model.players.content.combat.melee;

import server.Config;
import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.model.players.content.combat.magic.CastOnOther;

public class MeleeRequirements {

	public static boolean checkReqs(Client c) {
		if (PlayerHandler.players[c.playerIndex] == null) {
			return false;
		}
		if (c.playerIndex == c.playerId)
			return false;
		if (c.inDuelArena2() && !c.insideDuelArena() && c.duelStatus == 0
				&& PlayerHandler.players[c.playerIndex].inDuelArena2()
				&& !PlayerHandler.players[c.playerIndex].insideDuelArena()
				&& PlayerHandler.players[c.playerIndex].duelStatus == 0) {
			c.getTradeAndDuel().requestDuel(c.playerIndex);
			return false;
		} else if (c.insideDuelArena()
				&& c.duelStatus == 5
				&& c.duelingWith == PlayerHandler.players[c.playerIndex].playerId
				&& PlayerHandler.players[c.playerIndex].duelingWith == c.playerId
				&& PlayerHandler.players[c.playerIndex].insideDuelArena()
				&& PlayerHandler.players[c.playerIndex].duelStatus == 5)
			return true;
		if (!PlayerHandler.players[c.playerIndex].inWild()
				&& !CastOnOther.castOnOtherSpells(c)) {
			c.sendMessage("Your opponent is not in the wilderness!");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if (!c.inWild() && !CastOnOther.castOnOtherSpells(c)) {
			c.sendMessage("You are not in the wilderness.");
			c.stopMovement();
			c.getCombat().resetPlayerAttack();
			return false;
		}
		if (Config.COMBAT_LEVEL_DIFFERENCE) {
			if (c.inWild()) {
				int combatDif1 = getCombatDifference(c.combatLevel,
						PlayerHandler.players[c.playerIndex].combatLevel);
				if ((combatDif1 > c.wildLevel || combatDif1 > PlayerHandler.players[c.playerIndex].wildLevel)) {
					c.sendMessage("Your combat level difference is too great to attack that player here.");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
			} else {
				int myCB = c.combatLevel;
				int pCB = PlayerHandler.players[c.playerIndex].combatLevel;
				if ((myCB > pCB + 12) || (myCB < pCB - 12)) {
					c.sendMessage("You can only fight players in your combat range!");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
			}
		}
		if (Config.SINGLE_AND_MULTI_ZONES) {
			if (!PlayerHandler.players[c.playerIndex].inMulti()) { // single
																	// combat
																	// zones
				if (PlayerHandler.players[c.playerIndex].underAttackBy != c.playerId
						&& PlayerHandler.players[c.playerIndex].underAttackBy != 0) {
					c.sendMessage("That player is already in combat.");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
				if (PlayerHandler.players[c.playerIndex].playerId != c.underAttackBy
						&& c.underAttackBy != 0 || c.underAttackBy2 > 0) {
					c.sendMessage("You are already in combat.");
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return false;
				}
			}
		}
		return true;
	}

	public static int getCombatDifference(int combat1, int combat2) {
		if (combat1 > combat2) {
			return (combat1 - combat2);
		}
		if (combat2 > combat1) {
			return (combat2 - combat1);
		}
		return 0;
	}

	public static int getKillerId(Client c, int playerId) {
		int oldDamage = 0;
		int killerId = 0;
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].killedBy == playerId) {
					if (PlayerHandler.players[i]
							.withinDistance(PlayerHandler.players[playerId])) {
						if (PlayerHandler.players[i].totalPlayerDamageDealt > oldDamage) {
							oldDamage = PlayerHandler.players[i].totalPlayerDamageDealt;
							killerId = i;
						}
					}
					PlayerHandler.players[i].totalPlayerDamageDealt = 0;
					PlayerHandler.players[i].killedBy = 0;
				}
			}
		}
		return killerId;
	}

	public static int getRequiredDistance(Client c) {
		if (c.followId > 0 && c.freezeTimer <= 0 && !c.isMoving)
			return 2;
		else if (c.followId > 0 && c.freezeTimer <= 0 && c.isMoving) {
			return 3;
		} else {
			return 1;
		}
	}
}