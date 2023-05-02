package com.mycompany.ejerciciointegrador;
import java.util.ArrayList;

public class Alumno{
    private String nombre;
    private int legajo;
    ArrayList <String> materiasAprobadas= new ArrayList<String>();

    //Constructor
    public Alumno(String n, int l){
        this.nombre= n;
        this.legajo= l;
    }
    
    //Consultas
    public String obtenerNombre(){
        return nombre;
    }
    public int obtenerLegajo(){
        return legajo;
    }
    public void modificarNombre(String n){
        this.nombre= n;
    }
    public void modificarLegajo(int n){
        this.legajo= n;
    }
            
    public void mostrarMateriasAprobadas(){
        for(int i=0;i<materiasAprobadas.size();i++){
            System.out.println(materiasAprobadas.get(i));
        }
    }
    
    //Comandos
    public void agregarMateria(String materia){
        materiasAprobadas.add(materia);
    }
    public void eliminarMateria(String materia){
        if (materiasAprobadas.contains(materia)){
            materiasAprobadas.remove(materiasAprobadas.indexOf(materia));
        }       
    }
}
