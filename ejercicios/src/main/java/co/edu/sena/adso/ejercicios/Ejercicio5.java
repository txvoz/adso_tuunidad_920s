package co.edu.sena.adso.ejercicios;

import java.util.Scanner;

public class Ejercicio5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //(1)Variable de control - definirla e inicializarla
        //(2)Validacion de la variable de control - sentencia logica que permita ingresar al ciclo
        //(3)Modificacion de la variable de contro - aumentar, disminuir o modificar la variable para que no se cumpla la validacion


        String ingresar = "si"; //(1)

        while (ingresar.toLowerCase().equals("si")) //(2)
        {
            System.out.println("Ingrese numero: ");
            int numero = sc.nextInt();
            System.out.println("Desea ingresar mas numeros? si/no");
            ingresar = sc.next(); //(3)
        }

        System.out.println("Gracias por programar con el SENA");

    }

}
