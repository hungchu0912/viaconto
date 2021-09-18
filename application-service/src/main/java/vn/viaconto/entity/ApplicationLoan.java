package vn.viaconto.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.util.StringUtils;
import vn.viaconto.entity.constant.ApplicationStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

@Entity
@Data
public class ApplicationLoan {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              id;
    private String            personalId;
    @Setter(AccessLevel.NONE)
    private String            firstName;
    private String            lastName;
    private Date              birthDate;
    private BigDecimal        salary;
    private BigDecimal        monthlyLiability;
    private BigDecimal        requestedAmount;
    private int               requestedTerm;
    private BigDecimal        score;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    public void setFirstName(String firstName) {
        if (!StringUtils.isEmpty(firstName)) {
            this.firstName = firstName.toLowerCase();
        } else {
            this.firstName = firstName;
        }
    }
}
