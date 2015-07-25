package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import logging.Logging;
import view.GlobalStrings.Definitions;
import view.GlobalStrings.LanguageView;
import view.game.GameWindow;
import view.listener.StartViewButtonListener;
import view.settings.StartSettingsWindow;
import view.settings.StartViewSettingData;
import Game.Logic;
import GameUtilities.Field.Field;

public class StartView extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logic refGameLogic;
	private StartViewButtonListener viewButtListener;
	private GameWindow gameWindow;
	private StartSettingsWindow startSettWindow;
	private StartViewSettingData startSettData;
	public static volatile JLabel lblBattleComm;

	private LanguageView languageView;

	private JFrame frmBattleshipCommander;

	private JButton btnPlayerCom;
	private JButton btn2Player;
	private JButton btnSettings;
	private JLabel label;

	/**
	 * Create the application.
	 */
	public StartView(Logic refGameLogic)// , StartViewSettingData startSettData
	{
		try
		{
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e)
		{
			Logging.writeErrorMessage("Look And Feel nimbus in StartView not possible");
		}

		this.startSettData = new StartViewSettingData();
		this.refGameLogic = refGameLogic;

		this.languageView = new LanguageView(startSettData.getLanguage());

		initializeListeners();
		initializeComponets();
		changeColorHeaderLabel();
	}

	private void changeColorHeaderLabel()
	{
		synchronized (DEFAULT_MODALITY_TYPE)
		{
			Thread thread = new Thread()
			{
				public void run()
				{
					int blue = 100;
					int green = 100;
					int red = 0;
					while (true)
					{
						lblBattleComm
								.setForeground(new Color(red, green, blue));
						try
						{
							Thread.sleep(50);
							blue += 10;
							green++;
							if (blue > 245)
							{
								blue = 0;
							}
							if (green > 100)
							{
								green = 0;
							}

						}
						catch (InterruptedException e)
						{
							Logging.writeErrorMessage("Label Coloring StartView does not work");
						}
					}
				}
			};

			thread.start();
		}

	}

	private void initializeListeners()
	{
		this.viewButtListener = new StartViewButtonListener(refGameLogic);
	}

	private void initializeComponets()
	{
		frmBattleshipCommander = new JFrame();
		frmBattleshipCommander.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(StartView.class.getResource("/img/ship.png")));
		frmBattleshipCommander.getContentPane().setBackground(
				SystemColor.activeCaption);
		frmBattleshipCommander.setResizable(false);
		frmBattleshipCommander.setTitle("BATTLESHIP COMMANDER");
		frmBattleshipCommander.setBounds(100, 100, 535, 331);
		frmBattleshipCommander.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattleshipCommander.getContentPane().setLayout(null);
		this.btnPlayerCom = new JButton(
				languageView.getResourceString(LanguageView.PLAYER_VS_CPU));
		btnPlayerCom.setToolTipText("Alt+C");

		btnPlayerCom.addActionListener(this.viewButtListener);
		btnPlayerCom.addKeyListener(this.viewButtListener);
		btnPlayerCom.setMnemonic(KeyEvent.VK_C);
		btnPlayerCom.setBounds(19, 128, 162, 42);
		btnPlayerCom.setActionCommand(Definitions.PLAYER_VS_COM);
		frmBattleshipCommander.getContentPane().add(this.btnPlayerCom);
		this.btn2Player = new JButton(
				languageView.getResourceString(LanguageView.PLAYER_VS_PLAYER));
		btn2Player.setToolTipText("Alt+P");
		btn2Player.setBounds(19, 74, 162, 42);
		btn2Player.setActionCommand(Definitions.PLAYER_VS_PLAYER);

		frmBattleshipCommander.getContentPane().add(btn2Player);
		this.btnSettings = new JButton(
				languageView.getResourceString(LanguageView.SETTINGS));
		btnSettings.setToolTipText("Alt+S");
		btnSettings.addActionListener(this.viewButtListener);

		btnSettings.addKeyListener(this.viewButtListener);
		btnSettings.setMnemonic(KeyEvent.VK_S);

		btnSettings.setBounds(19, 181, 162, 42);
		btnSettings.setActionCommand(Definitions.SETTING_START_VIEW);

		frmBattleshipCommander.getContentPane().add(btnSettings);

		label = new JLabel("");
		label.setIcon(new ImageIcon(StartView.class
				.getResource("/img/shipBackground.jpg")));
		label.setBounds(193, 38, 340, 241);
		frmBattleshipCommander.getContentPane().add(label);

		lblBattleComm = new JLabel("BATTLESHIP COMMANDER");
		lblBattleComm.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC,
				26));
		lblBattleComm.setForeground(new Color(0, 0, 139));
		lblBattleComm.setBounds(59, 6, 393, 42);
		frmBattleshipCommander.getContentPane().add(lblBattleComm);
		frmBattleshipCommander.setVisible(true);
		btnPlayerCom.requestFocus();
	}

	// *****************Called from Logic****************************

	public void openStartViewSettings()
	{
		this.startSettWindow = new StartSettingsWindow(startSettData,
				this.languageView, refGameLogic.getgameSoundPlayer());
	}

	public void openViewGameFields()
	{
		this.gameWindow = new GameWindow(this, refGameLogic);
		buildConnection(startSettData.getMode());
	}

	public void openViewGameFieldsCPU()
	{
		this.gameWindow = new GameWindow(this, refGameLogic);
		buildConnection("cpu");
	}

	public void getNextCommandFromGameWindow()
	{
		this.gameWindow.getNextMove();
	}

	/**
	 * check which button have currently the focus for keylistener
	 * 
	 * @return -> the string of the focused Button
	 */
	public String checkFocusButton()
	{
		if (btnPlayerCom.isFocusOwner())
		{
			return Definitions.PLAYER_VS_COM;
		}
		else if (btn2Player.isFocusOwner())
		{
			return Definitions.PLAYER_VS_PLAYER;
		}
		else if (btnSettings.isFocusOwner())
		{
			return Definitions.SETTING_START_VIEW;
		}
		return "";
	}

	public void sendGameWindowMessage(String message)
	{
		this.gameWindow.sendMessge(message);

	}

	public void sendMouseMoveToGameView(int x, int y)
	{
		gameWindow.refreshByMouseMove(x, y);

	}

	public void sendSetButtonPressed()
	{
		gameWindow.setShipButtonPressed();
	}

	public void attacShipButtonPressed()
	{
		gameWindow.attacShipButtonPressed();

	}

	public void setEnemyFieldInGameWindow(Field enemyField)
	{
		gameWindow.setEnemyField(enemyField);

	}

	// *********************Functions for Logic****************

	public void sendAttackCommandToEnemy(String attackCommand)
	{
		refGameLogic.sendAttackCommandToEnemy(attackCommand);

	}

	private void buildConnection(String mode)
	{
		refGameLogic.startConnection(mode, startSettData.getIpAddress(),
				startSettData.getPort());
	}

	public void setInitFieldInLogic(Field ownInitField)
	{
		this.refGameLogic.setInitField(ownInitField);
	}
}
