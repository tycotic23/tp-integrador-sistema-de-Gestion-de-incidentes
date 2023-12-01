package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.TypeProblem;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class TypeProblemController {

    public TypeProblem createTypeProblem(TypeProblem  typeProblem){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(TypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            session.persist(typeProblem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente creado el tipo de problema");
            return typeProblem;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la creaciond el tipo de problema");
        }
        return null;


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
            System.out.println( "Corretamente eliminado el tipo de problema");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la eliminacion del tipo de problema");
        }

    }

    public void deleteLogicalTypeProblem(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(TypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            TypeProblem typeProblem = session.get(TypeProblem.class,id);
            typeProblem.setActive(false);
            session.persist(typeProblem);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Corretamente eliminado el tipo de problema");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la eliminacion del tipo de problema");
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
            System.out.println( "Correctamente actualizado el tipo de problema");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la actualizacion del tipo de problema");
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
        System.out.println( "Error en la obtencion del tipo de problema");
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
            sessionFactory.close();
            return typeProblems.stream().filter(TypeProblem::isActive).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al crear lista de tipos de problemas desde la base de datos");
        }
        System.out.println( "Finalizada lista de tipos de problemas");
        return null;
    }

    public List<TypeProblem> getAllTypeProblemAndRemoved(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(TypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<TypeProblem> cq=session.getCriteriaBuilder().createQuery(TypeProblem.class);
            cq.from(TypeProblem.class);
            List<TypeProblem> typeProblems =session.createQuery(cq).getResultList();
            sessionFactory.close();
            return typeProblems;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al crear lista de tipos de problemas desde la base de datos");
        }
        System.out.println( "Finalizada lista de tipos de problemas");
        return null;
    }

}
