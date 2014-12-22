package server.model.items;

import server.Config;
import server.Server;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.EarningPotential;
import server.model.players.content.LoyaltyHandler;
import server.model.players.content.combat.BountyHunter.PvPDrops;
import server.model.players.content.skills.Dungeoneering;
import server.util.Misc;

public class ItemAssistant {

	public static String getItemName2(int ItemID) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					return Server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		return "Unarmed";
	}

	public static void resetTimer(Client c, int ID) {
		if (ID == 18349)
			c.chaoticCharges[0] = 0;
		else if (ID == 18351)
			c.chaoticCharges[1] = 0;
		else if (ID == 18353)
			c.chaoticCharges[2] = 0;
	}

	private Client c;

	public boolean updateInventory = false;

	public static final int[] donItems = { 19032, 19037, 19034, 19035, 19036,
			19038, 19039, 19040 };

	public static final int[][] brokenBarrows = { { 4708, 4860 },
			{ 4710, 4866 }, { 4712, 4872 }, { 4714, 4878 }, { 4716, 4884 },
			{ 4720, 4896 }, { 4718, 4890 }, { 4720, 4896 }, { 4722, 4902 },
			{ 4732, 4932 }, { 4734, 4938 }, { 4736, 4944 }, { 4738, 4950 },
			{ 4724, 4908 }, { 4726, 4914 }, { 4728, 4920 }, { 4730, 4926 },
			{ 4745, 4956 }, { 4747, 4926 }, { 4749, 4968 }, { 4751, 4794 },
			{ 4753, 4980 }, { 4755, 4986 }, { 4757, 4992 }, { 4759, 4998 } };

	public boolean addedItem = false;

	public int L = 0;

	/**
	 * Bonuses
	 **/

	public final String[] BONUS_NAMES = { "Stab", "Slash", "Crush", "Magic",
			"Range", "Stab", "Slash", "Crush", "Magic", "Range", "Strength",
			"Prayer" };

	public ItemAssistant(Client client) {
		this.c = client;
	}

	public boolean addBankItem(int item, double amount) {
		// synchronized (c) { // no need for synchro / gabbe
		if (amount < 1) {
			amount = 1;
		}
		if (item <= 0) {
			return false;
		}
		for (int i = 0; i < c.bankItems.length; i++) {
			if ((c.bankItems[i] == (item + 1)) && (c.bankItems[i] > 0)
					&& (c.bankItemsN[i] < Config.MAXITEM_AMOUNT)) {
				c.bankItems[i] = (item + 1);
				if (((c.bankItemsN[i] + amount) < Config.MAXITEM_AMOUNT)
						&& ((c.bankItemsN[i] + amount) > -1)) {
					c.bankItemsN[i] += amount;
				} else {
					int old = c.bankItemsN[i];
					c.bankItemsN[i] = Config.MAXITEM_AMOUNT;
					if ((old + amount) - (long) Config.MAXITEM_AMOUNT > 0) {
						addBankItem(item, (old + amount)
								- (long) Config.MAXITEM_AMOUNT);
					}
				}
				return true;
			}
		}
		for (int i = 0; i < c.bankItems.length; i++) {
			if (c.bankItems[i] <= 0) {
				c.bankItems[i] = item + 1;
				if ((amount < Config.MAXITEM_AMOUNT) && (amount > -1)) {
					c.bankItemsN[i] = 1;
					if (amount > 1) {
						addBankItem(item, amount - 1);
						return true;
					}
				} else {
					int old = 0;
					c.bankItemsN[i] = Config.MAXITEM_AMOUNT;
					if ((old + amount) - (long) Config.MAXITEM_AMOUNT > 0) {
						addBankItem(item, (old + amount)
								- (long) Config.MAXITEM_AMOUNT);
					}
				}
				return true;
			}
		}
		return false;
		// }
	}

	/**
	 * Add Item
	 **/
	public boolean addItem(int item, int amount) {
		if (c != null) {
			if (amount < 1) {
				amount = 1;
			}
			if (item <= 0) {
				return false;
			}

			if ((((freeSlots() >= 1) || playerHasItem(item, 1)) && Item.itemStackable[item])
					|| ((freeSlots() > 0) && !Item.itemStackable[item])) {
				for (int i = 0; i < c.playerItems.length; i++) {
					if ((c.playerItems[i] == (item + 1))
							&& Item.itemStackable[item]
							&& (c.playerItems[i] > 0)) {
						c.playerItems[i] = (item + 1);
						if (((c.playerItemsN[i] + amount) < Config.MAXITEM_AMOUNT)
								&& ((c.playerItemsN[i] + amount) > -1)) {
							c.playerItemsN[i] += amount;
							L = c.playerItemsN[i];
						} else {
							c.playerItemsN[i] = Config.MAXITEM_AMOUNT;
						}
						/*
						 * if(c.getOutStream() != null && c != null ) {
						 * c.getOutStream().createFrameVarSizeWord(34);
						 * c.getOutStream().writeWord(3214);
						 * c.getOutStream().writeByte(i);
						 * c.getOutStream().writeWord(c.playerItems[i]); if
						 * (c.playerItemsN[i] > 254) {
						 * c.getOutStream().writeByte(255);
						 * c.getOutStream().writeDWord(c.playerItemsN[i]); }
						 * else { c.getOutStream().writeByte(c.playerItemsN[i]);
						 * } c.getOutStream().endFrameVarSizeWord();
						 * c.flushOutStream(); }
						 */
						updateInventory = true;
						updateInventory();
						i = 30;
						return true;
					}
				}
				for (int i = 0; i < c.playerItems.length; i++) {
					if (c.playerItems[i] <= 0) {
						c.playerItems[i] = item + 1;
						if ((amount < Config.MAXITEM_AMOUNT) && (amount > -1)) {
							c.playerItemsN[i] = 1;
							if (amount > 1) {
								c.getItems().addItem(item, amount - 1);
								return true;
							}
						} else {
							c.playerItemsN[i] = Config.MAXITEM_AMOUNT;
						}
						/*
						 * if(c.getOutStream() != null && c != null ) {
						 * c.getOutStream().createFrameVarSizeWord(34);
						 * c.getOutStream().writeWord(3214);
						 * c.getOutStream().writeByte(i);
						 * c.getOutStream().writeWord(c.playerItems[i]); if
						 * (c.playerItemsN[i] > 254) {
						 * c.getOutStream().writeByte(255);
						 * c.getOutStream().writeDWord(c.playerItemsN[i]); }
						 * else { c.getOutStream().writeByte(c.playerItemsN[i]);
						 * } c.getOutStream().endFrameVarSizeWord();
						 * c.flushOutStream(); }
						 */
						// resetItems(3214);
						updateInventory = true;
						updateInventory();
						L++;
						i = 30;
						return true;
					}
				}
				return false;

			} else {

				updateInventory = true;
				updateInventory();
				// resetItems(3214);
				c.sendMessage("Not enough space in your inventory.");
				return false;
			}
		}
		return false;
	}

	/**
	 * Weapons special bar, adds the spec bars to weapons that require them and
	 * removes the spec bars from weapons which don't require them
	 **/

	public void addSpecialBar(int weapon) {
		switch (weapon) {

		case 4151: // whip
		case 15441: // whip
		case 15442: // whip
		case 15443: // whip
		case 15444: // whip
			c.getPA().sendFrame171(0, 12323);
			specialAmount(weapon, c.specAmount, 12335);
			break;

		case 19780:// Korasi's Sword
		case 19784:
			c.getPA().sendFrame171(0, 7574);
			specialAmount(weapon, c.specAmount, 7586);
			break;

		case 15241:
		case 859: // magic bows
		case 861:
		case 11235:
		case 19146:
		case 19149:
		case 19143:
		case 14990:
		case 15701: // dark bow
		case 15702: // dark bow
		case 15703: // dark bow
		case 15704: // dark bow
		case 13879:
		case 13883:
			c.getPA().sendFrame171(0, 7549);
			specialAmount(weapon, c.specAmount, 7561);
			break;

		case 4587:// dscimmy
			c.getPA().sendFrame171(0, 7599);
			specialAmount(weapon, c.specAmount, 7611);
			break;

		case 3204: // d hally
			c.getPA().sendFrame171(0, 8493);
			specialAmount(weapon, c.specAmount, 8505);
			break;

		case 1377: // d battleaxe
			c.getPA().sendFrame171(0, 7499);
			specialAmount(weapon, c.specAmount, 7511);
			break;

		case 4153: // gmaul
			c.getPA().sendFrame171(0, 7474);
			specialAmount(weapon, c.specAmount, 7486);
			break;

		case 1249: // dspear
			c.getPA().sendFrame171(0, 7674);
			specialAmount(weapon, c.specAmount, 7686);
			break;

		case 14484: // dragon claws
			c.getPA().sendFrame171(0, 7800);
			specialAmount(weapon, c.specAmount, 7812);
			break;
		case 13902: // Statius War
			c.getPA().sendFrame171(0, 7474);
			specialAmount(weapon, c.specAmount, 7486);
			break;
		case 13904: // Statius War (deg)
			c.getPA().sendFrame171(0, 7474);
			specialAmount(weapon, c.specAmount, 7486);
			break;

		case 15486: // SOL
		case 1215:// dragon dagger
		case 1231:
		case 13899:
		case 13901:
		case 10887:
		case 5680:
		case 13905:
		case 13907:
		case 5698:
		case 1305: // dragon long
		case 20000:
		case 20001:
		case 20002:
		case 20003:
		case 11694:
		case 11698:
		case 11700:
		case 11730:
		case 11696:
			c.getPA().sendFrame171(0, 7574);
			specialAmount(weapon, c.specAmount, 7586);
			break;

		case 1434: // dragon mace
			c.getPA().sendFrame171(0, 7624);
			specialAmount(weapon, c.specAmount, 7636);
			break;

		default:
			c.getPA().sendFrame171(1, 7624); // mace interface
			c.getPA().sendFrame171(1, 7474); // hammer, gmaul
			c.getPA().sendFrame171(1, 7499); // axe
			c.getPA().sendFrame171(1, 7549); // bow interface
			c.getPA().sendFrame171(1, 7574); // sword interface
			c.getPA().sendFrame171(1, 7599); // scimmy sword interface, for most
												// swords
			c.getPA().sendFrame171(1, 8493);
			c.getPA().sendFrame171(1, 12323); // whip interface
			break;
		}
	}

	public void addToVoidList(int itemId) {
		switch (itemId) {
		case 2518:
			c.voidStatus[0]++;
			break;
		case 2520:
			c.voidStatus[1]++;
			break;
		case 2522:
			c.voidStatus[2]++;
			break;
		case 2524:
			c.voidStatus[3]++;
			break;
		case 2526:
			c.voidStatus[4]++;
			break;
		}
	}

	/**
	 * Banks our equipment for us
	 */
	public void bankEquipment() {
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] > 0 && c.playerEquipmentN[i] > 0)
				c.getItems().addItemToBank(c.playerEquipment[i],
						c.playerEquipmentN[i]);
		}
		for (int i1 = 0; i1 < c.playerEquipment.length; i1++) {
			deleteEquipment(c.playerEquipment[i1], i1);
		}
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
		c.isFullHelm = Item
				.isFullHelm(c.playerEquipment[Player.playerHat]);
		c.isFullMask = Item
				.isFullMask(c.playerEquipment[Player.playerHat]);
		c.isFullBody = Item
				.isFullBody(c.playerEquipment[Player.playerChest]);
	}

	/**
	 * Adds an item to the bank without checking if the player has it in there
	 * inventory
	 * 
	 * @param itemId
	 *            the id of the item were banking
	 * @param amount
	 *            amount of items to bank
	 */
	public void addItemToBank(int itemId, int amount) {
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] <= 0 || c.bankItems[i] == itemId + 1
					&& c.bankItemsN[i] + amount < Integer.MAX_VALUE) {
				c.bankItems[i] = itemId + 1;
				c.bankItemsN[i] += amount;
				resetBank();
				return;
			}
		}
	}

	public boolean bankItem(int itemID, int fromSlot, int amount) {
		if (c == null) {
			return false;
		}
		if (itemID < 0 || fromSlot < 0 || amount < 0) {
			c.sendMessage("Failed to bank item. Try relogging.");
			return false;
		}
		c.getDungeoneering();
		if (Dungeoneering.antiSmuggle(c, itemID)) {
			return false;
		}
		if (c.storing && playerHasItem(itemID, amount, fromSlot)) {
			c.firstslot();
			if (c.occupied[c.summoningslot] == false
					&& c.maxstore > c.summoningslot
					&& c.maxstore != c.totalstored) {

				if (Item.itemStackable[itemID]) {
					c.sendMessage("You can't store Stackable items.");
					return false;
				}

				// c.getPA().Frame34(7423, itemID, c.summoningslot, 1);
				c.getPA().Frame34(2702, itemID, c.summoningslot, 1);
				deleteItem(itemID, 1);
				c.occupied[c.summoningslot] = true;
				c.storeditems[c.summoningslot] = itemID;
				c.amount[c.summoningslot] = amount;
				c.summoningslot += 1;
				c.totalstored += 1;
				resetTempItems();
				resetBank();
			}

			if (c.totalstored == c.maxstore) {
				c.sendMessage("Your npc can only store " + c.maxstore
						+ " items");
			}

			return false;

		}
		if (c.isBanking) {
			c.getPA().openUpBank(getTabforItem(itemID));// Move to tab item is
														// in before adding
		}
		c.getPA().sendFrame126(bankSlotsUsed() + " / " + Config.BANK_SIZE,
				22003);
		if (c.inTrade) {
			c.sendMessage("You can't store items while trading!");
			return false;
		}
		/*
		 * if(c.tradeStatus == 1 && !c.inTrade) {
		 * c.getTradeAndDuel().declineTrade(); c.sendMessage(
		 * "AntiDupe: You tried to bank an item while dueling, duel closed.");
		 * return false; }
		 */
		if (c.inTrade) {
			c.getTradeAndDuel().declineDuel();
			c.sendMessage("AntiDupe: You tried to bank an item while dueling, trade closed.");
			return false;
		}
		if (c.isShopping) {
			c.sendMessage("You cannot bank items while shopping! - Walk then try.");
			return false;
		}/*
		 * if (c.lastAttackedBy != null) { c.sendMessage(
		 * "AntiDupe: Cannot bank item, errorcode: You can't drop items in combat."
		 * ); return false; }
		 */
		if (c.playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (c.playerItems[fromSlot] <= 0) { // Nullpointer fix
			return false;
		}
		if (!Item.itemIsNote[c.playerItems[fromSlot] - 1]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot] - 1]
					|| c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (c.bankingItems[i] == c.playerItems[fromSlot]) {
						if (c.playerItemsN[fromSlot] < amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (c.bankingItems[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					c.bankingItems[toBankSlot] = c.playerItems[fromSlot];
					if (c.playerItemsN[fromSlot] < amount) {
						amount = c.playerItemsN[fromSlot];
					}
					if ((c.bankingItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (c.bankingItemsN[toBankSlot] + amount) > -1) {
						c.bankingItemsN[toBankSlot] += amount;
					} else {
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((c.bankingItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (c.bankingItemsN[toBankSlot] + amount) > -1) {
						c.bankingItemsN[toBankSlot] += amount;
					} else {
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (c.bankingItems[i] == c.playerItems[fromSlot]) {
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (c.bankingItems[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							c.bankingItems[toBankSlot] = c.playerItems[firstPossibleSlot];
							c.bankingItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							c.bankingItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			}
		} else if (Item.itemIsNote[c.playerItems[fromSlot] - 1]
				&& !Item.itemIsNote[c.playerItems[fromSlot] - 2]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot] - 1]
					|| c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (c.bankingItems[i] == (c.playerItems[fromSlot] - 1)) {
						if (c.playerItemsN[fromSlot] < amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (c.bankingItems[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					c.bankingItems[toBankSlot] = (c.playerItems[fromSlot] - 1);
					if (c.playerItemsN[fromSlot] < amount) {
						amount = c.playerItemsN[fromSlot];
					}
					if ((c.bankingItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (c.bankingItemsN[toBankSlot] + amount) > -1) {
						c.bankingItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((c.bankingItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (c.bankingItemsN[toBankSlot] + amount) > -1) {
						c.bankingItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (c.bankingItems[i] == (c.playerItems[fromSlot] - 1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (c.bankingItems[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							c.bankingItems[toBankSlot] = (c.playerItems[firstPossibleSlot] - 1);
							c.bankingItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							c.bankingItemsN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			c.sendMessage("Item not supported " + (c.playerItems[fromSlot] - 1));
			return false;
		}
	}

	public boolean bankItem(int itemID, int fromSlot, int amount, int[] array,
			int[] arrayN) {
		if (c.inTrade) {
			c.sendMessage("You can't store items while trading!");
			return false;
		}
		if (c.playerItems[fromSlot] <= 0 || c.playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[c.playerItems[fromSlot] - 1]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot] - 1]
					|| c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (array[i] == c.playerItems[fromSlot]) {
						if (c.playerItemsN[fromSlot] < amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (array[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					array[toBankSlot] = c.playerItems[fromSlot];
					if (c.playerItemsN[fromSlot] < amount) {
						amount = c.playerItemsN[fromSlot];
					}
					if ((arrayN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (arrayN[toBankSlot] + amount) > -1) {
						arrayN[toBankSlot] += amount;
					} else {
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((arrayN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (arrayN[toBankSlot] + amount) > -1) {
						arrayN[toBankSlot] += amount;
					} else {
						c.sendMessage("Bank full!");
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (array[i] == c.playerItems[fromSlot]) {
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (array[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							array[toBankSlot] = c.playerItems[firstPossibleSlot];
							arrayN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							arrayN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			}
		} else if (Item.itemIsNote[c.playerItems[fromSlot] - 1]
				&& !Item.itemIsNote[c.playerItems[fromSlot] - 2]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot] - 1]
					|| c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (array[i] == (c.playerItems[fromSlot] - 1)) {
						if (c.playerItemsN[fromSlot] < amount)
							amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (array[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					array[toBankSlot] = (c.playerItems[fromSlot] - 1);
					if (c.playerItemsN[fromSlot] < amount) {
						amount = c.playerItemsN[fromSlot];
					}
					if ((arrayN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (arrayN[toBankSlot] + amount) > -1) {
						arrayN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if ((arrayN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT
							&& (arrayN[toBankSlot] + amount) > -1) {
						arrayN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((c.playerItems[fromSlot] - 1), fromSlot, amount);
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < Config.BANK_SIZE; i++) {
					if (array[i] == (c.playerItems[fromSlot] - 1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i = Config.BANK_SIZE + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
					for (int i = 0; i < Config.BANK_SIZE; i++) {
						if (array[i] <= 0) {
							toBankSlot = i;
							i = Config.BANK_SIZE + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							array[toBankSlot] = (c.playerItems[firstPossibleSlot] - 1);
							arrayN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < c.playerItems.length; i++) {
							if ((c.playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							arrayN[toBankSlot] += 1;
							deleteItem((c.playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetTempItems();
					resetBank();
					return true;
				} else {
					c.sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			c.sendMessage("Item not supported " + (c.playerItems[fromSlot] - 1));
			return false;
		}
	}

	public int bankSlotsUsed() {
		int freeS = 0;
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] > 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public boolean bankUntradeables(Client c) {
		boolean banked1 = false;
		boolean banked2 = false;

		for (int i = 0; i < c.playerItems.length; i++) {
			if (!tradeable(c.playerItems[i] - 1)) {
				c.getItems().addBankItem(c.playerItems[i] - 1,
						c.playerItemsN[i]);
				banked1 = true;
			}
		}
		for (int e = 0; e < c.playerEquipment.length; e++) {
			if (!tradeable(c.playerEquipment[e])) {
				c.getItems().addBankItem(c.playerEquipment[e],
						c.playerEquipmentN[e]);
				banked2 = true;
			}
		}
		if (banked1 || banked2)
			return true;

		return false;
	}

	/**
	 * Drop Item
	 **/

	public void createGroundItem(int itemID, int itemX, int itemY,
			int itemAmount) {
		if (c != null && itemID > 0 && itemX > 0 && itemY > 0) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC((itemY - 8 * c.mapRegionY));
			c.getOutStream().writeByteC((itemX - 8 * c.mapRegionX));
			c.getOutStream().createFrame(44);
			c.getOutStream().writeWordBigEndianA(itemID);
			c.getOutStream().writeWord(itemAmount);
			c.getOutStream().writeByte(0);
			c.flushOutStream();
		}
	}

	public void degradeItem(int ID, int slot, int amount) {
		String itemName = c.getItems().getItemName(ID);
		boolean d = false;
		if (c.playerEquipment[slot] == ID) {
			c.playerEquipment[slot] = -1;
			c.playerEquipmentN[slot] = 0;
			sendWeapon(c.playerEquipment[Player.playerWeapon],
					getItemName(c.playerEquipment[Player.playerWeapon]));
			resetBonus();
			getBonus();
			writeBonus();
			c.getCombat()
					.getPlayerAnimIndex(
							c.getItems()
									.getItemName(
											c.playerEquipment[Player.playerWeapon])
									.toLowerCase());
			c.getOutStream().createFrame(34);
			c.getOutStream().writeWord(6);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(slot);
			c.getOutStream().writeWord(0);
			c.getOutStream().writeByte(0);
			c.flushOutStream();
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
			c.isFullHelm = Item.isFullHelm(c.playerEquipment[Player.playerHat]);
			c.isFullMask = Item.isFullMask(c.playerEquipment[Player.playerHat]);
			c.isFullBody = Item
					.isFullBody(c.playerEquipment[Player.playerChest]);
			d = true;
		}
		if (!d && c.getItems().playerHasItem(ID, amount)) {
			c.getItems().deleteItem(ID, amount);
			d = true;
		}
		if (d && itemName != null && !itemName.equalsIgnoreCase("Unarmed"))
			c.sendMessage("Your " + itemName + " has degraded into dust.");
		if (d)
			resetTimer(c, ID);
	}

	/**
	 * delete all items
	 **/

	public void deleteAllItems() {
		for (int i1 = 0; i1 < c.playerEquipment.length; i1++) {
			deleteEquipment(c.playerEquipment[i1], i1);
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			deleteItem(c.playerItems[i] - 1, getItemSlot(c.playerItems[i] - 1),
					c.playerItemsN[i]);
		}
	}

	/**
	 * Delete Arrows
	 **/
	public void deleteArrow() {
		if (c != null) {
			if (c.playerEquipment[Player.playerCape] == 10499
					&& Misc.random(5) != 1
					&& c.playerEquipment[Player.playerArrows] != 4740)
				return;
			if (c.playerEquipmentN[Player.playerArrows] == 1) {
				c.getItems().deleteEquipment(
						c.playerEquipment[Player.playerArrows],
						Player.playerArrows);
			}
			if (c.playerEquipmentN[Player.playerArrows] != 0) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(1688);
				c.getOutStream().writeByte(Player.playerArrows);
				c.getOutStream().writeWord(
						c.playerEquipment[Player.playerArrows] + 1);
				if (c.playerEquipmentN[Player.playerArrows] - 1 > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(
							c.playerEquipmentN[Player.playerArrows] - 1);
				} else {
					c.getOutStream().writeByte(
							c.playerEquipmentN[Player.playerArrows] - 1);
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
				c.playerEquipmentN[Player.playerArrows] -= 1;
			}
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
		}
	}

	public void deleteEquipment() {
		if (c != null) {
			if (c.playerEquipmentN[Player.playerWeapon] == 1) {
				c.getItems().deleteEquipment(
						c.playerEquipment[Player.playerWeapon],
						Player.playerWeapon);
			}
			if (c.playerEquipmentN[Player.playerWeapon] != 0) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(1688);
				c.getOutStream().writeByte(Player.playerWeapon);
				c.getOutStream().writeWord(
						c.playerEquipment[Player.playerWeapon] + 1);
				if (c.playerEquipmentN[Player.playerWeapon] - 1 > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(
							c.playerEquipmentN[Player.playerWeapon] - 1);
				} else {
					c.getOutStream().writeByte(
							c.playerEquipmentN[Player.playerWeapon] - 1);
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
				c.playerEquipmentN[Player.playerWeapon] -= 1;
			}
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
		}
	}

	/**
	 * delete Item
	 **/

	public void deleteEquipment(int i, int j) {
		if (c != null) {
			if (PlayerHandler.players[c.playerId] == null) {
				return;
			}
			if (i < 0) {
				return;
			}

			c.playerEquipment[j] = -1;
			c.playerEquipmentN[j] = c.playerEquipmentN[j] - 1;
			c.getOutStream().createFrame(34);
			c.getOutStream().writeWord(6);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(j);
			c.getOutStream().writeWord(0);
			c.getOutStream().writeByte(0);
			getBonus();
			if (j == Player.playerWeapon) {
				sendWeapon(-1, "Unarmed");
			}
			resetBonus();
			getBonus();
			writeBonus();
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
		}
	}

	public void deleteItem(int id, int amount) {
		deleteItem(id, getItemSlot(id), amount);
	}

	public void deleteItem(int id, int slot, int amount) {
		if (id <= 0 || slot < 0) {
			return;
		}
		if (c.playerItems[slot] == (id + 1)) {
			if (c.playerItemsN[slot] > amount) {
				c.playerItemsN[slot] -= amount;
			} else {
				c.playerItemsN[slot] = 0;
				c.playerItems[slot] = 0;
			}
			resetItems(3214);
		}
	}

	public void deleteItem2(int id, int amount) {
		int am = amount;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (am == 0) {
				break;
			}
			if (c.playerItems[i] == (id + 1)) {
				if (c.playerItemsN[i] > amount) {
					c.playerItemsN[i] -= amount;
					break;
				} else {
					c.playerItems[i] = 0;
					c.playerItemsN[i] = 0;
					am--;
				}
			}
		}
		resetItems(3214);
	}

	/*
	 * Checks if the users inventory contains items. If it contains items, get
	 * the item count/free space. Checks if the users inventory contains gold.
	 * If it contains gold, get the gold count. Made by some randomm guy from
	 * r-s
	 */
	public void depositInventory() {
		for (int i = 0; i < c.playerItems.length; i++) {
			c.getDungeoneering();
			if (Dungeoneering.antiSmuggle(c, c.playerItems[i])) {
				return;
			}
		}
		final boolean debugging = false;
		boolean containsItems = false;
		int itemCount = 0;
		boolean containsGold = false;
		int goldCount = 0;
		int inventorySpace = c.getItems().freeSlots();
		int totalInventorySpace = 28;
		int usedInventorySpace = totalInventorySpace - inventorySpace;
		String tmp[] = new String[2];
		/* User has no items to deposit */
		if (usedInventorySpace == 0) {
			c.sendMessage("You have no items to deposit!");
			return;
		}
		if (usedInventorySpace > 0) {
			containsItems = true;
			itemCount = usedInventorySpace;
			if (c.getItems().playerHasItem(995)) {
				containsGold = true;
			}
		}
		if (containsGold) {
			goldCount = c.getItems().getItemAmount(995);
		}
		/* Used for debugging, just change the boolean to true if you wish */
		if (debugging) {
			c.sendMessage("Used Inventory Space: " + usedInventorySpace + ".");
			c.sendMessage("Contains Items: " + containsItems);
			c.sendMessage("Contains Gold: " + containsGold);
			c.sendMessage("Gold Amount: " + goldCount);
		}
		/* Calculate the Strings before they're put to use */
		if (goldCount == 1) {
			tmp[0] = "gold coin";
		}
		if (goldCount > 1) {
			tmp[0] = "gold coins";
		}
		if (itemCount > 0) {
			if (containsGold) {
				itemCount -= 1;
				c.getItems().bankItem(995, c.getItems().getItemSlot(995),
						goldCount);
				c.sendMessage("You deposit <shad=2320482>" + goldCount + " "
						+ tmp[0] + " into your bank.");
			}
			/* Calculate the Strings before they're put to use */
			if (itemCount == 1) {
				tmp[1] = "item";
			}
			if (itemCount > 1) {
				tmp[1] = "items";
			}
			for (int i = 0; i < c.playerItems.length; i++) {
				c.getItems().bankItem(c.playerItems[i], i, c.playerItemsN[i]);
			}
			/*
			 * Make sure the user doesn't recieve a message containing a null
			 * String
			 */
			if (tmp[1] != null) {
				if (containsGold) {
					c.sendMessage("<shad=2320482>You also deposit " + itemCount
							+ " " + tmp[1] + " into your bank.");
				} else if (!containsGold) {
					c.sendMessage("<shad=2320482>You deposit " + itemCount
							+ " " + tmp[1] + " into your bank.");
				}
			}
		}
	}

	public boolean dropableItem(int itemId) {
		for (int j = 0; j < Config.ITEM_TRADEABLE.length; j++) {
			if (itemId == Config.ITEM_TRADEABLE[j])
				return true;
		}
		return false;
	}

	/**
	 * Drop all items for your killer
	 **/

	public void dropAllItems() {
		if (c.playerRights == 3/* || c.isInArd() || c.isInFala() */) {
			c.playerLevel[3] = 99;
			c.getPA().refreshSkill(3);
			return;
		}
		if (c.playerRights == 2 || c.playerRights == 3/*
													 * || c.isInArd() ||
													 * c.isInFala()
													 */) {
			c.playerLevel[3] = 99;
			c.getPA().refreshSkill(3);
			return;
		}
		Client o = (Client) PlayerHandler.players[c.killerId];
		if (c.isDonator >= 1)
			if (bankUntradeables(c))
				c.sendMessage("Your untradeable items have been sent to your bank.");
		for (int i = 0; i < c.playerItems.length; i++) {
			if (o != null) {
				if (!dropableItem(c.playerItems[i] - 1)
						&& !specialCase(c.playerItems[i] - 1)) {
					Server.itemHandler.createGroundItem(o,
							c.playerItems[i] - 1, c.getX(), c.getY(),
							c.playerItemsN[i], c.killerId);
				} else if (specialCase(c.playerItems[i] - 1)) {
					Server.itemHandler.createGroundItem(o, 995, c.getX(),
							c.getY(), getUntradePrice(c.playerItems[i] - 1),
							c.killerId);
				}
			}
		}
		for (int e = 0; e < c.playerEquipment.length; e++) {
			if (o != null) {
				if (!dropableItem(c.playerEquipment[e])
						&& !specialCase(c.playerEquipment[e])) {
					Server.itemHandler.createGroundItem(o,
							c.playerEquipment[e], c.getX(), c.getY(),
							c.playerEquipmentN[e], c.killerId);
				} else if (specialCase(c.playerEquipment[e])) {
					Server.itemHandler.createGroundItem(o, 995, c.getX(),
							c.getY(), getUntradePrice(c.playerEquipment[e]),
							c.killerId);
				}
			}
		}

		if (o != null) {
			if (c.inWild() && o.inWild()) {
				PvPDrops.handleDrops(c);
				Server.itemHandler.createGroundItem(o, 526, c.getX(), c.getY(),
						1, c.killerId);
			}
		}

	}

	public void dropAllItemsPVP() {
		Client o = (Client) PlayerHandler.players[c.killerId];
		EarningPotential.giveBonusDrops(o, c);
	}

	/**
	 * Dropping Arrows
	 **/

	public void dropArrowNpc() {
		if (c.playerEquipment[Player.playerCape] == 10499)
			return;
		if (c.playerEquipment[Player.playerArrows] == 15243)
			return;
		/*
		 * if (c.playerEquipment[c.playerWeapon] == 13883) // MORRIGAN'S AXE
		 * return; if (c.playerEquipment[c.playerWeapon] == 13879) // MORRIGAN'S
		 * JAVELIN return;
		 */
		int enemyX = NPCHandler.npcs[c.oldNpcIndex].getX();
		int enemyY = NPCHandler.npcs[c.oldNpcIndex].getY();
		if (Misc.random(10) >= 4) {
			if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY) == 0) {
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, 1, c.getId());
			} else if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX,
					enemyY) != 0) {
				int amount = Server.itemHandler.itemAmount(c.rangeItemUsed,
						enemyX, enemyY);
				Server.itemHandler.removeGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, false);
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, amount + 1, c.getId());
			}
		}
	}

	public void dropArrowPlayer() {
		int enemyX = PlayerHandler.players[c.oldPlayerIndex].getX();
		int enemyY = PlayerHandler.players[c.oldPlayerIndex].getY();
		if (c.playerEquipment[Player.playerCape] == 10499) // AVA'S ACCUMAlator
			return;
		/*
		 * if (c.playerEquipment[c.playerWeapon] == 13883) // MORRIGAN'S AXE
		 * return; if (c.playerEquipment[c.playerWeapon] == 13879) // MORRIGAN'S
		 * JAVELIN return;
		 */
		if (c.playerEquipment[Player.playerArrows] == 15243) // HAND CANNON
																// SHOTS
			return;
		if (Misc.random(10) >= 4) {
			if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX, enemyY) == 0) {
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, 1, c.getId());
			} else if (Server.itemHandler.itemAmount(c.rangeItemUsed, enemyX,
					enemyY) != 0) {
				int amount = Server.itemHandler.itemAmount(c.rangeItemUsed,
						enemyX, enemyY);
				Server.itemHandler.removeGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, false);
				Server.itemHandler.createGroundItem(c, c.rangeItemUsed, enemyX,
						enemyY, amount + 1, c.getId());
			}
		}
	}

	/*
	 * End of Event
	 */

	public int findItem(int id, int[] items, int[] amounts) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if (((items[i] - 1) == id) && (amounts[i] > 0)) {
				return i;
			}
		}
		return -1;
	}

	public int freeBankSlots() {
		int freeS = 0;
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public int freeSlots() {
		int freeS = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public void fromBank(int itemID, int fromSlot, int amount) {
		// c.sendMessage(itemID + "@" + c.bankingItems[fromSlot]);
		if (!c.isBanking && !c.inConstruction()) {
			c.getPA().closeAllWindows();
			return;
		}
		if (c.searchTerm != null && !c.searchTerm.equals("FLAG")
				&& !c.searchTerm.equals("N/A")) {
			// c.sendMessage("search shit " + c.searchTerm);
			// c.sendMessage("lookin' for " + itemID + " / " + c.searchTerm);
			String temp = c.searchTerm;
			c.searchTerm = "FLAG";
			int tempT = c.bankingTab;
			int collect = amount;
			// c.sendMessage("@" + c.searchTerm + " " + amount + " " + itemID);
			// String item = "";
			for (int i = 0; i < c.getPA().tempItems.length; i++) {
				// item += (c.bankingItems[i] + " ");
				if (c.getPA().tempItems[i] == itemID + 1
						|| c.getPA().tempItems[i] == itemID) {
					int count = Math.min(c.getPA().tempItemsN[i], collect);
					if (collect == -1)
						count = c.getPA().tempItemsN[i];
					// c.sendMessage("taking " + (itemID));
					c.bankingTab = (c.getPA().tempItemsT[i]);
					if (c.bankingTab == 0) {
						c.bankingItems = c.bankItems;
						c.bankingItemsN = c.bankItemsN;
					}
					if (c.bankingTab == 1) {
						c.bankingItems = c.bankItems1;
						c.bankingItemsN = c.bankItems1N;
					}
					if (c.bankingTab == 2) {
						c.bankingItems = c.bankItems2;
						c.bankingItemsN = c.bankItems2N;
					}
					if (c.bankingTab == 3) {
						c.bankingItems = c.bankItems3;
						c.bankingItemsN = c.bankItems3N;
					}
					if (c.bankingTab == 4) {
						c.bankingItems = c.bankItems4;
						c.bankingItemsN = c.bankItems4N;
					}
					if (c.bankingTab == 5) {
						c.bankingItems = c.bankItems5;
						c.bankingItemsN = c.bankItems5N;
					}
					if (c.bankingTab == 6) {
						c.bankingItems = c.bankItems6;
						c.bankingItemsN = c.bankItems6N;
					}
					if (c.bankingTab == 7) {
						c.bankingItems = c.bankItems7;
						c.bankingItemsN = c.bankItems7N;
					}
					if (c.bankingTab == 8) {
						c.bankingItems = c.bankItems8;
						c.bankingItemsN = c.bankItems8N;
					}
					// c.getPA().openUpBank(c.bankingTab);
					c.getItems().fromBank(itemID + 1, c.getPA().tempItemsS[i],
							count);
					// c.sendMessage("Collecting " + itemID);
					collect -= count;
				}/*
				 * else { c.sendMessage(c.bankingItems[i] + " " + itemID); }
				 */
			}
			// for(int i = 0; i < item.length(); i+= 30) {
			// c.sendMessage(item.substring(i, i+30));
			// }
			// System.out.println("Done with collecting " + itemID);
			c.bankingTab = tempT;
			c.searchTerm = temp;
			// c.sendMessage("Ur search term was " + temp +
			// " so backed it up :3");
			if (itemID == 995) {
				int l = c.getItems().getItemAmount(995);
				int k = l + amount;
				if (k >= 2147483646) {
					c.sendMessage("You cannot withdraw any more coins as your inventory is already loaded!");
					return;
				}
			}
		} else {
			if (amount > 0) {
				if (c.bankingItems[fromSlot] > 0) {
					if (!c.takeAsNote) {
						if (Item.itemStackable[c.bankingItems[fromSlot] - 1]) {
							if (c.bankingItemsN[fromSlot] > amount) {
								if (addItem((c.bankingItems[fromSlot] - 1),
										amount)) {
									c.bankingItemsN[fromSlot] -= amount;
									resetBank();
									c.getItems().resetItems(5064);
								}
							} else {
								if (addItem((c.bankingItems[fromSlot] - 1),
										c.bankingItemsN[fromSlot])) {
									c.bankingItems[fromSlot] = 0;
									c.bankingItemsN[fromSlot] = 0;
									resetBank();
									c.getItems().resetItems(5064);
								}
							}
						} else {
							while (amount > 0) {
								if (c.bankingItemsN[fromSlot] > 0) {
									if (addItem((c.bankingItems[fromSlot] - 1),
											1)) {
										c.bankingItemsN[fromSlot] += -1;
										amount--;
									} else {
										amount = 0;
									}
								} else {
									amount = 0;
								}
							}
							resetBank();
							c.getItems().resetItems(5064);
						}
					} else if (c.takeAsNote
							&& Item.itemIsNote[c.bankingItems[fromSlot]]) {
						if (c.bankingItemsN[fromSlot] > amount) {
							if (addItem(c.bankingItems[fromSlot], amount)) {
								c.bankingItemsN[fromSlot] -= amount;
								resetBank();
								c.getItems().resetItems(5064);
							}
						} else {
							if (addItem(c.bankingItems[fromSlot],
									c.bankingItemsN[fromSlot])) {
								c.bankingItems[fromSlot] = 0;
								c.bankingItemsN[fromSlot] = 0;
								resetBank();
								c.getItems().resetItems(5064);
							}
						}
					} else {
						c.sendMessage("This item can't be withdrawn as a note.");
						if (Item.itemStackable[c.bankingItems[fromSlot] - 1]) {
							if (c.bankingItemsN[fromSlot] > amount) {
								if (addItem((c.bankingItems[fromSlot] - 1),
										amount)) {
									c.bankingItemsN[fromSlot] -= amount;
									resetBank();
									c.getItems().resetItems(5064);
								}
							} else {
								if (addItem((c.bankingItems[fromSlot] - 1),
										c.bankingItemsN[fromSlot])) {
									c.bankingItems[fromSlot] = 0;
									c.bankingItemsN[fromSlot] = 0;
									resetBank();
									c.getItems().resetItems(5064);
								}
							}
						} else {
							while (amount > 0) {
								if (c.bankingItemsN[fromSlot] > 0) {
									if (addItem((c.bankingItems[fromSlot] - 1),
											1)) {
										c.bankingItemsN[fromSlot] += -1;
										amount--;
									} else {
										amount = 0;
									}
								} else {
									amount = 0;
								}
							}
							resetBank();
							c.getItems().resetItems(5064);
						}
					}
				}
			}
		}
		if (c.searchTerm.equals("N/A"))
			c.getPA().openUpBank(c.bankingTab);
	}

	public int getBankAmount(int item) {
		int count = 0;
		for (int j = 0; j < c.bankItems.length; j++) {
			if (c.bankItems[j] == item + 1) {
				count += c.bankItemsN[j];
			}
		}
		return count;
	}

	public void getBonus() {
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] > -1) {
				for (int j = 0; j < Config.ITEM_LIMIT; j++) {
					if (Server.itemHandler.ItemList[j] != null) {
						if (Server.itemHandler.ItemList[j].itemId == c.playerEquipment[i]) {
							for (int k = 0; k < c.playerBonus.length; k++) {
								c.playerBonus[k] += Server.itemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					}
				}
			}
		}
	}

	public int getCarriedWealth() {
		int toReturn = 0;
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] > 0
					&& Server.itemHandler.ItemList[c.playerEquipment[i]] != null)
				toReturn += (Server.itemHandler.ItemList[c.playerEquipment[i]].ShopValue * c.playerEquipmentN[i]);
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] > 0
					&& Server.itemHandler.ItemList[c.playerItems[i]] != null)
				toReturn += (Server.itemHandler.ItemList[c.playerItems[i]].ShopValue * c.playerItemsN[i]);
		}
		return toReturn;

	}

	public int getEquipmentNet(Client c) {
		int toReturn = 0;
		for (int i = 0; i < c.playerEquipment.length; i++) {
			toReturn += (c.getShops().getItemShopValue(c.playerEquipment[i]) * c.playerEquipmentN[i]);
		}
		return toReturn;
	}

	public int getInventoryNet(Client c) {
		int toReturn = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			toReturn += (c.getShops().getItemShopValue(c.playerItems[i] + 1) * c.playerItemsN[i]);
		}
		return toReturn;
	}

	public int getItemAmount(int ItemID) {
		int itemCount = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if ((c.playerItems[i] - 1) == ItemID) {
				itemCount += c.playerItemsN[i];
			}
		}
		return itemCount;
	}

	public int getItemCount(int itemID) {
		int count = 0;
		for (int j = 0; j < c.playerItems.length; j++) {
			if (c.playerItems[j] == itemID + 1) {
				count += c.playerItemsN[j];
			}
		}
		return count;
	}

	public int getItemId(String itemName) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemName
						.equalsIgnoreCase(itemName)) {
					return Server.itemHandler.ItemList[i].itemId;
				}
			}
		}
		return -1;
	}

	public static String getItemName(int ItemID) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					return Server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		return "Unarmed";
	}

	public int getItemSlot(int ItemID) {
		for (int i = 0; i < c.playerItems.length; i++) {
			if ((c.playerItems[i] - 1) == ItemID) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Weapon Requirements
	 **/

	public void getRequirements(String itemName, int itemId) {
		c.attackLevelReq = c.defenceLevelReq = c.strengthLevelReq = c.rangeLevelReq = c.slayerLevelReq = c.dungLevelReq = c.magicLevelReq = c.Donatorreq = 0;
		if (itemName.contains("mystic") || itemName.contains("nchanted")) {
			if (itemName.contains("staff of light")) {
				c.magicLevelReq = 75;
				c.attackLevelReq = 75;
			}
			if (itemName.contains("staff")) {
				c.magicLevelReq = 20;
				c.attackLevelReq = 40;
			} else {
				c.magicLevelReq = 20;
				c.defenceLevelReq = 20;
			}
		}

		if (itemName.contains("infinity")) {
			c.magicLevelReq = 50;
			c.defenceLevelReq = 25;
		}
		if (itemName.contains("splitbark")) {
			c.magicLevelReq = 40;
			c.defenceLevelReq = 40;
		}
		if (itemName.contains("rune c'bow")) {
			c.rangeLevelReq = 61;
		}
		if (itemName.contains("black d'hide")) {
			c.rangeLevelReq = 70;
		}
		if (itemName.contains("slayer helmet")) {
			c.defenceLevelReq = 35;
		}
		if (itemName.contains("falador sh")) {
			c.defenceLevelReq = 30;
		}
		if (itemName.contains("gilded")) {
			c.defenceLevelReq = 40;
		}
		if (itemName.contains("third-age")) {
			c.defenceLevelReq = 40;
		}
		if (itemName.contains("tzhaar-ket-om")) {
			c.strengthLevelReq = 60;
		}
		if (itemName.contains("red d'hide")) {
			c.rangeLevelReq = 60;
		}
		if (itemName.contains("blue d'hide")) {
			c.rangeLevelReq = 50;
		}
		if (itemName.contains("green d'hide")) {
			c.rangeLevelReq = 40;
		}
		if (itemName.contains("snakeskin")) {
			c.rangeLevelReq = 40;
			c.defenceLevelReq = 30;
		}
		if (itemName.contains("initiate")) {
			c.defenceLevelReq = 20;
		}
		if (itemName.contains("bronze")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 1;
			}
			return;
		}
		if (itemName.contains("iron")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 1;
			}
			return;
		}
		if (itemName.contains("steel")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 5;
			}
			return;
		}
		if (itemName.contains("mithril")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 20;
			}
			return;
		}
		if (itemName.contains("adamant")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")) {
				c.attackLevelReq = c.defenceLevelReq = 30;
			}
			return;
		}
		if (itemName.contains("rune")) {
			if (!itemName.contains("knife") && !itemName.contains("dart")
					&& !itemName.contains("javelin")
					&& !itemName.contains("thrownaxe")
					&& !itemName.contains("'bow")) {
				c.attackLevelReq = c.defenceLevelReq = 40;
			}
			return;
		}
		if (itemName.contains("granite shield")) {
			if (!itemName.contains("maul")) {
				c.defenceLevelReq = 50;
			}
			return;
		}
		if (itemName.contains("granite maul")) {
			if (!itemName.contains("shield")) {
				c.attackLevelReq = 50;
			}
			return;
		}
		if (itemName.contains("warrior")) {
			if (!itemName.contains("ring")) {
				c.defenceLevelReq = 40;
			}
			return;
		}
		if (itemName.contains("dragonfire")) {

			c.defenceLevelReq = 75;

		}
		if (itemName.contains("flameburst")) {

			c.defenceLevelReq = 65;

		}
		if (itemName.contains("enchanted")) {

			c.defenceLevelReq = 40;

		}
		if (itemName.contains("primal kite")) {

			c.defenceLevelReq = 99;

		}
		if (itemName.contains("primal plate")) {

			c.defenceLevelReq = 99;

		}
		if (itemName.contains("d'hide body")) {
			if (!itemName.contains("chaps")) {
				c.defenceLevelReq = c.rangeLevelReq = 40;
			}
			return;
		}
		if (itemName.contains("celestial")) {

			c.defenceLevelReq = 90;
			c.magicLevelReq = 90;

		}
		if (itemName.contains("Sagittarian")) {

			c.defenceLevelReq = 90;
			c.rangeLevelReq = 90;

		}
		if (itemName.contains("dragon dagger")) {

			c.attackLevelReq = 60;
		}
		if (itemName.contains("drag dagger")) {

			c.attackLevelReq = 60;
		}
		if (itemName.contains("ancient")) {

			c.attackLevelReq = 50;
		}
		if (itemName.contains("hardleather")) {

			c.defenceLevelReq = 10;
		}
		if (itemName.contains("studded")) {

			c.defenceLevelReq = 20;
		}
		if (itemName.contains("party")) {

			// c.Donatorreq = 1;

		}
		if (itemName.contains("h'ween")) {

			// c.Donatorreq = 1;

		}
		if (itemName.contains("santa")) {

			// c.Donatorreq = 1;

		}
		if (itemName.contains("bandos")) {
			if (!itemName.contains("godsword")) {
				c.strengthLevelReq = c.defenceLevelReq = 65;
				// c.Donatorreq = 1;
				return;
			}
		}
		if (itemName.contains("dragon")) {
			if (!itemName.contains("nti-") && !itemName.contains("fire")) {
				c.attackLevelReq = c.defenceLevelReq = 60;
				return;
			}
		}
		if (itemName.contains("crystal")) {
			if (itemName.contains("shield")) {
				c.defenceLevelReq = 70;
			} else {
				c.rangeLevelReq = 70;
			}
			return;
		}
		if (itemName.contains("ahrim")) {
			if (itemName.contains("staff")) {
				c.magicLevelReq = 70;
				c.attackLevelReq = 70;
			} else {
				c.magicLevelReq = 70;
				c.defenceLevelReq = 70;
			}
		}
		if (itemName.contains("karil")) {
			if (itemName.contains("crossbow")) {
				c.rangeLevelReq = 70;
			} else {
				c.rangeLevelReq = 70;
				c.defenceLevelReq = 70;
			}
		}
		if (itemName.contains("armadyl")) {
			if (itemName.contains("godsword")) {
				c.attackLevelReq = 75;
				c.Donatorreq = 1;
			} else {
				c.rangeLevelReq = c.defenceLevelReq = 65;
			}
		}
		if (itemName.contains("saradomin")) {
			if (itemName.contains("sword")) {
				c.attackLevelReq = 70;
			}
			if (itemName.contains("crozier")) {
				c.attackLevelReq = 1;
				if (itemName.contains("robe")) {
					c.attackLevelReq = 1;

				} else {
					c.defenceLevelReq = 40;

				}
			}
		}
		if (itemName.contains("godsword")) {
			c.attackLevelReq = 75;
		}
		if (itemName.contains("3rd age") && !itemName.contains("amulet")) {
			c.defenceLevelReq = 60;
		}
		if (itemName.contains("verac") || itemName.contains("guthan")
				|| itemName.contains("dharok") || itemName.contains("torag")) {

			if (itemName.contains("hammers")) {
				c.attackLevelReq = 70;
				c.strengthLevelReq = 70;
			} else if (itemName.contains("axe")) {
				c.attackLevelReq = 70;
				c.strengthLevelReq = 70;
			} else if (itemName.contains("warspear")) {
				c.attackLevelReq = 70;
				c.strengthLevelReq = 70;
			} else if (itemName.contains("flail")) {
				c.attackLevelReq = 70;
				c.strengthLevelReq = 70;
			} else {
				c.defenceLevelReq = 70;
			}
		}

		switch (itemId) {
		case 10887:
		case 19780:
		case 19784:
			c.attackLevelReq = 70;
			c.strengthLevelReq = 60;
			return;
		case 13362:
		case 13358:
		case 13360:
			c.defenceLevelReq = 80;
			return;
		case 8839:
		case 8840:
		case 8842:
		case 11663:
		case 11664:
		case 11665:
			c.attackLevelReq = 42;
			c.rangeLevelReq = 42;
			c.strengthLevelReq = 42;
			c.magicLevelReq = 42;
			c.defenceLevelReq = 42;
			return;
		case 6528:
			c.strengthLevelReq = 50;
			return;
		case 10551:
		case 2503:
		case 2501:
		case 2499:
		case 1135:
			c.defenceLevelReq = 40;
			return;
		case 11235:
		case 6522:
			c.rangeLevelReq = 60;
			break;
		case 6524:
			c.defenceLevelReq = 60;
			break;
		case 11284:
		case 11283:
			c.defenceLevelReq = 75;
			return;
		case 6889:
		case 6914:
			c.magicLevelReq = 60;
			break;
		case 13905:
		case 13899:
		case 13902:
			c.attackLevelReq = 78;
			break;
		case 13893: // Pvp armor
		case 13877:
		case 13890:
		case 13884:
		case 13896:
			c.defenceLevelReq = 78;
			return;

		case 13830:
		case 13831:
		case 13832:
		case 13833:
		case 13834:
		case 13835:// Gilded Armour
		case 13836:
		case 13837:
		case 13838:
		case 13839:
		case 3480:
		case 3481:
		case 3482:
		case 3483:
		case 3484:
		case 3485:
		case 3486:
		case 3487:
		case 3488:
		case 3489:
		case 10782:
			c.defenceLevelReq = 40;
			return;

		case 10330:
		case 10332:
		case 10334:// Third-Age Range Robes
		case 10336:
			c.defenceLevelReq = 45;
			c.rangeLevelReq = 65;
			return;

		case 10338:
		case 10340:
		case 10342:// Third-Age Magic Robes
		case 10344:
			c.defenceLevelReq = 30;
			c.magicLevelReq = 65;
			return;

		case 10346:
		case 10348:
		case 10350:// Third-Age Melee Armour
		case 10352:
			c.defenceLevelReq = 65;
			return;

		case 13876:
		case 13870:
		case 13873:
			c.defenceLevelReq = 78;
			return;

		case 13858:
		case 13862:
		case 13864:
			c.magicLevelReq = 78;
			c.defenceLevelReq = 78;
			break;

		case 13736:
		case 13734:
		case 13740:
		case 13742:
		case 13738:
		case 13744:
			c.defenceLevelReq = 75;
			break;

		case 13882:
		case 13881:
		case 13880:
		case 13879:
		case 13883:
			c.rangeLevelReq = 78;
			break;

		case 861:
			c.rangeLevelReq = 50;
			break;
		case 19146:
		case 14990:
		case 19143:
		case 19149:
			c.rangeLevelReq = 99;
			break;
		case 20171:
			c.rangeLevelReq = 80;
			break;

		case 20135:
		case 20139:
		case 20143:
		case 20147:
		case 20151:
		case 20155:
		case 20159:
		case 20163:
		case 20167:
			c.attackLevelReq = 80;
			c.strengthLevelReq = 80;
			c.defenceLevelReq = 80;
			c.magicLevelReq = 80;
			c.rangeLevelReq = 80;
			break;

		case 15486:
			c.magicLevelReq = 75;
			c.attackLevelReq = 75;
			break;

		case 2412:
			c.magicLevelReq = 60;
			break;

		case 10828:
			c.defenceLevelReq = 55;
			break;
		case 11724:
		case 11726:
		case 11728:
			c.defenceLevelReq = 65;
			break;
		case 3751:
		case 3749:
		case 3755:
			c.defenceLevelReq = 40;
			break;

		case 7462:
		case 7461:
			c.defenceLevelReq = 40;
			break;
		case 8846:
			c.defenceLevelReq = 5;
			break;
		case 8847:
			c.defenceLevelReq = 10;
			break;
		case 8848:
			c.defenceLevelReq = 20;
			break;
		case 8849:
			c.defenceLevelReq = 30;
			break;

		case 8850:
			c.defenceLevelReq = 40;
			break;
		case 20072:
			c.defenceLevelReq = 60;
			break;

		case 7460:
			c.defenceLevelReq = 20;
			break;

		case 15071:
			c.defenceLevelReq = 60;
			break;

		case 837:
			c.rangeLevelReq = 61;
			break;

		case 15441:
		case 15442:
		case 15443:
		case 15444:
		case 4151: // if you don't want to use names
			c.attackLevelReq = 70;
			return;

		case 6724: // seercull
			c.rangeLevelReq = 60; // idk if that is correct
			return;

		case 1319: // rune 2h
			c.attackLevelReq = 40;
			return;

		case 14484: // dclaw
			c.attackLevelReq = 60;
			return;

		case 15241: // hand cannon
			c.rangeLevelReq = 61;
			return;

		case 18349: // c rapier
			c.attackLevelReq = 80;
			return;

		case 18351: // cls
			c.attackLevelReq = 80;
			// c.dungLevelReq = 82;
			return;

		case 13263:
			c.defenceLevelReq = 45;
			c.slayerLevelReq = 65;
			return;

		case 18353: // c maul
			c.attackLevelReq = 80;
			// c.dungLevelReq = 82;
			return;

		case 18355: // c staff
			// c.attackLevelReq = 80;
			c.magicLevelReq = 80;
			// c.dungLevelReq = 65;
			return;

		case 18357: // c cbow
			c.rangeLevelReq = 80;
			// c.dungLevelReq = 82;
			return;

		case 18361: // eagle-eye
			c.rangeLevelReq = 80;
			c.defenceLevelReq = 80;
			// c.dungLevelReq = 82;
			return;

		case 18359: // c kite
			c.defenceLevelReq = 80;
			c.dungLevelReq = 82;
			return;

		case 13355:// virtus
		case 13354:
		case 13352:
			c.defenceLevelReq = 60;
			c.dungLevelReq = 60;
			c.magicLevelReq = 80;
			return;

		case 13350: // pernix
		case 13348:
		case 13346:
			c.defenceLevelReq = 60;
			c.dungLevelReq = 82;
			c.rangeLevelReq = 80;
			return;

		case 18335: // arcane stream
			c.dungLevelReq = 50;
			c.magicLevelReq = 65;
			return;

		case 18509: // dung cape
			c.dungLevelReq = 98;
			return;

		case 4153:
			c.attackLevelReq = 50;
			c.strengthLevelReq = 50;
			return;
		}
	}

	public int getTabforItem(int itemID) {
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] == itemID || c.bankItems[i] == itemID + 1
					|| c.bankItems[i] == itemID - 1)
				return 0;
			else if (c.bankItems1[i] == itemID || c.bankItems1[i] == itemID + 1
					|| c.bankItems1[i] == itemID - 1)
				return 1;
			else if (c.bankItems2[i] == itemID || c.bankItems2[i] == itemID + 1
					|| c.bankItems2[i] == itemID - 1)
				return 2;
			else if (c.bankItems3[i] == itemID || c.bankItems3[i] == itemID + 1
					|| c.bankItems3[i] == itemID - 1)
				return 3;
			else if (c.bankItems4[i] == itemID || c.bankItems4[i] == itemID + 1
					|| c.bankItems4[i] == itemID - 1)
				return 4;
			else if (c.bankItems5[i] == itemID || c.bankItems5[i] == itemID + 1
					|| c.bankItems5[i] == itemID - 1)
				return 5;
			else if (c.bankItems6[i] == itemID || c.bankItems6[i] == itemID + 1
					|| c.bankItems6[i] == itemID - 1)
				return 6;
			else if (c.bankItems7[i] == itemID || c.bankItems7[i] == itemID + 1
					|| c.bankItems7[i] == itemID - 1)
				return 7;
			else if (c.bankItems8[i] == itemID || c.bankItems8[i] == itemID + 1
					|| c.bankItems8[i] == itemID - 1)
				return 8;
		}
		return c.bankingTab;// if not in bank add to current tab
	}

	public int getTotalCount(int itemID) {
		int count = 0;
		for (int j = 0; j < c.playerItems.length; j++) {
			if (Item.itemIsNote[itemID + 1]) {
				if (itemID + 2 == c.playerItems[j])
					count += c.playerItemsN[j];
			}
			if (!Item.itemIsNote[itemID + 1]) {
				if (itemID + 1 == c.playerItems[j]) {
					count += c.playerItemsN[j];
				}
			}
		}
		for (int j = 0; j < c.bankItems.length; j++) {
			if (c.bankItems[j] == itemID + 1)
				count += c.bankItemsN[j];
		}
		for (int j = 0; j < c.bankItems1.length; j++) {
			if (c.bankItems1[j] == itemID + 1)
				count += c.bankItems1N[j];
		}
		for (int j = 0; j < c.bankItems2.length; j++) {
			if (c.bankItems2[j] == itemID + 1)
				count += c.bankItems2N[j];
		}
		for (int j = 0; j < c.bankItems3.length; j++) {
			if (c.bankItems3[j] == itemID + 1)
				count += c.bankItems3N[j];
		}
		for (int j = 0; j < c.bankItems4.length; j++) {
			if (c.bankItems4[j] == itemID + 1)
				count += c.bankItems4N[j];
		}
		for (int j = 0; j < c.bankItems5.length; j++) {
			if (c.bankItems5[j] == itemID + 1)
				count += c.bankItems5N[j];
		}
		for (int j = 0; j < c.bankItems6.length; j++) {
			if (c.bankItems6[j] == itemID + 1)
				count += c.bankItems6N[j];
		}
		for (int j = 0; j < c.bankItems7.length; j++) {
			if (c.bankItems7[j] == itemID + 1)
				count += c.bankItems7N[j];
		}
		for (int j = 0; j < c.bankItems8.length; j++) {
			if (c.bankItems8[j] == itemID + 1)
				count += c.bankItems8N[j];
		}
		return count;
	}

	public int getUnnotedItem(int ItemID) {
		int NewID = ItemID - 1;
		String NotedName = "";
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					NotedName = Server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemName == NotedName) {
					if (Server.itemHandler.ItemList[i].itemDescription
							.startsWith("Swap this note at any bank for a") == false) {
						NewID = Server.itemHandler.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}

	public int getUntradePrice(int item) {
		switch (item) {
		case 2518:
		case 2524:
		case 2526:
			return 100000;
		case 2520:
		case 2522:
			return 150000;
		}
		return 0;
	}

	public void handleEffigies(int skillId) {
		int[] levelReq = { 91, 93, 95, 97 };
		if (System.currentTimeMillis() - c.lastThieve < 5000) {
			c.sendMessage("You really shouldn't be trying to do this. Please try again.");
			c.getPA().removeAllWindows();
			return;
		}
		if (c.getLastClicked() == 18778
				&& c.playerLevel[skillId] >= levelReq[0]) {
			if (c.getItems().playerHasItem(18778, 1)) {
				c.getItems().deleteItem(18778, 1);
				c.getItems().addItem(18779, 1);
				c.getPA().addSkillXP(15000, skillId);
				c.lastThieve = System.currentTimeMillis();
				c.startAnimation(7368);
				c.getPA().removeAllWindows();
				c.effigy = 0;
			}
		}
		if (c.getLastClicked() == 18779
				&& c.playerLevel[skillId] >= levelReq[1]) {
			if (c.getItems().playerHasItem(18779, 1)) {
				c.getItems().deleteItem(18779, 1);
				c.getItems().addItem(18780, 1);
				c.getPA().addSkillXP(30000, skillId);
				c.lastThieve = System.currentTimeMillis();
				c.startAnimation(7368);
				c.getPA().removeAllWindows();
				c.effigy = 0;
			}
		}
		if (c.getLastClicked() == 18780
				&& c.playerLevel[skillId] >= levelReq[2]) {
			if (c.getItems().playerHasItem(18780, 1)) {
				c.getItems().deleteItem(18780, 1);
				c.getItems().addItem(18781, 1);
				c.getPA().addSkillXP(45000, skillId);
				c.lastThieve = System.currentTimeMillis();
				c.startAnimation(7368);
				c.getPA().removeAllWindows();
				c.effigy = 0;
			}
		}
		if (c.getLastClicked() == 18781
				&& c.playerLevel[skillId] >= levelReq[3]) {
			if (c.getItems().playerHasItem(18781, 1)) {
				c.getItems().deleteItem(18781, 1);
				c.getItems().addItem(18782, 1);
				c.getPA().addSkillXP(60000, skillId);
				c.lastThieve = System.currentTimeMillis();
				c.startAnimation(7368);
				c.getPA().removeAllWindows();
				c.effigy = 0;
			}
		}
	}

	public void handleSpecialPickup(int itemId) {
		// c.sendMessage("My " + getItemName(itemId) +
		// " has been recovered. I should talk to the void knights to get it back.");
		// c.getItems().addToVoidList(itemId);
	}

	public boolean hasAllShards() {
		return playerHasItem(11712, 1) && playerHasItem(11712, 1)
				&& playerHasItem(11714, 1);
	}

	public boolean hasBlueArms() {
		return playerHasItem(6877, 1);
	}

	public boolean hasBlueHead() {
		return playerHasItem(6876, 1);
	}

	public boolean hasBlueMari() {
		return playerHasItem(946, 1) && playerHasItem(960, 1);
	}

	public boolean hasBlueTorso() {
		return playerHasItem(6875, 1);
	}

	public boolean hasGreenArms() {
		return playerHasItem(6881, 1);
	}

	public boolean hasGreenHead() {
		return playerHasItem(6880, 1);
	}

	public boolean hasGreenMari() {
		return playerHasItem(946, 1) && playerHasItem(960, 1);
	}

	public boolean hasGreenTorso() {
		return playerHasItem(6879, 1);
	}

	public boolean hasHandles() {
		return playerHasItem(946, 1) && playerHasItem(960, 1);
	}

	/**
	 * Christmas Event
	 **/
	public boolean hasPlank() {
		return playerHasItem(8794, 1) && playerHasItem(1511, 1);
	}

	public boolean hasRedArms() {
		return playerHasItem(6873, 1);
	}

	public boolean hasRedHead() {
		return playerHasItem(6872, 1);
	}

	public boolean hasRedMari() {
		return playerHasItem(946, 1) && playerHasItem(960, 1);
	}

	public boolean hasRedTorso() {
		return playerHasItem(6871, 1);
	}

	/**
	 * two handed weapon check
	 **/
	public boolean is2handed(String itemName, int itemId) {
		if (itemName.contains("ahrim") || itemName.contains("karil")
				|| itemName.contains("guthan") || itemName.contains("dharok")) {
			return true;
		}
		if (itemName.contains("crystal") || itemName.contains("maul")
				|| itemName.contains("cannon")) {
			return true;
		}
		if (itemName.contains("godsword") || itemName.contains("claw")
				|| itemName.contains("aradomin sword")
				|| itemName.contains("2h") || itemName.contains("spear")) {
			return true;
		}
		switch (itemId) {
		case 6724: // seercull
		case 11730:
		case 4153:
		case 6528:
		case 10887:
		case 14484:

			return true;
		}
		return false;
	}

	public boolean isHilt(int i) {
		return i >= 11702 && i <= 11708 && i % 2 == 0;
	}

	public boolean isStackable(int itemID) {
		return Item.itemStackable[itemID];
	}

	public int itemAmount(int itemID) {
		int tempAmount = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] == itemID) {
				tempAmount += c.playerItemsN[i];
			}
		}
		return tempAmount;
	}

	public void itemOnInterface(int id, int amount) {
		if (c != null) {
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(2274);
			c.getOutStream().writeWord(1);
			if (amount > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord_v2(amount);
			} else {
				c.getOutStream().writeByte(amount);
			}
			c.getOutStream().writeWordBigEndianA(id);
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public String itemType(int item) {
		if (Item.playerCape(item)) {
			return "cape";
		}
		if (Item.playerBoots(item)) {
			return "boots";
		}
		if (Item.playerGloves(item)) {
			return "gloves";
		}
		if (Item.playerShield(item)) {
			return "shield";
		}
		if (Item.playerAmulet(item)) {
			return "amulet";
		}
		if (Item.playerArrows(item)) {
			return "arrows";
		}
		if (Item.playerRings(item)) {
			return "ring";
		}
		if (Item.playerHats(item)) {
			return "hat";
		}
		if (Item.playerLegs(item)) {
			return "legs";
		}
		if (Item.playerBody(item)) {
			return "body";
		}
		if (item == 1) {
			return "cape";
		}
		if (item == 1052) {
			return "cape";
		}
		if (item == 19712) {
			return "shield";
		}
		if (item == 16711) {
			return "hat";
		}
		if (item == 19747) {
			return "hat";
		}
		if (item == 686) {
			return "weapon";
		}
		if (item == 16733) {
			return "body";
		}
		if (item == 2599) {
			return "body";
		}
		if (item == 17259) {
			return "body";
		}
		if (item == 14791) {
			return "hat";
		}
		if (item == 16689) {
			return "legs";
		}
		if (item == 11118) {
			return "ring";
		}

		if (item == 20010) {
			return "body";
		}
		if (item == 20009) {
			return "hat";
		}
		if (item == 6129) {
			return "body";
		}
		if (item == 20006) {
			return "boots";
		}
		if (item == 20005) {
			return "hat";
		}
		if (item == 20007) {
			return "legs";
		}
		if (item == 20008) {
			return "body";
		}
		if (item == 20006) {
			return "body";
		}
		if (item == 16428) {
			return "hat";
		}
		if (item == 15117) {
			return "cape";
		}
		if (item == 9419) {
			return "Arrows";
		}
		if (item == 15120) {
			return "cape";
		}
		if (item == 15121) {
			return "cape";
		}
		if (item == 15118) {
			return "hat";
		}
		if (item == 15119) {
			return "hat";
		}
		if (item == 15124) {
			return "hat";
		}

		if (item == 11119) {
			return "ring";
		}
		if (item == 11120) {
			return "ring";
		}
		if (item == 11121) {
			return "ring";
		}
		if (item == 11122) {
			return "ring";
		}
		if (item == 11123) {
			return "ring";
		}
		if (item == 11124) {
			return "ring";
		}
		if (item == 11125) {
			return "ring";
		}
		if (item == 11126) {
			return "ring";
		}
		if (item == 11127) {
			return "ring";
		}
		if (item == 16667) {
			return "legs";
		}
		if (item == 17361) {
			return "shield";
		}
		if (item == 16359) {
			return "boots";
		}
		if (item == 16293) {
			return "gloves";
		}
		if (item == 20116) {
			return "body";
		}
		return "weapon";
	}

	/**
	 * Item kept on death
	 **/

	public void keepItem(int keepItem, boolean deleteItem) {
		int value = 0;
		int item = 0;
		int slotId = 0;
		boolean itemInInventory = false;

		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] - 1 > 0) {
				int inventoryItemValue = c.getShops().getItemShopValue(
						c.playerItems[i] - 1);
				if (inventoryItemValue > value && (!c.invSlot[i])) {
					value = inventoryItemValue;
					item = c.playerItems[i] - 1;
					slotId = i;
					itemInInventory = true;
				}
			}
		}
		for (int i1 = 0; i1 < c.playerEquipment.length; i1++) {
			if (c.playerEquipment[i1] > 0) {
				int equipmentItemValue = c.getShops().getItemShopValue(
						c.playerEquipment[i1]);
				if (equipmentItemValue > value && (!c.equipSlot[i1])) {
					value = equipmentItemValue;
					item = c.playerEquipment[i1];
					slotId = i1;
					itemInInventory = false;
				}
			}
		}
		if (itemInInventory) {
			c.invSlot[slotId] = true;
			if (deleteItem) {
				deleteItem(c.playerItems[slotId] - 1,
						getItemSlot(c.playerItems[slotId] - 1), 1);
			}
		} else {
			c.equipSlot[slotId] = true;
			if (deleteItem) {
				deleteEquipment(item, slotId);
			}
		}
		c.itemKeptId[keepItem] = item;
	}

	public void makeBlade() {
		deleteItem(11710, 1);
		deleteItem(11712, 1);
		deleteItem(11714, 1);
		addItem(11690, 1);
		c.sendMessage("You combine the shards to make a blade.");
	}

	public void makeBlueMari() {
		deleteItem(6864, 1);
		deleteItem(6878, 1);
		addItem(6868, 1);
		c.sendMessage("You make a Blue Marionette.");
	}

	public void makeGodsword(int i) {
		if (playerHasItem(11690) && playerHasItem(i)) {
			deleteItem(11690, 1);
			deleteItem(i, 1);
			addItem(i - 8, 1);
			c.sendMessage("You combine the hilt and the blade to make a godsword.");
		}
	}

	public void makeGreenMari() {
		deleteItem(6864, 1);
		deleteItem(6882, 1);
		addItem(6869, 1);
		c.sendMessage("You make a Green Marionette.");
	}

	public void makeHandles() {
		deleteItem(960, 1);
		addItem(6864, 1);
		c.sendMessage("You make some Marionette Handles.");
	}

	public void makePlank() {
		deleteItem(1511, 1);
		addItem(960, 1);
		c.sendMessage("You make an Plank.");
	}

	public void makeRedMari() {
		deleteItem(6864, 1);
		deleteItem(6874, 1);
		addItem(6870, 1);
		c.sendMessage("You make a Red Marionette.");
	}

	/**
	 * Move Items
	 **/

	public void moveItems(int from, int to, int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;
			tempI = c.playerItems[from];
			tempN = c.playerItemsN[from];

			c.playerItems[from] = c.playerItems[to];
			c.playerItemsN[from] = c.playerItemsN[to];
			c.playerItems[to] = tempI;
			c.playerItemsN[to] = tempN;
		}

		if (moveWindow == 34453 && from >= 0 && to >= 0
				&& from < Config.BANK_SIZE && to < Config.BANK_SIZE
				&& to < Config.BANK_SIZE) {
			int tempI;
			int tempN;
			tempI = c.bankItems[from];
			tempN = c.bankItemsN[from];

			c.bankItems[from] = c.bankItems[to];
			c.bankItemsN[from] = c.bankItemsN[to];
			c.bankItems[to] = tempI;
			c.bankItemsN[to] = tempN;
		}

		if (moveWindow == 34453) {
			resetBank();
		}
		if (moveWindow == 18579) {
			int tempI;
			int tempN;
			tempI = c.playerItems[from];
			tempN = c.playerItemsN[from];

			c.playerItems[from] = c.playerItems[to];
			c.playerItemsN[from] = c.playerItemsN[to];
			c.playerItems[to] = tempI;
			c.playerItemsN[to] = tempN;
			// //updateInventory = true;
			resetItems(3214);
		}
		resetTempItems();
		if (moveWindow == 3724) {
			// //updateInventory = true;
			resetItems(3214);
		}

	}

	public boolean ownsCape() {
		if (c.getItems().playerHasItem(2412, 1)
				|| c.getItems().playerHasItem(2413, 1)
				|| c.getItems().playerHasItem(2414, 1))
			return true;
		for (int j = 0; j < Config.BANK_SIZE; j++) {
			if (c.bankItems[j] == 2412 || c.bankItems[j] == 2413
					|| c.bankItems[j] == 2414)
				return true;
		}
		if (c.playerEquipment[Player.playerCape] == 2413
				|| c.playerEquipment[Player.playerCape] == 2414
				|| c.playerEquipment[Player.playerCape] == 2415)
			return true;
		return false;
	}

	public boolean playerHasEquipped(int itemID) {
		itemID++;
		for (int i = 0; i < c.playerEquipment.length; i++) {
			if (c.playerEquipment[i] == itemID) {
				return true;
			}
		}
		return false;
	}

	public boolean playerHasItem(int itemID) {
		itemID++;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] == itemID)
				return true;
		}
		return false;
	}

	public boolean playerHasItem(int itemID, int amt) {
		itemID++;
		int found = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if (c.playerItems[i] == itemID) {
				if (c.playerItemsN[i] >= amt) {
					return true;
				} else {
					found++;
				}
			}
		}
		if (found >= amt) {
			return true;
		}
		return false;
	}

	public boolean playerHasItem(int itemID, int amt, int slot) {
		itemID++;
		int found = 0;
		if (c.playerItems[slot] == (itemID)) {
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] == itemID) {
					if (c.playerItemsN[i] >= amt) {
						return true;
					} else {
						found++;
					}
				}
			}
			if (found >= amt) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * BANK
	 */

	public void rearrangeBank() {
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankingItems[i] != 0) {
				totalItems++;
				if (highestSlot <= i) {
					highestSlot = i;
				}
			}
		}

		for (int i = 0; i <= highestSlot; i++) {
			if (c.bankingItems[i] == 0) {
				boolean stop = false;

				for (int k = i; k <= highestSlot; k++) {
					if (c.bankingItems[k] != 0 && !stop) {
						int spots = k - i;
						for (int j = k; j <= highestSlot; j++) {
							c.bankingItems[j - spots] = c.bankingItems[j];
							c.bankingItemsN[j - spots] = c.bankingItemsN[j];
							stop = true;
							c.bankingItems[j] = 0;
							c.bankingItemsN[j] = 0;
						}
					}
				}
			}
		}

		int totalItemsAfter = 0;
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankingItems[i] != 0) {
				totalItemsAfter++;
			}
		}

		if (totalItems != totalItemsAfter)
			c.disconnected = true;
	}

	public void removeAllItems() {
		for (int i = 0; i < c.playerItems.length; i++) {
			c.playerItems[i] = 0;
		}
		for (int i = 0; i < c.playerItemsN.length; i++) {
			c.playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	/**
	 * Pickup Item
	 **/

	public void removeGroundItem(int itemID, int itemX, int itemY, int Amount) {
		if (c != null) {
			c.getOutStream().createFrame(85);
			c.getOutStream().writeByteC((itemY - 8 * c.mapRegionY));
			c.getOutStream().writeByteC((itemX - 8 * c.mapRegionX));
			c.getOutStream().createFrame(156);
			c.getOutStream().writeByteS(0);
			c.getOutStream().writeWord(itemID);
			c.flushOutStream();
		}
	}

	public void removeItem(int slot) {
		// if(c != null) {
		if (c.getOutStream() != null && c != null) {
			if (c.playerEquipment[slot] > -1) {

				if (addItem(c.playerEquipment[slot], c.playerEquipmentN[slot])) {
					c.playerEquipment[slot] = -1;
					c.playerEquipmentN[slot] = 0;
					sendWeapon(c.playerEquipment[Player.playerWeapon],
							getItemName(c.playerEquipment[Player.playerWeapon]));
					resetBonus();
					getBonus();
					writeBonus();
					c.getCombat()
							.getPlayerAnimIndex(
									c.getItems()
											.getItemName(
													c.playerEquipment[Player.playerWeapon])
											.toLowerCase());
					c.getOutStream().createFrame(34);
					c.getOutStream().writeWord(6);
					c.getOutStream().writeWord(1688);
					c.getOutStream().writeByte(slot);
					c.getOutStream().writeWord(0);
					c.getOutStream().writeByte(0);
					c.flushOutStream();
					c.updateRequired = true;
					c.setAppearanceUpdateRequired(true);
					c.isFullHelm = Item
							.isFullHelm(c.playerEquipment[Player.playerHat]);
					c.isFullMask = Item
							.isFullMask(c.playerEquipment[Player.playerHat]);
					c.isFullBody = Item
							.isFullBody(c.playerEquipment[Player.playerChest]);
				}
			}
		}
	}

	/**
	 * Remove Item
	 **/
	public void removeItem(int wearID, int slot) {
		if (c != null) {
			if (c.getOutStream() != null && c != null) {
				if (c.playerEquipment[slot] > -1) {
					if (addItem(c.playerEquipment[slot],
							c.playerEquipmentN[slot])) {
						c.playerEquipment[slot] = -1;
						c.playerEquipmentN[slot] = 0;
						sendWeapon(
								c.playerEquipment[Player.playerWeapon],
								getItemName(c.playerEquipment[Player.playerWeapon]));
						resetBonus();
						getBonus();
						writeBonus();
						c.getCombat()
								.getPlayerAnimIndex(
										c.getItems()
												.getItemName(
														c.playerEquipment[Player.playerWeapon])
												.toLowerCase());
						c.getOutStream().createFrame(34);
						c.getOutStream().writeWord(6);
						c.getOutStream().writeWord(1688);
						c.getOutStream().writeByte(slot);
						c.getOutStream().writeWord(0);
						c.getOutStream().writeByte(0);
						c.flushOutStream();
						c.updateRequired = true;
						c.setAppearanceUpdateRequired(true);
						c.isFullHelm = Item
								.isFullHelm(c.playerEquipment[Player.playerHat]);
						c.isFullMask = Item
								.isFullMask(c.playerEquipment[Player.playerHat]);
						c.isFullBody = Item
								.isFullBody(c.playerEquipment[Player.playerChest]);
					}
				}
			}
		}
	}

	public void replaceItem(Client c, int i, int l) {
		for (int k = 0; k < c.playerItems.length; k++) {
			if (playerHasItem(i, 1)) {
				deleteItem(i, getItemSlot(i), 1);
				addItem(l, 1);
			}
		}
	}

	public void resetBank() {
		// synchronized(c) {
		if (c == null) {
			return;
		}
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(5382); // bank
		c.getOutStream().writeWord(Config.BANK_SIZE);
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankingItemsN[i] > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord_v2(c.bankingItemsN[i]);
			} else {
				c.getOutStream().writeByte(c.bankingItemsN[i]);
			}
			if (c.bankingItemsN[i] < 1) {
				c.bankingItems[i] = 0;
			}
			if (c.bankingItems[i] > Config.ITEM_LIMIT || c.bankingItems[i] < 0) {
				c.bankingItems[i] = Config.ITEM_LIMIT;
			}
			c.getOutStream().writeWordBigEndianA(c.bankingItems[i]);
		}
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
		// }
	}

	public void resetBank(int[] itemData, int[] amountData) {
		// synchronized(c) {
		c.getOutStream().createFrameVarSizeWord(53);
		c.getOutStream().writeWord(5382); // bank
		c.getOutStream().writeWord(Config.BANK_SIZE);
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (amountData[i] > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord_v2(amountData[i]);
			} else {
				c.getOutStream().writeByte(amountData[i]);
			}
			if (amountData[i] < 1) {
				itemData[i] = 0;
			}
			if (itemData[i] > Config.ITEM_LIMIT || itemData[i] < 0) {
				itemData[i] = Config.ITEM_LIMIT;
			}
			c.getOutStream().writeWordBigEndianA(itemData[i]);
		}
		c.getOutStream().endFrameVarSizeWord();
		c.flushOutStream();
		// }
	}

	public void resetBonus() {
		for (int i = 0; i < c.playerBonus.length; i++) {
			c.playerBonus[i] = 0;
		}
	}

	public void resetItems(int WriteFrame) {
		if (c != null) {
			if (c.getOutStream() != null && c != null) {
				c.getOutStream().createFrameVarSizeWord(53);
				c.getOutStream().writeWord(WriteFrame);
				c.getOutStream().writeWord(c.playerItems.length);
				for (int i = 0; i < c.playerItems.length; i++) {
					if (c.playerItemsN[i] > 254) {
						c.getOutStream().writeByte(255);
						c.getOutStream().writeDWord_v2(c.playerItemsN[i]);
					} else {
						c.getOutStream().writeByte(c.playerItemsN[i]);
					}
					c.getOutStream().writeWordBigEndianA(c.playerItems[i]);
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
			}
		}
	}

	/**
	 * Reset items kept on death
	 **/

	public void resetKeepItems() {
		for (int i = 0; i < c.itemKeptId.length; i++) {
			c.itemKeptId[i] = -1;
		}
		for (int i1 = 0; i1 < c.invSlot.length; i1++) {
			c.invSlot[i1] = false;
		}
		for (int i2 = 0; i2 < c.equipSlot.length; i2++) {
			c.equipSlot[i2] = false;
		}
	}

	public void resetTempItems() {
		if (c != null) {
			int itemCount = 0;
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] > -1) {
					itemCount = i;
				}
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(5064);
			c.getOutStream().writeWord(itemCount + 1);
			for (int i = 0; i < itemCount + 1; i++) {
				if (c.playerItemsN[i] > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(c.playerItemsN[i]);
				} else {
					c.getOutStream().writeByte(c.playerItemsN[i]);
				}
				if (c.playerItems[i] > Config.ITEM_LIMIT
						|| c.playerItems[i] < 0) {
					c.playerItems[i] = Config.ITEM_LIMIT;
				}
				c.getOutStream().writeWordBigEndianA(c.playerItems[i]);
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public void sendItemsKept() {
		if (c != null) {
			if (c.getOutStream() != null && c != null) {
				c.getOutStream().createFrameVarSizeWord(53);
				c.getOutStream().writeWord(6963);
				c.getOutStream().writeWord(c.itemKeptId.length);
				for (int i = 0; i < c.itemKeptId.length; i++) {
					if (c.playerItemsN[i] > 254) {
						c.getOutStream().writeByte(255);
						c.getOutStream().writeDWord_v2(1);
					} else {
						c.getOutStream().writeByte(1);
					}
					if (c.itemKeptId[i] > 0) {
						c.getOutStream().writeWordBigEndianA(
								c.itemKeptId[i] + 1);
					} else {
						c.getOutStream().writeWordBigEndianA(0);
					}
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
			}
		}
	}

	/**
	 * Wear Item
	 **/

	public void sendWeapon(int Weapon, String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Drag", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed")) {
			c.setSidebarInterface(0, 5855); // punch, kick, block
			c.getPA().sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			c.setSidebarInterface(0, 12290); // flick, lash, deflect
			c.getPA().sendFrame246(12291, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 12293);
		} else if (WeaponName2.toLowerCase().contains("maul")
				|| WeaponName.endsWith("warhammer")) {
			c.setSidebarInterface(0, 425); // war hamer equip.
			c.getPA().sendFrame246(426, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 428);
		} else if (WeaponName.endsWith("bow") || WeaponName.endsWith("10")
				|| c.playerEquipment[Player.playerWeapon] == 13879
				|| c.playerEquipment[Player.playerWeapon] == 15241
				|| c.playerEquipment[Player.playerWeapon] == 18357
				|| c.playerEquipment[Player.playerWeapon] == 13880
				|| c.playerEquipment[Player.playerWeapon] == 13881
				|| c.playerEquipment[Player.playerWeapon] == 13882
				|| c.playerEquipment[Player.playerWeapon] == 13883
				|| c.playerEquipment[Player.playerWeapon] == 14990
				|| WeaponName.endsWith("full")
				|| WeaponName.startsWith("seercull")) {
			c.setSidebarInterface(0, 1764); // accurate, rapid, longrange
			c.getPA().sendFrame246(1765, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 1767);
		} else if (WeaponName2.startsWith("dagger")
				|| c.playerEquipment[Player.playerWeapon] == 13905
				|| c.playerEquipment[Player.playerWeapon] == 13899
				|| WeaponName2.contains("Staff of light")
				|| c.playerEquipment[Player.playerWeapon] == 20403
				|| c.playerEquipment[Player.playerWeapon] == 18349
				|| c.playerEquipment[Player.playerWeapon] == 10887
				|| WeaponName2.contains("sword")) {
			c.setSidebarInterface(0, 2276); // stab, lunge, slash, block
			c.getPA().sendFrame246(2277, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 2279);
		} else if (WeaponName.startsWith("Staff")
				|| WeaponName.endsWith("staff") || WeaponName.endsWith("wand")) {
			c.setSidebarInterface(0, 328); // spike, impale, smash, block
			c.getPA().sendFrame246(329, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 331);
		} else if (WeaponName2.startsWith("dart")
				|| WeaponName2.startsWith("knife")
				|| WeaponName2.startsWith("javelin")
				|| WeaponName2.startsWith("zaryte-bow")
				|| WeaponName2.startsWith("zaryte")
				|| WeaponName.equalsIgnoreCase("toktz-xil-ul")) {
			c.setSidebarInterface(0, 4446); // accurate, rapid, longrange
			c.getPA().sendFrame246(4447, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 4449);
		} else if (WeaponName2.startsWith("pickaxe")) {
			c.setSidebarInterface(0, 5570); // spike, impale, smash, block
			c.getPA().sendFrame246(5571, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 5573);
		} else if (WeaponName2.startsWith("axe")
				|| WeaponName2.startsWith("battleaxe")) {
			c.setSidebarInterface(0, 1698); // chop, hack, smash, block
			c.getPA().sendFrame246(1699, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("halberd")) {
			c.setSidebarInterface(0, 8460); // jab, swipe, fend
			c.getPA().sendFrame246(8461, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 8463);
		} else if (c.playerEquipment[Player.playerWeapon] == 14484) {
			c.setSidebarInterface(0, 7762); // claws
			c.getPA().sendFrame246(7763, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 7765);
		} else if (WeaponName2.startsWith("scythe")) {
			c.setSidebarInterface(0, 8460); // jab, swipe, fend
			c.getPA().sendFrame246(8461, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("spear")) {
			c.setSidebarInterface(0, 4679); // lunge, swipe, pound, block
			c.getPA().sendFrame246(4680, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 4682);
		} else if (WeaponName2.toLowerCase().contains("mace")
				|| c.playerEquipment[Player.playerWeapon] == 13902) {
			c.setSidebarInterface(0, 3796);
			c.getPA().sendFrame246(3797, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 3799);
		} else if (c.playerEquipment[Player.playerWeapon] == 4153) {
			c.setSidebarInterface(0, 425); // war hamer equip.
			c.getPA().sendFrame246(426, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 428);
		} else if (c.playerEquipment[Player.playerWeapon] == 18351) {
			c.setSidebarInterface(0, 2423); // war hamer equip.
			c.getPA().sendFrame246(426, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 428);
		} else {
			c.setSidebarInterface(0, 2423); // chop, slash, lunge, block
			c.getPA().sendFrame246(2424, 200, Weapon);
			c.getPA().sendFrame126(WeaponName, 2426);
		}

	}

	/**
	 * Update Equip tab
	 **/

	public void setEquipment(int wearID, int amount, int targetSlot) {
		if (c != null) {
			c.getOutStream().createFrameVarSizeWord(34);
			c.getOutStream().writeWord(1688);
			c.getOutStream().writeByte(targetSlot);
			c.getOutStream().writeWord(wearID + 1);
			if (amount > 254) {
				c.getOutStream().writeByte(255);
				c.getOutStream().writeDWord(amount);
			} else {
				c.getOutStream().writeByte(amount);
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
			c.playerEquipment[targetSlot] = wearID;
			c.playerEquipmentN[targetSlot] = amount;
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
			c.isFullHelm = Item.isFullHelm(c.playerEquipment[Player.playerHat]);
			c.isFullMask = Item.isFullMask(c.playerEquipment[Player.playerHat]);
			c.isFullBody = Item
					.isFullBody(c.playerEquipment[Player.playerChest]);
		}
	}

	/**
	 * Specials bar filling amount
	 **/

	public void specialAmount(int weapon, double specAmount, int barId) {
		c.specBarId = barId;
		c.getPA().sendFrame70(specAmount >= 10 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 9 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 8 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 7 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 6 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 5 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 4 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 3 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 2 ? 500 : 0, 0, (--barId));
		c.getPA().sendFrame70(specAmount >= 1 ? 500 : 0, 0, (--barId));
		updateSpecialBar();
		sendWeapon(weapon, getItemName(weapon));
	}

	public boolean specialCase(int itemId) {
		switch (itemId) {
		case 2518:
		case 2520:
		case 2522:
		case 2524:
		case 2526:
			return true;
		}
		return false;
	}

	/**
	 * Items
	 **/
	public void toTab(int tab, int fromSlot) {
		if (tab == c.bankingTab)
			return;
		if (tab > c.getPA().getTabCount() + 1)
			return;
		if (c.searchTerm != "N/A")
			return;
		if (c.getPA().getBankItems(tab) >= 350) {
			c.sendMessage("You can't store any more items in this tab!");
			return;
		}
		if (!c.isBanking)
			return;
		int id = c.bankingItems[fromSlot];
		/*
		 * if(getTotalCount(id) == 0) return;//player doesn't have item!
		 */
		int amount = c.bankingItemsN[fromSlot];
		int[] invItems = new int[28];
		int[] invItemsN = new int[28];
		for (int i = 0; i < c.playerItems.length; i++) {
			invItems[i] = c.playerItems[i];
			invItemsN[i] = c.playerItemsN[i];
			c.playerItems[i] = 0;
			c.playerItemsN[i] = 0;
		}
		c.playerItems[0] = id;
		c.playerItemsN[0] = amount;
		c.bankingItems[fromSlot] = -1;
		c.bankingItemsN[fromSlot] = 0;
		if (tab == 0)
			bankItem(id, 0, amount, c.bankItems, c.bankItemsN);
		else if (tab == 1)
			bankItem(id, 0, amount, c.bankItems1, c.bankItems1N);
		else if (tab == 2)
			bankItem(id, 0, amount, c.bankItems2, c.bankItems2N);
		else if (tab == 3)
			bankItem(id, 0, amount, c.bankItems3, c.bankItems3N);
		else if (tab == 4)
			bankItem(id, 0, amount, c.bankItems4, c.bankItems4N);
		else if (tab == 5)
			bankItem(id, 0, amount, c.bankItems5, c.bankItems5N);
		else if (tab == 6)
			bankItem(id, 0, amount, c.bankItems6, c.bankItems6N);
		else if (tab == 7)
			bankItem(id, 0, amount, c.bankItems7, c.bankItems7N);
		else if (tab == 8)
			bankItem(id, 0, amount, c.bankItems8, c.bankItems8N);
		for (int i = 0; i < invItems.length; i++) {
			c.playerItems[i] = invItems[i];
			c.playerItemsN[i] = invItemsN[i];
		}
		c.getPA().openUpBank(c.bankingTab);// refresh
		c.getPA().openUpBank(c.bankingTab);// refresh twice to ensure update
	}

	public boolean tradeable(int itemId) {
		for (int j = 0; j < Config.ITEM_TRADEABLE.length; j++) {
			if (itemId == Config.ITEM_TRADEABLE[j])
				return false;
		}
		return true;
	}

	public void updateInventory() {
		updateInventory = false;
		resetItems(3214);
	}

	public void updateSlot(int slot) {
		if (c != null) {
			if (c.getOutStream() != null && c != null) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(1688);
				c.getOutStream().writeByte(slot);
				c.getOutStream().writeWord(c.playerEquipment[slot] + 1);
				if (c.playerEquipmentN[slot] > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(c.playerEquipmentN[slot]);
				} else {
					c.getOutStream().writeByte(c.playerEquipmentN[slot]);
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
			}
		}

	}

	/**
	 * Special attack text and what to highlight or blackout
	 **/

	public void updateSpecialBar() {
		if (c.usingSpecial && c.playerEquipment[Player.playerWeapon] != 15486) {
			c.getPA().sendFrame126(
					"@yel@ Special Attack (" + (int) c.specAmount * 10 + "%)",
					c.specBarId);
		} else {
			c.getPA().sendFrame126(
					"@bla@ Special Attack (" + (int) c.specAmount * 10 + "%)",
					c.specBarId);
		}
	}

	/**
	 * Wear Item
	 **/

	public boolean wearItem(int wearID, int slot) {
		if (c.isDonator == 0)
			for (int i : donItems)
				if (wearID == i) {
					c.sendMessage("You must be at least Bronze Member to wear this item.");
					return false;
				}
		int targetSlot = 0;
		if (wearID == 7927) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("As you put on the ring you turn into an egg!");
			c.npcId2 = 3689;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}

		if (wearID == 4804) {
			LoyaltyHandler.SharpShooterAura(c);
			return false;
		}
		if (wearID == 4805) {
			LoyaltyHandler.AsuraAura(c);
			return false;
		}
		if (c.wearId == 4025) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("you transformed!");
			c.npcId2 = 1481;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (c.wearId == 12169 && c.playerLevel[21] != 99) {
			c.sendMessage("You must have atleast level 99 Summoning to wield this item.");
			return false;
		}
		/*
		 * Castle wars
		 */
		if (c.wearId == 12170 && c.playerLevel[21] != 99) {
			c.sendMessage("You must have atleast level 99 Summoning to wield this item.");
			return false;
		}
		if (c.wearId == 12171 && c.playerLevel[21] != 99) {
			c.sendMessage("You must have atleast level 99 Summoning to wield this item.");
			return false;
		}
		if (c.wearId == 4026) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("you transformed!");
			c.npcId2 = 1482;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (c.wearId == 4029) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("you transformed!");
			c.npcId2 = 1465;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (c.wearId == 295) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("As you put on the H'ween amulet you turn into Dracula!");
			c.npcId2 = 757;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (c.wearId == 13560) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("As you put on the ring you turn into balance elemental");
			c.npcId2 = 8546;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (c.wearId == 13561) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("As you put on the ring you turn into Nex");
			c.npcId2 = 2636;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (c.wearId == 13562) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("As you put on the ring you turn into The A Dung Boss");
			c.npcId2 = 10141;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}

		if (c.wearId == 6583) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("As you put on the ring you turn into a rock!");
			c.npcId2 = 2626;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (c.wearId == 4024) {
			c.sendMessage("Type ::unpc to turn back to normal!");
			c.sendMessage("you transformed!");
			c.npcId2 = 1459;
			c.isNpc = true;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
		if (c.inCwWait == true || c.inCwGame) {
			if (targetSlot == 1) {
				c.sendMessage("You can't wear your own capes Castle Wars Game!");
				return false;
			}
		}
		if ((wearID == 773 || wearID == 773) && (c.playerRights < 3)) {
			c.sendMessage("This is a Owner only item.");
			return false;
		}/*
		 * if ((wearID ==1127) && c.kingQuest < 8) {
		 * c.sendMessage("You need to complete Dragon Slayer to wear this item."
		 * ); c.sendMessage(
		 * "To start the quest, talk to Razgar in Edgeville, outside bank!");
		 * return false; } if ((wearID ==15121)) { if(c.playerLevel[1] <= 99 &&
		 * c.playerLevel[2] <= 99 && c.playerLevel[3] <= 99 && c.playerLevel[4]
		 * <= 99 && c.playerLevel[5] <= 99 && c.playerLevel[6] <= 99 &&
		 * c.playerLevel[7] <= 99 && c.playerLevel[8] <= 99 && c.playerLevel[9]
		 * <= 99 && c.playerLevel[10] <= 99 && c.playerLevel[11] <= 99 &&
		 * c.playerLevel[12] <= 99 && c.playerLevel[13] <= 99 &&
		 * c.playerLevel[14] <= 99 && c.playerLevel[15] <= 99 &&
		 * c.playerLevel[16] <= 99 && c.playerLevel[17] <= 99 &&
		 * c.playerLevel[18] <= 99 && c.playerLevel[19] <= 99 &&
		 * c.playerLevel[20] <= 99 && c.playerLevel[21] <= 99 &&
		 * c.playerLevel[22] <= 99 && c.playerLevel[24] <= 99 &&
		 * c.playerLevel[23] <= 99) { c.sendMessage(
		 * "You need to have all 99's to unleash the power of this cape!");
		 * return false; } }
		 */

		// if ((wearID ==15117)) {
		// c.sendMessage("Your Veteran Cape will now power you.");
		// c.VetCape();
		// }
		if (c != null) {
			if (wearID > 15085 && wearID < 15102) {
				// if (c.clanId >= 0) {
				// 7} else {
				// c.sendMessage("You must be in a clan chat channel to do that.");
				// }
				return false;
			}
			if (targetSlot == 3) {
				c.usingSpecial = false;
				addSpecialBar(wearID);
			}
			boolean canWearItem = true;
			switch (wearID) {
			case 5733: // Rotten potato
				if (c.playerRights <= 0) {
					c.sendMessage("You do not have sufficient permissions to do that.");
					c.sendMessage("Please send a message to Anfernio telling how you received this.");
					return false;
				} else {
					if (c.playerRights > 1 && c.inWild()) {
						c.sendMessage("You're not allowed to do that inside of the Wilderness.");
						return false;
					} else {
						c.playerLevel[3] += 99;
						c.getPA().refreshSkill(3);
						c.sendMessage("You add 99 onto your current HP.");
						return false;
					}
				}
			case 9747:
			case 9748:
			case 9749:
				if (c.getPA().getLevelForXP(c.playerXP[0]) < 99) {
					c.sendMessage("You need 99 attack to wear this item.");
					return false;
				}
				break;

			case 9750:
			case 9751:
			case 9752:
				if (c.getPA().getLevelForXP(c.playerXP[2]) < 99) {
					c.sendMessage("You need 99 strength to wear this item.");
					return false;
				}
				break;

			case 9753:
			case 9754:
			case 9755:
				if (c.getPA().getLevelForXP(c.playerXP[1]) < 99) {
					c.sendMessage("You need 99 defence to wear this item.");
					return false;
				}
				break;

			case 9756:
			case 9757:
			case 9758:
				if (c.getPA().getLevelForXP(c.playerXP[4]) < 99) {
					c.sendMessage("You need 99 range to wear this item.");
					return false;
				}
				break;

			case 9759:
			case 9760:
			case 9761:
				if (c.getPA().getLevelForXP(c.playerXP[5]) < 99) {
					c.sendMessage("You need 99 prayer to wear this item.");
					return false;
				}
				break;

			case 9762:
			case 9763:
			case 9764:
				if (c.getPA().getLevelForXP(c.playerXP[6]) < 99) {
					c.sendMessage("You need 99 magic to wear this item.");
					return false;
				}
				break;

			case 9768:
			case 9769:
			case 9770:
				if (c.getPA().getLevelForXP(c.playerXP[3]) < 99) {
					c.sendMessage("You need 99 hitpoints to wear this item.");
					return false;
				}
				break;

			case 9767:
			case 9765:
			case 9766:
				if (c.getPA().getLevelForXP(c.playerXP[20]) < 99) {
					c.sendMessage("You need 99 runecrafting to wear this item.");
					return false;
				}
				break;

			case 9771:
			case 9772:
			case 9773:
				if (c.getPA().getLevelForXP(c.playerXP[16]) < 99) {
					c.sendMessage("You need 99 agility to wear this item.");
					return false;
				}
				break;

			case 9774:
			case 9775:
			case 9776:
				if (c.getPA().getLevelForXP(c.playerXP[15]) < 99) {
					c.sendMessage("You need 99 Herblore to wear this item.");
					return false;
				}
				break;

			case 9777:
			case 9778:
			case 9779:
				if (c.getPA().getLevelForXP(c.playerXP[17]) < 99) {
					c.sendMessage("You need 99 Thieving to wear this item.");
					return false;
				}
				break;

			case 9780:
			case 9781:
			case 9782:
				if (c.getPA().getLevelForXP(c.playerXP[12]) < 99) {
					c.sendMessage("You need 99 Crafting to wear this item.");
					return false;
				}
				break;

			case 9783:
			case 9784:
			case 9785:
				if (c.getPA().getLevelForXP(c.playerXP[9]) < 99) {
					c.sendMessage("You need 99 Fletching to wear this item.");
					return false;
				}
				break;

			case 9786:
			case 9787:
			case 9788:
				if (c.getPA().getLevelForXP(c.playerXP[18]) < 99) {
					c.sendMessage("You need 99 Slayer to wear this item.");
					return false;
				}
				break;

			case 9792:
			case 9793:
			case 9794:
				if (c.getPA().getLevelForXP(c.playerXP[14]) < 99) {
					c.sendMessage("You need 99 Mining to wear this item.");
					return false;
				}
				break;

			case 9795:
			case 9796:
			case 9797:
				if (c.getPA().getLevelForXP(c.playerXP[13]) < 99) {
					c.sendMessage("You need 99 Smithing to wear this item.");
					return false;
				}
				break;

			case 9798:
			case 9799:
			case 9800:
				if (c.getPA().getLevelForXP(c.playerXP[10]) < 99) {
					c.sendMessage("You need 99 Fishing to wear this item.");
					return false;
				}
				break;

			case 9801:
			case 9802:
			case 9803:
				if (c.getPA().getLevelForXP(c.playerXP[7]) < 99) {
					c.sendMessage("You need 99 Cooking to wear this item.");
					return false;
				}
				break;

			case 9806:
			case 9805:
			case 9804:
				if (c.getPA().getLevelForXP(c.playerXP[11]) < 99) {
					c.sendMessage("You need 99 Firemaking to wear this item.");
					return false;
				}
				break;

			case 9807:
			case 9808:
			case 9809:
				if (c.getPA().getLevelForXP(c.playerXP[8]) < 99) {
					c.sendMessage("You need 99 Woodcutting to wear this item.");
					return false;
				}
				break;

			case 9810:
			case 9811:
			case 9812:
				if (c.getPA().getLevelForXP(c.playerXP[19]) < 99) {
					c.sendMessage("You need 99 Farming to wear this item.");
					return false;
				}
				break;
			}
			/*
			 * Castle wars
			 * 
			 * if (CastleWars.isInCw(c) || CastleWars.isInCwWait(c)) { if
			 * (targetSlot == 1 || targetSlot == 0) { c.sendMessage(
			 * "You can't wear your own capes or hats in a Castle Wars Game!");
			 * return false; } }
			 */
			if (c.playerItems[slot] == (wearID + 1)) {
				getRequirements(getItemName(wearID).toLowerCase(), wearID);
				targetSlot = Item.targetSlots[wearID];

				if (itemType(wearID).equalsIgnoreCase("cape")) {
					targetSlot = 1;
				} else if (itemType(wearID).equalsIgnoreCase("hat")
						|| itemType(wearID).equalsIgnoreCase("hood")) {
					targetSlot = 0;
				} else if (itemType(wearID).equalsIgnoreCase("amulet")) {
					targetSlot = 2;
				} else if (itemType(wearID).equalsIgnoreCase("arrows")) {
					targetSlot = 13;
				} else if (itemType(wearID).equalsIgnoreCase("body")) {
					targetSlot = 4;
				} else if (itemType(wearID).equalsIgnoreCase("shield")) {
					targetSlot = 5;
				} else if (itemType(wearID).equalsIgnoreCase("legs")
						|| itemType(wearID).equalsIgnoreCase("tights")) {
					targetSlot = 7;
				} else if (itemType(wearID).equalsIgnoreCase("gloves")) {
					targetSlot = 9;
				} else if (itemType(wearID).equalsIgnoreCase("boots")) {
					targetSlot = 10;
				} else if (itemType(wearID).equalsIgnoreCase("ring")) {
					targetSlot = 12;
				} else {
					targetSlot = 3;
				}
				// ITEM WRONG SLOT FIXES
				if (wearID == 19111) {
					targetSlot = 1;
				}
				if (wearID == 10838) {
					targetSlot = 7;
				}
				if (wearID == 20044) {
					targetSlot = 4;
				}
				if (wearID == 19018) {
					targetSlot = 9;
				}
				if (wearID == 19020) {
					targetSlot = 5;
				}
				if (wearID == 19029) {
					targetSlot = 2;
				}
				if (wearID == 19043 || wearID == 19023 || wearID == 18743
						|| wearID == 12743 || wearID == 12742
						|| wearID == 19021 || wearID == 19022
						|| wearID == 19023) {
					targetSlot = 1;
				}
				if (wearID == 19032 || wearID == 19037 || wearID == 19034
						|| wearID == 19035 || wearID == 19036
						|| wearID == 19038 || wearID == 19039
						|| wearID == 19040 || wearID == 19988
						|| wearID == 19989 || wearID == 19027) {
					targetSlot = 1;// angle capes
				}
				/*
				 * 1 = Cape 2 = Hat 3 = Arrows 4 = Body 5 = Shield 6 = Empty 7 =
				 * legs 8 = empty 9 = gloves 10 = boots 11 = empty 12 = Ring
				 */
				if (wearID == 1901) {
					targetSlot = 9;
				}
				if (wearID == 20135) {
					targetSlot = 0;
				}
				if (wearID == 11118) {
					targetSlot = 9;
				}

				if (c.duelRule[11] && targetSlot == 0) {
					c.sendMessage("Wearing hats has been disabled in this duel!");
					return false;
				}
				if (c.duelRule[12] && targetSlot == 1) {
					c.sendMessage("Wearing capes has been disabled in this duel!");
					return false;
				}
				if (c.duelRule[13] && targetSlot == 2) {
					c.sendMessage("Wearing amulets has been disabled in this duel!");
					return false;
				}
				if (c.duelRule[14] && targetSlot == 3) {
					c.sendMessage("Wielding weapons has been disabled in this duel!");
					return false;
				}
				if (c.duelRule[15] && targetSlot == 4) {
					c.sendMessage("Wearing bodies has been disabled in this duel!");
					return false;
				}
				if ((c.duelRule[16] && targetSlot == 5)
						|| (c.duelRule[16] && is2handed(getItemName(wearID)
								.toLowerCase(), wearID))) {
					c.sendMessage("Wearing shield has been disabled in this duel!");
					return false;
				}
				if (c.duelRule[17] && targetSlot == 7) {
					c.sendMessage("Wearing legs has been disabled in this duel!");
					return false;
				}
				if (c.duelRule[18] && targetSlot == 9) {
					c.sendMessage("Wearing gloves has been disabled in this duel!");
					return false;
				}
				if (c.duelRule[19] && targetSlot == 10) {
					c.sendMessage("Wearing boots has been disabled in this duel!");
					return false;
				}
				if (c.duelRule[20] && targetSlot == 12) {
					c.sendMessage("Wearing rings has been disabled in this duel!");
					return false;
				}
				if (c.duelRule[21] && targetSlot == 13) {
					c.sendMessage("Wearing arrows has been disabled in this duel!");
					return false;
				}

				if (Config.itemRequirements) {
					if (targetSlot == 10 || targetSlot == 7 || targetSlot == 5
							|| targetSlot == 4 || targetSlot == 0
							|| targetSlot == 9 || targetSlot == 10) {
						if (c.defenceLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[1]) < c.defenceLevelReq) {
								c.sendMessage("You need a defence level of "
										+ c.defenceLevelReq
										+ " to wear this item.");
								canWearItem = false;
							}
						}
						if (c.rangeLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[4]) < c.rangeLevelReq) {
								c.sendMessage("You need a range level of "
										+ c.rangeLevelReq
										+ " to wear this item.");
								canWearItem = false;
							}
						}
						if (c.dungLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[24]) < c.dungLevelReq) {
								c.sendMessage("You need a Dungeoneering level of "
										+ c.dungLevelReq
										+ " to wear this item.");
								canWearItem = false;
							}
						}
						if (c.slayerLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[18]) < c.slayerLevelReq) {
								c.sendMessage("You need a Slayer level of "
										+ c.slayerLevelReq
										+ " to wear this item.");
								canWearItem = false;
							}
						}
						if (c.magicLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[6]) < c.magicLevelReq) {
								c.sendMessage("You need a magic level of "
										+ c.magicLevelReq
										+ " to wear this item.");
								canWearItem = false;
							}
						}
					}
					if (targetSlot == 3) {
						if (c.attackLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[0]) < c.attackLevelReq) {
								c.sendMessage("You need an attack level of "
										+ c.attackLevelReq
										+ " to wield this weapon.");
								canWearItem = false;
							}
						}
						if (c.dungLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[24]) < c.dungLevelReq) {
								c.sendMessage("You need a Dungeoneering level of "
										+ c.dungLevelReq
										+ " to wear this item.");
								canWearItem = false;
							}
						}
						if (c.slayerLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[18]) < c.slayerLevelReq) {
								c.sendMessage("You need a Slayer level of "
										+ c.slayerLevelReq
										+ " to wear this item.");
								canWearItem = false;
							}
						}
						if (c.rangeLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[4]) < c.rangeLevelReq) {
								c.sendMessage("You need a range level of "
										+ c.rangeLevelReq
										+ " to wield this weapon.");
								canWearItem = false;
							}
						}
						if (c.magicLevelReq > 0) {
							if (c.getPA().getLevelForXP(c.playerXP[6]) < c.magicLevelReq) {
								c.sendMessage("You need a magic level of "
										+ c.magicLevelReq
										+ " to wield this weapon.");
								canWearItem = false;
							}
						}
					}
				}

				if (!canWearItem) {
					return false;
				}

				int wearAmount = c.playerItemsN[slot];
				if (wearAmount < 1) {
					return false;
				}
				if (targetSlot == Player.playerCape) {
					if (c.inCwGame == true || c.inCwWait == true) {
						c.sendMessage("You cannot equip a cape at the moment.");
						return false;
					}
				}
				if (targetSlot == Player.playerWeapon && c.inCwGame) {
				}
				/*
				 * if(c.needsReset && c.autocasting) {
				 * c.getPA().resetAutocast(); c.needsReset = false; }
				 */

				if (slot >= 0 && wearID >= 0) {
					int toEquip = c.playerItems[slot];
					int toEquipN = c.playerItemsN[slot];
					int toRemove = c.playerEquipment[targetSlot];
					int toRemoveN = c.playerEquipmentN[targetSlot];
					/*
					 * Castle wars
					 */
					if (toEquip == toRemove + 1 && Item.itemStackable[toRemove]) {
						deleteItem(toRemove, getItemSlot(toRemove), toEquipN);
						c.playerEquipmentN[targetSlot] += toEquipN;
					} else if (targetSlot != 5 && targetSlot != 3) {
						c.playerItems[slot] = toRemove + 1;
						c.playerItemsN[slot] = toRemoveN;
						c.playerEquipment[targetSlot] = toEquip - 1;
						c.playerEquipmentN[targetSlot] = toEquipN;
					} else if (targetSlot == 5) {
						boolean wearing2h = is2handed(
								getItemName(
										c.playerEquipment[Player.playerWeapon])
										.toLowerCase(),
								c.playerEquipment[Player.playerWeapon]);
						if (wearing2h) {
							toRemove = c.playerEquipment[Player.playerWeapon];
							toRemoveN = c.playerEquipmentN[Player.playerWeapon];
							c.playerEquipment[Player.playerWeapon] = -1;
							c.playerEquipmentN[Player.playerWeapon] = 0;
							updateSlot(Player.playerWeapon);
						}
						c.playerItems[slot] = toRemove + 1;
						c.playerItemsN[slot] = toRemoveN;
						c.playerEquipment[targetSlot] = toEquip - 1;
						c.playerEquipmentN[targetSlot] = toEquipN;
					} else if (targetSlot == 3) {
						boolean is2h = is2handed(getItemName(wearID)
								.toLowerCase(), wearID);
						boolean wearingShield = c.playerEquipment[Player.playerShield] > 0;
						boolean wearingWeapon = c.playerEquipment[Player.playerWeapon] > 0;
						if (is2h) {
							if (wearingShield && wearingWeapon) {
								if (freeSlots() > 0) {
									c.playerItems[slot] = toRemove + 1;
									c.playerItemsN[slot] = toRemoveN;
									c.playerEquipment[targetSlot] = toEquip - 1;
									c.playerEquipmentN[targetSlot] = toEquipN;
									removeItem(
											c.playerEquipment[Player.playerShield],
											Player.playerShield);
								} else {
									c.sendMessage("You do not have enough inventory space to do this.");
									return false;
								}
							} else if (wearingShield && !wearingWeapon) {
								c.playerItems[slot] = c.playerEquipment[Player.playerShield] + 1;
								c.playerItemsN[slot] = c.playerEquipmentN[Player.playerShield];
								c.playerEquipment[targetSlot] = toEquip - 1;
								c.playerEquipmentN[targetSlot] = toEquipN;
								c.playerEquipment[Player.playerShield] = -1;
								c.playerEquipmentN[Player.playerShield] = 0;
								updateSlot(Player.playerShield);
							} else {
								c.playerItems[slot] = toRemove + 1;
								c.playerItemsN[slot] = toRemoveN;
								c.playerEquipment[targetSlot] = toEquip - 1;
								c.playerEquipmentN[targetSlot] = toEquipN;
							}
						} else {
							c.playerItems[slot] = toRemove + 1;
							c.playerItemsN[slot] = toRemoveN;
							c.playerEquipment[targetSlot] = toEquip - 1;
							c.playerEquipmentN[targetSlot] = toEquipN;
						}
					}
					// //updateInventory = true;
					resetItems(3214);
				}

				if (targetSlot == 3) {
					c.usingSpecial = false;
					addSpecialBar(wearID);
				}
				if (c.getOutStream() != null && c != null) {
					c.getOutStream().createFrameVarSizeWord(34);
					c.getOutStream().writeWord(1688);
					c.getOutStream().writeByte(targetSlot);
					c.getOutStream().writeWord(wearID + 1);

					if (c.playerEquipmentN[targetSlot] > 254) {
						c.getOutStream().writeByte(255);
						c.getOutStream().writeDWord(
								c.playerEquipmentN[targetSlot]);
					} else {
						c.getOutStream().writeByte(
								c.playerEquipmentN[targetSlot]);
					}

					c.getOutStream().endFrameVarSizeWord();
					c.flushOutStream();
				}
				sendWeapon(c.playerEquipment[Player.playerWeapon],
						getItemName(c.playerEquipment[Player.playerWeapon]));
				resetBonus();
				getBonus();
				writeBonus();
				c.getCombat().getPlayerAnimIndex(
						c.getItems()
								.getItemName(
										c.playerEquipment[Player.playerWeapon])
								.toLowerCase());
				c.getPA().requestUpdates();
				c.isFullHelm = Item
						.isFullHelm(c.playerEquipment[Player.playerHat]);
				c.isFullMask = Item
						.isFullMask(c.playerEquipment[Player.playerHat]);
				c.isFullBody = Item
						.isFullBody(c.playerEquipment[Player.playerChest]);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public void wearItem(int wearID, int wearAmount, int targetSlot) {
		if (c != null) {
			if (wearID == 12845 && c.inFoGGame == true) {
				return;
			}

			if (wearID == 7927) {
				c.sendMessage("Type ::unpc to turn back to normal!");
				c.sendMessage("As you put on the ring you turn into an egg!");
				c.npcId2 = 3689;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
			if (c.getOutStream() != null && c != null) {
				c.getOutStream().createFrameVarSizeWord(34);
				c.getOutStream().writeWord(1688);
				c.getOutStream().writeByte(targetSlot);
				c.getOutStream().writeWord(wearID + 1);

				if (wearAmount > 254) {
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord(wearAmount);
				} else {
					c.getOutStream().writeByte(wearAmount);
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();
				c.playerEquipment[targetSlot] = wearID;
				c.playerEquipmentN[targetSlot] = wearAmount;
				c.getItems().sendWeapon(
						c.playerEquipment[Player.playerWeapon],
						c.getItems().getItemName(
								c.playerEquipment[Player.playerWeapon]));
				c.getItems().resetBonus();
				c.getItems().getBonus();
				c.getItems().writeBonus();
				c.getCombat().getPlayerAnimIndex(
						c.getItems()
								.getItemName(
										c.playerEquipment[Player.playerWeapon])
								.toLowerCase());
				c.updateRequired = true;
				c.setAppearanceUpdateRequired(true);
				c.isFullHelm = Item
						.isFullHelm(c.playerEquipment[Player.playerHat]);
				c.isFullMask = Item
						.isFullMask(c.playerEquipment[Player.playerHat]);
				c.isFullBody = Item
						.isFullBody(c.playerEquipment[Player.playerChest]);
			}
		}
	}

	public void writeBonus() {
		int offset = 0;
		String send = "";
		for (int i = 0; i < c.playerBonus.length; i++) {
			if (c.playerBonus[i] >= 0) {
				send = BONUS_NAMES[i] + ": +" + c.playerBonus[i];
			} else {
				send = BONUS_NAMES[i] + ": -"
						+ java.lang.Math.abs(c.playerBonus[i]);
			}

			if (i == 10) {
				offset = 1;
			}
			c.getPA().sendFrame126(send, (1675 + i + offset));
		}

	}
}