package producingSimpleSounds;

import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Main {
	
	public static void main(String[] args) throws LineUnavailableException {
		ArrayList<Sound> sounds = new ArrayList<Sound>();
		
		int[] sample = new int[] { Sound.B };
		int[] sample2 = new int[] { Sound.Eh };
		int[] sample3 = new int[] { Sound.G };

		sounds.add(new Sound(sample, 40000));
		sounds.add(new Sound(sample2, 45000));
		sounds.add(new Sound(sample3, 40000));
		
		Sound.playSounds(sounds);
		
	}

}
