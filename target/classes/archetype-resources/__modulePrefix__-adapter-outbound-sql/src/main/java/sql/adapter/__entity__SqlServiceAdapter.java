#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sql.adapter;

import ${package}.core.port.out.${entity}SqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ${entity}SqlServiceAdapter implements ${entity}SqlService {

}
