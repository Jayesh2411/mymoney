Instructions:


TO BUILD:


mvn clean install -q assembly:single   ->To run with tests
mvn clean install -DskipTests -q assembly:single  ->To run without tests


TO RUN:

java -jar target/geektrust.jar /Users/jayeshsinha/Documents/mymoney/src/test/java/integrationtest/testdata1.txt

