package largetest.rawconnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public final class Connections {
    private final static Logger logger = LoggerFactory.getLogger(Connections.class);

  /** Uses JNDI and Datasource (preferred style).   */
  public static Connection getJNDIConnection(){
      try {
          logger.debug("Get JNDIConnection.");
          String DATASOURCE_CONTEXT = "java:comp/env/jdbc/LargetestDataSource";

          try {
            Context initialContext = new InitialContext();
            if ( initialContext == null){
                logger.error("JNDI problem. Cannot get InitialContext.");
            }
            DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                Connection connection = datasource.getConnection();
                return connection;
            }
            else {
                logger.error("Failed to lookup datasource.");
            }
          }
          catch ( NamingException ex ) {
              logger.error("Cannot get connection: " + ex);
          }
          catch(SQLException ex){
              logger.error("Cannot get connection: " + ex);
          }
      } catch (Exception e) {
          logger.error("Exception -> ",e);
      }
      return null;
  }
}



