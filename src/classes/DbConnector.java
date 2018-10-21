package classes;

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
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
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
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }
}
