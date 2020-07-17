/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo1;
import Analizador.*;
import Arbol.*;
import java.io.*;

/**
 *
 * @author sicmmar
 */
public class Ejemplo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String entrada = "imprimir(\"Hola Mundo\");\nimprimir(-54+1);\nimprimir(8-3);";
        Lexico lexico = new Lexico(new BufferedReader(new StringReader(entrada)));
        Sintactico sintactico = new Sintactico(lexico);
        try{
            sintactico.parse();
            Arbol arbol = new Arbol();
            arbol.Graficar(sintactico.raiz, "arbol");
            Recorrer recorrido = new Recorrer(sintactico.raiz);
            recorrido.ejecutar();
        }catch(Exception e){
            System.out.println("Error de análisis: " + e.getMessage());
        }
        
    }
    
}
