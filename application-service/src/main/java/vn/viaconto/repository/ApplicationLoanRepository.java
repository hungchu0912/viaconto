package vn.viaconto.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.viaconto.entity.ApplicationLoan;

@Repository
public interface ApplicationLoanRepository extends PagingAndSortingRepository<ApplicationLoan, Long>, JpaSpecificationExecutor<ApplicationLoan>{
}
