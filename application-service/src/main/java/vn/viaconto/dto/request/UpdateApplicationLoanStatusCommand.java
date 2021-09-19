package vn.viaconto.dto.request;

import lombok.Data;
import vn.viaconto.entity.constant.ApplicationStatus;

import javax.validation.constraints.NotNull;

@Data
public class UpdateApplicationLoanStatusCommand {
    @NotNull(message = "status cannot be null")
    ApplicationStatus status;
}
