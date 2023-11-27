package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Client;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientController {

    public void createClient(String businessName, String CUIT, String email){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Client client=new Client(businessName,CUIT, email);
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error in creation of client");

    }

    public void deleteClient(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            session.remove(client);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error deleting client");
    }

    public void updateClient(long id,String businessName, String CUIT, String email){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            client.setCUIT(CUIT);
            client.setEmail(email);
            client.setBusinessName(businessName);
            session.persist(client);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error updating client");
    }

    public void updateClient(Client clientUpdated){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            session.persist(clientUpdated);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating client");
        }

    }

    /*public void addServiceToClient(long id,ClientService clientService){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            client.addService(clientService);
            //session.persist(client);
            session.getTransaction().commit();
            sessionFactory.close();
            return "successfully updated";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error updating client";
    }*/

    public Client getClient(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return client;
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println( "Error updating client");
        return null;
    }

    public List<Client> getAllClient(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery <Client> cq=session.getCriteriaBuilder().createQuery(Client.class);
            cq.from(Client.class);
            List<Client> clients=session.createQuery(cq).getResultList();
            /*for (Client c:clients){
                System.out.println("Client ID: "+c.getId());
                System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());
            }*/
            sessionFactory.close();
            return clients;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error reading clients");
        }
        System.out.println( "Finished client list");
        return null;
    }
}
