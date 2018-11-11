#homework-lv report

##Container
Tomcat embedded servlet container

##Database
H2 in-memory database
- URL: /api/console 
- Connection settings:
    - JDBS URL: jdbc:h2:mem:loan
    - Pser Name: sa
    - No password required

##Rest-API 
- GET: api/loan/client/{clientid}
- POST: api/loan/apply
    - Body: {
        "clientId" : 1,
        "ipAddress" : "127.0.0.1",
        "requestedSum" : 1000,
        "duration" : 12
      }
- POST: api/loan/extend
    - Body: {
              "clientId" : 1,
              "loanId" : 1,
              "ipAddress" : "127.0.0.1",
              "duration" : 12
            }

##Execution
Run bootJar gradle task to create a jar file

