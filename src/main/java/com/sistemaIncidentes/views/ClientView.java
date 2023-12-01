package com.sistemaIncidentes.views;


import com.sistemaIncidentes.controllers.ServiceController;
import com.sistemaIncidentes.models.MailSender;

import java.util.Scanner;

public class ClientView {

    public static void main(String[] args) {

        //antes de crear el menu verifica si las tablas ya existen y tienen datos por defecto
        //firstCreation();
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

    public static void firstCreation(){
        final ServiceController serviceController=new ServiceController();
        if(serviceController.getAllServiceAndRemoved().isEmpty()){
            //si esta tabla esta vacia es la primera vez que se ejecuta
            //crear objetos necesarios para la prueba del programa

            //servicios
            //tecnicos

        }
    }


}
