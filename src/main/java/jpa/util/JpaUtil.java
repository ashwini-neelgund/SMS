package jpa.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory factory;
    private static final String NAME = "SMS"; // Persistence unit name

    /**
     * Creates and returns EntityManagerFactory for the Persistence unit
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory(){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(NAME);
        return factory;
    }

    public static void shutdown(){
        if(factory != null)
            factory.close();
    }

}
