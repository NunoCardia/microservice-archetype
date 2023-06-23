package it.pkg.rest.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class Error {

    private LocalDateTime timestamp;
    private String message;

}
