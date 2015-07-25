package gameSounds;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;

public class GameSoundPlayer extends JButton
{
	// Didi ADD
	public final static String SOUND_MENUE_WAV = "sound_menue.wav";
	public final static String SOUND_SETTING_WAV = "sound_settings.wav";
	public final static String SOUND_GAME_WAV = "sound_game.wav";
	public final static String SOUND_CANNON_HIT = "sound_cannon_hit";
	public final static String SOUND_CANNON_MISS = "sound_cannon_miss";

	// public final static String SOUND_GAME_WAV = "";

	private enum SoundTypes
	{
		BOMB, BACKGROUND, WATERSHOOT;
	}

	private Clip soundClip;
	private boolean soundIsOn;
	private String currentWavFileNameToPlay;

	public GameSoundPlayer()// todo argument titel for currentWavFileNameToPlay
	{
		soundIsOn = true;
		currentWavFileNameToPlay = GameSoundPlayer.SOUND_MENUE_WAV;
		startBackgroundSound(GameSoundPlayer.SOUND_MENUE_WAV);
	}

	public void startBackgroundSound(String wavFilename)
	{
		currentWavFileNameToPlay = wavFilename;
		if (soundIsOn)
		{

			synchronized (wavFilename)
			{
				try
				{
					this.soundClip = AudioSystem.getClip();
					this.soundClip.open(AudioSystem
							.getAudioInputStream(new File("sound\\"
									+ wavFilename)));
				}
				catch (Exception e)
				{
					// logger no file loading possible
					e.printStackTrace();
				}
				soundClip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}

	}

	public void startBombSound()
	{

	}

	public void stopBackGroundSounds()
	{
		soundClip.stop();
	}

	// Clip clip = AudioSystem.getClip();
	// clip.open(AudioSystem.getAudioInputStream(soundFileName));

	public void turnSoundOnOrOFF()
	{
		if (soundIsOn)
		{
			soundIsOn = false;
			stopBackGroundSounds();
		}
		else
		{
			soundIsOn = true;
			this.startBackgroundSound(this.currentWavFileNameToPlay);
		}
	}
}
