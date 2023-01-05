package com.ozgurbaybas.Services.Adapters.Mernis;


public interface UserCheckService {

    boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, int yearOfBirth);

}