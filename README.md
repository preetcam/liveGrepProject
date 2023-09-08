NASA Log Analyzer
A POC to develop an interactive tool to comb through NASA logs using a regex pattern and an optional date range.

Requirements:
Java JDK (8+)
Maven
NASA logs placed in src/main/resources/NASA_access_log
Steps to Run:
Compile the Application:
mvn clean package

Run the Application:
java -jar target/liveGrepProject.jar

Testing:
Testing with Postman
Launch Postman.
Choose the GET HTTP method from the dropdown.
Provide the URL: http://localhost:8080/
Set the parameters:
Key: directory, Value: /path/to/your/NASA_access_log.
Key: regex, Value: your_regex_here.
Key: dateRange, Value: your_date_range_here.

Sample : http://localhost:8080/searchLogs?regex=unicomp%5B0-9%5D&directory=resources%2FNASA_access_log&dateRange=01/Jun/1995-01/Aug/1995


