package server.model.players.GrandExchange;

/**
 * @author Alex(TheLife)
 */

public class Buyers {

	/**
	 * Integers
	 */
	public int id, itemId, amount, updatedAmount, price, percentage, slot,
			itemOne, itemTwo, itemOneAmount, itemTwoAmount, total, totalGp;

	/**
	 * Booleans
	 */
	public boolean aborted = false, completed = false, updated = false;

	/**
	 * Strings
	 */
	public String owner;

	/**
	 * New offer
	 */
	public Buyers(int Id) {
		id = Id;
	}
}