package co.edu.sena.adso.ejercicios;

import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double resultado;

        System.out.println("Ingrese numero: ");
        int numero = sc.nextInt();

        resultado = numero % 2;

        if(numero == 0) {
            System.out.println("El numero es cero");
        } else if(resultado == 0) {
            System.out.println("El numero de par");
        } else {
            System.out.println("El numero es impar");
        }

    }

}
