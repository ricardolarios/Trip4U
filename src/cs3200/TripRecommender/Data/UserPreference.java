package cs3200.TripRecommender.Data;

public class UserPreference {

    private int userID;
    private int preferenceID;
    private int level;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPreferenceID() {
        return preferenceID;
    }

    public void setPreferenceID(int preferenceID) {
        this.preferenceID = preferenceID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
