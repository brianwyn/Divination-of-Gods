package server.model.players.content;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import server.Config;
import server.Server;
import server.model.items.Item;
import server.model.npcs.NPCHandler;
import server.model.objects.Objects;
import server.model.players.Client;
import server.model.players.PlayerHandler;

public class PartyRoom {
	static Random r = new Random();
	public static int[] roomItems = new int[50];
	public static int[] roomItemsN = new int[50];
	static ArrayList<Point> coords = new ArrayList<Point>();
	public static int counterDelay = 0; // Delay for countdown timer
	public static int counterNpc = 659; // Npc that counts down
	public static long lastPRC;
	public static long lastPRK;
	public static int knightStage = 10;

	public static void accept(Client c) {
		for (int x = 0; x < c.party.length; x++) {
			if (c.partyN[x] > 0) {
				if (Item.itemStackable[c.party[x]]) {
					int slot = arraySlot(roomItems, c.party[x]);
					if (slot < 0) {
						c.sendMessage("There is not enough space to deposit this item.");
						break;
					}
					if (roomItems[slot] != c.party[x]) {
						roomItems[slot] = c.party[x];
						roomItemsN[slot] = c.partyN[x];
					} else {
						roomItemsN[slot] += c.partyN[x];
					}
					c.party[x] = -1;
					c.partyN[x] = 0;
				} else {
					int left = c.partyN[x];
					for (int y = 0; y < left; y++) {
						int slot = arraySlot(roomItems, -2);
						if (slot < 0) {
							c.sendMessage("There is not enough space to deposit this item.");
							break;
						}
						roomItems[slot] = c.party[x];
						roomItemsN[slot] = 1;
						c.partyN[x]--;
					}
					if (c.partyN[x] <= 0)
						c.party[x] = -1;
				}
			}
		}
		updateDeposit(c);
		updateGlobal(c);
		updateAll();

	}

	public static int arraySlot(int[] array, int target) {
		int spare = -1;
		for (int x = 0; x < array.length; x++) {
			if (array[x] == target) {
				return x;
			} else if (spare == -1 && array[x] <= 0) {
				spare = x;
			}
		}
		return spare;
	}

	public static void depositItem(Client c, int id, int amount) {
		int slot = arraySlot(c.party, id);
		for (int i : Config.ITEM_TRADEABLE) {
			if (i == id) {
				c.sendMessage("You can't sell this item.");
				return;
			}
		}
		/*
		 * if(c.isDonator == 0) { for (int i : Config.ITEM_DONATOR) { if (i ==
		 * id || i+1 == id) {
		 * c.sendMessage("You can't sell a "+c.getItems().getItemName
		 * (id).toLowerCase()+" unless you are a donator."); return; }
		 * 
		 * } }
		 */
		if (c.getItems().getItemAmount(id) <= amount) {
			amount = c.getItems().getItemAmount(id);
		}
		if (!c.getItems().playerHasItem(id, amount)) {
			c.sendMessage("You don't have that many items!");
			return;
		}
		if (slot == -1) {
			c.sendMessage("You can't deposit more than 8 items at once.");
			return;
		}
		c.getItems().deleteItem2(id, amount);
		if (c.party[slot] != id) {
			c.party[slot] = id;
			c.partyN[slot] = amount;
		} else {
			c.party[slot] = id;
			c.partyN[slot] += amount;
		}
		updateDeposit(c);
	}

	public static void dropAll() {
		int trys = 0;
		int amount = getAmount();
		if (amount < 1) {
			return;
		}
		for (int x = 0; x < roomItems.length; x++) {
			if (roomItemsN[x] > 0) {
				Balloon b = null;
				do {
					b = Balloon.getBalloon(roomItems[x], roomItemsN[x]);
					trys++;
				} while (coords.contains(b.getCoords()) && trys < 1000);
				Server.objectHandler.addObject(b);
				Server.objectHandler.placeObject(b);
			}
			if (trys > 1000) {
				break;
			}
			roomItems[x] = 0;
			roomItemsN[x] = 0;
		}
		trys = 0;
		for (int x = 0; x < amount * 2; x++) {
			Objects o;
			do {
				o = Balloon.getEmpty();
			} while (coords
					.contains(new Point(Objects.objectX, Objects.objectY))
					&& trys < 1000);
			if (trys > 1000) {
				break;
			}
			Server.objectHandler.addObject(o);
			Server.objectHandler.placeObject(o);
		}
		coords.clear();
	}

	public static void fix(Client c) {
		for (int x = 0; x < 8; x++) {
			if (c.party[x] < 0) {
				c.partyN[x] = 0;
			} else if (c.partyN[x] <= 0) {
				c.party[x] = 0;
			}
		}
	}

	public static int getAmount() {
		int amount = 0;
		for (int x = 0; x < roomItems.length; x++) {
			if (roomItems[x] > 0) {
				amount++;
			}
		}
		return amount;
	}

	public static void getCounterDelay(Client c) {
		long totalprice = 0;
		int counter = 30;
		for (int x = 0; x < roomItems.length; x++) {
			if (roomItemsN[x] > 0)
				totalprice += (c.getShops().getItemShopValue(roomItems[x]) * roomItemsN[x]);
		}
		if (totalprice > 150000000)
			counter = (int) totalprice / 5000000;
		if (counter > 500 || counter < 0)
			counter = 500;
		counterDelay = counter;
	}

	public static void itemOnInterface(Client c, int frame, int slot, int id,
			int amount) {
		c.outStream.createFrameVarSizeWord(34);
		c.outStream.writeWord(frame);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(id + 1);
		c.outStream.writeByte(255);
		c.outStream.writeDWord(amount);
		c.outStream.endFrameVarSizeWord();
	}

	public static void open(Client c) {
		updateGlobal(c);
		updateDeposit(c);
		c.getItems().resetItems(5064);
		c.getPA().sendFrame248(2156, 5063);
	}

	public static void PartyRoomCounter() {
		if (PartyRoom.counterDelay != 0) {

			if (System.currentTimeMillis() - lastPRC > 1000) {
				lastPRC = System.currentTimeMillis();
				PartyRoom.counterDelay--;
				if (PartyRoom.counterDelay == 0) {
					Server.npcHandler.SendPRMessage("Dropping now!", 659);
					PartyRoom.dropAll();
				} else
					Server.npcHandler.SendPRMessage(
							Integer.toString(PartyRoom.counterDelay), 659);
			}
		}
	}

	public static void performKnights(Client c, int stage) {
		switch (stage) {
		case 0:
			for (int x = 0; x < 8; x++)
				Server.npcHandler.spawnNpc(c, 660, 3042 + x, 3378, 0, 0, 0, 0,
						0, 0, false, false);
			break;
		case 1:
			Server.npcHandler.SendPRMessage("We're knights of the party room",
					660);
			break;
		case 2:
			Server.npcHandler.SendPRMessage(
					"We dance round and round like a loon", 660);
			break;
		case 3:
			Server.npcHandler.SendPRMessage("Quite often we like to sing", 660);
			break;
		case 4:
			Server.npcHandler.SendPRMessage("Unfortunately we make a din", 660);
			break;
		case 5:
			Server.npcHandler.SendPRMessage("We're knights of the party room",
					660);
			break;
		case 6:
			Server.npcHandler.SendPRMessage("Do you like our helmet plumes?",
					660);
			break;
		case 7:
			Server.npcHandler.SendPRMessage("Everyone's happy now we can move",
					660);
			break;
		case 8:
			Server.npcHandler.SendPRMessage(
					"Like a party animal in the groove", 660);
			break;
		case 9:
			NPCHandler.killNpc(660);
			break;
		}
	}

	public static void updateAll() {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client o = (Client) PlayerHandler.players[j];
				updateDeposit(o);
				updateGlobal(o);
			}
		}

	}

	public static void updateDeposit(Client c) {
		c.getItems().resetItems(5064);
		for (int x = 0; x < 8; x++) {
			if (c.partyN[x] <= 0)
				itemOnInterface(c, 2274, x, -1, 0);
			else
				itemOnInterface(c, 2274, x, c.party[x], c.partyN[x]);
		}
	}

	public static void updateGlobal(Client c) {
		for (int x = 0; x < roomItems.length; x++) {
			if (roomItemsN[x] <= 0)
				itemOnInterface(c, 2273, x, -1, 0);
			else
				itemOnInterface(c, 2273, x, roomItems[x], roomItemsN[x]);
		}
	}

	public static void withdrawItem(Client c, int slot) {
		if (c.party[slot] >= 0 && c.getItems().freeSlots() > 0) {
			c.getItems().addItem(c.party[slot], c.partyN[slot]);
			c.party[slot] = 0;
			c.partyN[slot] = 0;
		}
		updateDeposit(c);
		updateGlobal(c);
	}

	// 2273 items on chest
	// 2274 personal
}