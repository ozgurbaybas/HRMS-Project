package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.JobPosting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {

    List<JobPosting> getByIsActive(boolean isActive, Pageable pageable);
    List<JobPosting> getByIsActive(boolean isActive);
    List<JobPosting> getByIsActive(boolean isActive, Sort sort);
    List<JobPosting> getByIsActiveAndEmployer_Id(boolean isActive, int employerId, Sort sort);

}