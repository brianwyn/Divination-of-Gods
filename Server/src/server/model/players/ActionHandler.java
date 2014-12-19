package server.model.players;

import server.Config;
import server.Server;
import server.model.npcs.NPCHandler;
import server.model.objects.Object;
import server.model.players.content.CrystalChest;
import server.model.players.content.skills.Agility;
import server.model.players.content.skills.ConstructionEvents;
import server.model.players.content.skills.Firemaking;
import server.model.players.content.skills.Woodcutting;
import server.model.players.content.skills.impl.Cons;
import server.model.players.content.skills.impl.ConstructionObjects.Bank;
import server.model.players.content.skills.impl.ConstructionObjects.Bookcases;
import server.model.players.content.skills.impl.ConstructionObjects.Fireplaces;
import server.model.players.content.skills.impl.ConstructionObjects.Lecterns;
import server.model.players.content.skills.impl.ConstructionObjects.Telescopes;
import server.model.shops.ShopAssistant;
import server.util.Misc;
import server.util.ScriptManager;

public class ActionHandler {

	private Client c;
	int[] donatorRitem = { 12435, 4151, 15441, 15442, 15443, 15444, 15241,
			4753, 4755, 4757, 4759, 14595, 14603, 14602, 14605, 11235, 19308,
			19311, 19314, 19317, 19320, 12435, 4151, 15441, 15442, 15443,
			15444, 15241, 4753, 4755, 4757, 4759, 14595, 14603, 14602, 14605,
			11235, 19308, 19311, 19314, 19317, 19320, 962 };

	int[] PvpItems = { 14876, 14877, 14878, 14879, 14880, 14881, 14882, 14883,
			14884, 14885, 14886, 14888, 14889, 14890, 14891, 14892 };

	int[] PvpPrices = { 10000000, 1000000, 500000, 35000, 800000, 150000,
			280000, 840000, 150000, 125000, 80000, 5000000, 240000, 108700,
			200000, 284000 };

	public ActionHandler(Client Client) {
		this.c = Client;
	}

	public int donatorRitem() {
		return donatorRitem[(int) (Math.random() * donatorRitem.length)];
	}

	public void firstClickNpc(int npcType) {
		int index = c.npcClickIndex;
		c.fishitem = -1;
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		if (c.playerName.equalsIgnoreCase("Gabbe")) {
			c.sendMessage("Npc Id: " + npcType);
		}
		if (c.fishitem != -1) {
			if (!c.getItems().playerHasItem(c.fishitem)) {
				c.sendMessage("You need a "
						+ c.getItems().getItemName(c.fishitem)
						+ " to fish for " + c.getItems().getItemName(c.fishies));
				c.fishing = false;
				return;
			}
			if (c.getItems().freeSlots() == 0) {
				c.sendMessage("Your inventory is full.");
				c.fishing = false;
				return;
			}
			if (Player.playerFishing < c.fishreqt) {
				c.sendMessage("You need a fishing level of " + c.fishreqt
						+ " to fish here.");
				c.fishing = false;
				return;
			}
			c.fishtimer = c.getFishing().fishtime(c.fishies, c.fishreqt);
		}
		switch (npcType) {

		case 949:
			if (c.getPlayerGod() < 0)
				c.getDH().sendDialogues(1125, npcType);
			else
				c.sendMessage("You already worship " + c.getGod());
			break;
		case 1777:
			c.getDH().sendOption2("Sell Artifacts", "View Pking shop");
			c.teleAction = 428;
			break;
		case 8546:
			c.getPA().movePlayer(2791, 9329, 0);
			c.sendMessage("You teleport into a blazing fire and the Unholy Cursebearer is at it again!");
			c.sendMessage("He has put 4 balance elemental under his control and they have taken this land!");
			c.sendMessage("Fight back for this land for some great loot and slayer points!");
			c.sendMessage("Click on the blazing fire to summon them!");
			break;
		case 4241:
			if (c.inConstruction()) {
				if (c.payButler()) {
					c.getDH().sendDialogues(4249, 1);
					c.SaveGame();
					return;
				}
				c.getDH().sendOption2("Fetch planks", "Fetch supplies");
				c.teleAction = 4284;
			}
			break;
		case 166:
			c.getPA().openUpBank(0);
			break;
		case 42:
			c.teleAction = 444;
			c.getDH()
					.sendOption2("Tell me about tasks", "Show me your rewards");
			break;
		case 216:
			c.getDH().sendDialogues(216, 1);
			break;
		case 11226:
			c.getShops().openShop(53);
			break;
		case 590:
			c.sendMessage("You now have a total of " + c.funPoints
					+ " Funpoints points!");
			c.getShops().openShop(21);
			break;
		case 3788:
		case 3789:
			c.getPA().closeAllWindows();
			c.getPA().showInterface(18700);
			c.getPA().sendFrame126(Integer.toString(c.Commendations), 18729);
			break;
		case 6574:
			c.getPA().showInterface(6965);
			c.getPA().sendFrame126("Gravestone", 6968);
			c.getPA().sendFrame126("", 6969);
			c.getPA().sendFrame126(
					"In memory of " + Misc.optimizeText(c.theDeadGuy) + "...",
					6970);
			c.getPA().sendFrame126("Hope they have a great time in heaven!",
					6971);
			c.getPA().sendFrame126("", 6972);
			c.getPA().sendFrame126("", 6973);
			c.getPA().sendFrame126("", 6974);
			c.getPA().sendFrame126("", 6975);
			c.getPA().sendFrame126("", 6976);
			c.getPA().sendFrame126("", 6977);
			c.getPA().sendFrame126("", 6978);
			c.getPA().sendFrame126("", 6979);
			c.getPA().sendFrame126("", 6980);
			break;
		case 4995:
			c.getDH().sendDialogues(4995, 1);
			break;
		// easter event
		case 7197: // bunny
			if (c.hasHeardEasterDialogue == 0) {
				c.getDH().sendDialogues(6666, 1);
			} else {
				if (c.hasHeardEasterDialogue == 1
						|| c.hasHeardEasterDialogue == 2) {
					c.sendMessage("Go find my egg first, search in lumbridge!");
				}
				if (c.hasHeardEasterDialogue == 3
						&& c.getItems().playerHasItem(7928, 1)) {
					c.getDH().sendDialogues(2247, 1);
				}
				if (c.hasHeardEasterDialogue == 4) {
					c.sendMessage("You've alredy talked to the bunny!");
				}
			}
			break;

		case 7936: // cook
			if (c.hasHeardEasterDialogue == 4) {
				c.sendMessage("You've alredy talked to the cook!");
			}
			if (c.hasHeardEasterDialogue == 1) {
				c.getDH().sendDialogues(2228, 1);
			} else {
				if (c.hasHeardEasterDialogue == 2) {
					c.getDH().sendDialogues(2232, 1);
				} else {
					if (c.hasHeardEasterDialogue == 3
							&& c.getItems().playerHasItem(7928, 1)) {
						c.sendMessage("You have the egg, give it to the bunny!");
					}
				}
			}
			break;
		case 586:
			c.getShops().openShop(32);
			break;
		case 588:
			c.getShops().openShop(58);
			break;
		case 660:
			c.getDH().sendOption2("PKP Shop", "Voting Points Shop");
			c.dialogueAction = 2001;
			break;
		case 402:
			c.getShops().openShop(34);
			break;
		case 882:
			c.getShops().openShop(47);
			break;
		case 7121:
			c.getDH().sendOption4("Completeionist cape", "Veteran Cape",
					"Max Cape", "Milestone capes");
			c.teleAction = 535;
			break;
		case 223:
			if (c.playerLevel[3] > 30 && c.playerLevel[5] > 50) {
				c.getDH().sendDialogues(3333, 1);
			} else {
				if (c.playerLevel[3] < 30 && c.playerLevel[5] < 50) {
					c.getDH().sendDialogues(3332, 1);
				}
			}
			break;
		case 2617:
			c.getShops().openShop(57);
			break;
		case 3236:// Construction shop
			c.getShops().openShop(98);
			c.sendMessage("If the shop runs out of Nails, You will have to smith them you'reself!");
			break;
		case 4247:
			if (c.hasHousee == false) {
				c.getDH().sendDialogues(29164, npcType);
			}
			if (c.hasHousee == true) {
				c.getDH().sendDialogues(29170, npcType);
			}
			break;
		case 794:
			c.getDH().sendDialogues(2196, npcType);
			break;
		case 219:
			c.getDH().sendDialogues(1196, npcType);
			break;
		case 3920:
			c.getDH().sendDialogues(1185, npcType);
			break;
		case 4295:
			c.getDH().sendDialogues(1175, npcType);
			break;
		case 1167:
			c.getDH().sendDialogues(1155, npcType);
			break;
		case 553:
			c.getPA().showInterface(27000);
			break;
		case 7143: // firemaker
			c.getDH().sendDialogues(1137, npcType);
			break;
		case 4906: // woodcutter
			c.getDH().sendDialogues(1112, npcType);
			break;
		case 569: // crafter
			c.getDH().sendDialogues(1111, npcType);
			break;
		case 5559: // osman
			c.getShops().openShop(100);
			c.sendMessage("You currently have " + c.barbPoints
					+ " Assault Points.");
			break;
		case 9716: // barb assault
			c.getDH().sendDialogues(1054, npcType);
			break;
		case 6163:
			c.getPA().movePlayer(2872, 5269, 2);
			break;
		case 6165:
			c.getPA().movePlayer(2872, 5279, 2);
			break;
		case 33:
			c.getDH().sendDialogues(119, 1);
			c.sendMessage("To delete your PIN type ::delete ");
			break;
		case 1552: // SANTA
			if (c.getItems().freeSlots() < 4) {
				c.sendMessage("You need atleast 4 free inventory slots to talk to Santa!");
				return;
			}
			if (c.santaPrize == 1) {
				c.sendMessage("You've alredy received a gift from santa!");
				return;
			}
			if (c.santaPrize == 0) {
				c.getDH().sendDialogues(1552, npcType);
				c.getItems().addItem(6199, 1);
				c.getItems().addItem(3062, 1);
				c.getItems().addItem(10025, 1);
				c.santaPrize = 1;
			}
			break;
		case 9713:
			if (c.playerXP[24] >= 104273167) {
				c.getShops().openShop(67);
				c.sendMessage("Wow! Your 120 Dungeoneering so you may now acces the best-looking cape!");
			}
			break;

		case 4707:
			c.getShops().openShop(2);
			break;

		case 30:
			c.getShops().openShop(15);
			break;

		case 1861:
			c.sendMessage("" + c.dialogueAction + "");
			c.dialogueAction = 894;
			c.getDH().sendOption2("Weapons & Armour", "Ammunition");
			c.dialogueAction = 893;
			break;

		case 705:
			c.sendMessage("" + c.dialogueAction + "");
			// c.sendMessage("To be able to wear Rune Platebody's, finnish Dragon Slayer! Talk to Kazgar");
			c.dialogueAction = 524;
			c.getDH().sendOption2("Armours", "Weapons");
			c.dialogueAction = 524;
			c.getShops().openShop(5);
			break;

		case 6390:
			if (c.grimPrize == 0) {
				c.getDH().sendDialogues(90, npcType);
			} else if (c.grimPrize == 2) {
				c.sendMessage("You've alredy completed this Minigame..");
			} else if (c.grimPrize == 1) {
				if (c.getItems().freeSlots() < 7) {
					c.sendMessage("You need atleast 7 free inventory slots to receive reward");
					return;
				}
				if (c.getItems().playerHasItem(317, 1)
						&& c.getItems().playerHasItem(11256, 1)
						&& c.getItems().playerHasItem(1513, 1)
						&& c.getItems().playerHasItem(1727, 1)
						&& c.getItems().playerHasItem(451, 3)) {
					c.getDH().sendDialogues(104, npcType);
				}
				c.sendMessage("You don't have the items.. You need 1 Raw Shrimp, 1 Dragon Impling Jar");
				c.sendMessage("1 Magic log, 1 Amulet of magic and 3 Runite Ores.");
			}
			break;

		case 3299:
			c.getDH().sendDialogues(1115, npcType);
			break;
		case 2244:
			c.getPA().showInterface(26099);
			c.getPA().sendFrame200(26101, 9847);// chatid
			c.getPA().sendFrame185(26101);
			if (c.KC > c.DC) {
				c.getPA().sendFrame126("@or1@Kills: @gre@" + c.KC + " ", 26105);
				c.getPA().sendFrame126("@or1@Deaths: @red@" + c.DC + "", 26106);
			}
			if (c.KC < c.DC) {
				c.getPA().sendFrame126("@or1@Kills: @red@" + c.KC + "", 26105);
				c.getPA().sendFrame126("@or1@Deaths: @gre@" + c.DC + "", 26106);
			}
			c.getPA().sendFrame126("@or1@Name: @gre@" + c.playerName + "",
					26107);
			c.getPA().sendFrame126(
					"@or1@Combat Level: @gre@" + c.combatLevel + "", 26108);
			if (c.playerRights == 1) {
				c.getPA().sendFrame126("@or1@Rank: @gre@Moderator", 26109);
			}
			if (c.playerRights == 2) {
				c.getPA().sendFrame126("@or1@Rank: @gre@Web Designer", 26109);
			}
			if (c.playerRights == 3) {
				c.getPA().sendFrame126("@or1@Rank: @gre@Owner", 26109);
			}
			if (c.playerRights == 0) {
				c.getPA().sendFrame126("@or1@Rank: @gre@Player", 26109);
			}
			if (c.playerRights == 4) {
				c.getPA().sendFrame126("@or1@Rank: @gre@Donator", 26109);
			}
			c.getPA().sendFrame126(
					"@or1@Dung Tokens: @gre@" + c.dungPoints + " ", 26111);
			c.getPA().sendFrame126("@or1@Pk Points: @gre@" + c.pkPoints + "  ",
					26113);
			c.getPA().sendFrame126(
					"@or1@Slayer Points: @gre@" + c.SPoints + "  ", 26115);
			c.getPA().sendFrame126(
					"@or1@PC Points: @gre@" + c.Commendations + "  ", 26116);
			c.getPA().sendFrame126("@or1@----: @gre@-", 26117);

			c.getPA().sendFrame126("@or1@Gambles Won: @gre@-", 26118);
			c.getPA().sendFrame126("@or1@Gambles Lost: @gre@-", 26119);
			c.getPA().sendFrame126("@or1@Battles Won: @gre@-", 26120);
			c.getPA().sendFrame126("@or1@Battles Lost: @gre@-", 26121);
			c.getPA().sendFrame126("@or1@NPC Kills: @gre@-", 26122);
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			break;
		case 8591:
			c.getDH().sendDialogues(8591, npcType);
			break;
		case 0:
			c.getDH().sendDialogues(3663, npcType);
			break;
		case 1283:
			c.getDH().sendDialogues(1283, npcType);
			break;
		case 209:
			// if(c.bMQ >= 3) {
			c.getDH().sendDialogues(209, npcType);
			/*
			 * } else { c.sendMessage(
			 * "You need to complete the Herblore quest - talk to Kaqemeex!"); }
			 */
			break;
		case 875:
			if (c.absX > 2816 && c.absX < 2880 && c.absY > 2944
					&& c.absY < 3007) {
				c.getShops().openPlayerShop(c);
			} else {
				c.sendMessage("You can only view your shops in home.");
			}
			break;
		case 2127:
			if (c.playerCollect > 0) {
				c.sendMessage("You succesfully collected " + c.playerCollect
						+ " coins.");
				c.getItems().addItem(995, c.playerCollect);
				c.playerCollect = 0;
			} else {
				c.sendMessage("You dont have anything to collect");
			}
			break;

		case 706:
			c.getDH().sendDialogues(9, npcType);
			break;
		case 7601:
			c.getDH().sendDialogues(70, 4289);
			break;
		case 6794:
		case 6873:
			c.getDH().sendDialogues(75, 4289);
			break;
		case 946:
			c.getDH().sendDialogues(20, npcType);
			break;

		case 1113:
			c.getPA().movePlayer(3448, 3517, 0);
			c.sendMessage("You have teleported to the summmoning shops!");
			break;
		case 6970:
			c.getShops().openShop(12);
			break;
		case 6971:
			c.getShops().openShop(13);
			break;
		case 455:
			c.getDH().sendDialogues(1130, npcType);
			break;
		case 316:
			c.fishing = true;
			c.fishXP = 1000;
			c.fishies = 317;
			c.fishreqt = 0;
			c.fishitem = 303;
			c.fishemote = 621;
			c.fishies2 = 0;
			c.fishreq2 = 0;
			break;
		case 312:
			c.fishing = true;
			c.fishXP = 1000;
			c.fishies = 377;
			c.fishreqt = 40;
			c.fishitem = 301;
			c.fishemote = 619;
			break;
		case 334:
			c.fishing = true;
			c.fishXP = 1000;
			c.fishies = 317;
			c.fishreqt = 0;
			c.fishitem = 303;
			c.fishemote = 621;
			c.fishies2 = 0;
			c.fishreq2 = 0;
			break;
		case 324:// cage-harpoon spot choice cage
			c.fishing = true;
			c.fishXP = 7000;
			c.fishies = 377;
			c.fishreqt = 40;
			c.fishitem = 301;
			c.fishemote = 619;
			c.fishies2 = 389;
			c.fishreq2 = 81;
			break;
		case 325:
			c.fishing = true;
			c.fishXP = 20000;
			c.fishies = 15270;
			c.fishreqt = 99;
			c.fishitem = 301;
			c.fishemote = 619;
			c.fishies2 = 15272;
			c.fishreq2 = 99;
			break;
		case 8647:
			c.fishing = true;
			c.fishXP = 10;
			c.fishies = 17797;
			c.fishreqt = 80;
			c.fishitem = 301;
			c.fishemote = 619;
			c.fishies2 = 17797;
			c.fishreq2 = 80;
			break;
		case 320:
			c.fishing = true;
			c.fishXP = 20000;
			c.fishies = 15270;
			c.fishreqt = 99;
			c.fishitem = 301;
			c.fishemote = 619;
			c.fishies2 = 15272;
			c.fishreq2 = 95;
			break;
		case 326:
			c.fishing = true;
			c.fishXP = 2000;
			c.fishies = 341;
			c.fishreqt = 23;
			c.fishitem = 303;
			c.fishemote = 621;
			c.fishies2 = 363;
			c.fishreq2 = 46;
			break;
		case 313:
			c.fishing = true;
			c.fishXP = 2000;
			c.fishies = 341;
			c.fishreqt = 23;
			c.fishitem = 303;
			c.fishemote = 621;
			c.fishies2 = 363;
			c.fishreq2 = 46;
			break;
		case 3100:
			c.getPA().movePlayer(2717, 9801, 0);
			c.sendMessage("Goodluck killing the creatures from hell!");
			break;
		case 4289:
			c.kamfreenaDone = true;
			c.getDH().sendDialogues(47, 4289);
			break;
		case 1061:
			c.inCyclops = true;
			c.getWarriorsGuild().handleKamfreena(c, true);
			break;
		case 1062:
			c.kamfreenaDone = false;
			c.inCyclops = false;
			c.getWarriorsGuild().handleKamfreena(c, true);
			break;
		case 2258:
			c.getDH().sendDialogues(17, npcType);
			break;
		case 2261:
			c.getPA().walkableInterface(-1);
			c.getPA().movePlayer(2885, 5330, 2);
			break;

		case 2259:
			if (c.absX == 2885 && c.absY == 5345 && c.heightLevel == 2) {
				c.getPA().movePlayer(2885, 5332, 2);
			} else {
				c.getPA().movePlayer(2885, 5345, 2);
				c.getPA().walkableInterface(12418);
				c.sendMessage("You have entered Nex's Room, To leave talk to me on the other side.");
			}
			break;

		case 398:
			c.getPA().movePlayer(2918, 5273, 0);
			c.sendMessage("You have entered Saradomin, To leave talk to me on the other side.");
			break;
		case 399:
			c.getPA().movePlayer(2911, 5299, 2);
			break;
		case 1064:
			c.getPA().movePlayer(2852, 5333, 2);
			break;

		case 1063:
			c.getPA().movePlayer(2849, 5333, 2);
			c.sendMessage("You have entered Bandos, To leave talk to me on the other side.");
			break;

		case 70:
			c.getPA().movePlayer(2872, 5269, 2);
			c.sendMessage("You have entered Armadyl, To leave click the Pillar.");
			c.sendMessage("Note: Ruby bolts (e) and Diamond bolts (e) are recommended!");
			break;
		case 8275:
		case 9085:
		case 1597:
			c.sendMessage("You now have a total of " + c.slayerPoints
					+ " Slayer points!");
			c.dialogueAction = 0;
			c.teleAction = 0;
			if (c.slayerTask <= 0 || c.slayerTask == -1 || c.slayerTask <= -1
					|| c.taskAmount <= 0 || c.taskAmount == -1) {
				c.getDH().sendDialogues(11, npcType);
			} else {
				c.getDH().sendDialogues(13, npcType);
			}
			break;
		case 500:
			if (c.monkeyk0ed >= 20) {
				c.getDH().sendDialogues(30, npcType);
			} else {
				c.getDH().sendDialogues(32, npcType);
			}
			break;
		case 793:
			c.getShops().openShop(83);
			// c.getPA().showInterface(18070);
			// c.getPA().sendFrame126(""+c.dungPoints+"", 18071);
			// c.sendMessage("You currently have <col=255>" + c.dungPoints +
			// "</col> Dungeoneering Tokens.");
			break;
		case 9711:
			c.getShops().openShop(85);
			c.sendMessage("You currently have " + c.dungPoints
					+ " Dungeoneering Tokens.");
			break;
		case 540:
			c.getDH().sendDialogues(540, npcType);
			break;

		case 545:
			c.getDH().sendDialogues(545, npcType);
			break;
		case 211:
			c.getDH().sendDialogues(211, npcType);
			break;
		case 692:
			c.getShops().openShop(90);
			break;
		case 286:
			c.getShops().openShop(92);
			break;
		case 9400:
			c.getShops().openShop(93);
			break;
		case 919:
			c.getShops().openShop(10);
			break;
		case 1699:
			c.getShops().openShop(82);
			c.sendMessage("You currently have <col=255>" + c.bossPoints
					+ "</col> Boss Points.");
			break;

		case 3381:
			c.getShops().openShop(76);
			break;
		case 2830:
			c.getShops().openShop(73);
			c.sendMessage("You currently have <col=255>" + c.pkPoints
					+ "</col> InSid-P.");
			break;
		case 5030:
			c.getShops().openShop(74);
			c.sendMessage("You currently have <col=255>" + c.pkPoints
					+ "</col> InSid-P.");
			break;
		case 1294:
			c.getShops().openShop(72);
			break;
		case 5839:
			c.getShops().openShop(75);
			break;

		case 1778:
			c.getShops().openShop(71);
			break;
		case 1779:
			c.getShops().openShop(67);
			break;
		case 554:
			c.getShops().openShop(68);
			break;
		case 520:
			c.getShops().openShop(69);
			break;
		case 542:
			c.getShops().openShop(9);
			break;
		case 541:
			c.getShops().openShop(5);
			break;
		case 4290:
			c.getShops().openShop(66);
			break;

		case 461:
			c.getShops().openShop(2);
			break;

		case 683:
			c.getShops().openShop(3);
			break;

		case 84:
			c.getShops().openShop(3);
			break;

		case 595:
			c.getShops().openShop(98);
			break;

		// hunter

		// implin's
		case 6055:
			c.CatchimpNpc(index, 6055, 11238, 1500, 1, c.playerId);
			break;
		case 6056:
			c.CatchimpNpc(index, 6056, 11240, 3500, 17, c.playerId);
			break;
		case 6057:
			c.CatchimpNpc(index, 6057, 11242, 4000, 20, c.playerId);
			break;
		case 6058:
			c.CatchimpNpc(index, 6058, 11244, 5000, 34, c.playerId);
			break;
		case 6059:
			c.CatchimpNpc(index, 6059, 11246, 6000, 40, c.playerId);
			break;
		case 6060:
			c.CatchimpNpc(index, 6060, 11248, 8000, 50, c.playerId);
			break;
		case 6061:
			c.CatchimpNpc(index, 6061, 11250, 10000, 58, c.playerId);
			break;
		case 6062:
			c.CatchimpNpc(index, 6062, 11252, 12500, 65, c.playerId);
			break;
		case 6063:
			c.CatchimpNpc(index, 6063, 11254, 14000, 74, c.playerId);
			break;
		case 6064:
			c.CatchimpNpc(index, 6064, 11256, 25000, 90, c.playerId);
			break;
		case 7903:
			c.CatchimpNpc(index, 7903, 15517, 70000, 96, c.playerId);
			break;

		// end of implin's!

		// butterfly's
		case 5082:
			c.CatchHunterNpc("Black Warlock", 10010, 5082, 10014, 18000, 85,
					c.playerId);
			break;
		case 5083:
			c.CatchHunterNpc("Snowy Knight", 10010, 5083, 10016, 15000, 75,
					c.playerId);
			break;
		case 5084:
			c.CatchHunterNpc("Sapphire Glacialis", 10010, 5084, 10018, 7500,
					45, c.playerId);
			break;
		case 5085:
			c.CatchHunterNpc("Ruby Harvest", 10010, 5085, 10020, 5000, 30,
					c.playerId);
			break;
		// end of butterfly's

		// end of hunter :)
		case 2538:
			c.getShops().openShop(6);
			break;

		case 519:
			c.getShops().openShop(8);
			break;
		case 1282:
			c.getShops().openShop(7);
			break;
		case 1152:
			c.getDH().sendDialogues(16, npcType);
			break;
		case 5580:
			c.getDH().sendDialogues(70, npcType);
			break;
		case 494:
		case 496:
			/*
			 * c.getDH().sendOption2("Grand Exchange", "Open Bank");
			 * c.dialogueAction = 13813;
			 */
			c.getPA().openUpBank(0);
			break;
		case 2566:
			c.getShops().openSkillCape();
			break;
		case 905:
			if (c.mageBankMinigameDone == false) {
				c.getDH().sendDialogues(5, npcType);
			} else {
				c.getDH().sendDialogues(1414, npcType);
			}
			break;
		case 460:
			c.getDH().sendDialogues(3, npcType);
			break;
		case 6138:
			c.getDH().sendDialogues(200, npcType);
			break;
		case 522:
		case 523:
			c.getShops().openShop(1);
			break;
		case 599:
			c.getPA().showInterface(3559);
			c.canChangeAppearance = true;
			break;
		case 904:
			c.sendMessage("You have " + c.magePoints + " points.");
			break;
		default:
			ScriptManager.callFunc("npcClick1_" + npcType, c, npcType);
			if (c.playerRights == 3)
				Misc.println("First Click Npc : " + npcType);
			break;

		}
	}

	@SuppressWarnings("static-access")
	public void firstClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;

		c.turnPlayerTo(obX, obY);
		// c.sendMessage("Object type: " + objectType);
		if (Woodcutting.playerTrees(c, objectType)) {
			Woodcutting.attemptData(c, objectType, obX, obY);
			return;
		}
		switch (objectType) {
		case 25819:
			if (c.absY == 3502) {
				if (c.absX == 2740)
					c.getPA().movePlayer(2739, c.absY, c.heightLevel);
				if (c.absX == 2739)
					c.getPA().movePlayer(2740, c.absY, c.heightLevel);
			}
			break;
		case 13656:
			Telescopes.handlePanicInterface(c);
			break;
		case 13644:
		case 13643:
		case 13642:
			Lecterns.handleAction(c, c.constructionUpgrades[9]);
			break;
		case 13609:
		case 13611:
		case 13613:
			Fireplaces.lightFireplace(c);
			break;
		case 13598:
		case 13597:
		case 13599:
			Bookcases.searchBookcase(c);
			break;

		case 3742:
			c.getDH().sendDialogues(3742, 1);
			break;

		case 28714:
			c.getPA().startTeleport(3211, 3422, 0, "modern");
			break;
		case 1151:
			c.getThieving().stealFromStall(5561, 250, 99); // SAMY'S ::SZONE
															// FAIRY STALL!
			break;
		case 29261:
			if (c.absX == 3246) {
				c.getPA().movePlayer(3247, c.absY, 0);
			}
			if (c.absX == 3247) {
				c.getPA().movePlayer(3246, c.absY, 0);
			}
			break;
		// fog
		case 30143:
			if (c.isAttacker && c.inFoGGame) {
				c.sendMessage("You're the attacker! Not the carrier!");
				return;
			}

			break;

		case 30141:
			if (c.isAttacker) {
				c.sendMessage("The attacker can't enter this house!");
				return;
			}
			// NORTH EAST HOUSE
			// SOUTH DOOR
			if (c.absX == 1667 || c.absX == 1668 || c.absX == 1666) {
				if (c.absY == 5702 || c.absY == 5701) {
					c.getPA().movePlayer(1667, 5703, c.playerId * 4);
					c.sendMessage("When inside this house, you take damage and receive no charges.");
					c.inFoGHouse = true;
				} else if (c.absY == 5703 || c.absY == 5701) {
					c.getPA().movePlayer(1667, 5702, 0);
					c.sendMessage("You leave the house and feel normal. You receive charges.");
					c.inFoGHouse = false;
				}
			}

			// WEST DOOR
			if (c.absX == 1665 && c.absY == 5704) {
				c.getPA().movePlayer(1666, 5704, c.playerId * 4);
				c.sendMessage("When inside this house, you take damage and receive no charges.");
				c.inFoGHouse = true;
			} else if (c.absX == 1666 && c.absY == 5704) {
				c.getPA().movePlayer(1665, 5704, 0);
				c.sendMessage("You leave the house and feel normal. You receive charges.");
				c.inFoGHouse = false;

			}

			// NORTH DOOR
			if (c.absX == 1667 && c.absY == 5706) {
				c.getPA().movePlayer(1667, 5705, c.playerId * 4);
				c.sendMessage("When inside this house, you take damage and receive no charges.");
				c.inFoGHouse = true;
			} else if (c.absX == 1667 && c.absY == 5705) {
				c.getPA().movePlayer(1667, 5706, 0);
				c.sendMessage("You leave the house and feel normal. You receive charges.");
				c.inFoGHouse = false;
			}

			// EAST DOOR
			if (c.absX == 1669 && c.absY == 5704) {
				c.getPA().movePlayer(1668, 5704, c.playerId * 4);
				c.sendMessage("When inside this house, you take damage and receive no charges.");
				c.inFoGHouse = true;
			} else if (c.absX == 1668 && c.absY == 5704) {
				c.getPA().movePlayer(1669, 5704, 0);
				c.sendMessage("You leave the house and feel normal. You receive charges.");
				c.inFoGHouse = false;
			}

			break;
		case 30224:
			break;
		case 15478:
			if (c.hasHousee == true) {
				if (c.playerLevel[23] == 1) {
					c.sendMessage("Start of by building chairs!");
					c.getPA().teleTabTeleport(2058, 3146, c.playerId * 4,
							"house");
					return;
				}
				c.getPA().teleTabTeleport(2059, 3143, c.playerId * 4, "house");
				c.getPA().closeAllWindows();
				return;
			} else {
				if (c.hasHousee == false) {
					c.getDH().sendDialogues(29164, 0);
					return;
				}
			}
			break;

		case 4483:
		case 24914:
		case 25808:
			c.getPA().openUpBank(0);
			break;

		case 172:
			CrystalChest.searchChest(c, objectType, c.objectX, c.objectY);
			break;
		case 2091:
		case 11938:
		case 11937:
		case 11936:
			c.getMining().startMining(0, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2095:
		case 11933:
			c.getMining().startMining(1, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2093:
		case 11954:
			c.getMining().startMining(2, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2097:
		case 11963:
		case 11964:
		case 11965:
		case 11932:
		case 11930:
		case 11931:
			c.getMining().startMining(3, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2103:
		case 11942:
		case 11944:
		case 11943:
			c.getMining().startMining(4, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2105:
		case 11941:
		case 11939:
			c.getMining().startMining(5, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2107:
			c.getMining().startMining(6, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2090:
			c.getMining().startMining(7, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2094:
			c.getMining().startMining(8, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2092:
			c.getMining().startMining(9, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2096:
			c.getMining().startMining(10, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2102:
		case 11945:
		case 11946:
		case 11947:

			c.getMining().startMining(11, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2104:
			c.getMining().startMining(12, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2106:
		case 14859:
			c.getMining().startMining(13, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2100:
			c.getMining().startMining(14, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2101:
			c.getMining().startMining(15, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2098:
		case 11951:
			c.getMining().startMining(16, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2099:
			c.getMining().startMining(17, c.objectX, c.objectY,
					c.clickObjectType, objectType);
			break;
		case 2732:
			if(Firemaking.getLogData(c) != null)
				Firemaking.handleBonfire(c, Firemaking.getLogData(c).getLogId());
			else
				c.sendMessage("You need logs to throw into the bonfire.");
			break;
		case 2783:
			c.getDH().sendOption5("Bronze", "Iron", "Steel", "Mithril",
					"More..");
			c.dialogueAction = 61;
			break;
		case 3192:
			c.getPA().getHighscores("kills");
			c.getPA().sendFrame126("Divination of Gods Highscores: Kills",
					12102);
			break;
		case 50056:
			c.getPA().movePlayer(3052, 3497, 1);
			break;
		case 2641:
			c.getPA().movePlayer(3052, 3497, 1);
			break;
		case 1530:
			if (c.absX == 2964 && c.absY == 3206 || c.absX == 2963
					&& c.absY == 3206) {
				c.getPA().movePlayer(2965, 3206, 0);
				return;
			}
			if (c.absX == 2965 && c.absY == 3206 || c.absX == 2966
					&& c.absY == 3206) {
				c.getPA().movePlayer(2964, 3206, 0);
				return;
			}
			if (c.absX == 2611 && c.absY == 3399) {
				c.getPA().movePlayer(2611, 3398, 0);
			}
			if (c.absX == 2611 && c.absY == 3398) {
				c.getPA().movePlayer(2611, 3399, 0);
				if (c.absX == 2610 && c.absY == 3399) {
					c.getPA().movePlayer(2611, 3398, 0);
					if (c.absX == 2612 && c.absY == 3399) {
						c.getPA().movePlayer(2611, 3398, 0);
						if (c.absX == 2612 && c.absY == 3398) {
							c.getPA().movePlayer(2611, 3399, 0);
						} else if (c.absX == 2611 && c.absY == 3399) {
							c.getPA().movePlayer(2611, 3398, 0);
						} else {

							c.getPA().movePlayer(3052, 3490, 0);
						}
					}
				}
			}

			break;
		case 1536:
			// easter event
			if (c.absX == 3215 && c.absY == 3212) {
				c.getPA().movePlayer(3215, 3211, 0);
			}
			if (c.absX == 3215 && c.absY == 3213) {
				c.getPA().movePlayer(3215, 3211, 0);
			}
			if (c.absX == 3214 && c.absY == 3212) {
				c.getPA().movePlayer(3215, 3211, 0);
			}
			if (c.absX == 3216 && c.absY == 3212) {
				c.getPA().movePlayer(3215, 3211, 0);
			}
			if (c.absX == 3214 && c.absY == 3213) {
				c.getPA().movePlayer(3215, 3211, 0);
			}
			if (c.absX == 3213 && c.absY == 3212) {
				c.getPA().movePlayer(3215, 3211, 0);
			}
			if (c.absX == 3215 && c.absY == 3211) {
				c.getPA().movePlayer(3215, 3212, 0);
			}
			if (c.absX == 3214 && c.absY == 3211) {
				c.getPA().movePlayer(3215, 3212, 0);
			}
			if (c.absX == 3216 && c.absY == 3211) {
				c.getPA().movePlayer(3215, 3212, 0);
			}
			if (c.absX == 3215 && c.absY == 3211) {
				c.getPA().movePlayer(3215, 3212, 0);
			}
			break;
		case 12389:
			c.heightLevel -= 1;
			break;
		/*
		 * case 1530: // easter event if(c.absX == 3208 && c.absY == 3211) {
		 * c.getPA().movePlayer(3208, 3212, 0); } if(c.absX == 3207 && c.absY ==
		 * 3211) { c.getPA().movePlayer(3208, 3212, 0); } if(c.absX == 3209 &&
		 * c.absY == 3211) { c.getPA().movePlayer(3208, 3212, 0); } if(c.absX ==
		 * 3208 && c.absY == 3210) { c.getPA().movePlayer(3208, 3212, 0); }
		 * if(c.absX == 3208 && c.absY == 3212) { c.getPA().movePlayer(3208,
		 * 3211, 0); } if(c.absX == 3208 && c.absY == 3213) {
		 * c.getPA().movePlayer(3208, 3211, 0); } if(c.absX == 3209 && c.absY ==
		 * 3212) { c.getPA().movePlayer(3208, 3211, 0); } if(c.absX == 3207 &&
		 * c.absY == 3212) { c.getPA().movePlayer(3208, 3211, 0); }
		 * 
		 * //other event if (c.absX == 2611 && c.absY == 3398) {
		 * c.getPA().movePlayer(2611, 3399, 0); } else if (c.absX == 2611 &&
		 * c.absY == 3399) { c.getPA().movePlayer(2611, 3398, 0); } else if
		 * (c.absX == 3108 && c.absY == 9570) { c.getPA().movePlayer(3107, 9570,
		 * 0); c.sendMessage(
		 * "Search the room for an ancient talisman and bring it to aubury!"); }
		 * else if (c.absX == 3107 && c.absY == 9570) {
		 * c.getPA().movePlayer(3108, 9570, 0); // } } break;
		 */

		case 2294: // Barbarian Log Balance
			if (c.absX == 2551 && c.absY == 3546) {
				// c.startAnimation(762);
				c.obsticle(762, 40, -10, 0, 5550, 2000,
						"You passed obsticle succesfully.");
				c.ag1 = 1;
				c.isDoingBarbLog = true;
				c.antiSpamNet1 = false;
			}
			break;
		case 2282:
			c.antiSpamRope1 = false;
			c.sendMessage("false");
			if (c.absX == 2552 && c.absY == 3554) {
				c.getPA().movePlayer(2541, 3553, 0);
			}
			if (c.absX == 2550 && c.absY == 3554) {
				c.getPA().movePlayer(2541, 3553, 0);
			}
			if (c.absX == 2551 && c.absY == 3555) {
				c.getPA().movePlayer(2541, 3553, 0);
			}
			if (c.antiSpamRope1 == false && c.absX == 2541 && c.absY == 3553) {
				c.turnPlayerTo(c.objectX, c.objectY);
				c.startAnimation(3067);
				c.getPA().movePlayer(2551, 3549, 0);
				c.antiSpamRope1 = true;
			}
			break;
		case 2284: // Barbarian Obstacle net
			if (c.antiSpamNet1 == false) {
				c.agilityDelay(828, 2537, 3546, 1, 40, 2500,
						"You climbed the nets succesfully.");
				c.ag2 = 1;
				c.antiSpamNet1 = true;
			}
			break;
		case 2302: // Barbarian Balancing ledge
			if (c.inWild()) {
				return;
			}
			if (c.absX == 2536 && c.absY == 3547)
				c.obsticle(756, 40, -4, 0, 2500, 3500,
						"You passed the balancing ledge succesfully.");
			c.ag3 = 1;
			c.isGoingOverPlank = true;
			break;
		case 3205: // Ladder
			c.agilityDelay(827, 2532, 3546, 0, 40, 15, "You climb down.");
			c.ag4 = 1;
			break;
		case 1948: // Crumbling Walls
			Agility.AgilityWall(c, 80);

			break;
		// donator zone objects
		case 13620:
			c.getPA().movePlayer(2721, 9450, 4);
			c.sendMessage("You teleported to steel/iron donator only NPC's!");
			c.sendMessage("You'll only be able to see Donators here, this makes it alot easier to train.");
			break;
		case 13615:
			c.getPA().movePlayer(3115, 9838, 4);
			c.sendMessage("You teleported to Hill Giants donator only NPC's!");
			c.sendMessage("You'll only be able to see Donators here, this makes it alot easier to train.");
			break;
		case 8749:
			if (c.playerRights == 1 || c.playerRights == 2
					|| c.playerRights == 2 || c.playerRights == 3
					|| c.playerRights == 4) {
				if (c.gwdelay != 0) {
					c.sendMessage("You need to wait another " + c.gwdelay
							+ " seconds to do this.");
					return;
				}
				if (c.gwdelay == 0) {
					c.gwdelay = 300;
					c.getTimers().GWDTimer(c);
					c.specAmount = 10;
					c.startAnimation(645);
					c.sendMessage("Spec restored: 100%!");
				}
			} else {
				c.sendMessage("You need to be a donator for a full special restore.");
			}
			break;
		case 4412:
			c.getPA().movePlayer(2329, 3804, 0);
			break;
		case 1:
			if (!c.hasSearchedCrateInDung == true && c.InDung) {
				c.getItems().addItem(18169, 4);
				c.sendMessage("You've found 4 Web snippers!");
				c.hasSearchedCrateInDung = true;
			} else {
				if (c.InDung) {
					c.sendMessage("You search the crate... And find nothing.");
				}
			}
			break;
		case 21505:
			if (c.absX == 2328 && c.absY == 3805) {
				c.getPA().movePlayer(2329, 3804, 0);
			}
			if (c.absX == 2328 && c.absY == 3804) {
				c.getPA().movePlayer(2329, 3804, 0);
			} else {
				if (c.absX == 2329 && c.absY == 3804) {
					c.getPA().movePlayer(2328, 3804, 0);
				}
			}
			break;
		case 9359:
			c.getPA().movePlayer(2862, 9571, 0);
			break;
		case 21512:
			c.startAnimation(828);
			c.getPA().movePlayer(c.absX + 2, c.absY, c.heightLevel + 2);
			break;
		case 21513:
			c.startAnimation(828);
			c.getPA().movePlayer(c.absX - 2, c.absY, c.heightLevel - 2);
			break;
		case 21514:
			c.startAnimation(828);
			c.getPA().movePlayer(c.absX - 2, c.absY, c.heightLevel + 1);
			break;
		case 21515:
			c.startAnimation(828);
			c.getPA().movePlayer(c.absX + 2, c.absY, c.heightLevel - 1);
			break;
		case 21507:
			if (c.absX == 2328 && c.absY == 3805) {
				c.getPA().movePlayer(2329, 3805, 0);
			} else {
				if (c.absX == 2329 && c.absY == 3805) {
					c.getPA().movePlayer(2328, 3805, 0);
				}
			}
			break;
		case 21600:
			if (c.absX == 2326 && c.absY == 3802) {
				c.getPA().movePlayer(2326, 3801, 0);
			} else {
				if (c.absX == 2326 && c.absY == 3801) {
					c.getPA().movePlayer(2326, 3802, 0);
				}
			}
			break;
		case 11219:
			Bank.handleBankChest(c);
			break;
		case 13660:
			c.sendMessage("Coming in the future..");
			break;
		case 13641:
			c.getDH().sendOption5("Lumbridge", "Varrock", "Camelot", "Falador",
					"Canafis");
			c.teleAction = 20;
			break;

		case 11215:
			if (c.playerLevel[23] < 63) {
				c.sendMessage("You need level 64 construction to build that!");
				return;
			}
			if (!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to build this!");
				return;
			}
			if (c.getItems().playerHasItem(1539, 15)
					&& c.getItems().playerHasItem(8782, 2)
					&& c.getItems().playerHasItem(2351, 1)) {
				c.startAnimation(898);
				Cons.BuildAltar(c);
			} else {
				c.sendMessage("You don't have the required materials.");
				c.sendMessage("You need 15 Steel Nails, 2 Mahogany Planks and 1 Iron Bar!");
			}
			break;

		case 11214:
			c.getPA().movePlayer(obX, obY, c.heightLevel);
			c.getPA().showInterface(31250);
			c.sendMessage("Choose an item to build...");
			break;
		case 13405:
			if (c.objectX == 3352) {
				c.getPA().showInterface(31330);
			} else {
				c.getPA().startTeleport2(3349, 3346, 0);
				c.sendMessage("You teleported back to home area.");
			}

			break;
		case 3994:
			ConstructionEvents.MakeIronNails(c);
			break;
		case 2644:
			c.sendMessage("Hold on..Starting event & checking requirments..");
			c.spinFlax();
			c.turnPlayerTo(obX, obY);
			break;
		case 6551:
			c.sendMessage("Disabled.");
			// c.getPA().startTeleport(2893, 4811, 0, "modern");
			break;
		case 2492:
			c.getPA().movePlayer(2594, 3160, 0);
			break;
		case 3782:
			c.getPA().movePlayer(2897, 3618, 0);
			break;
		case 2646: // flax picking
			c.turnPlayerTo(c.objectX, c.objectY);
			if (System.currentTimeMillis() - c.buryDelay > 2000) {
				if (c.getItems().freeSlots() > 0) {
					if (Misc.random(1) == 1) {
						c.getPA().replaceObject(c, -1, obX, obY, 10);
					}
					c.getItems().addItem(1779, 1);
					c.startAnimation(827);
					c.sendMessage("You pick some flax.");
					if (c.getItems().playerHasItem(1779, 2) && !c.task1[8]) {
						c.task1[8] = true;
						c.sendMessage("You've completed the task: Pick some Flax!");
						c.TPoints += 1;
						c.achievementInterface("Pick some Flax!");
						c.sendMessage("You receive one Task point! You now have a total of "
								+ c.TPoints + " points!");
					}
					c.buryDelay = System.currentTimeMillis();
				} else {
					c.sendMessage("You do not have enough inventory space.");
				}
			}
			break;

		/* Party Room */
		case 2416:
		case 26194:// if falador
			c.getDH().sendDialogues(300, 0);
			break;
		/* Party Room End */

		case 10556:
			if (c.needstorelog == true) {
				c.sendMessage("Anti Bug System: Please relog before starting a new wave.");
				return;
			}
			Server.barbDefence.enter(c);
			c.sendMessage("Every once in a while you'll receive messages of your score.");
			break;
		case 28716: // SUMMON OBELISK blue one
		case 28722: // orange one
			c.getPA().closeAllWindows();
			c.getPA().showInterface(23471);
			c.sendMessage("WARNING: This will create ALL pouches available in your inventory.");
			break;
		case 26384:
			if (!c.hasHammer() && c.absX != 2850) {
				c.sendMessage("You need to have a hammer to enter here!");
				return;
			}
			if (c.absY == 5333 && c.absX == 2851 && c.heightLevel == 2
					&& c.hasHammer()) {
				c.getPA().movePlayer(2850, 5333, 2);
				c.sendMessage("Your hammer breaks when you bang on the gigantic door!");
				c.sendMessage("Welcome to the bandos room!");
				c.getItems().deleteItem(2347, 1);
			} else if (c.absY == 5333 && c.absX == 2850 && c.heightLevel == 2) {
				c.getPA().movePlayer(2851, 5333, 2);
			}
			break;
		case 26303:
			// if (c.hasGrapple()) {
			c.getPA().movePlayer(2872, 5279, 2);
			c.sendMessage("You have entered Armadyl.");
			// c.getItems().deleteEquipment(9419, c.playerArrows);

			// } else {
			// c.sendMessage("You need a grapple equipped to swing there");
			// }
			break;
		case 28121:
			c.sendMessage("Not added yet.");
			// BountyHunter.enterCave(c, 2);
			break;
		case 28120:
			// BountyHunter.enterCave(c, 1);
			c.sendMessage("Not added yet.");
			break;
		case 28119:
			// BountyHunter.enterCave(c, 0);
			c.sendMessage("Not added yet.");
			break;
		case 28122:
			// BountyHunter.leaveCave(c);
			c.sendMessage("Not added yet.");
			break;
		case 1738:
			c.getPA().movePlayer(2840, 3539, 2);
			// c.sendMessage("Push The NPC To Go Up There!");
			break;

		case 15638:
			c.getPA().movePlayer(2840, 3539, 0);
			// c.sendMessage("Push The NPC To Go Down There!");
			break;
		case 15641:
		case 15644:

			if (c.absX == 2854 && c.absY == 3546 && c.heightLevel == 0) {
				// c.getPA().movePlayer(2855, 3545, 0);
				c.getPA().movePlayer(2854, 3545, 0);

				return;

			} else if (c.absX == 2855 && c.absY == 3546 && c.heightLevel == 0) {
				c.getPA().movePlayer(2855, 3545, 0);

			} else if (c.absX == 2854 && c.absY == 3545 && c.heightLevel == 0) {
				c.getPA().movePlayer(2854, 3546, 0);

			} else if (c.absX == 2855 && c.absY == 3545 && c.heightLevel == 0) {
				c.getPA().movePlayer(2855, 3546, 0);

			} else if (c.absX == 2846 && c.absY == 3541 && c.heightLevel == 2) {
				if (!c.getItems().playerHasItem(8851, 100)) {
					c.sendMessage("You need 100 warriors guild tokens to enter here.");
					return;
				}
				c.getPA().movePlayer(2847, 3541, 2);
				c.inWG = true;
				c.getItems().deleteItem(8851, c.getItems().getItemSlot(8851),
						10);
				c.WGEvent();

			} else if (c.absX == 2846 && c.absY == 3540 && c.heightLevel == 2) {
				if (!c.getItems().playerHasItem(8851, 100)) {
					c.sendMessage("You need 100 warriors guild tokens to enter here.");
					return;
				}
				c.getPA().movePlayer(2847, 3540, 2);
				c.inWG = true;
				c.getItems().deleteItem(8851, c.getItems().getItemSlot(8851),
						10);
				c.WGEvent();

			} else if (c.absX == 2847 && c.absY == 3541 && c.heightLevel == 2) {
				if (!c.getItems().playerHasItem(8851, 100)) {
					c.sendMessage("You need 100 warriors guild tokens to enter here.");
					return;
				}
				c.getPA().movePlayer(2846, 3541, 2);
				c.inWG = false;
				c.getItems().deleteItem(8851, c.getItems().getItemSlot(8851),
						10);
				c.WGEvent();
				// teles in, now teles out

			} else if (c.absX == 2847 && c.absY == 3540 && c.heightLevel == 2) {
				c.getPA().movePlayer(2846, 3540, 2);
				c.inWG = false;

			} else if (c.absX == 2847 && c.absY == 3541 && c.heightLevel == 2) {
				c.getPA().movePlayer(2846, 3540, 2);
				c.inWG = false;

			}
			break;
		/*
		 * c.EventManager.getSingleton().addEvent(new Event() {
		 * 
		 * @Override public void execute() { if (c.heightLevel == 2) { if
		 * (c.absX >= 2838 && c.absX <= 2846 && c.absY >= 3543 && c.absY <= 3555
		 * || c.absX >= 2846 && c.absX <= 2876 && c.absY >= 3533 && c.absY <=
		 * 3556) { c.getItems().deleteItem(8851, c.getItems().getItemSlot(8851),
		 * 10); c.sendMessage("10 of your tokens crumble to dust."); blaaah
		 * removed fuck this
		 */

		case 4413:
			c.getPA().movePlayer(2957, 3218, 0);
			break;
		case 7273:
			c.getPA().movePlayer(2957, 3218, 0);
			break;
		case 2144:
		case 2143: // stupid fucking herblore quest booring shit
			if (c.absX == 2887 && c.absY == 9831) { // enter gates
				c.getPA().movePlayer(2889, 9831, 0);
				c.sendMessage("Great you're in the correct place! Now use the meats on the cauldron..");
			} else if (c.absX == 2888 && c.absY == 9831) { // enter gates
				c.getPA().movePlayer(2889, 9831, 0);
				c.sendMessage("Great you're in the correct place! Now use the meats on the cauldron..");
			} else if (c.absX == 2889 && c.absY == 9831) { // exit
				c.getPA().movePlayer(2888, 9831, 0);
			} else if (c.absX == 2889 && c.absY == 9830) { // exit
				c.getPA().movePlayer(2888, 9831, 0);
			}
			break;
		/*
		 * case 381: if (!c.getItems().playerHasItem(681,1) && c.rMQ == 2) {
		 * c.getItems().addItem(681, 1); c.rMQ = 3; } else if
		 * (c.getItems().playerHasItem(681,1)) {
		 * c.sendMessage("You alredy have the talisman..Go talk to Aubury!"); }
		 * else if (c.rMQ == 2) { c.sendMessage("Talk to Distentor first.."); }
		 * break;
		 */

		case 27668:
			if (c.absX == 3231 && c.absY == 5091 || c.absX == 3231
					&& c.absY == 5090 || c.absX == 3232 && c.absY == 5090) {
				c.getPA().movePlayer(3366, 3268, 0);
			}
			break;
		case 27669:
			c.getPA().movePlayer(3232, 5090, 0);
			break;
		case 3561:// balance ledge
			if (c.inWild()) {
				return;
			}
			if (c.absX == 2803 && c.absY == 9546) {
				c.obsticle(2592, 1, -7, 0, 3650, 1500,
						"You passed the balancing ledge succesfully.");
			} else if (c.absX == 2770 && c.absY == 9546) {
				c.obsticle(2592, 1, -7, 0, 3650, 1500,
						"You passed the balancing ledge succesfully.");
			} else if (c.absX == 2770 && c.absY == 9590) {
				c.obsticle(2592, 1, -7, 0, 3650, 1500,
						"You passed the log balance succesfully.");
			}
			break;
		case 3559:// balance ledge
			if (c.inWild()) {
				return;
			}
			if (c.absX == 2796 && c.absY == 9546) {
				c.obsticle(2592, 1, 7, 0, 3650, 1500,
						"You passed the log balance succesfully.");
			} else if (c.absX == 2763 && c.absY == 9546) {
				c.obsticle(2592, 1, 7, 0, 3650, 1500,
						"You passed the log balance succesfully.");
			} else if (c.absX == 2763 && c.absY == 9590) {
				c.obsticle(2592, 1, 7, 0, 3650, 1500,
						"You passed the balancing ledge succesfully.");
			}
			break;
		case 3553:
		case 3557:// log balance 0 w-e, 0 n-s
			if (c.absX == 2794 && c.absY == 9588) {
				c.obsticle(762, 1, 0, -7, 3330, 1500,
						"You passed the log balance succesfully.");
			} else if (c.absX == 2794 && c.absY == 9581) {
				c.obsticle(762, 1, 0, 7, 3330, 1500,
						"You passed the log balance succesfully.");
			} else if (c.absX == 2770 && c.absY == 9579) {
				c.obsticle(762, 1, -7, 0, 3330, 1500,
						"You passed the log balance succesfully.");
			} else if (c.absX == 2763 && c.absY == 9579) {
				c.obsticle(762, 1, 7, 0, 3330, 1500,
						"You passed the log balance succesfully.");
			} else if (c.absX == 2805 && c.absY == 9555) {
				c.obsticle(762, 1, 0, -7, 3330, 1500,
						"You passed the log balance succesfully.");
			} else if (c.absX == 2805 && c.absY == 9548) {
				c.obsticle(762, 1, 0, -7, 3330, 1500,
						"You passed the log balance succesfully.");
			}
			break;
		case 3578:
			if (c.absX == 2805 && c.absY == 9577) {
				c.obsticle(741, 1, 0, -7, 3330, 1500,
						"You passed the log balance succesfully.");
				// c.getPA().addSkillXP(1500, c.playerAgility);
			} else if (c.absX == 2805 && c.absY == 9570) {
				c.obsticle(741, 1, 0, 7, 3330, 1500,
						"You passed the log balance succesfully.");
				// c.getPA().addSkillXP(1500, c.playerAgility);
			} else if (c.absX == 2761 && c.absY == 9548) {
				c.obsticle(741, 1, 0, 7, 3330, 1500,
						"You passed the log balance succesfully.");
				// c.getPA().addSkillXP(1500, c.playerAgility);
			} else if (c.absX == 2761 && c.absY == 9555) {
				c.obsticle(741, 1, 0, -7, 3330, 1500,
						"You passed the log balance succesfully.");
				// c.getPA().addSkillXP(1500, c.playerAgility);
				// c.getPA().addSkillXP(1500, c.playerAgility);
			} else if (c.absX == 2785 && c.absY == 9568) {
				c.obsticle(741, 1, 7, 0, 3330, 1500,
						"You passed the log balance succesfully.");
				// c.getPA().addSkillXP(1500, c.playerAgility);
			} else if (c.absX == 2792 && c.absY == 9568) {
				c.obsticle(741, 1, -7, 0, 3330, 1500,
						"You passed the log balance succesfully.");
				// c.getPA().addSkillXP(1500, c.playerAgility);
			}
			break;
		case 3572:
		case 3570:
		case 3571: // plank balance
			if (c.absX == 2803 && c.absY == 9589 || c.absX == 2803
					&& c.absY == 9590 || c.absX == 2803 && c.absY == 9591) {
				c.obsticle(762, 1, -7, 0, 3330, 500,
						"You passed the obsticle succesfully.");
				c.getPA().addSkillXP(1500, Player.playerAgility);
				c.gnomeObsticle = 1;
			} else if (c.absX == 2796 && c.absY == 9591 || c.absX == 2796
					&& c.absY == 9590 || c.absX == 2796 && c.absY == 9589) {
				c.obsticle(762, 1, 7, 0, 3330, 500,
						"You passed the obsticle succesfully.");
				c.getPA().addSkillXP(1500, Player.playerAgility);
			} else if (c.absX == 2763 && c.absY == 9558 || c.absX == 2763
					&& c.absY == 9557 || c.absX == 2763 && c.absY == 9556) {
				c.obsticle(762, 1, 7, 0, 3330, 500,
						"You passed the obsticle succesfully.");
				c.getPA().addSkillXP(1500, Player.playerAgility);
			} else if (c.absX == 2796 && c.absY == 9591 || c.absX == 2796
					&& c.absY == 9590 || c.absX == 2796 && c.absY == 9589) {
				c.obsticle(762, 1, 0, 7, 3330, 500,
						"You passed the obsticle succesfully.");
				c.getPA().addSkillXP(1500, Player.playerAgility);
			} else if (c.absX == 2770 && c.absY == 9558 || c.absX == 2770
					&& c.absY == 9557 || c.absX == 2770 && c.absY == 9556) {
				c.obsticle(762, 1, -7, 0, 3330, 500,
						"You passed the obsticle succesfully.");
				c.getPA().addSkillXP(1500, Player.playerAgility);
			}
			break;
		case 3583:// hand holds COME BACK TO THIS AND FIX FACING AND ANIMATION
					// PROBLEM
			if (c.absX == 2792 && c.absY == 9592) {
				c.startAnimation(1117);
				c.obsticle(1117, 1, -7, 0, 3300, 1500,
						"You climbed past the hand holds succesfully.");
			} else if (c.absX == 2785 && c.absY == 9592) {
				c.startAnimation(1117);
				c.obsticle(1117, 1, 7, 0, 3600, 1500,
						"You climbed past the hand holds succesfully.");
			} else if (c.absX == 2792 && c.absY == 9544) {
				c.obsticle(1117, 1, -7, 0, 3600, 1500,
						"You climbed past the hand holds succesfully.");
			} else if (c.absX == 2785 && c.absY == 9544) {
				c.obsticle(1117, 1, 7, 0, 3600, 1500,
						"You climbed past the hand holds succesfully.");
			} else if (c.absX == 2759 && c.absY == 9566) {
				c.obsticle(1117, 1, 0, -7, 3600, 1500,
						"You climbed past the hand holds succesfully.");
			} else if (c.absX == 2759 && c.absY == 9559) {
				c.obsticle(1117, 1, 0, 7, 3600, 1500,
						"You climbed past the hand holds succesfully.");
			}
			break;
		case 3564:// monkey bars
			if (c.absX == 2772 && c.absY == 9578) {
				c.obsticle(744, 1, 0, -8, 4600, 1500,
						"You swing across the monkey bars succesfully.");
			} else if (c.absX == 2772 && c.absY == 9569) {
				c.obsticle(744, 1, 0, 8, 4600, 1500,
						"You swing across the monkey bars succesfully.");
			} else if (c.absX == 2782 && c.absY == 9546) {
				c.obsticle(744, 1, -8, 0, 4600, 1500,
						"You swing across the monkey bars succesfully.");
			} else if (c.absX == 2773 && c.absY == 9546) {
				c.obsticle(744, 1, 8, 0, 4600, 1500,
						"You swing across the monkey bars succesfully.");
			} else if (c.absX == 2794 && c.absY == 9567) {
				c.obsticle(744, 1, 0, -8, 4600, 1500,
						"You swing across the monkey bars succesfully.");
			} else if (c.absX == 2794 && c.absY == 9558) {
				c.obsticle(744, 1, 0, 8, 4600, 1500,
						"You swing across the monkey bars succesfully.");
			}
			break;
		case 3551:// rope
			if (c.absX == 2783 && c.absY == 9588) {
				c.obsticle(762, 1, 0, -7, 3330, 1500,
						"You passed the balancing rope succesfully.");
			} else if (c.absX == 2794 && c.absY == 9548) {
				c.obsticle(762, 1, 0, 7, 3330, 1500,
						"You passed the balancing rope succesfully.");
			} else if (c.absX == 2794 && c.absY == 9555) {
				c.obsticle(762, 1, 0, -7, 3330, 1500,
						"You passed the balancing rope succesfully.");
			} else if (c.absX == 2772 && c.absY == 9566) {
				c.obsticle(762, 1, 0, -7, 3330, 1500,
						"You passed the balancing rope succesfully.");
			} else if (c.absX == 2772 && c.absY == 9559) {
				c.obsticle(762, 1, 0, 7, 3330, 1500,
						"You passed the balancing rope succesfully.");
			} else if (c.absX == 2783 && c.absY == 9581) {
				c.obsticle(762, 1, 0, 7, 3330, 1500,
						"You passed the balancing rope succesfully.");
			}
			break;

		case 3379:
			if (c.playerLevel[24] >= 0) {
				c.getDH().sendOption2("Easy Dungeon", "Jungle Demon");
				c.teleAction = 1227;
				c.objectId = 0;
			} else {
				c.sendMessage("You need atleast a level of 49 Dungeoneering to enter this cave!");
				c.objectId = 0;
				return;
			}
			break;
		case 1765:
			c.getPA().movePlayer(2271, 4680, 0);
			break;

		case 9391:// tzhaar viewing orb
			if (c.InDung() || c.InDung2() || c.inDungBossRoom()
					|| c.inDuelArena() || !c.inPits()) {
				c.sendMessage("You cannot teleport out of Dungeoneering! To exit, use the ladders!");
				return;
			}
			// c.setSidebarInterface(10, 3209);
			// c.outStream.createFrame(106); // Writes the frame 106 out.
			// c.outStream.writeByteC(10); // Tells client to switch to the
			// magicinterface
			c.sendMessage("Currently Disabled.");
			break;

		case 26288:
		case 26287:
		case 26286:
		case 26289:

			if (c.gwdelay > 1) {
				c.sendMessage("You can only do this once every 5 minute!");
				return;
			}
			if (c.playerLevel[5] < c.getPA().getLevelForXP(c.playerXP[5])) {
				c.startAnimation(645);
				c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
				c.getPA().getLevelForXP(c.playerXP[5]);
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().getLevelForXP(c.playerXP[3]);
				c.sendMessage("You recharge your prayer points & HP..");
				c.getPA().refreshSkill(5);
				c.getPA().refreshSkill(3);
				c.gwdelay = 600;
				c.getTimers().GWDTimer(c);
			} else {
				c.sendMessage("You already have full prayer points.");
			}

			break;

		case 2295:
			c.getAgil().AgilityLog(c, "Log", 1, 0, -7, 2474, 3436, 60);
			c.ag1 = 1;
			break;
		case 2285:
			c.startAnimation(828);
			c.getAgil().AgilityNet(c, "Net", 1, 2471, 3426, 1, 2471, 3424, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2472, 3426, 1, 2472, 3424, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2473, 3426, 1, 2473, 3424, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2474, 3426, 1, 2474, 3424, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2475, 3426, 1, 2475, 3424, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2476, 3426, 1, 2476, 3424, 828,
					60);
			c.ag2 = 1;
			break;

		case 2313:
			c.startAnimation(828);
			c.getAgil().AgilityBranch(c, "Branch", 1, 2473, 3420, 2, 2473,
					3423, 828, 60);
			c.getAgil().AgilityBranch(c, "Branch", 1, 2473, 3420, 2, 2474,
					3422, 828, 60);
			c.getAgil().AgilityBranch(c, "Branch", 1, 2473, 3420, 2, 2472,
					3422, 828, 60);
			c.ag3 = 1;
			break;
		case 2312:
			c.getAgil().AgilityRope(c, "Rope", 1, +6, 0, 2477, 3420, 60);
			c.ag4 = 1;
			break;
		case 2314:
			c.startAnimation(828);
			c.sendMessage("You slipped and fell.");
			c.getAgil().AgilityBranch(c, "Branch", 1, 2486, 3420, 0, 2485,
					3419, 828, 60);
			c.getAgil().AgilityBranch(c, "Branch", 1, 2486, 3420, 0, 2485,
					3420, 828, 60);
			c.getAgil().AgilityBranch(c, "Branch", 1, 2486, 3420, 0, 2486,
					3420, 828, 60);
			c.ag5 = 1;
			break;
		case 2286:
			c.startAnimation(828);
			c.getAgil().AgilityNet(c, "Net", 1, 2483, 3425, 0, 2483, 3427, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2484, 3425, 0, 2484, 3427, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2485, 3425, 0, 2485, 3427, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2486, 3425, 0, 2486, 3427, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2487, 3425, 0, 2487, 3427, 828,
					60);
			c.getAgil().AgilityNet(c, "Net", 1, 2488, 3425, 0, 2488, 3427, 828,
					60);
			c.ag6 = 1;
			c.getAgil().bonus = true;
			break;
		case 154:
			c.fmwalkto(0, 1);
			c.getAgil().AgilityPipe(c, "Pipe", 1, 2484, 3430, 0, +7, 2996, 10,
					60);
			break;
		case 4058:
			c.fmwalkto(0, 1);
			c.getAgil().AgilityPipe(c, "Pipe", 1, 2487, 3430, 0, +7, 2996, 10,
					60);
			break;

		case 82016:
			if (c.takeAsNote = false) {
				c.takeAsNote = true;
			} else if (c.takeAsNote = true) {
				c.takeAsNote = false;
			}
			break;

		case 8972:
			if ((c.playerLevel[18] > 91)) {
				c.getPA().movePlayer(3052, 9577, 0);
				c.sendMessage("You find yourself in the Frost Dragons Lair!");
			} else {
				c.sendMessage("You need 92 Slayer to face frost dragons!");
			}

			break;

		case 4150:
			c.getPA().movePlayer(2606, 3154, 0);
			c.sendMessage("Welcome to Funpk!");

			break;

		case 7315:
			c.getPA().movePlayer(3093, 3479, 0);

			break;

		case 7316:
			c.getPA().movePlayer(3087, 3495, 0);
			c.sendMessage("You have returned from FunPk Unharmed!");

			break;

		case 2471:
			c.getPA().movePlayer(3363, 9638, 0);
			c.sendMessage("Welcome to PkBox!");
			break;
		case 4151:
			c.getPA().movePlayer(3089, 3489, 0);
			c.sendMessage("You return home unharmed.");
			break;

		case 8987:
			c.getPA().movePlayer(3211, 3422, 0);
			break;

		case 6455:
			c.getPA().movePlayer(2837, 3803, 1);
			break;

		case 6456:
			c.getPA().movePlayer(2837, 3806, 0);
			break;

		case 2469:
			c.getPA().movePlayer(1762, 5180, 0);
			break;

		case 6461:
			c.getPA().movePlayer(2851, 3809, 2);
			break;

		case 13623:
			c.getPA().movePlayer(2837, 3806, 0);
			c.sendMessage("Multi Zone Is Working!");
			break;

		case 411:
			if (c.altarPrayed == 0) {
				c.altarPrayed = 1;
				c.setSidebarInterface(5, 22500);
				c.startAnimation(645);
				c.sendMessage("You sense a surge of power flow through your body!");
				c.getCurse().resetCurse();
				// c.handleCurseBugFix();
				c.getCombat().resetPrayers();
			} else {
				c.getCombat().resetPrayers();
				c.altarPrayed = 0;
				c.setSidebarInterface(5, 5608);
				c.startAnimation(645);
				c.sendMessage("You sense a surge of purity flow through your body!");
				c.getCurse().resetCurse();
				// c.handleCurseBugFix();
				c.getCombat().resetPrayers();
			}
			break;

		case 13619:
			c.getPA().movePlayer(2717, 9801, 4);
			c.sendMessage("You teleported to tormented demons donator only NPC's!");
			c.sendMessage("You'll only be able to see Donators here, Sorta like world 2...");
			break;
		case 6452:
			if (c.absX == 3304 && c.absY == 9376) {
				c.getPA().movePlayer(3305, 9376, 0);
				c.sendMessage("Note: It has 3 waves on it's hp bar!");
				c.sendMessage("IF YOU'RE UNABLE TO ATTACK IT PLEASE RELOG.");
			} else {
				c.autoRet = 0;
				c.getCombat().resetPlayerAttack();
				c.getPA().movePlayer(3304, 9376, 0);
			}
			break;
		case 6451:
			if (c.absX == 3304 && c.absY == 9375) {
				c.getPA().movePlayer(3305, 9375, 0);
				c.sendMessage("Note: It has 3 waves on it's hp bar!");
				c.sendMessage("IF YOU'RE UNABLE TO ATTACK IT PLEASE RELOG.");
				c.clickedGate = true;
				c.heightLevel = 0;
			} else {
				c.autoRet = 0;
				c.getCombat().resetPlayerAttack();
				c.getPA().movePlayer(3304, 9375, 0);
				c.clickedGate = false;
			}
			break;
		case 13625:
			c.getPA().movePlayer(2975, 9515, 1);
			c.sendMessage("You teleported to Barrelchest Non-donators");
			c.sendMessage("The Donators portal to barrelchest is 3 barrelchest bosses spawns!");
			break;
		case 13617:
			c.getPA().movePlayer(2975, 9515, 5);
			c.sendMessage("You teleported to Barrelchest Donators");
			c.sendMessage("You will only see Donators here and 3 bosses!!");
			break;
		case 2882:
		case 2883:
			if (c.objectX == 3268) {
				if (c.absX < c.objectX) {
					c.getPA().walkTo(1, 0);
				} else {
					c.getPA().walkTo(-1, 0);
				}
			}
			break;
		case 272:
			c.getPA().movePlayer(c.absX, c.absY, 1);
			break;

		case 273:
			c.getPA().movePlayer(c.absX, c.absY, 0);
			break;

		case 60:
			c.getPA().movePlayer(3211, 3422, 0);
			break;
		case 26428:
			if (c.Zammy < 15 && c.absX == 2925 && c.absY == 5332) {
				c.sendMessage("You need atleast 15 Zamorak KC to enter here!");
				return;
			}
			if (c.absX == 2925 && c.absY == 5332) {
				c.getPA().movePlayer(2925, 5331, 6);
				c.Zammy -= 15;
				c.sendMessage("A magical force reset your Zamorak kill count!");
			}
			if (c.absX == 2925 && c.absY == 5331) {
				c.getPA().movePlayer(2925, 5332, 2);
				c.autoRet = 0;
				c.getCombat().resetPlayerAttack();
			}
			break;
		case 26425:
			if (c.Band < 15 && c.absX == 2863 && c.absY == 5354) {
				c.sendMessage("You need atleast 15 Bandos KC to enter here!");
				return;
			}
			if (c.absX == 2863 && c.absY == 5354) {
				c.getPA().movePlayer(2864, 5354, 6);
				c.Band -= 15;
				c.sendMessage("A magical force reset your Bandos kill count!");
			}
			if (c.absX == 2864 && c.absY == 5354) {
				c.getPA().movePlayer(2863, 5354, 2);
				c.autoRet = 0;
				c.getCombat().resetPlayerAttack();
			}
			break;
		case 26426:
			if (c.Arma < 15 && c.absX == 2839 && c.absY == 5295) {
				c.sendMessage("You need atleast 15 Armadyl KC to enter here!");
				return;
			}
			if (c.absX == 2839 && c.absY == 5295) {
				c.getPA().movePlayer(2839, 5296, 6);
				c.Arma -= 15;
				c.sendMessage("A magical force reset your Armadyl kill count!");
			}
			if (c.absX == 2839 && c.absY == 5296) {
				c.getPA().movePlayer(2839, 5295, 2);
				c.autoRet = 0;
				c.getCombat().resetPlayerAttack();
			}
			break;
		case 26427:
			if (c.Sara < 15 && c.absX == 2908 && c.absY == 5265) {
				c.sendMessage("You need atleast 15 Saradomin KC to enter here!");
				return;
			}
			if (c.absX == 2908 && c.absY == 5265) {
				c.Sara -= 15;
				c.sendMessage("A magical force reset your Saradomin kill count!");
				c.getPA().movePlayer(2907, 5265, 0);
			}
			if (c.absX == 2907 && c.absY == 5265) {
				c.getPA().movePlayer(2908, 5265, 0);
				c.autoRet = 0;
				c.getCombat().resetPlayerAttack();
			}
			break;
		case 2403:
			if (c.Culin == true) {
				c.getShops().openShop(65);
				return;
			}
			if (c.Agrith == true && c.Flambeed == false) {
				c.getShops().openShop(61);
				return;
			}
			if (c.Flambeed == true && c.Karamel == false) {
				c.getShops().openShop(62);
				return;
			}
			if (c.Karamel == true && c.Dessourt == false) {
				c.getShops().openShop(63);
				return;
			}
			if (c.Dessourt == true && c.Culin == false) {
				c.getShops().openShop(64);
				return;
			}
			if (c.Agrith == false) {
				c.getShops().openShop(60);
			}
			break;
		case 245:
			c.getPA().movePlayer(c.absX, c.absY + 2, 2);
			break;
		case 26293:
			// c.getPA().startTeleport(3211, 3422, 0, "modern");
			c.getPA().startTeleport(3087, 3492, 0, "modern");
			break;
		case 246:
			c.getPA().movePlayer(c.absX, c.absY - 2, 1);
			break;
		case 1766:
			c.getPA().movePlayer(3016, 3849, 0);
			break;
		case 1295:
			c.getPA().movePlayer(3087, 3493, 0);
			break;
		case 2024:
		case 11:
			c.getPA().movePlayer(3087, 3493, 0);
			break;
		case 410:
			if (c.playerMagicBook == 0) {
				if (c.playerEquipment[Player.playerWeapon] == 4675
						|| c.playerEquipment[Player.playerWeapon] == 15486
						|| c.playerEquipment[Player.playerWeapon] == 15040) {
					c.setSidebarInterface(0, 328);
				}
				c.playerMagicBook = 2;
				c.setSidebarInterface(6, 29999);
				c.sendMessage("Your mind becomes stirred with thoughs of dreams.");
				c.getPA().resetAutocast();
			} else {
				if (c.playerEquipment[Player.playerWeapon] == 4675
						|| c.playerEquipment[Player.playerWeapon] == 15486
						|| c.playerEquipment[Player.playerWeapon] == 15040) {
					c.setSidebarInterface(0, 328);
				}
				c.setSidebarInterface(6, 1151); // modern
				c.playerMagicBook = 0;
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
			}
			break;

		case 6552:
			if (c.playerMagicBook == 0) {
				if (c.playerEquipment[Player.playerWeapon] == 4675
						|| c.playerEquipment[Player.playerWeapon] == 15486
						|| c.playerEquipment[Player.playerWeapon] == 15040) {
					c.setSidebarInterface(0, 328);
				}
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 12855);
				c.sendMessage("An ancient wisdomin fills your mind.");
				c.getPA().resetAutocast();
			} else {
				if (c.playerEquipment[Player.playerWeapon] == 4675
						|| c.playerEquipment[Player.playerWeapon] == 15486
						|| c.playerEquipment[Player.playerWeapon] == 15040) {
					c.setSidebarInterface(0, 328);
				}
				c.setSidebarInterface(6, 1151); // modern
				c.playerMagicBook = 0;
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
			}
			break;

		case 1816:
			c.getPA().startTeleport2(2271, 4680, 0);
			break;
		case 1817:
			// c.getPA().startTeleport(3211, 3422, 0, "modern");
			c.getPA().startTeleport(3087, 3492, 0, "modern");
			break;
		case 1814:
			// ardy lever
			c.getPA().startTeleport(3153, 3923, 0, "modern");
			break;

		case 9356:
			// if (c.hasFollower == -1) {
			// c.getPA().enterCaves();
			// c.sendMessage("Do not relog!");
			c.getDH().sendOption2("Normal Fightcaves (Firecape)",
					"TookHar-Kal Caves(New Cape!)");
			c.dialogueAction = 111;
			// c.sendMessage("Relog to start the waves, if it bugs up, just relog!");
			// } else {
			// c.sendMessage("Please dismiss your familiar first");
			// }
			break;
		case 12356:
			if (c.Culin == true) {
				c.sendMessage("You have already finished this minigame!");
				return;
			}
			if (c.absX == 3218 || c.absX == 3217 || c.absX == 3219) {
				c.getPA().enterRFD();
				c.sendMessage("Note: this is not a Safe Minigame, you'll lose your items on death!");
				for (int p = 0; p < Player.PRAYER.length; p++) { // reset prayer
					// glows
					c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
					c.getPA().refreshSkill(5);
					c.prayerActive[p] = false;
					c.getPA().sendFrame36(Player.PRAYER_GLOW[p], 0);
				}
			} else {
				c.getPA().resetRFD();
			}
			break;
		case 1733:
			c.getPA().movePlayer(c.absX, c.absY + 6393, 0);
			break;

		case 1734:
			c.getPA().movePlayer(c.absX, c.absY - 6396, 0);
			break;

		case 9357:
			c.getPA().resetTzhaar();
			NPCHandler.deleteFCNpcs(c);
			break;

		case 8959:
			if (!c.getPA().checkForPlayer(2490,
					c.getY() == 10146 ? 10148 : 10146)) {
				c.sendMessage("There needs to be another player standing on the other trigger!");
				return;
			}
			if (c.getX() == 2490 && (c.getY() == 10146 || c.getY() == 10148)) {
				if (c.getPA().checkForPlayer(2490,
						c.getY() == 10146 ? 10148 : 10146)) {
					new Object(6951, c.objectX, c.objectY, c.heightLevel, 1,
							10, 8959, 15);
				}
			}
			break;

		case 2213:
		case 14367:
		case 11758:
		case 3193:
		case 6084:
		case 11402:
		case 29085:
		case 27663:
		case 21301:
		case 26972:
			// c.getDH().sendOption2("Grand Exchange", "Open Bank");
			// c.dialogueAction = 13813;
			c.getPA().closeAllWindows();
			c.getPA().openUpBank(0);
			break;

		case 2996:
			if (c.getItems().playerHasItem(989, 1)
					&& c.getItems().freeSlots() >= 1) {
				c.getItems().deleteItem(989, 1);
				c.getItems().addItem(c.getPA().randomCrystal(), 1);
				c.getDH().sendDialogues(38, 945);
			} else {
				c.getDH().sendDialogues(37, 945);
			}
			break;

		case 10177:
			c.getPA().movePlayer(1890, 4407, 0);
			break;
		case 10230:
			c.getPA().movePlayer(2900, 4449, 0);
			break;
		case 10229:
			c.getPA().movePlayer(1912, 4367, 0);
			break;
		case 2623:
			if (c.absX >= c.objectX)
				c.getPA().walkTo(-1, 0);
			else
				c.getPA().walkTo(1, 0);
			break;
		case 451:
		case 450:
			c.sendMessage("There's no ore left on the rock.");
			break;
		case 2491:
			// Mining.mineEss(c, objectType);
			c.sendMessage("Disabled atm");
			break;
		// pc boat
		case 14315:
			c.getPA().movePlayer(2661, 2639, 0);
			break;

		case 14314:
			c.getPA().movePlayer(2657, 2639, 0);
			break;

		case 1596:
		case 1597:
			if (c.getY() >= c.objectY)
				c.getPA().walkTo(0, -1);
			else
				c.getPA().walkTo(0, 1);
			break;

		case 14235:
		case 14233:
			if (c.objectX == 2670)
				if (c.absX <= 2670)
					c.absX = 2671;
				else
					c.absX = 2670;
			if (c.objectX == 2643)
				if (c.absX >= 2643)
					c.absX = 2642;
				else
					c.absX = 2643;
			if (c.absX <= 2585)
				c.absY += 1;
			else
				c.absY -= 1;
			c.getPA().movePlayer(c.absX, c.absY, 0);
			break;
		case 14829:
		case 14830:
		case 14827:
		case 14828:
		case 14826:
		case 14831:
			// Server.objectHandler.startObelisk(objectType);
			Server.objectManager.startObelisk(objectType);
			break;
		// case 4387:
		// Server.castleWars.joinWait(c,1);
		// break;

		// case 4388:
		// Server.castleWars.joinWait(c,2);
		// break;

		case 9369:
			// if (c.IsDueling) {
			// // c.sendMessage("Error! Please relog before entering here!");
			// return;
			// }
			// if (c.absX == 2399 && c.absY == 5177) {
			// c.getPA().walkTo(0, -2);
			// c.duelStatus = 0;
			// // c.inPits2 = true;
			// } else {
			// c.getPA().walkTo(0, 2);
			// c.duelStatus = 0;
			// c.inPits2 = true;
			// }
			c.sendMessage("Disabled for the moment, sorry!");
			break;

		case 9368:

			break;
		/*
		 * case 4411: case 4415: case 4417: case 4418: case 4419: case 4420:
		 * case 4469: case 4470: case 4911: case 4912: case 1757:
		 * Server.castleWars.handleObjects(c, objectType, obX, obY); break;
		 */

		// barrows
		// Chest
		case 10284:
			if (c.barrowsKillCount < 5) {
				c.sendMessage("You haven't killed all the brothers.");
			}
			if (c.barrowsKillCount == 5
					&& c.barrowsNpcs[c.randomCoffin][1] == 1) {
				c.sendMessage("I have already summoned this npc.");
			}
			if (c.barrowsNpcs[c.randomCoffin][1] == 0
					&& c.barrowsKillCount >= 5) {
				Server.npcHandler.spawnNpc(c, c.barrowsNpcs[c.randomCoffin][0],
						3551, 9694 - 1, 0, 0, 120, 30, 200, 200, true, true);
				c.barrowsNpcs[c.randomCoffin][1] = 1;
			}
			if ((c.barrowsKillCount > 5 || c.barrowsNpcs[c.randomCoffin][1] == 2)
					&& c.getItems().freeSlots() >= 2) {
				c.getPA().resetBarrows();
				c.getItems().addItem(c.getPA().randomRunes(),
						Misc.random(150) + 100);
				if (Misc.random(2) == 1)
					c.getItems().addItem(c.getPA().randomBarrows(), 1);
				/*
				 * c.shakeScreen(3, 2, 3, 2); World.getWorld().submit(new
				 * Events(4000) { public void execute() { int hitpointsLeft =
				 * (c.
				 * getPA().getXPForLevel(c.getPA().getLevelForXP(c.playerXP[3])
				 * + 1) - c.playerXP[3]); if(c.inBarrows()) {
				 * c.getPA().createPlayersProjectile(c.absX, c.absY, c.absX,
				 * c.absY, 60, 60, 60, 43, 31, - c.playerId - 1, 0);
				 * c.dealDamage(5); c.handleHitMask(5, c.playerId);
				 * c.getPA().sendFrame126("" + c.playerLevel[3] + "", 4016);
				 * c.getPA().sendFrame126("" +
				 * c.getPA().getLevelForXP(c.playerXP[3]) + "", 4017);
				 * c.getPA().sendFrame126("" + c.playerXP[3] + "", 18853);
				 * c.getPA().sendFrame126("" +
				 * c.getPA().getXPForLevel(c.getPA().
				 * getLevelForXP(c.playerXP[3]) + 1) + "", 18854);
				 * c.getPA().sendFrame126("" +hitpointsLeft + "", 18859);
				 * c.getPA().sendFrame126(c.playerLevel[3] + "/" +
				 * c.getPA().getLevelForXP(c.playerXP[3]), 18857);
				 * c.getPA().refreshSkill(3);
				 * 
				 * // this.stop(); } else { this.stop(); } } });
				 */

				// c.getPA().startTeleport(3564, 3288, 0, "modern");
			} else if (c.barrowsKillCount > 5 && c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need at least 2 inventory slot opened.");
			}
			break;
		// doors
		case 6749:
			if (obX == 3562 && obY == 9678) {
				c.getPA().object(3562, 9678, 6749, -3, 0);
				c.getPA().object(3562, 9677, 6730, -1, 0);
			} else if (obX == 3558 && obY == 9677) {
				c.getPA().object(3558, 9677, 6749, -1, 0);
				c.getPA().object(3558, 9678, 6730, -3, 0);
			}
			break;
		case 6730:
			if (obX == 3558 && obY == 9677) {
				c.getPA().object(3562, 9678, 6749, -3, 0);
				c.getPA().object(3562, 9677, 6730, -1, 0);
			} else if (obX == 3558 && obY == 9678) {
				c.getPA().object(3558, 9677, 6749, -1, 0);
				c.getPA().object(3558, 9678, 6730, -3, 0);
			}
			break;
		case 6727:
			if (obX == 3551 && obY == 9684) {
				c.sendMessage("You cant open this door..");
			}
			break;
		case 6746:
			if (obX == 3552 && obY == 9684) {
				c.sendMessage("You cant open this door..");
			}
			break;
		case 6748:
			if (obX == 3545 && obY == 9678) {
				c.getPA().object(3545, 9678, 6748, -3, 0);
				c.getPA().object(3545, 9677, 6729, -1, 0);
			} else if (obX == 3541 && obY == 9677) {
				c.getPA().object(3541, 9677, 6748, -1, 0);
				c.getPA().object(3541, 9678, 6729, -3, 0);
			}
			break;
		case 6729:
			if (obX == 3545 && obY == 9677) {
				c.getPA().object(3545, 9678, 6748, -3, 0);
				c.getPA().object(3545, 9677, 6729, -1, 0);
			} else if (obX == 3541 && obY == 9678) {
				c.getPA().object(3541, 9677, 6748, -1, 0);
				c.getPA().object(3541, 9678, 6729, -3, 0);
			}
			break;
		case 6726:
			if (obX == 3534 && obY == 9684) {
				c.getPA().object(3534, 9684, 6726, -4, 0);
				c.getPA().object(3535, 9684, 6745, -2, 0);
			} else if (obX == 3535 && obY == 9688) {
				c.getPA().object(3535, 9688, 6726, -2, 0);
				c.getPA().object(3534, 9688, 6745, -4, 0);
			}
			break;
		case 6745:
			if (obX == 3535 && obY == 9684) {
				c.getPA().object(3534, 9684, 6726, -4, 0);
				c.getPA().object(3535, 9684, 6745, -2, 0);
			} else if (obX == 3534 && obY == 9688) {
				c.getPA().object(3535, 9688, 6726, -2, 0);
				c.getPA().object(3534, 9688, 6745, -4, 0);
			}
			break;
		case 6743:
			if (obX == 3545 && obY == 9695) {
				c.getPA().object(3545, 9694, 6724, -1, 0);
				c.getPA().object(3545, 9695, 6743, -3, 0);
			} else if (obX == 3541 && obY == 9694) {
				c.getPA().object(3541, 9694, 6724, -1, 0);
				c.getPA().object(3541, 9695, 6743, -3, 0);
			}
			break;
		case 6724:
			if (obX == 3545 && obY == 9694) {
				c.getPA().object(3545, 9694, 6724, -1, 0);
				c.getPA().object(3545, 9695, 6743, -3, 0);
			} else if (obX == 3541 && obY == 9695) {
				c.getPA().object(3541, 9694, 6724, -1, 0);
				c.getPA().object(3541, 9695, 6743, -3, 0);
			}
			break;
		// end doors
		// coffins
		case 6707: // verac
			c.getPA().movePlayer(3556, 3298, 0);
			break;

		case 6823:
			if (server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[0][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2030, c.getX(), c.getY() - 1, -1,
						0, 120, 25, 200, 200, true, true);
				c.barrowsNpcs[0][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;

		case 6706: // torag
			c.getPA().movePlayer(3553, 3283, 0);
			break;

		case 6772:
			if (server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[1][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2029, c.getX() + 1, c.getY(), -1,
						0, 120, 20, 200, 200, true, true);
				c.barrowsNpcs[1][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;

		case 6705: // karil stairs
			c.getPA().movePlayer(3565, 3276, 0);
			break;
		case 6822:
			if (server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[2][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2028, c.getX(), c.getY() - 1, -1,
						0, 90, 17, 200, 200, true, true);
				c.barrowsNpcs[2][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;

		case 6704: // guthan stairs
			c.getPA().movePlayer(3578, 3284, 0);
			break;
		case 6773:
			if (server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[3][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2027, c.getX(), c.getY() - 1, -1,
						0, 120, 23, 200, 200, true, true);
				c.barrowsNpcs[3][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;

		case 6703: // dharok stairs
			c.getPA().movePlayer(3574, 3298, 0);
			break;
		case 6771:
			if (server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[4][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2026, c.getX(), c.getY() - 1, -1,
						0, 120, 45, 250, 250, true, true);
				c.barrowsNpcs[4][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;

		case 6702: // ahrim stairs
			c.getPA().movePlayer(3565, 3290, 0);
			break;
		case 6821:
			if (server.model.minigames.Barrows.selectCoffin(c, objectType)) {
				return;
			}
			if (c.barrowsNpcs[5][1] == 0) {
				Server.npcHandler.spawnNpc(c, 2025, c.getX(), c.getY() - 1, -1,
						0, 90, 19, 200, 200, true, true);
				c.barrowsNpcs[5][1] = 1;
			} else {
				c.sendMessage("You have already searched in this sarcophagus.");
			}
			break;

		case 8143:
			if (c.farm[0] > 0 && c.farm[1] > 0) {
				c.getFarming().pickHerb();
			}
			break;

		// DOORS
		case 1516:
		case 1519:
			if (c.objectY == 9698) {
				if (c.absY >= c.objectY)
					c.getPA().walkTo(0, -1);
				else
					c.getPA().walkTo(0, 1);
				break;
			}
		case 1531:
		case 1533:
		case 1534:
		case 11712:
		case 11711:
		case 11707:
		case 11708:
		case 6725:
		case 3198:
			// case 29261:
		case 3197:
			Server.objectHandler.doorHandling(objectType, c.objectX, c.objectY,
					0);
			break;

		/**********************************************
		 * Chaos tunnel portals - * *
		 **********************************************/
		case 28779:
			if (c.objectX == 3254 && c.objectY == 5451) {
				c.getPA().movePlayer(3250, 5448, 0);
			}
			if (c.objectX == 3250 && c.objectY == 5448) {
				c.getPA().movePlayer(3254, 5451, 0);
			}
			if (c.objectX == 3241 && c.objectY == 5445) {
				c.getPA().movePlayer(3233, 5445, 0);
			}
			if (c.objectX == 3233 && c.objectY == 5445) {
				c.getPA().movePlayer(3241, 5445, 0);
			}
			if (c.objectX == 3259 && c.objectY == 5446) {
				c.getPA().movePlayer(3265, 5491, 0);
			}
			if (c.objectX == 3265 && c.objectY == 5491) {
				c.getPA().movePlayer(3259, 5446, 0);
			}
			if (c.objectX == 3260 && c.objectY == 5491) {
				c.getPA().movePlayer(3266, 5446, 0);
			}
			if (c.objectX == 3266 && c.objectY == 5446) {
				c.getPA().movePlayer(3260, 5491, 0);
			}
			if (c.objectX == 3241 && c.objectY == 5469) {
				c.getPA().movePlayer(3233, 5470, 0);
			}
			if (c.objectX == 3233 && c.objectY == 5470) {
				c.getPA().movePlayer(3241, 5469, 0);
			}
			if (c.objectX == 3235 && c.objectY == 5457) {
				c.getPA().movePlayer(3229, 5454, 0);
			}
			if (c.objectX == 3229 && c.objectY == 5454) {
				c.getPA().movePlayer(3235, 5457, 0);
			}
			if (c.objectX == 3280 && c.objectY == 5460) {
				c.getPA().movePlayer(3273, 5460, 0);
			}
			if (c.objectX == 3273 && c.objectY == 5460) {
				c.getPA().movePlayer(3280, 5460, 0);
			}
			if (c.objectX == 3283 && c.objectY == 5448) {
				c.getPA().movePlayer(3287, 5448, 0);
			}
			if (c.objectX == 3287 && c.objectY == 5448) {
				c.getPA().movePlayer(3283, 5448, 0);
			}
			if (c.objectX == 3244 && c.objectY == 5495) {
				c.getPA().movePlayer(3239, 5498, 0);
			}
			if (c.objectX == 3239 && c.objectY == 5498) {
				c.getPA().movePlayer(3244, 5495, 0);
			}
			if (c.objectX == 3232 && c.objectY == 5501) {
				c.getPA().movePlayer(3238, 5507, 0);
			}
			if (c.objectX == 3238 && c.objectY == 5507) {
				c.getPA().movePlayer(3232, 5501, 0);
			}
			if (c.objectX == 3218 && c.objectY == 5497) {
				c.getPA().movePlayer(3222, 5488, 0);
			}
			if (c.objectX == 3222 && c.objectY == 5488) {
				c.getPA().movePlayer(3218, 5497, 0);
			}
			if (c.objectX == 3218 && c.objectY == 5478) {
				c.getPA().movePlayer(3215, 5475, 0);
			}
			if (c.objectX == 3215 && c.objectY == 5475) {
				c.getPA().movePlayer(3218, 5478, 0);
			}
			if (c.objectX == 3224 && c.objectY == 5479) {
				c.getPA().movePlayer(3222, 5474, 0);
			}
			if (c.objectX == 3222 && c.objectY == 5474) {
				c.getPA().movePlayer(3224, 5479, 0);
			}
			if (c.objectX == 3208 && c.objectY == 5471) {
				c.getPA().movePlayer(3210, 5477, 0);
			}
			if (c.objectX == 3210 && c.objectY == 5477) {
				c.getPA().movePlayer(3208, 5471, 0);
			}
			if (c.objectX == 3214 && c.objectY == 5456) {
				c.getPA().movePlayer(3212, 5452, 0);
			}
			if (c.objectX == 3212 && c.objectY == 5452) {
				c.getPA().movePlayer(3214, 5456, 0);
			}
			if (c.objectX == 3204 && c.objectY == 5445) {
				c.getPA().movePlayer(3197, 5448, 0);
			}
			if (c.objectX == 3197 && c.objectY == 5448) {
				c.getPA().movePlayer(3204, 5445, 0);
			}
			if (c.objectX == 3189 && c.objectY == 5444) {
				c.getPA().movePlayer(3187, 5460, 0);
			}
			if (c.objectX == 3187 && c.objectY == 5460) {
				c.getPA().movePlayer(3189, 5444, 0);
			}
			if (c.objectX == 3192 && c.objectY == 5472) {
				c.getPA().movePlayer(3186, 5472, 0);
			}
			if (c.objectX == 3186 && c.objectY == 5472) {
				c.getPA().movePlayer(3192, 5472, 0);
			}
			if (c.objectX == 3185 && c.objectY == 5478) {
				c.getPA().movePlayer(3191, 5482, 0);
			}
			if (c.objectX == 3191 && c.objectY == 5482) {
				c.getPA().movePlayer(3185, 5478, 0);
			}
			if (c.objectX == 3171 && c.objectY == 5473) {
				c.getPA().movePlayer(3167, 5471, 0);
			}
			if (c.objectX == 3167 && c.objectY == 5471) {
				c.getPA().movePlayer(3171, 5473, 0);
			}
			if (c.objectX == 3171 && c.objectY == 5478) {
				c.getPA().movePlayer(3167, 5478, 0);
			}
			if (c.objectX == 3167 && c.objectY == 5478) {
				c.getPA().movePlayer(3171, 5478, 0);
			}
			if (c.objectX == 3168 && c.objectY == 5456) {
				c.getPA().movePlayer(3178, 5460, 0);
			}
			if (c.objectX == 3178 && c.objectY == 5460) {
				c.getPA().movePlayer(3168, 5456, 0);
			}
			if (c.objectX == 3191 && c.objectY == 5495) {
				c.getPA().movePlayer(3194, 5490, 0);
			}
			if (c.objectX == 3194 && c.objectY == 5490) {
				c.getPA().movePlayer(3191, 5495, 0);
			}
			if (c.objectX == 3141 && c.objectY == 5480) {
				c.getPA().movePlayer(3142, 5489, 0);
			}
			if (c.objectX == 3142 && c.objectY == 5489) {
				c.getPA().movePlayer(3141, 5480, 0);
			}
			if (c.objectX == 3142 && c.objectY == 5462) {
				c.getPA().movePlayer(3154, 5462, 0);
			}
			if (c.objectX == 3154 && c.objectY == 5462) {
				c.getPA().movePlayer(3142, 5462, 0);
			}
			if (c.objectX == 3143 && c.objectY == 5443) {
				c.getPA().movePlayer(3155, 5449, 0);
			}
			if (c.objectX == 3155 && c.objectY == 5449) {
				c.getPA().movePlayer(3143, 5443, 0);
			}
			if (c.objectX == 3307 && c.objectY == 5496) {
				c.getPA().movePlayer(3317, 5496, 0);
			}
			if (c.objectX == 3317 && c.objectY == 5496) {
				c.getPA().movePlayer(3307, 5496, 0);
			}
			if (c.objectX == 3318 && c.objectY == 5481) {
				c.getPA().movePlayer(3322, 5480, 0);
			}
			if (c.objectX == 3322 && c.objectY == 5480) {
				c.getPA().movePlayer(3318, 5481, 0);
			}
			if (c.objectX == 3299 && c.objectY == 5484) {
				c.getPA().movePlayer(3303, 5477, 0);
			}
			if (c.objectX == 3303 && c.objectY == 5477) {
				c.getPA().movePlayer(3299, 5484, 0);
			}
			if (c.objectX == 3286 && c.objectY == 5470) {
				c.getPA().movePlayer(3285, 5474, 0);
			}
			if (c.objectX == 3285 && c.objectY == 5474) {
				c.getPA().movePlayer(3286, 5470, 0);
			}
			if (c.objectX == 3290 && c.objectY == 5463) {
				c.getPA().movePlayer(3302, 5469, 0);
			}
			if (c.objectX == 3302 && c.objectY == 5469) {
				c.getPA().movePlayer(3290, 5463, 0);
			}
			if (c.objectX == 3296 && c.objectY == 5455) {
				c.getPA().movePlayer(3299, 5450, 0);
			}
			if (c.objectX == 3299 && c.objectY == 5450) {
				c.getPA().movePlayer(3296, 5455, 0);
			}
			if (c.objectX == 3280 && c.objectY == 5501) {
				c.getPA().movePlayer(3285, 5508, 0);
			}
			if (c.objectX == 3285 && c.objectY == 5508) {
				c.getPA().movePlayer(3280, 5501, 0);
			}
			if (c.objectX == 3300 && c.objectY == 5514) {
				c.getPA().movePlayer(3297, 5510, 0);
			}
			if (c.objectX == 3297 && c.objectY == 5510) {
				c.getPA().movePlayer(3300, 5514, 0);
			}
			if (c.objectX == 3289 && c.objectY == 5533) {
				c.getPA().movePlayer(3288, 5536, 0);
			}
			if (c.objectX == 3288 && c.objectY == 5536) {
				c.getPA().movePlayer(3289, 5533, 0);
			}
			if (c.objectX == 3285 && c.objectY == 5527) {
				c.getPA().movePlayer(3282, 5531, 0);
			}
			if (c.objectX == 3282 && c.objectY == 5531) {
				c.getPA().movePlayer(3285, 5527, 0);
			}
			if (c.objectX == 3325 && c.objectY == 5518) {
				c.getPA().movePlayer(3323, 5531, 0);
			}
			if (c.objectX == 3323 && c.objectY == 5531) {
				c.getPA().movePlayer(3325, 5518, 0);
			}
			if (c.objectX == 3299 && c.objectY == 5533) {
				c.getPA().movePlayer(3297, 5536, 0);
			}
			if (c.objectX == 3297 && c.objectY == 5536) {
				c.getPA().movePlayer(3299, 5533, 0);
			}
			if (c.objectX == 3321 && c.objectY == 5554) {
				c.getPA().movePlayer(3315, 5552, 0);
			}
			if (c.objectX == 3315 && c.objectY == 5552) {
				c.getPA().movePlayer(3321, 5554, 0);
			}
			if (c.objectX == 3291 && c.objectY == 5555) {
				c.getPA().movePlayer(3285, 5556, 0);
			}
			if (c.objectX == 3285 && c.objectY == 5556) {
				c.getPA().movePlayer(3291, 5555, 0);
			}
			if (c.objectX == 3266 && c.objectY == 5552) {
				c.getPA().movePlayer(3262, 5552, 0);
			}
			if (c.objectX == 3262 && c.objectY == 5552) {
				c.getPA().movePlayer(3266, 5552, 0);
			}
			if (c.objectX == 3256 && c.objectY == 5561) {
				c.getPA().movePlayer(3253, 5561, 0);
			}
			if (c.objectX == 3253 && c.objectY == 5561) {
				c.getPA().movePlayer(3256, 5561, 0);
			}
			if (c.objectX == 3249 && c.objectY == 5546) {
				c.getPA().movePlayer(3252, 5543, 0);
			}
			if (c.objectX == 3252 && c.objectY == 5543) {
				c.getPA().movePlayer(3249, 5546, 0);
			}
			if (c.objectX == 3261 && c.objectY == 5536) {
				c.getPA().movePlayer(3268, 5534, 0);
			}
			if (c.objectX == 3268 && c.objectY == 5534) {
				c.getPA().movePlayer(3261, 5536, 0);
			}
			if (c.objectX == 3243 && c.objectY == 5526) {
				c.getPA().movePlayer(3241, 5529, 0);
			}
			if (c.objectX == 3241 && c.objectY == 5529) {
				c.getPA().movePlayer(3243, 5526, 0);
			}
			if (c.objectX == 3230 && c.objectY == 5547) {
				c.getPA().movePlayer(3226, 5553, 0);
			}
			if (c.objectX == 3226 && c.objectY == 5553) {
				c.getPA().movePlayer(3230, 5547, 0);
			}
			if (c.objectX == 3206 && c.objectY == 5553) {
				c.getPA().movePlayer(3204, 5546, 0);
			}
			if (c.objectX == 3204 && c.objectY == 5546) {
				c.getPA().movePlayer(3206, 5553, 0);
			}
			if (c.objectX == 3211 && c.objectY == 5533) {
				c.getPA().movePlayer(3214, 5533, 0);
			}
			if (c.objectX == 3214 && c.objectY == 5533) {
				c.getPA().movePlayer(3211, 5533, 0);
			}
			if (c.objectX == 3208 && c.objectY == 5527) {
				c.getPA().movePlayer(3211, 5523, 0);
			}
			if (c.objectX == 3211 && c.objectY == 5523) {
				c.getPA().movePlayer(3208, 5527, 0);
			}
			if (c.objectX == 3201 && c.objectY == 5531) {
				c.getPA().movePlayer(3197, 5529, 0);
			}
			if (c.objectX == 3197 && c.objectY == 5529) {
				c.getPA().movePlayer(3201, 5531, 0);
			}
			if (c.objectX == 3202 && c.objectY == 5515) {
				c.getPA().movePlayer(3196, 5512, 0);
			}
			if (c.objectX == 3196 && c.objectY == 5512) {
				c.getPA().movePlayer(3202, 5515, 0);
			}
			if (c.objectX == 3190 && c.objectY == 5515) {
				c.getPA().movePlayer(3190, 5519, 0);
			}
			if (c.objectX == 3190 && c.objectY == 5519) {
				c.getPA().movePlayer(3190, 5515, 0);
			}
			if (c.objectX == 3185 && c.objectY == 5518) {
				c.getPA().movePlayer(3181, 5517, 0);
			}
			if (c.objectX == 3181 && c.objectY == 5517) {
				c.getPA().movePlayer(3185, 5518, 0);
			}
			if (c.objectX == 3187 && c.objectY == 5531) {
				c.getPA().movePlayer(3182, 5530, 0);
			}
			if (c.objectX == 3182 && c.objectY == 5530) {
				c.getPA().movePlayer(3187, 5531, 0);
			}
			if (c.objectX == 3169 && c.objectY == 5510) {
				c.getPA().movePlayer(3159, 5501, 0);
			}
			if (c.objectX == 3159 && c.objectY == 5501) {
				c.getPA().movePlayer(3169, 5510, 0);
			}
			if (c.objectX == 3165 && c.objectY == 5515) {
				c.getPA().movePlayer(3173, 5530, 0);
			}
			if (c.objectX == 3173 && c.objectY == 5530) {
				c.getPA().movePlayer(3165, 5515, 0);
			}
			if (c.objectX == 3156 && c.objectY == 5523) {
				c.getPA().movePlayer(3152, 5520, 0);
			}
			if (c.objectX == 3152 && c.objectY == 5520) {
				c.getPA().movePlayer(3156, 5523, 0);
			}
			if (c.objectX == 3148 && c.objectY == 5533) {
				c.getPA().movePlayer(3153, 5537, 0);
			}
			if (c.objectX == 3153 && c.objectY == 5537) {
				c.getPA().movePlayer(3148, 5533, 0);
			}
			if (c.objectX == 3143 && c.objectY == 5535) {
				c.getPA().movePlayer(3147, 5541, 0);
			}
			if (c.objectX == 3147 && c.objectY == 5541) {
				c.getPA().movePlayer(3143, 5535, 0);
			}
			if (c.objectX == 3168 && c.objectY == 5541) {
				c.getPA().movePlayer(3171, 5542, 0);
			}
			if (c.objectX == 3171 && c.objectY == 5542) {
				c.getPA().movePlayer(3168, 5541, 0);
			}
			if (c.objectX == 3190 && c.objectY == 5549) {
				c.getPA().movePlayer(3190, 5554, 0);
			}
			if (c.objectX == 3190 && c.objectY == 5554) {
				c.getPA().movePlayer(3190, 5549, 0);
			}
			if (c.objectX == 3180 && c.objectY == 5557) {
				c.getPA().movePlayer(3174, 5558, 0);
			}
			if (c.objectX == 3174 && c.objectY == 5558) {
				c.getPA().movePlayer(3180, 5557, 0);
			}
			if (c.objectX == 3162 && c.objectY == 5557) {
				c.getPA().movePlayer(3158, 5561, 0);
			}
			if (c.objectX == 3158 && c.objectY == 5561) {
				c.getPA().movePlayer(3162, 5557, 0);
			}
			if (c.objectX == 3166 && c.objectY == 5553) {
				c.getPA().movePlayer(3162, 5545, 0);
			}
			if (c.objectX == 3162 && c.objectY == 5545) {
				c.getPA().movePlayer(3166, 5553, 0);
			}
			if (c.objectX == 3142 && c.objectY == 5545) {
				c.getPA().movePlayer(3115, 5528, 0);
			}
			break;
		// Borks portal
		case 29537:
			if (c.objectX == 3115 && c.objectY == 5528) {
				c.getPA().movePlayer(3142, 5545, 0);
			} else if (c.objectX == 1890 && c.objectY == 5407) {
				if (!c.inBarbDef && c.needstorelog) {
					c.disconnected = true;
				} else if (!c.inBarbDef && !c.needstorelog) {
					c.getDH().sendOption2("Start minigame", "End minigame");
					c.teleAction = 54;
				} else if (c.inBarbDef && !c.needstorelog) {
					c.getPA().moveBarb();
					c.teleAction = 54;
				}
			}
			break;

		case 9319:
			if (c.heightLevel == 0)
				c.getPA().movePlayer(c.absX, c.absY, 1);
			else if (c.heightLevel == 1)
				c.getPA().movePlayer(c.absX, c.absY, 2);
			break;

		case 9320:
			if (c.heightLevel == 1)
				c.getPA().movePlayer(c.absX, c.absY, 0);
			else if (c.heightLevel == 2)
				c.getPA().movePlayer(c.absX, c.absY, 1);
			break;

		case 4496:
		case 4494:
			if (c.heightLevel == 2 && c.absX == 3417 && c.absY == 3541
					|| c.heightLevel == 2 && c.absX == 3417 && c.absY == 3540) {
				c.getPA().movePlayer(c.absX - 5, c.absY, 1);
			} else if (c.heightLevel == 1 && c.absX == 3433 && c.absY == 3537
					|| c.heightLevel == 1 && c.absX == 3433 && c.absY == 3538) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 0);
			}

			break;

		case 4493:
			if (c.heightLevel == 0 && c.absX == 3438 && c.absY == 3538
					|| c.heightLevel == 0 && c.absX == 3438 && c.absY == 3537) {
				c.getPA().movePlayer(c.absX - 5, c.absY, 1);
			} else if (c.heightLevel == 1 && c.absX == 3433 && c.absY == 3537
					|| c.heightLevel == 1 && c.absX == 3433 && c.absY == 3538) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 2);
			}

			break;

		case 4495:
			if (c.heightLevel == 1 && c.absX == 3412 && c.absY == 3540
					|| c.heightLevel == 1 && c.absX == 3412 && c.absX == 3541) {
				c.getPA().movePlayer(c.absX + 5, c.absY, 2);
			}

			break;

		case 5126:
			if (c.absY == 3554)
				c.getPA().walkTo(0, 1);
			else
				c.getPA().walkTo(0, -1);
			break;

		case 1759:
			if (c.objectX == 2884 && c.objectY == 3397)
				c.getPA().movePlayer(c.absX, c.absY + 6400, 0);
			break;
		case 3203: // dueling forfeit
			if (c.duelCount > 0) {
				c.sendMessage("You may not forfeit yet.");
				break;
			}
			Client o = (Client) PlayerHandler.players[c.duelingWith];
			if (o == null) {
				c.getTradeAndDuel().resetDuel();
				c.getPA().movePlayer(
						Config.DUELING_RESPAWN_X
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						Config.DUELING_RESPAWN_Y
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						0);
				break;
			}
			if (c.duelRule[0]) {
				c.sendMessage("Forfeiting the duel has been disabled!");
				break;
			}
			{
				o.getPA().movePlayer(
						Config.DUELING_RESPAWN_X
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						Config.DUELING_RESPAWN_Y
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						0);
				c.getPA().movePlayer(
						Config.DUELING_RESPAWN_X
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						Config.DUELING_RESPAWN_Y
								+ (Misc.random(Config.RANDOM_DUELING_RESPAWN)),
						0);
				o.duelStatus = 6;
				o.getTradeAndDuel().duelVictory();
				c.getTradeAndDuel().resetDuel();
				c.getTradeAndDuel().resetDuelItems();
				o.sendMessage("The other player has forfeited the duel!");
				c.sendMessage("You forfeit the duel!");
				break;
			}

		case 4008:
			if (c.specAmount < 10.0) {
				if (c.specRestore > 0) {
					c.specRestore = 180;
					c.specAmount = 10.0;
					c.startAnimation(645);
					c.getItems().addSpecialBar(
							c.playerEquipment[Player.playerWeapon]);
					c.sendMessage("Your special attack has been restored.");
					c.sendMessage("You can only do this every 3 minutes.");
				} else {
					c.sendMessage("You must wait at least 3 minutes!");
				}
			} else {
				c.sendMessage("You already have full special attack!");
			}
			break;

		case 409:
			c.getDH().sendOption2("Restore Prayer Points", "Change Spellbook");
			c.dialogueAction = 3848;
			break;

		case 2875:
			if (!c.getItems().ownsCape()) {
				c.startAnimation(645);
				c.sendMessage("Guthix blesses you with a cape.");
				c.getItems().addItem(2413, 1);
			}
			break;
		case 2874:
			if (!c.getItems().ownsCape()) {
				c.startAnimation(645);
				c.sendMessage("Zamorak blesses you with a cape.");
				c.getItems().addItem(2414, 1);
			}
			break;
		case 2879:
			c.getPA().movePlayer(2538, 4716, 0);
			break;
		case 2878:
			if (c.mageBankMinigameDone == true) {
				c.getPA().movePlayer(2509, 4689, 0);
			} else {
				c.sendMessage("Talk to Kolodion first.");
				return;
			}
			break;
		case 5960:
			c.getPA().startTeleport2(3090, 3956, 0);
			break;

		case 1815:
			c.getPA().startTeleport2(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0);
			break;

		case 9706:
			c.getPA().startTeleport2(3105, 3951, 0);
			break;
		case 9707:
			c.getPA().startTeleport2(3105, 3956, 0);
			break;

		case 5959:
			c.getPA().startTeleport2(2539, 4712, 0);
			break;

		case 2558:
			c.sendMessage("This door is locked.");
			break;

		case 9294:
			if (c.absX < c.objectX) {
				c.getPA().movePlayer(c.objectX + 1, c.absY, 0);
			} else if (c.absX > c.objectX) {
				c.getPA().movePlayer(c.objectX - 1, c.absY, 0);
			}
			break;
		case 104:
			if (c.donatorChest == 0) {
				c.sendMessage("There appears to be nothing inside.");

			} else if (c.donatorChest >= 1) {
				c.donatorChest -= 1;
				c.getItems().addItem(donatorRitem(), Misc.random(1));
				// c.getItems().addItem(donatorRitem2(),Misc.random(1));
				c.getItems().addItem(995, Misc.random(10000000));

			} else {
				c.sendMessage("This is a donator only chest.");
			}
			break;
		case 9293:
			if (c.absX < c.objectX) {
				c.getPA().movePlayer(2892, 9799, 0);
			} else {
				c.getPA().movePlayer(2886, 9799, 0);
			}
			break;
		case 10529:
		case 10527:
			if (c.absY <= c.objectY)
				c.getPA().walkTo(0, 1);
			else
				c.getPA().walkTo(0, -1);
			break;
		case 3044:
			c.getSmithing().sendSmelting();
			break;

		}
	}

	public void handleBird1() {
		if (!NPCHandler.Summon(c.hasFollower, c)) {
			this.c.sendMessage("This is not your Familiar.");
		} else {
			if (this.c.getItems().freeSlots() > 0 && this.c.rockTailCount > 0) {
				--this.c.rockTailCount;
				this.c.sendMessage("There are another " + this.c.rockTailCount
						+ " Rocktails in your BoB");
				this.c.getItems().addItem(15272, 1);
			}

			if (this.c.rockTailCount <= 0) {
				this.c.sendMessage("Your BoB Has no more supplies left");
			}

			if (this.c.getItems().freeSlots() <= 0) {
				this.c.sendMessage("Not enough inventory space");
			}

		}
	}

	public void handleGhraak() {
		if (!NPCHandler.Summon(c.hasFollower, c)) {
			this.c.sendMessage("This is not your Familiar.");
		} else {
			this.c.sendMessage("Use the special attack button in your summoning tab.");
		}
	}

	public void handleSteel() {
		this.c.sendMessage("Use the special attack button in your sum tab..");
	}

	public void handleTort1() {
		if (!NPCHandler.Summon(c.hasFollower, c)) {
			this.c.sendMessage("This is not your Familiar.");
		} else {
			if (this.c.getItems().freeSlots() > 0 && this.c.rockTailCount > 0) {
				--this.c.rockTailCount;
				this.c.sendMessage("There are another " + this.c.rockTailCount
						+ " Rocktails in your BoB");
				this.c.getItems().addItem(15272, 1);
			}

			if (this.c.rockTailCount <= 0) {
				this.c.sendMessage("Your BoB Has no more supplies left");
			}

			if (this.c.getItems().freeSlots() <= 0) {
				this.c.sendMessage("Not enough inventory space");
			}

		}
	}

	public void handleUni() {
		if (!NPCHandler.Summon(c.hasFollower, c)) {
			this.c.sendMessage("This is not your Familiar.");
		} else {
			if (System.currentTimeMillis() - this.c.nextHeal > 5000L
					&& this.c.playerLevel[3] <= this.c.getPA().getLevelForXP(
							this.c.playerXP[3])) {
				this.c.playerLevel[3] += 5;
				this.c.getPA().refreshSkill(3);
				this.c.nextHeal = System.currentTimeMillis();
				this.c.healUni = true;
				this.c.sendMessage("The unicorn heals you 5 HP");
			}

			if (System.currentTimeMillis() - this.c.nextHeal <= 10000L) {
				this.c.sendMessage("You have to wait 5 seconds before performing another special move.");
			}

		}
	}

	public void handleYak1() {
		if (!NPCHandler.Summon(c.hasFollower, c)) {
			this.c.sendMessage("This is not your Familiar.");
		} else {
			if (this.c.getItems().freeSlots() > 0 && this.c.rockTailCount > 0) {
				--this.c.rockTailCount;
				this.c.sendMessage("There are another " + this.c.rockTailCount
						+ " Rocktails in your BoB");
				this.c.getItems().addItem(15272, 1);
			}

			if (this.c.rockTailCount <= 0) {
				this.c.sendMessage("Your BoB Has no more supplies left");
			}

			if (this.c.getItems().freeSlots() <= 0) {
				this.c.sendMessage("Not enough inventory space");
			}

		}
	}

	public void secondClickNpc(int npcType) {
		c.fishitem = -1;
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		if (c.fishitem != -1) {
			if (!c.getItems().playerHasItem(c.fishitem)) {
				c.sendMessage("You need a "
						+ c.getItems().getItemName(c.fishitem)
						+ " to fish for " + c.getItems().getItemName(c.fishies));
				c.fishing = false;
				return;
			}
			if (c.getItems().freeSlots() == 0) {
				c.sendMessage("Your inventory is full.");
				c.fishing = false;
				return;
			}
			if (Player.playerFishing < c.fishreqt) {
				c.sendMessage("You need a fishing level of " + c.fishreqt
						+ " to fish here.");
				c.fishing = false;
				return;
			}
			c.fishtimer = c.getFishing().fishtime(c.fishies, c.fishreqt);
		}
		switch (npcType) {
		case 4241:
			if (c.inConstruction()) {
				if (c.payButler()) {
					c.getDH().sendDialogues(4249, 1);
					c.SaveGame();
					return;
				}
				c.getDH().sendOption2("Fetch planks", "Fetch supplies");
				c.teleAction = 4284;
			}
			break;
		case 1777:
			c.getShops().openShop(ShopAssistant.PK_POINT_SHOP);
			break;
		case 166:
			c.getPA().openUpBank(0);
			break;
		case 883:
			c.getDH().sendDialogues(4819, 1);
			break;
		case 587:
			c.getShops().openShop(6);
			break;
		case 590:
			c.sendMessage("You now have a total of " + c.funPoints
					+ " FunPoints points!");
			c.getShops().openShop(21);
			break;
		case 884:
			c.sendMessage("You now have a total of " + c.votePoints
					+ " Voting points!");
			c.getShops().openShop(14);
			break;
		case 945:
			c.getPA().showInterface(16450);
			break;
		case 1282:
			this.c.getPA().refillSupplies();
			break;
		case 1588:
			this.handleGhraak();
			break;
		case 3590:
			this.handleTort1();
			break;
		case 3591:
			this.handleSteel();
			break;
		case 3592:
			this.handleUni();
			break;
		case 3594:
			this.handleYak1();
			break;
		case 3596:
			this.handleBird1();
			break;
		case 11226:
			c.getShops().openShop(53);
			break;
		case 3541:
			c.getShops().openShop(36);
			break;
		case 9085:
		case 1597:
			c.dialogueAction = 2521;
			c.teleAction = 0;
			c.getDH().sendOption2("Frost Dragons",
					"Red Dragons @red@(43 Wildy)");
			break;
		case 607:
			c.getDH().sendOption2("Agility Tickets Exchange",
					"Agility Points Exchange");
			c.dialogueAction = 2452;
			break;
		case 6574:
			c.sendMessage("This gravestone is level 1 and cannot be blessed.");
			break;
		case 4995:
			c.getPA().showInterface(17965);
			c.sendMessage("You currently have " + c.dominionPoints
					+ " Dominion Tower Points.");
			break;
		case 4247:
			if (c.hasHousee == true) {
				c.getPA().teleTabTeleport(1896, 5091, c.playerId * 4, "house");
				c.getPA().closeAllWindows();
				return;
			} else {
				if (c.hasHousee == false) {
					c.getDH().sendDialogues(29164, 0);
					return;
				}
			}
			break;

		case 586:
			c.getShops().openShop(32);
			break;
		case 794:
			c.getShops().openShop(50);
			break;
		case 219:
			c.getShops().openShop(48);
			break;
		case 3920:
			c.getShops().openShop(47);
			c.sendMessage("For better logs to fletch, you will have to cut them you're self.");
			break;
		case 4295:
			c.getShops().openShop(1);
			break;
		case 1167:
			c.getShops().openShop(44);
			break;
		case 7143:
			c.getShops().openShop(25);
			c.sendMessage("To get better logs you will have to cut them yourself.");
			break;
		case 455: // herblore
			// if(c.bMQ >= 3) {
			c.getShops().openShop(76);
			c.sendMessage("<shad=15369497>You need to get the herbs yourself - via farming/players.");
			/*
			 * } else if(c.bMQ == 0) { c.getDH().sendDialogues(2000, npcType); }
			 * else if (c.getItems().playerHasItem(2136, 1) &&
			 * c.getItems().playerHasItem(2134, 1) &&
			 * c.getItems().playerHasItem(2138, 1) && (c.bMQ == 1)) {
			 * c.getDH().sendDialogues(2012, npcType); return; } else if(c.bMQ
			 * == 1) {
			 * c.sendMessage("Bring me the following items before talking to me:"
			 * ); c.sendMessage(
			 * "Raw bear meat, from Varrock's woods, north east of Varrock Square.."
			 * ); c.sendMessage("Raw Chicken meat, from Camelot");
			 * c.sendMessage(
			 * "And finaly Raw Rat Meat, from Varrock multi level 1 Wilderness!"
			 * ); return; } else if (c.getItems().playerHasItem(523, 1) &&
			 * c.getItems().playerHasItem(524, 1) &&
			 * c.getItems().playerHasItem(525, 1) && (c.bMQ == 2)) {
			 * c.getDH().sendDialogues(2017, npcType); return; } else if(c.bMQ
			 * == 2) {
			 * c.sendMessage("Bring me the following items before talking to me:"
			 * ); c.sendMessage(
			 * "Enchanted Raw Rat Meat, Enchanted Raw Bear Meat, and Enchanted Chicken meat."
			 * ); return; }
			 */
			break;
		case 3299:
			c.getShops().openShop(26);
			break;
		case 4906: // woodcutter
			c.getShops().openShop(73);
			break;
		case 569: // crafter
			if (c.playerLevel[12] > 19) {
				c.getShops().openShop(71);
			} else {
				c.sendMessage("You need atleast a level of 20 to make amulets.");
				c.sendMessage("Train on flax till level 20!");
			}
			break;
		case 9713:
			// c.getShops().openShop(85);
			// c.sendMessage("You currently have " + c.dungPoints +
			// " Dungeoneering Tokens.");
			if (c.playerXP[24] >= 104273167) {
				c.getShops().openShop(67);
				c.sendMessage("Wow! Your 120 Dungeoneering so you may now acces the best-looking cape!");
				return;
			}
			if (c.playerLevel[24] > 98) {
				c.getShops().openShop(59);
				c.sendMessage("To acces the better-looking cape you need to be 120 Dungeoneering.");
			} else {
				c.sendMessage("You need atleast level 99 Dungeoneering to view that shop!");
			}
			break;
		case 9711:
			c.getShops().openShop(85);
			c.sendMessage("You currently have " + c.dungPoints
					+ " Dungeoneering Tokens.");
			break;
		case 705:
			c.sendMessage("" + c.dialogueAction + "");
			// c.sendMessage("To be able to wear Rune Platebody's, finnish Dragon Slayer! Talk to Kazgar");
			c.dialogueAction = 524;
			c.getDH().sendOption2("Armours", "Weapons");
			c.dialogueAction = 524;
			// c.getShops().openShop(5);
			break;

		/*
		 * case 462: // RUNE MYSTERIES if(c.rMQ == 2) {
		 * c.getPA().movePlayer(3105, 9576, 0); return; } else if(c.rMQ == 1) {
		 * c.sendMessage("Talk to me first!!"); return; } else if(c.rMQ == 6) {
		 * c.sendMessage("Go talk to aubury idiot"); return; } else if
		 * (c.getItems().playerHasItem(1436, 10) && (c.rMQ == 5)) {
		 * c.getPA().movePlayer(2842, 4832, 0);
		 * c.sendMessage("Return to Distentor when you've got the 10 Air Runes!"
		 * ); return; } else if(c.rMQ == 5) {
		 * c.sendMessage("Bring the stuff i wanted then talk to me.."); return;
		 * } break;
		 */
		case 526:
			c.getShops().openShop(14);
			break;
		/*
		 * case 1282: c.getShops().openShop(7); break;
		 */
		case 553:
			// if(c.rMQ >= 8) { // RUNE MYSTERIES DONE?
			c.getShops().openShop(19);
			// } else {
			// c.sendMessage("You need to finnish Rune mysteries before training RuneCrafting!");
			// }
			break;
		case 333:
			c.fishing = true;
			c.fishXP = 6000;
			c.sendMessage("Bonus XP: You receive 6K bonus XP for this catch.");
			c.fishies = 359;
			c.fishreqt = 35;
			c.fishitem = 311;
			c.fishemote = 618;
			c.fishies2 = 371;
			c.fishreq2 = 50;
			break;
		case 312:
			c.fishing = true;
			c.fishXP = 6000;
			c.fishies = 359;
			c.fishreqt = 35;
			c.fishitem = 311;
			c.fishemote = 618;
			c.fishies2 = 371;
			c.fishreq2 = 50;
			break;
		case 324:
			c.fishing = true;
			c.fishXP = 6000;
			c.sendMessage("Bonus XP: You receive 6K bonus XP for this catch.");
			c.fishies = 359;
			c.fishreqt = 35;
			c.fishitem = 311;
			c.fishemote = 618;
			c.fishies2 = 371;
			c.fishreq2 = 50;
			break;
		case 334:
			c.fishing = true;
			c.fishXP = 6000;
			c.sendMessage("Bonus XP: You receive 6K bonus XP for this catch.");
			c.fishies = 359;
			c.fishreqt = 35;
			c.fishitem = 311;
			c.fishemote = 618;
			c.fishies2 = 371;
			c.fishreq2 = 50;
			break;
		case 316:
			c.fishing = true;
			c.fishXP = 1500;
			c.fishies = 327;
			c.sendMessage("Bonus XP: You receive 1.5K bonus XP for this catch.");
			c.fishreqt = 5;
			c.fishitem = 307;
			c.fishemote = 622;
			c.fishies2 = 345;
			c.fishreq2 = 10;
			break;
		case 326:
			c.fishing = true;
			c.fishXP = 530;
			c.sendMessage("Bonus XP: You receive 2.5K bonus XP for this catch.");
			c.fishies = 327;
			c.fishreqt = 5;
			c.fishitem = 307;
			c.fishemote = 622;
			c.fishies2 = 345;
			c.fishreq2 = 10;
			break;
		case 331:
			c.fishing = true;
			c.fishXP = 2500;
			c.sendMessage("Bonus XP: You receive 2.5K bonus XP for this catch.");
			c.fishies = 349;
			c.fishreqt = 25;
			c.fishitem = 307;
			c.fishemote = 622;
			c.fishies2 = 0;
			c.fishreq2 = 0;

		case 313:
			c.fishing = true;
			c.fishXP = 15000;
			c.sendMessage("Bonus XP Week: You receive 15K Fishing XP!");
			c.fishies = 383;
			c.fishreqt = 79;
			c.fishitem = 311;
			c.fishemote = 618;
			c.fishies2 = 0;
			c.fishreq2 = 0;
			break;
		case 3788:
		case 3789:
			c.getPA().closeAllWindows();
			c.getPA().showInterface(18700);
			c.getPA().sendFrame126(Integer.toString(c.Commendations), 18729);
			break;
		case 494:
			c.getPA().openUpBank(0);
			break;
		case 27663:
			c.getPA().openUpBank(0);
			break;
		case 904:
			c.getShops().openShop(17);
			break;
		case 522:
		case 523:
			c.getShops().openShop(1);
			break;
		case 541:
			c.getShops().openShop(5);
			break;

		case 4707:
			c.getShops().openShop(2);
			break;

		case 1861:
			c.sendMessage("" + c.dialogueAction + "");
			c.dialogueAction = 894;
			c.getDH().sendOption2("Weapons & Armour", "Ammunition");
			c.dialogueAction = 893;
			break;

		case 8591:
			c.sendMessage("Kill me for rewards");
			break;

		case 540:
			c.getShops().openShop(27);
			c.sendMessage("To find out what scroll does what/or what scroll for what npc u need");
			c.sendMessage("Type ::scrolls");
			break;
		case 545:
			c.getShops().openShop(28);
			c.sendMessage("To find out what scroll does what/or what scroll for what npc u need");
			c.sendMessage("Type ::titans");
			break;

		case 209:
			c.getShops().openShop(74);
			break;

		case 659:
			c.getShops().openShop(72);
			break;

		case 170:
			c.getPA().showInterface(802);
			break;

		case 2538:
			c.getShops().openShop(6);
			break;

		case 519:
			c.getShops().openShop(8);
			break;
		case 1:
		case 9:
		case 18:
		case 20:
		case 26:
		case 21:
			c.getThieving().stealFromNPC(npcType);
			break;
		default:
			ScriptManager.callFunc("npcClick2_" + npcType, c, npcType);
			if (c.playerRights == 3)
				Misc.println("Second Click Npc : " + npcType);
			break;

		}
	}

	public void secondClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		c.sendMessage("Object type: " + objectType);
		switch (objectType) {
		case 6:
			c.getCannon().pickUpCannon();
			break;
		case 2282:
			c.antiSpamRope1 = false;
			c.sendMessage("false");
			if (c.absX == 2552 && c.absY == 3554) {
				c.getPA().movePlayer(2541, 3553, 0);
			}
			if (c.absX == 2550 && c.absY == 3554) {
				c.getPA().movePlayer(2541, 3553, 0);
			}
			if (c.absX == 2551 && c.absY == 3555) {
				c.getPA().movePlayer(2541, 3553, 0);
			}
			if (c.antiSpamRope1 == false && c.absX == 2541 && c.absY == 3553) {
				c.turnPlayerTo(c.objectX, c.objectY);
				c.startAnimation(3067);
				c.getPA().movePlayer(2551, 3549, 0);
				c.antiSpamRope1 = true;
			}
			break;
		case 26972:
			c.getPA().openUpBank(0);
			c.dialogueAction = 13813;
			break;
		case 3994:
			ConstructionEvents.MakeIronNails(c);
			break;
		case 2644: // flax spinnner!!
			c.spinFlax();
			break;
		case 2646: // flax picking
			c.turnPlayerTo(c.objectX, c.objectY);
			if (System.currentTimeMillis() - c.buryDelay > 2000) {
				if (c.getItems().freeSlots() > 0) {
					if (Misc.random(4) == 4) {
						c.getPA().replaceObject(c, -1, obX, obY, 10);
					}
					c.getItems().addItem(1779, 1);
					c.startAnimation(827);
					c.sendMessage("You pick some flax.");
					if (c.getItems().playerHasItem(1779, 2) && !c.task1[8]) {
						c.task1[8] = true;
						c.sendMessage("You've completed the task: Pick some Flax!");
						c.TPoints += 1;
						c.achievementInterface("Pick some Flax!");
						c.sendMessage("You receive one Task point! You now have a total of "
								+ c.TPoints + " points!");
					}
					c.buryDelay = System.currentTimeMillis();
				} else {
					c.sendMessage("You do not have enough inventory space.");
				}
			}
			break;
		case 28716: // SUMMON OBELISK blue one
		case 28722: // orange one
		case 28176: // orange one
			c.getPA().closeAllWindows();
			c.getPA().showInterface(23471);
			c.sendMessage("WARNING: This will create ALL pouches available in you're inventory.");
			break;
		case 11666:
		case 3044:
			c.getSmithing().sendSmelting();
			break;
		case 26288:
		case 26287:
		case 26286:
		case 26289:
			c.autoRet = 0;
			c.getCombat().resetPlayerAttack();
			c.getPA().movePlayer(2882, 5310, 2);
			c.sendMessage("You teleported out of the god's chamber.");
			break;
		case 2213:
		case 14367:
		case 24914:
		case 25808:
		case 11758:
		case 11402:
		case 6084:
		case 29085:
			c.getPA().openUpBank(0);
			break;
		case 26303:
			c.getPA().movePlayer(2872, 5279, 2);
			c.sendMessage("You have entered Armadyl.");
			break;
		case 2561:
			c.getThieving().stealFromStall(1897, 50, 40);
			break;

		case 1151:
			c.getThieving().stealFromStall(5561, 250, 99); // SAMY'S ::SZONE
															// FAIRY STALL!
			break;

		case 4874:
			c.getThieving().stealFromStall(1434, 100, 1); // uncut opal
			break;
		case 4875:
			c.getThieving().stealFromStall(1305, 130, 45); // raw swordie fish
			break;
		case 4876:
			c.getThieving().stealFromStall(3204, 150, 60); // diamond amulet
			break;
		case 4877:
			c.getThieving().stealFromStall(537, 190, 80); // orb of light
			break;
		case 4878:
			c.getThieving().stealFromStall(10462, 230, 95); // red topaz
			break;

		case 6163:
			c.getThieving().stealFromStall(2503, 120, 80);
			break;
		case 6165:
			c.getThieving().stealFromStall(4089, 170, 90);
			break;
		case 6166:
			c.getThieving().stealFromStall(2509, 200, 99);
			break;

		case 2558:
			if (System.currentTimeMillis() - c.lastLockPick < 3000
					|| c.freezeTimer > 0)
				break;
			if (c.getItems().playerHasItem(1523, 1)) {
				c.lastLockPick = System.currentTimeMillis();
				if (Misc.random(10) <= 3) {
					c.sendMessage("You fail to pick the lock.");
					break;
				}
				if (c.objectX == 3044 && c.objectY == 3956) {
					if (c.absX == 3045) {
						c.getPA().walkTo2(-1, 0);
					} else if (c.absX == 3044) {
						c.getPA().walkTo2(1, 0);
					}

				} else if (c.objectX == 3038 && c.objectY == 3956) {
					if (c.absX == 3037) {
						c.getPA().walkTo2(1, 0);
					} else if (c.absX == 3038) {
						c.getPA().walkTo2(-1, 0);
					}
				} else if (c.objectX == 3041 && c.objectY == 3959) {
					if (c.absY == 3960) {
						c.getPA().walkTo2(0, -1);
					} else if (c.absY == 3959) {
						c.getPA().walkTo2(0, 1);
					}
				}
			} else {
				c.sendMessage("I need a lockpick to pick this lock.");
			}
		case 2090:
		case 2091:
		case 3042:
		case 11938:
			// Mining.prospectRock(c, "copper ore");
			break;
		case 2094:
		case 2095:
		case 3043:
			// Mining.prospectRock(c, "tin ore");
			break;
		case 2110:
			// Mining.prospectRock(c, "blurite ore");
			break;
		case 2092:
		case 2093:
		case 11954:
			// Mining.prospectRock(c, "iron ore");
			break;
		case 2100:
		case 2101:
			// Mining.prospectRock(c, "silver ore");
			break;
		case 2098:
		case 2099:
			// Mining.prospectRock(c, "gold ore");
			break;
		case 2096:
		case 2097:
		case 11931:
		case 11930:
			// Mining.prospectRock(c, "coal");
			break;
		case 2102:
		case 2103:
		case 11942:
			// Mining.prospectRock(c, "mithril ore");
			break;
		case 2104:
		case 2105:
		case 11939:
			// Mining.prospectRock(c, "adamantite ore");
			break;
		case 2106:
		case 2107:
		case 14859:
			// Mining.prospectRock(c, "runite ore");
			break;
		case 450:
		case 451:
			// Mining.prospectNothing(c);
			break;
		default:
			ScriptManager.callFunc("objectClick2_" + objectType, c, objectType,
					obX, obY);
			break;
		}
	}

	public void store(int i, int npcType) {

		switch (npcType) {
		case 6807:
			if (NPCHandler.npcs[i].npcId == c.summoningnpcid) {
				c.sendMessage("You are now storing items inside your npc");
				c.Summoning().store();
			}
			break;
		}
	}

	public void thirdClickNpc(int npcType) {
		c.clickNpcType = 0;
		c.npcClickIndex = 0;
		switch (npcType) {
		case 9085:
		case 1597:
			c.getShops().openShop(18);
			break;
		case 607:
			/*
			 * if(c.playerLevel[Player.playerAgility] == 80 ||
			 * c.playerLevel[Player.playerAgility] > 79) {
			 * c.getPA().movePlayer(2551, 3546, 0);
			 * c.sendMessage("THE ROPE SWING DOES NOT WORK!"); } else {
			 * c.sendMessage
			 * ("You need atleast an Agility level of 80 to teleport there.");
			 * return; }
			 */
			c.sendMessage("Wilderness Agility Course coming soon!");

			break;
		case 6574:
			c.killGraveStone();
			break;
		case 4247:
			c.getShops().openShop(98);
			break;
		case 794:
			if (c.playerLevel[7] == 99) {
				c.getShops().openShop(51);
				c.sendMessage("Welcome, master Cook " + c.playerName + "!");
			} else {
				c.sendMessage("You're not level 99 in Cooking yet!");
			}
			break;
		case 219:
			if (c.playerLevel[10] == 99) {
				c.getShops().openShop(49);
				c.sendMessage("Welcome, master Fisher " + c.playerName + "!");
			} else {
				c.sendMessage("You're not level 99 in Fishing yet!");
			}
			break;
		case 3920:
			if (c.playerLevel[9] == 99) {
				c.getShops().openShop(70);
				c.sendMessage("Welcome, master Fletcher " + c.playerName + "!");
			} else {
				c.sendMessage("You're not level 99 in Fletching yet!");
			}
			break;
		case 4295:
			if (c.playerLevel[17] == 99) {
				c.getShops().openShop(35);
				c.sendMessage("Welcome, master Thief " + c.playerName + "!");
			} else {
				c.sendMessage("You're not level 99 in Thieving yet!");
			}
			break;
		case 553:
			if (c.playerLevel[20] == 99) {
				c.getShops().openShop(30);
				c.sendMessage("Welcome, master RuneCrafter " + c.playerName
						+ "!");
			} else {
				c.sendMessage("You're not level 99 in Runecrafting!");
			}
			break;

		case 1167: // hunter
			if (c.playerLevel[22] == 99) {
				c.getShops().openShop(45);
				c.sendMessage("Welcome, master Hunter " + c.playerName + "!");
			} else {
				c.sendMessage("You're not level 99 in Hunter.");
			}
			break;
		case 7143:
			if (c.playerLevel[11] == 99) {
				c.getShops().openShop(29);
				c.sendMessage("Welcome, master FireMaker " + c.playerName + "!");
			} else {
				c.sendMessage("You're not level 99 in FireMaking!");
			}
			break;

		case 455:
			if (c.playerLevel[15] == 99) {
				c.getShops().openShop(24);
				c.sendMessage("Welcome, master Potion Maker " + c.playerName
						+ "!");
			} else {
				c.sendMessage("You're not level 99 in Herblore yet!");
			}
			break;
		case 569:
			if (c.playerLevel[12] == 99) {
				c.getShops().openShop(22);
				c.sendMessage("Welcome, master Crafter " + c.playerName + "!");
			} else {
				c.sendMessage("You're not level 99 in Crafting yet!");
			}
			break;
		case 3299:
			if (c.playerLevel[19] == 99) {
				c.getShops().openShop(23);
				c.sendMessage("Welcome, master Farmer " + c.playerName + "!");
			} else {
				c.sendMessage("Achieve 99 Farming first!");
			}
			break;
		case 4906: // woodcutter
			if (c.playerLevel[8] == 99) {
				c.getShops().openShop(75);
				c.sendMessage("Welcome, master WoodCutter " + c.playerName
						+ "!");
			} else {
				c.sendMessage("Achieve 99 WoodCutting first! Go Chop' Chop'!");
			}
			break;

		}
	}

	public void thirdClickObject(int objectType, int obX, int obY) {
		c.clickObjectType = 0;
		if (c.playerRights == 3 && c.playerRights != 1) {
			c.sendMessage("Object type: " + objectType);
		}
		switch (objectType) {
		default:
			ScriptManager.callFunc("objectClick3_" + objectType, c, objectType,
					obX, obY);
			break;
		}
	}
}