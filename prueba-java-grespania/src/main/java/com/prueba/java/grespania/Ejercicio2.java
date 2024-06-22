package main.java.com.prueba.java.grespania;

import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        ejecutarEjercicio2();
    }

    public static void ejecutarEjercicio2() {
        Scanner sc = new Scanner(System.in);

        int numero = -1;
        while (numero < 0) {
            System.out.print("Por favor escribe un número entero positivo: ");
            numero = sc.nextInt();
        }

        // Para calcular el número de cifras convertimos el int en String y miramos el
        // tamaño.
        System.out.println(String.format("El número %d tiene %d cifras.", numero, Integer.toString(numero).length()));
        sc.close();
    }
}