package server.model.players.content.skills;

/**
 * @author Gabbe: gabbe_impactpk@live.com
 * Construction Skill 
 */

import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.items.Item;
import server.model.items.ItemAssistant;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.content.skills.impl.ConstructionObjects.Rugs;

public class Construction {

	public static void addItem(final Client c, final int ID,
			final int dialogueID, final int amount) {
		CycleEventHandler.getSingleton().stopEvents(c, 4245); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(4245, c, new CycleEvent() {
			// Executes after a while!
			public void execute(CycleEventContainer e) {
				c.getItems().L = 0;
				if (c.withdrawConstructionItem(ID, amount)) {
					c.getDH().itemName = "" + ItemAssistant.getItemName(ID) + "";
				}
				c.getDH().fetched = c.getItems().L;
				c.getDH().sendDialogues(dialogueID, 4241);
				c.payButlerReq++;
				c.payment = -1;
				c.mustPay = false;
				e.stop();
			}

			@Override
			public void stop() {
				// What to do when the event is stopped

			}
		}, 7);

	}

	public static void deleteButler(final Client c, final int ID) {
		CycleEventHandler.getSingleton().stopEvents(c, 4242); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(4242, c, new CycleEvent() {
			// Executes after a while!
			public void execute(CycleEventContainer e) {
				if (c == null || c.disconnected) {
					e.stop();
					return;
				}
				if (!c.inConstruction()) {
					e.stop();
					return;
				}
				c.butler = -1;
				for (int i = 0; i < NPCHandler.npcs.length; i++) {
					if (NPCHandler.npcs[i] != null) {
						if (NPCHandler.npcs[i].npcType == ID) {

							NPCHandler.npcs[i].isDead = true;
							NPCHandler.npcs[i].applyDead = true;
							NPCHandler.npcs[i].summon = false;
							NPCHandler.npcs[i].underAttackBy2 = -1; // KILLS
																	// BUTLER
							e.stop();
						}
					}
				}
			}

			@Override
			public void stop() {
				// What to do when the event is stopped
			}
		}, 1);

	}

	public static void fetchItem(Client c, int ID, int AM) {
		if (c.getItems().freeSlots() == 0 && Item.itemStackable[ID]) {
			c.sendMessage("You need some free inventory space before doing this.");
			return;
		}
		if (c.getItems().freeSlots() < AM && !Item.itemStackable[ID]) {
			c.getPA().closeAllWindows();
			if (AM == 1)
				c.sendMessage("You need " + AM + " free inventory slot.");
			else
				c.sendMessage("You need " + AM + " free inventory slots.");
		}

		c.getDH().itemNotFound = "";

		if (AM == 1) {
			deleteButler(c, c.butler);
			c.getDH().sendDialogues(4241, 4241);
			if (!c.hasBankItem(ID)) {
				if (!getItemName(ID, AM).equalsIgnoreCase("")) {
					c.getDH().itemNotFound = getItemName(ID, AM);
				}
				spawnButler(c, 4241, 7, true, 4242);
				return;
			} else if (c.hasBankItem(ID)) {
				spawnButler(c, 4241, 7, false, -1);
				addItem(c, ID, 4243, AM);
				return;
			}
		}

		if (AM == 5) {
			deleteButler(c, c.butler);
			c.getDH().sendDialogues(4241, 4241);
			if (!c.hasBankItem(ID)) {
				if (!getItemName(ID, AM).equalsIgnoreCase("")) {
					c.getDH().itemNotFound = getItemName(ID, AM);
				}
				spawnButler(c, 4241, 7, true, 4242);
				return;
			} else if (c.hasBankItem(ID)) {
				spawnButler(c, 4241, 7, false, -1);
				addItem(c, ID, 4243, AM);
				return;
			}

		}

		if (AM == 10) {
			deleteButler(c, c.butler);
			c.getDH().sendDialogues(4241, 4241);
			if (!c.hasBankItem(ID)) {
				if (!getItemName(ID, AM).equalsIgnoreCase("")) {
					c.getDH().itemNotFound = getItemName(ID, AM);
				}
				spawnButler(c, 4241, 7, true, 4242);
				return;
			} else if (c.hasBankItem(ID)) {
				spawnButler(c, 4241, 7, false, -1);
				addItem(c, ID, 4243, AM);
				return;
			}
		}

		if (AM == 15) {
			deleteButler(c, c.butler);
			c.getDH().sendDialogues(4241, 4241);
			if (!c.hasBankItem(ID)) {
				if (!getItemName(ID, AM).equalsIgnoreCase("")) {
					c.getDH().itemNotFound = getItemName(ID, AM);
				}
				spawnButler(c, 4241, 7, true, 4242);
				return;
			} else if (c.hasBankItem(ID)) {
				spawnButler(c, 4241, 7, false, -1);
				addItem(c, ID, 4243, AM);
				return;
			}
		}

		if (AM == 20) {
			deleteButler(c, c.butler);
			c.getDH().sendDialogues(4241, 4241);
			if (!c.hasBankItem(ID)) {
				if (!getItemName(ID, AM).equalsIgnoreCase("")) {
					c.getDH().itemNotFound = getItemName(ID, AM);
				}
				spawnButler(c, 4241, 7, true, 4242);
				return;
			} else if (c.hasBankItem(ID)) {
				spawnButler(c, 4241, 7, false, -1);
				addItem(c, ID, 4243, AM);
				return;
			}
		}
		if (AM >= 100) {
			deleteButler(c, c.butler);
			c.getDH().sendDialogues(4241, 4241);
			if (!c.hasBankItem(ID)) {
				if (!getItemName(ID, AM).equalsIgnoreCase("")) {
					c.getDH().itemNotFound = getItemName(ID, AM);
				}
				spawnButler(c, 4241, 7, true, 4242);
				return;
			} else if (c.hasBankItem(ID)) {
				spawnButler(c, 4241, 7, false, -1);
				addItem(c, ID, 4243, AM);
				return;
			}
		}

	}

	public static String getItemName(int ID, int amount) {
		String itemName = "";

		if (ID == 960)
			itemName = "planks";
		if (ID == 8778)
			itemName = "Oak planks";
		if (ID == 8780)
			itemName = "Teak planks";
		if (ID == 8782)
			itemName = "Mahogany planks";

		return "" + itemName + "";
	}

	/*
	 * public static void buildObject(Client c, int objectId, int ArrayID, int
	 * newObject, int X, int Y, int oldID, int face, String objectName) {
	 * if(ArrayID == -1 || newObject < 0 || X < 0 || Y < 0 || objectName ==
	 * null) return; if(c == null) return; if(!c.constructionObjects[ArrayID]) {
	 * if(hasReqs(c, objectName)) { c.getPA().checkObjectSpawn(newObject, X, Y,
	 * face, 10); c.constructionObjects[ArrayID] = true; } } else if
	 * (c.constructionObjects[ArrayID]) { c.getPA().checkObjectSpawn(newObject,
	 * X, Y, face, 10); c.constructionObjects[ArrayID] = false; } }
	 */
	public static boolean hasReqs(Client c, String object) {
		if (!c.getItems().playerHasItem(2347, 1)
				|| !c.getItems().playerHasItem(8794, 1)) {
			c.sendMessage("You need a saw and a hammer to build objects.");
			return false;
		}

		c.turnPlayerTo(c.objectX, c.objectY);
		if (object.equalsIgnoreCase("Wooden chair")) {
			if (c.getItems().playerHasItem(960, 2)
					&& c.getItems().playerHasItem(4820, 2)) {
				c.getItems().deleteItem2(4820, 2);
				c.getItems().deleteItem2(960, 2);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(250, 23);
				return true;
			} else {
				c.sendMessage("You need 2 planks and 2 Iron nails to build a chair.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Oak chair")) {
			if (c.playerLevel[23] < 19) {
				c.sendMessage("You need a Construction level of atleast 19 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8778, 2)
					&& c.getItems().playerHasItem(4820, 2)) {
				c.getItems().deleteItem2(4820, 2);
				c.getItems().deleteItem2(8778, 2);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(800, 23);
				return true;
			} else {
				c.sendMessage("You need 2 Oak planks and 3 Iron nails to build this chair.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Teak chair")) {
			if (c.playerLevel[23] < 45) {
				c.sendMessage("You need a Construction level of atleast 45 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8780, 2)
					&& c.getItems().playerHasItem(1539, 2)) {
				c.getItems().deleteItem2(1539, 2);
				c.getItems().deleteItem2(8780, 2);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(3000, 23);
				return true;
			} else {
				c.sendMessage("You need 2 Teak planks and 3 Steel nails to build this chair.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Mahogany chair")) {
			if (c.playerLevel[23] < 70) {
				c.sendMessage("You need a Construction level of atleast 70 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8782, 2)
					&& c.getItems().playerHasItem(1539, 2)) {
				c.getItems().deleteItem2(1539, 2);
				c.getItems().deleteItem2(8782, 2);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(8000, 23);
				return true;
			} else {
				c.sendMessage("You need 2 Mahogany planks and 3 Steel nails to build this chair.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Wooden bookcase")) {
			if (c.playerLevel[23] < 15) {
				c.sendMessage("You need a Construction level of atleast 15 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(960, 4)
					&& c.getItems().playerHasItem(4820, 4)) {
				c.getItems().deleteItem2(4820, 4);
				c.getItems().deleteItem2(960, 4);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(400, 23);
				return true;
			} else {
				c.sendMessage("You need 4 planks and 4 iron nails to build a bookcase.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Oak bookcase")) {
			if (c.playerLevel[23] < 35) {
				c.sendMessage("You need a Construction level of atleast 35 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8778, 3)
					&& c.getItems().playerHasItem(4820, 4)) {
				c.getItems().deleteItem2(4820, 4);
				c.getItems().deleteItem2(8778, 3);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(2500, 23);
				return true;
			} else {
				c.sendMessage("You need 4 Oak planks and 4 iron nails to build this bookcase.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Teak bookcase")) {
			if (c.playerLevel[23] < 47) {
				c.sendMessage("You need a Construction level of atleast 47 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8778, 3)
					&& c.getItems().playerHasItem(4820, 4)) {
				c.getItems().deleteItem2(4820, 4);
				c.getItems().deleteItem2(8778, 3);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(8000, 23);
				return true;
			} else {
				c.sendMessage("You need 4 Teak planks and 4 steel nails to build this bookcase.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Mahogany bookcase")) {
			if (c.playerLevel[23] < 60) {
				c.sendMessage("You need a Construction level of atleast 60 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8782, 3)
					&& c.getItems().playerHasItem(4820, 5)) {
				c.getItems().deleteItem2(4820, 5);
				c.getItems().deleteItem2(8782, 3);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(20000, 23);
				return true;
			} else {
				c.sendMessage("You need 4 Mahogany planks and 4 steel nails to build this bookcase.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Tree")) {
			if (c.playerLevel[23] < 8) {
				c.sendMessage("You need a Construction level of atleast 8 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8431, 1)) {
				c.getItems().deleteItem2(8431, 1);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(1000, 23);
				return true;
			} else {
				c.sendMessage("You need a Bagged plant(1) seed to plant this tree.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Oak tree")) {
			if (c.playerLevel[23] < 20) {
				c.sendMessage("You need a Construction level of atleast 20 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8421, 1)) {
				c.getItems().deleteItem2(8421, 1);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(1000, 23);
				return true;
			} else {
				c.sendMessage("You need a Bagged oak tree seed to plant this tree.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Willow tree")) {
			if (c.playerLevel[23] < 35) {
				c.sendMessage("You need a Construction level of atleast 35 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8423, 1)) {
				c.getItems().deleteItem2(8423, 1);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(1000, 23);
				return true;
			} else {
				c.sendMessage("You need a Bagged willow tree seed to plant this tree.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Yew tree")) {
			if (c.playerLevel[23] < 62) {
				c.sendMessage("You need a Construction level of atleast 62 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8427, 1)) {
				c.getItems().deleteItem2(8427, 1);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(1000, 23);
				return true;
			} else {
				c.sendMessage("You need a Bagged yew tree seed to plant this tree.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Magic tree")) {
			if (c.playerLevel[23] < 85) {
				c.sendMessage("You need a Construction level of atleast 85 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(8429, 1)) {
				c.getItems().deleteItem2(8429, 1);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(1000, 23);
				return true;
			} else {
				c.sendMessage("You need a Bagged yew tree seed to plant this tree.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Brown rug")) {
			if (c.playerLevel[23] < 20) {
				c.sendMessage("You need a Construction level of atleast 20 to build this.");
				return false;
			}
			if (c.getItems().playerHasItem(1759, 1)
					&& c.getItems().playerHasItem(1761, 1)) {
				c.getItems().deleteItem2(1761, 1);
				c.getItems().deleteItem2(1759, 1);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(1500, 23);
				return true;
			} else {
				c.sendMessage("You need some Ball of Wool and some Soft clay to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Red rug")) {
			if (c.playerLevel[23] < 40) {
				c.sendMessage("You need a Construction level of atleast 40 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(1759, 1)
					&& c.getItems().playerHasItem(1763, 1)) {
				c.getItems().deleteItem2(1763, 1);
				c.getItems().deleteItem2(1759, 1);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(4000, 23);
				return true;
			} else {
				c.sendMessage("You need some Ball of Wool and some Red dye to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Clay fireplace")) {
			if (c.playerLevel[23] < 30) {
				c.sendMessage("You need a Construction level of atleast 30 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(1761, 1)
					&& c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(1761, 1);
				c.getItems().deleteItem2(1511, 1);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(2500, 23);
				return true;
			} else {
				c.sendMessage("You need 2 pieces of Soft clay and some logs to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Stone fireplace")) {
			if (c.playerLevel[23] < 45) {
				c.sendMessage("You need a Construction level of atleast 45 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(3420, 2)
					&& c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(3420, 2);
				c.getItems().deleteItem2(1511, 1);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(6000, 23);
				return true;
			} else {
				c.sendMessage("You need 2 limestone bricks some logs to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Marble fireplace")) {
			if (c.playerLevel[23] < 67) {
				c.sendMessage("You need a Construction level of atleast 67 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8786, 2)
					&& c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(8786, 2);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(12000, 23);
				return true;
			} else {
				c.sendMessage("You need 2 Marble bricks and some logs to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Oak lectern")) {
			if (c.playerLevel[23] < 45) {
				c.sendMessage("You need a Construction level of atleast 45 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(8778, 1);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(1000, 23);
				return true;
			} else {
				c.sendMessage("You need an Oak plank to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Bank")) {
			if (c.playerLevel[23] < 95) {
				c.sendMessage("You need a Construction level of atleast 95 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8778, 8)
					&& c.getItems().playerHasItem(1539, 20)) {
				c.getItems().deleteItem2(8778, 8);
				c.getItems().deleteItem2(1539, 20);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(20000, 23);
				return true;
			} else {
				c.sendMessage("You need 8 Oak planks and 20 Steel nails to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Demon lectern")) {
			if (c.playerLevel[23] < 60) {
				c.sendMessage("You need a Construction level of atleast 60 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8778, 2)) {
				c.getItems().deleteItem2(8778, 2);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(3500, 23);
				return true;
			} else {
				c.sendMessage("You need 2 Oak planks to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Eagle lectern")) {
			if (c.playerLevel[23] < 75) {
				c.sendMessage("You need a Construction level of atleast 75 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8778, 3)) {
				c.getItems().deleteItem2(8778, 3);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(5000, 23);
				return true;
			} else {
				c.sendMessage("You need 3 Oak planks to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Crystal ball")) {
			if (c.playerLevel[23] < 45) {
				c.sendMessage("You need a Construction level of atleast 45 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8780, 3)
					&& c.getItems().playerHasItem(567, 1)
					&& c.getItems().playerHasItem(4692, 1)) {
				c.getItems().deleteItem2(8780, 3);
				c.getItems().deleteItem2(567, 1);
				c.getItems().deleteItem2(4692, 1);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(4000, 23);
				return true;
			} else {
				c.sendMessage("You need 3 Teak planks, 1 Unpowered orb and 1 Gold leaf to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Crystal of Power")) {
			if (c.playerLevel[23] < 70) {
				c.sendMessage("You need a Construction level of atleast 70 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8780, 3)
					&& c.getItems().playerHasItem(567, 2)
					&& c.getItems().playerHasItem(4692, 2)) {
				c.getItems().deleteItem2(8780, 3);
				c.getItems().deleteItem2(567, 2);
				c.getItems().deleteItem2(4692, 2);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(8000, 23);
				return true;
			} else {
				c.sendMessage("You need 3 Teak planks, 2 Unpowered orbs and 2 Gold leaves to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Wooden Globe")) {
			if (c.playerLevel[23] < 41) {
				c.sendMessage("You need a Construction level of atleast 41 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8778, 3)
					&& c.getItems().playerHasItem(4820, 3)
					&& c.getItems().playerHasItem(557, 4)) {
				c.getItems().deleteItem2(8778, 3);
				c.getItems().deleteItem2(557, 4);
				c.getItems().deleteItem2(4820, 3);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(2000, 23);
				return true;
			} else {
				c.sendMessage("You need 3 Oak Planks, 3 iron nails and 4 Earth runes to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Ornamental Globe")) {
			if (c.playerLevel[23] < 50) {
				c.sendMessage("You need a Construction level of atleast 50 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8780, 3)
					&& c.getItems().playerHasItem(4820, 5)
					&& c.getItems().playerHasItem(557, 7)) {
				c.getItems().deleteItem2(8780, 3);
				c.getItems().deleteItem2(557, 4);
				c.getItems().deleteItem2(4820, 5);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(4000, 23);
				return true;
			} else {
				c.sendMessage("You need 3 Teak Planks, 5 iron nails and 7 Earth runes to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Lunar Globe")) {
			if (c.playerLevel[23] < 60) {
				c.sendMessage("You need a Construction level of atleast 60 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8780, 3)
					&& c.getItems().playerHasItem(4820, 8)
					&& c.getItems().playerHasItem(8784, 1)) {
				c.getItems().deleteItem2(8780, 3);
				c.getItems().deleteItem2(8784, 1);
				c.getItems().deleteItem2(4820, 8);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(8000, 23);
				return true;
			} else {
				c.sendMessage("You need 3 Teak Planks, 8 iron nails and a Gold leaf to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Celestial Globe")) {
			if (c.playerLevel[23] < 70) {
				c.sendMessage("You need a Construction level of atleast 70 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8780, 3)
					&& c.getItems().playerHasItem(4820, 11)
					&& c.getItems().playerHasItem(8784, 2)) {
				c.getItems().deleteItem2(8780, 3);
				c.getItems().deleteItem2(8784, 2);
				c.getItems().deleteItem2(4820, 11);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(12000, 23);
				return true;
			} else {
				c.sendMessage("You need 3 Teak Planks, 11 iron nails and 2 Gold leaves to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Large Orrery")) {
			if (c.playerLevel[23] < 80) {
				c.sendMessage("You need a Construction level of atleast 80 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8782, 3)
					&& c.getItems().playerHasItem(1539, 11)
					&& c.getItems().playerHasItem(8784, 3)) {
				c.getItems().deleteItem2(8782, 3);
				c.getItems().deleteItem2(8784, 3);
				c.getItems().deleteItem2(1539, 11);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(16000, 23);
				return true;
			} else {
				c.sendMessage("You need 3 Mahogany Planks, 7 Steel nails and 3 Gold leaves to construct this.");
				return false;
			}
		}
		if (object.equalsIgnoreCase("Telescope")) {
			if (c.playerLevel[23] < 50) {
				c.sendMessage("You need a Construction level of atleast 50 to construct this.");
				return false;
			}
			if (c.getItems().playerHasItem(8778, 3)
					&& c.getItems().playerHasItem(1775, 1)) {
				c.getItems().deleteItem2(8778, 3);
				c.getItems().deleteItem2(1775, 1);
				c.startAnimation(898);
				c.getPA().closeAllWindows();
				c.getPA().addSkillXP(2500, 23);
				return true;
			} else {
				c.sendMessage("You need 3 Oak Planks and some Molten glass to construct this.");
				return false;
			}
		}
		return false;
	}

	public static void spawnButler(final Client c, final int ID,
			final int timer, final boolean dialogue, final int dialogueID) {
		CycleEventHandler.getSingleton().stopEvents(c, 4241); // let's stop the
																// event first.
		CycleEventHandler.getSingleton().addEvent(4241, c, new CycleEvent() {
			// Executes after a while!
			public void execute(CycleEventContainer e) {
				if (c == null || c.disconnected) {
					e.stop();
					return;
				}
				if (!c.inConstruction()) {
					e.stop();
					return;
				}
				c.butler = ID;
				Server.npcHandler.butt(c, ID, c.absX, c.absY - 1,
						c.heightLevel, 0, 0, 0, false, 0, 0);
				if (dialogue) {
					c.getDH().sendDialogues(dialogueID, 4241);
				}
				e.stop();
			}

			@Override
			public void stop() {
				// What to do when the event is stopped
			}
		}, timer);
	}

	public static void spawnObjects(final Client c) {
		if (c == null || c.disconnected) {
			return;
		}
		if (c.constructionObjects[0] && c.constructionUpgrades[2] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[2], 1898, 5092,
					2, 10);
		if (c.constructionObjects[1] && c.constructionUpgrades[3] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[3], 1901, 5092,
					2, 10);
		if (c.constructionObjects[2] && c.constructionUpgrades[4] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[4], 1902, 5092,
					2, 10);
		if (c.constructionObjects[3] && c.constructionUpgrades[5] != -1)
			Rugs.rugSpawning(c, false, c.constructionUpgrades[5]);
		else if (!c.constructionObjects[3])
			Rugs.rugSpawning(c, true, 15413);
		if (c.constructionObjects[4] && c.constructionUpgrades[0] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[0], 1896, 5088,
					0, 10);
		if (c.constructionObjects[5] && c.constructionUpgrades[1] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[1], 1903, 5088,
					2, 10);
		if (c.constructionObjects[6] && c.constructionUpgrades[6] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[6], 1899, 5095,
					1, 10);

		if (!c.constructionObjects[7]) {
			c.getPA().checkObjectSpawn(11219, 1888, 5095, 2, 10);
			c.getPA().checkObjectSpawn(11219, 1888, 5094, 2, 10);
		} else if (c.constructionObjects[7] && c.constructionUpgrades[7] != -1) {
			c.getPA().checkObjectSpawn(27663, 1888, 5095, 1, 10);
			c.getPA().checkObjectSpawn(-1, 1888, 5094, 1, 10);
		}

		/*
		 * if(!c.constructionObjects[8]) { c.getPA().checkObjectSpawn(15365,
		 * 1893, 5089, 1, 10); }
		 */
		c.getPA().checkObjectSpawn(1, 1887, 5091, 1, 10);
		c.getPA().checkObjectSpawn(1, 1887, 5092, 1, 10);

		if (c.constructionObjects[9] && c.constructionUpgrades[9] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[9], 1890, 5098,
					2, 10);

		if (c.constructionObjects[10] && c.constructionUpgrades[10] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[10], 1893, 5100,
					0, 10);

		if (c.constructionObjects[11] && c.constructionUpgrades[11] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[11], 1889, 5100,
					1, 10);
		if (c.constructionObjects[12] && c.constructionUpgrades[12] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[12], 1893, 5103,
					2, 10);

		if (c.constructionObjects[13] && c.constructionUpgrades[13] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[13], 1892, 5103,
					1, 10);
		if (c.constructionObjects[14] && c.constructionUpgrades[14] != -1)
			c.getPA().checkObjectSpawn(c.constructionUpgrades[14], 1891, 5103,
					1, 10);
	}

	public Construction(Client Client) {
	}

}