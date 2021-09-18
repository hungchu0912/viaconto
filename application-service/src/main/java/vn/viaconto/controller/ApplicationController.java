package vn.viaconto.controller;

import com.sipios.springsearch.anotation.SearchSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.viaconto.dto.request.CreateApplicationLoanCommand;
import vn.viaconto.dto.request.UpdateApplicationLoanStatusCommand;
import vn.viaconto.dto.response.ApplicationLoanResponseDTO;
import vn.viaconto.dto.response.PageResponse;
import vn.viaconto.entity.ApplicationLoan;
import vn.viaconto.service.ApplicationLoanService;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationLoanService applicationLoanService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_viaconto')")
    public PageResponse<ApplicationLoanResponseDTO> getLoans(Pageable pageable, @SearchSpec Specification<ApplicationLoan> specs) {
        return applicationLoanService.getApplicationList(pageable, specs);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_viaconto')")
    public ApplicationLoanResponseDTO getLoanWithId(@PathVariable Long id) {
        return applicationLoanService.getLoan(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_viaconto')")
    @PostMapping
    public ApplicationLoanResponseDTO createLoan(@RequestBody @Valid CreateApplicationLoanCommand command, Principal principal) {
        return applicationLoanService.createLoan(command);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_viaconto')")
    public ApplicationLoanResponseDTO updateLoan(@PathVariable Long id, @RequestBody @Valid CreateApplicationLoanCommand command) {
        return applicationLoanService.updateLoan(id, command);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('SCOPE_viaconto') and hasRole('ROLE_update_loan_status')")
    public ApplicationLoanResponseDTO updateLoanStatus(@PathVariable Long id, @RequestBody @Valid UpdateApplicationLoanStatusCommand command) {
        return applicationLoanService.updateStatus(id, command);
    }
}
