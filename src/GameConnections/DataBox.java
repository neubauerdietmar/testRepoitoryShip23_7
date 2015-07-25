package GameConnections;

import java.util.LinkedList;
import java.util.Queue;

import GameUtilities.Command;

/**
 * Collect and handle the game data
 * 
 * @author Schoenegger / Purkart / Koch
 */
public class DataBox
{
	private static Queue<Command> receiveCommands = new LinkedList<Command>();
	private static Queue<Command> sendCommands = new LinkedList<Command>();
	// Semaphor
	private static boolean accessForReceiveCommands = true;
	private static boolean accessForSendCommands = true;

	/**
	 * Get access to send commands
	 * 
	 * @return boolean
	 */
	private static boolean getAccessSendCommands()
	{
		while (!accessForSendCommands)
		{
			try
			{
				Thread.sleep((int) (Math.random() * 1000));
			}
			catch (Exception e)
			{
				System.out.println(e.toString() + "Error getAccessSendCommands");
			}
		}

		accessForSendCommands = false; // lock access for other calls

		return true;
	}

	private static void freeAccessSendCommands()
	{
		accessForSendCommands = true;
	}

	private static boolean getAccessReceiveCommands()
	{
		while (!accessForReceiveCommands)
		{
			try
			{
				Thread.sleep((int) (Math.random() * 1000));
			}
			catch (Exception e)
			{
				System.out.println(e.toString() + "Error getAccessreceiveCommands");
			}
		}

		accessForReceiveCommands = false; // lock acces for other calls

		return true;
	}

	private static void freeAccessReceiveCommands()
	{
		accessForReceiveCommands = true;
	}

	/**
	 * Checks if there are recieved Commands
	 * 
	 * @return boolean
	 */
	public static boolean isReceiveListEmpty()
	{
		if (receiveCommands.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Checks if there are Commands to send
	 * 
	 * @return boolean
	 */
	public static boolean isSendListEmpty()
	{
		if (sendCommands.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Get first Send Command
	 * 
	 * @return Command
	 */
	public static Command popSendCommand()
	{
		Command returnCommand;

		getAccessSendCommands();

		if (sendCommands.isEmpty())
		{
			returnCommand = null;
		}
		else
		{
			returnCommand = sendCommands.poll();
		}

		freeAccessSendCommands();
		return returnCommand;
	}

	/**
	 * Get first Revceive Command
	 * 
	 * @return Command
	 */
	public static Command popReceiveCommand()
	{
		Command returnCommand;

		getAccessReceiveCommands();

		if (receiveCommands.isEmpty())
		{
			returnCommand = null;
		}
		else
		{
			returnCommand = receiveCommands.poll();
		}

		freeAccessReceiveCommands();
		return returnCommand;
	}

	/**
	 * Pushs a send Command into the queue
	 * 
	 * @param command
	 */
	public static void pushSendCommand(Command command)
	{
		getAccessSendCommands();
		sendCommands.add(command);
		freeAccessSendCommands();
	}

	/**
	 * Pushs a send Receive into the queue
	 * 
	 * @param command
	 */
	public static void pushReceiveCommand(Command command)
	{
		getAccessReceiveCommands();

		receiveCommands.add(command);
		freeAccessReceiveCommands();
	}
}
