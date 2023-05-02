package com.mycompany.ejerciciointegrador;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    public Connection conectar= null;
    
    public final String usuario= "root";
    public final String contraseña= "root";
    public final String bd= "ejercicio_integrador";
    public final String ip= "localhost";
    public final String puerto= "3306";
    
    String ruta= "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;    
    
    public Connection estableceConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar= DriverManager.getConnection(ruta, usuario, contraseña);
            
            System.out.println("Se conecto correctamente");
        } catch (HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "No se conecto correctamente"+e);
        }
        return conectar;
    }
//    public Statement crearStatement() throws SQLException{
//        if (conectar==null || conectar.isClosed()){
//            throw new SQLException("La conexión a la base de datos está cerrada JSKDJFLA");
//        }
//        return conectar.createStatement();
//    }
    
    public Connection quitarConexion(){
        try {
            conectar.close();
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "No se desconecto correctamente"+e);
        }
        return conectar;
    }
}