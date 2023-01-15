package com.ozgurbaybas.Repository;


import com.ozgurbaybas.Models.UserActivation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActivationRepository extends JpaRepository<UserActivation, Integer> {

    UserActivation getByCode(String code);
    UserActivation getByUser_Id(int userId);
    List<UserActivation> getByIsActivated(boolean isActivated);

}