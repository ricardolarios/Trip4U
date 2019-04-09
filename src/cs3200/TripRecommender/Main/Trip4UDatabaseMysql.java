package cs3200.TripRecommender.Main;

import cs3200.TripRecommender.Data.Attraction;
import cs3200.TripRecommender.Data.Type;
import cs3200.TripRecommender.Data.User;
import cs3200.TripRecommender.Database.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an implementation of database API.
 */
public class Trip4UDatabaseMysql implements Trip4UAPI {

    DBUtils dbu;

    @Override
    public void insertUser(User u) {

    }

    @Override
    public void updatePreference(User u, Type pref, Integer level) {

    }

    @Override
    public List<Type> getAllTypes() {
        List<Type> types = new ArrayList<Type>();

        String sql = "Select * from type";

        try {
            Connection con = dbu.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next() != false) {
                Type importType = new Type();
                importType.setId(rs.getInt("type_id"));
                importType.setName(rs.getString("type_name"));
                types.add(importType);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return types;
    }

    @Override
    public List<Attraction> getRecommendations(User u, String location) {
        return null;
    }

    @Override
    public void authenticate(String user, String password) {
        this.dbu = new DBUtils("jdbc:mysql://localhost:3306/Trip4U?serverTimezone=EST5EDT",
                user,password);
    }

    @Override
    public void closeConnection() {
        dbu.closeConnection();
    }
}
