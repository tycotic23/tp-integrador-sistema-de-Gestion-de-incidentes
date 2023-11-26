package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Service;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ControllerService {

    public void createService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Service service=new Service();
            session.beginTransaction();
            session.persist(service);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error in creation of service");

    }

    public void deleteService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            session.remove(service);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error deleting service");
    }

    public void updateService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);



            session.persist(service);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error updating client");
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

    public Service getService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return service;
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println( "Error updating service");
        return null;
    }

    public List<Service> getAllService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Service> cq=session.getCriteriaBuilder().createQuery(Service.class);
            cq.from(Service.class);
            List<Service> services =session.createQuery(cq).getResultList();
            /*for (Client c:clients){
                System.out.println("Client ID: "+c.getId());
                System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());
            }*/
            sessionFactory.close();
            return services;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error reading services");
        }
        System.out.println( "Finished service list");
        return null;
    }

}
