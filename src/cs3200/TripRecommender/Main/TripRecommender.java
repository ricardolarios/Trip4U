package cs3200.TripRecommender.Main;

import cs3200.TripRecommender.Data.Type;
import cs3200.TripRecommender.Database.DBUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.*;
import java.util.List;

/**
 * Entry point for trip recommendations.
 */
public class TripRecommender {

    private static Parser parser = new Parser();
    private static Trip4UAPI connector = new Trip4UDatabaseMysql();

    /**
     * Entry point for program.
     * @param args arguments supplied when program is executed.
     * @throws Exception exception through buffer reader.
     */
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;

        connector.authenticate("User", "Password");
        List<Type> allTypes = connector.getAllTypes();

        for (Type t : allTypes) {
            System.out.println(t.getName());
        }


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
