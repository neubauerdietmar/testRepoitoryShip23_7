package GameConnections;

import Game.GlobalGameData;
import GameUtilities.Command;
import UnitTests.MockObjects.MockConnection;

/**
 * Handler for the connection commands
 * 
 * @author Schoenegger / Purkart / Koch
 */
public class ConnectionCommandHandler implements Runnable
{
	private ConnectionLogic connectionLogic;
	private Command commandSend;
	private Command commandReceive;
	private Connection connection = null;

	private static boolean abortConnection = false; // static.. you can call it
													// from everywhere

	/**
	 * Constructor
	 */
	public ConnectionCommandHandler()
	{
		this.connection = new LocalConnection();
		this.connectionLogic = new ConnectionLogic(connection);
	}

	/**
	 * Constructor
	 */
	public ConnectionCommandHandler(MockConnection refMockConnection)
	{
		this.connection = refMockConnection;
		this.connectionLogic = new ConnectionLogic(connection);
	}

	/**
	 * Constructor Server Connection
	 * 
	 * @param port
	 */
	public ConnectionCommandHandler(int port)
	{

		try
		{
			this.connection = new TCPConnectionServer(port);
			this.connectionLogic = new ConnectionLogic(connection);
		}
		catch (Exception exception)
		{
			System.out
					.println("Can not create connection. Please restart the Game !!!\n Exception:"
							+ exception.toString());
		}
	}

	/**
	 * Constructor Client Connection
	 * 
	 * @param port
	 * @param ipAdress
	 */
	public ConnectionCommandHandler(int port, String ipAdress)
	{
		GlobalGameData.setIsMyTurn(false);

		try
		{
			this.connection = new TCPConnectionClient(port, ipAdress);
			this.connectionLogic = new ConnectionLogic(connection);
		}
		catch (Exception exception)
		{
			System.out
					.println("Can not create connection. Please restart the Game !!!\n Exception:"
							+ exception.toString());
		}
	}

	/**
	 * Aborts all connections of this type
	 */
	public static void abortConnection()
	{
		abortConnection = true;
	}

	/**
	 * Runs the connection, send an receive TCP commands
	 */
	@Override
	public void run()
	{
		abortConnection = false;

		do
		{
			commandSend = getNextCommandFromDataBox();

			connectionLogic.sendCommandToPlayer(commandSend);

			wait(300);

			this.commandReceive = connectionLogic.getCommandFromPlayer();

			if (this.commandReceive != null && this.commandReceive.isValid())
			{
				sendCommandToDataBox(commandReceive);
			}

			wait(300);
		} while (!abortConnection);

		connectionLogic.closeConnection();
	}

	// ************private functions for Data Box communication***********

	private Command getNextCommandFromDataBox()
	{
		return DataBox.popSendCommand();
	}

	private void sendCommandToDataBox(Command command)
	{
		DataBox.pushReceiveCommand(command);
	}

	private void wait(int ms)
	{
		try
		{
			Thread.sleep(ms);

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

}