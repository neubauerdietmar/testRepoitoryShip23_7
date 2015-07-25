package logging;

import org.apache.log4j.Logger;

/**
 * static Logger Class
 * 
 * @author Thomas Schönegger
 * @since jdk 1.7
 * @version 1.0.0.1
 */
public class Logging
{
	public static final Logger logger = Logger.getLogger(Logging.class);

	/**
	 * writes a Debug Message String into the log file
	 * 
	 * @param message
	 */
	public static void writeDebugMessage(String message)
	{
		logger.debug(message);
	}

	/**
	 * writes a Error Message String into the log file
	 * 
	 * @param message
	 */
	public static void writeErrorMessage(String message)
	{
		logger.error(message);
	}

	/**
	 * writes a Info Message String into the log file
	 * 
	 * @param message
	 */
	public static void writeInfoMessage(String message)
	{
		logger.info(message);
	}

	/**
	 * writes a Warning Message String into the log file
	 * 
	 * @param message
	 */
	public static void writeWarningMessage(String message)
	{
		logger.warn(message);
	}
}
