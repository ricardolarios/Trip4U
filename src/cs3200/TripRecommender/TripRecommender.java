package cs3200.TripRecommender;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Entry point for trip recommendations.
 */
public class TripRecommender {

    public static Parser parser = new Parser();

    /**
     * Entry point for program.
     * @param args arguments supplied when program is executed.
     * @throws Exception exception through buffer reader.
     */
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;

        while(true) {
            input = reader.readLine();

            if (input.equalsIgnoreCase("quit")) {
                reader.close();
                System.out.println("Shutting down.");
                System.exit(0);
            }

            parser.parse(input);
        }
    }
}
