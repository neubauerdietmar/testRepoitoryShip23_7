package GameUtilities;

/**
 * Commands
 * 
 * @author Schonegger / Purkart / Koch
 *
 */
public class Command
{
    private Object commandData;
    private String commandType; // INIT_FIELD, ATTAC_COMMAND
    private int commandNr;

    /**
     * Constructor
     * 
     * @param commandNr
     * @param data
     * @param type
     */
    public Command(int commandNr, Object data, String type)
    {
	this.commandData = (Object) data;
	this.commandType = type;
	this.commandNr = commandNr;
    }

    /**
     * get the command Data
     * 
     * @return Object
     */
    public Object getCommandData()
    {
	return this.commandData;
    }

    /**
     * get the type of the command
     * 
     * @return String
     */
    public String getType()
    {
	return this.commandType;
    }

    /**
     * get the number of the new command
     * 
     * @return integer
     */
    public int getNummber()
    {
	return this.commandNr;
    }

    /**
     * check if the command is valide
     * 
     * @return boolean
     */
    public boolean isValid()
    {
	if (this.commandNr == 99)
	{
	    return false;
	}

	return true;
    }

    /**
     * generate the command string
     * 
     * @return String
     */
    public String toString()
    {
	return Integer.toString(this.commandNr) + ";" + this.commandType + ";" + this.commandData.toString();
    }

}
