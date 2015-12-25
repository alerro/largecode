package largetest.rawconnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class Connections {

  /** Uses JNDI and Datasource (preferred style).   */
  public static Connection getJNDIConnection(){
      try {
          String DATASOURCE_CONTEXT = "java:comp/env/jdbc/LargetestDataSource";

          try {
            Context initialContext = new InitialContext();
            if ( initialContext == null){
              log("JNDI problem. Cannot get InitialContext.");
            }
            DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                Connection connection = datasource.getConnection();
                return connection;
            }
            else {
              log("Failed to lookup datasource.");
            }
          }
          catch ( NamingException ex ) {
            log("Cannot get connection: " + ex);
          }
          catch(SQLException ex){
            log("Cannot get connection: " + ex);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
  }

  private static void log(Object aObject){
    System.out.println(aObject);
  }
}



