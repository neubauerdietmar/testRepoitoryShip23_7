package GameConnections.CommandBuilder;

import java.awt.Point;

import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.AttackPosition.AttackPosition;
import GameUtilities.Field.Field;

/***
 * Class to converting Commands
 * 
 * @author Schoenegger / Purkart / Koch
 */
public class CommandConverter
{
    /**
     * Consturctor
     */
    public CommandConverter()
    {

    }

    /**
     * Converts the command string ready to a TCP String
     * 
     * @param command
     * @return String
     */
    public String convertToTCPString(Command command)
    {

//	if (command == null)
//	{
//	    // System.out.println("command is null!!!");
//
//	}
	String string = command.toString();
	return string;
    }

    /**
     * convert the command string to a game command
     * 
     * @param commandString
     * @return command
     */
    public Command convertToGameCommand(String commandString)
    {
	String[] segments = commandString.split(";");

	int commandNr = Integer.parseInt(segments[0]);

	if (commandNr == 99)
	{
	    Command dummyCommand = new Command(commandNr, null, "KEEP_ALIVE");
	    return dummyCommand;
	}

	String commandType = segments[1];

	Object commandData = null;

	switch (commandType)
	{
	    case "INIT_FIELD":
		commandData = parseField(segments[2]);
		break;
	    case "ATTAC_COMMAND":
		commandData = parseAttackPosition(segments[2]);
		break;
	    default:
		commandData = null;
		break;
	}

	Command convertCommand = new Command(commandNr, commandData, commandType);
	return convertCommand;
    }

    private AttackPosition parseAttackPosition(String string)
    {
	String[] xyPositions = string.split(",");
	Point attackedPoint = new Point(Integer.parseInt(xyPositions[0]), Integer.parseInt(xyPositions[1]));
	AttackPosition transmittedAttack = new AttackPosition(attackedPoint);

	return transmittedAttack;
    }

    private Field parseField(String string)
    {
	Field transmittedField = new Field();

	String[] segments = string.split("-");

	for (String shipString : segments)
	{
	    if (shipString.isEmpty())
		continue;
	    transmittedField.setShipOnField(parseShip(shipString));
	}

	return transmittedField;
    }

    private Ship parseShip(String string)
    {
	String[] segments = string.split(",");

	int shipNumber = Integer.parseInt(segments[0]);

	ShipType shipType = ShipType.AIRCARRIER;
	switch (segments[1])
	{
	    case "AIRCARRIER":
		shipType = ShipType.AIRCARRIER;
		break;
	    case "YELLOW_SUBMARINE":
		shipType = ShipType.YELLOW_SUBMARINE;
		break;
	    case "DESTROYER":
		shipType = ShipType.DESTROYER;
		break;
	    default:
		break;
	}

	Point position = new Point(Integer.parseInt(segments[2]), Integer.parseInt(segments[3]));

	String alignment = segments[4];

	ShipPosition shipPosition = new ShipPosition(position, alignment);

	Ship ship = new Ship(shipPosition, shipType, shipNumber);

	return ship;
    }
}
