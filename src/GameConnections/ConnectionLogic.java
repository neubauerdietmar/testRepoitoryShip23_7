package GameConnections;

import GameUtilities.Command;

/**
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class ConnectionLogic
{
	Connection connection;

	/**
	 * Constructor
	 * 
	 * @param connection
	 */
	public ConnectionLogic(Connection connection)
	{
		this.connection = connection;
	}

	/**
	 * Send the command to play
	 * 
	 * @param commandSend
	 */
	public void sendCommandToPlayer(Command commandSend)
	{
		this.connection.sendCommand(commandSend);
	}

	/**
	 * Get the command from the Player
	 * 
	 * @return commandRecieve
	 */
	public Command getCommandFromPlayer()
	{
		Command commandRecieve = this.connection.receiveCommand();

		return commandRecieve;
	}

	/**
	 * Close the connection
	 */
	public void closeConnection()
	{
		this.connection.close();
	}

	/**
	 * Is the connection available
	 * 
	 * @return connection
	 */
	public boolean isConnectionAvailable()
	{
		return connection.isConnectionAvailable();
	}
}
