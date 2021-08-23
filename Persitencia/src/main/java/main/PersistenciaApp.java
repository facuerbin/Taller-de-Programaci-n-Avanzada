package main;

import entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenciaApp {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenciaAppPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Creo un cliente
            Cliente cliente = new Cliente("Facu", "Erbin", 12345678);
            Domicilio domicilio = new Domicilio("San Juan", 2400);
            domicilio.setCliente(cliente);
            cliente.setDomicilio(domicilio);

            entityManager.persist(cliente);

            // Busco al cliente
            /*
            Domicilio domicilio = entityManager.find(Domicilio.class, 1L);
            Cliente cliente = entityManager.find(Cliente.class, 1L);
            System.out.println(domicilio.getCliente().getDni());
            System.out.println(cliente.getDomicilio().getNombreCalle());
            */

            // Creo una Factura
            Factura factura = new Factura();

            factura.setNumero(12);
            factura.setFecha("23/08/2021");
            factura.setCliente(cliente);

            // Creo categorias
            Categoria perecederos = new Categoria("Perecederos");
            Categoria lacteos = new Categoria("Lacteos");
            Categoria limpieza = new Categoria("Limpieza");

            // Creo Artículos
            Articulo articulo1 = new Articulo(200, "Yogurt Ser", 20);
            articulo1.getCategorias().add(lacteos);
            articulo1.getCategorias().add(perecederos);
            lacteos.getArticulos().add(articulo1);
            perecederos.getArticulos().add(articulo1);

            Articulo articulo2 = new Articulo(300, "Detergente Magistrañ", 80);
            articulo2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(articulo2);

            // Creo Detalles de Factura
            DetalleFactura detalle1 = new DetalleFactura();
            detalle1.setArticulo(articulo1);
            detalle1.setCantidad(2);
            detalle1.setSubTotal(articulo1.getPrecio() * detalle1.getCantidad());

            articulo1.getDetalleFacturas().add(detalle1);
            factura.getDetalles().add(detalle1);
            detalle1.setFactura(factura);

            DetalleFactura detalle2 = new DetalleFactura(
                    1,
                    1 * articulo2.getPrecio(),
                    articulo2,
                    factura
                    );
            articulo2.getDetalleFacturas().add(detalle2);
            factura.getDetalles().add(detalle2);

            factura.setTotal(
                    detalle1.getSubTotal() + detalle2.getSubTotal()
            );

            entityManager.persist(factura);


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
