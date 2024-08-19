CSV File processor
- Load CSV data from testcustomer.csv file in classpath into collection of Customer object
- Convert Customer object to JSON string and sent it to CUstomer service 

1. Requirements
   For building and running the application you need:

    - Java 17
    - Maven 3.6.3


Steps to Run Java application
1) Without any command line arguments 
   - mvn clean compile
   - load csv data in sr/main/resources/testcustomer.csv file
   - run mvn command from application root folder
     mvn exec:java -Dexec.mainClass=org.tutorial.App 

2) With command line arguments

    mvn exec:java -Dexec.mainClass=org.tutorial.App -Dexec.args="http://localhost:8082 /Users/shilpashivaprasad/prashant/testcustomer.csv"
    
    It accepts 2 arguments
    arg0 - Customer service HOST & Port. If not passed, default URL is http://localhost:8080
    arg1 - CSV file URI . If not passed, application will read testcustomer.csv file classpath (resources folder)
3) Verify data import 
    - http://localhost:8082/api/fetch?customerReferance=<customerReference>