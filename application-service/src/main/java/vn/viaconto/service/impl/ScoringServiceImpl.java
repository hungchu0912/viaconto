package vn.viaconto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.viaconto.entity.ApplicationLoan;
import vn.viaconto.entity.constant.ApplicationStatus;
import vn.viaconto.service.ScoringService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class ScoringServiceImpl implements ScoringService {

    //Score =
    // (Sum of first name letter positions in the alphabet (a = 1, z = 26))
    // + Salary * 1.5
    // - MonthlyLiability * 3
    // + (year of birth)
    // - (month of birth)
    // - (Julian day of the year of birth (1st Feb = 32, etc.))
    @Override
    public void calculateScoreAndStatus(ApplicationLoan loanEntity) {
        BigDecimal firstNameSum    = BigDecimal.valueOf(loanEntity.getFirstName().chars().map(i -> i - 96).sum());
        BigDecimal salaryScore     = loanEntity.getSalary().multiply(BigDecimal.valueOf(1.5));
        BigDecimal mothlyLiability = loanEntity.getMonthlyLiability().multiply(BigDecimal.valueOf(3));
        LocalDate  localBirthDate  = loanEntity.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        BigDecimal year            = BigDecimal.valueOf(localBirthDate.getYear());
        BigDecimal month           = BigDecimal.valueOf(localBirthDate.getMonthValue());
        BigDecimal day             = BigDecimal.valueOf(localBirthDate.getDayOfYear());
        BigDecimal result          = firstNameSum.add(salaryScore).subtract(mothlyLiability).add(year).subtract(month).subtract(day);
        loanEntity.setScore(result);
        if (result.compareTo(BigDecimal.valueOf(2500)) < 0) {
            loanEntity.setStatus(ApplicationStatus.REJECTED);
        }
        else if (result.compareTo(BigDecimal.valueOf(3500)) > 0) {
            loanEntity.setStatus(ApplicationStatus.APPROVED);
        }
        else {
            loanEntity.setStatus(ApplicationStatus.MANUAL);
        }
    }
}
