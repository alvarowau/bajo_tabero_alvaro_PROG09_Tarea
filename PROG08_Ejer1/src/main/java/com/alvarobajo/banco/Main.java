package com.alvarobajo.banco;

import com.alvarobajo.banco.controller.Banco;
import com.alvarobajo.banco.view.Menu;

/**
 * Clase principal que inicia la aplicación bancaria.
 *
 * <p>Crea una instancia de la clase `Banco` y llama al método `menuPrincipal` de la clase `Menu` para
 * comenzar la interacción con el usuario.</p>
 *
 * @author Álvaro Bajo Tabero
 */
public class Main {

    /**
     * Punto de entrada principal para la aplicación bancaria.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Banco banco = new Banco();
        Menu.menuPrincipal(banco);
    }
}
