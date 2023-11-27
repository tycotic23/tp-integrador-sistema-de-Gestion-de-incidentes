package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.*;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SpecialityTechnicianController {

    public void createSpecialityTechnician(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTechnician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            SpecialityTechnician specialityTechnician=new SpecialityTechnician();
            session.beginTransaction();
            session.persist(specialityTechnician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error in creation of client service");
        }


    }

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
            System.out.println( "successfully created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error in creation of client service");
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
            System.out.println( "successfully removed");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error deleting client service");
        }

    }

    public void updateSpecialityTechnician(long id,SpecialityTechnician specialityTechnician){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTechnician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            //SpecialityTechnician specialityTechnician = session.get(SpecialityTechnician.class,id);
            //specialityTechnician.setCUIT(CUIT);
            //specialityTechnician.setEmail(email);
            //specialityTechnician.setBusinessName(businessName);
            session.persist(specialityTechnician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating client service");
        }

    }

    /*public void addClientToService(Client client,long idService){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTechnician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            SpecialityTechnician specialityTechnician = session.get(SpecialityTechnician.class,idService);
            client.addService(specialityTechnician);
            //specialityTechnician.setCUIT(CUIT);
            //specialityTechnician.setEmail(email);
            //specialityTechnician.setBusinessName(businessName);
            session.persist(specialityTechnician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating client service");
        }

    }

    public void addServiceToClient(Service service, long idService){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SpecialityTechnician.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            SpecialityTechnician specialityTechnician = session.get(SpecialityTechnician.class,idService);
            service.addClient(specialityTechnician);
            session.persist(specialityTechnician);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "successfully updated");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error updating client service");
        }

    }*/

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
        System.out.println( "Error updating client service");
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
            System.out.println( "Error reading clients services");
        }
        System.out.println( "Finished client service list");
        return null;
    }
}
