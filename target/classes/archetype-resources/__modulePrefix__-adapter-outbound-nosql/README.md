# SQL Adapter

## Introduction
Represents the right side of the [Hexagonal](https://blog.octo.com/en/hexagonal-architecture-three-principles-and-an-implementation-example/) (Ports & Adapters) architecture.

This is where we’ll find what the application needs, what it drives to work. It contains essential infrastructure details such as the code that interacts with the **database**, makes calls to the file system, or code that handles HTTP calls to other applications on which you depend for example.

This is the side where we find the actors who  **are managed**  by the  _Domain_.

This is nothing more than a concrete implementation of the **driven port** defined by application **core business**.

## Tech Stack
As described above this adapter will interact with the database.
We'll use the following tech stack:
 - [PostgreSQL](https://www.postgresql.org/) as relational database
 - [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data) to store and retrieve data
 - [MapStruct](https://mapstruct.org/) for mappings between _Domain_ and _Database Entities_
 - [Lombok](https://projectlombok.org/) to avoid repetitive code
 
## Implementation
This guide walks you through the process of the implementation of this adapter.
Let's suppose our core business exposes the following port to be implemented in this adapter.

```java
public interface ICustomerRemotePort {
    List<Customer> findAll() throws BaseException;

    Optional<Customer> findById(Long id) throws BaseException;

    Customer create(Customer customer) throws BaseException;

    void delete(Long id) throws BaseException;

    Customer update(Customer customer) throws BaseException;

    boolean exists(Long id) throws BaseException;
}
```

### Configuration
This stage must be done in our `application.yaml` file.
We'll define our desired configuration for Spring Data JPA.

```yaml
spring:  
  datasource:  
    url: jdbc:postgresql://localhost:5432/postgres  
    username: postgres  
    password: postgres 
  jpa:  
    database-platform: org.hibernate.dialect.PostgreSQL10Dialect  
    hibernate:  
      ddl-auto: update  
    open-in-view: false  
    show-sql: true
```

### Define a Simple Entity
We'll store `CustomerJpaEntity` objects, each annotated as JPA entity. 
Each entity must extend the [BaseJpaEntity](https://app.celfocus.com/gitlab/JAWWY/jawwy-microservices/blob/master/commons/common-jpa/src/main/java/com/celfocus/jawwy/common/jpa/entity/BaseJpaEntity.java). This class provides [auditing](https://app.celfocus.com/gitlab/JAWWY/jawwy-microservices/blob/master/commons/common-jpa/src/main/java/com/celfocus/jawwy/common/jpa/entity/Auditable.java) and is part of [common-jpa](https://app.celfocus.com/gitlab/JAWWY/jawwy-microservices/tree/master/commons/common-jpa) module. 

```java
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerJpaEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "customer_seq")
    private Long id;
    private Integer age;
    private String name;
    private String lastName;
}
```

### Repository & Queries
[Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data) repositories are interfaces that you can define to access data. JPA queries are created automatically from your method names.

These interfaces usually extend [CrudRepository](https://docs.spring.io/spring-data/commons/docs/2.2.4.RELEASE/api/org/springframework/data/repository/CrudRepository.html) which provides generics for CRUD operations. They also support pagination and sorting.

For more complex queries, you can annotate your method with Spring Data’s [Query](https://docs.spring.io/spring-data/jpa/docs/2.2.4.RELEASE/api/org/springframework/data/jpa/repository/Query.html) annotation.

The following example shows a typical Spring Data repository interface definition:

```java
@Repository("customerJpaRepository")
public interface ICustomerRepository extends JpaRepository<CustomerJpaEntity, Long> {
    List<CustomerJpaEntity> findByAge(Integer age);

    List<CustomerJpaEntity> findByNameIgnoreCase(String name);

    List<CustomerJpaEntity> findByNameContainingIgnoreCase(String name);
}
```

For example, `ICustomerRepository` defines 3 methods:
- `List<CustomerJpaEntity> findByAge(Integer age)` : Find all the customers that have the given age
- `List<CustomerJpaEntity> findByNameIgnoreCase(String name)` : Find all the customers that have the given name ignoring case
- `List<CustomerJpaEntity> findByNameContainingIgnoreCase(String name)` : Find all the customers that contains the given name ignoring case


### Mappings
At this stage, we'll show you how to map the _Domain_ into _Database Entities_ and vice-versa with [Mapstruct](https://mapstruct.org/). 

This API contains functions that automatically map between two Java Beans. With MapStruct we only need to create the interface, and the library will automatically create a concrete implementation during compile time.

##### Creating a POJO
Let's first create a simple Java POJO:

```java
public class Customer {
    private Long id;
    private Integer age;
    private String name;
    private String lastName;
}

public class CustomerJpaEntity extends BaseJpaEntity {
    @Id
    private Long id;
    private Integer age;
    private String name;
    private String lastName;
}
```

##### Mapper Interface

```java
@Mapper(config = BaseMapperConfig.class)
public interface CustomerJpaMapper {
    CustomerJpaEntity toCustomerJpaEntity(Customer customer);

    Customer toCustomer(CustomerJpaEntity customerJpaEntity);

    List<Customer> toCustomerList(Collection<CustomerJpaEntity> customerJpaEntities);
}
```

We did not create a concrete implementation for our  CustomerJpaMapper because [Mapstruct](https://mapstruct.org/) create it for us.

Our [BaseMapperConfig](https://app.celfocus.com/gitlab/JAWWY/jawwy-microservices/blob/master/commons/common/src/main/java/com/celfocus/jawwy/common/mapper/BaseMapperConfig.java) class is part of [common](https://app.celfocus.com/gitlab/JAWWY/jawwy-microservices/tree/master/commons/common) module. It configures each mapper as a autowire candidate.

You can find [here](https://www.baeldung.com/mapstruct) detailed information about mappings with different field names.

### Adapter
The adapter is the concrete implementation of the **driven port** defined by application **core business**.

At this stage, we'll implement the mentioned `ICustomerRemotePort`.
All we need is to inject the repository and the mapper in our implementation:
- `ICustomerRepository` : Retrieve/store data from the database  
- `CustomerJpaMapper` : Perform mappings between _Domain_ and _Database Entities_

```java
@Service("customerAdapterSql")
public class CustomerPersistenceAdapter implements ICustomerRemotePort {
    private final ICustomerRepository repository;
    private final CustomerJpaMapper mapper;

    @Autowired
    public CustomerPersistenceAdapter(ICustomerRepository repository, CustomerJpaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> findAll() {
        List<CustomerJpaEntity> entityList = this.repository.findAll();
        return this.mapper.toCustomerList(entityList);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return this.repository.findById(id)
                              .map(this.mapper::toCustomer);
    }

    @Override
    public Customer create(Customer customer) {
        return this.createOrUpdate(customer);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Customer update(Customer customer) {
        return this.createOrUpdate(customer);
    }

    @Override
    public boolean exists(Long id) {
        return this.repository.existsById(id);
    }

    private Customer createOrUpdate(Customer customer) {
        CustomerJpaEntity entity = this.mapper.toCustomerJpaEntity(customer);
        CustomerJpaEntity newEntity = this.repository.save(entity);
        return this.mapper.toCustomer(newEntity);
    }
}
```

This adapter must be injected in our **core business** with his qualified name via Spring's [Qualifier](https://www.baeldung.com/spring-qualifier-annotation) annotation.

For instance, let's suppose our **core business** wants to use this implementation on their Customer service: 

```java
@Service
public class CustomerServiceAdapter implements CustomerOperationsUseCase, GetCustomerQuery {
    private final ICustomerRemotePort port;
    private final CustomerDomainMapper mapper;

    @Autowired
    public CustomerServiceAdapter(@Qualifier("customerAdapterSql") ICustomerRemotePort port, CustomerDomainMapper mapper) {
        this.port = port;
        this.mapper = mapper;
    }

	// service methods
}
```
