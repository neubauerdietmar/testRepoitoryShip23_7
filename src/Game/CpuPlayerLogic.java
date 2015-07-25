package Game;

import java.awt.Point;

import logging.Logging;
import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.AttackPosition.AttackPosition;
import GameUtilities.Field.Field;

public class CpuPlayerLogic
{

	Field ownField = new Field();
	Field enemyField = new Field();
	Command nextReturnCommand;

	public CpuPlayerLogic()
	{

	}

	public Command getNextCommand()
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			Logging.writeErrorMessage("Problem in wait Time Answer Command CPU Logic");

		}

		Command actualCommand = nextReturnCommand;
		nextReturnCommand = null;
		return actualCommand;
	}

	public void sendCommand(Command command)
	{
		if (command == null)
		{
			System.out.println("send command Cpu PlayerLogic null objectCPU");
		}

		if (isCommandInitField(command))
		{
			setEnemyField(command);
			setShipsOnOwnField();
			nextReturnCommand = buildFieldCommand();
		}
		else if (isCommandAttacCommand(command))
		{
			int points[] = getCoordinatesfromAttacCommand(command);
			ownField.fireToPosition(points[0], points[1]);
			nextReturnCommand = getAnswerCommand();
		}
	}

	private Command buildFieldCommand()
	{
		Command fieldCommand = new Command(1, ownField, "INIT_FIELD");

		return fieldCommand;
	}

	private Command getAnswerCommand()
	{
		Point point = enemyField.getRandomfreeFieldCoordinate();
		return buildAttacCommand(point);

		// return null;
	}

	private Command buildAttacCommand(Point point)
	{
		Command attacCommand = new Command(1, new AttackPosition(point),
				"ATTAC_COMMAND");

		return attacCommand;
	}

	private int[] getCoordinatesfromAttacCommand(Command command)
	{
		AttackPosition attackPos = (AttackPosition) command.getCommandData();
		Point point = attackPos.getXyPosition();
		int pointCoordinates[] = new int[2];
		pointCoordinates[0] = point.x;
		pointCoordinates[1] = point.y;

		return pointCoordinates;
	}

	private boolean isCommandInitField(Command command)
	{
		if (command.getType().equals("INIT_FIELD"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void setEnemyField(Command command)
	{
		enemyField = (Field) command.getCommandData();
	}

	private boolean isCommandAttacCommand(Command command)
	{
		if (command.getType().equals("ATTAC_COMMAND"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void setShipsOnOwnField()
	{
		// TODO Random!
		// simple ship init for Alpha Version
		Ship ship1 = new Ship(new ShipPosition(new Point(2, 2), "HORIZONTAL"),
				ShipType.DESTROYER, 1);
		Ship ship2 = new Ship(new ShipPosition(new Point(4, 3), "HORIZONTAL"),
				ShipType.DESTROYER, 1);
		Ship ship3 = new Ship(new ShipPosition(new Point(1, 3), "HORIZONTAL"),
				ShipType.YELLOW_SUBMARINE, 1);
		Ship ship4 = new Ship(new ShipPosition(new Point(2, 5), "VERTICAL"),
				ShipType.YELLOW_SUBMARINE, 1);
		Ship ship5 = new Ship(new ShipPosition(new Point(3, 4), "VERTICAL"),
				ShipType.AIRCARRIER, 1);
		Ship ship6 = new Ship(new ShipPosition(new Point(1, 4), "VERTICAL"),
				ShipType.AIRCARRIER, 1);

		ownField.setShipOnField(ship1);
		ownField.setShipOnField(ship2);
		ownField.setShipOnField(ship3);
		ownField.setShipOnField(ship4);
		ownField.setShipOnField(ship5);
		ownField.setShipOnField(ship6);

		ownField.setTaken();
	}

}