package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    List<Employer> getByIsActivatedAndIsConfirmed(boolean isActivated, boolean isConfirmed);

}