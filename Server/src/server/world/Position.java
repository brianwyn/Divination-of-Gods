package server.world;

import server.util.Misc;

/**
 * Represents any position point in the rs2 world
 *
 * @author lare96
 */
public class Position {

	/**
	 * Fields that repesent the x, y, and z coordinates
	 */
	private int x, y, z;

	/**
	 * Constructs a new Position with the specified coordinates.
	 *
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 * @param z
	 *            the Z coordinate
	 */
	public Position(int x, int y, int z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	/**
	 * Constructs a new Position with the specified coordinates. and the height
	 * as 0
	 *
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 * @param z
	 *            the Z coordinate
	 */
	public Position(int x, int y) {
		setX(x);
		setY(y);
		setZ(0);
	}

	/**
	 * Sets the X coordinate.
	 *
	 * @param x
	 *            the X coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the X coordinate.
	 *
	 * @return the X coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the Y coordinate.
	 *
	 * @param y
	 *            the Y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the Y coordinate.
	 *
	 * @return the Y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the Z coordinate.
	 *
	 * @param z
	 *            the Z coordinate
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * Gets the Z coordinate.
	 *
	 * @return the Z coordinate.
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Checks if the position is within a another position
	 */
	public boolean withinDistance(Position position, int distance) {
		if (this.getZ() != position.getZ())
			return false;

		return Math.abs(position.getX() - this.getX()) <= distance
				&& Math.abs(position.getY() - this.getY()) <= distance;
	}

	/**
	 * Checks if the position is viewable from another position
	 */
	public boolean isViewableFrom(Position position) {
		if (this.getZ() != position.getZ())
			return false;

		return Math.abs(position.getX() - this.getX()) <= 14
				&& Math.abs(position.getY() - this.getY()) <= 14;
	}

	/**
	 * Checks if this position is equal to another position
	 */
	@Override
	public boolean equals(Object anObject) {
		if (!(anObject instanceof Position)) {
			return false;
		}

		Position p = (Position) anObject;

		if (p == this) {
			return true;
		}

		if (p.getX() == this.getX() && p.getY() == this.getY()
				&& p.getZ() == this.getZ()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the current position in a string literal
	 */
	@Override
	public String toString() {
		return "[x: " + this.getX() + " || y: " + this.getY() + " || z "
				+ this.getZ() + "]";
	}

	/**
	 * Returns a duplicate position of the instance calling this method
	 */
	@Override
	public Position clone() {
		return new Position(x, y, z);
	}

	public Position randomX(int min, int max) {
		this.setX(this.getX() + Misc.random(min, max));
		return this;
	}

	public Position randomY(int min, int max) {
		this.setY(this.getY() + Misc.random(min, max));
		return this;
	}

	/**
	 * Returns duplicate of position with modified x value
	 * 
	 * @param xMod
	 *            the amount to add
	 * @return position with x modified
	 */
	public Position modX(int xMod) {
		this.setX(x + xMod);
		return this;
	}

	/**
	 * Returns duplicate of position with modified y value
	 * 
	 * @param yMod
	 *            the amount to add
	 * @return position with y modified
	 */
	public Position modY(int yMod) {
		this.setY(y + yMod);
		return this;
	}

	/**
	 * Returns duplicate of position with modified z value
	 * 
	 * @param zMod
	 *            the amount to add
	 * @return position with z modified
	 */
	public Position modZ(int zMod) {
		this.setZ(z + zMod);
		return this;
	}

	/**
	 * Returns duplicate of position with new x value
	 * 
	 * @param newX
	 *            the amount to add
	 * @return position with the new x
	 */
	public Position newX(int newX) {
		this.setX(newX);
		return this;
	}

	/**
	 * Returns duplicate of position with new y value
	 * 
	 * @param newY
	 *            the amount to add
	 * @return position with the new y
	 */
	public Position newY(int newY) {
		this.setY(newY);
		return this;
	}

	/**
	 * Returns duplicate of position with new z value
	 * 
	 * @param newZ
	 *            the amount to add
	 * @return position with the new z
	 */
	public Position newZ(int newZ) {
		this.setZ(newZ);
		return this;
	}

}