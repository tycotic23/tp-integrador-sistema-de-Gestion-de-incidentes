package com.sistemaIncidentes.views;

import com.sistemaIncidentes.controllers.*;
import com.sistemaIncidentes.models.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MenuOperator implements Menu {
    private final ClientController clientController = new ClientController();

    private final ServiceController serviceController=new ServiceController();
    private final IncidentController incidentController = new IncidentController();
    private final ProblemController problemController = new ProblemController();
    private final TypeProblemController typeProblemController = new TypeProblemController();
    private final TechnicianController technicianController = new TechnicianController();
    private final MailSender mailSender=new MailSender();

    private final Scanner scan = new Scanner (System.in);
    @Override
    public void printMenu() {
        System.out.println("1-Ver todos los clientes ");
        System.out.println("2-Ver todos los tecnicos ");
        System.out.println("3-ver tecnicos disponibles para un problema ");
        System.out.println("4-ver datos del cliente ");
        System.out.println("5-registrar incidente ");
        System.out.println("6-dar por finalizado un incidente ");
    }

    @Override
    public boolean isValidOption(int option) {
        return option>=1 && option <=6;
    }

    @Override
    public void selectOption(int option) {
        switch (option) {
            //ver todos los clientes
            case 1 -> listClient();

            //Ver todos los tecnicos
            case 2 -> listTechnician();

            //ver tecnicos disponibles para un problema
            case 3 -> listTechnicianForTypeProblems();

            //Ver datos de un cliente
            case 4 -> getClient();

            //registrar un incidente
            case 5 -> registerIncident();

            //dar por finalizado un incidente
            case 6 -> closeIncident();
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



    private void closeIncident(){
        int technicianId;
        String considerations;
        //pedir id del tecnico
        System.out.println("Ingrese el id del técnico ");
        technicianId=scan.nextInt();
        //pedir consideraciones del tecnico ( si las hay)
        System.out.println("Continuar... ");
        scan.nextLine();
        System.out.println("Consideraciones del técnico: ");
        considerations=scan.nextLine();
        //modificar y persistir
        try {
            Technician technician = technicianController.getTechnician(technicianId);
            technicianController.setAvailableForId(technicianId, true);
            Incident incident=technician.getActiveIncident();
            incidentController.closeIncident(incident.getId(),considerations);
            System.out.println("Cerrado con éxito ");
            //notificar al cliente a traves de su contacto brindado que su incidente ya fue cerrado
            Client client=incident.getClient();
            mailSender.sendEmail(client.getEmail(),
                    "At. "+client.getBusinessName()+"- Incidente cerrado",
                    "Se le informa que el incidente NRO "+incident.getId()+" ha sido cerrado satisfactoriamente por el técnico "+technician.getName()+"." +
                            "\nA continuación se detallan consideraciones del técnico: "+considerations);
            System.out.println( "Mail enviado");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println( "Técnico no encontrado ");

        }
    }


    private void getClient(){
        int clientId;
        System.out.println("Ingrese el id del cliente ");
        clientId=scan.nextInt();
        System.out.println(clientController.getClient(clientId));
    }
    private void listTechnicianForTypeProblems(){
        int problemId;

        System.out.println("Ingrese el del tipo de problema ");
        listTypeProblems();
        problemId=scan.nextInt();
        List<Technician> technicians=getTechnicianForTypeProblems(problemId);
        for (Technician t: technicians){
            System.out.println(t);
        }
        if(technicians.isEmpty()){
            System.out.println("No hay técnicos disponibles");
        }
    }

    private List<Technician> getTechnicianForTypeProblems(long problemId){
        TypeProblem typeProblem = typeProblemController.getTypeProblem(problemId);
        return technicianController.getAllTechnician().stream()
                .filter(t->t.canResolveTypeProblem(typeProblem)).filter(Technician::isAvailable).collect(Collectors.toList());
    }



    private void listTypeProblems(){
        List<TypeProblem> typesproblem=typeProblemController.getAllTypeProblem();
        for (TypeProblem p: typesproblem){
            System.out.println(p);
        }
        if(typesproblem.isEmpty()){
            System.out.println("No hay ningún tipo de problema");
        }
    }


    private void listServices(){
        List<Service> services=serviceController.getAllService();
        for (Service s: services){
            System.out.println(s);
        }
    }

    private boolean registerIncident(){
        scan.nextLine();
        //pedir datos del usuario (razon y cuit)
        System.out.print("Ingrese la razón social: ");
        String business=scan.nextLine();
        System.out.println();
        System.out.print("Ingrese el cuit/cuil: ");
        String cuit=scan.next();
        System.out.println();
        //obtener el cliente
        try{
            Client client=clientController.getAllClient().stream()
                    .filter(c->c.getCUIT().equals(cuit)&&c.getBusinessName().equals(business))
                    .findFirst().orElse(null);
            if(client==null){
                System.out.println("Cliente no encontrado: ");
                return false;
            }
            System.out.println("Elija el id del servicio en cuestión: ");
            //listar los servicios
            listServices();
            //pedir el servicio en cuestion
            int serviceId= scan.nextInt();
            System.out.println();
            Service service=serviceController.getService(serviceId);
            //pedir datos del incidente: tipo de problema
            System.out.println("¿De qué tipo de problema se trata? Elija su id: ");
            listTypeProblems();
            int typeProblemId= scan.nextInt();
            System.out.println();
            TypeProblem typeProblem=typeProblemController.getTypeProblem(typeProblemId);
            //listar los tecnicos en base al tipo de problema
            System.out.println("Elija un técnico disponible de la siguiente lista: ");
            for(Technician t: getTechnicianForTypeProblems(typeProblemId)){
                System.out.println(t);
            }
            //elegir un tecnico
            int technicianId= scan.nextInt();
            System.out.println();
            Technician technician=technicianController.getTechnician(technicianId);
            //crear incidente
            Incident incident= incidentController.createIncident(client,service,technician);
            //actualizar estado de disponibilidad del tecnico
            technicianController.setAvailableForId(technicianId,false);
            //pedir problemas
            String problema;
            double time;
            boolean complex;
            String other;

            do{
                System.out.println("Continuar...");
                scan.nextLine();
                System.out.print("Describa su problema: ");
                problema=scan.nextLine();
                System.out.println();
                System.out.print("¿Tiempo estimado? Recuerde que el tiempo máximo es "+typeProblem.getMaxTime()+" :");
                time= scan.nextDouble();
                System.out.println();
                System.out.print("Inserte complejidad: 0 =simple 1 =complejo ");
                complex=scan.nextInt()!=0;
                System.out.println();
                //persistir la especialidad y crear la relacion

                problemController.createProblem(new Problem(complex,time,problema,typeProblem),typeProblem,incident);
                //preguntar si desea agregar otra
                do {
                    System.out.print("¿Desea agregar otro problema? s/n ");
                    other=scan.next();
                }while(!other.equals("s") && !other.equals("n"));
            }while(!other.equals("n"));
            //informar tiempo estimado
            System.out.println("Incidente agregado con éxito ");
            System.out.println("La fecha de resolución estimada es "+ incident.getEstimatedDate());
            //notificar al tecnico a traves de su contacto brindado
            mailSender.sendEmail(technician.getEmail(), "Nuevo incidente "+incident.getId(),"Se le ha asignado un nuevo incidente. Datos del mismo: "+incident.report());
            System.out.println("Notificado con éxito ");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println( "Cliente no encontrado ");
            return false;
        }
        return true;


        //

    }



}
