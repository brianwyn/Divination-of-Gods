package server.model.players.packets;

import server.Config;
import server.model.items.ItemAssistant;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.shops.ShopAssistant;

/**
 * Click NPC
 */
public class ClickNPC implements PacketType {
	public static final int ATTACK_NPC = 72, MAGE_NPC = 131, FIRST_CLICK = 155,
			SECOND_CLICK = 17, THIRD_CLICK = 21;

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		c.npcIndex = 0;
		c.npcClickIndex = 0;
		c.playerIndex = 0;
		c.clickNpcType = 0;
		c.getPA().resetFollow();
		switch (packetType) {

		/**
		 * Attack npc melee or range
		 **/
		case ATTACK_NPC:
			if (c.playerEquipment[Player.playerWeapon] == 19475) {
				c.usingMagic = true;
				c.stopMovement();
			}
			if (!c.mageAllowed) {
				c.mageAllowed = true;
				c.sendMessage("I can't reach that.");
				break;
			}
			c.npcIndex = c.getInStream().readUnsignedWordA();
			if (NPCHandler.npcs[c.npcIndex] == null) {
				c.npcIndex = 0;
				break;
			}
			if (NPCHandler.isHunterIMP(NPCHandler.npcs[c.npcIndex].npcType)) {

				return;
			}
			if (NPCHandler.npcs[c.npcIndex].npcType == 1777) {
				c.getShops().openShop(ShopAssistant.PK_POINT_SHOP);
				c.sendMessage("You have " + c.pkPoints + ". pking points");
				return;
			}
			if (NPCHandler.npcs[c.npcIndex].MaxHP == 0) {
				c.npcIndex = 0;
				break;
			}
			if (NPCHandler.npcs[c.npcIndex] == null) {
				c.sendMessage("Null");
				break;
			}
			if (c.autocastId > 0)
				c.autocasting = true;
			if (!c.autocasting && c.spellId > 0) {
				c.spellId = 0;
				c.getPA().resetAutocast();
				c.sendMessage("Autocasting reset!");
			}

			c.faceUpdate(c.npcIndex);
			c.usingMagic = false;
			boolean usingBow = false;
			boolean usingOtherRangeWeapons = false;
			boolean usingArrows = false;
			boolean usingCross = c.playerEquipment[Player.playerWeapon] == 9185
					|| c.playerEquipment[Player.playerWeapon] == 18357;
			if (c.playerEquipment[Player.playerWeapon] >= 4214
					&& c.playerEquipment[Player.playerWeapon] <= 4223)
				usingBow = true;
			for (int bowId : Player.BOWS) {
				if (c.playerEquipment[Player.playerWeapon] == bowId) {
					usingBow = true;
					for (int arrowId : Player.ARROWS) {
						if (c.playerEquipment[Player.playerArrows] == arrowId) {
							usingArrows = true;
						}
					}
				}
			}
			for (int otherRangeId : Player.OTHER_RANGE_WEAPONS) {
				if (c.playerEquipment[Player.playerWeapon] == otherRangeId) {
					usingOtherRangeWeapons = true;
				}
			}
			if ((usingBow || c.autocasting)
					&& c.goodDistance(c.getX(), c.getY(),
							NPCHandler.npcs[c.npcIndex].getX(),
							NPCHandler.npcs[c.npcIndex].getY(), 7)) {
				c.stopMovement();
			}

			if (usingOtherRangeWeapons
					&& c.goodDistance(c.getX(), c.getY(),
							NPCHandler.npcs[c.npcIndex].getX(),
							NPCHandler.npcs[c.npcIndex].getY(), 4)) {
				c.stopMovement();
			}
			if (!usingCross && c.playerEquipment[Player.playerWeapon] != 9185
					&& !usingArrows && usingBow
					&& c.playerEquipment[Player.playerWeapon] < 4212
					&& c.playerEquipment[Player.playerWeapon] > 4223
					&& !usingCross) {
				c.sendMessage("You have run out of arrows!");
				break;
			}
			if (c.getCombat().correctBowAndArrows() < c.playerEquipment[Player.playerArrows]
					&& Config.CORRECT_ARROWS
					&& usingBow
					&& !c.getCombat().usingCrystalBow() && !usingCross) {
				c.sendMessage("You can't use "
						+ ItemAssistant.getItemName(
								c.playerEquipment[Player.playerArrows])
								.toLowerCase()
						+ "s with a "
						+ ItemAssistant.getItemName(
								c.playerEquipment[Player.playerWeapon])
								.toLowerCase() + ".");
				c.stopMovement();
				c.getCombat().resetPlayerAttack();
				return;
			}

			if (c.followId > 0) {
				c.getPA().resetFollow();
			}
			if (c.attackTimer <= 0) {
				c.getCombat().attackNpc(c.npcIndex);
				c.attackTimer++;
			}

			break;

		/**
		 * Attack npc with magic
		 **/
		case MAGE_NPC:
			if (!c.mageAllowed) {
				c.mageAllowed = true;
				c.sendMessage("I can't reach that.");
				break;
			}
			// c.usingSpecial = false;
			// c.getItems().updateSpecialBar();

			c.npcIndex = c.getInStream().readSignedWordBigEndianA();
			int castingSpellId = c.getInStream().readSignedWordA();
			c.usingMagic = false;
			if (castingSpellId > 0)
				c.spellID = castingSpellId;

			if (NPCHandler.npcs[c.npcIndex] == null) {
				c.sendMessage("NPC NULLED");
				break;
			}
			// System.out.println(castingSpellId);

			if (NPCHandler.npcs[c.npcIndex].MaxHP == 0
					|| NPCHandler.npcs[c.npcIndex].npcType == 944) {
				c.sendMessage("You can't attack this npc.");
				break;
			}
			if (NPCHandler.isHunterIMP(NPCHandler.npcs[c.npcIndex].npcType)) {
				if (c.playerMagicBook != 0) {
					break;
				}
				if (castingSpellId != 1592 && castingSpellId != 1572
						&& castingSpellId != 1582) {
					break;
				}
				if (castingSpellId == 1592 || castingSpellId == 1572
						|| castingSpellId == 1582 && c.playerMagicBook == 0) {

					break;
				}
				break;
			}
			for (int i = 0; i < c.MAGIC_SPELLS.length; i++) {
				if (castingSpellId == c.MAGIC_SPELLS[i][0]) {
					c.spellId = i;
					c.usingMagic = true;
					break;
				}
			}

			if (castingSpellId == 1171) { // crumble undead
				for (int npc : Config.UNDEAD_NPCS) {
					if (NPCHandler.npcs[c.npcIndex].npcType != npc) {
						c.sendMessage("You can only attack undead monsters with this spell.");
						c.usingMagic = false;
						c.stopMovement();
						break;
					}
				}
			}
			/*
			 * if(!c.getCombat().checkMagicReqs(c.spellId)) { c.stopMovement();
			 * break; }
			 */

			if (c.autocasting)
				c.autocasting = false;
			c.getPA().resetAutocast();

			if (c.usingMagic) {
				if (c.goodDistance(c.getX(), c.getY(),
						NPCHandler.npcs[c.npcIndex].getX(),
						NPCHandler.npcs[c.npcIndex].getY(), 6)) {
					c.stopMovement();
				}
				if (c.attackTimer <= 0) {
					if (!NPCHandler
							.isHunterIMP(NPCHandler.npcs[c.npcIndex].npcType)) {

						c.getCombat().attackNpc(c.npcIndex);
						c.attackTimer++;
					}
				}
			}

			break;

		case FIRST_CLICK:
			c.npcClickIndex = c.inStream.readSignedWordBigEndian();

			if (c != null && NPCHandler.npcs[c.npcClickIndex] != null) {
				c.npcType = NPCHandler.npcs[c.npcClickIndex].npcType;

				if (c.goodDistance(NPCHandler.npcs[c.npcClickIndex].getX(),
						NPCHandler.npcs[c.npcClickIndex].getY(), c.getX(),
						c.getY(), 1)) {
					c.turnPlayerTo(NPCHandler.npcs[c.npcClickIndex].getX(),
							NPCHandler.npcs[c.npcClickIndex].getY());
					NPCHandler.npcs[c.npcClickIndex].facePlayer(c.playerId);

					if (NPCHandler.isHunterIMP(c.npcType)) {
						c.huntIMPX = NPCHandler.npcs[c.npcClickIndex].getX();
						c.huntIMPY = NPCHandler.npcs[c.npcClickIndex].getY();
					}
					c.getActions().firstClickNpc(c.npcType);
				} else {
					c.clickNpcType = 1;
				}
			}
			break;

		case SECOND_CLICK:
			c.npcClickIndex = c.inStream.readUnsignedWordBigEndianA();
			if (c != null && NPCHandler.npcs[c.npcClickIndex] != null) {
				c.npcType = NPCHandler.npcs[c.npcClickIndex].npcType;

				if (c.goodDistance(NPCHandler.npcs[c.npcClickIndex].getX(),
						NPCHandler.npcs[c.npcClickIndex].getY(), c.getX(),
						c.getY(), 1)) {
					c.turnPlayerTo(NPCHandler.npcs[c.npcClickIndex].getX(),
							NPCHandler.npcs[c.npcClickIndex].getY());
					NPCHandler.npcs[c.npcClickIndex].facePlayer(c.playerId);
					switch (c.npcType) {

					case 6807:
					case 6874:
					case 6868:
					case 6795:
					case 6816:
					case 6873:
						if (NPCHandler.npcs[c.npcClickIndex].npcId == c.summoningnpcid) {
							c.sendMessage("You are now storing items inside your npc");
							c.Summoning().store();
						} else {
							c.sendMessage("This is not your npc");
						}

						break;

					}
					c.getActions().secondClickNpc(c.npcType);
					// c.getActions().store(c.npcClickIndex, c.npcType);

				} else {
					c.clickNpcType = 2;
				}
			}
			break;

		case THIRD_CLICK:
			c.npcClickIndex = c.inStream.readSignedWord();
			if (c != null && NPCHandler.npcs[c.npcClickIndex] != null) {
				c.npcType = NPCHandler.npcs[c.npcClickIndex].npcType;

				if (c.goodDistance(NPCHandler.npcs[c.npcClickIndex].getX(),
						NPCHandler.npcs[c.npcClickIndex].getY(), c.getX(),
						c.getY(), 1)) {
					c.turnPlayerTo(NPCHandler.npcs[c.npcClickIndex].getX(),
							NPCHandler.npcs[c.npcClickIndex].getY());
					NPCHandler.npcs[c.npcClickIndex].facePlayer(c.playerId);
					c.getActions().thirdClickNpc(c.npcType);
				} else {
					c.clickNpcType = 3;
				}
			}
			break;
		}

	}
}
