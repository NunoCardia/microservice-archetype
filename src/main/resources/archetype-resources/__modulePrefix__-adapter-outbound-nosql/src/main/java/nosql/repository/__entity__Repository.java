#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.nosql.repository;

import ${package}.nosql.document.${entity}Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${entity}Repository extends MongoRepository<${entity}Document, Long> {
}