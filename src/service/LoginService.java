package service;

import dbUtils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginService {

    private String table_name = "user";
    Connection connection = null;

    public LoginService() {
        connection = new DBConnection().getConnection();
    }

    public Boolean getUserInfo(String username, String password) throws SQLException {
        try {
            String query = "SELECT * FROM " + table_name + "WHERE username=? & password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            new DBConnection().closeConnection(connection);
        }
        return false;

    }
}