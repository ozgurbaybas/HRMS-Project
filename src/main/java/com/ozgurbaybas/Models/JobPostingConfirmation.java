package com.ozgurbaybas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "job_posting_confirmations")
public class JobPostingConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "is_confirmed_date")
    private LocalDateTime isConfirmedDate;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private JobPosting jobPosting;

    @ManyToOne()
    @JoinColumn(name = "company_staff_id")
    private CompanyStaff companyStaff;

    public JobPostingConfirmation(JobPosting jobPosting, CompanyStaff companyStaff) {
        this.setJobPosting(jobPosting);
        this.setCompanyStaff(companyStaff);
    }

}