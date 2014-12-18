package server.model.players.content;

import server.model.players.Client;
import server.model.players.content.skills.Dungeoneering;

public class Dying {
	public static void handleDeaths(Client c) {
		if (c.cannonIsShooting && c.hasCannon) {
			c.bankCannonBalls = true;
			c.getCannon().bankCannon();
		} else {
			if (!c.cannonIsShooting && c.hasCannon) {
				c.bankCannonBalls = false;
				c.getCannon().bankCannon();
			}
		}
		if (c.isDead2(c) && c.inDung) {
			c.getDungeoneering();
			Dungeoneering.handleDeath(c);
			return;
		}
		if (c.isDead2(c) && c.inDung) {
			return;
		}
		if (c.isDead2(c) && c.trade11 > 0 && !c.inFightCaves() && !c.InDung
				&& !c.inPits() && !c.inBarbDef && !c.inPcGame() && !c.inFunPk()
				&& c.inCwWait == false && c.inCwGame == false) {
			c.getPA().moveSafe();
			return;
		}
		if (c.isDead2(c) && c.playerRights == 3 || c.playerRights == 2
				&& c.isDead2(c) && c.inCwWait == false && c.inCwGame == false) {
			c.getPA().moveAdmin();
			c.playerLevel[3] = 99;
			c.getPA().refreshSkill(3);
			return;
		} else if (c.isDead2(c) && c.inFunPk()) {
			c.getPA().moveFunPK();
			return;
		} else if (c.isDead2(c) && c.safeZone()) {
			c.getPA().moveFunPK();
			return;
		} else if (c.isDead2(c) && c.inFightCaves() && !c.InDung && !c.inPits()
				&& !c.inBarbDef && !c.inPcGame() && !c.inFunPk()) {
			c.getPA().moveSafe();
		} else if (c.isDead2(c) && c.inPcGame() && !c.inFightCaves()
				&& !c.inFunPk()) {
			c.getPA().movePc();
		} else if (c.isDead2(c) && c.inBarbDef && !c.inFightCaves()
				&& !c.inFunPk()) {
			c.getPA().moveBarb();
		} else if (c.isDead2(c) && c.respawnTimer == -6 && !c.inFightCaves()
				&& c.trade11 < 1 && !c.InDung && !c.inPits() && !c.inBarbDef
				&& !c.inPcGame() && !c.inFunPk()) {
			c.getPA().applyDead();
			return;

		}
	}
}
