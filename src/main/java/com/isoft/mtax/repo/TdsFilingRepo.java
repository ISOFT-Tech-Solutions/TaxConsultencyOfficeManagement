package com.isoft.mtax.repo;

import com.isoft.mtax.entity.TdsFiling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface TdsFilingRepo extends JpaRepository<TdsFiling,Long> {


    @Query("SELECT f FROM TdsFiling f WHERE f.tdsCustomer.id = :customerId")
    Page<TdsFiling> findFilingByTdsCustomerId(@Param("customerId") Long customerId, Pageable pageable);

    List<TdsFiling> findAllBydueDate(LocalDate dueDate);
    @Query("SELECT f FROM TdsFiling f WHERE f.organization.id = :orgId")
    List<TdsFiling> findTdsFilingByOrgId(Pageable page,@Param("orgId") Long orgId);
}
