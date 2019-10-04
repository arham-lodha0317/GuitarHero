import java.util.Random;

public class GuitarString {
    private RingBuffer<Double> guitarString;
    private int N;
    private int time;

    public GuitarString(double frequency) {
        N = (int) (44100 / frequency);
        guitarString = new RingBuffer<Double>(N);
        time = 0;

        for (int i = 0; i < N; i++) {
            guitarString.enqueue(0.);
        }
    }

    public GuitarString(double[] arr) {
        guitarString = new RingBuffer<>(arr.length);

        for (double x : arr) {
            guitarString.enqueue(x);
        }
    }

    public void pluck() {
        for (int i = 0; i < N; i++) {
            guitarString.dequeue();
        }
        for (int i = 0; i < N; i++) {
            guitarString.enqueue(Math.random() - .5);
        }
    }

    public void tic() {
        time++;
        double a = sample();
        guitarString.dequeue();
        double b = sample();

        guitarString.enqueue((a + b) / 2 * 0.994);
    }

    public double sample() {
        return guitarString.peek();
    }

    public int time() {
        return time;
    }

    public void pluckGaussian() {
        for (int i = 0; i < N; i++) {
            guitarString.dequeue();
        }

        Random random = new Random();
        for (int i = 0; i < N; i++) {
            guitarString.enqueue(random.nextGaussian() - .5);
        }
    }

    public void pluckNoise() {
        for (int i = 0; i < N; i++) {
            guitarString.dequeue();
        }

        ImprovedNoise noise = new ImprovedNoise();
        for (int i = 0; i < N; i++) {
            guitarString.enqueue(ImprovedNoise.noise(0, .5 , 1) - .5);
        }
    }

}
