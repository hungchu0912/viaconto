package vn.viaconto.controller;

import com.sipios.springsearch.anotation.SearchSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.viaconto.dto.request.CreateApplicationLoanCommand;
import vn.viaconto.dto.request.UpdateApplicationLoanStatusCommand;
import vn.viaconto.dto.response.ApplicationLoanResponseDTO;
import vn.viaconto.dto.response.PageResponse;
import vn.viaconto.entity.ApplicationLoan;
import vn.viaconto.service.ApplicationLoanService;

import javax.validation.Valid;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class ApplicationController {

    private static final String HAS_VIACONTO_SCOPE = "hasAuthority('SCOPE_viaconto')";

    private final ApplicationLoanService applicationLoanService;

    @GetMapping
    @PreAuthorize(HAS_VIACONTO_SCOPE)
    public PageResponse<ApplicationLoanResponseDTO> getLoans(Pageable pageable, @SearchSpec Specification<ApplicationLoan> specs) {
        return applicationLoanService.getApplicationList(pageable, specs);
    }

    @GetMapping("/{id}")
    @PreAuthorize(HAS_VIACONTO_SCOPE)
    public ApplicationLoanResponseDTO getLoanWithId(@PathVariable Long id) {
        return applicationLoanService.getLoan(id);
    }

    @PostMapping
    @PreAuthorize(HAS_VIACONTO_SCOPE)
    public ApplicationLoanResponseDTO createLoan(@RequestBody @Valid CreateApplicationLoanCommand command) {
        return applicationLoanService.createLoan(command);
    }

    @PutMapping("/{id}")
    @PreAuthorize(HAS_VIACONTO_SCOPE)
    public ApplicationLoanResponseDTO updateLoan(@PathVariable Long id, @RequestBody @Valid CreateApplicationLoanCommand command) {
        return applicationLoanService.updateLoan(id, command);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('SCOPE_viaconto') and hasRole('ROLE_update_loan_status')")
    public ApplicationLoanResponseDTO updateLoanStatus(@PathVariable Long id, @RequestBody @Valid UpdateApplicationLoanStatusCommand command) {
        return applicationLoanService.updateStatus(id, command);
    }
}
