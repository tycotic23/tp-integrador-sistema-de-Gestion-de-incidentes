package com.sistemaIncidentes.views;


import com.sistemaIncidentes.controllers.*;
import com.sistemaIncidentes.models.*;

import java.util.Scanner;

public class ClientView {

    public static void main(String[] args) {

        //antes de crear el menu verifica si las tablas ya existen y tienen datos por defecto
        firstCreationExample();
        //menu
        Scanner scan = new Scanner (System.in);
        int option;
        Menu menu=new MenuComercial();
        //el menu cambia segun el tipo de usuario
        String fin;
        do {
            do {
                System.out.println("¿Qué tipo de usuario es? ");
                System.out.println("Elija una opcion: ");
                System.out.println("1-RRHH ");
                System.out.println("2-Comercial ");
                System.out.println("3-Mesa de ayuda ");
                option = scan.nextInt();
                switch (option) {
                    case 1 -> menu = new MenuRRHH();
                    case 2 -> menu = new MenuComercial();
                    case 3 -> menu = new MenuOperator();
                }
            } while (option < 1 || option > 3);

            do {
                //imprimir menu
                System.out.println("Elija una opcion: ");
                menu.printMenu();
                System.out.println("0- Salir");
                do {
                    option = scan.nextInt();
                } while (!menu.isValidOption(option) && option != 0);
                //si la opcion es 0 sale, sino es otra opcion del menu
                if (option != 0) {
                    //ejecutar la opcion elegida
                    menu.selectOption(option);

                    //finalizar o continuar el bucle
                    do {
                        System.out.print("¿Continuar? s/n ");
                        fin = scan.next();
                    } while (!fin.equals("s") && !fin.equals("n"));
                    System.out.println();
                } else {
                    fin = "n";
                }

            } while (!fin.equals("n"));
            //volver o no al menu principal
            do {
                System.out.print("¿Desea volver al menu principal? s/n ");
                fin = scan.next();
            } while (!fin.equals("s") && !fin.equals("n"));
            System.out.println();
        }while(!fin.equals("n"));
    }

    public static void firstCreationExample(){
        final ServiceController serviceController=new ServiceController();
        if(serviceController.getAllServiceAndRemoved().isEmpty()){
            //si esta tabla esta vacia es la primera vez que se ejecuta
            //crear objetos necesarios para la prueba del programa
            final TechnicianController technicianController= new TechnicianController();
            final ClientController clientController=new ClientController();
            final ClientServiceController clientServiceController = new ClientServiceController();
            final SpecialityController specialityController= new SpecialityController();
            final SpecialityTechnicianController specialityTechnicianController = new SpecialityTechnicianController();
            final TypeProblemController typeProblemController=new TypeProblemController();
            final SpecialityTypeProblemController specialityTypeProblemController= new SpecialityTypeProblemController();

            //clientes
            Client client = clientController.createClient("Raul","124453","maildeprueba@asdasd");
            Client client2=clientController.createClient("karen","4534534","asdasd@adsasd");
            //servicios
            Service service=serviceController.createService("cables");
            Service service2=serviceController.createService("explosivo");
            //tecnicos
            Technician technician =technicianController.createTechnician(new Technician("Franco Ruperto","adsadasd"));
            Technician technician2 =technicianController.createTechnician(new Technician("Sofia Garibaldi","adsadasd"));
            //especialidades
            Speciality speciality=specialityController.createSpeciality("mecánica");
            Speciality speciality2=specialityController.createSpeciality("eléctrica");
            Speciality speciality3=specialityController.createSpeciality("química");
            //tipos de problema
            TypeProblem typeProblem =typeProblemController.createTypeProblem(new TypeProblem(50,"Temperatura elevada",2));
            TypeProblem typeProblem2 =typeProblemController.createTypeProblem(new TypeProblem(150,"Sistema caído",15));
            TypeProblem typeProblem3 =typeProblemController.createTypeProblem(new TypeProblem(2,"Mala praxis",1));
            TypeProblem typeProblem4 =typeProblemController.createTypeProblem(new TypeProblem(1050,"Otros problemas",20));

            //relacion tipos de problema con especialidades
            specialityTypeProblemController.createSpecialityTypeProblem(speciality2,typeProblem);
            specialityTypeProblemController.createSpecialityTypeProblem(speciality,typeProblem4);
            specialityTypeProblemController.createSpecialityTypeProblem(speciality3,typeProblem4);
            specialityTypeProblemController.createSpecialityTypeProblem(speciality2,typeProblem2);
            specialityTypeProblemController.createSpecialityTypeProblem(speciality,typeProblem3);

            //relacion clientes y servicios
            clientServiceController.createClientService(client,service);
            clientServiceController.createClientService(client,service2);
            clientServiceController.createClientService(client2,service);

            //relacion tecnicos y especialidades
            specialityTechnicianController.createSpecialityTechnician(speciality,technician2);
            specialityTechnicianController.createSpecialityTechnician(speciality2,technician2);
            specialityTechnicianController.createSpecialityTechnician(speciality2,technician);
            specialityTechnicianController.createSpecialityTechnician(speciality3,technician);

        }
    }


}
