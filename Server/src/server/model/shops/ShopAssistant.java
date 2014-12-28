package server.model.shops;

import server.Config;
import server.Server;
import server.model.items.Item;
import server.model.items.ItemAssistant;
import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.world.ShopHandler;

public class ShopAssistant {

	private Client c;

	public static final int PK_POINT_SHOP = 91;

	public long buyDelay;

	public int[] skillCapes = { 9747, 9753, 9750, 9768, 9756, 9759, 9762, 9801,
			9807, 9783, 9798, 9804, 9780, 9795, 9792, 9774, 9771, 9777, 9786,
			9810, 9765, 12169, 9948, 9789, 18508 };

	public ShopAssistant(Client client) {
		this.c = client;
	}

	public boolean addShopItem(int itemID, int amount) {
		boolean Added = false;
		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = c.getItems().getUnnotedItem(itemID);
		}
		for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
			if ((ShopHandler.ShopItems[c.myShopId][i] - 1) == itemID) {
				ShopHandler.ShopItemsN[c.myShopId][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
				if (ShopHandler.ShopItems[c.myShopId][i] == 0) {
					ShopHandler.ShopItems[c.myShopId][i] = (itemID + 1);
					ShopHandler.ShopItemsN[c.myShopId][i] = amount;
					ShopHandler.ShopItemsDelay[c.myShopId][i] = 0;
					break;
				}
			}
		}
		return true;
	}

	/**
	 * buy item from shop (Shop Price)
	 **/

	public void buyFromShopPrice(int removeId, int removeSlot) {
		int ShopValue = (int) Math.floor(getItemShopValue(removeId, 0,
				removeSlot));
		ShopValue *= 1;
		String ShopAdd = "";
		if (c.myShopId == 7390 && c.myShopClient != null
				&& !c.myShopClient.playerName.equals(c.playerName)) {
			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs "
					+ c.myShopClient.playerShopP[removeSlot] + " coins.");
			return;
		} else if (c.myShopId == 7390 && c.myShopClient != null
				&& c.myShopClient.playerName.equals(c.playerName)) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + c.playerShopP[removeSlot]
					+ " coins.");
			return;
		}
		if (c.myShopId == 20) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Slayer Points!");
			return;
		}
		if (c.myShopId == PK_POINT_SHOP) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getPkPointValue(removeId)
					+ " Pk Points!");
			return;
		}
		if (c.myShopId == 36) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " FoG Tokens!");
			return;
		}
		if (c.myShopId == 18) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Slayer Points!");
			return;
		}
		if (c.myShopId == 11) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Pking Points!");
			return;
		}
		if (c.myShopId == 53) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getDungShopPrice(removeId)
					+ " coins!");
			return;
		}
		if (c.myShopId == 84) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + dungShopPrices(removeId)
					+ " Dungeoneering Tokens.");
			return;
		}
		if (c.myShopId == 16) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Agility Points.");
			return;
		}
		if (c.myShopId == 100) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Assault Points.");
			return;
		}
		if (c.myShopId == 85) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + dungShopPrices(removeId)
					+ " Dungeoneering Tokens.");
			return;
		}
		if (c.myShopId == 55) { // NEW SHOP

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getDonatorPoints(removeId)
					+ " Donator Points.");
			return;
		}
		if (c.myShopId == 56) { // NEW SHOP

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getDonatorPoints(removeId)
					+ " Donator Points.");
			return;
		}
		if (c.myShopId == 53) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getDungShopPrice(removeId)
					+ " coins.");
			return;
		}
		if (c.myShopId == 14) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Vote Points.");
			return;
		}
		if (c.myShopId == 21) {

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": currently costs " + getSpecialItemValue(removeId)
					+ " Points.");
			return;
		}
		if (c.myShopId == 11) {
			c.sendMessage("This item current costs "
					+ c.getItems().getUntradePrice(removeId) + " coins.");
			return;
		}
		if (ShopValue >= 1000 && ShopValue < 1000000) {
			ShopAdd = " (" + (ShopValue / 1000) + "K)";
		} else if (ShopValue >= 1000000) {
			ShopAdd = " (" + (ShopValue / 1000000) + " million)";
		}

		c.sendMessage(ItemAssistant.getItemName(removeId)
				+ ": currently costs " + ShopValue + " coins" + ShopAdd);
	}

	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if (System.currentTimeMillis() - buyDelay < 1500) {
			return false;
		}
		if (c.myShopId == 7390 && c.myShopClient != null
				&& !c.myShopClient.properLogout
				&& !c.playerName.equals(c.myShopClient.playerName)) {
			if (c.playerRights == 2) {
				c.sendMessage("Sorry but buying as admin is disabled :/..");
				return false;
			}
			int bought = 0;
			int price = c.myShopClient.playerShopP[fromSlot];
			if (amount > c.myShopClient.playerShopN[fromSlot])
				amount = c.myShopClient.playerShopN[fromSlot];
			for (int x = 0; x < amount; x++) {
				if (c.getItems().playerHasItem(995,
						c.myShopClient.playerShopP[fromSlot])
						&& c.getItems().freeSlots() > 0) {
					c.getItems().deleteItem2(995,
							c.myShopClient.playerShopP[fromSlot]);
					c.getItems()
							.addItem(c.myShopClient.playerShop[fromSlot], 1);
					c.myShopClient.playerShopN[fromSlot]--;
					c.myShopClient.playerCollect += c.myShopClient.playerShopP[fromSlot];
					if (c.myShopClient.playerShopN[fromSlot] == 0) {
						c.myShopClient.playerShop[fromSlot] = 0;
						c.myShopClient.playerShopP[fromSlot] = 0;
					}
					bought++;
				} else {
					c.sendMessage("Not enought space or money.");
					break;
				}
			}
			if (bought > 0) {
				if (c.playerRights == 2) {
					c.sendMessage("Sorry but buying as admin is disabled :/..");
					return false;
				}
				resetShop(c.myShopClient);
				c.getItems().resetItems(3823);

				c.sendMessage("You just bought " + bought + " "
						+ ItemAssistant.getItemName(itemID) + " for "
						+ (bought * price));

				c.myShopClient.sendMessage(c.playerName + " has bought "
						+ bought + " " + ItemAssistant.getItemName(itemID)
						+ " from you!");
				c.myShopClient.sendMessage("You now have "
						+ c.myShopClient.playerCollect
						+ " coins to collect (::collect)");
			}
			return false;
		} else if (c.myShopId == 7390 && c.myShopClient != null
				&& !c.myShopClient.properLogout
				&& c.playerName.equals(c.myShopClient.playerName)) {
			if (c.playerRights == 2) {
				c.sendMessage("Sorry but buying as admin is disabled :/..");
				return false;
			}
			if (amount > c.myShopClient.playerShopN[fromSlot])
				amount = c.myShopClient.playerShopN[fromSlot];
			for (int x = 0; x < amount; x++) {
				if (c.getItems().freeSlots() > 0) {
					c.getItems()
							.addItem(c.myShopClient.playerShop[fromSlot], 1);
					c.myShopClient.playerShopN[fromSlot]--;
					if (c.myShopClient.playerShopN[fromSlot] == 0) {
						c.myShopClient.playerShop[fromSlot] = 0;
						c.myShopClient.playerShopP[fromSlot] = 0;
						fixShop(c);
					}
				} else {
					c.sendMessage("Not enought space.");
					break;
				}
			}
			resetShop(c.myShopClient);
			c.getItems().resetItems(3823);
			return false;
		} else if (c.myShopId == 7390) {
			return false;
		}
		if (ShopHandler.ShopItems[c.myShopId][fromSlot] - 1 != itemID
				&& c.myShopId != 15 && c.myShopId != 1 && c.myShopId != 7390) {
			c.sendMessage("Stop trying to cheat.");
			return false;
		}

		if (c.myShopId == 15) {
			skillBuy(itemID);
			return false;

		} else if (c.myShopId == 1) {
			buyVoid(itemID);
			return false;
		}

		if (!shopSellsItem(itemID))
			return false;

		if (amount > 0) {
			if (amount > ShopHandler.ShopItemsN[c.myShopId][fromSlot]) {
				amount = ShopHandler.ShopItemsN[c.myShopId][fromSlot];
			}
			// double ShopValue;
			// double TotPrice;
			int TotPrice2 = 0;
			// int Overstock;
			int Slot = 0;
			int Slot1 = 0;// Tokkul
			int Slot3 = 0;// Donator Gold
			int Slot4 = 0; // Money pouch

			if (c.myShopId == 18) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 11) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 47) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 36) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 21) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 16) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 20) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 100) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 14) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 84) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 53) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 85) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 55) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 56) {
				handleOtherShop(itemID);
				return false;
			}
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(getItemShopValue(itemID, 0,
						fromSlot));
				Slot = c.getItems().getItemSlot(995);
				Slot1 = c.getItems().getItemSlot(6529);
				Slot3 = c.getItems().getItemSlot(5555);
				Slot4 = c.MoneyCash;
				if (Slot == -1 && Slot4 == -1 && c.myShopId != 11
						&& c.myShopId != 29 && c.myShopId != 30
						&& c.myShopId != 31 && c.myShopId != 84
						&& c.myShopId != 85 && c.myShopId != 100) {
					c.sendMessage("You don't have enough coins.");
					break;
				}
				if (Slot1 == -1 && c.myShopId == 29 || c.myShopId == 30
						|| c.myShopId == 31) {
					c.sendMessage("You don't have enough tokkul.");
					break;
				}
				if (Slot3 == -1 && c.myShopId == 353) {
					c.sendMessage("You don't have enough donator gold.");
					break;
				}

				if (TotPrice2 <= 1) {
					TotPrice2 = (int) Math.floor(getItemShopValue(itemID, 0,
							fromSlot));
					TotPrice2 *= 1.66;
				}
				if (c.myShopId == 29 || c.myShopId == 30 || c.myShopId == 31) {
					if (c.playerItemsN[Slot1] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.getItems().deleteItem(6529,
									c.getItems().getItemSlot(6529), TotPrice2);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough tokkul.");
						break;
					}

				} else if (c.myShopId == 82) {
					if (c.bossPoints >= getBossPointValue(itemID)) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.bossPoints -= getBossPointValue(itemID);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
							if (i == 1) {
								c.sendMessage("You now have "
										+ c.bossPoints
										+ " Boss points. Sell back for 50% value.");
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						// edit ti to your liking, lets make sure it works
						c.sendMessage("You only have " + c.bossPoints
								+ " boss points you need "
								+ getBossPointValue(itemID) + " boss points.");
						break;
					}
				} else if (c.myShopId == PK_POINT_SHOP) {
					if (c.pkPoints >= getPkPointValue(itemID)) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.pkPoints -= getPkPointValue(itemID);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
							if (i == 1) {
								c.sendMessage("You now have "
										+ c.pkPoints
										+ " pk points. Sell back for 50% value.");
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						// edit ti to your liking, lets make sure it works
						c.sendMessage("You only have " + c.pkPoints
								+ " pk points you need "
								+ getPkPointValue(itemID) + " pk points.");
						break;
					}
				} else if (c.myShopId == 16) {
					if (c.SPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.SPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Agility Points.");
						break;
					}
				} else if (c.myShopId == 100) {
					if (c.barbPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.barbPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Points.");
						break;
					}
				} else if (c.myShopId == 84) {
					if (c.dungPoints >= dungShopPrices(itemID)) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.dungPoints -= dungShopPrices(itemID);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Dungeoneering Tokens.");
						break;
					}
				} else if (c.myShopId == 55) {
					if (c.DonorPoint >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.DonorPoint -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Donator Points..");
						break;
					}
				} else if (c.myShopId == 56) {
					if (c.DonorPoint >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.DonorPoint -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Donator Points..");
						break;
					}
				} else if (c.myShopId == 85) {
					if (c.dungPoints >= dungShopPrices(itemID)) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.dungPoints -= dungShopPrices(itemID);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Dungeoneering Tokens.");
						break;
					}
				} else if (c.myShopId == 11) {
					if (c.playerItemsN[Slot3] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.getItems().deleteItem(5555,
									c.getItems().getItemSlot(5555), TotPrice2);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough donator gold.");
						break;
					}
				} else if (c.myShopId != 11 && c.myShopId != 29
						|| c.myShopId != 30 || c.myShopId != 31
						|| c.myShopId != 84 || c.myShopId != 85
						|| c.myShopId != 16 || c.myShopId != 222) {

					/*
					 * if(Slot < TotPrice2 && c.MoneyCash < TotPrice2) {
					 * c.sendMessage("You don't have enough money.");
					 * c.sendMessage(""+Slot+""); return false; } else { if(Slot
					 * < 0 && c.MoneyCash < 0) {
					 * c.sendMessage("You don't have any money."); return false;
					 * } }
					 */
					if (!c.getItems().playerHasItem(995, TotPrice2)
							&& c.MoneyCash < TotPrice2) {
						c.sendMessage("You don't have enough money.");
						return false;
					}

					if (c.MoneyCash > TotPrice2 || c.MoneyCash == TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.MoneyCash -= TotPrice2;
							handleMoneyPouchMillOrK();
							// c.sendMessage("You buy an item for "+TotPrice2+" coins using your Money Pouch.");
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							return false;
						}
					} else {

						if (c.playerItemsN[Slot] >= TotPrice2
								|| c.playerItemsN[Slot] == TotPrice2) {
							if (c.getItems().freeSlots() > 0) {
								buyDelay = System.currentTimeMillis();
								c.getItems().deleteItem(995, TotPrice2);
								// c.sendMessage("You buy an item for "+TotPrice2+" coins.");
								c.getItems().addItem(itemID, 1);
								ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
								ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
								if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
									ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
								}
							} else {
								if (!Item.itemStackable[itemID]) {
									c.sendMessage("You don't have enough space in your inventory.");
									return false;
								} else if (Item.itemStackable[itemID]) {
									if (c.getItems().playerHasItem(itemID, 1)) {
										buyDelay = System.currentTimeMillis();
										c.getItems().deleteItem(995, TotPrice2);
										// c.sendMessage("You buy an item for "+TotPrice2+" coins.");
										c.getItems().addItem(itemID, 1);
										ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
										ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
										if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
											ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
										}
									} else {
										c.sendMessage("You don't have enough space in your inventory.");
										return false;
									}
								}
							}
						} else {
							c.sendMessage("You don't have enough money in your inventory or pouch.");
							break;
						}
					}
				}
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return false;
	}

	public void buyVoid(int item) {
		/*
		 * if (item > 2527 || item < 2518) return; //c.sendMessage("" + item);
		 * if (c.voidStatus[(item-2518)/2] > 0) { if (c.getItems().freeSlots()
		 * >= 1) { if
		 * (c.getItems().playerHasItem(995,c.getItems().getUntradePrice(item)))
		 * { c.voidStatus[(item-2518)/2]--;
		 * c.getItems().deleteItem(995,c.getItems().getItemSlot(995),
		 * c.getItems().getUntradePrice(item)); c.getItems().addItem(item,1);
		 * openVoid(); } else { c.sendMessage("This item costs " +
		 * c.getItems().getUntradePrice(item) + " coins to rebuy."); } } else {
		 * c.sendMessage("I should have a free inventory space."); } } else {
		 * c.sendMessage
		 * ("I don't need to recover this item from the void knights."); }
		 */
	}

	public int dungShopPrices(int id) {
		switch (id) {
		case 18337:
			return 850;
		case 18349:
		case 18351:
		case 18353:
		case 18357:
			return 2500;
		case 18355:
			return 1400;
		case 18335:
			return 700;
		case 17017:
		case 17193:
		case 17237:
			return 2500;
		case 18363:
		case 18361:
		case 16865:
		case 17339:
			return 2000;
		case 16755:
		case 17061:
			return 2000;
		case 16931:
		case 17171:
		case 17215:
		case 17317:
			return 1000;
		}
		return 0;
	}

	public int[] fixArray(int[] array) {
		int arrayPos = 0;
		int[] newArray = new int[array.length];
		for (int x = 0; x < array.length; x++) {
			if (array[x] != 0) {
				newArray[arrayPos] = array[x];
				arrayPos++;
			}
		}
		return newArray;
	}

	public void fixShop(Client o) {
		o.playerShop = fixArray(o.playerShop);
		o.playerShopN = fixArray(o.playerShopN);
		o.playerShopP = fixArray(o.playerShopP);
	}

	public int getDonatorPoints(int id) {
		switch (id) {
		case 7684:
			return 1;
		case 20065:
			return 5;
		case 19016:
			return 40;
		case 19013:
		case 19020:
			return 20;
		case 19011:
			return 100;
		case 19009:
		case 19992:
		case 19974:
		case 931:
		case 19018:
		case 13560:
			return 20;
		case 13561:
			return 30;
		case 13562:
			return 40;
		case 18355:
		case 19146:
			return 12;
		case 19335:
			return 5;
		case 19143:
		case 19149:
			return 10;
		case 7671:
			return 10;
		case 20086:
		case 20088:
		case 20083:
		case 20082:
		case 20080:
		case 20079:
		case 20078:
		case 20075:
		case 20074:
		case 20077:
			return 10;
		case 6818:
			return 10;
		case 18357:
			return 10;
		case 17361:
			return 20;
		case 18353:
			return 10;
		case 18349:
			return 5;
		case 18351:
			return 10;
		case 12780:
			return 100;
		case 13362:
			return 10;
		case 11607:
		case 11608:
		case 11611:
			return 5;
		case 12782:
			return 100;
		case 11609:
		case 11610:
			return 2;
		case 13360:
		case 13358:
			return 15;
		case 3789:
			return 10;
		case 13355:
			return 10;
		case 13354:
			return 15;
		case 13352:
			return 15;
		case 13350:
			return 10;
		case 13348:
			return 15;
		case 13346:
			return 15;
		case 11724:
		case 11726:
		case 11718:
		case 11720:
		case 11722:
			return 10;
		case 19780:
			return 5;
		case 16403:
			return 20;
		case 16425:
			return 20;
		case 20076:
		case 20087:
		case 20085:
		case 20073:
		case 20402:
		case 20090:
		case 20406:
			return 10;
		case 20060:
		case 20061:
		case 20403:
			return 20;
		case 16955:
			return 20;
		case 16909:
			return 20;
		case 16711:
			return 20;
		case 17259:
			return 25;
		case 16689:
			return 25;
		case 1038:
			return 20;
		case 1040:
			return 20;
		case 1042:
			return 20;
		case 1044:
			return 20;
		case 1046:
			return 20;
		case 1048:
			return 20;
		case 1053:
			return 15;
		case 1055:
			return 15;
		case 1057:
			return 15;
		case 14484:
			return 5;
		case 11694:
			return 5;
		case 13738:
			return 10;
		case 13740:
			return 15;
		case 13742:
			return 12;
		case 13744:
			return 5;
		case 19669:
			return 3;
		case 16293:
			return 10;
		case 16359:
			return 10;
		case 15426:
		case 15509:
		case 15503:
		case 15505:
		case 15507:
			return 10;
		}
		return 0;
	}

	public int get99Count() {
		int count = 0;
		for (int j = 0; j < skillCapes.length; j++) {
			if (c.getLevelForXP(c.playerXP[j]) >= 1) {
				count++;
			}
		}
		return count;
	}

	public int getDungShopPrice(int id) {
		switch (id) {
		case 1265:
		case 1351:
		case 590:
		case 1511:
			return 300;
		case 17807:
			return 3000;
		case 17811:
			return 10000;
		case 17616:
		case 17612:
		case 17610:
		case 17608:
			return 10000;
		case 16889:
		case 16935:
			return 10000;
		case 16691:
			return 8000;
		case 16699:
			return 10000;
		case 17239:
			return 12000;
		case 16895:
		case 16941:
			return 25000;
		case 16697:
			return 20000;
		case 17245:
			return 30000;
		case 16675:
			return 25000;
		case 16901:
		case 16947:
			return 50000;
		case 16703:
			return 40000;
		case 17251:
			return 75000;
		case 16681:
			return 50000;
		case 16907:
		case 16953:
			return 200000;
		case 16709:
			return 180000;
		case 17257:
			return 250000;
		case 16687:
			return 200000;
		case 16867:
			return 10000;
		case 16873:
			return 25000;
		case 16669:
			return 10000;
		case 16879:
			return 50000;
		case 16885:
			return 180000;
		case 17047:
			return 20000;
		case 17179:
			return 30000;
		case 17325:
			return 25000;
		case 17059:
			return 150000;
		case 17191:
			return 200000;
		case 17337:
			return 175000;
		}
		return 0;
	}

	public int getItemShopValue(int itemId) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == itemId) {
					return (int) Server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		return 0;
	}

	public double getItemShopValue(int ItemID, int Type, int fromSlot) {
		if (c.myShopId == 7390) {
			return c.myShopClient.playerShopP[fromSlot];
		}
		double ShopValue = 1;
		double TotPrice = 0;
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					ShopValue = Server.itemHandler.ItemList[i].ShopValue;

				}
			}
		}

		TotPrice = ShopValue;

		if (ShopHandler.ShopBModifier[c.myShopId] == 1) {
			TotPrice *= 1;
			TotPrice *= 1;
			if (Type == 1) {
				TotPrice *= 1;
			}
		} else if (Type == 1) {
			TotPrice *= 1;
		}
		return TotPrice;
	}

	public int getOtherDungItems(int itemId) {
		switch (itemId) {
		case 19892:
			return 15000;
		case 19893:
			return 20000;
		case 16432:
			return 150;
		case 16341:
			return 8000;
		case 18169:
			return 3000;
		case 18173:
			return 10000;
		case 16427:
			return 200;
		case 592:
			return 100;
		case 16285:
			return 30000;

		}
		return 0;
	}

	private int getBossPointValue(int itemId) {
		switch (itemId) {
		case 20003:// spec gs
			return 100;
		case 20000:// arc gs
			return 115;
		case 20002:// ely gs
			return 150;
		case 20001:// div gs
			return 200;
		case 13748:// divsig
			return 200;
		case 13752:// spec
			return 100;
		case 13750:
			return 150;
		case 13746:
			return 115;
		case 20012:
			return 50;
		case 20009:
		case 20010:
		case 20011:
			return 25;
		case 19008:
			return 130;
		case 19111:
		case 19013:
		case 14048:
			return 175;
		case 6570:
			return 45;
		case 19335:
			return 30;
		case 20083:
			return 1000;

		}
		return 0;
	}

	private int getPkPointValue(int itemId) {
		switch (itemId) {
		/*
		 * example
		 */
		case 14990:// z bow
			return 100;
		case 19043:// z bow
			return 200;
		case 19019:// z bow
			return 250;

		case 11696:// GodSwords
		case 11698:// GodSwords
		case 11700:// GodSwords
			return 40;

		case 6585:// fury
		case 11732:// Dboots
			return 5;

		case 20072:// Ddefender
			return 1;

		case 20084:
		case 11716:
			return 10;

		case 20015:
		case 20016:
		case 20017:
		case 20018:
			return 10;

		case 13864:// zuriels
		case 13861:
		case 13858:
		case 13867:
			return 20;

		case 13887:// Vesta
		case 13893:
		case 13899:
		case 13905:
			return 20;

		case 13884:// Statius
		case 13890:
		case 13896:
		case 13902:
			return 20;

		case 13870:// Morrigans
		case 13873:
		case 13876:
			return 20;
		case 13883:
			return 1;

		case 11846:// barrows
		case 11848:
		case 11850:
		case 11852:
		case 11854:
		case 11856:
			return 10;
		case 19780:
			return 18;

		default:
			return 5;
		}
	}

	public int getSpecialItemValue(int id) {
		switch (id) {
		case 7684:
			return 10;
		case 20012:
			return 2500;
		case 20010:
		case 20011:
			return 2000;
		case 4566:
		case 7673:
		case 5608:
		case 7918:
		case 19747:
			return 20;
		case 13281:
			return 50;
		case 7927:
		case 4029:
		case 4026:
		case 4025:
			return 20;
		case 15443:
		case 15442:
		case 15441:
		case 15702:
		case 15704:
		case 15701:
		case 15703:
		case 6859:
		case 6861:
		case 6863:
		case 6857:
		case 10400:
		case 10402:
		case 10404:
		case 10406:
		case 10408:
		case 10410:
		case 10412:
		case 10414:
		case 10416:
		case 10418:
			return 10;
		case 9470:
		case 17273:
			return 5;
		case 15018:
		case 15220:
		case 15019:
		case 15020:
		case 15017:
			return 20;
		case 3486:
		case 3481:
		case 3483:
		case 3485:
		case 3488:
		case 6617:
		case 6625:
		case 6627:
		case 6633:
		case 6619:
		case 6623:
		case 19329:
		case 19323:
		case 19325:
		case 19327:
		case 19331:
			return 20;
		case 6585:
		case 4151:
		case 11732:
		case 6737:
		case 6733:
		case 6731:
			return 150;
		case 11967:
			return 500;
		case 13362:
			return 1200;
		case 13360:
			return 1300;
		case 13358:
			return 1500;
		case 3789:
			return 1800;
		case 11613:
			return 500;
		case 15444:
		case 19275:
		case 19278:
		case 19281:
		case 19284:
		case 19287:
		case 19290:
		case 19293:
		case 19302:
		case 19305:
		case 20022:
		case 20021:
		case 20020:
		case 20019:
		case 20018:
		case 20017:
		case 20016:
		case 20015:
			return 100;
		case 18377:
		case 18379:
			return 300;
		case 18375:
			return 500;
		case 18337:
			return 300;
		case 19669:
			return 300;
		case 10550: // range hat
			return 65;
		case 10548: // fight hat
			return 65;
		case 10551: // torso
			return 75;
		case 11730: // sara sword
			return 75;

		case 13344: // ancient blah blah helm
			return 135;
		case 13342:
			return 200;
		case 13340:
			return 150;

		case 10552: // torso
			return 75;

			// END OF ASSAULT

		case 1767:
		case 1765:
		case 1771:
		case 5559:
			return 30;
		case 15486:
			return 110;
		case 6889:
			return 45;
		case 6916:
		case 6918:
		case 6920:
		case 6922:
		case 6924:
			return 70;
		case 19790:
			return 140;
		case 11663:
		case 11664:
		case 11665:
		case 8842:
			return 40;
		case 17020:
			return 70;
		case 17004:
		case 17003:
		case 17005:
		case 17002:
			return 50;
		case 15308:
		case 15312:
		case 15316:
		case 15320:
		case 15324:
			return 4;
		case 11696:
		case 11698:
		case 11700:
			return 800;
		case 11694:
			return 1000;
		case 18357:
			return 2300;
		case 10499:
			return 50;
		case 18017:
		case 18018:
		case 18019:
		case 18020:
			return 3;
		case 14508:
			return 350;
		case 8845:
			return 10;
		case 8846:
		case 11846:
		case 11848:
		case 11850:
		case 11852:
		case 11854:
		case 11856:
			return 20;
		case 8847:
			return 15;
		case 8848:
			return 20;
		case 8849:
		case 8850:
			return 25;
		case 6570:
			return 30;
		case 10887:
			return 400;
		case 11851:
		case 12210:
		case 12213:
		case 12216:
		case 12219:
		case 12222:
			return 150;
		case 11724:
			return 1000;
		case 11728:
			return 250;
		case 11720:
			return 1200;
		case 11722:
			return 1100;
		case 11726:
			return 1000;
		case 15126:
			return 200;
		case 13263:
			return 200;
		case 18335:
			return 700;
		case 19780:
			return 1600;
		case 16713:
			return 400;
		case 13870:
		case 13873:
			return 600;
		case 13876:
			return 450;
		case 15055:
			return 5;
		case 15031:
			return 750;
		case 15007:
		case 15006:
			return 800;
		case 15005:
		case 15004:
			return 850;
		case 11926:
		case 11928:
		case 11930:
			return 400;
		case 15003:
		case 15002:
		case 15001:
		case 15000:
		case 11858:
		case 11860:
		case 11862:
			return 900;
		case 16689:
			return 1000;
		case 17259:
			return 1000;
		case 17361:
			return 1000;
		case 16711:
			return 900;
		case 11821:
			return 1000;
		case 11822:
			return 1000;
		case 11820:
			return 800;
		case 15022:
		case 15026:
			return 750;
		case 15021:
		case 15023:
			return 850;
		case 13350:
			return 2200;
		case 2438:
			return 7;
		case 13351:
			return 2200;
		case 15025:
			return 230;
		case 15024:
			return 170;
		case 15043:
		case 15042:
			return 790;
		case 15041:
		case 15040:
			return 650;
		case 15051:
			return 500;
		case 15050:
			return 700;
		case 18355:
			return 1500;
		case 19785:
			return 360;
		case 19786:
			return 340;
		case 1555:
		case 7584:
		case 1556:
		case 1557:
		case 1558:
			return 150;
		case 13738:
		case 13742:
		case 13744:
			return 650;
		case 13740:
			return 650;
		case 18351:
		case 18353:
		case 18349:
			return 3000;
		case 19336:
		case 19337:
		case 19338:
		case 19341:
		case 19342:
		case 19343:
			return 30;
		case 13896:
		case 13884:
		case 13890:
		case 13887:
		case 13893:
		case 13864:
		case 13858:
		case 13861:
			return 100;
		case 13902:
		case 13867:
		case 13899:
			return 50;
		case 15505:
			return 45;
		case 15507:
			return 45;
		case 15509:
		case 15511:
			return 35;
		case 15602:
		case 15600:
		case 15604:
			return 40;
		case 15073:
			return 55;
		case 15074:
			return 55;
		case 17025:
			return 35;
		case 17018:
			return 90;
		case 17019:
			return 80;
		case 19784:
			return 1000;
		case 15054:
			return 200;
		case 17271:
			return 150;
		case 14936:
			return 300;
		case 14938:
			return 250;
		case 15090:
		case 15091:
		case 15092:
		case 15093:
		case 15094:
		case 15095:
		case 15096:
		case 15097:
		case 15098:
			return 350;
		case 15085:
			return 1300;
		case 15081:
			return 1150;
		case 15080:
			return 1150;
		case 15083:
			return 600;
		case 13352:
		case 13353:
		case 13354:
			return 700;
			// START OF AGILITY STORE
		case 10432: // green
		case 10428:
			return 300;
		case 10430: // blue skirt
		case 10434:
			return 250;
		case 10766: // BLACK BOATER
		case 10764: // blue boater
		case 10758: // redboater
		case 10762: // green boater
			return 220;
		case 13661: // adze
			return 600; // adze
		case 16222:
			return 1100;
		case 14497:
		case 14501:
			return 150;
		case 14499:
			return 60;
		case 15060:
		case 15062:
			return 150;
		case 15061:
			return 70;
		case 2577:
		case 2581:
			return 40;
		case 6914:
		case 6890:
			return 45;

		}
		return 0;
	}

	public void handleMoneyPouchMillOrK() {
		c.getPA().sendFrame126("" + c.MoneyCash + "", 8135);
		if (c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
			c.getPA().sendFrame126("" + c.MoneyCash / 1000 + "K", 8134);
		} else if (c.MoneyCash > 999999 && c.MoneyCash <= 2147483647) {
			c.getPA().sendFrame126("" + c.MoneyCash / 1000000 + "M", 8134);
		} else {
			c.getPA().sendFrame126("" + c.MoneyCash + "", 8134);
		}
		c.getPA().sendFrame126("" + c.MoneyCash + "", 8135);
	}

	public void handleOtherShop(int itemID) {
		if (c.myShopId == 20) {
			if (c.slayerPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.slayerPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Slayer Points to buy this item.");
			}
		} else if (c.myShopId == 53) {
			if (c.getItems().playerHasItem(18201, getDungShopPrice(itemID))) {
				if (c.getItems().freeSlots() > 0) {
					c.getItems().deleteItem(18201, getDungShopPrice(itemID));
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough coins to buy this item.");
			}
		} else if (c.myShopId == 18) {
			if (c.slayerPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.slayerPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Slayer Points to buy this item.");
			}
		} else if (c.myShopId == 11) {
			if (c.pkinPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.pkinPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Pking Points to buy this item.");
			}
		} else if (c.myShopId == 82) {
			if (c.bossPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.bossPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Boss Points to buy this item.");
			}
		} else if (c.myShopId == 14) {
			if (c.votePoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.votePoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Voting Points to buy this item.");
			}
		} else if (c.myShopId == 21) {
			if (c.funPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.funPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Fun Points to buy this item.");
			}
		} else if (c.myShopId == 47) {
			if (c.pkPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.pkPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Pk Points to buy this item.");
			}
		} else if (c.myShopId == 36) {
			if (c.getItems().playerHasItem(12852, getSpecialItemValue(itemID))) {
				if (c.getItems().freeSlots() > 0) {
					c.pkPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough FoG Tokens to buy this item.");
			}
		} else if (c.myShopId == 85) {
			if (c.dungPoints >= dungShopPrices(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.dungPoints -= dungShopPrices(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Dungeoneering Tokens.");
			}
		} else if (c.myShopId == 84) {
			if (c.dungPoints >= dungShopPrices(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.dungPoints -= dungShopPrices(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Dungeoneering Tokens to buy this item.");
			}
		} else if (c.myShopId == 16) {
			if (c.SPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.SPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Agility Points to buy this item.");
			}

		} else if (c.myShopId == 100) {
			if (c.barbPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.barbPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough points to buy this item.");
			}

		} else if (c.myShopId == 55) { // Donor shop
			if (c.DonorPoint >= getDonatorPoints(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.DonorPoint -= getDonatorPoints(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Donator Points to buy this item.");
			}
		} else if (c.myShopId == 56) { // Donor shop
			if (c.DonorPoint >= getDonatorPoints(itemID)) {
				if (c.getItems().freeSlots() > 0) {
					c.DonorPoint -= getDonatorPoints(itemID);
					c.getItems().addItem(itemID, 1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Donator Points to buy this item.");
			}

		}
	}

	public void openPlayerShop(Client o) {
		if (o == null || o.properLogout)
			return;
		c.getItems().resetItems(3823);
		resetShop(o);
		c.myShopClient = o;
		c.myShopId = 7390;
		c.isShopping = true;
		c.stopPlayerSkill = true;
		c.getPA().sendFrame248(3824, 3822);
		c.getPA().sendFrame126(o.playerName + "'s Shop!", 3901);
	}

	/**
	 * Shops
	 **/

	public void openShop(int ShopID) {
		c.getItems().resetItems(3823);
		resetShop(ShopID);
		c.isShopping = true;
		c.stopPlayerSkill = true;
		c.myShopId = ShopID;
		c.getPA().sendFrame248(3824, 3822);
		c.getPA().sendFrame126(ShopHandler.ShopName[ShopID], 3901);
	}

	public void openSkillCape() {
		c.myShopId = 15;
		int capes = get99Count();
		setupSkillCapes(capes, get99Count());
	}

	public void openVoid() {
		/*
		 * synchronized(c) { c.getItems().resetItems(3823); c.isShopping = true;
		 * c.stopPlayerSkill = true; c.myShopId = 15;
		 * c.getPA().sendFrame248(3824, 3822);
		 * c.getPA().sendFrame126("Void Recovery", 3901);
		 * 
		 * int TotalItems = 5; c.getOutStream().createFrameVarSizeWord(53);
		 * c.getOutStream().writeWord(3900);
		 * c.getOutStream().writeWord(TotalItems); for (int i = 0; i <
		 * c.voidStatus.length; i++) {
		 * c.getOutStream().writeByte(c.voidStatus[i]);
		 * c.getOutStream().writeWordBigEndianA(2519 + i * 2); }
		 * c.getOutStream().endFrameVarSizeWord(); c.flushOutStream(); }
		 */
	}

	public void resetShop(Client o) {
		if (c != null) {
			fixShop(o);
			for (int x = 0; x < 10; x++) {
				if (o.playerShopN[x] <= 0) {
					o.playerShop[x] = 0;
				}
			}
			int TotalItems = 0;
			for (int i = 0; i < 10; i++) {
				if (o.playerShop[i] > 0) {
					TotalItems++;
				}
			}
			if (TotalItems > 10) {
				TotalItems = 10;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
			int TotalCount = 0;
			for (int i = 0; i < o.playerShop.length; i++) {
				if (o.playerShop[i] > 0) {
					if (o.playerShopN[i] > 254) {
						c.getOutStream().writeByte(255);
						c.getOutStream().writeDWord_v2(o.playerShopN[i]);
					} else {
						c.getOutStream().writeByte(o.playerShopN[i]);
					}
					c.getOutStream().writeWordBigEndianA((o.playerShop[i] + 1));
					TotalCount++;
				}
				if (TotalCount > TotalItems) {
					break;
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public void resetShop(int ShopID) {
		if (c != null) {
			int TotalItems = 0;
			for (int i = 0; i < ShopHandler.MaxShopItems; i++) {
				if (ShopHandler.ShopItems[ShopID][i] > 0) {
					TotalItems++;
				}
			}
			if (TotalItems > ShopHandler.MaxShopItems) {
				TotalItems = ShopHandler.MaxShopItems;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
			int TotalCount = 0;
			for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
				if (ShopHandler.ShopItems[ShopID][i] > 0
						|| i <= ShopHandler.ShopItemsStandard[ShopID]) {
					if (ShopHandler.ShopItemsN[ShopID][i] > 254) {
						c.getOutStream().writeByte(255);
						c.getOutStream().writeDWord_v2(
								ShopHandler.ShopItemsN[ShopID][i]);
					} else {
						c.getOutStream().writeByte(
								ShopHandler.ShopItemsN[ShopID][i]);
					}
					if (ShopHandler.ShopItems[ShopID][i] > Config.ITEM_LIMIT
							|| ShopHandler.ShopItems[ShopID][i] < 0) {
						ShopHandler.ShopItems[ShopID][i] = Config.ITEM_LIMIT;
					}
					c.getOutStream().writeWordBigEndianA(
							ShopHandler.ShopItems[ShopID][i]);
					TotalCount++;
				}
				if (TotalCount > TotalItems) {
					break;
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public boolean sellItem(int itemID, int fromSlot, int amount) {
		if (c.inTrade) {
			c.sendMessage("You cant sell items to the shop while your in trade!");
			return false;
		}
		for (int i = 0; i < Config.NOT_ALLOWED.length; i++) {
			if (!c.inDung && itemID == Config.NOT_ALLOWED[i]) {
				c.sendMessage("That item is a forbidden out-of-dung item!!");
				c.sendMessage("Staff members have been notified and the item has been removed.");
				PlayerHandler.notifyStaff(c);
				if (c.getItems().playerHasItem(Config.NOT_ALLOWED[i], 1))
					c.getItems().deleteItem(Config.NOT_ALLOWED[i], 28);
				return false;
			}
		}
		if (c.playerRights == 2) {

			c.sendMessage("You can't sell "
					+ ItemAssistant.getItemName(itemID).toLowerCase() + ".");
			return false;
		}
		if (c.myShopId == 7390) {
			for (int i : Config.ITEM_TRADEABLE) {
				if (i == itemID) {
					c.sendMessage("You can't sell this item.");
					return false;
				}
			}
			if (c.playerName.equals(c.myShopClient.playerName)) {
				c.sellingId = itemID;
				c.sellingN = amount;
				c.sellingS = fromSlot;
				c.xInterfaceId = 7390;
				c.outStream.createFrame(27);
			} else {
				c.sendMessage("You can only sell items on your own store.");
			}
			return true;
		}
		if (c.myShopId == 14)
			return false;
		for (int i : Config.ITEM_SELLABLE) {
			if (i == itemID) {

				c.sendMessage("You can't sell "
						+ ItemAssistant.getItemName(itemID).toLowerCase() + ".");
				return false;
			}
		}
		if (c.myShopId == 21)
			return false;
		for (int i : Config.ITEM_SELLABLE) {
			if (i == itemID) {

				c.sendMessage("You can't sell "
						+ ItemAssistant.getItemName(itemID).toLowerCase() + ".");
				return false;
			}
		}
		if (c.myShopId == 53 && c.inDung) {
			// int price = (int) Math.floor(getItemShopValue(itemID, 1,
			// fromSlot));
			int price = getDungShopPrice(itemID);
			int ShopValue2 = getOtherDungItems(itemID);
			if (price == 0 && ShopValue2 == 0) {
				c.sendMessage("This item isn't sellable to this shop.");
				return false;
			}
			if (price > 0) {
				c.getItems().deleteItem(itemID, fromSlot, 1);
				c.getItems().addItem(18201, price);
				c.getItems().resetItems(3823);
				return true;
			} else if (ShopValue2 > 0 && price == 0) {
				c.getItems().deleteItem(itemID, fromSlot, 1);
				c.getItems().addItem(18201, ShopValue2);
				c.getItems().resetItems(3823);
				return true;
			}
			return false;
		}
		if (amount > 0 && itemID == (c.playerItems[fromSlot] - 1)) {
			if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= ShopHandler.ShopItemsStandard[c.myShopId]; i++) {
					if (itemID == (ShopHandler.ShopItems[c.myShopId][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false && !c.inDung) {

					c.sendMessage("You can't sell "
							+ ItemAssistant.getItemName(itemID).toLowerCase()
							+ " to this store.");

					return false;
				}
			}

			if (amount > c.playerItemsN[fromSlot]
					&& (Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == true || Item.itemStackable[(c.playerItems[fromSlot] - 1)] == true)) {
				amount = c.playerItemsN[fromSlot];
			} else if (amount > c.getItems().getItemAmount(itemID)
					&& Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == false
					&& Item.itemStackable[(c.playerItems[fromSlot] - 1)] == false) {
				amount = c.getItems().getItemAmount(itemID);
			}
			/*
			 * Comment out this stuff to disable selling
			 */
			if (c.myShopId == PK_POINT_SHOP) {
				if (c.getItems().playerHasItem(itemID)) {
					int value = getPkPointValue(itemID) * amount;
					value = (int) (value * 0.50);
					if (Item.itemStackable[itemID]) {
						c.getItems().deleteItem(itemID,
								c.getItems().getItemSlot(itemID), amount);
					} else {
						c.getItems().deleteItem2(itemID, amount);
					}
					addShopItem(itemID, amount);
					c.pkPoints += value;
					c.sendMessage("You now have " + c.pkPoints
							+ ". pking points");
					c.getItems().resetItems(3823);
					resetShop(c.myShopId);
					updatePlayerShop();
					return true;
				}
			}
			if (c.myShopId == 82) {
				if (c.getItems().playerHasItem(itemID)) {
					int value = getBossPointValue(itemID) * amount;
					value = (int) (value * 0.50);
					if (Item.itemStackable[itemID]) {
						c.getItems().deleteItem(itemID,
								c.getItems().getItemSlot(itemID), amount);
					} else {
						c.getItems().deleteItem2(itemID, amount);
					}
					addShopItem(itemID, amount);
					c.bossPoints += value;
					c.sendMessage("You now have " + c.bossPoints
							+ ". Boss points");
					c.getItems().resetItems(3823);
					resetShop(c.myShopId);
					updatePlayerShop();
					return true;
				}
			}
			// double ShopValue;
			// double TotPrice;
			int TotPrice2 = 0;
			// int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(getItemShopValue(itemID, 1,
						fromSlot));
				if (itemID == 14527 || itemID == 11882 || itemID == 11898
						|| itemID == 19588) {
					TotPrice2 = 130000;
				}
				if (c.getItems().freeSlots() > 0
						|| c.getItems().playerHasItem(995)) {
					if (Item.itemIsNote[itemID] == false) {
						c.getItems().deleteItem(itemID,
								c.getItems().getItemSlot(itemID), 1);
					} else {
						c.getItems().deleteItem(itemID, fromSlot, 1);
					}
					c.getItems().addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					c.sendMessage("You don't have enough space in your inventory.");
					break;
				}
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return true;
	}

	/**
	 * Sell item to shop (Shop Price)
	 **/
	public void sellToShopPrice(int removeId, int removeSlot) {
		if (c.playerRights == 2) {

			c.sendMessage("You can't sell "
					+ ItemAssistant.getItemName(removeId).toLowerCase() + ".");
			return;
		}
		for (int i = 0; i < Config.NOT_ALLOWED.length; i++) {
			if (!c.inDung && removeId == Config.NOT_ALLOWED[i]) {
				c.sendMessage("That item is a forbidden out-of-dung item!!");
				c.sendMessage("Staff members have been notified and the item has been removed.");
				PlayerHandler.notifyStaff(c);
				if (c.getItems().playerHasItem(Config.NOT_ALLOWED[i], 1))
					c.getItems().deleteItem(Config.NOT_ALLOWED[i], 28);
				return;
			}
		}
		for (int i : Config.ITEM_SELLABLE) {

			if (c.myShopId == 7390) {
				c.sendMessage("Choose your price by pushing sell on the item.");
				return;
			}

			if (i == removeId) {

				c.sendMessage("You can't sell "
						+ ItemAssistant.getItemName(removeId).toLowerCase()
						+ ".");
				return;
			}

		}
		boolean IsIn = false;
		if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
			for (int j = 0; j <= ShopHandler.ShopItemsStandard[c.myShopId]; j++) {
				if (removeId == (ShopHandler.ShopItems[c.myShopId][j] - 1)) {
					IsIn = true;
					break;
				}
			}
		} else {
			IsIn = true;
		}

		if (c.myShopId == PK_POINT_SHOP) {
			int shopValue = getPkPointValue(removeId);

			shopValue = (int) (shopValue * 0.50);

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": shop will buy for " + shopValue + " pk points.");
			return;
		}
		if (c.myShopId == 53 && c.inDung) {
			int ShopValue = getDungShopPrice(removeId);
			int ShopValue2 = getOtherDungItems(removeId);
			if (ShopValue == 0 && ShopValue2 == 0) {
				c.sendMessage("This item isn't sellable to this shop.");
				return;
			}
			if (ShopValue > 0) {
				String ShopAdd = "";
				if (ShopValue >= 1000 && ShopValue < 1000000) {
					ShopAdd = " (" + (ShopValue / 1000) + "K)";
				} else if (ShopValue >= 1000000) {
					ShopAdd = " (" + (ShopValue / 1000000) + " million)";
				}

				c.sendMessage(ItemAssistant.getItemName(removeId)
						+ ": shop will buy for " + ShopValue + " coins"
						+ ShopAdd);
				return;
			} else if (ShopValue == 0 && ShopValue2 > 0) {
				String ShopAdd = "";
				if (ShopValue2 >= 1000 && ShopValue2 < 1000000) {
					ShopAdd = " (" + (ShopValue2 / 1000) + "K)";
				} else if (ShopValue2 >= 1000000) {
					ShopAdd = " (" + (ShopValue2 / 1000000) + " million)";
				}

				c.sendMessage(ItemAssistant.getItemName(removeId)
						+ ": shop will buy for " + ShopValue2 + " coins"
						+ ShopAdd);
				return;
			}
		}
		if (IsIn == false) {

			c.sendMessage("You can't sell "
					+ ItemAssistant.getItemName(removeId).toLowerCase()
					+ " to this store.");
		} else {
			int ShopValue = (int) Math.floor(getItemShopValue(removeId, 1,
					removeSlot));
			String ShopAdd = "";
			if (ShopValue >= 1000 && ShopValue < 1000000) {
				ShopAdd = " (" + (ShopValue / 1000) + "K)";
			} else if (ShopValue >= 1000000) {
				ShopAdd = " (" + (ShopValue / 1000000) + " million)";
			}

			c.sendMessage(ItemAssistant.getItemName(removeId)
					+ ": shop will buy for " + ShopValue + " coins" + ShopAdd);
		}
	}

	public void setupSkillCapes(int capes, int capes2) {
		if (c != null) {
			c.getItems().resetItems(3823);
			c.isShopping = true;
			c.stopPlayerSkill = true;
			c.myShopId = 15;
			c.getPA().sendFrame248(3824, 3822);
			c.getPA().sendFrame126("Skillcape Shop", 3901);

			int TotalItems = 0;
			TotalItems = capes2;
			if (TotalItems > ShopHandler.MaxShopItems) {
				TotalItems = ShopHandler.MaxShopItems;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
			int off = (capes > 1 ? 2 : 0);
			for (int i = 0; i < skillCapes.length; i++) {
				if (c.getLevelForXP(c.playerXP[i]) < 1)
					continue;
				c.getOutStream().writeByte(1);
				c.getOutStream().writeWordBigEndianA(skillCapes[i] + off); // why
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}

	public boolean shopSellsItem(int itemID) {
		for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
			if (itemID == (ShopHandler.ShopItems[c.myShopId][i] - 1)) {
				return true;
			}
		}
		return false;
	}

	public void skillBuy(int item) {
		int nn = get99Count();
		if (nn > 1)
			nn = 1;
		else
			nn = 0;
		for (int j = 0; j < skillCapes.length; j++) {
			if (skillCapes[j] == item || skillCapes[j] + 1 == item) {
				if (c.getItems().freeSlots() > 1) {
					if (c.getItems().playerHasItem(995, 99000)) {
						if (c.getLevelForXP(c.playerXP[j]) >= 99) {
							c.getItems().deleteItem(995,
									c.getItems().getItemSlot(995), 99000);
							c.getItems().addItem(skillCapes[j] + nn, 1);
							c.getItems().addItem(skillCapes[j] + 2, 1);
						} else {
							c.sendMessage("You must have 99 in the skill of the cape you're trying to buy.");
						}
					} else {
						c.sendMessage("You need 99k to buy this item.");
					}
				} else {
					c.sendMessage("You must have at least 1 inventory spaces to buy this item.");
				}
			}
			/*
			 * if (skillCapes[j][1 + nn] == item) { if (c.getItems().freeSlots()
			 * >= 1) { if (c.getItems().playerHasItem(995,99000)) { if
			 * (c.getLevelForXP(c.playerXP[j]) >= 99) {
			 * c.getItems().deleteItem(995, c.getItems().getItemSlot(995),
			 * 99000); c.getItems().addItem(skillCapes[j] + nn,1);
			 * c.getItems().addItem(skillCapes[j] + 2,1); } else {
			 * c.sendMessage(
			 * "You must have 99 in the skill of the cape you're trying to buy."
			 * ); } } else { c.sendMessage("You need 99k to buy this item."); }
			 * } else { c.sendMessage(
			 * "You must have at least 1 inventory spaces to buy this item."); }
			 * break; }
			 */
		}
		c.getItems().resetItems(3823);
	}

	public void updatePlayerShop() {
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].isShopping == true
						&& PlayerHandler.players[i].myShopId == c.myShopId
						&& i != c.playerId) {
					PlayerHandler.players[i].updateShop = true;
				}
			}
		}
	}

	public void updateshop(int i) {
		resetShop(i);
	}

}