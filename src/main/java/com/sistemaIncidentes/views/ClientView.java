package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.ClientController;
import com.sistemaIncidentes.controllers.ClientServiceController;
import com.sistemaIncidentes.controllers.ServiceController;

public class ClientView {

    public static void main(String[] args) {



        ClientServiceController clientServiceController=new ClientServiceController();
        ClientController clientController=new ClientController();
        ServiceController serviceController=new ServiceController();
        //clientController.createClient("aasd","asdasd","asdasd");
        clientController.getAllClient();
        serviceController.getAllService();
        clientServiceController.getAllClientService();
        //clientServiceController.createClientService();



        //serviceController.createService("cables");




        System.out.println("Hello world! all created");
    }
}
