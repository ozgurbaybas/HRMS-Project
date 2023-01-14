package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.UpdatedEmployer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdatedEmployerRepository extends JpaRepository<UpdatedEmployer, Integer> {

    UpdatedEmployer getByEmployer_Id(int employerId);

}