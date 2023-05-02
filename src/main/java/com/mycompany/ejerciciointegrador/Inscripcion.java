package com.mycompany.ejerciciointegrador;
import java.time.LocalDateTime;

//Implementar clase Datetime y anotar sus métodos
public class Inscripcion {
    private Alumno alumno;
    private Materia materia;
    private LocalDateTime fecha;
    private boolean aprobada;
    
    //Constructor
    public Inscripcion (Alumno a, Materia m, LocalDateTime f){
        alumno= a;
        materia= m;
        fecha= f;
    }
    
    //Comandos
    public void cambiarAlumno(Alumno otroAlumno){
        alumno= otroAlumno;
    }
    public void cambiarMateria(Materia otraMateria){
        materia= otraMateria;
    }
    public void cambiarFecha(LocalDateTime otraFecha){
        fecha= otraFecha;
    }
    
    //Consultas
    public Alumno obtenerAlumno(){
        return alumno;
    }
    public Materia obtenerMateria(){
        return materia;
    }
    public LocalDateTime obtenerFecha(){
        return fecha;
    }
    public boolean aprobada(){
        int tamanio= materia.obtenerTamanioCorrelativas();
        int i=0;
        aprobada= true;
        
        while(aprobada || i<tamanio){
            if (!(materia.correlativas.get(i).equals(alumno.materiasAprobadas.get(i)))){
                aprobada= false;
            }
            i++;
        }
        return aprobada;
    }
}