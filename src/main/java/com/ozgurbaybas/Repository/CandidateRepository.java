package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Candidate getByIdentityNumber(String identityNumber);
}