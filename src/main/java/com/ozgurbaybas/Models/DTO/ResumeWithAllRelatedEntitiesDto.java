package com.ozgurbaybas.Models.DTO;

import com.ozgurbaybas.Models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeWithAllRelatedEntitiesDto {

    private int id;
    private LocalDate creationDate;
    private Candidate candidate;
    private CoverLetter coverLetter;
    private Image image;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<LanguageLevel> languageLevels;
    private List<Link> links;
    private List<Skill> skills;

}