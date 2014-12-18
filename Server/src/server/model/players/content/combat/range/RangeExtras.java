package server.model.players.content.combat.range;

import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.util.Misc;

public class RangeExtras {

	public static void appendMutliChinchompa(Client c, int npcId) {
		if (NPCHandler.npcs[npcId] != null) {
			NPC n = (NPC) NPCHandler.npcs[npcId];
			if (n.isDead) {
				return;
			}
			c.multiAttacking = true;
			int damage = Misc.random(c.getCombat().rangeMaxHit());
			if (NPCHandler.npcs[npcId].HP - damage < 0) {
				damage = NPCHandler.npcs[npcId].HP;
			}
			NPCHandler.npcs[npcId].underAttackBy = c.playerId;
			NPCHandler.npcs[npcId].underAttack = true;
			NPCHandler.npcs[npcId].handleHitMask(damage);
			// NPCHandler.npcs[npcId].dealDamage(damage);
		}
	}

	private static void createCombatGFX(Client c, int i, int gfx,
			boolean height100) {
		boolean npc = c.npcIndex > 0;
		Client p = null;
		if (i < PlayerHandler.players.length)
			p = (Client) PlayerHandler.players[i];
		if (npc) {
			if (i > NPCHandler.npcs.length)
				return;
			NPC n = (NPC) NPCHandler.npcs[i];
			if (height100) {
				if (n != null)
					n.gfx100(gfx);
			} else if (n != null)
				n.gfx0(gfx);

			return;
		}
		if (height100) {
			if (p != null)
				p.gfx100(gfx);
		} else if (p != null)
			p.gfx0(gfx);
	}

	public static void crossbowSpecial(Client c, int i, boolean npcAttack) {
		if (npcAttack) {
			if (i > NPCHandler.npcs.length)
				return;
		}

		NPC n = (NPC) NPCHandler.npcs[i];
		Client p = null;
		if (i < PlayerHandler.players.length && !npcAttack)
			p = (Client) PlayerHandler.players[i];

		c.crossbowDamage = 1.4;

		switch (c.lastArrowUsed) {
		case 9236: // Lucky Lightning
			createCombatGFX(c, i, 749, false);
			c.crossbowDamage = 1.25;
			break;
		case 9237: // Earth's Fury
			createCombatGFX(c, i, 755, false);
			break;
		case 9238: // Sea Curse
			createCombatGFX(c, i, 750, false);
			c.crossbowDamage = 1.10;
			break;
		case 9239: // Down to Earth
			createCombatGFX(c, i, 757, false);
			if (c.playerIndex > 0 && p != null) {
				p.playerLevel[6] -= 2;
				p.getPA().refreshSkill(6);
				p.sendMessage("Your magic has been lowered!");
			}
			break;
		case 9240: // Clear Mind
			createCombatGFX(c, i, 751, false);
			if (c.playerIndex > 0 && p != null) {
				p.playerLevel[5] -= 2;
				p.getPA().refreshSkill(5);
				p.sendMessage("Your prayer has been lowered!");
				c.playerLevel[5] += 2;
				if (c.playerLevel[5] >= c.getPA().getLevelForXP(c.playerXP[5])) {
					c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
				}
				c.getPA().refreshSkill(5);
			}
			break;
		case 9241: // Magical Posion
			createCombatGFX(c, i, 752, false);
			if (c.playerIndex > 0 && p != null) {
				p.getPA().appendPoison(6);
			}
			break;
		case 9242: // Blood Forfiet
			createCombatGFX(c, i, 754, false);

			if (c.playerLevel[3] - c.playerLevel[3] / 20 < 1) {
				break;
			}
			c.handleHitMask(c.playerLevel[3] / 20);
			c.dealDamage(c.playerLevel[3] / 20);
			if (c.npcIndex > 0 && n != null) {
				n.handleHitMask(n.HP / 10);
				// n.dealDamage(n.HP/10);
			} else if (c.playerIndex > 0 && p != null) {
				p.handleHitMask(c.playerLevel[3] / 10);
				p.dealDamage(c.playerLevel[3] / 10);
			}
			break;
		case 9243: // Armour Piercing
			createCombatGFX(c, i, 758, true);
			c.crossbowDamage = 1.15;
			c.ignoreDefence = true;
			break;
		case 9244: // Dragon's Breath
			createCombatGFX(c, i, 756, false);
			if (c.playerEquipment[Player.playerShield] != 1540
					|| c.playerEquipment[Player.playerShield] != 11283
					|| c.playerEquipment[Player.playerShield] != 11284) {
				c.crossbowDamage = 1.48;
			}
			break;
		case 9245: // Life Leech
			createCombatGFX(c, i, 753, false);
			c.crossbowDamage = 1.15;
			c.playerLevel[3] += c.boltDamage / 25;
			if (c.playerLevel[3] >= 112) {
				c.playerLevel[3] = 112;
			}
			c.getPA().refreshSkill(3);
			break;
		}
	}
}