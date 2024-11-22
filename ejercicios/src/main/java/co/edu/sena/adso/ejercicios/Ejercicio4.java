package co.edu.sena.adso.ejercicios;

import java.util.Scanner;

public class Ejercicio4 {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Cuantos numeros quiere ingresar");

        int cantidadPar = 0;
        int cantidadImpar = 0;
        int cantidadCero = 0;

        boolean hayMultiplosDe3 =  false;

        int cantidad = sc.nextInt();

        //(1)Variable de control - definirla e inicializarla
        //(2)Validacion de la variable de control - sentencia logica que permita ingresar al ciclo
        //(3)Modificacion de la variable de contro - aumentar, disminuir o modificar la variable para que no se cumpla la validacion

        for(int i = 0 /*(1)*/; i < cantidad /*(2)*/; i++ /*(3)*/) {
            System.out.println("Ingrese numero: ");
            int numero = sc.nextInt();

            if(numero == 0) {
                cantidadCero++;
            } else if(numero % 2 == 0) {
                cantidadPar++;
            } else {
                cantidadImpar++;
            }

            //***********
            if(numero != 0 && numero % 3 == 0) {
                hayMultiplosDe3 = true;
            }

        }//0,2,8,4,11

        System.out.println("La cantidad de ceros es " + cantidadCero);
        System.out.println("La cantidad de pares es " + cantidadPar);
        System.out.println("La cantidad de impares es " + cantidadImpar);
        System.out.println(hayMultiplosDe3 ? "Si hay multiplos de 3" : "No hay multiplos de 3");

        System.out.println("El programa finalizo");
    }

}
