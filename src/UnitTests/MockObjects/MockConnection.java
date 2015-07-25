package UnitTests.MockObjects;

import java.util.Stack;

import GameConnections.Connection;
import GameUtilities.Command;

public class MockConnection extends Connection
{
	Stack<Command> commandStack;

	@Override
	public Command receiveCommand()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendCommand(Command command)
	{
		commandStack.push(command);
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnectionAvailable()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public Command getCommandFromStack()
	{
		return commandStack.pop();
	}

}
