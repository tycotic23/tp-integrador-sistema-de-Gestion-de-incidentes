package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.ClientService;
import com.sistemaIncidentes.models.Service;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientServiceController {

    public void createClientService(Client client, Service service){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            ClientService clientService=new ClientService();
            session.beginTransaction();
            client.addService(clientService);
            service.addClient(clientService);
            session.persist(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente creado el servicio para el cliente ");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la creacion del servicio para el cliente");
        }


    }

    public void deleteClientService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            ClientService clientService = session.get(ClientService.class,id);
            session.remove(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente removido el servicio al cliente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al remover el servicio al cliente");
        }

    }

    public void updateClientService(long id,ClientService clientService){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            session.persist(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente actualizado el servicio al cliente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al intentar actualizar el servicio al cliente");
        }

    }


    public void addServiceToClient(Service service, long idService){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            ClientService clientService = session.get(ClientService.class,idService);
            service.addClient(clientService);
            session.persist(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente agregado el servicio al cliente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al intentar agregar el servicio al cliente");
        }

    }

    public ClientService getClientService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            ClientService clientService = session.get(ClientService.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return clientService;
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error al obtener el servicio del cliente en la base de datos");
        return null;
    }

    public List<ClientService> getAllClientService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<ClientService> cq=session.getCriteriaBuilder().createQuery(ClientService.class);
            cq.from(ClientService.class);
            List<ClientService> clientservices=session.createQuery(cq).getResultList();
            sessionFactory.close();
            return clientservices;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al obtener lista de servicios de clientes");
        }
        System.out.println( "Finalizada la lista de servicios a clientes");
        return null;
    }
}
