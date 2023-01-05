package com.ozgurbaybas.Services.Adapters.Mernis;

import org.springframework.stereotype.Service;

@Service
public class UserCheckManager implements UserCheckService {

    @Override
    public boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, int yearOfBirth) {
        return true;
    }

}