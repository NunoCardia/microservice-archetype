#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Error {

    private LocalDateTime timestamp;
    private String message;

}
