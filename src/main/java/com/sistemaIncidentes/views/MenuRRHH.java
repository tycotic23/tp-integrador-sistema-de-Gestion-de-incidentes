package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.*;
import com.sistemaIncidentes.models.Technician;
import com.sistemaIncidentes.models.Speciality;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MenuRRHH implements Menu{

    private final Scanner scan = new Scanner (System.in);
    private final TechnicianController technicianController=new TechnicianController();
    private final SpecialityController controllerSpeciality=new SpecialityController();
    private final SpecialityTechnicianController specialityTechnicianController=new SpecialityTechnicianController();

    private final ProblemController problemController=new ProblemController();

    private final IncidentController incidentController=new IncidentController();

    @Override
    public void printMenu() {
        System.out.println("1-Ver todos los técnicos ");
        System.out.println("2-Ver un técnico ");
        System.out.println("3-Dar de baja un técnico ");
        System.out.println("4-Actualizar un técnico ");
        System.out.println("5-Dar alta de un técnico ");
        System.out.println("6-Asignar una nueva especialidad a un técnico ");
        System.out.println("7-Crear informe de incidentes ");
        System.out.println("8-Consultar técnico con más incidentes resueltos con respecto a un numero especifico de dias ");
        System.out.println("9-Consultar técnico con más incidentes resueltos de cierta especialidad con respecto a un numero especifico de dias ");
        System.out.println("10-Consultar técnico más rápido en la resolucion de incidentes");
    }

    @Override
    public void selectOption(int option) {
        switch (option) {
            //ver todos los tecnicos
            case 1 -> listTechnician();

            //Ver un tecnico
            case 2 -> getTechnician();

            //Borrar un tecnico
            case 3 -> removeTechnician();

            //Actualizar un tecnico
            case 4 -> updateTechnician();

            //alta de tecnico
            case 5 -> addTechnician();

            //darle un servicio a un cliente
            case 6 -> addSpecialityToTechnician();

            //Crear informe de incidentes
            case 7 -> listTechnicianWithIncidents();

            //encontrar tecnico con mas incidentes resueltos
            case 8 -> technicianWithMostIncidentsSolvedInNDays();

            //Consultar técnico con más incidentes resueltos de cierta especialidad en los últimos n días
            case 9 -> technicianWithMostIncidentsSolvedInNDaysSpeciality();

            //Consultar técnico más rápido
            case 10 -> getFasterTechnician();
        }

    }

    @Override
    public boolean isValidOption(int option) {
        return option>=1 && option <=10;
    }


    private void getFasterTechnician(){
        Technician fasterTechinician=technicianController.getAllTechnician().stream().min(Comparator.comparing(Technician::getAverage)).get();
        if(fasterTechinician!=null){
            System.out.println("El técnico con mejor promedio de tiempo al resolver incidentes es "+fasterTechinician);
        }
        else{
            System.out.println("No hay suficientes datos ");
        }
    }


    private void addTechnician(){
        //pedir nuevos datos
        //nombre
        System.out.print("Nombre: ");
        String name=scan.nextLine();
        System.out.println();
        //email
        System.out.print("Email: ");
        String email=scan.next();
        System.out.println();

        //persistir
        Technician technician = new Technician(name,email);
        technicianController.createTechnician(technician);
        int speciality;
        String other;
        do{
            System.out.print("Elija una especialidad para el técnico (escriba su id): ");
            listSpecialities();
            speciality=scan.nextInt();
            System.out.println();
            //persistir la especialidad y crear la relacion
            specialityTechnicianController.createSpecialityTechnician(controllerSpeciality.getSpeciality(speciality),technician);
            //preguntar si desea agregar otra
            do {
                System.out.print("¿Desea agregar otra? s/n ");
                other=scan.next();
            }while(!other.equals("s") && !other.equals("n"));
        }while(!other.equals("n"));
    }

    private void addSpecialityToTechnician(){
        int technicianId;
        int specialityId;
        System.out.println("Ingrese el id del tecnico a añadir un servicio ");
        //mostrar tecnico original
        technicianId=scan.nextInt();
        Technician technician=technicianController.getTechnician(technicianId);
        System.out.println(technician);
        //mostrar una lista de todos los servicios
        listSpecialities();
        //pedir id del servicio
        System.out.println("Ingrese el id del servicio elegido ");
        specialityId=scan.nextInt();
        Speciality speciality=controllerSpeciality.getSpeciality(specialityId);
        //crear relacion
        specialityTechnicianController.createSpecialityTechnician(speciality,technician);
        System.out.println("Añadido con éxito ");
    }



    private void removeTechnician(){
        int technicianId;
        System.out.println("Ingrese el id del tecnico a borrar ");
        technicianId=scan.nextInt();
        //borrado logico del tecnico
        technicianController.deleteLogicalTechnician(technicianId);
        //borrado logico de todos sus incidentes y sus respectivos problemas tambien
        Technician technician =technicianController.getTechnician(technicianId);
        technician.getIncidents().forEach(
                i-> {
                    incidentController.deleteLogicalIncident(i.getId());
                    i.getProblems().forEach(
                            p->problemController.deleteLogicalProblem(p.getId())
                    );
                });
    }

    private void updateTechnician(){
        int technicianId;
        String change;
        System.out.println("Ingrese el id del tecnico a modificar ");
        //mostrar tecnico original
        technicianId=scan.nextInt();
        Technician technician=technicianController.getTechnician(technicianId);
        System.out.println(technician);
        //pedir nuevos datos
        //razon social
        do {
            System.out.print("¿Cambiar nombre? s/n ");
            change=scan.next();
        }while(!change.equals("s") && !change.equals("n"));
        System.out.println();
        if(change.equals("s")){
            System.out.print("Nuevo nombre: ");
            change=scan.nextLine();
            technician.setName(change);
        }
        System.out.println();


        //email
        do {
            System.out.print("¿Cambiar email? s/n ");
            change=scan.next();
        }while(!change.equals("s") && !change.equals("n"));
        System.out.println();
        if(change.equals("s")){
            System.out.print("Nuevo email: ");
            change=scan.next();
            technician.setEmail(change);
        }
        System.out.println();

        //persistir
        technicianController.updateTechnician(technicianId,technician);
    }

    private void listTechnician(){
        List<Technician> technicians=technicianController.getAllTechnician();
        for (Technician t: technicians){
            System.out.println(t);
        }
        if(technicians.isEmpty()){
            System.out.println("No hay técnicos para técnicos");
        }
    }

    private void technicianWithMostIncidentsSolvedInNDays(){
        int n;
        System.out.print("Ingrese la cantidad de días: ");
        n=scan.nextInt();
        System.out.println();
        List<Technician> technicians=technicianController.getAllTechnician();
        Technician technician= technicians.stream().max(Comparator.comparing(t -> t.getIncidentsSolvedNumberFromLastNDays(n))).orElse(null);
        if(technician!=null){
            System.out.println("El técnico con más incidentes resueltos en los últimos "+n+" días es " + technician);
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
        Speciality speciality = controllerSpeciality.getSpeciality(specialityID);
        List<Technician> technicians=technicianController.getAllTechnician();
        Technician technician=technicians.stream()
                .filter(t -> t.hasSpeciality(speciality.getName()))
                .max(Comparator.comparing(t -> t.getIncidentsSolvedNumberFromLastNDays(n))).get();
        if(technician!=null && technician.getIncidentsSolvedNumberFromLastNDays(n)!=0){
            System.out.println("El técnico con más incidentes resueltos en los últimos " + n + " días es " + technician);
        }
        else{
            System.out.println("No hay suficientes datos de esa fecha ");
        }


    }

    private void listTechnicianWithIncidents(){
        for (Technician t: technicianController.getAllTechnician()){
            t.report();
            System.out.println("-----------------------------");
        }
    }

    private void listSpecialities(){
        List<Speciality> specialities =controllerSpeciality.getAllSpeciality();
        for (Speciality s: specialities){
            System.out.println(s);
        }
        if(specialities.isEmpty()){
            System.out.println("No hay ninguna especialidad para mostrar");
        }
    }

    private void getTechnician(){
        int technicianId;
        System.out.println("Ingrese el id del tecnico ");
        technicianId=scan.nextInt();
        System.out.println(technicianController.getTechnician(technicianId));
    }


}
