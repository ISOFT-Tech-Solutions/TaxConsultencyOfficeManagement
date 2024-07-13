package com.isoft.mtax.service.impl;

import com.isoft.mtax.dto.FilingDto;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.TdsFiling;
import com.isoft.mtax.exception.ResourceNotFoundException;
import com.isoft.mtax.repo.TdsFilingRepo;
import com.isoft.mtax.service.FilingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FilingServiceImpl implements FilingService {

    @Autowired
    private TdsFilingRepo filingRepo;


    public String createFilingsForCustomer(Customer customer, TdsFiling filingRequest) {


        filingRepo.save(filingRequest);
        return "";
    }

    @Override
    public Page<FilingDto> findFilingsBasedonCustomer(Long customerId, Pageable pageable) {
        Page<TdsFiling> filings=filingRepo.findFilingByTdsCustomerId(customerId,pageable);
      /*  return filings
                .map(filing -> new FilingDto(filing.getId(),filing.isFiled(),filing.getFilingDate(),filing.getDueDate()));*/
        return null;

    }

    @Override
    public TdsFiling findFilingById(Long filingId) {
        return filingRepo.findById(filingId).orElseThrow(()-> new ResourceNotFoundException("Filing Not found "));
    }

    @Override
    public TdsFiling filingMarkComplete(TdsFiling filing) {
        filingRepo.save(filing);
        return filing;
    }

    @Override
    public List<FilingDto> dueFilingsData(String due) {
        LocalDate dueDate =LocalDate.parse(due);
       List<TdsFiling> dueFilings=filingRepo.findAllBydueDate(dueDate);
        /*return dueFilings.stream()
                .map(filing ->new FilingDto(filing.getId(), filing.isFiled(),filing.getFilingDate(),filing.getDueDate()))
                .collect(Collectors.toList());*/
        return null;
    }
}
