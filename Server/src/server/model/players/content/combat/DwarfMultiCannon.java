package server.model.players.content.combat;

import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.objects.Objects;
import server.model.players.Client;
import server.model.players.content.skills.impl.ABEPouchMakinByGabbe;
import server.util.Misc;

public class DwarfMultiCannon {

	/**
	 * To-Do: Exception when trying to set up a cannon within 3 coords of
	 * 
	 * 
	 * @author relex lawl / relex
	 */

	private Client player;

	private static final int CANNON = 6;

	private static final int CANNONBALL = 2, CANNON_BASE_ID = 6,
			CANNON_STAND_ID = 8, CANNON_BARRELS_ID = 10,
			CANNON_FURNACE_ID = 12;
	public static boolean objectsNeedsRestart = false;

	public static void checkNPCDistance(Client players) {
		if (players == null)
			return;
		NPC n = getNPCWithinDistance(players, players.cannonBaseX,
				players.cannonBaseY, players.cannonBaseH);
		if (n == null) {
			return;
		}
		int damage = Misc.random(38);
		if (players.inMulti() && n.inMulti()) {
			startCannonballProjectile(players, players.oldCannon, n);
			n.hitIcon = 1;
			n.hitDiff = damage;
			n.HP -= damage;
			n.hitUpdateRequired = true;
			n.killerId = players.playerId;
			n.facePlayer(players.playerId);
			ABEPouchMakinByGabbe.addSkillXP2(players, damage * 200, 4);
			// n.forceChat("im hit, multi");
		} else {
			if (n.underAttackBy > 0 && n.underAttackBy != players.playerId)
				return;
			startCannonballProjectile(players, players.oldCannon, n);
			n.hitIcon = 1;
			n.hitDiff = damage;
			n.HP -= damage;
			n.hitUpdateRequired = true;
			n.killerId = players.playerId;
			n.facePlayer(players.playerId);
			// n.forceChat("im hit, single");
			ABEPouchMakinByGabbe.addSkillXP2(players, damage * 200, 4);
		}
		players.cannonBalls--;

	}

	public static int distanceToSquare(int x, int y, int tx, int ty) {
		return (int) Math.sqrt((Math.abs(x - tx) + Math.abs(y - ty)));
	}

	private static NPC getNPCWithinDistance(Client players, int x, int y,
			int height) {
		NPC npc = null; // try now
		for (int i = 0; i < NPCHandler.npcs.length; i++) {
			if (NPCHandler.npcs[i] != null) {
				npc = NPCHandler.npcs[i];

				int myX = players.cannonBaseX;
				int myY = players.cannonBaseY;
				int theirX = npc.absX;
				int theirY = npc.absY;
				if (!npc.isDead && npc.heightLevel == height && !npc.isDead
						&& npc.HP != 0 && npc.npcType != 1266
						&& npc.npcType != 1268
						&& npc.npcType != players.hasFollower) {
					switch (players.rotation) {
					case 1: // north
						if (theirY > myY && theirX >= myX - 1
								&& theirX <= myX + 1)
							return npc;
						break;
					case 2: // north-east
						if (theirX >= myX + 1 && theirY >= myY + 1)
							return npc;
						break;
					case 3: // east
						if (theirX > myX && theirY >= myY - 1
								&& theirY <= myY + 1)
							return npc;
						break;
					case 4: // south-east
						if (theirY <= myY - 1 && theirX >= myX + 1)
							return npc;
						break;
					case 5: // south
						if (theirY < myY && theirX >= myX - 1
								&& theirX <= myX + 1)
							return npc;
						break;
					case 6: // south-west
						if (theirX <= myX - 1 && theirY <= myY - 1)
							return npc;
						break;
					case 7: // west
						if (theirX < myX && theirY >= myY - 1
								&& theirY <= myY + 1)
							return npc;
						break;
					case 8: // north-west
						if (theirX <= myX - 1 && theirY >= myY + 1)
							return npc;
						break;
					}
				}
			}
		}
		return null;
	}

	private static void startCannonballProjectile(Client player,
			Objects cannon, NPC n) {
		int oX = Objects.objectX;
		int oY = Objects.objectY;
		int offX = ((oX - n.getX()) * -1);
		int offY = ((oY - n.getY()) * -1);
		player.getPA().createPlayersProjectile(oX, oY, offY, offX, 50, 60, 53,
				20, 20, -player.oldNpcIndex + 1, 30);
	}

	public DwarfMultiCannon(Client client) {
		this.player = client;
	}

	public void bankCannon() {
		Objects cannon = null;
		for (server.model.objects.Objects o : Server.objectHandler.globalObjects) {
			if (Objects.objectX == player.cannonBaseX
					&& Objects.objectY == player.cannonBaseY
					&& o.objectHeight == player.cannonBaseH) {
				cannon = o;
			}
		}
		if (cannon == null) {
			player.sendMessage("This is not your cannon!");
			return;
		}
		player.startAnimation(827);
		server.model.objects.Objects empty = new server.model.objects.Objects(
				100, Objects.objectX, Objects.objectY, 0, 0, 10, 0);
		Server.objectHandler.addObject(empty);
		Server.objectHandler.placeObject(empty);
		Server.objectHandler.removeObject(empty);
		player.getItems().addItemToBank(CANNON_BASE_ID, 1);
		player.getItems().addItemToBank(CANNON_STAND_ID, 1);
		player.getItems().addItemToBank(CANNON_BARRELS_ID, 1);
		player.getItems().addItemToBank(CANNON_FURNACE_ID, 1);
		if (player.bankCannonBalls) {
			player.getItems().addItemToBank(CANNONBALL, player.cannonBalls);
		}
		player.hasCannon = false;
		player.cannonBalls = 0;
		Client.stopEventCannon = true;
		player.sendMessage("Your cannon parts were banked..");
		return;

	}

	public boolean cannonActive() {
		if (player.hasCannon && player.cannonIsShooting)
			return true;
		else
			return false;
	}

	public void pickUpCannon() {
		Objects cannon = null;
		for (server.model.objects.Objects o : Server.objectHandler.globalObjects) {
			if (Objects.objectX == player.cannonBaseX
					&& Objects.objectY == player.cannonBaseY
					&& o.objectHeight == player.cannonBaseH) {
				cannon = o;
			}
		}
		if (cannon == null) {
			player.sendMessage("This is not your cannon!");
			return;
		}
		if (player.getItems().freeSlots() < 5) {
			player.sendMessage("You do not have enough inventory space!");
			return;
		}
		player.startAnimation(827);
		server.model.objects.Objects empty = new server.model.objects.Objects(
				100, Objects.objectX, Objects.objectY, 0, 0, 10, 0);
		Server.objectHandler.addObject(empty);
		Server.objectHandler.placeObject(empty);
		Server.objectHandler.removeObject(empty);

		player.getItems().addItem(CANNON_BASE_ID, 1);
		player.getItems().addItem(CANNON_STAND_ID, 1);
		player.getItems().addItem(CANNON_BARRELS_ID, 1);
		player.getItems().addItem(CANNON_FURNACE_ID, 1);
		player.getItems().addItem(CANNONBALL, player.cannonBalls);
		player.cannonBalls = 0;
		Client.stopEventCannon = true;
		player.hasCannon = false;
		return;
	}

	private void restartCannon(final Objects cannon) {
		CycleEventHandler.getSingleton().stopEvents(player, 3); // STOP THE
																// EVENT FIRST
																// BEFORE
																// RUNNING IT
																// :):)

		CycleEventHandler.getSingleton().addEvent(3, player, new CycleEvent() {
			public void execute(CycleEventContainer e) {
				Client.stopEventCannon = false;
				player.cannonIsShooting = false;
				Client.restart = false;
				player.cannonBalls = player.cannonBalls;
				startFiringCannon(cannon, player);
				// player.sendMessage("Your cannon restarted. Cannonballs: "+player.cannonBalls+"");
				e.stop();
			}

			@Override
			public void stop() {

			}
		}, 1);

	}

	private void rotateCannon(Objects cannon) {
		switch (player.rotation) {
		case 1: // north
			player.getPA().objectAnim(Objects.objectX, Objects.objectY, 516,
					10, -1);
			break;
		case 2: // north-east
			player.getPA().objectAnim(Objects.objectX, Objects.objectY, 517,
					10, -1);
			break;
		case 3: // east
			player.getPA().objectAnim(Objects.objectX, Objects.objectY, 518,
					10, -1);
			break;
		case 4: // south-east
			player.getPA().objectAnim(Objects.objectX, Objects.objectY, 519,
					10, -1);
			break;
		case 5: // south
			player.getPA().objectAnim(Objects.objectX, Objects.objectY, 520,
					10, -1);
			break;
		case 6: // south-west
			player.getPA().objectAnim(Objects.objectX, Objects.objectY, 521,
					10, -1);
			break;
		case 7: // west
			player.getPA().objectAnim(Objects.objectX, Objects.objectY, 514,
					10, -1);
			break;
		case 8: // north-west
			player.getPA().objectAnim(Objects.objectX, Objects.objectY, 515,
					10, -1);
			player.rotation = 0;
			break;
		}
		if (player.rotation >= 8) {
			player.rotation = 0;
		}
	}

	public void setUpCannon() {
		if (player.hasCannon == true) {
			player.sendMessage("You've already got a cannon setup!");
			return;
		}
		if (player.inRimmington() || player.InDung() || player.inDuelArena()) {
			player.sendMessage("You cannot setup a cannon here!");
			return;
		}
		// if (!canSetUpCannon() || inGoodArea())
		// return;
		if (!player.getItems().playerHasItem(CANNON_BASE_ID)
				|| !player.getItems().playerHasItem(CANNON_STAND_ID)
				|| !player.getItems().playerHasItem(CANNON_BARRELS_ID)
				|| !player.getItems().playerHasItem(CANNON_FURNACE_ID)) {
			player.sendMessage("You don't have the required items to setup the cannon.");
			return;
		}
		CycleEventHandler.getSingleton().stopEvents(player, 2); // STOP THE
																// EVENT FIRST
																// BEFORE
																// RUNNING A NEW
																// ONE :):)
		CycleEventHandler.getSingleton().addEvent(2, player, new CycleEvent() {
			public void execute(CycleEventContainer e) {
				if (player.getItems().playerHasItem(CANNON_BASE_ID)
						&& player.getItems().playerHasItem(CANNON_STAND_ID)
						&& player.getItems().playerHasItem(CANNON_BARRELS_ID)
						&& player.getItems().playerHasItem(CANNON_FURNACE_ID)) {
					Client.stopEventCannon = false;
					player.startAnimation(827);
					Objects cannon = new Objects(CANNON, player.absX,
							player.absY, 0, 0, 10, 0);
					player.cannonBaseX = player.absX;
					player.cannonBaseY = player.absY;
					player.cannonBaseH = player.heightLevel;
					Server.objectHandler.addObject(cannon);
					Server.objectHandler.placeObject(cannon);
					player.getPA().checkObjectSpawn(CANNON, player.absX,
							player.absY, 10, 0);
					player.getItems().deleteItem(CANNON_BASE_ID, 1);
					player.getItems().deleteItem(CANNON_STAND_ID, 1);
					player.getItems().deleteItem(CANNON_BARRELS_ID, 1);
					player.getItems().deleteItem(CANNON_FURNACE_ID, 1);
					Objects.belongsTo = player.playerName;
					player.oldCannon = cannon;
					player.hasCannon = true;
					player.cannonIsShooting = false;
					player.settingUpCannon = false;
					e.stop();
					return;
				}
				e.stop(); // stops event

			}

			@Override
			public void stop() {
			}
		}, 1);
	}

	public void shootCannon() {
		Objects cannon = null;
		for (server.model.objects.Objects o : Server.objectHandler.globalObjects) {
			if (Objects.objectX == player.cannonBaseX
					&& Objects.objectY == player.cannonBaseY
					&& o.objectHeight == player.cannonBaseH) {
				cannon = o;
			}
		}
		if (cannon == null) {
			player.sendMessage("This is not your cannon!");
			return;
		}
		if (player.cannonIsShooting) {
			player.sendMessage("Your cannon is already firing!");
			return;
		}
		if (player.cannonIsShooting) {
			if (player.getItems().playerHasItem(CANNONBALL)) {
				int amountOfCannonBalls = player.getItems().getItemAmount(
						CANNONBALL) > 30 ? 30 : player.getItems()
						.getItemAmount(CANNONBALL);
				player.cannonBalls += amountOfCannonBalls;

			} else {
				player.sendMessage("Your cannon is already firing!");
				return;
			}
		}
		if (player.cannonBalls < 1) {
			int amountOfCannonBalls = player.getItems().getItemAmount(
					CANNONBALL) > 30 ? 30 : player.getItems().getItemAmount(
					CANNONBALL);
			if (amountOfCannonBalls < 1 && amountOfCannonBalls != 1) {
				player.sendMessage("You don't have any cannonballs!");
				return;
			}
			player.cannonBalls = amountOfCannonBalls;
			player.getItems().deleteItem(CANNONBALL,
					player.getItems().getItemSlot(CANNONBALL),
					amountOfCannonBalls);
			shootCannon();
		} else
			restartCannon(cannon);
		Client.stopEventCannon = false;
	}

	private void startFiringCannon(final Objects cannon, final Client c) {
		CycleEventHandler.getSingleton().stopEvents(c, 1); // STOP THE EVENT
															// FIRST BEFORE
															// RUNNING IT :):)

		CycleEventHandler.getSingleton().addEvent(1, c, new CycleEvent() {
			public void execute(CycleEventContainer e) {
				if (Client.stopEventCannon) {
					e.stop();
					return;
				}
				if (player.cannonBalls > 0) {
					player.cannonIsShooting = true;
					player.rotation++;
					rotateCannon(cannon);
					checkNPCDistance(player);
				} else {
					player.sendMessage("Your cannon has run out of ammo!");
					player.cannonIsShooting = false;
					Client.stopEventCannon = true;
					e.stop();
				}
			}

			@Override
			public void stop() {
				if (Client.stopEventCannon) {
					Client.stopEventCannon = true;
					player.cannonIsShooting = false;
					c.cannonBalls = c.cannonBalls;
					if (Client.restart == true) {
						restartCannon(cannon);
						return;
					}
				}
			}
		}, 1);
	}
}