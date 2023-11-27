package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Problem;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProblemController {
    public void createProblem(String businessName, String CUIT, String email){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Problem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Problem problem =new Problem();
            session.beginTransaction();
            session.persist(problem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error in creation of problem");
        }


    }

    public void deleteProblem(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Problem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Problem problem = session.get(Problem.class,id);
            session.remove(problem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error deleting problem");
        }

    }

    public void updateProblem(long id,String businessName, String CUIT, String email){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Problem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Problem problem = session.get(Problem.class,id);

            session.persist(problem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating client");
        }

    }



    public Problem getProblem(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Problem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Problem problem = session.get(Problem.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return problem;
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println( "Error updating problem");
        return null;
    }

    public List<Problem> getAllProblem(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Problem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Problem> cq=session.getCriteriaBuilder().createQuery(Problem.class);
            cq.from(Problem.class);
            List<Problem> problems=session.createQuery(cq).getResultList();
            /*for (Client c:clients){
                System.out.println("Client ID: "+c.getId());
                System.out.println("Email: "+c.getEmail());
                System.out.println("Business Name: "+c.getBusinessName());
                System.out.println("CUIT: "+c.getCUIT());
            }*/
            sessionFactory.close();
            return problems;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error reading problems");
        }
        System.out.println( "Finished problems list");
        return null;
    }
}
