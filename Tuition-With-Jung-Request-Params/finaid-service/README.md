# Financial Aid Service

This microservice allows clients to retrieve the amount of financial aid due for a particular student.

* ```/students``` (GET) returns a list of all students and their associated financial aid amounts
* ```students/{student id}``` (GET) returns the amount of financial aid for the given student

Financial aid information is returned in the following format:

```javascript
{
    "studentId": "<student id>",
    "aidAmount": <financial aid amount>
}
```

To run this service, type the following command in the project directory:

```./gradlew clean build bootRun```

The service runs on port 7001. 