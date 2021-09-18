package vn.viaconto.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import vn.viaconto.dto.request.CreateApplicationLoanCommand;
import vn.viaconto.dto.request.UpdateApplicationLoanStatusCommand;
import vn.viaconto.dto.response.ApplicationLoanResponseDTO;
import vn.viaconto.dto.response.PageResponse;
import vn.viaconto.entity.ApplicationLoan;

public interface ApplicationLoanService {
    ApplicationLoanResponseDTO getLoan(Long id);
    ApplicationLoanResponseDTO createLoan(CreateApplicationLoanCommand command);
    PageResponse<ApplicationLoanResponseDTO> getApplicationList(Pageable pageable, Specification<ApplicationLoan> specs);
    ApplicationLoanResponseDTO updateLoan(Long id, CreateApplicationLoanCommand command);
    ApplicationLoanResponseDTO updateStatus(Long id, UpdateApplicationLoanStatusCommand command);
}

