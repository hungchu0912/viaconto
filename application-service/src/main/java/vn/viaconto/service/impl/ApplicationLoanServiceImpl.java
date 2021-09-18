package vn.viaconto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.viaconto.config.DTOMapper;
import vn.viaconto.dto.request.CreateApplicationLoanCommand;
import vn.viaconto.dto.request.UpdateApplicationLoanStatusCommand;
import vn.viaconto.dto.response.ApplicationLoanResponseDTO;
import vn.viaconto.dto.response.PageResponse;
import vn.viaconto.entity.ApplicationLoan;
import vn.viaconto.exception.NotFoundException;
import vn.viaconto.repository.ApplicationLoanRepository;
import vn.viaconto.service.ApplicationLoanService;
import vn.viaconto.service.ScoringService;

@Service
@RequiredArgsConstructor
public class ApplicationLoanServiceImpl implements ApplicationLoanService {

    private static final String LOAN_NOT_FOUND = "Loan not found";

    private final ApplicationLoanRepository applicationLoanRepository;
    private final ScoringService            scoringService;
    private final DTOMapper                 dtoMapper;

    @Override
    public ApplicationLoanResponseDTO getLoan(Long id) {
        ApplicationLoan loanEntity = getApplicationLoan(id);
        return dtoMapper.toApplicationLoanResponseDto(loanEntity);
    }

    @Override
    public ApplicationLoanResponseDTO createLoan(CreateApplicationLoanCommand command) {
        ApplicationLoan loanEntity = dtoMapper.toApplicationLoanEntity(command);
        scoringService.calculateScoreAndStatus(loanEntity);
        return dtoMapper.toApplicationLoanResponseDto(applicationLoanRepository.save(loanEntity));
    }

    @Override
    public PageResponse<ApplicationLoanResponseDTO> getApplicationList(Pageable pageable, Specification<ApplicationLoan> specs) {
        return PageResponse.of(applicationLoanRepository.findAll(specs, pageable).map(dtoMapper::toApplicationLoanResponseDto));
    }

    @Override
    public ApplicationLoanResponseDTO updateLoan(Long id, CreateApplicationLoanCommand command) {
        ApplicationLoan loanEntity = getApplicationLoan(id);
        dtoMapper.updateApplicationLoan(command, loanEntity);
        scoringService.calculateScoreAndStatus(loanEntity);
        return dtoMapper.toApplicationLoanResponseDto(applicationLoanRepository.save(loanEntity));
    }

    @Override
    public ApplicationLoanResponseDTO updateStatus(Long id, UpdateApplicationLoanStatusCommand command) {
        ApplicationLoan loanEntity = getApplicationLoan(id);
        loanEntity.setStatus(command.getStatus());
        return dtoMapper.toApplicationLoanResponseDto(applicationLoanRepository.save(loanEntity));
    }

    private ApplicationLoan getApplicationLoan(Long id) {
        return applicationLoanRepository.findById(id).orElseThrow(() -> new NotFoundException(LOAN_NOT_FOUND));
    }
}
