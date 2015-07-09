package com.tw.core;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class Service {
    public String service() {

        try {
            SessionFactory sf =
                    new Configuration().configure().buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();

            for (int i = 0; i < 5; i++) {
                User user = new User("H测试", 1, "mail@no.com", 1);
                session.save(user);
            }

            tx.commit();
            session.close();

        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return "Hello World";
    }
}