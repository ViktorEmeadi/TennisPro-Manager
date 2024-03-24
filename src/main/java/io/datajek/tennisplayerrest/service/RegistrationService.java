package io.datajek.tennisplayerrest.service;

import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.response.RegisterResponse;
import io.datajek.tennisplayerrest.data.model.Registration;

import java.util.List;

public interface RegistrationService {
    RegisterResponse addRegistrationSingle(RegisterRequest request);
    RegisterResponse addRegistrationDouble(RegisterRequest request);
    List<RegisterResponse> getAllRegistrations();
    RegisterResponse getById(RegisterRequest request);
    RegisterResponse updateRegistration(RegisterRequest request);
    Registration getRegistration(RegisterRequest request);
    void deleteRegistration(RegisterRequest request);

}
