cls

javac -d bin\ .\src\Main\main.java .\src\Functions\* .\src\Matrix\* && jar cvmf MANIFEST.MF Kaydenji.jar bin\src\main.class bin\src\Functions\* bin\src\Matrix\*  && java -Kaydenji.jar
:: untuk compile semua file di source (src) menjadi binary (bin)


:: output berupa FinalApp.jar, butuh file manifest dan class dari bin


:: running file FinalApp.jar