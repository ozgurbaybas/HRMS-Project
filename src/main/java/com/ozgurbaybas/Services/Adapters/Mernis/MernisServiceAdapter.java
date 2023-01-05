package com.ozgurbaybas.Services.Adapters.Mernis;

import org.springframework.stereotype.Service;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

import java.rmi.RemoteException;

@Service
public class MernisServiceAdapter {
    public boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, int yearOfBirth) {

        KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();

        boolean result = false;

        try {
            result = kpsPublicSoapProxy.TCKimlikNoDogrula(
                    Long.parseLong(identityNumber),
                    firstName.toUpperCase(),
                    lastName.toUpperCase(),
                    yearOfBirth);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return result;
    }
}
