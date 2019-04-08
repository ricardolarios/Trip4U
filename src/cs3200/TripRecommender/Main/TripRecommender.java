package cs3200.TripRecommender.Main;

import cs3200.TripRecommender.Database.DBUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.*;

/**
 * Entry point for trip recommendations.
 */
public class TripRecommender {

    private static Parser parser = new Parser();
    private static DBUtils utils = new DBUtils("jdbc:mysql://localhost:3306/Trip4U?serverTimezone=EST5EDT",
            "User","Password");

    /**
     * Entry point for program.
     * @param args arguments supplied when program is executed.
     * @throws Exception exception through buffer reader.
     */
    public static void main(String[] args) throws Exception {

        String sql = "select * from destination";

        try {
            // get connection and initialize statement
            Connection con = utils.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next() != false) {
                System.out.println(rs.getInt("destination_id") + ", " +
                        rs.getString("city") + ", " + rs.getString("country"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

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
