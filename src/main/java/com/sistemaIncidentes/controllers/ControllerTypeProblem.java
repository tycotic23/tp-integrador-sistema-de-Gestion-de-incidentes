package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.TypeProblem;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Type;
import java.util.List;

public class ControllerTypeProblem {

    public void createTypeProblem(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(TypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            TypeProblem typeProblem=new TypeProblem();
            session.beginTransaction();
            session.persist(typeProblem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error in creation of type problem");
        }


    }

    public void deleteTypeProblem(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(TypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            TypeProblem typeProblem = session.get(TypeProblem.class,id);
            session.remove(typeProblem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error deleting type problem");
        }

    }

    public void updateTypeProblem(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(TypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            TypeProblem typeProblem = session.get(TypeProblem.class,id);



            session.persist(typeProblem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating type problem");
        }

    }



    public TypeProblem getTypeProblem(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(TypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            TypeProblem typeProblem = session.get(TypeProblem.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return typeProblem;
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println( "Error updating type problem");
        return null;
    }

    public List<TypeProblem> getAllTypeProblem(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(TypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<TypeProblem> cq=session.getCriteriaBuilder().createQuery(TypeProblem.class);
            cq.from(TypeProblem.class);
            List<TypeProblem> typeProblems =session.createQuery(cq).getResultList();
            /*for (Client c:clients){
                System.out.println("Client ID: "+c.getId());
                System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());
            }*/
            sessionFactory.close();
            return typeProblems;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error reading type problems");
        }
        System.out.println( "Finished type problem list");
        return null;
    }

}
