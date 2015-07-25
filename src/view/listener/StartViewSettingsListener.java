package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GlobalStrings.Definitions;

public class StartViewSettingsListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();

		switch (command)
		{
		case Definitions.SAVE_BUTTON_START_SETTINGS_VIEW :

			break;

		default :
			break;
		}

	}
}
