package cs3200.TripRecommender.Main;

import cs3200.TripRecommender.Data.Attraction;
import cs3200.TripRecommender.Data.User;
import cs3200.TripRecommender.Data.Type;

import java.util.List;

public interface Trip4UAPI {

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
   * Gets and displays a list of types that a user can choose from to add to their preferences.
   *
   * @return A list of all of the current preferences that are available to pick from.
   */
  public List<Type> getAllTypes();

  /**
   * Get a list of recommendations for the given user at the given location.
   * @param u the user.
   * @param location the location of the recommendations.
   */
  public List<Attraction> getRecommendations(User u, String location);

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
