package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Service;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceController {

    public void createService(String name){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Service service=new Service(name);
            session.beginTransaction();
            session.persist(service);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente creado el servicio");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error en la eliminacion del servicio");
        }


    }

    public void deleteService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            session.remove(service);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente eliminado el servicio");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al eliminar el servicio");
        }

    }

    public void deleteLogicalService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            service.setActive(false);
            session.persist(service);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Correctamente eliminado el servicio");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al eliminar el servicio");
        }

    }

    public void updateService(long id,String name){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            service.setName(name);
            session.persist(service);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println(  "Correcamente actualizado el servicio");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(  "Error al actualizar el servicio");
        }

    }

    public Service getService(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Service service = session.get(Service.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return service;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(  "Error en obtener el servicio desde la base de datos");
        }

        return null;
    }

    public List<Service> getAllService(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Service> cq=session.getCriteriaBuilder().createQuery(Service.class);
            cq.from(Service.class);
            List<Service> services=session.createQuery(cq).getResultList();
            sessionFactory.close();
            return services.stream().filter(Service::isActive).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(  "Error en la obtencion de la lista de servicios");
        }
        System.out.println(  "Finalizada lista de servicios");
        return null;
    }

    public List<Service> getAllServiceAndRemoved(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Service.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery<Service> cq=session.getCriteriaBuilder().createQuery(Service.class);
            cq.from(Service.class);
            List<Service> services=session.createQuery(cq).getResultList();
            sessionFactory.close();
            return services;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(  "Error en la obtencion de la lista de servicios");
        }
        System.out.println(  "Finalizada lista de servicios");
        return null;
    }
}
