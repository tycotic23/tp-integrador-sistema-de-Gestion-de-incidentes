package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Speciality;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class SpecialityController {
    public void createSpeciality(String name){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Speciality.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Speciality speciality=new Speciality(name);
            session.beginTransaction();
            session.persist(speciality);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente creada la especialidad");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la creacion de la especialidad");
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
            System.out.println( "Correctamente eliminada la especialidad");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la eliminacion de la especialidad");
        }

    }

    public void deleteLogicalSpeciality(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Speciality.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Speciality speciality = session.get(Speciality.class,id);
            speciality.setActive(false);
            session.persist(speciality);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente eliminada la especialidad");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la eliminacion de la especialidad");
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
            System.out.println( "Correctamente actualizada la especialidad");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la actualizacion de la especialidad");
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
        System.out.println( "Error en la obtencion de la especialidad");
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
            sessionFactory.close();
            return specialities.stream().filter(Speciality::isActive).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la obtencion de la lista de especialidades");
        }
        System.out.println( "Finalizada la lista de especialidades");
        return null;
    }

    public List<Speciality> getAllSpecialityAndRemoved(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Speciality.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Speciality> cq=session.getCriteriaBuilder().createQuery(Speciality.class);
            cq.from(Speciality.class);
            List<Speciality> specialities =session.createQuery(cq).getResultList();
            sessionFactory.close();
            return specialities;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la obtencion de la lista de especialidades");
        }
        System.out.println( "Finalizada la lista de especialidades");
        return null;
    }

}
