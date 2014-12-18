package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.content.LoyaltyHandler;
import server.model.players.content.SlayerRing;
import server.model.players.content.skills.RuneCraft;
import server.model.players.content.skills.impl.Binding;
import server.model.players.content.skills.impl.ImpLooting;
import server.util.Misc;

/**
 * Item Click 2 Or Alternative Item Option 1
 * 
 * @author Ryan / Lmctruck30
 * 
 *         Proper Streams
 */

public class ItemClick2 implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readSignedWordA();

		if (!c.getItems().playerHasItem(itemId, 1))
			return;
		if (c.inDung) {
			if (Binding.isBindable(c, itemId)) {
				c.getBind();
				Binding.bindItem(c, itemId);
			}
		}
		switch (itemId) {
		case 5733:
			if (c.playerRights == 3) {
				c.getPA().handleExtra(itemId);
			}
			break;
		case 686:
			if (c.playerRights == 2) {
				c.getPA().handleExtra(itemId);
				c.playerLevel[3] += 99;
				c.getPA().refreshSkill(3);
				c.sendMessage("You add 99 onto your current HP.");
			}
			break;
		case 6:
			c.getCannon().setUpCannon();
			break;
		case 18349:
			c.sendMessage("Your Chaotic rapier has " + c.chaoticCharges[0]
					+ " charges left.");
			break;
		case 18351:
			c.sendMessage("Your Chaotic longsword has " + c.chaoticCharges[1]
					+ " charges left.");
			break;
		case 18353:
			c.sendMessage("Your Chaotic maul has " + c.chaoticCharges[2]
					+ " charges left.");
			break;

		case 4804:
			LoyaltyHandler.handleTimeRemaining(c);
			break;
		case 4805:
			LoyaltyHandler.handleTimeRemaining2(c);
			break;
		case 19959:
			c.spinsLe += 1;
			break;
		case 19960:
			c.spinsLe += 2;
			break;
		case 19961:
			c.spinsLe += 3;
			break;
		case 19962:
			c.spinsLe += 5;
			break;
		case 19963:
			c.spinsLe += 10;
			break;
		case 1438:// Air Talisman
			RuneCraft.locate(c, 2985, 3292);
			break;
		case 1440:// Earth Talisman
			RuneCraft.locate(c, 3306, 3474);
			break;
		case 1442:// Fire Talisman
			RuneCraft.locate(c, 3313, 3255);
			break;
		case 1444:// Water Talisman
			RuneCraft.locate(c, 3185, 3165);
			break;
		case 1446:// Body Talisman
			RuneCraft.locate(c, 3053, 3445);
			break;
		case 1448:// Mind Talisman
			RuneCraft.locate(c, 2982, 3514);
			break;
		case 11283:
		case 11284:
		case 11285:
			c.sendMessage("Your shield has " + c.dfsCount + " charges.");
			break;
		case 13281:
			SlayerRing.handleKillsLeft(c);
			break;
		case 13282:
			SlayerRing.handleKillsLeft(c);
			break;
		case 13283:
			SlayerRing.handleKillsLeft(c);
			break;
		case 13284:
			SlayerRing.handleKillsLeft(c);
			break;
		case 13285:
			SlayerRing.handleKillsLeft(c);
			break;
		case 13286:
			SlayerRing.handleKillsLeft(c);
			break;
		case 13287:
			SlayerRing.handleKillsLeft(c);
			break;
		case 13288:
			SlayerRing.handleKillsLeft(c);
			break;
		case 15098:
			if (c.isDonator != 5) {
				c.sendMessage("You need have dice rank to dice!");
				return;
			}
			c.useDice(itemId, false);
			break;
		case 15100:
			if (c.isDonator != 5) {
				c.sendMessage("You need have dice rank to dice!");
				return;
			}
			c.useDice(itemId, false);
			break;
		case 15707:
		case 18817:
		case 18823:
		case 18821:
			c.handlePartyForm();
			break;

		case 11694:

			c.sendMessage("Dismantling has been disabled due to duping");
			break;
		case 11696:
			c.sendMessage("Dismantling has been disabled due to duping");
			break;
		case 11698:
			c.sendMessage("Dismantling has been disabled due to duping");
			break;
		case 11700:
			c.sendMessage("Dismantling has been disabled due to duping");
			break;
		case 11238:
		case 11240:
		case 11242:
		case 11244:
			ImpLooting.lootLowRewards(c, itemId);
			break;
		case 11254:
		case 11252:
		case 11248:
		case 11246:
			ImpLooting.lootMediumRewards(c, itemId);
			break;
		case 11250:
			ImpLooting.lootSeeds(c, itemId);
			break;
		case 11256:
			ImpLooting.lootDragonRewards(c, itemId);
			break;
		case 15517:
			ImpLooting.lootKinglyRewards(c, itemId);
			break;
		default:
			if (c.playerRights == 3)
				Misc.println(c.playerName + " - Item2dOption: " + itemId);
			break;
		}

	}

}
