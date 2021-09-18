package vn.viaconto.dto.response;

import lombok.Data;
import vn.viaconto.entity.constant.ApplicationStatus;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ApplicationLoanResponseDTO {
    private Integer           id;
    private String            personalId;
    private String            firstName;
    private String            lastName;
    private Date              birthDate;
    private BigDecimal        salary;
    private BigDecimal        monthlyLiability;
    private BigDecimal        requestedAmount;
    private BigDecimal        score;
    private ApplicationStatus status;
    private int               requestedTerm;
}
