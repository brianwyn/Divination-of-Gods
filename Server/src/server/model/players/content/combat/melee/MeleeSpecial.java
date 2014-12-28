package server.model.players.content.combat.melee;

import server.model.items.ItemAssistant;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.util.Misc;

/**
 * 
 * Weapon Specials
 * 
 * Class MeleeData
 * 
 * @author 2012
 * 
 */

public class MeleeSpecial {

	public static void activateSpecial(Client c, int weapon, int i) {
		/*
		 * if(NPCHandler.npcs[i] == null && c.npcIndex > 0) { return; }
		 * if(PlayerHandler.players[i] == null && c.playerIndex > 0) { return; }
		 */
		c.doubleHit = false;
		c.specEffect = 0;
		c.projectileStage = 0;
		c.specMaxHitIncrease = 2;
		if (c.npcIndex > 0) {
			c.oldNpcIndex = i;
		} else if (c.playerIndex > 0) {
			c.oldPlayerIndex = i;
			PlayerHandler.players[i].underAttackBy = c.playerId;
			PlayerHandler.players[i].logoutDelay = System.currentTimeMillis();
			PlayerHandler.players[i].singleCombatDelay = System
					.currentTimeMillis();
			PlayerHandler.players[i].killerId = c.playerId;
		}
		if (c.playerIndex > 0) {
			c.getPA().followPlayer();
		} else if (c.npcIndex > 0) {
			c.getPA().followNpc();
		}
		switch (weapon) {
		case 15241: // hand cannon spec!!
			c.usingBow = true;
			c.rangeItemUsed = c.playerEquipment[Player.playerArrows];
			c.getItems().deleteArrow();
			c.lastWeaponUsed = weapon;
			c.startAnimation(12175);
			c.specAccuracy = 5.0;
			c.specDamage = 1.40;
			c.hitDelay = 5;
			c.attackTimer -= 7;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			if (c.fightMode == 2)
				if (c.playerIndex > 0)
					c.getCombat().fireProjectilePlayer();
				else if (c.npcIndex > 0)
					c.getCombat().fireProjectileNpc();
			break;
		case 10887:
			c.gfx0(1027);
			c.startAnimation(5870);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			c.specDamage = 1.20;
			c.specAccuracy = 1.85;
			break;
		case 13905: // Vesta spear
			c.startAnimation(10499);
			c.gfx0(1835);
			c.specAccuracy = 1.25;
			c.specEffect = 6;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;

		case 13907: // Vesta spear (deg)
			c.startAnimation(10499);
			c.gfx0(1835);
			c.specAccuracy = 1.25;
			c.specEffect = 6;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;

		case 13899: // Vesta LongSword
			c.startAnimation(10502);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase() + 1);
			c.specDamage = 1.35;
			c.specAccuracy = 2.00;
			break;

		case 13901: // Vesta LongSword (deg)
			c.startAnimation(10502);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase() + 1);
			c.specDamage = 1.35;
			c.specAccuracy = 2.00;
			break;

		case 13902: // Statius
			c.startAnimation(10505);
			c.gfx0(1840);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase() + 1);
			c.specDamage = 1.35;
			c.specAccuracy = 2.25;
			break;

		case 13904: // Statius warhammer (Deg)
			c.startAnimation(10505);
			c.gfx0(1840);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase() + 1);
			c.specDamage = 1.35;
			c.specAccuracy = 2.25;
			break;
		case 1305: // dragon long
			c.gfx100(248);
			c.startAnimation(1058);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			c.specAccuracy = 1.10;
			c.specDamage = 1.20;
			break;

		case 1215: // dragon daggers
		case 1231:
		case 5680:
		case 5698:
			c.gfx100(252);
			c.startAnimation(1062);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			c.doubleHit = true;
			c.specAccuracy = 1.30;
			c.specDamage = 1.05;
			break;

		case 11730:
			c.gfx100(1224);
			c.startAnimation(7072);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			c.doubleHit = true;
			c.ssSpec = true;
			c.specAccuracy = 1.30;
			break;

		case 14484: // Dragon claws
			c.gfx0(1950);
			c.specAccuracy = 1.90;
			c.specDamage = 1.20;
			c.startAnimation(10961);
			c.usingClaws = true;
			c.doubleHit = true;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;

		case 4151: // whip
		case 15441: // whip
		case 15442: // whip
		case 15443: // whip
		case 15444: // whip
			if (NPCHandler.npcs[i] != null) {
				NPCHandler.npcs[i].gfx100(341);
			}
			c.specAccuracy = 1.10;
			c.startAnimation(1658);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;

		case 20000: // spirit gs
			c.startAnimation(7074);
			c.specDamage = 2.25;
			c.specAccuracy = 2.85;
			c.gfx0(1222);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;
		case 20001: // spirit gs
			c.startAnimation(7074);
			c.specDamage = 2.25;
			c.specAccuracy = 2.85;
			c.gfx0(1222);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;
		case 20002: // spirit gs
			c.startAnimation(7074);
			c.specDamage = 2.25;
			c.specAccuracy = 2.85;
			c.gfx0(1222);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;
		case 20003: // spirit gs
			c.startAnimation(7074);
			c.specDamage = 2.25;
			c.specAccuracy = 2.85;
			c.gfx0(1222);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;

		case 11694: // ags
			c.startAnimation(7074);
			c.specDamage = 1.25;
			c.specAccuracy = 1.85;
			c.gfx0(1222);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;
		case 11700:
			c.startAnimation(7070);
			c.gfx0(1221);
			c.specAccuracy = 1.25;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			c.specEffect = 2;
			break;

		case 19780:
			c.startAnimation(4000);
			if (c.npcIndex > 0) {
				NPC opp = NPCHandler.npcs[c.npcIndex];
				if (opp != null)
					opp.gfx0(1224);
				opp.hitIcon = 2;
				c.korasi = true;
			}
			c.specDamage = 6.38;
			c.specAccuracy = 6.99;
			c.ssSpec = true;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());

			break;

		case 11696:
			c.startAnimation(7073);
			c.gfx0(1223);
			c.specDamage = 1.10;
			c.specAccuracy = 1.5;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			c.specEffect = 3;
			break;

		case 11698:
			c.startAnimation(7071);
			c.gfx0(1220);
			c.specAccuracy = 1.25;
			c.specEffect = 4;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;

		case 1249:
			c.startAnimation(405);
			c.gfx100(253);
			if (c.playerIndex > 0) {
				Client o = (Client) PlayerHandler.players[i];
				o.getPA().getSpeared(c.absX, c.absY);
			}
			break;

		case 3204: // d hally
			c.gfx100(282);
			c.startAnimation(1203);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			if (NPCHandler.npcs[i] != null && c.npcIndex > 0) {
				if (!c.goodDistance(c.getX(), c.getY(),
						NPCHandler.npcs[i].getX(), NPCHandler.npcs[i].getY(), 1)) {
					c.doubleHit = true;
				}
			}
			if (i < PlayerHandler.players.length
					&& PlayerHandler.players[i] != null && c.playerIndex > 0) {
				if (!c.goodDistance(c.getX(), c.getY(),
						PlayerHandler.players[i].getX(),
						PlayerHandler.players[i].getY(), 1)) {
					c.doubleHit = true;
					c.delayedDamage2 = Misc.random(c.getCombat()
							.calculateMeleeMaxHit());
				}
			}
			break;

		case 4153: // maul
			c.startAnimation(1667);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			/*
			 * if (c.playerIndex > 0) gmaulPlayer(i); else gmaulNpc(i);
			 */
			c.gfx100(337);
			break;

		case 4587: // dscimmy
			c.gfx100(347);
			c.specEffect = 1;
			c.startAnimation(1872);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			break;

		case 1434: // mace
			c.startAnimation(1060);
			c.gfx100(251);
			c.specMaxHitIncrease = 3;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase()) + 1;
			c.specDamage = 1.35;
			c.specAccuracy = 1.15;
			break;

		case 859: // magic long
			c.usingBow = true;
			c.bowSpecShot = 3;
			c.rangeItemUsed = c.playerEquipment[Player.playerArrows];
			c.getItems().deleteArrow();
			c.lastWeaponUsed = weapon;
			c.startAnimation(426);
			c.gfx100(250);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			c.projectileStage = 1;
			if (c.fightMode == 2)
				c.attackTimer--;
			break;
		case 14990: // magic long
			c.specAccuracy = 6.85;
			c.specDamage = 6.50;
			c.usingBow = true;
			c.bowSpecShot = 1;
			c.rangeItemUsed = c.playerEquipment[Player.playerArrows];
			c.getItems().deleteArrow();
			c.lastWeaponUsed = weapon;
			c.startAnimation(426);
			c.gfx100(250);
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			c.projectileStage = 1;
			if (c.fightMode == 2)
				c.attackTimer--;
			break;

		case 861: // magic short
			c.usingBow = true;
			c.bowSpecShot = 1;
			c.rangeItemUsed = c.playerEquipment[Player.playerArrows];
			c.getItems().deleteArrow();
			c.getItems().deleteArrow();
			c.lastWeaponUsed = weapon;
			c.startAnimation(1074);
			c.hitDelay = 3;
			c.projectileStage = 1;
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				c.getCombat().fireProjectilePlayer();
			else if (c.npcIndex > 0)
				c.getCombat().fireProjectileNpc();
			break;

		case 19146: // guthix bow
			c.rangeItemUsed = c.playerEquipment[Player.playerArrows];
			c.getItems().deleteArrow();
			c.getItems().deleteArrow();
			if (c.playerIndex > 0) {
				c.getItems().dropArrowPlayer();
			} else if (c.npcIndex > 0) {
				c.getItems().dropArrowNpc();
			}
			c.lastWeaponUsed = weapon;
			c.hitDelay = 3;
			c.startAnimation(426);
			c.projectileStage = 1;
			c.gfx100(c.getCombat().getRangeStartGFX());
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				c.getCombat().fireProjectilePlayer();
			else if (c.npcIndex > 0)
				c.getCombat().fireProjectileNpc();
			c.specAccuracy = 5.85;
			c.specDamage = 5.50;
			break;
		case 19143: // guthix bow
			c.rangeItemUsed = c.playerEquipment[Player.playerArrows];
			c.getItems().deleteArrow();
			c.getItems().deleteArrow();
			if (c.playerIndex > 0) {
				c.getItems().dropArrowPlayer();
			} else if (c.npcIndex > 0) {
				c.getItems().dropArrowNpc();
			}
			c.lastWeaponUsed = weapon;
			c.hitDelay = 3;
			c.startAnimation(426);
			c.projectileStage = 1;
			c.gfx100(c.getCombat().getRangeStartGFX());
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				c.getCombat().fireProjectilePlayer();
			else if (c.npcIndex > 0)
				c.getCombat().fireProjectileNpc();
			c.specAccuracy = 8.85;
			c.specDamage = 8.50;
			break;
		case 19149: // guthix bow
			c.rangeItemUsed = c.playerEquipment[Player.playerArrows];
			c.getItems().deleteArrow();
			c.getItems().deleteArrow();
			if (c.playerIndex > 0) {
				c.getItems().dropArrowPlayer();
			} else if (c.npcIndex > 0) {
				c.getItems().dropArrowNpc();
			}
			c.lastWeaponUsed = weapon;
			c.hitDelay = 3;
			c.startAnimation(426);
			c.projectileStage = 1;
			c.gfx100(c.getCombat().getRangeStartGFX());
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				c.getCombat().fireProjectilePlayer();
			else if (c.npcIndex > 0)
				c.getCombat().fireProjectileNpc();
			c.specAccuracy = 8.85;
			c.specDamage = 8.50;
			break;

		case 11235: // dark bow
			c.usingBow = true;
			c.dbowSpec = true;
			c.rangeItemUsed = c.playerEquipment[Player.playerArrows];
			c.getItems().deleteArrow();
			c.getItems().deleteArrow();
			if (c.playerIndex > 0) {
				c.getItems().dropArrowPlayer();
			} else if (c.npcIndex > 0) {
				c.getItems().dropArrowNpc();
			}
			c.lastWeaponUsed = weapon;
			c.hitDelay = 3;
			c.startAnimation(426);
			c.projectileStage = 1;
			c.gfx100(c.getCombat().getRangeStartGFX());
			c.hitDelay = c.getCombat().getHitDelay(
					i,
					ItemAssistant.getItemName(
							c.playerEquipment[Player.playerWeapon])
							.toLowerCase());
			if (c.fightMode == 2)
				c.attackTimer--;
			if (c.playerIndex > 0)
				c.getCombat().fireProjectilePlayer();
			else if (c.npcIndex > 0)
				c.getCombat().fireProjectileNpc();
			c.specAccuracy = 1.85;
			c.specDamage = 1.65;
			break;
		}
		c.delayedDamage = Misc.random(c.getCombat().calculateMeleeMaxHit());
		c.delayedDamage2 = Misc.random(c.getCombat().calculateMeleeMaxHit());
		c.usingSpecial = false;
		if (c.playerEquipment[Player.playerRing] == 19669)
			c.specAmount *= 1.1;
		c.getItems().updateSpecialBar();

	}

	public static boolean checkSpecAmount(Client c, int weapon) {
		switch (weapon) {
		case 1249:
		case 1215:
		case 1231:
		case 5680:
		case 5698:
		case 1305:
		case 1434:
			if (c.specAmount >= 2.5) {
				c.specAmount -= 2.5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 4151: // whip
		case 15441: // whip
		case 15442: // whip
		case 15443: // whip
		case 15444: // whip
		case 11694:
		case 11698:
		case 4153:
		case 14484:
		case 10887:
		case 19780:
		case 15241:
		case 13902:
		case 13904:
		case 13905:
		case 13907:
		case 13883:
		case 13879:
			if (c.specAmount >= 5) {
				c.specAmount -= 5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 13899:
		case 13901:
			if (c.specAmount >= 2.5) {
				c.specAmount -= 2.5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;
		case 3204:
			if (c.specAmount >= 3) {
				c.specAmount -= 3;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 1377:
		case 11696:
		case 11730:
			if (c.specAmount >= 10) {
				c.specAmount -= 10;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 20000:
		case 20001:
		case 20002:
		case 20003:
		case 14990:
			if (c.specAmount >= 5.0) {
				c.specAmount -= 5.0;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		case 4587:
		case 859:
		case 861:
		case 11235:
		case 11700:
		case 19146:
		case 19149:
		case 19143:
			if (c.specAmount >= 5.5) {
				c.specAmount -= 5.5;
				c.getItems().addSpecialBar(weapon);
				return true;
			}
			return false;

		default:
			return true; // incase u want to test a weapon
		}
	}
}