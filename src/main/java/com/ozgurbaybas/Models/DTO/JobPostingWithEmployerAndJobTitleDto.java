package com.ozgurbaybas.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingWithEmployerAndJobTitleDto {

    private int id;
    private String companyName;
    private String title;
    private int numberOfOpenPositions;
    private LocalDate postingDate;
    private LocalDate closingDate;
    private boolean isActive;

}