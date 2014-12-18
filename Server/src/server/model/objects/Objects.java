package server.model.objects;

public class Objects {

	public static String belongsTo;
	public static int objectId;
	public static int objectX;
	public static int objectY;
	public int objectHeight;
	public int objectFace;
	public int objectType;
	public int objectTicks;

	public Objects(int id, int x, int y, int height, int face, int type,
			int ticks) {
		Objects.objectId = id;
		Objects.objectX = x;
		Objects.objectY = y;
		this.objectHeight = height;
		this.objectFace = face;
		this.objectType = type;
		this.objectTicks = ticks;
	}

	public int getObjectFace() {
		return this.objectFace;
	}

	public int getObjectHeight() {
		return this.objectHeight;
	}

	public int getObjectId() {
		return Objects.objectId;
	}

	public int getObjectType() {
		return this.objectType;
	}

	public int getObjectX() {
		return Objects.objectX;
	}

	public int getObjectY() {
		return Objects.objectY;
	}

}