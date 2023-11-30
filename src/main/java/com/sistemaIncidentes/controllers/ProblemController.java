package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.*;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class ProblemController {


    public void createProblem(Problem problem,TypeProblem typeProblem, Incident incident){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Problem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{

            session.beginTransaction();
            typeProblem.addProblem(problem);
            incident.addProblem(problem);
            session.persist(problem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente creado el problema");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la creacion del problema");
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
            System.out.println( "Correctamente eliminado el problema");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la elimizacion del problema");
        }

    }

    public void deleteLogicalProblem(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Problem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Problem problem = session.get(Problem.class,id);
            problem.setActive(false);
            session.persist(problem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente eliminado el problema");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la elimizacion del problema");
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
            System.out.println( "Correctamente actualizado el problema");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al actualizar el problema");
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
        System.out.println( "Error al obtener el problema desde la base de datos");
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
            sessionFactory.close();
            return problems.stream().filter(Problem::isActive).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al crear lista de problemas");
        }
        System.out.println( "Finalizada lista de problemas");
        return null;
    }

    public List<Problem> getAllProblemAndRemoved(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Problem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Problem> cq=session.getCriteriaBuilder().createQuery(Problem.class);
            cq.from(Problem.class);
            List<Problem> problems=session.createQuery(cq).getResultList();
            sessionFactory.close();
            return problems;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al crear lista de problemas");
        }
        System.out.println( "Finalizada lista de problemas");
        return null;
    }
}
