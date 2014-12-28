package server.model.players.content.skills;

import server.Config;
import server.model.items.ItemAssistant;
import server.model.players.Client;
import server.model.players.Player;
import server.util.Misc;
import server.world.Position;

/**
 * 
 * @author sk8rdude461
 * 
 **/

public class Runecrafting {

	public static final int PURE_ESS = 7936;
	public static final int RUNE_ESS = 1436;

	public enum RCInterfaceData {
		AIR(199058, new Position(2845, 4832, 0)),
		MIND(199059, new Position(2788, 4841, 0)),
		WATER(199060, new Position(3489, 3286, 0)),
		EARTH(199061, new Position(2660, 4839, 0)),
		FIRE(199062, new Position(2584, 4836, 0)),
		BODY(199063, new Position(2527, 4833, 0)),
		COSMIC(199064, new Position(2162, 4833, 0)),
		CHAOS(199065, new Position(2269, 4843, 0)),
		ASTRAL(199066, new Position(2157, 3862, 0)),
		NATURE(199067, new Position(2398, 4841, 0)),
		LAW(199068, new Position(2464, 4834, 0)),
		DEATH(199069, new Position(2207, 4836, 0)),
		BLOOD(199070, new Position(2463, 4893, 1));
		
		private int buttonId;
		private Position teleportPos;
		
		RCInterfaceData(int buttonId, Position teleportPos) {
			this.buttonId = buttonId;
			this.teleportPos = teleportPos;
		}

		/**
		 * @return the buttonId
		 */
		public int getButtonId() {
			return buttonId;
		}

		/**
		 * @return the teleportPos
		 */
		public Position getTeleportPos() {
			return teleportPos;
		}
	}
	
	private static RCInterfaceData getInterfaceData(int button) {
		for(RCInterfaceData data : RCInterfaceData.values())
			if(button == data.getButtonId())
				return data;
		return null;
	}
	
	public static boolean handleRCButton(Client c, int button) {
		RCInterfaceData clicked = getInterfaceData(button);
		if(clicked == null)
			return false;
		if(clicked.teleportPos.getX() == -1 || clicked.teleportPos.getY() == -1) {
			c.sendMessage("Sorry, that altar currently doesn't exist...");
			return false;
		}
		c.getPA().removeAllWindows();
		c.getPA().spellTeleport(clicked.teleportPos);
		c.sendMessage("You teleport to the " + Misc.optimizeText(clicked.toString()) + " altar.");
		
		return false;
	}
	
	public enum RCData {
		AIR(2478, 1, 5, false, 556, 10),
		MIND(2479, 2, 6, false, 558, 14),
		WATER(2480, 5, 6, false, 555, 19),
		EARTH(2481, 9, 7, false, 557, 26),
		FIRE(2482, 14, 7, false, 554, 35),
		BODY(2483, 20, 8, false, 559, 46),
		COSMIC(2484, 27, 8, true, 564, 59),
		CHAOS(2487, 35, 9, true, 562, 74),
		ASTRAL(17010, 40, 9, true, 9075, 82),
		NATURE(2486, 44, 9, true, 561, 91),
		LAW(2485, 54, 10, true, 563, -1),
		DEATH(2488, 65, 10, true, 560, -1),
		BLOOD(30624, 77, 11, true, 565, -1);

		private int objectId, requiredLevel, expGain, runeId, divideBy;
		boolean reqPure;

		private RCData(int objectID, int requiredLevel, int expGain,
				boolean reqPure, int runeId, int divideBy) {
			this.objectId = objectID;
			this.requiredLevel = requiredLevel;
			this.expGain = expGain;
			this.reqPure = reqPure;
			this.runeId = runeId;
			this.divideBy = divideBy;
		}

		public int getObjectId() {
			return objectId;
		}

		public int getRequiredLevel() {
			return requiredLevel;
		}

		public int getExpGain() {
			return expGain;
		}

		public boolean needsPureEss() {
			return reqPure;
		}

		public int getRuneId() {
			return runeId;
		}

		public int getDivide() {
			return divideBy;
		}
	}

	private static RCData getRCData(int object) {
		for (final RCData r : RCData.values()) {
			if (r.getObjectId() == object)
				return r;
		}
		return null;
	}

	public static void craftRunes(Client c, int objectId) {
		int runeEss = c.getItems().getItemAmount(RUNE_ESS);
		int pureEss = c.getItems().getItemAmount(PURE_ESS);
		int totalEss = runeEss + pureEss;
		RCData altar = getRCData(objectId);
		if (!validAltar(objectId) || altar == null)
			return;

		int exp = (altar.getExpGain() * totalEss)
				* Config.RUNECRAFTING_EXPERIENCE;
		int amountToCraft = totalEss * getRuneMultiplyer(c, altar.getRuneId());

		if (altar.getRequiredLevel() > c.playerLevel[Player.playerRunecrafting]) {
			c.sendMessage("You need " + altar.getRequiredLevel() + " Runecrafting to craft "
					+ ItemAssistant.getItemName(altar.getRuneId()) + "s.");
			return;
		}

		if (altar.needsPureEss() && pureEss < 1) {
			c.sendMessage("You need Pure Essence to craft runes at this altar.");
			return;
		}

		if (!altar.needsPureEss() && pureEss < 1 && runeEss < 1) {
			c.sendMessage("You need Rune Essence to craft runes at this altar.");
			return;
		}
		c.getItems().deleteItem2(RUNE_ESS, runeEss);
		c.getItems().deleteItem2(PURE_ESS, pureEss);
		c.gfx0(186);
		c.startAnimation(791);
		c.getPA().addSkillXP(exp, Player.playerRunecrafting);
		c.sendMessage("You craft " + amountToCraft + " "
				+ ItemAssistant.getItemName(altar.getRuneId()) + "s.");
		c.getItems().addItem(altar.getRuneId(), amountToCraft);
	}

	public static int getRuneMultiplyer(Client c, int id) {
		for (final RCData r : RCData.values()) {
			if (id == r.getRuneId()) {
				if (r.getDivide() > 0) {
					return (c.playerLevel[Player.playerRunecrafting] / r.getDivide()) + 1;
				}
			}
		}
		return 1;
	}

	public static void handleZMI(Client c) {
		int essAmount = c.getItems().getItemAmount(PURE_ESS);

		int[] possibleRunes = { 554, 555, 556, 557, 558, 559, 560, 561, 562,
				563, 564, 565, 566, 9075 };

		if (essAmount < 1) {
			c.sendMessage("You need Pure Essence to use this altar.");
			return;
		}

		c.gfx0(186);
		c.startAnimation(791, 0);

		int[] runesToGive = new int[essAmount];
		int[] amountToGive = new int[essAmount];
		int[] expToGive = new int[essAmount];

		for (int i = 0; i < 28; i++) {
			if (essAmount > 0 && i <= runesToGive.length - 1) {
				runesToGive[i] = possibleRunes[Misc
						.random(possibleRunes.length - 1)];
				amountToGive[i] = Misc.random(essAmount);
				essAmount -= amountToGive[i];
			} else
				continue;
		}

		for (final RCData r : RCData.values()) {
			for (int q = 0; q < runesToGive.length - 1; q++) {
				if (runesToGive[q] == r.getRuneId())
					expToGive[q] = (((r.getExpGain() * amountToGive[q]) * Config.RUNECRAFTING_EXPERIENCE) * 2);
			}
		}

		for (int j = 0; j < runesToGive.length - 1; j++) {
			c.getItems().deleteItem(PURE_ESS, amountToGive[j]);
			c.getItems().addItem(runesToGive[j], amountToGive[j]);
			c.getPA().addSkillXP(expToGive[j],
					Player.playerRunecrafting);
		}
		c.SaveGame();
	}

	public static boolean validAltar(int objectType) {
		for (final RCData r : RCData.values()) {
			if (r.objectId == objectType)
				return true;
		}
		return false;
	}

}