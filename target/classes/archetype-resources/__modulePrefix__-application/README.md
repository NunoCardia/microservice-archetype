# Application

This package basically is the configuration of the microservice. How this application has a springBootApplication, must have one file application.yml where we can find the information of the server port, the drivers of the mongoDB, postgres, camunda and the API´s that we consume in ESB package etc.
In the next example we show the application.yml of the customer-application.

```
server:  
 port: 8081  
spring:  
 application: name: customer-microservice  
  data:  
 mongodb: uri: ${data-mongodb-uri:mongodb://localhost:27017/customer}  
  datasource:  
 password: ${postgres-datasource-password:postgres}  
    url: ${postgres-datasource-url:jdbc:postgresql://localhost:5432/postgres}  
    username: ${postgres-datasource-username:postgres}  
  jpa:  
 database-platform: ${jpa-database-platform:org.hibernate.dialect.PostgreSQL10Dialect}  
    hibernate:  
 ddl-auto: ${jpa-hibernate-ddl-auto:update}  
    open-in-view: ${jpa-open-in-view:false}  
    show-sql: ${jpa-show-sql:true}  
camunda:  
 url: ${camunda-url:http://localhost:8085}  
  process: ${camunda-process:CheckoutProccessFlow}  
  variables:  
 in: return: ${camunda-variables-in-return:true}  
customer-service:  
 url: ${rest-customer-service-url:http://dummy.restapiexample.com/api/v1/}  
breakingbad-service:  
 url: ${rest-customer-service-url:https://www.breakingbadapi.com/api/}  
management:  
 endpoints: web: exposure: include: ${actuator-endpoints-web-include:health}  
  server:  
 port: ${actuator-server-port:8082}
 ```

