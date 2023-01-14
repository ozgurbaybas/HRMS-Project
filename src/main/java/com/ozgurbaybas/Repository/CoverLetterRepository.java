package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.CoverLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Integer> {

    CoverLetter getByCandidate_Id(int candidateId);

}