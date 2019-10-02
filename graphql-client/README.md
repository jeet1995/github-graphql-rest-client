## CS 474: Object Oriented Languages and Environments
Homework 1: A GraphQL Client for GitHub's v4 REST API
---
Name: Abhijeet Mohanty
---
### Objective

The objective of this homework is to form GraphQL queries so as to 
slice and dice the Github schema and to define models which store the responses
of these queries. 

### Instructions

#### Development Environment

The project was developed using the following environment:

- **OS:** macOs Mojave 
- **IDE:** IntelliJ IDEA Ultimate 2018.2
- **Profiling tools:** JProfiler
- **Scala version:** 2.12
- **SBT version:** 1.0
 
#### Assembly details

- Primarily the `build.sbt` file was added to the `graphql-client` where the dependencies along with the Scala and SBT versions used were specified.
- Then I imported at the project level to include `graphql-client` as an sbt module.

#### Prerequisites

- [Scala 2.12](https://www.javahelps.com/2018/12/setup-scala-on-intellij-idea.html) and [SBT 1.0](https://medium.com/@mattroberts297/using-scala-sbt-and-intellij-idea-c05857daedbd)

#### Running the application

- Navigate to the module **<parent_project>/graphql-client**  and then execute the following command :-
    ```
    >sbt clean compile test
    ```
    ```
    >sbt clean compile run
    ```
### About the application

#### Code flow overview

- `GithubGraphQLRunner` is the starting point of the application which passes GraphQL queries specified
in the `queries.conf` file.
- These queries are passed one at a time to the `GithubGraphQLClient` which builds the request object with the 
help of paramters defined in the `application.conf` file to hit
GitHub's endpoint.
- `QueryExecutor` executes queries and returns the response with the help of the `DataFactory`.
- `DataFactory` creates instances depending on the subtype of the `Data` to be created. This is evaluated with the help of the response JSON object.
- Once the response objects have been obtained and de-serialized into a instances which implement the `Data` interface,
the contents of these objects are displayed with the help of loggers.
- Finally the `DataPrintExecutor` prints the list of `Data` responses formed.

### Github models used

#### Viewer


- This is an example of a simple GraphQL query which fetches the **Viewer** related information from GitHub.

```
    query {
      viewer {
        login
        name
      }
    }
```
    
- This is the response which is returned in **JSON** format.
    
 ```
    {
      "data": {
        "viewer": {
          "login": "jeet1995",
          "name": "Abhijeet Mohanty"
        }
      }
    }
```
#### Repository


- Here we fetch **Repository** information based on the **Owner** along with fetching **Commit** related information.
    
 ```
    query($owner_name: String!, $repository_name: String!){
    	repository(owner: $owner_name, name: $repository_name){
        forkCount
        createdAt
        codeOfConduct{
          body
        }
        
        commitComments(first: $num_comments) {
          nodes{
            author{
              login
            }
          }
        }
       owner{
        login
      }
        licenseInfo{
          body
        }
      }
    }
 ```
    
- We define an input through another **JSON** structure such as below for the inputs :
    
 ```
    {
      "owner_name": "uber",
      "repository_name": "react-vis",
      "num_comments" : 7
    }
```
    
- And finally the response :
    
 ```
    {
      "data": {
        "repository": {
          "forkCount": 630,
          "createdAt": "2016-02-22T20:45:50Z",
          "commitComments": {
            "nodes": [
              {
                "author": {
                  "login": "bulyonov"
                }
              },
              {
                "author": {
                  "login": "fastfrwrd"
                }
              },
              {
                "author": {
                  "login": "fastfrwrd"
                }
              },
              {
                "author": {
                  "login": "fastfrwrd"
                }
              },
              {
                "author": {
                  "login": "roderickhsiao"
                }
              },
              {
                "author": {
                  "login": "roderickhsiao"
                }
              },
              {
                "author": {
                  "login": "balthazar"
                }
              }
            ]
          },
          "owner": {
            "login": "uber"
          }
        }
      }
    }
```
### Design patterns used

#### Abstract factory pattern : 

- This particular design pattern is used to create instances of the `Viewer` and `Repository` classes which
are a slicing of the GitHub's schema. The `Datafactory` class which implements the `AbstractFactory` creates
instances of an implementation of the `Data` - `Viewer` and `Repository` being examples of it.
- This design pattern was chosen at it provides a clean way to create instances of classes.
- What I thought was with the hierarchies, we can have an infinite number of abstract factories such as the `AbstractViewer` or
`AbstractRepository`. The fact that these can be defined infinitely is a con. The class hierarchy would be a bit too cumbersome to come up with.

#### Observer pattern :

- This particular design pattern is used by the `QueryLogger` class which is the observer on the subject which is 
the `QueryExecutor`. Each time a query execution is about to run, `QueryLogger` class observes the subject and 
logs a message. 
- This design pattern was chosen as it helps to keep track of the executed queries.
- In case of multiple subjects executing at the same time, there could be a race condition in the case say we want to report the
time these subjects started executing. To achieve synchronization in logging would be a challenge with this design pattern.

#### Facade pattern :

- Here the `GraphQLClientRunner` class behaves as a facade to the `GraphQLClient` class which consists of the 
deeper details as far as the execution of the application is concerned be it execution of queries or 
printing response data information.
- This design pattern hides the deeper implementation details from the end user such as how queries are executed and the response is deserialized.
- In order to hide the implementation details from the end user we may end up coupling concerns unrelated to each other.


### CPU and RAM usage information

- At different points of the execution, the JProfiler took was used to record key performance indicators snapshots were taken such as below.

- Memory
![Memory](snapshots/Memory.png)
- Process load
![ProcessLoad](snapshots/ProcessLoad.png)
- Thread
![Threads](snapshots/Threads.png)
- Garbage Collection
![GarbageCollection](snapshots/GarbageCollection.png)
