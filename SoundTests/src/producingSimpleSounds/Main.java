package producingSimpleSounds;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Main {
	// Wieviele Samples werden pro Sekunde abgespielt?
	// 44.1kHz(Musik CD) - 48kHz(Film) - 96kHz(Tonstudio)
	final static float SAMPLERATE = 1000 * 96;
	// Anzahl der Bits pro Sample
	final static int SAMPLESIZE = 16;

	/**
	 * plays a sample (one or multiple pitches at once)
	 * 
	 * @param sample
	 * @throws LineUnavailableException
	 */
	private static void playSample(int[] sample) throws LineUnavailableException {
		byte[] buf = new byte[2];
		AudioFormat af = new AudioFormat((float) SAMPLERATE, SAMPLESIZE, 1, true, true);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open();
		sdl.start();
		for (int i = 0; i < (float) SAMPLERATE/sample.length; i++) {
			for (int j = 0; j < sample.length; j++) {
				double angle = i / ((float) sample[j]) * 2 * Math.PI;
				buf[0] = (byte) (Math.sin(angle) * 100);
				sdl.write(buf, 0, 2);
			}
		}
		sdl.drain();
		sdl.stop();
	}

	public static void main(String[] args) throws LineUnavailableException {
		int[] sample = new int[]{ 180, 150 };

		for (int i = 0; i < 3; i++) {
			playSample(sample);
			sample[0] += 10;
		}
	
	}

}
