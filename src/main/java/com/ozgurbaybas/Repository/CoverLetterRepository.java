package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.CoverLetter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Integer> {

    List<CoverLetter> getByCandidate_Id(int candidateId);

}