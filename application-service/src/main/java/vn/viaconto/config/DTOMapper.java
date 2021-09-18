package vn.viaconto.config;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import vn.viaconto.dto.request.CreateApplicationLoanCommand;
import vn.viaconto.dto.response.ApplicationLoanResponseDTO;
import vn.viaconto.entity.ApplicationLoan;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DTOMapper {
    ApplicationLoanResponseDTO toApplicationLoanResponseDto(ApplicationLoan applicationLoan);
    ApplicationLoan toApplicationLoanEntity(CreateApplicationLoanCommand command);
    void updateApplicationLoan(CreateApplicationLoanCommand command, @MappingTarget ApplicationLoan applicationLoan);
}
