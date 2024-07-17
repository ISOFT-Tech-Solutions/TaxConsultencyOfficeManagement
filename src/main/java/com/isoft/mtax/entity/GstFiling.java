package com.isoft.mtax.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
@Entity
@Table(name = "gst_filing")
public class GstFiling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gst_filing_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer gstCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @Column(name = "filing_period")
    private Date filingPeriod;

    @Column(name = "filing_status")
    private String filingStatus;

    @Column(name = "filing_date")
    private LocalDate filingDate;

    @Column(name = "due_date")
    private Date dueDate;

    private double gstFileCharge;


}
