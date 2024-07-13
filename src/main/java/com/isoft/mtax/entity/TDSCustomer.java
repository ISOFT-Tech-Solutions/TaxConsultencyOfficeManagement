package com.isoft.mtax.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("TDS")
@Data
public class TDSCustomer extends Customer implements Serializable {

    private String tanNumber;

}
