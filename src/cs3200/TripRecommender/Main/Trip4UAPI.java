package cs3200.TripRecommender.Main;

import java.util.List;

public interface Trip4UAPI {

  /**
   * Creates a new user with the given User object.
   * @param u The user to be inserted
   */
  void insertUser(User u);

  /**
   * If the given user does not have the given type, adds it to their preferences.
   * If the given user already has the given type, updates it with the given level of preference.
   *
   * @param u The user that is being updated
   * @param pref The type that will be assigned to the user
   * @param level The level of preference for the given type
   */
  void udpatePreference(User u, Type pref, Integer level);

  /**
   * Gets a list of types that a user can choose from to add to their preferences.
   *
   * @return A list of all of the current preferences that are available to pick from.
   */
  List<String> getAllTypes();

  /**
   *
   */
  List<...> getRecommendations(...);

}
