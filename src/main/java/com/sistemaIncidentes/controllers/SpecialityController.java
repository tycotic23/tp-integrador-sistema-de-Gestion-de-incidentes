package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Speciality;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SpecialityController {
    public void createSpeciality(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Speciality.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Speciality speciality=new Speciality();
            session.beginTransaction();
            session.persist(speciality);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error in creation of speciality");
        }


    }

    public void deleteSpeciality(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Speciality.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Speciality speciality = session.get(Speciality.class,id);
            session.remove(speciality);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error deleting speciality");
        }

    }

    public void updateSpeciality(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Speciality.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Speciality speciality = session.get(Speciality.class,id);



            session.persist(speciality);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating speciality");
        }

    }



    public Speciality getSpeciality(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Speciality.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Speciality speciality = session.get(Speciality.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return speciality;
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println( "Error updating speciality");
        return null;
    }

    public List<Speciality> getAllSpeciality(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Speciality.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Speciality> cq=session.getCriteriaBuilder().createQuery(Speciality.class);
            cq.from(Speciality.class);
            List<Speciality> specialities =session.createQuery(cq).getResultList();
            /*for (Client c:clients){
                System.out.println("Client ID: "+c.getId());
                System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());
            }*/
            sessionFactory.close();
            return specialities;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error reading specialities");
        }
        System.out.println( "Finished speciality list");
        return null;
    }

}
