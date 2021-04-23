package by.it_academy.jd2.airports.dao.core;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

/**
 * @author Vitali Tsvirko
 */
public class ConnectionPoolCreator {
    private static volatile ConnectionPoolCreator instance;
    private final ComboPooledDataSource cpds;

    private ConnectionPoolCreator() throws PropertyVetoException {
        ResourceBundle resource = ResourceBundle.getBundle("database");

        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(resource.getString("db.driver"));
        cpds.setJdbcUrl(resource.getString("db.url"));
        cpds.setUser(resource.getString("db.user"));
        cpds.setPassword(resource.getString("db.password"));
        cpds.setMinPoolSize(Integer.parseInt(resource.getString("connectionPoolCreator.minPoolSize")));
        cpds.setAcquireIncrement(Integer.parseInt(resource.getString("connectionPoolCreator.acquireIncrement")));
        cpds.setMaxPoolSize(Integer.parseInt(resource.getString("connectionPoolCreator.maxPoolSize")));
        cpds.setMaxStatements(Integer.parseInt(resource.getString("connectionPoolCreator.maxStatements")));
    }

    public static DataSource getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (ConnectionPoolCreator.class){
                if (instance == null) {
                    instance = new ConnectionPoolCreator();
                }
            }
        }

        return instance.cpds;
    }
}
