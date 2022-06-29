# RESTful API - Book

This section describes the book related API endpoints and an end-to-end example on usage. The request and response are JSON objects.

## GET all books
`GET /api/web/books?topic={topic}&author={id}

Retrieves all books stored in the persistence system. The parameter `topic` is an optional argument to only retrive
books of certain topic. Similarly, `author` is an optional parameter to only retrieve books with
a specific author id.

#### Example

```
http GET http://localhost:8080/api/web/books\?author\=1\&topic\=SCI_FI
```
```json
[
  {
    "author": {
      "name": "Pol",
      "nationality": "Spain",
      "surname": "Sasi"
    },
    "date": "1995-01-01",
    "id": 1,
    "name": "Lord of the Rings",
    "topic": "SCI_FI"
  },
  {
    "author": {
      "name": "Pol",
      "nationality": "Spain",
      "surname": "Sasi"
    },
    "date": "2001-01-01",
    "id": 3,
    "name": "Lord of the Rings 2",
    "topic": "SCI_FI"
  }
]
```

## GET book

`GET /api/web/book\?id\={id}`

Retrieves the book matching the selected id.

#### Example

```
http GET http://localhost:8080/api/web/book\?id\=4      
```
```json
{
  "author": {
    "name": "John",
    "nationality": "England",
    "surname": "Clarck"
  },
  "date": "2017-01-01",
  "id": 4,
  "name": "El Resplandor",
  "topic": "HORROR"
}
```

## DELETE book
`DELETE /api/web/book?id={id}`

Deletes a book matching the selected id.

#### Example

```
http DELETE http://localhost:8080/api/web/book\?id\=4
```
```
Book with id 4.0 successfully deleted.
```

## PUT book
`PUT /api/web/book\?name\={name}\&releaseDate\={date}\&topic\={topic}\&author\={id}`

Creates a book.

#### Example

```
http PUT http://localhost:8080/api/web/book\?name\=SeeechBOok\&releaseDate\=2021-07-01\&topic\=HORROR\&author\=1   
```
```
Book successfully inserted
```

## PATCH book
`PATH /api/web/book\?id\={id}\&name\={name}\&releaseDate\={date}\&topic\={topic}\&author\={author_id}`

Update the book with the selected id with the provided  name, releaseDate, topic and author_id.

#### Example

```
http PATCH http://localhost:8080/api/web/book\?id\=2\&name\=PolBook\&releaseDate\=2021-01-01\&topic\=HORROR\&author\=1 
```
```
Book with id 2 successfully updated
```