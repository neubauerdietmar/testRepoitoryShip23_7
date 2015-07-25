package view.settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import logging.Logging;

public class StartViewSettingData
{
	// private LanguageView languageView;
	// private StartViewSettingData startSettData;
	private String configFilePath = "cnf/configGame.txt";
	private String ipAddress;
	private String port;
	private String mode;
	private String level;
	private String language;

	public String getConfigFilePath()
	{
		return configFilePath;
	}

	public void setConfigFilePath(String configFilePath)
	{
		this.configFilePath = configFilePath;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	public String getPort()
	{
		return port;
	}

	public void setPort(String port)
	{
		this.port = port;
	}

	/**
	 * Mode = Client, Host
	 * 
	 * @return
	 */
	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public String getLevel()
	{
		return level;
	}

	public void setLevel(String level)
	{
		this.level = level;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public StartViewSettingData()// LanguageView languageView
	{

		loadSettingsFromFile();
		// languageView = new LanguageView(startSettData.getLanguage());
	}

	public void loadSettingsFromFile()
	{
		try
		{
			FileReader fr = new FileReader(configFilePath);
			BufferedReader br = new BufferedReader(fr);
			String readedLine = "";

			do
			{
				readedLine = br.readLine();
				if (readedLine != null)
				{
					allocateEntry(readedLine);
				}
			} while (readedLine != null);

			br.close();

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

	}

	private void allocateEntry(String readedLine)
	{
		String[] entryData = readedLine.split(";");

		switch (entryData[0].trim())
		{
		case "ipAddress" :
			this.ipAddress = entryData[1].trim();
			break;
		case "port" :
			this.port = entryData[1].trim();
			break;
		case "mode" :
			this.mode = entryData[1].trim();
			break;
		case "level" :
			this.level = entryData[1].trim();
			break;
		case "language" :
			this.language = entryData[1].trim();
			break;
		default :
			break;
		}
	}

	public void writeSettingsToFile()
	{
		try
		{

			FileWriter fstream = new FileWriter(configFilePath, false);
			BufferedWriter out = new BufferedWriter(fstream);

			out.write("ipAddress" + ";" + this.getIpAddress() + "\n" + "port"
					+ ";" + this.getPort() + "\n" + "mode" + ";"
					+ this.getMode() + "\n" + "level;1" + "\n" + "language"
					+ ";" + this.getLanguage() + "\n");

			out.close();
		}
		catch (Exception e)
		{
			Logging.writeErrorMessage("Cannot write Start view Settings Data to File");
			System.err.println("Error: " + e.getMessage());
		}
	}
}
