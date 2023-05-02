package com.mycompany.ejerciciointegrador;
import java.util.ArrayList;

public class Materia{
    //Atributos de instancia
    private final String nombre;
    //private final int id;
    ArrayList <String> correlativas= new ArrayList();
    
    //Constructor
    public Materia(String n){
        nombre= n;
        //this.id= id;
    }
    
    //Métodos
    public boolean puedeCursar(Alumno alumno){
        //desarrollar
        return true;
    }
    public String obtenerNombre(){
        return nombre;
    }
    /*public int obtenerId(){
        return id;
    }*/
    public int obtenerTamanioCorrelativas(){
        return correlativas.size();
    }
    
    public void agregarCorrelativa(String materia){
        correlativas.add(materia);
    }
    public void eliminarCorrelativa(String materia){
        if (correlativas.contains(materia)){
            correlativas.remove(correlativas.indexOf(materia));
        }
    }
    public void mostrarCorrelativas(){
        for(int i=0;i<obtenerTamanioCorrelativas();i++){
            System.out.println(correlativas.get(i));
        }
    }
}