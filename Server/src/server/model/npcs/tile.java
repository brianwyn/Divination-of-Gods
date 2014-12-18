package server.model.npcs;

public class tile {
	// cost, heuristic, cost+heuristic
	public double G, H, F;
	public int x, y, hardness = 0;
	public tile parent;

	public tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Object t) {
		tile ta = (tile) t;
		if (this.x == ta.x && this.y == ta.y)
			return true;
		return false;
	}

	public tile getParent() {
		return parent;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setF() {
		this.F = G + H;
	}

	public void setG(tile t) {
		this.G = t.G + 1;
	}

	public void setHeuristic(double h) {
		this.H = h;
	}

	public void setParent(tile p) {
		this.parent = p;
	}

	public String toString() {
		return "X: " + x + ". Y: " + y;
	}
}
