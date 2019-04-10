package cs3200.TripRecommender.Main;

import cs3200.TripRecommender.Data.Attraction;
import cs3200.TripRecommender.Data.Type;
import cs3200.TripRecommender.Data.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Entry point for trip recommendations.
 */
public class TripRecommender {

    private static Parser parser = new Parser();
    private static Trip4UAPI connector = new Trip4UDatabaseMysql();
    private static User user = null;

    /**
     * Entry point for program.
     * @param args arguments supplied when program is executed.
     * @throws Exception exception through buffer reader.
     */
    public static void main(String[] args) throws Exception {

        // authenticate the connection with the database
        connector.authenticate("User", "Password");

        // set up a reader to take in user input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;

        System.out.println("Please enter user information.");

        while (user == null) {
            input = reader.readLine();

            if (input.length() == 0) {
                System.out.println("I told you to give me a user!");
            }
            else {
                setUser(input);
            }
        }

        // run a loop to take in user input
        while(true) {
            input = reader.readLine();

            // if the user wants to quit they can type quit
            if (input.equalsIgnoreCase("quit")) {
                reader.close();
                shutdown();
            }

            // split up the input into an array
            String[] firstInput = input.split("-");
            String[] inputArray = Arrays.copyOfRange(firstInput, 1, firstInput.length);
            for (int i = 0; i < inputArray.length; i++) {
                String s = inputArray[i];
                inputArray[i] = s.trim();
            }

            // if they enter nothing then nothing should happen
            if (inputArray.length == 0) {
                System.out.println("I mean that's cool I guess.");
            }
            else {
                switch (inputArray[0].toLowerCase()) {

                    case "show":
                        showSomething(inputArray);
                        break;
                    case "user":
                        updateUser(inputArray);
                        break;
                    case "edit":
                        editSomething(inputArray);
                        break;
                    default:
                        System.out.println("I'm not smart enough for that.");
                        break;
                }
            }


        }
    }

    /**
     * Update the current user.
     * @param inputArray the information entered by the user
     */
    private static void updateUser(String[] inputArray) {
        if (inputArray.length <= 1) {
            System.out.println("Well you have to give me a user to switch to!");
        }
        else {
            setUser(inputArray[1]);
        }
    }

    /**
     * Set the user to the one with the given name.
     * @param name the name of the new user.
     */
    private static void setUser(String name) {
        try {
            user = connector.getUser(name);
            System.out.println("Now operating as user: " + user.getName());
        }
        catch (IllegalArgumentException e) {
            System.out.println("That user doesn't exist.");
        }
    }

    /**
     * Closes the program.
     */
    private static void shutdown() {
        System.out.println("Shutting down.");
        connector.closeConnection();
        System.exit(0);
    }

    /**
     * Display some information to the user
     * @param inputArray the information the user wants to show
     */
    private static void showSomething(String[] inputArray) {

        List<Attraction> attractions = new ArrayList<Attraction>();
        if (inputArray.length <= 1) {
            System.out.println("Well I mean, you have to give me something to show.");
        }
        else {

            switch (inputArray[1].toLowerCase()) {

                case "recommendations":

                    switch (inputArray.length) {

                        case 3:
                            attractions = connector.getRecommendations(user, inputArray[2], 100);
                            break;
                        case 4:
                            attractions = connector.getRecommendations(user, inputArray[2],
                                    Integer.parseInt(inputArray[3]));
                            break;
                        default:
                            System.out.println("Not enough information.");
                            break;
                    }

                    HashMap<Integer, String> costMap = connector.getCostMap();
                    System.out.println("");
                    for (Attraction a : attractions) {
                        System.out.println(a.toString(costMap));
                        System.out.println("");
                    }
                    break;
                case "types":
                    List<Type> types = connector.getAllTypes();
                    for (Type t : types) {
                        System.out.println(t.getName());
                    }
                    break;
                default:
                    System.out.println("idk");
                    break;
            }

        }
    }

    /**
     * Edit something according to the user.
     * @param inputArray the information the user entered.
     */
    private static void editSomething(String[] inputArray) {
        if (inputArray.length <= 1 ) {
            System.out.println("Well you need to give me something to edit!");
        }
        else {
            // need to implement editing preferences here
        }
    }
}
