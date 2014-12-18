package server.model.players;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import server.model.npcs.tile;
import server.world.clip.region.Region;

public class PathFinder {

	private static final PathFinder pathFinder = new PathFinder();

	public static boolean canMove(int x, int y, int height, int moveTypeX,
			int moveTypeY) {
		if (height > 3)
			height = 0;
		// Region r = region;//West
		// East
		if (moveTypeX == 1 && moveTypeY == 0)// moveTypeX == -1 && moveTypeY ==
												// 0)
			return (Region.getClipping(x, y, height) & 0x1280108) == 0
					&& (Region.getClipping(x + 1, y, height) & 0x1280180) == 0;
		else
		// West
		if (moveTypeX == -1 && moveTypeY == 0)// moveTypeX == 1 && moveTypeY ==
												// 0)
			return (Region.getClipping(x, y, height) & 0x1280180) == 0
					&& (Region.getClipping(x - 1, y, height) & 0x1280108) == 0;
		else
		// North
		if (moveTypeX == 0 && moveTypeY == 1)// moveTypeX == 0 && moveTypeY ==
												// -1)
			return (Region.getClipping(x, y, height) & 0x1280102) == 0
					&& (Region.getClipping(x, y + 1, height) & 0x1280120) == 0;
		else
		// South
		if (moveTypeX == 0 && moveTypeY == -1)// moveTypeX == 0 && moveTypeY ==
												// 1)
			return (Region.getClipping(x, y, height) & 0x1280120) == 0
					&& (Region.getClipping(x, y - 1, height) & 0x1280102) == 0;
		else
		// NorthEast
		if (moveTypeX == 1 && moveTypeY == 1)
			return (Region.getClipping(x, y, height) & 0x1280102) == 0// Check
																		// if
																		// can
																		// go
																		// north.
					&& (Region.getClipping(x, y + 1, height) & 0x1280120) == 0

					&& (Region.getClipping(x, y, height) & 0x1280108) == 0// Check
																			// if
																			// can
																			// go
																			// East
					&& (Region.getClipping(x + 1, y, height) & 0x1280180) == 0

					&& (Region.getClipping(x, y + 1, height) & 0x1280108) == 0// Check
																				// if
																				// can
																				// go
																				// East
																				// from
																				// North
					&& (Region.getClipping(x + 1, y + 1, height) & 0x1280180) == 0

					&& (Region.getClipping(x + 1, y, height) & 0x1280102) == 0// Check
																				// if
																				// can
																				// go
																				// North
																				// from
																				// East
					&& (Region.getClipping(x + 1, y + 1, height) & 0x1280120) == 0;
		else
		// NorthWest
		if (moveTypeX == -1 && moveTypeY == 1)
			return (Region.getClipping(x, y, height) & 0x1280102) == 0// Going
																		// North
					&& (Region.getClipping(x, y + 1, height) & 0x1280120) == 0

					&& (Region.getClipping(x, y, height) & 0x1280180) == 0// Going
																			// West
					&& (Region.getClipping(x - 1, y, height) & 0x1280108) == 0

					&& (Region.getClipping(x, y + 1, height) & 0x1280180) == 0// Going
																				// West
																				// from
																				// North
					&& (Region.getClipping(x - 1, y + 1, height) & 0x1280108) == 0

					&& (Region.getClipping(x - 1, y, height) & 0x1280102) == 0// Going
																				// West
					&& (Region.getClipping(x - 1, y + 1, height) & 0x1280120) == 0;
		else
		// SouthEast
		if (moveTypeX == 1 && moveTypeY == -1)
			return (Region.getClipping(x, y, height) & 0x1280120) == 0// Going
																		// South
					&& (Region.getClipping(x, y - 1, height) & 0x1280102) == 0

					&& (Region.getClipping(x, y, height) & 0x1280108) == 0// Going
																			// East
					&& (Region.getClipping(x + 1, y, height) & 0x1280180) == 0

					&& (Region.getClipping(x, y - 1, height) & 0x1280108) == 0// Going
																				// East
																				// from
																				// South
					&& (Region.getClipping(x + 1, y - 1, height) & 0x1280180) == 0

					&& (Region.getClipping(x + 1, y, height) & 0x1280120) == 0// Going
																				// East
					&& (Region.getClipping(x + 1, y - 1, height) & 0x1280102) == 0;
		else
		// SouthWest
		if (moveTypeX == -1 && moveTypeY == -1)
			return (Region.getClipping(x, y, height) & 0x1280120) == 0// Going
																		// North
					&& (Region.getClipping(x, y - 1, height) & 0x1280102) == 0

					&& (Region.getClipping(x, y, height) & 0x1280180) == 0// Going
																			// West
					&& (Region.getClipping(x - 1, y, height) & 0x1280108) == 0

					&& (Region.getClipping(x, y - 1, height) & 0x1280180) == 0// Going
																				// West
																				// from
																				// North
					&& (Region.getClipping(x - 1, y - 1, height) & 0x1280108) == 0

					&& (Region.getClipping(x - 1, y, height) & 0x1280120) == 0// Going
																				// West
					&& (Region.getClipping(x - 1, y - 1, height) & 0x1280102) == 0;
		return false;
	}

	private static tile getLowestF(ArrayList<tile> open) {
		tile lowest = null;
		double lowestF = Double.MAX_VALUE;
		for (tile t : open) {
			if (t.F < lowestF) {
				lowest = t;
				lowestF = t.F;
			}
		}
		return lowest;
	} // forget this npc pathfinder code lol

	private static ArrayList<tile> getNeighs(tile curPoint, int height,
			ArrayList<tile> closed) {
		ArrayList<tile> neighs = new ArrayList<tile>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0)
					continue;
				neighs.add(new tile(curPoint.x + i, curPoint.y + j));
			}
		}
		return neighs;
	}

	public static Point getNextStep(int x, int y, int height, int destX,
			int destY, Point lastStep) {
		int bestX = 0, bestY = 0;
		double bestHeuristic = 100;
		for (int i = -1; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (i == j)
					continue;
				if (!canMove(x, y, height, i, j)
						|| (lastStep.getX() == x + i && lastStep.getY() == y
								+ j))
					continue;
				int dx = Math.abs(x - destX + i);
				int dy = Math.abs(y + j - destY + j);
				double curHeur = Math.sqrt(dx * dx + dy * dy);
				if (curHeur < bestHeuristic) {
					bestHeuristic = curHeur;
					bestX = i;
					bestY = j;
				}

			}
		}
		return new Point(bestX, bestY);
	}

	public static ArrayList<tile> getPath(int x, int y, int height, int destX,
			int destY) {
		// System.out.println("starting from "+x+":"+y);
		ArrayList<tile> path = new ArrayList<tile>();
		ArrayList<tile> open = new ArrayList<tile>();
		ArrayList<tile> closed = new ArrayList<tile>();
		open.add(new tile(x, y));
		tile curTile = null;
		int trys = 0;
		while (trys++ < 1000) {
			curTile = getLowestF(open);
			// System.out.println(curTile+" is current tile and dest is "+destX+":"+destY);
			if (curTile == null || (curTile.x == destX && curTile.y == destY)) {
				break;
			} else {
				open.remove(curTile);
				closed.add(curTile);
				ArrayList<tile> neighs = getNeighs(curTile, height, closed);
				for (tile ns : neighs) {
					if (!open.contains(ns)
							&& !closed.contains(ns)
							&& canMove(curTile.x, curTile.y, height, ns.x
									- curTile.x, ns.y - curTile.y)) {
						open.add(ns);
						ns.setG(curTile);
						ns.setHeuristic(Math.sqrt(Math.pow((ns.x - destX), 2)
								+ Math.pow((ns.y - destY), 2)));
						ns.setF();
						ns.setParent(curTile);
					} else if (!closed.contains(ns)) {
						closed.add(ns);
					}
				}
			}
		}
		/*
		 * if(trys < 1000 && curTile != null)
		 * System.out.println("Found a path :3"); else{
		 * System.out.println("didnt find a path.."); return null; }
		 */
		if (trys > 1000 && curTile == null)
			return null;
		while (curTile != null) {
			if (curTile.parent != null) {
				path.add(new tile(curTile.x - curTile.parent.x, curTile.y
						- curTile.parent.y));
			}
			curTile = curTile.parent;
		}
		Collections.reverse(path);
		return path;
	}// npc path finding, dont worry, u dont need this yet, i can all this later
		// if u want it for bosses etc. i add rs one for now :) as u wanted

	public static PathFinder getPathFinder() {
		return pathFinder;
	}

	public PathFinder() {
	}

	public void findRoute(Client c, int destX, int destY, boolean moveNear,
			int xLength, int yLength) {
		if (destX == c.getLocalX() && destY == c.getLocalY() && !moveNear) {
			// c.sendMessage("ERROR!");
			return;
		}
		destX = destX - 8 * c.getMapRegionX();
		destY = destY - 8 * c.getMapRegionY();
		int[][] via = new int[104][104];
		int[][] cost = new int[104][104];
		LinkedList<Integer> tileQueueX = new LinkedList<Integer>();
		LinkedList<Integer> tileQueueY = new LinkedList<Integer>();
		for (int xx = 0; xx < 104; xx++) {
			for (int yy = 0; yy < 104; yy++) {
				cost[xx][yy] = 99999999;
			}
		}
		int curX = c.getLocalX();
		int curY = c.getLocalY();
		via[curX][curY] = 99;
		cost[curX][curY] = 0;
		@SuppressWarnings("unused")
		int head = 0;
		int tail = 0;
		tileQueueX.add(curX);
		tileQueueY.add(curY);
		boolean foundPath = false;
		int pathLength = 4000;
		while (tail != tileQueueX.size() && tileQueueX.size() < pathLength) {
			curX = tileQueueX.get(tail);
			curY = tileQueueY.get(tail);
			int curAbsX = c.getMapRegionX() * 8 + curX;
			int curAbsY = c.getMapRegionY() * 8 + curY;
			if (curX == destX && curY == destY) {
				foundPath = true;
				break;
			}
			tail = (tail + 1) % pathLength;
			int thisCost = cost[curX][curY] + 1;
			if (curY > 0
					&& via[curX][curY - 1] == 0
					&& (Region.getClipping(curAbsX, curAbsY - 1, c.heightLevel) & 0x1280102) == 0) {
				tileQueueX.add(curX);
				tileQueueY.add(curY - 1);
				via[curX][curY - 1] = 1;
				cost[curX][curY - 1] = thisCost;
			}
			if (curX > 0
					&& via[curX - 1][curY] == 0
					&& (Region.getClipping(curAbsX - 1, curAbsY, c.heightLevel) & 0x1280108) == 0) {
				tileQueueX.add(curX - 1);
				tileQueueY.add(curY);
				via[curX - 1][curY] = 2;
				cost[curX - 1][curY] = thisCost;
			}
			if (curY < 104 - 1
					&& via[curX][curY + 1] == 0
					&& (Region.getClipping(curAbsX, curAbsY + 1, c.heightLevel) & 0x1280120) == 0) {
				tileQueueX.add(curX);
				tileQueueY.add(curY + 1);
				via[curX][curY + 1] = 4;
				cost[curX][curY + 1] = thisCost;
			}
			if (curX < 104 - 1
					&& via[curX + 1][curY] == 0
					&& (Region.getClipping(curAbsX + 1, curAbsY, c.heightLevel) & 0x1280180) == 0) {
				tileQueueX.add(curX + 1);
				tileQueueY.add(curY);
				via[curX + 1][curY] = 8;
				cost[curX + 1][curY] = thisCost;
			}
			if (curX > 0
					&& curY > 0
					&& via[curX - 1][curY - 1] == 0
					&& (Region.getClipping(curAbsX - 1, curAbsY - 1,
							c.heightLevel) & 0x128010e) == 0
					&& (Region.getClipping(curAbsX - 1, curAbsY, c.heightLevel) & 0x1280108) == 0
					&& (Region.getClipping(curAbsX, curAbsY - 1, c.heightLevel) & 0x1280102) == 0) {
				tileQueueX.add(curX - 1);
				tileQueueY.add(curY - 1);
				via[curX - 1][curY - 1] = 3;
				cost[curX - 1][curY - 1] = thisCost;
			}
			if (curX > 0
					&& curY < 104 - 1
					&& via[curX - 1][curY + 1] == 0
					&& (Region.getClipping(curAbsX - 1, curAbsY + 1,
							c.heightLevel) & 0x1280138) == 0
					&& (Region.getClipping(curAbsX - 1, curAbsY, c.heightLevel) & 0x1280108) == 0
					&& (Region.getClipping(curAbsX, curAbsY + 1, c.heightLevel) & 0x1280120) == 0) {
				tileQueueX.add(curX - 1);
				tileQueueY.add(curY + 1);
				via[curX - 1][curY + 1] = 6;
				cost[curX - 1][curY + 1] = thisCost;
			}
			if (curX < 104 - 1
					&& curY > 0
					&& via[curX + 1][curY - 1] == 0
					&& (Region.getClipping(curAbsX + 1, curAbsY - 1,
							c.heightLevel) & 0x1280183) == 0
					&& (Region.getClipping(curAbsX + 1, curAbsY, c.heightLevel) & 0x1280180) == 0
					&& (Region.getClipping(curAbsX, curAbsY - 1, c.heightLevel) & 0x1280102) == 0) {
				tileQueueX.add(curX + 1);
				tileQueueY.add(curY - 1);
				via[curX + 1][curY - 1] = 9;
				cost[curX + 1][curY - 1] = thisCost;
			}
			if (curX < 104 - 1
					&& curY < 104 - 1
					&& via[curX + 1][curY + 1] == 0
					&& (Region.getClipping(curAbsX + 1, curAbsY + 1,
							c.heightLevel) & 0x12801e0) == 0
					&& (Region.getClipping(curAbsX + 1, curAbsY, c.heightLevel) & 0x1280180) == 0
					&& (Region.getClipping(curAbsX, curAbsY + 1, c.heightLevel) & 0x1280120) == 0) {
				tileQueueX.add(curX + 1);
				tileQueueY.add(curY + 1);
				via[curX + 1][curY + 1] = 12;
				cost[curX + 1][curY + 1] = thisCost;
			}
		}
		if (!foundPath) {
			if (moveNear) {
				int i_223_ = 1000;
				int thisCost = 100;
				int i_225_ = 10;
				for (int x = destX - i_225_; x <= destX + i_225_; x++) {
					for (int y = destY - i_225_; y <= destY + i_225_; y++) {
						if (x >= 0 && y >= 0 && x < 104 && y < 104
								&& cost[x][y] < 100) {
							int i_228_ = 0;
							if (x < destX)
								i_228_ = destX - x;
							else if (x > destX + xLength - 1)
								i_228_ = x - (destX + xLength - 1);
							int i_229_ = 0;
							if (y < destY)
								i_229_ = destY - y;
							else if (y > destY + yLength - 1)
								i_229_ = y - (destY + yLength - 1);
							int i_230_ = i_228_ * i_228_ + i_229_ * i_229_;
							if (i_230_ < i_223_
									|| (i_230_ == i_223_ && (cost[x][y] < thisCost))) {
								i_223_ = i_230_;
								thisCost = cost[x][y];
								curX = x;
								curY = y;
							}
						}
					}
				}
				if (i_223_ == 1000)
					return;
			} else {
				return;
			}
		}
		tail = 0;
		tileQueueX.set(tail, curX);
		tileQueueY.set(tail++, curY);
		int l5;
		for (int j5 = l5 = via[curX][curY]; curX != c.getLocalX()
				|| curY != c.getLocalY(); j5 = via[curX][curY]) {
			if (j5 != l5) {
				l5 = j5;
				tileQueueX.set(tail, curX);
				tileQueueY.set(tail++, curY);
			}
			if ((j5 & 2) != 0) {
				curX++;
			} else if ((j5 & 8) != 0) {
				curX--;
			}
			if ((j5 & 1) != 0) {
				curY++;
			} else if ((j5 & 4) != 0) {
				curY--;
			}
		}
		c.resetWalkingQueue();
		int size = tail--;
		int pathX = c.getMapRegionX() * 8 + tileQueueX.get(tail);
		int pathY = c.getMapRegionY() * 8 + tileQueueY.get(tail);
		c.addToWalkingQueue(localize(pathX, c.getMapRegionX()),
				localize(pathY, c.getMapRegionY()));
		for (int i = 1; i < size; i++) {
			tail--;
			pathX = c.getMapRegionX() * 8 + tileQueueX.get(tail);
			pathY = c.getMapRegionY() * 8 + tileQueueY.get(tail);
			c.addToWalkingQueue(localize(pathX, c.getMapRegionX()),
					localize(pathY, c.getMapRegionY()));
		}
	}

	public int localize(int x, int mapRegion) {
		return x - 8 * mapRegion;
	}

}