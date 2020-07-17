/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import java.util.*;

/**
 *
 * @author sicmmar
 */
public class Nodo {
    public String nonterminal;
    public Object valor;
    public int fila;
    public int columna;
    public ArrayList<Nodo> hijos;
    
    public Nodo(String nonterminal, Object valor, int fila, int columna) {
        this.nonterminal = nonterminal;
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
        this.hijos = new ArrayList<>();
    }
    
    public void agregarHijo(Nodo hijo){
        this.hijos.add(hijo);
    }
}
