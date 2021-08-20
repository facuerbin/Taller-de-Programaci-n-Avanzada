package main;

import entidades.Cliente;
import entidades.Domicilio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenciaApp {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenciaAppPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            /*
            Cliente cliente = new Cliente("Facu", "Erbin", 12345678);
            Domicilio domicilio = new Domicilio("San Juan", 2400);
            domicilio.setCliente(cliente);
            cliente.setDomicilio(domicilio);

            entityManager.persist(cliente);
            */

            Domicilio domicilio = entityManager.find(Domicilio.class, 1L);
            Cliente cliente = entityManager.find(Cliente.class, 1L);

            System.out.println(domicilio.getCliente().getDni());
            System.out.println(cliente.getDomicilio().getNombreCalle());

            entityManager.flush();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e.toString());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
