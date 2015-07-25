package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import logging.Logging;
import view.GlobalStrings.Definitions;
import Game.Logic;

public class GameViewListener implements ActionListener, MouseMotionListener
{

	private Logic refLogic;

	public GameViewListener(Logic refLogic)
	{
		this.refLogic = refLogic;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{

	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		try
		{
			Thread.sleep(150);
		}
		catch (InterruptedException e1)
		{
			Logging.writeErrorMessage("Mouse Listener failed");
		}

		refLogic.sendMousePositionsToGameView(e.getX(), e.getY());
		// JOptionPane.showMessageDialog(null,
		// "mouseMoved  " + e.getX() + " " + e.getY(), "",
		// JOptionPane.OK_CANCEL_OPTION);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		switch (command)
		{
		case Definitions.BUTTON_SET_SHIP :
			refLogic.setShipButtonPressed();
			break;
		case Definitions.BUTTON_ATTAC :
			refLogic.attacShipButtonPressed();
			break;
		}

	}

}
