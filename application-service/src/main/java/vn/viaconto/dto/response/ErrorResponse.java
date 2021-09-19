package vn.viaconto.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ErrorResponse {
    private int            status;
    private OffsetDateTime timestamp;
    private String         errorMessage;
}
