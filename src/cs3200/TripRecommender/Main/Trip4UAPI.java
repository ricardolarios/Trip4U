package cs3200.TripRecommender.Main;

import cs3200.TripRecommender.Data.Attraction;
import cs3200.TripRecommender.Data.User;
import cs3200.TripRecommender.Data.Type;

import java.util.HashMap;
import java.util.List;


public interface Trip4UAPI {

  /**
   * Get the cost IDs and their descriptions.
   * @return a map from ID to description.
   */
  public HashMap<Integer, String> getCostMap();

  /**
   * Get user data with the given name.
   * @param name the name of the user.
   * @return the user object.
   */
  public User getUser(String name);

  /**
   * Creates a new user with the given User object.
   * @param u The user to be inserted
   */
  public void insertUser(User u);

  /**
   * If the given user does not have the given type, adds it to their preferences.
   * If the given user already has the given type, updates it with the given level of preference.
   *
   * @param u The user that is being updated
   * @param pref The type that will be assigned to the user
   * @param level The level of preference for the given type
   */
  public void updatePreference(User u, Type pref, Integer level);


  /**
  Adds a review to the database.
  * @param u the user making the review
  * @param a the attraction being reviewed
  * @param rating the user's rating of the attraction out of 5
  * @param comment the user's comment about the attraction
  */
  public void addReview(User u, Attraction a, Integer rating, String comment);

  /**
   * Gets and displays a list of types that a user can choose from to add to their preferences.
   *
   * @return A list of all of the current preferences that are available to pick from.
   */
  public List<Type> getAllTypes();

  /**
  * Gets and displays a list of attractions that a user can choose from to review.
  *
  * @return A list of all the current attractions currently present in the database.
  */
  public List<Attraction> getAllAttractions() {

  /**
   * Get a list of recommendations for the given user at the given location.
   * @param u the user.
   * @param location the location of the recommendations.
   * @param max the maximum number of recommendations to output.
   */
  public List<Attraction> getRecommendations(User u, String location, Integer max);

  /**
   * Set connection settings.
   * @param user the user to sign in as.
   * @param password the password to authenticate connection with user.
   */
  public void authenticate(String user, String password);

  /**
   * Close the connection after the use of database is over.
   */
  public void closeConnection();

}
