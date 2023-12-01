package com.sistemaIncidentes.controllers;

import com.sistemaIncidentes.models.Client;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class ClientController {

    public Client createClient(String businessName, String CUIT, String email){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            Client client=new Client(businessName,CUIT, email);
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "Cliente creado correctamente");
            return client;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al intentar crear el cliente");
        }
        return null;

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

    public void deleteLogicalClient(long id){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            client.setActive(false);
            session.persist(client);
            session.getTransaction().commit();
            sessionFactory.close();
            System.out.println( "correctamente borrado el cliente");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al intentar borrar el cliente");
        }

    }


    public void updateClient(long id,Client clientUpdated){
        SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class).buildSessionFactory();
        Session session=sessionFactory.openSession();

        try{
            session.beginTransaction();
            Client client = session.get(Client.class,id);
            client.setCUIT(clientUpdated.getCUIT());
            client.setBusinessName(clientUpdated.getBusinessName());
            client.setEmail(clientUpdated.getEmail());
            session.persist(client);
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
            return clients.stream().filter(Client::isActive).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( "Error al crear lista de clientes");
        }
        System.out.println( "Finalizada lista de clientes");
        return null;
    }

    public List<Client> getAllClientandInactive(){
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
