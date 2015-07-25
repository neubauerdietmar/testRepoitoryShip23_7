package Game;

/**
 * @author Schoenegger / Purkart / Koch
 */
public class GlobalGameData
{
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	private static boolean isMyTurn;

	/**
	 * Set my turn
	 * 
	 * @param turn
	 */
	public static void setIsMyTurn(boolean turn)
	{
		isMyTurn = turn;
	}

	/**
	 * Its my Turn
	 * 
	 * @return boolean
	 */
	public static boolean isMyTurn()
	{
		return isMyTurn;
	}
}
