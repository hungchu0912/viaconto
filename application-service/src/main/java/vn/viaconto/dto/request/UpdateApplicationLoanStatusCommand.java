package vn.viaconto.dto.request;

import lombok.Data;
import vn.viaconto.entity.constant.ApplicationStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UpdateApplicationLoanStatusCommand {
    @NotNull(message = "status cannot be null")
    ApplicationStatus status;
}
