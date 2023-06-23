#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.nosql.adapter;

import ${package}.core.port.out.${entity}NoSqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ${entity}NoSqlServiceAdapter implements ${entity}NoSqlService {

}
