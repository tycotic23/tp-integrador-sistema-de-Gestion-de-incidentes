package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.SpecialityController;
import com.sistemaIncidentes.controllers.TechnicianController;
import com.sistemaIncidentes.controllers.SpecialityTechnicianController;
import com.sistemaIncidentes.models.Technician;
import com.sistemaIncidentes.models.Speciality;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MenuRRHH implements Menu{

    private Scanner scan = new Scanner (System.in);
    private TechnicianController controllerTechnician=new TechnicianController();
    private SpecialityController controllerSpeciality=new SpecialityController();
    private SpecialityTechnicianController specialityTechnicianController=new SpecialityTechnicianController();

    @Override
    public void printMenu() {
        System.out.println("1-Ver todos los técnicos");
        System.out.println("2-Ver un técnico");
        System.out.println("3-Dar de baja un técnico");
        System.out.println("4-Actualizar un técnico");
        System.out.println("5-Dar alta de un técnico");
        System.out.println("6-Asignar nueva especialidad a un técnico");
        System.out.println("7-Crear informe de incidentes");
        System.out.println("8-Consultar técnico con más incidentes resueltos en los últimos n días");
        System.out.println("9-Consultar técnico con más incidentes resueltos de cierta especialidad en los últimos n días");
        System.out.println("10-Consultar técnico más rápido");
    }

    @Override
    public void selectOption(int option) {
        switch (option){
            //ver todos los tecnicos
            case 1:
                listTechnician();
                break;
            //Ver un tecnico
            case 2:
                getTechnician();
                break;
            //Borrar un tecnico
            case 3:
                removeTechnician();
                break;
            //Actualizar un tecnico
            case 4:
                updateTechnician();
                break;
            //alta de tecnico
            case 5:
                addTechnician();
                break;
            //darle un servicio a un cliente
            case 6:
                addSpecialityToTechnician();
                break;
            //Crear informe de incidentes
            case 7:
                listTechnicianWithIncidents();
                break;
            //encontrar tecnico con mas incidentes resueltos
            case 8:
                technicianWithMostIncidentsSolvedInNDays();
                break;
            //Consultar técnico con más incidentes resueltos de cierta especialidad en los últimos n días
            case 9:
                technicianWithMostIncidentsSolvedInNDaysSpeciality();
                break;
            //Consultar técnico más rápido
            case 10:
                getFasterTechnician();
                break;
        }

    }

    @Override
    public boolean isValidOption(int option) {
        return option>=1 && option <=10;
    }


    private void getFasterTechnician(){
        Technician fasterTechinician=controllerTechnician.getAllTechnician().stream().min(Comparator.comparing(Technician::getAverage)).get();
        if(fasterTechinician!=null){
            System.out.println("El técnico con mejor promedio de tiempo al resolver incidentes es "+fasterTechinician);
        }
        else{
            System.out.println("No hay suficientes datos");
        }
    }


    private void addTechnician(){
        String change;
        //pedir nuevos datos
        //nombre
        System.out.print("Nombre: ");
        String name=scan.next();
        System.out.println();
        //email
        System.out.print("Email: ");
        String email=scan.next();
        System.out.println();

        //persistir
        Technician technician = new Technician(name,email);
        controllerTechnician.createTechnician(technician);
        int speciality;
        String other;
        do{
            System.out.print("Elija una especialidad para el técnico (escriba su id): ");
            listSpecialities();
            speciality=scan.nextInt();
            System.out.println();
            //persistir la especialidad y crear la relacion
            specialityTechnicianController.createSpecialityTechnician(controllerSpeciality.getSpeciality((long)speciality),technician);
            //preguntar si desea agregar otra
            do {
                System.out.print("¿Desea agregar otra? s/n");
                other=scan.next();
            }while(!other.equals("s") && !other.equals("n"));
        }while(!other.equals("n"));
    }

    private void addSpecialityToTechnician(){
        int technicianId=0;
        int specialityId=0;
        System.out.println("Ingrese el id del techniciane a añadir un servicio");
        //mostrar techniciane original
        technicianId=scan.nextInt();
        Technician technician=controllerTechnician.getTechnician((long)technicianId);
        System.out.println(technician);
        //mostrar una lista de todos los servicios
        listSpecialities();
        //pedir id del servicio
        System.out.println("Ingrese el id del servicio elegido");
        specialityId=scan.nextInt();
        Speciality speciality=controllerSpeciality.getSpeciality((long)specialityId);
        //crear relacion
        specialityTechnicianController.createSpecialityTechnician(speciality,technician);
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

    private void technicianWithMostIncidentsSolvedInNDays(){
        int n;
        System.out.print("Ingrese la cantidad de días: ");
        n=scan.nextInt();
        System.out.println();
        List<Technician> technicians=controllerTechnician.getAllTechnician();
        Technician technician= technicians.stream().max(Comparator.comparing(t -> t.getIncidentsSolvedNumberFromLastNDays(n))).orElse(null);
        if(technician!=null){
            System.out.println("El técnico con más incidentes resueltos en los últimos "+n+" días es" + technician);
        }
    }

    private void technicianWithMostIncidentsSolvedInNDaysSpeciality(){
        int n;
        int specialityID;
        System.out.print("Ingrese la cantidad de días: ");
        n=scan.nextInt();
        System.out.println();
        System.out.print("Ingrese el id de la especialidad: ");
        listSpecialities();
        specialityID=scan.nextInt();
        System.out.println();
        Speciality speciality = controllerSpeciality.getSpeciality((long)specialityID);
        List<Technician> technicians=controllerTechnician.getAllTechnician();
        Technician technician=technicians.stream()
                .filter(t -> t.hasSpeciality(speciality.getName()))
                .max(Comparator.comparing(t -> t.getIncidentsSolvedNumberFromLastNDays(n))).get();
        if(technician!=null && technician.getIncidentsSolvedNumberFromLastNDays(n)!=0){
            System.out.println("El técnico con más incidentes resueltos en los últimos " + n + " días es" + technician);
        }
        else{
            System.out.println("No hay suficientes datos de esa fecha");
        }


    }

    private void listTechnicianWithIncidents(){
        for (Technician t: controllerTechnician.getAllTechnician()){
            System.out.println(t.toStringTechIncidents());
        }
    }

    private void listSpecialities(){
        for (Speciality s: controllerSpeciality.getAllSpeciality()){
            System.out.println(s);
        }
    }

    private void getTechnician(){
        int technicianId=0;
        System.out.println("Ingrese el id del tecnico");
        technicianId=scan.nextInt();
        System.out.println(controllerTechnician.getTechnician((long)technicianId));
    }


}
