package com.ozgurbaybas.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cover_letter_id")
    private CoverLetter coverLetter;

    @JsonIgnore
    @OneToMany(mappedBy = "resume")
    private List<Education> educations;

    @JsonIgnore
    @OneToMany(mappedBy = "resume")
    private List<Experience> experiences;

    @JsonIgnore
    @OneToMany(mappedBy = "resume")
    private List<LanguageLevel> languageLevels;

    @JsonIgnore
    @OneToMany(mappedBy = "resume")
    private List<Link> links;

    @JsonIgnore
    @OneToMany(mappedBy = "resume")
    private List<Skill> skills;

}