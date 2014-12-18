package server.model.players.content.combat;

import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.players.Client;

public class LeechNPC {

	public static boolean eventRunning = false;

	public static void applySoulSplit(Client c) {
		if (c.curseActive[18]) { // SoulSplit GFX's - CAUSES CRASH
			if (c.oldNpcIndex > 0) {
				if (NPCHandler.npcs[c.oldNpcIndex] != null) {
					// try {
					int pX = c.getX();
					int pY = c.getY();
					int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
					int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
					int offX = (pY - nY) * -1;
					int offY = (pX - nX) * -1;
					c.SSPLIT = true;
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50,
							50, 2263, 9, 9, -c.oldNpcIndex - 1, 24, 0);

					// EventManager.getSingleton().addEvent(new
					// Event() {
					// public void execute(EventContainer b) {
					// Server.playerHandler.players[c.oldPlayerIndex].gfx0(2264);
					// // 1738
					c.SSPLIT = false;
					if (c.curseActive[18] && !c.prayerActive[23]
							&& c.playerLevel[3] <= 99 && c.soulSplitHeal > 0) {
						if (c.playerLevel[3] + c.soulSplitHeal >= c.getPA()
								.getLevelForXP(c.playerXP[3])) {
							c.playerLevel[3] = c.getPA().getLevelForXP(
									c.playerXP[3]);
						} else {
							c.playerLevel[3] += c.soulSplitHeal;
						}
						c.getPA().refreshSkill(3);
						c.soulSplitHeal = 0;
					}
					NPC n = NPCHandler.npcs[c.oldNpcIndex];
					if (n != null) {
						n.gfx0(2264);
					}
					// b.stop();
					// }
					// }, 500);
					/*
					 * EventManager.getSingleton().addEvent(new Event() { //
					 * CAUSES CRASH public void execute(EventContainer b) {
					 * //c.getPA().createPlayersProjectile2(nX, nY, offX, offY,
					 * 50, 50, 2263, 9, 9, - c.playerId - 1, 24, 0); b.stop(); }
					 * }, 800);
					 */
					// } catch (Exception e) {
					// e.printStackTrace();
					// }
				}
			}
		}
	}

	public static void boostTimer(final Client c, final int stat) {
		if (eventRunning)
			return;
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				eventRunning = true;
				if (c.boostTimer > 0)
					c.boostTimer--;

				if (c.boostTimer < 0 || c.boostTimer == 0)
					container.stop();

				if (c == null || c.disconnected)
					container.stop();
			}

			@Override
			public void stop() {
				eventRunning = false;
				c.boosed[stat] = false;
			}
		}, 1);
	}

	public static int getGFXID(Client c) {
		if (c.curseActive[10])
			return 2253;
		if (c.curseActive[11])
			return 2238;
		if (c.curseActive[12])
			return 2242;
		if (c.curseActive[13])
			return 2246;
		if (c.curseActive[14])
			return 2250;

		return 2253;
	}

	public static int getProjectileID(Client c) {
		if (c.curseActive[10])
			return 2252;
		if (c.curseActive[11])
			return 2236;
		if (c.curseActive[12])
			return 2240;
		if (c.curseActive[13])
			return 2242;
		if (c.curseActive[14])
			return 2248;

		return 2252;
	}

	public static String getStatName(int stat) {
		if (stat == 0) {
			return "Attack";
		} else if (stat == 1) {
			return "Defence";
		} else if (stat == 2) {
			return "Strength";
		} else if (stat == 4) {
			return "Ranged";
		} else if (stat == 6) {
			return "Magic";
		}
		return null;
	}

	public static void leechNPC(Client c, int stat) {
		if (c.oldNpcIndex > 0) {
			if (NPCHandler.npcs[c.oldNpcIndex] != null) {
				NPC n = NPCHandler.npcs[c.oldNpcIndex];
				if (n == null)
					return;
				int pX = c.getX();
				int pY = c.getY();
				int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
				int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
				int offX = (pY - nY) * -1;
				int offY = (pX - nX) * -1;
				c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50, 50,
						getProjectileID(c), 9, 9, -c.oldNpcIndex - 1, 24, 0);
				n.gfx0(getGFXID(c));
				c.startAnimation(12575);
				// if(!n.npcLeeched) {
				/*
				 * if(!c.boosed[stat] && !eventRunning) { int leeched = (int)
				 * (c.getPA().getLevelForXP(c.playerXP[stat]) * .05); //n.attack
				 * -= leeched; c.playerLevel[stat] += leeched;
				 * c.getPA().refreshSkill(stat); c.boosed[stat] = true;
				 * c.boostTimer = 110; boostTimer(c, stat); }
				 */
				// }
				System.out.println(n.npcLeeched);
			}
		}
	}
}