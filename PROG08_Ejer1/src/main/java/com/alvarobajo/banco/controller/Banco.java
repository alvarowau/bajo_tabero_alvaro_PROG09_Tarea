package com.alvarobajo.banco.controller;

import com.alvarobajo.banco.models.CuentaBancaria;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un banco y gestiona cuentas bancarias.
 *
 * @author Álvaro Bajo Tabero
 */
public class Banco implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String NOMBRE_ARCHIVO = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "datoscuentasbancarias.dat";


    private static final String NOMBRE_TXT = "ListadoClientesCCC.txt";
    private List<CuentaBancaria> cuentas;

    /**
     * Constructor para inicializar una instancia de Banco.
     * Inicializa la lista de cuentas.
     */
    public Banco() {
        cargarCuentas();
        if (cuentas == null) {
            cuentas = new ArrayList<>();
        }
    }

    /**
     * Abre una nueva cuenta bancaria y la agrega al banco.
     *
     * @param nuevaCuenta Nueva cuenta bancaria a abrir.
     * @return true si la cuenta se abrió con éxito; false, si no se pudo abrir la cuenta.
     * @throws RuntimeException si se alcanza el límite de cuentas (si decides establecer un límite).
     */
    public boolean abrirCuenta(CuentaBancaria nuevaCuenta) {
        if (!estaIBANEnUso(nuevaCuenta.getIban())) {
            cuentas.add(nuevaCuenta);
            return true;
        } else {
            throw new RuntimeException("No se pueden abrir más cuentas. Límite alcanzado.");
        }
    }

    /**
     * Elimina una cuenta bancaria del banco si existe y su saldo es 0.
     *
     * @param iban Número de IBAN de la cuenta a eliminar.
     * @return true si la cuenta se eliminó con éxito; false, si no se pudo eliminar la cuenta.
     */
    public boolean eliminarCuenta(String iban) {
        Iterator<CuentaBancaria> iterator = cuentas.iterator();
        while (iterator.hasNext()) {
            CuentaBancaria cuenta = iterator.next();
            if (cuenta.getIban().equalsIgnoreCase(iban) && cuenta.getSaldo() == 0) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Lista todas las cuentas registradas en el banco.
     */
    public void listarCuentas() {
        if (!cuentas.isEmpty()) {
            System.out.println("Listado de cuentas:");
            for (CuentaBancaria cuenta : cuentas) {
                System.out.println(cuenta);
            }
        } else {
            System.out.println("No hay cuentas registradas en el banco.");
        }
    }

    /**
     * Busca una cuenta bancaria por el nombre del titular.
     *
     * @param nombreTitular Nombre del titular de la cuenta a buscar.
     * @return Cuenta bancaria encontrada o null si no se encuentra.
     */
    public CuentaBancaria buscarCuentaPorTitular(String nombreTitular) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getTitular().getNombre().equalsIgnoreCase(nombreTitular)) {
                return cuenta;
            }
        }
        return null;
    }

    /**
     * Busca una cuenta bancaria por el número de IBAN.
     *
     * @param iban Número de IBAN de la cuenta a buscar.
     * @return Cuenta bancaria encontrada o null si no se encuentra.
     */
    public CuentaBancaria buscarCuentaPorIBAN(String iban) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equalsIgnoreCase(iban)) {
                return cuenta;
            }
        }
        return null;
    }

    /**
     * Verifica si un número de IBAN está en uso en el banco.
     *
     * @param iban Número de IBAN a verificar.
     * @return true si el IBAN está en uso; false, si no está en uso.
     */
    public boolean estaIBANEnUso(String iban) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equalsIgnoreCase(iban)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra las cuentas del banco.
     */
    public void imprimirCuentas() {
        listarCuentas();
    }

    /**
     * Imprime la información de la cuenta bancaria asociada al titular.
     *
     * @param nombreTitular Nombre del titular de la cuenta a imprimir.
     */
    public void imprimirCuentaPorTitular(String nombreTitular) {
        CuentaBancaria cuenta = buscarCuentaPorTitular(nombreTitular);
        if (cuenta != null) {
            System.out.println("Cuenta encontrada para el titular " + nombreTitular + ":");
            System.out.println(cuenta);
        } else {
            System.out.println("No se encontró ninguna cuenta para el titular " + nombreTitular + ".");
        }
    }

    /**
     * Imprime la información de la cuenta bancaria asociada al número de IBAN.
     *
     * @param iban Número de IBAN de la cuenta a imprimir.
     */
    public void imprimirCuentaPorIBAN(String iban) {
        CuentaBancaria cuenta = buscarCuentaPorIBAN(iban);
        if (cuenta != null) {
            System.out.println("Cuenta encontrada para el IBAN " + iban + ":");
            System.out.println(cuenta);
        } else {
            System.out.println("No se encontró ninguna cuenta para el IBAN " + iban + ".");
        }
    }

    /**
     * Obtiene el número total de cuentas registradas en el banco.
     *
     * @return Número total de cuentas registradas.
     */
    public int getNumCuentas() {
        return cuentas.size();
    }

    /**
     * Obtiene la lista de cuentas bancarias registradas en el banco.
     *
     * @return Lista de cuentas bancarias.
     */
    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }


    /**
     * Carga las cuentas bancarias desde un archivo de datos.
     *
     * <p>Este método lee las cuentas bancarias desde el archivo especificado por la constante {@code NOMBRE_ARCHIVO}.
     * Si el archivo no existe, muestra un mensaje indicando que se creará uno nuevo al salir.
     * Si ocurre un error durante la lectura del archivo o la deserialización de las cuentas,
     * se muestra un mensaje de error detallado.
     */
    private void cargarCuentas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {
            cuentas = (List<CuentaBancaria>) ois.readObject();
            System.out.println("Cuentas cargadas correctamente desde el archivo: " + NOMBRE_ARCHIVO);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de cuentas. Se creará uno nuevo al salir.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar las cuentas: " + e.getMessage());
        }
    }

    /**
     * Guarda las cuentas bancarias en un archivo de datos.
     *
     * <p>Este método guarda las cuentas bancarias en el archivo especificado por la constante {@code NOMBRE_ARCHIVO}.
     * Si ocurre un error durante la escritura en el archivo, se muestra un mensaje de error detallado.
     */
    public void guardarCuentas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {
            oos.writeObject(cuentas);
            System.out.println("Cuentas guardadas correctamente en el archivo: " + NOMBRE_ARCHIVO);
        } catch (IOException e) {
            System.out.println("Error al guardar las cuentas: " + e.getMessage());
        }
    }


    /**
     * Genera y guarda el listado de clientes en un archivo de texto en el escritorio del usuario.
     * Si el archivo ya existe, lo sobrescribe.
     * <p>
     * El listado incluirá el nombre del titular, el número de IBAN y el saldo de cada cuenta bancaria
     * almacenada en el banco. Al final del archivo se incluirá el número total de cuentas.
     * <p>
     * El archivo se guardará en el escritorio del usuario con el nombre "ListadoClientesCCC.txt".
     * Si el archivo ya existe, se sobrescribirá con el nuevo listado generado.
     * <p>
     * Si ocurre algún error durante la generación o escritura del listado, se imprimirá un mensaje
     * de error en la consola.
     */
    public void generarListadoClientes() {
        File ARCHIVO_TXT = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + NOMBRE_TXT);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_TXT))) {
            for (CuentaBancaria cuenta : cuentas) {
                String linea = cuenta.getTitular().getNombre() + " - " + cuenta.getIban() + " - " + cuenta.getSaldo();
                writer.write(linea);
                writer.newLine();
            }
            writer.write("Número total de cuentas: " + cuentas.size());
            System.out.println("Listado de clientes generado con éxito.");
            System.out.println("el archivo se ha creado en el escritorio con el nombre de .");
        } catch (IOException e) {
            System.out.println("Error al generar el listado de clientes: " + e.getMessage());
        }
    }


}
