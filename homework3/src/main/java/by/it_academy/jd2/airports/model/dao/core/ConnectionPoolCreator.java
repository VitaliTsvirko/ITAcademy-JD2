package by.it_academy.jd2.airports.model.dao.core;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Vitali Tsvirko
 */
public class ConnectionPoolCreator {
    private static volatile ConnectionPoolCreator instance;
    private final ComboPooledDataSource cpds;

    private ConnectionPoolCreator() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.postgresql.Driver");
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/demo");
        cpds.setUser("postgres");
        cpds.setPassword("postgres");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(5);
        cpds.setMaxStatements(5);
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
