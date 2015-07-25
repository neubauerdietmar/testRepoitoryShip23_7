package Game;

import logging.Logging;

/**
 * Game - Start Class
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class Game
{
	/**
	 * Startpoint
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		// Frontend frontend = new Frontend();

		// frontend.callMenue();
		Logic gameLogic = new Logic(true);
		Logging.writeInfoMessage("Start Game");
	}

}
