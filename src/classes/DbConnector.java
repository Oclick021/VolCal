package classes;

import java.sql.*;
import java.util.Vector;

public class DbConnector {

    public DbConnector() {

    }

    public Vector<String> get(String query) {
        try {
            // create our mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/shapes";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            Vector<String> temp = new Vector<String>();
            while (rs.next()) {
                String item = "Cube" + "|" + rs.getString("height") + "|" + rs.getString("lenght") + "|" + rs.getString("width");
                temp.add(item);

            }
            return  temp;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
