package com.delivery.database;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

/*
 * Listener to set up data source
 */
public class DataSourceListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(DataSourceListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBUtils.initDataSource(getDataSource());
    }

    private DataSource getDataSource() throws IllegalStateException {
        DataSource ds;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/DB");
        } catch (NamingException e){
            String message = "Can't initialise data source";
            log.error(message + ". " + e.getMessage());
            throw new IllegalStateException(message, e);
        }
        return ds;
    }

}
