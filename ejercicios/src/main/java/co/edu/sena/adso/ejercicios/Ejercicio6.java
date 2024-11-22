package co.edu.sena.adso.ejercicios;

import java.util.Scanner;

public class Ejercicio6 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //(1)Variable de control - definirla e inicializarla
        //(2)Validacion de la variable de control - sentencia logica que permita ingresar al ciclo
        //(3)Modificacion de la variable de contro - aumentar, disminuir o modificar la variable para que no se cumpla la validacion

        //(1)
        String opcion = null;
        do {
            System.out.println("** Menu Principal **");
            System.out.println("A. Saludar");
            System.out.println("B. Saludar en ingles");
            System.out.println("C. Saludar en portugues");
            System.out.println("D. Salir");

            System.out.println("Ingrese opcion:");
            opcion = sc.next();

            switch (opcion) {
                case "A":
                    System.out.println("Saludando en español: Hola");
                    sc.next();
                    break;
                case "B":
                    System.out.println("Saludando en ingles: Hi");
                    sc.next();
                    break;
                case "C":
                    System.out.println("Saludando en portugues: Ola");
                    sc.next();
                    break;
                case "D":
                    System.out.println("Chao se finalizo el programa");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }

        } while (!opcion.equals("D") /*(2)*/);

            //!(opcion == "D")

    }

}
