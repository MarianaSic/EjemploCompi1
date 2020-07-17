/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo1;

import Arbol.*;

/**
 *
 * @author sicmmar
 */
public class Recorrer {
    private final Nodo raiz;
    
    public Recorrer(Nodo raiz){
        this.raiz = raiz;
    }
    
    public void ejecutar(){
        ejecutar(this.raiz, null);
    }
    
    private void ejecutar(Nodo raiz, Entorno ent){
        switch(raiz.nonterminal){
            case "INICIO":
                ejecutar(raiz.hijos.get(0), ent);
                break;
            case "LI":
                for(int i = 0; i < raiz.hijos.size(); i++)
                    ejecutar(raiz.hijos.get(i), ent);
                break;
            case "I":
                Imprimir(raiz, ent);
                break;
        }
    }
    
    private void Imprimir(Nodo raiz, Entorno ent){
        Expresion exp = resolverExpresion(raiz.hijos.get(2), ent);
        System.out.println(" ::" + exp.valor);
    }
    
    private Expresion resolverExpresion(Nodo raiz, Entorno ent){
        Expresion hijo1, hijo2;
        switch(raiz.nonterminal){
            case "Negativo":
                hijo1 = resolverExpresion(raiz.hijos.get(1), ent);
                if (!hijo1.tipo.equals("ERROR")){
                    if(raiz.hijos.get(1).nonterminal.equals("Numero"))
                        return new Expresion("Numero", Integer.parseInt(raiz.hijos.get(1).valor.toString())*-1);
                    
                }
            case "Suma":
                hijo1 = resolverExpresion(raiz.hijos.get(0), ent);
                hijo2 = resolverExpresion(raiz.hijos.get(2), ent);
                if(hijo1.tipo.equals("String") && hijo2.tipo.equals("String"))
                    return new Expresion("String", hijo1.valor.toString() + hijo2.valor.toString()); 
                else if(hijo1.tipo.equals("Numero") && hijo2.tipo.equals("Numero"))
                    return new Expresion("Numero", Integer.parseInt(hijo1.valor.toString()) + Integer.parseInt(hijo2.valor.toString())); 
            case "Resta":
                hijo1 = resolverExpresion(raiz.hijos.get(0), ent);
                hijo2 = resolverExpresion(raiz.hijos.get(2), ent);
                if(hijo1.tipo.equals("Numero") && hijo2.tipo.equals("Numero"))
                    return new Expresion("Numero", Integer.parseInt(hijo1.valor.toString()) - Integer.parseInt(hijo2.valor.toString())); 
            //terminales
            case "String":
                return new Expresion("String", raiz.valor);
            case "Numero":
                return new Expresion("Numero", raiz.valor);
            case "Identificador":
                //buscar el valor del id
                return new Expresion("Identificador", "Va el valor del id -> " + raiz.valor);
        }
        return new Expresion("ERROR", "ERROR");
    }
}
