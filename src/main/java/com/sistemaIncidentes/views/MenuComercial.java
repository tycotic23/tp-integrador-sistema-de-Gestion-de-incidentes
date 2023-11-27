package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.ClientController;
import com.sistemaIncidentes.controllers.ClientServiceController;
import com.sistemaIncidentes.controllers.ServiceController;
import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.Service;

import java.util.Scanner;

public class MenuComercial implements Menu{

    private ClientController clientController=new ClientController();
    private ServiceController serviceController=new ServiceController();
    private ClientServiceController clientServiceController= new ClientServiceController();
    private Scanner scan = new Scanner (System.in);
    @Override
    public void printMenu() {
        System.out.println("1-Ver todos los clientes");
        System.out.println("2-Ver un cliente");
        System.out.println("3-Borrar un cliente");
        System.out.println("4-Actualizar un cliente");
        System.out.println("5-Dar alta de un cliente");
        System.out.println("6-Contratar servicio para cliente");
    }

    @Override
    public boolean isValidOption(int option) {
        return option>=1 && option <=6;
    }

    @Override
    public void selectOption(int option) {
        Scanner scan = new Scanner (System.in);
        int clientId=0;
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
            //alta de cliente
            case 5:
                addClient();
                break;
            //darle un servicio a un cliente
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
        String businessName=scan.next();
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
        /*clientServiceController.createClientService();
        clientServiceController.addServiceToClient(service,5);
        clientServiceController.addClientToService(client,5);*/
        System.out.println("Añadido con éxito");
    }



    private void removeClient(){
        int clientId=0;
        System.out.println("Ingrese el id del cliente a borrar");
        clientId=scan.nextInt();
        clientController.deleteClient((long)clientId);
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
        do {
            System.out.print("¿Cambiar razón social? s/n");
            change=scan.next();
        }while(!change.equals("s") && !change.equals("n"));
        System.out.println();
        if(change.equals("s")){
            System.out.print("Nueva razon social: ");
            change=scan.next();
        }
        System.out.println();
        client.setBusinessName(change);
        //CUIT
        do {
            System.out.print("¿Cambiar CUIT? s/n");
            change=scan.next();
        }while(!change.equals("s") && !change.equals("n"));
        System.out.println();
        if(change.equals("s")){
            System.out.print("Nuevo CUIT: ");
            change=scan.next();
        }
        System.out.println();
        client.setCUIT(change);
        //email
        do {
            System.out.print("¿Cambiar email? s/n");
            change=scan.next();
        }while(!change.equals("s") && !change.equals("n"));
        System.out.println();
        if(change.equals("s")){
            System.out.print("Nuevo email: ");
            change=scan.next();
        }
        System.out.println();
        client.setEmail(change);
        //persistir
        clientController.updateClient(client);
    }

    private void listClient(){
        for (Client c: clientController.getAllClient()){
            System.out.println(c);
        }
    }

    private void listServices(){
        for (Service s: serviceController.getAllService()){
            System.out.println(s);
        }
    }

    private void getClient(){
        int clientId=0;
        System.out.println("Ingrese el id del cliente");
        clientId=scan.nextInt();
        System.out.println(clientController.getClient((long)clientId));
    }
}