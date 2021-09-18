package vn.viaconto.service;

import vn.viaconto.entity.ApplicationLoan;

public interface ScoringService {
    void calculateScoreAndStatus(ApplicationLoan loanEntity);
}
