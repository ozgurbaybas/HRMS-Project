package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.Education;
import org.springdoc.core.converters.models.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Integer> {

    List<Education> getByResume_Id(int resumeId);
    List<Education> getByResume_Id(int resumeId, Sort sort);

}