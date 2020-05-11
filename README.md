# cs-credit-cards-service
Clearscore Assignment

# Getting Started

### About the architecture
* This webservice is designed based on fault tolerant architecture using Hystrix circuit breaker.
* Business logic is separated for both card providers so that if one card provider service is unavailable then it won't impact on others.
* In case of exception it will return meaningful response.
* It consists two spring profiles dev and prod. By default dev profile is active. If you want to run it with prod profile please check **_How to Run_**

### How to Run
* Please run provided **_start.sh_**
* If you want to change **http port** please edit provided bash script and change **HTTP_PORT** environment variable.
* If you want to change **CSCards API URI** please edit provided bash script and change **CSCARDS_ENDPOINT** environment variable.
* If you want to change **ScoredCards API URI** please edit provided bash script and change **SCOREDCARDS_ENDPOINT** environment variable.

### Deployment
* You can stand-alone run this service on your server, or
* You can dockerize service and deploy.

### Calling "/creditcards" endpoint.
* Use any client you prefer.
* Provide JSON request body, eg,
{
"name": "John Smith",
"creditScore": 500,
"salary": 28000
}
* Response will be JSON array (sort by cardScore desc) with http status 200 in case of success response.
* Error response in case of bad request or internal server error with http status 400 and 500 respectively. Eg, bad request -
* Example Bad Request:
{
    "errorMessage": "Validation Failed",
    "errorMessages": {
        "name": "Full name is required"
    }
}
