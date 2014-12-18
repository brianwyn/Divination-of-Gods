package server.model.players.content.combat;

import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.content.combat.magic.MagicData;
import server.model.players.content.combat.magic.MagicExtras;
import server.model.players.content.combat.magic.MagicMaxHit;
import server.model.players.content.combat.magic.MagicRequirements;
import server.model.players.content.combat.melee.CombatPrayer;
import server.model.players.content.combat.melee.MeleeData;
import server.model.players.content.combat.melee.MeleeExtras;
import server.model.players.content.combat.melee.MeleeMaxHit;
import server.model.players.content.combat.melee.MeleeRequirements;
import server.model.players.content.combat.melee.MeleeSpecial;
import server.model.players.content.combat.range.RangeData;
import server.model.players.content.combat.range.RangeExtras;
import server.model.players.content.combat.range.RangeMaxHit;

public class CombatAssistant {

	private Client c;

	public int[][] slayerReqs = { { 1648, 1 }, { 1612, 1 }, { 1643, 1 },
			{ 1618, 1 }, { 1624, 1 }, { 1610, 1 }, { 1613, 1 }, { 1615, 1 },
			{ 2783, 1 } };

	public CombatAssistant(Client Client) {
		this.c = Client;
	}

	public void activatePrayer(int i) {
		CombatPrayer.activatePrayer(c, i);
	}

	public void activateSpecial(int weapon, int i) {
		MeleeSpecial.activateSpecial(c, weapon, i);
	}

	public void addCharge() {
		MeleeExtras.addCharge(c);
	}

	public void addNPCHit(int i, Client c) {
		AttackNPC.addNPCHit(i, c);
	}

	public void appendMultiBarrage(int playerId, boolean splashed) {
		MagicExtras.appendMultiBarrage(c, playerId, splashed);
	}

	public void appendMultiBarrageNPC(int npcId, boolean splashed) {
		MagicExtras.appendMultiBarrageNPC(c, npcId, splashed);
	}

	public void appendMutliChinchompa(int npcId) {
		RangeExtras.appendMutliChinchompa(c, npcId);
	}

	public void appendVengeance(int otherPlayer, int damage) {
		MeleeExtras.appendVengeance(c, otherPlayer, damage);
	}

	public void appendVengeanceNPC(int otherPlayer, int damage) {
		MeleeExtras.appendVengeanceNPC(c, otherPlayer, damage);
	}

	public void applyNpcMeleeDamage(int i, int damageMask, int damage) {
		AttackNPC.applyNpcMeleeDamage(c, i, damageMask, damage);
	}

	public void applyPlayerHit(Client c, final int i) {
		AttackPlayer.applyPlayerHit(c, i);
	}

	public void applyPlayerMeleeDamage(int i, int damageMask, int damage) {
		AttackPlayer.applyPlayerMeleeDamage(c, i, damageMask, damage);
	}

	public void applyRecoil(int damage, int i) {
		MeleeExtras.applyRecoil(c, damage, i);
	}

	public void applyRecoilNPC(int damage, int i) {
		MeleeExtras.applyRecoilNPC(c, damage, i);
	}

	public void applySmite(int index, int damage) {
		MeleeExtras.applySmite(c, index, damage);
	}

	public void attackNpc(int i) {
		AttackNPC.attackNpc(c, i);
	}

	public void attackPlayer(int i) {
		AttackPlayer.attackPlayer(c, i);
	}

	public int bestMeleeAtk() {
		return MeleeMaxHit.bestMeleeAtk(c);
	}

	public int bestMeleeDef() {
		return MeleeMaxHit.bestMeleeDef(c);
	}

	public int calculateMeleeAttack() {
		return MeleeMaxHit.calculateMeleeAttack(c);
	}

	public int calculateMeleeDefence() {
		return MeleeMaxHit.calculateMeleeDefence(c);
	}

	public int calculateMeleeMaxHit() {
		return (int) MeleeMaxHit.calculateBaseDamage(c, c.usingSpecial);
	}

	public int calculateRangeAttack() {
		return RangeMaxHit.calculateRangeAttack(c);
	}

	public int calculateRangeDefence() {
		return RangeMaxHit.calculateRangeDefence(c);
	}

	public boolean checkMagicReqs(int spell) {
		return MagicRequirements.checkMagicReqs(c, spell);
	}

	public boolean checkMultiBarrageReqs(int i) {
		return MagicExtras.checkMultiBarrageReqs(c, i);
	}

	public boolean checkMultiBarrageReqsNPC(int i) {
		return MagicExtras.checkMultiBarrageReqsNPC(i);
	}

	public boolean checkReqs() {
		return MeleeRequirements.checkReqs(c);
	}

	public boolean checkSpecAmount(int weapon) {
		return MeleeSpecial.checkSpecAmount(c, weapon);
	}

	public int correctBowAndArrows() {
		return RangeData.correctBowAndArrows(c);
	}

	public void crossbowSpecial(Client c, int i, boolean npcAttack) {
		RangeExtras.crossbowSpecial(c, i, npcAttack);
	}

	public void delayedHit(final Client c, final int i) {
		AttackNPC.delayedHit(c, i);
	}

	public void fireProjectileNpc() {
		RangeData.fireProjectileNpc(c);
	}

	public void fireProjectilePlayer() {
		RangeData.fireProjectilePlayer(c);
	}

	public int getAttackDelay(String s) {
		return MeleeData.getAttackDelay(c, s);
	}

	public int getBlockEmote() {
		return MeleeData.getBlockEmote(c);
	}

	public int getCombatDifference(int combat1, int combat2) {
		return MeleeRequirements.getCombatDifference(combat1, combat2);
	}

	public int getEndGfxHeight() {
		return MagicData.getEndGfxHeight(c);
	}

	public int getEndHeight() {
		return MagicData.getEndHeight(c);
	}

	public int getFreezeTime() {
		return MagicData.getFreezeTime(c);
	}

	public int getHitDelay(int i, String weaponName) {
		return MeleeData.getHitDelay(c, i, weaponName);
	}

	public int getKillerId(int playerId) {
		return MeleeRequirements.getKillerId(c, playerId);
	}

	public int getMagicGraphic(Client c, int i) {
		return MagicData.getMagicGraphic(c, i);
	}

	public void getPlayerAnimIndex(String weaponName) {
		MeleeData.getPlayerAnimIndex(c, weaponName);
	}

	public int getProjectileShowDelay() {
		return RangeData.getProjectileShowDelay(c);
	}

	public int getProjectileSpeed() {
		return RangeData.getProjectileSpeed(c);
	}

	public int getRangeProjectileGFX() {
		return RangeData.getRangeProjectileGFX(c);
	}

	public int getRangeStartGFX() {
		return RangeData.getRangeStartGFX(c);
	}

	public int getRangeStr(int i) {
		return RangeData.getRangeStr(i);
	}

	public int getRequiredDistance() {
		return MeleeRequirements.getRequiredDistance(c);
	}

	public int getStaffNeeded() {
		return MagicData.getStaffNeeded(c);
	}

	public int getStartDelay() {
		return MagicData.getStartDelay(c);
	}

	public int getStartGfxHeight() {
		return MagicData.getStartGfxHeight(c);
	}

	public int getStartHeight() {
		return MagicData.getStartHeight(c);
	}

	public int getWepAnim(String weaponName) {
		return MeleeData.getWepAnim(c, weaponName);
	}

	public boolean godSpells() {
		return MagicData.godSpells(c);
	}

	public boolean goodSlayer(int i) {
		for (int j = 0; j < slayerReqs.length; j++) {
			if (slayerReqs[j][0] == NPCHandler.npcs[i].npcType) {
				if (slayerReqs[j][1] > c.playerLevel[Player.playerSlayer]) {
					c.sendMessage("You need a slayer level of "
							+ slayerReqs[j][1] + " to harm this NPC.");
					return false;
				}
			}
		}
		return true;
	}

	public void handleDfs(final Client c) {
		MeleeExtras.handleDragonFireShield(c);
	}

	public void handleDfsNPC(final Client c) {
		MeleeExtras.handleDragonFireShieldNPC(c);
	}

	public void handleGmaulPlayer() {
		MeleeExtras.graniteMaulSpecial(c);
	}

	public void handlePrayerDrain() {
		CombatPrayer.handlePrayerDrain(c);
	}

	public boolean kalphite1(int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 1158:
			return true;
		}
		return false;
	}

	public boolean kalphite2(int i) {
		switch (NPCHandler.npcs[i].npcType) {
		case 1160:
			return true;
		}
		return false;
	}

	public int mageAtk() {
		return MagicMaxHit.mageAttack(c);
	}

	public int mageDef() {
		return MagicMaxHit.mageDefefence(c);
	}

	public int magicMaxHit() {
		return MagicMaxHit.magiMaxHit(c);
	}

	public boolean multis() {
		return MagicData.multiSpells(c);
	}

	public void multiSpellEffect(int playerId, int damage) {
		MagicExtras.multiSpellEffect(c, playerId, damage);
	}

	public void multiSpellEffectNPC(int npcId, int damage) {
		MagicExtras.multiSpellEffectNPC(c, npcId, damage);
	}

	public int npcDefenceAnim(int i) {
		return MeleeData.npcDefenceAnim(i);
	}

	public void playerDelayedHit(final Client c, final int i) {
		AttackPlayer.playerDelayedHit(c, i);
	}

	public boolean properBolts() {
		return usingBolts(c.playerEquipment[Player.playerArrows]);
	}

	public int rangeMaxHit() {
		return RangeMaxHit.maxHit(c);
	}

	public void reducePrayerLevel() {
		CombatPrayer.reducePrayerLevel(c);
	}

	public void removeRecoil(Client c) {
		MeleeExtras.removeRecoil(c);
	}

	public void resetPlayerAttack() {
		MeleeData.resetPlayerAttack(c);
	}

	public void resetPrayers() {
		CombatPrayer.resetPrayers(c);
	}

	public boolean usingBolts(int i) {
		return (i >= 9140 && i <= 9145) || (i >= 9236 && i <= 9245);
	}

	public boolean usingCrystalBow() {
		return c.playerEquipment[Player.playerWeapon] >= 4212
				&& c.playerEquipment[Player.playerWeapon] <= 4223;
	}

	public boolean usingDbow() {
		return c.playerEquipment[Player.playerWeapon] == 11235;
	}

	public boolean usingHally() {
		return MeleeData.usingHally(c);
	}

	public boolean wearingStaff(int runeId) {
		return MagicRequirements.wearingStaff(c, runeId);
	}
}