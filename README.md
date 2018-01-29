# Sample Rest API application

## Required software

For compilation and development you need to install following software packages for your OS:

- Docker >= 17.12-ce
- Java JDK 8

## Run as an application

To run Sample Rest API application:

	docker-compose up --build

## A simplified development environment

Start database using the following command:

	docker-compose up database
	
Build and start up the application in dev mode:

	./gradlew bootRunDev
	

## REST API

The URL for the content is http://localhost:8080/

### Save value

**Request:**

	POST /saver/

	Host: localhost:8080
	Content-type: application/json
	Accept: application/json

	{
		"value" : "100.0"
	}
	
**Returns:**

	success
	
**Error:**

	'value' is required.
	
### Get latest saved value

**Request:**

	GET /saver/

	Host: localhost:8080
	Content-type: application/json
	Accept: application/json

**Returns:**

	{ "time":"1516881556", "value": 100.0 }
	
