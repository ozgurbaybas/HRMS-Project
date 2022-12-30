package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {
}