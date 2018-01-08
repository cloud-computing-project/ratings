# RSO: Ratings microservice

## Prerequisites

```bash
docker run -d --name ratings -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=rating -p 5433:5432 postgres:latest
```

## Run application in Docker

```bash
docker run -p 8085:8085 ejmric/ratings
```

## Travis status 
[![Build Status](https://travis-ci.org/cloud-computing-project/ratings.svg?branch=master)](https://travis-ci.org/cloud-computing-project/ratings)