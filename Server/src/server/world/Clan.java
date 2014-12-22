package server.world;

/**
 * @author Sanity
 */

public class Clan {

	public Clan(String owner, String name, String password, boolean lootshare,
			boolean hasPassword, long[] friendsList) {
		this.owner = owner;
		this.name = name;
		this.password = password;
		this.lootshare = lootshare;
		this.hasPassword = hasPassword;
		this.friendsList = friendsList;
	}

	public int[] members = new int[50];
	public int[] mutedMembers = new int[50];
	public long[] friendsList = new long[200];
	public String name;
	public String owner;
	public String password;
	public boolean lootshare;
	public boolean hasPassword;
}