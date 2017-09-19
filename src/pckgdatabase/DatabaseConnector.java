
package pckgdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {
   
    public static Connection databaseConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
             Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","system");
        
        return connection;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return null;
    
    }
   
        
    
}
