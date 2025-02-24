package com.isi.dev.tpparametrage.registration;

import java.util.List;

public interface RegistrationService {
    RegistrationResponse addRegistration(RegistrationRequest request);
    RegistrationResponse getRegistrationById(Long id);
    List<RegistrationResponse> getAllRegistrations();
    RegistrationResponse updateRegistration(RegistrationRequest request);
    void deleteRegistrationById(Long id);
}