package com.mycompany.ejerciciointegrador;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class EjercicioIntegrador {

    public static void main(String[] args) throws SQLException {
        Scanner consola = new Scanner(System.in);

        // - - - - - - - - - - - - - - - - MENU PRINCIPAL - - - - - - - - - - - - - - - - - - - - - - - - -
        int ciclo = 1;
        int seleccion = 0;
        int respuestaMenu;

        do {
            do {
                System.out.println("Bienvenido al programa organizativo de inscripción de alumnos a materias");
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

                System.out.println("1. Crear tablas de Alumnos y Materias");
                System.out.println("- - - - - - - - - - - - - - - - - - - - -");
                System.out.println("2. Agregar materias al sistema");
                System.out.println("3. Agregar a un alumno");
                System.out.println("4. Inscribir un alumno a una materia");
                System.out.print("Opción N°: ");
                respuestaMenu = consola.nextInt();

                if (seleccion >= 1 && seleccion <= 5) {
                    ciclo = 1;
                } else {
                    System.out.println("Por favor, ingrese una opcion dentro del menu.");
                }

            } while (ciclo == 0);

            switch (respuestaMenu) {
                case (1) -> {
                    crearTablas();
                    break;
                }
                case (2) -> {
                    agregarMateria();
                    break;
                }
                case (3) -> {
                    agregarAlumno();
                    break;
                }
                case (4) -> {
                    inscribirAlumno();
                    break;
                }
                default ->
                    System.out.println("Por favor, ingrese una opcion dentro del menu.");
            }
        } while (ciclo != 2);
    }

    //- - - - - - - - - - - - - - - - - - METODOS - - - - - - - - - - - - - - - - - - - - - 
    //Crear tablas en la base de datos
    public static void crearTablas() throws SQLException {
        Conexion db = new Conexion();
        db.estableceConexion();
        Statement stmt = db.conectar.createStatement();

        //TABLA DE Materia
        try {
            stmt.executeUpdate("CREATE TABLE Materias (nombre_mat VARCHAR(50), correlativa VARCHAR(50))");  //Tabla materia con nombres y correlativas
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
        consola.useDelimiter("\n");
        Conexion db = new Conexion();

        db.estableceConexion();
        Statement stmt = db.conectar.createStatement();
        String sql;

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Ingrese los siguientes datos: ");

        System.out.print("Nombre: ");
        String nombreMateria = consola.next();       //Ingresa el nombre

        Materia nvaMateria = new Materia(nombreMateria);    //Creamos objeto Materia y le damos nombre

        System.out.println("Cuantas correlativas tiene?");
        int nroCorrelativas = consola.nextInt();

        //Agregamos la materia a la base de datos
        db.estableceConexion();
//        sql = "INSERT INTO Materias (nombre_mat) VALUES ('" + nvaMateria.obtenerNombre() + "')";
//        for (int i = 0; i < nroCorrelativas; i++) {         //Lo agregamos la cantidad de veces de la correlativa
//            stmt.executeUpdate(sql);
//        }

        //Ahora agregamos sus correlativas
        System.out.println("Sus correlativas: ");
        String correlativa;

        for (int i = 0; i < nroCorrelativas; i++) {
            System.out.print(i + ". Correlativa: ");
            correlativa = consola.next();

            //Agregamos la materia al ArrayList de Materias aprobadas
            nvaMateria.agregarCorrelativa(correlativa);

            //Agregamos la materia a la base de datos
//            sql = "INSERT INTO Materias (correlativa) VALUES ('" + correlativa + "')";
            sql = "INSERT INTO Materias (nombre_mat, correlativa) VALUES ('" + nvaMateria.obtenerNombre() + "', '" + correlativa + "')";
            stmt.executeUpdate(sql);
        }

        stmt.close();
        db.quitarConexion();

        System.out.println("Listo, la materia " + nvaMateria.obtenerNombre() + " fue creada y añadida en la base de datos.");
    }

    //Agregar un alumno
    public static void agregarAlumno() throws SQLException {
        Scanner consola = new Scanner(System.in);
        consola.useDelimiter("\n");
        Conexion db = new Conexion();

        db.estableceConexion();
        Statement stmt = db.conectar.createStatement();
        String sql;

        System.out.println("Ingrese el nombre: ");
        String nombre = consola.next();
        System.out.println("Ingrese el Legajo del alumno: ");
        boolean cumple = false;
        int legajo = 0;

        while (!cumple) {
            legajo = consola.nextInt();
            if (legajo > 9999) {      //El legajo debe incluir minimo 5 caracteres
                cumple = true;
            } else {
                System.out.println("El numero es menor al pedido, vuelva a ingresarlo");
                cumple = true;
            }
        }

        //Creamos al nuevo Alumno
        Alumno nvoAlumno = new Alumno(nombre, legajo);   //Creamos nuevo objeto tipo Alumno

        System.out.println("¿Cuantas materias tiene aprobadas?");
        int cantMaterias = consola.nextInt();

        //Agregamos el nombre y el legajo a la Tabla de Alumnos
        db.estableceConexion();

        //sql = "INSERT INTO Alumno (legajo, nombre) VALUES ('" + legajo + "', '" + nombre + "')";
//        for (int i = 0; i < cantMaterias - 1; i++) {
//            stmt.executeUpdate(sql);
//        }
        //Vamos a agregar las materias aprobadas a la tabla y al ArrayList del objeto
        System.out.println("Ahora ingrese los nombres de las materias");
        for (int i = 0; i < cantMaterias; i++) {      //Restamos uno porque el ArrayList empieza en cero
            System.out.print(i + ". Nombre: ");
            String nombreMateria = consola.next();

            //Lo agregamos a la tabla de Materias Aprobadas
            sql = "INSERT INTO Alumno (legajo, nombre, mat_aprobada) VALUES ('" + legajo + "', '" + nombre + "', '" + nombreMateria + "')";
            //sql = "INSERT INTO Alumno (mat_aprobada) VALUES ('" + nombreMateria + "')";
            stmt.executeUpdate(sql);

            //Ahora a las materias aprobadas del ArrayList del Alumno
            nvoAlumno.agregarMateria(nombreMateria);
        }
        System.out.println("El alumno fue registrado exitosamente");
        db.quitarConexion();
    }

    //Inscribir un Alumno
    public static void inscribirAlumno() throws SQLException {
        Scanner consola = new Scanner(System.in);
        consola.useDelimiter("\n");
        Conexion db = new Conexion();
        ResultSet rs;
        String sql;

        db.estableceConexion();
        Statement stmt = db.conectar.createStatement();

        System.out.println("Ingrese el legajo del Alumno: ");
        int ins_legajo = consola.nextInt();

        sql = ("SELECT nombre FROM Alumno WHERE legajo=" + ins_legajo);
        rs = stmt.executeQuery(sql);
        if (rs.next()) {       //Si es un legajo dentro de la base de datos, se va a ejecutar todo
            String nombre = rs.getString("nombre");

            Alumno insAlumno = new Alumno(nombre, ins_legajo);   //Creamos el objeto para agregarle las materias aprobadas

            //Contamos la cantidad de filas con ese legajo
            //rs.last();      //muevo el cursor al final
            int cantidadFilas = rs.getRow();         //obtengo la ultima posicion
            //rs.first();

            //Ahora quiero obtener las materias aprobadas y guardarlas en el ArrayList del objeto Alumno
            boolean cumple = false;

            //Creamos un bucle para obtener el String de cada materia aprobada y meterlo en su ArrayList
            sql = ("SELECT mat_aprobada FROM Alumno WHERE Legajo=" + ins_legajo);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String matAprobada = rs.getString("mat_aprobada");
                insAlumno.agregarMateria(matAprobada);
            }

            if (rs.getRow() > cantidadFilas) {
                cumple = true;
            }

            //Ahora si la materia existe dentro de la base de datos
            System.out.println("Ahora ingrese la materia a la que se quiere inscribir el Alumno: ");
            String materia = consola.next();
            Materia nvaMateria = new Materia(materia);

            sql = ("SELECT nombre_mat FROM Materias WHERE nombre_mat= '" + materia+ "'");    //con esto selecciono todas las correlativas que coincidan con la materia buscada
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                boolean cumple2 = false;
                String correlativa;
                //Contamos la cantidad de filas con esa Materia
                int cantidadFilas2 = rs.getRow();         //obtengo la ultima posicion

                while (!cumple2) {   //Mientras no cumpla, se van a guardar las correlativas en el ArrayList de Materia
                    correlativa = rs.getString("correlativa");
                    nvaMateria.agregarCorrelativa(correlativa);
                    rs.next();

                    if (rs.getRow() > cantidadFilas2) {
                        cumple2 = true;
                    }
                }
                //Ahora si el alumno y la materia existen dentro de la base de datos, vamos a realizar la inscripcion
                if (nvaMateria.puedeCursar(insAlumno)) {
                    System.out.println("Vamos a usar la fecha de hoy");
                    Inscripcion nvaInscripcion = new Inscripcion(insAlumno, nvaMateria, LocalDateTime.now());
                    nvaInscripcion.aprobada(true);

                } else {
                    System.out.println("Las correlativas no coinciden con las materias aprobadas del alumno");
                }
            } else {
                System.out.println("La materia no existe dentro de la base de datos");
            }
        } else {
            System.out.println("El legajo ingresado no existe dentro de la base de datos");
        }
        stmt.close();
        rs.close();
        db.quitarConexion();
    }
}
