package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Candidate getByIdentityNumber(String identityNumber);
    List<Candidate> getByIsActivated(boolean isActivated);
}