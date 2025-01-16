package org.example.travelagency.Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.example.travelagency.Cliente;

import java.util.ArrayList;

public class ClienteManager {
    public static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("Persistencia");

    public static void insertCliente(Cliente cliente){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();
        manager.close();
    }
    public static void updateCliente(Cliente cliente){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(cliente);
        manager.getTransaction().commit();
        manager.close();
    }
    public static void deleteCliente(Cliente cliente){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Cliente c = manager.find(Cliente.class,cliente.getId());
        manager.remove(c);
        manager.getTransaction().commit();
        manager.close();
    }
    public static String getNombre(int id){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Cliente e = manager.find(Cliente.class, id);
        manager.getTransaction().commit();
        manager.close();
        return e.getNombre();
    }
    public static Cliente getClienteByNombre(String name){  //JPQL
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        //no es la tabla es la clase :D
        TypedQuery< Cliente> query =
                manager.createQuery("FROM Cliente where nombre = :nombre", Cliente.class);
        query.setParameter("nombre", name);
        Cliente e  = query.getSingleResult();
        //Entrenador e = query.getResultList().stream().findFirst().orElse(null);
        manager.getTransaction().commit();
        manager.close();
        return e;
    }

    public static ArrayList<Cliente> getCliente(){  //JPQL
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        TypedQuery<Cliente> query =
                manager.createQuery("FROM Cliente", Cliente.class);
        ArrayList<Cliente> listado  = new ArrayList<>(query.getResultList());
        manager.getTransaction().commit();
        manager.close();
        return listado;
    }



}
