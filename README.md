
# Book Shelf

Book Shelf backend service to deal with authors and books. The backend is a REST server that accepts requests and gets/updates
the information stored in a persistence system.


## Prerequisites
- OpenJDK (13.0.7)
- Scala (3.1.2)
- Sbt (1.6.2)
- Docker (20.10.17)

## Quickstart

- Clone the repo:
```
git clone https://github.com/paulsasi/book-shelf.git
cd book-shelf
```

- Run the database:
```
docker-compose -f utils/docker/docker-compose-postgres.yaml up -d
psql -h localhost -U book-shelf book-shelf -f src/main/resources/db/V001_schema.sql
```

- Run the Book Shelf service:
```
sbt run
```