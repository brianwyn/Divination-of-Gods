package server.world.clip;

public class CachedObject {

	private int objectId;
	private int objectType;
	private int objectOrientation;

	public CachedObject(int objectId, int objectType, int objectOrientation) {
		this.objectId = objectId;
		this.objectType = objectType;
		this.objectOrientation = objectOrientation;
	}

	public void changeOrientation(int o) {
		objectOrientation = o;
	}

	public int getId() {
		return objectId;
	}

	public int getOrientation() {
		return objectOrientation;
	}

	public int getType() {
		return objectType;
	}

}