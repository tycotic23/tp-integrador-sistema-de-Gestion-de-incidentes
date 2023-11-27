package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.*;
import com.sistemaIncidentes.models.Client;
import com.sistemaIncidentes.models.Service;
import com.sistemaIncidentes.models.SpecialityTypeProblem;
import com.sistemaIncidentes.models.Technician;

import java.util.Scanner;

public class MenuOperator implements Menu {
    private ClientController clientController = new ClientController();

    private ServiceController serviceController=new ServiceController();
    private ClientServiceController clientServiceController= new ClientServiceController();
    private IncidentController IncidentController = new IncidentController();
    private ControllerProblem controllerProblem = new ControllerProblem();
    private ControllerSpeciality controllerSpeciality = new ControllerSpeciality();
    private ControllerTechnician controllerTechnichian = new ControllerTechnician();
    private ControllerTypeProblem controllerTypeProblem = new ControllerTypeProblem();
    private SpecialityTypeProblem specialityType = new SpecialityTypeProblem();
    private ControllerTechnician controllerTechnician = new ControllerTechnician();

    private Scanner scan = new Scanner (System.in);
    @Override
    public void printMenu() {
        System.out.println("1-Ver todos los clientes");
        System.out.println("2-Ver todos los tecnicos");
        System.out.println("3-ver tecnicos disponibles para este problema");
        System.out.println("4-ver datos del cliente");
        System.out.println("5-registrar incidente");
        System.out.println("6-dar por finalizado un incidente");
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
            //Ver todos los tecnicos
            case 2:
                listTechnician();
                break;
            //ver tecnicos disponibles para un problema
            case 3:

                break;
            //Actualizar un cliente
            case 4:
                getClient();
                break;
            //alta de cliente
            case 5:

                break;
            //darle un servicio a un cliente
            case 6:

                break;
        }

    }


    private void listClient(){
        for (Client c: clientController.getAllClient()){
            System.out.println(c);
        }
    }

    private void listTechnician(){
        for (Technician c: controllerTechnichian.getAllTechnician()){
            System.out.println(c);
        }
    }




    private void getClient(){
        int clientId=0;
        System.out.println("Ingrese el id del cliente");
        clientId=scan.nextInt();
        System.out.println(clientController.getClient((long)clientId));
    }
    private void getTechnician(){
        int technicianId=0;
        System.out.println("Ingrese el id del Tecnico");
        technicianId=scan.nextInt();
        System.out.println(controllerTechnician.getTechnician((long)technicianId));
    }
}
