package server.model.players.content;

import server.Config;
import server.Server;
import server.model.players.Client;

public class SlayerRing {

	public static void handleCharges(Client c, int charge) {
		if (charge == 8) {
			c.getItems().deleteItem(13281, 1);
			c.getItems().addItem(13282, 1);
		}
		if (charge == 7) {
			c.getItems().deleteItem(13282, 1);
			c.getItems().addItem(13283, 1);
		}
		if (charge == 6) {
			c.getItems().deleteItem(13283, 1);
			c.getItems().addItem(13284, 1);
		}
		if (charge == 5) {
			c.getItems().deleteItem(13284, 1);
			c.getItems().addItem(13285, 1);
		}
		if (charge == 4) {
			c.getItems().deleteItem(13285, 1);
			c.getItems().addItem(13286, 1);
		}
		if (charge == 3) {
			c.getItems().deleteItem(13286, 1);
			c.getItems().addItem(13287, 1);
		}
		if (charge == 2) {
			c.getItems().deleteItem(13287, 1);
			c.getItems().addItem(13288, 1);
		}
		if (charge == 1) {
			c.getItems().deleteItem(13288, 1);
		}
		if (charge != 1) {
			c.sendMessage("You use up one of your charges..");
		} else {
			c.sendMessage("Your ring has crumbled to dust.");
		}
	}

	/** Author: Gabbe **/
	public static void handleKillsLeft(Client c) {
		if (c.slayerTask == 0 || c.slayerTask < 0) {
			c.sendMessage("You do not have a Slayer task at the moment!");
			return;
		}
		if (c.slayerTask > 0 && c.taskAmount == 0) {
			c.sendMessage("You've completed your task! Visit the Slayer master!");
			return;
		}
		if (c.taskAmount > 0 && c.slayerTask > 0) {
			c.sendMessage("You need to slay another "
					+ c.taskAmount
					+ " "
					+ Server.npcHandler.getNpcListName(c.slayerTask)
							.replaceAll("_", " ") + "s.");
			return;
		}
	}

	public static void startTeleport(int x, int y, int height,
			String teleportType, int charge, boolean usingRing, Client c) {
		if (c.inRandomEvent() && RandomEvents.canTeleFromRandom == false) {
			c.sendMessage("Please finnish the random event first.");
			return;
		}
		if (Config.SOUND) {
			c.sendSound(c.getSound().TELEPORT);
		}
		if (c.inCwWait) {
			c.sendMessage("To leave, use the portal!");
			return;
		}
		if (c.arenas() || c.inTrade || c.InDung() || c.isBanking) {
			c.sendMessage("You can't teleport right now!");
			return;
		}
		if (c.inBarbDef == true) {
			c.sendMessage("Teleporting will make you loose points! Type ::endgame instead!");
			return;
		}
		if (c.inCwWait) {
			c.sendMessage("To leave, use the portal!");
			return;
		}
		if (c.isNpc == true) {
			c.sendMessage("Type ::return before you can do that!");
			return;
		}
		if (c.isTalkingWithDungMaster == true) {
			c.sendMessage("Please close the interface you have open first.");
			return;
		}
		c.getPA().fixBugs();
		c.dialogueAction = -1;
		c.getPA().resetFishing();
		// c.getMining().resetMining();
		c.isCooking = false;
		c.playerIsFiremaking = false;
		if (c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL
				&& !c.inFunPk()) {
			c.sendMessage("You can't teleport above level "
					+ Config.NO_TELEPORT_WILD_LEVEL + " in the wilderness.");
			return;
		}
		if (c.duelStatus == 5) {
			c.sendMessage("You can't teleport during a duel!");
			return;
		}
		if (c.InDung() || c.InDung2() || c.inDungBossRoom()) {
			c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders or do ::enddung");
			return;
		}
		if (c.inPits || c.viewingOrb || c.getPA().inPitsWait()) {
			c.sendMessage("You can't teleport in here!");
			return;
		}
		if (c.inGWD()) {
			c.getPA().ResetGWKC();
		}
		if (c.inJail() && c.Jail == true) {
			c.sendMessage("You can't teleport out of prison fucking fool!");
			return;
		}
		if (c.Jail == true) {
			c.sendMessage("You can't teleport out of prison fucking goon!");
			return;
		}
		if (c.inWarriorG() && c.heightLevel == 2) {
			c.sendMessage("You can't teleport out of Warrior Guild!");
			return;
		}
		if (c.inRFD()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if (c.inFightCaves()) {
			c.sendMessage("You can't teleport out of this minigame!");
			return;
		}
		if (c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			c.sendMessage("You can't teleport above level "
					+ Config.NO_TELEPORT_WILD_LEVEL + " in the wilderness.");
			return;
		}
		if (System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
			c.sendMessage("You are teleblocked and can't teleport.");
			return;
		}
		if (!c.isDead && c.teleTimer == 0 && c.respawnTimer == -6) {
			if (c.playerIndex > 0 || c.npcIndex > 0) {
				c.getCombat().resetPlayerAttack();
			}
			c.stopMovement();
			c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151
					: c.playerMagicBook == 1 ? 12855
							: c.playerMagicBook == 2 ? 29999 : 12855);
			c.getPA().removeAllWindows();
			c.setAppearanceUpdateRequired(true);
			// c.updatePlayerLook();
			c.teleX = x;
			c.teleY = y;
			c.npcIndex = 0;
			c.playerIndex = 0;
			c.faceUpdate(0);
			c.teleHeight = height;
			c.playerStandIndex = 0x328;
			c.playerTurnIndex = 0x337;
			c.playerWalkIndex = 0x333;
			c.playerTurn180Index = 0x334;
			c.playerTurn90CWIndex = 0x335;
			c.playerTurn90CCWIndex = 0x336;
			c.playerRunIndex = 0x338;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			if (teleportType.equalsIgnoreCase("modern")) {
				c.getTradeAndDuel().declineTrade();
				c.startAnimation(8939);
				c.teleTimer = 9;
				c.gfx0(1576);
				c.teleEndGfx = 1577;
				c.teleEndAnimation = 8941;
			}

		}
		c.updateWalkEntities();
		if (usingRing) {
			handleCharges(c, charge);
			c.sendMessage("You need to slay another "
					+ c.taskAmount
					+ " "
					+ Server.npcHandler.getNpcListName(c.slayerTask)
							.replaceAll("_", " ") + "s.");
			// c.updatePlayerLook();
		}
	}

	public static void teleport(Client c, int charge) {
		if (c.slayerTask == 0 || c.slayerTask < 0) {
			c.sendMessage("You do not have a Slayer task at the moment!");
			return;
		}
		if (c.slayerTask > 0 && c.taskAmount == 0) {
			c.sendMessage("You've completed your task! Visit the Slayer master!");
			return;
		}
		if (c.slayerTask == 119) { // Chaos dwarfs
			startTeleport(2895, 9771, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 117) { // Hill giants
			startTeleport(2701, 9444, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 103) { // Ghosts
			startTeleport(2900, 9849, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 78) { // Bats
			startTeleport(2913, 9839, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 181) { // Chaos druids
			startTeleport(2933, 9848, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 1643) { // Infernal Mages
			startTeleport(3436, 3563, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 1624) { // Dust devils
			startTeleport(2436, 3547, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 1265) { // Rock crabs
			startTeleport(2678, 3716, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 1610) { // Gargoyle
			startTeleport(3441, 3544, 2, "modern", charge, true, c);
		}
		if (c.slayerTask == 1613) { // Nechryael
			startTeleport(3439, 3565, 2, "modern", charge, true, c);
		}
		if (c.slayerTask == 1615) { // abbyssal demons
			startTeleport(3419, 3567, 2, "modern", charge, true, c);
		}
		if (c.slayerTask == 1618) { // Blood velds
			startTeleport(3425, 3565, 1, "modern", charge, true, c);
		}
		if (c.slayerTask == 1612) { // Banshees
			startTeleport(3443, 3544, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 1612) { // Crawling hands
			startTeleport(3438, 3573, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 82) { // lesser demons
			startTeleport(2927, 9802, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 1341) { // Dagganoths
			startTeleport(2476, 10150, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 84) { // Black demons
			startTeleport(2866, 9769, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 2783) { // dark beasts
			startTeleport(2906, 9686, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 1158) { // KALPHITE QUEEN
			startTeleport(2987, 9632, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 49) { // Hellhounds
			startTeleport(2856, 9845, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 55) { // Blue drags
			startTeleport(2896, 9800, 0, "modern", charge, true, c);
			c.sendMessage("It's recommended to wear an Anti-dragon shield when figthing a Dragon!");
		}
		if (c.slayerTask == 50) { // King black drag
			startTeleport(2256, 4680, 0, "modern", charge, true, c);
			c.sendMessage("It's recommended to wear an Anti-dragon shield when figthing a Dragon!");
			c.sendMessage("Run north to fight the mighty King Black Dragon!");
		}
		if (c.slayerTask == 941) { // Green drags WILD
			c.getDH().sendDialogues(321, 1);
		}
		if (c.slayerTask == 9467 || c.slayerTask == 9465
				|| c.slayerTask == 9463) { // Strykewyrms
			startTeleport(2516, 4642, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 53) { // Red drags WILD
			c.getDH().sendDialogues(325, 1);
		}
		if (c.slayerTask == 6260) { // Bandos boss
			c.getDH().sendDialogues(329, 1);
		}
		if (c.slayerTask == 52) { // blue baby drags
			startTeleport(2919, 9803, 0, "modern", charge, true, c);
			c.sendMessage("It's recommended to wear an Anti-dragon shield when figthing a Dragon!");
		}
		if (c.slayerTask == 8349) { // Tormented demons
			startTeleport(2719, 9811, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 10530) { // Forgotten warrior
			startTeleport(2517, 3045, 0, "modern", charge, true, c);
		}
		if (c.slayerTask == 5666) { // barrelschest
			startTeleport(2367, 4956, 0, "modern", charge, true, c);
		}
	}
}
