package com.geekbrains.authservice.controller;

import com.geekbrains.apiservice.RegistrationRequest;
import com.geekbrains.authservice.exception.MarketError;
import com.geekbrains.authservice.repository.UserRepository;
import com.geekbrains.authservice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RegistrationController {

    private final UserRepository userRepository;

    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public ResponseEntity<?> tryToRegistration(@RequestBody RegistrationRequest registrationRequest) {
        boolean isEmpty = userRepository.findByUsername(registrationRequest.getUsername()).isEmpty();
        if (isEmpty) {
            registrationService.save(registrationRequest);
            return new ResponseEntity<>(new MarketError("Registration successful"), HttpStatus.CREATED);
        }
            return new ResponseEntity<>(new MarketError("This user " + registrationRequest.getUsername() + " already exist"), HttpStatus.BAD_REQUEST);
    }
}
