package com.example.demo.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpHeaders;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString
public class DataNotFoundException extends Exception {

    private String message;
    /**
     * @param message Exception message
     */
    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
