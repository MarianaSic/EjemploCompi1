/*----------------------------------------------------------------------------
--------------------- 1ra. Area: Codigo de Usuario
----------------------------------------------------------------------------*/

//-------> Paquete, importaciones

package Analizador;

import java_cup.runtime.*;
import java.util.*;
//import Errores.*;

%%

/*----------------------------------------------------------------------------
--------------------- 2da. Area: Opciones y Declaraciones
----------------------------------------------------------------------------*/

%{
    String cadena="";
    //public ArrayList<SyntaxError> ErroresLexicos =new ArrayList<>();
%}

//-------> Directivas
%public
%class Lexico
%cupsym simbolos
%cup
%char
%line
%column
%8bit
%full
%unicode
%ignorecase

//-------> Expresiones Regulares

numero = [0-9]+("." [0-9]+)?
id = [a-zA-ZñÑ_][a-zA-ZñÑ0-9_]*
/*
bspace = [\u005C] "n"
//comillasimple = [\u005C] [\u0027]
//comilladoble = [\u005C] [\u0022]
caracteres = ("#" | "$" | "&" | "(" | ")" | "/" | "<" | "=" | "@" | "[" | "]" | "^" | "_" | "`" | "-")
*/

//-------> Estados    EN ORDEN
%state SIMPLE
%state MULTILINEA
%state CADENA

%%
/*-------------------------------------------------------------------
--------------------- 3ra. y ultima area: Reglas Lexicas
-------------------------------------------------------------------*/

<CADENA>{
      \"                             { String temporal=cadena; cadena=""; yybegin(YYINITIAL); 
                                        return new Symbol(simbolos.sstring, yychar,yyline,temporal); }
      [^\n\r\"\\]+                   { cadena+=yytext(); }
      \\t                            { cadena+=yytext(); }
      \\n                            { cadena+=yytext(); }
      \\r                            { cadena+=yytext(); }
      \\\"                           { cadena+=yytext(); }
      \\                             { cadena+=yytext(); }
    }

<YYINITIAL> "/*"                {yybegin(MULTILINEA);}     // Si la entrada es un comentario inicia aqui
<MULTILINEA> "*/"             {yybegin(YYINITIAL);}        // Si se acaba el comentario vuelve a YYINITIAL
<MULTILINEA> .                {}
<MULTILINEA> [ \t\r\n\f]      {}

<YYINITIAL> "//"                {yybegin(SIMPLE);}   // Si la entrada es comentario simple inicia aqui
<SIMPLE> [^"\n"]         {}                          // 
<SIMPLE> "\n"            {yybegin(YYINITIAL);}       // aqui sale del estado

//-------> Simbolos admitidos en el lenguaje

<YYINITIAL> "("         {   return new Symbol(simbolos.para, yycolumn, yyline, yytext());}

<YYINITIAL> ")"         {  return new Symbol(simbolos.parc, yycolumn, yyline, yytext());}

<YYINITIAL> "{"         {  return new Symbol(simbolos.llavea, yycolumn, yyline, yytext());}

<YYINITIAL> "}"         {  return new Symbol(simbolos.llavec, yycolumn, yyline, yytext());}

<YYINITIAL> "="         {  return new Symbol(simbolos.igual, yycolumn, yyline, yytext());}

<YYINITIAL> ";"         {   return new Symbol(simbolos.pyc, yycolumn, yyline, yytext());}

<YYINITIAL> "+"         {   return new Symbol(simbolos.mas, yycolumn, yyline, yytext());}

<YYINITIAL> "-"         {   return new Symbol(simbolos.menos, yycolumn, yyline, yytext());}

//-------> Palabras Reservadas

<YYINITIAL> "imprimir"             {  return new Symbol(simbolos.imprimir, yycolumn, yyline, yytext());}

<YYINITIAL> "string"             {  return new Symbol(simbolos.rstring, yycolumn, yyline, yytext());}

<YYINITIAL> "int"             {  return new Symbol(simbolos.rint, yycolumn, yyline, yytext());}


"\"" {yybegin(CADENA);}


<YYINITIAL> {numero}                  { return new Symbol(simbolos.numero, yycolumn, yyline, yytext());}

<YYINITIAL> {id}                  {  return new Symbol(simbolos.id, yycolumn, yyline, yytext());}


[ \t\r\n\f]                 {/* ignore white space. */ }
 
.                           {   System.out.println("Error Lexico: <<"+yytext()+">> ["+yyline+" , "+yycolumn+"]");
                               /*ErroresLexicos.add(new SyntaxError("Lexico", yyline, yycolumn, "El caracter " + yytext() + " no es admitido en el lenguaje", "Archivo de Datos"));
                                */
                            }


