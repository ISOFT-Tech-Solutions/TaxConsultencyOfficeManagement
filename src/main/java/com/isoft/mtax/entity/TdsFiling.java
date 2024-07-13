package com.isoft.mtax.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isoft.mtax.dto.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tds_filing")
public class TdsFiling implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tds_filing_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer tdsCustomer;

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
