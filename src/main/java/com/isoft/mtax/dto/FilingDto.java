package com.isoft.mtax.dto;

import com.isoft.mtax.entity.TDSCustomer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilingDto {
    private Long id;
    private boolean filed;
    private LocalDate filingDate;
    private LocalDate dueDate;


}
