package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.Experience;
import org.springdoc.core.converters.models.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

    List<Experience> getByResume_Id(int resumeId);
    List<Experience> getByResume_Id(int resumeId, Sort sort);

}