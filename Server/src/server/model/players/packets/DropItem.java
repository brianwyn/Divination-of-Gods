package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.content.skills.impl.Binding;

/**
 * Drop Item
 **/
public class DropItem implements PacketType {

	public void dat(Client c) {
		int itemId = c.getInStream().readUnsignedWordA();
		c.getInStream().readUnsignedByte();
		c.getInStream().readUnsignedByte();
		int slot = c.getInStream().readUnsignedWordA();
		c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
	}

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readUnsignedWordA();
		c.getInStream().readUnsignedByte();
		c.getInStream().readUnsignedByte();
		int slot = c.getInStream().readUnsignedWordA();
		if (!c.enterdBankpin && c.hasBankPin && Config.ALLOWPINS) {
			c.getBankPin().openPin();
			c.sendMessage("Please enter your Bank-Pin before doing this!");
			c.openBank = false;
			return;
		}
		if (c.arenas() || c.inTrade || c.isBanking || c.inCwWait) {
			c.sendMessage("You can't drop items right now!");
			return;
		}
		if (c.tradeStatus == 1) {
			c.getTradeAndDuel().declineTrade();
			return;
		}
		if (c.inTrade || c.inDuelScreen
				|| !c.getItems().playerHasItem(itemId, 1, slot)) {
			return;
		}
		if (c.inDung && itemId == 17489) {

			if (c.Partner.equalsIgnoreCase("None")) {
				c.GatestoneX = c.absX;
				c.GatestoneY = c.absY;
			} else {
				for (Player p : PlayerHandler.players) {
					if (p != null) {
						Client ALLPLAYERS = (Client) p;
						if (ALLPLAYERS.playerName.equalsIgnoreCase(c.Partner)) {
							Client c2 = (Client) p;
							if (c2 != null) {
								c.GatestoneX = c.absX;
								c.GatestoneY = c.absY;
								c2.GatestoneX = c.absX;
								c2.GatestoneY = c.absY;

							}
						}
					}

				}
			}
			/*
			 * } else if(itemId != 17489 && itemId != 18314) {
			 * c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
			 * c.sendMessage
			 * ("Your item vanishes as it touches the Dungeon floor."); return;
			 * }
			 */
		}
		if (c.playerName.equalsIgnoreCase("game")) {
			c.sendMessage("Your dropping has been disabled.");
			return;
		}

		if (itemId == 4045 && !c.inDuelArena()) {
			if (c.inCwGame == false) {
				c.getItems().deleteItem(4045, 28);
				return;
			}
			int explosiveHit = 15;
			c.startAnimation(827);
			c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
			c.handleHitMask(explosiveHit, c.playerId);
			c.dealDamage(explosiveHit);
			c.getPA().refreshSkill(3);
			c.forcedText = "Ouch!";
			c.forcedChatUpdateRequired = true;
			c.updateRequired = true;
			return;

		}

		if (c.duelStatus == 1) {
			Client o = (Client) PlayerHandler.players[c.duelingWith];
			c.getTradeAndDuel().declineDuel();
			o.getTradeAndDuel().declineDuel();
			o.sendMessage("Duel closed - other player dropped an item.");
			return;
		}
		if (c.playerRights == 2) {
			c.sendMessage("Admins cannot drop items. Type ::empty instead!");
			return;
		}
		if (c.isShopping) {
			c.sendMessage("You cannot drop items while shopping! - Walk then try.");
			return;
		}
		if (c.playerItemsN[slot] != 0 && itemId != -1
				&& c.playerItems[slot] == itemId + 1) {
			if (!c.getItems().playerHasItem(itemId, 1, slot)) {
				return;
			}
		}
		c.getPA().closeAllWindows();
		boolean droppable = true;
		for (int i : Config.UNDROPPABLE_ITEMS) {
			if (i == itemId) {
				c.sendMessage("<shad=15695415>This item is currently un-droppable!");
				droppable = false;
				break;
			}
		}

		for (int i : Config.ITEM_SELLABLE) {
			if (i == itemId) {
				droppable = false;
				c.sendMessage("<shad=15695415>This item is currently un-tradeable!");
				break;
			}
		}
		if (c.inRFD()) {
			c.sendMessage("<shad=15695415>You may not drop in the RFD Minigame.");
			return;
		}
		if (c.trade11 > 0) {
			c.sendMessage("<shad=15695415>Your still in starter trial! You can't drop items yet.");
			return;
		}
		if (c.playerItemsN[slot] != 0 && itemId != -1
				&& c.playerItems[slot] == itemId + 1) {
			if (droppable) {
				for (int i = 0; i < Binding.bindedItem.length; i++) {
					if (itemId == Binding.bindedItem[i]) {
						c.droppedItem = itemId;
						c.getPA().destroyBindInterface(itemId);
						c.destroy = 1;
						return;
					}
				}
				if (c.underAttackBy > 0) {
					if (c.getShops().getItemShopValue(itemId) > 10000) {
						c.sendMessage("You may not drop items worth more than 10,000gp while in combat.");
						return;
					}
				}
				Server.itemHandler.createGroundItem(c, itemId, c.getX(),
						c.getY(), c.playerItemsN[slot], c.getId());
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.SaveGame();
				c.justDropped = true;
				if (Config.SOUND) {
					c.sendSound(c.getSound().DROPITEM);
				}
				c.droppedItem = itemId;
				c.SaveGame();
				c.getPA().closeAllWindows();
			} else {
				c.sendMessage("This item cannot be dropped.");
			}
		}
	}
}