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

    public void createClientService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            ClientService clientService=new ClientService();
            session.beginTransaction();
            session.persist(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error in creation of client service");

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
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error deleting client service");
    }

    public void updateClientService(long id,ClientService clientService){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            //ClientService clientService = session.get(ClientService.class,id);
            //clientService.setCUIT(CUIT);
            //clientService.setEmail(email);
            //clientService.setBusinessName(businessName);
            session.persist(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating client service");
        }

    }

    public void addClientToService(Client client,long idService){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            ClientService clientService = session.get(ClientService.class,idService);
            client.addService(clientService);
            //clientService.setCUIT(CUIT);
            //clientService.setEmail(email);
            //clientService.setBusinessName(businessName);
            session.persist(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating client service");
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
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating client service");
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
        System.out.println( "Error updating client service");
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
            /*for (ClientService cs:clientservices){
                System.out.println("Client ID: "+cs.getId());
                /*System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());
            }*/
            sessionFactory.close();
            return clientservices;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error reading clients services");
        }
        System.out.println( "Finished client service list");
        return null;
    }
}
