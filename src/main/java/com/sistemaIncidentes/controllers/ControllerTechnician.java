package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Technician;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ControllerTechnician {
    public void createService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Technician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Technician technician = new Technician();
            session.beginTransaction();
            session.persist(technician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error in creation of technichian");
        }


    }

    public void deleteService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Technician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Technician technician = session.get(Technician.class,id);
            session.remove(technician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error deleting technichian");
        }

    }

    public void updateTechnichian(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Technician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Technician technician = session.get(Technician.class,id);



            session.persist(technician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating technichian");
        }

    }


    public Technician getTechnichian(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Technician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Technician technician = session.get(Technician.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return technician;
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println( "Error updating service");
        return null;
    }

    public List<Technician> getAllTechnichian(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Technician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Technician> cq=session.getCriteriaBuilder().createQuery(Technician.class);
            cq.from(Technician.class);
            List<Technician> services =session.createQuery(cq).getResultList();
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
            System.out.println( "Error reading technichian");
        }
        System.out.println( "Finished service list");
        return null;
    }

}
