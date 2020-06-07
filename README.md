# stocks-market-job
Manage Jobs for Stock Shares

## Motivation

- Manage jobs for Stocks Shares, and responsible to trigger execution that retrieves stock share data in the stock 
market service.

## Prerequisites

Make sure the prerequisite list below is present on your environment
    
- [JAVA 8+](https://www.java.com/en/download/) _(at least)_
- [Springframework 2.2.0](https://spring.io/)
- [Maven 3+](https://maven.apache.org/)
    - Follow the [Baeldung tutorial](https://www.baeldung.com/install-maven-on-windows-linux-mac) to configure the 
    environment variables properly 
- [Docker](https://www.docker.com/) _the latest stable version is recommended for debugging_

## API Reference (Endpoints)

Links below describes how the API endpoints are defined.

- [actuator/health](docs/actuator-health.md)
- [scheduler/v1/job](docs/scheduler.md)

## Running (localhost)

#### Build artifacts

- In the application root, run `mvn clean install` in order to generate the proper binaries. 

```bash
$ mvn clean install
```

The statement below is expected
```bash
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

Once the build is completed, the `jar` is now contained in the `target` folder.

#### Run the multi-container application

- Still in the application root, run `docker-compose up --build`, and wait for the application deployment in docker.

```
$ docker-compose up --build
```
 
By default, docker runs in `localhost`, anyhow check the current host in `docker host` at Docker settings.

## Logs

Not yet implemented

## Class Diagram

Not yet implemented
