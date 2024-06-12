package com.isoft.mtax.entity;

import com.isoft.mtax.dto.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.SpringVersion;

@Entity
@Data

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Customer extends Auditable<String> {
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
