package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.ClientService;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientServiceController {

    public String createClientService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            ClientService clientService=new ClientService();
            session.beginTransaction();
            session.persist(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            return "successfully created";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error in creation of client service";

    }

    public String deleteClientService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            ClientService clientService = session.get(ClientService.class,id);
            session.remove(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            return "successfully removed";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error deleting client service";
    }

    public String updateClientService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            ClientService clientService = session.get(ClientService.class,id);
            //clientService.setCUIT(CUIT);
            //clientService.setEmail(email);
            //clientService.setBusinessName(businessName);
            session.persist(clientService);
            session.getTransaction().commit();
            sessionFactory.close();
            return "successfully updated";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error updating client service";
    }

    public String getClientService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            ClientService clientService = session.get(ClientService.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return "Client "+id+": "+clientService.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error updating client service";
    }

    public String getAllClientService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClientService.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<ClientService> cq=session.getCriteriaBuilder().createQuery(ClientService.class);
            cq.from(ClientService.class);
            List<ClientService> clientservices=session.createQuery(cq).getResultList();
            for (ClientService cs:clientservices){
                System.out.println("Client ID: "+cs.getId());
                /*System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());*/
            }
            sessionFactory.close();
        }catch (Exception e){
            e.printStackTrace();
            return "Error reading clients services";
        }
        return "Finished client service list";
    }
}
