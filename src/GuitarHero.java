import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] guitarStrings =new GuitarString[keyboard.length()];
        for (int i = 0; i < guitarStrings.length; i++) {
            guitarStrings[i] = new GuitarString(440 * Math.pow( 1.05956, (i - 24)));
        }

        while (true) {
            // check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();

                if(keyboard.indexOf(key) != -1)guitarStrings[keyboard.indexOf(key)].pluck();
            }
            // compute the superposition of samples

            double sample = Arrays.stream(guitarStrings).mapToDouble(GuitarString::sample).sum();

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            Arrays.stream(guitarStrings).forEach(GuitarString::tic);
        }
    }
}
