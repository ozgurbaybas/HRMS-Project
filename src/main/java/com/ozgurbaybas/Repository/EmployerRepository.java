package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    List<Employer> getByUserActivation_IsActivated(boolean isActivated);
    List<Employer> getByUserConfirmations_IsConfirmedAndUserConfirmations_UserConfirmationType_Id(boolean isConfirmed, int userConfirmationTypeId);
    List<Employer> getByUserConfirmations_IsConfirmedAndUserConfirmations_UserConfirmationType_Id(boolean isConfirmed, int userConfirmationTypeId, Sort sort);
    LocalDate now();
}