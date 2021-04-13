# Dev News Exercise
Exercise in building Spring application and rest APIs, created during the Back-end Development 
module in Software Development Academy, iteration 9. The project should be a backend API, without 
a graphical user interface, for a developer news site. 

## Project Specifications
Users should be able to create articles, comment on them and post their reactions. The project 
should be a Spring application, using Gradle, Spring Web, Spring JPA, and PostgreSQL.

### Articles
Endpoints for the article API (prescribed):

| HTTP Method | HTTP Path | Action |
| ------------|-----------|--------|
| `GET`    | `/articles`      | return all articles |
| `GET`    | `/articles/{id}` | return a specific article based on the provided id|
| `POST`   | `/articles`      | create a new article|
| `PUT`    | `/articles/{id}` | update the given article|
| `DELETE` | `/articles/{id}` | delete the given article|

### Comments

Endpoints for the comment API (prescribed):

| HTTP Method | HTTP Path | Action |
| ------------|-----------|--------|
| `GET`    | `/articles/{articleId}/comments`    | return all comments on article given by `articleId` |
| `GET`    | `/comments?authorName={authorName}` | return all comments made by author given by `authorName` |
| `POST`   | `/articles/{articleId}/comments`    | create a new comment on article given by `articleId` |
| `PUT`    | `/comments/{id}`                    | update the given comment |
| `DELETE` | `/comments/{id}`                    | delete the given comment |

### Topics

Endpoints for the topic API (prescribed):

| HTTP Method | HTTP Path | Action |
| ------------|-----------|--------|
| `GET`    | `/topics` | return all topics |
| `GET`    | `/articles/{articleId}/topics` | return all topics associated with article given by `articleId` |
| `POST`   | `/articles/{articleId}/topics` | associate the topic with the article given by `articleId`. If topic does not already exist, it is created |
| `POST`   | `/topics` | create a new topic |
| `PUT`    | `/topics/{id}` | update the given topic |
| `DELETE` | `/topics/{id}` | delete the given topic |
| `DELETE` | `/articles/{articleId}/topics/{topicId}` | delete the association of a topic for the given article. The topic & article themselves remain |
| `GET`    | `/topics/{topicId}/articles` | return all articles associated with the topic given by `topicId` |

 - Additional endpoint added:
- `POST`, `/articles/{articleId}/topics/{topicId}`, Associate (existing) topic with one article

### Reactions (Bonus Exercise)

Endpoints for the reaction API:

| HTTP Method | HTTP Path | Action |
| ------------|-----------|--------|
| `GET`    | `/reactions` | View all created reactions |
| `GET`    | `/articles/{articleId}/reactions`| View all reactions on an article |
| `GET`    | `/comments/{commentId}/reactions`| View all reactions on a comment |
| `POST`   | `/articles/{articleId}/reactions`| Add reaction to article |
| `POST`   | `/comments/{commentId}/reactions`| Add reaction to comment |
| `DELETE` | `/reactions/{id}`| Delete reaction |

## Files & Directories

Within the main folder, there is a resource directory containing the Spring application properties file, and a java 
project directory with three packages: API, Model, and Repository, which contain the classes and interfaces required.
Within the API directory is a package containing exception.

```
.src/main
├── resources
└── se.sdaproject
    ├── api
    │   └── exception
    ├── model
    └── repository
```

## Getting Started

This exercise can be run by first starting Docker, then entering the command:

`docker-compose up `

And then can be run by using the Gradle bootRun command:

`gradlew bootRun `

Following this, the recommended way of sending commands to the API is using Postman.

### Sample Postman Commands

Listed below are some sample Postman commands, with HTTP Method, address, title, and content.

-  GET, `localhost:8080/articles`, List All Articles

- POST, `localhost:8080/articles`, Create New Article,
  ```
  {
     "title":"99 reasons to learn Spring",
     "body":"In this article I'll be listing 99 reasons why you should learn spring and use it in your next project...",
     "authorName":"Fiona Thompson"
  }
  ```
- POST, `localhost:8080/articles/1/comments`, Create Comment On Article 1,
    ```
  {
       "body":"This article is very well written!",
       "authorName":"Fiona Thompson"
  }
    ```
- PUT, `localhost:8080/comments/1`, Update Comment With ID 1,
    ```
  {
       "body":"This article is not very well written.",
       "authorName":"Frank",
       "article": {
           "id":1
       }
  }
    ```
- GET, `localhost:8080/comments?authorName=Frank`, View Comments With authorName Frank
  
- POST, `localhost:8080/articles/1/topics`, Create New Topic On Article ID 1,
    ```
  {
       "name":"spring"
  }
    ```
- POST, `localhost:8080/articles/1/reactions`, Add Heart Reaction To Article ID 1,
  ```
  {
     "type":"heart"
  }
    ```
- POST, `localhost:8080/comments/1/reactions`, Add Award Reaction To Comment ID 1,
    ```
  {
       "type":"award"
  }
    ```
- GET, `localhost:8080/comments/1/reactions`, View All Reactions On Comment ID 1

- GET, `localhost:8080/articles/1/topics`, List All Topics Associated With Article ID 1

- DELETE, `localhost:8080/articles/1/topics/1`, Detete Topic 1 From Article 1



## Author
Fiona Thompson

Created between Apr 7-13 2021 for an assignment from Software Development Academy, iteration 9.