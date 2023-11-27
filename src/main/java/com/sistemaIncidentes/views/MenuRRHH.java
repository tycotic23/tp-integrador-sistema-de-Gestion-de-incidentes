package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.ControllerSpeciality;
import com.sistemaIncidentes.controllers.ControllerTechnician;
import com.sistemaIncidentes.controllers.SpecialityTechnicianController;
import com.sistemaIncidentes.models.Technician;
import com.sistemaIncidentes.models.Service;
import com.sistemaIncidentes.models.Speciality;

import java.util.Scanner;

public class MenuRRHH implements Menu{

    private Scanner scan = new Scanner (System.in);
    private ControllerTechnician controllerTechnician;
    private ControllerSpeciality controllerSpeciality;
    private SpecialityTechnicianController specialityTechnicianController;

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




    private void addTechnician(){
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
        //technicianController.createTechnician(businessName,cuit,email);
    }

    private void addServiceToTechnician(){
        int technicianId=0;
        int serviceId=0;
        System.out.println("Ingrese el id del techniciane a añadir un servicio");
        //mostrar techniciane original
        technicianId=scan.nextInt();
        Technician technician=controllerTechnician.getTechnician((long)technicianId);
        System.out.println(technician);
        //mostrar una lista de todos los servicios
        listSpecialities();
        //pedir id del servicio
        System.out.println("Ingrese el id del servicio elegido");
        serviceId=scan.nextInt();
        //Service service=serviceController.getService((long)serviceId);
        //crear relacion
        //technicianServiceController.createTechnicianService(technician,service);
        System.out.println("Añadido con éxito");
    }



    private void removeTechnician(){
        int technicianId=0;
        System.out.println("Ingrese el id del techniciane a borrar");
        technicianId=scan.nextInt();
        controllerTechnician.deleteTechnician((long)technicianId);
    }

    private void updateTechnician(){
        int technicianId=0;
        String change;
        System.out.println("Ingrese el id del techniciane a modificar");
        //mostrar techniciane original
        technicianId=scan.nextInt();
        Technician technician=controllerTechnician.getTechnician((long)technicianId);
        System.out.println(technician);
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
        //technician.setBusinessName(change);
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
        //technician.setCUIT(change);
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
        technician.setEmail(change);
        //persistir
        //controllerTechnician.updateTechnician(technician);
    }

    private void listTechnician(){
        for (Technician t: controllerTechnician.getAllTechnician()){
            System.out.println(t);
        }
    }

    private void listSpecialities(){
        for (Speciality s: controllerSpeciality.getAllSpeciality()){
            System.out.println(s);
        }
    }

    private void getTechnician(){
        int technicianId=0;
        System.out.println("Ingrese el id del techniciane");
        technicianId=scan.nextInt();
        System.out.println(controllerTechnician.getTechnician((long)technicianId));
    }
}
