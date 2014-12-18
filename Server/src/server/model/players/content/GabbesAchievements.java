package server.model.players.content;

import server.model.players.Client;

public class GabbesAchievements {

	private final static String NAME = "Divination of Gods Diary Achievements";

	// IT'S A [][] CAUSE WE WILL ADD MORE PAGES.
	private final static String[][] EASYTASKS = new String[][] { {
			"@dre@IX Beginner Tasks", "@bla@(*) Bury a regular bone", "",
			"@bla@(*) Steal an Opal", "", "@bla@(*) Mine some iron", "",
			"@bla@(*) Smith a Bronze Dagger", "", "@bla@(*) Cut down a Tree",
			"", "@bla@(*) Light a fire", "", "@bla@(*) Summon a Spirit Wolf",
			"", "@bla@(*) Craft some Air runes", "", "@bla@(*) Pick some Flax",
			"", "@bla@(*) Complete a Slayer Task", "@bla@From Kuradel", "", } };

	private final static String[][] ADVANCEDTASKS = new String[][] { {
			"@dre@IX Advanced Tasks", "@bla@(*) Kill: 40 Rock crabs", "",
			"@bla@(*) Kill: 20 Dark beasts", "", "@bla@(*) Kill: 5 Players",
			"", "@bla@(*) Obtain: Rune defender", "",
			"@bla@(*) Obtain: 5 Level 99s", "", "@bla@(*) Set: Email", "",
			"@bla@(*) Set: Bank PIN", "", "@bla@(*) Complete: 10 PC", "Games",
			"@bla@(*) Complete: 10 FoG", "Games",
			"@bla@(*) Achieve: 5 Slayer task", "@bla@Streak", "", } };

	private final static String[][] ELITETASKS = new String[][] { {
			"@dre@IX ELITE Tasks", "@bla@(*) Kill: 15 Players", "",
			"@bla@(*) Light 150 fires", "", "@bla@(*) Mine 150 Runite ores",
			"", "@bla@(*) Smith 150 Runite daggers", "",
			"@bla@(*) Spin 150 Flax", "", "@bla@(*) Summon 150 Familiars", "",
			"@bla@(*) Bury 150 bones", "", "@bla@(*) Steal 150 Red Topaz", "",
			"@bla@(*) Catch 150 Implings", "", "@bla@(*) Complete a total of",
			"@bla@100 Slayer tasks", "", } };

	public static void checkReqs(final Client c) {

		if (!hasAll99s(c)) {
			c.sendMessage("You need to be atleast level 120 in every skill.");
			return;
		}

		if (hasAll99s(c)) {
			handleCompletionistCape(c);

		}

	}

	public static void CReqs(final Client c) {
		c.getPA().showInterface(6965);
		c.getPA().sendFrame126("Completionist Cape Requirements", 6968);
		c.getPA().sendFrame126("", 6969);
		c.getPA()
				.sendFrame126("99 in all skills & 120 in Dungeoneering.", 6970);
		c.getPA().sendFrame126("All achievements must be finished.", 6971);
		c.getPA().sendFrame126("400 FoG Tokens in inventory.", 6972);
		c.getPA().sendFrame126("Atleast 100 Hours online", 6973);
		c.getPA().sendFrame126("Game mode: Hard.", 6974);
		c.getPA().sendFrame126("5 Slayer Task Streak", 6975);
		c.getPA().sendFrame126("", 6976);
		c.getPA().sendFrame126("", 6977);
		c.getPA().sendFrame126("", 6978);
		c.getPA().sendFrame126("", 6979);
		c.getPA().sendFrame126("", 6980);
	}

	public static void finishAdvancedAchievement(Client c, String Name,
			int PageId) {

	}

	// @Override
	public static String getName() {
		return NAME;
	}

	public static String[][] getPages(Client c, String level) {
		if (level.equalsIgnoreCase("Easy")) {
			if (c.task1[0]) {
				EASYTASKS[0][1] = "(*) Bury a regular bone";
			}
			if (c.task1[1]) {
				EASYTASKS[0][3] = "(*) Steal an Opal";
			}
			if (c.task1[2]) {
				EASYTASKS[0][5] = "(*) Mine some Iron";
			}
			if (c.task1[3]) {
				EASYTASKS[0][7] = "(*) Smith a Bronze Dagger";
			}
			if (c.task1[4]) {
				EASYTASKS[0][9] = "(*) Cut down a Tree";
			}
			if (c.task1[5]) {
				EASYTASKS[0][11] = "(*) Light a fire";
			}
			if (c.task1[6]) {
				EASYTASKS[0][13] = "(*) Summon a Spirit Wolf";
			}
			if (c.task1[7]) {
				EASYTASKS[0][15] = "(*) Craft some Air runes";
			}
			if (c.task1[8]) {
				EASYTASKS[0][17] = "(*) Pick some Flax";
			}
			if (c.task1[9]) {
				EASYTASKS[0][19] = "(*) Complete a Slayer Task";
				EASYTASKS[0][20] = "From Kuradel";
			}

			return EASYTASKS;

		} else if (level.equalsIgnoreCase("Advanced")) {
			if (c.task2[0]) {
				ADVANCEDTASKS[0][1] = "(*) Kill: 40 Rock crabs";
			}
			if (c.task2[1]) {
				ADVANCEDTASKS[0][3] = "(*) Kill: 20 Dark beasts";
			}
			if (c.task2[2]) {
				ADVANCEDTASKS[0][5] = "(*) Obtain: Rune defender";
			}
			if (c.task2[3]) {
				ADVANCEDTASKS[0][7] = "(*) Obtain: Rune defender";
			}
			if (c.task2[4]) {
				ADVANCEDTASKS[0][9] = "(*) Obtain: 5 Level 99s";
			}
			if (c.task2[5]) {
				ADVANCEDTASKS[0][11] = "(*) Set: Email";
			}
			if (c.task2[6]) {
				ADVANCEDTASKS[0][13] = "(*) Set: Bank PIN";
			}
			if (c.task2[7]) {
				ADVANCEDTASKS[0][13] = "(*) Complete: 10 PC";
				ADVANCEDTASKS[0][14] = "Games";
			}

			return ADVANCEDTASKS;
		} else if (level.equalsIgnoreCase("Elite")) {
			if (c.task3[0]) {
				ELITETASKS[0][1] = "(*) Kill: 15 Players";
			}
			if (c.task3[1]) {
				ELITETASKS[0][3] = "(*) Light 150 fires";
			}
			if (c.task3[2]) {
				ELITETASKS[0][5] = "(*) Mine 150 Runite ores";
			}
			if (c.task3[3]) {
				ELITETASKS[0][7] = "(*) Smith 150 Runite daggers";
			}
			if (c.task3[4]) {
				ELITETASKS[0][9] = "(*) Spin 150 Flax";
			}
			if (c.task3[5]) {
				ELITETASKS[0][11] = "(*) Summon 150 Familiars";
			}
			if (c.task3[6]) {
				ELITETASKS[0][13] = "(*) Bury 150 bones";
			}
			if (c.task3[7]) {
				ELITETASKS[0][15] = "(*) Steal 150 Red Topaz";
			}
			if (c.task3[8]) {
				ELITETASKS[0][17] = "(*) Catch 150 Implings";
			}
			if (c.task3[9]) {
				ELITETASKS[0][19] = "(*) Complete a total of";
				ELITETASKS[0][20] = "100 Slayer tasks";
			}
			return ELITETASKS;
		}
		return EASYTASKS;
	}

	public static void handleCompletionistCape(final Client c) {
		if (c.playerLevel[1] > 119 && c.playerLevel[2] > 119
				&& c.playerLevel[3] > 119 && c.playerLevel[4] > 119
				&& c.playerLevel[5] > 119 && c.playerLevel[6] > 119
				&& c.playerLevel[7] > 119 && c.playerLevel[8] > 119
				&& c.playerLevel[9] > 119 && c.playerLevel[10] > 119
				&& c.playerLevel[11] > 119 && c.playerLevel[12] > 119
				&& c.playerLevel[13] > 119 && c.playerLevel[14] > 119
				&& c.playerLevel[15] > 119 && c.playerLevel[16] > 119
				&& c.playerLevel[17] > 119 && c.playerLevel[18] > 119
				&& c.playerLevel[19] > 119 && c.playerLevel[20] > 119
				&& c.playerLevel[21] > 119 && c.playerLevel[22] > 119
				&& c.playerLevel[23] > 119 && c.playerLevel[24] > 119) {
			c.getShops().openShop(87);
		} else {
			c.sendMessage("You do not have atleast level 99 in every skill.");
			return;
		}
	}

	public static void handleEliteTask(Client c, int ID, int Reward,
			String Message) {
		if (!c.task3[ID]) {
			c.achievementInterface(Message);
			c.task3[ID] = true;
			c.sendMessage("You've completed the task: " + Message);
			c.TPoints += Reward;
			c.sendMessage("You've received " + Reward
					+ " Task points. You now have " + c.TPoints + " points!");
			c.SaveGame();
		}
	}

	public static void handleMaxCape(Client c) {
		if (c.playerLevel[1] > 98 && c.playerLevel[2] > 98
				&& c.playerLevel[3] > 98 && c.playerLevel[4] > 98
				&& c.playerLevel[5] > 98 && c.playerLevel[6] > 98
				&& c.playerLevel[7] > 98 && c.playerLevel[8] > 98
				&& c.playerLevel[9] > 98 && c.playerLevel[10] > 98
				&& c.playerLevel[11] > 98 && c.playerLevel[12] > 98
				&& c.playerLevel[13] > 98 && c.playerLevel[14] > 98
				&& c.playerLevel[15] > 98 && c.playerLevel[16] > 98
				&& c.playerLevel[17] > 98 && c.playerLevel[18] > 98
				&& c.playerLevel[19] > 98 && c.playerLevel[20] > 98
				&& c.playerLevel[21] > 98 && c.playerLevel[22] > 98
				&& c.playerLevel[23] > 98 && c.playerLevel[24] > 98) {
			c.getShops().openShop(83);
		} else {
			c.sendMessage("You do not have atleast level 99 in every skill.");
			return;
		}
	}

	public static void handleMilestoneCapes(Client c) {
		if (c.playerLevel[1] > 98 && c.playerLevel[2] > 98
				&& c.playerLevel[3] > 98 && c.playerLevel[4] > 98
				&& c.playerLevel[5] > 98 && c.playerLevel[6] > 98
				&& c.playerLevel[7] > 98 && c.playerLevel[8] > 98
				&& c.playerLevel[9] > 98 && c.playerLevel[10] > 98
				&& c.playerLevel[11] > 98 && c.playerLevel[12] > 98
				&& c.playerLevel[13] > 98 && c.playerLevel[14] > 98
				&& c.playerLevel[15] > 98 && c.playerLevel[16] > 98
				&& c.playerLevel[17] > 98 && c.playerLevel[18] > 98
				&& c.playerLevel[19] > 98 && c.playerLevel[20] > 98
				&& c.playerLevel[21] > 98 && c.playerLevel[22] > 98
				&& c.playerLevel[23] > 98 && c.playerLevel[24] > 98) {
			c.getShops().openShop(82);
		} else {
			if (c.playerLevel[1] > 89 && c.playerLevel[2] > 89
					&& c.playerLevel[3] > 89 && c.playerLevel[4] > 89
					&& c.playerLevel[5] > 89 && c.playerLevel[6] > 89
					&& c.playerLevel[7] > 89 && c.playerLevel[8] > 89
					&& c.playerLevel[9] > 89 && c.playerLevel[10] > 89
					&& c.playerLevel[11] > 89 && c.playerLevel[12] > 89
					&& c.playerLevel[13] > 89 && c.playerLevel[14] > 89
					&& c.playerLevel[15] > 89 && c.playerLevel[16] > 89
					&& c.playerLevel[17] > 89 && c.playerLevel[18] > 89
					&& c.playerLevel[19] > 89 && c.playerLevel[20] > 89
					&& c.playerLevel[21] > 89 && c.playerLevel[22] > 89
					&& c.playerLevel[23] > 89 && c.playerLevel[24] > 89) {
				c.getShops().openShop(81);
			} else {
				if (c.playerLevel[1] > 79 && c.playerLevel[2] > 79
						&& c.playerLevel[3] > 79 && c.playerLevel[4] > 79
						&& c.playerLevel[5] > 79 && c.playerLevel[6] > 79
						&& c.playerLevel[7] > 79 && c.playerLevel[8] > 79
						&& c.playerLevel[9] > 79 && c.playerLevel[10] > 79
						&& c.playerLevel[11] > 79 && c.playerLevel[12] > 79
						&& c.playerLevel[13] > 79 && c.playerLevel[14] > 79
						&& c.playerLevel[15] > 79 && c.playerLevel[16] > 79
						&& c.playerLevel[17] > 79 && c.playerLevel[18] > 79
						&& c.playerLevel[19] > 79 && c.playerLevel[20] > 79
						&& c.playerLevel[21] > 79 && c.playerLevel[22] > 79
						&& c.playerLevel[23] > 79 && c.playerLevel[24] > 79) {
					c.getShops().openShop(80);
				} else {
					if (c.playerLevel[1] > 69 && c.playerLevel[2] > 69
							&& c.playerLevel[3] > 69 && c.playerLevel[4] > 69
							&& c.playerLevel[5] > 69 && c.playerLevel[6] > 69
							&& c.playerLevel[7] > 69 && c.playerLevel[8] > 69
							&& c.playerLevel[9] > 69 && c.playerLevel[10] > 69
							&& c.playerLevel[11] > 69 && c.playerLevel[12] > 69
							&& c.playerLevel[13] > 69 && c.playerLevel[14] > 69
							&& c.playerLevel[15] > 69 && c.playerLevel[16] > 69
							&& c.playerLevel[17] > 69 && c.playerLevel[18] > 69
							&& c.playerLevel[19] > 69 && c.playerLevel[20] > 69
							&& c.playerLevel[21] > 69 && c.playerLevel[22] > 69
							&& c.playerLevel[23] > 69 && c.playerLevel[24] > 69) {
						c.getShops().openShop(79);
					} else {
						if (c.playerLevel[1] > 59 && c.playerLevel[2] > 59
								&& c.playerLevel[3] > 59
								&& c.playerLevel[4] > 59
								&& c.playerLevel[5] > 59
								&& c.playerLevel[6] > 59
								&& c.playerLevel[7] > 59
								&& c.playerLevel[8] > 59
								&& c.playerLevel[9] > 59
								&& c.playerLevel[10] > 59
								&& c.playerLevel[11] > 59
								&& c.playerLevel[12] > 59
								&& c.playerLevel[13] > 59
								&& c.playerLevel[14] > 59
								&& c.playerLevel[15] > 59
								&& c.playerLevel[16] > 59
								&& c.playerLevel[17] > 59
								&& c.playerLevel[18] > 59
								&& c.playerLevel[19] > 59
								&& c.playerLevel[20] > 59
								&& c.playerLevel[21] > 59
								&& c.playerLevel[22] > 59
								&& c.playerLevel[23] > 59
								&& c.playerLevel[24] > 59) {
							c.getShops().openShop(78);
						} else {
							if (c.playerLevel[1] > 49 && c.playerLevel[2] > 49
									&& c.playerLevel[3] > 49
									&& c.playerLevel[4] > 49
									&& c.playerLevel[5] > 49
									&& c.playerLevel[6] > 49
									&& c.playerLevel[7] > 49
									&& c.playerLevel[8] > 49
									&& c.playerLevel[9] > 49
									&& c.playerLevel[10] > 49
									&& c.playerLevel[11] > 49
									&& c.playerLevel[12] > 49
									&& c.playerLevel[13] > 49
									&& c.playerLevel[14] > 49
									&& c.playerLevel[15] > 49
									&& c.playerLevel[16] > 49
									&& c.playerLevel[17] > 49
									&& c.playerLevel[18] > 49
									&& c.playerLevel[19] > 49
									&& c.playerLevel[20] > 49
									&& c.playerLevel[21] > 49
									&& c.playerLevel[22] > 49
									&& c.playerLevel[23] > 49
									&& c.playerLevel[24] > 49) {
								c.getShops().openShop(77);
							} else {
								c.sendMessage("You need to be atleast 50 in all skills to open this shop.");
							}
						}
					}
				}
			}
		}
	}

	public static boolean hasAll99s(Client c) {
		for (int i = 0; i <= 24; i++) {
			if (c.getLevelForXP(c.playerXP[i]) >= 99
					&& c.getLevelForXP(c.playerXP[24]) >= 99)
				return true;
		}
		return false;
	}

	public static void openBook(Client c, String level) {
		if (level.equalsIgnoreCase("Easy")) {
			c.bookPages = getPages(c, "Easy");
			c.bookPage = 0;
			c.maxPages = getPages(c, "Easy").length - 1;
			c.bookName = getName();
			sendDiary(c, c.bookName, c.bookPages[0]);
		} else if (level.equalsIgnoreCase("Advanced")) {
			c.bookPages = getPages(c, "Advanced");
			c.bookPage = 0;
			c.maxPages = getPages(c, "Advanced").length - 1;
			c.bookName = getName();
			sendDiary(c, c.bookName, c.bookPages[0]);
		} else if (level.equalsIgnoreCase("Elite")) {
			c.bookPages = getPages(c, "Elite");
			c.bookPage = 0;
			c.maxPages = getPages(c, "Elite").length - 1;
			c.bookName = getName();
			sendDiary(c, c.bookName, c.bookPages[0]);
		}
	}

	public static void sendDiary(Client c, String T, String[] P) {
		if (P == null) {
			c.sendMessage("This book has no pages...");
			return;
		}
		c.startAnimation(1350);
		c.getPA().sendFrame126(T, 903);
		for (int i = 0; i < P.length; i++)
			c.getPA().sendFrame126(P[i], 843 + i);
		c.getPA().sendFrame126("- " + (c.bookPage + 1) + " -", 14165);
		c.getPA().sendFrame126("", 14166);
		c.getPA().showInterface(837);
		c.flushOutStream();
	}

	public static void vetCape(final Client c) {
		c.sendMessage("Speak to Ace about this Cape ");

	}

	public static void writeAchievementTab(final Client c) {
		for (int i = 0; i <= 9; i++) {

			if (c.task1[i]) {
				c.getPA().sendFrame126("@gre@Beginner tasks", 33161);
			} else {
				c.getPA().sendFrame126("@yel@Beginner tasks", 33161);
			}

		}
		c.getPA().sendFrame126("", 444);
		c.getPA().sendFrame126("<col=ffb000>Task points: " + c.TPoints + "",
				33170);
		// c.getPA().sendFrame126("<col=ff00ff>Divination of Gods Achievements",
		// 33163);

		c.getPA().sendFrame126("@yel@Advanced tasks", 33162);
		c.getPA().sendFrame126("@yel@Elite tasks", 33163);

	}

}