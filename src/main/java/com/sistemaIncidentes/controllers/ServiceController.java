package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Service;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ServiceController {

    public void createService(String name){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Service service=new Service(name);
            session.beginTransaction();
            session.persist(service);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error in creation of service");
        }


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
            System.out.println( "Error deleting service");
        }

    }

    public void updateService(long id,String name){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            service.setName(name);
            session.persist(service);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println(  "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(  "Error updating service");
        }

    }

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
            System.out.println(  "Error updating service");
        }

        return null;
    }

    public List<Service> getAllService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Service> cq=session.getCriteriaBuilder().createQuery(Service.class);
            cq.from(Service.class);
            List<Service> services=session.createQuery(cq).getResultList();
            /*for (Service c:services){
                System.out.println("Service ID: "+c.getId());
                System.out.println("Name: "+c.getName());
            }*/
            sessionFactory.close();
            return services;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(  "Error reading services");
        }
        System.out.println(  "Finished service list");
        return null;
    }
}
