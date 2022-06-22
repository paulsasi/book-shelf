# RESTful API

This section describes the API endpoints and an end-to-end example on usage. The request and response are JSON objects.

## GET all authors
`GET /api/web/authors`

Retrieves all authors stored in the persistence system.

#### Example

```
http GET http://localhost:8080/api/web/authors
```
```json
[
  { "id": 1, "name": "mario", "nationality": "Portugal", "surname": "Lelel" },
  { "id": 2, "name": "Paul", "nationality": "Spain", "surname": "Sese" }
]
```

## GET author

`GET /api/web/author\?id\={id}`

Retrieves the author with the selected id.

#### Example

```
http GET http://localhost:8080/api/web/author\?id\=1
```

```json
{ "id": 1, "name": "mario", "nationality": "Portugal", "surname": "Lelel" }
```

## PUT author
`PUT /api/web/author?name={name}&surname={surname}&nationality={nationality}`

Creates an author.

#### Example

```
http PUT http://localhost:8080/api/web/author\?name\=Paul\&surname\=Sese\&nationality\=Spain
```
```
Author successfully posted
```


## DELETE author
`DELETE /api/web/author?id={id}`

Deletes an author with the selected id.

#### Example
```
http DELETE http://localhost:8080/api/web/author\?id\=1
```
```
Author with id 1 successfully deleted.
```

## PATCH author
`PATH /api/web/author\?id\={id}\&name\={name}}\&surname\={surname}\&nationality\={nationality}`

Update the author with the selected id with the provided  name, surname and nationality.

```
http PATCH http://localhost:8080/api/web/author\?id\=1\&name\=asier\&surname\=goya\&nationality\=greece  
```
```
Author successfully patched
```

