package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;
import server.world.clip.region.Region;

/**
 * Click Object
 */
public class ClickObject implements PacketType {

	public static final int FIRST_CLICK = 132, SECOND_CLICK = 252,
			THIRD_CLICK = 70;

	public void handleSpecialCase(Client c, int id, int x, int y) {

	}

	int[] extras = { 2483, 2480, 8972, 30624, 29537, 2908, 2909, 3782, 15478, 26972, 23271,
			21600, 12356, 2213, 15638, 1738, 15641, 15644, 15653, 6771, 6822,
			6705, 6706, 6772, 6773, 6774, 6775, 6704, 6703, 6702, 6701, 6821,
			6822, 6823, 6707, 6706, 6708, 24357, 28716, 31070, 31071, 31073,
			31068, 31077, 31078, 31079, 31083, 31085, 31081, 31080, 31072,
			31086, 31088, 31069, 31065, 31066, 26847, 17010};

	private boolean isExtra(int object) {
		for (int e : extras)
			if (object == e)
				return true;
		return false;
	}

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		c.clickObjectType = c.objectX = c.objectId = c.objectY = 0;
		c.objectYOffset = c.objectXOffset = 0;
		c.getPA().resetFollow();
		c.getCombat().resetPlayerAttack();
		c.getPA().requestUpdates();
		switch (packetType) {

		case FIRST_CLICK:
			c.objectX = c.getInStream().readSignedWordBigEndianA();
			c.objectId = c.getInStream().readUnsignedWord();
			c.objectY = c.getInStream().readUnsignedWordA();
			c.objectDistance = 1;
			// Barrows & Warriors Guild & GWD object fix 29537
			if (c.inCwGame == false && !c.atMining() && !c.isInPrivCon()
					&& !c.inPits() && !c.inFunPk() && !c.atFarming()
					&& !c.AtCorp() && !c.InDung() && !c.inDungBossRoom()
					&& !c.inRFD() && !c.inFightCaves() && !c.inGWD()
					&& !isExtra(c.objectId)) {
				if (!c.inGWD() && !c.inBarrows() && !c.inWarriorG()) {
					if (!Region.objectExists(c.objectX, c.objectY,
							c.heightLevel, c.objectId)) {
						System.out.println("Nonexisting " + c.objectId
								+ "  was clicked by " + c.playerName + " ");
						return;
					}
				}
			}

			if (c.playerRights == 3) {
				Misc.println("objectId: " + c.objectId + "  ObjectX: "
						+ c.objectX + "  objectY: " + c.objectY + " Xoff: "
						+ (c.getX() - c.objectX) + " Yoff: "
						+ (c.getY() - c.objectY));
			}
			if (Math.abs(c.getX() - c.objectX) > 25
					|| Math.abs(c.getY() - c.objectY) > 25) {
				c.resetWalkingQueue();
				break;
			}

			switch (c.objectId) {
			case 28716:// Makes summon obelisk work..
				c.objectXOffset = 2;
				c.objectYOffset = 2;
				c.objectDistance = 2;
				break;
			case 7:
			case 8:
			case 9:
				c.getCannon().pickUpCannon();
				break;

			case 6:
				c.getCannon().shootCannon();
				break;
			case 2478:
			case 2479:
			case 2480:
			case 2481:
			case 2482:
			case 2483:
			case 2484:
			case 2485:
			case 2486:
			case 2487:
			case 2488:
			case 2489:
			case 2490:
			case 17010:
			case 30624:
				c.objectDistance = 3;
				break;

			case 350:
				c.sendMessage("Stop looking for pornos in my drawers!");
				break;

			case 4617:
				c.sendMessage("Leave my books alone!");
				break;

			case 3822:
				c.sendMessage("~The finest weed throughout Gielinor~");
				break;
			/*
			 * CastleWars
			 */

			case 881:
				c.getPA().movePlayer(3237, 9858, 0);
				c.sendMessage("Welcome to Varrock Sewers");
				break;
			case 2909:
				c.getPA().movePlayer(2791, 9329, 4);
				c.sendMessage("You have summoned the balance elemental prepare your self!");
				break;
			case 2908:
				c.getPA().movePlayer(2791, 9329, 4);
				c.sendMessage("You have summoned the balance elemental prepare your self!");
				break;
			case 27568:
				c.getPA().spellTeleport(3194, 5446, 0);
				c.sendMessage("Welcome to the Chaos tunnel");
				break;
			case 4389: // sara
			case 4390: // zammy waiting room portal
				break;
			case 3566:// rope's dont work yet
				if (c.absX == 2806 && c.absY == 9587) {
					c.startAnimation(2750);
					c.getPA().movePlayer(2806, 9582, 3);
					c.teleEndAnimation = 2588;
				} else if (c.absX == 2764 && c.absY == 9569) {
					c.startAnimation(2750);
					c.getPA().movePlayer(2804, 9587, 3);
					c.teleEndAnimation = 2588;
				} else if (c.absX == 2764 && c.absY == 9569) {
					c.startAnimation(2750);
					c.getPA().movePlayer(2769, 9569, 3);
					c.teleEndAnimation = 2588;
				} else if (c.absX == 2769 && c.absY == 9567) {
					c.startAnimation(2750);
					c.getPA().movePlayer(2764, 9567, 3);
					c.teleEndAnimation = 2588;
				} else if (c.absX == 2804 && c.absY == 9582) {
					c.startAnimation(2750);
					c.getPA().movePlayer(2804, 9587, 3);
					c.teleEndAnimation = 2588;
				}
				break;
			case 4428:
			case 4427:
				if (c.absX == 2372 && c.absY == 3119) {
					c.getPA().movePlayer(2372, 3120, 0);
				} else {
					if (c.absX == 2373 && c.absY == 3119) {
						c.getPA().movePlayer(2373, 3120, 0);
					} else {
						if (c.absX == 2372 && c.absY == 3120) {
							c.getPA().movePlayer(2372, 3119, 0);
						} else {
							if (c.absX == 2373 && c.absY == 3120) {
								c.getPA().movePlayer(2373, 3119, 0);
							}
						}
					}
				}
				break;
			case 4465:
				if (c.absX == 2415 && c.absY == 3073) {
					c.getPA().movePlayer(2414, 3073, 0);
				} else {
					if (c.absX == 2411 && c.absY == 3073) {
						c.getPA().movePlayer(2415, 3073, 0);
					}
				}
				break;
			case 3565:// come back to this
				if (c.absX == 2805 && c.absY == 9564) {
					c.getPA().walkTo(0, -2);
					c.startAnimation(839);
				} else if (c.absX == 2804 && c.absY == 9582) {
					c.getPA().walkTo(0, 2);
					c.startAnimation(839);
				} else if (c.absX == 2779 && c.absY == 9590) {
					c.startAnimation(839);
					c.getPA().walkTo(-3, 0);
				} else if (c.absX == 2805 && c.absY == 9564) {
					c.startAnimation(839);
					c.getPA().walkTo(0, 3);
				} else if (c.absX == 2805 && c.absY == 9561) {
					c.startAnimation(839);
					c.getPA().walkTo(0, 3);
				} else if (c.absX == 2783 && c.absY == 9561) {
					c.startAnimation(839);
					c.getPA().walkTo(0, 3);
				} else if (c.absX == 2783 && c.absY == 9564) {
					c.startAnimation(839);
					c.getPA().walkTo(0, -3);
				} else if (c.absX == 2776 && c.absY == 9590) {
					c.startAnimation(839);
					c.getPA().walkTo(3, 0);
				} else if (c.absX == 2779 && c.absY == 9590) {
					c.startAnimation(839);
					c.getPA().walkTo(-3, 0);
				}
				break;
			case 23271:
				if (c.absY == 3523) {
					c.WildDitch(2750, 1, 0, -3, 1000, 1500,
							"You cross the ditch..");
				} else if (c.absY == 3520) {
					c.WildDitch(2750, 1, 0, 3, 1000, 1500,
							"You cross the ditch..");
				}
				break;

			case 1733:
				c.objectYOffset = 2;
				break;

			case 26288:
			case 26287:
			case 26286:
			case 26289:
			case 1738:
				c.objectDistance = 3;
				break;

			case 3192:
				c.objectDistance = 7;
				break;

			case 4058:
			case 154:
				c.objectDistance = 7;
				break;

			case 6451:
			case 6452:
				c.objectDistance = 1;
				break;

			case 3044:
				c.objectDistance = 3;
				break;

			case 245:
				c.objectYOffset = -1;
				c.objectDistance = 0;
				break;

			case 272:
				c.objectYOffset = 1;
				c.objectDistance = 0;
				break;

			case 273:
				c.objectYOffset = 1;
				c.objectDistance = 0;
				break;

			case 246:
				c.objectYOffset = 1;
				c.objectDistance = 0;
				break;

			case 4493:
			case 4494:
			case 4496:
			case 4495:
				c.objectDistance = 5;
				break;
			case 10229:
			case 6522:
				c.objectDistance = 2;
				break;
			case 8959:
				c.objectYOffset = 1;
				break;
			case 4417:
				if (c.objectX == 2425 && c.objectY == 3074)
					c.objectYOffset = 2;
				break;
			case 4420:
				if (c.getX() >= 2383 && c.getX() <= 2385) {
					c.objectYOffset = 1;
				} else {
					c.objectYOffset = -2;
				}
			case 6552:
			case 409:
				c.objectDistance = 2;
				break;
			case 2879:
			case 2878:
				c.objectDistance = 3;
				break;
			case 2558:
				c.objectDistance = 0;
				if (c.absX > c.objectX && c.objectX == 3044)
					c.objectXOffset = 1;
				if (c.absY > c.objectY)
					c.objectYOffset = 1;
				if (c.absX < c.objectX && c.objectX == 3038)
					c.objectXOffset = -1;
				break;
			case 9356:
				c.objectDistance = 2;
				break;
			case 5959:
			case 1815:
			case 5960:
			case 1816:
				c.objectDistance = 0;
				break;

			case 9293:
				c.objectDistance = 2;
				break;
			case 4418:
				if (c.objectX == 2374 && c.objectY == 3131)
					c.objectYOffset = -2;
				else if (c.objectX == 2369 && c.objectY == 3126)
					c.objectXOffset = 2;
				else if (c.objectX == 2380 && c.objectY == 3127)
					c.objectYOffset = 2;
				else if (c.objectX == 2369 && c.objectY == 3126)
					c.objectXOffset = 2;
				else if (c.objectX == 2374 && c.objectY == 3131)
					c.objectYOffset = -2;
				break;
			case 9706:
				c.objectDistance = 0;
				c.objectXOffset = 1;
				break;
			case 9707:
				c.objectDistance = 0;
				c.objectYOffset = -1;
				break;

			case 13999:
				c.getPA().startTeleport(3087, 3498, 0, "modern");
				c.teleportToX = 3093;
				c.teleportToY = 3487;

				break;
			case 4419:
			case 6707: // verac
				c.objectYOffset = 3;
				break;
			case 6823:
				c.objectDistance = 2;
				c.objectYOffset = 1;
				break;

			case 6706: // torag
				c.objectXOffset = 2;
				break;
			case 6772:
				c.objectDistance = 2;
				c.objectYOffset = 1;
				break;

			case 6705: // karils
				c.objectYOffset = -1;
				break;
			case 6822:
				c.objectDistance = 2;
				c.objectYOffset = 1;
				break;

			case 6704: // guthan stairs
				c.objectYOffset = -1;
				break;
			case 6773:
				c.objectDistance = 2;
				c.objectXOffset = 1;
				c.objectYOffset = 1;
				break;

			case 6703: // dharok stairs
				c.objectXOffset = -1;
				break;
			case 6771:
				c.objectDistance = 2;
				c.objectXOffset = 1;
				c.objectYOffset = 1;
				break;

			case 6702: // ahrim stairs
				c.objectXOffset = -1;
				break;
			case 6821:
				c.objectDistance = 2;
				c.objectXOffset = 1;
				c.objectYOffset = 1;
				break;
			case 1276:
			case 1278:// trees
			case 1281: // oak
			case 1308: // willow
			case 1307: // maple
			case 1309: // yew
			case 1306: // yew
				c.objectDistance = 3;
				break;
			default:
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
				break;
			}
			if (c.goodDistance(c.objectX + c.objectXOffset, c.objectY
					+ c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
				c.getActions().firstClickObject(c.objectId, c.objectX,
						c.objectY);
			} else {
				c.clickObjectType = 1;

			}
			break;

		case SECOND_CLICK:
			c.objectId = c.getInStream().readUnsignedWordBigEndianA();
			c.objectY = c.getInStream().readSignedWordBigEndian();
			c.objectX = c.getInStream().readUnsignedWordA();
			c.objectDistance = 1;
			if (c.playerRights == 6) {
				Misc.println("objectId: " + c.objectId + "  ObjectX: "
						+ c.objectX + "  objectY: " + c.objectY + " Xoff: "
						+ (c.getX() - c.objectX) + " Yoff: "
						+ (c.getY() - c.objectY));
			}

			switch (c.objectId) {
			case 28716:// Makes summon obelisk work..
				c.objectXOffset = 2;
				c.objectYOffset = 2;
				c.objectDistance = 2;
				break;
			case 13581:
			case 13641:
			case 13411:
			case 13151:
			case 13648:
			case 13191:
				c.handleRemoval();
				break;
			case 6163:
			case 6165:
			case 6166:
			case 6164:
			case 6162:
				c.objectDistance = 2;
				break;
			case 26288:
			case 26287:
			case 26286:
			case 26289:
				c.objectDistance = 3;
				break;
			default:
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
				break;

			}
			if (c.goodDistance(c.objectX + c.objectXOffset, c.objectY
					+ c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
				c.getActions().secondClickObject(c.objectId, c.objectX,
						c.objectY);
			} else {
				c.clickObjectType = 2;
			}
			break;

		case THIRD_CLICK:
			c.objectX = c.getInStream().readSignedWordBigEndian();
			c.objectY = c.getInStream().readUnsignedWord();
			c.objectId = c.getInStream().readUnsignedWordBigEndianA();

			if (c.playerRights == 6) {
				Misc.println("objectId: " + c.objectId + "  ObjectX: "
						+ c.objectX + "  objectY: " + c.objectY + " Xoff: "
						+ (c.getX() - c.objectX) + " Yoff: "
						+ (c.getY() - c.objectY));
			}

			switch (c.objectId) {
			default:
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
				break;
			}
			if (c.goodDistance(c.objectX + c.objectXOffset, c.objectY
					+ c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
				c.getActions().secondClickObject(c.objectId, c.objectX,
						c.objectY);
			} else {
				c.clickObjectType = 3;
			}
			break;
		}

	}

}
