package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import server.model.players.Client;

/**
 * Connection Check Class
 * 
 * @author Ryan / Lmctruck30
 * 
 */

public class Connection {

	public static ArrayList<String> bannedIps = new ArrayList<String>();
	public static ArrayList<String> bannedNames = new ArrayList<String>();
	public static ArrayList<String> mutedIps = new ArrayList<String>();
	public static ArrayList<String> mutedNames = new ArrayList<String>();
	public static ArrayList<String> loginLimitExceeded = new ArrayList<String>();
	public static ArrayList<String> starterRecieved1 = new ArrayList<String>();
	public static ArrayList<String> starterRecieved2 = new ArrayList<String>();

	/**
	 * Adding Ban IP
	 **/
	public static void addIpToBanList(String IP) {
		bannedIps.add(IP);
	}

	/**
	 * Writes the IP into the text file - use ::ipban username
	 **/
	public static void addIpToFile(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					Config.LOG_PATH + "/bans/IpsBanned.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adding Name To List
	 */
	public static void addIpToLoginList(String IP) {
		loginLimitExceeded.add(IP);
	}

	public static void addIpToMuteFile(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					Config.LOG_PATH + "/bans/IpsMuted.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addIpToMuteList(String IP) {
		mutedIps.add(IP);
		addIpToMuteFile(IP);
	}

	public static void addIpToStarter1(String IP) {
		starterRecieved1.add(IP);
		addIpToStarterList1(IP);
	}

	public static void addIpToStarter2(String IP) {
		starterRecieved2.add(IP);
		addIpToStarterList2(IP);
	}

	public static void addIpToStarterList1(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					Config.LOG_PATH + "/starters/FirstStarterRecieved.txt",
					true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addIpToStarterList2(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					Config.LOG_PATH + "/starters/SecondStarterRecieved.txt",
					true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adding banned username
	 **/
	public static void addNameToBanList(String name) {
		bannedNames.add(name.toLowerCase());
	}

	/**
	 * Writes the username into the text file - when using the ::ban playername
	 * command
	 **/
	public static void addNameToFile(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					Config.LOG_PATH + "/bans/UsersBanned.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addNameToMuteList(String name) {
		mutedNames.add(name.toLowerCase());
		addUserToFile(name);
	}
	
	public static void addIPToIPBanFile(String IP) {
		bannedIps.add(IP.toLowerCase());
		addIpToFile(IP);
	}

	public static void addUserToFile(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					Config.LOG_PATH + "/bans/UsersMuted.txt", true));
			try {
				out.newLine();
				out.write(Name);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void appendStarters() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					Config.LOG_PATH + "/starters/FirstStarterRecieved.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					starterRecieved1.add(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void appendStarters2() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					Config.LOG_PATH + "/starters/SecondStarterRecieved.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					starterRecieved2.add(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads all the Ips from text file then adds them all to ban list
	 **/
	public static void banIps() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					Config.LOG_PATH + "/bans/IpsBanned.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					addIpToBanList(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads all usernames from text file then adds them all to the ban list
	 **/
	public static void banUsers() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					Config.LOG_PATH + "/bans/UsersBanned.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					addNameToBanList(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkLoginList(String IP) {
		loginLimitExceeded.add(IP);
		int num = 0;
		for (String ips : loginLimitExceeded) {
			if (IP.equals(ips)) {
				num++;
			}
		}
		return (num > Config.IPS_ALLOWED);
	}

	/**
	 * Clear Name List
	 */
	public static void clearLoginList() {
		loginLimitExceeded.clear();
	}

	public static void deleteFromFile(String file, String name) {
		try {
			BufferedReader r = new BufferedReader(new FileReader(file));
			ArrayList<String> contents = new ArrayList<String>();
			while (true) {
				String line = r.readLine();
				if (line == null) {
					break;
				} else {
					line = line.trim();
				}
				if (!line.equalsIgnoreCase(name)) {
					contents.add(line);
				}
			}
			r.close();
			BufferedWriter w = new BufferedWriter(new FileWriter(file));
			for (String line : contents) {
				w.write(line, 0, line.length());
				w.newLine();
			}
			w.flush();
			w.close();
		} catch (Exception e) {
		}
	}

	public static boolean hasRecieved1stStarter(String IP) {
		if (starterRecieved1.contains(IP)) {
			return true;
		}
		return false;
	}

	public static boolean hasRecieved2ndStarter(String IP) {
		if (starterRecieved2.contains(IP)) {
			return true;
		}
		return false;
	}

	/**
	 * Adds the banned usernames and ips from the text file to the ban list
	 **/
	public static void initialize() {
		banUsers();
		banIps();
		muteUsers();
		muteIps();
		appendStarters();
		appendStarters2();
	}

	/**
	 * Contains Ban IP
	 **/
	public static boolean isIpBanned(String IP) {
		if (bannedIps.contains(IP)) {
			return true;
		}
		return false;
	}

	public static boolean isIPMuted(String IP) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					Config.LOG_PATH + "/bans/IpsMuted.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					if (data.equalsIgnoreCase(IP)) {
						return true;
					}
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isMuted(Client c) {
		if (c.isMuted || isIPMuted(c.connectedFrom))
			return true;
		else
			return false;
	}

	/**
	 * Contains banned username
	 **/
	public static boolean isNamedBanned(String name) {
		if (bannedNames.contains(name.toLowerCase())) {
			return true;
		}
		return false;
	}
	public static void muteIps() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					Config.LOG_PATH + "/bans/IpsMuted.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					mutedIps.add(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void muteUsers() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					Config.LOG_PATH + "/bans/UsersMuted.txt"));
			String data = null;
			try {
				while ((data = in.readLine()) != null) {
					mutedNames.add(data);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removing Ban IP
	 **/
	public static void removeIpFromBanList(String IP) {
		bannedIps.remove(IP);
	}

	/**
	 * Remove Ip From List
	 */
	public static void removeIpFromLoginList(String IP) {
		loginLimitExceeded.remove(IP);
	}

	/**
	 * Removing banned username
	 **/
	public static boolean removeNameFromBanList(String name) {
		deleteFromFile(Config.LOG_PATH + "/bans/UsersBanned.txt", name);
		return bannedNames.remove(name.toLowerCase());
	}

	public static void removeNameFromMuteList(String name) {
		mutedNames.remove(name.toLowerCase());
		deleteFromFile(Config.LOG_PATH + "/bans/UsersMuted.txt", name);
	}

	public static void unIPMuteUser(String name) {
		mutedIps.remove(name);
		deleteFromFile(Config.LOG_PATH + "/bans/IpsMuted.txt", name);
	}

	public static void unMuteUser(String name) {
		mutedNames.remove(name);
		deleteFromFile(Config.LOG_PATH + "/bans/UsersMuted.txt", name);
	}
}