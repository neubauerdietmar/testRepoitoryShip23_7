package Game;

import GameConnections.DataBox;
import GameUtilities.Command;
import GameUtilities.AttackPosition.AttackPosition;
import GameUtilities.Field.Field;

/**
 * @author Team Schoenegger / Purkart / Koch
 *
 *
 *         Handler for the commands between the component
 */

public class CommandHandler
{
	private Logic referenceLogic;
	private int commandNo = 0;

	public CommandHandler(Logic refLogic)
	{
		this.referenceLogic = refLogic;
	}

	/**
	 * Send the inititalised field
	 * 
	 * @param initCommand
	 *            command
	 */
	public void sendInitField(Command initCommand)
	{

		DataBox.pushSendCommand(initCommand);

		receiveInitFieldFromEnemy();
	}

	/**
	 * Send the Attac Command
	 * 
	 * @param attacCommand
	 *            command
	 */
	public void sendAttacCommand(Command attacCommand)
	{
		DataBox.pushSendCommand(attacCommand);
		receiveAttacCommandFromEnemy();
	}

	/**
	 * Received the attact command from the emeny player
	 */
	private void receiveAttacCommandFromEnemy()
	{
		receiveCommandFromDataBox();

	}

	// **************receive Command********************
	// create a List of sendet commandos
	// If commando was not done locate Command per number and send it again!!

	/**
	 * received the initialised field from enemy
	 * 
	 */
	public void receiveInitFieldFromEnemy()
	{
		while (DataBox.isReceiveListEmpty())
		{
			// Wait for receive Data Box --- Command Handler---
			wait(300);
		}
		receiveCommandFromDataBox();
	}

	/**
	 * received the command form the databox
	 */
	public void receiveCommandFromDataBox()
	{
		Command command = null;
		while (command == null)
		{
			command = DataBox.popReceiveCommand();
			// WAIT
			wait(300);
		}

		// check type of Commands
		switch (command.getType())
		{
			case "INIT_FIELD":
				setEnemyFieldInLogicByCommand(command);
				break;
			case "ATTAC_COMMAND":
				sendValidAttacCommandToLogic(command);
				break;
		}
	}

	private void sendValidAttacCommandToLogic(Command command)
	{
		if (command.getCommandData() instanceof AttackPosition)
		{
			referenceLogic.setEnemyAttacCommand((AttackPosition) command.getCommandData());
		}
		else
		{
			System.out.println("No valid Attac command received");
		}
	}

	private void setEnemyFieldInLogicByCommand(Command command)
	{
		if (command.getCommandData() instanceof Field)
		{
			referenceLogic.setEnemyField((Field) command.getCommandData());
		}
		else
		{
			System.out.println("Error in EnemyField ---Command Handler--");
		}
	}

	// helpers
	private int getNewCommandNumber()
	{
		return ++this.commandNo;
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
