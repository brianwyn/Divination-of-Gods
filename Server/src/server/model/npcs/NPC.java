package server.model.npcs;

import server.model.players.Client;
import server.util.Misc;
import server.util.Stream;

public class NPC {

	public int lastX, lastY;
	public int npcId;
	public int npcType;
	public int index;
	public boolean IsAttackingPerson = false;
	public int absX, absY;
	public int heightLevel;
	public int makeX, makeY, maxHit, defence, attack, moveX, moveY, direction,
			walkingType;
	public int spawnX, spawnY;
	public int viewX, viewY;
	public int oldIndexNPC;
	public int barrageorb = 0;
	public int attacknpc;
	public int tries;

	/**
	 * attackType: 0 = melee, 1 = range, 2 = mage, 3 = kbd fire
	 */
	public int attackType, projectileId, endGfx, spawnedBy, hitDelayTimer, HP,
			MaxHP, hitDiff, animNumber, actionTimer, enemyX, enemyY;

	public boolean NeedsRespawn = false;

	public boolean applyDead, isDead, needRespawn, respawns;

	public boolean walkingHome, underAttack;

	public int attackingnpc;
	public int underAttackBy2;
	public int freezeTimer, attackTimer, killerId, killedBy, oldIndex,
			underAttackBy;
	public long lastDamageTaken;
	public boolean randomWalk;
	public boolean dirUpdateRequired;
	public boolean animUpdateRequired;
	public boolean hitUpdateRequired;
	public boolean updateRequired;
	public boolean forcedChatRequired;
	public boolean faceToUpdateRequired;
	public int firstAttacker;
	public int followPlayer;
	public int npcIndex;
	public boolean summon;
	public int lastsummon;
	public int npcslot;
	public String forcedText;
	/**
	 * Graphics
	 **/

	public int mask80var1 = 0;
	public int mask80var2 = 0;
	protected boolean mask80update = false;
	/**
	 * 
	 Face
	 * 
	 **/

	public int FocusPointX = -1, FocusPointY = -1;

	public int face = 0;

	public int hitIcon = 0;

	public int hitFocus;

	public int hitDiff2 = 0;

	public boolean hitUpdateRequired2 = false;

	public int CIcon = 0;

	public boolean dragonBreath = false;
	public int setIcon = -1;
	public boolean npcLeeched = false;

	public NPC(int _npcId, int _npcType) {
		npcId = _npcId;
		npcType = _npcType;
		direction = -1;
		isDead = false;
		applyDead = false;
		actionTimer = 0;
		randomWalk = true;
	}

	public void appendAnimUpdate(Stream str) {
		str.writeWordBigEndian(animNumber);
		str.writeByte(1);
	}

	public void appendFaceEntity(Stream str) {
		str.writeWord(face);
	}

	public void appendFaceToUpdate(Stream str) {
		str.writeWordBigEndian(viewX);
		str.writeWordBigEndian(viewY);
	}

	public void appendHitUpdate(Stream str) {
		if (HP <= 0) {
			isDead = true;
		}
		str.writeByteC(hitDiff);
		if (hitDiff > 0) {
			str.writeByteS(1);
		} else {
			str.writeByteS(0);
		}
		str.writeByte(hitIcon + 1);// icon
		str.writeByte(hitFocus);// focus
		str.writeByteS(Misc.getCurrentHP(HP, MaxHP, 100));
		str.writeByteC(100);
	}

	public void appendHitUpdate2(Stream str) {
		if (HP <= 0) {
			isDead = true;
		}
		str.writeByteA(hitDiff2);
		if (hitDiff2 > 0) {
			str.writeByteC(1);
		} else {
			str.writeByteC(0);
		}
		str.writeByte(hitIcon + 1);// icon
		str.writeByte(hitFocus);// focus
		str.writeByteA(HP);
		str.writeByte(MaxHP);
	}

	public void appendMask80Update(Stream str) {
		str.writeWord(mask80var1);
		str.writeDWord(mask80var2);
	}

	public void appendNPCUpdateBlock(Stream str) {
		if (!updateRequired)
			return;
		int updateMask = 0;
		if (animUpdateRequired)
			updateMask |= 0x10;
		if (hitUpdateRequired2)
			updateMask |= 8;
		if (mask80update)
			updateMask |= 0x80;
		if (dirUpdateRequired)
			updateMask |= 0x20;
		if (forcedChatRequired)
			updateMask |= 1;
		if (hitUpdateRequired)
			updateMask |= 0x40;
		if (FocusPointX != -1)
			updateMask |= 4;

		str.writeByte(updateMask);

		if (animUpdateRequired)
			appendAnimUpdate(str);
		if (hitUpdateRequired2)
			appendHitUpdate2(str);
		if (mask80update)
			appendMask80Update(str);
		if (dirUpdateRequired)
			appendFaceEntity(str);
		if (forcedChatRequired) {
			str.writeString(forcedText);
		}
		if (hitUpdateRequired)
			appendHitUpdate(str);
		if (FocusPointX != -1)
			appendSetFocusDestination(str);

	}

	private void appendSetFocusDestination(Stream str) {
		str.writeWordBigEndian(FocusPointX);
		str.writeWordBigEndian(FocusPointY);
	}

	public boolean AtCorp() {
		if (absX > 2879 && absX < 2918 && absY > 4369 && absY < 4414) {
			return true;
		}
		return false;
	}

	public boolean atCorpMulti() {
		if (absX > 3331 && absX < 3391 && absY > 3242 && absY < 3260) {
			return true;
		}
		return false;
	}

	public boolean atDemon() {
		if (absX >= 2363 && absX <= 2391 && absY >= 4676 && absY <= 4700) {
			return true;
		}
		return false;
	}

	public String barbRandom(Client c, int type) {
		switch (type) {
		case 0:
			return "KILL THAT MONGREL!";
		case 1:
			return "YOU'RE MINE, " + c.playerName.toUpperCase() + "!";
		case 2:
			return "YOU REALLY THINK YOU CAN BEAT ME?";
		case 3:
			return "IS THAT ALL YOU'VE GOT WEAKLING?";
		case 4:
			return "FAILURE IS NOT AN OPTION MY MINIONS! ATTACK!";
		case 5:
			return "WHAT ARE YOU ALL DOING? KILL HIM!";
		case 6:
			return "BOW DOWN TO THE BARBARIANS " + c.playerName.toUpperCase()
					+ "!";
		case 12:
			return "OUR DEFENCE IS BEING BROKEN DEFEND US!";
		case 7:
			return "SHOW YOURSELF MINIONS DEFEAT THIS COWARD!";
		}
		return "";
	}

	public void clearUpdateFlags() {
		updateRequired = false;
		forcedChatRequired = false;
		hitUpdateRequired = false;
		hitUpdateRequired2 = false;
		animUpdateRequired = false;
		dirUpdateRequired = false;
		mask80update = false;
		forcedText = null;
		moveX = 0;
		moveY = 0;
		direction = -1;
		FocusPointX = -1;
		FocusPointY = -1;
		hitFocus = 0;
	}

	public boolean coordsCheck(int X1, int X2, int Y1, int Y2) {
		return absX >= X1 && absX <= X2 && absY >= Y1 && absY <= Y2;
	}

	public void facenpc(int npc) {
		face = npc;
		dirUpdateRequired = true;
		updateRequired = true;
	}

	public void facePlayer(int player) {
		if (!NPCHandler.isHunterIMP(npcType))
			face = player + 32768;
		dirUpdateRequired = true;
		updateRequired = true;
	}

	public void forceAnim(int number) {
		animNumber = number;
		animUpdateRequired = true;
		updateRequired = true;
	}

	/**
	 * Text update
	 **/

	public void forceChat(String text) {
		forcedText = text;
		forcedChatRequired = true;
		updateRequired = true;
	}

	public void getNextNPCMovement(int i) {
		direction = -1;
		if (NPCHandler.npcs[i].freezeTimer == 0) {
			direction = getNextWalkingDirection();
		}
	}

	public int getNextWalkingDirection() {
		int dir;
		dir = Misc.direction(absX, absY, (absX + moveX), (absY + moveY));

		if (dir == -1)
			return -1;
		dir >>= 1;
		absX += moveX;
		absY += moveY;
		return dir;
	}

	public int getX() {
		return absX;
	}

	public int getY() {
		return absY;
	}

	public void gfx0(int gfx) {
		mask80var1 = gfx;
		mask80var2 = 65536;
		mask80update = true;
		updateRequired = true;
	}

	public void gfx100(int gfx) {
		mask80var1 = gfx;
		mask80var2 = 6553600;
		mask80update = true;
		updateRequired = true;
	}

	public void gfx50(int gfx) {
		mask80var1 = gfx;
		mask80var2 = 3276800;
		mask80update = true;
		updateRequired = true;
	}

	public void handleHitMask(int damage) {
		if (!hitUpdateRequired) {
			hitUpdateRequired = true;
			hitDiff = damage;
		} else if (!hitUpdateRequired2) {
			hitUpdateRequired2 = true;
			hitDiff2 = damage;
		}
		updateRequired = true;
	}

	public void handleHitMask(int damage, int focus) {
		if (!hitUpdateRequired) {
			hitUpdateRequired = true;
			hitDiff = damage;
			hitFocus = focus;
		} else if (!hitUpdateRequired2) {
			hitUpdateRequired2 = true;
			hitDiff2 = damage;
			hitFocus = focus;
		}
		updateRequired = true;
	}

	public int handleSlayerPoints() {
		switch (npcType) {
		case 6260:
			return 2;
		}
		return 0;
	}

	public boolean inBarbDef() {
		return (coordsCheck(1882, 1891, 5403, 5412));
	}

	public boolean inDominionTower() {
		if (absX > 3035 && absX < 3056 && absY > 3372 && absY < 3387) {
			return true;
		}
		return false;
	}

	public boolean InDung() {
		if (absX > 1854 && absX < 1922 && absY > 5179 && absY < 5248) {
			return true;
		}
		return false;
	}

	public boolean inDungBossRoom() {
		if (absX > 1902 && absX < 1919 && absY > 5208 && absY < 5231) {
			return true;
		}
		return false;
	}

	public boolean inFightCaves() {
		return absX >= 2360 && absX <= 2445 && absY >= 5045 && absY <= 5125;
	}

	public boolean inMulti() {
		if (inBarbDef()) {
			return true;
		}
		if (atDemon()) {
			return true;
		}
		if (AtCorp()) {
			return true;
		}
		if (InDung()) {
			return true;
		}
		if ((absX >= 3136 && absX <= 3327 && absY >= 3519 && absY <= 3607)
				|| (absX >= 2909 && absX <= 2939 && absY >= 5189 && absY <= 5220)
				|| (absX >= 3210 && absX <= 3339 && absY >= 9333 && absY <= 9424)
				|| (absX >= 3013 && absX <= 3036 && absY >= 5214 && absY <= 5249)
				|| (absX >= 3190 && absX <= 3327 && absY >= 3648 && absY <= 3839)
				|| (absX >= 3200 && absX <= 3390 && absY >= 3840 && absY <= 3967)
				|| (absX >= 2992 && absX <= 3007 && absY >= 3912 && absY <= 3967)
				|| (absX >= 2946 && absX <= 2959 && absY >= 3816 && absY <= 3831)
				|| (absX >= 3008 && absX <= 3199 && absY >= 3856 && absY <= 3903)
				|| (absX >= 3008 && absX <= 3071 && absY >= 3600 && absY <= 3711)
				|| (absX >= 3072 && absX <= 3327 && absY >= 3608 && absY <= 3647)
				|| (absX >= 2312 && absX <= 2332 && absY >= 3783 && absY <= 3801)
				|| (absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619)
				|| (absX >= 2820 && absX <= 2955 && absY >= 5250 && absY <= 5370)
				|| (absX >= 2371 && absX <= 2422 && absY >= 5062 && absY <= 5117)
				|| (absX >= 2896 && absX <= 2927 && absY >= 3595 && absY <= 3630)
				|| (absX >= 2892 && absX <= 2932 && absY >= 4435 && absY <= 4464)
				|| (absX >= 2824 && absX <= 2827 && absY >= 3808 && absY <= 3812)
				|| (absX >= 2453 && absX <= 2476 && absY >= 4768 && absY <= 4790)
				|| (absX >= 2947 && absX <= 3002 && absY >= 9475 && absY <= 9528)
				|| (absX >= 2256 && absX <= 2287 && absY >= 4680 && absY <= 4711)
				|| (absX >= 2980 && absX <= 2993 && absY >= 9629 && absY <= 9644)
				|| absX > 2784 && absX < 2802 && absY > 9321 && absY < 9341) {
			return true;
		}
		return false;
	}

	public boolean inPcGame() {
		return absX >= 2624 && absX <= 2690 && absY >= 2550 && absY <= 2619;
	}

	public boolean inRandomEvent() {
		if (absX > 2583 && absX < 2615 && absY > 4756 && absY < 4790) {
			return true;
		}
		return false;
	}

	public boolean inWild() {
		if (absX > 2941 && absX < 3392 && absY > 3518 && absY < 3966
				|| absX > 2941 && absX < 3392 && absY > 9918 && absY < 10366) {
			return true;
		}
		return false;
	}

	public void requestAnimation(int animId, int i) {
		animNumber = animId;
		animUpdateRequired = true;
		updateRequired = true;
	}

	public void startAnim(int animId) {
		animNumber = animId;
		animUpdateRequired = true;
		updateRequired = true;
	}

	public void turnNpc(int i, int j) {
		FocusPointX = 2 * i + 1;
		FocusPointY = 2 * j + 1;
		updateRequired = true;

	}

	public void updateNPCMovement(Stream str) {
		if (direction == -1) {

			if (updateRequired) {

				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
		} else {

			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, Misc.xlateDirectionToClient[direction]);
			if (updateRequired) {
				str.writeBits(1, 1);
			} else {
				str.writeBits(1, 0);
			}
		}
	}

}
