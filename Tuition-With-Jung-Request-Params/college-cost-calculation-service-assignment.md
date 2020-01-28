# College Cost Calculation Service

Time to complete: 90 minutes

### Objective:

1. Create a tuition calculation service for a given student.
2. This microservice must use the Financial Aid and Tuition services.
3. This microservice must have adequate unit and integration tests.
4. This microservice must include exception handling.

### Infrastructure (Supplied)

You will be supplied the following services to help complete your project. Each service includes a README file with instructions for running the service and documentation of the API:

* Eureka Service Registry
* Financial Aid Service
* Tuition Service

### College Cost Calcuation Service Requirements (You Implement)

Your service will calculate the cost of college for a given student. The calculation will take the following into account:

* The student's major
* In state / out of state tuition
* Graduate/undergradute program
* Financial aid

The total cost is the program tuition less any financial aid due to the student.

#### API

* URL: /cost/{studentId}/{major}?inState=[true|false]&undergraduate=[true|false]
* HTTP Method: GET
* RequestBody: None
* ResponseBody: College cost information (JSON - see below)

#### Response Format

```javascript
{
   "studentId":"1234",
   "major":"business",
   "inState":"true",
   "undergraduate":"false",
   "financialAid":"500.00",
   "tuition":"12500.00",
   "cost":"12000.00"
}
```

