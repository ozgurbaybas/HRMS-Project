package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    Resume getByCandidate_Id(int candidateId);
    Resume getByCoverLetter_Id(int coverLetterId);

}