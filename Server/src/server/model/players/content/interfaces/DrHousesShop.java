package server.model.players.content.interfaces;

import server.model.players.Client;
import server.util.Misc;

public class DrHousesShop {

	public static String selected = "";

	public static void confirm(Client c) {
		if (getPrice(c) == -1) {
			c.sendMessage("No title was selected.");
			return;
		}
		int l = getPrice(c);
		if (c.LoyaltyPoints >= l && getTitleID(c) != -1) {
			c.LoyaltyPoints -= l;
			c.playerTitle = getTitleID(c);
			c.sendMessage("You've successfully purchased the title " + selected
					+ " for " + l + " Loyalty points.");
		} else if (c.LoyaltyPoints < getPrice(c)) {
			c.sendMessage("You do not have enough Loyalty points to buy this rank.");
		}
		c.getPA().sendFrame126("" + c.LoyaltyPoints + "", 16482);
		c.getPA().sendFrame126("" + c.LoyaltyPoints + "", 40482);
	}

	public static void confirmCostume(Client c) {
		if (getPrice(c) == -1) {
			c.sendMessage("No costume was selected.");
			return;
		}
		int l = getPrice(c);

		if (getFreeSlotsRequired(c) == -1)
			return;

		if (c.getItems().freeSlots() < getFreeSlotsRequired(c)) {
			c.sendMessage("You need " + getFreeSlotsRequired(c)
					+ " free inventory slots to buy this.");
			return;
		}

		if (c.LoyaltyPoints >= l
				&& c.getItems().freeSlots() >= getFreeSlotsRequired(c)) {
			c.LoyaltyPoints -= l;
			rewardPlayer(c);
			c.sendMessage("You've successfully purchased the " + selected
					+ " for " + l + " Loyalty points.");
		} else if (c.LoyaltyPoints < getPrice(c)) {
			c.sendMessage("You do not have enough Loyalty points to buy this costume.");
		}
		c.getPA().sendFrame126("" + c.LoyaltyPoints + "", 40482);
		c.getPA().sendFrame126("" + c.LoyaltyPoints + "", 16482);
	}

	public static int getFreeSlotsRequired(Client c) {
		if (selected == "Frog Costume")
			return 3;
		if (selected == "Mime Costume")
			return 5;
		if (selected == "Zombie Costume")
			return 5;
		if (selected == "Warlock Costume")
			return 3;
		if (selected == "Jester Costume")
			return 4;
		if (selected == "Skeleton Costume")
			return 5;
		if (selected == "Sled" || selected == "Basket")
			return 1;
		if (selected == "Santa Costume")
			return 4;
		if (selected == "Grimreaper's Hood")
			return 1;
		if (selected == "Investigator's Costume")
			return 3;
		return -1;
	}

	public static int getPrice(Client c) {
		if (selected.equalsIgnoreCase(""))
			return -1;

		if (selected == "Sir" || selected == "Lady")
			return 1000;

		if (selected == "King" || selected == "Queen")
			return 3000;

		if (selected == "Gangster")
			return 5000;

		if (selected == "Peedo")
			return 8000;

		if (selected == "Demon")
			return 10000;

		if (selected == "Angel")
			return 10000;

		if (selected == "Unstoppable")
			return 15000;

		if (selected == "Nerd")
			return 20000;
		if (selected == "Psychopath")
			return 25000;
		if (selected == "Immortal")
			return 30000;
		if (selected == "Frog Costume" || selected == "Mime Costume")
			return 1000;
		if (selected == "Zombie Costume")
			return 2000;
		if (selected == "Warlock Costume")
			return 4000;
		if (selected == "Jester Costume")
			return 3000;
		if (selected == "Skeleton Costume")
			return 5000;
		if (selected == "Sled" || selected == "Basket")
			return 7500;
		if (selected == "Witchdoctor Costume")
			return 13000;
		if (selected == "Santa Costume")
			return 15000;
		if (selected == "Grimreaper's Hood")
			return 15000;
		if (selected == "Investigator's Costume")
			return 17500;
		return -1;
	}

	public static int getTitleID(Client c) {
		if (selected.equalsIgnoreCase(""))
			return -1;

		if (selected == "Sir")
			return 1;
		if (selected == "Lady")
			return 2;
		if (selected == "King")
			return 3;
		if (selected == "Queen")
			return 4;
		if (selected == "Gangster")
			return 5;
		if (selected == "Peedo")
			return 6;
		if (selected == "Demon")
			return 7;
		if (selected == "Angel")
			return 8;
		if (selected == "Unstoppable")
			return 9;
		if (selected == "Nerd")
			return 10;
		if (selected == "Psychopath")
			return 11;
		if (selected == "Immortal")
			return 12;

		return -1;
	}

	public static void handleButtons(Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0,
				packetSize);
		switch (actionButtonId) {
		case 193044:
			break;
		case 193046:
			break;
		case 193048:
			break;
		case 193050:
			break;
		case 193052:
			break;
		case 193054:
			break;
		case 64128:
		case 158064:
			selected = "";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 64130:
		case 158066:
		case 193080:
		case 173226:
		case 134210:
			c.getPA().showInterface(16450);// title
			selected = "";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;

		case 193081:
		case 64131:
		case 158067:
		case 173227:
		case 134211:
			c.getPA().showInterface(40450);// Costume ,
			selected = "";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;

		case 193082:
		case 64132:
		case 158068:
		case 173228:
		case 134212:
			c.sendMessage("This section is currently unavailable.");
			// c.getPA().showInterface(44450);//Recolour
			selected = "";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;

		case 193083:
		case 64133:
		case 158069:
		case 173229:
		case 134213:
			// c.getPA().showInterface(49450);//Spin
			c.sendMessage("This section is coming very soon!");
			selected = "";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;

		case 193086:
		case 158072:
		case 64136:
		case 173232:
		case 134126:
			// c.getPA().showInterface(34450);//Task
			c.sendMessage("This section is coming very soon!");
			selected = "";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;

		case 64068:
			selected = "Sir";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64080:
			selected = "Demon";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64082:
			selected = "Angel";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64084:
			selected = "Unstoppable";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64086:
			selected = "Nerd";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64088:
			selected = "Psychopath";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64090:
			selected = "Immortal";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64070:
			selected = "Lady";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64072:
			selected = "King";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64074:
			selected = "Queen";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64076:
			selected = "Gangster";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64078:
			selected = "Peedo";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			break;
		case 64126:
			confirm(c);
			break;
		case 64134:
		case 158070:
			c.getPA().closeAllWindows();
			selected = "";
			c.getPA().sendFrame126("Selected: " + selected + "", 16522);
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		// Costume

		case 158004:
			selected = "Frog Costume";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158006:
			selected = "Mime Costume";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158008:
			selected = "Zombie Costume";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158010:
			selected = "Warlock Costume";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158012:
			selected = "Jester Costume";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158014:
			selected = "Skeleton Costume";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158016:
			selected = "Sled";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158018:
			selected = "Basket";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158020:
			selected = "";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			c.sendMessage("This costume is currently unavailable.");
			break;
		case 158022:
			selected = "Santa Costume";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			c.sendMessage("This costume is currently unavailable.");
			break;
		case 158024:
			selected = "Grimreaper's Hood";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158026:
			selected = "Investigator's Costume";
			c.getPA().sendFrame126("Selected: " + selected + "", 40522);
			break;
		case 158062:
			confirmCostume(c);
			break;
		}
	}

	public static void open(Client c, int ID) {
		c.getPA().showInterface(ID);
	}

	public static void rewardPlayer(Client c) {
		if (selected == "Frog Costume") {
			c.getItems().addItem(6188, 1);
			c.getItems().addItem(6184, 1);
			c.getItems().addItem(6185, 1);
			return;
		} else if (selected == "Mime Costume") {
			c.getItems().addItem(3057, 1);
			c.getItems().addItem(3058, 1);
			c.getItems().addItem(3059, 1);
			c.getItems().addItem(3060, 1);
			c.getItems().addItem(3061, 1);
		} else if (selected == "Zombie Costume") {
			c.getItems().addItem(7594, 1);
			c.getItems().addItem(7592, 1);
			c.getItems().addItem(7593, 1);
			c.getItems().addItem(7595, 1);
			c.getItems().addItem(7596, 1);
		} else if (selected == "Warlock Costume") {
			c.getItems().addItem(14076, 1);
			c.getItems().addItem(14077, 1);
			c.getItems().addItem(14081, 1);
		} else if (selected == "Jester Costume") {
			c.getItems().addItem(10836, 1);
			c.getItems().addItem(10837, 1);
			c.getItems().addItem(10838, 1);
			c.getItems().addItem(10839, 1);
		} else if (selected == "Skeleton Costume") {
			c.getItems().addItem(9925, 1);
			c.getItems().addItem(9924, 1);
			c.getItems().addItem(9923, 1);
			c.getItems().addItem(9922, 1);
			c.getItems().addItem(9921, 1);
		} else if (selected == "Sled") {
			c.getItems().addItem(4084, 1);
		} else if (selected == "Basket") {
			c.getItems().addItem(4565, 1);
		} else if (selected == "Santa Costume") {
			c.getItems().addItem(14595, 1);
			c.getItems().addItem(14603, 1);
			c.getItems().addItem(14602, 1);
			c.getItems().addItem(14605, 1);
		} else if (selected == "Grimreaper's Hood") {
			c.getItems().addItem(11789, 1);
		} else if (selected == "Investigator's Costume") {
			c.getItems().addItem(19708, 1);
			c.getItems().addItem(19706, 1);
			c.getItems().addItem(19707, 1);
		}
	}

}