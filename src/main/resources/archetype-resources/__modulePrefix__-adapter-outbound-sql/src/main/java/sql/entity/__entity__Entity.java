#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "${entity.toUpperCase()}")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ${entity}Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "${entity.toLowerCase()}_generator")
    @SequenceGenerator(name = "${entity.toLowerCase()}_generator", sequenceName = "${entity.toLowerCase()}_seq")
    private Long id;
}
