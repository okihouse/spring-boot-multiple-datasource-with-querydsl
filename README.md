# Description
This is sample project for spring-boot-multiple-datasource-with-querydsl

# Requirements
* Java 1.6+ (this project is set up with version 1.8)
* [Spring boot](http://projects.spring.io/spring-boot/) 1.2.8+ (spring-boot-starter-redis)

# Run
```bash
	mvn test
```

## Sample API

- **GET** /api/first  // get all first db datas
- **GET** /api/first/messages  // get all messages first db datas
- **PUT** /api/first // save random data to first db 

- **GET** /api/second  // get all second db datas
- **GET** /api/second/messages  // get all messages second db datas
- **PUT** /api/second // save random data to second db 
