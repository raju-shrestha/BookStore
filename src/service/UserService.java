package service;

import dbUtils.DBConnection;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private String table_name = "user";
    Connection connection = null;

    public UserService(){

        connection = new DBConnection().getConnection();

    }


    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();

        String query = "SELECT * FROM " + table_name; //query
        PreparedStatement statement = null;

        //create preparedStatement
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //run the preparedstatement query
        try {
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                User user = new User(); //create new book object in each loop and add that object to list
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection); //finally close the connection to db
        }

        return userList;
    }

    /**
     * Save to database
     */
    public boolean saveUser(User user) {
        String query = "INSERT INTO " + table_name + "( username ,password) VALUES (?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }
        System.out.println("query = " + query);
        return false;
    }

    /**
     * Get Info to Update particular book
     */

    public User getUserInfo(String id) {
        try {
            User userinfo = new User(); //List is not used here as we will get only one record with given id
            String query = "SELECT * FROM " + table_name + " WHERE id=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                userinfo.setUsername(rs.getString("username"));
                userinfo.setPassword(rs.getString("password"));
            }
            return userinfo;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }

        return null;
    }
    /**
     * Updating the existing record in db
     */

    public boolean updateRecord(User user) {
        try {
            String query = "UPDATE " + table_name + " SET username=?, password=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ;
            return preparedStatement.executeUpdate() != 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            new DBConnection().closeConnection(connection);
        }

        return false;
    }

    /**
     * Delete record from db
     *
     * @param id
     * @return
     */

    public boolean deleteUser(String id) {
        try {
            String query = "DELETE FROM " + table_name + " WHERE id =?;";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, id);
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new DBConnection().closeConnection(connection);
        }

        return false;
    }
}