package server.model.players.content.combat;

import server.Config;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.combat.range.RangeData;
import server.util.Misc;

public class AttackPlayer {

	public static void applyPlayerHit(final Client c, final int i) {
		c.stopPlayerSkill = false;
		if (!c.usingClaws) {
			c.getCombat().applyPlayerMeleeDamage(i, 1,
					Misc.random(c.getCombat().calculateMeleeMaxHit()));
			if (c.doubleHit) {
				if (!c.oldSpec) {
					c.getCombat().applyPlayerMeleeDamage(i, 2,
							Misc.random(c.getCombat().calculateMeleeMaxHit()));
				} else {
					CycleEventHandler.getSingleton().addEvent(c,
							new CycleEvent() {
								@Override
								public void execute(
										CycleEventContainer container) {
									c.getCombat().applyPlayerMeleeDamage(
											i,
											2,
											Misc.random(c.getCombat()
													.calculateMeleeMaxHit()));
									container.stop();
								}

								@Override
								public void stop() {

								}
							}, 1);
				}
			}
		} else {
			c.getPA().hitDragonClaws(c,
					Misc.random(c.getCombat().calculateMeleeMaxHit()));
		}
	}

	public static void applyPlayerMeleeDamage(Client c, int i, int damageMask,
			int damage) {
		c.previousDamage = damage;
		Client o = (Client) PlayerHandler.players[i];
		if (o == null) {
			return;
		}
		boolean veracsEffect = false;
		boolean guthansEffect = false;
		if (c.getPA().fullVeracs()) {
			if (Misc.random(4) == 1) {
				veracsEffect = true;
			}
		}
		if (c.getPA().fullGuthans()) {
			if (Misc.random(4) == 1) {
				guthansEffect = true;
			}
		}
		if (!c.usingClaws) {
			if (damageMask == 1) {
				damage = c.delayedDamage;
				c.delayedDamage = 0;
			} else {
				damage = c.delayedDamage2;
				c.delayedDamage2 = 0;
			}
		}
		if (Misc.random(o.getCombat().calculateMeleeDefence()) > Misc.random(c
				.getCombat().calculateMeleeAttack()) && !veracsEffect) {
			damage = 0;
			c.bonusAttack = 0;
		} else if (c.playerEquipment[Player.playerWeapon] == 5698
				&& o.poisonDamage <= 0 && Misc.random(3) == 1) {
			o.getPA().appendPoison(13);
			c.bonusAttack += damage / 3;
		} else {
			c.bonusAttack += damage / 3;
		}
		if (o.prayerActive[18]
				&& System.currentTimeMillis() - o.protMeleeDelay > 1500
				&& !veracsEffect) { // if prayer active reduce damage by 40%
			damage = (int) damage * 60 / 100;
		}
		if (c.maxNextHit && !c.usingClaws) {
			damage = c.getCombat().calculateMeleeMaxHit();
		}
		if (damage > 0 && guthansEffect) {
			c.playerLevel[3] += damage;
			if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
				c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			c.getPA().refreshSkill(3);
			o.gfx0(398);
		}
		if (c.ssSpec && damageMask == 2) {
			damage = 5 + Misc.random(11);
			c.ssSpec = false;
		}
		if (PlayerHandler.players[i].playerLevel[3] - damage < 0) {
			damage = PlayerHandler.players[i].playerLevel[3];
		}
		if (o != null) {
			if (o.curseActive[9] && damage > 0) {// Melee Deflect
				c.getCurse().appendDeflect(o.playerId, damage, 4);
			}
		}
		if (damage > 0) {
			if (c.playerEquipment[Player.playerWeapon] == 18349) {
				if (c.chaoticCharges[0] == 0 || c.chaoticCharges[0] < 0)
					c.getItems().degradeItem(18349, Player.playerWeapon, 1);

				if (c.chaoticCharges[0] > 0)
					c.chaoticCharges[0]--;
			} else if (c.playerEquipment[Player.playerWeapon] == 18351) {
				if (c.chaoticCharges[1] == 0 || c.chaoticCharges[1] < 0)
					c.getItems().degradeItem(18351, Player.playerWeapon, 1);

				if (c.chaoticCharges[1] > 0)
					c.chaoticCharges[1]--;
			} else if (c.playerEquipment[Player.playerWeapon] == 18353) {
				if (c.chaoticCharges[2] == 0 || c.chaoticCharges[2] < 0)
					c.getItems().degradeItem(18353, Player.playerWeapon, 1);

				if (c.chaoticCharges[2] > 0)
					c.chaoticCharges[2]--;

			}
		}
		if (o.vengOn && damage > 0)
			c.getCombat().appendVengeance(i, damage);
		if (damage > 0)
			c.getCombat().applyRecoil(damage, i);
		switch (c.specEffect) {
		case 1: // dragon scimmy special
			if (damage > 0) {
				if (o.prayerActive[16] || o.prayerActive[17]
						|| o.prayerActive[18] || o.curseActive[7]
						|| o.curseActive[8] || o.curseActive[9]) {
					o.headIcon = -1;
					o.getPA().sendFrame36(Player.PRAYER_GLOW[16], 0);
					o.getPA().sendFrame36(Player.PRAYER_GLOW[17], 0);
					o.getPA().sendFrame36(Player.PRAYER_GLOW[18], 0);
				}
				o.sendMessage("You have been injured!");
				o.stopPrayerDelay = System.currentTimeMillis();
				o.prayerActive[16] = false;
				o.prayerActive[17] = false;
				o.prayerActive[18] = false;
				o.getPA().requestUpdates();
			}
			break;
		case 2:
			if (damage > 0) {
				if (o.freezeTimer <= 0)
					o.freezeTimer = 30;
				o.gfx0(369);
				o.sendMessage("You have been frozen.");
				o.frozenBy = c.playerId;
				o.stopMovement();
				c.sendMessage("You freeze your enemy.");
			}
			break;
		case 3:
			if (damage > 0) {
				o.playerLevel[1] -= damage;
				o.sendMessage("You feel weak.");
				if (o.playerLevel[1] < 1)
					o.playerLevel[1] = 1;
				o.getPA().refreshSkill(1);
			}
			break;
		case 4:
			if (damage > 0) {
				if (c.playerLevel[3] + damage > c.getLevelForXP(c.playerXP[3]))
					if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
						;
					else
						c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
				else
					c.playerLevel[3] += damage;
				c.getPA().refreshSkill(3);
			}
			break;
		}
		c.specEffect = 0;
		if (c.fightMode == 3) { // check
			c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 0);
			c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 1);
			c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 2);
			c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 3);
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(1);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
		} else {
			c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE), c.fightMode);
			c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3), 3);
			c.getPA().refreshSkill(c.fightMode);
			c.getPA().refreshSkill(3);
		}
		PlayerHandler.players[i].logoutDelay = System.currentTimeMillis();
		PlayerHandler.players[i].underAttackBy = c.playerId;
		PlayerHandler.players[i].killerId = c.playerId;
		PlayerHandler.players[i].singleCombatDelay = System.currentTimeMillis();
		if (c.killedBy != PlayerHandler.players[i].playerId)
			c.totalPlayerDamageDealt = 0;
		c.killedBy = PlayerHandler.players[i].playerId;
		c.getCombat().applySmite(i, damage);
		switch (damageMask) {
		case 1:
			PlayerHandler.players[i].dealDamage(damage);
			PlayerHandler.players[i].damageTaken[c.playerId] += damage;
			c.totalPlayerDamageDealt += damage;
			PlayerHandler.players[i].updateRequired = true;
			o.getPA().refreshSkill(3);
			break;

		case 2:
			PlayerHandler.players[i].dealDamage(damage);
			PlayerHandler.players[i].damageTaken[c.playerId] += damage;
			c.totalPlayerDamageDealt += damage;
			PlayerHandler.players[i].updateRequired = true;
			c.doubleHit = false;
			o.getPA().refreshSkill(3);
			break;
		}

		if (c.korasi) {
			PlayerHandler.players[i].hitIcon = 2;
			c.korasi = false;
		} else {
			PlayerHandler.players[i].hitIcon = 0;
		}
		PlayerHandler.players[i].handleHitMask(damage);
		if (damage > 0)
			c.soulSplitHeal = damage / 6;

	}

	public static void attackPlayer(Client c, int i) {
		if (c.playerEquipment[Player.playerWeapon] == 20012
				&& c.playerLevel[6] > 80) {
			c.autocasting = true;
			c.spellId = 52;
			c.autocastId = 52;
			c.usingMagic = false;
			// startPPSDegrade();
		}
		if (PlayerHandler.players[i] != null) {
			if (c.trade11 > 0) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("You are still in starter-tutorial and can't be attacked/attack other players.");
				return;
			}
			if (PlayerHandler.players[i].trade11 > 0) {
				c.getCombat().resetPlayerAttack();
				c.sendMessage("That player is still in starter-tutorial and can't be attacked.");
				return;
			}
			if (PlayerHandler.players[i].isDead) {
				c.getCombat().resetPlayerAttack();
				return;
			}
			if (c.respawnTimer > 0 || PlayerHandler.players[i].respawnTimer > 0) {
				c.getCombat().resetPlayerAttack();
				return;
			}
			if (!c.getCombat().checkReqs()) {
				return;
			}
			boolean sameSpot = c.absX == PlayerHandler.players[i].getX()
					&& c.absY == PlayerHandler.players[i].getY();
			if (!c.goodDistance(PlayerHandler.players[i].getX(),
					PlayerHandler.players[i].getY(), c.getX(), c.getY(), 25)
					&& !sameSpot) {
				c.getCombat().resetPlayerAttack();
				return;
			}
			if (PlayerHandler.players[i].respawnTimer > 0) {
				PlayerHandler.players[i].playerIndex = 0;
				c.getCombat().resetPlayerAttack();
				return;
			}

			if (PlayerHandler.players[i].heightLevel != c.heightLevel) {
				c.getCombat().resetPlayerAttack();
				return;
			}
			if (c.playerEquipment[Player.playerRing] == 2570) {

				if (c.playerLevel[3] > 0
						&& c.playerLevel[3] <= c.getLevelForXP(c.playerXP[3]) / 10
						&& c.underAttackBy > 0) {
					int wildlvl = (((c.absY - 3520) / 8) + 1);
					if (wildlvl < 20) {
						c.sendMessage("Your ring of life is destroyed as it saves you.");
						Client o = (Client) PlayerHandler.players[i];
						if (o != null) {
							o.sendMessage("Your opponent has been saved by a ring of life.");
						}
						c.getItems().deleteEquipment(2570, Player.playerRing);
						c.getPA().startTeleport(2831, 2973, 0, "modern");
						c.getItems().wearItem(-1, 1, 12); // deletes the ring
						c.getCombat().resetPlayerAttack();

					}
				}
			}
			if (PlayerHandler.players[i] != null) {
				if (c.curseActive[19]) { // Turmoil
					c.getstr = PlayerHandler.players[i].playerLevel[2] * 10 / 100;
					c.getdef = PlayerHandler.players[i].playerLevel[1] * 15 / 100;
					c.getatt = PlayerHandler.players[i].playerLevel[0] * 15 / 100;

				}
				if (c.curseActive[10]) { // Leech Attack
					c.getatt = PlayerHandler.players[i].playerLevel[0] * 10 / 100;
				}
				if (c.curseActive[13]) { // Leech Defense
					c.getdef = PlayerHandler.players[i].playerLevel[1] * 10 / 100;
				}
				if (c.curseActive[14]) { // Leech Strength
					c.getstr = PlayerHandler.players[i].playerLevel[2] * 10 / 100;
				}
				if (c.curseActive[11]) { // Leech Ranged
					c.getatt = PlayerHandler.players[i].playerLevel[4] * 10 / 100;
				}
				if (c.curseActive[12]) { // Leech Magic
					c.getatt = PlayerHandler.players[i].playerLevel[6] * 10 / 100;
				}
				if (c.curseActive[2]) { // Sap Attack
					c.getatt = PlayerHandler.players[i].playerLevel[0] * 5 / 100;
				}
				if (c.curseActive[3]) { // Sap Defense
					c.getdef = PlayerHandler.players[i].playerLevel[1] * 5 / 100;
				}
				if (c.curseActive[4]) { // Sap Strength
					c.getstr = PlayerHandler.players[i].playerLevel[2] * 5 / 100;
				}
			}
			c.followId = i;
			c.followId2 = 0;
			if (c.attackTimer <= 0) {
				c.usingBow = false;
				c.specEffect = 0;
				c.usingRangeWeapon = false;
				c.rangeItemUsed = 0;
				c.usingBow = false;
				c.usingArrows = false;
				c.usingOtherRangeWeapons = false;
				c.usingCross = c.playerEquipment[Player.playerWeapon] == 9185
						|| c.playerEquipment[Player.playerWeapon] == 18357;
				c.projectileStage = 0;

				if (c.absX == PlayerHandler.players[i].absX
						&& c.absY == PlayerHandler.players[i].absY) {
					if (c.freezeTimer > 0) {
						c.getCombat().resetPlayerAttack();
						return;
					}
					c.followId = i;
					c.attackTimer = 0;
					return;
				}
				// c.sendMessage("Made it here1.");
				if (!c.usingMagic) {
					for (int bowId : Player.BOWS) {
						if (c.playerEquipment[Player.playerWeapon] == bowId) {
							c.usingBow = true;
							for (int arrowId : Player.ARROWS) {
								if (c.playerEquipment[Player.playerArrows] == arrowId) {
									c.usingArrows = true;
								}
							}
						}
					}

					for (int otherRangeId : Player.OTHER_RANGE_WEAPONS) {
						if (c.playerEquipment[Player.playerWeapon] == otherRangeId) {
							c.usingOtherRangeWeapons = true;
						}
					}
				}
				if (c.autocasting) {
					c.spellId = c.autocastId;
					c.usingMagic = true;
				}
				if (c.spellId > 0) {
					c.usingMagic = true;
				}
				c.attackTimer = c.getCombat().getAttackDelay(
						c.getItems()
								.getItemName(
										c.playerEquipment[Player.playerWeapon])
								.toLowerCase());

				if ((!c.goodDistance(c.getX(), c.getY(),
						PlayerHandler.players[i].getX(),
						PlayerHandler.players[i].getY(), 4) && (c.usingOtherRangeWeapons
						&& !c.usingBow && !c.usingMagic))
						|| (!c.goodDistance(c.getX(), c.getY(),
								PlayerHandler.players[i].getX(),
								PlayerHandler.players[i].getY(), 2) && (!c.usingOtherRangeWeapons
								&& c.getCombat().usingHally() && !c.usingBow && !c.usingMagic))
						|| (!c.goodDistance(c.getX(), c.getY(),
								PlayerHandler.players[i].getX(),
								PlayerHandler.players[i].getY(), c.getCombat()
										.getRequiredDistance()) && (!c.usingOtherRangeWeapons
								&& !c.getCombat().usingHally() && !c.usingBow && !c.usingMagic))
						|| (!c.goodDistance(c.getX(), c.getY(),
								PlayerHandler.players[i].getX(),
								PlayerHandler.players[i].getY(), 10) && (c.usingBow || c.usingMagic))) {
					c.attackTimer = 1;
					if (!c.usingBow && !c.usingMagic
							&& !c.usingOtherRangeWeapons && c.freezeTimer > 0)
						c.getCombat().resetPlayerAttack();
					return;
				}

				if (c.playerEquipment[Player.playerWeapon] == 15241
						&& c.playerEquipment[Player.playerArrows] != 15243) {
					c.sendMessage("You have run out of Hand cannon ammo.");
					c.stopMovement();
					c.npcIndex = 0;
					return;
				}
				if (c.usingCross && !c.usingMagic) {
					if (!RangeData.correctEquipment(c)) {
						c.stopMovement();
						c.getCombat().resetPlayerAttack();
						return;

					}
				}

				if (c.usingBow || c.usingMagic || c.usingOtherRangeWeapons
						|| c.getCombat().usingHally()) {
					c.stopMovement();
				}

				if (!c.getCombat().checkMagicReqs(c.spellId)) {
					c.stopMovement();
					c.getCombat().resetPlayerAttack();
					return;
				}

				c.faceUpdate(i + 32768);
				if (!c.attackedPlayers.contains(c.playerIndex)
						&& !PlayerHandler.players[c.playerIndex].attackedPlayers
								.contains(c.playerId)) {
					c.attackedPlayers.add(c.playerIndex);
					c.isSkulled = true;
					c.skullTimer = Config.SKULL_TIMER;
					c.headIconPk = 0;
					c.getPA().requestUpdates();
				}

				c.specAccuracy = 1.0;
				c.specDamage = 1.0;
				c.delayedDamage = c.delayedDamage2 = 0;
				if (c.usingSpecial && !c.usingMagic) {
					if (c.getCombat().checkSpecAmount(
							c.playerEquipment[Player.playerWeapon])) {
						c.lastArrowUsed = c.playerEquipment[Player.playerArrows];
						c.getCombat().activateSpecial(
								c.playerEquipment[Player.playerWeapon], i);
						c.followId = c.playerIndex;
						return;
					} else {
						c.sendMessage("You don't have the required special energy to use this attack.");
						c.usingSpecial = false;
						c.getItems().updateSpecialBar();
						c.playerIndex = 0;
						return;
					}
				}
				if (c.playerLevel[3] > 0 && !c.isDead
						&& PlayerHandler.players[i].playerLevel[3] > 0) {
					if (!c.usingMagic) {
						c.startAnimation(c
								.getCombat()
								.getWepAnim(
										c.getItems()
												.getItemName(
														c.playerEquipment[Player.playerWeapon])
												.toLowerCase()));
						c.mageFollow = false;
					} else {
						c.startAnimation(c.MAGIC_SPELLS[c.spellId][2]);
						c.mageFollow = true;
						c.followId = c.playerIndex;
					}
				}
				PlayerHandler.players[i].underAttackBy = c.playerId;
				PlayerHandler.players[i].logoutDelay = System
						.currentTimeMillis();
				PlayerHandler.players[i].singleCombatDelay = System
						.currentTimeMillis();
				PlayerHandler.players[i].killerId = c.playerId;
				c.lastArrowUsed = 0;
				c.rangeItemUsed = 0;
				if (!c.usingBow && !c.usingMagic && !c.usingOtherRangeWeapons) { // melee
																					// hit
																					// delay
					c.followId = PlayerHandler.players[c.playerIndex].playerId;
					c.getPA().followPlayer();
					c.hitDelay = c
							.getCombat()
							.getHitDelay(
									i,
									c.getItems()
											.getItemName(
													c.playerEquipment[Player.playerWeapon])
											.toLowerCase());
					c.delayedDamage = Misc.random(c.getCombat()
							.calculateMeleeMaxHit());
					c.projectileStage = 0;
					c.oldPlayerIndex = i;
				}

				if (c.usingBow && !c.usingOtherRangeWeapons && !c.usingMagic
						|| c.usingCross) { // range hit delay
					if (c.playerEquipment[Player.playerWeapon] >= 4212
							&& c.playerEquipment[Player.playerWeapon] <= 4223) {
						c.rangeItemUsed = c.playerEquipment[Player.playerWeapon];
						c.crystalBowArrowCount++;
					} else {
						c.rangeItemUsed = c.playerEquipment[Player.playerArrows];
						c.getItems().deleteArrow();
					}
					if (c.fightMode == 2)
						c.attackTimer--;
					if (c.usingCross)
						c.usingBow = true;
					c.usingBow = true;
					c.followId = PlayerHandler.players[c.playerIndex].playerId;
					c.getPA().followPlayer();
					c.lastWeaponUsed = c.playerEquipment[Player.playerWeapon];
					c.lastArrowUsed = c.playerEquipment[Player.playerArrows];
					c.gfx100(c.getCombat().getRangeStartGFX());
					c.hitDelay = c
							.getCombat()
							.getHitDelay(
									i,
									c.getItems()
											.getItemName(
													c.playerEquipment[Player.playerWeapon])
											.toLowerCase());
					c.projectileStage = 1;
					c.oldPlayerIndex = i;
					c.getCombat().fireProjectilePlayer();
				}

				if (c.usingOtherRangeWeapons) { // knives, darts, etc hit delay
					c.rangeItemUsed = c.playerEquipment[Player.playerWeapon];
					c.getItems().deleteEquipment();
					c.usingRangeWeapon = true;
					c.followId = PlayerHandler.players[c.playerIndex].playerId;
					c.getPA().followPlayer();
					c.gfx100(c.getCombat().getRangeStartGFX());
					if (c.fightMode == 2)
						c.attackTimer--;
					c.hitDelay = c
							.getCombat()
							.getHitDelay(
									i,
									c.getItems()
											.getItemName(
													c.playerEquipment[Player.playerWeapon])
											.toLowerCase());
					c.projectileStage = 1;
					c.oldPlayerIndex = i;
					c.getCombat().fireProjectilePlayer();
				}

				if (c.usingMagic) { // magic hit delay
					int pX = c.getX();
					int pY = c.getY();
					int nX = PlayerHandler.players[i].getX();
					int nY = PlayerHandler.players[i].getY();
					int offX = (pY - nY) * -1;
					int offY = (pX - nX) * -1;
					c.castingMagic = true;
					c.npcFDelay = 5;
					c.projectileStage = 2;
					if (c.MAGIC_SPELLS[c.spellId][3] > 0) {
						if (c.getCombat().getStartGfxHeight() == 100) {
							c.gfx100(c.MAGIC_SPELLS[c.spellId][3]);
						} else {
							c.gfx0(c.MAGIC_SPELLS[c.spellId][3]);
						}
					}
					if (c.MAGIC_SPELLS[c.spellId][4] > 0) {
						c.getPA().createPlayersProjectile(pX, pY, offX, offY,
								50, 78, c.MAGIC_SPELLS[c.spellId][4],
								c.getCombat().getStartHeight(),
								c.getCombat().getEndHeight(), -i - 1,
								c.getCombat().getStartDelay());
					}
					if (c.autocastId > 0) {
						c.followId = c.playerIndex;
						c.followDistance = 5;
					}
					c.hitDelay = c
							.getCombat()
							.getHitDelay(
									i,
									c.getItems()
											.getItemName(
													c.playerEquipment[Player.playerWeapon])
											.toLowerCase());
					c.oldPlayerIndex = i;
					c.oldSpellId = c.spellId;
					c.spellId = 0;
					Client o = (Client) PlayerHandler.players[i];
					if (c.MAGIC_SPELLS[c.oldSpellId][0] == 12891 && o.isMoving) {
						// c.sendMessage("Barrage projectile..");
						c.getPA().createPlayersProjectile(pX, pY, offX, offY,
								50, 85, 368, 25, 25, -i - 1,
								c.getCombat().getStartDelay());
					}
					if (Misc.random(o.getCombat().mageDef()) > Misc.random(c
							.getCombat().mageAtk())) {
						// if(Misc.random(o.getCombat().mageAtk()) >
						// Misc.random(o.getCombat().mageDef())) {
						c.magicFailed = true;
					} else {
						c.magicFailed = false;
					}

					// if(Misc.random(o.getCombat().mageAtk()) >
					// Misc.random(o.getCombat().mageDef())) {
					// c.magicFailed = false;
					// } else if(Misc.random(o.getCombat().mageAtk()) <
					// Misc.random(o.getCombat().mageDef())) {
					// c.magicFailed = true;
					// }

					int freezeDelay = c.getCombat().getFreezeTime();// freeze
																	// time
					if (freezeDelay > 0
							&& PlayerHandler.players[i].freezeTimer <= -3
							&& !c.magicFailed) {
						PlayerHandler.players[i].freezeTimer = freezeDelay;
						o.resetWalkingQueue();
						o.sendMessage("You have been frozen.");
						o.frozenBy = c.playerId;
					}
					if (!c.autocasting && c.spellId <= 0)
						c.playerIndex = 0;
				}

				if (c.curseActive[10]) { // Leech Attack
					int failed = Misc.random(5);
					Client c2 = (Client) PlayerHandler.players[i];
					if (c2 != null) {
						if (failed < 5) {
							c.Lattack = false;
							return;
						} else if (failed == 5) {
							c2.playerLevel[0] -= Misc.random(8);
							if (c2.playerLevel[0] < 1)
								c2.playerLevel[0] = 1;
							c2.getPA().refreshSkill(0);
						}
						if (c.oldPlayerIndex > 0) {
							if (PlayerHandler.players[c.oldPlayerIndex] != null) {
								final int pX = c.getX();
								final int pY = c.getY();
								final int nX = PlayerHandler.players[i].getX();
								final int nY = PlayerHandler.players[i].getY();
								final int offX = (pY - nY) * -1;
								final int offY = (pX - nX) * -1;
								c.getPA().createPlayersProjectile2(pX, pY,
										offX, offY, 50, 50, 2252, 9, 9,
										-c.oldPlayerIndex - 1, 24, 0);
								c2.sendMessage(""
										+ Misc.optimizeText(c.playerName)
										+ " has leeched your Attack!");
								c2.gfx0(2253);
								c.startAnimation(12575);
								c.Lattack = false;
							}
						}
					}
				}

				if (c.curseActive[13]) { // Leech Defence
					int failed = Misc.random(5);
					Client c2 = (Client) PlayerHandler.players[i];
					if (c2 != null) {
						if (failed < 5) {
							c.Ldefense = false;
							return;
						} else if (failed == 5) {
							c2.playerLevel[1] -= Misc.random(8);
							if (c2.playerLevel[1] < 1)
								c2.playerLevel[1] = 1;
							c2.getPA().refreshSkill(1);
						}
						if (c.oldPlayerIndex > 0) {
							if (PlayerHandler.players[c.oldPlayerIndex] != null) {
								final int pX = c.getX();
								final int pY = c.getY();
								final int nX = PlayerHandler.players[i].getX();
								final int nY = PlayerHandler.players[i].getY();
								final int offX = (pY - nY) * -1;
								final int offY = (pX - nX) * -1;
								c.getPA().createPlayersProjectile2(pX, pY,
										offX, offY, 50, 50, 2242, 9, 9,
										-c.oldPlayerIndex - 1, 24, 0);
								c2.sendMessage(""
										+ Misc.optimizeText(c.playerName)
										+ " has leeched your Defence!");
								c2.gfx0(2246);
								c.startAnimation(12575);
								c.Ldefense = false;
							}
						}
					}
				}

				if (c.curseActive[14]) { // Leech Strength
					int failed = Misc.random(5);
					Client c2 = (Client) PlayerHandler.players[i];
					if (c2 != null) {
						if (failed < 5) {
							c.Lstrength = false;
							return;
						} else if (failed == 5) {
							c2.playerLevel[2] -= Misc.random(8);
							if (c2.playerLevel[2] < 1)
								c2.playerLevel[2] = 1;
							c2.getPA().refreshSkill(2);
						}
						if (c.oldPlayerIndex > 0) {
							if (PlayerHandler.players[c.oldPlayerIndex] != null) {
								final int pX = c.getX();
								final int pY = c.getY();
								final int nX = PlayerHandler.players[i].getX();
								final int nY = PlayerHandler.players[i].getY();
								final int offX = (pY - nY) * -1;
								final int offY = (pX - nX) * -1;
								c.getPA().createPlayersProjectile2(pX, pY,
										offX, offY, 50, 50, 2248, 9, 9,
										-c.oldPlayerIndex - 1, 24, 0);
								c2.sendMessage(""
										+ Misc.optimizeText(c.playerName)
										+ " has leeched your Strength!");
								c2.gfx0(2250);
								c.startAnimation(12575);
								c.Lstrength = false;
							}
						}
					}
				}

				if (c.curseActive[11]) { // Leech Ranged
					int failed = Misc.random(5);
					Client c2 = (Client) PlayerHandler.players[i];
					if (c2 != null) {
						if (failed < 5) {
							c.Lranged = false;
							return;
						} else if (failed == 5) {
							c2.playerLevel[4] -= Misc.random(8);
							if (c2.playerLevel[4] < 1)
								c2.playerLevel[4] = 1;
							c2.getPA().refreshSkill(4);
						}
						if (c.oldPlayerIndex > 0) {
							if (PlayerHandler.players[c.oldPlayerIndex] != null) {
								final int pX = c.getX();
								final int pY = c.getY();
								final int nX = PlayerHandler.players[i].getX();
								final int nY = PlayerHandler.players[i].getY();
								final int offX = (pY - nY) * -1;
								final int offY = (pX - nX) * -1;
								c.getPA().createPlayersProjectile2(pX, pY,
										offX, offY, 50, 50, 2236, 9, 9,
										-c.oldPlayerIndex - 1, 24, 0);
								c2.sendMessage(""
										+ Misc.optimizeText(c.playerName)
										+ " has leeched your Ranged!");
								c2.gfx0(2238);
								c.startAnimation(12575);
								c.Lranged = false;
							}
						}
					}
				}

				if (c.curseActive[12]) { // Leech Magic
					int failed = Misc.random(5);
					Client c2 = (Client) PlayerHandler.players[i];
					if (c2 != null) {
						if (failed < 5) {
							c.Lmagic = false;
							return;
						} else if (failed == 5) {
							c2.playerLevel[6] -= Misc.random(7) + 1;
							if (c2.playerLevel[6] < 1)
								c2.playerLevel[6] = 1;
							c2.getPA().refreshSkill(6);
						}
						if (c.oldPlayerIndex > 0) {
							if (PlayerHandler.players[c.oldPlayerIndex] != null) {
								final int pX = c.getX();
								final int pY = c.getY();
								final int nX = PlayerHandler.players[i].getX();
								final int nY = PlayerHandler.players[i].getY();
								final int offX = (pY - nY) * -1;
								final int offY = (pX - nX) * -1;
								c.getPA().createPlayersProjectile2(pX, pY,
										offX, offY, 50, 50, 2240, 9, 9,
										-c.oldPlayerIndex - 1, 24, 0);
								c2.sendMessage(""
										+ Misc.optimizeText(c.playerName)
										+ " has leeched your Magic!");
								c2.gfx0(2242);
								c.startAnimation(12575);
								c.Lmagic = false;
							}
						}
					}
				}

				if (c.curseActive[16]) { // Leech Special
					int failed = Misc.random(10);
					Client c2 = (Client) PlayerHandler.players[i];
					if (c2 != null) {
						if (failed < 10) {
							c.Lspecial = false;
							return;
						} else if (failed == 10) {
							c2.specAmount -= 0.5;
							c.specAmount += 0.5;
							final int pX = c.getX();
							final int pY = c.getY();
							final int nX = PlayerHandler.players[i].getX();
							final int nY = PlayerHandler.players[i].getY();
							final int offX = (pY - nY) * -1;
							final int offY = (pX - nX) * -1;
							c.getPA().createPlayersProjectile2(pX, pY, offX,
									offY, 50, 50, 2256, 9, 9,
									-c.oldPlayerIndex - 1, 24, 0);
							c2.sendMessage("" + Misc.optimizeText(c.playerName)
									+ " has leeched your Special Attack!");
							c2.gfx0(2258);
							c.startAnimation(12575);
							c.getItems().updateSpecialBar();
							c.sendMessage("You have leeched "
									+ Misc.optimizeText(c2.playerName)
									+ "'s special attack!");
							// c2.specAmount -= Misc.random(8);
							if (c2.specAmount < 1)
								c2.specAmount = 1;
						}
						if (c.oldPlayerIndex > 0) {
							if (PlayerHandler.players[c.oldPlayerIndex] != null) {
								final int pX = c.getX();
								final int pY = c.getY();
								final int nX = PlayerHandler.players[i].getX();
								final int nY = PlayerHandler.players[i].getY();
								final int offX = (pY - nY) * -1;
								final int offY = (pX - nX) * -1;
								c.getPA().createPlayersProjectile2(pX, pY,
										offX, offY, 50, 50, 2256, 9, 9,
										-c.oldPlayerIndex - 1, 24, 0);
								// c2.sendMessage("Your special attack has been leeched by "
								// +Misc.optimizeText(c.playerName)+"!");
								c2.gfx0(2258);
								c.startAnimation(12575);
								c.getItems().updateSpecialBar();
								c.Lspecial = false;
							}
						}
					}
				}

				if (c.curseActive[18]) { // SoulSplit GFX's - CAUSES CRASH
					if (c.oldPlayerIndex > 0) {
						if (PlayerHandler.players[c.oldPlayerIndex] != null) {
							// try {
							final int pX = c.getX();
							final int pY = c.getY();
							final int nX = PlayerHandler.players[i].getX();
							final int nY = PlayerHandler.players[i].getY();
							final int offX = (pY - nY) * -1;
							final int offY = (pX - nX) * -1;
							c.SSPLIT = true;
							c.getPA().createPlayersProjectile2(pX, pY, offX,
									offY, 50, 50, 2263, 9, 9,
									-c.oldPlayerIndex - 1, 24, 0);

							// EventManager.getSingleton().addEvent(new
							// Event() {
							// public void execute(EventContainer b) {
							// Server.playerHandler.players[c.oldPlayerIndex].gfx0(2264);
							// // 1738
							c.SSPLIT = false;
							if (c.curseActive[18] && !c.prayerActive[23]
									&& c.playerLevel[3] <= 99
									&& c.soulSplitHeal > 0) {
								c.sendMessage("" + c.soulSplitHeal + "");
								if (c.playerLevel[3] + c.soulSplitHeal >= c
										.getPA().getLevelForXP(c.playerXP[3])) {
									c.playerLevel[3] = c.getPA().getLevelForXP(
											c.playerXP[3]);
								} else {
									c.playerLevel[3] += c.soulSplitHeal;
								}
								c.getPA().refreshSkill(3);
								c.soulSplitHeal = 0;
							}
							Client c3 = (Client) PlayerHandler.players[i];
							if (c3 != null) {
								c3.gfx0(2264);
							}
							// b.stop();
							// }
							// }, 500);
							/*
							 * EventManager.getSingleton().addEvent(new Event()
							 * { // CAUSES CRASH public void
							 * execute(EventContainer b) {
							 * //c.getPA().createPlayersProjectile2(nX, nY,
							 * offX, offY, 50, 50, 2263, 9, 9, - c.playerId - 1,
							 * 24, 0); b.stop(); } }, 800);
							 */
							// } catch (Exception e) {
							// e.printStackTrace();
							// }
						}
					}
				}
			}
		}
	}

	public static void playerDelayedHit(final Client c, final int i) {
		if (PlayerHandler.players[i] != null) {
			if (PlayerHandler.players[i].isDead || c.isDead
					|| PlayerHandler.players[i].playerLevel[3] <= 0
					|| c.playerLevel[3] <= 0) {
				c.playerIndex = 0;
				return;
			}
			if (PlayerHandler.players[i].respawnTimer > 0) {
				c.faceUpdate(0);
				c.playerIndex = 0;
				return;
			}
			Client o = (Client) PlayerHandler.players[i];
			o.getPA().removeAllWindows();
			if (o.playerIndex <= 0 && o.npcIndex <= 0) {
				if (o.autoRet == 1) {
					o.playerIndex = c.playerId;
				}
			}

			if (o.attackTimer <= 3 || o.attackTimer == 0 && o.playerIndex == 0
					&& !c.castingMagic) { // block animation
				o.startAnimation(o.getCombat().getBlockEmote());
			}
			if (o.inTrade) {
				o.getTradeAndDuel().declineTrade();
			}
			if (c.projectileStage == 0 && !c.usingMagic) { // melee hit damage
				c.getCombat().applyPlayerHit(c, i);
			}
			c.getCombat().addCharge();
			if (!c.castingMagic && c.projectileStage > 0) { // range hit damage
				int damage = Misc.random(c.getCombat().rangeMaxHit());
				int damage2 = -1;
				if (c.lastWeaponUsed == 11235 && c.lastWeaponUsed == 19146
						&& c.lastWeaponUsed == 19143
						&& c.lastWeaponUsed == 19149
						&& c.lastWeaponUsed == 14990 || c.bowSpecShot == 1) {
					damage2 = Misc.random(c.getCombat().rangeMaxHit());
				}
				if (c.lastWeaponUsed == 19146 || c.bowSpecShot == 1) {
					damage2 = Misc.random(c.getCombat().rangeMaxHit());
				}
				if (c.lastWeaponUsed == 14990 || c.bowSpecShot == 1) {
					damage2 = Misc.random(c.getCombat().rangeMaxHit());
				}
				c.rangeEndGFX = RangeData.getRangeEndGFX(c);

				if (Misc.random(10 + o.getCombat().calculateRangeDefence()) > Misc
						.random(10 + c.getCombat().calculateRangeAttack())
						&& !c.ignoreDefence) {
					damage = 0;
				}
				if (c.playerEquipment[3] == 9185) {
					if (Misc.random(10) == 1) {
						if (damage > 0) {
							c.boltDamage = damage;
							c.getCombat().crossbowSpecial(c, i, false);
							damage *= c.crossbowDamage;
						}
					}
				}
				if (c.lastWeaponUsed == 11235 && c.lastWeaponUsed == 19146
						&& c.lastWeaponUsed == 19143
						&& c.lastWeaponUsed == 19149
						&& c.lastWeaponUsed == 14990 || c.bowSpecShot == 1) {
					if (Misc.random(10 + o.getCombat().calculateRangeDefence()) > Misc
							.random(10 + c.getCombat().calculateRangeAttack()))
						damage2 = 0;
				}
				if (c.lastWeaponUsed == 1 || c.bowSpecShot == 1) {
					if (Misc.random(10 + o.getCombat().calculateRangeDefence()) > Misc
							.random(10 + c.getCombat().calculateRangeAttack()))
						damage2 = 0;
				}

				if (c.dbowSpec) {
					o.gfx100(c.lastArrowUsed == 11212 ? 1100 : 1103);
					if (damage < 8)
						damage = 8;
					if (damage2 < 8)
						damage2 = 8;
					c.dbowSpec = false;
				}
				if (PlayerHandler.players[i].playerLevel[3] - damage < 0) {
					damage = PlayerHandler.players[i].playerLevel[3];
				}
				if (PlayerHandler.players[i].playerLevel[3] - damage - damage2 < 0) {
					damage2 = PlayerHandler.players[i].playerLevel[3] - damage;
				}
				if (damage < 0)
					damage = 0;
				if (damage2 < 0 && damage2 != -1)
					damage2 = 0;
				if (o.vengOn) {
					c.getCombat().appendVengeance(i, damage);
					c.getCombat().appendVengeance(i, damage2);
				}
				if (damage > 0)
					c.getCombat().applyRecoil(damage, i);
				if (damage2 > 0)
					c.getCombat().applyRecoil(damage2, i);
				if (c.fightMode == 3) {
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE / 3),
							4);
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE / 3),
							1);
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE / 3),
							3);
					c.getPA().refreshSkill(1);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(4);
				} else {
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE), 4);
					c.getPA().addSkillXP((damage * Config.RANGE_EXP_RATE / 3),
							3);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(4);
				}
				boolean dropArrows = true;

				for (int noArrowId : Player.NO_ARROW_DROP) {
					if (c.lastWeaponUsed == noArrowId) {
						dropArrows = false;
						break;
					}
				}
				if (dropArrows) {
					c.getItems().dropArrowPlayer();
				}
				if (c.rangeEndGFX > 0
						&& !c.getCombat().usingBolts(c.lastArrowUsed)) {
					if (c.rangeEndGFXHeight) {
						o.gfx100(c.rangeEndGFX);
					} else {
						o.gfx0(c.rangeEndGFX);
					}
				}
				if (PlayerHandler.players[i] != null) {
					if (PlayerHandler.players[i].curseActive[8] && damage > 0) {// Range
																				// Deflect
						c.getCurse().appendDeflect(
								PlayerHandler.players[i].playerId, damage, 4);
					}
				}
				PlayerHandler.players[i].hitIcon = 1;
				PlayerHandler.players[i].underAttackBy = c.playerId;
				PlayerHandler.players[i].logoutDelay = System
						.currentTimeMillis();
				PlayerHandler.players[i].singleCombatDelay = System
						.currentTimeMillis();
				PlayerHandler.players[i].killerId = c.playerId;
				PlayerHandler.players[i].dealDamage(damage);
				PlayerHandler.players[i].damageTaken[c.playerId] += damage;
				c.killedBy = PlayerHandler.players[i].playerId;
				PlayerHandler.players[i].handleHitMask(damage,
						PlayerHandler.players[i].playerId);
				c.ignoreDefence = false;
				if (damage2 != -1) {
					PlayerHandler.players[i].dealDamage(damage2);
					PlayerHandler.players[i].damageTaken[c.playerId] += damage2;
					PlayerHandler.players[i].handleHitMask(damage2);
				}
				o.getPA().refreshSkill(3);
				PlayerHandler.players[i].updateRequired = true;
				c.getCombat().applySmite(i, damage);
				if (damage2 != -1)
					c.getCombat().applySmite(i, damage2);

			} else if (c.projectileStage > 0) { // magic hit damageno0b
				int damage = 0;
				if (c.spellSwap) {
					c.spellSwap = false;
					c.setSidebarInterface(6, 16640);
					c.playerMagicBook = 2;
					c.gfx0(-1);
				}
				if (c.fullVoidMage()
						&& c.playerEquipment[Player.playerWeapon] == 8841) {
					damage = Misc.random(c.getCombat().magicMaxHit() + 13);
				} else {
					damage = Misc.random(c.getCombat().magicMaxHit());
				}
				if (c.getCombat().godSpells()) {
					if (System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
						damage += 10;
					}
				}
				if (c.magicFailed)
					damage = 0;

				if (o.prayerActive[16] || o.curseActive[7]
						&& System.currentTimeMillis() - o.protMageDelay > 1500) {// if
																					// prayer
																					// active
																					// reduce
																					// damage
																					// by
																					// half
					damage = (int) damage * 60 / 100;
				}
				if (o.playerEquipment[Player.playerShield] == 13742
						&& !o.prayerActive[16] || !o.curseActive[7]
						&& damage >= 1) {
					if (Misc.random(4) == 3) {
						damage = (int) damage * 65 / 100;
					}
				}

				if (o.playerEquipment[Player.playerWeapon] == 15486
						&& damage >= 1 && o.SolProtect >= 1) {
					damage = (int) damage / 2;
				}
				if (PlayerHandler.players[i].playerLevel[3] - damage < 0) {
					damage = PlayerHandler.players[i].playerLevel[3];
				}
				if (o.vengOn)
					c.getCombat().appendVengeance(i, damage);
				if (damage > 0)
					c.getCombat().applyRecoil(damage, i);
				if (c.magicDef) {
					c.getPA().addSkillXP((damage * Config.MELEE_EXP_RATE / 3),
							1);
					c.getPA().refreshSkill(1);
				}
				c.getPA().addSkillXP(
						(c.MAGIC_SPELLS[c.oldSpellId][7] + damage
								* Config.MAGIC_EXP_RATE), 6);
				c.getPA().addSkillXP(
						(c.MAGIC_SPELLS[c.oldSpellId][7] + damage
								* Config.MAGIC_EXP_RATE / 3), 3);
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(6);

				if (c.getCombat().getEndGfxHeight() == 100 && !c.magicFailed) { // end
																				// GFX
					PlayerHandler.players[i]
							.gfx100(c.MAGIC_SPELLS[c.oldSpellId][5]);
				} else if (!c.magicFailed) {
					PlayerHandler.players[i]
							.gfx0(c.MAGIC_SPELLS[c.oldSpellId][5]);
				} else if (c.magicFailed) {
					PlayerHandler.players[i].gfx100(85);
				}

				if (!c.magicFailed) {
					if (System.currentTimeMillis()
							- PlayerHandler.players[i].reduceStat > 35000) {
						PlayerHandler.players[i].reduceStat = System
								.currentTimeMillis();
						switch (c.MAGIC_SPELLS[c.oldSpellId][0]) {
						case 12987:
						case 13011:
						case 12999:
						case 13023:
							PlayerHandler.players[i].playerLevel[0] -= ((o
									.getPA()
									.getLevelForXP(
											PlayerHandler.players[i].playerXP[0]) * 10) / 100);
							break;
						}
					}

					switch (c.MAGIC_SPELLS[c.oldSpellId][0]) {
					case 12445: // teleblock
						if (System.currentTimeMillis() - o.teleBlockDelay > o.teleBlockLength) {
							o.teleBlockDelay = System.currentTimeMillis();
							o.sendMessage("You have been teleblocked.");
							if (o.prayerActive[16]
									|| o.curseActive[7]
									&& System.currentTimeMillis()
											- o.protMageDelay > 1500)
								o.teleBlockLength = 150000;
							else
								o.teleBlockLength = 300000;
						}
						break;

					case 12901:
					case 12919: // blood spells
					case 12911:
					case 12929:
						int heal = (int) (damage / 4);
						if (c.playerLevel[3] + heal > c.getPA().getLevelForXP(
								c.playerXP[3])) {
							c.playerLevel[3] = c.getPA().getLevelForXP(
									c.playerXP[3]);
						} else {
							c.playerLevel[3] += heal;
						}
						c.getPA().refreshSkill(3);
						break;

					case 1153:
						PlayerHandler.players[i].playerLevel[0] -= ((o.getPA()
								.getLevelForXP(
										PlayerHandler.players[i].playerXP[0]) * 5) / 100);
						o.sendMessage("Your attack level has been reduced!");
						PlayerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System
								.currentTimeMillis();
						o.getPA().refreshSkill(0);
						break;

					case 1157:
						PlayerHandler.players[i].playerLevel[2] -= ((o.getPA()
								.getLevelForXP(
										PlayerHandler.players[i].playerXP[2]) * 5) / 100);
						o.sendMessage("Your strength level has been reduced!");
						PlayerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System
								.currentTimeMillis();
						o.getPA().refreshSkill(2);
						break;

					case 1161:
						PlayerHandler.players[i].playerLevel[1] -= ((o.getPA()
								.getLevelForXP(
										PlayerHandler.players[i].playerXP[1]) * 5) / 100);
						o.sendMessage("Your defence level has been reduced!");
						PlayerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System
								.currentTimeMillis();
						o.getPA().refreshSkill(1);
						break;

					case 1542:
						PlayerHandler.players[i].playerLevel[1] -= ((o.getPA()
								.getLevelForXP(
										PlayerHandler.players[i].playerXP[1]) * 10) / 100);
						o.sendMessage("Your defence level has been reduced!");
						PlayerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System
								.currentTimeMillis();
						o.getPA().refreshSkill(1);
						break;

					case 1543:
						PlayerHandler.players[i].playerLevel[2] -= ((o.getPA()
								.getLevelForXP(
										PlayerHandler.players[i].playerXP[2]) * 10) / 100);
						o.sendMessage("Your strength level has been reduced!");
						PlayerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System
								.currentTimeMillis();
						o.getPA().refreshSkill(2);
						break;

					case 1562:
						PlayerHandler.players[i].playerLevel[0] -= ((o.getPA()
								.getLevelForXP(
										PlayerHandler.players[i].playerXP[0]) * 10) / 100);
						o.sendMessage("Your attack level has been reduced!");
						PlayerHandler.players[i].reduceSpellDelay[c.reduceSpellId] = System
								.currentTimeMillis();
						o.getPA().refreshSkill(0);
						break;
					}
				}
				if (PlayerHandler.players[i] != null) {
					if (PlayerHandler.players[i].curseActive[7] && damage > 0) {// Magic
																				// Deflect
						c.getCurse().appendDeflect(
								PlayerHandler.players[i].playerId, damage, 4);
					}
				}
				PlayerHandler.players[i].hitIcon = 2;
				PlayerHandler.players[i].logoutDelay = System
						.currentTimeMillis();
				PlayerHandler.players[i].underAttackBy = c.playerId;
				PlayerHandler.players[i].killerId = c.playerId;
				PlayerHandler.players[i].singleCombatDelay = System
						.currentTimeMillis();
				if (c.getCombat().magicMaxHit() != 0) {
					PlayerHandler.players[i].dealDamage(damage);
					PlayerHandler.players[i].damageTaken[c.playerId] += damage;
					c.totalPlayerDamageDealt += damage;
					if (!c.magicFailed) {
						PlayerHandler.players[i].handleHitMask(damage);
					}
				}
				c.getCombat().applySmite(i, damage);
				c.killedBy = PlayerHandler.players[i].playerId;
				o.getPA().refreshSkill(3);
				PlayerHandler.players[i].updateRequired = true;
				c.usingMagic = false;
				c.castingMagic = false;
				if (o.inMulti() && c.getCombat().multis()) {
					c.barrageCount = 0;
					for (int j = 0; j < PlayerHandler.players.length; j++) {
						if (PlayerHandler.players[j] != null) {
							if (j == o.playerId)
								continue;
							if (c.barrageCount >= 9)
								break;
							if (o.goodDistance(o.getX(), o.getY(),
									PlayerHandler.players[j].getX(),
									PlayerHandler.players[j].getY(), 1))
								c.getCombat().appendMultiBarrage(j,
										c.magicFailed);
						}
					}
				}
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(6);
				c.oldSpellId = 0;
			}
		}
		c.getPA().requestUpdates();
		if (c.bowSpecShot <= 0) {
			c.oldPlayerIndex = 0;
			c.projectileStage = 0;
			c.lastWeaponUsed = 0;
			c.doubleHit = false;
			c.bowSpecShot = 0;
		}
		if (c.bowSpecShot != 0) {
			c.bowSpecShot = 0;
		}
	}
}