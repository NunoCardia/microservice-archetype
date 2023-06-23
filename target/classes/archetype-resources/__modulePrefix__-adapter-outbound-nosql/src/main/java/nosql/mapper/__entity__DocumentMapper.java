#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.nosql.mapper;

import ${package}.nosql.config.BaseMapperConfig;
import ${package}.nosql.document.${entity}Document;
import ${package}.core.domain.${entity}Core;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface ${entity}DocumentMapper {

    ${entity}Core ${entity.toLowerCase()}DocumentTo${entity}Core(${entity}Document ${entity.toLowerCase()}Document);

    ${entity}Document ${entity.toLowerCase()}CoreTo${entity}Document(${entity}Core ${entity.toLowerCase()}Core);

}
