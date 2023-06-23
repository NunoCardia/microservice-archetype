#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sql.mapper;


import ${package}.sql.config.BaseMapperConfig;
import ${package}.sql.entity.${entity}Entity;
import ${package}.core.domain.${entity}Core;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface ${entity}EntityMapper {

    ${entity}Core ${entity.toLowerCase()}EntityTo${entity}Core(${entity}Entity ${entity.toLowerCase()}Entity);

    ${entity}Entity ${entity.toLowerCase()}CoreTo${entity}Entity(${entity}Core ${entity.toLowerCase()}Core);

}
