#Installation
JDK8  
Gradle  
Maven  
GoogleChrome  

#Execution
##First time execution may be slower as necessary dependencies will be downloaded
mkdir WeightWatchers
git clone https://github.com/shamsjaved/WeightWatchers WeightWatchers
cd WeightWatchers/ShamsJaved_WeightWatchers

gradle run -Parg1=reverseString -Parg2=src/main/resources/stringForReversal.txt  
gradle run -Parg1=parseHtml -Parg2=src/main/resources/sample.html  
gradle run -Parg1=parseProperties -Parg2=src/main/resources/sample.properties 
