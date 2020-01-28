# Tuition Service

This microservice allows clients to retrieve the in state/out of state, graduate/undergraduate tuition amounts for various majors. If the major given in the request does not exist, the service throws and exception and returns a 500 error.

* ```/majors``` returns a list of majors that exist in the system
* ```/tuition/{major}``` returns the tuition information for the given major. This request has two optional query parameters that both default to ```true```:
  * ```inState``` - indicates whether or not the student qualifies for in state tuition
  * ```undergraduate``` - indicates whether or not the student is in an undergraduate or graduate program
  * Example request:
    * ```/tuition/English?inState=false&undergraduate=false```

Tuition information is returned in the following formate:

```javascript
{
    "major": "specified major",
    "inState": <true or false>,
    "undergraduate": <true or false>,
    "tuition": <tuition amount>
}
```

To run this service, type the following command in the project directory:

```./gradlew clean build bootRun```

The service runs on port 8001. 