package com.ozgurbaybas.Services.Adapters.Mernis;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserCheckManager implements UserCheckService {

    @Override
    public boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, LocalDate dateOfBirth) {
        return true;
    }

}