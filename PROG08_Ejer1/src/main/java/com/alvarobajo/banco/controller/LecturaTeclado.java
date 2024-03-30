package com.alvarobajo.banco.controller;

import java.util.Scanner;

/**
 * Clase que facilita la lectura de datos desde la entrada estándar.
 * Implementa la interfaz AutoCloseable para cerrar el recurso Scanner.
 * Utilizada para recoger textos, enteros y decimales desde el teclado.
 *
 * @author Álvaro Bajo Tabero
 */
public class LecturaTeclado implements AutoCloseable {

    private static final Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Escanea la entrada del usuario y devuelve un texto no vacío.
     *
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Texto no vacío ingresado por el usuario.
     */
    private static String escanearEntrada(String mensaje) {
        System.out.println(mensaje);
        return entradaTeclado.nextLine();
    }

    /**
     * Recoge un texto no vacío desde la entrada del usuario.
     *
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Texto no vacío ingresado por el usuario.
     */
    public static String recogerTexto(String mensaje) {
        String texto;
        do {
            texto = escanearEntrada(mensaje);
        } while (texto.isEmpty());
        return texto;
    }

    /**
     * Recoge un número entero desde la entrada del usuario.
     *
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Número entero ingresado por el usuario.
     */
    public static int recogerEntero(String mensaje) {
        String recogido;
        do {
            recogido = escanearEntrada(mensaje);
        } while (!esNumeroEntero(recogido));
        return Integer.parseInt(recogido);
    }

    /**
     * Recoge un número decimal desde la entrada del usuario.
     *
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Número decimal ingresado por el usuario.
     */
    public static double recogerDecimal(String mensaje) {
        String recogido;
        do {
            recogido = escanearEntrada(mensaje);
        } while (!esNumeroDecimal(recogido));
        return Double.parseDouble(recogido);
    }

    /**
     * Verifica si la cadena proporcionada es un número entero.
     *
     * @param cadenaNumero Cadena a verificar.
     * @return true si la cadena es un número entero; false, en caso contrario.
     */
    private static boolean esNumeroEntero(String cadenaNumero) {
        try {
            Integer.parseInt(cadenaNumero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica si la cadena proporcionada es un número decimal.
     *
     * @param cadenaNumero Cadena a verificar.
     * @return true si la cadena es un número decimal; false, en caso contrario.
     */
    private static boolean esNumeroDecimal(String cadenaNumero) {
        try {
            // Permitir "," o "." como separadores decimales
            cadenaNumero = cadenaNumero.replace(",", ".");
            Double.parseDouble(cadenaNumero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Método close() para cerrar el recurso Scanner al finalizar.
     */
    @Override
    public void close() {
        entradaTeclado.close();
    }
}
