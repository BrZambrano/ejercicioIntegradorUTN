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
        this.alumno= a;
        this.materia= m;
        this.fecha= f;
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
    public boolean aprobada(boolean respuesta){
        aprobada= respuesta;
        return aprobada;
    }
}