package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.DTO.JobPostingWithEmployerAndJobTitleDto;
import com.ozgurbaybas.Models.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {

    @Query("Select new  com.ozgurbaybas.Models.DTO.JobPostingWithEmployerAndJobTitleDto"
            + "(jp.id, e.companyName, jt.title, jp.numberOfOpenPositions, jp.postingDate, jp.closingDate, jp.isActive) "
            + "From JobPosting jp Inner Join jp.employer e Inner Join jp.jobTitle jt")
    List<JobPostingWithEmployerAndJobTitleDto> getJobPostingWithEmployerAndJobTitleDetailsByIsActive(boolean isActive);

    /*
    @Query("Select new  com.ozgurbaybas.Models.DTO.JobPostingWithEmployerAndJobTitleDto"
            + "(jp.id, e.companyName, jt.title, jp.numberOfOpenPositions, jp.postingDate, jp.closingDate, jp.isActive) "
            + "From JobPosting jp Inner Join jp.employer e Inner Join jp.jobTitle jt")
    List<JobPostingWithEmployerAndJobTitleDto> getJobPostingWithEmployerAndJobTitleDetailsByIsActive(boolean isActive , Sort sort);
     */

    @Query("Select new  com.ozgurbaybas.Models.DTO.JobPostingWithEmployerAndJobTitleDto"
            + "(jp.id, e.companyName, jt.title, jp.numberOfOpenPositions, jp.postingDate, jp.closingDate, jp.isActive) "
            + "From JobPosting jp Inner Join jp.employer e Inner Join jp.jobTitle jt")
    List<JobPostingWithEmployerAndJobTitleDto> getJobPostingWithEmployerAndJobTitleDetailsByIsActiveAndEmployer_EmployerId(boolean isActive, int employerId);

}