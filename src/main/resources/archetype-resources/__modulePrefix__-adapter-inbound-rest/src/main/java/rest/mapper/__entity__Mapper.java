#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.mapper;

import ${package}.rest.config.BaseMapperConfig;
import ${package}.core.domain.${entity}Core;
import ${package}.rest.dto.${entity}Request;
import ${package}.rest.dto.${entity}Response;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(config = BaseMapperConfig.class)
public interface ${entity}Mapper {

    ${entity}Core ${entity.toLowerCase()}RequestTo${entity}Core(${entity}Request ${entity.toLowerCase()}Request);

    ${entity}Response ${entity.toLowerCase()}CoreTo${entity}Response(${entity}Core ${entity.toLowerCase()});

    List<${entity}Response> ${entity.toLowerCase()}CoreListTo${entity}ResponseList(List<${entity}Core> ${entity.toLowerCase()}s);


}
