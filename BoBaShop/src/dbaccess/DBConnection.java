
package dbaccess;





import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    
    private static final String DRIVER="org.apache.derby.jdbc.ClientDriver";
    private static final String URI="jdbc:derby://localhost:1527/Stock";
    private static final String USERNAME="app";
    private static final String PASSWORD="app";
    
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn=DriverManager.getConnection(URI, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return conn;
    }
    
}
