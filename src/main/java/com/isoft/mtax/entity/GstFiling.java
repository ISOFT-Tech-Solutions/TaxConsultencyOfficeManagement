package com.isoft.mtax.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "gst_filing")
public class GstFiling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gst_filing_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "filing_period")
    private Date filingPeriod;

    @Column(name = "filing_status")
    private String filingStatus;

    @Column(name = "filing_date")
    private Date filingDate;

    @Column(name = "due_date")
    private Date dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id")
    private Rate rate;

}
