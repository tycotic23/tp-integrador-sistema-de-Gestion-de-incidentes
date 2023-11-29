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
            System.out.println( "Tecnico correctamente creado");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la creacion del tecnico");
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
            System.out.println( "Correctamente eliminado el tecnico");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la eliminacion del tecnico");
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
            System.out.println( "Correctamente actualizado el tecnico");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la actualizacion del tecnico");
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
        System.out.println( "Error en la obtencion del tecnico");
        return null;
    }

    public void setAvailableForId(long id, boolean available){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Technician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Technician technician = session.get(Technician.class,id);
            technician.setAvailable(available);
            session.getTransaction().commit();
            sessionFactory.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la obtencion del estado del tecnico");
        }
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
            System.out.println( "Error al realizar la lista de tecnico desde la base de datos");
        }
        System.out.println( "Finalizada lista de tecnicos");
        return null;
    }

}
