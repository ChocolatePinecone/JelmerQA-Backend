# JelmerQA-Backend application

A [Spring Boot](http://projects.spring.io/spring-boot/) based app for processing questions to [Jelmer Pijnappel](https://nl.linkedin.com/in/jelmer-pijnappel), asked from the website [jelmerpijnappel.com](https://jelmerpijnappel.com).

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Maven 3](https://maven.apache.org)

## Running the application locally

Use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) with the development profile like so:

```shell
mvn spring-boot:run -P development
```

## Deploying the application to Heroku

The easiest way to deploy the application to Heroku is to push to the master branch on the github repo.
The Heroku server is set up to automatically detect changes on the master branch and trigger a deployment.

## Generating javadocs

Use the javadoc maven plugin, which will generate the javadocs under `target/site/apidocs`:

```shell
mvn javadoc:javadoc
```

## Generating sequence diagrams

This project has multiple diagrams to clearly convey how program processes and flows should work.
These have been made as PlantUML text files that can be used to generate diagram images. To generate these, use the command:

```shell
mvn exec:java@plantuml
```

## Connecting to the database

A Postgres database is used on the server, so use the PostgreSQL tool and the corresponding `psql` command to access the database directly.