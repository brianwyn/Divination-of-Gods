package server.model.players;

import server.model.players.content.RandomEvents;
import server.util.Misc;

public class DialogueHandler {

	public static void handleRandomEventDialogues(Client c) {

		if (c == null) {
			return;
		}
		int random = Misc.random(210);
		if (random > -1 && random < 50) {
			c.getDH().sendDialogues(2525, 1);
			RandomEvents.pheasentId = 2459;
		} else {
			if (random > 49 && random < 100) {
				RandomEvents.pheasentId = 2460;
				c.getDH().sendDialogues(2526, 1);
			} else {
				if (random > 99 && random < 145) {
					RandomEvents.pheasentId = 2461;
					c.getDH().sendDialogues(2527, 1);
				} else {
					if (random > 143 && random < 200) {
						RandomEvents.pheasentId = 2462;
						c.getDH().sendDialogues(2528, 1);
					} else {
						if (random > 199 && random < 211) {
							RandomEvents.pheasentId = 2462;
							c.getDH().sendDialogues(2528, 1);
						}
					}
				}
			}
		}
	}

	private Client c;

	/**
	 * Handles all talking
	 * 
	 * @param dialogue
	 *            The dialogue you want to use
	 * @param npcId
	 *            The npc id that the chat will focus on during the chat
	 */

	/**
	 * 
	 * Gabbe: If you're adding an npc talking remember c.nextChat = 0; OTHERWISE
	 * IT WILL SEND RANDOM DIALOGUES AFTER THE CHAT!
	 * 
	 */

	public String itemName = "";
	public String itemNotFound = "";
	public int fetched = 0;

	public DialogueHandler(Client client) {
		this.c = client;
	}

	/*
	 * Information Box
	 */
	/*
	 * Information Box
	 */

	public void Kill() {
		c.nextChat = 999;
		c.teleAction = 11;
		c.getPA().sendFrame126("Dismiss your monster", 2460);
		c.getPA().sendFrame126("Yes", 2461);
		c.getPA().sendFrame126("No", 2462);
		c.getPA().sendFrame164(2459);
	}

	/*
	 * Options
	 */

	public void sendDialogues(int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch (dialogue) {
		case 4240:
			// c.getDH().sendNpcChat2("Master,", "I'm yours to command.",
			// c.talkingNpc, "Butler");
			c.nextChat = 0;
			break;
		case 4249:
			if (c.payment == -1)
				c.payment = c.getPayment();

			c.mustPay = true;
			c.getDH().sendNpcChat2("Master,",
					"I require a payment off atleast " + c.payment + " coins.",
					c.talkingNpc, "Butler");
			c.nextChat = 4250;
			break;
		case 4250:
			c.getDH().sendOption2("Pay Butler", "Don't Pay Butler");
			c.teleAction = 4325;
			c.nextChat = 0;
			break;
		case 4251:
			c.getDH()
					.sendNpcChat1("Thank you, Master.", c.talkingNpc, "Butler");
			c.nextChat = 0;
			break;
		case 3551:
			c.getDH().sendNpcChat2("Your target is deeper then Level 20",
					"Wilderness! Are you sure you wish to teleport?",
					c.talkingNpc, "Bounty Hunter");
			c.nextChat = 3552;
			break;
		case 3552:
			c.getDH().sendOption2("Teleport", "Don't teleport");
			c.teleAction = 3552;
			c.nextChat = 0;
			break;
		case 4241:
			c.getDH().sendNpcChat2("Master,",
					"I'll check your bank for that item.", c.talkingNpc,
					"Butler");
			c.nextChat = 0;
			break;
		case 4245:
			c.getDH().sendNpcChat2("Master,",
					"I'm currently unavailable to fetch that.", c.talkingNpc,
					"Butler");
			c.nextChat = 0;
			break;
		case 4242:
			if (itemNotFound == "")
				c.getDH().sendNpcChat2("Master,",
						"I did not find that item in your bank.", c.talkingNpc,
						"Butler");
			if (itemNotFound != "")
				c.getDH()
						.sendNpcChat2(
								"Master,",
								"I did not find any " + itemNotFound
										+ " in your bank.", c.talkingNpc,
								"Butler");

			c.nextChat = 0;
			break;
		case 4243:
			if (fetched == 1)
				c.getDH().sendNpcChat1(
						"Master, I've successfully fetched " + fetched + " "
								+ itemName + " for you.", c.talkingNpc,
						"Butler");
			if (fetched > 1)
				c.getDH().sendNpcChat1(
						"Master, I've successfully fetched " + fetched + " "
								+ itemName + "s for you.", c.talkingNpc,
						"Butler");

			c.nextChat = 0;
			break;
		case 444:
			c.getDH().sendNpcChat3("Everyone have tasks to complete,",
					"You can view your tasks by entering the Quest tab",
					"And then clicking on the green button.", c.talkingNpc,
					"Tasks");
			c.nextChat = 445;
			break;
		case 445:
			c.getDH().sendNpcChat3("When you complete a task..",
					"You will receive points which you can",
					"Exchange in my store for great items.", c.talkingNpc,
					"Tasks");
			c.nextChat = 446;
			break;
		case 446:
			c.getDH().sendNpcChat2("If you have any questions,",
					"Don't hestitate to ask for help!", c.talkingNpc, "Tasks");
			c.nextChat = 0;
			break;

		case 216:
			c.getDH().sendNpcChat3("Welcome to the Donator's church!",
					"Here you can recharge both your prayer and spec,",
					"You can also change your spell and prayer book.",
					c.talkingNpc, "Priest");
			break;
		case 300:
			sendOption3("Balloon Bonanza (1000 coins).",
					"Nightly Dance (500 coins).", "No action.");
			c.dialogueAction = 201;
			break;
		case 33:
			sendNpcChat3("You clean off the dust off of the Ancient effigy.",
					"The relic begins to make some sort of weird noises.",
					"I think there may be something inside here.",
					c.talkingNpc, "Ancient Effigy");
			c.nextChat = 34;
			break;
		case 34:
			if (c.getLastClicked() == 18778) {
				sendNpcChat3(
						"This will require at least a level of 91 in one of the two skills",
						"to investigate. After investigation it becomes nourished,",
						"rewarding 15,000 experience in the skill used.",
						c.talkingNpc, "Starved ancient effigy");
			} else if (c.getLastClicked() == 18779) {
				sendNpcChat3(
						"This will require at least a level of 93 in one of the two skills",
						"to investigate. After investigation it becomes sated,",
						"rewarding 30,000 experience in the skill used.",
						c.talkingNpc, "Nourished ancient effigy");
			} else if (c.getLastClicked() == 18780) {
				sendNpcChat3(
						"This will require at least a level of 95 in one of the two skills",
						"to investigate. After investigation it becomes gordged,",
						"rewarding 45,000 experience in the skill used.",
						c.talkingNpc, "Sated ancient effigy");
			} else if (c.getLastClicked() == 18781) {
				sendNpcChat4(
						"This will require at least a level of 97 in one of the two skills",
						"to investigate. After investigation it provides 60,000 ",
						"experience in the skill used and, then crumbles to dust,",
						"leaving behind a dragonkin lamp.", c.talkingNpc,
						"Gorged ancient effigy");
			}
			if (c.effigy == 0) {
				c.nextChat = 35;
			} else {
				c.nextChat = 36;
			}
			break;
		case 35:
			int r = 1 + Misc.random(6);
			switch (r) {
			case 1:
				sendOption5("", "Crafting", "", "Agility", "");
				c.effigy = 1;
				c.dialogueAction = 35;
				break;
			case 2:
				sendOption5("", "Runecrafting", "", "Thieving", "");
				c.effigy = 2;
				c.dialogueAction = 36;
				break;
			case 3:
				sendOption5("", "Cooking", "", "Firemaking", "");
				c.effigy = 3;
				c.dialogueAction = 37;
				break;
			case 4:
				sendOption5("", "Farming", "", "Fishing", "");
				c.effigy = 4;
				c.dialogueAction = 38;
				break;
			case 5:
				sendOption5("", "Fletching", "", "Woodcutting", "");
				c.effigy = 5;
				c.dialogueAction = 39;
				break;
			case 6:
				sendOption5("", "Herblore", "", "Prayer", "");
				c.effigy = 6;
				c.dialogueAction = 40;
				break;
			case 7:
				sendOption5("", "Smithing", "", "Mining", "");
				c.effigy = 7;
				c.dialogueAction = 41;
				break;
			}
			break;
		case 36:
			switch (c.effigy) {
			case 1:
				sendOption5("", "Crafting", "", "Agility", "");
				c.dialogueAction = 35;
				break;
			case 2:
				sendOption5("", "Runecrafting", "", "Thieving", "");
				c.dialogueAction = 36;
				break;
			case 3:
				sendOption5("", "Cooking", "", "Firemaking", "");
				c.dialogueAction = 37;
				break;
			case 4:
				sendOption5("", "Farming", "", "Fishing", "");
				c.dialogueAction = 38;
				break;
			case 5:
				sendOption5("", "Fletching", "", "Woodcutting", "");
				c.dialogueAction = 39;
				break;
			case 6:
				sendOption5("", "Herblore", "", "Prayer", "");
				c.dialogueAction = 40;
				break;
			case 7:
				sendOption5("", "Smithing", "", "Mining", "");
				c.dialogueAction = 41;
				break;
			}
			break;
		case 41:
			c.getDH().sendNpcChat3("When changing Game mode",
					"All skills are reset.", "Continue?", c.talkingNpc,
					"Change Game mode?");
			c.nextChat = 42;
			break;
		case 42:
			c.getDH().sendOption2("Yes", "No");
			c.dialogueAction = 945;
			break;
		case 5995:
			c.getDH().sendNpcChat1("GO CAPTURE THEIR FLAG!!", c.talkingNpc,
					"Castle Wars");
			c.nextChat = 0;
			break;
		case 321:
			c.getDH()
					.sendNpcChat2(
							"Green Dragons are located in the Wilderness(@red@Lvl 18@bla@)",
							"Do you wish to teleport into level 43 Wilderness?",
							c.talkingNpc, "Slayer Ring");
			c.nextChat = 326;
			break;
		case 326:
			c.getDH().sendOption2("Yes", "No");
			c.dialogueAction = 949;
			break;
		case 325:
			c.getDH()
					.sendNpcChat2(
							"Red Dragons are located in the Wilderness(@red@Lvl 43@bla@)",
							"Do you wish to teleport into level 18 Wilderness?",
							c.talkingNpc, "Slayer Ring");
			c.nextChat = 322;
			break;
		case 322:
			c.getDH().sendOption2("Yes", "No");
			c.dialogueAction = 948;
			break;
		case 329:
			c.getDH().sendNpcChat2(
					"Warning! You're about to enter a god chamber!",
					"Do you wish to teleport into the bandos room?",
					c.talkingNpc, "Slayer Ring");
			c.nextChat = 330;
			break;
		case 330:
			c.getDH().sendOption2("Yes", "No");
			c.dialogueAction = 935;
			break;
		case 5996:
			c.getDH().sendNpcChat4("@red@Note: You cannot leave a CW game!",
					"Wait till the game ends.",
					"If you logout from a CW game, you",
					"Will receive a blackmark.", c.talkingNpc, "Castle Wars");
			c.nextChat = 0;
			break;
		case 4995:
			c.getDH().sendNpcChat3("Welcome.",
					"Here you can kill bosses for rewards!",
					"Select your class rate!", c.talkingNpc, "King Gabbe");
			c.nextChat = 4996;
			break;
		case 3742:
			c.getDH().sendNpcChat3(
					"If you are not Samy, you should not be here,",
					"because this is the Samyzone and is for Samy's only.",
					"If you are not a Samy I recommend that you leave.",
					c.talkingNpc, "Warning");
			c.nextChat = 0;
			break;
		case 4996:
			c.getPA().showInterface(46100);
			break;
		case 626:
			c.getDH().sendNpcChat3("Here are the three main shops,",
					"Armours and weapons", "Can be bought here!", c.talkingNpc,
					"Server Guide");
			c.nextChat = 627;
			break;
		case 627:
			c.guidePlayer2();
			break;
		case 628:
			c.getDH().sendNpcChat3("This is the general store.",
					"You can sell your items here!",
					"You can also claim rewards here after voting.",
					c.talkingNpc, "Server Guide");
			c.nextChat = 629;
			break;
		case 629:
			c.guidePlayer3();
			break;
		case 630:
			c.getDH().sendNpcChat3("Here is the Skillcape Store!",
					"After achieveing 99 in a skill,",
					"You can purchase and equip the cape of true masters!",
					c.talkingNpc, "Server Guide");
			c.nextChat = 631;
			break;
		case 631:
			c.guidePlayer4();
			break;
		case 632:
			c.getDH().sendNpcChat3("Here is the Slayer Master!",
					"You can receive Slayer Tasks",
					"Or spend your Slayer points in her shop!", c.talkingNpc,
					"Server Guide");
			c.nextChat = 633;
			break;
		case 633:
			c.guidePlayer5();
			break;
		case 634:
			c.getDH().sendNpcChat3("I'll now show you some of our minigames!",
					"Here's our Castle Wars,",
					"Brave warriors risk their lives here for rewards.",
					c.talkingNpc, "Server Guide");
			c.nextChat = 635;
			break;
		case 635:
			c.guidePlayer7();
			break;
		case 636:
			c.getDH().sendNpcChat2("This is the Duel Arena!",
					"Warriors across the land duel eachother here!",
					c.talkingNpc, "Server Guide");
			c.nextChat = 640;
			break;
		case 637:
			c.guidePlayer6();
			break;
		case 638:
			c.getDH().sendNpcChat3("Here's Pest Control!",
					"Close the portals to get rid of the monsters",
					"From the void!", c.talkingNpc, "Server Guide");
			c.nextChat = 639;
			break;
		case 639:
			c.guidePlayer8();
			break;
		case 640:
			c.guidePlayer9();
			break;
		case 641:
			c.getDH().sendNpcChat3("It's time i show you some other things!",
					"Here's our summoning area!",
					"Players train the Summoning skill here!", c.talkingNpc,
					"Server Guide");
			c.nextChat = 642;
			break;
		case 642:
			c.guidePlayer10();
			break;
		case 643:
			c.getDH().sendNpcChat2("Here's the Dungeoneering area.",
					"Players explore dungeon floors here!", c.talkingNpc,
					"Server Guide");
			c.nextChat = 644;
			break;
		case 644:
			c.guidePlayer11();
			break;
		case 645:
			c.getDH().sendNpcChat3("This is Nex", "Divination of Gods's hardest boss!",
					"If you defeat her you might receive a great reward!",
					c.talkingNpc, "Server Guide");
			c.nextChat = 685;
			break;
		case 685:
			c.guidePlayer12();
			break;
		case 647:
			c.getDH().sendNpcChat1(
					"If you ever need help dont forget to ask other players!",
					c.talkingNpc, "Server Guide");
			c.nextChat = 666;
			break;
		case 648:
			c.getDH()
					.sendNpcChat3(
							"I suggest that you start your adventure by typing ::vote!",
							"You will receive 10 vote points which you can spend",
							"On nice items in the General store.",
							c.talkingNpc, "Server Guide");
			c.stopGuideEvent();
			c.nextChat = 647;
			break;
		case 666:
			c.doTut = false;
			c.getPA().closeAllWindows();
			c.getPA().addStarterItems();
			c.doingTut = false;
			break;
		case 4997:
			c.getDH().sendNpcChat2("Dominion Tower points reward: 5",
					"If you die you will loose your items!", c.talkingNpc,
					"King Gabbe");
			c.nextChat = 4998;
			break;
		case 4998:
			sendOption2("Fight Dad", "Don't Fight");
			c.teleAction = 4998;
			break;
		case 4999:
			c.getDH().sendNpcChat2("Dominion Tower points reward: 6",
					"If you die you will loose your items!", c.talkingNpc,
					"King Gabbe");
			c.nextChat = 5000;
			break;
		case 5000:
			sendOption2("Fight Spirit", "Don't Fight");
			c.teleAction = 5000;
			break;
		case 5672:
			sendOption2("Pick", "Leave");
			c.dialogueAction = 2000;
			c.dialogueId = 999;
			c.teleAction = -1;

			break;
		case 5001:
			c.getDH().sendNpcChat2("Dominion Tower points reward: 9",
					"If you die you will loose your items!", c.talkingNpc,
					"King Gabbe");
			c.nextChat = 5002;
			break;
		case 5002:
			sendOption2("Fight Ket-Zek", "Don't Fight");
			c.teleAction = 5002;
			break;
		case 5003:
			c.getDH().sendNpcChat2("Dominion Tower points reward: 6",
					"If you die you will loose your items!", c.talkingNpc,
					"King Gabbe");
			c.nextChat = 5004;
			break;
		case 5004:
			sendOption2("Fight Sigmund", "Don't Fight");
			c.teleAction = 5004;
			break;
		case 5005:
			c.getDH().sendNpcChat2("Dominion Tower points reward: 13",
					"If you die you will loose your items!", c.talkingNpc,
					"King Gabbe");
			c.nextChat = 5006;
			break;
		case 5006:
			sendOption2("Fight Treus", "Don't Fight");
			c.teleAction = 5006;
			break;
		case 5007:
			c.getDH().sendNpcChat2("Dominion Tower points reward: 7",
					"If you die you will loose your items!", c.talkingNpc,
					"King Gabbe");
			c.nextChat = 5008;
			break;
		case 5008:
			sendOption2("Fight Kendal", "Don't Fight");
			c.teleAction = 5008;
			break;
		case 5009:
			c.getDH().sendNpcChat2("Dominion Tower points reward: 7",
					"If you die you will loose your items!", c.talkingNpc,
					"King Gabbe");
			c.nextChat = 5010;
			break;
		case 5010:
			sendOption2("Fight Melzar", "Don't Fight");
			c.teleAction = 5010;
			break;

		case 6666:
			c.getDH().sendNpcChat1("Where can it be...", c.talkingNpc,
					"Easter Bunny");
			c.nextChat = 6667;
			break;

		case 6667:
			sendPlayerChat2("What?", "Where can what be?");
			c.nextChat = 6668;
			break;
		case 6668:
			c.getDH().sendNpcChat3("Oh sorry,", "I'm looking for my egg.",
					"For some reason it dissapeared!", c.talkingNpc,
					"Easter Bunny");
			c.nextChat = 6669;
			break;
		case 6669:
			sendOption2("Help the bunny", "Don't help the bunny");
			c.nextChat = 2225;
			c.dialogueAction = 188;
			break;
		case 2225:
			c.getDH().sendPlayerChat2("I can help you find it...",
					"If you reward me!");
			c.nextChat = 6671;
			c.dialogueAction = 0;
			break;
		case 6671:
			c.getDH().sendNpcChat3("You would? Thank you so much!",
					"I'll give you my most valuable ring,",
					"but only if you bring me my egg!", c.talkingNpc,
					"Easter Bunny");
			c.nextChat = 6672;
			break;

		case 6672:
			c.getDH().sendPlayerChat1("Okay, deal!");
			c.nextChat = 6673;
			break;

		case 6673:
			c.getDH().sendNpcChat3("I was playing with the egg in lumbridge.",
					"I went to the toilet to take a huge dump,",
					"and when i came back it was gone!", c.talkingNpc,
					"Easter Bunny");
			c.nextChat = 6674;
			break;
		case 6674:
			c.getDH().sendPlayerChat1(
					"Okay i guess i'll go to lumbridge and check it out.");
			c.nextChat = 0;
			c.hasHeardEasterDialogue = 1;
			c.SaveGame();
			break;
		case 2228:
			c.getDH().sendPlayerChat1(
					"Hello.. I'm looking for an egg, have you seen one?");
			c.nextChat = 2229;
			break;

		case 4819:
			sendOption2("Donator Shop", "Donator Shop 2");
			c.dialogueAction = 4381;
			break;

		case 2229:
			c.getDH().sendNpcChat2("Haha, i have plenty of eggs!",
					"You will have to be more specific!", c.talkingNpc,
					"Lumbridge Cook");
			c.nextChat = 2230;
			break;

		case 2230:
			c.getDH().sendPlayerChat3("Okay..A bunny was playing with an egg,",
					"he went to take a dump..",
					"and when he returned his egg was gone.");
			c.nextChat = 2231;
			break;
		case 2231:
			c.getDH().sendNpcChat2("Hmm nope didn't see that!",
					"I did find an egg in the fountain though!", c.talkingNpc,
					"Lumbridge Cook");
			c.nextChat = 2232;
			c.hasHeardEasterDialogue = 2;
			c.SaveGame();
			break;
		case 2232:
			c.getDH().sendNpcChat3("It cost me alot of energi to",
					"stick my head in the fountain so, i'll",
					"sell you the egg for 1M Coins.", c.talkingNpc,
					"Lumbridge Cook");
			c.nextChat = 2233;
			break;
		case 2233:
			sendOption2("Buy the egg", "Don't buy the egg.");
			c.dialogueAction = 458;
			break;
		case 2235:
			c.getDH().sendNpcChat1("You don't have 1M coins!", c.talkingNpc,
					"Lumbridge Cook");
			c.nextChat = 0;
			break;
		case 2245:
			c.getDH().sendNpcChat2("I see you have the gold.", "Here you go!",
					c.talkingNpc, "Lumbridge Cook");
			c.getItems().deleteItem(995, 1000000);
			c.getItems().addItem(7928, 1);
			c.hasHeardEasterDialogue = 3;
			c.nextChat = 2246;
			break;
		case 2246:
			c.getDH().sendPlayerChat1("Holy shit, this egg is huge!");
			c.nextChat = 0;
			break;
		case 2247:
			c.getDH().sendNpcChat2("Thank you so much!",
					"Here, take this ring!!", c.talkingNpc, "Easter Bunny");
			c.nextChat = 0;
			c.getItems().addItem(7927, 1);
			c.hasHeardEasterDialogue = 4;
			break;
		case 4444:
			c.getDH()
					.sendNpcChat4(
							"Oh Wow! I see your a master skiller!",
							"Since your 99 in all stats, i can sell",
							"You a milestone cape, called the Completionist Cape.",
							"It will only cost you 250K!", c.talkingNpc,
							"Brother Kojo");
			c.nextChat = 4445;
			break;
		case 4445:
			sendOption2("Buy Milestone Cape", "Don't buy Milestone Cape");
			c.dialogueAction = 4445;
			break;
		case 400:
			c.getDH().sendNpcChat2("Hello, welcome to the official Divination of Gods",
					"Dungeoneering area!", c.talkingNpc, "Thok");
			c.nextChat = 401;
			break;
		case 401:
			sendOption4("Start Dungeoneering", "Show me your shop",
					"Explain Party-Dungeoneering", "Nevermind");
			c.dialogueAction = 1317;
			break;

		case 125:
			sendOption2("Re-do Dungeon", "Quit Dungeon");
			c.dialogueAction = 1327;
			break;
		// new jad
		case 3332:
			c.getDH().sendNpcChat2(
					"Hello, your not skilled enough to talk to me",
					"You need atleast level 30 Def and 50 Prayer.",
					c.talkingNpc, "Brother Kojo");
			c.nextChat = 3334;
			break;
		case 3333:
			c.getDH().sendNpcChat4("Hello, i see that your over",
					"30 Defence and 50 Prayer.",
					"Would you want me to take you to the",
					"Chaos altar where you can unleash the power of Zaros?",
					c.talkingNpc, "Brother Kojo");
			c.nextChat = 3334;
			break;
		case 3334:
			c.getDH().sendOption2("Yes", "No");
			c.dialogueAction = 333;
			c.nextChat = 0;
			break;
		case 2222:
			c.getDH().sendNpcChat4(
					"If you wish to go in here you will have to",
					"Risk a Fire-cape. You will not get it back!",
					"Many people have died in these caves.",
					"Proceed with caution.", c.talkingNpc, "Warning");
			c.nextChat = 2223;
			break;
		case 2223:
			c.getDH().sendOption2("Go in", "Don't go in");
			c.dialogueAction = 112;
			break;

		/**
		 * CONSTRUCTION By Gabbe (Someone released these things that will change
		 * direct of object, creds to them)
		 * 
		 **/

		case 29164:
			c.getDH().sendNpcChat3("Hello.",
					"I have a intereseting house for sale",
					"Would you like to purchase it for 1M Coins?",
					c.talkingNpc, "Estate Agent");
			c.nextChat = 29165;
			break;

		case 29165:
			c.getDH().sendOption2("Yes, buy a house!", "No.");
			c.dialogueAction = 77;
			break;

		case 29169:
			c.getDH().sendNpcChat2("So be it.",
					"Talk to me if you change your mind.", c.talkingNpc,
					"Estate Agent");
			c.nextChat = 0;
			break;

		case 29170:
			c.getDH().sendNpcChat3("You can start training Construction",
					"By teleporting to your house.",
					"Simply build furnite to advance in the skill.",
					c.talkingNpc, "Estate Agent");
			c.nextChat = 29172;
			break;

		case 29172:
			c.getDH().sendNpcChat2("If you need any help,", "Ask a moderator!",
					c.talkingNpc, "Estate Agent");
			c.nextChat = 0;
			break;
		case 29173:
			c.getDH().sendNpcChat2("Right click on me and choose visit land",
					"to get started!", c.talkingNpc, "Estate Agent");
			c.nextChat = 0;
			break;

		case 28164:
			c.constructionID = 2854;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 28165:
			c.constructionID = 61;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 28166:
			c.constructionID = 1276;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 28167:
			c.constructionID = 1281;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 28168:
			c.constructionID = 1088;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 28169:
			c.constructionID = 594;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 57;
			break;
		case 29111:
			c.constructionID = 13411;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 58;
			break;
		case 29112:
			c.constructionID = 13584;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 29113:
			c.constructionID = 13598;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 59;
			break;
		case 29114:
			c.constructionID = 13571;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 60;
			break;
		case 28170:
			c.constructionID = 8503;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 28171:
			c.constructionID = 13417;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 28172:
			c.constructionID = 0;
			break;
		case 28173:
			c.constructionID = 0;
			break;
		case 28174:
			c.constructionID = 0;
			break;
		case 28175:
			c.constructionID = 0;
			break;
		case 28176:
			c.constructionID = 0;
			break;
		case 28177:
			c.constructionID = 0;
			break;
		case 28178:
			c.constructionID = 13432;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 56;
			break;
		case 28179:
			c.constructionID = 593;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 28180:
			c.constructionID = 1088;
			c.getDH().sendOption4("North", "East", "South", "West");
			c.dialogueAction = 55;
			break;
		case 5555:
			sendNpcChat4("WARNING:", "This boss is EXTREMELY HARD to defeat.",
					"It requires ATLEAST 5 Players", "Range is recommended.",
					c.talkingNpc, "WARNING");
			c.nextChat = 5557;
			break;

		case 5557:
			sendOption2("Yes, teleport me!", "Woah Hell No.");
			c.nextChat = 0;
			c.teleAction = 966;
			break;

		case 2196:
			sendNpcChat4("Hello! I'm here to teach you about Cooking.",
					"Cooking is used to warm up raw fish so you can eat it.",
					"When you eat a cooked item you will receive HP.",
					"To cook a raw fish simply use", c.talkingNpc, "Cook");
			c.nextChat = 2197;
			break;
		case 2197:
			sendNpcChat4("The raw fish on a cooking range or a fire.",
					"If you have the required level",
					"You will automaticly start cooking the fish.",
					"At level 1 cooking you can only cook Shrimp.",
					c.talkingNpc, "Cook");
			c.nextChat = 2198;
			break;
		case 2198:
			sendNpcChat4("Buy a raw shrimp from my store",
					"and use it on the range.",
					"If you need anymore information", "Ask a staff member!",
					c.talkingNpc, "Cook");
			c.nextChat = 0;
			break;
		case 1196:
			sendNpcChat4("Hello! I'm here to help you train Fishing.",
					"Fishing is very easy but it's also very slow.",
					"At level 1 you can only catch shrimps.",
					"First, buy a small net from my store", c.talkingNpc,
					"Fisherman");
			c.nextChat = 1197;
			break;
		case 1197:
			sendNpcChat4("Then when having a fishing net in you're inventory",
					"Simply start fishing! After a short time you",
					"Will receive fish.",
					"Fish is mostly cooked using the cooking skill",
					c.talkingNpc, "Fisherman");
			c.nextChat = 1198;
			break;
		case 1198:
			sendNpcChat4(
					"For more information about cooking",
					"Talk to Charlie The Cook.",
					"Well now that you know the basics go train!",
					"More fish will be unlocked for you to fish when you level up.",
					c.talkingNpc, "Fisherman");
			c.nextChat = 0;
			break;
		case 1185:
			sendNpcChat4(
					"Hi, i know fletching pretty good.",
					"It's really simple! To fletch you will always have to have",
					"A knife and a log of you're level. For example:",
					"Let's say you're level 1 in fletching. You will have to",
					c.talkingNpc, "Fletcher");
			c.nextChat = 1186;
			break;
		case 1186:
			sendNpcChat4("Buy a knife from my store, and a log.",
					"Then simply use the knife on the log.",
					"At level 1 you can only fletch shafts.",
					"Now that you've got the knowledge, any questions?",
					c.talkingNpc, "Fletcher");
			c.nextChat = 1187;
			break;
		case 1188:
			sendNpcChat2("......", "When you're level 99 i might.",
					c.talkingNpc, "Fletcher");
			c.nextChat = 0;
			break;
		case 1187:
			sendPlayerChat1("Please eat dinner with me?");
			c.nextChat = 1188;
			break;
		case 1175:
			sendNpcChat4("Hi, im here to recruit thiefs.",
					"The problem is, there are so many guards!",
					"But that is what makes it fun, right?",
					"Anyway, pickpocket the NPC's till", c.talkingNpc, "Thief");
			c.nextChat = 1176;
			break;

		case 1176:
			sendNpcChat4("You get around level 40 Thieving, then",
					"Start stealing from the stalls!",
					"When stealing from stalls you will receive",
					"items, that you can sell to my store for money.",
					c.talkingNpc, "Thief");
			c.nextChat = 1177;
			break;
		case 1177:
			sendNpcChat4(
					"Well that's about everything.",
					"Don't forget to ask a staff member",
					"If you need further assistance in the thieving skill.",
					"Oh and i also sell Skillcapes for master thiefs. Go get 99!",
					c.talkingNpc, "Thief");
			c.nextChat = 0;
			break;
		case 1155:
			sendNpcChat4("Welcome to the Puro-Puro",
					"Hunter training area! You can catch",
					"different kinds of imps here!", "Good luck!",
					c.talkingNpc, "Tamayu");
			c.nextChat = 0;
			break;
		case 1137:
			sendNpcChat1("Hi, I'm the master of the Firemaking Skill...",
					c.talkingNpc, "Firemaker");
			c.nextChat = 1138;
			break;
		case 1138:
			sendPlayerChat1("Weird. You don't look like a Firemaker...");
			c.nextChat = 1139;
			break;

		case 1139:
			sendNpcChat4("Would you atleast let me finish?",
					"I was going to tell you that I've retired.",
					"Well I'm going to tell you a little about Firemaking.",
					"All you need to do is use a tinderbox on a log.",
					c.talkingNpc, "FireMaker");
			c.nextChat = 1140;
			break;

		case 1140:
			sendNpcChat1("The logs will begin to burn, and create a fire...",
					c.talkingNpc, "FireMaker");
			c.nextChat = 1141;
			break;

		case 1141:
			sendPlayerChat1("Noshit");
			c.nextChat = 1142;
			break;

		case 1142:
			sendNpcChat3("LET ME finish!.",
					"Well there isn't anything more to say.",
					"Just remember to buy a skillcape when you reach lvl 99!",
					c.talkingNpc, "FireMaker");
			c.nextChat = 0;
			break;

		case 1130:
			sendNpcChat4("Hello, I'm the master of the Herblore Skill.",
					"There are 2 ways to train Herblore:",
					"Making potions or cleaning herbs.",
					"To make potions, simply buy a vial of water then add",
					c.talkingNpc, "Master Potion Maker");
			c.nextChat = 1131;
			break;
		case 1131:
			sendNpcChat4(
					"The required ingridient & Leaf. For example,",
					"Let's say you're level 1 herblore.",
					"We will be making a Attack Potion (3), for beginners.",
					"First get a vial of water, and a clean Guam (from Farming)",
					c.talkingNpc, "Master Potion Maker");
			c.nextChat = 1132;
			break;
		case 1132:
			sendNpcChat4("When you've gotten the clean guam, use it",
					"on the vial of water. You will now receive,",
					"A Guam Potion (Unf). To turn it into,",
					"An Attack Potion (3) you must add the last ingridient.",
					c.talkingNpc, "Master Potion Maker");
			c.nextChat = 1133;
			break;
		case 1133:
			sendNpcChat4("An attack Potion (3)'s last ingridient is",
					"'Eye of newt'. You can buy it from my shop!",
					"When you've bought an Eye Of newt",
					"Use it on the Guam Potion (Unf)!", c.talkingNpc,
					"Master Potion Maker");
			c.nextChat = 1134;
			break;
		case 1134:
			sendNpcChat4("Now that you've created an Attack Potion (3)",
					"You can easily repeat this process!",
					"When you reach higher levels, better potions will be",
					"Unlocked for you to mix.", c.talkingNpc,
					"Master Potion Maker");
			c.nextChat = 1135;
			break;
		case 1135:
			sendNpcChat3("If you need further assistance,",
					"Please ask a staff member.",
					"Don't forget to buy a skillcape at level 99!",
					c.talkingNpc, "Master Potion Maker");
			c.nextChat = 0;
			break;
		case 1115:
			sendNpcChat4("Welcome my friend!",
					"Here you can train Farming and Herblore!",
					"First, buy a rake, seed dibber",
					"And a watering can from me. Then buy", c.talkingNpc,
					"Master Farmer");
			c.nextChat = 1116;
			break;
		case 1116:
			sendNpcChat4("A seed for you're level. Let's say",
					"you're level 1 in Farming. Then you'd have to buy",
					"a Guam Seed. When you have got the required items,",
					"Go outside, first rake the patch", c.talkingNpc,
					"Master Farmer");
			c.nextChat = 1117;
			break;
		case 1117:
			sendNpcChat4("Then use you're seed on it.",
					"Finally Water the patch and observe the Magic!",
					"When the plant has grown You can 'Pick it'.",
					"This is the part that will get you the farming XP.",
					c.talkingNpc, "Master Farmer");
			c.nextChat = 1120;
			break;
		case 1120:
			sendNpcChat1("You will also receive Herblore 'leaves'.",
					c.talkingNpc, "Master Farmer");
			c.nextChat = 1118;
			break;
		case 1118:
			sendNpcChat3("Cleaning the dirt of these leaves will",
					"Grant you Herblore XP! Well, make sure to",
					"Come and buy a skillcape when you're level 99 Farming!",
					c.talkingNpc, "Master Farmer");
			c.nextChat = 0;
			break;

		case 1111:// crafter man
			sendNpcChat4(
					"Welcome my friend!",
					"Here you can train Crafting!",
					"Either pick flax and spin it or just buy uncut stones from me.",
					"You can craft them into amulets with a chisel.",
					c.talkingNpc, "Crafter");
			c.nextChat = 1114;
			break;

		case 1114:// crafter man
			sendNpcChat3("Don't forget to buy a skillcape from",
					"Me when you reach a Crafting level of 99! The",
					"Cape proves that you're a master of the crafting skill.",
					c.talkingNpc, "Crafter");
			c.nextChat = 0;
			break;

		case 1112:// Woodcutter
			sendNpcChat3("Welcome my friend!",
					"Here you can train Woodcutting",
					"Buy your axe from me and get started!", c.talkingNpc,
					"Woodcutter");
			c.nextChat = 1113;
			break;
		case 1113:
			sendNpcChat4("When you cut wood you will receive logs.",
					"Make sure you burn these for free FM XP!",
					"When you reach level 99 Woodcutting",
					"Make sure to come and buy a skillcape from me! ",
					c.talkingNpc, "Woodcutter");
			c.nextChat = 0;
			break;

		/* Barb assault start */

		case 365:
			sendStatement("You Currently Have " + c.barbPoints
					+ " Barbarian Points.");
			c.nextChat = 0;
			break;

		case 1054:
			sendOption2("What is this place?", "Who are you?");
			c.dialogueAction = 510;
			c.nextChat = 1055;
			break;

		case 1055:
			sendNpcChat4(
					"This place is the Barbarian Coluseum. We hold combat",
					"related events within the ring to the west.",
					"Most importantly, you can play the Barbarian Defence mini-",
					"game here. To start, enter the ring to the west.",
					c.talkingNpc, "Barbarian Master");
			c.nextChat = 1056;
			break;

		case 1056:
			sendPlayerChat1("Interesting, What rewards do i recieve for participating?");
			c.nextChat = 1057;
			break;

		case 1057:
			sendNpcChat4("Let me explain a few things for you.",
					"The points you gain is damage",
					"dealt generated. So the more damage",
					"you deal the more points you recieve!", c.talkingNpc,
					"Barbarian Master");
			c.nextChat = 1058;
			break;

		case 1058:
			sendNpcChat4(
					"Say for example i done 5431 Damage total,",
					"And i died, i would recieve around 70 Points for my efforts,",
					"All Class Types can be used,", "Range/Mage/Melee",
					c.talkingNpc, "Barbarian Master");
			c.nextChat = 1059;
			break;

		case 1059:
			sendNpcChat3("Best of all you can bring your most valuable",
					"Armor into the arena,",
					"If you were to die nothing will be lost,", c.talkingNpc,
					"Barbarian Master");
			c.nextChat = 1060;
			break;
		case 1060:
			sendNpcChat3("Oh and, you can also bring familiar",
					"into the arena!",
					"They will help you complete the waves.", c.talkingNpc,
					"Barbarian Master");
			c.nextChat = 1061;
			break;

		case 1061:
			sendNpcChat3("You can leave the arena anytime",
					"by typing ::endgame",
					"You will receive points for you're efforts.",
					c.talkingNpc, "Barbarian Master");
			c.nextChat = 1062;
			break;
		case 1062:
			sendNpcChat2("This command can also be used",
					"If your character get's stuck.", c.talkingNpc,
					"Barbarian Master");
			c.nextChat = 1063;
			break;
		case 1063:
			sendNpcChat4("Remember to spend you're points in osman's shop!",
					"He's sitting over there, watching",
					"The fights. He has alot of good items",
					"For sale. Good luck!", c.talkingNpc, "Barbarian Master");
			c.nextChat = 1064;
			break;
		case 1064:
			sendPlayerChat2("Sounds good, I'm going to try it out sometime.",
					"Thanks for the help.");
			c.nextChat = 0;
			break;
		case 1065:
			sendStatement("I don't think it would be fair to interrupt a busy trainer.");
			c.nextChat = 0;
			break;

		/* Barb End */

		case 119:
			sendNpcChat2("Good day to you " + c.playerName + "",
					"Would you like a bank pin?", c.talkingNpc, "Bank server");
			c.nextChat = 120;
			break;
		case 120:
			sendOption2("Yes please! I like my account secure.",
					"No thanks I'm good for now.");
			c.dialogueAction = 120;
			c.nextChat = -1;
			break;
		case 1552:
			sendNpcChat3("Ho, ho ho!", "Christmas is around the corner!",
					"Here take these goodies!", c.talkingNpc, "Santa");
			c.nextChat = 0;
			// c.santaPrize = 1;
			break;
		case 19995:
			sendNpcChat3("Thanks for donating!", "Please take this ring for your",
					"double experience weekend!", c.talkingNpc, "Musician");
			c.nextChat = 0;
			break;
			
		case 256:
			sendNpcChat1("Would you like to abandon the Dungeon?",
					c.talkingNpc, "Dungeoneering");
			c.nextChat = 257;
			break;

		case 257:
			sendOption2("No", "Yes");
			c.dialogueAction = 257;
			c.dialogueId = 71;
			c.teleAction = -1;
			break;

		case 258:
			sendNpcChat2("Are you sure you wish to drop this item?",
					"It will Disappear, and you can't get it back!",
					c.talkingNpc, "GenocidePK Protection system.");
			c.nextChat = 259;
			break;
		case 259:
			sendOption2("No, i missclicked.", "Yes, drop the item");
			c.dialogueAction = 259;
			c.teleAction = -1;
			break;
		// HERBLORE QUEST
		case 2000:
			sendNpcChat1("Hi, did you need something?", c.talkingNpc,
					"Kaqemeex");
			c.nextChat = 2001;
			break;
		case 2001:
			sendPlayerChat1("Nice cape you got there.");
			c.nextChat = 2002;
			break;
		case 2002:
			sendNpcChat2("Thanks, this cape proves that im a",
					"Excellent Potion Maker!", c.talkingNpc, "Kaqemeex");
			c.nextChat = 2003;
			break;

		case 2003:
			sendNpcChat2("I'll teach you how to make potions",
					"if you do me one favour.", c.talkingNpc, "Kaqemeex");
			c.nextChat = 2004;
			break;

		case 2004:
			sendPlayerChat1("Really? What's the favour?");
			c.nextChat = 2005;
			break;

		case 2005:
			sendNpcChat4("I'm trying to create a potion that will",
					"Make me able to one hit noobs like Tommy17890",
					"In the Wilderness, but to do this i need you to",
					"Get me these items.", c.talkingNpc, "Kaqemeex");
			c.nextChat = 2006;
			break;

		case 2006:
			sendNpcChat3("Raw bear meat - Kill bears",
					"Raw Chicken - Kill chickens",
					"Raw Rat Meat - Kill Rats (Varrock Wild lvl: 1)",
					c.talkingNpc, "Kaqemeex");
			c.nextChat = 2007;
			break;
		case 9999: // starterrrr
			sendOption5("Pure Stater & Items",
					"Berserker Pure Starter & Items",
					"Range Tank Skills & Items", "Overall Skills & Items",
					"Skiller (No skills & Skiller items)");
			c.dialogueAction = 50;
			c.dialogueId = 40;
			c.selectStarterr = true;
			break;
		case 9998: // starterrrr
			sendOption5("Chaotic Maul", "Chaotic Rapier", "Chaotic Longsword",
					"Chaotic Crossbow", "Chaotic Staff");
			c.dialogueAction = 51;
			c.selectStarterr = true;
			break;
		case 2007:
			sendNpcChat2("So, do you want to start the Quest?",
					"I'll teach you the arts of Pot Making!", c.talkingNpc,
					"Kaqemeex");
			c.nextChat = 2008;
			break;

		case 2008:
			sendPlayerChat1("Yeh sure..But you better own that Tommy guy!");
			// c.hBQ = 1;
			c.nextChat = 2009;
			break;

		case 2009:
			sendNpcChat2("Okay, you've started the Quest.",
					"Here are some locations of the hunts:", c.talkingNpc,
					"Kaqemeex");
			c.nextChat = 2010;
			break;

		case 2010:
			sendNpcChat3("Raw bear meat - South of Rats (Varrock).",
					"Raw Chicken - Kill chickens - Camelot",
					"Raw Rat Meat - Kill Rats (Varrock Wild lvl: 1)",
					c.talkingNpc, "Kaqemeex");
			c.nextChat = 2011;
			break;

		case 2011:
			sendNpcChat2("Come back when you've got the",
					"Items for new instructions.", c.talkingNpc, "Kaqemeex");
			c.nextChat = 0;
			c.bMQ = 1;
			break;

		case 2012:
			sendNpcChat2("Holy shit you actually got me the items!",
					"Now, all you need to do is Enchant them for me.",
					c.talkingNpc, "Kaqemeex");
			c.nextChat = 2013;
			break;

		case 2013:
			sendPlayerChat1("Enchant them?! How.. I suck at HOKUS POKUS..");
			c.nextChat = 2014;
			break;
		case 2014:
			sendNpcChat4("All you need to do is dip them in the",
					"Taverly Dungeon, use each item on the cauldron",
					"And they will be enchanted.",
					"Use Monster teleport to get to Taverly Dungeon.",
					c.talkingNpc, "Kaqemeex");
			c.nextChat = 2015;
			break;

		case 2015:
			sendPlayerChat2(
					"So, teleport to Taverly dungeon, run north, go through gates,",
					"To the Cauldron thingy and use the raw items on it?");
			c.nextChat = 2016;
			break;
		case 2016:
			sendNpcChat2(
					"Correct. Bring the me the enchanced items when your done",
					"And i'll reward you.", c.talkingNpc, "Kaqemeex");
			c.nextChat = 0;
			c.bMQ = 2;
			break;

		case 2017:
			sendNpcChat2("Sweet, you got the enchanted meats!",
					"Now i'll teach you about Herblore.", c.talkingNpc,
					"Kaqemeex");
			c.nextChat = 2018;
			break;

		case 2018:
			sendNpcChat4("Herblore is a very powerful skill,",
					"When your Herblore level is around 82,",
					"You will be able to make Powerful Potions",
					"Like Extreme Pots, they raise your stats ALOT!",
					c.talkingNpc, "Kaqemeex");
			c.nextChat = 2019;
			break;

		case 2019:
			sendNpcChat4("To make a potion, you first need the UNF Potion.",
					"Using a cleaned herb on a vial of water",
					"Will create an UNF potion, then",
					"All you need to do is add the Final Ingridient!",
					c.talkingNpc, "Kaqemeex");
			c.nextChat = 2020;
			break;

		case 2020:
			sendNpcChat3("When you've completed this Quest,",
					"I'll open my personal Ingridients Shop just for you!",
					"So you will easily be able to train Herblore.",
					c.talkingNpc, "Kaqemeex");
			// c.bMQ = 3;
			c.nextChat = 2021;
			break;

		case 2021:
			sendPlayerChat2("Dude i got it seriously stop talking",
					"My head will blow up soon...");
			c.nextChat = 2023;
			break;

		case 2023:
			sendNpcChat1("Sorry, just remember to check out my shop later!",
					c.talkingNpc, "Kaqemeex");
			c.getItems().deleteItem(523, 1);
			c.getItems().deleteItem(524, 1);
			c.getItems().deleteItem(525, 1);
			c.getItems().deleteItem(522, 1);
			c.sendMessage("You've completed the quest! You can now train Herblore.");
			c.getPA().addSkillXP(50000, 15);
			c.nextChat = 0;
			c.bMQ = 3;
			break;

		// end of herb quest
		// Razgar
		case 50:
			sendNpcChat1("THE DRAGON IS BACK!", c.talkingNpc, "Razgar");
			c.nextChat = 51;
			break;
		case 51:
			sendPlayerChat1("What? Where?!");
			c.nextChat = 52;
			break;
		case 52:
			sendNpcChat1("It's you're job to find it and kill it!",
					c.talkingNpc, "Razgar");
			c.nextChat = 53;
			break;
		case 53:
			sendPlayerChat2("I'm not afraid of Dragons!",
					"Where should i start looking?");
			c.nextChat = 54;
			break;
		case 54:
			sendNpcChat3("Well, Gypsy Aris has some information",
					"You should go talk to her in varrock",
					"This quest won't be easy. I wish you best of luck!",
					c.talkingNpc, "Razgar");
			c.kingQuest = 1;
			c.nextChat = 0;
			break;
		// Gypsy
		case 55:
			sendNpcChat2("What are you doing here???",
					"I'M TRYING TO MASTURBATE", c.talkingNpc, "Gypsy");
			c.nextChat = 56;
			break;
		case 56:
			sendPlayerChat2("Lmao stop raging, Razgar sent me here",
					"He said you have some information about a quest");
			c.nextChat = 57;
			break;
		case 57:
			sendNpcChat4("Oh yes, there's a huge Dragon,",
					"It's Summoned by a Dark Wizard",
					"The dragon needs to be slain, we are all in danger",
					"It will soon attack Varrock!", c.talkingNpc, "Gypsy");
			c.nextChat = 58;
			break;
		case 58:
			sendPlayerChat1("Ok, so how do I get to the dragon and how do I kill it?");
			c.nextChat = 59;
			break;
		case 59:
			sendNpcChat2("Go talk to the Wizard in lumbridge graveyard",
					"I don't slay dragons, i'm to old!", c.talkingNpc, "Gypsy");
			c.nextChat = 60;
			break;
		case 60:
			sendPlayerChat1("Rofl noob.. I'll go see the Wizard.");
			c.kingQuest = 2;
			c.nextChat = 0;
			break;
		// Sedridor
		case 61:
			sendNpcChat1("Wasup?", c.talkingNpc, "Sedridor");
			c.nextChat = 62;
			break;
		case 62:
			sendPlayerChat2("Im on a quest to slay an evil Dragon",
					"and the Gypsy told me to come talk to you");
			c.nextChat = 63;
			break;
		case 63:
			sendNpcChat2("Ok, i know how to slay the dragon but...",
					"Bring me 2 Dragon Bones.", c.talkingNpc, "Sedridor");
			c.nextChat = 64;
			break;
		case 64:
			sendPlayerChat1("ffs dude... I'll be back with the items");
			c.kingQuest = 3;
			c.nextChat = 0;
			break;
		case 65:
			sendPlayerChat1("Here are the items you asked for...");
			c.kingQuest = 4;
			c.nextChat = 66;
			break;
		case 66:
			sendNpcChat2("Ok thanks, go talk to horvik to get prepared",
					"He is located in varrock, near fountain.", c.talkingNpc,
					"Sedridor");
			c.kingQuest = 5;
			c.nextChat = 67;
			break;
		case 67:
			sendPlayerChat1("Ok i will do that");
			c.nextChat = 0;
			break;
		case 77:
			sendPlayerChat2("I talked to Horvik and he gave me some items",
					"so what do i do now?");
			c.nextChat = 78;
			break;
		case 78:
			sendNpcChat4("You can go ahead and kill the dragon now",
					"I will teleport you to him, be careful",
					"Make sure you equip ur anti shield and bring food",
					"You might need prayer potions also", c.talkingNpc,
					"Sedridor");
			c.nextChat = 73;
			break;
		case 73:
			sendOption2("Im ready to kill the dragon",
					"Im not ready to kill the dragon");
			c.dialogueAction = 200;
			break;
		case 74:
			sendPlayerChat1("I slayed the dragon!");
			c.nextChat = 75;
			break;
		case 75:
			sendNpcChat4("Congratulations on completing the quest!",
					"Here is your reward:", "400K Strength XP, 7M GP",
					"And a Green Dragon Mask!", c.talkingNpc, "Sedridor");
			c.getPA().addSkillXP(40000, 2);
			c.getItems().addItem(19281, 1);
			c.getItems().addItem(995, 7000000);
			c.kingQuest = 8;
			c.nextChat = 0;
			c.sendMessage("You can now wear Rune Platebody and open Horvik's Shop in edgeville!");
			break;
		// Horvik
		case 68:
			sendNpcChat1("Hello, what do you need?", c.talkingNpc, "Horvik");
			c.nextChat = 69;
			break;
		case 69:
			sendPlayerChat2("Im going to slay the dragon",
					"and sedridor said you can help me get prepared");
			c.nextChat = 82;
			break;
		case 82:
			sendNpcChat1("Ok, take these items then go talk to sedridor",
					c.talkingNpc, "Horvik");
			c.getItems().addItem(1113, 1);
			c.getItems().addItem(1079, 1);
			c.getItems().addItem(1540, 1);
			c.kingQuest = 6;
			c.nextChat = 0;
			break;
		case 90:
			sendNpcChat1("W'sup " + c.playerName + ".", c.talkingNpc,
					"Grim Reaper");
			c.nextChat = 91;
			c.grimPrize = 0;
			break;
		case 91:
			sendPlayerChat1("Honestly im boored like shit");
			c.nextChat = 92;
			break;
		case 92:
			sendNpcChat1("Oo... Okay well..", c.talkingNpc, "Grim Reaper");
			c.nextChat = 93;
			break;
		case 93:
			sendPlayerChat1("I WANT FREE HALLOWEEN STUFF PLZ");
			c.nextChat = 97;
			break;
		case 97:
			sendNpcChat1("Hmm...I'll give you some items IF", c.talkingNpc,
					"Grim Reaper");
			c.nextChat = 98;
			break;
		case 98:
			sendPlayerChat1("Damn there's always a 'if'...");
			c.nextChat = 99;
			break;
		case 99:
			sendNpcChat1("Listen you moron, i want these items:", c.talkingNpc,
					"Grim Reaper");
			c.nextChat = 100;
			break;
		case 100:
			sendNpcChat2("1 Raw Shrimp, 1 Dragon Imp Jar, 1 Magic Log.",
					"1 Amulet of Magic, And 3 Runite ores.", c.talkingNpc,
					"Grim Reaper");
			c.nextChat = 101;
			break;
		case 101:
			sendPlayerChat1("Holy shit dude you better gimmie bandos after this..");
			c.nextChat = 103;
			break;
		case 103:
			sendNpcChat1("Just talk to me when u got the ST0FF..",
					c.talkingNpc, "Grim Reaper");
			c.nextChat = 0;
			c.grimPrize = 1;
			break;
		case 104:
			sendPlayerChat1("Yo man i got the stuff!");
			c.nextChat = 105;
			break;
		case 105:
			sendNpcChat1("Sweet, Here take these items..", c.talkingNpc,
					"Grim Reaper");
			c.nextChat = 0;
			if (c.grimPrize == 1)
				c.getItems().deleteItem(317, 1);
			c.getItems().deleteItem(11256, 1);
			c.getItems().deleteItem(451, 3);
			c.getItems().deleteItem(1727, 1);
			c.getItems().deleteItem(1513, 1);
			c.getItems().addItem(10724, 1);
			c.getItems().addItem(10725, 1);
			c.getItems().addItem(10726, 1);
			c.getItems().addItem(10727, 1);
			c.getItems().addItem(10728, 1);
			c.getItems().addItem(10735, 1);
			c.grimPrize = 2;
			break;

		case 109:
			sendOption5("One 6-sided die", "Two 6-sided dice",
					"One 4-sided die", "One 8-sided die", "More...");
			c.dialogueAction = 106;
			c.teleAction = 0;
			c.nextChat = 0;
			break;
		/**/
		/***
		 * Start of Rune Mysteries Quest by Gabbe & Samy, removing this cred
		 * makes you a fag /
		 **/
		case 553:
			sendNpcChat4("Hi, i've lost my Ancient Talisman",
					"Could you help me find it?",
					"I'll reward you in the art of RuneCrafting",
					"I'll teach you how to RuneCraft!", c.talkingNpc, "Aubury");
			c.nextChat = 554;
			break;
		case 554:
			sendPlayerChat2("Uhm yeh sure...", "what do you need me to do?");
			c.nextChat = 556;
			break;
		case 556:
			sendNpcChat4("Sweet, Go talk to Distentor in Wizards Tower.",
					"He's located south of Draynor Village.",
					"He might know where it is!",
					"Cuz i lost the Talisman when i was there.", c.talkingNpc,
					"Aubury");
			c.nextChat = 557;
			break;
		case 557:
			sendPlayerChat2("K but you better teach me",
					"RuneCrafting after this..");
			c.nextChat = 0;
			c.rMQ = 1;
			c.sendMessage("You've now started Rune Mysteries Quest.");
			break;
		case 558:
			sendNpcChat1("WAAAAAAZUPPPPPPPPPP.", c.talkingNpc, "Distentor");
			c.nextChat = 559;
			break;
		case 559:
			sendPlayerChat2("Looking for a freaking Talisman",
					"Have you seen it?");
			c.nextChat = 560;
			break;
		case 560:
			sendNpcChat4("Yeh i think it was in the basement",
					"Somewhere.. I guess you will have to find it!",
					"I can teleport you to the basement if you'd like..",
					"Right click on me, and choose teleport.", c.talkingNpc,
					"Distentor");
			c.nextChat = 561;
			break;
		case 561:
			sendPlayerChat2("Can't you do some HOKUS POKUS and get me it?",
					"I don't feel like searching a basement dude..");
			c.nextChat = 562;
			break;
		case 562:
			sendNpcChat2("Well, if you change your mind,",
					"Right click on me and choose teleport.", c.talkingNpc,
					"Distentor");
			c.nextChat = 563;
			break;
		case 563:
			sendPlayerChat1("*COUGH* Great Wizard my ass *COUGH*");
			c.nextChat = 0;
			c.rMQ = 2;
			break;
		case 564:
			sendNpcChat2("Come back when you have the talisman!",
					"You should have gotten it by now!", c.talkingNpc, "Aubury");
			c.nextChat = 0;
			break;
		case 565:
			sendNpcChat2("Woah is that my talisman??",
					"You've done an excellent job so far.", c.talkingNpc,
					"Aubury");
			c.nextChat = 566;
			break;
		case 566:
			sendPlayerChat2("But..?", "TEACH ME RUNECRAFTING");
			c.nextChat = 567;
			break;
		case 567:
			sendNpcChat2("I'll take that talisman pal.",
					"It's not over yet...", c.talkingNpc, "Aubury");
			c.nextChat = 568;
			c.getItems().deleteItem(681, 1);
			c.rMQ = 4;
			break;
		case 568:
			sendPlayerChat2("Dude are you kidding?", "What's left to do??");
			c.nextChat = 569;
			break;
		case 569:
			sendNpcChat1("Go talk to Distentor", c.talkingNpc, "Aubury");
			c.nextChat = 0;
			break;

		case 570:
			sendNpcChat1("Hey man :D", c.talkingNpc, "Distentor");
			c.nextChat = 571;
			break;

		case 571:
			sendPlayerChat2("...Not you again", "So what did you need me for?");
			c.nextChat = 573;
			break;
		case 573:
			sendNpcChat2("I'd like you to Bring me:",
					"10 air and fire runes, and 2 Magic Potion's (4).",
					c.talkingNpc, "Distentor");
			c.nextChat = 574;
			break;
		case 574:
			sendNpcChat2("I've heard you can get Mage pots,",
					"From Giles shop in EdgeVille..", c.talkingNpc, "Distentor");
			c.rMQ = 5;
			c.nextChat = 0;
			break;

		case 575:
			sendNpcChat2("Woot you brought me the st0ff!",
					"I'm going to own so many noobs in wild..", c.talkingNpc,
					"Distentor");
			c.nextChat = 576;
			c.getItems().deleteItem(554, 10);
			c.getItems().deleteItem(3040, 2);
			c.getItems().deleteItem(556, 10);
			c.rMQ = 6;
			break;

		case 576:
			sendNpcChat1("Go talk to Aubury now.", c.talkingNpc, "Distentor");
			c.nextChat = 0;
			break;

		case 577:
			sendNpcChat4("Thanks for all your help!",
					"I will now teach you RuneCrafting",
					"To RuneCraft, buy a talisman from my store",
					"Then Right click it and push Locate", c.talkingNpc,
					"Aubury");
			c.nextChat = 578;
			break;

		case 578:
			sendNpcChat4("Make sure you have Rune Essence's",
					"And the required RuneCrafting level before",
					"Trying to RuneCraft,",
					"Then you will start crafting Runes!", c.talkingNpc,
					"Aubury");
			c.nextChat = 579;
			break;

		case 579:
			sendNpcChat1("If you want your reward, talk to me again.",
					c.talkingNpc, "Aubury");
			c.nextChat = 0;
			c.rMQ = 7;
			break;

		case 580:
			sendNpcChat3("Here's your reward:", "50K RuneCrafting XP, 5M GP",
					"And an Air RuneCrafting Staff!", c.talkingNpc, "Aubury");
			c.getItems().addItem(13630, 1);
			c.getItems().addItem(995, 5000000);
			c.getPA().addSkillXP(50000, 20);
			c.sendMessage("Quest complete, reward: Air RuneCrafting Staff, 5M GP, and 50k RuneCrafting XP!");
			c.rMQ = 8;
			c.nextChat = 581;
			break;

		case 581:
			sendPlayerChat2("Holy shit man", "Thanks!");
			c.nextChat = 0;
			break;

		case 1001:
			sendStatement("You must have atleast one free inventory space.");
			c.nextChat = 0;
			break;

		case 1002:
			sendStatement("Please claim your previous reward before spinning again.");
			c.nextChat = 0;
			break;

		case 1003:
			sendStatement("You have claimed your reward.");
			c.nextChat = 0;
			break;

		case 1004:
			sendStatement("You have discarded your reward.");
			c.nextChat = 0;
			break;

		case 1005:
			sendStatement("You have no reward to claim.");
			c.nextChat = 0;
			break;

		case 1006:
			sendStatement("You have no reward to discard.");
			c.nextChat = 0;
			break;

		case 1283: // 1283
			sendNpcChat2("Check out our new Boss called Nex!",
					"He drops hand cannons! Type ::nex", c.talkingNpc,
					"Swensen");
			c.nextChat = 0;
			break;
		case 209:
			sendNpcChat4(
					"Hello there " + Misc.optimizeText(c.playerName) + "!",
					"Once you receive a level of atleast 80 Herblore",
					"You can mix your own Extreme potions!",
					"Simply buy the ingridients from me and get to work!",
					c.talkingNpc, "Potion mixer");
			c.nextChat = 0;
			break;
		case 540: // scrolls seller
			sendNpcChat4("Hi, i sell summoning scrolls For the NPC's:",
					"Bunyip, Pack yak, Unicorn stallion",
					"And Wolpertinger! To find out",
					"What NPC uses what scroll type ::scrolls", c.talkingNpc,
					"Summoning scroll seller");
			c.nextChat = 0;
			break;
		case 545: // titan sroll seller
			sendNpcChat4("Hi, i sell summoning scrolls For the NPC's:",
					"Fire, Steel, Geyser and Moss titan!",
					"They're scrolls make them stronger! To find out",
					"What NPC uses what scroll type ::titans", c.talkingNpc,
					"Summoning Scroll seller");
			c.nextChat = 0;
			break;

		case 211: // 1283
			sendNpcChat4("Hi, i just discovered this Portal,",
					"When i enetered it i saw a huge icy dragon!",
					"I litterly shit my pants and teleported home!",
					"If you want to visit them just enter the white portal ",
					c.talkingNpc, "Mr. Shit He's Pants.");
			c.dialogueAction = 211;
			c.nextChat = 0;
			break;

		case 110:
			sendOption5("One 10-sided die", "One 12-sided die",
					"One 20-sided die", "Two 10-sided dice for 1-100",
					"Back...");
			c.dialogueAction = 107;
			c.teleAction = 0;
			c.nextChat = 0;
			break;
		case 200:
			sendNpcChat4("Hello there " + c.playerName + "!",
					" I have the ability to reset your combat stats for free!",
					"But remember, this is irreversable!",
					"What would you like me to do?", c.talkingNpc, "Town Crier");
			c.nextChat = 210;
			break;
		case 210:
			sendOption4("Reset Defence", "Reset Prayer", "Reset Attack",
					"Reset All Combat Stats");
			c.dialogueAction = 42;
			break;
		case 230:
			sendNpcChat2("Congratulations!",
					"Your defence has been completely reset!", c.talkingNpc,
					"Town Crier");
			c.nextChat = 0;
			break;
		case 240:
			sendNpcChat2("Congratulations!",
					"Your attack has been completely reset!", c.talkingNpc,
					"Town Crier");
			c.nextChat = 0;
			break;
		case 250:
			sendNpcChat2("Congratulations!",
					"Your combat stats have been completely reset!",
					c.talkingNpc, "Town Crier");
			c.nextChat = 0;
			break;
		case 260:
			sendNpcChat2("Congratulations!",
					"Your prayer have been completely reset!", c.talkingNpc,
					"Town Crier");
			c.nextChat = 0;
			break;
		case 1337:
			sendOption2("PK'er Scoreboard", "Skiller Scoreboard");
			c.dialogueAction = 1337;
			break;

		case 80:
			sendStatement("Should I tele you ?");
			c.nextChat = 81;
			break;

		case 81:
			sendOption2("Yes get me out of this fucking hell hole!",
					"Hell no! I love it here, I'm nuts for these monkeys!");
			c.dialogueAction = 27;
			c.nextChat = 0;
			break;

		case 38:
			sendStatement("Congratulations, you open the chest and got a reward!");
			c.nextChat = 0;
			break;

		case 37:
			sendStatement("You need atleast 1 free inventory spaces! and a crystal key!");
			c.nextChat = 0;
			break;

		case 30:
			sendNpcChat4("Congratulations!",
					"You have killed 20 monkeys hope you learned something..",
					"would you like to escape?", "Do not break anymore rules!",
					c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 26;
			c.nextChat = 31;
			break;
		case 31:
			sendOption2("Yes get me out of this fucking hell hole!",
					"Hell no! I love it here, I'm nuts for these monkeys!");
			c.dialogueAction = 27;
			c.nextChat = 0;
			break;
		case 76:
			sendOption2("Castle Pk (14)", "Clan War Zone (34)");
			c.dialogueAction = 51;
			break;
		case 32:
			sendNpcChat4("You cannot Escape yet!", "You've killed "
					+ c.monkeyk0ed + " out of 20 monkeys!",
					"Come back when you have killed 20", "Kthxbai",
					c.talkingNpc, "Mosol Rei");
			c.dialogueAction = 30;
			c.nextChat = 0;
			break;
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 20:
			sendOption4("Information", "Black Jack", "Five", "Maybe later...");
			c.dialogueAction = 100;
			break;

		case 25:
			sendOption4("", "Black Jack", "Five", "");
			c.dialogueAction = 101;
			break;

		case 21:
			sendNpcChat4(
					"The way we play this game is simple. The way you win is",
					"You need to get a higher number than me and you win the",
					"500,000 coins. You need to bet 250,000 coins per round.",
					"If you get over 22 you bust and you lose.", c.talkingNpc,
					"~ Black Jack ~");
			c.nextChat = 22;
			break;

		case 22:
			sendNpcChat4(
					"",
					"If i get 22+ I bust and I lose. If you get 21 then you have black",
					"jack and you win double of what you bet.", "",
					c.talkingNpc, "~ Black Jack ~");
			c.nextChat = 0;
			break;

		case 23:
			sendNpcChat4(
					"This is my own game which I made. It's pretty simple",
					"and resembles poker but it's a lot different. The aim of this",
					"game is to get the same number like the random number",
					"You got 2 numbers if both hit the same you win.",
					c.talkingNpc, "~ Five ~");
			c.nextChat = 24;
			break;
		case 24:
			sendNpcChat4("",
					"To play this game you need to bet 1,000,000 coins. You",
					"can win a lot of good items but also lose a lot of cash.",
					"", c.talkingNpc, "~ Five ~");
			c.nextChat = 0;
			break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 45:
			sendNpcChat2("Since you haven't shown me a defender to",
					"prove your prowess as a warrior.", 4289, "Kamfreena");
			c.nextChat = 46;
			break;
		case 46:
			sendNpcChat3("I'll release some Cyclopes which might drop bronze",
					"defenders for you to start off with, unless you show me",
					"another. Have fun in there.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 47:
			sendNpcChat2("The cyclops will now drop:",
					"" + c.getWarriorsGuild().getCyclopsDrop126(c)
							+ " defenders.", 4289, "Kamfreena");
			c.nextChat = -1;
			break;
		case 2:
			sendOption2("Yea! I'm fearless!", "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat4(
					"Hello!",
					"My name is Kuradel and I am a master of the slayer skill.",
					"I can assign you a slayer task suitable to your combat level.",
					"Would you like a slayer task?", c.talkingNpc, "Kuradel");
			c.nextChat = 4;
			break;
		case 5:
			sendNpcChat4("Hello adventurer...",
					"My name is Kolodion, the master of this mage bank.",
					"Would you like to play a minigame in order ",
					"to be able to wear a powerful godcape?", c.talkingNpc,
					"Kolodion");
			c.nextChat = 6;
			break;
		case 6:
			sendNpcChat3("The way the game works is as follows...",
					"You will be teleported to the wilderness,",
					"You must kill a very powerful mage..", c.talkingNpc,
					"Kolodion");
			c.nextChat = 15;
			break;
		case 11:
			sendNpcChat4(
					"Hello!",
					"My name is Kuradel and I am a master of the slayer skill.",
					"I can assign you a slayer task suitable to your combat level.",
					"Would you like a slayer task?", c.talkingNpc, "Kolodion");
			c.nextChat = 12;
			break;
		case 12:
			sendOption2("Yes I would like a slayer task.",
					"No I would not like a slayer task.");
			c.dialogueAction = 5;
			break;
		case 13:
			sendNpcChat4(
					"Hello!",
					"My name is Kuradel and I am a master of the slayer skill.",
					"I see I have already assigned you a task to complete.",
					"Would you like me to give you an easier task?",
					c.talkingNpc, "Kuradel");
			c.nextChat = 166;
			break;
		case 1125:
			sendNpcChat4(
					"Hello and welcome!",
					"You must be the hero everyone",
					"has been talking about!",
					"We are so glad your here!",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 1126;
			break;
		case 1126:
			sendNpcChat4(
					"So what on earth could",
					"could posses such a brave and noble",
					"adventurer to come to these ancient lands",
					"without a death wish",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 1127;
			break;
		case 1127:
			sendNpcChat1(
					"oh! so you've come to learn about the divine ones eh?",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 1128;
			break;
		case 1128:
			sendNpcChat1(
					"Well then, allow me to start you off",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 1129;
			break;
		case 1129:
			sendNpcChat4(
					"First lets talk about the mother of all gods",
					"Ferris the Ancient!",
					"She is the eldest and wisest of the lands",
					"and gave birth to the rest of the gods.",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 11130;
			break;
		case 11130:
			sendNpcChat4(
					"Next lets talk about Zamorak",
					"Zamorak believes that chaos brings order",
					"and that through slaying all who defy him",
					"will bring you closer to the divine.",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 11131;
			break;
		case 11131:
			sendNpcChat4(
					"Now lets talk about Armadyl",
					"Armadyl is a bird style god who believes",
					"those who can not fly do not deserve to exist",
					"and seeks to destroy those without flight",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 11132;
			break;
		case 11132:
			sendNpcChat4(
					"That brings us to Bandos",
					"Bandos believes only warriors may survive",
					"and that through strength, comes command",
					"with command comes power to control",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 11133;
			break;
		case 11133:
			sendNpcChat4(
					"We must also talk about Nex",
					"Nex is the eldest of the children who",
					"wishes to destroy her younger siblings as",
					"they have upset the order she maintained for so many of ages.",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 11134;
			break;
		case 11134:
			sendNpcChat4(
					"Then there is Saradomin",
					"Saradomin is the god of wisdom and believes",
					"peace can only be obtained through harmony",
					"and that we can love one another and co-exist",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 11135;
			break;
		case 11135:
			sendNpcChat4(
					"Finally we have Guthix",
					"Guthix has been missing for many ages and it",
					"is believed that he sleeps in the heart of this planet",
					"giving life to the plants and nourishing man kind forever",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 11136;
			break;
		case 11136:
			sendNpcChat3(
					"Whichever god you choose will decide your on-going fate",
					"So I implore you to choose wisely, as you may",
					"only choose one...",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 11137;
			break;
		case 11137:
			sendNpcChat1(
					"So which will you choose?",
					c.talkingNpc, "Quest Guide");
			c.nextChat = 11138;
			break;
		case 11138: //more options to send to 1139, and names will open a boss point system in essence
			sendOption4(
					"Zamorak - God of Chaos",
					"Armadyl - God of Flight",
					"Bandos - God of War",
					"More options");// You would have had errors lol.
			c.dialogueAction = 11138;
			break;
		case 11139:
			sendOption3(
					"Nex - God of Ancients",
					"Saradomin - God of Wisdom",
					"Guthix - God of Balance");// This isn't done??
			c.dialogueAction = 11139;
			break;
		case 166:

			sendNpcChat3("If you choose an easier task, your current",
					"Slayer Task Streak will be reset to 0.", "Continue?",
					c.talkingNpc, "Kuradel");
			c.nextChat = 14;
			break;
		case 1414:
			sendNpcChat4("Ahhh! " + Misc.optimizeText(c.playerName) + "!",
					"You've proven yourself worthy enough to wear",
					"Our famous Godcapes! Simply step into the",
					"Sparkling pool and let the light guide you.",
					c.talkingNpc, "Kolodion");
			break;
		case 14:
			sendOption2("Yes I would like an easier task.",
					"No I would like to keep my task.");
			c.dialogueAction = 6;
			break;
		case 15:
			sendNpcChat3("You will loose items on death!",
					"You will also be unable to teleport.", "Continue?",
					c.talkingNpc, "Kolodion");
			c.dialogueAction = 7;
			c.nextChat = 1438;
			break;
		case 1438:
			sendOption2("Yes I would like to play!",
					"No, sounds too dangerous for me.");
			c.dialogueAction = 7;
			break;
		case 16:
			sendOption2("I would like to reset my barrows brothers.",
					"I would like to fix all my barrows");
			c.dialogueAction = 8;
			break;
		case 17:
			sendOption5("Air", "Mind", "Water", "Earth", "More");
			c.dialogueAction = 10;
			c.dialogueId = 17;
			c.teleAction = -1;
			break;
		case 18:
			sendOption5("Fire", "Body", "Cosmic", "Astral", "More");
			c.dialogueAction = 11;
			c.dialogueId = 18;
			c.teleAction = -1;
			break;
		case 19:
			sendOption5("Nature", "Law", "Death", "Blood", "More");
			c.dialogueAction = 12;
			c.dialogueId = 19;
			c.teleAction = -1;
			break;
		case 8591:
			sendNpcChat3("Start Nomads Requiem?",
					"All you need to do is kill me in a fight",
					"And gain a reward!", c.talkingNpc, "Nomads Requiem");
			c.nextChat = 8592;
			break;
		case 8592:
			sendOption2("HELL NO", "Yeh ima own you");
			c.dialogueAction = 8591;
			c.dialogueId = 8592;
			c.teleAction = -1;
			break;
		case 3663:
			sendNpcChat4("Yo, i saw this angry goblin",
					"Want me to show you where?",
					"He had a slayer helmet and a dwarf item",
					"I'm guessing he will be a tough challenge!", c.talkingNpc,
					"Hans");
			c.nextChat = 3664;
			break;
		case 3664:
			sendOption2("No thanks", "Yeh Sure i'm boored anyway");
			c.dialogueAction = 3664;
			c.dialogueId = 3664;
			c.teleAction = -1;
			break;
		case 70:
			sendNpcChat4("I have found a secret tunnel!",
					"I saw some Red Dragons!",
					"One of the dragon burned my leg, fucking cunt!",
					"Would you like me to bring you there?", c.talkingNpc,
					"Bonafido");
			c.nextChat = 71;
			break;
		case 71:
			sendOption2("HELL NO", "Red Dragons (43 Wildy!!)");
			c.dialogueAction = 13;
			c.dialogueId = 71;
			c.teleAction = -1;
			break;
		case 2525:
			c.getDH().sendNpcChat3("Please kill a one-tailed Pheasent",
					"And come talk to me", "After you've looted the Raw meat.",
					c.talkingNpc, "Freaky Forester");
			c.nextChat = 0;
			break;
		case 2526:
			c.getDH().sendNpcChat3("Please kill a two-tailed Pheasent",
					"And come talk to me", "After you've looted the Raw meat.",
					c.talkingNpc, "Freaky Forester");
			c.nextChat = 0;
			break;
		case 2527:
			c.getDH().sendNpcChat3("Please kill a three-tailed Pheasent",
					"And come talk to me", "After you've looted the Raw meat.",
					c.talkingNpc, "Freaky Forester");
			c.nextChat = 0;
			break;
		case 2528:
			c.getDH().sendNpcChat3("Please kill a four-tailed Pheasent",
					"And come talk to me", "After you've looted the Raw meat.",
					c.talkingNpc, "Freaky Forester");
			c.nextChat = 0;
			break;
		case 2529:
			c.getDH().sendNpcChat3("Please kill a five-tailed Pheasent",
					"And come talk to me", "After you've looted the Raw meat.",
					c.talkingNpc, "Freaky Forester");
			c.nextChat = 0;
			break;
		case 2530:
			c.getDH().sendNpcChat3("Well done!",
					"You must now cook it! Use it",
					"On the range to the north!", c.talkingNpc,
					"Freaky Forester");
			c.nextChat = 0;
			RandomEvents.pheasentId = 2140;
			break;
		case 2531:
			c.getDH().sendNpcChat3("Thanks so much!",
					"You may now leave through", "The portal to the east!",
					c.talkingNpc, "Freaky Forester");
			c.nextChat = 0;
			RandomEvents.pheasentId = 0;
			break;
		}
	}

	public void sendNpcChat1(String s, int ChatNpc, String name) {
		c.getPA().sendFrame200(4883, 591);
		c.getPA().sendFrame126(name, 4884);
		c.getPA().sendFrame126(s, 4885);
		c.getPA().sendFrame126("Click here to continue.", 4886);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4882);
	}

	public void sendNpcChat2(String s, String s1, int ChatNpc, String name) {
		c.getPA().sendFrame200(4888, 591);
		c.getPA().sendFrame126(name, 4889);
		c.getPA().sendFrame126(s, 4890);
		c.getPA().sendFrame126(s1, 4891);
		c.getPA().sendFrame75(ChatNpc, 4888);
		c.getPA().sendFrame164(4887);
	}

	public void sendNpcChat3(String s, String s1, String s2, int ChatNpc,
			String name) {
		c.getPA().sendFrame200(4894, 591);
		c.getPA().sendFrame126(name, 4895);
		c.getPA().sendFrame126(s, 4896);
		c.getPA().sendFrame126(s1, 4897);
		c.getPA().sendFrame126(s2, 4898);
		c.getPA().sendFrame75(ChatNpc, 4894);
		c.getPA().sendFrame164(4893);
	}

	public void sendNpcChat4(String s, String s1, String s2, String s3,
			int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 591);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}

	@SuppressWarnings("unused")
	private void sendOption(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2470);
		c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}

	/*
	 * Statements
	 */

	public void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}

	/*
	 * Npc Chatting
	 */

	public void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2470);
		c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126(s2, 2473);
		c.getPA().sendFrame164(2469);
	}

	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}

	/*
	 * Player Chating Back
	 */

	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	private void sendPlayerChat1(String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(c.playerName, 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}

	private void sendPlayerChat2(String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}

	private void sendPlayerChat3(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 591);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}

	@SuppressWarnings("unused")
	private void sendPlayerChat4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(987, 591);
		c.getPA().sendFrame126(c.playerName, 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}

	public void sendStartInfo(String text, String text1, String text2,
			String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}

	public void sendStatement(String s) { // 1 line click here to continue chat
											// box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}

	public void talk(int face, String line1, String line2, String line3,
			String line4, int npcID) {
		c.getPA().sendFrame200(4901, face);
		c.getPA().sendFrame126(
				c.getPA().GetNpcName(npcID).replaceAll("_", " "), 4902);
		c.getPA().sendFrame126("" + line1, 4903);
		c.getPA().sendFrame126("" + line2, 4904);
		c.getPA().sendFrame126("" + line3, 4905);
		c.getPA().sendFrame126("" + line4, 4906);
		c.getPA().sendFrame126("Click here to continue", 4907);
		c.getPA().sendFrame75(npcID, 4901);
		c.getPA().sendFrame164(4900);
	}
}
