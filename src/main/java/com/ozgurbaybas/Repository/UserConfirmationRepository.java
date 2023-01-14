package com.ozgurbaybas.Repository;

import com.ozgurbaybas.Models.UserConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserConfirmationRepository extends JpaRepository<UserConfirmation, Integer> {
    List<UserConfirmation> getByUser_Id(int userId);
}