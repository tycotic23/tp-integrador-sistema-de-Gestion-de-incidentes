package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.*;
import com.sistemaIncidentes.models.*;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class MenuOperator implements Menu {
    private ClientController clientController = new ClientController();

    private ServiceController serviceController=new ServiceController();
    private ClientServiceController clientServiceController= new ClientServiceController();
    private IncidentController IncidentController = new IncidentController();
    private ProblemController problemController = new ProblemController();
    private SpecialityController specialityController = new SpecialityController();
    private TechnicianController TechnicianControler = new TechnicianController();
    private TypeProblemController typeProblemController = new TypeProblemController();
    private SpecialityTypeProblem specialityType = new SpecialityTypeProblem();
    private TechnicianController technicianController = new TechnicianController();
    private SpecialityTechnicianController specialityTechnicianController = new SpecialityTechnicianController();

    private Scanner scan = new Scanner (System.in);
    @Override
    public void printMenu() {
        System.out.println("1-Ver todos los clientes");
        System.out.println("2-Ver todos los tecnicos");
        System.out.println("3-ver tecnicos disponibles para x problema");
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
            //Ver datos de un cliente
            case 4:
                getClient();
                break;
            //registrar un incidente
            case 5:

                break;
            //dar por finalizado un incidente
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
        for (Technician c: technicianController.getAllTechnician()){
            System.out.println(c);
        }
    }




    private void getClient(){
        int clientId=0;
        System.out.println("Ingrese el id del cliente");
        clientId=scan.nextInt();
        System.out.println(clientController.getClient((long)clientId));
    }
    private void getTechnicianForTypeProblems(){
        int technicianId=0;
        int problemId=0;
        System.out.println("Ingrese el id del Tecnico");
        technicianId=scan.nextInt();
        System.out.println("Ingrese el del tipo de problema");
        problemId=scan.nextInt();
        Problem problem = problemController.getProblem((long)problemId);
        List<Technician> technicians=technicianController.getAllTechnician();
        //Technician technician=technicians.stream().filter(t -> t.hasSpeciality(speciality.getName()));
        /*if(){
            System.out.println("");
        }
        else{
            System.out.println("");
        }*/
    }

    private void listProblems(){
        for (Problem s: problemController.getAllProblem()){
            System.out.println(s);
        }
    }
    private void listSpecialities(){
        for (Speciality s: specialityController.getAllSpeciality()){
            System.out.println(s);
        }
    }


}
