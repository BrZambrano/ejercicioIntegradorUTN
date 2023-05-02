package com.mycompany.ejerciciointegrador;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class EjercicioIntegrador {

    public static void main(String[] args) throws SQLException {
        Scanner consola = new Scanner(System.in);

        // - - - - - - - - - - - - - - - - MENU PRINCIPAL - - - - - - - - - - - - - - - - - - - - - - - - -
        System.out.println("Bienvenido al programa organizativo de inscripción de alumnos a materias");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        System.out.println("1. Crear tablas de Alumnos y Materias");
        System.out.println("- - - - - - - - - - - - - - - - - - - - -");
        System.out.println("2. Agregar materias al sistema");
        System.out.println("3. Agregar a un alumno");
        System.out.println("4. Inscribir un alumno a una materia");
        System.out.println("- - - - - - - - - - - - - - - - - - - - -");
        System.out.println("5. Opciones de Alumno");
        System.out.println("6. Opciones de Inscripción");
        System.out.println("7. Opciones de Materia");
        int respuestaMenu = consola.nextInt();

        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        boolean cumple = false;
        String respuestaMenu_iterar;
        while (!cumple) {
            switch (respuestaMenu) {
                case (1):
                    crearTablas();

                    System.out.println("Quiere realizar otra operacion?");
                    respuestaMenu_iterar = consola.nextLine();
                    if (respuestaMenu_iterar.equals("No") || respuestaMenu_iterar.equals("no") || respuestaMenu_iterar.equals("NO")) {
                        cumple = true;
                    }
                    break;
                case (2):
                    agregarMateria();

                    respuestaMenu_iterar = consola.nextLine();
                    if (respuestaMenu_iterar.equals("No") || respuestaMenu_iterar.equals("no") || respuestaMenu_iterar.equals("NO")) {
                        cumple = true;
                    }
                    break;
                case (3):
                    agregarAlumno();

                    respuestaMenu_iterar = consola.nextLine();
                    if (respuestaMenu_iterar.equals("No") || respuestaMenu_iterar.equals("no") || respuestaMenu_iterar.equals("NO")) {
                        cumple = true;
                    }
                    break;
                case (4):
                    inscribirAlumno();

                    respuestaMenu_iterar = consola.nextLine();
                    if (respuestaMenu_iterar.equals("No") || respuestaMenu_iterar.equals("no") || respuestaMenu_iterar.equals("NO")) {
                        cumple = true;
                    }
                    break;
                case (5):
                    opcionesAlumno();

                    respuestaMenu_iterar = consola.nextLine();
                    if (respuestaMenu_iterar.equals("No") || respuestaMenu_iterar.equals("no") || respuestaMenu_iterar.equals("NO")) {
                        cumple = true;
                    }
                    break;
                case (6):
                    opcionesInscripcion();

                    respuestaMenu_iterar = consola.nextLine();
                    if (respuestaMenu_iterar.equals("No") || respuestaMenu_iterar.equals("no") || respuestaMenu_iterar.equals("NO")) {
                        cumple = true;
                    }
                    break;
                case (7):
                    opcionesMateria();

                    respuestaMenu_iterar = consola.nextLine();
                    if (respuestaMenu_iterar.equals("No") || respuestaMenu_iterar.equals("no") || respuestaMenu_iterar.equals("NO")) {
                        cumple = true;
                    }
                    break;
                default:
                    System.out.println("Por favor, ingrese una opcion dentro del menu.");
            }
        }
    }

    //- - - - - - - - - - - - - - - - - - METODOS - - - - - - - - - - - - - - - - - - - - - 
    //Crear tablas en la base de datos
    public static void crearTablas() throws SQLException {
        Conexion db = new Conexion();
        db.estableceConexion();
        Statement stmt = db.conectar.createStatement();

        //TABLA DE Materia
        try {
            stmt.executeUpdate("CREATE TABLE Materias (nombre_mat VARCHAR(50), correlativa INT(50))");  //Tabla materia con nombres y correlativas
            System.out.println("Se creo correctamente la tabla Materia");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear correctamente la tabla Alumnos " + e);
        }

        //TABLA DE Alumnos
        try {
            stmt.executeUpdate("CREATE TABLE Alumno (legajo INT(50), nombre VARCHAR(20), mat_aprobada VARCHAR(20))");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear correctamente la tabla Alumno " + e);
        }

        stmt.close();
        db.quitarConexion();
    }

    //Agregar materia
    public static void agregarMateria() throws SQLException {
        Scanner consola = new Scanner(System.in);
        Conexion db = new Conexion();

        db.estableceConexion();
        Statement stmt = db.conectar.createStatement();
        String sql;

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Ingrese los siguientes datos: ");

        System.out.print("Nombre: ");
        String nombreMateria = consola.nextLine();       //Ingresa el nombre

        Materia nvaMateria = new Materia(nombreMateria);    //Creamos objeto Materia y le damos nombre

        System.out.println("Cuantas correlativas tiene?");
        int nroCorrelativas = consola.nextInt();

        //Agregamos la materia a la base de datos
        db.estableceConexion();
        sql = "INSERT INTO Materia (nombre_mat) VALUES (\"" + nvaMateria.obtenerNombre() + "\"))";
        for (int i = 0; i < nroCorrelativas; i++) {         //Lo agregamos la cantidad de veces de la correlativa
            stmt.executeQuery(sql);
        }

        //Ahora agregamos sus correlativas
        System.out.println("Sus correlativas: ");
        String correlativa;

        for (int i = 0; i < nroCorrelativas; i++) {
            System.out.print(i + ". Correlativa: ");
            correlativa = consola.next();

            //Agregamos la materia al ArrayList de Materias aprobadas
            nvaMateria.agregarCorrelativa(correlativa);

            //Agregamos la materia a la base de datos
            sql = "INSERT INTO Correlativas (correlativa) VALUES (\"" + correlativa + "\"))";
            stmt.executeUpdate(sql);
        }

        stmt.close();
        db.quitarConexion();

        System.out.println("Listo, la materia " + nvaMateria.obtenerNombre() + " fue creada y añadida en la base de datos.");
        System.out.println("Si desea obtener o manipular información de las materias, elija la opción 5 en el menú principal.");
    }

    //Agregar un alumno
    public static void agregarAlumno() throws SQLException {
        Scanner consola = new Scanner(System.in);
        Conexion db = new Conexion();

        db.estableceConexion();
        Statement stmt = db.conectar.createStatement();
        String sql;

        System.out.println("Ingrese el nombre: ");
        String nombre = consola.nextLine();
        System.out.println("Ingrese el Legajo del alumno: ");
        boolean cumple = false;
        int legajo = 0;

        while (!cumple) {
            legajo = consola.nextInt();
            if (legajo >= 10000) {      //El legajo debe incluir minimo 5 caracteres
                cumple = true;
            }
        }

        //Creamos al nuevo Alumno
        Alumno nvoAlumno = new Alumno(nombre, legajo);   //Creamos nuevo objeto tipo Alumno

        System.out.println("¿Cuantas materias tiene aprobadas?");
        int cantMaterias = consola.nextInt();

        //Agregamos el nombre y el legajo a la Tabla de Alumnos
        db.estableceConexion();
        sql = "INSERT INTO Alumnos (legajo, nombre) VALUES ('" + legajo + "','" + nombre + "');";

        for (int i = 0; i < cantMaterias - 1; i++) {
            stmt.executeQuery(sql);
        }

        //Vamos a agregar las materias aprobadas a la tabla y al ArrayList del objeto
        System.out.println("Ahora ingrese los nombres de las materias");
        for (int i = 0; i < cantMaterias - 1; i++) {      //Restamos uno porque el ArrayList empieza en cero
            System.out.print(i + ". Nombre: ");
            String nombreMateria = consola.nextLine();

            //Lo agregamos a la tabla de Materias Aprobadas
            sql = "INSERT INTO Alumno (mat_aprobada) VALUES ('" + nombreMateria + "')";
            stmt.executeQuery(sql);

            //Ahora a las materias aprobadas del ArrayList del Alumno
            nvoAlumno.agregarMateria(nombreMateria);
        }
        System.out.println("El alumno fue registrado exitosamente");
        db.quitarConexion();
    }

    //Inscribir un Alumno
    public static void inscribirAlumno() throws SQLException {
        Scanner consola = new Scanner(System.in);
        Conexion db = new Conexion();
        ResultSet rs;
        String sql;

        db.estableceConexion();
        Statement stmt = db.conectar.createStatement();

        System.out.println("Ingrese el legajo del Alumno: ");
        int ins_legajo = consola.nextInt();

        sql = ("SELECT nombre FROM Alumno WHERE legajo=" + ins_legajo);
        rs = stmt.executeQuery(sql);
        if (rs.first()) {   //Si es un legajo dentro de la base de datos, se va a ejecutar todo
            String nombre = rs.getString("nombre");
            
            Alumno insAlumno = new Alumno(nombre, ins_legajo);   //Creamos el objeto para agregarle las materias aprobadas

            //Contamos la cantidad de filas con ese legajo
            rs.last();      //muevo el cursor al final
            int cantidadFilas = rs.getRow();         //obtengo la ultima posicion
            rs.first();
            
            //Ahora quiero obtener las materias aprobadas y guardarlas en el ArrayList del objeto Alumno
            boolean cumple= false;
            
            while (!cumple){
                sql = ("SELECT mat_aprobada FROM Alumno WHERE Legajo=" + ins_legajo);
                rs = stmt.executeQuery(sql);
                String matAprobada = rs.getString("mat_aprobada");
                if (){
                    
                }
            }
            
            
            
            
        }
    }

    //Opciones de Inscripcion
    System.out.println (

    "a. Cambiar nombre del alumno");
    System.out.println (

    "b. Cambiar nombre de la materia");
    System.out.println (
    "c. Cambiar fecha de inscripcion");
            String eleccion = consola.nextLine();

    switch (eleccion) {
        case 'a' = nvoAlumno.
    }
}
