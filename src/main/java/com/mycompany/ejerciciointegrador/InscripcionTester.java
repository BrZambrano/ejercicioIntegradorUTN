package com.mycompany.ejerciciointegrador;
import java.util.Scanner;

public class InscripcionTester {
    public static void main (String[] args){
        Scanner consola= new Scanner(System.in);
        
        
        Materia materiaQui= new Materia("Quimica");
        Materia materiaMat= new Materia("Matematicas");
        Materia materiaFis= new Materia("Fisica");
        
        materiaQui.agregarCorrelativa("Biologia");
        materiaQui.agregarCorrelativa("Geologia");
        materiaQui.agregarCorrelativa("Hidrografia");
        
        materiaMat.agregarCorrelativa("Numeros I");
        materiaMat.agregarCorrelativa("Introduccion");
        materiaMat.agregarCorrelativa("Aritmetica");
        
        materiaFis.agregarCorrelativa("Caida libre I");
        materiaFis.agregarCorrelativa("Lanzamiento I");
        materiaFis.agregarCorrelativa("Matematica I");
        
        System.out.println("Ahora va a mostrar las correlativas de Quimica (Biologia, geologia, hidrografia)");
        materiaQui.mostrarCorrelativas();
        
        System.out.println("Ahora va a mostrar las correlativas de Matematicas (Numeros I, Introduccion, Aritemetica)");
        materiaMat.mostrarCorrelativas();
        
        System.out.println("Ahora va a mostrar las correlativas de Fisica(Caida Libre, lanzamiento, matematica)");
        materiaFis.mostrarCorrelativas();
        
        materiaQui.eliminarCorrelativa("Geologia");
        System.out.println("El tamaño de correlatias de Quimica son (2): "+ materiaQui.obtenerTamanioCorrelativas());
        
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        System.out.println("Buenas, vamos a testear la clase Inscripcion");
        System.out.println("Ingrese nombre: ");
        String nombre= consola.nextLine();
        System.out.println("Ingrese legajo: ");
        int legajo= consola.nextInt();
        
        
        Alumno alumno1= new Alumno(nombre, legajo);
        Alumno alumno2= new Alumno("Juan", 15846);
        Alumno alumno3= new Alumno("Pepe", 14521);
        
        System.out.println("Alumno 1, nombre:"+alumno1.obtenerNombre());
        System.out.println("legajo: "+alumno1.obtenerLegajo());
        
        System.out.println("Alumno 2, nombre: "+alumno2.obtenerNombre());
        
        alumno1.agregarMateria(materiaQui);
        alumno1.agregarMateria(materiaMat);
        alumno2.agregarMateria(materiaMat);
        alumno3.agregarMateria(materiaFis);
        alumno3.agregarMateria(materiaMat);
        alumno3.agregarMateria(materiaQui);
        
        
        System.out.println("A alumno2 le quitamos matematicas");
        alumno2.eliminarMateria(materiaMat);
        alumno2.mostrarMateriasAprobadas();
        
        System.out.println("Alumno1: ");
        alumno1.mostrarMateriasAprobadas();
        
        System.out.println("Alumno3: ");
        alumno3.mostrarMateriasAprobadas();
        
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
        System.out.println("- - - Inscripcion - - -");
        System.out.print("Alumno: ");
        alumno1.modificarNombre(consola.nextLine());
        System.out.print("Legajo: ");
        alumno1.modificarLegajo(consola.nextInt());
        
        System.out.println("Materia (Mat, Fis, Qui): ");
        nombre= consola.nextLine();
        Materia respuesta= new Materia(nombre);
        
        
    }
}
