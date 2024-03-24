package io.datajek.tennisplayerrest.controller;

import io.datajek.tennisplayerrest.data.dto.request.RegisterRequest;
import io.datajek.tennisplayerrest.data.dto.response.RegisterResponse;
import io.datajek.tennisplayerrest.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    @Autowired
    private RegistrationService service;

    @PostMapping("/registrations")
    public RegisterResponse addRegistration(@RequestBody RegisterRequest request){
        return service.addRegistrationSingle(request);
    }

    @GetMapping("/registrations/{id}")
    public RegisterResponse getRegistration(RegisterRequest request){
        return service.getById(request);
    }

    @GetMapping("/registrations")
    public List<RegisterResponse> getAllRegistrations(){
        return service.getAllRegistrations();
    }

    @PatchMapping("/registrations/{id}")
    public RegisterResponse updateRegistration(@RequestBody RegisterRequest request){
        return service.updateRegistration(request);
    }

    @DeleteMapping("/registrations/{id}")
    public void deleteRegistration(RegisterRequest request){
        service.deleteRegistration(request);
    }
}
