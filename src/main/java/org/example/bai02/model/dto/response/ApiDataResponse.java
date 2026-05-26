package org.example.bai02.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApiDataResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private T errors;
    private HttpStatus status;
}
