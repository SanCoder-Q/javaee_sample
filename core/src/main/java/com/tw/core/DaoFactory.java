package com.tw.core;

import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by SanCoder on 7/9/15.
 */
public class DaoFactory {

    private static DaoFactory factory;
    private static SessionFactory sf;

    public static DaoFactory getDaoFactory() {
        if (factory == null)
            factory = new DaoFactory();
        return factory;
    }

    private DaoFactory() {
        if (sf == null) {
//            Configuration con = new Configuration().configure();
//            System.out.println(con.getProperty("dialect"));
//            Properties setting = con.getProperties();
//            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(setting);
            //StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder(new BootstrapServiceRegistryBuilder().enableAutoClose().build()).applySettings(setting);
            sf = new Configuration().buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
        }
    }

    public static UserDao userDao() throws SQLException, ClassNotFoundException {
        return new UserDao(sf);
    }
}
