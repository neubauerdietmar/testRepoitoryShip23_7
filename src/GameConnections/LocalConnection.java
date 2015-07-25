package GameConnections;

import java.awt.Point;

import Game.CpuPlayer;
import GameUtilities.Command;
import GameUtilities.Ship;
import GameUtilities.ShipPosition;
import GameUtilities.ShipType;

/**
 * Local Connection - CPU Player
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public class LocalConnection extends Connection
{
	private CpuPlayer cpuPlayer;

	/**
	 * Local Connection - CPU Player
	 */
	public LocalConnection()
	{
		this.cpuPlayer = new CpuPlayer();
	}

	/**
	 * is connectection available
	 * 
	 * @return true
	 */
	@Override
	public boolean isConnectionAvailable()
	{
		return true;
	}

	/**
	 * Receive Command
	 * 
	 * @return command
	 */
	@Override
	public Command receiveCommand()
	{

		return cpuPlayer.receiveCommand();
	}

	/**
	 * send command
	 */
	@Override
	public void sendCommand(Command command)
	{
		if (command != null && command.isValid())
		{
			cpuPlayer.sendCommand(command);
		}
	}

	/**
	 * close
	 */
	@Override
	public void close()
	{
		// TODO Auto-generated method stub

	}

}
