#Installation
JDK8  
Gradle  
Maven  

#Execution
##First time execution may be slower as necessary dependencies will be downloaded
cd <projectdir>  
gradle run -Parg1=reverseString -Parg2=src/main/resources/stringForReversal.txt  
gradle run -Parg1=parseHtml -Parg2=src/main/resources/sample.html  
gradle run -Parg1=parseProperties -Parg2=src/main/resources/sample.properties  
