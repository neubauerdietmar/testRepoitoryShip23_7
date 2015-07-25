package view.settings;

import gameSounds.GameSoundPlayer;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import logging.Logging;
import view.GlobalStrings.LanguageView;
import view.listener.StartViewSettingsListener;
import GameUtilities.GlobalValues;

public class StartSettingsWindow extends JDialog
{

	private StartViewSettingData startViewSettingsData;
	private LanguageView languageView;
	private GameSoundPlayer gameSoundPlayer;

	private JFrame frmSettings;
	private JTextField txtIPAddress;
	private JLabel lblPort;
	private JLabel lblHost;
	private JLabel lblLevel;
	private JSpinner spinnerLevel;
	private JSpinner spinnerPort;
	private JLabel lblIpAddress;
	private JCheckBox chckbxNewCheckBox;
	private JComboBox<String> comboBox;
	private JLabel lblLanguage;
	private JButton btnSave;
	private StartViewSettingsListener viewSettListener;

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.constructor
	 */
	// Add Didi
	public StartSettingsWindow(StartViewSettingData startSettData,
			LanguageView languageView, GameSoundPlayer gameSoundPlayer)
	{
		this.languageView = languageView;
		this.viewSettListener = new StartViewSettingsListener();
		startViewSettingsData = startSettData;
		this.gameSoundPlayer = gameSoundPlayer;
		initialize();
	}

	public StartSettingsWindow(StartViewSettingData startSettData,
			LanguageView languageView)
	{
		this.languageView = languageView;
		this.viewSettListener = new StartViewSettingsListener();
		startViewSettingsData = startSettData;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		frmSettings = new JFrame();
		frmSettings.setResizable(false);
		frmSettings.setTitle(languageView
				.getResourceString(LanguageView.SETTINGS));
		frmSettings.getContentPane().setBackground(SystemColor.activeCaption);
		frmSettings.getContentPane().setLayout(null);

		txtIPAddress = new JTextField();
		txtIPAddress.setText(startViewSettingsData.getIpAddress());
		txtIPAddress.setBounds(149, 7, 133, 27);
		frmSettings.getContentPane().add(txtIPAddress);
		txtIPAddress.setColumns(10);

		spinnerPort = new JSpinner();
		spinnerPort.setModel(new SpinnerNumberModel(8000, 8000, 8500, 1));
		spinnerPort.setBounds(149, 49, 63, 28);
		frmSettings.getContentPane().add(spinnerPort);
		spinnerPort.setValue(Integer.parseInt(startViewSettingsData.getPort()));

		lblIpAddress = new JLabel(
				languageView.getResourceString(LanguageView.IP_ADDRESS));
		lblIpAddress.setBounds(27, 4, 73, 35);
		frmSettings.getContentPane().add(lblIpAddress);

		lblPort = new JLabel(languageView.getResourceString(LanguageView.PORT));
		lblPort.setBounds(27, 42, 73, 35);
		frmSettings.getContentPane().add(lblPort);

		lblHost = new JLabel("Host");
		lblHost.setBounds(27, 83, 73, 35);
		frmSettings.getContentPane().add(lblHost);

		chckbxNewCheckBox = new JCheckBox("");
		if (startViewSettingsData.getMode().equals("host"))
		{
			chckbxNewCheckBox.setSelected(true);
		}
		else
		{
			chckbxNewCheckBox.setSelected(false);
		}
		chckbxNewCheckBox.setBounds(149, 95, 21, 23);
		frmSettings.getContentPane().add(chckbxNewCheckBox);

		lblLevel = new JLabel(
				languageView.getResourceString(LanguageView.LEVEL));
		lblLevel.setBounds(27, 132, 73, 35);
		frmSettings.getContentPane().add(lblLevel);

		spinnerLevel = new JSpinner();
		spinnerLevel.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spinnerLevel.setBounds(149, 139, 63, 28);
		frmSettings.getContentPane().add(spinnerLevel);

		lblLanguage = new JLabel(
				languageView.getResourceString(LanguageView.LANGUAGE));
		lblLanguage.setBounds(27, 188, 73, 35);
		frmSettings.getContentPane().add(lblLanguage);

		comboBox = new JComboBox<String>();
		comboBox.addItem("ENGLISH");
		comboBox.addItem("DEUTSCH");
		comboBox.setBounds(149, 192, 133, 26);

		if (this.startViewSettingsData.getLanguage().equals(
				LanguageView.ENGLISH))
			comboBox.setSelectedIndex(0);
		else
			comboBox.setSelectedIndex(1);
		frmSettings.getContentPane().add(comboBox);

		btnSave = new JButton(languageView.getResourceString(LanguageView.SAVE));
		btnSave.setBounds(27, 241, 100, 28);
		btnSave.setMnemonic(KeyEvent.VK_S);

		gameSoundPlayer.setMnemonic(KeyEvent.VK_M);
		// add didi
		frmSettings.addWindowListener(new WindowListener()
		{

			@Override
			public void windowOpened(WindowEvent e)
			{
				gameSoundPlayer.stopBackGroundSounds();
				gameSoundPlayer
						.startBackgroundSound(GameSoundPlayer.SOUND_SETTING_WAV);
			}

			@Override
			public void windowClosing(WindowEvent e)
			{
				gameSoundPlayer.stopBackGroundSounds();
				gameSoundPlayer
						.startBackgroundSound(GameSoundPlayer.SOUND_MENUE_WAV);
			}

			@Override
			public void windowIconified(WindowEvent e)
			{

			}

			@Override
			public void windowDeiconified(WindowEvent e)
			{

			}

			@Override
			public void windowDeactivated(WindowEvent e)
			{

			}

			@Override
			public void windowClosed(WindowEvent e)
			{

			}

			@Override
			public void windowActivated(WindowEvent e)
			{

			}
		});

		btnSave.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						&& btnSave.isFocusOwner() == true)
				{
					saveSettingsIfValideAndWriteToFile();
				}
				else if (e.getKeyCode() == KeyEvent.VK_M)
				{
					gameSoundPlayer.turnSoundOnOrOFF();
				}
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					gameSoundPlayer.stopBackGroundSounds();
					gameSoundPlayer
							.startBackgroundSound(GameSoundPlayer.SOUND_MENUE_WAV);
					frmSettings.dispose();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{

			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{

			}

		});
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveSettingsIfValideAndWriteToFile();
			}
		});

		gameSoundPlayer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gameSoundPlayer.turnSoundOnOrOFF();
			}
		});
		frmSettings.getContentPane().add(gameSoundPlayer);

		frmSettings.getContentPane().add(btnSave);
		frmSettings.setBounds(100, 100, 354, 303);

		frmSettings.setVisible(true);
	}

	private void saveSettingsIfValideAndWriteToFile()
	{
		if (checkIfSettingAreValid())
		{
			startViewSettingsData.setIpAddress(txtIPAddress.getText());
			startViewSettingsData.setPort(spinnerPort.getValue().toString());
			startViewSettingsData.setMode(getHostState());
			startViewSettingsData.setLanguage(comboBox.getSelectedItem()
					.toString());
			startViewSettingsData.writeSettingsToFile();
			Logging.writeInfoMessage("changed Start View Settings");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid Values",
					"Settings Values are not valid", JOptionPane.PLAIN_MESSAGE);
		}
	}

	protected boolean checkIfSettingAreValid()
	{
		if (!(txtIPAddress.getText().equals("localhost") || txtIPAddress
				.getText().trim().length() == GlobalValues.IP_ADDRESS_LENGHT))
		{
			return false;
		}
		if (!((int) spinnerPort.getValue() >= 8000 && (int) spinnerPort
				.getValue() <= 8500))
		{
			return false;
		}

		return true;
	}

	private String getHostState()
	{
		if (chckbxNewCheckBox.isSelected())
		{
			return "host";
		}
		else
		{
			return "client";
		}
	}
}
