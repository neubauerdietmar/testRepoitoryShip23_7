package GameConnections;

import GameUtilities.Command;
/**
 * Defines a Game Connection
 * 
 * @author Schoenegger / Purkart / Koch
 *
 */
public abstract class Connection
{
    public abstract Command receiveCommand();

    public abstract void sendCommand(Command command);

    public abstract void close();

    public abstract boolean isConnectionAvailable();
}