package view.GlobalStrings;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import logging.Logging;

public class LanguageView
{
	private ResourceBundle bundle;

	public static final String VIEW_STRING_EN = "ENGLISH";
	public static final String VIEW_STRING_DE = "DEUTSCH";

	public static final String DEFAULT_STRING = " ";

	public static final String PLAYER_VS_PLAYER = "Player_vs._Player";
	public static final String PLAYER_VS_CPU = "Player_vs._CPU";
	public static final String SETTINGS = "Settings";

	public static final String IP_ADDRESS = "IP_Address";
	public static final String PORT = "Port";
	public static final String HOST = "Host";
	public static final String LEVEL = "Level";
	public static final String LANGUAGE = "Language";
	public static final String SAVE = "Save";

	public static final String GERMAN = "German";
	public static final String ENGLISH = "English";
	public static final String IMPORT_FILE = "ImportFile";
	public static final String EXPORT_CSV = "ExportCSV";
	public static final String EXPORT_XML = "ExportXML";
	public static final String FILE = "File";

	public static final String THEME = "Theme";
	public static final String COLOR = "Color";
	public static final String CHANGE = "Change";

	public LanguageView(String language)
	{

		setLanguage(language);
	}

	public void setLanguage(String languageDictionary)
	{
		try
		{
			this.bundle = ResourceBundle.getBundle("LanguageViewStrings",
					new Locale(languageDictionary));
		}
		catch (MissingResourceException e)
		{
			Logging.writeErrorMessage("Cannot load Resource Bundle for Multilanguage");
		}
	}

	public String getResourceString(String languageDictionary)
	{
		String resourceString;
		try
		{
			resourceString = this.bundle.getString(languageDictionary);
		}
		catch (MissingResourceException mre)
		{
			if (languageDictionary != null)
			{
				resourceString = languageDictionary;
			}
			else
			{
				resourceString = DEFAULT_STRING;
			}

			Logging.writeInfoMessage("translation string \""
					+ languageDictionary
					+ "\" wasn't found in File! Using default string");
		}
		return resourceString;
	}

}
