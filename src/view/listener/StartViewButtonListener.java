package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GlobalStrings.Definitions;
import Game.Logic;

public class StartViewButtonListener implements ActionListener, KeyListener
{

	private Logic refGameLogic;

	public StartViewButtonListener(Logic refGameLogic)
	{
		this.refGameLogic = refGameLogic;
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();

		switch (command)
		{
		case Definitions.PLAYER_VS_COM :
			refGameLogic.openViewGameFieldCPU();
			break;
		case Definitions.PLAYER_VS_PLAYER :
			refGameLogic.openViewGameField();
			break;
		case Definitions.SETTING_START_VIEW :
			refGameLogic.openStartViewSettings();
			break;
		default :
			break;
		}
	}

	// Add Didi Key Listener
	@Override
	public void keyPressed(KeyEvent e)
	{
		int i = 0;
		i = 123;
		// JFrame source = (JFrame) e.getSource();
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_ESCAPE :
			// System.exit(0);
			refGameLogic.quitGame();
			break;
		case KeyEvent.VK_P :
			refGameLogic.openViewGameField();
			break;
		case KeyEvent.VK_C :
			refGameLogic.openViewGameFieldCPU();
			break;
		case KeyEvent.VK_S :
			refGameLogic.openStartViewSettings();
			break;
		case KeyEvent.VK_ENTER :

			switch (refGameLogic.checkFocusInStartView())
			{
			case Definitions.PLAYER_VS_COM :
				refGameLogic.openViewGameField();
				break;
			case Definitions.PLAYER_VS_PLAYER :
				;
				break;
			case Definitions.SETTING_START_VIEW :
				refGameLogic.openStartViewSettings();
				break;
			default :
				break;
			}
		default :
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

}
