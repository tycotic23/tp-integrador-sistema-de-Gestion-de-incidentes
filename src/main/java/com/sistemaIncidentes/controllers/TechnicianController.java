package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Technician;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TechnicianController {
    public void createTechnician(Technician technician){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Technician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
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

    public void deleteTechnician(long id){
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

    public void updateTechnician(long id){
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


    public Technician getTechnician(long id){
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
        System.out.println( "Error updating technician");
        return null;
    }


    public List<Technician> getAllTechnician(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Technician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Technician> cq=session.getCriteriaBuilder().createQuery(Technician.class);
            cq.from(Technician.class);
            List<Technician> technicians =session.createQuery(cq).getResultList();
            sessionFactory.close();
            return technicians;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error reading technichian");
        }
        System.out.println( "Finished technician list");
        return null;
    }

}
