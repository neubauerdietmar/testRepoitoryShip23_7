package Game;

import java.util.Scanner;

import GameConnections.ConnectionCommandHandler;
import GameUtilities.Field.Field;

/**
 * contains all the Frontend data
 * 
 * @author Schoenegger / Purkart / Koch
 */
public class Frontend
{
	private Field fieldWithSettings;
	private Thread connectionCommandHandler;
	private FrontendGame frontendGame = new FrontendGame(this); // Send referenz
																// to
																// frontendGame
	private Player player = new Player(true);
	private Logic logic = new Logic(true);

	/**
	 * calls the Menu at the beginning of the game
	 */
	public void callMenue()
	{
		String connectionType = "";

		System.out
				.println("***********************************************************");
		System.out
				.println("*                  BATTLESHIP COMMANDER                   *");
		System.out
				.println("***********************************************************");

		System.out.println("Select Game \n");

		System.out.println("vs Host Game = 'H'");
		System.out.println("vs Client Game = 'CL'");
		System.out.println("vs CPU = 'CPU'");

		System.out.println("Please select: ");

		player.sendFrontendReferenceToLogic(this);
		// logic.setFrontendReference(this);

		connectionType = readMenueInput();
	}

	/**
	 * red the intput read and valdiate the menu input by user
	 * 
	 * @return String
	 */
	private String readMenueInput()
	{
		String connectionType;

		connectionType = readStringFromConsole();

		while (!createConnectionByInput(connectionType))
		{
			System.out.println("Please Insert the Correct Text ");
			connectionType = readStringFromConsole();
		}

		return connectionType;
	}

	/**
	 * Create the connection by the user input
	 * 
	 * @param connectionType
	 * @return boolean
	 */
	private boolean createConnectionByInput(String connectionType)
	{
		switch (connectionType)
		{
		case "H" :
			readHostSettings();
			return true;

		case "CL" :
			readClientSettings();
			return true;

		case "CPU" :
			createCpuPlayerConnection();
			return true;

		default :
			return false;
		}
	}

	/**
	 * Read the host Settings
	 */
	private void readHostSettings()
	{
		String hostPort;

		System.out.println("Select Host Port 8000 - 8500 ");
		hostPort = readStringFromConsole();

		while (!createHostConnectionByInput(hostPort))
		{
			System.out.println("Please Insert the Correct Text ");
			System.out.println("Select Host Port 8000 - 8500 ");
			hostPort = readStringFromConsole();
		}
	}

	/**
	 * Create a host connection by input
	 * 
	 * @param hostPort
	 * @return boolean
	 */
	private boolean createHostConnectionByInput(String hostPort)
	{
		int portNo;

		try
		{
			portNo = Integer.parseInt(hostPort);

		}
		catch (Exception e)
		{
			return false;
		}

		if (checkPortNumber(portNo))
		{
			connectionCommandHandler = new Thread(new ConnectionCommandHandler(
					portNo));
			connectionCommandHandler.start();
			executeGameSetupMenue();
			return true;
		}

		return false;
	}

	/**
	 * read the client setting by user
	 */
	private void readClientSettings()
	{
		String clientPort;
		String ipAddress;

		System.out.println("Select Host Port 8000 - 8500 ");
		clientPort = readStringFromConsole();

		System.out.println("Insert Valid ip address ");
		ipAddress = readStringFromConsole();

		while (!createClientConnectionByInput(clientPort, ipAddress))
		{
			System.out.println("Please Insert the Correct Text ");
			System.out.println("Select Host Port 8000 - 8500 ");
			clientPort = readStringFromConsole();

			System.out.println("Insert Valid ip address ");
			ipAddress = readStringFromConsole();
		}

	}

	/**
	 * Create client connection by input
	 * 
	 * @param clientPort
	 * @param ipAddress
	 * @return boolean
	 */
	private boolean createClientConnectionByInput(String clientPort,
			String ipAddress)
	{
		int portNo;

		try
		{
			portNo = Integer.parseInt(clientPort);

		}
		catch (Exception e)
		{
			return false;
		}

		if (checkPortNumber(portNo) && checkIpAddress(ipAddress))
		{
			// this.player = new Player(false);
			this.logic = new Logic(false);
			connectionCommandHandler = new Thread(new ConnectionCommandHandler(
					portNo, ipAddress));
			connectionCommandHandler.start();
			// logic.setFrontendReference(this);
			executeGameSetupMenue();

			return true;
		}

		return false;
	}

	/**
	 * Check the IP Adress
	 * 
	 * @param ipAddress
	 * @return boolean
	 */
	private boolean checkIpAddress(String ipAddress)
	{
		if (ipAddress.length() <= 15)
		{
			return true;
		}
		// TODO Better check string.split(".") && < 256
		return false;
	}

	/**
	 * Create CPU player connection
	 * 
	 * @return boolean
	 */
	private boolean createCpuPlayerConnection()
	{
		System.out.println("Cpu player created");

		connectionCommandHandler = new Thread(new ConnectionCommandHandler());
		connectionCommandHandler.start();
		executeGameSetupMenue();

		System.out.println("cpu player game executed");

		// TODO exceptionhandling for false
		return true;
	}

	/**
	 * Read string from console
	 * 
	 * @return Scanner object
	 */
	private String readStringFromConsole()
	{
		return new Scanner(java.lang.System.in).nextLine();
	}

	/**
	 * Check port number
	 * 
	 * @param portNo
	 * @return boolean
	 */
	private boolean checkPortNumber(int portNo)
	{
		if (portNo >= 8000 && portNo <= 8500)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Execute game setup menu
	 */
	private void executeGameSetupMenue()
	{
		FrontendGameSetup gameSetup = new FrontendGameSetup();
		if (gameSetup.callMenue())
		{
			this.fieldWithSettings = gameSetup.getFinischedField();
		}

		sendInitFieldToLogic();
	}

	/**
	 * send the init field to the logic
	 */
	private void sendInitFieldToLogic()
	{
		logic.setInitField(fieldWithSettings);
	}

	// --------->>>>>now the Logic controlles the
	// game<<<<<<---------------------------

	// *******************************CALL BY
	// REFERENCE******************************** //

	/**
	 * Send the feedback back, weather the enemy had initialized his field.
	 */
	public void sendFeedbackThatEnemyHasInitHisField()
	{
		System.out.println("\n Enemy has init his field");
	}

	/**
	 * get the next command
	 * 
	 * @return String
	 */
	public String getNextCommand()
	{
		return frontendGame.getNextMove();
	}

	/**
	 * Ask the Logic whether the move is valid
	 * 
	 * @param nextMove
	 * @return
	 */
	public boolean askLogikIsAttacMoveValid(String nextMove)
	{
		return logic.isAttacMoveValid(nextMove);
	}

	public void displayGameOver()
	{
		System.out.println("YOU LOSE!!!");
	}

	public void displayWin()
	{
		System.out.println("YOU WIN!!!");
	}
}