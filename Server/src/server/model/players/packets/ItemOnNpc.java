package server.model.players.packets;

import server.Server;
import server.core.event.CycleEvent;
import server.core.event.CycleEventContainer;
import server.core.event.CycleEventHandler;
import server.model.items.UseItem;
import server.model.npcs.NPC;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.PacketType;

public class ItemOnNpc implements PacketType {
	public int tick = 0;

	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		final int itemId = c.getInStream().readSignedWordA();
		final int i = c.getInStream().readSignedWordA();
		int slot = c.getInStream().readSignedWordBigEndian();
		final int npcId = NPCHandler.npcs[i].npcType;

		if (c != null) {

			if (itemId == 7684) {
				CycleEventHandler.getSingleton().addEvent(this,
						new CycleEvent() {
							@Override
							public void execute(CycleEventContainer container) {
								if (!c.getItems().playerHasItem(7684)) {
									c.sendMessage("You do not have a deathtouched dart in your inventory.");
									container.stop();
									return;
								}
								NPC n = NPCHandler.npcs[i];
								if (n == null) {
									c.sendMessage("You cannot use your dart on this NPC.");
									container.stop();
								}
								/*
								 * if(n.npcType == c.hasFollower) {
								 * c.sendMessage
								 * ("You cannot use this on your familiar!");
								 * container.stop(); return; }
								 */
								if (n.npcType == npcId) {
									if (n.MaxHP == 0) {
										c.npcIndex = 0;
										c.sendMessage("You cannot use your dart on this NPC.");
										container.stop();
										return;
									}
									System.out.println(n.npcType);
									// Summon npcs/pc npcs etc. I'm to lazy to
									// get that array containing summon
									// familiars ids
									if (n.npcType >= 6000 && n.npcType <= 10000
											&& n.npcType != 8133) {
										c.sendMessage("The Deathtouched dart does not work on this NPC.");
										container.stop();
										return;
									}
									c.startAnimation(725);
									c.gfx0(1207);
									n.gfx0(2128);
									n.hitIcon = 2; // mage dmg
									n.handleHitMask(n.MaxHP, n.MaxHP);

									NPCHandler.useDart(c, n, n.npcType, n.absX,
											n.absY);
									c.getItems().deleteItem(7684, 1);
									String name = Server.npcHandler
											.getNpcListName(npcId);
									c.sendMessage("You've used your deathtouched dart to slay "
											+ name.replaceAll("_", " ") + ".");

									container.stop();
								}
							}

							@Override
							public void stop() {
								tick = 0;
							}
						}, 1);
			}

			UseItem.ItemonNpc(c, itemId, npcId, slot);

		}
	}

}