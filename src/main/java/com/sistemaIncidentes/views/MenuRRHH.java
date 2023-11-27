package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.ControllerTechnician;
import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.Service;
import com.sistemaIncidentes.models.Speciality;

public class MenuRRHH implements Menu{

    private ControllerTechnician controllerTechnician;
    private Speciality

    @Override
    public void printMenu() {
        System.out.println("1-Ver todos los técnicos");
        System.out.println("2-Ver un técnico");
        System.out.println("3-Dar de baja un técnico");
        System.out.println("4-Actualizar un técnico");
        System.out.println("5-Dar alta de un técnico");
        System.out.println("6-Crear informe de incidentes");
        System.out.println("7-Consultar técnico con más incidentes resueltos en los últimos n días");
        System.out.println("8-Consultar técnico con más incidentes resueltos de cierta especialidad en los últimos n días");
        System.out.println("9-Consultar técnico más rápido");
    }

    @Override
    public void selectOption(int option) {


    }

    @Override
    public boolean isValidOption(int option) {
        return option>=1 && option <=9;
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
