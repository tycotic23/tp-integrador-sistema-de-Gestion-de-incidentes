package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Client;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientController {

    public String createClient(String businessName, String CUIT, String email){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Client client=new Client(businessName,CUIT, email);
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
            sessionFactory.close();
            return "successfully created";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error in creation of client";

    }

    public String deleteClient(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            session.remove(client);
            session.getTransaction().commit();
            sessionFactory.close();
            return "successfully removed";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error deleting client";
    }

    public String updateClient(long id,String businessName, String CUIT, String email){
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
            return "successfully updated";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error updating client";
    }

    public String getClient(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return "Client "+id+": "+client.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error updating client";
    }

    public String getAllClient(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery <Client> cq=session.getCriteriaBuilder().createQuery(Client.class);
            cq.from(Client.class);
            List<Client> clients=session.createQuery(cq).getResultList();
            for (Client c:clients){
                System.out.println("Client ID: "+c.getId());
                System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());
            }
            sessionFactory.close();
        }catch (Exception e){
            e.printStackTrace();
            return "Error reading clients";
        }
        return "Finished client list";
    }
}
