package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.*;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SpecialityTechnicianController {

    public void createSpecialityTechnician(Speciality speciality, Technician technician){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTechnician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            SpecialityTechnician specialityTechnician=new SpecialityTechnician();
            session.beginTransaction();
            speciality.addTechnician(specialityTechnician);
            technician.addSpeciality(specialityTechnician);
            session.persist(specialityTechnician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente creada la especialidad en el tecnico");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la creacion de la especialidad en el tecnico");
        }


    }

    public void deleteSpecialityTechnician(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTechnician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            SpecialityTechnician specialityTechnician = session.get(SpecialityTechnician.class,id);
            session.remove(specialityTechnician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente creada la especialidad en el tecnico");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la eliminacion de la especialidad en el tecnico");
        }

    }

    public void updateSpecialityTechnician(long id,SpecialityTechnician specialityTechnician){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTechnician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            session.persist(specialityTechnician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correcamente actualizada la especialidad del tecnico");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la actualizacion de la especialidad del tecnico");
        }

    }

    public SpecialityTechnician getSpecialityTechnician(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTechnician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            SpecialityTechnician specialityTechnician = session.get(SpecialityTechnician.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return specialityTechnician;
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println( "Error en la obtencion de la especialidad del tecnico");
        return null;
    }

    public List<SpecialityTechnician> getAllSpecialityTechnician(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTechnician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<SpecialityTechnician> cq=session.getCriteriaBuilder().createQuery(SpecialityTechnician.class);
            cq.from(SpecialityTechnician.class);
            List<SpecialityTechnician> clientservices=session.createQuery(cq).getResultList();
            sessionFactory.close();
            return clientservices;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la obtencion de la especialidades de los tecnicos en la base de datos");
        }
        System.out.println( "Finizada la lista de especialidades de los tecnicos en la base de datos");
        return null;
    }
}
