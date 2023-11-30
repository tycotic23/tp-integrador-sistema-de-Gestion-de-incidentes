package com.sistemaIncidentes.controllers;
import com.sistemaIncidentes.models.*;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class IncidentController {

    public Incident createIncident(Client client, Service service, Technician technician){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Incident incident=new Incident();
            session.beginTransaction();
            client.addIncident(incident);
            service.addIncident(incident);
            technician.addIncident(incident);
            session.persist(incident);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente creado el incidente");
            return incident;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al crear el incidente");
        }
        return null;

    }

    public void deleteIncident(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Incident incident = session.get(Incident.class,id);
            session.remove(incident);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente eliminado el incidente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al eliminar el cliente");
        }

    }

    public void deleteLogicalIncident(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Incident incident = session.get(Incident.class,id);
            incident.setActive(false);
            session.persist(incident);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente eliminado el incidente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al eliminar el cliente");
        }

    }

    public void closeIncident(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Incident incident = session.get(Incident.class,id);
            incident.setSolved(true);
            session.persist(incident);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente finalizado el incidente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al finalizar el incidente");
        }

    }

    public Incident getIncident(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Incident incident = session.get(Incident.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return incident;
        }catch (Exception e){
            e.printStackTrace();


        }
        System.out.println( "Error al intentar obtener incidente en la base de datos");
        return null;
    }

    public List<Incident> getAllIncident(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery <Incident> cq=session.getCriteriaBuilder().createQuery(Incident.class);
            cq.from(Incident.class);
            List<Incident> incidents=session.createQuery(cq).getResultList();
            sessionFactory.close();
            return incidents.stream().filter(Incident::isActive).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al leer los incidentes desde la base de datos");
        }
        System.out.println( "Finalizada lista de incidentes");
        return null;
    }

    public List<Incident> getAllIncidentAndRemoved(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Incident.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery <Incident> cq=session.getCriteriaBuilder().createQuery(Incident.class);
            cq.from(Incident.class);
            List<Incident> incidents=session.createQuery(cq).getResultList();
            sessionFactory.close();
            return incidents;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al leer los incidentes desde la base de datos");
        }
        System.out.println( "Finalizada lista de incidentes");
        return null;
    }
}
