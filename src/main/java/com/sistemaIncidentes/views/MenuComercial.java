package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.ClientController;
import com.sistemaIncidentes.models.Client;

import java.util.Scanner;

public class MenuComercial implements Menu{

    private ClientController clientController=new ClientController();
    @Override
    public void printMenu() {
        System.out.println("1-Ver todos los clientes");
        System.out.println("2-Ver un cliente");
        System.out.println("3-Borrar un cliente");
        System.out.println("4-Actualizar un cliente");
        System.out.println("5-Dar alta de un cliente");
    }

    @Override
    public boolean isValidOption(int option) {
        return option>=1 && option <=5;
    }

    @Override
    public void selectOption(int option) {
        Scanner scan = new Scanner (System.in);
        int clientId=0;
        switch (option){
            //ver todos los clientes
            case 1:
                for (Client c: clientController.getAllClient()){
                    System.out.println(c);
                }
                break;
            //Ver un cliente
            case 2:
                System.out.println("Ingrese el id del cliente");
                clientId=scan.nextInt();
                System.out.println(clientController.getClient((long)clientId));
                break;
            //Borrar un cliente
            case 3:
                System.out.println("Ingrese el id del cliente a borrar");
                clientId=scan.nextInt();
                clientController.deleteClient((long)clientId);
                break;
            //Actualizar un cliente
            case 4:
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
                break;
        }

    }
}
