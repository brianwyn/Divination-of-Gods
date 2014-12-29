package server.model.players.content;

import server.model.players.Client;
import server.world.Position;

/**
 * A single class to handle all custom teleport interfaces Because Gabbe cannot
 * fucking program.
 * 
 * @author T H C
 * 
 */
public class CustomInterfaces {

	public static void openSkillInterface(Client player) {
		player.getPA().showInterface(33000);
		player.getPA().sendFrame126(
				"@whi@Total Level: " + player.getPA().getTotalLevel() + "",
				33041);
	}

	public static void openMinigameInterface(Client player) {

		player.getPA().showInterface(42000);
	}

	public static boolean clickButton(Client player, int clicked) {
		Teleport teleportButton = getTeleport(clicked);
		OpenInterface interfaceButton = getInterface(clicked);
		if (interfaceButton != null) {
			player.getPA().showInterface(interfaceButton.getNewInterface());
			player.sendMessage("You open the "
					+ interfaceButton.toString().toLowerCase() + " interface.");
			return true;
		}
		if (teleportButton != null) {
			if(teleportButton.getTeleportTo().getX() < 0 || teleportButton.getTeleportTo().getY() < 0 ) {
				player.sendMessage("<col=11141120>This feature is coming soon!");
				return true;
			}
			player.getPA().spellTeleport(teleportButton.getTeleportTo());
			player.sendMessage("You teleport to the "
					+ teleportButton.toString().toLowerCase() + " area.");
			return true;
		}
		return false;
	}

	private static Teleport getTeleport(int button) {
		for (Teleport t : Teleport.values())
			if (button == t.getButtonId())
				return t;
		return null;
	}

	private static OpenInterface getInterface(int button) {
		for (OpenInterface o : OpenInterface.values())
			if (button == o.getButtonId())
				return o;
		return null;
	}

	/**
	 * Contains all the data for buttons that require teleporting.
	 */
	enum Teleport {
		MINING(128241, new Position(3022, 9739, 0)),
		WOODCUTTING(128245, new Position(2664, 3349, 0)),
		AGILITY(128253, new Position(2480, 3437, 0)),
		FLETCHING(129003, new Position(2708, 3463, 0)),
		THIEVING(129013, new Position(2661, 3309, 0)),
		SMITHING(128243, new Position(2972, 3373, 0)),
		FIREMAKING(128247, new Position(2905, 3463, 0)),
		HERBLORE(128251, new Position(2816, 3460, 0)),
		SLAYER(129015, new Position(3162, 3476, 0)),
		CONSTRUCTION(129001, new Position(2736, 3502, 0)),
		FISHING(128237, new Position(2604, 3408, 0)),
		CRAFTING(129005, new Position(2739, 3446, 0)),
		FARMING(128249, new Position(2816, 3460, 0)),
		HUNTER(128255, new Position(2591, 4318, 0)),
		SUMMONING(129007, new Position(2208, 5347, 0)),
		DUNGEONEERING(129011, new Position(2419, 3528, 0)),
		COOKING(128239, new Position(2613, 3398, 0)),
		DUEL_ARENA(164021, new Position(3358, 3270, 0)),
		BARROWS(164022, new Position(3565, 3314, 0)),
		WARRIORS_GUILD(164023, new Position(2865, 3546, 0)),
		PEST_CONTROL(164024, new Position(2662, 2650, 0)),
		FIGHT_CAVES(164025, new Position(2438, 5172, 0)),
		FIST_OF_GUTHIX(164026, new Position(-1, -1, -1)),
		CASTLE_WARS(164027, new Position(-1, -1, -1)),
		BARB_ASSAULT(164028, new Position(1895, 5408, 0)),
		DOMINION_TOWER(164029, new Position(3222, 3222, 0)),
		RFD(164030, new Position(3217, 9621, 0)),
		NOMAD(164031, new Position(2967, 3206, 0)),
		;

		private int buttonId;
		private Position teleportTo;

		Teleport(int buttonId, Position teleportTo) {
			this.buttonId = buttonId;
			this.teleportTo = teleportTo;
		}

		/**
		 * @return the buttonId
		 */
		public int getButtonId() {
			return buttonId;
		}

		/**
		 * @return the teleportTo
		 */
		public Position getTeleportTo() {
			return teleportTo;
		}
	}

	/**
	 * Contains all the data for buttons that opens a new interface.
	 */
	enum OpenInterface {
		RUNECRAFTING(129009, 51000);

		private int buttonId, newInterface;

		OpenInterface(int buttonId, int newInterface) {
			this.buttonId = buttonId;
			this.newInterface = newInterface;
		}

		/**
		 * @return the buttonId
		 */
		public int getButtonId() {
			return buttonId;
		}

		/**
		 * @return the newInterface
		 */
		public int getNewInterface() {
			return newInterface;
		}
	}

}
