package vn.viaconto.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateApplicationLoanCommand {
    @NotBlank(message = "personal id cannot be blank")
    private String     personalId;
    @NotBlank(message = "first name cannot be blank")
    private String     firstName;
    private String     lastName;
    @NotNull(message = "birth date cannot be blank")
    private Date       birthDate;
    @NotNull(message = "salary cannot be null")
    private BigDecimal salary;
    @NotNull(message = "monthly liability cannot be null")
    private BigDecimal monthlyLiability;
    @NotNull(message = "requested amount cannot be null")
    private BigDecimal requestedAmount;
    private int        requestedTerm;
}
