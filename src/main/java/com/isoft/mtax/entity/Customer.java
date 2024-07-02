package com.isoft.mtax.entity;

import com.isoft.mtax.dto.Auditable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.core.SpringVersion;
@MappedSuperclass

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="customer_name")
    private String customerName;
    @Column(name = "pan")
    private String pan;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "phone_no")
    private String phoneNo;



}
