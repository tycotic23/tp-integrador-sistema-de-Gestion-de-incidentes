package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.*;
import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.Service;

import java.util.List;
import java.util.Scanner;

public class MenuComercial implements Menu{

    private final ClientController clientController=new ClientController();
    private final ServiceController serviceController=new ServiceController();
    private final ClientServiceController clientServiceController= new ClientServiceController();
    private final ProblemController problemController=new ProblemController();

    private final IncidentController incidentController=new IncidentController();
    private final Scanner scan = new Scanner (System.in);
    @Override
    public void printMenu() {
        System.out.println("1-Ver todos los clientes ");
        System.out.println("2-Ver un cliente ");
        System.out.println("3-Borrar un cliente ");
        System.out.println("4-Actualizar un cliente ");
        System.out.println("5-Dar alta de un cliente ");
        System.out.println("6-Contratar servicio para cliente ");
    }

    @Override
    public boolean isValidOption(int option) {
        return option>=1 && option <=6;
    }

    @Override
    public void selectOption(int option) {
        switch (option) {
            //ver todos los clientes
            case 1 -> listClient();

            //Ver un cliente
            case 2 -> getClient();

            //Borrar un cliente
            case 3 -> removeClient();

            //Actualizar un cliente
            case 4 -> updateClient();

            //Alta de cliente
            case 5 -> addClient();

            //Darle un servicio a un cliente
            case 6 -> addServiceToClient();
        }

    }

    private void addClient(){
        //pedir nuevos datos
        //razon social
        System.out.print("Razon social: ");
        String businessName=scan.nextLine();
        System.out.println();
        //CUIT
        System.out.print("CUIT: ");
        String cuit=scan.next();
        System.out.println();
        //email
        System.out.print("Email: ");
        String email=scan.next();
        System.out.println();

        //persistir
        clientController.createClient(businessName,cuit,email);
    }

    private void addServiceToClient(){
        int clientId;
        int serviceId;
        System.out.println("Ingrese el id del cliente a añadir un servicio");
        //mostrar cliente original
        clientId=scan.nextInt();
        Client client=clientController.getClient(clientId);
        System.out.println(client);
        //mostrar una lista de todos los servicios
        listServices();
        //pedir id del servicio
        System.out.println("Ingrese el id del servicio elegido");
        serviceId=scan.nextInt();
        Service service=serviceController.getService(serviceId);
        //crear relacion
        clientServiceController.createClientService(client,service);
        System.out.println("Añadido con éxito el servicio");
    }



    private void removeClient(){
        int clientId;
        System.out.println("Ingrese el id del cliente a borrar");
        clientId=scan.nextInt();
        //borrado logico del cliente
        clientController.deleteLogicalClient(clientId);
        //borrado logico de todos sus incidentes y sus respectivos problemas tambien
        Client client =clientController.getClient(clientId);
        client.getIncidents().forEach(
                i-> {
                    incidentController.deleteLogicalIncident(i.getId());
                    i.getProblems().forEach(
                            p->problemController.deleteLogicalProblem(p.getId())
                    );
                });
    }

    private void updateClient(){
        int clientId;
        String change;
        System.out.println("Ingrese el id del cliente a modificar");
        //mostrar cliente original
        clientId=scan.nextInt();
        Client client=clientController.getClient(clientId);
        System.out.println(client);
        //pedir nuevos datos
        //razon social
        scan.nextLine();
        do {
            System.out.print("¿Cambiar razón social? s/n ");
            change=scan.nextLine();
        }while(!change.equals("s") && !change.equals("n"));
        System.out.println();
        if(change.equals("s")){
            System.out.print("Nueva razon social: ");
            change=scan.nextLine();
            client.setBusinessName(change);
        }
        System.out.println();

        //CUIT
        do {
            System.out.print("¿Cambiar CUIT? s/n ");
            change=scan.nextLine();
        }while(!change.equals("s") && !change.equals("n"));
        System.out.println();
        if(change.equals("s")){
            System.out.print("Nuevo CUIT: ");
            change=scan.next();
            client.setCUIT(change);
        }
        System.out.println();

        //email
        do {
            System.out.print("¿Cambiar email? s/n ");
            change=scan.next();
        }while(!change.equals("s") && !change.equals("n"));
        System.out.println();
        if(change.equals("s")){
            System.out.print("Nuevo email: ");
            change=scan.next();
            client.setEmail(change);
        }
        System.out.println();

        //persistir
        clientController.updateClient(clientId,client);
    }

    private void listClient(){
        List<Client> clients=clientController.getAllClient();
        if(clients.isEmpty()){
            System.out.println("No hay ningún cliente");
        }
        for (Client c: clients){
            System.out.println(c);
        }
    }

    private void listServices(){
        List<Service> services=serviceController.getAllService();
        if(services.isEmpty()){
            System.out.println("No hay ningún servicio");
        }
        for (Service s: services){
            System.out.println(s);
        }
    }

    private void getClient(){
        int clientId;
        System.out.println("Ingrese el id del cliente ");
        clientId=scan.nextInt();
        System.out.println(clientController.getClient(clientId));
    }
}
