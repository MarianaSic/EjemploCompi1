/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo1;

import java.util.*;

/**
 *
 * @author sicmmar
 */
public class Entorno {
    public HashMap<String, Simbolo> Tabla;
    Entorno anterior;
    //public ArrayList<SyntaxError> error = new ArrayList<>();

    public Entorno(Entorno anterior) {
        this.anterior = anterior;
        this.Tabla = new HashMap<>();
    }
    
    public void nuevoSimbolo(String nombre, Simbolo nuevo, int fila, int columna){
        fila++;
        columna++;
        
        if(Tabla.containsKey(nombre.toUpperCase())){
            System.out.println("La variable ya existe, error en la fila " + fila + " y columna " + columna);
            //error.add(new SyntaxError("Semantico", fila, columna, "La variable ya existe", "Creación de variables"));
        }
        else
            Tabla.put(nombre.toUpperCase(), nuevo);
    }
    
    public Simbolo Buscar(String nombre, int fila, int columna){
        fila++;
        columna++;
        
        for(Entorno ent = this; ent != null; ent = ent.anterior){
            if(ent.Tabla.containsKey(nombre.toUpperCase()))
                return ent.Tabla.get(nombre.toUpperCase());
        }
        
        System.out.println("La variable no existe, error en la fila " + fila + " y columna " + columna);
        //error.add(new SyntaxError("Semantico", fila, columna, "La variable no existe", "Búsqueda de variables"));
        return null;
    }
    
    public void editarSimbolo(String nombre, Simbolo nuevo, int fila, int columna){
        fila++;
        columna++;
        boolean bandera = false;
        for(Entorno ent = this; ent != null; ent = ent.anterior){
            if(ent.Tabla.containsKey(nombre.toUpperCase())){
                bandera = true;
                ent.Tabla.replace(nombre.toUpperCase(), nuevo);
            }
        }
        if(!bandera)
            System.out.println("La variable no existe, error en la fila " + fila + " y columna " + columna);
        //error.add(new SyntaxError("Semantico", fila, columna, "La variable no existe", "Reasignación de variables"));
    }
}
