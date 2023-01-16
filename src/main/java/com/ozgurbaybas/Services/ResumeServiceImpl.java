package com.ozgurbaybas.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ozgurbaybas.Core.Utilities.Result.DataResult;
import com.ozgurbaybas.Core.Utilities.Result.Result;
import com.ozgurbaybas.Core.Utilities.Result.SuccessDataResult;
import com.ozgurbaybas.Core.Utilities.Result.SuccessResult;
import com.ozgurbaybas.Repository.ResumeRepository;
import com.ozgurbaybas.Models.Resume;
import org.springframework.data.domain.Sort;
import com.ozgurbaybas.Models.DTO.ResumeWithAllRelatedEntitiesDto;

@Service
public class ResumeServiceImpl implements ResumeService {

    private ResumeRepository resumeRepository;
    private CoverLetterService coverLetterService;
    private ImageService imageService;
    private EducationService educationService;
    private ExperienceService experienceService;

    @Autowired
    public ResumeServiceImpl(ResumeRepository resumeRepository, CoverLetterService coverLetterService, ImageService imageService, EducationService educationService, ExperienceService experienceService) {
        this.resumeRepository = resumeRepository;
        this.coverLetterService = coverLetterService;
        this.imageService = imageService;
        this.educationService = educationService;
        this.experienceService = experienceService;
    }

    @Override
    public Result add(Resume resume) {

        resume.setCreationDate(LocalDate.now());

        resumeRepository.save(resume);
        return new SuccessResult("Resume added.");
    }

    @Override
    public Result update(Resume resume) {

        resume = getById(resume.getId()).getData();
        resume.setCreationDate(LocalDate.now());
        resumeRepository.save(resume);
        return new SuccessResult("Resume updated.");
    }

    @Override
    public Result delete(int id) {

        resumeRepository.deleteById(id);
        return new SuccessResult("Resume deleted.");
    }

    @Override
    public DataResult<List<Resume>> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "creationDate");

        return new SuccessDataResult<List<Resume>>(resumeRepository.findAll(sort));
    }

    @Override
    public DataResult<Resume> getById(int id) {
        return new SuccessDataResult<Resume>(resumeRepository.getById(id));
    }


    @Override
    public Result addCoverLetterToResume(int resumeId, int coverLetterId) {

        Resume resume = getById(resumeId).getData();
        ((Resume) resume).setCoverLetter(coverLetterService.getById(coverLetterId).getData());

        update(resume);
        return new SuccessResult("Cover letter added to resume.");
    }


    @Override
    public DataResult<Resume> getByCandidateId(int candidateId) {
        return new SuccessDataResult<Resume>(resumeRepository.getByCandidate_Id(candidateId));
    }

    @Override
    public DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByCandidateId(int candidateId) {

        Resume resume = getByCandidateId(candidateId).getData();

        ResumeWithAllRelatedEntitiesDto resumeWithAllRelatedEntitiesDto = new ResumeWithAllRelatedEntitiesDto(
                resume.getId(),
                resume.getCreationDate(),
                resume.getCandidate(),
                resume.getCoverLetter(),
                imageService.getByUserId(candidateId).getData(),
                educationService.getAllByResumeIdSortedByGraduationDate(resume.getId()).getData(),
                experienceService.getAllByResumeIdSortedByTerminationDate(resume.getId()).getData(),
                resume.getLanguageLevels(),
                resume.getLinks(),
                resume.getSkills()
        );

        return new SuccessDataResult<ResumeWithAllRelatedEntitiesDto>(resumeWithAllRelatedEntitiesDto);
    }

    @Override
    public DataResult<List<ResumeWithAllRelatedEntitiesDto>> getAllResumesDetailsByActivatedCandidate() {

        List<ResumeWithAllRelatedEntitiesDto> resumes = new ArrayList<ResumeWithAllRelatedEntitiesDto>();

        for (Resume resume : getAll().getData()) {
            if (resume.getCandidate().getUserActivation().isActivated() == true ) {
                resumes.add(getResumeDetailsByCandidateId(resume.getCandidate().getId()).getData());
            }
        };

        return new SuccessDataResult<List<ResumeWithAllRelatedEntitiesDto>>(resumes);
    }

    @Override
    public Result deleteCoverLetterFromResume(int resumeId) {

        Resume resume = getById(resumeId).getData();
        resume.setCoverLetter(null);

        update(resume);
        return new SuccessResult("Cover letter removed from resume.");
    }

}