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
import java.util.HashMap;
import java.util.List;

/**
 * Represents an implementation of database API.
 */
public class Trip4UDatabaseMysql implements Trip4UAPI {

    DBUtils dbu;

    @Override
    public HashMap<Integer, String> getCostMap() {
        HashMap<Integer, String> costMap = new HashMap<>();

        String sql = "Select * from cost";

        try {
            Connection con = dbu.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next() != false) {
                costMap.put(rs.getInt("cost_id"), rs.getString("description"));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return costMap;
    }

    @Override
    public User getUser(String name) {
        User u = null;

        String sql = "Select * from user where name like \'%" + name + "%\'";

        try {
            Connection con = dbu.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next() != false) {
                u = new User();
                u.setAge(rs.getInt("age"));
                u.setId(rs.getInt("user_id"));
                u.setName(rs.getString("name"));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        if (u == null) {
            throw new IllegalArgumentException("No user matching that name.");
        }

        return u;
    }

    @Override
    public void insertUser(User u) {

    }

    @Override
    public void updatePreference(User u, Type pref, Integer level) {

        String sql = "Insert into user_has_preference (user_id, preference_id, level) values"
                + " (" + Integer.toString(u.getId()) +", " + Integer.toString(pref.getId())
                + ", " + level.toString() + " on duplicate key update level = "
                + level.toString();
        try {
            Connection con = dbu.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
            System.out.println("Preference has been updated.");
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
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
    public List<Attraction> getRecommendations(User u, String location, Integer max) {
        List<Attraction> attractions = new ArrayList<>();

        String sql = "Select a.name, a.description, a.cost_id from user u join user_has_preference using (user_id)"
                + " join type on (preference_id = type_id)"
                + " join attraction a using (type_id)"
                + " join destination d using (destination_id)"
                + " where (city like \'%" + location + "%\'"
                + " or country like \'%" + location + "%\')"
                + " and u.name = \'" + u.getName() +"\'"
                + " order by level desc limit " + max.toString();

        try {
            Connection con = dbu.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next() != false) {
                Attraction a = new Attraction();
                a.setName(rs.getString("name"));
                a.setDescription(rs.getString("description"));
                a.setCostID(rs.getInt("cost_id"));
                attractions.add(a);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return attractions;
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
