cls

javac -d bin\ .\src\Main\main.java .\src\Functions\* .\src\Matrix\* && jar cvmf MANIFEST.MF Algeo01-22013.jar bin\src\Main\* bin\src\Functions\* bin\src\Matrix\*  && java -Algeo01-22013.jar

:: untuk compile semua file di source (src) menjadi binary (bin)

:: output berupa Algeo01-22013.jar, butuh file manifest dan class dari bin

:: running file Algeo01-22013.jar