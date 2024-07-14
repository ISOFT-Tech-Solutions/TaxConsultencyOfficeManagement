package com.isoft.mtax.service;

import com.isoft.mtax.dto.FilingDto;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.TdsFiling;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilingService {


    String createFilingsForCustomer(Customer customer, TdsFiling filingRequest);

    Page<FilingDto> findFilingsBasedonCustomer(Long customerId, Pageable pageable);

    TdsFiling findFilingById(Long filingId);

    TdsFiling filingMarkComplete(TdsFiling filing);

    List<FilingDto> dueFilingsData(String due);
}
