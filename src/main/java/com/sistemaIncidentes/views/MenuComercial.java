package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.ClientController;
import com.sistemaIncidentes.controllers.ClientServiceController;
import com.sistemaIncidentes.controllers.IncidentController;
import com.sistemaIncidentes.controllers.ServiceController;
import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.Service;

import java.util.List;
import java.util.Scanner;

public class MenuComercial implements Menu{

    private ClientController clientController=new ClientController();
    private ServiceController serviceController=new ServiceController();
    private ClientServiceController clientServiceController= new ClientServiceController();

    private IncidentController incidentController=new IncidentController();
    private Scanner scan = new Scanner (System.in);
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
        switch (option){
            //ver todos los clientes
            case 1:
                listClient();
                break;
            //Ver un cliente
            case 2:
                getClient();
                break;
            //Borrar un cliente
            case 3:
                removeClient();
                break;
            //Actualizar un cliente
            case 4:
                updateClient();
                break;
            //Alta de cliente
            case 5:
                addClient();
                break;
            //Darle un servicio a un cliente
            case 6:
                addServiceToClient();
                break;
        }

    }

    private void addClient(){
        String change;
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
        int clientId=0;
        int serviceId=0;
        System.out.println("Ingrese el id del cliente a añadir un servicio");
        //mostrar cliente original
        clientId=scan.nextInt();
        Client client=clientController.getClient((long)clientId);
        System.out.println(client);
        //mostrar una lista de todos los servicios
        listServices();
        //pedir id del servicio
        System.out.println("Ingrese el id del servicio elegido");
        serviceId=scan.nextInt();
        Service service=serviceController.getService((long)serviceId);
        //crear relacion
        clientServiceController.createClientService(client,service);
        System.out.println("Añadido con éxito el servicio");
    }



    private void removeClient(){
        int clientId=0;
        System.out.println("Ingrese el id del cliente a borrar");
        clientId=scan.nextInt();
        //borrado logico del cliente
        clientController.deleteLogicalClient((long)clientId);
        //borrado logico de todos sus incidentes
        Client client =clientController.getClient((long)clientId);
        client.getIncidents().forEach(i->incidentController.deleteLogicalIncident(i.getId()));
    }

    private void updateClient(){
        int clientId=0;
        String change;
        System.out.println("Ingrese el id del cliente a modificar");
        //mostrar cliente original
        clientId=scan.nextInt();
        Client client=clientController.getClient((long)clientId);
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
        clientController.updateClient((long)clientId,client);
    }

    private void listClient(){
        List<Client> clients=clientController.getAllClient();
        if(clients.size()==0){
            System.out.println("No hay ningún cliente");
        }
        for (Client c: clients){
            System.out.println(c);
        }
    }

    private void listServices(){
        List<Service> services=serviceController.getAllService();
        if(services.size()==0){
            System.out.println("No hay ningún servicio");
        }
        for (Service s: services){
            System.out.println(s);
        }
    }

    private void getClient(){
        int clientId=0;
        System.out.println("Ingrese el id del cliente ");
        clientId=scan.nextInt();
        System.out.println(clientController.getClient((long)clientId));
    }
}
