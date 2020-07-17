SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_251\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
SET JFLEX_HOME="C:\Users\sicmmar\Documents\Java\Ejemplo1\Librerias\jflex-1.7.0\lib"
cd "C:\Users\sicmmar\Documents\Java\Ejemplo1\src\Analizador"
java -jar %JFLEX_HOME%\jflex-full-1.7.0.jar lexico.jflex
pause
