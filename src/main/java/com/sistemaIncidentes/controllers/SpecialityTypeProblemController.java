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
            System.out.println( "Correctamente creado el tipo de problema del tipo de especialidad");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la creacion de tipo de problema para la especialidad");
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
            System.out.println( "Correctamente removido el tipo de problema para la especialidad ");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la eliminacion del tipo de problema para la especialidad");
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
            System.out.println( "Correctamente actualizado el tipo de problema para la especialidad");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al intentar actualizar el tipo de problema para la especialidad");
        }

    }


    public List<SpecialityTypeProblem> getAllSpecialityTypeProblem(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTypeProblem.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<SpecialityTypeProblem> cq=session.getCriteriaBuilder().createQuery(SpecialityTypeProblem.class);
            cq.from(SpecialityTypeProblem.class);
            List<SpecialityTypeProblem> specialityTypeProblems =session.createQuery(cq).getResultList();
            sessionFactory.close();
            return specialityTypeProblems;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la creacion de lista de tipos de problema para especialidad");
        }
        System.out.println( "Finalizada lista de tipos de problema para especialidad");
        return null;
    }

}
