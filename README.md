# Subscriptions REST Service

The aim of the project is to design and develop a Subscription API. This service will allow users to sign up for a specified newsletter and receive personalised emails. 

The subscription service interacts with two more internal services: the event and email services. The event service it will be used to create new newsletters and list the existing ones. Finally, the second service sends a personalised email to subscribers of an specified newsletter. 

The project contains a unit test collection using JUnit to validate and ensure that code meets its design and behaves as intended.

## Requirements to build the application

* Java SE Development Kit 1.8 or later
* Apache Maven 3.0+


## Build and run
* Download the repository with the source code using git:

	`git clone https://github.com/ARecuenco92/subscriptions-rest-service.git`

* Compile the source code, run tests, and package the code up in a JAR file with Maven:

	`mvn package`

* Execute the application and the embedded Server will start, deploy the services and listen on port 8080:

	`java -jar target/subscriptions-rest-service-0.0.1-SNAPSHOT.jar`

* Open a browser and navigate to http://localhost:8080/swagger-ui.html

* See the subscription API and interact with it using Swagger UI

## Software frameworks/libraries

### Server side

* **Apache Maven**: Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.

	Reference: [Apache Maven](https://maven.apache.org/)

* **Spring Boot**: is an application framework for the Java platform that provides extensions for building web applications on top of the Java EE platform.

	Reference: [Spring Boot](https://projects.spring.io/spring-boot/)

* **H2 Database**: is a relational database management system that can be embedded in Java applications. It is used to store the newsletter subscriptions.

	Reference: [H2 Database Engine](http://www.h2database.com/html/main.html)
	
* **JUnit 4**: is a simple framework to write unit tests in Java applications. It is used to ensure that the code behaves as intended.

	Reference: [H2 Database Engine](http://www.h2database.com/html/main.html)
		
* **Swagger2 & Swagger UI**: is a powerful open source framework backed by a large ecosystem of tools that helps you design, build, document, and consume your RESTful APIs. Swagger UI  allows anyone to visualize and interact with the API’s resources. 

	Reference: [Swagger2](http://swagger.io/)
## References

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
