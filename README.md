PROG09_Ejerc1_Ejerc2

Este proyecto se ha modificado para cumplir con los requisitos de los Ejercicio 1 de la Unidad de Trabajo 8.
Cambios Realizados

Ejercicio 1

    Persistencia de Datos: Se ha implementado la persistencia de los datos de cuentas bancarias. Cuando la aplicación finaliza correctamente, los datos se guardan en un archivo binario llamado datoscuentasbancarias.dat. Además, al iniciar la aplicación de manera adecuada, los datos se cargan desde este archivo para rellenar la estructura de datos.

    Modificaciones en la Clase CuentaBancaria: Se han realizado las modificaciones necesarias en la clase CuentaBancaria para que los objetos sean serializables y puedan ser almacenados en el archivo binario.

Ejercicio 2

    Nueva Opción en el Menú: Se ha agregado una nueva opción al menú de la aplicación llamada "Listado clientes". Esta opción permite generar un archivo de texto llamado ListadoClientesCCC.txt que contiene una línea de texto por cada cuenta bancaria almacenada. Cada línea muestra el nombre del propietario y el CCC (Código Cuenta Cliente) de cada cuenta. La última línea del archivo muestra el número total de cuentas existentes.

Cómo se Realizaron los Cambios

    Persistencia de Datos: Se implementó la lógica necesaria en la clase Banco para guardar y cargar los datos de las cuentas bancarias en el archivo binario datoscuentasbancarias.dat.

    Modificaciones en la Clase CuentaBancaria: Se añadieron las interfaces Serializable y Comparable a la clase CuentaBancaria y se realizaron los ajustes necesarios en los atributos y métodos para garantizar la serialización adecuada de los objetos.

    Nueva Opción en el Menú: Se modificó la interfaz de usuario para incluir la opción "Listado clientes" en el menú. Se añadió un método en la clase Banco llamado generarListadoClientes() para generar el archivo de texto con la información de las cuentas bancarias.

Estos cambios permiten que la aplicación mantenga y manipule los datos de las cuentas bancarias de manera persistente, así como proporcionar al usuario la capacidad de generar un listado de clientes de forma sencilla y rápida. Es importante destacar que los archivos se generarán correctamente si se utiliza la aplicación de manera adecuada y se cierra correctamente, evitando cierres abruptos que puedan impedir la escritura de los datos en los archivos.
