package co.edu.sena.adso.ejercicios;

/*
* La tienda M&M require el desarrollo de un software para calcular el valor final
* de una compra por parte de los usuarios, por cada compra se debe realizar un descuento del
* 20%
* */

public class Ejercicio1 {

    public static void main(String[] args) { //inicio
        /*
        * Entrada
        * valor_compra = double
        * */
        double valor_compra;
        valor_compra = 895000;

        /*
        * Procesos
        * porcentaje_descuento = valor_compra * 0.2
        * total_pagar = valor_compra - porcentaje_descuento
        * */

        double porcentaje_descuento = valor_compra * 0.2;
        double total_pagar = valor_compra - porcentaje_descuento;

        /*
        * Salida
        * Imprimir total_pagar
        * */

        System.out.println("Hola estas en la tienda M&M");
        System.out.println("Su compra fue de: " + valor_compra);
        System.out.println("Su descuento fue de: " + porcentaje_descuento);
        System.out.println("El valor de su pago es de: " + total_pagar);


    } //fin

}
