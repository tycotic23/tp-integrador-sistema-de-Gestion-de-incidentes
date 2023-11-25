package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Service;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ServiceController {

    public String createService(String name){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Service service=new Service(name);
            session.beginTransaction();
            session.persist(service);
            session.getTransaction().commit();
            sessionFactory.close();
            return "successfully created";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error in creation of service";

    }

    public String deleteService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            session.remove(service);
            session.getTransaction().commit();
            sessionFactory.close();
            return "successfully removed";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error deleting service";
    }

    public String updateService(long id,String name){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            service.setName(name);
            session.persist(service);
            session.getTransaction().commit();
            sessionFactory.close();
            return "successfully updated";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error updating service";
    }

    public String getService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return "Service "+id+": "+service.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error updating service";
    }

    public String getAllService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Service> cq=session.getCriteriaBuilder().createQuery(Service.class);
            cq.from(Service.class);
            List<Service> services=session.createQuery(cq).getResultList();
            for (Service c:services){
                System.out.println("Service ID: "+c.getId());
                System.out.println("Name: "+c.getName());
            }
            sessionFactory.close();
        }catch (Exception e){
            e.printStackTrace();
            return "Error reading services";
        }
        return "Finished service list";
    }
}
