SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_251\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
SET CUP_HOME="C:\Users\sicmmar\Documents\Java\Ejemplo1\Librerias"
cd "C:\Users\sicmmar\Documents\Java\Ejemplo1\src\Analizador"
java -jar %CUP_HOME%\java-cup-11b.jar -parser Sintactico -symbols simbolos sintactico.cup
pause
