package producingSimpleSounds;

import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Sound {
	// How many samples are played per second?
	// 44.1kHz(Music CD) - 48kHz(Movie) - 96kHz(Studio)
	final static float SAMPLERATE = 1000 * 96;
	// Amount of bits per sample (2^Samplesize)
	final static int SAMPLESIZE = 8;

	final static int DEFAULTSOUNDLENGTH = 35000;
	
	// Notes
	final static int Eh = 146;
	final static int B = 195;
	final static int G = 245;
	final static int D = 328;
	final static int A = 437;
	final static int El = 560;

	int[] samples;
	int soundLength;

	/**
	 * 
	 * @param samples Sound which is played. A sound consists of one or multiple samples
	 * @param length How long the sound is played for (higher number = longer)
	 */
	public Sound(int[] samples, int length) {
		this.samples = samples;
		this.soundLength = length;
	}

	/**
	 * plays a sound 
	 * @param sounds list of sounds to be played
	 * @throws LineUnavailableException
	 */
	static void playSounds(ArrayList<Sound> sounds) throws LineUnavailableException {
		byte[] buf = new byte[5];
		AudioFormat af = new AudioFormat((float) SAMPLERATE, SAMPLESIZE, 1, true, true);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open();
		sdl.start();

		for (Sound currentSound : sounds) {
			for (int i = 0; i < (float) currentSound.soundLength; i++) {
				for (int j = 0; j < currentSound.samples.length; j++) {
					double angle = i/((float) currentSound.samples[j]) * 2 * Math.PI;
					buf[0] = (byte) (Math.sin(angle) * 100);
					sdl.write(buf, 0, 2);
					System.out.println(buf[0]);
				}
			}
		}

		sdl.drain();
		sdl.stop();
	}
	
	

}
