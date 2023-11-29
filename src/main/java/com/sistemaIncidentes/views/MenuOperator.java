package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.*;
import com.sistemaIncidentes.models.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MenuOperator implements Menu {
    private ClientController clientController = new ClientController();

    private ServiceController serviceController=new ServiceController();
    private ClientServiceController clientServiceController= new ClientServiceController();
    private IncidentController incidentController = new IncidentController();
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
                listTechnicianForTypeProblems();
                break;
            //Ver datos de un cliente
            case 4:
                getClient();
                break;
            //registrar un incidente
            case 5:
                registerIncident();
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
    private void listTechnicianForTypeProblems(){
        int technicianId=0;
        int problemId=0;

        System.out.println("Ingrese el del tipo de problema");
        listTypeProblems();
        problemId=scan.nextInt();
        List<Technician> technicians=getTechnicianForTypeProblems((long)problemId);
        for (Technician t: technicians){
            System.out.println(t);
        }
    }

    private List<Technician> getTechnicianForTypeProblems(long problemId){
        TypeProblem typeProblem = typeProblemController.getTypeProblem(problemId);
        return technicianController.getAllTechnician().stream()
                .filter(t->t.canResolveTypeProblem(typeProblem)).filter(Technician::isAvailable).collect(Collectors.toList());
    }

    private void listProblems(){
        for (Problem p: problemController.getAllProblem()){
            System.out.println(p);
        }
    }

    private void listTypeProblems(){
        for (TypeProblem p: typeProblemController.getAllTypeProblem()){
            System.out.println(p);
        }
    }
    private void listSpecialities(){
        for (Speciality s: specialityController.getAllSpeciality()){
            System.out.println(s);
        }
    }

    private void listServices(){
        for (Service s: serviceController.getAllService()){
            System.out.println(s);
        }
    }

    private void registerIncident(){
        //pedir datos del usuario (razon y cuit)
        System.out.print("Ingrese la razón social: ");
        String business=scan.next();
        System.out.println();
        System.out.print("Ingrese el cuit/cuil: ");
        String cuit=scan.next();
        System.out.println();
        //obtener el cliente
        try{
            Client client=clientController.getAllClient().stream()
                    .filter(c->c.getCUIT().equals(cuit)&&c.getBusinessName().equals(business))
                    .findFirst().orElse(null);
            System.out.println("Elija el id del servicio en cuestión: ");
            //listar los servicios
            for (Service s: client.getServicesList()){
                System.out.println(s);
            }
            //pedir el servicio en cuestion
            int serviceId= scan.nextInt();
            System.out.println();
            Service service=serviceController.getService((long)serviceId);
            //pedir datos del incidente: tipo de problema
            System.out.println("¿De qué tipo de problema se trata? Elija su id: ");
            listTypeProblems();
            int typeProblemId= scan.nextInt();
            System.out.println();
            TypeProblem typeProblem=typeProblemController.getTypeProblem((long)typeProblemId);
            //listar los tecnicos en base al tipo de problema
            System.out.println("Elija un técnico disponible de la siguiente lista: ");
            for(Technician t: getTechnicianForTypeProblems((long)typeProblemId)){
                System.out.println(t);
            }
            //elegir un tecnico
            int technicianId= scan.nextInt();
            System.out.println();
            Technician technician=technicianController.getTechnician((long)technicianId);
            //crear incidente
            Incident incident= incidentController.createIncident(client,service,technician);
            //actualizar estado de disponibilidad del tecnico
            technicianController.setAvailableForId((long)technicianId,false);
            //pedir problemas
            String problema;
            double time;
            boolean complex;
            String other;
            do{
                System.out.print("Describa su problema: ");
                problema=scan.next();
                System.out.println();
                System.out.print("¿Tiempo estimado? Recuerde que el tiempo máximo es "+typeProblem.getMaxTime()+" :");
                time= scan.nextDouble();
                System.out.println();
                System.out.print("Incerte complejidad: 0=simple 1=complejo ");
                complex=scan.nextInt()!=0;
                System.out.println();
                //persistir la especialidad y crear la relacion

                problemController.createProblem(new Problem(complex,time,problema,typeProblem),typeProblem,incident);
                //preguntar si desea agregar otra
                do {
                    System.out.print("¿Desea agregar otro problema? s/n");
                    other=scan.next();
                }while(!other.equals("s") && !other.equals("n"));
            }while(!other.equals("n"));
            System.out.println("Incidente agregado con éxito");
            //System.out.println("La fecha de resolución estimada es "+ incident.getEstimatedDate());
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println( "Cliente no encontrado");
        }



        //

    }



}
