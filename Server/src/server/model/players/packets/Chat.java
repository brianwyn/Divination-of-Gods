package server.model.players.packets;

import server.Connection;
import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;

/**
 * Chat : Credits to Coder Alex account
 **/
public class Chat implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.setChatTextEffects(c.getInStream().readUnsignedByteS());
		c.setChatTextColor(c.getInStream().readUnsignedByteS());
		c.setChatTextSize((byte) (c.packetSize - 2));
		c.inStream.readBytes_reverseA(c.getChatText(), c.getChatTextSize(), 0);
		String[] stuffz = { "devious", "firepk", "ventrilica", "ventrili",
				"hade5", "hades5", "pvplegacy", "junglepk", "runeinsanit",
				"n3t", "n e t", "n et", "ne t", "C  0 m", "C 0 M", "C 0  M",
				"C  O  M", "runeinsanity", "runeinsanit", "runeinsan",
				"rune-insanity", "rune-insanit", ".c0", ".c0m",
				"legendsdomain", "createaforum", "vampirez", "-scape",
				"rswebclients", "pvplegacy", "junglepk", ".n3t", ".c0m", "c0m",
				"n3t", ".tk", ",net", ",runescape", "pvpmaster", "pvpmasters",
				"PvPMasters.", ",org", ",com", ",be", ",nl", ",info", "dspk",
				".info", ".org", ".tk", ".net", ".com", ".co.uk", ".be", ".nl",
				".dk", ".co.cz", ".co", ".us", ".biz", ".eu", ".de", ".cc",
				".i n f o", ".o r g", ".t k", ".n e t", ".c o m", ".c o . u k",
				".b e", ".n l", ".d k", ".c o . c z", ".c o", ".u s", ".b i z",
				".e u", ".d e", ".c c", "kandarin", "o r g", "www", "w w w" };
		String message = Misc.textUnpack(c.getChatText(), c.packetSize - 2)
				.toLowerCase();
		if (c.said >= 15000 && (c.playerRights != 3 && c.playerRights != 2)) {
			c.sendMessage("You have been IPMuted!");
			Connection.addNameToMuteList(c.playerName);
			Connection.addIpToMuteList(c.playerName);
		}
		for (int i = 0; i < stuffz.length; i++) {
			if (message.contains(stuffz[i]) && (c.playerRights != 3 && c.playerRights != 2)) {
				c.said++;
				c.sendMessage("You shouldn't be spamming this. A word was blocked in your sentence.");
				c.sendMessage("If you continue to send this message you will be automuted!!");
				return;
			}
		}
		if (!Connection.isMuted(c)) {
			c.setChatTextUpdateRequired(true);
			c.getPA().writeChatLog(message);
		} else if (Connection.isMuted(c)) {
			c.sendMessage("You are muted, no one can hear you.");
			return;
		}
	}
}