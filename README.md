# Products API (v1.0.0)

## Patterns and best practices used:

* Embedded H2 database.
* Rest API 
* Services pattern (@Service)
* Data access object pattern (DAO, @Repository)
* Inversion of control pattern & dependency injection (@Autowired)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You need to install at least these software before been able to use the project.

* JDK 1.8+
* Sprint Tool Suite

### Installing

* Cloning the repository

```
git clone https://github.com/pabloxtiyo/products-api.git
```

* Import the application as a maven project from the Spring Tool Suite
* The application has his own H2 embedded database
* Run the solution as an spring boot application.
* The application comes with two started users, admin with password 'admin' and Role 'ADMIN' and user with password 'user' and Role 'USER'.
* You can also use swagger to navigate throug the API visiting this url:
```
http://locahost:8080/pablo-portillo/swagger-ui.html#/
```
* To visit the H2 database console please go to:
```
http://localhost:8080/pablo.portillo/console
```

End with an example of getting some data out of the system or using it for a little demo

## Built With

* [Spring Framework](https://spring.io/) - Core JAVA framework
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring DATA](https://spring.io/projects/spring-data)
* [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html) 
* [Spring Security](https://spring.io/projects/spring-security)
* [Thymeleaf](https://www.thymeleaf.org/) - Templates Engine
* [Maven](https://maven.apache.org/) - Dependency Management
* [JQuery](https://jquery.com/) - Used to generate RSS Feeds


## Authors

* **Pablo Portillo** - *Enhancements & definitions* 

## Acknowledgments

* I really hope that this testing API can help you to understand a little bit about REST and his principles.
