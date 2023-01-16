package com.ozgurbaybas.Core.DataAccess;


import com.ozgurbaybas.Core.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByEmail(String email);
}
