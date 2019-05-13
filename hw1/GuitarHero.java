import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] gsarray = new GuitarString[37];

        for (int i = 0; i < 37; i++) {
            double fre = 440 * Math.pow(2, (i - 24) / 12);
            gsarray[i] = new GuitarString(fre);
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index < 37) {
                    gsarray[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < 37; i++) {
                sample = sample + gsarray[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; i++) {
                gsarray[i].tic();
            }

        }

    }
}
