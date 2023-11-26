package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.ClientController;
import com.sistemaIncidentes.controllers.ClientServiceController;
import com.sistemaIncidentes.controllers.ServiceController;
import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.ClientService;
import com.sistemaIncidentes.models.Service;

public class ClientView {

    public static void main(String[] args) {


        //crear controladores
        ClientServiceController clientServiceController=new ClientServiceController();
        ClientController clientController=new ClientController();
        ServiceController serviceController=new ServiceController();
        //crear un cliente
        /*clientController.createClient("aasd","asdasd","asdasd");
        clientController.createClient("aasd","asdasd","asdasd");
        clientController.createClient("aasd","asdasd","asdasd");*/
        //traer unos clientes
        Client client = clientController.getClient(1);
        Client client2 = clientController.getClient(2);
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
        Service service = serviceController.getService(1);

        //conectar los clientservice con sus servicios
        clientServiceController.addServiceToClient(service,1);
        clientServiceController.addServiceToClient(service,2);
        clientServiceController.addServiceToClient(service,3);
        clientServiceController.addServiceToClient(service,4);



        //toca el menu: registrar incidente
        //tiene una lista de los tecnicos segun el tipo de problema
        //elige un tecnico
        //poner los datos del incidente nuevo
        //crear incidente con el controllador
        //crear la relacion entre tecnico e incidente (controlador del incidente)

        //menu: cerrar incidente
        //en tecnico pasar su incidente a null
        //mandar notificacion al tecnico


        System.out.println("Hello world! all created");
    }
}
