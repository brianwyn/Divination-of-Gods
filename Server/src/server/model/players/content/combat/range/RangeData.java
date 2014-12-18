package server.model.players.content.combat.range;

import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;

public class RangeData {

	public static final int rangedData[][] = {
			// WEAPON ID FOLLOWED BY ARROW IDS THAT CAN BE USED WITH THE WEAPON.
			/**
			 * Crossbows
			 */
			{ 18357, 877, 9140, 9141, 9142, 9143, 9144, 9145, 9336, 9337, 9338,
					9339, 9340, 9341, 9342, 9236, 9237, 9238, 9239, 9240, 9241,
					9242, 9243, 9244, 9245 },
			{ 9185, 877, 9140, 9141, 9142, 9143, 9144, 9145, 9336, 9337, 9338,
					9339, 9340, 9341, 9342, 9236, 9237, 9238, 9239, 9240, 9241,
					9242, 9243, 9244, 9245 },
			{ 9183, 877, 9140, 9141, 9142, 9143, 9236, 9237, 9238, 9239, 9240,
					9241 },
			{ 9181, 877, 9140, 9141, 9142, 9236, 9237, 9238, 9239 },
			{ 9179, 877, 9140, 9141, 9236, 9237, 9238 },
			{ 9177, 877, 9140, 9236 },
			{ 9174, 877 },
			/**
			 * Bows
			 */
			{ 11235, 19146, 19143, 19149, 14990, 882, 884, 886, 888, 890, 892,
					11212 }, { 861, 882, 884, 886, 888, 890, 892 },
			{ 859, 882, 884, 886, 888, 890, 892 },
			{ 857, 882, 884, 886, 888, 890 }, { 855, 882, 884, 886, 888, 890 },
			{ 853, 882, 884, 886, 888 }, { 851, 882, 884, 886, 888 },
			{ 849, 882, 884, 886 }, { 847, 882, 884, 886 }, { 843, 882, 884 },
			{ 845, 882, 884 }, { 841, 882 }, { 839, 882 }, { 15241, 15243 } };

	/*
	 * Loops through array to find if the player arrows are equal to the array
	 * data
	 */
	public static boolean correctAmmo(int weapon, int currentArrows) {
		int k = -1;
		int index = -1;
		for (int n = 0; n < rangedData.length; n++) {
			if (rangedData[n][0] == weapon) {
				k = rangedData[n][0];
				index = n;
			}
		}
		if (index == -1 || k == -1)
			return false;
		if (weapon == k) {
			for (int i = 0; i < rangedData[index].length; i++) {
				if (rangedData[index][i] == currentArrows) {
					return true;
				}
			}
		}
		return false;
	}

	// THESE UFCKING METHODS BELONG TO GABBE
	public static boolean correctArrowsAndBow(Client c) {
		int weapon = c.playerEquipment[3];
		int playerArrows = c.playerEquipment[Player.playerArrows];
		if (usingThrowWeapons(weapon)) {
			return true;
		}
		if (playerArrows < 1 && c.attackTimer <= 0) {
			c.sendMessage("You don't have any ammunition to fire.");
			return false;
		}
		if (!correctAmmo(weapon, playerArrows)) {
			boolean addS = !c.getItems().getItemName(playerArrows)
					.endsWith("s")
					&& !c.getItems().getItemName(playerArrows).endsWith("(e)");
			String add = addS ? "s" : "";
			boolean vowel = false;
			if (c.getItems().getItemName(weapon).startsWith("A")
					|| c.getItems().getItemName(weapon).startsWith("E")
					|| c.getItems().getItemName(weapon).startsWith("I")
					|| c.getItems().getItemName(weapon).startsWith("O")
					|| c.getItems().getItemName(weapon).startsWith("U"))
				vowel = true;
			String add2 = vowel ? "an" : "a";
			c.sendMessage("You can not use "
					+ c.getItems().getItemName(playerArrows) + "" + add
					+ " with " + add2 + " " + c.getItems().getItemName(weapon)
					+ ".");
			return false;
		}
		return true;
	}

	public static int correctBowAndArrows(Client c) {
		if (c.getCombat().properBolts())
			return -1;
		switch (c.playerEquipment[Player.playerWeapon]) {
		case 15241:
			return 15243;
		case 839:
		case 841:
			return 882;

		case 843:
		case 845:
			return 884;

		case 847:
		case 849:
			return 886;

		case 851:
		case 853:
			return 888;

		case 855:
		case 857:
			return 890;

		case 859:
		case 861:
			return 892;

		case 4734:
		case 4935:
		case 4936:
		case 4937:
			return 4740;

		case 19146:
			return 19157;

		case 19149:
			return 19162;

		case 19143:
			return 19152;

		case 11235:
		case 14990:
			return 11212;
		}
		return -1;
	}

	public static boolean correctEquipment(Client c) {
		if (!correctArrowsAndBow(c)) {
			return false;
		}
		return true;
	}

	public static void fireProjectileNpc(Client c) {
		if (c.oldNpcIndex > 0) {
			if (NPCHandler.npcs[c.oldNpcIndex] != null) {
				c.projectileStage = 2;
				int pX = c.getX();
				int pY = c.getY();
				int nX = NPCHandler.npcs[c.oldNpcIndex].getX();
				int nY = NPCHandler.npcs[c.oldNpcIndex].getY();
				int offX = (pY - nY) * -1;
				int offY = (pX - nX) * -1;
				c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50,
						c.getCombat().getProjectileSpeed(),
						c.getCombat().getRangeProjectileGFX(), 43, 31,
						c.oldNpcIndex + 1,
						c.getCombat().getProjectileShowDelay());
				if (c.getCombat().usingDbow())
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50,
							c.getCombat().getProjectileSpeed(),
							c.getCombat().getRangeProjectileGFX(), 60, 31,
							c.oldNpcIndex + 1, c.getCombat().getStartDelay(),
							35);
			}
		}
	}

	public static void fireProjectilePlayer(Client c) {
		if (c.oldPlayerIndex > 0) {
			if (PlayerHandler.players[c.oldPlayerIndex] != null) {
				c.projectileStage = 2;
				int pX = c.getX();
				int pY = c.getY();
				int oX = PlayerHandler.players[c.oldPlayerIndex].getX();
				int oY = PlayerHandler.players[c.oldPlayerIndex].getY();
				int offX = (pY - oY) * -1;
				int offY = (pX - oX) * -1;
				if (!c.msbSpec)
					c.getPA().createPlayersProjectile(pX, pY, offX, offY, 50,
							c.getCombat().getProjectileSpeed(),
							c.getCombat().getRangeProjectileGFX(), 43, 31,
							-c.oldPlayerIndex - 1,
							c.getCombat().getStartDelay());
				else if (c.msbSpec) {
					c.getPA().createPlayersProjectile2(pX, pY, offX, offY, 50,
							c.getCombat().getProjectileSpeed(),
							c.getCombat().getRangeProjectileGFX(), 43, 31,
							-c.oldPlayerIndex - 1,
							c.getCombat().getStartDelay(), 10);
					c.msbSpec = false;
				}
				if (c.getCombat().usingDbow())
					// c.getPA().createProjectile3(pY, pX, offY, offX, 50,
					// c.getCombat().getProjectileSpeed(),
					// c.getCombat().getRangeProjectileGFX(), 60, 31, -
					// c.oldPlayerIndex - 1, c.getCombat().getStartDelay(), 35);
					c.getPA().createProjectile3(pY, pX, offY, offX,
							c.getCombat().getRangeProjectileGFX(), 53, 31, 100,
							-c.oldPlayerIndex - 1);

			}
		}
	}

	public static int getProjectileShowDelay(Client c) {
		int[] data = { 806, 806, 808, 809, 810, 811, 10033, 10034, 11230, };
		int str = 53;
		for (int i = 0; i < data.length; i++) {
			if (c.playerEquipment[Player.playerWeapon] == data[i]) {
				str = 32;
			}
		}
		return str;
	}

	public static int getProjectileSpeed(Client c) {
		if (c.dbowSpec)
			return 100;
		switch (c.playerEquipment[3]) {
		case 10033:
		case 10034:
			return 60;
		}
		return 70;
	}

	public static int getRangeEndGFX(Client c) {
		int str = -1;
		int gfx = 0;
		int[][] data = { { 10033, 157, 100 }, { 10034, 157, 100 }, };
		for (int l = 0; l < data.length; l++) {
			if (c.playerEquipment[Player.playerWeapon] == data[l][0]) {
				str = data[l][1];
				gfx = data[l][2];
			}
		}
		if (gfx == 100) {
			c.rangeEndGFXHeight = true;
		}
		return str;
	}

	public static int getRangeProjectileGFX(Client c) {
		if (c.dbowSpec) {
			return c.playerEquipment[Player.playerArrows] == 11212 ? 1099
					: 1101;
		}
		if (c.dbowSpec) {
			return c.playerEquipment[Player.playerArrows] == 11212 ? 1100
					: 1101;
		}
		if (c.bowSpecShot > 0) {
			switch (c.rangeItemUsed) {
			default:
				return 249;
			}
		}
		boolean castingMagic = (c.usingMagic || c.mageFollow || c.autocasting || c.spellId > 0);
		if (castingMagic) {
			return -1;
		}
		if (c.playerEquipment[Player.playerWeapon] == 9185
				|| c.playerEquipment[Player.playerWeapon] == 18357)
			return 27;
		int str = -1;
		int[][] data = {
				// KNIFES
				{ 863, 213 },
				{ 864, 212 },
				{ 865, 214 },
				{ 866, 216 },
				{ 867, 217 },
				{ 868, 218 },
				{ 869, 215 },

				// DARTS
				{ 806, 226 },
				{ 807, 227 },
				{ 808, 228 },
				{ 809, 229 },
				{ 810, 230 },
				{ 811, 231 },

				// JAVELINS
				{ 825, 200 },
				{ 826, 201 },
				{ 827, 202 },
				{ 828, 203 },
				{ 829, 204 },
				{ 830, 205 },

				// AXES
				{ 800, 36 },
				{ 801, 35 },
				{ 802, 37 },
				{ 803, 38 },
				{ 804, 39 },
				{ 805, 40 },

				// ARROWS
				{ 882, 10 }, { 884, 9 }, { 886, 11 }, { 888, 12 }, { 890, 13 },
				{ 892, 15 },
				{ 11212, 1120 },
				{ 19157, 250 },
				{ 19162, 250 },
				{ 19152, 250 },

				// CHINCHOMPA
				{ 10033, 908 },
				{ 10034, 909 },

				// OTHERS
				{ 6522, 442 }, { 4740, 27 }, { 4212, 249 }, { 14990, 249 },
				{ 19143, 249 }, { 4214, 249 }, { 4215, 249 }, { 4216, 249 },
				{ 4217, 249 }, { 4218, 249 }, { 4219, 249 }, { 4220, 249 },
				{ 4221, 249 }, { 4222, 249 }, { 4223, 249 }, };
		for (int l = 0; l < data.length; l++) {
			if (c.rangeItemUsed == data[l][0]) {
				str = data[l][1];
			}
		}
		return str;
	}

	public static int getRangeStartGFX(Client c) {
		int str = -1;
		int[][] data = {
				// KNIFES
				{ 863, 220 },
				{ 864, 219 },
				{ 865, 221 },
				{ 866, 223 },
				{ 867, 224 },
				{ 868, 225 },
				{ 869, 222 },

				// DARTS
				{ 806, 1234 }, { 807, 1235 },
				{ 808, 1236 },
				{ 809, 1238 },
				{ 810, 1239 },
				{ 811, 1240 },
				{ 11230, 1242 },

				// JAVELIN
				{ 825, 206 }, { 826, 207 },
				{ 827, 208 },
				{ 828, 209 },
				{ 829, 210 },
				{ 830, 211 },

				// AXES
				{ 800, 42 }, { 801, 43 }, { 802, 44 },
				{ 803, 45 },
				{ 804, 46 },
				{ 805, 48 },

				// ARROWS
				{ 882, 19 }, { 884, 18 }, { 886, 20 }, { 888, 21 },
				{ 890, 22 },
				{ 892, 24 },

				// CRYSTAL_BOW
				{ 4212, 250 }, { 4214, 250 }, { 4215, 250 }, { 4216, 250 },
				{ 4217, 250 }, { 4218, 250 }, { 4219, 250 }, { 4220, 250 },
				{ 4221, 250 }, { 4222, 250 }, { 4223, 250 }, };
		for (int l = 0; l < data.length; l++) {
			if (c.rangeItemUsed == data[l][0]) {
				str = data[l][1];
			}
		}
		if (c.playerEquipment[3] == 11235) {
			int[][] moreD = { { 882, 1104 }, { 884, 1105 }, { 886, 1106 },
					{ 888, 1107 }, { 890, 1108 }, { 892, 1109 },
					{ 11212, 1111 }, };
			for (int l = 0; l < moreD.length; l++) {
				if (c.playerEquipment[Player.playerArrows] == moreD[l][0]) {
					str = moreD[l][1];
				}
			}
		}
		if (c.playerEquipment[3] == 14990) {
			int[][] moreD = { { 882, 250 }, { 884, 250 }, { 886, 250 },
					{ 888, 250 }, { 890, 250 }, { 892, 250 }, { 11212, 250 }, };
			for (int l = 0; l < moreD.length; l++) {
				if (c.playerEquipment[Player.playerArrows] == moreD[l][0]) {
					str = moreD[l][1];
				}
			}
		}
		if (c.playerEquipment[3] == 19143) {
			int[][] moreD = { { 19152, 250 }, { 882, 250 }, { 884, 250 },
					{ 886, 250 }, { 888, 250 }, { 890, 250 }, { 892, 250 },
					{ 11212, 250 }, };
			for (int l = 0; l < moreD.length; l++) {
				if (c.playerEquipment[Player.playerArrows] == moreD[l][0]) {
					str = moreD[l][1];
				}
			}
		}
		if (c.playerEquipment[3] == 19149) {
			int[][] moreD = { { 19162, 250 }, { 882, 250 }, { 884, 250 },
					{ 886, 250 }, { 888, 250 }, { 890, 250 }, { 892, 250 },
					{ 11212, 250 }, };
			for (int l = 0; l < moreD.length; l++) {
				if (c.playerEquipment[Player.playerArrows] == moreD[l][0]) {
					str = moreD[l][1];
				}
			}
		}
		if (c.playerEquipment[3] == 19146) {
			int[][] moreD = { { 19157, 250 }, { 882, 250 }, { 884, 250 },
					{ 886, 250 }, { 888, 250 }, { 890, 250 }, { 892, 250 },
					{ 11212, 250 }, };
			for (int l = 0; l < moreD.length; l++) {
				if (c.playerEquipment[Player.playerArrows] == moreD[l][0]) {
					str = moreD[l][1];
				}
			}
		}
		return str;
	}

	public static int getRangeStr(int i) {
		int str = 0;
		int[][] data = { { 877, 10 }, { 9140, 46 }, { 9145, 36 }, { 9141, 64 },
				{ 9142, 82 }, { 9143, 100 }, { 9144, 115 }, { 9236, 14 },
				{ 9237, 30 }, { 9238, 48 }, { 9239, 66 }, { 9240, 83 },
				{ 9241, 85 }, { 9242, 103 }, { 9243, 105 }, { 9244, 117 },
				{ 9245, 120 }, { 15243, 130 }, { 882, 7 }, { 884, 10 },
				{ 886, 16 }, { 888, 22 }, { 890, 31 }, { 892, 49 },
				{ 4740, 55 }, { 11212, 60 }, { 806, 1 }, { 807, 3 },
				{ 808, 4 }, { 809, 7 }, { 810, 10 }, { 811, 14 },
				{ 11230, 20 }, { 864, 3 }, { 863, 4 }, { 865, 7 }, { 866, 10 },
				{ 867, 14 }, { 868, 24 }, { 825, 6 }, { 826, 10 }, { 827, 12 },
				{ 828, 18 }, { 829, 28 }, { 830, 42 }, { 800, 5 }, { 801, 7 },
				{ 802, 11 }, { 803, 16 }, { 804, 23 }, { 805, 36 },
				{ 9976, 0 }, { 9977, 15 }, { 4212, 70 }, { 14990, 70 },
				{ 4214, 70 }, { 4215, 70 }, { 4216, 70 }, { 4217, 70 },
				{ 4218, 70 }, { 4219, 70 }, { 4220, 70 }, { 4221, 70 },
				{ 4222, 70 }, { 4223, 70 }, { 6522, 49 }, { 10034, 15 }, };
		for (int l = 0; l < data.length; l++) {
			if (i == data[l][0]) {
				str = data[l][1];
			}
		}
		return str;
	}

	public static boolean usingThrowWeapons(int weaponId) {
		switch (weaponId) {
		// START OF KNIVES
		case 863:
		case 864:
		case 865:
		case 869:
		case 866:
		case 867:
		case 868:
			// START OF DARTS
		case 806:
		case 807:
		case 808:
		case 809:
		case 810:
		case 811:
		case 11230:
			// START OF THROW AXES
		case 801:
		case 802:
		case 803:
		case 804:
		case 805:
			return true;
		}
		return false;
	}

}