package UnitTests.GameUtilities;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;

import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.AttackPosition.AttackPosition;
import GameUtilities.Field.Field;

public class JCommand
{

	@Test
	public void correctCommandOneShip()
	{
		Point point = new Point(1, 1);
		Field field = new Field();
		ShipPosition shipPosition = new ShipPosition(point, "vertical");
		Ship ship = new Ship(shipPosition, ShipType.AIRCARRIER, 1);
		field.setShipOnField(ship);
		Command command = new Command(1, field, "INIT_FIELD");

		Assert.assertEquals("1;INIT_FIELD;-1,AIRCARRIER,1,1,vertical",
				command.toString());

	}

	@Test
	public void correctCommandMoreThanOneShip()
	{
		Point point = new Point(1, 1);
		Field field = new Field();
		ShipPosition shipPosition = new ShipPosition(point, "vertical");
		Ship ship1 = new Ship(shipPosition, ShipType.AIRCARRIER, 1);
		field.setShipOnField(ship1);
		Ship ship2 = new Ship(shipPosition, ShipType.YELLOW_SUBMARINE, 2);
		field.setShipOnField(ship2);
		Command command = new Command(1, field, "INIT_FIELD");

		Assert.assertEquals(
				"1;INIT_FIELD;-1,AIRCARRIER,1,1,vertical-2,YELLOW_SUBMARINE,1,1,vertical",
				command.toString());

	}

	@Test
	public void correctCommandOneAttac()
	{
		Point point = new Point(1, 1);
		AttackPosition attackPos = new AttackPosition(point);

		Command command = new Command(1, attackPos, "ATTAC_COMMAND");

		Assert.assertEquals("1;ATTAC_COMMAND;1,1", command.toString());

	}

	@Test
	public void correctCommandMoreThanOneAttac()
	{
		Point point = new Point(1, 1);
		AttackPosition attackPos = new AttackPosition(point);

		Command command = new Command(1, attackPos, "ATTAC_COMMAND");

		Assert.assertEquals("1;ATTAC_COMMAND;1,1", command.toString());

	}

}
