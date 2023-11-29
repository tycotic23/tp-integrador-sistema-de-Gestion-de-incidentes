package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Client;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientController {

    public void createClient(String businessName, String CUIT, String email){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Client client=new Client(businessName,CUIT, email);
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Cliente creado correctamente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al intentar crear el cliente");
        }


    }

    public void deleteClient(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            session.remove(client);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "correctamente borrado el cliente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al intentar borrar el cliente");
        }

    }


    public void updateClient(Client clientUpdated){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            session.persist(clientUpdated);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "correctamente actualizado el cliente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al intentar actualizar el cliente");
        }

    }

    public Client getClient(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            session.getTransaction().commit();
            sessionFactory.close();
            return client;
        }catch (Exception e){
            e.printStackTrace();

        }
        System.out.println( "Error al intentar obtener el cliente desde la base de datos");
        return null;
    }

    public List<Client> getAllClient(){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            CriteriaQuery <Client> cq=session.getCriteriaBuilder().createQuery(Client.class);
            cq.from(Client.class);
            List<Client> clients=session.createQuery(cq).getResultList();
            sessionFactory.close();
            return clients;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al crear lista de clientes");
        }
        System.out.println( "Finalizada lista de clientes");
        return null;
    }
}
