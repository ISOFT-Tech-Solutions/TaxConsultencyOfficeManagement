package com.isoft.mtax.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Revnue {


    private Long id;

    private TdsFiling filing;


    private Customer customer;


    private Organization organization;

    @Column(nullable = false)
    private String filingType;

    @Column(nullable = false)
    private BigDecimal baseRate;

    @Column(nullable = false)
    private BigDecimal finalRate;

    @Column(nullable = false)
    private BigDecimal discount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate filingDate;

}
