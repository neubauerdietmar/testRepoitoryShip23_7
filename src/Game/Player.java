package Game;

import GameUtilities.Field.Field;

/***
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class Player // Player not needed
{

	private Logic logic;

	/**
	 * Constructor
	 * 
	 * @param isFirstPlayer
	 */
	public Player(boolean isFirstPlayer) // The Host is the First Player
	{
		logic = new Logic(isFirstPlayer);
	}

	/**
	 * Create the own field
	 * 
	 * @param ownInitField
	 */
	public void createOwnField(Field ownInitField)
	{
		logic.setInitField(ownInitField);
	}

	/**
	 * send Frontend Reference to Logig
	 * 
	 * @param refFrontend
	 */
	public void sendFrontendReferenceToLogic(Frontend refFrontend)
	{
		// logic.setFrontendReference(refFrontend);
	}

}
