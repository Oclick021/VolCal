package classes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnector {

    private String myDriver = "com.mysql.jdbc.Driver";
    private String myUrl = "jdbc:mysql://localhost/shapes?autoReconnect=true&useSSL=false";
    private String userName = "root";
    private String passWord = "";

    public DbConnector() {

    }
    public  boolean isDbConnected() {
        //final String CHECK_SQL_QUERY = "SELECT 1";
        try {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, userName, passWord) ;
            if(!conn.isClosed() || conn!=null){
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Connection with database failed!");
            return false;
        }
        return false;
    }

    public ResultSet get(String query) {
        try {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, userName, passWord) ;
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            return rs;
        } catch (Exception e) {



        }
        return null;
    }

    public void insert(String query) {
        try {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, userName, passWord);
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            st.executeUpdate(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Mysql connection error");

            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }
}
