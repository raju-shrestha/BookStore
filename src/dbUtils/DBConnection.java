package dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBConnection {
    Connection connection = null;
    private  String DB_URL ="jdbc:mysql://localhost:3306/bookstore";
    private  String DB_USER ="root";
    private  String DB_PASSWORD ="";

    /**
     *
     * @return Connection
     */
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Method to close the connection
     * @param connection
     */
    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/*    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();

            if(dbConnection.getConnection()!=null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Opps!!! Some Error Occurred!!! \n");
            }
        System.out.println("new SimpleDateFormat(\"yyyy-MM-dd\").format(new Date()) = " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }*/
}
