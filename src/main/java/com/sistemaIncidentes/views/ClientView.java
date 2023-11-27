package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.ClientController;
import com.sistemaIncidentes.controllers.ClientServiceController;
import com.sistemaIncidentes.controllers.ServiceController;
import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.ClientService;
import com.sistemaIncidentes.models.Service;

import java.util.List;
import java.util.Scanner;

public class ClientView {

    public static void main(String[] args) {


        //crear controladores
        /*ClientServiceController clientServiceController=new ClientServiceController();
        ClientController clientController=new ClientController();
        ServiceController serviceController=new ServiceController();*/
        //crear un cliente
        /*clientController.createClient("aasd","asdasd","asdasd");
        clientController.createClient("aasd","asdasd","asdasd");
        clientController.createClient("aasd","asdasd","asdasd");*/
        //traer unos clientes
        /*Client client = clientController.getClient(1);
        Client client2 = clientController.getClient(2);*/
        //crear un clientservice
        /*clientServiceController.createClientService();
        clientServiceController.createClientService();
        clientServiceController.createClientService();
        clientServiceController.createClientService();*/
        //conectar los clientservice con sus clientes
        /*clientServiceController.addClientToService(client,1);
        clientServiceController.addClientToService(client,2);
        clientServiceController.addClientToService(client,3);
        clientServiceController.addClientToService(client2,4);*/



        //crear un servicio
        //serviceController.createService("cables");

        //traer un servicio
        //Service service = serviceController.getService(1);

        //conectar los clientservice con sus servicios
        /*clientServiceController.addServiceToClient(service,1);
        clientServiceController.addServiceToClient(service,2);
        clientServiceController.addServiceToClient(service,3);
        clientServiceController.addServiceToClient(service,4);*/



        //toca el menu: registrar incidente
        //tiene una lista de los tecnicos segun el tipo de problema
        //elige un tecnico
        //poner los datos del incidente nuevo
        //crear incidente con el controllador
        //crear la relacion entre tecnico e incidente (controlador del incidente)

        //menu: cerrar incidente
        //en tecnico pasar su incidente a null
        //mandar notificacion al tecnico
        Scanner scan = new Scanner (System.in);
        int option=0;
        Menu menu=new MenuComercial();
        //el menu cambia segun el tipo de usuario
        do {
            System.out.println("¿Qué tipo de usuario es?");
            System.out.println("Elija una opcion: ");
            System.out.println("1-RRHH");
            System.out.println("2-Comercial");
            System.out.println("3-Mesa de ayuda");
            option = scan.nextInt();
            switch (option) {
                case 1:
                    break;
                case 2:
                    menu=new MenuComercial();
                    break;
                case 3:
                    break;
            }
        }while(option<1 || option>3);

        String fin;
        do{
            //imprimir menu
            System.out.println("Elija una opcion: ");
            menu.printMenu();
            do {
                option = scan.nextInt();
            }while(!menu.isValidOption(option));
            //ejecutar la opcion elegida
            menu.selectOption(option);

            //finalizar o continuar el bucle
            do {
                System.out.print("¿Continuar? s/n ");
                fin=scan.next();
            }while(!fin.equals("s") && !fin.equals("n"));
            System.out.println();

        }while(!fin.equals("n"));
    }


}
