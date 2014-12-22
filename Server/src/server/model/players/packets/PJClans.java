package server.model.players.packets;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import server.Server;
import server.world.Clan;

import com.google.gson.Gson;

/**
 * 
 * @authors PJNoMore
 * 
 */

public class PJClans {

	int saveCount = 0;

	public void initialize() {
		loadClans();
	}

	public void process() {
		if (saveCount == 30) {
			saveClans();
			saveCount = 0;
		}
		saveCount++;
	}

	public void saveClans() {
		for (int i = 0; i < Server.clanChat.clans.length; i++) {
			Clan saveClan = Server.clanChat.clans[i];
			if (saveClan != null) {
				saveClan(saveClan);
			}
		}
	}

	public void saveClan(Clan clan) {
		try (FileWriter file = new FileWriter("./Data/Clans/" + clan.name
				+ ".json")) {
			Gson gson = new Gson();
			String json = gson.toJson(clan);
			file.write(json);
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadClans() {
		File clanDir = new File("./Data/Clans/");
		File[] files = clanDir.listFiles();
		int numClans = files.length;
		int loaded = 0;
		for (int i = 0; i < numClans; i++) {
			File clanFile = files[i];
			try {
				Gson gson = new Gson();
				FileReader reader = new FileReader(clanFile);
				Clan clan = gson.fromJson(reader, Clan.class);
				Server.clanChat.createClan(clan.owner, clan.name,
						clan.password, clan.lootshare, clan.hasPassword,
						clan.friendsList);
				loaded++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Loaded " + loaded + " Clans");
	}

}