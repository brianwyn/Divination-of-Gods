package server.core.event.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import server.model.players.PlayerHandler;

public class PlayersOnline extends Task {

	public static void read() {
		try {
			URL url = new URL("http://www.URLLINK.COM");
			URLConnection urlConnection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			String info;
			while ((info = in.readLine()) != null)
				System.out.println(info);
			in.close();
		} catch (Exception e) {
		}
	}

	private final String LOCATION = "./";

	public PlayersOnline(int cycles) {
		super(cycles);
	}

	protected void execute() {
		write("There are currently " + PlayerHandler.getPlayerCount()
				+ " players online.");
	}

	public void write(String data) {
		File file = new File(LOCATION + "players.txt");
		if (file.exists())
			file.delete();
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(data);
			bw.flush();
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (final IOException ioe2) {
				}
			}
		}
	}

}