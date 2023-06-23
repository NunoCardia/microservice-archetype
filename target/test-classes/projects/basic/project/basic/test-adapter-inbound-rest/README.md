# REST API Adapter

## Introduction

The customer adapter inbound rest is an adapter that receives the action requested by one client and converts it into a request to a driver port. 
This driver adapter offers the HTTP REST API, and translates an incoming http request to a call to a "driver port" of the hexagon, in order to perform the requested functionality. The driver port is an interface offering the use cases of the hexagon.

## Tech Stack
As described above this adapter will interact with the rest client api.
We'll use the following tech stack:

 - [REST Controller](https://www.baeldung.com/spring-controller-vs-restcontroller) It's a convenience annotation that combines  _@Controller_  and  _@ResponseBody_
 - [MapStruct](https://mapstruct.org/) for mappings between _Domain_ and _Rest client
 - [Lombok](https://projectlombok.org/) to avoid repetitive code
 

### Mappings

At this stage, we'll show how to map the CustomerDto into Customer and vice-versa with [Mapstruct](https://mapstruct.org/).  

This API contains functions that automatically map between two Java Beans. With MapStruct we only need to create the interface, and the library will automatically create a concrete implementation during compile time.

For the simple Customer java Pojo we use the Customer class that exists in the Customer-Core (Domain side).

##### Creating a DTO
Here we create a Java DTO:

```java
@NoArgsConstructor  
@AllArgsConstructor  
@Data  
public class CustomerDto {  
 private Long id;  
 private Integer age;  
 private String name;  
 private String lastName;  
}
```

##### Mapper Interface

```java
@Mapper(config = BaseMapperConfig.class)  
public interface CustomerRestMapper {  
  CustomerDto toCustomerDto(Customer customer);  
  
  Customer toCustomer(CustomerDto customerDto);  
  
  List<CustomerDto> toCustomerDtoList(Collection<Customer> customers);  
}
```

### Adapter

For this adapter we apply the CQRS pattern so we need create two controllers. One controller to the queries and other to controller the commands.

For CustomerQueryController all we need is to inject the query and the mapper in the implementation:
- `GetCustomerQuery` : The interface that exists in the port in of the customer core to implement reading methods
- `CustomerRestMapper` : Perform mappings between CustomerDto and Customer_

```java

@RestController  
@RequestMapping(path = "/customers")  
@Validated  
public class CustomerQueryController implements ICustomerQueryController {  
 private final GetCustomerQuery query;  
 private final CustomerRestMapper mapper;  
  
@Autowired  
public CustomerQueryController(GetCustomerQuery query, CustomerRestMapper mapper) {  
 this.query = query;  
 this.mapper = mapper;  
  }  
  
@Override  
public ResponseEntity<CustomerDto> findById(@Min(1) Long customerId) throws BaseException {  
Customer customer = this.query.findById(customerId)  
                                      .orElseThrow(() -> new ResourceNotFoundException(customerId));  
  
  CustomerDto customerDto = this.mapper.toCustomerDto(customer);  
 return ResponseEntity.ok(customerDto);  
  }  
  
    @Override  
  public ResponseEntity<Collection<CustomerDto>> findAll() throws BaseException {  
  List<Customer> customerList = this.query.findAll();  
  List<CustomerDto> customerDt = this.mapper.toCustomerDtoList(customerList);  
 return ResponseEntity.ok(customerDto);  
  }  
}

```

