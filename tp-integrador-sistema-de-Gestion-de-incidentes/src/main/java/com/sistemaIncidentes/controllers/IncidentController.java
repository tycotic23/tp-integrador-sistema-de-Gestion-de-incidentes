package com.sistemaIncidentes.controllers;
import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.Incident;
import com.sistemaIncidentes.models.Service;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
public class IncidentController {
    public void createIncident(Service service, Client client ){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Incident incident =new Incident();
            session.beginTransaction();
            session.persist(incident);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error in creation of client");

    }

    public void deleteIncident(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Incident incident = session.get(Incident.class,id);
            session.remove(incident);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error deleting incident");
    }

    public void updateIncident(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Incident incident = session.get(Incident.class,id);


            session.persist(incident);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error updating incident");
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

    public Incident getIncident(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Incident incident = session.get(Incident.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return incident;
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println( "Error updating incident");
        return null;
    }

    public List<Incident> getAllIncident(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery <Incident> cq=session.getCriteriaBuilder().createQuery(Incident.class);
            cq.from(Incident.class);
            List<Incident> incidents=session.createQuery(cq).getResultList();
            /*for (Client c:clients){
                System.out.println("Client ID: "+c.getId());
                System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());
            }*/
            sessionFactory.close();
            return incidents;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error reading incidents");
        }
        System.out.println( "Finished incident list");
        return null;
    }
}
