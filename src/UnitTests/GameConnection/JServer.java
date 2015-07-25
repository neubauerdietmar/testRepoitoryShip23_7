package UnitTests.GameConnection;

import org.junit.Test;
import org.junit.Assert;

import java.awt.Point;

import GameConnections.Connection;
import GameConnections.TCPConnectionServer;
import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;
import GameUtilities.Field.Field;

public class JServer
{

    @Test
    public void connectionServer()
    {

	Point point = new Point(1, 1);
	Field field = new Field();
	ShipPosition shipPosition = new ShipPosition(point, "vertical");
	Ship ship = new Ship(shipPosition, ShipType.AIRCARRIER, 1);
	field.setShipOnField(ship);
	Command command = new Command(1, field, "INIT_FIELD");

	Connection connectionServer = null;
	// Connection connectionClient = null;

	try
	{
	    connectionServer = new TCPConnectionServer(8020);
	    // connectionClient = new TCPConnectionClient(8020, "localhost");
	}
	catch (Exception exception)
	{
	    System.out.println("Can not create connection!");
	}

	for (int i = 0; i < 10; i++)
	{
	    connectionServer.sendCommand(command);
	    this.wait(50);
	    // command = connectionClient.receiveCommand();
	    // connectionClient.sendCommand(command);
	    // this.wait(50);
	    command = connectionServer.receiveCommand();
	    System.out.println("---------------------- SERVER");
	}

	Assert.assertEquals("1;INIT_FIELD;-1,AIRCARRIER,1,1,vertical", command.toString());

    }

    private void wait(int ms)
    {
	try
	{
	    Thread.sleep(ms);
	}
	catch (Exception exception)
	{
	}
    }

}