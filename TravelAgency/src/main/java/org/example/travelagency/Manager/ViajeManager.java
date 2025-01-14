package org.example.travelagency.Manager;

import org.example.travelagency.Cliente;
import org.example.travelagency.Viaje;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ViajeManager {

    public static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("Persistencia");
    public static void insertViaje(Viaje viaje){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(viaje);
        manager.getTransaction().commit();
        manager.close();
    }
    public static void updateViaje(Viaje viaje){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(viaje);
        manager.getTransaction().commit();
        manager.close();
    }
    public static void deleteViaje(Viaje viaje){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Viaje c = manager.find(Viaje.class,viaje.getId());
        manager.remove(c);
        manager.getTransaction().commit();
        manager.close();
    }
    public static String getNombre(int id){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Viaje e = manager.find(Viaje.class, id);
        manager.getTransaction().commit();
        manager.close();
        return e.getPais();
    }
    public static Viaje getViajeByNombre(String name){  //JPQL
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        //no es la tabla es la clase :D
        TypedQuery< Viaje> query =
                manager.createQuery("FROM viajes where nombre = :nombre", Viaje.class);
        query.setParameter("nombre", name);
        Viaje e  = query.getSingleResult();
        //Entrenador e = query.getResultList().stream().findFirst().orElse(null);
        manager.getTransaction().commit();
        manager.close();
        return e;
    }

    public static ArrayList<Viaje> getViaje(){  //JPQL
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        TypedQuery<Viaje> query =
                manager.createQuery("FROM viajes", Viaje.class);
        ArrayList<Viaje> listado  = new ArrayList<>(query.getResultList());
        manager.getTransaction().commit();
        manager.close();
        return listado;
    }
}
