https://github.com/GrzegorzDudzinski/# hsbc-test-project

To read documentation run spring-boot app and point your browser to: http://localhost:8080/

Maven build process:
clean install on project persistence
clean install on project services
clean package on project rest

Run:
run class com.hsbc.testproject.rest.WebAppInitializer

Postman collection HSBC.postman_collection.json consists of rest queries usefull when testing. 

PROBLEMS FOUND:
*At: hsbc-test-project/persistence/src/main/java/com/hsbc/testproject/persistence/memory/MemoryService.java 
  - Mutable User class as HasMap key. This issue can cause memory leaks. 
*At: hsbc-test-project/restapi/src/main/java/com/hsbc/testproject/rest/exceptions/ErrorHandler.java 
  - Each exception thrown at rest controllers handled the same way with no differentiating.
  - @ExceptionHandler(Throwable.class) will catch Errors also. 
  - No exception logging 
*No rest api version controller. 
*At: hsbc-test-project/restapi/src/main/java/com/hsbc/testproject/rest/controllers/RestAPIController.java
  - Method  createPost should return ResponseEntity with Headers containing location of created resource. 
  - Method  getUserPosts should return HTTPStatus.NOT_FOUND in case of retrieving non existing users posts (same issue at:  addTrackedUser and getUserTrackedPosts)
  - Resource map shoud be like this 
    users/{login} {
          /trackedUsers
          /trackedPosts
    }


In case you find something else please email me at: g.dudzinski666@gmail.com
(i had only few hours to write this code so please be forgiving) 
