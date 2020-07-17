/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import java.io.*;

/**
 *
 * @author sicmmar
 */
public class Arbol {
    private int i;
    
    public Arbol(){
        i = 0;
    }
    
    public void Graficar(Nodo raiz, String ruta){
        FileWriter archivo = null;
        PrintWriter pw;
        
        try {
            archivo = new FileWriter("./Graphviz/"+ ruta +".dot");
            pw = new PrintWriter(archivo);
            
            pw.println("digraph arbol{ node[shape=\"box\"]");
            pw.println(recorrer(raiz));
            pw.println("}");

        } catch (IOException e) {
            System.out.println("Error de archivo " + e);
        } finally{
            try {
                
                if(null != archivo)
                    archivo.close();
                
            } catch (IOException e) {
                System.out.println("Error de fichero " + e);
            }
        }
        
        try {
            String cmd = "dot -Tpdf ./Graphviz/"+ ruta +".dot -o ./Graphviz/"+ ruta +".pdf"; 
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println (ioe);
        }
    }
    
    private String recorrer(Nodo raiz){
        String respuesta = "";
        i++;
        String padre = "n" + i;
        respuesta += padre + "[label = \""+ raiz.nonterminal +" \\n "+ raiz.valor +"\"];";
        
        for(Nodo n : raiz.hijos){
            respuesta += padre + " -> n" + (i + 1) + ";\n";
            respuesta += recorrer(n);
        }
        
        return respuesta;
    }
}
