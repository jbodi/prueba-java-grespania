package com.prueba.java.grespania;

import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        ejecutarEjercicio1();
    }

    public static void ejecutarEjercicio1() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Escribe el texto donde buscar: ");
        String text2 = sc.nextLine();

        System.out.print("Escribe el texto a buscar: ");
        String text3 = sc.nextLine();

        System.out.println(text2.contains(text3));
        sc.close();
    }
}
