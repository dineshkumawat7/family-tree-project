package com.ebit.tree.family.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldValidationErrorResponse<T> {
    private LocalDateTime timestamp;
    private String status;
    private String message;
    private T fields;
}
