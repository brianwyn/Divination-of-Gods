package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.content.ImpSpawn;
import server.model.players.content.skills.impl.Cons;
import server.model.players.content.skills.impl.ConstructionObjects.Bank;
import server.model.players.content.skills.impl.ConstructionObjects.Bookcases;
import server.model.players.content.skills.impl.ConstructionObjects.Chairs;
import server.model.players.content.skills.impl.ConstructionObjects.CrystalBall;
import server.model.players.content.skills.impl.ConstructionObjects.Fireplaces;
import server.model.players.content.skills.impl.ConstructionObjects.Globes;
import server.model.players.content.skills.impl.ConstructionObjects.Lecterns;
import server.model.players.content.skills.impl.ConstructionObjects.Plants;
import server.model.players.content.skills.impl.ConstructionObjects.Rugs;
import server.model.players.content.skills.impl.ConstructionObjects.Telescopes;

/**
 * Author: Gabbe, removes construction objects, pushes through puro puro wall
 * packet Note: Object IDs for the objects ARE NOT THEIR REAL IDS
 **/
public class BuildingAndPassing implements PacketType {
	public static int getArrayID(int obj) {
		switch (obj) {
		case 15544:
			return 4;
		case 15538:
		case 13709:
			return 0;
		case 15540:
		case 13710:
			return 1;
		case 15539:
		case 13711:
			return 2;
		case 15415:
			return 3;
		}
		return -1;
	}

	// Faces:
	// 0 = south
	// 1 = west
	// 2 = north
	// 3 = east
	public static int getFace(int obj) {
		switch (obj) {
		case 15538:
		case 15539:
		case 15540:
		case 13709:
		case 13710:
		case 13582:
		case 13711:
			return 2;
		}
		return -1;
	}

	public static int getObjectID(int obj) {
		switch (obj) {
		case 15544:
			return 13598;

		case 13711:
		case 13583:
			return 15411;
		case 15539:
			return 13583;
		case 15412:
		case 15540:
			return 13582;
		case 13710:
			return 15412;
		case 15538:
			return 13581;
		case 13581:
		case 13709:
			return 15410;
		case 15415:
			return 13588;

		}
		return -1;
	}

	public static String getObjectName(int obj) {
		switch (obj) {
		case 15538:
			return "Chair";
		case 15540:
			return "Oak chair";
		case 15539:
			return "Teak chair";
		case 15544:
			return "Oak bookcase";
		}

		return "null";
	}

	public static int getObjectX(int obj, int j) {
		switch (obj) {
		case 15544:
		case 13598:
			return 1856;
		case 13709:
		case 15538:
			return 1858;
		case 15540:
		case 13710:
			return 1860;
		case 15539:
			return 1861;
		case 13711:
			return 1861;
		}

		return -1;
	}

	public static int getObjectY(int obj, int j) {
		switch (obj) {
		case 15544:
		case 13598:
			return 5113;
		case 15539:
			return 5116;
		case 13709:
		case 15538:
			return 5116;
		case 15540:
		case 13710:
			return 5115;
		case 13711:
			return 5116;
		}

		return -1;
	}

	public static int getOldID(int obj) {
		switch (obj) {
		case 13709:
			return 15538;
		case 13710:
			return 15541;
		case 15541:
			return 13709;
		case 15538:
			return 13710;

		case 15413:
			return 15540;
		case 15540:
			return 13582;
		}
		return -1;
	}

	public static void removeThing(Client c, int objectId) {
		if (objectId == 13581) {
			if (c.COChair > 0) {
				c.COChair = 0;
				Cons.spawnChairv1(c);
				c.getPA().closeAllWindows();
				return;
			} else {
				c.sendMessage("You don't even have a chair!");
				c.getPA().closeAllWindows();
				return;
			}
		}

		if (objectId == 13411) {
			if (c.COtreee > 0) {
				c.COtreee = 0;
				Cons.SpawnDaTree(c);
				c.getPA().closeAllWindows();
				return;
			} else {
				c.sendMessage("You don't even have a tree!");
				c.getPA().closeAllWindows();
				return;
			}
		}
		if (objectId == 13151) {
			if (c.CObeddy > 0) {
				c.CObeddy = 0;
				Cons.SpawnDaBed(c);
				c.getPA().closeAllWindows();
				return;
			} else {
				c.sendMessage("You don't even have a bed!");
				c.getPA().closeAllWindows();
				return;
			}
		}
		if (objectId == 13191) {
			if (c.COaltar > 0) {
				c.COaltar = 0;
				Cons.SpawnDaAltar(c);
				c.getPA().closeAllWindows();
				return;
			} else {
				c.sendMessage("You don't even have a prayer-altar!");
				c.getPA().closeAllWindows();
				return;
			}
		}
		if (objectId == 13648) {
			if (c.COLectt > 0) {
				c.COLectt = 0;
				Cons.SpawnLectaNiggas(c);
				c.getPA().closeAllWindows();
				return;
			} else {
				c.sendMessage("You don't even have any burners!");
				c.getPA().closeAllWindows();
				return;
			}
		}
		if (objectId == 13641) {
			if (c.COtelee > 0) {
				c.COtelee = 0;
				Cons.SpawnTeleporter(c);
				c.getPA().closeAllWindows();
				return;
			} else {
				c.sendMessage("You don't even have a teleporter!");
				c.getPA().closeAllWindows();
				return;
			}
		}
		if (objectId == 13660) {
			if (c.COcryst > 0) {
				c.COcryst = 0;
				Cons.SpawnCrystal(c);
				c.getPA().closeAllWindows();
				return;
			} else {
				c.sendMessage("You don't even have a crystal!");
				c.getPA().closeAllWindows();
				return;
			}
		}
		if (objectId == 3193) {
			if (c.COchest > 0) {
				c.COchest = 0;
				Cons.SpawnDaMuneyNiggaChest(c);
				c.getPA().closeAllWindows();
				return;
			} else {
				c.sendMessage("You don't even have a bankchest!");
				c.getPA().closeAllWindows();
				return;
			}
		}
	}

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		if (c.getInStream() != null)
			c.objectId = c.getInStream().readUnsignedWord();
		c.objectX = c.getInStream().readSignedWordBigEndianA();
		c.objectY = c.getInStream().readUnsignedWordA();
		if (c != null) {

			if (c.inConstruction()) {
				Chairs.init(c);
				Rugs.init(c);
				CrystalBall.init(c);
				Bank.init(c);
				Plants.init(c);
				Globes.init(c);
				Telescopes.init(c);
				Lecterns.init(c);
				Fireplaces.init(c);
				Bookcases.init(c);
			}

			ImpSpawn.passWall(c);
		}

	}
}
