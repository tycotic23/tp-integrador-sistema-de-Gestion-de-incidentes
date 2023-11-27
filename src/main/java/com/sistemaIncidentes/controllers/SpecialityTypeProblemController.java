package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Speciality;
import com.sistemaIncidentes.models.SpecialityTypeProblem;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SpecialityTypeProblemController {
    public void createSpecialityTypeProblem(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            SpecialityTypeProblem specialityTypeProblem=new SpecialityTypeProblem();
            session.beginTransaction();
            session.persist(specialityTypeProblem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error in creation of speciality type problem");
        }


    }

    public void deleteSpecialityTypeProblem(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            SpecialityTypeProblem specialityTypeProblem = session.get(SpecialityTypeProblem.class,id);
            session.remove(specialityTypeProblem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error deleting speciality type problem");
        }

    }

    public void updateSpecialityTypeProblem(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            SpecialityTypeProblem specialityTypeProblem = session.get(SpecialityTypeProblem.class,id);



            session.persist(specialityTypeProblem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating speciality type problem");
        }

    }

    public SpecialityTypeProblem getService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            SpecialityTypeProblem specialityTypeProblem = session.get(SpecialityTypeProblem.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return specialityTypeProblem;
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println( "Error updating speciality type problem");
        return null;
    }

    public List<SpecialityTypeProblem> getAllSpecialityTypeProblem(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<SpecialityTypeProblem> cq=session.getCriteriaBuilder().createQuery(SpecialityTypeProblem.class);
            cq.from(SpecialityTypeProblem.class);
            List<SpecialityTypeProblem> specialityTypeProblems =session.createQuery(cq).getResultList();
            /*for (Client c:clients){
                System.out.println("Client ID: "+c.getId());
                System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());
            }*/
            sessionFactory.close();
            return specialityTypeProblems;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error reading speciality type problems");
        }
        System.out.println( "Finished service list");
        return null;
    }

}
