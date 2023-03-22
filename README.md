# 1oT_Backend
## Technology used:

- Gradle: 7.6.1
- Spring Boot: latest
- Lombok: 1.18.24

## Compiles and hot-reloads for development
```
./gradlew bootRun
```
or
```
run following file src/main/java/com/example/iot_backend/IoTBackendApplication.java
You can do this with Ctrl + Shift + f10
```

## Some notes
### Functionality that has been implemented 

- Getting the xml response from reference url and parsing it into dto objects.
- Finding the minimum and maximum temperature
- Writing the average temperature to a file. Two cases:\
       1)File does not exist, create a file and write "temperature + time stamp"\
       2)File already exists, update the existing file by adding a new "line temperature + timestamp" to the end of the file

### Experience

 - The most difficult part on the back for me was the parsing of the xml response, because I had never worked with this type of language before. 
 
