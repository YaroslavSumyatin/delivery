package com.delivery.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class DataSourceListener implements ServletContextListener {

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
            System.err.println(e.getMessage());
            throw new IllegalStateException("Can't initialise data source", e);
        }
        return ds;
    }

}
