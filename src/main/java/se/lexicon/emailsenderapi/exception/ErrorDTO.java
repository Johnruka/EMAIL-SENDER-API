package se.lexicon.emailsenderapi.exception;

import java.time.LocalDateTime;

public class ErrorDTO {

    private Integer errorCode;
    private String errorMessage;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorDTO(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
